package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.adapter.MapMarkerAdapter;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_BACK;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_NEXT;

public class G10P15A00MapView extends Activity {
    private GoogleMap mMap;
    private ArrayList<MapMarkerAdapter> mMapMarkersArrayAdapter = new ArrayList<MapMarkerAdapter>();
    private HashMap<Marker, MapMarkerAdapter> mMarkersHashMap;

    //Object
    G01P01ParcelableData obj_g01;
    //View dataArray
    private TextView la_bottomTitleBar;
    private TextView la_topTitleBar;

    //private ArrayList<String> LS_LATITUDE;
    //private ArrayList<String> LS_LONGITUDE;


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
    private String HOTEL_NAME;
    private String LATITUDE;
    private String LONGITUDE;
    private String DISTANCE;
    private String MEMBER_PRICE;
    private String MEMBER_DISC_PRICE;
    private String LIST_PRICE;
    private String LIST_DISC_PRICE;
    private String REMAIN_ROOM;
    private String IMAGEURL;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G10P15A00MapView------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g10_p15_hotel_info_map);

        //SetupInitialData
        InitialSetupConfiguration();


        //Get Data From G10P15
        GetData();

        //Reload Data Before Page Load
        SetupToView();

        setUpMapIfNeeded();

        //Go Back To G10P15
        BackToScrollView_G10P15();
    }

    private void InitialSetupConfiguration() {
        //Set Constant
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
        this.HOTEL_NAME = new String();
        this.LATITUDE = new String();
        this.LONGITUDE = new String();
        this.DISTANCE = new String();

        this.MEMBER_PRICE = new String();
        this.MEMBER_DISC_PRICE = new String();
        this.LIST_PRICE = new String();
        this.LIST_DISC_PRICE = new String();
        this.REMAIN_ROOM = new String();
        this.IMAGEURL = new String();
        this.PAGE_FLAG = new String();


        //Views
        la_topTitleBar = (TextView) findViewById(R.id.g03_p05_top_title_bar);
        la_bottomTitleBar = (TextView) findViewById(R.id.g03_p05_bottom_title_bar);
        mMarkersHashMap = new HashMap<Marker, MapMarkerAdapter>();

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }
            if (!obj_g01.getHotelCode().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            if (!obj_g01.getHotelCodeNew().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getRdImageURL().isEmpty()) {
                IMAGEURL = obj_g01.getRdImageURL();
            }

            if (!obj_g01.getLatitude().isEmpty()) {
                LATITUDE = obj_g01.getLatitude();
            }

            if (!obj_g01.getLongitude().isEmpty()) {
                LONGITUDE = obj_g01.getLongitude();
            }

            if (!obj_g01.getEatchMemberPrice().isEmpty()) {
                MEMBER_PRICE = obj_g01.getEatchMemberPrice();
            }

            if (!obj_g01.getEatchMemberDiscPrice().isEmpty()) {
                MEMBER_DISC_PRICE = obj_g01.getEatchMemberDiscPrice();
            }

            if (!obj_g01.getEatchListPrice().isEmpty()) {
                LIST_PRICE = obj_g01.getEatchListPrice();
            }

            if (!obj_g01.getEatchListDiscPrice().isEmpty()) {
                LIST_DISC_PRICE = obj_g01.getEatchListDiscPrice();
            }

            if (!obj_g01.getEatchRemainRoom().isEmpty()) {
                REMAIN_ROOM = obj_g01.getEatchRemainRoom();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
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



 /*           if (!obj_g01.getLsLatitude().isEmpty()) {
                LS_LATITUDE = obj_g01.getLsLatitude();
            }

            if (!obj_g01.getLsLongitude().isEmpty()) {
                LS_LONGITUDE = obj_g01.getLsLongitude();
            }*/
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


    private void BackToScrollView_G10P15() {
        Button button = (Button) findViewById(R.id.g03_p05_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void setUpMapIfNeeded() {
        setUpMap();
        MakeData();
        plotMarkers(mMapMarkersArrayAdapter);
    }

    private void MakeData() {
        mMapMarkersArrayAdapter.add(new MapMarkerAdapter(HOTEL_NAME,HOTEL_CODE, IMAGEURL, Double.parseDouble(LATITUDE), Double.parseDouble(LONGITUDE),MEMBER_PRICE,MEMBER_DISC_PRICE,LIST_PRICE,LIST_DISC_PRICE,REMAIN_ROOM,DISTANCE));
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
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.g10p15map)).getMap();
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
                if(PAGE_FLAG.equalsIgnoreCase("G01_G10")) {
                    obj_g01.setPageFlag("G01_G10M");
                }else if(PAGE_FLAG.equalsIgnoreCase("G06_G10")) {
                    obj_g01.setPageFlag("G06_G10M");
                }
                goTo(G10P15A07A21HotelInfoScrollView.class, COD_NEXT);
            }
        });
    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private ImageLoader imageLoader;
        public MarkerInfoWindowAdapter() {
            imageLoader = new ImageLoader(G10P15A00MapView.this);
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
}
