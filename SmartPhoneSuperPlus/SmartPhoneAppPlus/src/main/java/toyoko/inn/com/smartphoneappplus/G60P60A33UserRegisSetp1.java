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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePair;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePairArrayAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G60P60A33UserRegisSetp1 extends Activity {
    G01P01ParcelableData obj_g01;

    private String RSRVSPRSNUID;
    private String PRCSSNGTYPE;
    private String FMLYNAME;
    private String FRSTNAME;
    private String DATEBIRTH;
    private String SEX;
    private String COUNTRY;
    private String PHNNMBR;
    private String MMBRSHPFLAG;
    private String MAIL1USERID;
    private String MAIL2;
    private String PASSWORD1;
    private String PASSWORD2;
    private String PASSDELFLG;
    private String NWSLTTR;
    private String PAGEFLAG;
    private RadioButton rd_a1;
    private RadioButton rd_a2;
    private EditText la_familyName;
    private EditText la_firstName;
    private EditText la_mail1_userid;
    private EditText la_mail_2;
   // private RadioButton la_sex_1;
  //  private RadioButton la_sex_2;
    private EditText la_password_1;
    private EditText la_password_2;
    private EditText password2;
    private Spinner la_gender;
    private Spinner la_country;
    private EditText la_phoneNumber;
    private ToggleButton la_nwslttr;
    private EditText la_mail1_userid_confirm;
    private EditText la_dob;
    private Dialog la_popupDialog;
    private String MAIL1CONFIRM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G60P60A33UserRegisSetp1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g60_p60_user_registration_ur_1);

        SetupConfiguration();

        GetData();

        SetupToView();

        GoBack();

        G60P60_goToConfirmPage();


    }

    private void SetupConfiguration() {

        this.RSRVSPRSNUID = new String();
        this.PRCSSNGTYPE = new String();
        this.FMLYNAME = new String();
        this.FRSTNAME = new String();
        this.DATEBIRTH = new String();
        this.SEX = new String();
        this.COUNTRY = new String();
        this.PHNNMBR = new String();
        this.MMBRSHPFLAG = new String();
        this.MAIL1USERID = new String();
        this.MAIL1CONFIRM = new String();
        this.MAIL2 = new String();
        this.NWSLTTR = new String();
        this.PASSWORD1 = new String();
        this.PASSWORD2 = new String();
        this.PASSDELFLG = new String();
        this.PAGEFLAG = new String();

        this.la_popupDialog = new Dialog(this);
        this.la_mail1_userid = (EditText) findViewById(R.id.g60_p60_mail1_userid);
        this.la_mail1_userid_confirm = (EditText) findViewById(R.id.g60_p60_mail1_userid_confirm);
        this.la_password_1 = (EditText) findViewById(R.id.g60_p60_password1);
        this.la_password_2 = (EditText) findViewById(R.id.g60_p60_password2);
        this.la_familyName = (EditText) findViewById(R.id.g60_p60_family_name);
        this.la_firstName = (EditText) findViewById(R.id.g60_p60_first_name);
        this.la_dob = (EditText) findViewById(R.id.g60_p60_dob);
/*        this.la_sex_1 = (RadioButton) findViewById(R.id.g60_p60_sex_1);
        this.la_sex_2 = (RadioButton) findViewById(R.id.g60_p60_sex_2);*/
        this.la_gender = (Spinner) findViewById(R.id.g60_p60_gender);
        this.la_country = (Spinner) findViewById(R.id.g60_p60_national);
        this.la_phoneNumber = (EditText) findViewById(R.id.g60_p60_phone_number);
        this.la_mail_2 = (EditText) findViewById(R.id.g60_p60_email_2);
        this.la_nwslttr = (ToggleButton) findViewById(R.id.g60_p60_newsletter);
        /*
        this.la_nwslttr_2 = (RadioButton) findViewById(R.id.g60_p60_newsletter_2);*/

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGEFLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FMLYNAME = obj_g01.getCustFmlyName();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getCustFrstName().isEmpty()) {
                FRSTNAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustDateBirth().isEmpty()) {
                DATEBIRTH = obj_g01.getCustDateBirth();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MMBRSHPFLAG = obj_g01.getCustMmbrshpFlag();
            }

        }
    }

    private void GoBack() {
        final Button button = (Button) findViewById(R.id.g60_p60_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G80P80A00LoginInitial.class, COD_BACK);
            }
        });
    }

    private void G60P60_goToConfirmPage() {
        final Button button = (Button) findViewById(R.id.g22p29_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToVariable();
                if (SetupValidation()) {
                    SetupToParcel();
                    new JSONParse().execute();
                }
            }
        });
    }

    private void G60P60_executeJsonData() {
        if (PAGEFLAG.equalsIgnoreCase("G02P20")) {
            new JSONParse().execute();
        } else {
            SetupToParcel();
        }
    }

    //Gender
    private AdapterView.OnItemSelectedListener Spinner1_Gender = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair)la_gender.getSelectedItem();
            SEX = item.getKey().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    //Country
    private AdapterView.OnItemSelectedListener Spinner1_OnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair)la_country.getSelectedItem();
            COUNTRY = item.getKey().toString();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    private void SetupToView() {

        addCross(getApplicationContext(), la_mail1_userid);
        addCross(getApplicationContext(), la_mail1_userid_confirm);
        addCross(getApplicationContext(), la_familyName);
        addCross(getApplicationContext(), la_firstName);
        addCross(getApplicationContext(), la_dob);
        addCross(getApplicationContext(), la_mail_2);
        addCross(getApplicationContext(), la_phoneNumber);

        //Country
        la_country.setOnItemSelectedListener(Spinner1_OnItemSelectedListener);
        KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter =  getCountryKeyValueToAdapter(adapter);
        la_country.setAdapter(adapter);
        la_country.setPrompt("国籍");
        COUNTRY = "JPN";
        la_country.setSelection(adapter.getPosition(COUNTRY));



        //Gender
        la_gender.setOnItemSelectedListener(Spinner1_Gender);
        KeyValuePairArrayAdapter adapter_gender = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter_gender =  getCountryKeyValueToAdapter(adapter_gender);
        la_gender.setAdapter(adapter_gender);
        la_gender.setPrompt("性別");
        SEX = "M";
        la_gender.setSelection(adapter.getPosition(SEX));

/*        //Sex
        la_sex_1.setChecked(true);
        la_sex_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_sex_1.setChecked(true);
                la_sex_2.setChecked(false);
            }
        });


        la_sex_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_sex_2.setChecked(true);
                la_sex_1.setChecked(false);
            }
        });*/




/*
        //Membership Flag
        if (rd_a1.isChecked()) {
            MMBRSHPFLAG = "Y";
        }

        //Family Name
        if (!la_familyName.getText().toString().isEmpty()) {
            FMLYNAME = la_familyName.getText().toString();
        }

        //First name
        if (!la_firstName.getText().toString().isEmpty()) {
            FRSTNAME = la_firstName.getText().toString();
        }

        //Phone Number
        if (!la_phoneNumber.getText().toString().isEmpty()) {
            PHNNMBR = la_phoneNumber.getText().toString();
        }

        //Email 1
        if (!la_mail1_userid.getText().toString().isEmpty()) {
            MAIL1USERID = la_mail1_userid.getText().toString();
        }

        if (!la_mail_2.getText().toString().isEmpty()) {
            MAIL2 = la_mail_2.getText().toString();
        }

        if (!MAIL1USERID.contentEquals(MAIL2)) {
            Log.e("Email Error", "Content Not Matched...Please Try again");
        }


        if (la_sex_1.isChecked()) {
            SEX = "M";
        } else {
            SEX = "F";
        }

        if (!la_password_1.getText().toString().isEmpty()) {
            PASSWORD1 = la_password_1.getText().toString();
        }

        if (!password2.getText().toString().isEmpty()) {
            PASSWORD2 = password2.getText().toString();
        }

        if (!la_nwslttr.isChecked()) {
            NWSLTTR = "Y";
        } else {
            NWSLTTR = "N";
        }

        if (!passdel_1.isChecked()) {
            PASSDELFLG = "Y";
        } else {
            PASSDELFLG = "N";
        }

        if (!MMBRSHPFLAG.isEmpty() && MMBRSHPFLAG.equalsIgnoreCase("Y")) {
            rd_a1.setChecked(true);
            rd_a2.setChecked(false);
        } else {
            rd_a1.setChecked(false);
            rd_a2.setChecked(true);
        }

        rd_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_a1.setChecked(true);
                rd_a2.setChecked(false);
            }
        });

        rd_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_a1.setChecked(false);
                rd_a2.setChecked(true);
            }
        });

        if (!FMLYNAME.isEmpty()) {
            la_familyName.setText(FMLYNAME);
        }

        if (!FRSTNAME.isEmpty()) {
            la_firstName.setText(FRSTNAME);
        }

        if (!PHNNMBR.isEmpty()) {
            la_phoneNumber.setText(PHNNMBR);
        }

        if (!MAIL1USERID.isEmpty()) {
            la_mail1_userid.setText(MAIL1USERID);
            la_mail_2.setText(MAIL1USERID);
        }

        if (!SEX.isEmpty() && SEX.equalsIgnoreCase("M")) {
            la_sex_1.setChecked(true);
            la_sex_2.setChecked(false);
        } else {
            la_sex_1.setChecked(false);
            la_sex_2.setChecked(true);
        }




        if (!PHNNMBR.isEmpty()) {
            la_phoneNumber.setText(PHNNMBR);
        }

        if (!PASSWORD1.isEmpty()) {
            la_password_1.setText(PASSWORD1);
            password2.setText(PASSWORD1);
        }

        if (!NWSLTTR.isEmpty() && NWSLTTR.equalsIgnoreCase("Y")) {
            la_nwslttr.setChecked(true);
            la_nwslttr_2.setChecked(false);
        } else {
            la_nwslttr.setChecked(false);
            la_nwslttr_2.setChecked(true);
        }

        la_nwslttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_nwslttr.setChecked(true);
                la_nwslttr_2.setChecked(false);
            }
        });

        la_nwslttr_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la_nwslttr.setChecked(false);
                la_nwslttr_2.setChecked(true);
            }
        });

        //Password Delete Flag
        if (!PASSDELFLG.isEmpty() && PASSDELFLG.equalsIgnoreCase("Y")) {
            passdel_1.setChecked(true);
            passdel_2.setChecked(false);
        } else {
            passdel_1.setChecked(false);
            passdel_2.setChecked(true);
        }

        passdel_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passdel_1.setChecked(true);
                passdel_2.setChecked(false);
            }
        });

        passdel_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passdel_1.setChecked(false);
                passdel_2.setChecked(true);
            }
        });
*/

    }

    private void SetupToVariable() {
        MAIL1USERID = la_mail1_userid.getText().toString();
        MAIL1CONFIRM = la_mail1_userid_confirm.getText().toString();
        PASSWORD1 = la_password_1.getText().toString();
        PASSWORD2 = la_password_2.getText().toString();
        FMLYNAME = la_familyName.getText().toString();
        FRSTNAME = la_firstName.getText().toString();
        DATEBIRTH = la_dob.getText().toString();


/*        if (la_sex_1.isChecked()) {
            SEX = "M";
        } else {
            SEX = "F";
        }*/


        PHNNMBR = la_phoneNumber.getText().toString();
        MAIL2 = la_mail_2.getText().toString();

        //Check Email Flag
        NWSLTTR = "N";
        la_nwslttr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NWSLTTR = "Y";
                }
            }
        });
    }

    private void SetupToParcel() {
        obj_g01.setCustLgnId(MAIL1USERID);
        obj_g01.setCustPsswrd(PASSWORD1);
        obj_g01.setCustFmlyName(FMLYNAME);
        obj_g01.setCustFrstName(FRSTNAME);
        obj_g01.setCustDateBirth(DATEBIRTH);
        obj_g01.setCustSex(SEX);
        obj_g01.setCustNtnltyCode(COUNTRY);
        obj_g01.setCustMmbrshpFlag(MMBRSHPFLAG);
        obj_g01.setCustNtnltyCode(COUNTRY);
        obj_g01.setCustPhnNmbr(PHNNMBR);
        obj_g01.setCustNwslttr(NWSLTTR);
        obj_g01.setCustMbEmlAddrss(MAIL2);
        obj_g01.setCustNwslttr(NWSLTTR);
    }

    private boolean SetupValidation() {

        String ma1 = la_mail1_userid.getText().toString();
        String ma1c = la_mail1_userid_confirm.getText().toString();
        String pa1 = la_password_1.getText().toString();
        String pa2 = la_password_2.getText().toString();
        String fa = la_familyName.getText().toString();
        String fi = la_firstName.getText().toString();
        String dob = la_dob.getText().toString();
        String ph1 = la_phoneNumber.getText().toString();
        String ma2 = la_mail_2.getText().toString();

        if (ma1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ma1c.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (pa1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (fi.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (fa.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ph1.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (ma2.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (!ma1c.equalsIgnoreCase(ma1)) {
            SetupErrorField(ERR_MAIL_MATCH);
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

        if(getPassValidation(pa2)==false){
            SetupErrorField(ERR_PASSWORD_ALPHBATE_NUMBER);
            return false;
        }

        if (!pa1.equalsIgnoreCase(pa2)) {
            SetupErrorField(ERR_PASSWORD_MATCH);
            return false;
        }

        if (dob.isEmpty()) {
            SetupErrorField(ERR_EMPTY_FILD);
            return false;
        }

        if (dob.length() < 8) {
            SetupErrorField(ERR_CHECK_DOB);
            return false;
        }

        if (ph1.length() < 6) {
            SetupErrorField(ERR_PHONENO_FILD);
            return false;
        }

        return true;
    }

    private void SetupErrorField(String errorMessage) {
        la_mail1_userid.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_mail1_userid_confirm.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password_1.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password_2.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_familyName.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_firstName.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_dob.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_mail_2.setBackgroundResource(R.drawable.util_textview_bk_pink);

        if (MAIL1USERID.isEmpty()) {
            la_mail1_userid.setFocusableInTouchMode(true);
            la_mail1_userid.requestFocus();
            la_mail1_userid.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_mail1_userid.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (MAIL1CONFIRM.isEmpty()) {
            la_mail1_userid_confirm.setFocusableInTouchMode(true);
            la_mail1_userid_confirm.requestFocus();
            la_mail1_userid_confirm.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_mail1_userid.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (PASSWORD1.isEmpty()) {
            la_password_1.setFocusableInTouchMode(true);
            la_password_1.requestFocus();
            la_password_1.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);

        }

        if (PASSWORD2.isEmpty()) {
            la_password_2.setFocusableInTouchMode(true);
            la_password_2.requestFocus();
            la_password_2.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (FMLYNAME.isEmpty()) {
            la_familyName.setFocusableInTouchMode(true);
            la_familyName.requestFocus();
            la_familyName.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_familyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (FRSTNAME.isEmpty()) {
            la_firstName.setFocusableInTouchMode(true);
            la_firstName.requestFocus();
            la_firstName.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_firstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (DATEBIRTH.isEmpty()) {
            la_dob.setFocusableInTouchMode(true);
            la_dob.requestFocus();
            la_dob.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_dob.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (PHNNMBR.isEmpty()) {
            la_phoneNumber.setFocusableInTouchMode(true);
            la_phoneNumber.requestFocus();
            la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_phoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (MAIL2.isEmpty()) {
            la_mail_2.setFocusableInTouchMode(true);
            la_mail_2.requestFocus();
            la_mail_2.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_mail_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (!MAIL1USERID.equalsIgnoreCase(MAIL1CONFIRM)) {
            la_mail1_userid_confirm.setFocusableInTouchMode(true);
            la_mail1_userid_confirm.requestFocus();
            la_mail1_userid.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_mail1_userid_confirm.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_mail1_userid_confirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if(getPassValidation(PASSWORD1) ==false){
            la_password_1.setFocusableInTouchMode(true);
            la_password_1.requestFocus();
            la_password_1.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if(getPassValidation(PASSWORD2) == false){
            la_password_2.setFocusableInTouchMode(true);
            la_password_2.requestFocus();
            la_password_2.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (!PASSWORD1.equalsIgnoreCase(PASSWORD2)) {
            la_password_2.setFocusableInTouchMode(true);
            la_password_2.requestFocus();
            la_password_2.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_password_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_worning, 0);
        }

        if (DATEBIRTH.length() <8) {
            la_dob.setFocusableInTouchMode(true);
            la_dob.requestFocus();
            la_dob.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_dob.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_worning, 0);
        }

        if (PHNNMBR.length() < 6) {
            la_phoneNumber.setFocusableInTouchMode(true);
            la_phoneNumber.requestFocus();
            la_phoneNumber.setBackgroundResource(R.drawable.util_textview_bk_red);
            la_phoneNumber.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_worning, 0);
        }

        errorPopup(null, errorMessage);
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataBeforeJsonWorkA33();
        }

        private void setDataBeforeJsonWorkA33() {
            api.setPrcssngType(ComInitData.ST_THREE);   //3:初めての方
            api.setRsrvsPrsnUid("");
            api.setLgnId(MAIL1USERID);
            api.setLgnPsswrd(PASSWORD1);
            api.setMmbrshpFlag(MMBRSHPFLAG);
            api.setMmbrshpNmbr("");
            api.setFmlyName(FMLYNAME);
            api.setFrstName(FRSTNAME);
            api.setDateBirth(DATEBIRTH);
            api.setNtnltyCode(COUNTRY);
            api.setSex(SEX);
            api.setPhnNmbr(PHNNMBR);
            api.setEmlAddrss(MAIL1USERID);
            api.setNwslttr(NWSLTTR);
            api.setEmlAddrss2(MAIL2);
            Log.e(ComLogMsg.PARAM_G60P60, api.getRequestDataA033().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G60P60A33UserRegisSetp1.this);
            processDialog.setMessage(MSG_PROCESSING);
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
            Log.e(ComLogMsg.JSON_G60P60, json.toString());
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            goTo(G61P61A00UserRegisSetp2.class, COD_NEXT);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BGNL0004")){
                errorMessage = ERR_LOGIN_EXISTS;
            }

            errorPopup(null, errorMessage);
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
        Intent intent = new Intent(getApplicationContext(), myClass);
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

    private void SetupToJson() {
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }
}
