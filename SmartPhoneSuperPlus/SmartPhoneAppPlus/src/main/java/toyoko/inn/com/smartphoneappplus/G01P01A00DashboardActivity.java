package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G01P01A00DashboardActivity extends Activity {

    private G01P01ParcelableData obj_g01;
    private LinearLayout la_roomType1;
    private LinearLayout la_roomType2;
    private LinearLayout la_roomType3;
    private LinearLayout la_top_button_layout;
    private RadioButton la_radioButton1;
    private RadioButton la_radioButton2;
    private RadioButton la_radioButton3;
    private Button la_destSrcText;
    private TextView la_person1_room1;
    private TextView la_person2_room2;
    private TextView la_person1_room2;
    private Button la_button_acc_info;

    private String MOOD;
    private String DESTINATION_KEY;
    private String ROOM_TYPE;
    private String LONGITUDE;
    private String LATITUDE;
    private String DISTANCE;
    private String COUNTRY;
    private String COUNTRY_CODE;
    private String AREA;
    private String AREA_CODE;
    private String STATE;
    private String STATE_CODE;
    private String HOTEL_NUM;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String SMOKING_FLAG;
    private String RSRVSPRSNUID;
    private String MEMBERSHIP_FLAG;
    private String PAGE_FLAG;
    private Boolean LoginPopupFlag;
    private LinearLayout ll_currentposition;
    private LinearLayout ll_keyword;
    private LinearLayout ll_bookmark;
    private String CITY_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G01P01A00DashboardActivity------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g01_p01_dashboard);

        //Initialization for Constraint Setup
        InitialSetupConfiguration();

        //Get Data
        GetData();

        //Setup Data To View
        SetupToView();

        //Change Radio button
        ChangeRadioButton();

        //Room Size / Person Number Selection
        HotelTypeActionLinearButton();

        //If Not Login Dialog Box will appear
        SetupLoginDialogBox();

        //Go To Keyword Search
        GoToDestinationSearch();

        //Go To Hotel List
        GoToHotelListView();

        //Go To Account Information
        GoToLoginAndAccountPage();

        //Go To Conditional Search
        GoToConditionalSearch();

        //==>Go To News Information
        GoToNewsInformation();

        //==>Reservation Test
        GoToReservationRegistration();


    }

    private void GoToReservationRegistration() {
/*        Button reservation_registration = (Button) findViewById(R.id.reservationtest);
        reservation_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*
                lngg=ja,
                key=webapi.toyoko-inn.com,
                mmbrshpFlag=N,
                chcknDate=20150305,
                htlCode=00800,
                roomType=S,
                nmbrNghts=1,
                nmbrPpl=1,
                nmbrRms=1,
                smkngFlag=Y]
                *//*
                obj_g01.setNumberOfRoom("1");
               // obj_g01.setCheckinDate("20150306");
               // obj_g01.setCheckinDate("20150307");
                obj_g01.setHotelName("東横INN蒲田１【駐車場電話00800");
                obj_g01.setRoomType("1");
                obj_g01.setNumberOfPeople("1");
                obj_g01.setNumberOfStayNight("5");
                obj_g01.setHotelCodeNew("00800");
                obj_g01.setHotelCode("00800");
                obj_g01.setMood("3");

                obj_g01.setRdEcoFlag("N");
                obj_g01.setRdVodFlag("N");
                obj_g01.setRdBsnssPackFlag("N");
                obj_g01.setRdSmokingFlag("Y");
                obj_g01.setRdRoomName("喫煙シングル");
                obj_g01.setRdRoomTypeCode("S");
                obj_g01.setRdNumberOfMaxPeople("1");
                obj_g01.setRdTotalPrice("10000");
                obj_g01.setRdTotalPriceTax("14000");

                obj_g01.setCustFmlyName("Anamul Haq");
                obj_g01.setCustFrstName("Farid");
                obj_g01.setCustSex("F");
                obj_g01.setCustNtnltyCode("JPA");
                obj_g01.setCustPhnNmbr("090-65468765");
                obj_g01.setRdEcoFlag("N");
                obj_g01.setCustMmbrshpFlag("N");
                obj_g01.setCustRsrvsPrsnUid("207317");
                goTo(wwwwMainActivity.class, COD_NEXT);
            }
        });*/
    }

    private void InitialSetupConfiguration() {
        this.la_top_button_layout = (LinearLayout) findViewById(R.id.g01_p01_top_button_layout);
        this.ll_currentposition = (LinearLayout) findViewById(R.id.ll_currentposition);
        this.ll_keyword = (LinearLayout) findViewById(R.id.ll_keyword);
        this.ll_bookmark = (LinearLayout) findViewById(R.id.ll_bookmark);
        this.la_destSrcText = (Button) findViewById(R.id.g01_search_field);
        this.la_button_acc_info = (Button) findViewById(R.id.act_g02_frd_acc_info);
        this.la_roomType1 = (LinearLayout) findViewById(R.id.la_btn_free_room01);
        this.la_roomType2 = (LinearLayout) findViewById(R.id.la_btn_free_room02);
        this.la_roomType3 = (LinearLayout) findViewById(R.id.la_btn_free_room03);
        this.la_radioButton1 = (RadioButton) findViewById(R.id.btn_radio_01);
        this.la_radioButton2 = (RadioButton) findViewById(R.id.btn_radio_02);
        this.la_radioButton3 = (RadioButton) findViewById(R.id.btn_radio_03);
        this.la_person1_room1 = (TextView) findViewById(R.id.g01_person1_room1);
        this.la_person2_room2 = (TextView) findViewById(R.id.g01_person2_room2);
        this.la_person1_room2 = (TextView) findViewById(R.id.g01_person1_room2);

        this.MEMBERSHIP_FLAG = new String();
        this.RSRVSPRSNUID = new String();
        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.DESTINATION_KEY = new String();
        this.LONGITUDE = new String();
        this.LATITUDE = new String();
        this.DISTANCE = new String();
        this.COUNTRY = new String();
        this.COUNTRY_CODE = new String();
        this.AREA = new String();
        this.AREA_CODE = new String();
        this.CITY_CODE = new String();
        this.STATE = new String();
        this.STATE_CODE = new String();
        this.HOTEL_NUM = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.SMOKING_FLAG = new String();
        this.PAGE_FLAG = new String();
        this.LoginPopupFlag = true;

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getRoomType().isEmpty() && obj_g01.getRoomType() != null) {
                ROOM_TYPE = obj_g01.getRoomType();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }
            if (!obj_g01.getCountry().isEmpty()) {
                COUNTRY = obj_g01.getCountry();
            }

            if (!obj_g01.getCountryCode().isEmpty()) {
                COUNTRY_CODE = obj_g01.getCountryCode();
            }

            if (!obj_g01.getArea().isEmpty()) {
                AREA = obj_g01.getArea();
            }

            if (!obj_g01.getAreaCode().isEmpty()) {
                AREA_CODE = obj_g01.getAreaCode();
            }

            if (!obj_g01.getState().isEmpty()) {
                STATE = obj_g01.getState();
            }

            if (!obj_g01.getStateCode().isEmpty()) {
                STATE_CODE = obj_g01.getStateCode();
            }

            if (!obj_g01.getCityCode().isEmpty()) {
                CITY_CODE = obj_g01.getCityCode();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }
        }
    }

    private void SetupToVariable() {
        NUMBER_OF_NIGHT = "1";
        if (ROOM_TYPE != null) {
            if (ROOM_TYPE.equalsIgnoreCase("1")) {
                NUMBER_OF_PEOPLE = "1";
                NUMBER_OF_ROOM = "1";
            } else if (ROOM_TYPE.equalsIgnoreCase("2")) {
                NUMBER_OF_PEOPLE = "2";
                NUMBER_OF_ROOM = "1";
            } else if (ROOM_TYPE.equalsIgnoreCase("3")) {
                NUMBER_OF_PEOPLE = "1";
                NUMBER_OF_ROOM = "2";
            }
        }

        if (MOOD != null) {
            if (MOOD.equalsIgnoreCase("2")) {
            } else if (MOOD.equalsIgnoreCase("1") == true) {
                DESTINATION_KEY = la_destSrcText.getText().toString();
            } else if (MOOD.equalsIgnoreCase("3") == true) {
                getLetituteAndLengitute();
            } else if (MOOD.equalsIgnoreCase("4") == true) {

            } else {
                getLetituteAndLengitute();
            }
        } else {
            getLetituteAndLengitute();
        }
    }

    private void SetupToView() {
        if(RSRVSPRSNUID.isEmpty()){
            DISTANCE= ST_FIVE;
        }

        if (!ROOM_TYPE.isEmpty()) {
            if (ROOM_TYPE.equalsIgnoreCase(ST_ONE)) {
                la_roomType1.setBackgroundResource(R.drawable.util_topbox_grey_on);
                la_roomType2.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType3.setBackgroundResource(R.drawable.util_topbox_white_off);
            } else if (ROOM_TYPE.equalsIgnoreCase(ST_TWO)) {
                la_roomType1.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType2.setBackgroundResource(R.drawable.util_topbox_grey_on);
                la_roomType3.setBackgroundResource(R.drawable.util_topbox_white_off);
            }
            //:現在地からの検索
            else if (ROOM_TYPE.equalsIgnoreCase(ST_THREE)) {
                la_roomType1.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType2.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType3.setBackgroundResource(R.drawable.util_topbox_grey_on);
            }else {
                la_roomType1.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType2.setBackgroundResource(R.drawable.util_topbox_white_off);
                la_roomType3.setBackgroundResource(R.drawable.util_topbox_white_off);
            }
        }else {
            la_roomType1.setBackgroundResource(R.drawable.util_topbox_white_off);
            la_roomType2.setBackgroundResource(R.drawable.util_topbox_white_off);
            la_roomType3.setBackgroundResource(R.drawable.util_topbox_white_off);
        }

        if (MOOD != null) {
            if (MOOD.equalsIgnoreCase("2")) {
                la_radioButton1.setChecked(false);
                la_radioButton2.setChecked(true);
                la_radioButton3.setChecked(false);
                ll_currentposition.setBackgroundResource(R.drawable.util_row_start_off);
                la_destSrcText.setBackgroundResource(R.drawable.util_textview_bk_grey);
                ll_keyword.setBackgroundResource(R.drawable.util_row_middle_on);
                ll_bookmark.setBackgroundResource(R.drawable.util_row_end_off);
            } else if (MOOD.equalsIgnoreCase("1") == true) {
                la_radioButton1.setChecked(false);
                la_radioButton2.setChecked(true);
                la_radioButton3.setChecked(false);
                ll_currentposition.setBackgroundResource(R.drawable.util_row_start_off);
                la_destSrcText.setBackgroundResource(R.drawable.util_textview_bk_grey);
                ll_keyword.setBackgroundResource(R.drawable.util_row_middle_on);
                ll_bookmark.setBackgroundResource(R.drawable.util_row_end_off);
            } else if (MOOD.equalsIgnoreCase("3") == true) {
                la_radioButton1.setChecked(true);
                la_radioButton2.setChecked(false);
                la_radioButton3.setChecked(false);
                ll_currentposition.setBackgroundResource(R.drawable.util_row_start_on);
                la_destSrcText.setBackgroundResource(R.drawable.util_textview_bk_white);
                ll_keyword.setBackgroundResource(R.drawable.util_row_middle_off);
                ll_bookmark.setBackgroundResource(R.drawable.util_row_end_off);
            } else if (MOOD.equalsIgnoreCase("4") == true) {
                la_radioButton1.setChecked(false);
                la_radioButton2.setChecked(false);
                la_radioButton3.setChecked(true);
                ll_currentposition.setBackgroundResource(R.drawable.util_row_start_off);
                la_destSrcText.setBackgroundResource(R.drawable.util_textview_bk_white);
                ll_keyword.setBackgroundResource(R.drawable.util_row_middle_off);
                ll_bookmark.setBackgroundResource(R.drawable.util_row_end_on);
            }
        }

        la_radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOOD = "2";
                SetupToView();
                SetupToVariable();
                SetupToParcel();
                goTo(G04P02A03DestinationSearch.class, COD_NORMAL);
            }
        });


        ll_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOOD = "2";
                SetupToView();
                SetupToVariable();
                SetupToParcel();
                goTo(G04P02A03DestinationSearch.class, COD_NORMAL);
            }
        });

        if (!DESTINATION_KEY.isEmpty()) {
            la_destSrcText.setText(DESTINATION_KEY);
        }
    }

    private void GoToDestinationSearch() {
        la_destSrcText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOOD = "2";
                SetupToView();
                SetupToVariable();
                SetupToParcel();
                goTo(G04P02A03DestinationSearch.class, COD_NORMAL);
            }
        });
    }

    private void GoToHotelListView() {
        final Button button = (Button) findViewById(R.id.g01_btn_goto_g11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToVariable();
                if (SetupValidation()) {
                    SetupToJson();
                }
            }
        });
    }

    private void GoToLoginAndAccountPage() {
        la_button_acc_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                if (RSRVSPRSNUID.isEmpty()) {
                    goTo(G50P26A32Login.class, COD_NEXT);
                } else {
                    goTo(G02P20A01AccountInformation.class, COD_NEXT);
                }
            }
        });
    }

    private void GoToConditionalSearch() {
        final Button btn_p09 = (Button) findViewById(R.id.act_p09_frd);
        btn_p09.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MOOD = "3"; // Search Condition
               // SetupToView();
                SetupToVariable();
                SetupToParcel();
                obj_g01.setSmokingFlag(ComMsg.COD_Y);
                obj_g01.setNumberOfRoom(ST_ONE);
                obj_g01.setNumberOfStayNight(ST_ONE);
                goTo(G06P09A00SearchByCondition.class, COD_NORMAL);
            }
        });
    }

    private void GoToNewsInformation() {
        final Button button = (Button) findViewById(R.id.g01_news_information);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToVariable();
                SetupToParcel();
                goTo(G32P14NoticeBoard.class, COD_NORMAL);
            }
        });
    }

    private void SetupToParcel() {
        obj_g01.setPageFlag("G01");
        obj_g01.setMood(MOOD);
        obj_g01.setRoomType(ROOM_TYPE);
        obj_g01.setDestinationKey(DESTINATION_KEY);
        obj_g01.setCountry(COUNTRY);
        obj_g01.setCountryCode(COUNTRY_CODE);
        obj_g01.setArea(AREA);
        obj_g01.setAreaCode(AREA_CODE);
        obj_g01.setCityCode(CITY_CODE);
        obj_g01.setState(STATE);
        obj_g01.setStateCode(STATE_CODE);
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setDistance(DISTANCE);
        obj_g01.setLongitude(LONGITUDE);
        obj_g01.setLatitude(LATITUDE);
        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setNumberOfPeople(NUMBER_OF_PEOPLE);
        obj_g01.setNumberOfRoom(NUMBER_OF_ROOM);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setCustRsrvsPrsnUid(RSRVSPRSNUID);
        obj_g01.setCustMmbrshpFlag(MEMBERSHIP_FLAG);
        obj_g01.setSwitchPage(ComInitData.ST_NULL);
    }

    private void ChangeRadioButton() {
        la_radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MOOD.equalsIgnoreCase("2")) {
                    DESTINATION_KEY = "";
                    la_destSrcText.setText("");
                }
                MOOD = "3";
                SetupToView();
            }
        });

        //目的地を入力
        la_radioButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (MOOD.isEmpty()) {
                    MOOD = "1";
                } else {
                    if (MOOD.equalsIgnoreCase("2")) {
                        MOOD = "2";
                    } else {
                        MOOD = "1";
                    }
                }
                SetupToView();
            }
        });

        //お気に入りのホテル
        la_radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOOD = "4";
                SetupToView();
            }
        });
    }

    private void HotelTypeActionLinearButton() {
        la_roomType1 = (LinearLayout) findViewById(R.id.la_btn_free_room01);
        la_roomType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ROOM_TYPE = "1";
                SetupToView();
            }
        });

        LinearLayout layoutButton02 = (LinearLayout) findViewById(R.id.la_btn_free_room02);
        layoutButton02.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ROOM_TYPE = "2";
                SetupToView();
            }
        });

        LinearLayout layoutButton03 = (LinearLayout) findViewById(R.id.la_btn_free_room03);
        layoutButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ROOM_TYPE = "3";
                SetupToView();
            }
        });

    }

    private void SetupLoginDialogBox() {
        if (PAGE_FLAG.equalsIgnoreCase("G04P02")) {
            LoginPopupFlag = false;
        } else {
            LoginPopupFlag = true;
        }
    }

    private boolean SetupValidation() {
        if (MOOD.isEmpty() && ROOM_TYPE.isEmpty()) {
            errorPopup(null, ERR_TOP_1);
            return false;
        }else if (!MOOD.isEmpty() && ROOM_TYPE.isEmpty()) {
            errorPopup(null,  ERR_TOP_2);
            return false;
        }else if (MOOD.isEmpty() && !ROOM_TYPE.isEmpty()) {
            errorPopup(null,  ERR_TOP_5);
            return false;
        } else if (MOOD.equalsIgnoreCase("1")) {
            String destKey = la_destSrcText.getText().toString();
            la_destSrcText.setBackgroundResource(R.drawable.util_textview_bk_grey);
            if (destKey.isEmpty()) {
                errorPopup(null,  ERR_TOP_3);
                return false;
            }
        } else if (MOOD.equalsIgnoreCase("3") && !CheckGPRSConnection()) {
            buildAlertMessageNoGps(null,  ERR_TOP_4);
            return false;
        }
        return true;
    }



    private void getLetituteAndLengitute() {
/*
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
*/

        LATITUDE = "35.559268";
        LONGITUDE = "139.712387";
        //LONGITUDE = String.valueOf(longitude);
        //LATITUDE = String.valueOf(latitude);
    }

    public boolean CheckGPRSConnection() {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(G01P01A00DashboardActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g00p00_gprs);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.btn_error_code);
        la_errorCode.setText(getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.btn_error_message);
        la_errorMsg.setText(eMessage);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.gprs_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button yes = (Button) dialog.findViewById(R.id.gprs_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A008();
        }

        private void checkURLValidation() {
            if (isValidURL(api.getURLA008())) {
                errorCode = null;
                errorMessage = ERR_URL;
                cancel(true);
            }
        }

        private void setApiRequestData_A008() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setSmkngFlag(SMOKING_FLAG);
            api.setMood(MOOD);
            api.setKywrd(DESTINATION_KEY);
            api.setLttd(LATITUDE);
            api.setLngtd(LONGITUDE);
            api.setCntryCode(COUNTRY_CODE);
            api.setAreaCode(AREA_CODE);
            api.setSttCode(STATE_CODE);
            api.setCityCode(CITY_CODE);
            api.setDstnc(DISTANCE);
            api.setRoomType(ST_NULL);
            Log.e(ComLogMsg.PARAM_G01P01, api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G01P01A00DashboardActivity.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA008());
            JSONObject json = jParser.getJSONData(api.getURLA008());
            if (json == null){
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G01P01, json.toString());
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToParcel();
            if (MOOD.equalsIgnoreCase("4")) {
                goTo(G21P31A20FavoriteHotels.class, COD_NEXT);
            } else {
                goTo(G11P04A08HotelSearchList.class, COD_NEXT);
            }
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            processDialog.dismiss();
            if(errorCode.equalsIgnoreCase("BAPI1004")){
                errorMessage = ERR_NO_HOTEL_FOUND;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void SetupToJson() {
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }


}
