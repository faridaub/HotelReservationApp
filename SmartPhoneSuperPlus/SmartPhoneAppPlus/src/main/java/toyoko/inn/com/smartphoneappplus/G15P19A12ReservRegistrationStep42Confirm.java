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
import java.util.Map;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.Cmm_BlueBar;
import static toyoko.inn.com.smartphoneappplus.common.ComActivity.Cmm_FieldLevel;
import static toyoko.inn.com.smartphoneappplus.common.ComActivity.getRegisCustInfoHideAble;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateConcatWithDateFormate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.getMultipleRoomsData;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.getScreen;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.isDataSuccess;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.isNetworkAvailable;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G15P19A12ReservRegistrationStep42Confirm extends Activity {
    private G01P01ParcelableData obj_g01;

    ArrayList<Map<String, ArrayList<String>>> dataArray = new ArrayList<Map<String, ArrayList<String>>>();

    ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> fullData = new ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>();

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
    private ArrayList<String> LS_COUNTRY_CODE;
    private ArrayList<String> LS_COUNTRY_VALUE;
    private ArrayList<String> LS_SEX;
    private ArrayList<String> LS_PHONE_NUMBER;
    private ArrayList<String> LS_BUSINESS_FLAG;
    private ArrayList<String> LS_BUSINESS_PACK_SELECTED;
    private ArrayList<String> LS_ECO_FLAG;
    private ArrayList<String> LS_ECO_DATE_SELECTION;
    private ArrayList<String> LS_BABY_FLAG;
    private ArrayList<String> LS_VOD_FLAG;
    private ArrayList<String> LS_CHECKIN_TIME;

    private ArrayList<String> LS_ECO_DATA_ROOM1;
    private ArrayList<String> LS_ECO_DATA_ROOM2;
    private ArrayList<String> LS_ECO_DATA_ROOM3;
    private ArrayList<String> LS_ECO_DATA_ROOM4;


    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String ROOM_TYPE_CODE;
    private String PLAN_CODE;
    private String NUMBER_OF_PEOPLE;
    private LinearLayout parent;
    private String TTLPRCINCLDNGTAX;
    private String TTLPRC;
    private String ROOM1_OPTNPRC;
    private String ROOM2_OPTNPRC;
    private String ROOM3_OPTNPRC;
    private String ROOM4_OPTNPRC;

    private ArrayList<String> optnPrc;
    private ArrayList<String> sbttlPrc;
    private ArrayList<String> sbttlPrcIncldngTax;
    private String RSRVSPRSNUID;
    private String HOTEL_CODE;
    private LinearLayout[] la_mainEcoLayout;
    private LinearLayout[] la_mainBusinessLayout;
    private LinearLayout[] la_mainBabyLayout;
    private LinearLayout[] la_mainVodLayout;
    private ArrayList<String> LS_RSRV_NMBR;
    private String HOTEL_NAME;
    int leftWidth;
    private String ROOM1_CHECK_IN_TIME;
    private String RECEIPTTYPE;
    private String RECEIPTNAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G15P19A12ReservRegistrationStep42Confirm------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g15_p19_reservation_confirmation_4);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
        GetData();

        //Print Data which We Receive
        CreateLayoutProgrammatically();

        //Print Data which We Receive
        GoToReservationCompletePage();

        //Execute Json Parser
        SetupToJsonCheck();

        //BackTo
        BackTo();

        //BackTo
        Edit();

    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g14_p18_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(COD_BACK);
            }
        });

    }

    private void Edit() {
        Button button = (Button) findViewById(R.id.g15_p19_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(COD_BACK);
            }
        });

    }


    private void GoToReservationCompletePage() {
        //go(R.id.g15_p19_submit,G15P35A13ReservRegistrationStep43Finish.class,"normal","no");
        Button button = (Button) findViewById(R.id.g15_p19_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToJsonSubmit();
            }
        });
    }

    private void CreateLayoutProgrammatically() {
        HeadlineHotelName();
        CheckinDate();
        CheckoutDate();
        int numberOfRooms = Integer.valueOf(NUMBER_OF_ROOM);
        this.la_mainEcoLayout = new LinearLayout[numberOfRooms];
        this.la_mainBusinessLayout = new LinearLayout[numberOfRooms];
        this.la_mainBabyLayout = new LinearLayout[numberOfRooms];
        this.la_mainVodLayout = new LinearLayout[numberOfRooms];
        for (int nRooms = 0; nRooms < numberOfRooms; nRooms++) {
            InitialazeLayout(nRooms);
            DynamicHeadlineTitleRow1(nRooms);
            DynamicSmokingTitleRow2(nRooms);
            DynamicNumPeopleTitleRow3(nRooms);
            PeopelInfoAndOptionPrice(nRooms);
        }
    }

    private void InitialazeLayout(int position) {
        this.la_mainEcoLayout[position] = new LinearLayout(this);
        this.la_mainBusinessLayout[position] = new LinearLayout(this);
        this.la_mainBabyLayout[position] = new LinearLayout(this);
        this.la_mainVodLayout[position] = new LinearLayout(this);
    }

    private void HeadlineHotelName() {
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_headline_1);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText( HOTEL_NAME );
        text1.setTextSize(15);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }


    private void CheckinDate() {
        LinearLayout checkinLayout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        checkinLayout.setOrientation(LinearLayout.HORIZONTAL);
        checkinLayout.setBackgroundResource(R.drawable.util_com_background_1);
        checkinLayout.setLayoutParams(p1);
        checkinLayout.setPadding(10, 5, 10, 5);
        checkinLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(checkinLayout);

        TextView col1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        col1.setText(FLD_CHECKIN);
        col1.setTextSize(15);
        col1.setPadding(10, 10, 10, 10);
        col1.setLayoutParams(p3);
        col1.setTextColor(Color.BLACK);
        checkinLayout.addView(col1);

        TextView col2 = new TextView(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        col2.setText(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        col2.setTextSize(15);
        col2.setPadding(10, 10, 10, 10);
        col2.setLayoutParams(p4);
        col2.setTextColor(Color.BLACK);
        checkinLayout.addView(col2);
    }

    private void CheckoutDate() {
        LinearLayout checkOutLayout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 10);
        checkOutLayout.setOrientation(LinearLayout.HORIZONTAL);
        checkOutLayout.setBackgroundResource(R.drawable.util_com_background_1);
        checkOutLayout.setLayoutParams(p1);
        checkOutLayout.setPadding(10, 10, 10, 10);
        checkOutLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(checkOutLayout);

        TextView col1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        col1.setText(FLD_CHECKOUT);
        col1.setTextSize(15);
        col1.setPadding(10, 10, 10, 10);
        col1.setLayoutParams(p3);
        col1.setTextColor(Color.BLACK);
        checkOutLayout.addView(col1);

        TextView col2 = new TextView(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        col2.setText(ComLib.dateConvertFormattedDate(CHECK_OUT_DATE));
        col2.setTextSize(15);
        col2.setPadding(10, 10, 10, 10);
        col2.setLayoutParams(p4);
        col2.setTextColor(Color.BLACK);
        checkOutLayout.addView(col2);
    }

    private void DynamicHeadlineTitleRow1(int rooms) {
        int numroom = rooms + 1;
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_headline_1);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        //Left Text (【お部屋 " + numroom + "】)
        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText( SW_ROOM + SIN_SPACE_1 + numroom );
        text1.setTextSize(15);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }

    private void DynamicSmokingTitleRow2(int rooms) {
        //Second Layout
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setBackgroundResource(R.drawable.util_com_background_1);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        TextView textImg = new TextView(this);
        LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param3.setMargins(10, 0, 5, 0);
        textImg.setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        textImg.setLayoutParams(param3);
        layout1.addView(textImg);

        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText(LS_ROOM_TYPE_NAME.get(rooms).toString());
        text1.setTextSize(15);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        text1.setTextColor(Color.BLACK);
        layout1.addView(text1);
    }

    private void DynamicNumPeopleTitleRow3(int rooms) {
        //Second Layout
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setBackgroundResource(R.drawable.util_com_background_1);
        layout1.setLayoutParams(p1);
        layout1.setPadding(10, 10, 10, 10);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout1);


        Cmm_BlueBar(getApplicationContext(),layout1);

        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        text1.setText(FLD_USING_PEOPLE);
        text1.setTextSize(15);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p3);
        text1.setTextColor(Color.BLACK);
        layout1.addView(text1);

        StringBuilder sb = new StringBuilder();
        sb.append(LS_NUMBER_OF_PEOPLE.get(rooms).toString());
        sb.append(LV_EACH_ROOM_NAME);


        TextView text2 = new TextView(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        text2.setText(sb);
        text2.setTextSize(15);
        text2.setPadding(10, 10, 10, 10);
        text2.setLayoutParams(p4);
        text2.setTextColor(Color.BLACK);
        layout1.addView(text2);
    }

    private void PeopelInfoAndOptionPrice(int rooms) {
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 10);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_background_1);
        layout1.setLayoutParams(p1);
        layout1.setPadding(15, 15, 15, 15);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout1);

        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p2.setMargins(0, 0, 0, -4);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout2.setBackgroundResource(R.drawable.util_gra_greydeep_npad_ystroke_ycorner_clickable);
        layout2.setLayoutParams(p2);
        layout2.setPadding(20, 20, 20, 20);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView text2_1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, -3);
        text2_1.setText(FLD_ACOMOINFO);
        text2_1.setTextSize(15);
        text2_1.setPadding(10, 10, 10, 10);
        text2_1.setLayoutParams(p3);
        text2_1.setTextColor(Color.BLACK);
        layout2.addView(text2_1);

        for (int nFields = 0; nFields < leftTitleLevel().size(); nFields++) {
            int x = -3;
            if (nFields == leftTitleLevel().size() - 1) {
                x = 0;
            }
            LinearLayout child1 = new LinearLayout(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            p4.setMargins(0, 0, 0, x);
            child1.setOrientation(LinearLayout.HORIZONTAL);
            child1.setLayoutParams(p4);
            child1.setGravity(Gravity.CENTER_VERTICAL);
            layout1.addView(child1);

            // Left Part (Layout)
            LinearLayout leftPart = new LinearLayout(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(leftWidth, LayoutParams.MATCH_PARENT);
            p5.setMargins(0, 0, 0, x);
            leftPart.setOrientation(LinearLayout.HORIZONTAL);
            leftPart.setBackgroundResource(R.drawable.util_confirm_left_section);
            leftPart.setLayoutParams(p5);
            leftPart.setPadding(10, 25, 10, 25);
            leftPart.setGravity(Gravity.CENTER | Gravity.LEFT);
            child1.addView(leftPart);

            Cmm_BlueBar(getApplicationContext(),leftPart);

            //Left Text
            TextView leftText = new TextView(this);
            LinearLayout.LayoutParams p7 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            p7.setMargins(0, 0, 0, 5);
            leftText.setText(leftTitleLevel().get(nFields).toString());
            leftText.setPadding(20, 0, 20, 0);
            leftText.setTextColor(Color.BLACK);
            leftText.setLayoutParams(p7);
            leftText.setTextSize(11);
            leftPart.addView(leftText);

            // Right Part (Layout)
            LinearLayout rightPart = new LinearLayout(this);
            LinearLayout.LayoutParams p8 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            p8.setMargins(0, 0, 0, x);
            rightPart.setOrientation(LinearLayout.HORIZONTAL);
            rightPart.setBackgroundResource(R.drawable.util_confirm_right_section);
            rightPart.setLayoutParams(p8);
            rightPart.setPadding(10, 25, 10, 25);
            rightPart.setGravity(Gravity.CENTER_VERTICAL);
            child1.addView(rightPart);

            // Right Text
            TextView rightText = new TextView(this);
            LinearLayout.LayoutParams p9 = new LinearLayout.LayoutParams(10, LayoutParams.WRAP_CONTENT);
            p9.weight = 1f;
            rightText.setText(rightTitleValue(rooms).get(nFields).toString());
            rightText.setPadding(15, 0, 15, 0);
            rightText.setTextColor(Color.BLACK);
            rightText.setTextSize(11);
            rightText.setLayoutParams(p9);
            rightPart.addView(rightText);

        }
        DynamicOptionTitleRow4(layout1, rooms);
    }

    private void DynamicOptionTitleRow4(LinearLayout layout1, int rooms) {

        //Third Layout Customer Information Layout
        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p2.setMargins(0, 20, 0, -1);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout2.setBackgroundResource(R.drawable.util_gra_greydeep_npad_ystroke_ycorner_clickable);
        layout2.setLayoutParams(p2);
        layout2.setPadding(10, 10, 10, 10);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        //Left Option (選択したオプション)
        TextView text2_1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        text2_1.setText(FLD_OPTION_SELECTION);
        text2_1.setTextSize(15);
        text2_1.setPadding(10, 10, 10, 10);
        text2_1.setLayoutParams(p3);
        text2_1.setTextColor(Color.BLACK);
        layout2.addView(text2_1);

        int rowCounter = 0;
        String ecoPlan = new String();
        if (LS_ECO_FLAG.get(rooms).toString().equalsIgnoreCase(COD_Y)) {
            StringBuilder sp = new StringBuilder();
            sp.append(SIN_START_BRECKET);
            sp.append(SW_TERGETDATE);

            if (rooms == 0) {
                sp.append(dateConcatWithDateFormate(LS_ECO_DATA_ROOM1));
            } else if (rooms == 1) {
                sp.append(dateConcatWithDateFormate(LS_ECO_DATA_ROOM2));
            } else if (rooms == 2) {
                sp.append(dateConcatWithDateFormate(LS_ECO_DATA_ROOM3));
            } else if (rooms == 3) {
                sp.append(dateConcatWithDateFormate(LS_ECO_DATA_ROOM4));
            }else{
                sp.append(dateConcatWithDateFormate(LS_ECO_DATA_ROOM1));
            }

            sp.append(SIN_CLOSE_BRECKET);
            ecoPlan = sp.toString();
        } else {
            la_mainEcoLayout[rooms].setVisibility(View.GONE);
            ecoPlan = ST_ONE;
        }

        String businessSelectFlag = new String();
        if (LS_BUSINESS_FLAG.get(rooms).toString().equalsIgnoreCase("Y")) {
            String businessPack = new String();
            if (LS_BUSINESS_PACK_SELECTED.get(rooms).equalsIgnoreCase("1")) {
                businessPack = NUM_BUSINESSPLAN_1;
            } else if (LS_BUSINESS_PACK_SELECTED.get(rooms).equalsIgnoreCase("2")) {
                businessPack = NUM_BUSINESSPLAN_2;
            } else if (LS_BUSINESS_PACK_SELECTED.get(rooms).equalsIgnoreCase("3")) {
                businessPack = NUM_BUSINESSPLAN_3;
            }
            businessSelectFlag = businessPack.toString();
        } else {
            la_mainBusinessLayout[rooms].setVisibility(View.GONE);
            businessSelectFlag = ST_TWO;
        }

        String vodFlag = new String();
        if (LS_VOD_FLAG.get(rooms).toString().equalsIgnoreCase(COD_Y)) {
            vodFlag = SW_YES;
        } else {
            la_mainVodLayout[rooms].setVisibility(View.GONE);
            vodFlag = ST_THREE;
        }

        String babyFlag = new String();
        if (LS_BABY_FLAG.get(rooms).toString().equalsIgnoreCase(COD_Y)) {
            babyFlag = SW_YES;
        } else {
            babyFlag = ST_FOUR;
            la_mainBabyLayout[rooms].setVisibility(View.GONE);

        }

        getRegisCustInfoHideAble(getApplicationContext(), layout1, la_mainEcoLayout[rooms], FLD_ECO_PLAN1, ecoPlan);
        getRegisCustInfoHideAble(getApplicationContext(), layout1, la_mainBusinessLayout[rooms], FLD_BUSINESSPACK, businessSelectFlag);
        getRegisCustInfoHideAble(getApplicationContext(), layout1, la_mainVodLayout[rooms], FLD_VOD1, vodFlag);
        getRegisCustInfoHideAble(getApplicationContext(), layout1, la_mainBabyLayout[rooms], FLD_BABY_FLAG, babyFlag);
    }


    private ArrayList<String> leftTitleLevel() {
        ArrayList<String> left_ttl = new ArrayList<String>();
        left_ttl.add(FLD_FAMILY_NAME1);
        left_ttl.add(FLD_FIRST_NAME1);
        left_ttl.add(FLD_MEMBERSHIP_FLAG1);
        left_ttl.add(FLD_COUNTRY);
        left_ttl.add(FLD_GENDER);
        left_ttl.add(FLD_PHONE_NUM);
        left_ttl.add(FLD_CHECKIN_TIME);
        return left_ttl;
    }

    private ArrayList<String> rightTitleValue(int j) {
        ArrayList<String> right_ttl = new ArrayList<String>();
        String FirstName = new String();
        String LastName = new String();
        String MemberFlag = new String();
        String Nationality = new String();
        String Sex = FLD_GENDER;
        String TelephoneNumber = new String();
        String checkinTime = new String();
        if (!LS_FRIST_NAME.get(j).isEmpty()) {
            FirstName = LS_FRIST_NAME.get(j).toString();
        }
        if (!LS_FAMILY_NAME.get(j).isEmpty()) {
            LastName = LS_FAMILY_NAME.get(j).toString();
        }
        if (!LS_MEMBERSHIP_FLAG.get(j).isEmpty()) {
            if (LS_MEMBERSHIP_FLAG.get(j).equalsIgnoreCase(COD_Y)) {
                MemberFlag = SW_MEMBER;
            } else {
                MemberFlag = SW_GENERAL;
            }
        }
        if (!LS_COUNTRY_VALUE.get(j).isEmpty()) {
            Nationality = LS_COUNTRY_VALUE.get(j).toString();
        }
        if (!LS_SEX.get(j).isEmpty()) {
            if (LS_SEX.get(j).equalsIgnoreCase(COD_M)) {
                Sex = SW_MALE;
            }
        }
        if (!LS_PHONE_NUMBER.get(j).isEmpty()) {
            TelephoneNumber = LS_PHONE_NUMBER.get(j).toString();
        }

        if (!LS_CHECKIN_TIME.get(j).isEmpty()) {
           String cktime =  LS_CHECKIN_TIME.get(j).toString();
            checkinTime = ComLib.getTimeValueAccCode(cktime);
        }

        right_ttl.add(FirstName);
        right_ttl.add(LastName);
        right_ttl.add(MemberFlag);
        right_ttl.add(Nationality);
        right_ttl.add(Sex);
        right_ttl.add(TelephoneNumber);
        right_ttl.add(checkinTime);
        return right_ttl;
    }


    private void InitialSetupConfiguration() {

        //Parent Layout
        this.parent = (LinearLayout) findViewById(R.id.g15_p19_group2_roomtype_1);



        this.LS_CHECK_IN_DATE = new ArrayList<String>();
        this.LS_CHECK_OUT_DATE = new ArrayList<String>();
        this.LS_ROOM_TYPE_NAME = new ArrayList<String>();
        this.LS_NUMBER_OF_PEOPLE = new ArrayList<String>();
        this.LS_FAMILY_NAME = new ArrayList<String>();
        this.LS_FRIST_NAME = new ArrayList<String>();
        this.LS_MEMBERSHIP_FLAG = new ArrayList<String>();
        this.LS_MEMBERSHIP_NUMBER = new ArrayList<String>();
        this.LS_COUNTRY_CODE = new ArrayList<String>();
        this.LS_COUNTRY_VALUE = new ArrayList<String>();
        this.LS_SEX = new ArrayList<String>();
        this.LS_PHONE_NUMBER = new ArrayList<String>();
        this.LS_BUSINESS_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_PACK_SELECTED = new ArrayList<String>();
        this.LS_ECO_FLAG = new ArrayList<String>();
        this.LS_ECO_DATE_SELECTION = new ArrayList<String>();
        this.LS_BABY_FLAG = new ArrayList<String>();
        this.LS_VOD_FLAG = new ArrayList<String>();
        this.LS_CHECKIN_TIME = new ArrayList<String>();


        this.LS_ECO_DATA_ROOM1 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM2 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM3 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM4 = new ArrayList<String>();
        this.LS_RSRV_NMBR = new ArrayList<String>();

        this.RSRVSPRSNUID = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.TTLPRCINCLDNGTAX = new String();
        this.TTLPRC = new String();
        this.HOTEL_CODE = new String();
        this.HOTEL_NAME = new String();
        this.RECEIPTTYPE = new String();
        this.RECEIPTNAME = new String();

        this.ROOM1_OPTNPRC = new String();
        this.ROOM2_OPTNPRC = new String();
        this.ROOM3_OPTNPRC = new String();
        this.ROOM4_OPTNPRC = new String();
        this.ROOM1_CHECK_IN_TIME = new String();
        this.optnPrc = new ArrayList<String>();
        this.sbttlPrc = new ArrayList<String>();
        this.sbttlPrcIncldngTax = new ArrayList<String>();
        this.leftWidth = Integer.valueOf(getScreen(this).get("leftwidth"));
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            //General Data
            //--------------------------------------------------------------------------------------
            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getRdPlanCode().isEmpty()) {
                PLAN_CODE = obj_g01.getRdPlanCode();
            }

            if (!obj_g01.getReceiptType().isEmpty()) {
                RECEIPTTYPE = obj_g01.getReceiptType();
            }

            if (!obj_g01.getReceiptType().isEmpty()) {
                RECEIPTNAME = obj_g01.getReceiptName();
            }


            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getHotelCode().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCode();
            }

            //List Of Data
            //--------------------------------------------------------------------------------------
            if (!obj_g01.getLsCheckInDate().isEmpty()) {
                LS_CHECK_IN_DATE = obj_g01.getLsCheckInDate();
            }
            if (!obj_g01.getLsCheckOutDate().isEmpty()) {
                LS_CHECK_OUT_DATE = obj_g01.getLsCheckOutDate();
            }
            if (!obj_g01.getLsRoomTypeName().isEmpty()) {
                LS_ROOM_TYPE_NAME = obj_g01.getLsRoomTypeName();
            }
            if (!obj_g01.getLsNumberPeople().isEmpty()) {
                LS_NUMBER_OF_PEOPLE = obj_g01.getLsNumberPeople();
            }
            if (!obj_g01.getLsFamilyName().isEmpty()) {
                LS_FAMILY_NAME = obj_g01.getLsFamilyName();
            }
            if (!obj_g01.getLsFirstName().isEmpty()) {
                LS_FRIST_NAME = obj_g01.getLsFirstName();
            }
            if (!obj_g01.getLsMembershipFlag().isEmpty()) {
                LS_MEMBERSHIP_FLAG = obj_g01.getLsMembershipFlag();
            }
            if (!obj_g01.getLsMembershipNo().isEmpty()) {
                LS_MEMBERSHIP_NUMBER = obj_g01.getLsMembershipNo();
            }
            if (!obj_g01.getLsCountryCode().isEmpty()) {
                LS_COUNTRY_CODE = obj_g01.getLsCountryCode();
            }
            if (!obj_g01.getLsCountryValue().isEmpty()) {
                LS_COUNTRY_VALUE = obj_g01.getLsCountryValue();
            }

            if (!obj_g01.getLsSex().isEmpty()) {
                LS_SEX = obj_g01.getLsSex();
            }
            if (!obj_g01.getLsPhoneNumber().isEmpty()) {
                LS_PHONE_NUMBER = obj_g01.getLsPhoneNumber();
            }
            if (!obj_g01.getLsBusinessFlag().isEmpty()) {
                LS_BUSINESS_FLAG = obj_g01.getLsBusinessFlag();
            }
            if (!obj_g01.getLsBusinessData().isEmpty()) {
                LS_BUSINESS_PACK_SELECTED = obj_g01.getLsBusinessData();
            }
            if (!obj_g01.getLsEcoFlag().isEmpty()) {
                LS_ECO_FLAG = obj_g01.getLsEcoFlag();
            }
            if (!obj_g01.getLsEcoDateSelection().isEmpty()) {
                LS_ECO_DATE_SELECTION = obj_g01.getLsEcoDateSelection();
            }
            if (!obj_g01.getLsBabyFlag().isEmpty()) {
                LS_BABY_FLAG = obj_g01.getLsBabyFlag();
            }
            if (!obj_g01.getLsVodFlag().isEmpty()) {
                LS_VOD_FLAG = obj_g01.getLsVodFlag();
            }
            if (!obj_g01.getLsCheckinTime().isEmpty()) {
                LS_CHECKIN_TIME = obj_g01.getLsCheckinTime();
            }

            if (!obj_g01.getRoom1CheckinTime().isEmpty()) {
                ROOM1_CHECK_IN_TIME = obj_g01.getRoom1CheckinTime();
            }

            if (!obj_g01.getLsEcoDataRoom1().isEmpty()) {
                LS_ECO_DATA_ROOM1 = obj_g01.getLsEcoDataRoom1();
            }

            if (!obj_g01.getLsEcoDataRoom2().isEmpty()) {
                LS_ECO_DATA_ROOM2 = obj_g01.getLsEcoDataRoom2();
            }

            if (!obj_g01.getLsEcoDataRoom3().isEmpty()) {
                LS_ECO_DATA_ROOM3 = obj_g01.getLsEcoDataRoom3();
            }

            if (!obj_g01.getLsEcoDataRoom4().isEmpty()) {
                LS_ECO_DATA_ROOM4 = obj_g01.getLsEcoDataRoom4();
            }
        }
    }

    private class JSONParseCheck extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        private JSONParseCheck() {
            super();
            MainDataForBeforeSubmitJSONA012();
            setApiRequestDataA012(Integer.valueOf(NUMBER_OF_ROOM));
        }

        private void setApiRequestDataA012(int maxvalue) {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setMood(ST_ONE);
            api.setHotelCode(HOTEL_CODE);
            api.setRoom1_chcknTime(ROOM1_CHECK_IN_TIME);
            for (int x = 0; x < Integer.valueOf(NUMBER_OF_ROOM); x++) {
                if (x == 0) {
                    api.setEcoDtsList1(LS_ECO_DATA_ROOM1);
                } else if (x == 1) {
                    api.setEcoDtsList2(LS_ECO_DATA_ROOM2);
                } else if (x == 2) {
                    api.setEcoDtsList3(LS_ECO_DATA_ROOM3);
                } else if (x == 3) {
                    api.setEcoDtsList4(LS_ECO_DATA_ROOM4);
                }
            }
            Log.e("PARAM-G15P19-4", api.getRequestDataA012(maxvalue, dataArray).toString());
        }

        private void MainDataForBeforeSubmitJSONA012() {
            ArrayList<String> rsrvtnNmbr = new ArrayList<String>();
            ArrayList<String> checkinDate = new ArrayList<String>();
            ArrayList<String> checkOutDate = new ArrayList<String>();
            ArrayList<String> roomTypeCode = new ArrayList<String>();
            ArrayList<String> planCode = new ArrayList<String>();
            ArrayList<String> ecoDtsList = new ArrayList<String>();
            ArrayList<String> ecoChckn = new ArrayList<String>();
            ArrayList<String> bsnssPackType = new ArrayList<String>();

            int max = Integer.valueOf(NUMBER_OF_ROOM);
            for (int i = 0; i < max; i++) {
                rsrvtnNmbr.add(ST_NULL);
                roomTypeCode.add(ROOM_TYPE_CODE);
                planCode.add(PLAN_CODE);
                ecoDtsList.add(ST_NULL);
                ecoChckn.add(COD_N);
                bsnssPackType.add(ST_NULL);
            }

            map.put(CT_RSRVTNNMBR, rsrvtnNmbr);
            map.put(CT_CHCKNDATE, LS_CHECK_IN_DATE);
            map.put(CT_CHCKTDATE, LS_CHECK_OUT_DATE);
            map.put(CT_ROOMTYPE, roomTypeCode);
            map.put(CT_PLANCODE, planCode);
            map.put(CT_NMBRPPL, LS_NUMBER_OF_PEOPLE);
            map.put(CT_FMLYNAME, LS_FAMILY_NAME);
            map.put(CT_FRSTNAME, LS_FRIST_NAME);
            map.put(CT_SEX, LS_SEX);
            map.put(CT_MMBRSHPFLAG, LS_MEMBERSHIP_FLAG);
            map.put(CT_MMBRSHPNMBR, LS_MEMBERSHIP_NUMBER);
            map.put(CT_NTNLTYCODE, LS_COUNTRY_CODE);
            map.put(CT_PHNNMBR, LS_PHONE_NUMBER);
            map.put(CT_ECOFLAG, LS_ECO_FLAG);
            map.put(CT_ECODTSLIST, ecoDtsList);
            map.put(CT_ECOCHCKN, ecoChckn);
            map.put(CT_VODFLAG, LS_VOD_FLAG);
            map.put(CT_BSNSSPACKFLAG, LS_BUSINESS_FLAG);
            map.put(CT_BSNSSPACKTYPE, LS_BUSINESS_PACK_SELECTED);
            map.put(CT_CHLDRNSHRNGBED, LS_BABY_FLAG);
          //  map.put(CT_CHCKNTIME, LS_CHECKIN_TIME);

            dataArray.add(map);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G15P19A12ReservRegistrationStep42Confirm.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA012(Integer.valueOf(NUMBER_OF_ROOM), dataArray));
            JSONObject json = jParser.getJSONData(api.getURLA012());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G15P19-4", json.toString());
            if (!isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            TTLPRC = json.optString(CT_TTLPRC);
            TTLPRCINCLDNGTAX = json.optString(CT_TTLPRCINCLDNGTAX);

            optnPrc = getMultipleRoomsData(json, CT_OPTNPRC, Integer.valueOf(NUMBER_OF_ROOM));
            sbttlPrc = getMultipleRoomsData(json, CT_SBTTLPRC, Integer.valueOf(NUMBER_OF_ROOM));
            sbttlPrcIncldngTax = getMultipleRoomsData(json, CT_SBTTLPRCINCLDNGTAX, Integer.valueOf(NUMBER_OF_ROOM));
            Log.e("JSON-G15P19", json.toString());
            try {
                JSONArray jsonData1 = json.getJSONArray(LT_ROOM1_PRCLIST);
                // Room 1
                for (int i = 0; i < jsonData1.length(); i++) {
                    JSONObject jsonObject = jsonData1.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_PRC, jsonObject.getString(CT_PRC));
                    map.put(CT_PRCINCLDNGTAX, jsonObject.getString(CT_PRCINCLDNGTAX));
                    arraylist_1.add(map);
                }

                // Room 2
                JSONArray jsonData2 = json.getJSONArray(LT_ROOM2_PRCLIST);
                for (int i = 0; i < jsonData2.length(); i++) {
                    JSONObject jsonObject = jsonData2.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_PRC, jsonObject.getString(CT_PRC));
                    map.put(CT_PRCINCLDNGTAX, jsonObject.getString(CT_PRCINCLDNGTAX));
                    arraylist_2.add(map);
                }

                // Room 3
                JSONArray jsonData3 = json.getJSONArray(LT_ROOM3_PRCLIST);
                for (int i = 0; i < jsonData3.length(); i++) {
                    JSONObject jsonObject = jsonData3.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_PRC, jsonObject.getString(CT_PRC));
                    map.put(CT_PRCINCLDNGTAX, jsonObject.getString(CT_PRCINCLDNGTAX));
                    arraylist_3.add(map);
                }

                // Room 4
                JSONArray jsonData4 = json.getJSONArray(LT_ROOM4_PRCLIST);
                for (int i = 0; i < jsonData4.length(); i++) {
                    JSONObject jsonObject = jsonData4.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_PRC, jsonObject.getString(CT_PRC));
                    map.put(CT_PRCINCLDNGTAX, jsonObject.getString(CT_PRCINCLDNGTAX));
                    arraylist_4.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            DynamicPriceLayoutCreation();
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
    }

    private class JSONParseSubmit extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParseSubmit() {
            super();
            MainDataForBeforeSubmitA013();
            setApiRequestDataA013(Integer.valueOf(NUMBER_OF_ROOM));
        }

        private void setApiRequestDataA013(int maxvalue) {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setHotelCode(HOTEL_CODE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            for (int x = 0; x < Integer.valueOf(NUMBER_OF_ROOM); x++) {
                if (x == 0) {
                    api.setEcoDtsList1(LS_ECO_DATA_ROOM1);
                } else if (x == 1) {
                    api.setEcoDtsList2(LS_ECO_DATA_ROOM2);
                } else if (x == 2) {
                    api.setEcoDtsList3(LS_ECO_DATA_ROOM3);
                } else if (x == 3) {
                    api.setEcoDtsList4(LS_ECO_DATA_ROOM4);
                }
            }
            MainDataForBeforeSubmitA013();
            Log.e("PARAM-G15P19-5",api.getRequestDataA013(maxvalue, dataArray).toString());
        }

        private void MainDataForBeforeSubmitA013() {
            ArrayList<String> roomTypeCode = new ArrayList<String>();
            ArrayList<String> planCode = new ArrayList<String>();
            ArrayList<String> ecoDtsList = new ArrayList<String>();
            ArrayList<String> ecoChckn = new ArrayList<String>();
            ArrayList<String> prcList = new ArrayList<String>();
            ArrayList<String> prcIncldngTaxList = new ArrayList<String>();
            ArrayList<String> optnPrc = new ArrayList<String>();
            ArrayList<String> sbttlPrc = new ArrayList<String>();
            ArrayList<String> sbttlPrcIncldngTax = new ArrayList<String>();
            ArrayList<String> receiptType = new ArrayList<String>();
            ArrayList<String> receiptName = new ArrayList<String>();

            int max = Integer.valueOf(NUMBER_OF_ROOM);
            for (int i = 0; i < max; i++) {
                roomTypeCode.add(ROOM_TYPE_CODE);
                planCode.add(PLAN_CODE);
                ecoDtsList.add(ST_NULL);
                ecoChckn.add(COD_N);
                prcList.add(ST_NULL);
                prcIncldngTaxList.add(ST_ZERO);
                optnPrc.add(ST_ZERO);
                sbttlPrc.add(ST_NULL);
                sbttlPrcIncldngTax.add("5000");
                receiptType.add(RECEIPTTYPE);
                receiptName.add(RECEIPTNAME);

            }
            map.put(CT_CHCKNDATE, LS_CHECK_IN_DATE);
            map.put(CT_CHCKTDATE, LS_CHECK_OUT_DATE);
            map.put(CT_ROOMTYPE, roomTypeCode);
            map.put(CT_PLANCODE, planCode);
            map.put(CT_NMBRPPL, LS_NUMBER_OF_PEOPLE);
            map.put(CT_FMLYNAME, LS_FAMILY_NAME);
            map.put(CT_FRSTNAME, LS_FRIST_NAME);
            map.put(CT_SEX, LS_SEX);
            map.put(CT_MMBRSHPFLAG, LS_MEMBERSHIP_FLAG);
            map.put(CT_MMBRSHPNMBR, LS_MEMBERSHIP_NUMBER);
            map.put(CT_NTNLTYCODE, LS_COUNTRY_CODE);
            map.put(CT_PHNNMBR, LS_PHONE_NUMBER);
            map.put(CT_ECOFLAG, LS_ECO_FLAG);
            map.put(CT_ECODTSLIST, ecoDtsList);
            map.put(CT_ECOCHCKN, ecoChckn);
            map.put(CT_VODFLAG, LS_VOD_FLAG);
            map.put(CT_BSNSSPACKFLAG, LS_BUSINESS_FLAG);
            map.put(CT_BSNSSPACKTYPE, LS_BUSINESS_PACK_SELECTED);
            map.put(CT_CHLDRNSHRNGBED, LS_BABY_FLAG);
            map.put(CT_CHCKNTIME, LS_CHECKIN_TIME);
            map.put(CT_PRCLIST, prcList);
            map.put(CT_PRCINCLDNGTAXLIST, prcIncldngTaxList);
            map.put(CT_OPTNPRC, optnPrc);
            map.put(CT_SBTTLPRC, sbttlPrc);
            map.put(CT_SBTTLPRCINCLDNGTAX, sbttlPrcIncldngTax);
            map.put(CT_RECEIPTTYPE,receiptType);
            map.put(CT_RECEIPTNAME,receiptName);

            dataArray.add(map);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G15P19A12ReservRegistrationStep42Confirm.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA013(Integer.valueOf(NUMBER_OF_ROOM), dataArray));
            JSONObject json = jParser.getJSONData(api.getURLA013());
            Log.e("JSON-G15P19-5", json.toString());
            if (!isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            LS_RSRV_NMBR = getMultipleRoomsData(json, CT_RSRV_NMBR, Integer.valueOf(NUMBER_OF_ROOM));
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToParcel();
            goTo(G15P35A13ReservRegistrationStep43Finish.class, COD_NEXT);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BCMN0001")){
                errorMessage = ERR_SYSTEM_ERROR;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void SetupToParcel() {
        obj_g01.setLsRsrv_nmbr(LS_RSRV_NMBR);
    }

    private void DynamicPriceLayoutCreation() {
        LinearLayout TopLayout = new LinearLayout(this);
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0, 10, 0, 10);
        TopLayout.setOrientation(LinearLayout.VERTICAL);
        TopLayout.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        TopLayout.setLayoutParams(l_total);
        TopLayout.setPadding(10, 10, 10, 10);
        TopLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(TopLayout);
        TotalPriceLavel(TopLayout);
        TotalPriceAmount(TopLayout);
        TotalPriceDetails(TopLayout);
        BottomAgreementSectionSubTitel();
    }

    private void TotalPriceLavel(LinearLayout topLayout) {
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setPadding(10, 10, 10, 10);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        topLayout.addView(layout1);


        Cmm_BlueBar(getApplicationContext(),layout1);

        //Total Price Level
        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        text1.setText(FLD_PAYMENT_AMOUNT);
        text1.setTextSize(15);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p3);
        text1.setTextColor(Color.BLACK);
        layout1.addView(text1);
    }

    private void TotalPriceAmount(LinearLayout topLayout) {
        StringBuilder sb_tp = new StringBuilder();
        sb_tp.append(TTLPRC);
        sb_tp.append(SIN_START_BRECKET_TAX);
        sb_tp.append(TTLPRCINCLDNGTAX);
        sb_tp.append(SIN_CLOSE_BRECKET);

        //Total Price 113,000
        TextView text2 = new TextView(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        text2.setText(sb_tp);
        text2.setTextSize(17);
        text2.setTextColor(Color.parseColor("#a70505"));
        text2.setPadding(10, 10, 10, 10);
        text2.setLayoutParams(p4);
        topLayout.addView(text2);
    }

    private void TotalPriceDetails(LinearLayout topLayout) {
        int numberRooms = Integer.valueOf(NUMBER_OF_ROOM);
        for (int rooms = 0; rooms < numberRooms; rooms++) {
            final LinearLayout motherLayout = new LinearLayout(this);
            LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            motherLayout.setOrientation(LinearLayout.VERTICAL);
            motherLayout.setLayoutParams(l_total);
            motherLayout.setPadding(0, 0, 0, 10);
            topLayout.addView(motherLayout);

            //Start Title
            LinearLayout titleLayout = new LinearLayout(this);
            LinearLayout.LayoutParams paramTitle = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            paramTitle.setMargins(0,0,0,-3);
            titleLayout.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
            titleLayout.setOrientation(LinearLayout.HORIZONTAL);
            titleLayout.setLayoutParams(paramTitle);
            motherLayout.addView(titleLayout);

            // Leading Text
            TextView text = new TextView(this);
            text.setText(FLD_BREAKDWON);
            text.setTextSize(15);
            text.setTextColor(Color.BLACK);
            text.setPadding(10, 10, 10, 10);
            titleLayout.addView(text);
            //End Title


            //Left part Payment Details
            LinearLayout leadingPayment = new LinearLayout(this);
            LinearLayout.LayoutParams l_param1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            leadingPayment.setBackgroundResource(R.drawable.util_confirm_yellow_left);
            leadingPayment.setOrientation(LinearLayout.HORIZONTAL);
            leadingPayment.setLayoutParams(l_param1);
            motherLayout.addView(leadingPayment);

            // Left Side Text
            TextView textLeft_hMain = new TextView(this);
            LinearLayout.LayoutParams pText_hMain = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            pText_hMain.setMargins(10, 10, 40, 0);
            textLeft_hMain.setTextSize(15);
            textLeft_hMain.setTextColor(Color.BLACK);
            int ym = rooms + 1;
            textLeft_hMain.setText(FLD_ROOM1 + ym);
            textLeft_hMain.setLayoutParams(pText_hMain);
            leadingPayment.addView(textLeft_hMain);


            //Rigth Layout
            LinearLayout rightLeadingLayout = new LinearLayout(this);
            LinearLayout.LayoutParams l_param2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
          //  rightLeadingLayout.setBackgroundResource(R.drawable.util_confirm_yellow_right);
            rightLeadingLayout.setOrientation(LinearLayout.VERTICAL);
            rightLeadingLayout.setLayoutParams(l_param2);
          //  rightLeadingLayout.setPadding(10, 10, 10, 10);
            rightLeadingLayout.setWeightSum(1);
            leadingPayment.addView(rightLeadingLayout);

            getPaymentData(rightLeadingLayout, rooms);
        }
    }

    private void getPaymentData(LinearLayout rightLeadingLayout, int rooms) {
        int numberNights = Integer.valueOf(NUMBER_OF_NIGHT);
        for (int nights = 0; nights < numberNights; nights++) {
            String price = new String();
            if (arraylist_1.size() != 0) {
                price = arraylist_1.get(nights).get(CT_PRC).toString();
            }
            StringBuilder modData_1 = new StringBuilder();
            modData_1.append(nights + 1);
            modData_1.append(SW_NIGHTDATE);
            modData_1.append(SIN_SPACE_2);
            modData_1.append(price);
            modData_1.toString();

            TextView details_price_1 = new TextView(this);
            LinearLayout.LayoutParams p_text_layRightMain = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            p_text_layRightMain.setMargins(0, 0, 0, 0);
            details_price_1.setTextSize(11);
            details_price_1.setTextColor(Color.BLACK);
            details_price_1.setBackgroundResource(R.drawable.util_confirm_yellow_right);
            details_price_1.setText(modData_1);
            details_price_1.setLayoutParams(p_text_layRightMain);
            details_price_1.setPadding(5, 5, 5, 5);
            rightLeadingLayout.addView(details_price_1);

        }
        //------------------------ End Details Price List -------------------------------------

        //------------------------ Option Price List ------------------------------------------

        String optionPrice = new String();
        if (optnPrc.size() != 0) {
            optionPrice = optnPrc.get(rooms).toString();
        }

        StringBuilder modData_2 = new StringBuilder();
        modData_2.append(SW_OPTION);
        modData_2.append(SIN_SPACE_2);
        modData_2.append(optionPrice);
        modData_2.toString();

        TextView details_price_2 = new TextView(this);
        LinearLayout.LayoutParams p_text_lay_2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        p_text_lay_2.setMargins(0, 0, 0, 0);
        details_price_2.setTextSize(11);
        details_price_2.setTextColor(Color.BLACK);
        details_price_2.setBackgroundResource(R.drawable.util_confirm_yellow_right);
        details_price_2.setText(modData_2);
        details_price_2.setLayoutParams(p_text_lay_2);
        details_price_2.setPadding(5, 5, 5, 5);
        rightLeadingLayout.addView(details_price_2);

        //------------------------ End Option Price List --------------------------------------


        //------------------------ Option Price List ------------------------------------------

        String subTotalPrice = new String();
        if (sbttlPrc.size() != 0) {
            subTotalPrice = sbttlPrc.get(rooms).toString();
        }

        String subTotalPriceTax = new String();
        if (sbttlPrcIncldngTax.size() != 0) {
            subTotalPriceTax = sbttlPrcIncldngTax.get(rooms).toString();
        }

        StringBuilder modData_3 = new StringBuilder();
        modData_3.append(SW_TOTALIZATION1);
        modData_3.append(SIN_SPACE_1);
        modData_3.append(subTotalPrice);
        modData_3.append(SIN_START_BRECKET);
        modData_3.append(SW_TAX_INC);
        modData_3.append(subTotalPriceTax);
        modData_3.append(SIN_CLOSE_BRECKET);


        TextView details_price_3 = new TextView(this);
        LinearLayout.LayoutParams p_text_lay_3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        p_text_lay_3.setMargins(0, 0, 0, 0);
        details_price_3.setTextSize(11);
        details_price_3.setTextColor(Color.BLACK);
        details_price_3.setBackgroundResource(R.drawable.util_confirm_yellow_right);
        details_price_3.setText(modData_3);
        details_price_3.setLayoutParams(p_text_lay_3);
        details_price_3.setPadding(5, 5, 5, 5);
        rightLeadingLayout.addView(details_price_3);

        //------------------------ End Option Price List --------------------------------------
    }

    private void BottomAgreementSectionSubTitel() {

        LinearLayout TopLayout = new LinearLayout(this);
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0, 10, 0, 30);
        TopLayout.setOrientation(LinearLayout.VERTICAL);
        TopLayout.setBackgroundResource(R.drawable.util_confirm_notice);
        TopLayout.setLayoutParams(l_total);
        TopLayout.setPadding(10, 10, 10, 10);
        TopLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(TopLayout);

            LinearLayout SubLayout = new LinearLayout(this);
            LinearLayout.LayoutParams l_totals = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            SubLayout.setOrientation(LinearLayout.HORIZONTAL);
           // SubLayout.setBackgroundResource(R.drawable.util_confirm_notice);
            SubLayout.setLayoutParams(l_totals);
            TopLayout.addView(SubLayout);

                Cmm_BlueBar(getApplicationContext(),SubLayout);

                Cmm_FieldLevel(getApplicationContext(),SubLayout,SW_AGREEMENT,0);

            TextView text2 = new TextView(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            p4.weight = 1f;
            p4.setMargins(0, 0, 5, 0);
            text2.setText(LV_AMOUNT_NOTICE);
            text2.setTextSize(15);
            text2.setTextColor(Color.BLACK);
            text2.setPadding(10, 10, 10, 10);
            text2.setLayoutParams(p4);
            TopLayout.addView(text2);

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

    private void SetupToJsonCheck() {
        if (isNetworkAvailable(this)) {
            new JSONParseCheck().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

    private void SetupToJsonSubmit() {
        if (isNetworkAvailable(this)) {
            new JSONParseSubmit().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
