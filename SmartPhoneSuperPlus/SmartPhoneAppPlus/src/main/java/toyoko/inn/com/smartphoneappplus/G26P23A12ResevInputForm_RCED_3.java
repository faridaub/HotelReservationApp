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
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.*;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_ONE;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_TWO;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComReservRegisActivity.*;


public class G26P23A12ResevInputForm_RCED_3 extends Activity {


    final ArrayList<String> room2EcoDateArray = new ArrayList<String>();
    final ArrayList<String> room3EcoDateArray = new ArrayList<String>();
    final ArrayList<String> room4EcoDateArray = new ArrayList<String>();
    //Param Data For API

    private ArrayList<String> ecoDataRoom1 = new ArrayList<String>();
    private ArrayList<String> ecoDataRoom2 = new ArrayList<String>();
    private ArrayList<String> ecoDataRoom3 = new ArrayList<String>();
    private ArrayList<String> ecoDataRoom4 = new ArrayList<String>();

    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String HOTEL_CODE;
    private String ROOM_TYPE_CODE;
    private String ROOM_TYPE_NAME;
    private String NUMBER_OF_NIGHT;
    private String RD_NUMBER_OF_MAX_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String PARAM_ECOFLAG;
    private String PARAM_ECODTSLIST;
    private String VOD_FLAG;
    private String BUSINESS_FLAG;
    private String PARAM_BSNSSPACKTYPE;
    private String MODE; // If mood = 2 then without must item price will display

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
    private TextView la_checkInDate;
    private TextView la_numStay;
    private TextView la_gustRoom;
    private TextView la_memberPrice;
    private TextView la_normalPrice;
    private TextView la_equipment_list;
    private LinearLayout la_member_pricing;
    private LinearLayout la_normal_pricing;
    private TextView la_member_price_title;
    private TextView la_normal_price_title;
    private LinearLayout la_room_type_two;
    private LinearLayout la_room_type_one;
    private G01P01ParcelableData obj_g01;
    private String TOTAL_PRICE_TAX;
    private String TOTAL_PRICE;
    private TextView la_total_price_calc;
    private TextView la_checkOutDate;

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
    private ArrayList<String> LS_COUNTRY;
    private ArrayList<String> LS_SEX;
    private ArrayList<String> LS_PHONE_NUMBER;
    private ArrayList<String> LS_BUSINESS_FLAG;
    private ArrayList<String> LS_BUSINESS_FLAG_DATA;
    private ArrayList<String> LS_ECO_FLAG;
    private ArrayList<String> LS_ECO_DATA_ROOM1;
    private ArrayList<String> LS_ECO_DATA_ROOM2;
    private ArrayList<String> LS_ECO_DATA_ROOM3;
    private ArrayList<String> LS_ECO_DATA_ROOM4;
    private ArrayList<String> LS_BABY_FLAG;
    private ArrayList<String> LS_VOD_FLAG;
    private ArrayList<String> LS_CHECKIN_TIME;


    private String[] genderRoom;
    private String[] lastName;
    private String[] country;
    private String[] telNum;
    private LinearLayout parent;


    //Lavel

    private LinearLayout[] px_business_pack_layout;
    private LinearLayout[] px_ecoSelectionLayout;

    private TextView[] la_roomTypeName;
    /* private Button[] la_updateRoom;*/
    private TextView[] la_smokingIcon;
    private EditText[] la_familyName;
    private EditText[] la_firstName;
    private EditText[] la_membershipNumber;
    private EditText[] la_phoneNumber;
    private RadioButton[] la_male;
    private RadioButton[] la_fmale;

    private Spinner[] la_gender;
    private Spinner[] la_country;
    private ToggleButton[] la_membershipFlag;
    private ToggleButton[] la_ecoFlag;
    private ToggleButton[] la_businessFlag;
    private ToggleButton[] la_vodFlag;
    private ToggleButton[] la_babyFlag;
    private Spinner[] la_checkInTime;
    private RadioButton[] la_businessPack;


    private String[] st_familyName;
    private String[] st_firstName;
    private String[] st_membershipFlag;
    private String[] st_membershipNumber;
    private String[] st_gender_code;
    private String[] st_country_code;
    private String[] st_phoneNumber;
    private String[] st_ecoFlag;
    private String[] st_ecoSelection;
    private String[] st_vodFlag;
    private String[] st_businessFlag;
    private String[] st_businessFlagData;
    private String[] st_babyFlag;

    String[] st_room1EcoDateArray;
    String[] st_room2EcoDateArray;
    String[] st_room3EcoDateArray;
    String[] st_room4EcoDateArray;


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
    private String COUNTRY;
    private String PHONE_NUMBER;
    private String ECO_FLAG;
    private String BUSINESS_FLAG_DATA;
    private String BABY_FLAG;
    private String HOTEL_NAME;
    private String CHECK_IN_TIME;

    int ecoPositionRoom1;
    int ecoPositionRoom2;
    int ecoPositionRoom3;
    int ecoPositionRoom4;
    private String CUSTRSRVTNNMBR;
    private String ROOM1_CHECK_IN_TIME;

