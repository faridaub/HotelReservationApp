package toyoko.inn.com.smartphoneappplus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

public class G09P13A00AccommodationDate extends Activity implements OnClickListener {

    //UI References
    private G01P01ParcelableData obj_g01;
    private String CHECKINDATE;
    private String CHECKOUTDATE;
    private String NUMBER_OF_DAYS;

    private EditText la_fromDate;
    private EditText la_toDate;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter1;
    private SimpleDateFormat dateFormatter2;
    private TextView la_numburDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G09P13A00AccommodationDate------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g09_p13_acc_date);

        SetupDefaultConfiguration();

        GetData();

        SetupToView();

        GoToConditionSearch();

        BackToSearchByConditionPage();
    }

    private void BackToSearchByConditionPage() {
        Button button = (Button)findViewById(R.id.g01_p13_bk_toppage);
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
        }
    }

    private void SetupToParcel() {
        obj_g01.setCheckinDate(CHECKINDATE);
        obj_g01.setCheckoutDate(CHECKOUTDATE);
        obj_g01.setNumberOfStayNight(NUMBER_OF_DAYS);
    }

    private void GoToConditionSearch() {
        Button button = (Button)findViewById(R.id.g09_p13_take_dicition);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G06P09A00SearchByCondition.class, "back");
            }
        });
    }

    private void SetupDefaultConfiguration() {
        this.CHECKINDATE = new String();
        this.CHECKOUTDATE = new String();
        this.NUMBER_OF_DAYS = new String();

        dateFormatter1 = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
        dateFormatter2 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        la_fromDate = (EditText) findViewById(R.id.g09_p13_check_in_date);
        la_toDate = (EditText) findViewById(R.id.g09_p13_check_out_date);
        la_numburDays = (TextView)findViewById(R.id.g09_p13_number_of_night);
    }


    private void SetupToView() {

        la_fromDate.setOnClickListener(this);
        la_toDate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();

        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                la_fromDate.setText(dateFormatter1.format(newDate.getTime()));
                CHECKINDATE = dateFormatter2.format(newDate.getTime());
                SetNumberDays();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                la_toDate.setText(dateFormatter1.format(newDate.getTime()));
                CHECKOUTDATE = dateFormatter2.format(newDate.getTime());
                SetNumberDays();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }


    private void SetNumberDays() {

        if(!CHECKINDATE.isEmpty() && !CHECKOUTDATE.isEmpty()){
            NUMBER_OF_DAYS = ComLib.dateDateBetweenDates(CHECKINDATE, CHECKOUTDATE);
            this.la_numburDays.setText(NUMBER_OF_DAYS);
        }
    }


    @Override
    public void onClick(View view) {
        if (view == la_fromDate) {
            fromDatePickerDialog.show();
        } else if (view == la_toDate) {
            toDatePickerDialog.show();
        }
    }

    private void errorPopup(String eCode, String eMessage) {
        StringBuilder sb = new StringBuilder();
        if (eCode != null) {
            sb.append("[" + eCode + "]");
        }
        sb.append("エラー");

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(sb);
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