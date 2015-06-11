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
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G23P30A23CustomerInfoUpdateSetp2 extends Activity {
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

    private TextView la_family_name;
    private TextView la_frist_name;
    private TextView la_sex;
    private TextView la_birth_date;
    private TextView la_email1;
    private TextView la_email2;
    private TextView la_country;
    private TextView la_password1;
    private TextView la_password2;
    private TextView la_phone_number;
    private TextView la_membership;
    private TextView la_newsletter;
    private String LOGIN_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G23P30A23CustomerInfoUpdateSetp2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g23_p30_customer_update_setp2);

        //==Setup Configuration
        SetupConfiguration();

        //==<<Get Parceable Data
        GetData();

        //==Reload Before Action
        SetupToView();

        //<<==Back To Account Page
        BackToPage();

        //==>>Go To Next Page
        GoToPage();


    }

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_CONNECTION);
        }
    }

    private void BackToPage() {
        final Button button = (Button)findViewById(R.id.g23_p30_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void GoToPage() {
        final Button button = (Button)findViewById(R.id.g23_p30_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToJson();
            }
        });
    }

    private void SetupToView() {
        la_membership.setText(ComMsg.MSG_TOYOKO_INN_GENERAL);
        if(MMBRSHPFLAG.equalsIgnoreCase(ComMsg.COD_Y)){
           la_membership.setText(ComMsg.MSG_TOYOKO_INN_GROUPCARD);
        }
        la_birth_date.setText(DATEBIRTH);
        la_email1.setText(PCEMLADDRSS1);
        la_sex.setText(ComLib.getSex(SEX));
        la_family_name.setText(FMLYNAME);
        la_frist_name.setText(FRSTNAME);
        la_country.setText(ComLib.getCountryValueAccCode(NTNLTYCODE));


        if(NWSLTTR.equalsIgnoreCase(ComMsg.COD_Y)){
            la_newsletter.setText(ComMsg.SW_YES);
        }else{
            la_newsletter.setText(ComMsg.SW_NO);
        }

        la_password1.setText(ComMsg.MSG_PASSWORD_HIDE);
        la_password2.setText(ComMsg.MSG_PASSWORD_HIDE);

        la_phone_number.setText(PHNNMBR);
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");

            if(!obj_g01.getCustLgnId().isEmpty()){
                LOGIN_ID = obj_g01.getCustLgnId();
            }

            if (!obj_g01.getCustLgnPsswrd().isEmpty()) {
                LOGIN_PASSWORD = obj_g01.getCustLgnPsswrd();
            }

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FMLYNAME = obj_g01.getCustFmlyName();
            }
            if (!obj_g01.getCustFrstName().isEmpty()) {
                FRSTNAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustNtnltyCode().isEmpty()) {
                NTNLTYCODE = obj_g01.getCustNtnltyCode();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
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

            if (!obj_g01.getCustPcEmlAddrss().isEmpty()) {
                PCEMLADDRSS1 = obj_g01.getCustPcEmlAddrss();
            }

            if (!obj_g01.getCustNwslttr().isEmpty()) {
                NWSLTTR = obj_g01.getCustNwslttr();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHNNMBR = obj_g01.getCustPhnNmbr();
            }
        }
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
        LOGIN_ID = new String();

        la_membership = (TextView)findViewById(R.id.g23_p30_membership_flag);
        la_family_name = (TextView)findViewById(R.id.g23_p30_family_name);
        la_frist_name = (TextView)findViewById(R.id.g23_p30_first_name);
        la_sex = (TextView)findViewById(R.id.g23_p30_sex);
        la_birth_date = (TextView)findViewById(R.id.g23_p30_birth_date);
        la_email1 = (TextView)findViewById(R.id.g23_p30_email_address_1);
        la_email2 = (TextView)findViewById(R.id.g23_p30_email_address_2);
        la_country = (TextView)findViewById(R.id.g23_p30_natiality);
        la_password1 = (TextView)findViewById(R.id.g23_p30_password_1);
        la_password2 = (TextView)findViewById(R.id.g23_p30_password_2);
        la_phone_number = (TextView)findViewById(R.id.g23_p30_phoneNumber);
        la_newsletter = (TextView)findViewById(R.id.g23_p30_newsletter);
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setApiRequestData_A023();
        }

        private void setApiRequestData_A023() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPrcssngType(ComInitData.ST_ONE); //1:登録・更新
            api.setFmlyName(FMLYNAME);
            api.setFrstName(FRSTNAME);
            api.setDateBirth(DATEBIRTH);
            api.setSex(SEX);
            api.setNtnltyCode(NTNLTYCODE);
            api.setPhnNmbr(PHNNMBR);
            api.setMmbrshpFlag(MMBRSHPFLAG);
            api.setMmbrshpNmbr(ComInitData.ST_NULL); //Not Must Item
            api.setPcEmlAddrss(PCEMLADDRSS1);
            api.setNwslttr(NWSLTTR);
            api.setPsswrd(ComInitData.ST_NULL);  //Not Must Item
            api.setLgnId(LOGIN_ID);
            api.setLgnPsswrd(LOGIN_PASSWORD);

            Log.e(ComLogMsg.PARAM_G23P30, api.getRequestDataA023().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G23P30A23CustomerInfoUpdateSetp2.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA023());
            JSONObject json = jParser.getJSONData(api.getURLA023());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G23P30, json.toString());
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
            goTo(G23P38A23CustomerInfoUpdateSetp3.class, ComMsg.COD_NEXT);
            processDialog.dismiss();
        }
        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }

    private void errorPopup(String eCode, String eMessage) {
        StringBuilder sb = new StringBuilder();
        if (eCode != null) {
            sb.append("[" + eCode + "]");
        }
        sb.append("エラー");

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(sb);
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