    int ecoWidth;
    int echHight;
    private String screenSize;
    private String RD_PLAN_CODE;
    private String RD_PLAN_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGEeee", "------------------------------------G26P23A12ResevInputForm_RCED_3------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g26_p23_reserv_confirm_edit_delete_rced_3);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G13P17
        GetData();

        //Initialize Variable
        InitializeVariable();

        //Print Data which We Receive
        CreateLayoutProgrammatically();

        //Set Total Price Frist Time
        SetupToViewPriceList();

        //Load Data If Exists
        SetupToView();

        //Back To G13P17
        BackTo();

        //Back To G13P17
        GoToConfirmPage();

    }

    private void InitializeVariable() {
        int numberOfRooms = Integer.valueOf(NUMBER_OF_ROOM);
        this.px_business_pack_layout = new LinearLayout[numberOfRooms];
        this.la_roomTypeName = new TextView[numberOfRooms];
       /* this.la_updateRoom = new Button[numberOfRooms];*/
        this.la_smokingIcon = new TextView[numberOfRooms];

        this.la_familyName = new EditText[numberOfRooms];
        this.la_firstName = new EditText[numberOfRooms];

        this.la_membershipFlag = new ToggleButton[numberOfRooms];
        this.la_male = new RadioButton[numberOfRooms];
        this.la_fmale = new RadioButton[numberOfRooms];
        this.la_membershipNumber = new EditText[numberOfRooms];
        this.la_phoneNumber = new EditText[numberOfRooms];


        this.la_gender = new Spinner[numberOfRooms];
        this.la_country = new Spinner[numberOfRooms];

        this.la_ecoFlag = new ToggleButton[numberOfRooms];
        this.px_ecoSelectionLayout = new LinearLayout[numberOfRooms];
        this.la_businessFlag = new ToggleButton[numberOfRooms];
        this.la_vodFlag = new ToggleButton[numberOfRooms];
        this.la_babyFlag = new ToggleButton[numberOfRooms];
        this.la_checkInTime = new Spinner[numberOfRooms];
        this.la_businessPack = new RadioButton[3];

        this.st_familyName = new String[numberOfRooms];
        this.st_firstName = new String[numberOfRooms];
        this.st_membershipFlag = new String[numberOfRooms];
        this.st_membershipNumber = new String[numberOfRooms];
        this.st_gender_code = new String[numberOfRooms];
        this.st_country_code = new String[numberOfRooms];
        this.st_phoneNumber = new String[numberOfRooms];
        this.st_ecoFlag = new String[numberOfRooms];
        this.st_ecoSelection = new String[numberOfRooms];
        this.st_vodFlag = new String[numberOfRooms];
        this.st_businessFlag = new String[numberOfRooms];
        this.st_businessFlagData = new String[numberOfRooms];
        this.st_babyFlag = new String[numberOfRooms];
        this.st_checkinTime = new String[numberOfRooms];
        this.st_personCounter = new int[numberOfRooms];

        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");
        room2EcoDateArray.add("-");


        for (int i = 0; i < Integer.valueOf(NUMBER_OF_ROOM); i++) {
            final int finalI = i;
            if (i == 0) {
                st_familyName[i] = FAMILY_NAME;
                st_firstName[i] = FIRST_NAME;
                st_membershipFlag[i] = MEMBERSHIP_FLAG;
                st_membershipNumber[i] = "";
                st_gender_code[i] = SEX;
                st_country_code[i] = COUNTRY;
                st_phoneNumber[i] = PHONE_NUMBER;
                st_ecoFlag[i] = ECO_FLAG;
                st_ecoSelection[i] = "";
                st_vodFlag[i] = VOD_FLAG;
                st_businessFlag[i] = BUSINESS_FLAG;
                st_businessFlagData[i] = BUSINESS_FLAG_DATA;
                st_babyFlag[i] = BABY_FLAG;
                st_checkinTime[i] = CHECK_IN_TIME;
            } else {
                st_familyName[i] = "";
                st_firstName[i] = "";
                st_membershipFlag[i] = "N";
                st_membershipNumber[i] = "";
                st_gender_code[i] = SEX;
                st_country_code[i] = "JPN";
                st_phoneNumber[i] = "";
                st_ecoFlag[i] = "N";
                st_ecoSelection[i] = "";
                st_vodFlag[i] = "N";
                st_businessFlag[i] = "N";
                st_businessFlagData[i] = "";
                st_babyFlag[i] = "N";
                st_checkinTime[i] = "200000";
            }
        }
    }

    private void InitialSetupConfiguration() {
        parent = (LinearLayout) findViewById(R.id.g26_p23_group2_roomtype_1);

        //Param Data Internal Constant
        this.ecoPositionRoom1 = NM_ZERO;
        this.ecoPositionRoom2 = NM_ZERO;
        this.ecoPositionRoom3 = NM_ZERO;
        this.ecoPositionRoom4 = NM_ZERO;

        st_room1EcoDateArray = new String[7];
        st_room2EcoDateArray = new String[7];
        st_room3EcoDateArray = new String[7];
        st_room4EcoDateArray = new String[7];


        this.CHECK_IN_DATE = new String();
        this.HOTEL_CODE = new String();
        this.ROOM_TYPE_CODE = new String();
        this.ROOM_TYPE_NAME = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.RD_NUMBER_OF_MAX_PEOPLE = new String();
        this.NUMBER_OF_MAX_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.PARAM_ECOFLAG = new String();
        this.PARAM_ECODTSLIST = new String();
        this.VOD_FLAG = new String();
        this.BUSINESS_FLAG = new String();
        this.PARAM_BSNSSPACKTYPE = new String();
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
        this.COUNTRY = new String();
        this.PHONE_NUMBER = new String();
        this.ECO_FLAG = new String();
        this.BUSINESS_FLAG_DATA = new String();
        this.BABY_FLAG = new String();
        this.HOTEL_NAME = new String();
        this.CHECK_IN_TIME = new String();
        this.RD_PLAN_CODE = new String();
        this.RD_PLAN_NAME = new String();
        this.CUSTRSRVTNNMBR = new String();
        this.ROOM1_CHECK_IN_TIME = new String();

        //List Array
        //------------------------------------------------------------------------------------------
        this.LS_CHECK_IN_DATE = new ArrayList<String>();
        this.LS_CHECK_OUT_DATE = new ArrayList<String>();
        this.LS_ROOM_TYPE_NAME = new ArrayList<String>();
        this.LS_FRIST_NAME = new ArrayList<String>();
        this.LS_FAMILY_NAME = new ArrayList<String>();
        this.LS_MEMBERSHIP_FLAG = new ArrayList<String>();
        this.LS_MEMBERSHIP_NUMBER = new ArrayList<String>();
        this.LS_SEX = new ArrayList<String>();
        this.LS_PHONE_NUMBER = new ArrayList<String>();
        this.LS_COUNTRY = new ArrayList<String>();
        this.LS_ECO_FLAG = new ArrayList<String>();
        this.LS_VOD_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_FLAG = new ArrayList<String>();
        this.LS_BUSINESS_FLAG_DATA = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM1 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM2 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM3 = new ArrayList<String>();
        this.LS_ECO_DATA_ROOM4 = new ArrayList<String>();
        this.LS_BABY_FLAG = new ArrayList<String>();
        this.LS_CHECKIN_TIME = new ArrayList<String>();
        this.LS_NUMBER_OF_PEOPLE = new ArrayList<String>();

        //Json Array
        this.dlyPrcInfrmtnArrayList = new ArrayList<HashMap<String, String>>();
        this.TERMS_AND_CONDITION = new String();
        this.EQUIPMENT_LIST = new ArrayList<String>();

        this.TTLPRCINCLDNGTAX = new String();
        this.TTLPRC = new String();

        this.EcoDataBigArray = new ArrayList<HashMap<String, ArrayList<String>>>();
        this.optnPrc = new ArrayList<String>();
        this.sbttlPrc = new ArrayList<String>();
        this.sbttlPrcIncldngTax = new ArrayList<String>();

        this.screenSize = new String();
        this.ecoWidth = Integer.valueOf(getScreen(getApplicationContext()).get("ecoWidth"));
        this.echHight= Integer.valueOf(getScreen(getApplicationContext()).get("ecoHeight"));
        this.screenSize = getScreen(getApplicationContext()).get("size");
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

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

            if (!obj_g01.getHotelCodeNew().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getRdRoomTypeCode().isEmpty()) {
                ROOM_TYPE_CODE = obj_g01.getRdRoomTypeCode();
            }

            if (!obj_g01.getRdRoomName().isEmpty()) {
                ROOM_TYPE_NAME = obj_g01.getRdRoomName();
            }

            if (!obj_g01.getRdPlanCode().isEmpty()) {
                RD_PLAN_CODE = obj_g01.getRdPlanCode();
            }

            if (!obj_g01.getRdPlanName().isEmpty()) {
                RD_PLAN_NAME = obj_g01.getRdPlanName();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                RD_NUMBER_OF_MAX_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getRdEcoFlag().isEmpty()) {
                ECO_FLAG = obj_g01.getRdEcoFlag();
            }

            if (!obj_g01.getRdVodFlag().isEmpty()) {
                VOD_FLAG = obj_g01.getRdVodFlag();
            }

            if (!obj_g01.getRdBsnssPackFlag().isEmpty()) {
                BUSINESS_FLAG = obj_g01.getRdBsnssPackFlag();
            }

            if (!obj_g01.getRdBsnssPackData().isEmpty()) {
                BUSINESS_FLAG_DATA = obj_g01.getRdBsnssPackData();
            }

            if (!obj_g01.getRdSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getRdSmokingFlag();
            }

            if (!obj_g01.getRdBabyBad().isEmpty()) {
                BABY_FLAG = obj_g01.getRdBabyBad();
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

            if (!obj_g01.getRdCheckInTime().isEmpty()) {
                CHECK_IN_TIME = obj_g01.getRdCheckInTime();
            }

            //List Data
            //--------------------------------------------------------------------------------------
            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FAMILY_NAME = obj_g01.getCustFmlyName();
            }
            if (!obj_g01.getCustFrstName().isEmpty()) {
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCustRsrvtnNmbr().isEmpty()) {
                CUSTRSRVTNNMBR = obj_g01.getCustRsrvtnNmbr();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustNtnltyCode().isEmpty()) {
                COUNTRY = obj_g01.getCustNtnltyCode();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHONE_NUMBER = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getLsEcoDataRoom1().isEmpty()) {
                LS_ECO_DATA_ROOM1 = obj_g01.getLsEcoDataRoom1();
            }
        }
    }

    private void CreateLayoutProgrammatically() {
        int numberOfRooms = Integer.valueOf(NUMBER_OF_ROOM);

        ReservCommonHeadline(getApplicationContext(), parent, getSimpleString(FLD_RESERVATION_NO, CUSTRSRVTNNMBR));
        ReservCommonHeadline(getApplicationContext(), parent, HOTEL_NAME);
        ReservCommonFields(getApplicationContext(), parent, FLD_CHECKIN, dateConvertFormattedDate(CHECK_IN_DATE));
        ReservCommonFields(getApplicationContext(), parent, FLD_CHECKOUT, dateConvertFormattedDate(CHECK_OUT_DATE));
        for (int nRooms = 0; nRooms < numberOfRooms; nRooms++) {
            int numroom = nRooms + 1;
            String roomtext = SIN_START_3D_BRECKET_TAX + numroom + SIN_CLOSE_3D_BRECKET_TAX;
            ReservCommonHeadline(getApplicationContext(), parent, roomtext);
            Fld_Smoking04(nRooms);
            Fld_NumberPeople05(nRooms);
            ReservCommonHeadline(getApplicationContext(), parent, FLD_ACOMOINFO);
            /*宿泊者情報*/
            Fld_FamilyName07(nRooms);
            Fld_FirstName08(nRooms);
        //    ReservCommonHints(getApplicationContext(), parent, LV_NAME);
            Fld_Gender11(nRooms);
            Fld_MembershipFlag(nRooms);
            Fld_MemberNumber13(nRooms);
            Fld_CountrySelection14(nRooms);
            Fld_TelephoneNumber15(nRooms);
            ReservCommonHints(getApplicationContext(), parent, LV_TELEPHONENUMBER);
            ReservCommonHeadline(getApplicationContext(), parent, FLD_ACOMOINFO);
            if(iData(NUMBER_OF_NIGHT)>1) {
                Fld_EcoFlag(nRooms);
                Fld_EcoSelection(nRooms);
                ReservCommonHints(getApplicationContext(), parent, LV_ECOPLAN);
            }
            Fld_VodPlan22(nRooms);
            ReservCommonHints(getApplicationContext(), parent, LV_VODPLAN);
            Fld_BusinessFlag(nRooms);
            Fld_BusinessSelection(nRooms);
            ReservCommonHints(getApplicationContext(), parent, LV_BUSINESSPACK);
            Fld_BabyDetailsRow26(nRooms);
            ReservCommonHints(getApplicationContext(), parent, LV_BABYBAD);
            Fld_CheckinTime28(nRooms);
        }

        TTL_DynamicPriceLayoutCreation();
    }

    private void Fld_Smoking04(final int nRooms) {
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setBackgroundResource(R.drawable.util_com_background_1);
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

    }

    private void Fld_NumberPeople05(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.util_com_background_1);
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
            deduction.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
            layout.addView(deduction);

            StringBuilder sb = new StringBuilder();
            sb.append(RD_NUMBER_OF_MAX_PEOPLE);
            sb.append("名");
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
            addition.setBackgroundResource(R.drawable.util_com_button_1);
            layout.addView(addition);

        st_personCounter[nRooms] = Integer.valueOf(RD_NUMBER_OF_MAX_PEOPLE);

        if (st_personCounter[nRooms] < 2) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
        }
        if (st_personCounter[nRooms] > Integer.valueOf(NUMBER_OF_MAX_PEOPLE) - 1) {
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
        }
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st_personCounter[nRooms]++;
                if (st_personCounter[nRooms] < Integer.valueOf(NUMBER_OF_MAX_PEOPLE)) {
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    addition.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    addition.setEnabled(false);
                    deduction.setEnabled(true);
                }
                RD_NUMBER_OF_MAX_PEOPLE = String.valueOf(st_personCounter[nRooms]);
                StringBuilder sb = new StringBuilder();
                sb.append(RD_NUMBER_OF_MAX_PEOPLE);
                sb.append("名");
                numberPeople.setText(sb.toString());
            }
        });

        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st_personCounter[nRooms]--;
                if (st_personCounter[nRooms] > 1) {
                    deduction.setBackgroundResource(R.drawable.util_com_button_1);
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                } else {
                    deduction.setBackgroundResource(R.drawable.util_gra_greylite_ypad_ystroke_ycorner_yclickable);
                    addition.setBackgroundResource(R.drawable.util_com_button_1);
                    deduction.setEnabled(false);
                    addition.setEnabled(true);
                }
                RD_NUMBER_OF_MAX_PEOPLE = String.valueOf(st_personCounter[nRooms]);
                numberPeople.setText(RD_NUMBER_OF_MAX_PEOPLE);
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

    private void Fld_Gender11(final int nRooms) {
        genderLayout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        genderLayout.setOrientation(LinearLayout.HORIZONTAL);
        genderLayout.setLayoutParams(p1);
        genderLayout.setPadding(10, 10, 10, 10);
        genderLayout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(genderLayout);

        Cmm_BlueBar(getApplicationContext(), genderLayout);

        Cmm_FieldLevel(getApplicationContext(), genderLayout,FLD_GENDER, 0);

        Cmm_MustItem2(getApplicationContext(), genderLayout);

        ComActivity.Cmm_MustItemSpaceRight(getApplicationContext(), genderLayout);

        la_gender[nRooms] = new Spinner(this);
        genderLayout.addView(la_gender[nRooms]);

/*        la_male[nRooms] = new RadioButton(this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p4.setMargins(0, 0, 5, 0);
        la_male[nRooms].setText(SW_FMALE);
        la_male[nRooms].setTextSize(15);
        la_male[nRooms].setLayoutParams(p4);
        la_male[nRooms].setTextColor(Color.BLACK);
        la_male[nRooms].setChecked(true);
        genderLayout.addView(la_male[nRooms]);

        la_fmale[nRooms] = new RadioButton(this);
        LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p5.setMargins(0, 0, 5, 0);
        la_fmale[nRooms].setText(SW_FMALE);
        la_fmale[nRooms].setTextSize(15);
        la_fmale[nRooms].setLayoutParams(p5);
        la_fmale[nRooms].setTextColor(Color.BLACK);
        la_fmale[nRooms].setChecked(false);
        genderLayout.addView(la_fmale[nRooms]);*/
    }

    private void Fld_MembershipFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0,0);
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
        p1.setMargins(0, 0, 0, -3);
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
            la_phoneNumber[nRooms].setSingleLine(true);
            la_phoneNumber[nRooms].setLayoutParams(p5);
            la_phoneNumber[nRooms].setBackgroundResource(R.drawable.util_textview_bk_pink);
            la_phoneNumber[nRooms].setTextColor(Color.BLACK);
            la_phoneNumber[nRooms].setKeyListener(DigitsKeyListener.getInstance("1234567890"));
            layout.addView(la_phoneNumber[nRooms]);
    }

    private void Fld_EcoFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            TextView textImg = new TextView(this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(10, ActionBar.LayoutParams.WRAP_CONTENT);
            p2.setMargins(0, 0, 5, 0);
            textImg.setBackgroundColor(Color.BLUE);
            textImg.setLayoutParams(p2);
            layout.addView(textImg);

            TextView text1 = new TextView(this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.weight = 1f;
            p3.setMargins(0, 0, 5, 0);
            text1.setText(FLD_ECO_PLAN2);
            text1.setTextSize(15);
            text1.setPadding(10, 10, 10, 10);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            layout.addView(text1);

            la_ecoFlag[nRooms] = new ToggleButton(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(0, 0, 5, 0);
            la_ecoFlag[nRooms].setTextSize(15);
            la_ecoFlag[nRooms].setPadding(10, 10, 10, 10);
            la_ecoFlag[nRooms].setLayoutParams(p5);
            la_ecoFlag[nRooms].setTextColor(Color.BLACK);
            layout.addView(la_ecoFlag[nRooms]);
    }

    private void Fld_EcoSelection(final int nRooms) {
        px_ecoSelectionLayout[nRooms] = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        px_ecoSelectionLayout[nRooms].setOrientation(LinearLayout.VERTICAL);
        px_ecoSelectionLayout[nRooms].setLayoutParams(p1);
        px_ecoSelectionLayout[nRooms].setPadding(10, 10, 10, 10);
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

        final CheckBox[] la_dateCheckbox;
        final TextView[] la_dateSelection;
        final int maxField = 7;
        la_dateSelection = new TextView[maxField];
        la_dateCheckbox = new CheckBox[maxField];
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

            if (x < iData(NUMBER_OF_NIGHT) - 1) {
                layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
            } else {
                layout3.setBackgroundResource(R.drawable.util_gra_yellow_lite);
            }
            layout3.setLayoutParams(p4);
            layout3.setPadding(5, 5, 10, 5);
            layout3.setGravity(Gravity.CENTER_VERTICAL);
            layout2.addView(layout3);

            //Checkbox
            LinearLayout.LayoutParams p4_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            if(screenSize.equalsIgnoreCase("HDPI")) {
                p4_1.setMargins(0, 0, 0, 0);
            }else{
                p4_1.setMargins(2, 2, 2, 2);
            }
            la_dateCheckbox[x] = new CheckBox(this);
            la_dateCheckbox[x].setLayoutParams(p4_1);
            la_dateCheckbox[x].setId(x);

            //Load when first Page Open
            if(LS_ECO_DATA_ROOM1.size()!=0) {
                String xdata = new String();
                for (String sData : LS_ECO_DATA_ROOM1) {
                    xdata = dateNumberOfDaysFromTwoDate(CHECK_IN_DATE, sData);
                    int pos = Integer.valueOf(xdata) -1;
                    if(pos ==x) {
                        la_dateCheckbox[x].setChecked(true);
                        st_room1EcoDateArray[x] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(x + 1));
                        layout3.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
                    }
                }
            }

            layout3.addView(la_dateCheckbox[x]);
            dates[x] = dateMonthDayAdditionUsingDays(CHECK_IN_DATE, String.valueOf(x + 1));
            la_dateSelection[x] = new TextView(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(2, 0, 2, 0);
            la_dateSelection[x].setLayoutParams(p5);
            la_dateSelection[x].setPadding(11, 13, 11, 13);

            if (x < iData(NUMBER_OF_NIGHT) - 1) {
                la_dateSelection[x].setText(dates[x]);
            }
            la_dateSelection[x].setTextSize(8);
            if (x < iData(NUMBER_OF_NIGHT) - 1) {
                la_dateSelection[x].setTextColor(Color.BLACK);
            } else {
                la_dateSelection[x].setTextColor(Color.GRAY);
            }
            la_dateSelection[x].setId(x);
            layout3.addView(la_dateSelection[x]);

            final int xy = x;
            la_dateCheckbox[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    int data = id + 1;

                    if (xy < iData(NUMBER_OF_NIGHT) - 1) {
                        if (la_dateCheckbox[xy].isChecked()) {
                            la_dateSelection[xy].setTextColor(Color.WHITE);
                            la_dateCheckbox[xy].setChecked(true);
                            layout3.setBackgroundResource(R.drawable.util_confirm_yellow_leading);
                            if (nRooms == 0) {
                                st_room1EcoDateArray[id] = dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                ecoPositionRoom1++;
                            }

                            if (nRooms == 1) {
                                if (ecoPositionRoom2 < 3) {
                                    st_room2EcoDateArray[id] =  dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                    ecoPositionRoom2++;
                                } else {
                                    st_room2EcoDateArray[id] = "";
                                    la_dateSelection[xy].setTextColor(Color.BLACK);
                                    la_dateSelection[xy].setEnabled(false);
                                    la_dateCheckbox[xy].setChecked(false);
                                    layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
                                }
                            }
                            if (nRooms == 2) {
                                if (ecoPositionRoom3 < 3) {
                                    st_room3EcoDateArray[id] =  dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                    ecoPositionRoom3++;
                                } else {
                                    st_room3EcoDateArray[id] = "";
                                    la_dateSelection[xy].setTextColor(Color.BLACK);
                                    la_dateSelection[xy].setEnabled(false);
                                    la_dateCheckbox[xy].setChecked(false);
                                    layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
                                }
                            }
                            if (nRooms == 3) {
                                if (ecoPositionRoom4 < 3) {
                                    st_room4EcoDateArray[id] =  dateSameDataPlus(CHECK_IN_DATE, String.valueOf(data));
                                    ecoPositionRoom4++;
                                } else {
                                    st_room4EcoDateArray[id] = "";
                                    la_dateSelection[xy].setTextColor(Color.BLACK);
                                    la_dateSelection[xy].setEnabled(false);
                                    la_dateCheckbox[xy].setChecked(false);
                                    layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
                                }
                            }
                            checkStart();

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
                            la_dateSelection[xy].setTextColor(Color.BLACK);
                            la_dateSelection[xy].setEnabled(false);
                            la_dateCheckbox[xy].setChecked(false);
                            layout3.setBackgroundResource(R.drawable.util_confirm_yellow_left);
                        }
                    } else {
                        layout3.setBackgroundResource(R.drawable.util_gra_yellow_lite);
                        la_dateSelection[xy].setEnabled(false);
                        la_dateCheckbox[xy].setChecked(false);
                    }
                }
            });
        }
    }

    private void Fld_VodPlan22(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            TextView textImg = new TextView(this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(10, ActionBar.LayoutParams.WRAP_CONTENT);
            p2.setMargins(0, 0, 5, 0);
            textImg.setBackgroundColor(Color.BLUE);
            textImg.setLayoutParams(p2);
            layout.addView(textImg);

            TextView text1 = new TextView(this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.weight = 1f;
            p3.setMargins(0, 0, 5, 0);
            text1.setText(FLD_VOD2);
            text1.setTextSize(15);
            text1.setPadding(10, 10, 10, 10);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            layout.addView(text1);

            la_vodFlag[nRooms] = new ToggleButton(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(0, 0, 5, 0);
            la_vodFlag[nRooms].setTextSize(15);
            la_vodFlag[nRooms].setPadding(10, 10, 10, 10);
            la_vodFlag[nRooms].setLayoutParams(p5);
            la_vodFlag[nRooms].setTextColor(Color.BLACK);
            layout.addView(la_vodFlag[nRooms]);

    }

    private void Fld_BusinessFlag(final int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            TextView textImg = new TextView(this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(10, ActionBar.LayoutParams.WRAP_CONTENT);
            p2.setMargins(0, 0, 5, 0);
            textImg.setBackgroundColor(Color.BLUE);
            textImg.setLayoutParams(p2);
            layout.addView(textImg);

            TextView text1 = new TextView(this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.weight = 1f;
            p3.setMargins(0, 0, 5, 0);
            text1.setText(FLD_BUSINESSPACK);
            text1.setTextSize(15);
            text1.setPadding(10, 10, 10, 10);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            layout.addView(text1);

            la_businessFlag[nRooms] = new ToggleButton(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(0, 0, 5, 0);
            la_businessFlag[nRooms].setTextSize(15);
            la_businessFlag[nRooms].setPadding(10, 10, 10, 10);
            la_businessFlag[nRooms].setLayoutParams(p5);
            la_businessFlag[nRooms].setTextColor(Color.BLACK);
            layout.addView(la_businessFlag[nRooms]);
    }

    private void Fld_BusinessSelection(final int nRooms) {
        px_business_pack_layout[nRooms] = new LinearLayout(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p3.setMargins(0, 0, 0, -3);
        px_business_pack_layout[nRooms].setOrientation(LinearLayout.VERTICAL);
        px_business_pack_layout[nRooms].setBackgroundResource(R.drawable.util_com_background_1);
        px_business_pack_layout[nRooms].setLayoutParams(p3);
        px_business_pack_layout[nRooms].setPadding(10, 10, 10, 10);
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

            la_businessPack[x] = new RadioButton(this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p4.setMargins(0, 0, 0, p);
            la_businessPack[x].setLayoutParams(p4);
            la_businessPack[x].setId(x);
            la_businessPack[x].setTextColor(Color.BLACK);
            subGroup_1.addView(la_businessPack[x]);

            if (BUSINESS_FLAG.equalsIgnoreCase("Y")) {
                if (!BUSINESS_FLAG_DATA.isEmpty()) {
                    int xd = iData(BUSINESS_FLAG_DATA);
                    if (x + 1 == xd) {
                        la_businessPack[x].setChecked(true);
                    }
                }
            }

            final int finalX = x;
            la_businessPack[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    int selectedPlan = id + 1;
                    st_businessFlagData[nRooms] = String.valueOf(selectedPlan);
                    for (int j = 0; j < 3; j++) {
                        if (j == id) {
                            la_businessPack[j].setChecked(true);
                        } else {
                            la_businessPack[j].setChecked(false);
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
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(layout);

            TextView textImg = new TextView(this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(10, ActionBar.LayoutParams.WRAP_CONTENT);
            p2.setMargins(0, 0, 5, 0);
            textImg.setBackgroundColor(Color.BLUE);
            textImg.setLayoutParams(p2);
            layout.addView(textImg);

            TextView text1 = new TextView(this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.weight = 1f;
            p3.setMargins(0, 0, 5, 0);
            text1.setText(FLD_BABY_FLAG);
            text1.setTextSize(15);
            text1.setPadding(10, 10, 10, 10);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            layout.addView(text1);

            la_babyFlag[nRooms] = new ToggleButton(this);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p5.setMargins(0, 0, 5, 0);
            la_babyFlag[nRooms].setTextSize(15);
            la_babyFlag[nRooms].setPadding(10, 10, 10, 10);
            la_babyFlag[nRooms].setLayoutParams(p5);
            la_babyFlag[nRooms].setTextColor(Color.BLACK);
            layout.addView(la_babyFlag[nRooms]);
    }

    private void Fld_CheckinTime28(int nRooms) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 20);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setBackgroundResource(R.drawable.util_com_background_1);
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
        la_totalPrice.setTextSize(15);
        la_totalPrice.setTextColor(Color.parseColor("#a70505"));
        la_totalPrice.setPadding(10, 10, 10, 10);
        la_totalPrice.setLayoutParams(p4);
        la_totalPrice.setTextColor(Color.BLACK);
        TopLayout.addView(la_totalPrice);
    }

    //Gender
    private AdapterView.OnItemSelectedListener Spinner1_Gender = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            for (int i = 0; i < iData(NUMBER_OF_ROOM); i++) {
                KeyValuePair item = (KeyValuePair) la_gender[i].getSelectedItem();
                st_gender_code[i] = item.getKey().toString();
            }
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //Country
    private AdapterView.OnItemSelectedListener counterListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            for (int i = 0; i < Integer.valueOf(NUMBER_OF_ROOM); i++) {
                KeyValuePair item = (KeyValuePair) la_country[i].getSelectedItem();
                st_country_code[i] = item.getKey().toString();
                Toast.makeText(G26P23A12ResevInputForm_RCED_3.this, item.getKey().toString(), Toast.LENGTH_LONG).show();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //la_checkInTime
    private AdapterView.OnItemSelectedListener checkinTimeListener = new AdapterView.OnItemSelectedListener() {
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
        for (int i = 0; i < Integer.valueOf(NUMBER_OF_ROOM); i++) {
            final int finalI = i;

            //Family Name
            if (st_familyName[i] != null) {
                la_familyName[i].setText(st_familyName[i]);
            } else {
                st_familyName[i] = la_familyName[i].getText().toString();
            }

            //First Name
            if (st_firstName[i] != null) {
                la_firstName[i].setText(st_firstName[i]);
            } else {
                st_firstName[i] = la_firstName[i].getText().toString();
            }

            //Membership Flag
            if (st_membershipFlag[i] != null) {
                if (st_membershipFlag[i].equalsIgnoreCase("Y")) {
                    st_membershipFlag[i] = "Y";
                    la_membershipFlag[i].setText("YES");
                    la_membershipFlag[i].setChecked(true);
                } else {
                    st_membershipFlag[i] = "N";
                    la_membershipFlag[i].setText("NO");
                    la_membershipFlag[i].setChecked(false);
                }
            } else {
                st_membershipFlag[i] = "N";
            }

            la_membershipFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_membershipFlag[finalI] = "Y";
                    } else {
                        st_membershipFlag[finalI] = "N";
                    }
                    LoadCheckinTime(finalI);
                    checkStart();

                }
            });

            //Phone Number
            if (st_phoneNumber[i] != null) {
                la_phoneNumber[i].setText(st_phoneNumber[i]);
            } else {
                st_phoneNumber[i] = la_phoneNumber[i].getText().toString();
            }
            //Membership Number
            if (st_membershipNumber[i] != null) {
                la_membershipNumber[i].setText(st_membershipNumber[i]);
            } else {
                if (!la_membershipNumber[i].getText().toString().isEmpty()) {
                    st_membershipNumber[i] = la_membershipNumber[i].getText().toString();
                } else {
                    st_membershipNumber[i] = "";
                }
            }

            //Set Gender
            KeyValuePairArrayAdapter adapter_gender = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
            adapter_gender =  ComLib.getGenderKeyValueToAdapter(adapter_gender);
            la_gender[i].setAdapter(adapter_gender);
            la_gender[i].setPrompt("性別");
            la_gender[i].setPrompt(SW_GENDER);
            la_gender[i].setSelection(adapter_gender.getPosition(st_gender_code[i]));
            la_gender[i].setOnItemSelectedListener(Spinner1_Gender);


            //Country
            KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
            adapter = getCountryKeyValueToAdapter(adapter);
            la_country[i].setAdapter(adapter);
            la_country[i].setPrompt(SW_COUNTRY);
            la_country[i].setSelection(adapter.getPosition(st_country_code[i]));
            la_country[i].setOnItemSelectedListener(counterListener);


/*            //Sex
            if (st_sex[i] != null) {
                if (st_sex[i].equalsIgnoreCase("M")) {
                    st_sex[i] = "M";
                    la_male[i].setChecked(true);
                    la_fmale[i].setChecked(false);
                } else {
                    st_sex[i] = "F";
                    la_male[i].setChecked(false);
                    la_fmale[i].setChecked(true);
                }
            } else {
                st_sex[i] = "M";
            }

            la_male[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    st_sex[finalI] = "M";
                    la_male[finalI].setChecked(true);
                    la_fmale[finalI].setChecked(false);
                    SetupToVariable();
                }
            });

            la_fmale[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    st_sex[finalI] = "F";
                    la_male[finalI].setChecked(false);
                    la_fmale[finalI].setChecked(true);
                    SetupToVariable();
                }
            });*/

            if(iData(NUMBER_OF_NIGHT)>1) {
                //Eco Flag
                if (st_ecoFlag[i] != null) {
                    if (st_ecoFlag[i].equalsIgnoreCase("Y")) {
                        st_ecoFlag[i] = "Y";
                        la_ecoFlag[i].setText("YES");
                        la_ecoFlag[i].setChecked(true);
                        px_ecoSelectionLayout[i].setVisibility(View.VISIBLE);
                    } else {
                        px_ecoSelectionLayout[i].setVisibility(View.GONE);
                        st_ecoFlag[i] = "N";
                        la_ecoFlag[i].setText("NO");
                        la_ecoFlag[i].setChecked(false);
                    }
                } else {
                    st_ecoFlag[i] = "N";
                }

                la_ecoFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            st_ecoFlag[finalI] = "Y";
                            px_ecoSelectionLayout[finalI].setVisibility(View.VISIBLE);
                        } else {
                            st_ecoFlag[finalI] = "N";
                            px_ecoSelectionLayout[finalI].setVisibility(View.GONE);
                        }
                    }
                });
            }else{
                st_ecoFlag[i] = "N";
            }

            //Smoking Flag
            if (SMOKING_FLAG.equalsIgnoreCase("Y")) {
                la_smokingIcon[i].setBackgroundResource(R.drawable.ic_g12_p15_smoking_yes);
            } else {
                la_smokingIcon[i].setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
            }

/*            //Room Change
            la_updateRoom[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goTo(G12P16A09ChooseRoomList.class, ComMsg.COD_BACK);
                }
            });*/

            if (ROOM_TYPE_NAME != null) {
                la_roomTypeName[i].setText(ROOM_TYPE_NAME);
            }

            //Business Plan
            if (!st_businessFlag[i].isEmpty()) {
                if (st_businessFlag[i].equalsIgnoreCase("Y")) {
                    st_businessFlag[i] = "Y";
                    la_businessFlag[i].setChecked(true);
                    px_business_pack_layout[i].setVisibility(View.VISIBLE);
                } else {
                    la_businessFlag[i].setChecked(false);
                    for (int j = 0; j < 3; j++) {
                        la_businessPack[j].setChecked(false);
                    }
                    px_business_pack_layout[i].setVisibility(View.GONE);
                }
            } else {
                st_businessFlag[i] = "N";
            }

            la_businessFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_businessFlag[finalI] = "Y";
                        la_businessPack[0].setChecked(true);
                        st_businessFlagData[finalI] = ST_ONE;
                        px_business_pack_layout[finalI].setVisibility(View.VISIBLE);
                    } else {
                        st_businessFlag[finalI] = "N";
                        st_businessFlagData[finalI] = "";
                        px_business_pack_layout[finalI].setVisibility(View.GONE);
                        checkStart();
                    }
                }
            });

            if(BUSINESS_FLAG_DATA.equalsIgnoreCase("1")){
                la_businessPack[0].setChecked(true);
                st_businessFlagData[i] = ST_ONE;
            }else if(BUSINESS_FLAG_DATA.equalsIgnoreCase("2")){
                la_businessPack[1].setChecked(true);
                st_businessFlagData[i] = ST_TWO;
            }else if(BUSINESS_FLAG_DATA.equalsIgnoreCase("3")){
                la_businessPack[2].setChecked(true);
                st_businessFlagData[i] = ST_THREE;
            }else{
                la_businessPack[0].setChecked(true);
                st_businessFlagData[i] = ST_ONE;
            }

            //Vod Flag
            if (st_vodFlag[i] != null) {
                if (st_vodFlag[i].equalsIgnoreCase("Y")) {
                    la_vodFlag[i].setChecked(true);
                    st_vodFlag[i] = "Y";
                } else {
                    st_vodFlag[i] = "N";
                    la_vodFlag[i].setChecked(false);
                }
            } else {
                st_vodFlag[i] = "N";
                la_vodFlag[i].setChecked(false);
            }

            la_vodFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_vodFlag[finalI] = "Y";
                    } else {
                        st_vodFlag[finalI] = "N";
                    }
                    checkStart();
                }
            });

            //Baby Flag
            if (st_babyFlag[i] != null) {
                if (st_babyFlag[i].equalsIgnoreCase("Y")) {
                    st_babyFlag[i] = "Y";
                    la_babyFlag[i].setChecked(true);
                } else {
                    st_babyFlag[i] = "N";
                    la_babyFlag[i].setChecked(false);
                }
            } else {
                st_babyFlag[i] = "N";
                la_babyFlag[i].setChecked(false);
            }

            la_babyFlag[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        st_babyFlag[finalI] = "Y";
                    } else {
                        st_babyFlag[finalI] = "N";
                    }
                    checkStart();
                }
            });

            LoadCheckinTime(i);
        }
    }

    private void checkStart() {
        MODE = ST_TWO;
        submitFlag=false;
        SetupToVariable();
        SetupToJson();
    }

    private void checkEndAndSubmit() {
        MODE = ST_ONE;
        submitFlag=true;
        SetupToVariable();
        SetupToJson();
    }

    private void LoadCheckinTime(int i) {
        //Time
        KeyValuePairArrayAdapter checkInTimeAdapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        // checkInTimeAdapter = getTimeKeyValueMember(checkInTimeAdapter);

        if(st_membershipFlag[i].equalsIgnoreCase(COD_N)){
            checkInTimeAdapter.clear();
            checkInTimeAdapter =  getTimeKeyValueNonMember(checkInTimeAdapter);
            st_checkinTime[i] = "160000";
            ROOM1_CHECK_IN_TIME = "160000";
        }else{
            checkInTimeAdapter.clear();
            checkInTimeAdapter =  getTimeKeyValueMember(checkInTimeAdapter);
            st_checkinTime[i] = "150000";
            ROOM1_CHECK_IN_TIME = "150000";
        }

        la_checkInTime[i].setAdapter(checkInTimeAdapter);
        la_checkInTime[i].setSelection(checkInTimeAdapter.getPosition(st_checkinTime[i]));
        la_checkInTime[i].setOnItemSelectedListener(checkinTimeListener);
    }

    private void SetupToViewPriceList() {
        StringBuilder sb_tp = new StringBuilder();
        sb_tp.append("お支払い金額");
        sb_tp.append(TOTAL_PRICE);
        sb_tp.append("(税込");
        sb_tp.append(TOTAL_PRICE_TAX);
        sb_tp.append(")");

        Toast.makeText(G26P23A12ResevInputForm_RCED_3.this, sb_tp, Toast.LENGTH_LONG).show();
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

        if (la_phoneNumber[x].getText().toString().isEmpty()) {
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

    private void field_genderSex(int x) {
        genderLayout.setBackgroundResource(R.drawable.util_gra_orangedeep_npad_ystroke_ycorner_nclickable);
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
        if (MODE.isEmpty() || MODE.equalsIgnoreCase("1")) {
            int start = Integer.valueOf(NUMBER_OF_ROOM) - 1;
            for (int k = start; k >= 0; k--) {
                //field_MembershipFlag(k);
                field_PhoneNumber(k);
                field_firstName(k);
                // field_genderSex(k);
                field_familyName(k);
            }
        }
        return validationFlag;
    }

    private void SetupToVariable() {
        for (int x = 0; x < Integer.valueOf(NUMBER_OF_ROOM); x++) {

            if (x == 0) {
                LS_ECO_DATA_ROOM1 = getEcoDateToRemoveNullData(st_room1EcoDateArray);
            }
            if (x == 1) {
                LS_ECO_DATA_ROOM2 = getEcoDateToRemoveNullData(st_room2EcoDateArray);
            }
            if (x == 2) {
                LS_ECO_DATA_ROOM3 = getEcoDateToRemoveNullData(st_room3EcoDateArray);
            }
            if (x == 3) {
                LS_ECO_DATA_ROOM2 = getEcoDateToRemoveNullData(st_room4EcoDateArray);
            }

            st_familyName[x] = la_familyName[x].getText().toString();
            st_firstName[x] = la_firstName[x].getText().toString();
            if (la_membershipFlag[x].isChecked()) {
                st_membershipFlag[x] = "Y";
            } else {
                st_membershipFlag[x] = "N";
            }
            st_membershipNumber[x] = la_membershipNumber[x].getText().toString();

            st_phoneNumber[x] = la_phoneNumber[x].getText().toString();
            if (la_businessFlag[x].isChecked()) {
                st_businessFlag[x] = "Y";
            } else {
                for (int j = 0; j < 3; j++) {
                    la_businessPack[j].setChecked(false);
                }
                st_businessFlag[x] = "N";
            }

            if(iData(NUMBER_OF_NIGHT)>1) {
                if (la_ecoFlag[x].isChecked()) {
                    st_ecoFlag[x] = "Y";
                } else {
                    st_ecoFlag[x] = "N";
                }
            }else{
                st_ecoFlag[x] = "N";
            }

            if (la_vodFlag[x].isChecked()) {
                st_vodFlag[x] = "Y";
            } else {
                st_vodFlag[x] = "N";
            }
            //st_checkinTime[x] = la_checkInTime[x].getSelectedItem().toString();

            LS_CHECK_IN_DATE.add(x, CHECK_IN_DATE);
            LS_CHECK_OUT_DATE.add(x, CHECK_OUT_DATE);
            LS_ROOM_TYPE_NAME.add(x, ROOM_TYPE_NAME);
            LS_NUMBER_OF_PEOPLE.add(x, String.valueOf(st_personCounter[x]));
            LS_FAMILY_NAME.add(x, st_familyName[x]);
            LS_FRIST_NAME.add(x, st_firstName[x]);
            LS_MEMBERSHIP_FLAG.add(x, st_membershipFlag[x]);
            LS_MEMBERSHIP_NUMBER.add(x, st_membershipNumber[x]);
            LS_COUNTRY.add(x, st_country_code[x]);
            LS_SEX.add(x, st_gender_code[x]);
            LS_PHONE_NUMBER.add(x, st_phoneNumber[x]);
            LS_BUSINESS_FLAG.add(x, st_businessFlag[x]);
            LS_BUSINESS_FLAG_DATA.add(x, st_businessFlagData[x]);
            LS_ECO_FLAG.add(x, st_ecoFlag[x]);
            LS_BABY_FLAG.add(x, st_babyFlag[x]);
            LS_VOD_FLAG.add(x, st_vodFlag[x]);
         //   LS_CHECKIN_TIME.add(x, st_checkinTime[x]);
        }
    }

    private void SetupToParcel() {
        if (MODE.equalsIgnoreCase(ST_ONE)) {
            int r = NM_ZERO;
            obj_g01.setCheckinDate(LS_CHECK_IN_DATE.get(r).toString());
            obj_g01.setCheckoutDate(LS_CHECK_OUT_DATE.get(r).toString());
            obj_g01.setRdSmokingFlag(SMOKING_FLAG);
            obj_g01.setRdRoomName(LS_ROOM_TYPE_NAME.get(r).toString());
            obj_g01.setRdNumberOfMaxPeople(LS_NUMBER_OF_PEOPLE.get(r).toString());
            obj_g01.setCustFmlyName(LS_FAMILY_NAME.get(r).toString());
            obj_g01.setCustFrstName(LS_FRIST_NAME.get(r).toString());
            obj_g01.setCustMmbrshpFlag(LS_MEMBERSHIP_FLAG.get(r).toString());
            obj_g01.setCustMmbrshpNmbr(LS_MEMBERSHIP_NUMBER.get(r).toString());
            obj_g01.setCustNtnltyCode(LS_COUNTRY.get(r).toString());
            obj_g01.setCustSex(LS_SEX.get(r).toString());
            obj_g01.setCustPhnNmbr(LS_PHONE_NUMBER.get(r).toString());
            obj_g01.setRdBsnssPackFlag(LS_BUSINESS_FLAG.get(r).toString());
            obj_g01.setRdBsnssPackData(LS_BUSINESS_FLAG_DATA.get(r).toString());
            obj_g01.setRdEcoFlag(LS_ECO_FLAG.get(r).toString());
            obj_g01.setLsEcoDateSelection(LS_ECO_DATA_ROOM1);
            obj_g01.setRdVodFlag(LS_VOD_FLAG.get(r).toString());
            obj_g01.setRdBabyBad(LS_BABY_FLAG.get(r).toString());
       //     obj_g01.setRdCheckInTime(LS_CHECKIN_TIME.get(r).toString());
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
        Button button = (Button) findViewById(R.id.g26_p23_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    checkEndAndSubmit();
                    /*
                    MODE = "1";
                   submitFlag = true;
                    //SetupToView();
                    SetupToVariable();
                    SetupToJson();*/
                } else {
                    errorPopup();
                }
            }
        });
    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g26_p23_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAGE_FLAG.equalsIgnoreCase("G50P26")) {
                    goTo(G13P17A10RoomDetailsSetting.class, "back");
                } else {
                    finish("back");
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
            MainDataForBeforeSubmitJsonA012();
            setApiRequestDataA012(Integer.valueOf(NUMBER_OF_ROOM));
        }


        //MainData For Test Purpost
        //------------------------------------------------------------------------------------------
        private void MainDataForBeforeSubmitJsonA012() {
            ArrayList<String> rsrvtnNmbr = new ArrayList<String>();
            ArrayList<String> checkinDate = new ArrayList<String>();
            ArrayList<String> checkOutDate = new ArrayList<String>();
            ArrayList<String> roomTypeCode = new ArrayList<String>();
            ArrayList<String> ecoDtsList = new ArrayList<String>();
            ArrayList<String> ecoChckn = new ArrayList<String>();
            ArrayList<String> bsnssPackType = new ArrayList<String>();
            ArrayList<String> roomPlanCode = new ArrayList<String>();

            int max = Integer.valueOf(NUMBER_OF_ROOM);
            for (int i = 0; i < max; i++) {
                rsrvtnNmbr.add("");
                checkinDate.add(CHECK_IN_DATE);
                checkOutDate.add(CHECK_OUT_DATE);
                roomTypeCode.add(ROOM_TYPE_CODE);
                roomPlanCode.add(RD_PLAN_CODE);
                ecoDtsList.add("");
                ecoChckn.add("N");
                bsnssPackType.add("");
            }
            map.put(CT_RSRVTNNMBR, rsrvtnNmbr);
            map.put(CT_CHCKNDATE, checkinDate);
            map.put(CT_CHCKTDATE, checkOutDate);
            map.put(CT_ROOMTYPE, roomTypeCode);
            map.put(CT_PLANCODE,roomPlanCode);
            map.put(CT_NMBRPPL, LS_NUMBER_OF_PEOPLE);
            map.put(CT_FMLYNAME, LS_FAMILY_NAME);
            map.put(CT_FRSTNAME, LS_FRIST_NAME);
            map.put(CT_SEX, LS_SEX);
            map.put(CT_MMBRSHPFLAG, LS_MEMBERSHIP_FLAG);
            map.put(CT_MMBRSHPNMBR, LS_MEMBERSHIP_NUMBER);
            map.put(CT_NTNLTYCODE, LS_COUNTRY);
            map.put(CT_PHNNMBR, LS_PHONE_NUMBER);
            map.put(CT_ECOFLAG, LS_ECO_FLAG);
            map.put(CT_ECODTSLIST, ecoDtsList);
            map.put(CT_ECOCHCKN, ecoChckn);
            map.put(CT_VODFLAG, LS_VOD_FLAG);
            map.put(CT_BSNSSPACKFLAG, LS_BUSINESS_FLAG);
            map.put(CT_BSNSSPACKTYPE, LS_BUSINESS_FLAG_DATA);
            map.put(CT_CHLDRNSHRNGBED, LS_BABY_FLAG);
           // map.put(CT_CHCKNTIME, LS_CHECKIN_TIME);
            dataArray.add(map);
        }


        private void setApiRequestDataA012(int numRooms) {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setMood(MODE);
            api.setHotelCode(HOTEL_CODE);
            api.setRoom1_chcknTime(ROOM1_CHECK_IN_TIME);
            api.setEcoDtsList1(LS_ECO_DATA_ROOM1);
            Log.e("PARAM-G26P23", api.getRequestDataA012(numRooms, dataArray).toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G26P23A12ResevInputForm_RCED_3.this);
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
            Log.e("JSON-G26P23", json.toString());
            if (!isDataDuplicate(json.optString(ComConstant.CT_ERRRCODE))) {
                if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                    errorCode = json.optString(ComConstant.CT_ERRRCODE);
                    errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                    processDialog.dismiss();
                    cancel(true);
                }
            }

            TOTAL_PRICE = json.optString(ComConstant.CT_TTLPRC);
            TOTAL_PRICE_TAX = json.optString(ComConstant.CT_TTLPRCINCLDNGTAX);
            optnPrc = getMultipleRoomsData(json, "optnPrc", Integer.valueOf(NUMBER_OF_ROOM));
            sbttlPrc = getMultipleRoomsData(json, "sbttlPrc", Integer.valueOf(NUMBER_OF_ROOM));
            sbttlPrcIncldngTax = getMultipleRoomsData(json, "sbttlPrcIncldngTax", Integer.valueOf(NUMBER_OF_ROOM));

            // Reservation Input Form
            try {
                JSONArray jsonData1 = json.getJSONArray(ComConstant.LT_ROOM1_PRCLIST);
                // Room 1
                for (int i = 0; i < jsonData1.length(); i++) {
                    JSONObject jsonObject = jsonData1.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_PRC, jsonObject.getString(ComConstant.CT_PRC));
                    map.put(ComConstant.CT_PRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_PRCINCLDNGTAX));
                    arraylist_1.add(map);
                }

                // Room 2
                JSONArray jsonData2 = json.getJSONArray(ComConstant.LT_ROOM2_PRCLIST);
                for (int i = 0; i < jsonData2.length(); i++) {
                    JSONObject jsonObject = jsonData2.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_PRC, jsonObject.getString(ComConstant.CT_PRC));
                    map.put(ComConstant.CT_PRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_PRCINCLDNGTAX));
                    arraylist_2.add(map);
                }

                // Room 3
                JSONArray jsonData3 = json.getJSONArray(ComConstant.LT_ROOM3_PRCLIST);
                for (int i = 0; i < jsonData3.length(); i++) {
                    JSONObject jsonObject = jsonData3.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_PRC, jsonObject.getString(ComConstant.CT_PRC));
                    map.put(ComConstant.CT_PRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_PRCINCLDNGTAX));
                    arraylist_3.add(map);
                }

                // Room 4
                JSONArray jsonData4 = json.getJSONArray(ComConstant.LT_ROOM4_PRCLIST);
                for (int i = 0; i < jsonData4.length(); i++) {
                    JSONObject jsonObject = jsonData4.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(ComConstant.CT_PRC, jsonObject.getString(ComConstant.CT_PRC));
                    map.put(ComConstant.CT_PRCINCLDNGTAX, jsonObject.getString(ComConstant.CT_PRCINCLDNGTAX));
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
            processDialog.dismiss();
            if (submitFlag) {
                SetupToParcel();
                goTo(G27P24A17ReservConfimSubmit_RCED_4.class, COD_NEXT);
            }
        }

        @Override
        protected void onCancelled() {
            jsonErrorPopup(errorCode, errorMessage);
        }
    }

    private void jsonErrorPopup(String eCode, String eMessage) {
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

    private void errorPopup() {
        final Dialog dialog = new Dialog(G26P23A12ResevInputForm_RCED_3.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g14p18_error);
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

    private void SetupToJson() {
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            obj_g01.setErrrMssg(ERR_CONNECTION);
            goTo(ComActivity.class, "next");
        }
    }

    private void finish(String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase("back")) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase("next")) {
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
