package toyoko.inn.com.smartphoneappplus;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.getScreen;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.isNetworkAvailable;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.FLD_BREAKDWON;


public class G27P24A17ReservConfimSubmit_RCED_4 extends Activity {
    private G01P01ParcelableData obj_g01;

    ArrayList<Map<String,ArrayList<String>>> dataArray = new ArrayList<Map<String,ArrayList<String>>>();

    ArrayList<HashMap<String,ArrayList<HashMap<String,String>>>> fullData =  new ArrayList<HashMap<String,ArrayList<HashMap<String,String>>>>();

    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    public ArrayList<HashMap<String, String>> arraylist_1 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_2 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_3 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_4 = new ArrayList<HashMap<String, String>>();

    private ArrayList<String> LS_CHECK_IN_DATE;
    private ArrayList<String> LS_CHECK_OUT_DATE;
    private ArrayList<String> LS_ROOM_TYPE_NAME;
    private ArrayList<String> LS_NUMBER_OF_PEOPLE;
    private ArrayList<String> LS_FAMILY_NAME;
    private ArrayList<String> LS_FRIST_NAME;
    private ArrayList<String> LS_MEMBERSHIP_FLAG;
    private ArrayList<String> LS_MEMBERSHIP_NUMBER;
    private ArrayList<String> LS_COUNTRY;
    private ArrayList<String> LS_SEX;
    private ArrayList<String> LS_PHONE_NUMBER;
    private ArrayList<String> LS_BUSINESS_FLAG;
    private ArrayList<String> LS_BUSINESS_DATA;
    private ArrayList<String> LS_ECO_FLAG;
    private ArrayList<String> LS_ECO_DATE_SELECTION;
    private ArrayList<String> LS_BABY_FLAG;
    private ArrayList<String> LS_VOD_FLAG;
    private ArrayList<String> LS_CHECKIN_TIME;
    private ArrayList<String> LS_ECO_DATA_ROOM2;
    private ArrayList<String> LS_ECO_DATA_ROOM3;
    private ArrayList<String> LS_ECO_DATA_ROOM4;

    private ArrayList<String> LS_PRICE;
    private ArrayList<String> LS_PRCINCLDNGTAX;



    private String TTLPRCINCLDNGTAX;
    private String TTLPRC;
    private String ROOM1_OPTNPRC;
    private String ROOM2_OPTNPRC;
    private String ROOM3_OPTNPRC;
    private String ROOM4_OPTNPRC;

    private ArrayList<String>optnPrc;
    private ArrayList<String>sbttlPrc;
    private ArrayList<String>sbttlPrcIncldngTax;


    private LinearLayout parent;
    private TextView la_hotel_name;
    private TextView la_reservationID;
    private TextView la_accmodation_date;
    private TextView la_smoking_icon;
    private TextView la_room_type_name;
    private TextView la_number_people;
    private TextView la_family_name;
    private TextView la_first_name;
    private TextView la_membership_flag;
    private TextView la_country;
    private TextView la_sex;
    private TextView la_phone_number;
    private TextView la_checkintime;
    private TextView la_eco_plan;
    private TextView la_business_pack;
    private TextView la_baby_bad;
    private TextView la_vod_plan;

    private TextView la_total_price;
    private LinearLayout la_payment_layout;
    private TextView la_grand_total;

    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String ROOM_TYPE_CODE;
    private String NUMBER_OF_PEOPLE;

