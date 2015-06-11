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
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.getCountryValueAccCode;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G54P54A34Confirm extends Activity {
    private G01P01ParcelableData obj_g01;
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
    private String NEWSLETTER;
    private String EMAIL_ADDRESS2;
    private String MAILSUBMIT_FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G54P54A34Confirm------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g54_p54_initial_setting);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //==Set Data To View
        SetupToView();

        //<<==Back To Previous Page
        BackTo();

        //==Check and Go To Json Data
        SetupToJson();

    }
    private void SetupDefultConfiguration() {
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
        this.NEWSLETTER = new String();
        this.EMAIL_ADDRESS2 = new String();
        this.MAILSUBMIT_FLAG = new String();
    }
    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");

            if (!obj_g01.getCustLgnId().isEmpty()) {
                LOGIN_ID = obj_g01.getCustLgnId();
            }

            if (!obj_g01.getCustLgnPsswrd().isEmpty()) {
                PASSWORD = obj_g01.getCustLgnPsswrd();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
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
                NEWSLETTER = obj_g01.getCustNwslttr();
            }

            if (!obj_g01.getCustProgressType().isEmpty()) {
                PROGRESS_TYPE = obj_g01.getCustProgressType();
            }

        }
    }
    private void SetupToView() {

        TextView la_test1 = (TextView) findViewById(R.id.g54_p54_membership);
        la_test1.setText(ComMsg.MSG_TOYOKO_INN_GROUPCARD);
        if (MEMBERSHIP_FLAG.equalsIgnoreCase(ComMsg.COD_N)) {
            la_test1.setText(ComMsg.MSG_TOYOKO_INN_GENERAL);
        }
        TextView la_test2 = (TextView) findViewById(R.id.g54_p54_membership_num);
        la_test2.setText(MEMBERSHIP_NUM);
        TextView la_test3 = (TextView) findViewById(R.id.g54_p54_familyname);
        la_test3.setText(FAMILY_NAME);
        TextView la_test4 = (TextView) findViewById(R.id.g54_p54_firstname);
        la_test4.setText(FIRST_NAME);
        TextView la_test5 = (TextView) findViewById(R.id.g54_p54_dob);
        la_test5.setText(DOB);
        TextView la_test6 = (TextView) findViewById(R.id.g54_p54_country);
        la_test6.setText(getCountryValueAccCode(COUNTRY));
        TextView la_test7 = (TextView) findViewById(R.id.g54_p54_sex);
        la_test7.setText(ComMsg.SW_FMALE);
        if (SEX.equalsIgnoreCase(ComMsg.COD_M)) {
            la_test7.setText(ComMsg.SW_MALE);
        }
        TextView la_test8 = (TextView) findViewById(R.id.g54_p54_phn_num);
        la_test8.setText(PHONE_NUMBER);

        TextView la_test9 = (TextView) findViewById(R.id.g54_p54_email_address1);
        la_test9.setText(EMAIL_ADDRESS1);


        TextView la_test10 = (TextView) findViewById(R.id.g54_p54_mail_submit_flag);
        la_test10.setText(ComMsg.SW_NO);
        if (MEMBERSHIP_FLAG.equalsIgnoreCase(ComMsg.COD_Y)) {
            la_test10.setText(ComMsg.SW_YES);
        }
    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g54_p54_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_NEXT);
            }
        });
    }
    private void G51P51_goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            goTo(ComActivity.class, ComMsg.COD_NORMAL);
        }
    }
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String Login_id =  new String();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataA34();
        }

        private void setDataA34() {
            api.setPrcssngType(PROGRESS_TYPE);
            api.setRsrvsPrsnUid("");
            api.setLgnId(LOGIN_ID);
            api.setLgnPsswrd(PASSWORD);
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setMmbrshpNmbr("");
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            api.setDateBirth(DOB);
            api.setNtnltyCode(COUNTRY);
            api.setSex(SEX);
            api.setPhnNmbr(PHONE_NUMBER);
            api.setEmlAddrss(EMAIL_ADDRESS1);
            api.setNwslttr(NEWSLETTER);
            api.setEmlAddrss2(EMAIL_ADDRESS2);
            Log.e(ComLogMsg.PARAM_G53P53, api.getRequestDataA034().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G54P54A34Confirm.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA034());
            JSONObject json = jParser.getJSONData(api.getURLA034());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G54P54, json.toString());
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
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
            goTo(G55P55A00Submit.class, ComMsg.COD_NEXT);
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

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }

}
