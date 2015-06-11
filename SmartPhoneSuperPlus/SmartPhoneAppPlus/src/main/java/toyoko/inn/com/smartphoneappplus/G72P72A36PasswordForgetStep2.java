package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;

public class G72P72A36PasswordForgetStep2 extends Activity {
    private G01P01ParcelableData obj_g01;

    private EditText la_password1;
    private EditText la_password2;


    private String AUTH_KEY;
    private String RSRVSPRSNUID;
    private String LGNID;
    private String PASSWORD;
    private CheckBox la_display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G72P72A36PasswordForgetStep2------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g72_p72_password_forget_setp2);

        //==Default Configuration
        SetupDefultConfiguration();

        //==>>Get Data From Previous Page
        GetData();

        //Setup To View
        SetupToView();

        //<<==Back To Previous Page
        BackToPreviousPage();

        //==Check and Go To Json Data
        GoToNextPage();

    }

    private void SetupToView() {

        la_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                boolean checked = checkBox.isChecked();
                if (checked) {
                    la_password1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    la_password2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    la_password1.setInputType(129);
                    la_password2.setInputType(129);
                }
            }
        });
    }

    private void GoToNextPage() {
        Button button = (Button) findViewById(R.id.g72_p72_action);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    SetupToVariable();
                    SetupToJson();
                } else {
                    layoutError();
                    errorPopup(null, ComMsg.ERR_EMPTYCHECK);
                }
            }
        });
    }

    private void SetupToVariable() {
        PASSWORD = la_password1.getText().toString();
    }

    private void layoutError() {
        if (la_password1.getText().toString().isEmpty()) {
            la_password1.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        if (la_password2.getText().toString().isEmpty()) {
            la_password2.setBackgroundResource(R.drawable.util_textview_bk_red);
        }
        errorPopup(null,ComMsg.ERR_AUTH_FIELD_EMPTY);
    }

    private boolean checkValidation() {
        boolean errorFlag = true;
        String ps1 = la_password1.getText().toString();
        String ps2 = la_password2.getText().toString();

        if (ps1.isEmpty()) {
            errorFlag = false;
        }
        if (ps2.isEmpty()) {
            errorFlag = false;
        }
        if (!ps1.contentEquals(ps2)) {
            errorFlag = false;
        }
        return errorFlag;
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getCustAuthKey().isEmpty()) {
                AUTH_KEY = obj_g01.getCustAuthKey();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getCustLgnId().isEmpty()) {
                LGNID = obj_g01.getCustLgnId();
            }
        }
    }

    private void SetupDefultConfiguration() {
        la_display = (CheckBox) findViewById(R.id.g72_p27_display_pass);
        la_password1 = (EditText) findViewById(R.id.g72_p72_password1);
        la_password2 = (EditText) findViewById(R.id.g72_p72_password2);

        this.AUTH_KEY = new String();
        this.RSRVSPRSNUID = new String();
        this.LGNID = new String();
        this.PASSWORD = new String();

    }

    private void BackToPreviousPage() {
        Button button = (Button) findViewById(R.id.g72_p72_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish("back");
            }
        });
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataBeforeJsonWork();
        }

        private void setDataBeforeJsonWork() {
            api.setAthntctnKey(AUTH_KEY);
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setLgnId(LGNID);
            api.setLgnPsswrd(PASSWORD);
            Log.e("PARAMS-G72P72", api.getRequestDataA036().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G72P72A36PasswordForgetStep2.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA036());
            JSONObject json = jParser.getJSONData(api.getURLA036());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G72P72", json.toString());
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
            goTo(G73P73A00PasswordForgetStep3.class, ComMsg.COD_NEXT);
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
