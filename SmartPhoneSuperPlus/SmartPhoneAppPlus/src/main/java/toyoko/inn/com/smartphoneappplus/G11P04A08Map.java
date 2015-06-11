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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.MapMarkerAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_DSTNCCRRNTPSTN;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRCODE;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRMSSG;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_HTLNAME;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_IMGURL;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_LISTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_MMBROFFCLWEBDSCNTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_MMBRPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_NMBRRRMS;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_OFFCLWEBDSCNTPRC;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.LT_HOTEL_LIST;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.isValidURL;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComLogMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_BACK;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_NEXT;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_EMPTYCHECK;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_NO_HOTEL_FOUND;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_URL;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.MSG_PROCESSING;

public class G11P04A08Map extends Activity {
    private GoogleMap mMap;
    private ArrayList<MapMarkerAdapter> mMapMarkersArrayAdapter;
    private HashMap<Marker, MapMarkerAdapter> mMarkersHashMap;

    //Object
    G01P01ParcelableData obj_g01;
    //View dataArray
    private TextView la_bottomTitleBar;
    private TextView la_topTitleBar;

    private ArrayList<String> LS_LATITUDE;
    private ArrayList<String> LS_LONGITUDE;


    //Local Variable
    //private double LATITUDE = 35.682785;
    //private double LONGITUDE = 139.910954;
    private String MOOD;
    private String ROOM_TYPE;
    private String DESTINATION_KEY;
    private String CHECK_IN_DATE;
    private String HOTEL_NUM;
    private String HOTEL_CODE;
    private String NUMBER_OF_HOTEL;
    private String MEMBERSHIP_FLAG;
    private String NUMBER_OF_NIGHT;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    JSONArray jsonData = null;
    private String SMOKING_FLAG;
    private String LATITUDE;
    private String LONGITUDE;
    private String COUNTRY_CODE;
    private String AREA_CODE;
    private String STATE_CODE;
    private String DISTANCE;
    private String ROOM_TYPE_CODE;
    private String NUM_OF_HOTEL;
    private ArrayList<HashMap<String, String>> dataarraylist;
    private Button la_reset;
    private String CITY_CODE;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G11P04A08Map------------------------------------");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.g11_p04_hotel_search_listview_map);

        //SetupInitialData
        InitialSetupConfiguration();


        //Get Data From G10P15
        GetDataFrom_G10P15();

        //Reload Data Before Page Load
        SetupToView();

        //Reset map
        ResetMap();

        //Json
        SetupToJson();

        //Setup Map
        setUpMapIfNeeded();

        //Go Back To G10P15
        BackToHotelList();


