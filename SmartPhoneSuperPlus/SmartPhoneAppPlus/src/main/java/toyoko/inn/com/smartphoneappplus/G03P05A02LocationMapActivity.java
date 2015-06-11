package toyoko.inn.com.smartphoneappplus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

public class G03P05A02LocationMapActivity extends FragmentActivity {

    //Object
    G01P01ParcelableData obj_g01;
    //Google Map
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G03P05A02LocationMapActivity------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g03_p05_location_map);

        //SetupInitialData
        InitialSetupConfiguration();

        //Get Data From G10P15
        G03P05_GetDataFrom_G10P15();

        //Reload Data Before Page Load
        SetupToView();

        //Setup Map Before Action
        setUpMapIfNeeded();

        //Go Back To G10P15
        G03P05_backToScrollView_G10P15();

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
        this.LS_LATITUDE = new ArrayList<String>();
        this.LS_LONGITUDE = new ArrayList<String>();

        //Views
        la_topTitleBar = (TextView) findViewById(R.id.g03_p05_top_title_bar);
        la_bottomTitleBar = (TextView) findViewById(R.id.g03_p05_bottom_title_bar);

    }

        private void G03P05_GetDataFrom_G10P15() {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");


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


    private void G03P05_backToScrollView_G10P15() {
        Button button = (Button) findViewById(R.id.g03_p05_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish("back");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {


        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Get latitude of the current location
        // double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        //double longitude = myLocation.getLongitude();


        //Looping for Multiple Lattitude and Longitude
        //------------------------------------------------------------------------------------------
        for (int i = 0; i < LS_LATITUDE.size(); i++) {

            Double ltd = Double.valueOf(LS_LATITUDE.get(i));
            Double lng = Double.valueOf(LS_LONGITUDE.get(i));

            // Create a LatLng object for the current location
            LatLng latLng = new LatLng(ltd, lng);

            // Show the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Zoom in the Google Map
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


            LatLng myCoordinates = new LatLng(ltd, lng);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 12);
            mMap.animateCamera(yourLocation);


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myCoordinates)      // Sets the center of the map to LatLng (refer to previous snippet)
                    .zoom(13)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(ltd, lng))
                            .title("You are here!")
                            .snippet("Consider yourself located")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel_building))
            );
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
