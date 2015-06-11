package toyoko.inn.com.smartphoneappplus;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.SubString;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.concatListData;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.getErrorCode;

public class G10P15A07A21HotelInfoScrollView extends Activity {

    private G01P01ParcelableData obj_g01;
    private ImageLoader lb_imgLoader;

    LinearLayout la_pagerLayout;
    LinearLayout la_pageGroup;
    private Button la_add_to_bookmark;
    private TextView la_hotelNameMainTitle;
    private TextView la_hotelNameSubTitle;
    private TextView text_address;
    private TextView text_access_details;
    private TextView text_park;
    private TextView text_bus_park;
    private TextView text_bus_rental;
    private TextView text_checkin;
    private TextView text_checkout;
    private TextView text_breakfast;
    private TextView text_equipment;
    private TextView text_barrier_fee;
    private TextView text_ios;
    private TextView text_phone_num;
    private TextView text_pickup;
    private ImageView la_mainImageSlider;
    private TextView[] la_pagerCercle;

    private ArrayList<String> lo_imageData;
    private int lo_position;
    private int lo_total_num_img;
    private int lo_checkNumHotel;
    private boolean lo_checkFirstTimeLoad;

    private ArrayList<String> ACCESS_INFO_LIST = new ArrayList<String>();
    private ArrayList<String> CREDIT_LIST = new ArrayList<String>();
    private ArrayList<String> EQUIPMENT_LIST = new ArrayList<String>();

    private String HOTEL_NAME;
    private String ADDRESS;
    private String PARK;
    private String BUS_PARK;
    private String PICKUP;
    private String BUS_RENTAL;
    private String CHECKIN;
    private String CHECKOUT;
    private String BREAKFAST;
    private String BARRIER_FEE;
    private String IOS;
    private String PHONE_NUM;
    private String HOTEL_CODE;
    private String RSRVSPRSNUID;
    private String DELETEFLAG;

    private LinearLayout layout_address;
    private LinearLayout layout_access_details;
    private LinearLayout layout_park;
    private LinearLayout layout_bus_park;
    private LinearLayout layout_pickup;
    private LinearLayout layout_bus_rental;
    private LinearLayout layout_checkin;
    private LinearLayout layout_checkout;
    private LinearLayout layout_breakfast;
    private LinearLayout layout_allow_creditcard;
    private LinearLayout layout_equipment;
    private LinearLayout layout_barrier_fee;
    private LinearLayout layout_ios;
    private LinearLayout layout_use_agreemnt;
    private LinearLayout layout_phone_num;


