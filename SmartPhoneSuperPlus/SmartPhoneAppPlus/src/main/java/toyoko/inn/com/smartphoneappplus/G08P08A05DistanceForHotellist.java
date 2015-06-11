package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G08P08A05DistanceForHotellist extends Activity {

    private SeekBar la_progressBar = null;
    private TextView la_totalDistance;
    private G01P01ParcelableData obj_g01;

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
    private Button la_submit;

    private float discrete = 0;
    private float start = 1;
    private float end = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G08P08A05DistanceForHotellist------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g08_p08_distance_hotellist);

        //== Default Configuration
        SetupDefaultConfiguration();

        //<<== Get Data From G01 & G06
        G07P07_GetDataFrom_G06_G06();

        //Setup To view
        SetupDataToView();

        //==Distance Calculation
        G08P08_DistanceCalculation();

        //==Back To Previous Page
        G08P08_BackTo_G24P32();

        //==>Set And Go To Next Page
        G08P08_SetDataAndBack();

        //==>GoTo Filter Page
        G08P08_goToFilterPage();


    }

    private void G08P08_goToFilterPage() {
        la_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G06P06A05FilterActivity.class,ComMsg.COD_BACK);
            }
        });
    }

    private void goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            goTo(ComActivity.class,ComMsg.COD_NEXT);
        }
    }


    private void G08P08_BackTo_G24P32() {
        go(R.id.g08_p08_back,G24P32A19Settings.class,"back","n");
    }

    private void G08P08_SetDataAndBack() {
        go(R.id.g08_p08_setdata,G24P32A19Settings.class,"normal","y");
    }

    private void SetupDefaultConfiguration() {
        la_totalDistance = (TextView)findViewById(R.id.g08_p08_distance_chk);
        la_progressBar = (SeekBar) findViewById(R.id.g08_p08volume_bar);
        la_submit = (Button)findViewById(R.id.g08_p08_setdata);


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
        this.DISTANCE = new String();
    }

    private void ReloadBeforeAction() {
        obj_g01.setDistance(DISTANCE);
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

    private void G08P08_DistanceCalculation() {
        float distance = Float.valueOf(DISTANCE) * 10;
        int currentPos=(int)(Math.round(distance));
        la_progressBar.setProgress(currentPos);
        la_progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                float dis = end - start;
                discrete = (start + ((temp / 100) * dis));
                String data = String.format("%.1f", discrete);
                la_totalDistance.setText(data);
                DISTANCE = data;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                DISTANCE = la_totalDistance.getText().toString();
                goToJsonParse();
            }
        });
    }


    //JSON Parse Data
    //----------------------------------------------------------------------------------------------
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();

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
            Log.e("PARAM-G08P08", api.getRequestDataA005().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G08P08A05DistanceForHotellist.this);
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
            Log.e("JSON-G08P08", json.toString());
            NUMBER_OF_REMAIN_HOTEL = json.optString(ComConstant.CT_NUM_HOTELS);
            if(!NUMBER_OF_REMAIN_HOTEL.contentEquals("0")) {
                if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                    goToErrorPage(json);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupDataToView();
            processDialog.dismiss();
        }
    }

    private void SetupDataToView() {
        if(!DISTANCE.isEmpty()) {
            la_totalDistance.setText(DISTANCE);
        }

        if(!NUMBER_OF_HOTEL.isEmpty() && !NUMBER_OF_REMAIN_HOTEL.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("ホテル");
            sb.append(NUMBER_OF_REMAIN_HOTEL);
            sb.append("/");
            sb.append(NUMBER_OF_HOTEL);
            sb.append("軒 表示");
            la_submit.setText(sb.toString());
        }
    }


    //finish
    //----------------------------------------------------------------------------------------------
    private void finish(final Class myClass, String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase("back")) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase("next")) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    //Go
    //----------------------------------------------------------------------------------------------
    private void go(final int buttonID, final Class myClass, final String forwordState, final String reloadFlag) {
        final Button btn = ((Button) findViewById(buttonID));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reloadFlag.equalsIgnoreCase("y")) {
                    ReloadBeforeAction();
                }
                goTo(myClass, forwordState);
            }
        });
    }

    //goTo
    //----------------------------------------------------------------------------------------------
    private void goTo(final Class myClass, String forwordState) {
        Intent intent = new Intent(getApplicationContext(),myClass);
        intent.putExtra("DATA", obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase("back")) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase("next")) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    //Error Page
    //----------------------------------------------------------------------------------------------
    private void goToErrorPage(JSONObject json) {
        obj_g01.setErrrCode(json.optString(ComConstant.CT_ERRRCODE));
        obj_g01.setErrrMssg(json.optString(ComConstant.CT_ERRRMSSG));
        Intent intent = new Intent(getApplicationContext(), ComActivity.class);
        intent.putExtra("DATA", obj_g01);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }


}