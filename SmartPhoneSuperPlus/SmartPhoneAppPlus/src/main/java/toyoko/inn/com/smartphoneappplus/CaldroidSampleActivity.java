package toyoko.inn.com.smartphoneappplus;

/*
Develop By : Farid Anamul Haq
Project Note : Now i have to create
custome start end loop to select each day
fragment
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.getButtonONOFF;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateReturnStringToDate;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivity extends FragmentActivity {
    G01P01ParcelableData obj_g01;
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private int counter = 0;

    private Date firsttimeDate = new Date();
    private Date startDate = new Date();
    private Date endDate = new Date();
    private Date countDate = new Date();
    private SimpleDateFormat dateFormatter1;
    private SimpleDateFormat dateFormatter2;
    private TextView la_checkindate;
    private TextView la_checkoutdate;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_NIGHT;
    private TextView la_number_day;
    private int dayCounter;
    private  Button addition;
    private  Button deduction;
    ArrayList<Date> cleanOldData = new ArrayList<Date>();

    private String OldCheckInDate = new String();
    private String OldCheckOutDate = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATA PAGE", "------------------------------------CaldroidSampleActivity------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_caldroidsample);

        //Setup Initial Configuration
        SetupToConfiguration();

        //Get data
        GetData();

        //Setup Saved View
        SetupToView(savedInstanceState);

        //Setup Number Of Days
        SetupNights();

        //Back To Search By Condition Page
        BackTo();

        //Set and Back
        GoToSearchByCondigion();

    }

    private void GoToSearchByCondigion() {
        Button button = (Button)findViewById(R.id.cal_setupandback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G06P09A00SearchByCondition.class, COD_BACK);
            }
        });
    }

    private void BackTo() {
        Button button = (Button)findViewById(R.id.cal_toppage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G06P09A00SearchByCondition.class, COD_BACK);
            }
        });
    }


    private void SetupToConfiguration() {
        this.dateFormatter1 = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
        this.dateFormatter2 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);

        this.la_checkindate = (TextView) findViewById(R.id.checkindate);
        this.la_checkoutdate = (TextView) findViewById(R.id.checkoutdate);
        this.la_number_day = (TextView) findViewById(R.id.bx_numberofdays);
        this.addition = (Button)findViewById(R.id.bx_plus);
        this.deduction =(Button)findViewById(R.id.bx_minus);
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_NIGHT = new String();

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);
            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getCheckoutDate().isEmpty()) {
                CHECK_OUT_DATE = obj_g01.getCheckoutDate();
            }

        }
    }


    private void SetupToView(Bundle savedInstanceState) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        caldroidFragment = new CaldroidFragment();
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        }
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
            args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.SUNDAY); // Tuesday
            caldroidFragment.setArguments(args);
        }

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.caldroidcalendar1, caldroidFragment);
        t.commit();

        FillFregmentAndSetupCheckinCheckout(CHECK_IN_DATE, CHECK_OUT_DATE);

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                CheckEligibleDates(date);
            }

            public void CheckEligibleDates(Date clickedDate) {
                final SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
                Calendar cal = Calendar.getInstance();
                String currentDate = fm.format(cal.getTime());
                String dateCllicked = fm.format(clickedDate);

                //Set Checkin Date
                if (!dateCllicked.isEmpty() && counter == 0 && dateAfterCurrentDateAndEqual(currentDate, dateCllicked)) {
                    CHECK_IN_DATE = dateCllicked;
                    CHECK_OUT_DATE = "";
                    counter = 1;

                    FillFregmentAndSetupCheckinCheckout(CHECK_IN_DATE, CHECK_OUT_DATE);
                    OldCheckInDate = CHECK_IN_DATE;
                }

                //Set CheckOut Date
                else if (!dateCllicked.isEmpty() && dateAfterCurrentDate(CHECK_IN_DATE, dateCllicked)) {
                    CHECK_OUT_DATE = dateCllicked;
                    counter = 0;
                    FillFregmentAndSetupCheckinCheckout(CHECK_IN_DATE, CHECK_OUT_DATE);
                    OldCheckOutDate = CHECK_OUT_DATE;
                }
            }

            @Override
            public void onChangeMonth(int month, int year) {
            }

            @Override
            public void onLongClickDate(Date date, View view) {
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                }
            }

        };
        caldroidFragment.setCaldroidListener(listener);
    }


    private void SetupToParcel() {
        obj_g01.setCheckinDate(CHECK_IN_DATE);
        obj_g01.setCheckoutDate(CHECK_OUT_DATE);
        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
    }

    /**
     * Save current states of the Caldroid here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }

        if (dialogCaldroidFragment != null) {
            dialogCaldroidFragment.saveStatesToKey(outState,
                    "DIALOG_CALDROID_SAVED_STATE");
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


    private void FillFregmentAndSetupCheckinCheckout(String checkinDate, String checkoutDate) {
        CHECK_IN_DATE   = checkinDate;
        CHECK_OUT_DATE  = checkoutDate;

        if(CHECK_IN_DATE.isEmpty()){
            CHECK_IN_DATE =  dateGetCurrentDate();
        }
        if(CHECK_OUT_DATE.isEmpty()){
            CHECK_OUT_DATE  = dateSameDataPlus(checkinDate, ST_ONE);
        }
        NUMBER_OF_NIGHT = dateNumberOfDaysFromTwoDate(CHECK_IN_DATE,CHECK_OUT_DATE);
        if(Integer.valueOf(NUMBER_OF_NIGHT)>7){
            NUMBER_OF_NIGHT =ST_SEVEN;
            CHECK_OUT_DATE  = dateSameDataPlus(CHECK_IN_DATE, ST_SEVEN);
        }
        la_checkindate.setText(dateReturnFormettedDate(CHECK_IN_DATE));
        la_checkoutdate.setText(dateReturnFormettedDate(CHECK_OUT_DATE));
        SetDataToView(CHECK_IN_DATE, NUMBER_OF_NIGHT);
        getButtonONOFF(addition, deduction, Integer.valueOf(NUMBER_OF_NIGHT), 7, 1);

    }

    private void SetDataToView(String checkinDate, String numberNights){
        CHECK_IN_DATE   = checkinDate;
        NUMBER_OF_NIGHT =  numberNights;
        dayCounter = Integer.valueOf(NUMBER_OF_NIGHT);
        la_number_day.setText(NUMBER_OF_NIGHT);
        CHECK_OUT_DATE = dateSameDataPlus(CHECK_IN_DATE, NUMBER_OF_NIGHT);
        fillDateToFragment(CHECK_IN_DATE,CHECK_OUT_DATE);
        la_checkindate.setText(dateReturnFormettedDate(CHECK_IN_DATE));
        la_checkoutdate.setText(dateReturnFormettedDate(CHECK_OUT_DATE));
    }

    private void fillDateToFragment(String checkinDate, String checkoutDate) {
        if(cleanOldData.size()>0){
            for(int j =0;j<cleanOldData.size();j++){
                caldroidFragment.setBackgroundResourceForDate(R.color.white, cleanOldData.get(j));
            }
            cleanOldData.clear();
        }
        ArrayList<Date> dateFillArray = dateReturnArrayListFromTwoDate(checkinDate, checkoutDate);
        caldroidFragment.refreshView();
        for(int j =0;j<dateFillArray.size();j++){
            if(j==0) {
                caldroidFragment.setBackgroundResourceForDate(R.drawable.util_gra_creem_circle_2, dateFillArray.get(j));
            }else if(j==dateFillArray.size()-1) {
                caldroidFragment.setBackgroundResourceForDate(R.drawable.util_gra_creem_circle_2, dateFillArray.get(j));
            }else{
                caldroidFragment.setBackgroundResourceForDate(R.drawable.util_gra_creem_circle_1, dateFillArray.get(j));
            }
        }
        cleanOldData  = dateFillArray;
    }



    private void SetupNights() {
        getButtonONOFF(addition, deduction, dayCounter, 7, 1);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                dayCounter++;
                getButtonONOFF(addition, deduction, dayCounter, 7, 1);
                NUMBER_OF_NIGHT = String.valueOf(dayCounter);
               SetDataToView(CHECK_IN_DATE, NUMBER_OF_NIGHT);
            }
        });


        deduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                dayCounter--;
                getButtonONOFF(addition, deduction, dayCounter, 7, 1);
                NUMBER_OF_NIGHT = String.valueOf(dayCounter);
                SetDataToView(CHECK_IN_DATE, NUMBER_OF_NIGHT);
            }
        });
    }

}