    private String CUSTRSRVSPRSNUID;
    private String CUSTRSRVID;
    private String CUSTRSRVTNNMBR;
    private String HOTEL_CODE;
    private String HOTEL_NAME;
    private String ROOM_TYPE_NAME;
    private String SMOKING_FLAG;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String SEX;
    private String MEMBERSHIP_FLAG;
    private String MEMBERSHIP_NUMBER;
    private String PHONE_NUMBER;
    private String COUNTRY;
    private String ECO_FLAG;
    private String ECO_CHECKING;
    private String VOD_FLAG;
    private String BUSINESS_FLAG;
    private String BUSINESS_FLAG_DATA;
    private String LS_BUSINESS_PACK_SELECTED;
    private String CHILDREN_BED;
    private String CHECKIN_TIME;
    private String TOTAL_OPTION_PRICE;
    private String TOTAL_PRICE;
    private String TOTAL_PRICE_INC_TAX;
    private String HOTELVLDFLG;
    private String BABY_FLAG;
    private ArrayList<String> LS_ECO_DATA_ROOM1;
    private LinearLayout la_ChildEcoPlanLayout;
    private LinearLayout la_ChildBusinessPlanLayout;
    private LinearLayout la_ChildVodPlanLayout;
    private LinearLayout la_ChildBabyBadLayout;
    private String RD_PLAN_CODE;
    private String RECEIPT_TYPE;
    private String RECEIPT_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G27P24A17ReservConfimSubmit_RCED_4------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g27_p24_reserv_confirm_edit_delete_rced_4);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
        GetData();

        //Print Data which We Receive
        CreateLayoutProgramatically();

        //Setup To View
        SetupToView();

        //Print Data which We Receive
        GoToReservationComplete();

        //Back To Previous
        BackToPrevious();


    }

    private void BackToPrevious() {
        Button button = (Button)findViewById(R.id.g27_p24_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LS_PRICE.clear();
                finish(G27P24A17ReservConfimSubmit_RCED_4.class,"back");
            }
        });
    }

    private void SetupToParcel() {


    }

    private void SetupToView() {
        
        la_smoking_icon.setBackgroundResource(R.drawable.ic_smoking_no);
        if(SMOKING_FLAG.equalsIgnoreCase("Y")){
            la_smoking_icon.setBackgroundResource(R.drawable.ic_smoking_yes);
        }

        this.la_hotel_name.setText(HOTEL_NAME);

        StringBuilder rid =new StringBuilder();
        rid.append("予約番号");
        rid.append(" : ");
        rid.append(CUSTRSRVTNNMBR);
        this.la_reservationID.setText(rid);

        this.la_accmodation_date.setText(ComLib.getFormettedDateAndStayNights(CHECK_IN_DATE, NUMBER_OF_NIGHT));
        this.la_room_type_name.setText(ROOM_TYPE_NAME);

        StringBuilder num_sb = new StringBuilder();
        num_sb.append(NUMBER_OF_PEOPLE);
        num_sb.append("名(部屋あたり)");
        this.la_number_people.setText(num_sb);


        this.la_family_name.setText(FAMILY_NAME);
        this.la_first_name.setText(FIRST_NAME);
        if(MEMBERSHIP_FLAG.equalsIgnoreCase("Y")) {
            this.la_membership_flag.setText("東横INNクラブカード会員");
        }else{
            this.la_membership_flag.setText("一般");
        }

        this.la_country.setText(ComLib.getCountryValueAccCode(COUNTRY));

        if(SEX.equalsIgnoreCase("M")){
            this.la_sex.setText("男性");
        }else{
            this.la_sex.setText("女性");
        }

        this.la_phone_number.setText(PHONE_NUMBER);

        this.la_checkintime.setText(ComLib.getTimeValueAccCode(CHECKIN_TIME));

        StringBuilder total_price =new StringBuilder();
        total_price.append(TOTAL_PRICE);
        total_price.append("(税込");
        total_price.append(TOTAL_PRICE_INC_TAX);
        total_price.append(")");
        this.la_total_price.setText(total_price);


      //Eco Plan Details
        StringBuilder ecoPlan = new StringBuilder();
        if(ECO_FLAG.equalsIgnoreCase("Y")){
            ecoPlan.append("(対象日 ");
            ecoPlan.append(ComLib.dateConcatWithDateFormate(LS_ECO_DATA_ROOM1));
            ecoPlan.append(")");
        }else{
            la_ChildEcoPlanLayout.setVisibility(View.GONE);
        }
        this.la_eco_plan.setText(ecoPlan);

        String businessPlan = new String();
        if(BUSINESS_FLAG.equalsIgnoreCase(COD_Y)){
            if(BUSINESS_FLAG_DATA.equalsIgnoreCase("1")){
                businessPlan = NUM_BUSINESSPLAN_1;
            }else if(BUSINESS_FLAG_DATA.equalsIgnoreCase("2")){
                businessPlan = NUM_BUSINESSPLAN_2;
            }else{
                businessPlan = NUM_BUSINESSPLAN_3;
            }
        }else{
            la_ChildBusinessPlanLayout.setVisibility(View.GONE);
        }
        this.la_business_pack.setText(businessPlan);


        String vodPlan = new String();
        if(VOD_FLAG.equalsIgnoreCase(COD_Y)){
            vodPlan = SW_YES;
        }else{
            la_ChildVodPlanLayout.setVisibility(View.GONE);
        }
        this.la_vod_plan.setText(vodPlan);


        String babyBadFlag = new String();
        if(BABY_FLAG.equalsIgnoreCase(COD_Y)){
            babyBadFlag = SW_YES;
        }else{
            la_ChildBabyBadLayout.setVisibility(View.GONE);
        }
        this.la_baby_bad.setText(babyBadFlag);

        StringBuilder grand_total =new StringBuilder();
        grand_total.append(TOTAL_PRICE);
        grand_total.append("(税込");
        grand_total.append(TOTAL_PRICE_INC_TAX);
        grand_total.append(")");
        this.la_grand_total.setText(grand_total);
    }

    private void GoToReservationComplete() {
       Button button = (Button)findViewById(R.id.g27_p24_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToJson();
            }
        });
    }



    //Loop and create Layout
    //----------------------------------------------------------------------------------------------
    private void CreateLayoutProgramatically() {
        ComActivity.getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_RESERVATION_NO + CUSTRSRVTNNMBR);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_ACCOMODATION, la_hotel_name);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_CHECKIN, la_accmodation_date);
        ComActivity.getCommonFieldLayoutWithSmoking(getApplicationContext(), parent, FLD_ROOM1, la_smoking_icon, la_room_type_name);
        ComActivity.getCommonFieldLayoutLast(getApplicationContext(), parent, FLD_USING_PEOPLE, la_number_people);
        ComActivity.getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_ACOMOINFO);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_FIRST_NAME1, la_first_name);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_FAMILY_NAME1, la_family_name);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent,FLD_MEMBERSHIP_FLAG1, la_membership_flag);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_COUNTRY, la_country);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_GENDER, la_sex);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_PHONE_NUM, la_phone_number);
        ComActivity.getCommonFieldLayoutLast(getApplicationContext(), parent, FLD_CHECKIN_TIME, la_checkintime);
        ComActivity.getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_OPTION_SELECTION);
        ComActivity.getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildEcoPlanLayout, FLD_ECO_PLAN1, la_eco_plan);
        ComActivity.getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildBusinessPlanLayout, FLD_BUSINESSPACK, la_business_pack);
        ComActivity.getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildVodPlanLayout, FLD_VOD1, la_vod_plan);
        ComActivity.getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildBabyBadLayout, FLD_BABY_FLAG, la_baby_bad);
        ComActivity.getCommonGroupLayoutConfirmPageFirst(getApplicationContext(), parent, FLD_PAYMENT_AMOUNT);
        ComActivity.getCommonFieldLayout(getApplicationContext(), parent, FLD_PAYMENT_AMOUNT, la_total_price);
        ParNightPriceDetails();
    }

    private void ParNightPriceDetails() {
        int leftWidth = Integer.valueOf(getScreen(getApplicationContext()).get("leftwidth"));
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0,0,0,30);
        la_payment_layout.setOrientation(LinearLayout.VERTICAL);
        la_payment_layout.setBackgroundResource(R.drawable.util_com_background_1);
        la_payment_layout.setLayoutParams(l_total);
        la_payment_layout.setPadding(10, 10, 10, 10);
        la_payment_layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(la_payment_layout);

        // Left Level (内訳)
        TextView details_price_1 = new TextView(this);
        LinearLayout.LayoutParams px_1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_1.setMargins(0, 0, 5, 0);
        details_price_1.setTextSize(15);
        details_price_1.setTextColor(Color.BLACK);
        details_price_1.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
        details_price_1.setText(FLD_BREAKDWON);
        details_price_1.setLayoutParams(px_1);
        details_price_1.setPadding(5, 5, 5, 5);
        la_payment_layout.addView(details_price_1);
        for(int nights=0;nights<LS_PRICE.size();nights++) {

            // Left Layout (Leading)
            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams px_2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            px_2.setMargins(0, 0, 0, 0);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(px_2);
            layout1.setPadding(0, 0, 0, 0);
            layout1.setGravity(Gravity.CENTER_VERTICAL);
            la_payment_layout.addView(layout1);

            StringBuilder builder = new StringBuilder();
            builder.append(nights+1);
            builder.append(FLD_HAKUBI_KINRYOU);

            //Left Level (拍日（会員料金）)
            TextView parnight_price_level = new TextView(this);
            LinearLayout.LayoutParams px_3 = new LinearLayout.LayoutParams(leftWidth-5, LayoutParams.WRAP_CONTENT);
            px_3.setMargins(0, 0, 0, 0);
            parnight_price_level.setTextSize(11);
            parnight_price_level.setTextColor(Color.BLACK);
            parnight_price_level.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            parnight_price_level.setText(builder.toString());
            parnight_price_level.setLayoutParams(px_3);
            parnight_price_level.setPadding(10,10, 10, 10);
            layout1.addView(parnight_price_level);

            //Right Price ()
            TextView parnigh_price = new TextView(this);
            LinearLayout.LayoutParams px_4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            px_4.setMargins(0, 0, 0, 0);
            parnigh_price.setTextSize(11);
            parnigh_price.setTextColor(Color.BLACK);
            parnigh_price.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            parnigh_price.setText(LS_PRICE.get(nights).toString());
            parnigh_price.setLayoutParams(px_4);
            parnigh_price.setPadding(10,10, 10, 10);
            layout1.addView(parnigh_price);
        }

        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout2);

        TextView option_title = new TextView(this);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(leftWidth, LayoutParams.MATCH_PARENT);
        px_6.setMargins(0, 0, 0, 0);
        option_title.setTextSize(12);
        option_title.setTextColor(Color.BLACK);
        option_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_title.setText(FLD_OPTION_SELECTION);
        option_title.setLayoutParams(px_6);
        option_title.setPadding(10, 10, 10, 10);
        layout2.addView(option_title);

        TextView option_price = new TextView(this);
        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_7.setMargins(0, 0, 0, 0);
        option_price.setTextSize(12);
        option_price.setTextColor(Color.BLACK);
        option_price.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_price.setText(TOTAL_OPTION_PRICE);
        option_price.setLayoutParams(px_7);
        option_price.setPadding(10, 10, 10, 10);
        layout2.addView(option_price);

        //Left Layout (Leading)
        LinearLayout layout3 = new LinearLayout(this);
        LinearLayout.LayoutParams px_8 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_8.setMargins(0, 0, 0, 0);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setLayoutParams(px_8);
        layout3.setPadding(0, 0, 0, 0);
        layout3.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout3);

        //Left Total Amount (小計)
        TextView totalamount_title = new TextView(this);
        LinearLayout.LayoutParams px_9 = new LinearLayout.LayoutParams(leftWidth, LayoutParams.WRAP_CONTENT);
        px_9.setMargins(0, 0, 0, 0);
        totalamount_title.setTextSize(12);
        totalamount_title.setTextColor(Color.BLACK);
        totalamount_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        totalamount_title.setText(SW_TOTALIZATION2);
        totalamount_title.setLayoutParams(px_9);
        totalamount_title.setPadding(10, 10, 10, 10);
        layout3.addView(totalamount_title);

        //Right Value
        la_grand_total = new TextView(this);
        LinearLayout.LayoutParams px_10 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_10.setMargins(0, 0, 0, 0);
        la_grand_total.setTextSize(11);
        la_grand_total.setTextColor(Color.BLACK);
        la_grand_total.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        la_grand_total.setText(TOTAL_OPTION_PRICE);
        la_grand_total.setLayoutParams(px_10);
        la_grand_total.setPadding(10, 10, 10, 10);
        layout3.addView(la_grand_total);

    }


    private void InitialSetupConfiguration() {
        this.parent = (LinearLayout) findViewById(R.id.g27_p17_parent);
        this.la_payment_layout = new LinearLayout(this);
        this.la_reservationID = new TextView(this);
        this.la_hotel_name  = new TextView(this);
        this.la_accmodation_date = new TextView(this);
        this.la_smoking_icon = new TextView(this);
        this.la_room_type_name = new TextView(this);
        this.la_number_people = new TextView(this);
        this.la_family_name = new TextView(this);
        this.la_first_name = new TextView(this);
        this.la_membership_flag = new TextView(this);
        this.la_country = new TextView(this);
        this.la_sex = new TextView(this);
        this.la_phone_number = new TextView(this);
        this.la_checkintime = new TextView(this);
        this.la_business_pack = new TextView(this);
        this.la_eco_plan = new TextView(this);
        this.la_baby_bad = new TextView(this);
        this.la_vod_plan = new TextView(this);
        this.la_total_price = new TextView(this);

        this.LS_CHECK_IN_DATE = new ArrayList<String>();
        this.LS_CHECK_OUT_DATE = new ArrayList<String>();
        this.LS_ROOM_TYPE_NAME = new ArrayList<String>();
        this.LS_NUMBER_OF_PEOPLE = new ArrayList<String>();
        this.LS_FAMILY_NAME = new ArrayList<String>();
        this.LS_FRIST_NAME = new ArrayList<String>();
        this.LS_MEMBERSHIP_FLAG = new ArrayList<String>();
        this.LS_MEMBERSHIP_NUMBER = new ArrayList<String>();
        this.LS_COUNTRY = new ArrayList<String>();
        this.LS_SEX = new ArrayList<String>();
        this.LS_PHONE_NUMBER = new ArrayList<String>();
        this.LS_BUSINESS_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_DATA = new ArrayList<String>();
        this.LS_ECO_FLAG = new ArrayList<String>();
        this.LS_ECO_DATE_SELECTION = new ArrayList<String>();
        this.LS_BABY_FLAG = new ArrayList<String>();
        this.LS_VOD_FLAG = new ArrayList<String>();
        this.LS_CHECKIN_TIME = new ArrayList<String>();
        this.LS_PRICE = new ArrayList<String>();
        this.LS_PRCINCLDNGTAX = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM1 = new ArrayList<String>();


        this.CUSTRSRVSPRSNUID = new String();
        this.NUMBER_OF_ROOM= new String();
        this.NUMBER_OF_NIGHT= new String();
        this.TTLPRCINCLDNGTAX = new String();
        this.TTLPRC = new String();
        this.HOTEL_CODE = new String();
        this.CUSTRSRVTNNMBR = new String();

        this.ROOM1_OPTNPRC = new String();
        this.ROOM2_OPTNPRC = new String();
        this.ROOM3_OPTNPRC = new String();
        this.ROOM4_OPTNPRC = new String();
        this.optnPrc = new ArrayList<String>();
        this.sbttlPrc = new ArrayList<String>();
        this.sbttlPrcIncldngTax = new ArrayList<String>();


        this.la_ChildEcoPlanLayout = new LinearLayout(this);
        this.la_ChildBusinessPlanLayout = new LinearLayout(this);
        this.la_ChildVodPlanLayout = new LinearLayout(this);
        this.la_ChildBabyBadLayout = new LinearLayout(this);


        this.BUSINESS_FLAG = new String();
         this.BUSINESS_FLAG_DATA = new String();
         this.NUMBER_OF_ROOM = new String();
         this.NUMBER_OF_NIGHT = new String();
         this.CHECK_IN_DATE = new String();
         this.CHECK_OUT_DATE = new String();
         this.ROOM_TYPE_CODE = new String();
         this.NUMBER_OF_PEOPLE = new String();
         this.CUSTRSRVSPRSNUID = new String();
         this.CUSTRSRVID = new String();
         this.CUSTRSRVTNNMBR = new String();
         this.HOTEL_CODE = new String();
         this.HOTEL_NAME = new String();
         this.ROOM_TYPE_NAME = new String();
         this.SMOKING_FLAG = new String();
         this.FAMILY_NAME = new String();
         this.FIRST_NAME = new String();
         this.SEX = new String();
         this.MEMBERSHIP_FLAG = new String();
         this.MEMBERSHIP_NUMBER = new String();
         this.PHONE_NUMBER = new String();
         this.COUNTRY = new String();
         this.ECO_FLAG = new String();
         this.ECO_CHECKING = new String();
         this.VOD_FLAG = new String();
         this.BUSINESS_FLAG = new String();
         this.LS_BUSINESS_PACK_SELECTED = new String();
         this.CHILDREN_BED = new String();
         this.CHECKIN_TIME = new String();
         this.TOTAL_OPTION_PRICE = new String();
         this.TOTAL_PRICE = new String();
         this.TOTAL_PRICE_INC_TAX = new String();
         this.HOTELVLDFLG = new String();
         this.BABY_FLAG = new String();
         this.RD_PLAN_CODE = new String();
         this.RECEIPT_TYPE = new String();
         this.RECEIPT_NAME = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                CUSTRSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if(!obj_g01.getCustRsrvtnNmbr().isEmpty()){
                CUSTRSRVTNNMBR = obj_g01.getCustRsrvtnNmbr();
            }

            if(!obj_g01.getCustRsrvid().isEmpty()){
                CUSTRSRVID = obj_g01.getCustRsrvid();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getRdPlanCode().isEmpty()) {
                RD_PLAN_CODE = obj_g01.getRdPlanCode();
            }

            if (!obj_g01.getReceiptType().isEmpty()) {
                RECEIPT_TYPE = obj_g01.getReceiptType();
            }

            if (!obj_g01.getReceiptName().isEmpty()) {
                RECEIPT_NAME = obj_g01.getReceiptName();
            }

            if(!obj_g01.getHotelCode().isEmpty()){
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            if(!obj_g01.getHotelName().isEmpty()){
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if(!obj_g01.getRdSmokingFlag().isEmpty()){
                SMOKING_FLAG = obj_g01.getRdSmokingFlag();
            }

            if (!obj_g01.getRdRoomName().isEmpty()) {
                ROOM_TYPE_NAME = obj_g01.getRdRoomName();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if(!obj_g01.getCustFmlyName().isEmpty()){
                FAMILY_NAME = obj_g01.getCustFmlyName();
            }

            if(!obj_g01.getCustFrstName().isEmpty()){
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if(!obj_g01.getCustMmbrshpFlag().isEmpty()){
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if(!obj_g01.getCustMmbrshpNmbr().isEmpty()){
                MEMBERSHIP_NUMBER = obj_g01.getCustMmbrshpNmbr();
            }

            if(!obj_g01.getCustNtnltyCode().isEmpty()){
                COUNTRY = obj_g01.getCustNtnltyCode();
            }

            if(!obj_g01.getRdCheckInTime().isEmpty()){
                CHECKIN_TIME = obj_g01.getRdCheckInTime();
            }

            if(!obj_g01.getCustSex().isEmpty()){
                SEX = obj_g01.getCustSex();
            }

            if(!obj_g01.getCustPhnNmbr().isEmpty()){
                PHONE_NUMBER = obj_g01.getCustPhnNmbr();
            }

            if(!obj_g01.getRdEcoFlag().isEmpty()){
                ECO_FLAG = obj_g01.getRdEcoFlag();
            }

            if(!obj_g01.getLsEcoDateSelection().isEmpty()){
                LS_ECO_DATA_ROOM1 = obj_g01.getLsEcoDateSelection();
            }

            if(!obj_g01.getRdBsnssPackFlag().isEmpty()){
                BUSINESS_FLAG = obj_g01.getRdBsnssPackFlag();
            }

            if (!obj_g01.getRdBsnssPackData().isEmpty()) {
                BUSINESS_FLAG_DATA  = obj_g01.getRdBsnssPackData();
            }

            if(!obj_g01.getRdVodFlag().isEmpty()){
                VOD_FLAG = obj_g01.getRdVodFlag();
            }

            if(!obj_g01.getRdBabyBad().isEmpty()){
                BABY_FLAG = obj_g01.getRdBabyBad();
            }

            if (!obj_g01.getLsPrc().isEmpty()) {
                LS_PRICE  = obj_g01.getLsPrc();
            }

            if (!obj_g01.getLsPrcIncldngTax().isEmpty()) {
                LS_PRCINCLDNGTAX  = obj_g01.getLsPrcIncldngTax();
            }

            if(!obj_g01.getRdTotalPrice().isEmpty()){
                TOTAL_PRICE = obj_g01.getRdTotalPrice();
            }

            if(!obj_g01.getRdTotalOptionPrice().isEmpty()){
                TOTAL_OPTION_PRICE = obj_g01.getRdTotalOptionPrice();
            }

            if(!obj_g01.getRdTotalPriceTax().isEmpty()){
                TOTAL_PRICE_INC_TAX = obj_g01.getRdTotalPriceTax();
            }

        }
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private String errorCode = new String();
        private String errorMessage = new String();
        private APIs api = new APIs();
        private boolean errorFlag;

        private JSONParse() {
            super();
            setApiRequestDataA017();
        }

        private void setApiRequestDataA017() {
            api.setRsrvsPrsnUid(CUSTRSRVSPRSNUID);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setHotelCode(HOTEL_CODE);
            api.setRsrvId(CUSTRSRVID);
            api.setRsrvtnNmbr(CUSTRSRVTNNMBR);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setChcktDate(CHECK_OUT_DATE);
            api.setRoomType(ROOM_TYPE_CODE);
            api.setPlanCode(RD_PLAN_CODE);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            api.setSex(SEX);
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setMmbrshpNmbr(MEMBERSHIP_NUMBER);
            api.setNtnltyCode(COUNTRY);
            api.setPhnNmbr(PHONE_NUMBER);
            api.setEcoFlag(ECO_FLAG);
            api.setEcoDtsList1(LS_ECO_DATA_ROOM1);
            api.setEcoDtsList2(LS_ECO_DATA_ROOM2);
            api.setEcoDtsList3(LS_ECO_DATA_ROOM3);
            api.setEcoDtsList4(LS_ECO_DATA_ROOM4);
            api.setEcoChckn("N");
            api.setVodFlag(VOD_FLAG);
            api.setBsnssPackFlag(BUSINESS_FLAG);
            api.setBsnssPackType(BUSINESS_FLAG_DATA);
            api.setChldrnShrngBed(BABY_FLAG);
            api.setChcknTime(CHECKIN_TIME);
            api.setPrcList("5000");
            api.setPrcIncldngTaxList("5400");
            api.setOptnPrc("5400");
            api.setSbttlPrc("5400");
            api.setSbttlPrcIncldngTax("5400");
            api.setReceiptType(RECEIPT_TYPE);
            api.setReceiptName(RECEIPT_NAME);
            api.setTtlPrc(TOTAL_PRICE);
            api.setTtlPrcIncldngTax(TOTAL_PRICE_INC_TAX);
            Log.e("PARAM-G27P24A17-4",api.getRequestDataA017().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G27P24A17ReservConfimSubmit_RCED_4.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA017());
            JSONObject json = jParser.getJSONData(api.getURLA017());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G27P2417-4", json.toString());
            //Reservation Input Form Submit
            errorFlag = true;
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode =  json.optString(ComConstant.CT_ERRRCODE);
                errorMessage =  json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
                errorFlag = false;
            }
          return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            if(errorFlag) {
                goTo(G28P36A00ReservConfirmEditDelete_RCED_5.class,"next");
            }
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
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

    private void ReloadBeforeAction() {

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

    private void SetupToJson() {
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }


}
