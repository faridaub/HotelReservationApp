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
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_EMPTY_FILD_DOB;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_EMPTY_FILD_FAMILY;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_EMPTY_FILD_FIRST;


public class G58P58A27OftenKnownMembership extends Activity {
    private G01P01ParcelableData obj_g01;

    private EditText la_dob;
    private EditText la_family_name;
    private EditText la_first_name;

    private String MMBRSHPNMBR;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String DATEBIRTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G58P58A27OftenKnownMembership------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g58_p58_often_known);

        //Default Configuration
        SetupDefultConfiguration();

        //Get Data From Previous Page
        GetData();

        //Setup To View
        SetupToView();

        //Back To Previous Page
        BackToG00P26();

        //Check and Go To Json Data
        G00P42_checkAndGoToJsonExecution();

    }

    private void SetupToView() {
        addCross(getApplicationContext(), la_family_name);
        addCross(getApplicationContext(), la_first_name);
        addCross(getApplicationContext(), la_dob);
    }

    private void G00P42_checkAndGoToJsonExecution() {
        Button button = (Button) findViewById(R.id.g58_p58_action);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    SetupToVariable();
                    SetupToJson();
                }
            }
        });
    }

    private void layoutError(String errorMessage) {
        String family = la_family_name.getText().toString();
        String first = la_first_name.getText().toString();
        String dob = la_dob.getText().toString();

        if(family.isEmpty()){
            la_family_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if(first.isEmpty()){
            la_first_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if(dob.isEmpty()){
            la_dob.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (dob.length() < 8) {
            la_dob.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        errorPopup(null, errorMessage);

    }


    private boolean SetupValidation() {
        String family = la_family_name.getText().toString();
        String first = la_first_name.getText().toString();
        String dob = la_dob.getText().toString();

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
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
        }
    }

    private void SetupDefultConfiguration() {
        la_family_name = (EditText) findViewById(R.id.g58_p58_family_name);
        la_first_name = (EditText) findViewById(R.id.g58_p58_first_name);
        la_dob = (EditText) findViewById(R.id.g58_p58_dob);

        this.MMBRSHPNMBR = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.DATEBIRTH = new String();
    }

    private void BackToG00P26() {
        Button button = (Button) findViewById(R.id.g58_p58_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G80P80A00LoginInitial.class,ComMsg.COD_BACK);
            }
        });
    }

    private void SetupToVariable(){
        FAMILY_NAME = la_family_name.getText().toString();
        FIRST_NAME = la_first_name.getText().toString();
        DATEBIRTH = la_dob.getText().toString();
    }

    //Json
    //----------------------------------------------------------------------------------------------
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String login_id = new String();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataA27();
        }

        private void setDataA27() {
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            api.setDateBirth(DATEBIRTH);
            api.setPhnNmbr("");
            Log.e("PARAM-G58P58", api.getRequestDataA027().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G58P58A27OftenKnownMembership.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA027());
            JSONObject json = jParser.getJSONData(api.getURLA027());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G58P58", json.toString());
            errorCode = json.optString(ComConstant.CT_ERRRCODE);
            errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
            if(!ComLib.isDataBGNL0004(json.optString(ComConstant.CT_ERRRCODE))) {
                if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                    processDialog.dismiss();
                    cancel(true);
                }
            }
            obj_g01.setCustRsrvsPrsnUid(json.optString(ComConstant.CT_RSRVSPRSNUID));
            obj_g01.setCustSex(json.optString(ComConstant.CT_SEX));
            obj_g01.setCustNtnltyCode(json.optString(ComConstant.CT_NTNLTYCODE));
            obj_g01.setCustPhnNmbr(json.optString(ComConstant.CT_PHNNMBR));
            obj_g01.setCustMmbrshpFlag(json.optString(ComConstant.CT_MMBRSHPFLAG));
            obj_g01.setCustMmbrshpNmbr(json.optString(ComConstant.CT_MMBRSHPNMBR));
            obj_g01.setCustPcEmlAddrss(json.optString(ComConstant.CT_PCEMLADDRSS));
            obj_g01.setCustPcEmlAddrss(json.optString(ComConstant.CT_NWSLTTR));
            obj_g01.setCustMbEmlAddrss(json.optString(ComConstant.CT_MBLEMLADDRSS));
            login_id = json.optString(ComConstant.CT_LGNID);
            obj_g01.setCustLgnId(login_id);
            obj_g01.setCustLgnPsswrd(json.optString(ComConstant.CT_LGNPSSWRD));
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToParcel();
            if (errorCode.equalsIgnoreCase(ComConstant.CT_ERRRCODE_BGNL0004)) {
                goTo(G59P59A27TelephoneNumber.class, "next");
            } else {
                if (login_id.equalsIgnoreCase("") || login_id.isEmpty()) {
                    goTo(G53P53A33MissEmailPassEntryForm.class, ComMsg.COD_NEXT);
                } else {
                    goTo(G52P52A00LoginDifferentOption.class, ComMsg.COD_NEXT);
                }
            }
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BGNL0001")){
                errorMessage = ComMsg.ERR_AUTH_ERROR;
            }
            errorPopup(null, errorMessage);
        }
    }

    private void SetupToParcel(){
        obj_g01.setCustFmlyName(FAMILY_NAME);
        obj_g01.setCustFrstName(FIRST_NAME);
        obj_g01.setCustDateBirth(DATEBIRTH);
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
