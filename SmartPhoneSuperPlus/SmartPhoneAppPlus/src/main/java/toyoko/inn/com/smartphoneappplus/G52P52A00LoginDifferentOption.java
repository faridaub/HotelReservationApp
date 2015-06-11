package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
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

import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G52P52A00LoginDifferentOption extends Activity {
    private G01P01ParcelableData obj_g01;
    private String MMBRSHPNMBR;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String LOGIN_ID;
    private TextView la_login_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G52P52A00LoginDifferentOption------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g52_p52_login_diff_option);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //=Setup To View
        SetupToView();

        //==>>Login By Id
        GoToLoginById();

        //==>ForgetPassword
        GoToPasswordPage();

        //==>Login By Different id
        GoToRegistrationDifferentID();

        //==Check and Go To Json Data
        BackToLoginPage();

    }


    private void GoToLoginById() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g52_p52_loginbyid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G50P26A32Login.class,"next");
            }
        });
    }

    private void BackToLoginPage() {
        Button button = (Button) findViewById(R.id.g52_p52_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G50P26A32Login.class,"next");
            }
        });
    }

    private void GoToRegistrationDifferentID() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g52_p52_new_registration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G53P53A33MissEmailPassEntryForm.class,"next");
            }
        });
    }

    private void GoToPasswordPage() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g52_p52_forget_pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G70P70A35PasswordForgetStep0.class,"next");
            }
        });
    }

    private void SetupToView() {
        la_login_id.setText(LOGIN_ID);
    }


    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable(ComMsg.COD_DATA);
            if (!obj_g01.getCustLgnId().isEmpty()) {
                LOGIN_ID = obj_g01.getCustLgnId();
            }
        }
    }

    private void SetupDefultConfiguration() {
        la_login_id = (TextView)findViewById(R.id.g52_p52_login_id);
        this.MMBRSHPNMBR = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.LOGIN_ID = new String();
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
        Intent intent = intent = new Intent(getApplicationContext(), myClass);
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