    private ImageView lmg_address;
    private ImageView lmg_access_details;
    private ImageView lmg_park;
    private ImageView lmg_bus_park;
    private ImageView lmg_pickup;
    private ImageView lmg_bus_rental;
    private ImageView lmg_checkin;
    private ImageView lmg_checkout;
    private ImageView lmg_breakfast;
    private ImageView lmg_allow_creditcard;
    private ImageView lmg_equipment;
    private ImageView lmg_barrier_fee;
    private ImageView lmg_ios;
    private ImageView lmg_use_agreemnt;
    private ImageView lmg_phone_num;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G10P15A07A21HotelInfoScrollView------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g10_p15_hotel_info_scroll_view);

        //==Constant Setup
        InitialSetupConfiguration();

        //== Get Data From G11P04
        GetData();

        //Setup To View
        SetupToView();

        //==>Go To Map Button Action
        GoToPageGoogleMap();

        //<<==Back To Hotel List
        BackToPageHotelList();

        //<<==Image Next previous
        SetupImageNextPrevious();

        //==> Go TO Choose Room Type
        GoToPageChooseRoom();

        //== Go To Search By Current Location
        GoToSearchCurrentLocation();

        //== Nearby Other Hotel List
        GoToNearbyOtherHotelList();

        //JSON Data Connect
        SetupToJson1();

        //Check
        SetupToJson2Check();

    }



    private void InitialSetupConfiguration() {

        this.layout_address = (LinearLayout) findViewById(R.id.la_g10_address);
        this.layout_access_details = (LinearLayout) findViewById(R.id.la_g10_access_details);
        this.layout_park = (LinearLayout) findViewById(R.id.la_g10_park_info);
        this.layout_bus_park = (LinearLayout) findViewById(R.id.la_g10_bus_park_info);
        this.layout_pickup = (LinearLayout) findViewById(R.id.la_g10_pickup_info);
        this.layout_bus_rental = (LinearLayout) findViewById(R.id.la_g10_bus_rent);
        this.layout_checkin = (LinearLayout) findViewById(R.id.la_g10_checkin_time);
        this.layout_checkout = (LinearLayout) findViewById(R.id.la_g10_checkout_time);
        this.layout_breakfast = (LinearLayout) findViewById(R.id.la_g10_breakfast_time);
        this.layout_allow_creditcard = (LinearLayout) findViewById(R.id.la_g10_allow_creditcard);
        this.layout_equipment = (LinearLayout) findViewById(R.id.la_g10_equipment_data);
        this.layout_barrier_fee = (LinearLayout) findViewById(R.id.la_g10_barrier_free);
        this.layout_ios = (LinearLayout) findViewById(R.id.la_g10_is09_number);
        this.layout_use_agreemnt = (LinearLayout) findViewById(R.id.la_g10_use_agreemnt);
        this.layout_phone_num = (LinearLayout) findViewById(R.id.la_g10_phone_number);

        this.lmg_address = (ImageView)findViewById(R.id.g10_arr_addrss);
        this.lmg_access_details = (ImageView)findViewById(R.id.g10_arr_access);
        this.lmg_park = (ImageView)findViewById(R.id.g10_arr_park);
        this.lmg_bus_park = (ImageView)findViewById(R.id.g10_arr_bus_park);
        this.lmg_pickup = (ImageView)findViewById(R.id.g10_arr_pickup);
        this.lmg_bus_rental = (ImageView)findViewById(R.id.g10_arr_bus_rent);
        this.lmg_checkin = (ImageView)findViewById(R.id.g10_arr_checkin);
        this.lmg_checkout = (ImageView)findViewById(R.id.g10_arr_checkout);
        this.lmg_breakfast = (ImageView)findViewById(R.id.g10_arr_brekfast);
        this.lmg_allow_creditcard = (ImageView)findViewById(R.id.g10_arr_credit_card);
        this.lmg_equipment = (ImageView)findViewById(R.id.g10_arr_equipment);
        this.lmg_barrier_fee = (ImageView)findViewById(R.id.g10_arr_barrier_fee);
        this.lmg_ios = (ImageView)findViewById(R.id.g10_arr_ios);
        this.lmg_use_agreemnt = (ImageView)findViewById(R.id.g10_arr_use_agreement);
        this.lmg_phone_num = (ImageView)findViewById(R.id.g10_arr_phone_number);

        this.lb_imgLoader = new ImageLoader(getApplicationContext());
        this.la_hotelNameMainTitle = (TextView) findViewById(R.id.g10_p15_hotel_name_main_title);
        this.la_hotelNameSubTitle = (TextView) findViewById(R.id.g10_p15_hotel_name_sub_title);
        this.text_address = (TextView) findViewById(R.id.g10_p15_address);
        this.text_access_details = (TextView) findViewById(R.id.g10_p15_access);
        this.text_park = (TextView) findViewById(R.id.g10_p15_park);
        this.text_bus_park = (TextView) findViewById(R.id.g10_p15_bus_park);
        this.text_pickup = (TextView) findViewById(R.id.g10_p15_pickup);
        this.text_bus_rental = (TextView) findViewById(R.id.g10_p15_bus_rental);
        this.text_checkin = (TextView) findViewById(R.id.g10_p15_checkin);
        this.text_checkout = (TextView) findViewById(R.id.g10_p15_checkout);
        this.text_breakfast = (TextView) findViewById(R.id.g10_p15_breakfast);
        this.text_equipment = (TextView) findViewById(R.id.g10_p15_equipment);
        this.text_barrier_fee = (TextView) findViewById(R.id.g10_p15_barrier_fee);
        this.text_ios = (TextView) findViewById(R.id.g10_p15_ios);
        this.text_phone_num = (TextView) findViewById(R.id.g10_p15_phone_num);
        this.la_add_to_bookmark = (Button) findViewById(R.id.g10_p15_add_bookmark);
        this.la_pagerLayout = (LinearLayout) findViewById(R.id.radiobuttons233);
        this.la_mainImageSlider = (ImageView) findViewById(R.id.g10_p15_main_image_view);
        this.la_pageGroup = new LinearLayout(this); //create the RadioGroup
        this.la_pagerLayout.addView(la_pageGroup);
        this.la_pageGroup.setOrientation(LinearLayout.HORIZONTAL);//or RadioGroup.VERTICAL

        this.lo_imageData = new ArrayList<String>();
        this.lo_checkFirstTimeLoad = false;
        this.lo_checkNumHotel = 0;
        this.lo_position = 0;

        this.HOTEL_NAME = new String();
        this.ADDRESS = new String();
        this.PARK = new String();
        this.BUS_PARK = new String();
        this.PICKUP = new String();
        this.BUS_RENTAL = new String();
        this.CHECKIN = new String();
        this.CHECKOUT = new String();
        this.BREAKFAST = new String();
        this.BARRIER_FEE = new String();
        this.IOS = new String();
        this.PHONE_NUM = new String();
        this.RSRVSPRSNUID = new String();
        this.DELETEFLAG = new String();
        this.PAGE_FLAG = new String();

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getHotelCode().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
        }
    }

    private void SetupToView() {
        la_add_to_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lo_checkFirstTimeLoad = false;
                if (!RSRVSPRSNUID.isEmpty()) {
                    SetupToJson2Check();
                } else {
                    errorPopup(null, ERR_LOGIN_CHK);
                }
            }
        });

    }

    private void mydataFile(LinearLayout linearLayout , ImageView imaveView,TextView textView, final String dataList, final String pageCodeData) {

        int i;
        if(dataList.isEmpty() || dataList.equalsIgnoreCase("") ||  dataList.equalsIgnoreCase(" ")){
            i = 0;
        }else{
            i = dataList.length();
        }

        linearLayout.setVisibility(View.VISIBLE);
        imaveView.setVisibility(View.VISIBLE);
        if (i > 30){
            textView.setText( SubString(dataList) );
            imaveView.setVisibility(View.VISIBLE);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj_g01.setSubPageFlag(pageCodeData);
                    obj_g01.setExtraPageData(dataList);
                    goTo(G10P15ExtraPage.class, COD_NEXT);
                }
            });

        }else if(i < 30 && i >0){
            textView.setText(dataList);
            imaveView.setVisibility(View.GONE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
    }

    private void SetupBookmarkFlag() {
        //Every Time when Button Click
        if (lo_checkFirstTimeLoad == false) {
            if (lo_checkNumHotel != 0) {
                DELETEFLAG = "Y";
                bookMarkPopup(null, ERR_DEL_BOOKMARK);
            } else {
                DELETEFLAG = "N";
                SetupToJson3Registration();
            }
        }
        //First Loading
        else if (lo_checkFirstTimeLoad == true) {
            if (lo_checkNumHotel != 0) {
                DELETEFLAG = "Y";
                la_add_to_bookmark.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);
                la_add_to_bookmark.setTextColor(Color.BLACK);
                la_add_to_bookmark.setBackgroundResource(R.drawable.util_gra_orangedeep_npad_ystroke_ycorner_yclickable);
            } else {
                DELETEFLAG = "N";
                la_add_to_bookmark.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bookmark, 0, 0, 0);
                la_add_to_bookmark.setTextColor(Color.WHITE);
                la_add_to_bookmark.setBackgroundResource(R.drawable.util_gra_bluedeep_button_2);
            }
        }
    }

    private void LoadImageSlider(){
        lo_imageData.add("http://www.toyoko-inn.com/hotel/images/h190h1.jpg");
        lo_imageData.add("http://www.toyoko-inn.com/hotel/images/h253h1.jpg");
        lo_imageData.add("http://www.toyoko-inn.com/hotel/images/h177h1.jpg");

        this.lo_total_num_img = lo_imageData.size();
        this.la_pagerCercle = new TextView[lo_total_num_img];
        for (int i = 0; i < lo_total_num_img; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            la_pagerCercle[i] = new TextView(this);
            la_pagerCercle[i].setBackgroundResource(R.drawable.util_page_off);
            la_pagerCercle[i].setLayoutParams(params);
            la_pagerCercle[i].setId(i);
            la_pageGroup.addView(la_pagerCercle[i]); //the RadioButtons are added to the radioGroup instead of the radioButtonsLayout
        }
        la_pagerCercle[lo_position].setBackgroundResource(R.drawable.util_page_on);


        final Button next = (Button) findViewById(R.id.g10_right_button);
        final Button back = (Button) findViewById(R.id.g10_left_button);
        lb_imgLoader.DisplayImage(lo_imageData.get(lo_position), 0, la_mainImageSlider);
        next.setOnClickListener(new View.OnClickListener() {
            private Object dataPosition;

            @Override
            public void onClick(View v) {
                if (lo_position < (lo_total_num_img - 1)) {
                    lo_position = lo_position + 1;
                }
                getPagingButton();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lo_position < lo_total_num_img && lo_position > -1) {
                    lo_position = lo_position - 1;
                }
                if (lo_position > -1) {
                    getPagingButton();
                }
            }
        });
    }


    private void SetupToViewAfter() {

        if (!HOTEL_NAME.isEmpty()) {
            la_hotelNameMainTitle.setText(HOTEL_NAME);
            StringBuilder sub_title = new StringBuilder();
            sub_title.append(HOTEL_NAME);
            sub_title.append(" ");
            sub_title.append("詳細");
            la_hotelNameSubTitle.setText(sub_title.toString());
        }
        obj_g01.setHotelName(HOTEL_NAME);

        //Address
        mydataFile(layout_address,lmg_address, text_address,ADDRESS, TAG_G10P15_ADDRESS);
        mydataFile(layout_access_details,lmg_access_details, text_access_details,concatListData(ACCESS_INFO_LIST),TAG_G10P15_ACCESS);
        mydataFile(layout_park, lmg_park, text_park, PARK,TAG_G10P15_PARK);
        mydataFile(layout_bus_park, lmg_bus_park, text_bus_park, BUS_PARK,TAG_G10P15_BUS_PARK);
        mydataFile(layout_pickup, lmg_pickup, text_pickup, PICKUP,TAG_G10P15_PICKUP);
        mydataFile(layout_bus_rental,lmg_bus_rental, text_bus_rental, BUS_RENTAL,TAG_G10P15_BUS_RENTAL);
        mydataFile(layout_checkin, lmg_checkin, text_checkin, CHECKIN,TAG_G10P15_CHECKIN);
        mydataFile(layout_checkout, lmg_checkout, text_checkout, CHECKOUT,TAG_G10P15_CHECKOUT);
        mydataFile(layout_breakfast, lmg_breakfast, text_breakfast, BREAKFAST,TAG_G10P15_BREAKFAST);

        if (CREDIT_LIST.size() != 0) {
            lmg_allow_creditcard.setVisibility(View.GONE);
            layout_allow_creditcard.setVisibility(View.VISIBLE);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.g10_p15_image_layout);
            for (String s : CREDIT_LIST) {
                ImageView imageView = new ImageView(this);
                imageView.setPadding(0, 4, 10, 4);
                lb_imgLoader.DisplayImage(s, 0, imageView);
                linearLayout.addView(imageView);
            }
        }else{
            layout_allow_creditcard.setVisibility(View.GONE);
        }

        mydataFile(layout_equipment, lmg_equipment, text_equipment, concatListData(EQUIPMENT_LIST),TAG_G10P15_EQUIPMENT);
        mydataFile(layout_barrier_fee, lmg_barrier_fee, text_barrier_fee, BARRIER_FEE,TAG_G10P15_BARRIER_FEE);
        mydataFile(layout_ios, lmg_ios, text_ios,IOS,TAG_G10P15_IOS);
        layout_use_agreemnt.setVisibility(View.GONE);
        mydataFile(layout_phone_num, lmg_phone_num, text_phone_num, PHONE_NUM, TAG_G10P15_PHONE_NUM);

    }

    private void GoToPageGoogleMap() {
        Button button = (Button) findViewById(R.id.g10_p15_google_map);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    SetupToParcel();
                    goTo(G10P15A00MapView.class, COD_NEXT);
                } else {
                    errorPopup(null, ERR_GPRS_CONNECTION);
                }
            }
        });
    }

    public boolean SetupValidation() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

    private void GoToNearbyOtherHotelList() {
        Button button = (Button) findViewById(R.id.g10_p15_nearby_hotel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G00P00A00WebView3.class, COD_NEXT);
            }
        });
    }

    private void GoToSearchCurrentLocation() {
        Button button = (Button) findViewById(R.id.g10_p15_search_current);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G10P15A00WebView2.class, COD_NEXT);
            }
        });
    }

    private void SetupToJson1() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse1HotelDetails().execute();
        } else {
            errorPopup(null, ERR_CONNECTION);
        }
    }

    private void SetupToJson2Check() {
        if (!RSRVSPRSNUID.isEmpty()) {
            if (ComLib.isNetworkAvailable(this)) {
                new JSONParse2CheckA20().execute();
            } else {
                errorPopup(null, ERR_CONNECTION);
            }
        }
    }

    private void SetupToJson3Registration() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse3RegistrationA21().execute();
        } else {
            errorPopup(null, ERR_CONNECTION);
        }
    }

    private void SetupToParcel() {
        obj_g01.setPageFlag("G01_G10");
        if(PAGE_FLAG.equalsIgnoreCase("G06")) {
            obj_g01.setPageFlag("G06_G10");
        }else if(PAGE_FLAG.equalsIgnoreCase("G06_G10M")) {
            obj_g01.setPageFlag("G06_G10");
        }else if(PAGE_FLAG.equalsIgnoreCase("G01_G10M")) {
            obj_g01.setPageFlag("G01_G10");
        }
        obj_g01.setLsAccessData(ACCESS_INFO_LIST);
        obj_g01.setLsEquipmentData(EQUIPMENT_LIST);
        obj_g01.setHotelName(HOTEL_NAME);
    }

    public class JSONParse1HotelDetails extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse1HotelDetails() {
            lo_checkFirstTimeLoad = true;
            settingApiParametter_1_A07();
        }

        private void settingApiParametter_1_A07() {
            api.setHotelCode(HOTEL_CODE);
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            Log.e(ComLogMsg.PARAM_G10P15, api.getRequestDataA007().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G10P15A07A21HotelInfoScrollView.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA007());
            JSONObject json = jParser.getJSONData(api.getURLA007());
            Log.e(ComLogMsg.JSON_G10P15, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            try {
                HOTEL_NAME = json.optString(ComConstant.CT_HTLNAME);
                ADDRESS = json.optString(ComConstant.CT_ADDRSS);
                PARK = json.optString(ComConstant.CT_PRKNGINFMTN);
                BUS_PARK = json.optString(ComConstant.CT_BUSINFMTN);
                PICKUP = json.optString(ComConstant.CT_PCKPINFMTN);
                BUS_RENTAL = json.optString(ComConstant.CT_RNTCRINFMTN);
                CHECKIN = json.optString(ComConstant.CT_CHCKNTIME);
                CHECKOUT = json.optString(ComConstant.CT_CHCKTTIME);
                BREAKFAST = json.optString(ComConstant.CT_BRKFSTTIME);
                BARRIER_FEE = json.optString(ComConstant.CT_BRRRFRINFMTN);
                IOS = json.optString(ComConstant.CT_ISOINFMTN);
                PHONE_NUM = json.optString(ComConstant.CT_PHNNMBR);
                lo_imageData.add(json.optString(ComConstant.CT_IMGURL));

                //Access List
                JSONArray accessInfoList = json.getJSONArray(ComConstant.LT_ACCESS_INFO_LIST);
                //Log.e("LT_ACCESS_INFO_LIST", accessInfoList.toString());
                for (int i = 0; i < accessInfoList.length(); i++) {
                    JSONObject jsonObject = accessInfoList.getJSONObject(i);
                    String accessInfo = jsonObject.getString(ComConstant.CT_ACCSSINFMTN);
                    ACCESS_INFO_LIST.add(accessInfo);
                }

                //Credit List
                JSONArray creditInfo = json.getJSONArray(ComConstant.LT_CREDIT_INFO_LIST);
                //Log.e("LT_CREDIT_INFO_LIST", creditInfo.toString());
                for (int i = 0; i < creditInfo.length(); i++) {
                    JSONObject imageURL = creditInfo.getJSONObject(i);
                    String creditName = imageURL.getString(ComConstant.CT_IMGURL);
                    CREDIT_LIST.add(creditName);
                }

                //Equipment List
                JSONArray equipment_info = json.getJSONArray(ComConstant.LT_EQUIPMENT_INFO_LIST);
                //Log.e("LT_EQUIPMENT_INFO_LIST", equipment_info.toString());
                for (int i = 0; i < equipment_info.length(); i++) {
                    JSONObject equipment = equipment_info.getJSONObject(i);
                    String creditName = equipment.getString(ComConstant.CT_EQPMNTNAME);
                    EQUIPMENT_LIST.add(creditName);
                }

            } catch (JSONException e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            LoadImageSlider();
            SetupToViewAfter();
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    public class JSONParse2CheckA20 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse2CheckA20() {
            settingApiParametter_2_A20();
        }

        private void settingApiParametter_2_A20() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPageNmbr(ComInitData.ST_ONE);
            api.setFvrtHtlCode(HOTEL_CODE);
            Log.e(ComLogMsg.PARAM_G10P15_2, api.getRequestDataA020().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G10P15A07A21HotelInfoScrollView.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA020());
            JSONObject json = jParser.getJSONData(api.getURLA020());
            Log.e(ComLogMsg.JSON_G10P15_2, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE)) && ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            lo_checkNumHotel = json.optInt(ComConstant.CT_NMBRMYFVRTS);
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupBookmarkFlag();
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    public class JSONParse3RegistrationA21 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse3RegistrationA21() {
            settingApiParametterA21();
        }

        private void settingApiParametterA21() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setFvrtHtlCode(HOTEL_CODE);
            api.setDltFlag(DELETEFLAG);
            Log.e(ComLogMsg.PARAM_G10P15_3, api.getRequestDataA021().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G10P15A07A21HotelInfoScrollView.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA021());
            JSONObject json = jParser.getJSONData(api.getURLA021());
            Log.e(ComLogMsg.JSON_G10P15_3, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            if (DELETEFLAG.equalsIgnoreCase("Y")) {
                la_add_to_bookmark.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bookmark, 0, 0, 0);
                la_add_to_bookmark.setTextColor(Color.WHITE);
                la_add_to_bookmark.setBackgroundResource(R.drawable.util_gra_bluedeep_button_2);
            } else {
                la_add_to_bookmark.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done, 0, 0, 0);
                la_add_to_bookmark.setTextColor(Color.BLACK);
                la_add_to_bookmark.setBackgroundResource(R.drawable.util_gra_orangedeep_npad_ystroke_ycorner_yclickable);
            }
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    private void GoToPageChooseRoom() {
        Button button = (Button) findViewById(R.id.g10_btn_chooseRoom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G12P16A09ChooseRoomList.class, COD_NEXT);
            }
        });
    }

    private void BackToPageHotelList() {
        Button button = (Button) findViewById(R.id.g10_p15_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obj_g01.getPageFlag().equalsIgnoreCase("G06_G10_G13")) {
                    obj_g01.setPageFlag("G06_G10");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G01_G10_G13")) {
                    obj_g01.setPageFlag("G01_G10");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G01_G10M")) {
                    obj_g01.setPageFlag("G01");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G06_G10M")) {
                    obj_g01.setPageFlag("G06");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G01_G10")) {
                    obj_g01.setPageFlag("G01");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G06_G10")) {
                    obj_g01.setPageFlag("G06");
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G33P31")) {
                    finish(COD_BACK);
                } else if (obj_g01.getPageFlag().equalsIgnoreCase("G19P27")) {
                    finish(COD_BACK);
                } else if (obj_g01.getPageFlag().equalsIgnoreCase("G21P31")) {
                    finish(COD_BACK);
                } else if (obj_g01.getPageFlag().equalsIgnoreCase("G11P04A08MAP")) {
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                } else if (obj_g01.getPageFlag().equalsIgnoreCase("G10P15A00MAP")) {
                    goTo(G11P04A08HotelSearchList.class, COD_BACK);
                }
                else {
                    finish(COD_BACK);
                }
            }
        });
    }

    private void SetupImageNextPrevious() {

    }

    private void getPagingButton() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(lo_imageData.get(lo_position), "alpha", 1f, .3f);
        fadeOut.setDuration(2000);
        lb_imgLoader.DisplayImage(lo_imageData.get(lo_position), 0, la_mainImageSlider);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(lo_imageData.get(lo_position), "alpha", .3f, 1f);
        fadeIn.setDuration(2000);

        for (int i = 0; i < lo_total_num_img; i++) {
            la_pagerCercle[i].setBackgroundResource(R.drawable.util_page_off);
        }
        la_pagerCercle[lo_position].setBackgroundResource(R.drawable.util_page_on);
    }

    private void bookMarkPopup(String eCode, String eMessage) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_bookmark);
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

        Button yes = (Button) dialog.findViewById(R.id.bk_delete_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                la_add_to_bookmark.setBackgroundResource(R.drawable.util_gra_orangedeep_npad_ystroke_ycorner_yclickable);
                SetupToJson3Registration();
            }
        });

        Button no = (Button) dialog.findViewById(R.id.bk_delete_no);
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

}
