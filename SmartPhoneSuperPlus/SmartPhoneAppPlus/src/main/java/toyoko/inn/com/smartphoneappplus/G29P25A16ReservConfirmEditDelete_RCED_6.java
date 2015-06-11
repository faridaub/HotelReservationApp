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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.*;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.FLD_BREAKDWON;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.SW_OPTION;


public class G29P25A16ReservConfirmEditDelete_RCED_6 extends Activity {
    private G01P01ParcelableData obj_g01;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String ROOM_TYPE_CODE;
    private String NUMBER_OF_PEOPLE;
    private LinearLayout parent;
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
    private String BABY_FLAG;
    private String CHECKIN_TIME;
    private String TOTAL_OPTION_PRICE;
    private String TOTAL_PRICE;
    private String TOTAL_PRICE_INC_TAX;
    private String HOTELVLDFLG;

    private ArrayList<String> LS_PRICE;
    private ArrayList<String> LS_PRCINCLDNGTAX;
    private ArrayList<String> LS_TRGTDATE;

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
    private TextView la_vod_plan;
    private TextView la_baby_bad;
    private TextView la_total_price;
    private LinearLayout la_payment_layout;

    private LinearLayout la_ChildEcoPlanLayout;
    private LinearLayout la_ChildBusinessPlanLayout;
    private LinearLayout la_ChildVodPlanLayout;
    private LinearLayout la_ChildBabyBadLayout;

