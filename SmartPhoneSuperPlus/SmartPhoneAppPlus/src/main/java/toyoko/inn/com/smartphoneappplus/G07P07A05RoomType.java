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
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G07P07A05RoomType extends Activity {

    private RadioButton la_single;
    private RadioButton la_double;
    private RadioButton la_join;
    private G01P01ParcelableData obj_g01;
    private Button la_back;
    private Button la_submit_button;

    private String ROOM_TYPE_CODE;
    private String NUMBER_OF_HOTEL;
    private String NUMBER_OF_REMAIN_HOTEL;
    private String CHECK_IN_DATE;
    private String NUMBER_OF_NIGHT;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String SMOKING_FLAG;
    private String LATITUDE;
    private String LONGITUDE;
    private String DISTANCE;
    private String MEMBERSHIP_FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G07P07A05RoomType------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g07_p07_room_type);

        // Initialization for Constraint Setup
        InitialSetupConfiguration();

        //Get Data Form G06P06
        G07P07_GetDataFrom_G06_G06();

        //Setup Data To View
        SetupDataToView();

        //Back To Filter Page
        G07P07_backTo_G06P06();

        //GoTo Filter Page
        G07P07_goToFilterPage();

    }

    private void G07P07_goToFilterPage() {
        la_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setRdRoomTypeCode(ROOM_TYPE_CODE);
                obj_g01.setNumberOfRemainHotel(NUMBER_OF_REMAIN_HOTEL);
                goTo(G06P06A05FilterActivity.class, "back");
            }
        });
    }

    private void SetupDataToView() {

        if(!ROOM_TYPE_CODE.isEmpty()){
            if(ROOM_TYPE_CODE.contentEquals("S")){
               la_single.setChecked(true);
               la_double.setChecked(false);
               la_join.setChecked(false);
            }else  if(ROOM_TYPE_CODE.contentEquals("SW")){
                la_single.setChecked(false);
                la_double.setChecked(true);
                la_join.setChecked(false);
            }else{
                la_single.setChecked(false);
                la_double.setChecked(false);
                la_join.setChecked(true);
            }
        }

        if(!NUMBER_OF_HOTEL.isEmpty() && !NUMBER_OF_REMAIN_HOTEL.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("ホテル");
            sb.append(NUMBER_OF_REMAIN_HOTEL);
            sb.append("/");
            sb.append(NUMBER_OF_HOTEL);
            sb.append("軒 表示");
            la_submit_button.setText(sb.toString());
        }
    }

    private void G07P07_goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            goTo(ComActivity.class,"next");
        }
    }


    private void G07P07_backTo_G06P06() {
        la_back = (Button)findViewById(R.id.g07_p07_back);
        la_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish("back");
            }
        });
    }

    //Retrieve Data From G01
    private void G07P07_GetDataFrom_G06_G06() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getNumberOfHotel().isEmpty()) {
                NUMBER_OF_HOTEL = obj_g01.getNumberOfHotel();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getNumberOfRemainHotel().isEmpty()) {
                NUMBER_OF_REMAIN_HOTEL   = obj_g01.getNumberOfRemainHotel();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
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
        }
    }

    private void InitialSetupConfiguration() {
        this.NUMBER_OF_REMAIN_HOTEL = new String();
        this.NUMBER_OF_HOTEL = new String();
        this.CHECK_IN_DATE = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.SMOKING_FLAG = new String();
        this.LATITUDE = new String();
        this.LONGITUDE = new String();
        this.ROOM_TYPE_CODE = new String();
        this.DISTANCE = new String();
        this.MEMBERSHIP_FLAG = new String();


        la_single = (RadioButton)findViewById(R.id.g07p07_single_room);
        la_double=(RadioButton)findViewById(R.id.g07p07_double_room);
        la_join=(RadioButton)findViewById(R.id.g07p07_join_room);
        la_back = (Button)findViewById(R.id.g07_p07_back);
        la_submit_button = (Button)findViewById(R.id.p07_p07_goto_hotel_list);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.g07p07_single_room:
                if (checked)
                    ROOM_TYPE_CODE="S";
                    la_single.setChecked(true);
                    la_double.setChecked(false);
                    la_join.setChecked(false);
                    break;
            case R.id.g07p07_double_room:
                if (checked)
                    ROOM_TYPE_CODE="SW";
                    la_single.setChecked(false);
                    la_double.setChecked(true);
                    la_join.setChecked(false);
                    break;
            case R.id.g07p07_join_room:
                if (checked)
                    ROOM_TYPE_CODE="SWK";
                    la_single.setChecked(false);
                    la_double.setChecked(false);
                    la_join.setChecked(true);
                    break;
        }
        G07P07_goToJsonParse();
    }



    //JSON Parse Data
    //----------------------------------------------------------------------------------------------
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
            Log.e("PARAM-G07P07", api.getRequestDataA005().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G07P07A05RoomType.this);
            processDialog.setMessage("実施中....");
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA005());
            JSONObject json = jParser.getJSONData(api.getURLA005());
            Log.e("JSON-G07P07", json.toString());
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

}
