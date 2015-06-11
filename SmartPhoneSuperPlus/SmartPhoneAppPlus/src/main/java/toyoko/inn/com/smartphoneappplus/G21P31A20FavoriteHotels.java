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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.G21P31_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateGetCurrentDate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G21P31A20FavoriteHotels extends Activity {

    private G01P01ParcelableData obj_g01;
    private G21P31_ListViewAdapter adapter;

    private ListView la_dataListArray;

    private ArrayList<HashMap<String, String>> arraylist;


    //Local Data Received
    private String MOOD;
    private String DESTINATION_KEY;
    private String ROOM_TYPE;
    private String LONGITUDE;
    private String LATITUDE;
    private String DISTANCE;
    private String COUNTRY;
    private String COUNTRY_CODE;
    private String AREA;
    private String AREA_CODE;
    private String STATE;
    private String STATE_CODE;
    private String HOTEL_NUM;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String HOTEL_CODE;
    private String SMOKING_FLAG;
    private String PAGE_FLAG;
    private String NUM_OF_HOTEL;

    private String RSRVSPRSNUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G21P31A20FavoriteHotels------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g21_p31_favorites);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //==>> Back To Home Page G01
        BackToHomePage();


        //==Loading Jason Parse Data
        SetupToJson();

    }

    //Setup Initial Data
    private void InitialSetupConfiguration() {
        this.arraylist = new ArrayList<HashMap<String, String>>();
        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.DESTINATION_KEY = new String();
        this.LONGITUDE = new String();
        this.LATITUDE = new String();
        this.DISTANCE = new String();
        this.COUNTRY = new String();
        this.COUNTRY_CODE = new String();
        this.AREA = new String();
        this.AREA_CODE = new String();
        this.STATE = new String();
        this.STATE_CODE = new String();
        this.HOTEL_NUM = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE=new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.HOTEL_CODE = new String();
        this.SMOKING_FLAG = new String();
        this.PAGE_FLAG = new String();
        this.NUM_OF_HOTEL = new String();
        this.RSRVSPRSNUID = new String();
    }

    private void SetupToParcel(){
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setNumberOfHotel(NUM_OF_HOTEL);
        //Set Common Place
        obj_g01.setNumberOfStayNight(ST_ONE);
        obj_g01.setNumberOfRoom(ST_ONE);
        obj_g01.setNumberOfPeople(ST_ONE);
        obj_g01.setCheckinDate(dateGetCurrentDate());
        obj_g01.setPageFlag("G21P31");
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getRoomType().isEmpty()) {
                ROOM_TYPE = obj_g01.getRoomType();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
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

            if (!obj_g01.getCountry().isEmpty()) {
                COUNTRY = obj_g01.getCountry();
            }

            if (!obj_g01.getCountryCode().isEmpty()) {
                COUNTRY_CODE = obj_g01.getCountryCode();
            }

            if (!obj_g01.getArea().isEmpty()) {
                AREA = obj_g01.getArea();
            }

            if (!obj_g01.getAreaCode().isEmpty()) {
                AREA_CODE = obj_g01.getAreaCode();
            }

            if (!obj_g01.getState().isEmpty()) {
                STATE = obj_g01.getState();
            }

            if (!obj_g01.getStateCode().isEmpty()) {
                STATE_CODE = obj_g01.getStateCode();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT =  obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM =obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE =  obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                SMOKING_FLAG =  obj_g01.getRdSmokingFlag();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID =  obj_g01.getCustRsrvsPrsnUid();
            }

        }
    }

    private void BackToHomePage() {
        final Button button = (Button) findViewById(R.id.g21_back_toppage_g01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                if(PAGE_FLAG.equalsIgnoreCase("G01")){
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                }else {
                    goTo(G02P20A01AccountInformation.class, COD_BACK);
                }
            }
        });
    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A020();
        }

        private void setApiRequestData_A020() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPageNmbr(ST_ONE);
            api.setFvrtHtlCode("");
            Log.e("PARAM-G21P31", api.getRequestDataA020().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G21P31A20FavoriteHotels.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA020());
            JSONObject json = jParser.getJSONData(api.getURLA020());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G21P31", json.toString());
            if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            try {
                NUM_OF_HOTEL = json.optString(CT_NMBRMYFVRTS);
                JSONArray jsonData = json.getJSONArray(LT_MYFVRTSINFRMTNLIST);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);

                    // Store JSON item To Sting Data
                    String hotelCode = jsonObject.getString(CT_HTLCODE);
                    String hotelName = jsonObject.getString(CT_HTLNAME);
                    String imgurl = jsonObject.getString(CT_IMGURL);
                    String snglrmprc = jsonObject.getString(CT_SNGLRMPRC);
                    String snglrmprcincldngtax = jsonObject.getString(CT_SNGLRMPRCINCLDNGTAX);
                    String mmbrsnglrmprc = jsonObject.getString(CT_MMBRSNGLRMPRC);
                    String mmbrsnglrmprcincldngtax = jsonObject.getString(CT_MMBRSNGLRMPRCINCLDNGTAX);

                    //Set To HashMap for Mapping Data Item
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_HTLCODE,hotelCode);
                    map.put(CT_HTLNAME, hotelName);
                    map.put(CT_IMGURL, imgurl);
                    map.put(CT_SNGLRMPRC, snglrmprc);
                    map.put(CT_SNGLRMPRCINCLDNGTAX, snglrmprcincldngtax);
                    map.put(CT_MMBRSNGLRMPRC, mmbrsnglrmprc);
                    map.put(CT_MMBRSNGLRMPRCINCLDNGTAX, mmbrsnglrmprcincldngtax);
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            la_dataListArray = (ListView) findViewById(R.id.g21_list_view);
            adapter = new G21P31_ListViewAdapter(G21P31A20FavoriteHotels.this, arraylist);
            la_dataListArray.setAdapter(adapter);
            la_dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HOTEL_CODE = (String) adapter.getData(position, CT_HTLCODE);
                    GoToHotelDetailsPage();
                }

                private void GoToHotelDetailsPage() {
                    SetupToParcel();
                    goTo(G10P15A07A21HotelInfoScrollView.class, COD_NEXT);
                }
            });
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BAPI1004")){
                errorMessage = ERR_FAVORITE_HOTEL;
            }
            errorPopup(null, errorMessage);
        }
    }



    private void errorPopup(String eCode, String eMessage) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(getErrorCode(eCode));
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
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void goTo(final Class myClass, String forwordState) {
        Intent intent = new Intent(getApplicationContext(), myClass);
        intent.putExtra(COD_DATA, obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