    private TextView la_grand_total;
    private String TTL_RESERVATIONNUM;
    private HashMap<String,String> srnData;
    private int leftWidth;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G29P25A16ReservConfirmEditDelete_RCED_6------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g29_p25_reserv_confirm_edit_delete_rced_6);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
        GetData();

        //Print Data which We Receive
        CreateFieldProgramatically();

        //Execute Json Parser
        SetupToJsonOne();

        //Back To Previous Page
        BackTo();

        //Reservation Update
        GoToReservationCancel();

    }

    private void InitialSetupConfiguration() {
        this.parent = (LinearLayout) findViewById(R.id.g29_p25_group2_roomtype_1);
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
        this.la_eco_plan = new TextView(this);
        this.la_business_pack = new TextView(this);
        this.la_vod_plan = new TextView(this);
        this.la_baby_bad = new TextView(this);
        this.la_total_price = new TextView(this);
        this.la_ChildEcoPlanLayout = new LinearLayout(this);
        this.la_ChildBusinessPlanLayout = new LinearLayout(this);
        this.la_ChildVodPlanLayout = new LinearLayout(this);
        this.la_ChildBabyBadLayout = new LinearLayout(this);


        this.CHECK_OUT_DATE =new String();
        this.CHECK_IN_DATE = new String();
        this.TTL_RESERVATIONNUM = new String();
        this.CUSTRSRVSPRSNUID = new String();
        this.NUMBER_OF_ROOM= new String();
        this.NUMBER_OF_NIGHT= new String();
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
        this.BUSINESS_FLAG_DATA = new String();
        this.BABY_FLAG = new String();
        this.CHECKIN_TIME = new String();
        this.TOTAL_OPTION_PRICE = new String();
        this.TOTAL_PRICE = new String();
        this.TOTAL_PRICE_INC_TAX = new String();
        this.HOTELVLDFLG = new String();
        this.PAGE_FLAG = new String();

        this.LS_PRCINCLDNGTAX = new ArrayList<String>();
        this.LS_PRICE = new ArrayList<String>();
        this.LS_TRGTDATE = new ArrayList<String>();
        this.srnData = getScreen(getApplicationContext());
        this.leftWidth = Integer.valueOf(srnData.get("leftwidth"));
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if(!obj_g01.getCustRsrvsPrsnUid().isEmpty()){
                CUSTRSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
            if(!obj_g01.getCustRsrvid().isEmpty()){
                CUSTRSRVID = obj_g01.getCustRsrvid();
            }
            if(!obj_g01.getCustRsrvtnNmbr().isEmpty()){
                CUSTRSRVTNNMBR = obj_g01.getCustRsrvtnNmbr();
            }

            if(!obj_g01.getHotelCode().isEmpty()){
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            if(!obj_g01.getNumberOfStayNight().isEmpty()){
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if(!obj_g01.getNumberOfRoom().isEmpty()){
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if(!obj_g01.getPageFlag().isEmpty()){
                PAGE_FLAG = obj_g01.getPageFlag();
            }

        }
    }

    private void CreateFieldProgramatically() {
        getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_RESERVATION_NO + CUSTRSRVTNNMBR);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_ACC_DATE, la_accmodation_date);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_ACCOMODATION, la_hotel_name);
        getCommonFieldLayoutWithSmoking(getApplicationContext(), parent, FLD_ROOM1, la_smoking_icon, la_room_type_name);
        getCommonFieldLayoutLast(getApplicationContext(), parent, FLD_USING_PEOPLE, la_number_people);
        getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_ACOMOINFO);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_FAMILY_NAME1, la_family_name);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_FIRST_NAME1, la_first_name);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_MEMBERSHIP_FLAG1, la_membership_flag);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_COUNTRY, la_country);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_GENDER, la_sex);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_PHONE_NUM, la_phone_number);
        getCommonFieldLayoutLast(getApplicationContext(), parent, FLD_CHECKIN_TIME, la_checkintime);
        getCommonGroupLayoutConfirmPage(getApplicationContext(), parent, FLD_OPTION_SELECTION);
        getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildEcoPlanLayout, FLD_ECO_PLAN1, la_eco_plan);
        getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildBusinessPlanLayout, FLD_BUSINESSPACK, la_business_pack);
        getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildVodPlanLayout, FLD_VOD1, la_vod_plan);
        getCommonFieldLayoutHideAble(getApplicationContext(), parent, la_ChildBabyBadLayout, FLD_BABY_FLAG, la_baby_bad);
        getCommonGroupLayoutConfirmPageFirst(getApplicationContext(), parent, FLD_PAYMENT_AMOUNT);
        getCommonFieldLayout(getApplicationContext(), parent, FLD_PAYMENT_AMOUNT, la_total_price);
    }

    private class JSONParseOneA015 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode = new String();
        private String errorMessage= new String();

        private JSONParseOneA015() {
            super();
            setApiRequestDataA015();
        }

        private void setApiRequestDataA015() {
            api.setRsrvsPrsnUid(CUSTRSRVSPRSNUID);
            api.setRsrvId(CUSTRSRVID);
            api.setHotelCode(HOTEL_CODE);
            api.setRsrvtnNmbr(CUSTRSRVTNNMBR);
            Log.e("PARAM-G17P22A15",api.getRequestDataA015().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G29P25A16ReservConfirmEditDelete_RCED_6.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA015());
            JSONObject json = jParser.getJSONData(api.getURLA015());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G17P22A15", json.toString());
            //First Confirm Page
            if(!isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode =  json.optString(CT_ERRRCODE);
                errorMessage =  json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }

            CUSTRSRVTNNMBR = json.optString(CT_RSRVTNNMBR);
            HOTEL_CODE  = json.optString(CT_HTLCODE);
            HOTEL_NAME = json.optString(CT_HTLNAME);
            CHECK_IN_DATE = json.optString(CT_CHCKNDATE);
            CHECK_OUT_DATE = json.optString(CT_CHCKTDATE);
            ROOM_TYPE_CODE = json.optString(CT_ROOMTYPE);
            ROOM_TYPE_NAME = json.optString(CT_ROOMNAME);
            SMOKING_FLAG = json.optString(CT_SMKNGFLAG);
            NUMBER_OF_PEOPLE = json.optString(CT_NMBRPPL);
            FAMILY_NAME = json.optString(CT_FMLYNAME);
            FIRST_NAME = json.optString(CT_FRSTNAME);
            MEMBERSHIP_FLAG = json.optString(CT_MMBRSHPFLAG);
            MEMBERSHIP_NUMBER = json.optString(CT_MMBRSHPNMBR);
            SEX =json.optString(CT_SEX);
            COUNTRY = json.optString(CT_NTNLTYCODE);
            PHONE_NUMBER = json.optString(CT_PHNNMBR);
            ECO_FLAG = json.optString(CT_ECOFLAG);
            ECO_CHECKING = json.optString(CT_ECOCHCKN);
            VOD_FLAG = json.optString(CT_VODFLAG);
            BUSINESS_FLAG = json.optString(CT_BSNSSPACKFLAG);
            BUSINESS_FLAG_DATA = json.optString(CT_BSNSSPACKTYPE);
            BABY_FLAG = json.optString(CT_CHLDRNSHRNGBED);
            CHECKIN_TIME = json.optString(CT_CHCKNTIME);

            TOTAL_OPTION_PRICE = json.optString(CT_OPTNPRC);
            //TOTAL_OPTION_PRICE = "100";
            TOTAL_PRICE = json.optString(CT_TTLPRC);
            //TOTAL_PRICE = "100";
            TOTAL_PRICE_INC_TAX = json.optString(CT_TTLPRCINCLDNGTAX);
            //TOTAL_PRICE_INC_TAX = "100";

            HOTELVLDFLG = json.optString(CT_HTLVLDFLAG);

            try {
                JSONArray jsonData = json.getJSONArray(LT_DLYINFRMTN);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    String flag = jsonObject.getString(CT_ECOFLAG);
                    if(flag.equalsIgnoreCase("Y")) {
                        LS_TRGTDATE.add(jsonObject.getString(CT_TRGTDATE));
                    }
                    LS_PRICE.add(jsonObject.getString(CT_PRC));
                    LS_PRCINCLDNGTAX.add(jsonObject.getString(CT_PRCINCLDNGTAX));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ParNightPriceDetails();
            SetupToView();
            processDialog.dismiss();
        }
        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    private class JSONParseTwoA016 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs apiCancel = new APIs();
        private String errorMessage= new String();

        private JSONParseTwoA016() {
            super();
            setApiRequestDataA016();
        }

        private void setApiRequestDataA016() {
            apiCancel.setRsrvsPrsnUid(CUSTRSRVSPRSNUID);
            apiCancel.setNmbrRsrvtns(ST_ONE);
            apiCancel.setRsrvId(CUSTRSRVID);
            apiCancel.setHotelCode(HOTEL_CODE);
            apiCancel.setRsrvtnNmbr(CUSTRSRVTNNMBR);
            Log.e("PARAM_G29P15_x2", apiCancel.getRequestDataA016().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G29P25A16ReservConfirmEditDelete_RCED_6.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParserCancel = new CommonJSONParser();
            jParserCancel.setArrayList(apiCancel.getRequestDataA016());
            JSONObject jsonCancel = jParserCancel.getJSONData(apiCancel.getURLA016());
            Log.e("JSON_G29P25_x2", jsonCancel.toString());
            if(!isDataSuccess(jsonCancel.optString(CT_ERRRCODE))) {
                errorMessage =  jsonCancel.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            SetupToParceal();
            goTo(G28P36A00ReservConfirmEditDelete_RCED_7.class, COD_NEXT);
        }
        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
    }

    private void BackTo() {
        Button  back = (Button)findViewById(R.id.g29_p25_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PAGE_FLAG.equalsIgnoreCase("G28P36")) {
                    goTo(G02P20A01AccountInformation.class,COD_BACK);
                }else{
                    finish(COD_BACK);
                }
            }
        });
    }


    private void GoToReservationCancel() {
        Button button = (Button)findViewById(R.id.g29_p25_reservation_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCancel(null,ERR_RESERV_CANCEL);
            }
        });
    }

    private void SetupToView() {
        la_smoking_icon.setBackgroundResource(R.drawable.ic_smoking_yes);
        if(SMOKING_FLAG.equalsIgnoreCase(COD_Y)){
            la_smoking_icon.setBackgroundResource(R.drawable.ic_smoking_yes);
        }

        this.la_hotel_name.setText(HOTEL_NAME);

        StringBuilder rid =new StringBuilder();
        rid.append(FLD_RESERVATION_NO);
        rid.append(" : ");
        rid.append(CUSTRSRVTNNMBR);
        this.la_reservationID.setText(rid);

        this.la_accmodation_date.setText(getFormettedDateAndStayNights(CHECK_IN_DATE, NUMBER_OF_NIGHT));
        this.la_room_type_name.setText(ROOM_TYPE_NAME);

        StringBuilder num_sb = new StringBuilder();
        num_sb.append(NUMBER_OF_PEOPLE);
        num_sb.append(LV_EACH_ROOM_NAME);
        this.la_number_people.setText(num_sb);


        this.la_family_name.setText(FAMILY_NAME);
        this.la_first_name.setText(FIRST_NAME);
        if(MEMBERSHIP_FLAG.equalsIgnoreCase("Y")) {
            this.la_membership_flag.setText(MSG_TOYOKO_INN_GROUPCARD);
        }else{
            this.la_membership_flag.setText("一般");
        }

        this.la_country.setText(getCountryValueAccCode(COUNTRY));



        if(SEX.equalsIgnoreCase("M")){
            this.la_sex.setText(SW_MALE);
        }else{
            this.la_sex.setText(SW_FMALE);
        }

        this.la_phone_number.setText(PHONE_NUMBER);

        this.la_checkintime.setText(getTimeValueAccCode(CHECKIN_TIME));


        //Eco Plan Details
        StringBuilder ecoPlan = new StringBuilder();
        if(ECO_FLAG.equalsIgnoreCase("Y")){
            ecoPlan.append(SIN_START_BRECKET_TARGET);
            ecoPlan.append(dateConcatWithDateFormate(LS_TRGTDATE));
            ecoPlan.append(SIN_CLOSE_BRECKET);
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
            }else if(BUSINESS_FLAG_DATA.equalsIgnoreCase("3")){
                businessPlan = NUM_BUSINESSPLAN_3;
            }else{
                businessPlan = NUM_BUSINESSPLAN_0;
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


        StringBuilder total_price =new StringBuilder();
        total_price.append(TOTAL_PRICE);
        total_price.append(SIN_START_BRECKET_TAX);
        total_price.append(TOTAL_PRICE_INC_TAX);
        total_price.append(SIN_CLOSE_BRECKET);
        this.la_total_price.setText(total_price);
        //    this.la_grand_total.setText(total_price);

    }



    private void ParNightPriceDetails() {
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0,10,0,30);
        la_payment_layout.setOrientation(LinearLayout.VERTICAL);
        la_payment_layout.setBackgroundResource(R.drawable.util_com_background_1);
        la_payment_layout.setLayoutParams(l_total);
        la_payment_layout.setPadding(10, 10, 10, 10);
        la_payment_layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(la_payment_layout);

        // Left Breakdown (内訳)
        TextView details_price_1 = new TextView(this);
        LinearLayout.LayoutParams px_1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_1.setMargins(0, 0, 0, 0);
        details_price_1.setTextSize(15);
        details_price_1.setTextColor(Color.BLACK);
        details_price_1.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
        details_price_1.setText(FLD_BREAKDWON);
        details_price_1.setLayoutParams(px_1);
        details_price_1.setPadding(10, 10, 10, 10);
        la_payment_layout.addView(details_price_1);
        for(int nights=0;nights<LS_PRICE.size();nights++) {

            //Leading Layout (After 内訳)
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

            //left level
            TextView parnight_price_level = new TextView(this);
            LinearLayout.LayoutParams px_3 = new LinearLayout.LayoutParams(leftWidth-5, LayoutParams.WRAP_CONTENT);
            px_3.setMargins(0, 0, 0, 0);
            parnight_price_level.setTextSize(11);
            parnight_price_level.setTextColor(Color.BLACK);
            parnight_price_level.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            parnight_price_level.setText(builder.toString());
            parnight_price_level.setTextSize(11);
            parnight_price_level.setLayoutParams(px_3);
            parnight_price_level.setPadding(10,10, 10, 10);
            layout1.addView(parnight_price_level);

            //right level
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

        //Layout (Option)
        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout2);

        //Left Option Level
        TextView option_title = new TextView(this);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(leftWidth, LayoutParams.WRAP_CONTENT);
        px_6.setMargins(0, 0, 0, 0);
        option_title.setTextSize(12);
        option_title.setTextColor(Color.BLACK);
        option_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_title.setText(SW_OPTION);
        option_title.setLayoutParams(px_6);
        option_title.setPadding(10, 10, 10, 10);
        layout2.addView(option_title);

        //Total Option Price
        TextView option_price = new TextView(this);
        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_7.setMargins(0, 0, 0, 0);
        option_price.setTextSize(12);
        option_price.setTextColor(Color.BLACK);
        option_price.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_price.setText(TOTAL_OPTION_PRICE);
        option_price.setLayoutParams(px_7);
        option_price.setPadding(10, 10, 10, 10);
        layout2.addView(option_price);

        //Layout (Total Price )
        LinearLayout layout3 = new LinearLayout(this);
        LinearLayout.LayoutParams px_8 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_8.setMargins(0, 0, 0, 0);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setLayoutParams(px_8);
        layout3.setPadding(0, 0, 0, 0);
        layout3.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout3);

        //Left Level (小計 : )
        TextView totalamount_title = new TextView(this);
        LinearLayout.LayoutParams px_9 = new LinearLayout.LayoutParams(leftWidth, LayoutParams.WRAP_CONTENT);
        px_9.setMargins(0, 0, 0, 0);
        totalamount_title.setTextSize(11);
        totalamount_title.setTextColor(Color.BLACK);
        totalamount_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        totalamount_title.setText(SW_TOTALIZATION2);
        totalamount_title.setLayoutParams(px_9);
        totalamount_title.setPadding(10, 10, 10, 10);
        layout3.addView(totalamount_title);

        //Right Totalization ( 小計:32011)
        la_grand_total = new TextView(this);
        LinearLayout.LayoutParams px_10 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_10.setMargins(0, 0, 0, 0);
        la_grand_total.setTextSize(11);
        la_grand_total.setTextColor(Color.BLACK);
        la_grand_total.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        la_grand_total.setText(TOTAL_OPTION_PRICE);
        la_grand_total.setLayoutParams(px_10);
        la_grand_total.setPadding(10, 10, 10, 10);
        layout3.addView(la_grand_total);

    }



    private void SetupToParceal() {
        obj_g01.setLsPrc(LS_PRICE);
        obj_g01.setLsPrcIncldngTax(LS_PRCINCLDNGTAX);
        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setRdRoomTypeCode(ROOM_TYPE_CODE);
        obj_g01.setRdRoomName(ROOM_TYPE_NAME);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setNumberOfPeople(NUMBER_OF_PEOPLE);
        obj_g01.setNumberOfRoom(ST_NUM_OF_ROOMS);
        obj_g01.setRdSmokingFlag(SMOKING_FLAG);
        obj_g01.setCustFmlyName(FAMILY_NAME);
        obj_g01.setCustFrstName(FIRST_NAME);
        obj_g01.setCustSex(SEX);
        obj_g01.setCustMmbrshpFlag(MEMBERSHIP_FLAG);
        obj_g01.setCustMmbrshpNmbr(MEMBERSHIP_NUMBER);
        obj_g01.setCustNtnltyCode(COUNTRY);
        obj_g01.setCustPhnNmbr(PHONE_NUMBER);
        obj_g01.setRdEcoFlag(ECO_FLAG);
        obj_g01.setRdVodFlag(VOD_FLAG);
        obj_g01.setRdBsnssPackFlag(BUSINESS_FLAG);
        obj_g01.setRdBsnssPackData(BUSINESS_FLAG_DATA);
        obj_g01.setRdBabyBad(BABY_FLAG);
        obj_g01.setLsEcoDataRoom1(LS_TRGTDATE);
        obj_g01.setRdCheckInTime(CHECKIN_TIME);
        obj_g01.setCustRsrvtnNmbr(CUSTRSRVTNNMBR);
        obj_g01.setCustRsrvid(CUSTRSRVID);
        obj_g01.setRdNumberOfMaxPeople(NUMBER_OF_PEOPLE);
        obj_g01.setRdTotalPrice(TOTAL_PRICE);
        obj_g01.setRdTotalPriceTax(TOTAL_PRICE_INC_TAX);
        obj_g01.setRdTotalOptionPrice(TOTAL_OPTION_PRICE);

    }

    private void confirmCancel(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_cancel_confirm);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.top_title);
        la_errorCode.setText(getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.body_message);
        la_errorMsg.setText(eMessage);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button yes = (Button) dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToJsonTwo();
                dialog.dismiss();
            }
        });


        Button no = (Button) dialog.findViewById(R.id.no);
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

    private void SetupToJsonOne() {
        if (isNetworkAvailable(this)) {
            new JSONParseOneA015().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }


    private void SetupToJsonTwo() {
        if (isNetworkAvailable(this)) {
            new JSONParseTwoA016().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }


}
