package toyoko.inn.com.smartphoneappplus;

import  android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G56P56A00LoginDifferent extends Activity {
    private G01P01ParcelableData obj_g01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G56P56A00LoginDifferent------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g56_p56_initial_setting);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        // Go To Login
        GoToLogin();

        //Go To Forget Password
        GoToForgetPassword();

        //Back To Previous Page
        BackToPreviousPage();

    }

    private void BackToPreviousPage() {
        LinearLayout la_login = (LinearLayout)findViewById(R.id.g56_p56_email_addresss);
        la_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G70P70A35PasswordForgetStep0.class,COD_NEXT);
            }
        });
    }

    private void GoToForgetPassword() {
        LinearLayout la_login = (LinearLayout)findViewById(R.id.g50_p26_forget_pass);
        la_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G70P70A35PasswordForgetStep0.class,COD_NEXT);
            }
        });
    }

    private void GoToLogin() {
        LinearLayout la_login = (LinearLayout)findViewById(R.id.g50_p26_member_card);
        la_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G50P26A32Login.class,COD_NEXT);
            }
        });
    }

    private void SetupDefultConfiguration() {


    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);
        }
    }






    private void errorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(getErrorCode(eCode));
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
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void goTo(final Class myClass, String forwordState) {
        Intent intent = intent = new Intent(getApplicationContext(), myClass);
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
}
