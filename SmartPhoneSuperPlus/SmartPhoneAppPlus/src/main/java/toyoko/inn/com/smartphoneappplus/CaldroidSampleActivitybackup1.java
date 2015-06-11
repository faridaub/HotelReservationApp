package toyoko.inn.com.smartphoneappplus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_ONE;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateAfterCurrentDate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateAfterCurrentDateAndEqual;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateReturnStringToDate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateSameDataPlus;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivitybackup1 extends FragmentActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private int counter = 0;

    private Date firsttimeDate = new Date();
    private Date startDate = new Date();
    private Date endDate = new Date();
    private Date countDate = new Date();
    String ftDate           = new String();
    String enDate           = new String();



    /*  private void setCustomResourceForDates() {
          Calendar cal = Calendar.getInstance();

          // Min date is last 7 days
          cal.add(Calendar.DATE, -18);
          Date blueDate = cal.getTime();

          // Max date is next 7 days
          cal = Calendar.getInstance();
          cal.add(Calendar.DATE, 16);
          Date greenDate = cal.getTime();


          if (caldroidFragment != null) {
              caldroidFragment.setBackgroundResourceForDate(R.color.blue,
                      blueDate);
              caldroidFragment.setBackgroundResourceForDate(R.color.green,
                      greenDate);
              caldroidFragment.setTextColorForDate(R.color.white, blueDate);
              caldroidFragment.setTextColorForDate(R.color.white, greenDate);
          }

      }
  */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_caldroidsamplebackup);



        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY); // Tuesday

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            caldroidFragment.setArguments(args);
        }

        // setCustomResourceForDates();


        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.caldroidcalendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();

                CheckEligibleDates(date);

  /*              //If not same
                if (date.compareTo(countDate) != 0 && (date.compareTo(CaldroidSampleActivity.this.currentSystemDate) !=-1 || currentSystemDate.equalsIgnoreCase(clickDate))) {
                    countDate = date;
                    counter = counter + 1;
                    caldroidFragment.setBackgroundResourceForDate(R.color.green, date);
                    caldroidFragment.setTextColorForDate(R.color.white, date);
                    caldroidFragment.refreshView();
                } else {
                    caldroidFragment.setBackgroundResourceForDate(R.color.white, date);
                    caldroidFragment.setTextColorForDate(R.color.black, date);
                    caldroidFragment.refreshView();
                    countDate = new Date();
                }


                //If first clicked Date and Previous Date is Not Same
                if (date.compareTo(countDate) != 0){

                 }&& (date.compareTo(countDate) != -1 || currentDate.equalsIgnoreCase(clickedDate))) {
                    countDate = date;
                    counter = counter + 1;
                    caldroidFragment.setBackgroundResourceForDate(R.color.green, date);
                    caldroidFragment.setTextColorForDate(R.color.white, date);
                    caldroidFragment.refreshView();


                } else {
                    caldroidFragment.setBackgroundResourceForDate(R.color.white, date);
                    caldroidFragment.setTextColorForDate(R.color.black, date);
                    caldroidFragment.refreshView();
                    countDate = new Date();
                }

                */

            }

            public void CheckEligibleDates(Date clickedDate) {
                final SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
                Calendar cal = Calendar.getInstance();
                String currentDate = fm.format(cal.getTime());
                String dateCllicked = fm.format(clickedDate);


                //First Time when Clicked and No Counter Started
                if (!dateCllicked.isEmpty() && counter == 0 && dateAfterCurrentDateAndEqual(currentDate, dateCllicked)) {
                    ftDate = dateCllicked;
                    enDate = dateSameDataPlus(ftDate, ST_ONE);
                    counter = 1;
                    fillFragment(dateReturnStringToDate(ftDate),dateReturnStringToDate(enDate));
                }

                else if (!dateCllicked.isEmpty() && dateAfterCurrentDate(ftDate, dateCllicked)) {
                    enDate = dateCllicked;
                    counter = 0;
                    fillFragment(dateReturnStringToDate(ftDate),dateReturnStringToDate(enDate));
                }
                //If Start Date Only End Date is Emapy
/*                else if (!ftDate.isEmpty() && enDate.isEmpty()) {
                    enDate = dateCllicked;
                    counter = 0;
                    fillFragment(dateReturnStringToDate(ftDate),dateReturnStringToDate(enDate));
                }*/
                else {
                    ftDate = currentDate;
                    enDate = dateSameDataPlus(ftDate, ST_ONE);
                    fillFragment(dateReturnStringToDate(ftDate),dateReturnStringToDate(enDate));
                 //   ftDate = null;
                }
            }



            private void fillFragment(Date fromDate,Date toDate) {
                    caldroidFragment.setSelectedDates(fromDate, toDate);
                    //caldroidFragment.setBackgroundResourceForDate(R.color.blue, clickedDate);
                    //caldroidFragment.setTextColorForDate(R.color.white, clickedDate);
                    caldroidFragment.refreshView();

            }


            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        final TextView textView = (TextView) findViewById(R.id.textview);

        final Button customizeButton = (Button) findViewById(R.id.customize_button);

        // Customize the calendar
        customizeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (undo) {
                    customizeButton.setText("customize");
                    textView.setText("");

                    // Reset calendar
                    caldroidFragment.clearDisableDates();
                    caldroidFragment.clearSelectedDates();
                    caldroidFragment.setMinDate(null);
                    caldroidFragment.setMaxDate(null);
                    caldroidFragment.setShowNavigationArrows(true);
                    caldroidFragment.setEnableSwipe(true);
                    caldroidFragment.refreshView();
                    undo = false;
                    return;
                }

                // Else
                undo = true;
                customizeButton.setText("customize");
                Calendar cal = Calendar.getInstance();

                // Min date is last 7 days
                cal.add(Calendar.DATE, -7);
                Date minDate = cal.getTime();

                // Max date is next 7 days
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 14);
                Date maxDate = cal.getTime();

                // Set selected dates
                // From Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 2);
                Date fromDate = cal.getTime();

                // To Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 3);
                Date toDate = cal.getTime();

                // Set disabled dates
                ArrayList<Date> disabledDates = new ArrayList<Date>();
                for (int i = 5; i < 8; i++) {
                    cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, i);
                    disabledDates.add(cal.getTime());
                }

                // Customize
                caldroidFragment.setMinDate(minDate);
                caldroidFragment.setMaxDate(maxDate);
                caldroidFragment.setDisableDates(disabledDates);
                caldroidFragment.setSelectedDates(fromDate, toDate);
                caldroidFragment.setShowNavigationArrows(false);
                caldroidFragment.setEnableSwipe(false);

                caldroidFragment.refreshView();

                // Move to date
                // cal = Calendar.getInstance();
                // cal.add(Calendar.MONTH, 12);
                // caldroidFragment.moveToDate(cal.getTime());

                String text = "Today: " + formatter.format(new Date()) + "\n";
                text += "Min Date: " + formatter.format(minDate) + "\n";
                text += "Max Date: " + formatter.format(maxDate) + "\n";
                text += "Select From Date: " + formatter.format(fromDate)
                        + "\n";
                text += "Select To Date: " + formatter.format(toDate) + "\n";
                for (Date date : disabledDates) {
                    text += "Disabled Date: " + formatter.format(date) + "\n";
                }

                textView.setText(text);
            }
        });

        Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);

        final Bundle state = savedInstanceState;
        showDialogButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Setup caldroid to use as dialog
                dialogCaldroidFragment = new CaldroidFragment();
                dialogCaldroidFragment.setCaldroidListener(listener);

                // If activity is recovered from rotation
                final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
                if (state != null) {
                    dialogCaldroidFragment.restoreDialogStatesFromKey(
                            getSupportFragmentManager(), state,
                            "DIALOG_CALDROID_SAVED_STATE", dialogTag);
                    Bundle args = dialogCaldroidFragment.getArguments();
                    if (args == null) {
                        args = new Bundle();
                        dialogCaldroidFragment.setArguments(args);
                    }
                } else {
                    // Setup arguments
                    Bundle bundle = new Bundle();
                    // Setup dialogTitle
                    dialogCaldroidFragment.setArguments(bundle);
                }

                dialogCaldroidFragment.show(getSupportFragmentManager(),
                        dialogTag);
            }
        });
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

}
