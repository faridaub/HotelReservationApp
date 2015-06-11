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
import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G20P28A19AccommodationHistory_AH_2 extends Activity {
    private G01P01ParcelableData obj_g01;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String ROOM_TYPE_CODE;
    private String NUMBER_OF_PEOPLE;
    private LinearLayout parent;
    private String RSRVSPRSNUID;
    private String RSRVID;
    private String RSRVTNNMBR;
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
    private String ECO_FLG;
    private String ECO_CHECKING;
    private String VOD_FLAG;
    private String BUSINESS_FLAG;
    private String BUSINESS_PACK_TYPE;
    private String CHILDREN_BED;
    private String CHECKIN_TIME;
    private String TOTAL_OPTION_PRICE;
    private String TOTAL_PRICE;
    private String TOTAL_PRICE_INC_TAX;
    private String HOTELVLDFLG;

    private ArrayList<String> LS_PRICE;
    private ArrayList<String> LS_PRCINCLDNGTAX;

    private TextView la_hotel_name;
    private TextView la_reservationID;
    private TextView la_accmodation_date;
    private TextView la_smoking_icon;
    private TextView la_room_type;
    private TextView la_number_people;
    private TextView la_family_name;
    private TextView la_first_name;
    private TextView la_membership_flag;
    private TextView la_country;
    private TextView la_sex;
    private TextView la_phone_number;
    private TextView la_checkintime;
    private TextView la_business_pack;
    private TextView la_eco_plan;
    private TextView la_total_price;
    private LinearLayout la_payment_layout;
    private TextView la_grand_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G20P28A19AccommodationHistory_AH_2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g20_p28_accommodation_history_ah_2);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
        G15P19_getDataFrom_G14P18();

        //Print Data which We Receive
        G15P19_CreateFieldProgramatically();

        //Execute Json Parser
        SetupToJson();

        //Print Data which We Receive
        G15P19_goToReservationCompletePage();

        //Back To Previous Page
        G20P28_back();

    }

    private void G20P28_back() {
        Button  back = (Button)findViewById(R.id.g20_p28_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G19P27A18AccommodationHistory_AH_1.class,"back");
            }
        });
    }

    private void SetupToView() {
        this.la_hotel_name.setText(HOTEL_NAME);

        StringBuilder rid =new StringBuilder();
        rid.append("予約番号");
        rid.append(" : ");
        rid.append(RSRVID);
        this.la_reservationID.setText(rid);

        this.la_accmodation_date.setText(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        this.la_room_type.setText(ROOM_TYPE_NAME);

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

        this.la_country.setText(COUNTRY);

        if(SEX.equalsIgnoreCase("M")){
            this.la_sex.setText("男性");
        }else{
            this.la_sex.setText("女性");
        }

        this.la_phone_number.setText(PHONE_NUMBER);
        this.la_checkintime.setText(CHECKIN_TIME);

        StringBuilder total_price =new StringBuilder();
        total_price.append(TOTAL_PRICE);
        total_price.append("(税込");
        total_price.append(TOTAL_PRICE_INC_TAX);
        total_price.append(")");
        this.la_total_price.setText(total_price);

        this.la_eco_plan.setText(ECO_FLG);
        this.la_business_pack.setText(BUSINESS_FLAG);

        StringBuilder grand_total =new StringBuilder();
        grand_total.append(TOTAL_PRICE);
        grand_total.append("(税込");
        grand_total.append(TOTAL_PRICE_INC_TAX);
        grand_total.append(")");
        this.la_grand_total.setText(grand_total);



    }


    private void G15P19_goToReservationCompletePage() {
        go(R.id.g20_p28_submit,G15P35A13ReservRegistrationStep43Finish.class,"normal","no");
    }

    private void SetupToJson() {
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


    //Loop and create Layout
    //----------------------------------------------------------------------------------------------
    private void G15P19_CreateFieldProgramatically() {
        Group1_ReservationID();
        Common_Layout("宿泊先", la_hotel_name);
        Common_Layout("チェックイン", la_accmodation_date);
        Common_LayoutSmoking("お部屋", la_room_type, la_smoking_icon);
        Common_Layout("ご利用人数", la_number_people);
        Group2_CustomerInfo();
        Common_Layout("名", la_first_name);
        Common_Layout("性", la_family_name);
        Common_Layout("会員・一般", la_membership_flag);
        Common_Layout("国籍", la_country);
        Common_Layout("性別", la_sex);
        Common_Layout("電話番号",la_phone_number);
        Common_Layout("チェックイン時刻",la_checkintime);
        Group3_SelectedOption();
        Common_Layout("宿泊先", la_eco_plan);
        Common_Layout("宿泊先", la_business_pack);
        Group4_PaymentOption();
        Common_Layout("お支払い金額",la_total_price);
    }

    private void Common_Layout(String titleName,TextView textview){
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout1);

        LinearLayout layout2 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(300, LayoutParams.WRAP_CONTENT);
        px_6.setMargins(0, 0, 0, 0);
        col1.setTextSize(14);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);


        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(14);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }
    private void Common_LayoutSmoking(String titleName,TextView textview1,TextView textview2){
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout1);

        LinearLayout layout2 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(300, LayoutParams.WRAP_CONTENT);
        px_6.setMargins(0, 0, 0, 0);
        col1.setTextSize(14);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout layout3 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setLayoutParams(px_7);
        layout3.setBackgroundResource(R.drawable.util_com_background_1);
        layout3.setPadding(13, 13, 13, 13);
        layout3.setGravity(Gravity.CENTER_VERTICAL);
        layout2.addView(layout3);

        LinearLayout.LayoutParams px_8 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        px_8.setMargins(0, 0, 5, 0);
        textview2.setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        textview2.setLayoutParams(px_8);
        textview2.setPadding(13, 13, 13, 13);
        layout3.addView(textview2);

        LinearLayout.LayoutParams px_9 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_9.setMargins(0, 0, 0, 0);
        textview1.setTextSize(14);
        textview1.setTextColor(Color.BLACK);
        textview1.setLayoutParams(px_9);
        layout3.addView(textview1);

    }
    private void Group1_ReservationID() {
        LinearLayout layout1 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0,0,0,-3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_gra_bluedeep_npad_ystroke_ycorners_yclickable);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        la_reservationID = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        la_reservationID.setText("予約番号　：");
        la_reservationID.setTextSize(15);
        la_reservationID.setTextColor(Color.WHITE);
        la_reservationID.setPadding(10, 10, 10, 10);
        la_reservationID.setLayoutParams(param2);
        layout1.addView(la_reservationID);
    }
    private void Group2_CustomerInfo() {
        LinearLayout layout1 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0,0,0,-3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_gra_bluedeep_npad_ystroke_ycorners_yclickable);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        TextView text1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText("宿泊者情報");
        text1.setTextSize(15);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }
    private void Group3_SelectedOption() {
        LinearLayout layout1 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0,0,0,-3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_gra_bluedeep_npad_ystroke_ycorners_yclickable);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        TextView text1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText("選択したオプション");
        text1.setTextSize(15);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }
    private void Group4_PaymentOption() {
        LinearLayout layout1 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0,0,0,-3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_gra_bluedeep_npad_ystroke_ycorners_yclickable);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        TextView text1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText("お支払い金額");
        text1.setTextSize(15);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }
    private void ParNightPriceDetails() {
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0,0,0,30);
        la_payment_layout.setOrientation(LinearLayout.VERTICAL);
        la_payment_layout.setBackgroundResource(R.drawable.util_com_background_1);
        la_payment_layout.setLayoutParams(l_total);
        la_payment_layout.setPadding(10, 10, 10, 10);
        la_payment_layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(la_payment_layout);

        TextView details_price_1 = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_1.setMargins(0, 0, 5, 0);
        details_price_1.setTextSize(15);
        details_price_1.setTextColor(Color.BLACK);
        details_price_1.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
        details_price_1.setText("内訳");
        details_price_1.setLayoutParams(px_1);
        details_price_1.setPadding(5, 5, 5, 5);
        la_payment_layout.addView(details_price_1);
        for(int nights=0;nights<LS_PRICE.size();nights++) {

            LinearLayout layout1 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
            LinearLayout.LayoutParams px_2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            px_2.setMargins(0, 0, 0, 0);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(px_2);
            layout1.setPadding(0, 0, 0, 0);
            layout1.setGravity(Gravity.CENTER_VERTICAL);
            la_payment_layout.addView(layout1);

            StringBuilder builder = new StringBuilder();
            builder.append(nights+1);
            builder.append("拍日（会員料金）");


            TextView parnight_price_level = new TextView(G20P28A19AccommodationHistory_AH_2.this);
            LinearLayout.LayoutParams px_3 = new LinearLayout.LayoutParams(250, LayoutParams.WRAP_CONTENT);
            px_3.setMargins(0, 0, 5, 0);
            parnight_price_level.setTextSize(11);
            parnight_price_level.setTextColor(Color.BLACK);
            parnight_price_level.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            parnight_price_level.setText(builder.toString());
            parnight_price_level.setLayoutParams(px_3);
            parnight_price_level.setPadding(5, 5, 5, 5);
            layout1.addView(parnight_price_level);

            TextView parnigh_price = new TextView(G20P28A19AccommodationHistory_AH_2.this);
            LinearLayout.LayoutParams px_4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            px_4.setMargins(0, 0, 5, 0);
            parnigh_price.setTextSize(11);
            parnigh_price.setTextColor(Color.BLACK);
            parnigh_price.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            parnigh_price.setText(LS_PRICE.get(nights).toString());
            parnigh_price.setLayoutParams(px_4);
            parnigh_price.setPadding(5, 5, 5, 5);
            layout1.addView(parnigh_price);
        }

        LinearLayout layout2 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout2);

        TextView option_title = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(250, LayoutParams.WRAP_CONTENT);
        px_6.setMargins(0, 0, 5, 0);
        option_title.setTextSize(12);
        option_title.setTextColor(Color.BLACK);
        option_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_title.setText("オプション");
        option_title.setLayoutParams(px_6);
        option_title.setPadding(5, 5, 5, 5);
        layout2.addView(option_title);

        TextView option_price = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_7.setMargins(0, 0, 5, 0);
        option_price.setTextSize(12);
        option_price.setTextColor(Color.BLACK);
        option_price.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        option_price.setText(TOTAL_OPTION_PRICE);
        option_price.setLayoutParams(px_7);
        option_price.setPadding(5, 5, 5, 5);
        layout2.addView(option_price);

        LinearLayout layout3 = new LinearLayout(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_8 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        px_8.setMargins(0, 0, 0, 0);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setLayoutParams(px_8);
        layout3.setPadding(0, 0, 0, 0);
        layout3.setGravity(Gravity.CENTER_VERTICAL);
        la_payment_layout.addView(layout3);

        TextView totalamount_title = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_9 = new LinearLayout.LayoutParams(250, LayoutParams.WRAP_CONTENT);
        px_9.setMargins(0, 0, 5, 0);
        totalamount_title.setTextSize(12);
        totalamount_title.setTextColor(Color.BLACK);
        totalamount_title.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        totalamount_title.setText("小計");
        totalamount_title.setLayoutParams(px_9);
        totalamount_title.setPadding(5, 5, 5, 5);
        layout3.addView(totalamount_title);

        la_grand_total = new TextView(G20P28A19AccommodationHistory_AH_2.this);
        LinearLayout.LayoutParams px_10 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        px_10.setMargins(0, 0, 5, 0);
        la_grand_total.setTextSize(11);
        la_grand_total.setTextColor(Color.BLACK);
        la_grand_total.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        la_grand_total.setText(TOTAL_OPTION_PRICE);
        la_grand_total.setLayoutParams(px_10);
        la_grand_total.setPadding(5, 5, 5, 5);
        layout3.addView(la_grand_total);

    }


    private void InitialSetupConfiguration() {
        this.parent = (LinearLayout) findViewById(R.id.g20_p28_group2_roomtype_1);
        this.la_payment_layout = new LinearLayout(this);
        this.la_hotel_name  = new TextView(this);
        this.la_accmodation_date = new TextView(this);
        this.la_smoking_icon = new TextView(this);
        this.la_room_type = new TextView(this);
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
        this.la_total_price = new TextView(this);

        this.RSRVSPRSNUID = new String();
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
        this.ECO_FLG = new String();
        this.ECO_CHECKING = new String();
        this.VOD_FLAG = new String();
        this.BUSINESS_FLAG = new String();
        this.BUSINESS_PACK_TYPE = new String();
        this.CHILDREN_BED = new String();
        this.CHECKIN_TIME = new String();
        this.TOTAL_OPTION_PRICE = new String();
        this.TOTAL_PRICE = new String();
        this.TOTAL_PRICE_INC_TAX = new String();
        this.HOTELVLDFLG = new String();

        this.LS_PRCINCLDNGTAX = new ArrayList<String>();
        this.LS_PRICE = new ArrayList<String>();

    }

    private void G15P19_getDataFrom_G14P18() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if(!obj_g01.getCustRsrvsPrsnUid().isEmpty()){
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
            if(!obj_g01.getCustRsrvid().isEmpty()){
                RSRVID = obj_g01.getCustRsrvid();
            }
            if(!obj_g01.getCustRsrvtnNmbr().isEmpty()){
                RSRVTNNMBR = obj_g01.getCustRsrvtnNmbr();
            }

            if(!obj_g01.getHotelCode().isEmpty()){
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            if(!obj_g01.getNumberOfRoom().isEmpty()){
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if(!obj_g01.getNumberOfStayNight().isEmpty()){
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }
        }
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode = new String();
        private String errorMessage= new String();

        private JSONParse() {
            super();
            setApiRequestDataA019();
        }

        private void setApiRequestDataA019() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setRsrvId(RSRVID);
            api.setHotelCode(HOTEL_CODE);
            api.setRsrvtnNmbr(RSRVTNNMBR);
            Log.e("PARAM-G20P28A19",api.getRequestDataA019().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G20P28A19AccommodationHistory_AH_2.this);
            processDialog.setMessage("実施中....");
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA019());
            JSONObject json = jParser.getJSONData(api.getURLA019());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G20P28A19", json.toString());
            if(!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode =  json.optString(ComConstant.CT_ERRRCODE);
                errorMessage =  json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }

            RSRVTNNMBR = json.optString(ComConstant.CT_RSRVTNNMBR);
            HOTEL_CODE  = json.optString(ComConstant.CT_HTLCODE);
            HOTEL_NAME = json.optString(ComConstant.CT_HTLNAME);
            CHECK_IN_DATE = json.optString(ComConstant.CT_CHCKNDATE);
            CHECK_OUT_DATE = json.optString(ComConstant.CT_CHCKTDATE);
            ROOM_TYPE_CODE = json.optString(ComConstant.CT_ROOMTYPE);
            ROOM_TYPE_NAME = json.optString(ComConstant.CT_ROOMNAME);
            NUMBER_OF_PEOPLE = json.optString(ComConstant.CT_NMBRPPL);
            SMOKING_FLAG = json.optString(ComConstant.CT_SMKNGFLAG);
            FAMILY_NAME = json.optString(ComConstant.CT_FMLYNAME);
            FIRST_NAME = json.optString(ComConstant.CT_FRSTNAME);
            SEX =json.optString(ComConstant.CT_SEX);
            MEMBERSHIP_FLAG = json.optString(ComConstant.CT_MMBRSHPFLAG);
            MEMBERSHIP_NUMBER = json.optString(ComConstant.CT_MMBRSHPNMBR);
            PHONE_NUMBER = json.optString(ComConstant.CT_PHNNMBR);
            COUNTRY = json.optString(ComConstant.CT_NTNLTYCODE);
            ECO_FLG = json.optString(ComConstant.CT_ECOFLAG);
            ECO_CHECKING = json.optString(ComConstant.CT_ECOCHCKN);
            VOD_FLAG = json.optString(ComConstant.CT_VODFLAG);
            BUSINESS_FLAG = json.optString(ComConstant.CT_BSNSSPACKFLAG);
            BUSINESS_PACK_TYPE = json.optString(ComConstant.CT_BSNSSPACKTYPE);
            CHILDREN_BED = json.optString(ComConstant.CT_CHLDRNSHRNGBED);
            CHECKIN_TIME = json.optString(ComConstant.CT_CHCKNTIME);
            TOTAL_OPTION_PRICE = json.optString(ComConstant.CT_TTLOPTNPRC);
            TOTAL_PRICE = json.optString(ComConstant.CT_TTLPRC);
            TOTAL_PRICE_INC_TAX = json.optString(ComConstant.CT_TTLPRCINCLDNGTAX);
            HOTELVLDFLG = json.optString(ComConstant.CT_HTLVLDFLAG);

            try {
                JSONArray jsonData = json.getJSONArray(ComConstant.LT_DLYINFRMTN);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    LS_PRICE.add(jsonObject.getString(ComConstant.CT_PRC));
                   LS_PRCINCLDNGTAX.add(jsonObject.getString(ComConstant.CT_PRCINCLDNGTAX));
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
            jsonErrorPopup(errorCode, errorMessage);
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

    //goTo
    //----------------------------------------------------------------------------------------------
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

    // Error Popup Box
    //----------------------------------------------------------------------------------------------
    private void jsonErrorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code) ;
        la_errorCode.setText("[" +eCode+ "] エラー");
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.json_error_message) ;
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
}
