package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

//public class G09P13A00AccommodationDate extends Activity implements OnClickListener {
public class G09P13A00AccommodationDateBackupPaer1 extends Activity {
    //UI References
    private G01P01ParcelableData obj_g01;
    private String CHECKINDATE;
    private String CHECKOUTDATE;
    private String NUMBER_OF_DAYS;

    private Button la_topFromCheckin;
    private Button la_topToCheckout;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter1;
    private SimpleDateFormat dateFormatter2;
   // private TextView la_numburDays;

    SimpleDateFormat commonformet;

    private long calenderDate;
    private int cYear;
    private int cMonth;
    private int cDay;

    private TextView la_bottomFromCheckin;
    private TextView la_bottomToCheckout;
    private TextView la_topNumNight;
    private  TextView la_bottomNumNight;

    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G09P13A00AccommodationDateBackupPaer1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g09_p13_acc_date_pear1);

        SetupDefaultConfiguration();

        GetData();

        SetupToView();

        GoToConditionSearch();

        BackToSearchByConditionPage();
    }

    private void LoadingToField(){
        if(!NUMBER_OF_DAYS.isEmpty()) {
            CHECKOUTDATE = ComLib.dateCurrentCheckout(NUMBER_OF_DAYS);
            la_topFromCheckin.setText(ComLib.dateCurrentCheckinFormet());
            la_topToCheckout.setText(ComLib.dateCurrentCheckoutFormet(NUMBER_OF_DAYS));
            la_bottomFromCheckin.setText(ComLib.dateCurrentCheckinFormet());
            la_bottomToCheckout.setText(ComLib.dateCurrentCheckoutFormet(NUMBER_OF_DAYS));
            la_topNumNight.setText(NUMBER_OF_DAYS);
            la_bottomNumNight.setText(NUMBER_OF_DAYS);
            counter = Integer.valueOf(NUMBER_OF_DAYS);
        }
    }

    private void Setup3NumberOfRooms() {
        final Button addition = (Button)findViewById(R.id.bt_plus);
        final Button deduction=(Button)findViewById(R.id.bt_minus);


        if(counter <2) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(R.drawable.util_off);
        }else{
            deduction.setEnabled(true);
            deduction.setBackgroundResource(R.drawable.util_on);
        }

        if(counter >7){
            addition.setEnabled(false);
            addition.setBackgroundResource(R.drawable.util_off);
        }

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter <7){

                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                }else{
                    addition.setBackgroundResource(R.drawable.util_off);
                    deduction.setBackgroundResource(R.drawable.util_on);
                    addition.setEnabled(false);
                    deduction.setEnabled(true);
                }
                NUMBER_OF_DAYS = String.valueOf(counter);
                LoadingToField();
            }
        });


        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if(counter >1) {
                    deduction.setBackgroundResource(R.drawable.util_on);
                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(true);
                    addition.setEnabled(true);
                }else{
                    deduction.setBackgroundResource(R.drawable.util_off);
                    addition.setBackgroundResource(R.drawable.util_on);
                    deduction.setEnabled(false);
                    addition.setEnabled(true);
                }
                NUMBER_OF_DAYS = String.valueOf(counter);
                LoadingToField();
            }
        });
    }


    private void BackToSearchByConditionPage() {
        Button button = (Button) findViewById(R.id.g01_p13_bk_toppage);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (obj_g01.getCalenderDate() != 0L) {
                calenderDate = obj_g01.getCalenderDate();
            }

        }
    }

    private void SetupToParcel() {
        obj_g01.setCalenderDate(calenderDate);
        obj_g01.setCheckinDate(CHECKINDATE);
        obj_g01.setCheckoutDate(CHECKOUTDATE);
        obj_g01.setNumberOfStayNight(NUMBER_OF_DAYS);

    }

    private void GoToConditionSearch() {
        Button button = (Button) findViewById(R.id.g09_p13_take_dicition);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G06P09A00SearchByCondition.class, ComMsg.COD_BACK);
            }
        });
    }

    private void SetupDefaultConfiguration() {
        this.CHECKINDATE = new String();
        this.CHECKOUTDATE = new String();
        this.NUMBER_OF_DAYS = new String();
        this.counter =0;
        this.la_topFromCheckin = (Button) findViewById(R.id.g09_p13_check_in_date);
        this.la_topToCheckout = (Button) findViewById(R.id.g09_p13_check_out_date);
      //  this.la_numburDays = (TextView) findViewById(R.id.g09_p13_number_of_night);

        this.la_bottomFromCheckin = (TextView) findViewById(R.id.tv_checkin);
        this.la_bottomToCheckout = (TextView) findViewById(R.id.tv_checkout);
        this.la_topNumNight = (TextView) findViewById(R.id.tv_numofnight);
        this.la_bottomNumNight =(TextView) findViewById(R.id.bt_numberofnight);
        this.commonformet = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
    }


    private void SetupToView() {
        if(NUMBER_OF_DAYS.isEmpty()){
            NUMBER_OF_DAYS = "1";
        }

        if(CHECKINDATE.isEmpty()){
            CHECKINDATE = ComLib.dateCurrentCheckin();
        }

        if(!NUMBER_OF_DAYS.isEmpty()) {
            CHECKOUTDATE = ComLib.dateCurrentCheckout(NUMBER_OF_DAYS);
            la_topFromCheckin.setText(ComLib.dateCurrentCheckinFormet());
            la_topToCheckout.setText(ComLib.dateCurrentCheckoutFormet(NUMBER_OF_DAYS));
            la_bottomFromCheckin.setText(ComLib.dateCurrentCheckinFormet());
            la_bottomToCheckout.setText(ComLib.dateCurrentCheckoutFormet(NUMBER_OF_DAYS));
            la_topNumNight.setText(NUMBER_OF_DAYS);
            la_bottomNumNight.setText(NUMBER_OF_DAYS);
            counter = Integer.valueOf(NUMBER_OF_DAYS);
        }

        la_topFromCheckin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                FariderrorPopup("checkin");
            }
        });


        la_topToCheckout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                FariderrorPopup("checkout");
            }
        });


        Setup3NumberOfRooms();

    }


    private void FariderrorPopup(final String flag) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupc_calendar_original);
        final LinearLayout la_layout_title = (LinearLayout)dialog.findViewById(R.id.popupc_la_title);
        final TextView la_title = (TextView) dialog.findViewById(R.id.popupc_title);
        final TextView la_xmonth = (TextView) dialog.findViewById(R.id.popupc_xmonth);
        final TextView la_xday = (TextView) dialog.findViewById(R.id.popupc_xday);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        //Press Action
        final CalendarView la_calendar = (CalendarView) dialog.findViewById(R.id.popupc_calendar);
        final Button la_yes = (Button) dialog.findViewById(R.id.popupc_ok);
        la_calendar.setShowWeekNumber(false);


        //Title
        if (flag.equalsIgnoreCase("checkin")) {
            la_title.setText("チェックイン");
            if(!CHECKINDATE.isEmpty()){
                long milliTime = ComLib.dateRegurnMilliTime(CHECKINDATE);
                la_calendar.setDate (milliTime, true, true);
            }
        }else{
            if(!CHECKOUTDATE.isEmpty()){
                long milliTime = ComLib.dateRegurnMilliTime(CHECKOUTDATE);
                la_calendar.setDate (milliTime, true, true);
            }
        }

        //Checkin Date and Checkout Date
        if (!CHECKINDATE.isEmpty() && !CHECKOUTDATE.isEmpty()) {
            NUMBER_OF_DAYS = ComLib.dateDateBetweenDates(CHECKINDATE, CHECKOUTDATE);
            StringBuilder sb = new StringBuilder();
            sb.append(ComLib.dateConvertFormattedDate(CHECKINDATE));
            sb.append("～");
            sb.append(NUMBER_OF_DAYS);
            sb.append("泊");
            la_title.setText(sb.toString());

            la_topNumNight.setText(NUMBER_OF_DAYS);
            la_bottomNumNight.setText(NUMBER_OF_DAYS);
        }else{
            NUMBER_OF_DAYS = "1";
        }

        counter = Integer.valueOf(NUMBER_OF_DAYS);
        calenderDate = la_calendar.getDate();

        //Month Formet
        final SimpleDateFormat monformet = new SimpleDateFormat("yyyy年M月");
        final String monthTitle = monformet.format(calenderDate);
        la_xmonth.setText(monthTitle);

        //Day Formet
        final SimpleDateFormat dayformet = new SimpleDateFormat("dd");
        String dayTitle = dayformet.format(calenderDate);
        la_xday.setText(dayTitle);

        la_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                SimpleDateFormat tFormet = new SimpleDateFormat("yyyyMMdd");
                calenderDate = la_calendar.getDate();

                if (flag.equalsIgnoreCase("checkin")) {
                    CHECKINDATE = tFormet.format(calenderDate);
                } else {
                    CHECKOUTDATE= tFormet.format(calenderDate);
                }

                if(!CHECKINDATE.isEmpty() && !CHECKOUTDATE.isEmpty() ) {
                    NUMBER_OF_DAYS = ComLib.dateDateBetweenDates(CHECKINDATE, CHECKOUTDATE);
                    StringBuilder sb = new StringBuilder();
                    sb.append(ComLib.dateConvertFormattedDate(CHECKINDATE));
                    sb.append("～");
                    sb.append(NUMBER_OF_DAYS);
                    sb.append("泊");
                    la_title.setText(sb.toString());
                }

                if(Integer.valueOf(NUMBER_OF_DAYS)<1 && flag.equalsIgnoreCase("checkin")){
                    NUMBER_OF_DAYS ="0";
                    CHECKOUTDATE = "";
                    Log.e("Log.e",CHECKINDATE.toString());
                    la_topToCheckout.setText("");
                    la_bottomToCheckout.setText("");

                 //   la_layout_title.setBackgroundResource(R.drawable.util_gra_red_alert);
                  //  la_title.setText("選択内容を確認してください。");
                 //   la_yes.setVisibility(View.GONE);
                }

               else if(Integer.valueOf(NUMBER_OF_DAYS)<1 && flag.equalsIgnoreCase("checkout")) {
                    la_layout_title.setBackgroundResource(R.drawable.util_gra_red_alert);
                    la_title.setText("選択内容を確認してください。");
                    la_yes.setVisibility(View.GONE);
                }
                else if(Integer.valueOf(NUMBER_OF_DAYS)>7){
                    la_layout_title.setBackgroundResource(R.drawable.util_gra_red_alert);
                    la_title.setText("選択内容を確認してください。");
                    la_yes.setVisibility(View.GONE);
                }
                else if(ComLib.dateBeforeCurrentDateCheck(CHECKINDATE,ComLib.dateCurrentCheckin())){
                    la_layout_title.setBackgroundResource(R.drawable.util_gra_red_alert);
                    la_title.setText("選択内容を確認してください。");
                    la_yes.setVisibility(View.GONE);
                }
                else {
                    la_layout_title.setBackgroundResource(R.drawable.util_com_headline_1);
                    la_yes.setVisibility(View.VISIBLE);
                }

                String monthTitle = monformet.format(calenderDate);
                la_xmonth.setText(monthTitle);

                String dayTitle = dayformet.format(calenderDate);
                la_xday.setText(dayTitle);
            }
        });



        la_yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sformet = new SimpleDateFormat("yyyy/MM/dd(E)");
                String selectedDate = sformet.format(calenderDate);

                if(Integer.valueOf(NUMBER_OF_DAYS)>=0){
                    if (flag.equalsIgnoreCase("checkin")) {
                        la_topFromCheckin.setText(selectedDate);
                        la_bottomFromCheckin.setText(selectedDate);
                    } else {
                        la_topToCheckout.setText(selectedDate);
                        la_bottomToCheckout.setText(selectedDate);
                    }

                    counter = Integer.valueOf(NUMBER_OF_DAYS);
                    la_topNumNight.setText(NUMBER_OF_DAYS);
                    la_bottomNumNight.setText(NUMBER_OF_DAYS);

                    if(CHECKINDATE.isEmpty()) {
                        SimpleDateFormat initD = new SimpleDateFormat("yyyyMMdd");
                        CHECKINDATE = initD.format(calenderDate);
                    }
                    dialog.dismiss();
                }
            }
        });


        Button no = (Button) dialog.findViewById(R.id.popupc_no);
        no.setOnClickListener(new OnClickListener() {
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
        no.setOnClickListener(new OnClickListener() {
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