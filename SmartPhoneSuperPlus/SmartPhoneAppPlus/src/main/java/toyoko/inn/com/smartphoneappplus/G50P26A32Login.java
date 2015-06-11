package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G50P26A32Login extends Activity {
    G01P01ParcelableData obj_g01;
    private EditText la_userid;
    private EditText la_password;
    private Button la_login_submit;
    private CheckBox la_auto_login;
    private String PAGE_FLAG;
    private String LGNID;
    private String PASSWORD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G50P26A32Login--------------------------------------");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.g50_p26_a32_login);

        //==Default Configuration
        SetupConfiguration();

        //==>>Get Data From Previous
        GetData();

        //==Login Authontication
        SetupToView();

        //==Login Button Press Action
        GoToLoggedInAction();

        //==Go To Customar Information Change
        GoToChangeCustomarInfo();

        //==Go To Forget Password
        GoToForgetPassword();

        //==Back To Previous Page
        BackToPreviousPage();

        //==Initial Settings
        GoToInitialSettings();

    }

    private void SetupConfiguration() {
        la_userid = (EditText) findViewById(R.id.g50_p26_id_mail);
        la_password = (EditText) findViewById(R.id.g50_p26_password);
        la_login_submit = (Button) findViewById(R.id.g50_p26_login_submit);
        la_auto_login = (CheckBox) findViewById(R.id.g50_p26_auto_login);

        this.PAGE_FLAG = new String();
        this.LGNID = new String();
        this.PASSWORD = new String();
    }


    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);
            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
                Log.e("222","222");
            }
        }
    }

    private void SetupToView() {

        addCross(getApplicationContext(), la_userid);
        addCross(getApplicationContext(), la_password);

        //Set True Auto Login
        la_auto_login.setChecked(true);


        //Set Password Display
        CheckBox la_pass_display = (CheckBox) findViewById(R.id.g50_p26_pass_display);
        la_pass_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                boolean checked = checkBox.isChecked();
                if(checked){
                    la_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    la_password.setInputType(129);
                }
            }
        });

    }

    private void GoToLoggedInAction() {
        //When Click Login Button
        errorMessageDisable();
        la_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lgin = la_userid.getText().toString();
                String pass = la_password.getText().toString();
                if (!lgin.isEmpty() && !pass.isEmpty() && !mailCheck(lgin)) {
                    errorMessageEnable();
                    errorPopup(null, ERR_INCORRECT_EMAIL);
                }else if (lgin.isEmpty()) {
                    errorMessageEnable();
                    errorPopup(null, ERR_LOGIN_ID_EMPTY);
                }else if (pass.isEmpty()) {
                    errorMessageEnable();
                    errorPopup(null, ERR_PASSWORD_EMPTY);
                }else if (lgin.isEmpty() || pass.isEmpty()) {
                    errorMessageEnable();
                    errorPopup(null, ERR_EMPTYCHECK);
                } else {
                    errorMessageDisable();
                    SetupToVariable();
                    SetupToJson();
                }
            }
        });
    }


    private void GoToForgetPassword() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g50_p26_forget_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G70P70A35PasswordForgetStep0.class, COD_NEXT);
            }
        });
    }

    private void GoToInitialSettings() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g50_p26_member_card);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G80P80A00LoginInitial.class, COD_NEXT);
            }
        });

    }

    private void errorMessageDisable() {
        la_userid.setBackgroundResource(R.drawable.util_textview_bk_pink);
        la_password.setBackgroundResource(R.drawable.util_textview_bk_pink);
    }

    private void errorMessageEnable() {
        la_userid.setBackgroundResource(R.drawable.util_textview_bk_red);
        la_password.setBackgroundResource(R.drawable.util_textview_bk_red);
    }




    private void SetupToVariable() {
        LGNID = la_userid.getText().toString();
        PASSWORD = la_password.getText().toString();
    }

    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            setDataBeforeJsonWorkA032();
        }

        private void setDataBeforeJsonWorkA032() {
            api.setLgnId(LGNID);
            api.setLgnPsswrd(PASSWORD);
            Log.e("PARAM-G50P26", api.getRequestDataA032().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G50P26A32Login.this);
            processDialog.setMessage( MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA032());
            JSONObject json = jParser.getJSONData(api.getURLA032());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G50P26", json.toString());
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            errorCode = json.optString(ComConstant.CT_ERRRCODE);
            if (errorCode.equalsIgnoreCase("BCMN0000")) {
                obj_g01.setCustRsrvsPrsnUid(json.optString(ComConstant.CT_RSRVSPRSNUID));
                obj_g01.setCustFmlyName(json.optString(ComConstant.CT_FMLYNAME));
                obj_g01.setCustFrstName(json.optString(ComConstant.CT_FRSTNAME));
                obj_g01.setCustSex(json.optString(ComConstant.CT_SEX));
                obj_g01.setCustDateBirth(json.optString(ComConstant.CT_DATEBIRTH));
                obj_g01.setCustNtnltyCode(json.optString(ComConstant.CT_NTNLTYCODE));
                obj_g01.setCustPhnNmbr(json.optString(ComConstant.CT_PHNNMBR));
                obj_g01.setCustMmbrshpFlag(json.optString(ComConstant.CT_MMBRSHPFLAG));
                if (!json.optString(ComConstant.CT_MMBRSHPFLAG).isEmpty()) {
                    obj_g01.setCustMmbrshpNmbr(json.optString(ComConstant.CT_MMBRSHPNMBR));
                }

                obj_g01.setCustPcEmlAddrss(json.optString(ComConstant.CT_PCEMLADDRSS));
                obj_g01.setCustNwslttr(json.optString(ComConstant.CT_NWSLTTR));
                obj_g01.setCustLgnId(json.optString(ComConstant.CT_LGNID));
                obj_g01.setCustLgnPsswrd(json.optString(ComConstant.CT_LGNPSSWRD));


                if (PAGE_FLAG.equalsIgnoreCase("G06_G10_G13")) {
                    obj_g01.setPageFlag("G06_G10_G13_G50");
                    goTo(G14P18A12ReservRegistrationStep41Entry.class, COD_NEXT);
                } else if(PAGE_FLAG.equalsIgnoreCase("G01_G10_G13")){
                    obj_g01.setPageFlag("G01_G10_G13_G50");
                    goTo(G14P18A12ReservRegistrationStep41Entry.class, COD_NEXT);
                } else{
                    goTo(G02P20A01AccountInformation.class,  COD_NEXT);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            if(errorCode.equalsIgnoreCase("BGNL0001")){
                errorMessage = ERR_LOGIN_ERROR;
            }
            errorMessageEnable();
            errorPopup(null, errorMessage);
        }
    }

    private void LoginErrorDialogBox() {
        final Dialog dialog = new Dialog(G50P26A32Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_g00p26_faild_login);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.retry_login);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void GoToChangeCustomarInfo() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g50_p26_member_card);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G00P42A00CustomerInfoChange.class,  COD_NEXT);
            }
        });
    }

    private void BackToPreviousPage() {
        Button button = (Button) findViewById(R.id.g50_p26_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAGE_FLAG.equalsIgnoreCase("G06_G10_G13")) {
                    obj_g01.setPageFlag("G06_G10_G13_G50");
                    goTo(G14P18A12ReservRegistrationStep41Entry.class, COD_NEXT);
                } else if(PAGE_FLAG.equalsIgnoreCase("G01_G10_G13")){
                    obj_g01.setPageFlag("G01_G10_G13_G50");
                    goTo(G14P18A12ReservRegistrationStep41Entry.class, COD_NEXT);
                } else{
                    goTo(G01P01A00DashboardActivity.class,  COD_BACK);
                }
            }
        });

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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
        Intent intent = intent = new Intent(getApplicationContext(), myClass);
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
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}


