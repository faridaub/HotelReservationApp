package toyoko.inn.com.smartphoneappplus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G09P13A00AccommodationDateBackupPaer2 extends Activity {

    private G01P01ParcelableData obj_g01;
    private CalendarView la_calendar;
    private TextView la_title;

    private TextView la_xmonth;
    private TextView la_xday;
    //private TextView la_xyear;


    private Long calenderDate;
    private int cYear;
    private int cMonth;
    private int cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G09P13A00AccommodationDateBackupPaer2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupc_calendar_original);

        initialSetupConfiguration();

        GetData();

        SetupToView();

        getCurrentDate();

        SetFormattedDate();



    }

    private void GetData() {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

                if(obj_g01.getCalenderDate()!=0L){
                    calenderDate = obj_g01.getCalenderDate();
                }

            }
    }

    private void initialSetupConfiguration() {
        this.la_calendar = (CalendarView) findViewById(R.id.popupc_calendar);
        this.la_title = (TextView)findViewById(R.id.popupc_title);
        this.la_xmonth = (TextView)findViewById(R.id.popupc_xmonth);
        this.la_xday= (TextView)findViewById(R.id.popupc_xday);
        calenderDate = 0L;
    }

    private void getCurrentDate() {
        Calendar c = Calendar.getInstance();
        this.cYear = c.get(Calendar.YEAR);
        this.cMonth = c.get(Calendar.MONTH);
        this.cDay = c.get(Calendar.DAY_OF_MONTH);
    }

    private void SetupToParcel() {
        obj_g01.setCalenderDate(calenderDate);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void SetupToView() {

        la_title.setText("チェックイン");

        //Calender Conf
        if(calenderDate==0L) {
            calenderDate = la_calendar.getDate();
        }

        la_calendar.setShowWeekNumber(true);
/*
        la_calendar.setFirstDayOfWeek(Calendar.MONDAY);
        la_calendar.setFocusedMonthDateColor(Color.BLACK);
        la_calendar.setUnfocusedMonthDateColor(Color.YELLOW);
*/

        la_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G09P13A00AccommodationDateBackupPaer1.class,ComMsg.COD_NORMAL);
            }
        });

        //Press Action
        la_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                cDay = day;
                cMonth = month;
                cYear = year;
                calenderDate = la_calendar.getDate();
                SetFormattedDate();
                SetupToParcel();
                goTo(G09P13A00AccommodationDateBackupPaer1.class,ComMsg.COD_NORMAL);
            }
        });
    }


    private void SetFormattedDate() {
       //Month Formet
        SimpleDateFormat mformet = new SimpleDateFormat("yyyy年M月");
        String monthTitle= mformet.format(calenderDate);
        la_xmonth.setText(monthTitle);

        //Day Formet
        SimpleDateFormat dformet = new SimpleDateFormat("dd");
        String dayTitle= dformet.format(calenderDate);
        la_xday.setText(dayTitle);
    }


    //Set Date To CalenderView
    private void SetDateToCalenderView() {
        Calendar c = Calendar.getInstance();
        c.set(cYear, cMonth, cDay);
        long milliTime = c.getTimeInMillis();
        la_calendar.setDate (milliTime, true, true);

        Calendar c2 = Calendar.getInstance();
        c2.set(2015, 2, 13);
        long milliTime2 = c2.getTimeInMillis();
        la_calendar.setDate (milliTime2, true, true);
        //la_calendar.
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
