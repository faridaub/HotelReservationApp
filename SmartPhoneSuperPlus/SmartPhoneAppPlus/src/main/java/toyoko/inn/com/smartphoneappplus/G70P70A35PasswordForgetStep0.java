package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G70P70A35PasswordForgetStep0 extends Activity {
    private G01P01ParcelableData obj_g01;

    private EditText la_email_address;
    private EditText la_family_name;
    private EditText la_first_name;
    private EditText la_date_of_birth;

    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String DATE_OF_BIRTH;
    private String EMAIL_ADDRESS;
    private String AUTH_CODE;
    private String LGNID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G70P70A35PasswordForgetStep0------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g70_p70_password_forget_setp0);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //Setup To View
        SetupToView();

        //<<==Back To Previous Page
        BackTo_G00P26();

        //==Check and Go To Json Data
        CheckAndGoToJsonExecution();

    }

    private void SetupToView() {
        addCross(getApplicationContext(), la_email_address);
        addCross(getApplicationContext(), la_family_name);
        addCross(getApplicationContext(), la_first_name);
        addCross(getApplicationContext(), la_date_of_birth);
    }


    private void CheckAndGoToJsonExecution() {
            layoutNoError();
            Button button = (Button) findViewById(R.id.g70_p70_action);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(SetupValidation()) {
                        layoutNoError();
                        SetupToJson();
                    }
                }
            });
    }

    private void layoutNoError() {
        EMAIL_ADDRESS = la_email_address.getText().toString();
        FAMILY_NAME =  la_family_name.getText().toString();
        FIRST_NAME = la_first_name.getText().toString();
        DATE_OF_BIRTH = la_date_of_birth.getText().toString();

    }

    private void layoutError(String errorMessage) {
        String mail = la_email_address.getText().toString();
        String family = la_family_name.getText().toString();
        String first = la_first_name.getText().toString();
        String dob = la_date_of_birth.getText().toString();

        if(mail.isEmpty()){
            la_email_address.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if(family.isEmpty()){
            la_family_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if(first.isEmpty()){
            la_first_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if(dob.isEmpty()){
            la_date_of_birth.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        if (dob.length() < 8) {
            la_date_of_birth.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        errorPopup(null, errorMessage);

    }

    //Field Validation
    //----------------------------------------------------------------------------------------------
    private boolean SetupValidation() {
        String mail = la_email_address.getText().toString();
        String family = la_family_name.getText().toString();
        String first = la_first_name.getText().toString();
        String dob = la_date_of_birth.getText().toString();

        if(mail.isEmpty()){
            layoutError(ERR_EMPTY_FILD_EMAIL);
            return false;
        }
        if(family.isEmpty()){
            layoutError(ERR_EMPTY_FILD_FAMILY);
            return false;
        }
        if(first.isEmpty()){
            layoutError(ERR_EMPTY_FILD_FIRST);
            return false;
        }
        if(dob.isEmpty()){
            layoutError(ERR_EMPTY_FILD_DOB);
            return false;
        }

        if (dob.length() < 8) {
            layoutError(ERR_EMPTY_FILD_DOB);
            return false;
        }

        return true;
    }


    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");
        }
    }

    private void SetupDefultConfiguration() {
        la_email_address = (EditText)findViewById(R.id.g70_p70_email_address);
        la_family_name =(EditText)findViewById(R.id.g70_p70_family_name);
        la_first_name = (EditText)findViewById(R.id.g70_p70_first_name);
        la_date_of_birth = (EditText)findViewById(R.id.g70_p70_date_of_birth);

        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.DATE_OF_BIRTH = new String();
        this.EMAIL_ADDRESS = new String();
        this.AUTH_CODE = new String();
        this.LGNID = new String();

    }

    private void BackTo_G00P26() {
        Button button = (Button) findViewById(R.id.g70_p70_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G50P26A32Login.class,ComMsg.COD_BACK);
            }
        });
    }


    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;
        private String authCode;
        private String loginId;
        private String rsrvsPrsnUid;

        public JSONParse() {
            super();
            setDataBeforeJsonWork();
        }

        private void setDataBeforeJsonWork() {
            api.setPcEmlAddrss(EMAIL_ADDRESS);
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            api.setDateBirth(DATE_OF_BIRTH);
            Log.e("PARAM-G70P70", api.getRequestDataA035().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G70P70A35PasswordForgetStep0.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA035());
            JSONObject json = jParser.getJSONData(api.getURLA035());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G70P70", json.toString());
            authCode = json.optString(ComConstant.CT_ATHNTCTNKEY);
            loginId = json.optString(ComConstant.CT_LGNID);
            rsrvsPrsnUid = json.optString(ComConstant.CT_RSRVSPRSNUID);
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            obj_g01.setCustAuthKey(authCode);
            obj_g01.setCustLgnId(loginId);
            obj_g01.setCustRsrvsPrsnUid(rsrvsPrsnUid);
            goTo(G71P71A00PasswordForgetStep1.class, ComMsg.COD_NEXT);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
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

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }

}
