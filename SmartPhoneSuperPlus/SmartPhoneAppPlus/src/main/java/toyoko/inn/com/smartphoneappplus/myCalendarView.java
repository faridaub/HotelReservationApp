package toyoko.inn.com.smartphoneappplus;

/**
 * Created by Farid on 2015/02/12.
 */

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.content.Intent;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.common.ComMsg;

public class myCalendarView extends Activity {

    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        errorPopup(null,"hello");
    }

    private void errorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(ComMsg.getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.json_error_message);
        la_errorMsg.setText(eMessage);

        CalendarView calendar = new CalendarView(this);
        calendar.setOnDateChangeListener(mDateSetListener);
        setContentView(calendar);

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

    // the callback received when the user "sets" the date in the dialog
    private CalendarView.OnDateChangeListener mDateSetListener =
            new CalendarView.OnDateChangeListener() {

                public void onSelectedDayChange(CalendarView view, int year,
                                                int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    String selectedDate = new StringBuilder().append(mMonth + 1).append("-").append(mDay).append("-")
                            .append(mYear).append(" ").toString();

                    Bundle b = new Bundle();
                    b.putString("dateSelected", selectedDate);

                    //Add the set of extended data to the intent and start it
                    Intent intent = new Intent();
                    intent.putExtras(b);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            };

}