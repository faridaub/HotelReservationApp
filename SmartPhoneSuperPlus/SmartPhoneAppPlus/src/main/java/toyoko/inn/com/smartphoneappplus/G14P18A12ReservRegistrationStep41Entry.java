package toyoko.inn.com.smartphoneappplus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePair;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePairArrayAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComReservRegisActivity;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import static toyoko.inn.com.smartphoneappplus.common.ComActivity.*;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G14P18A12ReservRegistrationStep41Entry extends Activity {
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String HOTEL_CODE;
    private String ROOM_TYPE_CODE;
    private String ROOM_TYPE_NAME;
    private String NUMBER_OF_NIGHT;
    private String RD_NUMBER_OF_MAX_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String MODE; // If mood = 2 then without must item price will display

    ArrayList<HashMap<String, String>> arraylist;
    private ArrayList<HashMap<String, String>> arrayList;
    private ArrayList<HashMap<String, String>> dlyPrcInfrmtnArrayList;
    ArrayList<Map<String, ArrayList<String>>> dataArray = new ArrayList<Map<String, ArrayList<String>>>();
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    public ArrayList<HashMap<String, String>> arraylist_1 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_2 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_3 = new ArrayList<HashMap<String, String>>();
    public ArrayList<HashMap<String, String>> arraylist_4 = new ArrayList<HashMap<String, String>>();


    private List<String> EQUIPMENT_LIST;
    private String TERMS_AND_CONDITION;


    //View
    private G01P01ParcelableData obj_g01;
    private String TOTAL_PRICE_TAX;
    private String TOTAL_PRICE;
    //private TextView la_total_price_calc;
    //private TextView la_checkOutDate;

    private String TTLPRCINCLDNGTAX;
    private String TTLPRC;


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
    private LinearLayout parent;

    private LinearLayout[] px_business_pack_layout;
    private LinearLayout[] px_ecoSelectionLayout;

    private TextView[] la_roomTypeName;
    private Button[] la_updateRoom;
    private TextView[] la_smokingIcon;
    private EditText[] la_familyName;
    private EditText[] la_firstName;
    private EditText[] la_membershipNumber;
    private EditText[] la_phoneNumber;
    private RadioButton[] la_male;
    private RadioButton[] la_fmale;
    private Spinner[] la_country;
    private Spinner[] la_gender;
    private ToggleButton[] la_membershipFlag;
    private ToggleButton[] la_ecoFlag;
    private ToggleButton[] la_businessFlag;
    private ToggleButton[] la_vodFlag;
    private ToggleButton[] la_babyFlag;
    private Spinner[] la_checkInTime;
    private CheckBox[][] la_dateCheckbox;
    private RadioButton[][] la_businessPack;

    private String[] st_familyName;
    private String[] st_firstName;
    private String[] st_membershipFlag;
    private String[] st_membershipNumber;
    private String[] st_gender_code;
    private String[] st_gender_value;
    private String[] st_country_code;
    private String[] st_country_value;
    private String[] st_phoneNumber;
    private String[] st_ecoFlag;
    private String[] st_ecoSelection;
    private String[] st_vodFlag;
    private String[] st_businessFlag;
    private String[] st_businessSelected;
    private String[] st_babyFlag;


    String[] st_room1EcoDateArray;
    String[] st_room2EcoDateArray;
    String[] st_room3EcoDateArray;
    String[] st_room4EcoDateArray;

    private ArrayList<String> LS_ECO_DATA_ROOM1;
    private ArrayList<String> LS_ECO_DATA_ROOM2;
    private ArrayList<String> LS_ECO_DATA_ROOM3;
    private ArrayList<String> LS_ECO_DATA_ROOM4;



    private int[] st_personCounter;
    private boolean validationFlag;
    private LinearLayout genderLayout;
    private TextView la_totalPrice;
    private ArrayList<String> optnPrc;
    private ArrayList<String> sbttlPrc;
    private ArrayList<String> sbttlPrcIncldngTax;


    private String RSRVSPRSNUID;
    private String SMOKING_FLAG;
    private String NUMBER_OF_MAX_PEOPLE;
    private ArrayList<HashMap<String, ArrayList<String>>> EcoDataBigArray;
    private String PAGE_FLAG;
    private String MEMBERSHIP_FLAG;
    private String SEX;
    private Boolean submitFlag;
    private String[] st_checkinTime;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String NTNLTYCODE;
    private String PHONE_NUMBER;
    private String ECO_FLAG;

    int ecoPositionRoom1;
    int ecoPositionRoom2;
    int ecoPositionRoom3;
    int ecoPositionRoom4;
    private String HOTEL_NAME;
    int ecoWidth;
    int echHight;
    private String screenSize;
    private String PLAN_CODE;
    private String ROOM1_CHECK_IN_TIME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G14P18A12ReservRegistrationStep41Entry------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g14_p18_reservation_registration_3);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G13P17
        GetData();

        //Initialize Variable
        InitializeVariable();

        //Print Data which We Receive
        CreateLayoutProgrammatically();

        //Load Data If Exists
        SetupToView();

        //Back To G13P17
        BackTo();

        //Back To G13P17
        GoToConfirmPage();

        //Check Starting
        checkStart();

    }

    private void InitializeVariable() {
        int numberOfRooms = iData(NUMBER_OF_ROOM);
        this.px_business_pack_layout = new LinearLayout[numberOfRooms];
        this.la_roomTypeName = new TextView[numberOfRooms];
        this.la_updateRoom = new Button[numberOfRooms];
        this.la_smokingIcon = new TextView[numberOfRooms];
        this.la_familyName = new EditText[numberOfRooms];
        this.la_firstName = new EditText[numberOfRooms];

        this.la_membershipFlag = new ToggleButton[numberOfRooms];
        this.la_male = new RadioButton[numberOfRooms];
        this.la_fmale = new RadioButton[numberOfRooms];
        this.la_membershipNumber = new EditText[numberOfRooms];
        this.la_phoneNumber = new EditText[numberOfRooms];
        this.la_country = new Spinner[numberOfRooms];
        this.la_gender = new Spinner[numberOfRooms];

        this.la_ecoFlag = new ToggleButton[numberOfRooms];
        this.px_ecoSelectionLayout = new LinearLayout[numberOfRooms];
        this.la_businessFlag = new ToggleButton[numberOfRooms];
        this.la_vodFlag = new ToggleButton[numberOfRooms];
        this.la_babyFlag = new ToggleButton[numberOfRooms];
        this.la_checkInTime = new Spinner[numberOfRooms];
        this.la_dateCheckbox = new CheckBox[numberOfRooms][7];
        this.la_businessPack = new RadioButton[numberOfRooms][3];

        this.st_familyName = new String[numberOfRooms];
        this.st_firstName = new String[numberOfRooms];
        this.st_membershipFlag = new String[numberOfRooms];
        this.st_membershipNumber = new String[numberOfRooms];
        this.st_gender_code = new String[numberOfRooms];
        this.st_gender_value = new String[numberOfRooms];
        this.st_country_code = new String[numberOfRooms];
        this.st_country_value = new String[numberOfRooms];
        this.st_phoneNumber = new String[numberOfRooms];
        this.st_ecoFlag = new String[numberOfRooms];
        this.st_ecoSelection = new String[numberOfRooms];
        this.st_vodFlag = new String[numberOfRooms];
        this.st_businessFlag = new String[numberOfRooms];
        this.st_businessSelected = new String[numberOfRooms];
        this.st_babyFlag = new String[numberOfRooms];
        this.st_checkinTime =  new String[numberOfRooms];
        this.st_personCounter = new int[numberOfRooms];

        for (int i = 0; i < iData(NUMBER_OF_ROOM); i++) {
            final int finalI = i;
            if (i == 0) {
                st_familyName[i] = FAMILY_NAME;
                st_firstName[i] = FIRST_NAME;
                st_membershipFlag[i] = MEMBERSHIP_FLAG;
                st_membershipNumber[i] = "";
                st_gender_code[i] = SEX;
                st_country_code[i] = NTNLTYCODE;
                st_phoneNumber[i] = PHONE_NUMBER;
                st_ecoFlag[i] = "N";
                st_ecoSelection[i] = "";
                st_vodFlag[i] = "N";
                st_businessFlag[i] = "N";
                st_businessSelected[i] = "";
                st_babyFlag[i] = "N";
                st_checkinTime[i] = "150000";
            } else {
                st_familyName[i] = "";
                st_firstName[i] = "";
                st_membershipFlag[i] = "N";
                st_membershipNumber[i] = "";
                st_gender_code[i] = "M";
                st_country_code[i] = "JPN";
                st_phoneNumber[i] = "";
                st_ecoFlag[i] = "N";
                st_ecoSelection[i] = "";
                st_vodFlag[i] = "N";
                st_businessFlag[i] = "N";
                st_businessSelected[i] = "";
                st_babyFlag[i] = "N";
                st_checkinTime[i] = "20000";
            }
        }
    }

    private void InitialSetupConfiguration() {
        parent = (LinearLayout) findViewById(R.id.g15_p19_group2_roomtype_1);

        //Param Data Internal Constant
        this.CHECK_IN_DATE = new String();
        this.HOTEL_CODE = new String();
        this.ROOM_TYPE_CODE = new String();
        this.ROOM_TYPE_NAME = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.RD_NUMBER_OF_MAX_PEOPLE = new String();
        this.NUMBER_OF_MAX_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.CHECK_OUT_DATE = new String();
        this.TOTAL_PRICE_TAX = new String();
        this.TOTAL_PRICE = new String();
        this.RSRVSPRSNUID = new String();
        this.MODE = new String();
        this.SMOKING_FLAG = new String();
        this.PAGE_FLAG = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.SEX = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.NTNLTYCODE = new String();
        this.PHONE_NUMBER = new String();
        this.ECO_FLAG = new String();
        this.HOTEL_NAME = new String();
        this.PLAN_CODE = new String();
        this.ROOM1_CHECK_IN_TIME = new String();


        this.LS_CHECK_IN_DATE = new ArrayList<String>();
        this.LS_CHECK_OUT_DATE = new ArrayList<String>();
        this.LS_ROOM_TYPE_NAME = new ArrayList<String>();
        this.LS_FRIST_NAME = new ArrayList<String>();
        this.LS_FAMILY_NAME = new ArrayList<String>();
        this.LS_MEMBERSHIP_FLAG = new ArrayList<String>();
        this.LS_MEMBERSHIP_NUMBER = new ArrayList<String>();
        this.LS_SEX = new ArrayList<String>();
        this.LS_PHONE_NUMBER = new ArrayList<String>();
        this.LS_COUNTRY_CODE = new ArrayList<String>();
        this.LS_COUNTRY_VALUE = new ArrayList<String>();
        this.LS_ECO_FLAG = new ArrayList<String>();
        this.LS_VOD_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_PACK_SELECTED = new ArrayList<String>();
        this.LS_ECO_DATE_SELECTION = new ArrayList<String>();
        this.LS_BABY_FLAG = new ArrayList<String>();
        this.LS_CHECKIN_TIME = new ArrayList<String>();
        this.LS_NUMBER_OF_PEOPLE = new ArrayList<String>();

        //Json Array
        this.arraylist = new ArrayList<HashMap<String, String>>();
        this.dlyPrcInfrmtnArrayList = new ArrayList<HashMap<String, String>>();
        this.TERMS_AND_CONDITION = new String();
        this.EQUIPMENT_LIST = new ArrayList<String>();

        this.TTLPRCINCLDNGTAX = new String();
        this.TTLPRC = new String();

        this.EcoDataBigArray = new ArrayList<HashMap<String, ArrayList<String>>>();
        //this.la_total_price_calc = (TextView) findViewById(R.id.g14p188_total_price_calc);
        //this.la_checkOutDate = (TextView) findViewById(R.id.g14_p18_check_out_date);
        this.optnPrc = new ArrayList<String>();
        this.sbttlPrc = new ArrayList<String>();
        this.sbttlPrcIncldngTax = new ArrayList<String>();
        this.ecoPositionRoom1 = NM_ZERO;
        this.ecoPositionRoom2 = NM_ZERO;
        this.ecoPositionRoom3 = NM_ZERO;
        this.ecoPositionRoom4 = NM_ZERO;

        this.st_room1EcoDateArray = new String[7];
        this.st_room2EcoDateArray = new String[7];
        this.st_room3EcoDateArray = new String[7];
        this.st_room4EcoDateArray = new String[7];

        this.LS_ECO_DATA_ROOM1 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM2 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM3 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM4 = new ArrayList<String>();

        this.screenSize = new String();
        this.ecoWidth = Integer.valueOf(getScreen(getApplicationContext()).get("ecoWidth"));
        this.echHight= Integer.valueOf(getScreen(getApplicationContext()).get("ecoHeight"));
        this.screenSize = getScreen(getApplicationContext()).get("size");

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getHotelCodeNew().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getRdPlanCode().isEmpty()) {
                PLAN_CODE = obj_g01.getRdPlanCode();
            }

            if (!obj_g01.getRdRoomName().isEmpty()) {
                ROOM_TYPE_NAME = obj_g01.getRdRoomName();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getRdNumberOfMaxPeople().isEmpty()) {
                RD_NUMBER_OF_MAX_PEOPLE = obj_g01.getRdNumberOfMaxPeople();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getRdSmokingFlag();
            }

            if (!obj_g01.getRdNumberOfMaxPeople().isEmpty()) {
                NUMBER_OF_MAX_PEOPLE = obj_g01.getRdNumberOfMaxPeople();
            }

            if (!obj_g01.getRdTotalPrice().isEmpty()) {
                TOTAL_PRICE = obj_g01.getRdTotalPrice();
            }

            if (!obj_g01.getRdTotalPriceTax().isEmpty()) {
                TOTAL_PRICE_TAX = obj_g01.getRdTotalPriceTax();
            }

            if (!obj_g01.getCustFmlyName().isEmpty()) {
               FAMILY_NAME = obj_g01.getCustFmlyName();
            }
            if (!obj_g01.getCustFrstName().isEmpty()) {
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
               MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustNtnltyCode().isEmpty()) {
                NTNLTYCODE = obj_g01.getCustNtnltyCode();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHONE_NUMBER = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getRdEcoFlag().isEmpty()) {
                ECO_FLAG = obj_g01.getRdEcoFlag();
            }
        }
    }

    private void CreateLayoutProgrammatically() {
        int numberOfRooms = iData(NUMBER_OF_ROOM);
        ComReservRegisActivity.ReservCommonHeadline(getApplicationContext(), parent, HOTEL_NAME);
        ComReservRegisActivity.ReservCommonFields(getApplicationContext(), parent, FLD_CHECKIN, ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        ComReservRegisActivity.ReservCommonFields(getApplicationContext(), parent, FLD_CHECKOUT, ComLib.dateConvertFormattedDate(CHECK_OUT_DATE));
        for (int nRooms = 0; nRooms < numberOfRooms; nRooms++) {
            int numroom = nRooms + 1;
            String roomtext = SW_ROOM + numroom ;
            ComReservRegisActivity.ReservCommonHeadline(getApplicationContext(), parent, roomtext);
            Fld_Smoking04(nRooms);
            Fld_NumberPeople05(nRooms);
            /*宿泊者情報*/
            Fld_FamilyName07(nRooms);
            Fld_FirstName08(nRooms);
            Fld_Gender11(nRooms);
            Fld_MembershipFlag(nRooms);
            Fld_MemberNumber13(nRooms);
            Fld_CountrySelection14(nRooms);
            Fld_TelephoneNumber15(nRooms);
            ComReservRegisActivity.ReservCommonHeadline(getApplicationContext(), parent, FLD_ACOMOINFO);
            if(iData(NUMBER_OF_NIGHT)>1) {
                Fld_EcoFlag(nRooms);
                Fld_EcoSelection(nRooms);
            }
            Fld_VodPlan22(nRooms);
            Fld_BusinessFlag(nRooms);
            Fld_BusinessSelection(nRooms);
            Fld_BabyDetailsRow26(nRooms);
            Fld_CheckinTime28(nRooms);
        }

        TTL_DynamicPriceLayoutCreation();
    }

    private void Fld_Smoking04(final int nRooms) {
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, 0);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(param1);
        parent.addView(layout1);

        la_smokingIcon[nRooms] = new TextView(this);
        LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param3.setMargins(10, 0, 5, 0);
        la_smokingIcon[nRooms].setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        la_smokingIcon[nRooms].setLayoutParams(param3);
        layout1.addView(la_smokingIcon[nRooms]);

        la_roomTypeName[nRooms] = new TextView(this);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param2.weight = 1f;
        param2.setMargins(10, 10, 10, 10);
        la_roomTypeName[nRooms].setTextSize(15);
        la_roomTypeName[nRooms].setPadding(10, 10, 10, 10);
        la_roomTypeName[nRooms].setLayoutParams(param2);
        la_roomTypeName[nRooms].setTextColor(Color.BLACK);
        layout1.addView(la_roomTypeName[nRooms]);

        la_updateRoom[nRooms] = new Button(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p3.setMargins(10, 10, 10, 10);
        la_updateRoom[nRooms].setText(SW_UPDATE);
        la_updateRoom[nRooms].setTextSize(12);
        la_updateRoom[nRooms].setPadding(15, 10, 15, 10);
        la_updateRoom[nRooms].setLayoutParams(p3);
        la_updateRoom[nRooms].setTextColor(Color.WHITE);
        la_updateRoom[nRooms].setBackgroundResource(R.drawable.util_com_button_1);
        layout1.addView(la_updateRoom[nRooms]);
    }

    private void Fld_NumberPeople05(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            Cmm_BlueBar(getApplicationContext(), layout);

            TextView text1 = new TextView(this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.weight = 1f;
            p3.setMargins(0, 0, 5, 0);
            text1.setText(FLD_USING_PEOPLE);
            text1.setTextSize(15);
            text1.setPadding(10, 10, 10, 10);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            layout.addView(text1);

            final Button deduction = new Button(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p4.setMargins(10, 10, 10, 10);
            deduction.setText("-");
            deduction.setTextSize(15);
            deduction.setTextSize(20);
            deduction.setLayoutParams(p4);
            deduction.setTextColor(Color.WHITE);
            deduction.setBackgroundResource(R.drawable.util_off);
            layout.addView(deduction);

            StringBuilder sb = new StringBuilder();
            sb.append(RD_NUMBER_OF_MAX_PEOPLE);
            sb.append(FLD_FIRST_NAME1);

            final TextView numberPeople = new TextView(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(10, 10, 10, 10);
            numberPeople.setTextSize(15);
            numberPeople.setText(sb.toString());
            numberPeople.setPadding(10, 10, 10, 10);
            numberPeople.setLayoutParams(p5);
            numberPeople.setTextColor(Color.BLACK);
            layout.addView(numberPeople);

            final Button addition = new Button(this);
            LinearLayout.LayoutParams p6 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p6.setMargins(10, 10, 10, 10);
            addition.setText("+");
            addition.setTextSize(15);
            addition.setTextSize(20);
            addition.setLayoutParams(p6);
            addition.setTextColor(Color.WHITE);
            addition.setBackgroundResource(R.drawable.util_on);
            layout.addView(addition);
            st_personCounter[nRooms] = Integer.valueOf(RD_NUMBER_OF_MAX_PEOPLE);
            getButtonONOFF(addition, deduction, st_personCounter[nRooms], Integer.valueOf(RD_NUMBER_OF_MAX_PEOPLE), 1);
            addition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    st_personCounter[nRooms]++;
                    getButtonONOFF(addition, deduction, st_personCounter[nRooms], Integer.valueOf(RD_NUMBER_OF_MAX_PEOPLE), 1);
                    RD_NUMBER_OF_MAX_PEOPLE = String.valueOf(st_personCounter[nRooms]);
                    StringBuilder sba = new StringBuilder();
                    sba.append(RD_NUMBER_OF_MAX_PEOPLE);
                    sba.append("名");
                    numberPeople.setText(sba.toString());
                }
            });

            deduction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    st_personCounter[nRooms]--;
                    getButtonONOFF(addition, deduction, st_personCounter[nRooms], Integer.valueOf(RD_NUMBER_OF_MAX_PEOPLE), 1);
                    RD_NUMBER_OF_MAX_PEOPLE = String.valueOf(st_personCounter[nRooms]);
                    StringBuilder sbb = new StringBuilder();
                    sbb.append(RD_NUMBER_OF_MAX_PEOPLE);
                    sbb.append("名");
                    numberPeople.setText(sbb.toString());
                }
            });
    }

    private void Fld_FamilyName07(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_FAMILY_NAME2 , 0);

                Cmm_MustItem2(getApplicationContext(), subLayout);

            la_familyName[nRooms] = new EditText(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.weight = 1f;
            p5.setMargins(10, 10, 10, 10);
            la_familyName[nRooms].setTextSize(15);
            la_familyName[nRooms].setPadding(10, 10, 10, 10);
            la_familyName[nRooms].setLayoutParams(p5);
            la_familyName[nRooms].setSingleLine(true);
            la_familyName[nRooms].setBackgroundResource(R.drawable.util_textview_bk_pink);
            la_familyName[nRooms].setTextColor(Color.BLACK);
            la_familyName[nRooms].setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
            layout.addView(la_familyName[nRooms]);
    }

    private void Fld_FirstName08(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_FIRST_NAME2 , 0);

                Cmm_MustItem2(getApplicationContext(), subLayout);

            la_firstName[nRooms] = new EditText(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(10, 10, 10, 10);
            p5.weight = 1;
            la_firstName[nRooms].setTextSize(15);
            la_firstName[nRooms].setPadding(10, 10, 10, 10);
            la_firstName[nRooms].setLayoutParams(p5);
            la_firstName[nRooms].setSingleLine(true);
            la_firstName[nRooms].setBackgroundResource(R.drawable.util_textview_bk_pink);
            la_firstName[nRooms].setTextColor(Color.BLACK);
            la_firstName[nRooms].setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
            layout.addView(la_firstName[nRooms]);
    }


    private void Fld_Gender11(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            Cmm_BlueBar(getApplicationContext(), layout);

            Cmm_FieldLevel(getApplicationContext(), layout,FLD_GENDER, 0);

            Cmm_MustItem2(getApplicationContext(), layout);

            Cmm_WidthSpace(getApplicationContext(), layout);

            la_gender[nRooms] = new Spinner(this);
            layout.addView(la_gender[nRooms]);

    }

    private void Fld_MembershipFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

        Cmm_BlueBar(getApplicationContext(), layout);

        Cmm_FieldLevel(getApplicationContext(), layout,FLD_MEMBERSHIP_FLAG2, 1);

        la_membershipFlag[nRooms] = new ToggleButton(this);
        LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p5.setMargins(0, 0, 5, 0);
        la_membershipFlag[nRooms].setTextSize(15);
        la_membershipFlag[nRooms].setPadding(10, 10, 10, 10);
        la_membershipFlag[nRooms].setLayoutParams(p5);
        la_membershipFlag[nRooms].setTextColor(Color.BLACK);
        layout.addView(la_membershipFlag[nRooms]);

    }

    private void Fld_MemberNumber13(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_MEMBERSHIP_NUM, 0);

            la_membershipNumber[nRooms] = new EditText(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.weight = 1f;
            p5.setMargins(10, 10, 10, 10);
            la_membershipNumber[nRooms].setTextSize(15);
            la_membershipNumber[nRooms].setPadding(10, 10, 10, 10);
            la_membershipNumber[nRooms].setLayoutParams(p5);
            la_membershipNumber[nRooms].setSingleLine(true);
            la_membershipNumber[nRooms].setBackgroundResource(R.drawable.util_textview_bk_pink);
            la_membershipNumber[nRooms].setTextColor(Color.BLACK);
            layout.addView(la_membershipNumber[nRooms]);
    }

    private void Fld_CountrySelection14(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

        Cmm_BlueBar(getApplicationContext(), layout);

        Cmm_FieldLevel(getApplicationContext(), layout,FLD_COUNTRY, 0);

        Cmm_MustItem2(getApplicationContext(), layout);

        Cmm_WidthSpace(getApplicationContext(), layout);

        la_country[nRooms] = new Spinner(this);
        layout.addView(la_country[nRooms]);

    }

    private void Fld_TelephoneNumber15(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_PHONE_NUM, 0);

                Cmm_MustItem2(getApplicationContext(), subLayout);

            la_phoneNumber[nRooms] = new EditText(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.weight = 1f;
            p5.setMargins(10, 10, 10, 10);
            la_phoneNumber[nRooms].setTextSize(15);
            la_phoneNumber[nRooms].setPadding(10, 10, 10, 10);
            la_phoneNumber[nRooms].setLayoutParams(p5);
            la_phoneNumber[nRooms].setSingleLine(true);
            la_phoneNumber[nRooms].setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            la_phoneNumber[nRooms].setBackgroundResource(R.drawable.util_textview_bk_pink);
            la_phoneNumber[nRooms].setTextColor(Color.BLACK);
            la_phoneNumber[nRooms].setKeyListener(DigitsKeyListener.getInstance("1234567890"));
            layout.addView(la_phoneNumber[nRooms]);
            ComReservRegisActivity.ReservCommonHintsWithoutBackground(getApplicationContext(), layout, LV_TELEPHONENUMBER);
    }

    private void Fld_EcoFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_ECO_PLAN2, 1);

                la_ecoFlag[nRooms] = new ToggleButton(this);
                LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                p5.setMargins(0, 0, 5, 0);
                la_ecoFlag[nRooms].setTextSize(15);
                la_ecoFlag[nRooms].setPadding(10, 10, 10, 10);
                la_ecoFlag[nRooms].setLayoutParams(p5);
                la_ecoFlag[nRooms].setTextColor(Color.BLACK);
                subLayout.addView(la_ecoFlag[nRooms]);

            ComReservRegisActivity.ReservCommonHintsWithoutBackground(getApplicationContext(), layout, LV_ECOPLAN);

    }

    private void Fld_EcoSelection(final int nRooms) {
        px_ecoSelectionLayout[nRooms] = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        px_ecoSelectionLayout[nRooms].setOrientation(LinearLayout.VERTICAL);
        px_ecoSelectionLayout[nRooms].setBackgroundResource(R.drawable.util_com_background_1);
        px_ecoSelectionLayout[nRooms].setLayoutParams(p1);
   //     px_ecoSelectionLayout[nRooms].setPadding(10, 10, 10, 10);
        px_ecoSelectionLayout[nRooms].setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(px_ecoSelectionLayout[nRooms]);

        TextView text1 = new TextView(this);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p2.weight = 1f;
        p2.setMargins(0, 0, 5, 0);
        text1.setText(FLD_ECO_PLAN3);
        text1.setTextSize(10);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p2);
        text1.setGravity(Gravity.CENTER);
        text1.setTextColor(Color.BLACK);
        px_ecoSelectionLayout[nRooms].addView(text1);

        LinearLayout layout2 = new LinearLayout(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p3.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setBackgroundResource(R.drawable.util_com_background_1);
        layout2.setLayoutParams(p3);
        layout2.setPadding(10, 5, 10, 5);
        layout2.setGravity(Gravity.CENTER);
        px_ecoSelectionLayout[nRooms].addView(layout2);

        final TextView[] la_dateSelection;
        final int maxField = 7;
        la_dateSelection = new TextView[maxField];
        String dates[] = new String[maxField];
        for (int x = 0; x < maxField; x++) {
            st_room1EcoDateArray[x] = "";
            st_room2EcoDateArray[x] = "";
            st_room3EcoDateArray[x] = "";
            st_room4EcoDateArray[x] = "";

            final LinearLayout layout3 = new LinearLayout(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ecoWidth, echHight);
            p4.setMargins(2, 2, 2, 2);
            layout3.setOrientation(LinearLayout.VERTICAL);
            if (x < iData(NUMBER_OF_NIGHT)-1) {
                layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
            } else {
                layout3.setBackgroundResource(R.drawable.util_confirm_yellow_right);
            }
            layout3.setLayoutParams(p4);
            layout3.setPadding(5, 5, 10, 5);
            layout3.setGravity(Gravity.CENTER);
            layout2.addView(layout3);

            //Checkbox
            LinearLayout.LayoutParams p4_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            if(screenSize.equalsIgnoreCase("HDPI")) {
                p4_1.setMargins(0, 0, 0, 0);
            }else{
                p4_1.setMargins(2, 2, 2, 2);
            }
            la_dateCheckbox[nRooms][x] = new CheckBox(this);
            la_dateCheckbox[nRooms][x].setGravity(Gravity.CENTER);
            la_dateCheckbox[nRooms][x].setId(x);
            la_dateCheckbox[nRooms][x].setLayoutParams(p4_1);
            layout3.addView(la_dateCheckbox[nRooms][x]);

            dates[x] = ComLib.dateMonthDayAdditionUsingDays(CHECK_IN_DATE, String.valueOf(x + 1));
            la_dateSelection[x] = new TextView(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(2, 0, 2, 0);
            la_dateSelection[x].setLayoutParams(p5);
            la_dateSelection[x].setPadding(11, 13, 11, 13);
            if (x < iData(NUMBER_OF_NIGHT)-1) {
                la_dateSelection[x].setText(dates[x]);
            }
            la_dateSelection[x].setTextSize(8);
            if (x < Integer.valueOf(NUMBER_OF_NIGHT)-1) {
                la_dateSelection[x].setTextColor(Color.BLACK);
            } else {
                la_dateSelection[x].setTextColor(Color.GRAY);
            }
            la_dateSelection[x].setId(x);
            layout3.addView(la_dateSelection[x]);
            final int xy = x;

            la_dateCheckbox[nRooms][x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    int data = id + 1;

                    if (xy < iData(NUMBER_OF_NIGHT) - 1) {
                        if (la_dateCheckbox[nRooms][xy].isChecked()) {
                          //  la_dateSelection[xy].setTextColor(Color.WHITE);
                            la_dateCheckbox[nRooms][xy].setChecked(true);
                         //   layout3.setBackgroundResource(R.drawable.util_gra_yellowdeep_npad_ystroke_ycorner_clickable);
                            if (nRooms == 0) {
                                st_room1EcoDateArray[id] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                ecoPositionRoom1++;
                            }
                            if (nRooms == 1) {
                                st_room2EcoDateArray[id] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                ecoPositionRoom2++;
                            }
                            if (nRooms == 2) {
                                st_room3EcoDateArray[id] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                ecoPositionRoom3++;
                            }
                            if (nRooms == 3) {
                                st_room4EcoDateArray[id] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                ecoPositionRoom4++;
                            }

                        } else {

                            if (nRooms == 0) {
                                ecoPositionRoom1--;
                                st_room1EcoDateArray[id] = "";
                            }
                            if (nRooms == 1) {
                                ecoPositionRoom2--;
                                st_room2EcoDateArray[id] = "";
                            }
                            if (nRooms == 2) {
                                ecoPositionRoom3--;
                                st_room3EcoDateArray[id] = "";
                            }
                            if (nRooms == 3) {
                                ecoPositionRoom4--;
                                st_room4EcoDateArray[id] = "";
                            }
                         //   la_dateSelection[xy].setTextColor(Color.BLACK);
                            la_dateSelection[xy].setEnabled(false);
                            la_dateCheckbox[nRooms][xy].setChecked(false);
                         //   layout3.setBackgroundResource(R.drawable.util_gra_yellow_npad_ystroke_ycorner_clickable);
                        }
                        checkStart();
                        /*
                        submitFlag = false;
                        MODE = ST_TWO;
                        SetupToVariable();
                        SetupToJson();
                        */
                    } else {
                        layout3.setBackgroundResource(R.drawable.util_confirm_yellow_right);
                        la_dateSelection[xy].setEnabled(false);
                        la_dateCheckbox[nRooms][xy].setChecked(false);
                    }
                }
            });
        }
    }



    private void Fld_VodPlan22(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_VOD2, 1);

                 la_vodFlag[nRooms] = new ToggleButton(this);
                 LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                 p5.setMargins(0, 0, 5, 0);
                 la_vodFlag[nRooms].setTextSize(15);
                 la_vodFlag[nRooms].setPadding(10, 10, 10, 10);
                 la_vodFlag[nRooms].setLayoutParams(p5);
                 la_vodFlag[nRooms].setTextColor(Color.BLACK);
                 subLayout.addView(la_vodFlag[nRooms]);

        ComReservRegisActivity.ReservCommonHintsWithoutBackground(getApplicationContext(), layout, LV_VODPLAN);

    }

    private void Fld_BusinessFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_BUSINESSPACK, 1);

                la_businessFlag[nRooms] = new ToggleButton(this);
                LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                p5.setMargins(0, 0, 5, 0);
                la_businessFlag[nRooms].setTextSize(15);
                la_businessFlag[nRooms].setPadding(10, 10, 10, 10);
                la_businessFlag[nRooms].setLayoutParams(p5);
                la_businessFlag[nRooms].setTextColor(Color.BLACK);
                subLayout.addView(la_businessFlag[nRooms]);

        ComReservRegisActivity.ReservCommonHintsWithoutBackground(getApplicationContext(), layout, LV_BUSINESSPACK);
    }

    private void Fld_BusinessSelection(final int nRooms) {
        px_business_pack_layout[nRooms] = new LinearLayout(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p3.setMargins(0, 0, 0, -3);
        px_business_pack_layout[nRooms].setOrientation(LinearLayout.VERTICAL);
        px_business_pack_layout[nRooms].setLayoutParams(p3);
        px_business_pack_layout[nRooms].setGravity(Gravity.LEFT);
        parent.addView(px_business_pack_layout[nRooms]);

        ArrayList<String> opt1 = new ArrayList<String>();
        opt1.add(MSG_BUSINESSPLAN_1A);
        opt1.add(MSG_BUSINESSPLAN_2A);
        opt1.add(MSG_BUSINESSPLAN_3A);

        ArrayList<String> opt2 = new ArrayList<String>();
        opt2.add(MSG_BUSINESSPLAN_1B);
        opt2.add(MSG_BUSINESSPLAN_2B);
        opt2.add(MSG_BUSINESSPLAN_3B);

        for (int x = 0; x < 3; x++) {
            int p = -3;
            if (x == 2) {
                p = 0;
            }

            //Group Layout
            LinearLayout optionGroup = new LinearLayout(this);
            LinearLayout.LayoutParams p6 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p6.setMargins(0, 0, 0, p);
            optionGroup.setOrientation(LinearLayout.HORIZONTAL);
            optionGroup.setBackgroundResource(R.drawable.util_com_background_1);
            optionGroup.setLayoutParams(p6);
            px_business_pack_layout[nRooms].addView(optionGroup);

            LinearLayout subGroup_1 = new LinearLayout(this);
            LinearLayout.LayoutParams p7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p6.setMargins(0, 0, 0, p);
            subGroup_1.setPadding(10,0,10,0);
            subGroup_1.setOrientation(LinearLayout.VERTICAL);
            subGroup_1.setLayoutParams(p7);
            optionGroup.addView(subGroup_1);

            la_businessPack[nRooms][x] = new RadioButton(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p4.setMargins(0, 0, 0, p);
            la_businessPack[nRooms][x].setLayoutParams(p4);
            la_businessPack[nRooms][x].setPadding(0,0,0,0);
            la_businessPack[nRooms][x].setId(x);
            la_businessPack[nRooms][x].setTextColor(Color.BLACK);
            subGroup_1.addView(la_businessPack[nRooms][x]);
            final int finalX = x;
            la_businessPack[nRooms][x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    int selectedPlan =  id + 1;
                    st_businessSelected[nRooms] = String.valueOf(selectedPlan);
                    for (int j = 0; j < 3; j++) {
                        if (j == id) {
                            la_businessPack[nRooms][j].setChecked(true);
                        } else {
                            la_businessPack[nRooms][j].setChecked(false);
                        }
                    }
                    checkStart();
                }
            });

            LinearLayout subGroup_2 = new LinearLayout(this);
            LinearLayout.LayoutParams p8 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT,1f);
            p6.setMargins(0, 0, 0, p);
            subGroup_2.setOrientation(LinearLayout.VERTICAL);
            subGroup_2.setBackgroundResource(R.drawable.util_com_background_1);
            subGroup_2.setLayoutParams(p8);
            optionGroup.addView(subGroup_2);


            TextView testLevel = new TextView(this);
            LinearLayout.LayoutParams p9 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p9.setMargins(0, 0, 5, 0);
            testLevel.setText(opt1.get(x).toString());
            testLevel.setTextSize(10);
            testLevel.setPadding(10, 10, 10, 10);
            testLevel.setLayoutParams(p9);
            testLevel.setTextColor(Color.BLACK);
            subGroup_2.addView(testLevel);


            TextView textsubLevel = new TextView(this);
            LinearLayout.LayoutParams p10 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p10.setMargins(0, 0, 5, 0);
            textsubLevel.setText(opt2.get(x).toString());
            textsubLevel.setTextSize(10);
            textsubLevel.setPadding(10, 10, 10, 10);
            textsubLevel.setLayoutParams(p10);
            textsubLevel.setTextColor(Color.BLUE);
            subGroup_2.addView(textsubLevel);
        }
    }

    private void Fld_BabyDetailsRow26(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            LinearLayout subLayout = new LinearLayout(this);
            LinearLayout.LayoutParams p2_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setLayoutParams(p2_1);
            subLayout.setGravity(Gravity.LEFT);
            layout.addView(subLayout);

                Cmm_BlueBar(getApplicationContext(), subLayout);

                Cmm_FieldLevel(getApplicationContext(), subLayout,FLD_BABY_FLAG, 1);

                la_babyFlag[nRooms] = new ToggleButton(this);
                LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                p5.setMargins(0, 0, 5, 0);
                la_babyFlag[nRooms].setTextSize(15);
                la_babyFlag[nRooms].setPadding(10, 10, 10, 10);
                la_babyFlag[nRooms].setLayoutParams(p5);
                la_babyFlag[nRooms].setTextColor(Color.BLACK);
                subLayout.addView(la_babyFlag[nRooms]);

        ComReservRegisActivity.ReservCommonHintsWithoutBackground(getApplicationContext(), layout, LV_BABYBAD);

    }

    private void Fld_CheckinTime28(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 20);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

        Cmm_BlueBar(getApplicationContext(), layout);

        Cmm_FieldLevel(getApplicationContext(), layout,FLD_CHECKIN_TIME, 0);

        Cmm_MustItem2(getApplicationContext(), layout);

        Cmm_WidthSpace(getApplicationContext(), layout);

        la_checkInTime[nRooms] = new Spinner(this);
        layout.addView(la_checkInTime[nRooms]);
    }

    private void TTL_DynamicPriceLayoutCreation() {
        LinearLayout TopLayout = new LinearLayout(this);
        LinearLayout.LayoutParams l_total = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        l_total.setMargins(0, 15, 0, 30);
        TopLayout.setOrientation(LinearLayout.VERTICAL);
        TopLayout.setBackgroundResource(R.drawable.util_gra_yellow_lite);
        TopLayout.setLayoutParams(l_total);
        TopLayout.setPadding(10, 15, 10, 15);
        TopLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(TopLayout);

            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            p1.setMargins(0, 0, 0, -3);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(p1);
            layout1.setPadding(10, 10, 10, 10);
            layout1.setGravity(Gravity.CENTER_VERTICAL);
            TopLayout.addView(layout1);

                Cmm_BlueBar(getApplicationContext(), layout1);

                Cmm_FieldLevel(getApplicationContext(), layout1,FLD_PAYMENT_AMOUNT, 1);

        la_totalPrice = new TextView(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        la_totalPrice.setTextSize(17);
        la_totalPrice.setTextColor(Color.parseColor("#a70505"));
        la_totalPrice.setPadding(10, 10, 10, 10);
        la_totalPrice.setLayoutParams(p4);
        TopLayout.addView(la_totalPrice);
    }

    //Gender
    private AdapterView.OnItemSelectedListener Spinner1_Gender = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            for (int i = 0; i < iData(NUMBER_OF_ROOM); i++) {
                KeyValuePair item = (KeyValuePair) la_gender[i].getSelectedItem();
                st_gender_code[i] = item.getKey().toString();
                st_gender_value[i] = item.getValue().toString();
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };


    //Country
    private AdapterView.OnItemSelectedListener Spinner2_Country = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            for (int i = 0; i < iData(NUMBER_OF_ROOM); i++) {
                KeyValuePair item = (KeyValuePair) la_country[i].getSelectedItem();
                st_country_code[i] = item.getKey().toString();
                st_country_value[i] = item.getValue().toString();
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //CheckinTime
    private AdapterView.OnItemSelectedListener Spinner3_CheckinTime = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            for (int i = 0; i < Integer.valueOf(NUMBER_OF_ROOM); i++) {
                KeyValuePair item = (KeyValuePair) la_checkInTime[i].getSelectedItem();
                st_checkinTime[i] = item.getKey().toString();
                if(i==0){
                    ROOM1_CHECK_IN_TIME = item.getKey().toString();
                }
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };



    private void SetupToView() {
        for (int i = 0; i < iData(NUMBER_OF_ROOM); i++) {
            final int finalI = i;
            addCross(getApplicationContext(), la_familyName[i]);
            addCross(getApplicationContext(), la_firstName[i]);
            addCross(getApplicationContext(), la_membershipNumber[i]);
            addCross(getApplicationContext(), la_phoneNumber[i]);
            addCross(getApplicationContext(), la_firstName[i]);

            //Family Name
            if (st_familyName[i]!=null) {
                la_familyName[i].setText(st_familyName[i]);
            }else{
                st_familyName[i] = la_familyName[i].getText().toString();
            }

            //First Name
            if (st_firstName[i]!=null) {
                la_firstName[i].setText(st_firstName[i]);
            }else{
                st_firstName[i] = la_firstName[i].getText().toString();
            }

            //Membership Flag
            if (st_membershipFlag[i]!=null) {
                if (st_membershipFlag[i].equalsIgnoreCase(COD_Y)) {
                    st_membershipFlag[i] = COD_Y;
                    la_membershipFlag[i].setText(SW_YES_E);
                    la_membershipFlag[i].setChecked(true);
                } else {
                    st_membershipFlag[i] = COD_N;
                    la_membershipFlag[i].setText(SW_NO_E);
                    la_membershipFlag[i].setChecked(false);
                }
            }else{
                st_membershipFlag[i] = COD_N;
            }

            //Set Gender
            KeyValuePairArrayAdapter adapter_gender = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
            adapter_gender =  ComLib.getGenderKeyValueToAdapter(adapter_gender);
            la_gender[i].setAdapter(adapter_gender);
            la_gender[i].setPrompt("性別");
            la_gender[i].setPrompt(SW_GENDER);
            la_gender[i].setSelection(adapter_gender.getPosition(st_gender_code[i]));
            la_gender[i].setOnItemSelectedListener(Spinner1_Gender);


            //Set Country
            KeyValuePairArrayAdapter adapter_country = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
            adapter_country =  ComLib.getCountryKeyValueToAdapter(adapter_country);
            la_country[i].setAdapter(adapter_country);
            la_country[i].setPrompt("国籍");
            la_country[i].setPrompt(SW_COUNTRY);
            la_country[i].setSelection(adapter_country.getPosition(st_country_code[i]));
            la_country[i].setOnItemSelectedListener(Spinner2_Country);

            //Set CheckinTime
            LoadCheckinTime(i);

            la_membershipFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_membershipFlag[finalI] = COD_Y;
                    } else {
                        st_membershipFlag[finalI] = COD_N;
                    }
                    LoadCheckinTime(finalI);
                    checkStart();
                }
            });

            //Phone Number
            if (st_phoneNumber[i]!=null) {
                la_phoneNumber[i].setText(st_phoneNumber[i]);
            }else{
                st_phoneNumber[i] =  la_phoneNumber[i].getText().toString();
            }
            //Membership Number
            if (st_membershipNumber[i]!=null) {
                la_membershipNumber[i].setText(st_membershipNumber[i]);
            }else{
                if(!la_membershipNumber[i].getText().toString().isEmpty()) {
                    st_membershipNumber[i] = la_membershipNumber[i].getText().toString();
                }else{
                    st_membershipNumber[i] =ST_NULL;
                }
            }


            if(iData(NUMBER_OF_NIGHT)>1) {
                //Eco Flag
                if (st_ecoFlag[i] != null) {
                    if (st_ecoFlag[i].equalsIgnoreCase(COD_Y)) {
                        st_ecoFlag[i] = COD_Y;
                        la_ecoFlag[i].setText(SW_YES_E);
                        la_ecoFlag[i].setChecked(true);
                        px_ecoSelectionLayout[i].setVisibility(View.VISIBLE);
                    } else {
                        px_ecoSelectionLayout[i].setVisibility(View.GONE);
                        st_ecoFlag[i] = COD_N;
                        la_ecoFlag[i].setText(SW_YES_E);
                        la_ecoFlag[i].setChecked(false);
                    }
                } else {
                    st_ecoFlag[i] = COD_N;
                }

                la_ecoFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            st_ecoFlag[finalI] = COD_Y;
                            px_ecoSelectionLayout[finalI].setVisibility(View.VISIBLE);
                        } else {
                            for (int j = 0; j < 7; j++) {
                                if(finalI==0) {
                                    st_room1EcoDateArray[j] = "";
                                }
                                if(finalI==1) {
                                    st_room2EcoDateArray[j] = "";
                                }
                                if(finalI==2) {
                                    st_room3EcoDateArray[j] = "";
                                }
                                if(finalI==3) {
                                    st_room4EcoDateArray[j] = "";
                                }
                                la_dateCheckbox[finalI][j].setChecked(false);
                            }

                            st_ecoFlag[finalI] = COD_N;
                            px_ecoSelectionLayout[finalI].setVisibility(View.GONE);
                            checkStart();
                        }
                    }
                });
            }else{
                st_ecoFlag[i] = COD_N;
            }

            //Smoking Flag
            if (SMOKING_FLAG.equalsIgnoreCase(COD_Y)) {
                la_smokingIcon[i].setBackgroundResource(R.drawable.ic_g12_p15_smoking_yes);
            }else{
                la_smokingIcon[i].setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
            }

            //Room Change
            la_updateRoom[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goTo(G12P16A09ChooseRoomList.class, COD_BACK);
                }
            });

            if(ROOM_TYPE_NAME!=null) {
                la_roomTypeName[i].setText(ROOM_TYPE_NAME);
            }

            st_businessFlag[i] = COD_N;
            //Business Plan
            if(!st_businessFlag[i].isEmpty()) {
                if (st_businessFlag[i].equalsIgnoreCase(COD_Y)) {
                    st_businessFlag[i] = COD_Y;
                    px_business_pack_layout[i].setVisibility(View.VISIBLE);
                } else {
                    for (int j = 0; j < 3; j++) {
                       la_businessPack[i][j].setChecked(false);
                    }
                    px_business_pack_layout[i].setVisibility(View.GONE);
                }
            }else{
                st_businessFlag[i] = COD_N;
            }

            la_businessFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_businessFlag[finalI] = COD_Y;
                        la_businessPack[finalI][0].setChecked(true);
                        st_businessSelected[finalI] = String.valueOf(ST_ONE);
                        px_business_pack_layout[finalI].setVisibility(View.VISIBLE);
                    } else {
                        for (int j = 0; j < 3; j++) {
                            la_businessPack[finalI][j].setChecked(false);
                        }
                        st_businessFlag[finalI] = COD_N;
                        st_businessSelected[finalI]=ST_NULL;
                        px_business_pack_layout[finalI].setVisibility(View.GONE);
                        checkStart();
                    }
                }
            });

            st_vodFlag[i] = COD_N;
            //Vod Flag
            if(st_vodFlag[i]!=null) {
                if (st_vodFlag[i].equalsIgnoreCase(COD_Y)) {
                    st_vodFlag[i] = COD_Y;
                } else {
                    st_vodFlag[i] = COD_N;
                }
            }else{
                st_vodFlag[i] = COD_N;
            }

            la_vodFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_vodFlag[finalI] = COD_Y;
                    }else{
                        st_vodFlag[finalI] = COD_N;
                    }
                    checkStart();
                }
            });

            st_babyFlag[i] = COD_N;
            //Baby Flag
            if(st_babyFlag[i]!=null) {
                if (st_babyFlag[i].equalsIgnoreCase(COD_Y)) {
                    st_babyFlag[i] = COD_Y;
                } else {
                    st_babyFlag[i] = COD_N;
                }
            }else{
                st_babyFlag[i] = COD_N;
            }

            la_babyFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_babyFlag[finalI] = COD_Y;
                    }else{
                        st_babyFlag[finalI] = COD_N;
                    }
                    checkStart();
                }
            });
        }

        SetupToViewPriceList();
    }

    private void LoadCheckinTime(int i) {
        KeyValuePairArrayAdapter adapterCheckinTime = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        if(st_membershipFlag[i].equalsIgnoreCase(COD_N)){
            adapterCheckinTime.clear();
            adapterCheckinTime =  getTimeKeyValueNonMember(adapterCheckinTime);
            st_checkinTime[i] = "160000";
            ROOM1_CHECK_IN_TIME = "160000";
        }else{
            adapterCheckinTime.clear();
            adapterCheckinTime =  getTimeKeyValueMember(adapterCheckinTime);
            st_checkinTime[i] = "150000";
            ROOM1_CHECK_IN_TIME = "150000";
        }
        la_checkInTime[i].setAdapter(adapterCheckinTime);
        la_checkInTime[i].setPrompt("チェックイン時間");
        la_checkInTime[i].setSelection(adapterCheckinTime.getPosition(st_checkinTime[i]));
        la_checkInTime[i].setOnItemSelectedListener(Spinner3_CheckinTime);
    }

    private void checkStart() {
        submitFlag =false;
        MODE = ST_TWO;
        SetupToVariable();
        SetupToJson();
    }

    private void checkEndAndSubmit() {
        MODE = ST_ONE;
        submitFlag=true;
        SetupToVariable();
        SetupToJson();
    }


    private void SetupToViewPriceList() {
        StringBuilder sb_tp = new StringBuilder();
     //   sb_tp.append(FLD_PAYMENT_AMOUNT);
        sb_tp.append(TOTAL_PRICE);
        sb_tp.append(SIN_START_BRECKET_TAX);
        sb_tp.append(TOTAL_PRICE_TAX);
          sb_tp.append(SIN_CLOSE_BRECKET);
        Toast.makeText(this, sb_tp, Toast.LENGTH_LONG).show();
        la_totalPrice.setText(sb_tp);
    }

    private void field_MembershipFlag(final int x) {
        if (la_membershipNumber[x].getText().toString().isEmpty()) {
            la_membershipNumber[x].setBackgroundResource(R.drawable.util_textview_bk_red);
            la_membershipNumber[x].setFocusableInTouchMode(true);
            la_membershipNumber[x].requestFocus();
            validationFlag = false;
        }
        la_membershipNumber[x].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    la_membershipNumber[x].setBackgroundResource(R.drawable.util_textview_bk_pink);
                    validationFlag = false;
                }
            }
        });
    }

    private void field_PhoneNumber(final int x) {
        String phoneNum = la_phoneNumber[x].getText().toString();
        int min_phone_num = la_phoneNumber[x].getText().length();
        if (phoneNum.isEmpty() || min_phone_num <6) {
            la_phoneNumber[x].setBackgroundResource(R.drawable.util_textview_bk_red);
            la_phoneNumber[x].setFocusableInTouchMode(true);
            la_phoneNumber[x].requestFocus();
            validationFlag = false;
        }

        la_phoneNumber[x].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    la_phoneNumber[x].setBackgroundResource(R.drawable.util_textview_bk_pink);
                    validationFlag = false;
                }
            }
        });
    }

    private void field_firstName(final int x) {
        if (la_firstName[x].getText().toString().isEmpty()) {
            la_firstName[x].setBackgroundResource(R.drawable.util_textview_bk_red);
            la_firstName[x].setFocusableInTouchMode(true);
            la_firstName[x].requestFocus();
            validationFlag = false;
        }
        la_firstName[x].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    la_firstName[x].setBackgroundResource(R.drawable.util_textview_bk_pink);
                    validationFlag = false;
                }
            }
        });
    }

    private void field_familyName(final int x) {
        if (la_familyName[x].getText().toString().isEmpty()) {
            la_familyName[x].setBackgroundResource(R.drawable.util_textview_bk_red);
            la_familyName[x].setFocusableInTouchMode(true);
            la_familyName[x].requestFocus();
            validationFlag = false;
        }
        la_familyName[x].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    la_familyName[x].setBackgroundResource(R.drawable.util_textview_bk_pink);
                    validationFlag = false;
                }
            }
        });
    }

    private boolean SetupValidation() {
        validationFlag = true;
        if (MODE.equalsIgnoreCase(ST_ONE)) {
            int start = iData(NUMBER_OF_ROOM) - 1;
            for (int k = start; k >= 0; k--) {
                field_PhoneNumber(k);
                field_firstName(k);
                field_familyName(k);
            }
        }
        return validationFlag;
    }

    private void SetupToVariable() {
        for (int x = 0; x < iData(NUMBER_OF_ROOM); x++) {
            if(x ==0){
                LS_ECO_DATA_ROOM1 = getEcoDateToRemoveNullData(st_room1EcoDateArray);
                if(LS_ECO_DATA_ROOM1.size()==0){
                    st_ecoFlag[x] = COD_N;
                }
            }
            if(x ==1) {
                LS_ECO_DATA_ROOM2 = getEcoDateToRemoveNullData(st_room2EcoDateArray);
            }
            if(x ==2) {
                LS_ECO_DATA_ROOM3 = getEcoDateToRemoveNullData(st_room3EcoDateArray);
            }
            if(x ==3) {
                LS_ECO_DATA_ROOM4 = getEcoDateToRemoveNullData(st_room4EcoDateArray);
            }


            st_familyName[x] = la_familyName[x].getText().toString();
            st_firstName[x] = la_firstName[x].getText().toString();
            if (la_membershipFlag[x].isChecked()) {
                st_membershipFlag[x] = COD_Y;
            } else {
                st_membershipFlag[x] = COD_N;
            }
            st_membershipNumber[x] = la_membershipNumber[x].getText().toString();

            st_phoneNumber[x] = la_phoneNumber[x].getText().toString();
            if (la_businessFlag[x].isChecked()) {
                st_businessFlag[x] = COD_Y;
            } else {
                st_businessFlag[x] = COD_N;
                for (int j = 0; j < 3; j++) {
                    la_businessPack[x][j].setChecked(false);
                }
            }

            if(iData(NUMBER_OF_NIGHT)>1) {
                if (la_ecoFlag[x].isChecked()) {
                    st_ecoFlag[x] = COD_Y;
                } else {
                    st_ecoFlag[x] = COD_N;
                }
            }else{
                st_ecoFlag[x] = COD_N;
            }

            if (la_vodFlag[x].isChecked()) {
                st_vodFlag[x] = COD_Y;
            } else {
                st_vodFlag[x] = COD_N;
            }

            LS_CHECK_IN_DATE.add(x, CHECK_IN_DATE);
            LS_CHECK_OUT_DATE.add(x, CHECK_OUT_DATE);
            LS_ROOM_TYPE_NAME.add(x, ROOM_TYPE_NAME);
            LS_NUMBER_OF_PEOPLE.add(x, String.valueOf(st_personCounter[x]));
            LS_FAMILY_NAME.add(x, st_familyName[x]);
            LS_FRIST_NAME.add(x, st_firstName[x]);
            LS_MEMBERSHIP_FLAG.add(x, st_membershipFlag[x]);
            LS_MEMBERSHIP_NUMBER.add(x, st_membershipNumber[x]);
            LS_SEX.add(x, st_gender_code[x]);
            LS_COUNTRY_CODE.add(x, st_country_code[x]);
            LS_COUNTRY_VALUE.add(x, st_country_value[x]);
            LS_PHONE_NUMBER.add(x, st_phoneNumber[x]);
            LS_BUSINESS_FLAG.add(x, st_businessFlag[x]);
            LS_BUSINESS_PACK_SELECTED.add(x, st_businessSelected[x]);
            LS_ECO_FLAG.add(x , st_ecoFlag[x]);
            LS_BABY_FLAG.add(x, st_babyFlag[x]);
            LS_VOD_FLAG.add(x, st_vodFlag[x]);
            LS_CHECKIN_TIME.add(x, st_checkinTime[x]);
        }
    }

    private void SetupToParcel() {
        if (MODE.equalsIgnoreCase(ST_ONE)) {
            obj_g01.setLsCheckInDate(LS_CHECK_IN_DATE);
            obj_g01.setLsCheckOutDate(LS_CHECK_OUT_DATE);
            obj_g01.setLsRoomTypeName(LS_ROOM_TYPE_NAME);
            obj_g01.setRdNumberOfMaxPeople(RD_NUMBER_OF_MAX_PEOPLE);
            obj_g01.setLsNumberPeople(LS_NUMBER_OF_PEOPLE);
            obj_g01.setLsFamilyName(LS_FAMILY_NAME);
            obj_g01.setLsFirstName(LS_FRIST_NAME);
            obj_g01.setLsMembershipFlag(LS_MEMBERSHIP_FLAG);
            obj_g01.setLsMemberNumber(LS_MEMBERSHIP_NUMBER);
            obj_g01.setLsCountryCode(LS_COUNTRY_CODE);
            obj_g01.setLsCountryValue(LS_COUNTRY_VALUE);
            obj_g01.setLsSex(LS_SEX);
            obj_g01.setLsPhoneNumber(LS_PHONE_NUMBER);
            obj_g01.setLsBusinessFlag(LS_BUSINESS_FLAG);
            obj_g01.setLsBusinessData(LS_BUSINESS_PACK_SELECTED);
            obj_g01.setLsEcoFlag(LS_ECO_FLAG);
            obj_g01.setLsEcoDateSelection(LS_ECO_DATE_SELECTION);
            obj_g01.setLsBabyFlag(LS_BABY_FLAG);
            obj_g01.setLsVodFlag(LS_VOD_FLAG);
            obj_g01.setLsCheckinTime(LS_CHECKIN_TIME);
            obj_g01.setRoom1CheckinTime(ROOM1_CHECK_IN_TIME);
            obj_g01.setRdTotalPrice(TOTAL_PRICE);
            obj_g01.setRdTotalPriceTax(TOTAL_PRICE_TAX);
            obj_g01.setLsEcoDataRoom1(LS_ECO_DATA_ROOM1);
            obj_g01.setLsEcoDataRoom2(LS_ECO_DATA_ROOM2);
            obj_g01.setLsEcoDataRoom3(LS_ECO_DATA_ROOM3);
            obj_g01.setLsEcoDataRoom4(LS_ECO_DATA_ROOM4);
        }
    }

    private void GoToConfirmPage() {

        Button button = (Button) findViewById(R.id.g14p18_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MODE = ST_ONE;
                if (SetupValidation()) {
                    checkEndAndSubmit();
                } else {
                    displayErrorMessage();
                }
            }
        });
    }

    private void displayErrorMessage() {
            int maxRoom = iData(NUMBER_OF_ROOM);
            for (int room = 0; room <maxRoom; room++) {
                    int cRoom = room +1;
                if(la_familyName[room].getText().toString().isEmpty()){
                    errorPopup(null,getErrorMessage(cRoom, FLD_FAMILY_NAME2));
                    break;
                }else if(la_firstName[room].getText().toString().isEmpty()){
                    errorPopup(null,getErrorMessage(cRoom, FLD_FIRST_NAME2));
                    break;
                } else if(la_phoneNumber[room].getText().toString().isEmpty()){
                    errorPopup(null,getErrorMessage(cRoom, FLD_PHONE_NUM));
                    break;
                } else if(la_phoneNumber[room].getText().length() < 6){
                    errorPopup(null,getErrorMessagePhone(cRoom, FLD_PHONE_NUM));
                    break;
                }
            }
    }

    private String getErrorMessage(int room,String fieldName) {
       StringBuilder sb = new StringBuilder();
        sb.append(FLD_ROOM1);
        sb.append(room);
        sb.append("の");
        sb.append(fieldName);
        sb.append(ERR_EMPTY_FIELD);
        return sb.toString();
    }

    private String getErrorMessagePhone(int room,String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append(FLD_ROOM1);
        sb.append(room);
        sb.append("の");
        sb.append(fieldName);
        sb.append(ERR_CHECK);
        return sb.toString();
    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g14p18_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAGE_FLAG.equalsIgnoreCase("G06_G10_G13_G50")) {
                    obj_g01.setPageFlag("G06_G10_G13");
                    goTo(G13P17A10RoomDetailsSetting.class, COD_BACK);
                }  else if (PAGE_FLAG.equalsIgnoreCase("G01_G10_G13_G50")) {
                    obj_g01.setPageFlag("G01_G10_G13");
                    goTo(G13P17A10RoomDetailsSetting.class, COD_BACK);
                }else if (PAGE_FLAG.equalsIgnoreCase("G01_G10_G13")) {
                    goTo(G13P17A10RoomDetailsSetting.class, COD_BACK);
                }else if (PAGE_FLAG.equalsIgnoreCase("G06_G10_G13")) {
                    goTo(G13P17A10RoomDetailsSetting.class, COD_BACK);
                }else {
                    finish(COD_BACK);
                }
            }
        });
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private String errorCode = new String();
        private String errorMessage = new String();
        private APIs api = new APIs();

        public JSONParse() {
            super();
            MainDataForBeforeSubmitJSONA012();
            setApiRequestDataA012(iData(NUMBER_OF_ROOM));
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

            int max = iData(NUMBER_OF_ROOM);
            for (int i = 0; i < max; i++) {
                rsrvtnNmbr.add("");
                checkinDate.add(CHECK_IN_DATE);
                checkOutDate.add(CHECK_OUT_DATE);
                roomTypeCode.add(ROOM_TYPE_CODE);
                planCode.add(PLAN_CODE);
                ecoDtsList.add("");
                ecoChckn.add("N");
                bsnssPackType.add("");
            }
            map.put(CT_RSRVTNNMBR, rsrvtnNmbr);
            map.put(CT_CHCKNDATE, checkinDate);
            map.put(CT_CHCKTDATE, checkOutDate);
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
         //   map.put(CT_CHCKNTIME, LS_CHECKIN_TIME);

            dataArray.add(map);
        }

        private void setApiRequestDataA012(int maxvalue) {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setMood(MODE);
            api.setHotelCode(HOTEL_CODE);
            api.setRoom1_chcknTime(ROOM1_CHECK_IN_TIME);
            api.setEcoDtsList1(LS_ECO_DATA_ROOM1);
            api.setEcoDtsList2(LS_ECO_DATA_ROOM2);
            api.setEcoDtsList3(LS_ECO_DATA_ROOM3);
            api.setEcoDtsList4(LS_ECO_DATA_ROOM4);
            Log.e("PARAM-G14P18-3x", api.getRequestDataA012(maxvalue, dataArray).toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G14P18A12ReservRegistrationStep41Entry.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA012(iData(NUMBER_OF_ROOM), dataArray));
            JSONObject json = jParser.getJSONData(api.getURLA012());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G14P18-3x", json.toString());
            if (!isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = json.optString(CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }

            // I have to change
            TOTAL_PRICE = json.optString(CT_TTLPRC);
            TOTAL_PRICE_TAX = json.optString(CT_TTLPRCINCLDNGTAX);

            optnPrc = ComLib.getMultipleRoomsData(json, CT_OPTNPRC, iData(NUMBER_OF_ROOM));
            sbttlPrc = ComLib.getMultipleRoomsData(json, CT_SBTTLPRC, iData(NUMBER_OF_ROOM));
            sbttlPrcIncldngTax = ComLib.getMultipleRoomsData(json, CT_SBTTLPRCINCLDNGTAX, iData(NUMBER_OF_ROOM));
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
            SetupToViewPriceList();
            if (submitFlag) {
                SetupToParcel();
                goTo(G15P19A12ReservRegistrationStep42Confirm.class, COD_NEXT);
            }
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BAPI1005")){
                errorMessage = ERR_ECO_SElECT_THREEDAY;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void ButtonONOFF(Button addition, Button deduction,int counter,int max,int min) {
        if(counter<=min) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_off);
        }

        if(counter>min && counter<max) {
            addition.setEnabled(true);
            deduction.setEnabled(true);
            addition.setBackgroundResource(R.drawable.util_on);
            deduction.setBackgroundResource(R.drawable.util_on);
        }

        if(counter >=max){
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_off);
        }
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
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }
}