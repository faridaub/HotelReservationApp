package toyoko.inn.com.smartphoneappplus.common;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.R;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

public class ComReservRegisActivity extends Activity {
    //Object
    G01P01ParcelableData obj_g01;
    TextView la_error_message;
    private String ERROR_MESSAGE;
    private String ERROR_CODE;
    private TextView la_error_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Reservation Registration MustItem
    public static void Cmm_MustItem(Context context, LinearLayout parentLayout) {
        TextView mustItem = new TextView(context);
        LinearLayout.LayoutParams mstItm = new LinearLayout.LayoutParams(60, ActionBar.LayoutParams.WRAP_CONTENT);
        mstItm.setMargins(0, 0, 5, 0);
        mustItem.setText("必須");
        mustItem.setGravity(Gravity.CENTER);
        mustItem.setBackgroundColor(Color.RED);
        mustItem.setTextColor(Color.WHITE);
        mustItem.setLayoutParams(mstItm);
        parentLayout.addView(mustItem);
    }

    //Reservation Registration Must Item With Space
    public static void Cmm_MustItemSpaceRight(Context context, LinearLayout parentLayout) {
        TextView mustItem = new TextView(context);
        LinearLayout.LayoutParams mstItm = new LinearLayout.LayoutParams(60, ActionBar.LayoutParams.WRAP_CONTENT);
        mstItm.weight = 1f;
        mstItm.setMargins(0, 0, 5, 0);
        mustItem.setGravity(Gravity.CENTER);
        mustItem.setTextColor(Color.WHITE);
        mustItem.setLayoutParams(mstItm);
        parentLayout.addView(mustItem);
    }

    //Reservation Registration Headline
    public static void ReservCommonHeadline(Context context, LinearLayout parentLayout, String titleName) {
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_headline_1);
        layout1.setLayoutParams(param1);
        parentLayout.addView(layout1);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText(titleName);
        text1.setTextSize(15);
        text1.setSingleLine(true);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }

    //Reservation Checking and Checkout Field
    public static void ReservCommonFields(Context context, LinearLayout parentLayout, String textLevel, String textValue) {
        LinearLayout checkinLayout = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        checkinLayout.setOrientation(LinearLayout.HORIZONTAL);
      //  checkinLayout.setBackgroundResource(R.drawable.util_com_background_1);
        checkinLayout.setLayoutParams(p1);
        checkinLayout.setPadding(10, 5, 10, 5);
        checkinLayout.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(checkinLayout);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        col1.setText(textLevel);
        col1.setTextSize(15);
        col1.setPadding(10, 10, 10, 10);
        col1.setLayoutParams(p3);
        col1.setTextColor(Color.BLACK);
        checkinLayout.addView(col1);

        TextView col2 = new TextView(context);
        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p4.setMargins(10, 10, 10, 10);
        col2.setText(textValue);
        col2.setTextSize(15);
        col2.setPadding(10, 10, 10, 10);
        col2.setLayoutParams(p4);
        col2.setTextColor(Color.BLACK);
        checkinLayout.addView(col2);
    }

    public static void ReservCommonHints(Context context, LinearLayout parentLayout, String textHints) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 10, 10, 10);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        text1.setText(textHints);
        text1.setTextSize(10);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p3);
        text1.setTextColor(Color.BLUE);
        layout.addView(text1);
    }

    public static void ReservCommonHintsWithoutBackground(Context context, LinearLayout parentLayout, String textHints) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 0);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(p1);
        layout.setPadding(10, 5, 10, 5);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p3.weight = 1f;
        p3.setMargins(0, 0, 5, 0);
        text1.setText(textHints);
        text1.setTextSize(10);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p3);
        text1.setTextColor(Color.BLUE);
        layout.addView(text1);
    }



}
