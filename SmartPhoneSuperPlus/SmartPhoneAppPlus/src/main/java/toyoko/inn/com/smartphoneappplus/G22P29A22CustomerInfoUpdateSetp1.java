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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePair;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePairArrayAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.getPassValidation;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_EMPTY_FILD;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_MAIL_MATCH;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_PASSWORD_ALPHBATE_NUMBER;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_PASSWORD_MATCH;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_PHONENO_FILD;


public class G22P29A22CustomerInfoUpdateSetp1 extends Activity {
    G01P01ParcelableData obj_g01;

    private String RSRVSPRSNUID;
    private String PRCSSNGTYPE;
    private String FMLYNAME;
    private String FRSTNAME;
    private String DATEBIRTH;
    private String SEX;
    private String NTNLTYCODE;
    private String NTNLTYVALUE;
    private String PHNNMBR;
    private String MMBRSHPFLAG;
    private String PCEMLADDRSS1;
    private String PCEMLADDRSS2;
    private String LOGIN_PASSWORD;
    private String PASSWORD2;
    private String PASSDELFLG;
    private String NWSLTTR;
    private String PAGEFLAG;

    private RadioButton la_rd_a1;
    private RadioButton la_rd_a2;
    private EditText la_familyName;
    private EditText la_firstName;
    private EditText la_mail1;
    private EditText la_mail2;
    private EditText la_password1;
    private EditText la_password2;
    private Spinner la_country;
    private Spinner la_gender;
    private EditText la_phoneNumber;
    private String LOGIN_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G22P29A22CustomerInfoUpdateSetp1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g22_p29_customer_update_step1);

        SetupConfiguration();

        GetData();

        SetupToJson();

        BackToPage();

        GoToPage();

    //    GoToPrivicyPolicy();

    }

    private void GoToPrivicyPolicy() {
/*        Button button = (Button) findViewById(R.id.g22_p29_privicy_policy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G22P29A00PrivacyPolicyWebView.class,ComMsg.COD_NEXT);
            }
        });*/
    }

    private void SetupConfiguration() {
        RSRVSPRSNUID = new String();
        PRCSSNGTYPE = new String();
        FMLYNAME = new String();
        FRSTNAME = new String();
        DATEBIRTH = new String();
        SEX = new String();
        NTNLTYCODE = new String();
        NTNLTYVALUE = new String();
        PHNNMBR = new String();
        MMBRSHPFLAG = new String();
        PCEMLADDRSS1 = new String();
        PCEMLADDRSS2 = new String();
        NWSLTTR = new String();
        LOGIN_PASSWORD = new String();
        PASSWORD2 = new String();
        PASSDELFLG = new String();
        PAGEFLAG = new String();
        LOGIN_ID = new String();

        la_rd_a1 = (RadioButton) findViewById(R.id.g22_p29_membership_flag_1);
        la_rd_a2 = (RadioButton) findViewById(R.id.g22_p29_membership_flag_2);
        la_familyName = (EditText) findViewById(R.id.g22_p29_family_name);
        la_firstName = (EditText) findViewById(R.id.g22_p29_first_name);
        la_phoneNumber = (EditText) findViewById(R.id.g22_p29_phonenumber);
        la_mail1 = (EditText) findViewById(R.id.g22_p29_mail1_userid);
        la_mail2 = (EditText) findViewById(R.id.g22_p29_email_2);

        la_password1 = (EditText) findViewById(R.id.g22_p29_pass_1);
        la_password2 = (EditText) findViewById(R.id.g22_p29_pass_2);
        la_country = (Spinner) findViewById(R.id.g22_p29_country);
        la_gender = (Spinner) findViewById(R.id.g22_p29_gender);


    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGEFLAG = obj_g01.getPageFlag();
            }
        }
    }

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ComMsg.ERR_CONNECTION);
        }
    }

    private void BackToPage() {
        final Button button = (Button) findViewById(R.id.g22_p29_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G02P20A01AccountInformation.class, ComMsg.COD_BACK);
            }
        });
    }

    private void GoToPage() {
        final Button button = (Button) findViewById(R.id.g22p29_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    SetupToVariable();
                    SetupToParcel();
                    goTo(G23P30A23CustomerInfoUpdateSetp2.class, ComMsg.COD_NEXT);
                }
            }
        });
    }

    private void SetupErrorField(String errorMessage) {

        String fa = la_familyName.getText().toString();
        String fi = la_firstName.getText().toString();
        String ph1 = la_phoneNumber.getText().toString();
        String ma1 = la_mail1.getText().toString();
        String ma1c = la_mail2.getText().toString();
        String pa1 = la_password1.getText().toString();
        String pa2 = la_password2.getText().toString();

        la_familyName.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_firstName.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_mail1.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_mail2.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password1.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password2.setBackgroundResource(R.drawable.util_textview_bk_pink);

        if (fa.isEmpty()) {
            la_familyName.setFocusableInTouchMode(true);
            la_familyName.requestFocus();
            la_familyName.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (fi.isEmpty()) {
            la_firstName.setFocusableInTouchMode(true);
            la_firstName.requestFocus();
            la_firstName.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ph1.isEmpty()) {
            la_phoneNumber.setFocusableInTouchMode(true);
            la_phoneNumber.requestFocus();
            la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ph1.length() < 6) {
            la_phoneNumber.setFocusableInTouchMode(true);
            la_phoneNumber.requestFocus();
            la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ma1.isEmpty()) {
            la_mail1.setFocusableInTouchMode(true);
            la_mail1.requestFocus();
            la_mail1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ma1c.isEmpty()) {
            la_mail2.setFocusableInTouchMode(true);
            la_mail2.requestFocus();
            la_mail2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (!ma1c.equalsIgnoreCase(ma1)) {
            la_mail2.setFocusableInTouchMode(true);
            la_mail2.requestFocus();
            la_mail2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (pa1.isEmpty()) {
            la_password1.setFocusableInTouchMode(true);
            la_password1.requestFocus();
            la_password1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        if(getPassValidation(pa1)==false){
            la_password1.setFocusableInTouchMode(true);
            la_password1.requestFocus();
            la_password1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        if (pa2.isEmpty()) {
            la_password2.setFocusableInTouchMode(true);
            la_password2.requestFocus();
            la_password2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        if (!pa1.equalsIgnoreCase(pa2)) {
            la_password2.setFocusableInTouchMode(true);
            la_password2.requestFocus();
            la_password2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }

        errorPopup(null, errorMessage);
    }

    private boolean SetupValidation() {
        String fa = la_familyName.getText().toString();
        String fi = la_firstName.getText().toString();
        String ph1 = la_phoneNumber.getText().toString();
        String ma1 = la_mail1.getText().toString();
        String ma1c = la_mail2.getText().toString();
        String pa1 = la_password1.getText().toString();
        String pa2 = la_password2.getText().toString();

        if (fa.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (fi.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ph1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ph1.length() < 6) {
            SetupErrorField(ERR_PHONENO_FILD);
            return false;
        }

        if (ma1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ma1c.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (!ma1c.equalsIgnoreCase(ma1)) {
            SetupErrorField(ERR_MAIL_MATCH);
            return false;
        }

        if (pa1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if(getPassValidation(pa1)==false){
            SetupErrorField(ERR_PASSWORD_ALPHBATE_NUMBER);
            return false;
        }

        if (pa2.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (!pa1.equalsIgnoreCase(pa2)) {
            SetupErrorField(ERR_PASSWORD_MATCH);
            return false;
        }

        return true;

    }

    private void G22_executeJsonData() {
        if (PAGEFLAG.equalsIgnoreCase("G02P20")) {
            new JSONParse().execute();
        } else {
            SetupToView();
        }
    }

    private void SetupToVariable() {

        if (la_rd_a1.isChecked()) {
            MMBRSHPFLAG = "Y";
        } else {
            MMBRSHPFLAG = "N";
        }

        FMLYNAME = la_familyName.getText().toString();
        FRSTNAME = la_firstName.getText().toString();
        PHNNMBR = la_phoneNumber.getText().toString();
        PCEMLADDRSS1 = la_mail1.getText().toString();
        PCEMLADDRSS2 = la_mail2.getText().toString();
        LOGIN_PASSWORD = la_password1.getText().toString();
        PASSWORD2 = la_password2.getText().toString();
    }

    //Country
    private AdapterView.OnItemSelectedListener Spinner1_OnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair) la_country.getSelectedItem();
            NTNLTYCODE = item.getKey().toString();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //Gender
    private AdapterView.OnItemSelectedListener Spinner2_Gender = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair) la_gender.getSelectedItem();
            SEX = item.getKey().toString();
            Toast.makeText(G22P29A22CustomerInfoUpdateSetp1.this, item.getKey().toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    private void SetupToView() {
        addCross(getApplicationContext(), la_familyName);
        addCross(getApplicationContext(), la_familyName);
        addCross(getApplicationContext(), la_firstName);
        addCross(getApplicationContext(), la_phoneNumber);
        addCross(getApplicationContext(), la_mail1);
        addCross(getApplicationContext(), la_mail2);
        addCross(getApplicationContext(), la_password1);
        addCross(getApplicationContext(), la_password2);

        if (!MMBRSHPFLAG.isEmpty() && MMBRSHPFLAG.equalsIgnoreCase("Y")) {
            la_rd_a1.setChecked(true);
            la_rd_a2.setChecked(false);
        } else {
            la_rd_a1.setChecked(false);
            la_rd_a2.setChecked(true);
        }

        la_rd_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_rd_a1.setChecked(true);
                la_rd_a2.setChecked(false);
            }
        });

        la_rd_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_rd_a1.setChecked(false);
                la_rd_a2.setChecked(true);
            }
        });

        if (!FMLYNAME.isEmpty()) {
            la_familyName.setText(FMLYNAME);
        }

        if (!FRSTNAME.isEmpty()) {
            la_firstName.setText(FRSTNAME);
        }

        //membership Card
        String countryCode = new String();
        if (!NTNLTYCODE.isEmpty()) {
            countryCode = NTNLTYCODE;
        } else {
            countryCode = "JPN";
        }

        la_country.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);
        la_country.setPrompt("国籍");
        KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter = ComLib.getCountryKeyValueToAdapter(adapter);
        la_country.setAdapter(adapter);
        la_country.setSelection(adapter.getPosition(countryCode));

        if (!PHNNMBR.isEmpty()) {
            la_phoneNumber.setText(PHNNMBR);
        }

        if (!PCEMLADDRSS1.isEmpty()) {
            la_mail1.setText(PCEMLADDRSS1);
        //    la_mail2.setText(PCEMLADDRSS1);
        }


        //membership Card
        String gender = new String();
        if (!SEX.isEmpty()) {
            gender = SEX;
        } else {
            gender = "M";
        }

        la_gender.setOnItemSelectedListener(Spinner2_Gender);
        la_gender.setPrompt("性別");
        KeyValuePairArrayAdapter adapter_gender = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter_gender = ComLib.getGenderKeyValueToAdapter(adapter_gender);
        la_gender.setAdapter(adapter_gender);
        la_gender.setSelection(adapter.getPosition(gender));

        if (!PHNNMBR.isEmpty()) {
            la_phoneNumber.setText(PHNNMBR);
        }

        if (!LOGIN_PASSWORD.isEmpty()) {
            la_password1.setText(LOGIN_PASSWORD);
       //     la_password2.setText(LOGIN_PASSWORD);
        }
    }

    public void SetupToParcel() {
        obj_g01.setCustLgnId(LOGIN_ID);
        obj_g01.setCustLgnPsswrd(LOGIN_PASSWORD);
        obj_g01.setCustDateBirth(DATEBIRTH);
        obj_g01.setCustFmlyName(FMLYNAME);
        obj_g01.setCustFrstName(FRSTNAME);
        obj_g01.setCustMmbrshpFlag(MMBRSHPFLAG);
        obj_g01.setCustNtnltyCode(NTNLTYCODE);
        obj_g01.setCustNwslttr(NWSLTTR);
        obj_g01.setCustPcEmlAddrss(PCEMLADDRSS1);
        obj_g01.setCustPhnNmbr(PHNNMBR);
        obj_g01.setCustPsswrd("");
        obj_g01.setCustSex(SEX);
        obj_g01.setCustRsrvsPrsnUid(RSRVSPRSNUID);
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A022();
        }

        private void setApiRequestData_A022() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            Log.e(ComLogMsg.PARAM_G22P29, api.getRequestDataA022().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G22P29A22CustomerInfoUpdateSetp1.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA022());
            JSONObject json = jParser.getJSONData(api.getURLA022());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G22P29, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            LOGIN_ID = json.optString(ComConstant.CT_LGNID);
            LOGIN_PASSWORD = json.optString(ComConstant.CT_LGNPSSWRD);
            RSRVSPRSNUID = json.optString(ComConstant.CT_RSRVSPRSNUID);
            PRCSSNGTYPE = json.optString(ComConstant.CT_PRCSSNGTYPE);
            FMLYNAME = json.optString(ComConstant.CT_FMLYNAME);
            FRSTNAME = json.optString(ComConstant.CT_FRSTNAME);
            DATEBIRTH = json.optString(ComConstant.CT_DATEBIRTH);
            SEX = json.optString(ComConstant.CT_SEX);
            PHNNMBR = json.optString(ComConstant.CT_PHNNMBR);
            NTNLTYCODE = json.optString(ComConstant.CT_NTNLTYCODE);
            MMBRSHPFLAG = json.optString(ComConstant.CT_MMBRSHPFLAG);
            PCEMLADDRSS1 = json.optString(ComConstant.CT_PCEMLADDRSS);
            NWSLTTR = json.optString(ComConstant.CT_NWSLTTR);
            PASSDELFLG = "N";
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToView();
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
