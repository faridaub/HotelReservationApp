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
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G61P61A00UserRegisSetp2 extends Activity {
    G01P01ParcelableData obj_g01;
    private String RSRVSPRSNUID;
    private String PRCSSNGTYPE;
    private String FMLYNAME;
    private String FRSTNAME;
    private String DOB;
    private String SEX;
    private String PHNNMBR;
    private String MMBRSHPFLAG;
    private String MAIL1USERID;
    private String PCEMLADDRSS2;
    private String PASSWORD;
    private String PASSWORD2;
    private String PASSDELFLG;
    private String NWSLTTR;

    private TextView la_family_name;
    private TextView la_frist_name;
    private TextView la_sex;
    private TextView la_dob;
    private TextView la_mail1_userid;
    private TextView la_mail2;
    private TextView la_country;
    private TextView la_password;
    private TextView la_password2;
    private TextView la_phone_number;
    private TextView la_membership;
    private TextView la_newsletter;
    private TextView la_membership_number;
    private String MMBRSHPNMBR;
    private String COUNTRY;
    private String MAIL2;
    private Dialog la_popupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G61P61A00UserRegisSetp2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g61_p61_user_registration_ur_2);

        //==Setup Configuration
        SetupConfiguration();

        //==<<Get Parceable Data
        G23_GetDataFrom_G22();

        //Setup To View
        SetupToView();

        //GoTo Registration Page
        G61P61_goToRegistration();

        //Back To Previous Page
        G61P61_backTo();

    }

    private void G61P61_backTo() {
        Button button = (Button)findViewById(R.id.g61_p61_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void G61P61_goToRegistration() {
        Button button = (Button)findViewById(R.id.g61_p61_registration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToJson();
            }
        });
    }


    private void SetupConfiguration() {

        RSRVSPRSNUID = new String();
        PRCSSNGTYPE = new String();
        FMLYNAME = new String();
        FRSTNAME = new String();
        DOB = new String();
        SEX = new String();
        PHNNMBR = new String();
        MMBRSHPFLAG = new String();
        MAIL1USERID = new String();
        PCEMLADDRSS2 = new String();
        NWSLTTR = new String();
        PASSWORD = new String();
        PASSWORD2 = new String();
        PASSDELFLG = new String();
        MMBRSHPNMBR = new String();
        COUNTRY = new String();
        MAIL2 = new String();

        this.la_popupDialog = new Dialog(this);
        la_mail1_userid = (TextView) findViewById(R.id.g61_p61_mail1_userid);
        la_password = (TextView) findViewById(R.id.g61_p61_password);
        la_membership = (TextView) findViewById(R.id.g61_p61_membership_flag);
        la_membership_number = (TextView) findViewById(R.id.g61_p61_membership_number);
        la_family_name = (TextView) findViewById(R.id.g61_p61_family_name);
        la_frist_name = (TextView) findViewById(R.id.g61_p61_first_name);
        la_dob = (TextView) findViewById(R.id.g61_p61_dob);
        la_sex = (TextView) findViewById(R.id.g61_p61_sex);
        la_country = (TextView) findViewById(R.id.g61_p61_country);
        la_phone_number = (TextView) findViewById(R.id.g61_p61_phone_number);
        la_mail2 = (TextView) findViewById(R.id.g61_p61_mail2);
        la_newsletter = (TextView) findViewById(R.id.g61_p61_newsletter_flag);

    }
    private void G23_GetDataFrom_G22() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);

            if (!obj_g01.getCustLgnId().isEmpty()) {
                MAIL1USERID = obj_g01.getCustLgnId();
            }
            if (!obj_g01.getCustPsswrd().isEmpty()) {
                PASSWORD = obj_g01.getCustPsswrd();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MMBRSHPFLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FMLYNAME = obj_g01.getCustFmlyName();
            }

            if (!obj_g01.getCustFrstName().isEmpty()) {
                FRSTNAME = obj_g01.getCustFrstName();
            }
            if (!obj_g01.getCustNtnltyCode().isEmpty()) {
                COUNTRY = obj_g01.getCustNtnltyCode();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustDateBirth().isEmpty()) {
                DOB = obj_g01.getCustDateBirth();
            }

            if (!obj_g01.getCustMbEmlAddrss().isEmpty()) {
                MAIL2 = obj_g01.getCustMbEmlAddrss();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHNNMBR = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getCustNwslttr().isEmpty()) {
                NWSLTTR = obj_g01.getCustNwslttr();
            }
        }
    }
    private void SetupToView() {

        la_mail1_userid.setText(MAIL1USERID);
        la_password.setText("ｾｷｭﾘﾃｨの観点から表示しません");

        la_membership.setText("東横INN一般");
        if (MMBRSHPFLAG.equalsIgnoreCase("Y")) {
            la_membership.setText("東横INNクラブカード会員");
        }
        la_membership_number.setText(MMBRSHPNMBR);

        la_dob.setText(DOB);

        if (SEX.equalsIgnoreCase("Y")) {
            la_sex.setText("男性");
        } else {
            la_sex.setText("女性");
        }
        la_family_name.setText(FMLYNAME);
        la_frist_name.setText(FRSTNAME);
        la_country.setText(COUNTRY);

        if (NWSLTTR.equalsIgnoreCase("Y")) {
            la_newsletter.setText("はい");
        } else {
            la_newsletter.setText("いいえ");
        }

        la_phone_number.setText(PHNNMBR);
        la_mail2.setText(MAIL2);
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataBeforeJsonWorkA34();
        }

        private void setDataBeforeJsonWorkA34() {
            api.setPrcssngType("3");   //3:初めての方
            api.setRsrvsPrsnUid("");
            api.setLgnId(MAIL1USERID);
            api.setLgnPsswrd(PASSWORD);
            api.setMmbrshpFlag(MMBRSHPFLAG);
            api.setMmbrshpNmbr("");
            api.setFmlyName(FMLYNAME);
            api.setFrstName(FRSTNAME);
            api.setDateBirth(DOB);
            api.setNtnltyCode(COUNTRY);
            api.setSex(SEX);
            api.setPhnNmbr(PHNNMBR);
            api.setEmlAddrss(MAIL1USERID);
            api.setNwslttr(NWSLTTR);
            api.setEmlAddrss2(MAIL2);
            Log.e("PARAM-G61P61", api.getRequestDataA034().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G61P61A00UserRegisSetp2.this);
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
            Log.e("JSON-G61P61", json.toString());
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
            goTo(G62P62A00UserRegisSetp3.class,"next");
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

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }

}
