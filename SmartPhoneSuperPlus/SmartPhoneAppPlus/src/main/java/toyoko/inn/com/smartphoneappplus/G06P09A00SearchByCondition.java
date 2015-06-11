package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ForkJoinTask;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_DSTNCCRRNTPSTN;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRCODE;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRMSSG;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_HTLCODE;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_HTLNAME;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_IMGURL;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_LISTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_MMBROFFCLWEBDSCNTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_MMBRPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_NMBRRRMS;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_OFFCLWEBDSCNTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.LT_HOTEL_LIST;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateConvertFormattedDate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

/*
*     goTo(G09P13A00AccommodationDateBackupPaer1.class,ComMsg.COD_NEXT);
* */
public class G06P09A00SearchByCondition extends Activity {

    //Data Pass Property
    private G01P01ParcelableData obj_g01;
    private TextView la_keyword;
    private TextView la_date_title;
    private TextView la_num_or_rooms;
    private TextView la_num_of_people;
    private ToggleButton la_smoking_flag;
    private TextView la_smoking_taz;

    private String DESTINATION_KEY;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_NIGHT;
    private String NUMBER_OF_PEOPLE;
    private String SMOKING_FLAG;
    private String NUMBER_OF_ROOM;
    private String MOOD;

    private String COUNTRY_CODE;
    private String AREA_CODE;
    private String STATE_CODE;
    private String LATITUDE;
    private String LONGITUDE;
    private String DISTANCE;
    private String MEMBERSHIP_FLAG;

