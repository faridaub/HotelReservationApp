package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import toyoko.inn.com.smartphoneappplus.adapter.G11_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;

public class G31P14NewsInformation extends Activity {
    //View Property
    private TextView la_topTitleBar;
    private TextView la_bottomTitleBar;

    //Common Setup
    ListView dataListArray;
    G11_ListViewAdapter adapter;
    JSONArray jsonData = null;
    ArrayList<HashMap<String, String>> arraylist;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G31P14NewsInformation------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g02_account_information);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        G11_GetDataFrom_G01_G06();

        //==>> Back To Home Page G01
        G31_BackToHomePage_G01();

        //==>> Go To Google Map
        G11P04_goToGoogleMap_G03P05();

        //== Go To Filter Page
        G11P04_goToFilterPage_G06P06();

        //==Loading Jason Parse Data
        G11P04_goToJsonParse();

    }

    private void G11P04_goToJsonParse() {
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

    private void G11P04_goToGoogleMap_G03P05() {
        Button button = (Button)findViewById(R.id.g11_p04_google_map);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), G03P05A02LocationMapActivity.class);
                intent.putExtra("DATA", obj_g01);
                startActivityForResult(intent, 1);
                G31P14NewsInformation.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });
    }

    //Setup Initial Data
    private void InitialSetupConfiguration() {
        //View Constant
        la_topTitleBar = (TextView) findViewById(R.id.g11_top_title_bar);
        la_bottomTitleBar = (TextView) findViewById(R.id.g11_bottom_title_bar);
        la_num_hotels   =(TextView)findViewById(R.id.g11p04_num_hotels);

        //Json Array
        arraylist = new ArrayList<HashMap<String, String>>();
        //Internal Constant
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
        this.page_number = new String();
        this.NUM_OF_HOTEL = new String();
    }

    private void ReloadBeforeAction() {

        if(!NUM_OF_HOTEL.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(NUM_OF_HOTEL);
            sb.append("軒");
            la_num_hotels.setText(sb.toString());
        }


        String room_title = new String();
        String date_title = new String();
        if(!ROOM_TYPE.isEmpty()) {
            if (ROOM_TYPE.equalsIgnoreCase("1")) {
                room_title = "1名ｘ1室";
            } else if (ROOM_TYPE.equalsIgnoreCase("2")) {
                room_title = "2名ｘ1室";
            } else if (ROOM_TYPE.equalsIgnoreCase("3")) {
                room_title = "1名ｘ2室";
            } else {
                room_title = "1名ｘ1室";
            }
        }
        if (!CHECK_IN_DATE.isEmpty()) {
            date_title = "2014年11月21日";
        }

        //Concatinate Two String
        StringBuilder title = new StringBuilder();
        title.append(date_title);
        title.append("～1泊");
        title.append("  ");
        title.append(room_title);
        la_topTitleBar.setText(title.toString());

        //Setting Mood
        String mood_title = new String();
        if (MOOD.equalsIgnoreCase("1") || MOOD.equalsIgnoreCase("2")) {
            if(!arraylist.isEmpty()){
                HOTEL_NUM = String.valueOf(arraylist.size());
            }
            StringBuilder str = new StringBuilder();
            str.append("キーワード");
            str.append(":");
            str.append(DESTINATION_KEY);
            str.append(" ");
            str.append(HOTEL_NUM);
            str.append("軒");
            mood_title = str.toString();
        }  else if (MOOD.equalsIgnoreCase("3")) {
            mood_title = "現在地からの検索";
        } else {
            mood_title = "現在地からの検索";
        }
        la_bottomTitleBar.setText(mood_title);

        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setNumberOfHotel(NUM_OF_HOTEL);

    }

    private void G11_GetDataFrom_G01_G06() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
            if (!obj_g01.getRoomType().isEmpty()) {
                ROOM_TYPE = obj_g01.getRoomType();
                //Log.e("DATA-G11P04 ROOMTYPE", obj_g01.getRoomType());
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
                //Log.e("DATA-G11P04 DESTINATION KEY", obj_g01.getDestinationKey());
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
                //Log.e("DATA-G11P04 MOOD", obj_g01.getMood());
            }

            if (!obj_g01.getLatitude().isEmpty()) {
                LATITUDE = obj_g01.getLatitude();
                //Log.e("DATA-G11P04 LATITUDE", obj_g01.getLatitude());
            }

            if (!obj_g01.getLongitude().isEmpty()) {
                LONGITUDE = obj_g01.getLongitude();
                //Log.e("DATA-G11P04 LONGITUDE", obj_g01.getLongitude());
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
                //Log.e("DATA-G11P04 discrete", obj_g01.getDistance());
            }

            if (!obj_g01.getCountry().isEmpty()) {
                COUNTRY = obj_g01.getCountry();
                //Log.e("DATA-G11P04 COUNTRY", obj_g01.getCountry());
            }

            if (!obj_g01.getCountryCode().isEmpty()) {
                COUNTRY_CODE = obj_g01.getCountryCode();
                //Log.e("DATA-G11P04 COUNTRY CODE", obj_g01.getCountryCode());
            }

            if (!obj_g01.getArea().isEmpty()) {
                AREA = obj_g01.getArea();
                //Log.e("DATA-G11P04 AREA", obj_g01.getArea());
            }

            if (!obj_g01.getAreaCode().isEmpty()) {
                AREA_CODE = obj_g01.getAreaCode();
                //Log.e("DATA-G11P04 AREA_CODE", obj_g01.getAreaCode());
            }

            if (!obj_g01.getState().isEmpty()) {
                STATE = obj_g01.getState();
                //Log.e("DATA-G11P04 STATE", obj_g01.getState());
            }

            if (!obj_g01.getStateCode().isEmpty()) {
                STATE_CODE = obj_g01.getStateCode();
                //Log.e("DATA-G11P04 STATE_CODE", obj_g01.getStateCode());
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
                //Log.e("DATA-G11P04 HOTEL_NUM", obj_g01.getHotelNum());
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
                //Log.e("DATA-G11P04 CHECK-IN-DATE", obj_g01.getCheckinDate());
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
                //Log.e("DATA-G11P04 CHECK-OUT-DATE", obj_g01.getCheckoutDate());
            }

            if (!obj_g01.getPageFlag().isEmpty()) {
                //Log.e("DATA-G11P04 PAGE FLAG ", obj_g01.getPageFlag());
                page_number = obj_g01.getPageFlag();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                //Log.e("DATA-G11P04 NUMBER OF NIGHT ", obj_g01.getNumberOfNight());
                NUMBER_OF_NIGHT =  obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                //Log.e("DATA-G11P04 NUMBER OF ROOM ", obj_g01.getNumberOfRoom());
                NUMBER_OF_ROOM =obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                //Log.e("DATA-G11P04 NUMBER OF PEOPLE", obj_g01.getNumberOfPeople());
                NUMBER_OF_PEOPLE =  obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                //Log.e("DATA-G11P04 SMOKING FLAG", obj_g01.getRdSmokingFlag());
                SMOKING_FLAG =  obj_g01.getRdSmokingFlag();
            }

        }
    }

    private void G11P04_goToFilterPage_G06P06() {
        final Button button = (Button) findViewById(R.id.g11p04_filtering);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G06P06A05FilterActivity.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G31P14NewsInformation.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });
    }

    private void G31_BackToHomePage_G01() {
        final Button button = (Button) findViewById(R.id.g31_back_toppage_g01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G01P01A00DashboardActivity.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G31P14NewsInformation.this.overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
            }
        });
    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();

        public JSONParse() {
            super();
            setApiRequestData_A008();
        }

        private void setApiRequestData_A008() {
            api.setMmbrshpFlag("Y");
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setSmkngFlag(SMOKING_FLAG);
            api.setMood(MOOD);
            api.setKywrd(DESTINATION_KEY);
            api.setLttd(LATITUDE);
            api.setLngtd(LONGITUDE);
            api.setDstnc(DISTANCE);
            api.setCntryCode(COUNTRY_CODE);
            api.setAreaCode(AREA_CODE);
            api.setSttCode(STATE_CODE);
            //Log.e("PARAM-G11P04", api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G31P14NewsInformation.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }



        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA008());
            JSONObject json = jParser.getJSONData(api.getURLA008());
            try {
                // Getting JSON Array from URL
                jsonData = json.getJSONArray(ComConstant.LT_HOTEL_LIST);
                if (json == null) {
                    json = jsonNullCheck(json);
                }

                NUM_OF_HOTEL = String.valueOf(jsonData.length());

                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);

                    // Store JSON item To Sting Data
                    String hotelName = jsonObject.getString(ComConstant.CT_HTLNAME);
                    String hotelCode = jsonObject.getString(ComConstant.CT_HTLCODE);
                    String mmbrPrc = jsonObject.getString(ComConstant.CT_MMBRPRC);
                    String listPrc = jsonObject.getString(ComConstant.CT_LISTPRC);
                    String offclWebDscntPrc = jsonObject.getString(ComConstant.CT_OFFCLWEBDSCNTPRC);
                    String mmbrOffclWebDscntPrc = jsonObject.getString(ComConstant.CT_MMBROFFCLWEBDSCNTPRC);
                    String hotelImageURL = jsonObject.getString(ComConstant.CT_IMGURL);
                    String numberOfRemainRoom = jsonObject.getString(ComConstant.CT_NMBRRRMS);
                    String distanceRemain = jsonObject.getString(ComConstant.CT_DSTNCCRRNTPSTN);

                    //Set To HashMap for Mapping Data Item
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_HTLNAME, hotelName);
                    map.put(ComConstant.CT_HTLCODE,hotelCode);
                    map.put(ComConstant.CT_MMBRPRC, mmbrPrc);
                    map.put(ComConstant.CT_LISTPRC, listPrc);
                    map.put(ComConstant.CT_OFFCLWEBDSCNTPRC, offclWebDscntPrc);
                    map.put(ComConstant.CT_MMBROFFCLWEBDSCNTPRC, mmbrOffclWebDscntPrc);
                    map.put(ComConstant.CT_IMGURL, hotelImageURL);
                    map.put(ComConstant.CT_NMBRRRMS,numberOfRemainRoom);
                    map.put(ComConstant.CT_DSTNCCRRNTPSTN,distanceRemain);



                    HashMap<String, String> map2 = ComLib.sortByValues(map);

                    //Set To Array List
                    arraylist.add(map2);

                }
            } catch (JSONException e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ReloadBeforeAction();
            processDialog.dismiss();
            dataListArray = (ListView) findViewById(R.id.g31_list_view);
            adapter = new G11_ListViewAdapter(G31P14NewsInformation.this, arraylist);
            //filterDataByOrder();
            dataListArray.setAdapter(adapter);

            //On Click Listiner
            dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HOTEL_CODE = (String) adapter.getData(position, ComConstant.CT_HTLCODE);
                    //Reload Data Before Action
                    ReloadBeforeAction();
                    //view.setBackgroundResource(R.drawable.listview_image_background);
                    Intent intent = new Intent(getApplicationContext(), G10P15A07A21HotelInfoScrollView.class);
                    intent.putExtra("DATA", obj_g01);
                    startActivity(intent);
                    G31P14NewsInformation.this.overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                }
            });
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
        Intent intent =  intent = new Intent(getApplicationContext(),myClass);
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
        obj_g01.setErrrMssg(json.optString(ComConstant.CT_ERRRMSSG));
        obj_g01.setErrrCode(json.optString(ComConstant.CT_ERRRCODE));
        Intent intent = new Intent(getApplicationContext(), ComActivity.class);
        intent.putExtra("DATA", obj_g01);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }


}
