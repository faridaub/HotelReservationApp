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
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComLogMsg.*;


public class G51P51A26HoldingMembershipCard extends Activity {
    private G01P01ParcelableData obj_g01;
    private Spinner la_mm_no_1;
    private String sp_data;
    private EditText ed_mm_no_2;
    private EditText ed_mm_no_3;
    private EditText ed_family_name;
    private EditText ed_first_name;
    private String MMBRSHPNMBR;
    private String FAMILY_NAME;
    private String FIRST_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G51P51A26HoldingMembershipCard------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g51_p51_initial_setting);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //== Setup To view
        SetupToView();

        //<<==Back To Previous Page
        BackToPrevious();

        //==Check and Go To Json Data
        G00P42_checkAndGoToJsonExecution();

    }

    //Country
    private AdapterView.OnItemSelectedListener Spinner1_MembershipNumber = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            KeyValuePair item = (KeyValuePair) la_mm_no_1.getSelectedItem();
            sp_data = item.getKey().toString();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    };

    private void SetupToView() {
        //membership Card
        la_mm_no_1.setOnItemSelectedListener(Spinner1_MembershipNumber);
        KeyValuePairArrayAdapter adapter = new KeyValuePairArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter = ComLib.setMembershipCardKeyValueToAdapter(adapter);
        la_mm_no_1.setAdapter(adapter);
        String selectKey = "I";
        la_mm_no_1.setSelection(adapter.getPosition(selectKey));
    }

    private void G00P42_checkAndGoToJsonExecution() {
        Button button = (Button) findViewById(R.id.g51_p51_action);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    SetupToVeriable();
                    SetupToJson();
                } else {
                    fieldError_YES();
                    errorPopup(null, ComMsg.ERR_AUTH_ERROR);
                }
            }
        });
    }

    private void SetupToVeriable() {
        StringBuilder sbMembershipNumber = new StringBuilder();
        sbMembershipNumber.append(sp_data);
        sbMembershipNumber.append(ed_mm_no_2.getText().toString());
        sbMembershipNumber.append("-");
        sbMembershipNumber.append(ed_mm_no_3.getText().toString());
        MMBRSHPNMBR = sbMembershipNumber.toString();
        FAMILY_NAME = ed_family_name.getText().toString();
        FIRST_NAME = ed_first_name.getText().toString();
    }

    private void fieldError_YES() {
        if (ed_mm_no_3.getText().toString().isEmpty()) {
            ed_mm_no_3.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ed_mm_no_2.getText().toString().isEmpty()) {
            ed_mm_no_2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ed_family_name.getText().toString().isEmpty()) {
            ed_family_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (ed_first_name.getText().toString().isEmpty()) {
            ed_first_name.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
    }

    private boolean checkValidation() {
        boolean data = true;
        if (la_mm_no_1.getSelectedItem().toString().isEmpty()) {
            data = false;
        }
        if (ed_mm_no_2.getText().toString().isEmpty()) {
            data = false;
        }

        if (ed_mm_no_3.getText().toString().isEmpty()) {
            data = false;
        }

        if (ed_family_name.getText().toString().isEmpty()) {
            data = false;
        }

        if (ed_first_name.getText().toString().isEmpty()) {
            data = false;
        }
        return data;
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);
        }
    }

    private void SetupDefultConfiguration() {
        this.la_mm_no_1 = (Spinner) findViewById(R.id.g51_p51_mn_1);
        this.sp_data = new String();
        this.ed_mm_no_2 = (EditText) findViewById(R.id.g51_p51_mn_2);
        this.ed_mm_no_3 = (EditText) findViewById(R.id.g51_p51_mn_3);
        this.ed_family_name = (EditText) findViewById(R.id.g51_p51_family_name);
        this.ed_first_name = (EditText) findViewById(R.id.g51_p51_first_name);
        this.MMBRSHPNMBR = new String();
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
    }

    private void BackToPrevious() {
        Button button = (Button) findViewById(R.id.g51_p51_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G80P80A00LoginInitial.class, ComMsg.COD_BACK);
            }
        });
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String login_id = new String();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataBeforeJsonWorkA026();
        }

        private void setDataBeforeJsonWorkA026() {
            api.setMmbrshpNmbr(MMBRSHPNMBR);
            api.setFmlyName(FAMILY_NAME);
            api.setFrstName(FIRST_NAME);
            Log.e(PARAM_G51P51, api.getRequestDataA026().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G51P51A26HoldingMembershipCard.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA026());
            JSONObject json = jParser.getJSONData(api.getURLA026());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(JSON_G51P51, json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            login_id = json.optString(ComConstant.CT_LGNID);
            obj_g01.setCustLgnId(login_id);
            obj_g01.setCustLgnPsswrd(json.optString(ComConstant.CT_LGNPSSWRD));
            obj_g01.setCustFmlyName(json.optString(ComConstant.CT_FMLYNAME));
            obj_g01.setCustFrstName(json.optString(ComConstant.CT_FRSTNAME));
            obj_g01.setCustDateBirth(json.optString(ComConstant.CT_DATEBIRTH));
            obj_g01.setCustSex(json.optString(ComConstant.CT_SEX));
            obj_g01.setCustNtnltyCode(json.optString(ComConstant.CT_NTNLTYCODE));
            obj_g01.setCustPhnNmbr(json.optString(ComConstant.CT_PHNNMBR));
            obj_g01.setCustPcEmlAddrss(json.optString(ComConstant.CT_PCEMLADDRSS));
            obj_g01.setCustMmbrshpNmbr(json.optString(ComConstant.CT_MMBRSHPNMBR));
            obj_g01.setCustMbEmlAddrss(json.optString(ComConstant.CT_EMLADDRSS2));
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
            if (errorCode.equalsIgnoreCase("BGNL0001")) {
                errorMessage = ComMsg.ERR_ERROR_OCCUR;
            }
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
        } else {
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }

}
