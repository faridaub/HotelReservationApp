package toyoko.inn.com.smartphoneappplus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static android.view.View.OnClickListener;


public class G15P35A13ReservRegistrationStep43Finish extends Activity {
    private G01P01ParcelableData obj_g01;

    ArrayList<Map<String,ArrayList<String>>> dataArray = new ArrayList<Map<String,ArrayList<String>>>();

    ArrayList<HashMap<String,ArrayList<HashMap<String,String>>>> fullData =  new ArrayList<HashMap<String,ArrayList<HashMap<String,String>>>>();

    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();


    private String NUMBER_OF_ROOM;

    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;

    private LinearLayout parent;

    private ArrayList<String> LS_RSRV_NMBR;
    private TextView la_checkInDate;
    private TextView la_checkOutDate;
    private TextView la_numberRooms;
    private TextView la_totalAmount;

    private String TOTAL_PRICE;
    private String TOTAL_PRICE_TAX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G15P35A13ReservRegistrationStep43Finish------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g15_p35_reservation_finish);

        //Default Configuration
        InitialSetupConfiguration();

        //Get Data From G14P18
       GetDataFrom_G14P18();

        //SetupToView
        SetupToViewDefault();

        //Execute Json Parser
        //GoToJSonParsar();

        //Back To Home Page
        BackToHomePage();

        //Setup To View
        SetupToView();

        //Back To Reservation Update Page
        BackToUpdatePage();


    }

    private void SetupToParcel() {
        obj_g01.setPageFlag("G15P35");
    }

    private void BackToUpdatePage() {
        Button button = (Button)findViewById(R.id.g14_p35_confirm_page);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G16P21A14ReservListView_RCED_1.class,ComMsg.COD_BACK);
            }


        });
    }

    private void BackToHomePage() {
       Button button = (Button)findViewById(R.id.g14p18_back);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class,ComMsg.COD_BACK);
            }
        });
    }

    private void SetupToViewDefault() {
        if(!CHECK_IN_DATE.isEmpty()) {
            la_checkInDate.setText(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        }
        if(!CHECK_OUT_DATE.isEmpty()) {
            la_checkOutDate.setText(ComLib.dateConvertFormattedDate(CHECK_OUT_DATE));
        }

        if(!NUMBER_OF_ROOM.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(NUMBER_OF_ROOM);
            sb.append("部屋");
            la_numberRooms.setText(sb.toString());
        }
        if(!TOTAL_PRICE_TAX.isEmpty() && !TOTAL_PRICE.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(TOTAL_PRICE);
            sb.append(" (");
            sb.append(TOTAL_PRICE_TAX);
            sb.append(")");
            la_totalAmount.setText(sb.toString());
        }

    }

    private void SetupToView() {
        int maxRooms = Integer.valueOf(NUMBER_OF_ROOM);
        for(int j=0;j<maxRooms;j++){
            String reservationNumber = new String();
            if(LS_RSRV_NMBR.size()!=0){
                reservationNumber = LS_RSRV_NMBR.get(j).toString();
            }
            DynamicCheckinLayout(reservationNumber,j,maxRooms);
        }
    }

    private void InitialSetupConfiguration() {

        this.LS_RSRV_NMBR = new ArrayList<String>();
        this.TOTAL_PRICE = new String();
        this.TOTAL_PRICE_TAX = new String();
        this.NUMBER_OF_ROOM= new String();

        this.parent = (LinearLayout) findViewById(R.id.g15_p35_reservation_number);
        this.la_checkInDate = (TextView)findViewById(R.id.g15_p35_checkindate);
        this.la_checkOutDate = (TextView)findViewById(R.id.g15_p35_checkout_date);
        this.la_numberRooms = (TextView)findViewById(R.id.g15_p35_number_rooms);
        this.la_totalAmount = (TextView)findViewById(R.id.g15_35_total_amount);
    }


    private void GetDataFrom_G14P18() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if(!obj_g01.getLsRsrv_nmbr().isEmpty()){
                LS_RSRV_NMBR = obj_g01.getLsRsrv_nmbr();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

            if(!obj_g01.getRdTotalPrice().isEmpty()){
                TOTAL_PRICE = obj_g01.getRdTotalPrice();
            }

            if(!obj_g01.getRdTotalPriceTax().isEmpty()){
                TOTAL_PRICE_TAX = obj_g01.getRdTotalPriceTax();
            }

            obj_g01.setRdTotalPriceTax(TOTAL_PRICE_TAX);

        }
    }

    private void DynamicCheckinLayout(String str,int i , int max) {

        int paddingBottom = -3;
        if(i==max-1){
            paddingBottom = 0;
        }

        LinearLayout subParent = new LinearLayout(G15P35A13ReservRegistrationStep43Finish.this);
        LinearLayout.LayoutParams ps1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        ps1.setMargins(0, 0, 0, paddingBottom);
        subParent.setOrientation(LinearLayout.HORIZONTAL);
        subParent.setBackgroundResource(R.drawable.util_com_background_1);
        subParent.setLayoutParams(ps1);
        subParent.setPadding(10, 5, 10, 5);
        subParent.setGravity(Gravity.CENTER_VERTICAL);
        parent.addView(subParent);

/*        TextView textImg = new TextView(G15P35A13ReservRegistrationStep43Finish.this);
        LinearLayout.LayoutParams ps2 = new LinearLayout.LayoutParams(8, ActionBar.LayoutParams.WRAP_CONTENT);
        ps2.setMargins(0, 0, 5, 0);
        textImg.setBackgroundColor(Color.BLUE);
        textImg.setTextColor(Color.BLACK);
        textImg.setLayoutParams(ps2);
        textImg.setTextSize(18);
        subParent.addView(textImg);*/

        ComActivity.Cmm_BlueBar(getApplicationContext(),subParent);


        StringBuilder sb_1 = new StringBuilder();
        sb_1.append("部屋");
        sb_1.append(i+1);
        sb_1.append(" ");
        sb_1.append("予約番号");

        TextView col1 = new TextView(G15P35A13ReservRegistrationStep43Finish.this);
        LinearLayout.LayoutParams ps3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        ps3.setMargins(0, 0, 30, 0);
        col1.setText(sb_1);
        col1.setTextSize(15);
        col1.setPadding(10, 10, 10, 10);
        col1.setLayoutParams(ps3);
        col1.setTextColor(Color.BLACK);
        subParent.addView(col1);

        TextView col2 = new TextView(G15P35A13ReservRegistrationStep43Finish.this);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        col2.setText(str);
        col2.setTextSize(15);
        col2.setPadding(10, 10, 10, 10);
        col2.setLayoutParams(p4);
        col2.setTextColor(Color.BLACK);
        subParent.addView(col2);
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
}
