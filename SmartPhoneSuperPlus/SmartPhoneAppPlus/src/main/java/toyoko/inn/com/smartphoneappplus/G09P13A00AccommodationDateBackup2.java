package toyoko.inn.com.smartphoneappplus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G09P13A00AccommodationDateBackup2 extends Activity {
    CalendarView calendar;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private TextView la_checkin_textview;
    private TextView la_checkout_textview;
    private String NUMBER_OF_NIGHT;
    private TextView la_number_of_nights;
    private G01P01ParcelableData obj_g01;
    private Button la_take_dicition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G09P13A00AccommodationDateBackup2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g09_p13_accommodation_date_backup2);

    /*    CalendarView cal = (CalendarView) findViewById(R.id.calendarView1);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                Toast.makeText(getBaseContext(), "Selected Date is\n\n"
                                + dayOfMonth + " : " + month + " : " + year,
                        Toast.LENGTH_LONG).show();
            }
        });*/



        //Setup
        initialSetupConfiguration();

        //initializes the calendarview
        initializeCalendar();

        //GoBack
        G09P13_BackTo_G06P09();

    }

    private void G09P13_BackTo_G06P09() {
        la_take_dicition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G06P09A00SearchByCondition.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G09P13A00AccommodationDateBackup2.this.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);

            }
        });

    }

    private void ReloadBeforeAction(){
        if(!CHECK_IN_DATE.isEmpty()) {
            la_checkin_textview.setText(ComLib.dateConvertFormattedDate(CHECK_IN_DATE));
        }

        if(!CHECK_OUT_DATE.isEmpty()) {
            la_checkout_textview.setText(ComLib.dateConvertFormattedDate(CHECK_OUT_DATE));
        }

        if(!CHECK_IN_DATE.isEmpty() && !CHECK_OUT_DATE.isEmpty()) {
            NUMBER_OF_NIGHT = ComLib.dateDateBetweenDates(CHECK_IN_DATE, CHECK_OUT_DATE);
            la_number_of_nights.setText(NUMBER_OF_NIGHT);
        }

        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);

    }

    private void initialSetupConfiguration() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
        }

        CHECK_IN_DATE = new String();
        CHECK_OUT_DATE = new String();
        NUMBER_OF_NIGHT = new String();

        la_checkin_textview = (TextView)findViewById(R.id.g09_p13_check_in_date);
        la_checkout_textview = (TextView)findViewById(R.id.g09_p13_check_out_date);
        la_number_of_nights     =(TextView)findViewById(R.id.g09_p13_number_of_night);
        la_take_dicition    =(Button)findViewById(R.id.g09_p13_take_dicition);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.g09p13_calendarView1);


        //


        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the fir    st day of the Calendar
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //calendar.setWeekDayTextAppearance(1);

        //The background color for the selected week.
        //  calendar.setSelectedWeekBackgroundColor(R.color.green);

        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        //calendar.setSelectedDateVerticalBar(Color.WHITE);

        calendar.setUnfocusedMonthDateColor(getApplicationContext().getResources().getColor(R.color.transparent));

        calendar.setWeekSeparatorLineColor(getApplicationContext().getResources().getColor(R.color.transparent));




        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                calendar.setSelected(true);
                calendar.setDate(view.getDate());
                calendar.setMaxDate(view.getMaxDate());

                if(CHECK_IN_DATE.isEmpty()) {
                    CHECK_IN_DATE = String.valueOf(year)+String.valueOf(month)  + ComLib.setLpad(String.valueOf(day));
                }else {
                    CHECK_OUT_DATE = String.valueOf(year)+String.valueOf(month) + ComLib.setLpad(String.valueOf(day));
                }

              //  ReloadBeforeAction();

                Toast.makeText(getApplicationContext(), year +  "年" +  month + "月" + day + "日"  , Toast.LENGTH_LONG).show();
            }
        });
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

    //goTo
    //----------------------------------------------------------------------------------------------
    private void goTo(final Class myClass, String forwordState) {
        Intent intent = new Intent(getApplicationContext(),myClass);
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
}
