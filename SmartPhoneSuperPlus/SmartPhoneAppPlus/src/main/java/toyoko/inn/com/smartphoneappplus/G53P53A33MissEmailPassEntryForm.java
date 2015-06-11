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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.getCountryValueAccCode;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_BACK;


public class G53P53A33MissEmailPassEntryForm extends Activity {
    private G01P01ParcelableData obj_g01;
    private EditText la_email1;
    private EditText la_password1;
    private EditText la_password2;
    private String MMBRSHPNMBR;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String MEMBERSHIP_FLAG;
    private String MEMBERSHIP_NUM;
    private String DOB;
    private String COUNTRY;
    private String SEX;
    private String PHONE_NUMBER;
    private String PROGRESS_TYPE;
    private String LOGIN_ID;
    private String PASSWORD;
    private String EMAIL_ADDRESS1;
    private String NEWSLETTER_FLAG;
    private String EMAIL_ADDRESS2;
    private EditText la_email1_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G53P53A33MissEmailPassEntryForm------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g53_p53_initial_setting);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData_G00P26();

        //==Setup Email Flag
        G00P42_emailFlagSetup();

        //==Set Data To View
        SetupToView();

        //<<==Back To Previous Page
        BackTo();

        //==Check and Go To Json Data
        G00P42_checkAndGoToJsonExecution();

        //== Go To Agreement
        GoToWebView();

    }

    private void GoToWebView() {
        Button button = (Button) findViewById(R.id.g53_p53_agreement);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G53P53A00WebView.class, ComMsg.COD_NEXT);
            }
        });
    }


    private void G00P42_emailFlagSetup() {
        NEWSLETTER_FLAG = "N";
        ToggleButton emailFlag = (ToggleButton) findViewById(R.id.g53_p53_mail_submit_flag);
        emailFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NEWSLETTER_FLAG = "Y";
                } else {
                    NEWSLETTER_FLAG = "N";
                }
            }
        });
    }

    private void G53P53_goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            goTo(ComActivity.class, ComMsg.COD_NORMAL);
        }
    }

    private void G00P42_checkAndGoToJsonExecution() {
        Button button = (Button) findViewById(R.id.g53_p53_action);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    SetupToVariable();
                    G53P53_goToJsonParse();
                }
            }
        });
    }

    private void SetupToVariable() {
        if (MEMBERSHIP_FLAG.equalsIgnoreCase("Y")) {
            PROGRESS_TYPE = ComInitData.ST_ONE;
        } else if (MEMBERSHIP_FLAG.equalsIgnoreCase("N")) {
            PROGRESS_TYPE = ComInitData.ST_TWO;
        } else {
            PROGRESS_TYPE = ComInitData.ST_THREE;
        }
        LOGIN_ID  = la_email1.getText().toString();
        EMAIL_ADDRESS1 = la_email1.getText().toString();
        PASSWORD = la_password1.getText().toString();
    }

    private void SetupToParcel(){
        obj_g01.setCustLgnId(LOGIN_ID);
        obj_g01.setCustLgnPsswrd(PASSWORD);
        obj_g01.setCustMmbrshpFlag(MEMBERSHIP_FLAG);
        obj_g01.setCustFmlyName(FAMILY_NAME);
        obj_g01.setCustFrstName(FIRST_NAME);
        obj_g01.setCustDateBirth(DOB);
        obj_g01.setCustSex(SEX);
        obj_g01.setCustNtnltyCode(COUNTRY);
        obj_g01.setCustPhnNmbr(PHONE_NUMBER);
        obj_g01.setCustPcEmlAddrss(EMAIL_ADDRESS1);
        obj_g01.setCustMbEmlAddrss(EMAIL_ADDRESS2);
        obj_g01.setCustNwslttr(NEWSLETTER_FLAG);
        obj_g01.setCustProgressType(PROGRESS_TYPE);
    }

    private void SetupErrorField(String errorMessage) {
        String e_10 = la_email1.getText().toString();
        String e_11 = la_email1_confirm.getText().toString();
        String pa1 = la_password1.getText().toString();
        String pa2 = la_password2.getText().toString();

        la_email1.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_email1_confirm.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password1.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password2.setBackgroundResource(R.drawable.util_textview_bk_pink);

        if (e_10.isEmpty()) {
            la_email1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (e_11.isEmpty()) {
            la_email1_confirm.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (!e_10.equalsIgnoreCase(e_11)) {
            la_email1.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_email1_confirm.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (pa1.isEmpty()) {
            la_password1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (pa2.isEmpty()) {
            la_password2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (!pa1.equalsIgnoreCase(pa2)) {
            la_password1.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        errorPopup(null, errorMessage);
    }

    private boolean SetupValidation() {
        String e_10 = la_email1.getText().toString();
        String e_11 = la_email1_confirm.getText().toString();

        String pa1 = la_password1.getText().toString();
        String pa2 = la_password2.getText().toString();


        if (e_10.isEmpty()) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }
        if (e_11.isEmpty()) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }
        if (!e_10.equalsIgnoreCase(e_11)) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }
        if (pa1.isEmpty()) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }
        if (pa2.isEmpty()) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }
        if (!pa1.equalsIgnoreCase(pa2)) {
            SetupErrorField(ComMsg.ERR_EMPTY_FILD);
            return false;
        }

        return true;
    }

    private void GetData_G00P26() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCustLgnPsswrd().isEmpty()) {
                PASSWORD = obj_g01.getCustLgnPsswrd();
            }

            if (!obj_g01.getCustMmbrshpNmbr().isEmpty()) {
                MEMBERSHIP_NUM = obj_g01.getCustMmbrshpNmbr();
            }

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FAMILY_NAME = obj_g01.getCustFmlyName();
            }

            if (!obj_g01.getCustFrstName().isEmpty()) {
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustDateBirth().isEmpty()) {
                DOB = obj_g01.getCustDateBirth();
            }

            if (!obj_g01.getCustNtnltyCode().isEmpty()) {
                COUNTRY = obj_g01.getCustNtnltyCode();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHONE_NUMBER = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getCustLgnId().isEmpty()) {
                LOGIN_ID = obj_g01.getCustLgnId();
            }

            if (!obj_g01.getCustPcEmlAddrss().isEmpty()) {
                EMAIL_ADDRESS1 = obj_g01.getCustPcEmlAddrss();
            }

            if (!obj_g01.getCustMbEmlAddrss().isEmpty()) {
                EMAIL_ADDRESS2 = obj_g01.getCustMbEmlAddrss();
            }

            if (!obj_g01.getCustNwslttr().isEmpty()) {
                NEWSLETTER_FLAG = obj_g01.getCustNwslttr();
            }



        }
    }
    private void SetupToView() {
        la_email1.setText(EMAIL_ADDRESS1);

        TextView la_test1 = (TextView) findViewById(R.id.g53_p53_membership);
        la_test1.setText(ComMsg.MSG_TOYOKO_INN_GROUPCARD);
        if (MEMBERSHIP_FLAG.equalsIgnoreCase(ComMsg.COD_N)) {
            la_test1.setText(ComMsg.MSG_TOYOKO_INN_GENERAL);
        }
        TextView la_test2 = (TextView) findViewById(R.id.g53_p53_membership_num);
        la_test2.setText(MEMBERSHIP_NUM);
        TextView la_test3 = (TextView) findViewById(R.id.g53_p53_familyname);
        la_test3.setText(FAMILY_NAME);
        TextView la_test4 = (TextView) findViewById(R.id.g53_p53_firstname);
        la_test4.setText(FIRST_NAME);
        TextView la_test5 = (TextView) findViewById(R.id.g53_p53_dob);
        la_test5.setText(DOB);
        TextView la_test6 = (TextView) findViewById(R.id.g53_p53_country);
        la_test6.setText(getCountryValueAccCode(COUNTRY));
        TextView la_test7 = (TextView) findViewById(R.id.g53_p53_sex);
        la_test7.setText(ComMsg.SW_FMALE);
        if (SEX.equalsIgnoreCase(ComMsg.COD_M)) {
            la_test7.setText(ComMsg.SW_MALE);
        }
        TextView la_test8 = (TextView) findViewById(R.id.g53_p53_phn_num);
        la_test8.setText(PHONE_NUMBER);


        TextView la_test9 = (TextView) findViewById(R.id.g53_p53_email_address1);
        la_test9.setText(EMAIL_ADDRESS1);

        TextView la_test10 = (TextView) findViewById(R.id.g53_p53_email2);
        la_test10.setText(EMAIL_ADDRESS2);





    }

    private void SetupDefultConfiguration() {
        la_email1 = (EditText) findViewById(R.id.g53_p53_email_1);
        la_email1_confirm = (EditText) findViewById(R.id.g53_p53_email_1_confirm);
        la_password1 = (EditText) findViewById(R.id.g53_p53_password1);
        la_password2 = (EditText) findViewById(R.id.g53_p53_password2);

        this.MMBRSHPNMBR = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.MEMBERSHIP_NUM = new String();
        this.DOB = new String();
        this.COUNTRY = new String();
        this.SEX = new String();
        this.PHONE_NUMBER = new String();
        this.PROGRESS_TYPE = new String();
        this.LOGIN_ID = new String();
        this.PASSWORD = new String();
        this.EMAIL_ADDRESS1 = new String();
        this.NEWSLETTER_FLAG = new String();
        this.EMAIL_ADDRESS2 = new String();
    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g53_p53_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   goTo(G51P51A26HoldingMembershipCard.class, ComMsg.COD_NORMAL);
                finish(COD_BACK);
            }
        });
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String Login_id = new String();
        private String errorCode;
        private String errorMessage;
        private boolean registeredId;

        public JSONParse() {
            super();
            registeredId = false;
            setDataBeforeJsonWork33();
        }

        private void setDataBeforeJsonWork33() {
            api.setPrcssngType(PROGRESS_TYPE);
            api.setLgnId(LOGIN_ID);
            api.setLgnPsswrd(PASSWORD);
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setMmbrshpNmbr(MEMBERSHIP_NUM);
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            api.setDateBirth(DOB);
            api.setNtnltyCode(COUNTRY);
            api.setSex(SEX);
            api.setPhnNmbr(PHONE_NUMBER);
            api.setEmlAddrss(EMAIL_ADDRESS1);
            api.setNwslttr(NEWSLETTER_FLAG);
            api.setEmlAddrss2(EMAIL_ADDRESS2);
            Log.e(ComLogMsg.PARAM_G53P53, api.getRequestDataA033().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G53P53A33MissEmailPassEntryForm.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA033());
            JSONObject json = jParser.getJSONData(api.getURLA033());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G53P53, json.toString());
            if(!ComLib.isDataBGNL0004(json.optString(ComConstant.CT_ERRRCODE))) {
                if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                    errorCode = json.optString(ComConstant.CT_ERRRCODE);
                    errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                    processDialog.dismiss();
                    cancel(true);
                }
            }

            if(ComLib.isDataBGNL0004(json.optString(ComConstant.CT_ERRRCODE))) {
                 registeredId = true;
            }

            try {
                JSONArray jsonData = json.getJSONArray(ComConstant.LT_MMBRSHPNMBRARRY);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToParcel();
            if(registeredId){
                goTo(G56P56A00LoginDifferent.class, ComMsg.COD_NEXT);
            }else {
                goTo(G54P54A34Confirm.class, ComMsg.COD_NEXT);
            }
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
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
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
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
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }
}
