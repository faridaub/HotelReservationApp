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
import android.widget.EditText;
import android.widget.TextView;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

public class G71P71A00PasswordForgetStep1 extends Activity {
    private G01P01ParcelableData obj_g01;

    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String DATE_OF_BIRTH;
    private String EMAIL_ADDRESS;
    private EditText la_auth_code;
    private String AUTH_KEY;
    private Button la_button;
    private String NEW_AUTH_KEY;
    private String LGNID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G71P71A00PasswordForgetStep1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g71_p71_password_forget_setp1);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //== Set To View
        SetupToView();

        //<<==Back To Previous Page
        BackToPreviousPage();

        //Go To Setp 3
        GoToNextPage();
    }

    private void SetupToView() {
        TextView userid =  (TextView)findViewById(R.id.g71_p71_userid);
        userid.setText(LGNID);
    }

    private void GoToNextPage() {
        layoutNoError();
       la_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(checkValidation()){
                   layoutNoError();
                   checkAuthValues();
               }else{
                   layoutError();
               }
           }
       });
    }

    private void checkAuthValues() {
        if(AUTH_KEY.contentEquals(NEW_AUTH_KEY)){
            goTo(G72P72A36PasswordForgetStep2.class,"next");
        }else{
            LoginErrorDialogBox();
        }

    }

    private void layoutNoError() {
        NEW_AUTH_KEY = la_auth_code.getText().toString();
    }

    private void layoutError() {
        if(la_auth_code.getText().toString().isEmpty()){
            la_auth_code.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
    }

    private boolean checkValidation() {
          boolean errorFlag = true;
      if(la_auth_code.getText().toString().isEmpty()){
            errorFlag =false;
        }
        return errorFlag;
    }



    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");
            if(!obj_g01.getCustAuthKey().isEmpty()){
                AUTH_KEY = obj_g01.getCustAuthKey();
            }

            if(!obj_g01.getCustLgnId().isEmpty()){
                LGNID = obj_g01.getCustLgnId();
            }

        }
    }

    private void SetupDefultConfiguration() {
        la_auth_code = (EditText)findViewById(R.id.g71_p71_auth_code);
        la_button =(Button)findViewById(R.id.g71_p71_action);
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.DATE_OF_BIRTH = new String();
        this.EMAIL_ADDRESS = new String();
        this.AUTH_KEY = new String();
        this.NEW_AUTH_KEY = new String();
        this.LGNID = new String();
    }

    private void BackToPreviousPage() {
        Button button = (Button) findViewById(R.id.g71_p71_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish("back");
            }
        });
    }

    private void LoginErrorDialogBox() {
        final Dialog dialog = new Dialog(G71P71A00PasswordForgetStep1.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g00p26_faild_login);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        la_auth_code.setBackgroundResource(R.drawable.util_textview_bk_red);
        Button no = (Button) dialog.findViewById(R.id.retry_login);
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
