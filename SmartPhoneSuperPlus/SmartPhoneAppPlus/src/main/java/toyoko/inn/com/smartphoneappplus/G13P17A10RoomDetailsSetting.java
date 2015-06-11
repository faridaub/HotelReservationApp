package toyoko.inn.com.smartphoneappplus;

import android.animation.ObjectAnimator;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.Config.CON_ROOM_IMG;


public class G13P17A10RoomDetailsSetting extends Activity {

    private LinearLayout la_pagerLayout;
    private LinearLayout la_pageGroup;
    private ImageLoader lb_imgLoader;
    private ArrayList<String> lo_imageData;
    private ImageView la_mainImageSlider;
    private TextView[] la_pagerCercle;
    private TextView la_checkInDate;
    private TextView la_num_of_night;
    private TextView la_gustRoom;
    private TextView la_equipment_list;
    private LinearLayout la_member_pricing;
    private LinearLayout la_normal_pricing;
    private TextView la_member_price_title;
    private TextView la_normal_price_title;
    private TextView la_smpking_icon;
    private Button la_increase;
    private Button la_decrese;
    private TextView la_checkOutDate;
    private TextView la_roomName;
    private TextView la_cancelpolicy;
    private TextView la_num_of_max_people;

    private G01P01ParcelableData obj_g01;

    private ArrayList<String> EQUIPMENT_LIST;
    private ArrayList<HashMap<String, String>> BREAKDOWN_LIST;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String HOTEL_CODE;
    private String TOTAL_PRICE;
    private String TOTAL_PRICE_TAX;
    private String ROOM_TYPE_CODE;
    private String RD_ROOM_NAME;
    private String RD_SMOKING_FLAG;
    private String NUMBER_OF_NIGHT;
    private String MEMBERSHIP_FLAG;
    private String TTLLISTPRC;
    private String TTLLISTPRCINCLDNGTAX;
    private String TTLMMBRPRC;
    private String TTLMMBRPRCINCLDNGTAX;
    private String MAXSTAY;
    private String RSRVSPRSNUID;
    private String PAGE_FLAG;
    private String RD_NUMBER_OF_MAX_PEOPLE;
    private String CANCELPOLICY;
    private String TERMS_AND_CONDITION;
    private String PLAN_CODE;


