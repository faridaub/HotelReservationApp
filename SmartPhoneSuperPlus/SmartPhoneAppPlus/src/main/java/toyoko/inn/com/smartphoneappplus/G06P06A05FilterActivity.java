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
import android.widget.ToggleButton;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G06P06A05FilterActivity extends Activity {

    //Data Pass Property
    private G01P01ParcelableData obj_g01;
    private TextView la_keyword;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_NIGHT;
    private TextView la_distance_title;
    private TextView la_num_of_nights;
    private ToggleButton la_smoking_flag;
    private TextView la_smoking_taz;
    private String SMOKING_FLAG;
    private String NUMBER_OF_ROOM;
    private int num_nights;
    private int num_peopel_counter;
    private String MEMBERSHIP_FLAG;
    private String LONGITUDE;
    private String LATITUDE;
    private APIs api;
    private String NUMBER_OF_PEOPLE;
    private String DISTANCE;
    private String NUMBER_OF_HOTEL;
    private String NUMBER_OF_REMAIN_HOTEL;
    private Button la_goto_hotel_list;
    private Button la_back;
    private String ROOM_TYPE_CODE;
    private TextView la_room_type;
    private String PAGE_FLAG;

    //button = (Button) findViewById(R.id.p06_p09_goto_hotel_list);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G06P06A05FilterActivity------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g06_p06_filter);

        //==Default Configuration
        InitialSetupConfiguration();

        //==>Get Data From G01 G04 G05
        GetData();

        //==SetupDataToView
        SetupDataToView();

        //==Search From Current Location
        CommonDataMovement();

        //==>GoTo Hotel List
       GoToHotelList_G11P04();

        //<==Back To Home Page
        BackToHomePage_G01P01();

        //Reload Data Before Action
        //Execute Json Parser
        GoToJSonParsar();


    }

    private void SetupDataToView() {
        if(!ROOM_TYPE_CODE.isEmpty()){
            if(ROOM_TYPE_CODE.contentEquals("S")){
                la_room_type.setText("シングル");
            }else  if(ROOM_TYPE_CODE.contentEquals("SW")){
                la_room_type.setText("ダブル");
            }else{
                la_room_type.setText("ツイン");
            }
        }

        if(!NUMBER_OF_REMAIN_HOTEL.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("ホテル");
            sb.append(NUMBER_OF_REMAIN_HOTEL);
            sb.append("/");
            sb.append(NUMBER_OF_HOTEL);
            sb.append("軒 表示");
            la_goto_hotel_list.setText(sb);
        }

        if (!SMOKING_FLAG.isEmpty()) {
            if (SMOKING_FLAG.equalsIgnoreCase("Y")) {
                la_smoking_taz.setText("指摘あり");
                la_smoking_flag.setChecked(true);
            } else {
                la_smoking_taz.setText("指摘なし");
                la_smoking_flag.setChecked(false);
            }
        }

        if (!NUMBER_OF_NIGHT.isEmpty()) {
            la_num_of_nights.setText(NUMBER_OF_NIGHT);
        }

        if (!DISTANCE.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("現在から");
            sb.append(DISTANCE);
            sb.append("km");
            la_distance_title.setText(sb.toString());
        }
    }

    private void GoToJSonParsar() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            Intent intent = new Intent(getApplicationContext(), ComActivity.class);
            intent.putExtra("DATA", obj_g01);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void CommonDataMovement() {
        //==Setup Step1
        Step1RoomType();

        //== Setup Step2
        Step4DistanceCalc();

        //==Setup Setp3
        Setup3NumberOfNights();

        //==Tougle Box
        Step5SmokingFlag();
    }

    private void BackToHomePage_G01P01() {
        la_back = (Button) findViewById(R.id.g06_p06_back);
        la_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(PAGE_FLAG.contentEquals("G07P07")){
                    SetupDataToView();
                    ReloadBeforeAction();
                    goTo(G11P04A08HotelSearchList.class,"normal");
                }else if(PAGE_FLAG.contentEquals("G08P08")){
                    SetupDataToView();
                    ReloadBeforeAction();
                    goTo(G11P04A08HotelSearchList.class,"normal");
                }else{
                    finish("back");
                }

            }
        });

    }

    private void Setup3NumberOfNights() {
        final Button addition = (Button) findViewById(R.id.g06_p06_nights_plus);
        final Button deduction = (Button) findViewById(R.id.g06_p06_nights_minus);
        if (num_nights < 2) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
        }
        if (num_nights > 3) {
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
        }

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_nights++;
                if (num_nights < 4) {
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    addition.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    addition.setEnabled(false);
                    deduction.setEnabled(true);
                }
                NUMBER_OF_NIGHT = String.valueOf(num_nights);
                new JSONParse().execute();
            }
        });


        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_nights--;
                if (num_nights > 1) {
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    deduction.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(false);
                    addition.setEnabled(true);
                }
                NUMBER_OF_NIGHT = String.valueOf(num_nights);
                new JSONParse().execute();
            }
        });
    }

    private void Step5SmokingFlag() {
        la_smoking_flag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SMOKING_FLAG = "N";
                if (isChecked) {
                    Log.d("alarmCheck", "ALARM SET TO TRUE");
                    SMOKING_FLAG = "Y";
                }
                GoToJSonParsar();
            }
        });
    }

    private void GoToHotelList_G11P04() {
        la_goto_hotel_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!NUMBER_OF_REMAIN_HOTEL.contentEquals("0")) {
                    ReloadBeforeAction();
                    goTo(G11P04A08HotelSearchList.class, "normal");
                }else{
                    noHotelFound();
                }
            }
        });
    }

    private void Step1RoomType() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g06_p06_step01_room_type);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupDataToView();
                ReloadBeforeAction();
                goTo(G07P07A05RoomType.class,"next");
            }
        });
    }

    private void Step4DistanceCalc() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g06_p06_step02_distance);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetupDataToView();
                ReloadBeforeAction();
                goTo(G08P08A05DistanceForHotellist.class,"next");
            }
        });
    }

    private void InitialSetupConfiguration() {
        this.MEMBERSHIP_FLAG = new String("N");
        this.CHECK_IN_DATE = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.SMOKING_FLAG = new String();
        this.LONGITUDE = new String();
        this.LATITUDE = new String();
        this.DISTANCE = new String();
        this.NUMBER_OF_REMAIN_HOTEL = new String();
        this.NUMBER_OF_HOTEL = new String();
        this.ROOM_TYPE_CODE = new String();
        this.PAGE_FLAG = new String();

        la_back = (Button)findViewById(R.id.g06_p06_back);
        la_goto_hotel_list = (Button) findViewById(R.id.p06_p06_goto_hotel_list);
        la_keyword = (TextView) findViewById(R.id.g06_p09_keyword);
        la_distance_title = (TextView) findViewById(R.id.g06_p06_distance_title);
        la_num_of_nights = (TextView) findViewById(R.id.g06_p06_number_of_nights);
        la_smoking_flag = (ToggleButton) findViewById(R.id.g06_p06_smoking_flag);
        la_smoking_taz = (TextView) findViewById(R.id.g06_p06_smoking_taz);
        la_room_type = (TextView) findViewById(R.id.g06_p06_room_type);

        api = new APIs();
    }

    //Retrieve Data From G01
    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
            if(!obj_g01.getNumberOfRemainHotel().isEmpty()){
                NUMBER_OF_REMAIN_HOTEL = obj_g01.getNumberOfRemainHotel();
            }

            if(!obj_g01.getRdRoomTypeCode().isEmpty()){
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
                num_nights = Integer.parseInt(NUMBER_OF_NIGHT);
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getNumberOfHotel().isEmpty()) {
                NUMBER_OF_HOTEL = obj_g01.getNumberOfHotel();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getRdSmokingFlag();
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

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }
        }
    }

    private void ReloadBeforeAction() {
        CHECK_OUT_DATE = ComLib.dateMonthDayAdditionUsingDays(CHECK_IN_DATE, NUMBER_OF_NIGHT);
        obj_g01.setPageFlag("G06P06");
        obj_g01.setNumberOfRemainHotel(NUMBER_OF_REMAIN_HOTEL);
        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setNumberOfRoom(NUMBER_OF_ROOM);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setRdSmokingFlag(SMOKING_FLAG);
        obj_g01.setNumberOfHotel(NUMBER_OF_HOTEL);
        obj_g01.setRdRoomTypeCode(ROOM_TYPE_CODE);
    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A005();
        }

        private void setApiRequestData_A005() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setSmkngFlag(SMOKING_FLAG);
            api.setLttd(LATITUDE);
            api.setLngtd(LONGITUDE);
            api.setDstnc(DISTANCE);
            api.setRoomType(ROOM_TYPE_CODE);
            Log.e("PARAM-G06P06", api.getRequestDataA005().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G06P06A05FilterActivity.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA005());
            JSONObject json = jParser.getJSONData(api.getURLA005());
            Log.e("JSON-G06P06", json.toString());
            NUMBER_OF_REMAIN_HOTEL = json.optString(ComConstant.CT_NUM_HOTELS);
            if(!NUMBER_OF_REMAIN_HOTEL.contentEquals("0")) {
                if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                    errorCode = json.optString(ComConstant.CT_ERRRCODE);
                    errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                    processDialog.dismiss();
                    cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupDataToView();
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    // No Hotel Found
    //----------------------------------------------------------------------------------------------
    private void noHotelFound() {
        final Dialog dialog = new Dialog(G06P06A05FilterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g06p06_error);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.no_hotel_code);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private void errorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
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
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }
}