    private int num_room_counter;
    private int num_peopel_counter;
    private String CITY_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G06P09A00SearchByCondition------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g06_p09_search_by_condition);

        //==Default Configuration
        InitialSetupConfiguration();

        //==>Get Data From G01 G04 G05
        GetData();

        //Setup To View
        SetupToView();

        //==Search From Current Location
        CommonDataMovement();

        //==>GoTo Hotel List
        GoToHotelList();

        //<==Back To Home Page
        BackToHomePage();
    }

    private void BackToHomePage() {
        Button button = (Button) findViewById(R.id.g06_p09_back_toppage);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupToParcel();
                obj_g01.setRdSmokingFlag(COD_EMPTY);
                goTo(G01P01A00DashboardActivity.class, COD_NORMAL);
            }
        });
    }


    private void CommonDataMovement() {

        //==Setup Step1
        Step1SearchFromCurrentLoc();

        //== Setup Step2
        Step1AccomodationDate();

        //==Setup Setp3
        Setup3NumberOfRooms();

        //==Setup Step4
        Setup4NumberOfPeople();

        //==Tougle Box
        Step5SmokingFlag();

    }

    private void ButtonONOFF(Button addition, Button deduction, int counter, int max, int min) {
        if (counter <= min) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_off);
        }

        if (counter > min && counter < max) {
            addition.setEnabled(true);
            deduction.setEnabled(true);
            addition.setBackgroundResource(R.drawable.util_on);
            deduction.setBackgroundResource(R.drawable.util_on);
        }

        if (counter >= max) {
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_off);
        }
    }

    private void Setup4NumberOfPeople() {
        final Button addition = (Button) findViewById(R.id.g06_p09_people_plus);
        final Button deduction = (Button) findViewById(R.id.g06_p09_people_minus);
        ButtonONOFF(addition, deduction, num_peopel_counter, 5, 1);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_peopel_counter++;
                ButtonONOFF(addition, deduction, num_peopel_counter, 5, 1);
                NUMBER_OF_PEOPLE = String.valueOf(num_peopel_counter);
                SetupToView();
            }
        });


        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_peopel_counter--;
                ButtonONOFF(addition, deduction, num_peopel_counter, 5, 1);
                NUMBER_OF_PEOPLE = String.valueOf(num_peopel_counter);
                SetupToView();
            }
        });
    }


    private void Setup3NumberOfRooms() {
        final Button addition = (Button) findViewById(R.id.g06_p09_room_plus);
        final Button deduction = (Button) findViewById(R.id.g06_p09_room_minus);
        if (num_room_counter < 2) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_off);
        } else {
            deduction.setEnabled(true);
            deduction.setBackgroundResource(R.drawable.util_on);
        }

        if (num_room_counter > 3) {
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_off);
        }

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_room_counter++;
                if (num_room_counter < 4) {
                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    addition.setBackgroundResource(R.drawable.util_off);
                    deduction.setBackgroundResource(R.drawable.util_on);
                    addition.setEnabled(false);
                    deduction.setEnabled(true);
                }
                NUMBER_OF_ROOM = String.valueOf(num_room_counter);
                SetupToView();
            }
        });


        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_room_counter--;
                if (num_room_counter > 1) {
                    deduction.setBackgroundResource(R.drawable.util_on);
                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    deduction.setBackgroundResource(R.drawable.util_off);
                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(false);
                    addition.setEnabled(true);
                }
                NUMBER_OF_ROOM = String.valueOf(num_room_counter);
                SetupToView();
            }
        });
    }

    private void Step5SmokingFlag() {

        la_smoking_flag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SMOKING_FLAG = ComMsg.COD_Y;
                if (isChecked) {
                    SMOKING_FLAG = ComMsg.COD_N;
                }else{
                    SMOKING_FLAG = ComMsg.COD_Y;
                }
                SetupToView();
            }
        });
    }

    private void GoToHotelList() {
        final Button button = (Button) findViewById(R.id.p06_p09_goto_hotel_list);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupToParcel();
                SetupToJson();
            }
        });
    }

    private void Step1SearchFromCurrentLoc() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g06_p09_step01_current_place);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupToParcel();
                goTo(G04P02A03DestinationSearch.class, ComMsg.COD_NEXT);
            }
        });
    }

    private void Step1AccomodationDate() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g06_p09_step02_accomodation_date);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupToParcel();
                goTo(CaldroidSampleActivity.class, ComMsg.COD_NEXT);
            }
        });
    }

    private void InitialSetupConfiguration() {
        this.DESTINATION_KEY = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.SMOKING_FLAG = new String();
        this.NUMBER_OF_ROOM = new String();
        this.MOOD = new String();

        this.COUNTRY_CODE = new String();
        this.AREA_CODE = new String();
        this.STATE_CODE = new String();
        this.CITY_CODE = new String();
        this.LATITUDE = new String();
        this.LONGITUDE = new String();
        this.DISTANCE = new String();
        this.MEMBERSHIP_FLAG = new String();

        this.la_keyword = (TextView) findViewById(R.id.g06_p09_keyword);
        this.la_date_title = (TextView) findViewById(R.id.g06_p09_date_title);
        this.la_num_or_rooms = (TextView) findViewById(R.id.g06_p09_number_of_rooms);
        this.la_num_of_people = (TextView) findViewById(R.id.g06_p09_number_of_people);
        this.la_smoking_flag = (ToggleButton) findViewById(R.id.g06_p09_smoking_flag);
        this.la_smoking_taz = (TextView) findViewById(R.id.g06_p09_smoking_taz);
    }

    //Retrieve Data From G01
    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
                num_peopel_counter = Integer.parseInt(NUMBER_OF_PEOPLE);
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
                num_room_counter = Integer.parseInt(NUMBER_OF_ROOM);
            }

            if (!obj_g01.getSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getSmokingFlag();
            }

            //Changing Variable
            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getCountryCode().isEmpty()) {
                COUNTRY_CODE = obj_g01.getCountryCode();
            }

            if (!obj_g01.getAreaCode().isEmpty()) {
                AREA_CODE = obj_g01.getAreaCode();
            }

            if (!obj_g01.getStateCode().isEmpty()) {
                STATE_CODE = obj_g01.getStateCode();
            }

            if (!obj_g01.getCityCode().isEmpty()) {
                CITY_CODE = obj_g01.getCityCode();
            }




            if (!obj_g01.getLatitude().isEmpty()) {
                LATITUDE = obj_g01.getLatitude();
            }

            if (!obj_g01.getLongitude().isEmpty()) {
                LONGITUDE = obj_g01.getLongitude();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }
        }
    }

    private void SetupToView() {
        if (!SMOKING_FLAG.isEmpty()) {
            if (SMOKING_FLAG.equalsIgnoreCase(ComMsg.COD_Y)) {
                la_smoking_taz.setText(ComMsg.MSG_APPLIED_N);
                la_smoking_flag.setChecked(false);
            } else {
                la_smoking_taz.setText(ComMsg.MSG_APPLIED_Y);
                la_smoking_flag.setChecked(true);
            }
        }

        if (!DESTINATION_KEY.isEmpty()) {
            la_keyword.setText(DESTINATION_KEY);
        }

        if (!NUMBER_OF_ROOM.isEmpty()) {
            la_num_or_rooms.setText(NUMBER_OF_ROOM);
        }


        if (!NUMBER_OF_PEOPLE.isEmpty()) {
            la_num_of_people.setText(NUMBER_OF_PEOPLE);
        }


        if (!CHECK_IN_DATE.isEmpty() && !NUMBER_OF_NIGHT.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(dateConvertFormattedDate(CHECK_IN_DATE));
            sb.append("～");
            sb.append(NUMBER_OF_NIGHT);
            sb.append("泊");
            la_date_title.setText(sb.toString());
        }
    }

    private void SetupToParcel() {
        obj_g01.setPageFlag("G06");
        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setNumberOfRoom(NUMBER_OF_ROOM);
        obj_g01.setNumberOfPeople(NUMBER_OF_PEOPLE);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setSmokingFlag(SMOKING_FLAG);
        obj_g01.setCountryCode(COUNTRY_CODE);
        obj_g01.setAreaCode(AREA_CODE);
        obj_g01.setStateCode(STATE_CODE);
        obj_g01.setDestinationKey(DESTINATION_KEY);
        obj_g01.setLatitude(LATITUDE);
        obj_g01.setLongitude(LONGITUDE);
        obj_g01.setDistance(DISTANCE);
        obj_g01.setSwitchPage(ST_NULL);
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;
        private ForkJoinTask mTask;

        public JSONParse() {
            super();
            // checkURLValidation();
            setApiRequestData_A008();
        }

        private void checkURLValidation() {
            if (ComLib.isValidURL(api.getURLA008())) {
                errorCode = null;
                errorMessage = ERR_URL;
                cancel(true);
            }
        }

        private void setApiRequestData_A008() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setSmkngFlag(SMOKING_FLAG);
            api.setMood(MOOD);
            api.setKywrd(DESTINATION_KEY);
            api.setLttd(LATITUDE);
            api.setLngtd(LONGITUDE);
            api.setCntryCode(COUNTRY_CODE);
            api.setAreaCode(AREA_CODE);
            api.setSttCode(STATE_CODE);
            api.setCityCode(CITY_CODE);
            api.setDstnc(DISTANCE);
            api.setRoomType(COD_EMPTY);
            Log.e("PARAM_G06P09", api.getURLA008().toString());
            Log.e("PARAM_G06P09", api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G06P09A00SearchByCondition.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA008());
            JSONObject json = jParser.getJSONData(api.getURLA008());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON_G06P09", json.toString());
            if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            goTo(G11P04A08HotelSearchList.class, ComMsg.COD_NEXT);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            processDialog.dismiss();
            if (errorCode.equalsIgnoreCase("BAPI1004")) {
                errorMessage = ERR_NO_HOTEL_FOUND;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void errorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(G06P09A00SearchByCondition.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(ComMsg.getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.json_error_message);
        la_errorMsg.setText(eMessage);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.json_submit);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void finish(String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase(ComMsg.COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void goTo(final Class myClass, String forwordState) {
        Intent intent = new Intent(getApplicationContext(), myClass);
        intent.putExtra(ComMsg.COD_DATA, obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase(ComMsg.COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }
}