    private int lo_total_num_img;
    private int lo_position;
    private boolean lo_firstLoadCheck;
    private TextView la_smpking_img;
    private int counter;
    private int maxStay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G13P17A10RoomDetailsSetting------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g13_p17_details_room);

        //==Constant Setup
        InitialSetupConfiguration();

        //==>Get Data From G12P16
        GetData();

        //SetDataToView
        SetupToView();

        //<<==Back To G12P16
        BackToChoseRoom();

        //==>GoTo Details Page
        GoToEquipmentDetailsPage();

        //==>Goto Cancel Policy
        GoToCancelPolicy();

        //==>Goto Agreement Page
        GoToAgreementPage();

        //==>Choose Room Type
        GoToReservationRegistration();

        //Loading Jason Parsing Data
        SetupToJson();

    }

    private void LoadImageSlider() {
        lo_imageData.add("http://www.toyoko-inn.com/info/new_images/bt_gro_heart.jpg");
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


        final Button next = (Button) findViewById(R.id.g13_p17_right_button);
        final Button back = (Button) findViewById(R.id.g13_p17_left_button);
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


    private void GoToAgreementPage() {
        LinearLayout la_equipment = (LinearLayout)findViewById(R.id.g13_p17_agreement);
        la_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G13P17A10WebView.class, COD_NEXT);
            }
        });
    }


    private void GoToEquipmentDetailsPage() {
        LinearLayout la_equipment = (LinearLayout)findViewById(R.id.g13_p17_equipment_info);
        la_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setLsEquipmentData(EQUIPMENT_LIST);
                obj_g01.setCancelPolicy(CANCELPOLICY);
                obj_g01.setSubPageFlag("G13P17-EQP");
                goTo(G13P17ListDetailsViews.class, COD_NEXT);
            }
        });
    }

    private void GoToCancelPolicy() {
        LinearLayout la_equipment = (LinearLayout)findViewById(R.id.g13_p17_cancel_policy_layout);
        la_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setLsEquipmentData(EQUIPMENT_LIST);
                obj_g01.setCancelPolicy(CANCELPOLICY);
                obj_g01.setSubPageFlag("G13P17-CP");
                goTo(G13P17ListDetailsViews.class, COD_NEXT);
            }
        });
    }


    private void SetupToView(){
        la_num_of_max_people.setBackgroundResource(R.drawable.ic_person_icon_01);
        if(RD_NUMBER_OF_MAX_PEOPLE.equalsIgnoreCase(ST_TWO)){
            la_num_of_max_people.setBackgroundResource(R.drawable.ic_person_icon_02);
        }

        la_smpking_icon.setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        la_smpking_img.setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        if(RD_SMOKING_FLAG.equalsIgnoreCase(COD_Y)){
            la_smpking_icon.setBackgroundResource(R.drawable.ic_g12_p15_smoking_yes);
            la_smpking_img.setBackgroundResource(R.drawable.ic_g12_p15_smoking_yes);
        }

        if(!RD_ROOM_NAME.isEmpty()){
            la_gustRoom.setText(RD_ROOM_NAME);
            la_roomName.setText(RD_ROOM_NAME);
        }

        if (!NUMBER_OF_NIGHT.isEmpty()) {
            la_num_of_night.setText(NUMBER_OF_NIGHT);
        }

        //Set Number Of Date
        if (!CHECK_IN_DATE.isEmpty() && !NUMBER_OF_NIGHT.isEmpty()) {
            CHECK_OUT_DATE = dateYearMonthDayAdditionUsingDays(CHECK_IN_DATE, NUMBER_OF_NIGHT);
            la_checkInDate.setText(dateConvertFormattedDate(CHECK_IN_DATE));
            la_checkOutDate.setText(dateConvertFormattedDate(CHECK_OUT_DATE));
        }
    }
    private void SetupToViewAfterJson() {

        if(!MAXSTAY.isEmpty()){
            maxStay =Integer.valueOf(MAXSTAY);
        }

        //==Increment and Decrement Button Action
        IncrementDecrementButtonAction();

        //Set Equipment Date
        if (EQUIPMENT_LIST.size() != 0) {
            String eData = concatListData(EQUIPMENT_LIST);
            la_equipment_list.setText(SubString(eData));
        }

        if(!CANCELPOLICY.isEmpty()){
            la_cancelpolicy.setText(SubString(CANCELPOLICY));
        }

        StringBuilder member_title = new StringBuilder();
        member_title.append(TTLMMBRPRC);
        member_title.append("（税込");
        member_title.append(TTLMMBRPRCINCLDNGTAX);
        member_title.append("）");

        StringBuilder normal_title = new StringBuilder();
        normal_title.append(TTLLISTPRC);
        normal_title.append("（税込");
        normal_title.append(TTLLISTPRCINCLDNGTAX);
        normal_title.append("）");

        la_member_price_title.setText(member_title.toString());
        la_normal_price_title.setText(normal_title.toString());

        if (BREAKDOWN_LIST.size() != 0) {
            for (int i = 0; i < BREAKDOWN_LIST.size(); i++) {
                int maxvalue = BREAKDOWN_LIST.size();
                int x = i+1;
                //MemberS Details
                String member_star_days = BREAKDOWN_LIST.get(i).get(ComConstant.CT_STAYDAY);
                String member_price = BREAKDOWN_LIST.get(i).get(ComConstant.CT_MMBRPRC);
                String member_tax = BREAKDOWN_LIST.get(i).get(ComConstant.CT_MMBRPRCINCLDNGTAX);

                //Personal Details
                String normal_price = BREAKDOWN_LIST.get(i).get(ComConstant.CT_LISTPRC);
                String normal_tax = BREAKDOWN_LIST.get(i).get(ComConstant.CT_LISTPRCINCLDNGTAX);

                if (member_star_days.equalsIgnoreCase("1")) {
                    la_checkInDate.setText(dateConvertFormattedDate(CHECK_IN_DATE));
                    la_checkOutDate.setText(dateConvertFormattedDate(CHECK_OUT_DATE));

                }

                StringBuilder mRow = new StringBuilder();
                mRow.append(member_price);
                mRow.append("（税込");
                mRow.append(member_tax);
                mRow.append("）");

                StringBuilder pRow = new StringBuilder();
                pRow.append(normal_price);
                pRow.append("（税込");
                pRow.append(normal_tax);
                pRow.append("）");

                TextView member = new TextView(getApplicationContext());
                member.setText(mRow.toString());

                TextView normal = new TextView(getApplicationContext());
                normal.setText(pRow.toString());

                ComActivity.getCommonFieldLayoutDup2(getApplicationContext(), la_member_pricing, member_star_days + "泊目", member, maxvalue, x);
                ComActivity.getCommonFieldLayoutDup2(getApplicationContext(), la_normal_pricing, member_star_days + "泊目", normal, maxvalue, x);

                if (i == BREAKDOWN_LIST.size() - 1) {
                    BREAKDOWN_LIST.clear();
                }
            }
        }
    }
    private void SetupToVariable(){

        if(counter!=0){
            NUMBER_OF_NIGHT = String.valueOf(counter);
        }

        if(MEMBERSHIP_FLAG.equalsIgnoreCase(COD_N)){
            TOTAL_PRICE = TTLLISTPRC;
            TOTAL_PRICE_TAX = TTLLISTPRCINCLDNGTAX;
        }else{
            TOTAL_PRICE = TTLMMBRPRC;
            TOTAL_PRICE_TAX = TTLMMBRPRCINCLDNGTAX;
        }
    }

    private void SetButtonFlag() {
        // if less then 2  (decrease off)
        if (counter < 2) {
            la_decrese.setEnabled(false);
            la_decrese.setClickable(false);
            la_decrese.setBackgroundResource(R.drawable.util_off);
        }
        //If grater then 6
        if (counter > maxStay-1) {
            la_increase.setEnabled(false);
            la_increase.setClickable(false);
            la_increase.setBackgroundResource(R.drawable.util_off);
        }

        //if grater then 1 (decrease on)
        if (counter > 1) {
            la_decrese.setEnabled(true);
            la_decrese.setClickable(true);
            la_decrese.setBackgroundResource(R.drawable.util_on);
        }

        //If grater then 7 (increase on)
        if (counter < maxStay) {
            la_increase.setEnabled(true);
            la_increase.setClickable(true);
            la_increase.setBackgroundResource(R.drawable.util_on);
        }

    }


    private void IncrementDecrementButtonAction() {
            SetButtonFlag();
        la_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < maxStay){
                    counter++;
                    NUMBER_OF_NIGHT = String.valueOf(counter);
                    la_member_pricing.removeAllViews();
                    la_normal_pricing.removeAllViews();
                    SetupToVariable();
                    SetupToView();
                    DesiableButton();
                    SetupToJson();
                 }
            }
        });

        la_decrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter >1) {
                    counter--;

                    la_member_pricing.removeAllViews();
                    la_normal_pricing.removeAllViews();
                    SetupToVariable();
                    SetupToView();
                    DesiableButton();
                    SetupToJson();
                }
            }
        });
    }

    private void DesiableButton() {
        la_increase.setBackgroundResource(R.drawable.util_off);
        la_increase.setClickable(false);
        la_increase.setEnabled(false);
        la_decrese.setBackgroundResource(R.drawable.util_off);
        la_decrese.setClickable(false);
        la_decrese.setEnabled(false);
    }


    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);
            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getRdRoomName().isEmpty()) {
                RD_ROOM_NAME = obj_g01.getRdRoomName();
            }
            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }
            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
                counter = Integer.parseInt(NUMBER_OF_NIGHT);
            }

            if (!obj_g01.getRdNumberOfMaxPeople().isEmpty()) {
                RD_NUMBER_OF_MAX_PEOPLE = obj_g01.getRdNumberOfMaxPeople();
            }

            if (!obj_g01.getHotelCodeNew().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                RD_SMOKING_FLAG = obj_g01.getRdSmokingFlag();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if(!obj_g01.getCustRsrvsPrsnUid().isEmpty()){
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
        }
    }

    private void BackToChoseRoom() {
        Button button = (Button) findViewById(R.id.g13_p17_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PAGE_FLAG.equalsIgnoreCase("G01_G10_G13_G50")) {
                    obj_g01.setPageFlag("G01_G10");
                    obj_g01.setRdSmokingFlag("");
                    goTo(G12P16A09ChooseRoomList.class, COD_BACK);
                }else if(PAGE_FLAG.equalsIgnoreCase("G06_G10_G13_G50")) {
                    obj_g01.setPageFlag("G06_G10");
                    goTo(G12P16A09ChooseRoomList.class, COD_BACK);
                }else if(PAGE_FLAG.equalsIgnoreCase("G01_G10_G13")) {
                    obj_g01.setPageFlag("G01_G10");
                    obj_g01.setRdSmokingFlag("");
                    goTo(G12P16A09ChooseRoomList.class, COD_BACK);
                }else if(PAGE_FLAG.equalsIgnoreCase("G06_G10_G13")) {
                    obj_g01.setPageFlag("G06_G10");
                    goTo(G12P16A09ChooseRoomList.class, COD_BACK);
                }else{
                    finish(COD_BACK);
                }
            }
        });
    }



    private void GoToReservationRegistration() {
        Button la_button = (Button) findViewById(R.id.g13_17_goto_reservation);
        if(RSRVSPRSNUID.isEmpty()){
            la_button.setText(MSG_LOGIN_BUTTON);
        }
        la_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // SetupToViewAfterJson();
                SetupToVariable();
                SetupToParceal();
                if (!RSRVSPRSNUID.isEmpty()) {
                    goTo(G14P18A12ReservRegistrationStep41Entry.class, COD_NEXT);
                }else{
                     goTo(G50P26A32Login.class, COD_NEXT);
                }
            }
        });
    }

    private void InitialSetupConfiguration() {
        this.lb_imgLoader = new ImageLoader(getApplicationContext());
        this.TERMS_AND_CONDITION = new String();
        this.HOTEL_CODE = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.EQUIPMENT_LIST = new ArrayList<String>();
        this.BREAKDOWN_LIST = new ArrayList<HashMap<String, String>>();
        this.NUMBER_OF_NIGHT = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.RD_SMOKING_FLAG = new String();
        this.PAGE_FLAG = new String();
        this.RD_NUMBER_OF_MAX_PEOPLE = new String();
        this.CANCELPOLICY = new String();
        this.ROOM_TYPE_CODE = new String();
        this.RD_ROOM_NAME = new String();
        this.RSRVSPRSNUID = new String();
        this.PLAN_CODE = new String("0000");

        this.la_pagerLayout = (LinearLayout) findViewById(R.id.g13_p17_radio_paging);
        this.la_mainImageSlider = (ImageView) findViewById(R.id.g13_p17_main_image_view);
        this.la_pageGroup = new LinearLayout(this); //create the RadioGroup
        this.lo_imageData = new ArrayList<String>();
        this.lo_position =0;
        la_pagerLayout.addView(la_pageGroup);
        la_pageGroup.setOrientation(LinearLayout.HORIZONTAL);//or RadioGroup.VERTICAL

        this.TTLLISTPRC = new String();
        this.TTLLISTPRCINCLDNGTAX =new String();
        this.TTLMMBRPRC = new String();
        this.TTLMMBRPRCINCLDNGTAX = new String();
        this.TOTAL_PRICE = new String();
        this.TOTAL_PRICE_TAX = new String();

        this.MAXSTAY = new String();
        this.maxStay = 4;
        this.counter = 1;
        this.lo_firstLoadCheck = true;

        la_roomName =(TextView)findViewById(R.id.g13_subtitle);
        la_checkInDate = (TextView) findViewById(R.id.g13_p17_check_in_date);
        la_checkOutDate = (TextView) findViewById(R.id.g13_p17_check_out_date);
        la_smpking_icon =(TextView)findViewById(R.id.g13_p17_smoking_icon);
        la_smpking_img  = (TextView)findViewById(R.id.g13_p17_smoking_img);

        la_num_of_max_people = (TextView)findViewById(R.id.g13_p17_num_max_room);
        la_num_of_night = (TextView) findViewById(R.id.g13_p17_number_of_nights);
        la_gustRoom = (TextView) findViewById(R.id.g13_p17_guest_room);
        la_equipment_list = (TextView) findViewById(R.id.g13_p17_guest_room_equipment);
        la_member_pricing = (LinearLayout) findViewById(R.id.g13_p17_details_pricing);
        la_normal_pricing = (LinearLayout) findViewById(R.id.g13_p17_normal_price);
        la_member_price_title = (TextView) findViewById(R.id.g13_p17_member_price_title);
        la_normal_price_title = (TextView) findViewById(R.id.g13_p17_normal_price_title);
        la_increase = (Button) findViewById(R.id.g13_p17_increment);
        la_decrese = (Button) findViewById(R.id.g13_p17_decrement);
        la_cancelpolicy = (TextView)findViewById(R.id.g13_p17_cancel_policy);

    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            settingParameterValueA10();
        }

        private void settingParameterValueA10() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setHotelCode(HOTEL_CODE);
            api.setRoomType(ROOM_TYPE_CODE);
            api.setPlanCode(PLAN_CODE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setSmkngFlag(RD_SMOKING_FLAG);
            Log.e("PARAM-G13P17", api.getRequestDataA010().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G13P17A10RoomDetailsSetting.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA010());
            JSONObject json = jParser.getJSONData(api.getURLA010());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G13P17", json.toString());
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            try {

               String imageData =json.optString(ComConstant.CT_IMGURL);
                if(imageData.equalsIgnoreCase("") || imageData.isEmpty() || imageData.equalsIgnoreCase(" ")){
                    imageData = CON_ROOM_IMG;
                }
                lo_imageData.add(imageData);
                TTLLISTPRC = json.optString(ComConstant.CT_TTLLISTPRC);
                TTLLISTPRCINCLDNGTAX = json.optString(ComConstant.CT_TTLLISTPRCINCLDNGTAX);
                TTLMMBRPRC = json.optString(ComConstant.CT_TTLMMBRPRC);
                TTLMMBRPRCINCLDNGTAX = json.optString(ComConstant.CT_TTLMMBRPRCINCLDNGTAX);
                MAXSTAY= json.optString(ComConstant.CT_MAXSTAY);

                TERMS_AND_CONDITION = json.optString(ComConstant.CT_TRMSCNDTNS);
               CANCELPOLICY = json.optString(ComConstant.CT_CNCLLTNPOLICY);

                JSONArray equip = json.getJSONArray(ComConstant.LT_EQUIPMENT_INFO_LIST);
                for (int i = 0; i < equip.length(); i++) {
                    JSONObject jsonObject = equip.getJSONObject(i);
                    EQUIPMENT_LIST.add(jsonObject.getString(ComConstant.CT_EQPMNTNAME));
                }
                JSONArray brkdown = json.getJSONArray(ComConstant.LT_BRKDWN_INFO_LIST);
                for (int i = 0; i < brkdown.length(); i++) {
                    JSONObject jsonObject = brkdown.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_STAYDAY, jsonObject.getString(ComConstant.CT_STAYDAY));
                    map.put(ComConstant.CT_LISTPRC, jsonObject.getString(ComConstant.CT_LISTPRC));
                    map.put(ComConstant.CT_MMBRPRC, jsonObject.getString(ComConstant.CT_MMBRPRC));
                    map.put(ComConstant.CT_LISTPRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_LISTPRCINCLDNGTAX));
                    map.put(ComConstant.CT_MMBRPRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_MMBRPRCINCLDNGTAX));
                    BREAKDOWN_LIST.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            if(lo_firstLoadCheck) {
                LoadImageSlider();
            }
            SetupToViewAfterJson();
            lo_firstLoadCheck = false;
            la_increase.setClickable(true);
            la_decrese.setClickable(true);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            la_increase.setClickable(true);
            la_decrese.setClickable(true);
            errorPopup(errorCode, errorMessage);
        }
    }

    private void SetupToParceal() {
        obj_g01.setPageFlag("G01_G10_G13");
        if(PAGE_FLAG.equalsIgnoreCase("G06_G10")) {
            obj_g01.setPageFlag("G06_G10_G13");
        }
        obj_g01.setRdPlanCode(PLAN_CODE);
        obj_g01.setRdTotalPrice(TOTAL_PRICE);
        obj_g01.setRdTotalPriceTax(TOTAL_PRICE_TAX);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
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
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ERR_EMPTYCHECK);
        }
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

}