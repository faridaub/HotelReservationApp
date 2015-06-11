package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G28P36A00ReservConfirmEditDelete_RCED_5 extends Activity {
    private G01P01ParcelableData obj_g01;

    private TextView la_reservationID;
    private TextView la_hotel_name;
    private TextView la_checkindate;
    private TextView la_checkoutdate;
    private TextView la_numberrooms;
    private TextView la_totalamount;


    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String ROOM_TYPE_CODE;
    private String NUMBER_OF_PEOPLE;

    private String RSRVSPRSNUID;
    private String RSRVID;
    private String CUSTRSRVTNNMBR;
    private String HOTEL_CODE;
    private String TTLPRC;
    private String TTLPRCINCLDNGTAX;
    private String HOTEL_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G28P36A00ReservConfirmEditDelete_RCED_5------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g28_p36_reserv_confirm_edit_delete_setp5);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
        G15P19_getDataFrom_G14P18();

        //Setup To View
        SetupToView();

        //Back To Home Page
        G27P24_backToHomePage();

        //Back To List View
        BackToListView();

    }

    private void BackToListView() {
        Button button  = (Button)findViewById(R.id.g28_p36_go_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setPageFlag("G28P36");
                goTo(G17P22A15ReservConfirm_RCED_2.class, ComMsg.COD_BACK);
            }
        });

    }

    private void G27P24_backToHomePage() {
        Button button = (Button)findViewById(R.id.g28_p36_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_BACK);
            }
        });
    }


    private void SetupToView() {
        la_reservationID.setText(CUSTRSRVTNNMBR);
        la_hotel_name.setText(HOTEL_NAME);
        la_checkindate.setText(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        la_checkoutdate.setText(ComLib.dateConvertFormattedDate(CHECK_OUT_DATE));
        la_numberrooms.setText(NUMBER_OF_ROOM);
        la_totalamount.setText(ComLib.getTotalPriceWithTax(TTLPRC, TTLPRCINCLDNGTAX));
    }

    private void InitialSetupConfiguration() {
        this.la_reservationID = (TextView)findViewById(R.id.g28_p36_reserv_number);
        this.la_hotel_name = (TextView)findViewById(R.id.g28_p36_hotel_name);
        this.la_checkindate = (TextView)findViewById(R.id.g28_p36_checkin_date);
        this.la_checkoutdate= (TextView)findViewById(R.id.g28_p36_checkout_date);
        this.la_numberrooms = (TextView)findViewById(R.id.g28_p36_number_rooms);
        this.la_totalamount = (TextView)findViewById(R.id.g28_p36_total_amount);


        this.RSRVSPRSNUID = new String();
        this.NUMBER_OF_ROOM= new String();
        this.HOTEL_CODE = new String();
        this.CUSTRSRVTNNMBR = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.RSRVSPRSNUID = new String();
        this.RSRVID = new String();
        this.CUSTRSRVTNNMBR = new String();
        this.TTLPRC = new String();
        this.TTLPRCINCLDNGTAX = new String();
        this.HOTEL_NAME = new String();

    }

    private void G15P19_getDataFrom_G14P18() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if (!obj_g01.getCustRsrvid().isEmpty()) {
                RSRVID = obj_g01.getCustRsrvid();
            }

            if (!obj_g01.getCustRsrvtnNmbr().isEmpty()) {
                CUSTRSRVTNNMBR = obj_g01.getCustRsrvtnNmbr();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getCustRsrvtnNmbr();
            }

            if (!obj_g01.getRdTotalPrice().isEmpty()) {
                TTLPRC = obj_g01.getRdTotalPrice();
            }

            if (!obj_g01.getRdTotalPriceTax().isEmpty()) {
                TTLPRCINCLDNGTAX = obj_g01.getRdTotalPriceTax();
            }
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
        Intent intent =  intent = new Intent(getApplicationContext(),myClass);
        intent.putExtra("DATA", obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase("back")) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase("next")) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }



}