        //Back Previous Page
        BackToHomePage();

    }

    private void BackToHomePage() {
        Button button = (Button) findViewById(R.id.g03p05m_backhome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class,COD_NEXT);
            }
        });
    }

    private void ResetMap() {
        la_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    private void BackToHotelList() {
        Button button = (Button) findViewById(R.id.g11p04m_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void InitialSetupConfiguration() {
        //Set Constant
        this.mMapMarkersArrayAdapter = new ArrayList<MapMarkerAdapter>();
        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.DESTINATION_KEY = new String();
        this.CHECK_IN_DATE = new String();
        this.HOTEL_NUM = new String();
        this.NUMBER_OF_HOTEL = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.LS_LATITUDE = new ArrayList<String>();
        this.LS_LONGITUDE = new ArrayList<String>();
        this.PAGE_FLAG = new String();

        this.SMOKING_FLAG = new String();
        this.LATITUDE = new String();
        this.LONGITUDE = new String();
        this.COUNTRY_CODE = new String();
        this.AREA_CODE = new String();
        this.STATE_CODE = new String();
        this.CITY_CODE = new String();
        this.DISTANCE = new String();
        this.ROOM_TYPE_CODE = new String();
        this.NUM_OF_HOTEL = new String();



        //Views
        la_topTitleBar = (TextView) findViewById(R.id.g03_p05_top_title_bar);
        la_bottomTitleBar = (TextView) findViewById(R.id.g03_p05_bottom_title_bar);
        la_reset = (Button) findViewById(R.id.g11_reset);
        mMarkersHashMap = new HashMap<Marker, MapMarkerAdapter>();
        dataarraylist = new ArrayList<HashMap<String, String>>();

    }

    private void GetDataFrom_G10P15() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getNumberOfHotel().isEmpty()) {
                NUMBER_OF_HOTEL = obj_g01.getNumberOfHotel();
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

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }
            if (!obj_g01.getRoomType().isEmpty()) {
                ROOM_TYPE = obj_g01.getRoomType();
            }
            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getLatitude().isEmpty()) {
                LATITUDE = obj_g01.getLatitude();
            }

            if (!obj_g01.getLongitude().isEmpty()) {
                LONGITUDE = obj_g01.getLongitude();
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

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }

            if (!obj_g01.getLsLatitude().isEmpty()) {
                LS_LATITUDE = obj_g01.getLsLatitude();
            }

            if (!obj_g01.getLsLongitude().isEmpty()) {
                LS_LONGITUDE = obj_g01.getLsLongitude();
            }
        }

    }
    private void SetupToView() {
        //Top Level
        StringBuilder top_level_title = new StringBuilder();
        if (CHECK_IN_DATE != null) {
            top_level_title.append(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        } else {
            top_level_title.append(" ");
        }

        top_level_title.append("～");
        if (NUMBER_OF_NIGHT != null) {
            top_level_title.append(NUMBER_OF_NIGHT);
        } else {
            top_level_title.append("0");
        }
        top_level_title.append("泊");
        top_level_title.append("  ");
        if (NUMBER_OF_PEOPLE != null) {
            top_level_title.append(NUMBER_OF_PEOPLE);
        } else {
            top_level_title.append("0");
        }
        top_level_title.append("名");

        if (NUMBER_OF_ROOM != null) {
            top_level_title.append(NUMBER_OF_ROOM);
        } else {
            top_level_title.append("0");
        }
        top_level_title.append("室");
        la_topTitleBar.setText(top_level_title.toString());


        //Bottom Level
        StringBuilder bottom_level_title = new StringBuilder();
        if (MOOD.equalsIgnoreCase("1") || MOOD.equalsIgnoreCase("2")) {
            bottom_level_title.append("キーワード");
            bottom_level_title.append(":");
            if (DESTINATION_KEY != null) {
                bottom_level_title.append(DESTINATION_KEY);
            } else {
                bottom_level_title.append("");
            }
        } else if (MOOD.equalsIgnoreCase("3")) {
            bottom_level_title.append("現在地からの検索");
        } else {
            bottom_level_title.append("現在地からの検索");
        }

        bottom_level_title.append(" ");
        if (NUMBER_OF_HOTEL != null) {
            bottom_level_title.append(NUMBER_OF_HOTEL);
        } else {
            bottom_level_title.append("0");
        }
        bottom_level_title.append("軒");
        la_bottomTitleBar.setText(bottom_level_title.toString());
    }




    private void setUpMapIfNeeded() {
        setUpMap();
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A008();
        }

        private void checkURLValidation() {
            if (isValidURL(api.getURLA008())) {
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
            Log.e(PARAM_G11P04MAP, api.getURLA008().toString());
            Log.e(PARAM_G11P04MAP, api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G11P04A08Map.this);
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
                if (json == null) {
                    json = jsonNullCheck(json);
                }

                Log.e(JSON_G11P04MAP, json.toString());

                if (json == null) {
                    errorCode = json.optString(CT_ERRRCODE);
                    errorMessage = json.optString(CT_ERRRMSSG);
                    cancel(true);
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
                    String mmbrOffclWebDscntPrc = jsonObject.getString(CT_MMBROFFCLWEBDSCNTPRC);
                    String listPrc = jsonObject.getString(CT_LISTPRC);
                    String offclWebDscntPrc = jsonObject.getString(CT_OFFCLWEBDSCNTPRC);
                    String hotelImg = jsonObject.getString(CT_IMGURL);

                    String lttd = jsonObject.getString(CT_LTTD);
                    String lngtd = jsonObject.getString(CT_LNGTD);
                    String numberOfRemainRoom = jsonObject.getString(CT_NMBRRRMS);
                    String distanceRemain = jsonObject.getString(CT_DSTNCCRRNTPSTN);


                    mMapMarkersArrayAdapter.add(new MapMarkerAdapter(hotelName,hotelCode, hotelImg, Double.parseDouble(lttd), Double.parseDouble(lngtd), mmbrPrc, mmbrOffclWebDscntPrc, listPrc, offclWebDscntPrc,numberOfRemainRoom,distanceRemain));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            plotMarkers(mMapMarkersArrayAdapter);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            processDialog.dismiss();
            if(errorCode.equalsIgnoreCase("BAPI1004")){
                errorMessage = ERR_NO_HOTEL_FOUND;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void plotMarkers(ArrayList<MapMarkerAdapter> markers) {
        if (markers.size() > 0) {
            for (MapMarkerAdapter mapMarkerAdapter : markers) {
                LatLng myCoordinates = new LatLng(mapMarkerAdapter.getmLatitude(), mapMarkerAdapter.getmLongitude());
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 17.0f);
                mMap.animateCamera(yourLocation);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(myCoordinates)      // Sets the center of the map to LatLng (refer to previous snippet)
                        .zoom(13)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(mapMarkerAdapter.getmLatitude(), mapMarkerAdapter.getmLongitude()));
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel_building));

                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, mapMarkerAdapter);
                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }

    private void setUpMap() {
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.g11p04_map)).getMap();
            if (mMap != null) {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            } else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }

        //OnClick Info Window Listener...
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                MapMarkerAdapter mapMarkerAdapter = mMarkersHashMap.get(marker);
                obj_g01.setHotelCode(mapMarkerAdapter.getmHotelCode());
                obj_g01.setHotelCodeNew(mapMarkerAdapter.getmHotelCode());
                obj_g01.setRdImageURL(mapMarkerAdapter.getmHotelImage());
                obj_g01.setEatchMemberPrice(mapMarkerAdapter.getMemberPrice());
                obj_g01.setEatchMemberDiscPrice(mapMarkerAdapter.getMemberDiscPrice());
                obj_g01.setEatchListPrice(mapMarkerAdapter.getListPrice());
                obj_g01.setEatchListDiscPrice(mapMarkerAdapter.getListDiscPrice());
                obj_g01.setLatitude(String.valueOf(mapMarkerAdapter.getmLatitude()));
                obj_g01.setLongitude(String.valueOf(mapMarkerAdapter.getmLongitude()));
                obj_g01.setEatchRemainRoom(mapMarkerAdapter.getmRematinRoom());
                obj_g01.setEatchDistance(mapMarkerAdapter.getmDistance());

                if(PAGE_FLAG.equalsIgnoreCase("G01")) {
                    obj_g01.setPageFlag("G01_G10M");
                }else if(PAGE_FLAG.equalsIgnoreCase("G06")) {
                    obj_g01.setPageFlag("G06_G10M");
                }
                goTo(G10P15A07A21HotelInfoScrollView.class, COD_BACK);
            }
        });

    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private ImageLoader imageLoader;
        public MarkerInfoWindowAdapter() {
            imageLoader = new ImageLoader(G11P04A08Map.this);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v = getLayoutInflater().inflate(R.layout.g11_p04_maplist, null);
            MapMarkerAdapter mapMarkerAdapter = mMarkersHashMap.get(marker);
            TextView hotelName = (TextView) v.findViewById(R.id.g11m_hotel_name);
            hotelName.setText(mapMarkerAdapter.getmHotelName());

            TextView maxMemberPrice = (TextView) v.findViewById(R.id.g11m_mmbrPrc);
            maxMemberPrice.setText(mapMarkerAdapter.getMemberPrice());

            TextView minMemberPrice = (TextView) v.findViewById(R.id.g11m_mmbrOffclWebDscntPrc);
            minMemberPrice.setText(mapMarkerAdapter.getMemberPrice());

            TextView maxListPrice = (TextView) v.findViewById(R.id.g11m_listPrc);
            maxListPrice.setText(mapMarkerAdapter.getMemberPrice());

            TextView minListPrice = (TextView) v.findViewById(R.id.g11m_offclWebDscntPrc);
            minListPrice.setText(mapMarkerAdapter.getMemberPrice());

            TextView distance = (TextView) v.findViewById(R.id.g11p04m_current_distance);
            StringBuilder sb_dis = new StringBuilder();

            sb_dis.append("現在地から ");
            sb_dis.append(mapMarkerAdapter.getmDistance());
            distance.setText(sb_dis.toString());

            TextView remainRooom = (TextView) v.findViewById(R.id.g11p04m_nmbr_of_remans_room);
            StringBuilder sb = new StringBuilder();
            if(Integer.valueOf(mapMarkerAdapter.getmRematinRoom())<10) {
                sb.append("残り");
                sb.append(mapMarkerAdapter.getmRematinRoom());
                sb.append("室");
            }else{
                sb.append("空室有り");
            }
            remainRooom.setText(sb.toString());


            int loader = R.drawable.no_image;
            ImageView image = (ImageView) v.findViewById(R.id.map_hotel_image);
            imageLoader.DisplayImage(mapMarkerAdapter.getmHotelImage(), loader, image);
            return v;
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
        Intent intent = intent = new Intent(getApplicationContext(), myClass);
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
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
