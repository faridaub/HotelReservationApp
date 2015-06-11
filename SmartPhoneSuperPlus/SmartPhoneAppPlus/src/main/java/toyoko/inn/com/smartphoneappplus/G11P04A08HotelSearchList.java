package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.G11_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePair;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePairArrayAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMapComparator;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G11P04A08HotelSearchList extends Activity {
    //View Property
    private TextView la_topTitleBar;
    private TextView la_bottomTitleBar;

    //Common Setup
    ListView dataListArray;
    G11_ListViewAdapter adapter;
    JSONArray jsonData = null;
    ArrayList<HashMap<String, String>> dataarraylist;

    //Object
    G01P01ParcelableData obj_g01;

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
    private String CITY_CODE;
    private String HOTEL_NUM;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String HOTEL_CODE;
    private String SMOKING_FLAG;
    private String page_number;
    private String NUM_OF_HOTEL;
    private TextView la_num_hotels;
    private String RSRVSPRSNUID;
    private String MEMBERSHIP_FLAG;
    private ArrayList<String> LS_LATITUDE;
    private ArrayList<String> LS_LONGITUDE;
    private String PAGE_FLAG;
    private String ROOM_TYPE_CODE;
    private TextView la_order;
    private Button la_back_button;
    private Spinner la_country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G11P04A08HotelSearchList------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g11_p04_hotel_search_listview);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //Setup To View
        SetupToView();

        //Order List
        SetupOrderByDistance();

        //==>> Back To Home Page G01
        BackToHomePage();

        //==>> Go To Google Map
        GoToGoogleMap();

        //== Go To Filter Page
        GoToFilterPage();

        //== Check Server Connection
        SetupToJson1();

    }

    private void SetupToView() {

        //membership Card
        String countryCode = new String();
        if (!COUNTRY.isEmpty()) {
            countryCode = COUNTRY;
        } else {
            countryCode = "JPN";
        }

        la_country.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);
        la_country.setPrompt("国籍");
        KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, R.layout.spinner_drop_down_item);
        adapter = ComLib.getCountryKeyValueToAdapter(adapter);
        la_country.setAdapter(adapter);
        la_country.setSelection(adapter.getPosition(countryCode));


        if (PAGE_FLAG != null) {
            if (PAGE_FLAG.contentEquals("G50P26")) {
                la_back_button.setText("ホーム");
            } else if (PAGE_FLAG.contentEquals("G06P06")) {
                la_back_button.setText("ホーム");
            } else if (PAGE_FLAG.contentEquals("G01P01")) {
                la_back_button.setText("ホーム");
            } else if (PAGE_FLAG.contentEquals("G06P09")) {
                la_back_button.setText("戻る");
            } else {
                la_back_button.setText("ホーム");
            }
        }
    }

    private void GoToGoogleMap() {
        if (CheckGPRSConnection()) {
            Button button = (Button) findViewById(R.id.g11_p04_google_map);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SetupToParcel();
                    SetupToJson2();
                }
            });
        } else {
            buildAlertMessageNoGps(null,  ERR_TOP_4);
        }
    }
    

    //Setup Initial Data
    private void InitialSetupConfiguration() {
        dataListArray = (ListView) findViewById(R.id.g11_list_view);
        la_topTitleBar = (TextView) findViewById(R.id.g11_top_title_bar);
        la_bottomTitleBar = (TextView) findViewById(R.id.g11_bottom_title_bar);
        la_num_hotels = (TextView) findViewById(R.id.g11p04_num_hotels);
        la_order = (TextView) findViewById(R.id.g11_p04_ordering);
        la_back_button = (Button) findViewById(R.id.g11_P04_back);
        la_country = (Spinner) findViewById(R.id.g11_p04_spinner);


        dataarraylist = new ArrayList<HashMap<String, String>>();
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
        this.CITY_CODE = new String();
        this.HOTEL_NUM = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.HOTEL_CODE = new String();
        this.SMOKING_FLAG = new String();
        this.page_number = new String();
        this.NUM_OF_HOTEL = new String();
        this.RSRVSPRSNUID = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.PAGE_FLAG = new String();
        this.ROOM_TYPE_CODE = new String();
        this.LS_LATITUDE = new ArrayList<String>();
        this.LS_LONGITUDE = new ArrayList<String>();
    }

    private void SetupToViewAfter() {
        if (!NUM_OF_HOTEL.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(NUM_OF_HOTEL);
            sb.append("軒");
            la_num_hotels.setText(sb.toString());
        }

        StringBuilder numNights = new StringBuilder();
        numNights.append("～");
        numNights.append(NUMBER_OF_NIGHT);
        numNights.append("泊");

        StringBuilder roomPeople = new StringBuilder();
        roomPeople.append(NUMBER_OF_PEOPLE);
        roomPeople.append("名");
        roomPeople.append("X");
        roomPeople.append(NUMBER_OF_ROOM);
        roomPeople.append("室");

        String date_title = new String();
        if (!CHECK_IN_DATE.isEmpty()) {
            date_title = ComLib.dateConvertFormattedDate(CHECK_IN_DATE);
            // date_title = "2014年11月21日";
        }

        //Concatinate Two String
        StringBuilder title = new StringBuilder();
        title.append(date_title);
        title.append(numNights.toString());
        title.append("  ");
        title.append(roomPeople.toString());
        la_topTitleBar.setText(title.toString());

        //Setting Mood
        String mood_title = new String();
        if (MOOD.equalsIgnoreCase(ST_ONE) || MOOD.equalsIgnoreCase(ST_TWO)) {
            if (!dataarraylist.isEmpty()) {
                HOTEL_NUM = String.valueOf(dataarraylist.size());
            }
            StringBuilder str = new StringBuilder();
            str.append("キーワード");
            str.append(":");
            str.append(DESTINATION_KEY);
            str.append(" ");
            mood_title = str.toString();
        } else if (MOOD.equalsIgnoreCase("3")) {
            mood_title = "現在地からの検索";
        } else {
            mood_title = "現在地からの検索";
        }
        la_bottomTitleBar.setText(mood_title);
    }


    //Country
    private AdapterView.OnItemSelectedListener Spinner1_OnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair) la_country.getSelectedItem();
            COUNTRY = item.getKey().toString();
            Toast.makeText(G11P04A08HotelSearchList.this, item.getKey().toString(), Toast.LENGTH_LONG).show();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    private void SetupToParcel() {
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setNumberOfHotel(NUM_OF_HOTEL);
        obj_g01.setCustRsrvsPrsnUid(RSRVSPRSNUID);
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

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

            if (!obj_g01.getCityCode().isEmpty()) {
                CITY_CODE = obj_g01.getCityCode();
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
                page_number = obj_g01.getPageFlag();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getSmokingFlag();
            }

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            //Login Data
            //--------------------------------------------------------------------------------------
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

        }
    }

    private void GoToFilterPage() {
        Button button = (Button) findViewById(R.id.g11_p04_filtering);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G06P06A05FilterActivity.class, COD_NORMAL);

            }
        });
    }

    private void BackToHomePage() {
        la_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAGE_FLAG.contentEquals("G06")) {
                    goTo(G06P09A00SearchByCondition.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G01")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G50P26")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G06P06")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G01P01")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G06P09")) {
                    goTo(G06P09A00SearchByCondition.class, COD_BACK);
                } else if (PAGE_FLAG.contentEquals("G10P15A00MAP")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                }/* else if (PAGE_FLAG.contentEquals("G13P17")) {
                    goTo(G01P01A00DashboardActivity.class, COD_BACK);
                }*/ else {
                    finish(COD_BACK);
                }
            }
        });
    }


    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

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
            api.setRoomType(ROOM_TYPE_CODE);
            Log.e(ComLogMsg.PARAM_G11P04, api.getURLA008().toString());
            Log.e(ComLogMsg.PARAM_G11P04, api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G11P04A08HotelSearchList.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            try {
                CommonJSONParser jParser = new CommonJSONParser();
                jParser.setArrayList(api.getRequestDataA008());
                JSONObject json = jParser.getJSONData(api.getURLA008());
                Log.e(ComLogMsg.JSON_G11P04, json.toString());
                if (json == null) {
                    json = jsonNullCheck(json);
                }

                if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                    errorCode = json.optString(CT_ERRRCODE);
                    errorMessage = json.optString(CT_ERRRMSSG);
                    cancel(true);
                }

                jsonData = json.getJSONArray(LT_HOTEL_LIST);
                NUM_OF_HOTEL = String.valueOf(jsonData.length());

                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);

                    // Store JSON item To Sting Data
                    String hotelName = jsonObject.getString(CT_HTLNAME);
                    String hotelCode = jsonObject.getString(CT_HTLCODE);
                    String mmbrPrc = jsonObject.getString(CT_MMBRPRC);
                    String listPrc = jsonObject.getString(CT_LISTPRC);
                    String offclWebDscntPrc = jsonObject.getString(CT_OFFCLWEBDSCNTPRC);
                    String mmbrOffclWebDscntPrc = jsonObject.getString(CT_MMBROFFCLWEBDSCNTPRC);
                    String hotelImageURL = jsonObject.getString(CT_IMGURL);
                    String numberOfRemainRoom = jsonObject.getString(CT_NMBRRRMS);
                    String distanceRemain = jsonObject.getString(CT_DSTNCCRRNTPSTN);

                    String lttd = jsonObject.getString(CT_LTTD);
                    String lngtd = jsonObject.getString(CT_LNGTD);


                    //Set To HashMap for Mapping Data Item
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_HTLNAME, hotelName);
                    map.put(CT_HTLCODE, hotelCode);
                    map.put(CT_MMBRPRC, mmbrPrc);
                    map.put(CT_LISTPRC, listPrc);
                    map.put(CT_OFFCLWEBDSCNTPRC, offclWebDscntPrc);
                    map.put(CT_MMBROFFCLWEBDSCNTPRC, mmbrOffclWebDscntPrc);
                    map.put(CT_IMGURL, hotelImageURL);
                    map.put(CT_LTTD, lttd);
                    map.put(CT_LNGTD, lngtd);
                    map.put(CT_NMBRRRMS, numberOfRemainRoom);
                    map.put(CT_DSTNCCRRNTPSTN, distanceRemain);
                    map.put("MOOD",MOOD);
                    dataarraylist.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToViewAfter();
            SetupDataToAdapater();
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

    private void SetupOrderByDistance() {
        Button button = (Button) findViewById(R.id.g11_p04_ordering);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(dataarraylist, new ComMapComparator(CT_DSTNCCRRNTPSTN));
                SetupDataToAdapater();
            }
        });
    }

    private void SetupDataToAdapater() {
        adapter = new G11_ListViewAdapter(G11P04A08HotelSearchList.this, dataarraylist);
        dataListArray.setAdapter(adapter);
        dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HOTEL_CODE = (String) adapter.getData(position, CT_HTLCODE);
                String hotelName = (String) adapter.getData(position, CT_HTLNAME);
                String imageUrl = (String) adapter.getData(position, CT_IMGURL);
                String latitude = (String) adapter.getData(position, CT_LTTD);
                String longitude = (String) adapter.getData(position, CT_LNGTD);
                String remainRooms = (String) adapter.getData(position, CT_NMBRRRMS);
                String remainDistance = (String) adapter.getData(position, CT_DSTNCCRRNTPSTN);

                String memberPrice = (String) adapter.getData(position, CT_MMBRPRC);
                String memberDiscPrice = (String) adapter.getData(position, CT_MMBROFFCLWEBDSCNTPRC);
                String listPrice = (String) adapter.getData(position, CT_LISTPRC);
                String listDiscPrice = (String) adapter.getData(position, CT_OFFCLWEBDSCNTPRC);

                obj_g01.setHotelName(hotelName);
                obj_g01.setRdImageURL(imageUrl);
                obj_g01.setHdLatitude(latitude);
                obj_g01.setHdLongitude(longitude);
                obj_g01.setEatchRemainRoom(remainRooms);
                obj_g01.setEatchDistance(remainDistance);
                obj_g01.setEatchMemberPrice(memberPrice);
                obj_g01.setEatchMemberDiscPrice(memberDiscPrice);
                obj_g01.setEatchListPrice(listPrice);
                obj_g01.setEatchDistance(listDiscPrice);

                SetupToParcel();
                goTo(G10P15A07A21HotelInfoScrollView.class, COD_NEXT);
            }
        });
    }

    public class GoogleMapJsonParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public GoogleMapJsonParse() {
            super();
            setApiRequestData_A002();
        }

        private void setApiRequestData_A002() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            Log.e("PARAM2-G11P04", api.getRequestDataA002().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G11P04A08HotelSearchList.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA002());
            JSONObject json = jParser.getJSONData(api.getURLA002());
            if (json == null){
                json = jsonNullCheck(json);
            }
            Log.e("JSON2-G11P04", json.toString());
            if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            try {
                JSONArray jsonData = json.getJSONArray(LT_HOTEL_INFO);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    LS_LATITUDE.add(i, jsonObject.getString(CT_LTTD));
                    LS_LONGITUDE.add(i, jsonObject.getString(CT_LNGTD));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            obj_g01.setLsLatitude(LS_LATITUDE);
            obj_g01.setLsLongitude(LS_LONGITUDE);
            if(CheckGPRSConnection()) {
                goTo(G11P04A08Map.class, COD_NEXT);
            }else{
                buildAlertMessageNoGps(null,  ERR_TOP_4);
            }
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            processDialog.dismiss();
            errorPopup(null, errorMessage);
        }
    }

    public boolean CheckGPRSConnection() {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(G11P04A08HotelSearchList.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g00p00_gprs);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.btn_error_code);
        la_errorCode.setText(getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.btn_error_message);
        la_errorMsg.setText(eMessage);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.gprs_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button yes = (Button) dialog.findViewById(R.id.gprs_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
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

    private void SetupToJson1() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }


    private void SetupToJson2() {
        if (ComLib.isNetworkAvailable(this)) {
            new GoogleMapJsonParse().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
