package toyoko.inn.com.smartphoneappplus.common;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.R;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.FLD_FIRST_NAME2;

public class ComActivity extends Activity {
    //Object
    G01P01ParcelableData obj_g01;
    TextView la_error_message;
    private String ERROR_MESSAGE;
    private String ERROR_CODE;
    private TextView la_error_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_server_connection);

        //==Setup Data
        //InitialSetupConfiguration();

        //Get Data From Request Pgae
        GetDataFromRequestPage();

    }

    //Button On Off state
    //----------------------------------------------------------------------------------------------
    public static void getButtonONOFF(Button addition, Button deduction, int value, int maxNumber, int minNumber) {

        int on  = R.drawable.util_on;
        int off =  R.drawable.util_off;

         if(value<=minNumber) {
            deduction.setEnabled(false);
            deduction.setBackgroundResource(off);
        }

        if(value>=maxNumber) {
            addition.setEnabled(false);
            addition.setBackgroundResource(off);
        }

        if(value>minNumber) {
            deduction.setEnabled(true);
            deduction.setBackgroundResource(on);
        }

        if(value<maxNumber) {
            addition.setEnabled(true);
            addition.setBackgroundResource(on);
        }
    }



    public static void addCross(Context context, final EditText et){
        String value = "";
        final String viewMode = "editing";
        final String viewSide = "right";
        et.setText(value);
        final Drawable x = context.getResources().getDrawable(R.drawable.ic_cross2);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        Drawable x2 = viewMode.equals("never")?null:viewMode.equals("always")?null:viewMode.equals("editing")?(value.equals("") ? null : x):viewMode.equals("unlessEditing")?(value.equals("") ? x : null):null;
        et.setCompoundDrawables(viewSide.equals("left")?x2:null, null, viewSide.equals("right")?x2:null, null);
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (et.getCompoundDrawables()[viewSide.equals("left")?0:2] == null) {
                    return false;
                }
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                //x pressed
                if ((viewSide.equals("left")&&event.getX() < et.getPaddingLeft() + x.getIntrinsicWidth())
                        ||(viewSide.equals("right")&&event.getX() > et.getWidth() - et.getPaddingRight() - x.getIntrinsicWidth())) {
                    Drawable x3 = viewMode.equals("never")?null:viewMode.equals("always")?null:viewMode.equals("editing")?null:viewMode.equals("unlessEditing")?x:null;
                    et.setText("");
                    et.setCompoundDrawables(viewSide.equals("left")?x3:null, null, viewSide.equals("right")?x3:null, null);
                }
                return false;
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Drawable x4 = viewMode.equals("never")?null:viewMode.equals("always")?null:viewMode.equals("editing")?(et.getText().toString().equals("") ? null : x):viewMode.equals("unlessEditing")?(et.getText().toString().equals("") ? x : null):null;
                et.setCompoundDrawables(viewSide.equals("left")?x4:null, null, viewSide.equals("right")?x4:null, null);
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });


    }



    //Common Group Layout - All
    //----------------------------------------------------------------------------------------------
    public static void getCommonGroupLayoutConfirmPage(Context context, LinearLayout parentLayout, String titleName) {
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, 0);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_headline_1);
        layout1.setLayoutParams(param1);
        parentLayout.addView(layout1);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText(titleName);
        text1.setTextSize(13);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }

    //Common Group Layout - First
    //----------------------------------------------------------------------------------------------
    public static void getCommonGroupLayoutConfirmPageFirst(Context context, LinearLayout parentLayout, String titleName) {
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 10, 0, -3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_com_headline_1);
        layout1.setLayoutParams(param1);
        parentLayout.addView(layout1);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText(titleName);
        text1.setTextSize(13);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }



    //Common Group Layout
    //----------------------------------------------------------------------------------------------
    public static void getCommonGroupLayoutRegistrationPage(Context context, LinearLayout parentLayout, String titleName) {
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.setBackgroundResource(R.drawable.util_gra_bluedeep_npad_ystroke_ycorners_yclickable);
        layout1.setLayoutParams(param1);
        parentLayout.addView(layout1);

        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        param2.setMargins(10, 10, 10, 10);
        text1.setText(titleName);
        text1.setTextSize(13);
        text1.setTextColor(Color.WHITE);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(param2);
        layout1.addView(text1);
    }

    //Common Group Layout
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayoutWithSmoking(Context context, LinearLayout parentLayout, String titleName, TextView smokingFlag, TextView roomName) {
        int leftWidth = Integer.valueOf(getScreen(context).get("leftwidth"));
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams px1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(px1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout1);

        //Left Layout (Leading)
        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px2.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px2);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        //Left Level
        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px3 = new LinearLayout.LayoutParams(leftWidth, ActionBar.LayoutParams.WRAP_CONTENT);
        px3.setMargins(0, 0, 0, 0);
        col1.setTextSize(14);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px3);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        //Right Layout (Leading) Smoking
        LinearLayout layout3 = new LinearLayout(context);
        LinearLayout.LayoutParams px4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px3.setMargins(0, 0, -3, 0);
        layout3.setOrientation(LinearLayout.HORIZONTAL);
        layout3.setLayoutParams(px4);
        layout3.setBackgroundResource(R.drawable.util_com_background_1);
        layout3.setPadding(13, 13, 13, 13);
        layout3.setGravity(Gravity.CENTER_VERTICAL);
        layout2.addView(layout3);

        // Right Icon (Leading) Smoking
        LinearLayout.LayoutParams px5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
        px5.setMargins(0, 0, 5, 0);
        smokingFlag.setBackgroundResource(R.drawable.ic_g12_p15_smoking_no);
        smokingFlag.setLayoutParams(px5);
        smokingFlag.setPadding(13, 13, 13, 13);
        layout3.addView(smokingFlag);

        // Right text
        LinearLayout.LayoutParams px6 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px6.setMargins(0, 0, 0, 0);
        roomName.setTextSize(14);
        roomName.setTextColor(Color.BLACK);
        roomName.setLayoutParams(px6);
        layout3.addView(roomName);
    }

    //Common Field Layout
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayoutHideAble(Context context, LinearLayout parentLayout, LinearLayout childLayout, String titleName, TextView textview) {
        int leftWidth = Integer.valueOf(getScreen(context).get("leftwidth"));
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        childLayout.setOrientation(LinearLayout.HORIZONTAL);
        childLayout.setLayoutParams(p1);
        childLayout.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(childLayout);

        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        childLayout.addView(layout2);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(leftWidth, ActionBar.LayoutParams.MATCH_PARENT);
        px_6.setMargins(0, 0, -3, 0);
        col1.setTextSize(13);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(13);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }


    //Registration Confirmation Customer Information Activity
    public static void getRegisCustInfoHideAble(Context context, LinearLayout parentLayout, LinearLayout childLayout, String leftTitle, String rightTitle) {
        int leftWidth = Integer.valueOf(getScreen(context).get("leftwidth"));
        int end= -3;
        if(rightTitle.equalsIgnoreCase("1")){
            end = 0;
        }else if(rightTitle.equalsIgnoreCase("2")){
            end = 0;
        }else if(rightTitle.equalsIgnoreCase("3")){
            end = 0;
        }else if(rightTitle.equalsIgnoreCase("4")){
            end = 0;
        }

        LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p4.setMargins(0, 0, 0, end);
        childLayout.setOrientation(LinearLayout.HORIZONTAL);
        childLayout.setLayoutParams(p4);
        childLayout.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(childLayout);

        // Left Part ( Layout )
        LinearLayout leftPart = new LinearLayout(context);
        LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(leftWidth, ActionBar.LayoutParams.MATCH_PARENT);
        p5.setMargins(0, 0, 0, -end);
        leftPart.setOrientation(LinearLayout.HORIZONTAL);
        leftPart.setBackgroundResource(R.drawable.util_confirm_left_section);
        leftPart.setLayoutParams(p5);
        leftPart.setPadding(10, 25, 10, 25);
        leftPart.setGravity(Gravity.CENTER | Gravity.LEFT);
        childLayout.addView(leftPart);

        Cmm_BlueBar(context,leftPart);

        //Left Text
        TextView leftText = new TextView(context);
        LinearLayout.LayoutParams p7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
        leftText.setText(leftTitle);
        leftText.setPadding(20, 0, 20, 0);
        leftText.setTextColor(Color.BLACK);
        leftText.setLayoutParams(p7);
        leftText.setTextSize(11);
        leftPart.addView(leftText);


        // Right Part (Layout)
        LinearLayout rightPart = new LinearLayout(context);
        LinearLayout.LayoutParams p8 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p8.setMargins(0, 0, 0, -end);
        rightPart.setOrientation(LinearLayout.HORIZONTAL);
        rightPart.setBackgroundResource(R.drawable.util_confirm_right_section);
        rightPart.setLayoutParams(p8);
        rightPart.setPadding(10, 25, 10, 25);
        rightPart.setGravity(Gravity.CENTER_VERTICAL);
        childLayout.addView(rightPart);


        //Right text
        TextView rightText = new TextView(context);
        LinearLayout.LayoutParams p9 = new LinearLayout.LayoutParams(10, ActionBar.LayoutParams.MATCH_PARENT);
        p9.weight = 1f;
        rightText.setText(rightTitle);
        rightText.setPadding(20, 0, 20, 0);
        rightText.setTextColor(Color.BLACK);
        rightText.setLayoutParams(p9);
        rightText.setTextSize(11);
        rightPart.addView(rightText);
    }




    //Common Field All
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayout(Context context, LinearLayout parentLayout, String titleName, TextView textview) {
        int leftWidth = Integer.valueOf(getScreen(context).get("leftwidth"));
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout1);

        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(leftWidth, ActionBar.LayoutParams.MATCH_PARENT);
        px_6.setMargins(0, 0, -3, 0);
        col1.setTextSize(13);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(13);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }

    //Common Field Last
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayoutLast(Context context, LinearLayout parentLayout, String titleName, TextView textview) {
        int leftWidth = Integer.valueOf(getScreen(context).get("leftwidth"));
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 0, 0, 10);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout1);

        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(leftWidth, ActionBar.LayoutParams.MATCH_PARENT);
        px_6.setMargins(0, 0, -3, 0);
        col1.setTextSize(13);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(13);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }


    //Common Field First
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayoutFirst(Context context, LinearLayout parentLayout, String titleName, TextView textview) {
        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, 5, 0, -3);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout1);

        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(315, ActionBar.LayoutParams.MATCH_PARENT);
        px_6.setMargins(0, 0, -3, 0);
        col1.setTextSize(13);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(13);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }







    //Common Field Layout
    //----------------------------------------------------------------------------------------------
    public static void getCommonFieldLayoutDup2(Context context, LinearLayout parentLayout, String titleName, TextView textview, int max, int id) {
        int bottom;
        int top;
        if (max == id) {
            bottom = 0;
        } else {
            bottom = -3;
        }

        if (id == 1) {
            top = -3;
        }else{
            top = 0;
        }

        LinearLayout layout1 = new LinearLayout(context);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        p1.setMargins(0, top, 0, bottom);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        layout1.setLayoutParams(p1);
        layout1.setGravity(Gravity.CENTER_VERTICAL);
        parentLayout.addView(layout1);

        LinearLayout layout2 = new LinearLayout(context);
        LinearLayout.LayoutParams px_5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        px_5.setMargins(0, 0, 0, 0);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        layout2.setLayoutParams(px_5);
        layout2.setPadding(0, 0, 0, 0);
        layout2.setGravity(Gravity.CENTER_VERTICAL);
        layout1.addView(layout2);

        TextView col1 = new TextView(context);
        LinearLayout.LayoutParams px_6 = new LinearLayout.LayoutParams(150, ActionBar.LayoutParams.WRAP_CONTENT);
        px_6.setMargins(0, 0, -3, 0);
        col1.setTextSize(13);
        col1.setTextColor(Color.BLACK);
        col1.setBackgroundResource(R.drawable.util_gra_greylite_npad_ystroke_ycorner_yclickable_dup3);
        col1.setText(titleName);
        col1.setLayoutParams(px_6);
        col1.setPadding(13, 13, 13, 13);
        layout2.addView(col1);

        LinearLayout.LayoutParams px_7 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        px_7.setMargins(0, 0, 0, 0);
        textview.setTextSize(13);
        textview.setTextColor(Color.BLACK);
        textview.setBackgroundResource(R.drawable.util_com_background_1);
        textview.setLayoutParams(px_7);
        textview.setPadding(13, 13, 13, 13);
        layout2.addView(textview);
    }

    //MustItem
    public static void Cmm_MustItem(Context context, LinearLayout parentLayout) {
        TextView mustItem = new TextView(context);
        LinearLayout.LayoutParams mstItm = new LinearLayout.LayoutParams(60, ActionBar.LayoutParams.WRAP_CONTENT);
        mstItm.setMargins(0, 0, 5, 0);
        mustItem.setText("必須");
        mustItem.setGravity(Gravity.CENTER);
        mustItem.setBackgroundResource(R.color.red);
        mustItem.setTextColor(Color.WHITE);
        mustItem.setLayoutParams(mstItm);
        parentLayout.addView(mustItem);
    }

    public static void Cmm_MustItem2(Context context, LinearLayout parentLayout) {
        TextView mustItem = new TextView(context);
        LinearLayout.LayoutParams mstItm = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        mstItm.setMargins(0, 0, 5, 0);
        mustItem.setTextSize(11);
        mustItem.setText("必須");
        mustItem.setGravity(Gravity.CENTER);
        mustItem.setPadding(5,5,5,5);
        mustItem.setBackgroundResource(R.color.red);
        mustItem.setTextColor(Color.WHITE);
        mustItem.setLayoutParams(mstItm);
        parentLayout.addView(mustItem);
    }

    public static void Cmm_BlueBar(Context context,LinearLayout layout) {
        TextView textImg = new TextView(context);
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(13, ActionBar.LayoutParams.WRAP_CONTENT);
        p2.setMargins(10, 0, 15, 0);
        textImg.setBackgroundResource(R.color.blue);
        textImg.setTextSize(13);
        textImg.setLayoutParams(p2);
        layout.addView(textImg);
    }

    public static void Cmm_FieldLevel(Context context,LinearLayout layout , String fieldName , int widthFlag) {
        TextView text1 = new TextView(context);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        if(widthFlag==1){
            p3.weight = 1f;
        }
        p3.setMargins(0, 0, 5, 0);
        text1.setText(fieldName);
        text1.setTextSize(15);
        text1.setPadding(10, 10, 10, 10);
        text1.setLayoutParams(p3);
        text1.setTextColor(Color.BLACK);
        layout.addView(text1);
    }



    public static void Cmm_WidthSpace(Context context, LinearLayout parentLayout) {
        TextView emptyField = new TextView(context);
        LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        p5.weight = 1f;
        emptyField.setLayoutParams(p5);
        parentLayout.addView(emptyField);
    }


    //Must Item With Space
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


/*    private void SetupMessage() {
        la_error_code.setText("[" + ERROR_CODE + "]");
        la_error_message.setText(ERROR_MESSAGE);
    }*/

    private void GetDataFromRequestPage() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
            if (!obj_g01.getErrrMssg().isEmpty()) {
                ERROR_MESSAGE = obj_g01.getErrrMssg();
            }
            if (!obj_g01.getErrrCode().isEmpty()) {
                ERROR_CODE = obj_g01.getErrrCode();
            }
        }
    }

/*    private void InitialSetupConfiguration() {
        this.ERROR_MESSAGE = new String();
        this.ERROR_CODE = new String();
        la_error_code = (TextView) findViewById(R.id.error_code);
        la_error_message = (TextView) findViewById(R.id.error_message);
    }*/

}
