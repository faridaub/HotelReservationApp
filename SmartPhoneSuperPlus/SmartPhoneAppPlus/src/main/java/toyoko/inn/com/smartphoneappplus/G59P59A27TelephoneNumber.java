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
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G59P59A27TelephoneNumber extends Activity {
    private G01P01ParcelableData obj_g01;

    private EditText la_dob;
    private EditText la_phone_number;
    private EditText la_first_name;

    private String MMBRSHPNMBR;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private String ERROR_CODE;
    private String DATEBIRTH;
    private String PHONE_NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G59P59A27TelephoneNumber------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g59_p59_telephone_num);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //<<==Back To Previous Page
        BackTo();

        //==Check and Go To Json Data
        G00P42_checkAndGoToJsonExecution();

    }

    private void G58P58_goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            obj_g01.setErrrMssg(ComMsg.ERR_CONNECTION);
            goTo(ComActivity.class, ComMsg.COD_NORMAL);
        }
    }

    private void G00P42_checkAndGoToJsonExecution() {
        Button button = (Button) findViewById(R.id.g59_p59_action);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetupValidation()) {
                    SetupToVariable();
                    G58P58_goToJsonParse();
                } else {
                    SetupErrorField();
                    errorPopup(null, ComMsg.ERR_G59P59MSG);
                }
            }
        });
    }

    private void SetupErrorField() {
        la_phone_number.setBackgroundResource(R.drawable.util_textview_bk_pink);
        if (la_phone_number.getText().toString().isEmpty()) {
            la_phone_number.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
    }

    private boolean SetupValidation() {
        boolean data = true;

        if (la_phone_number.getText().toString().isEmpty()) {
            data = false;
        }
        return data;
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FAMILY_NAME = obj_g01.getCustFmlyName();
            }

            if (!obj_g01.getCustFrstName().isEmpty()) {
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustDateBirth().isEmpty()) {
                DATEBIRTH = obj_g01.getCustDateBirth();
            }
        }
    }

    private void SetupDefultConfiguration() {
        la_phone_number = (EditText) findViewById(R.id.g59_p59_phone_number);
        this.MMBRSHPNMBR = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.ERROR_CODE = new String();
        this.DATEBIRTH = new String();
        this.PHONE_NUMBER = new String();
    }

    private void BackTo() {
        Button button = (Button) findViewById(R.id.g59_p59_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G50P26A32Login.class, ComMsg.COD_NEXT);
            }
        });
    }

    private void SetupToVariable() {
        PHONE_NUMBER = la_phone_number.getText().toString();
    }


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
            api.setPhnNmbr(PHONE_NUMBER);
            Log.e(ComLogMsg.PARAM_G59P59, api.getRequestDataA027().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G59P59A27TelephoneNumber.this);
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
            Log.e(ComLogMsg.JSON_G59P59, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            obj_g01.setCustRsrvsPrsnUid(json.optString(ComConstant.CT_RSRVSPRSNUID));
            obj_g01.setCustFmlyName(json.optString(ComConstant.CT_FMLYNAME));
            obj_g01.setCustFrstName(json.optString(ComConstant.CT_FRSTNAME));
            obj_g01.setCustDateBirth(json.optString(ComConstant.CT_DATEBIRTH));
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
            if (login_id.equalsIgnoreCase("") || login_id.isEmpty()) {
                goTo(G53P53A33MissEmailPassEntryForm.class, ComMsg.COD_NEXT);
            } else {
                goTo(G52P52A00LoginDifferentOption.class, ComMsg.COD_NEXT);
            }
            processDialog.dismiss();
        }
        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }


    private void finish(final Class myClass, String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase(ComMsg.COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
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

    //goTo
    //----------------------------------------------------------------------------------------------
    private void goTo(final Class myClass, String forwordState) {
        Intent intent = intent = new Intent(getApplicationContext(), myClass);
        intent.putExtra("DATA", obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase("back")) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase("next")) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

}
