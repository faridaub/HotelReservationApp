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
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G00P48A00LoginRegistrationAsGeneralFinish extends Activity {
    private G01P01ParcelableData obj_g01;
    private String RSRVSPRSNUID;
    private String PRCSSNGTYPE;
    private String FMLYNAME;
    private String FRSTNAME;
    private String DATEBIRTH;
    private String SEX;
    private String NTNLTYCODE;
    private String PHNNMBR;
    private String MMBRSHPFLAG;
    private String PCEMLADDRSS1;
    private String PCEMLADDRSS2;
    private String PASSWORD1;
    private String PASSWORD2;
    private String PASSDELFLG;
    private String NWSLTTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G00P48A00LoginRegistrationAsGeneralFinish------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g00_p48_a00_login_registration_as_general_finish);

        SetupConfiguration();

        G00P48_GetDataFrom_G00P47();

        G00P48_backToUpdate_G00P26();

        G00P48_backToTop_G01P01();

        new JSONParse().execute();

    }


    private void SetupConfiguration() {

        RSRVSPRSNUID = new String();
        PRCSSNGTYPE = new String();
        FMLYNAME = new String();
        FRSTNAME = new String();
        DATEBIRTH = new String();
        SEX = new String();
        NTNLTYCODE = new String();
        PHNNMBR = new String();
        MMBRSHPFLAG = new String();
        PCEMLADDRSS1 = new String();
        PCEMLADDRSS2 = new String();
        NWSLTTR = new String();
        PASSWORD1 = new String();
        PASSWORD2 = new String();
        PASSDELFLG = new String();

    }

    private void G00P48_GetDataFrom_G00P47() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");

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

            if (!obj_g01.getCustPcEmlAddrss().isEmpty()) {
                PCEMLADDRSS1 = obj_g01.getCustPcEmlAddrss();
            }

            if (!obj_g01.getCustNwslttr().isEmpty()) {
                NWSLTTR = obj_g01.getCustNwslttr();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHNNMBR = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getCustPsswrd().isEmpty()) {
                PASSWORD1 = obj_g01.getCustPsswrd();
            }

        }

    }

    private void ReloadBeforeAction() {
        obj_g01.setPageFlag("G23P38");
    }

    private void G00P48_backToUpdate_G00P26() {
        final Button button = (Button)findViewById(R.id.g23_p38_back_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G50P26A32Login.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P48A00LoginRegistrationAsGeneralFinish.this.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
            }
        });
    }


    private void G00P48_backToTop_G01P01() {

        final Button button = (Button)findViewById(R.id.g23_p38_back_top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), G01P01A00DashboardActivity.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P48A00LoginRegistrationAsGeneralFinish.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });

    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();

        public JSONParse() {
            super();
            setApiRequestData_A023();
        }

        private void setApiRequestData_A023() {
            api.setPrcssngType("1");
            api.setFmlyName(FMLYNAME);
            api.setFrstName(FRSTNAME);
            api.setDateBirth(DATEBIRTH);
            api.setSex(SEX);
            api.setNtnltyCode("392");
            api.setPhnNmbr(PHNNMBR);
            api.setMmbrshpFlag(MMBRSHPFLAG);
            api.setPcEmlAddrss(PCEMLADDRSS1);
            api.setNwslttr(NWSLTTR);
            api.setPsswrd(PASSWORD1);

            Log.e("PARAM-G23P30", api.getRequestDataA023().toString());

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G00P48A00LoginRegistrationAsGeneralFinish.this);
            processDialog.setMessage("実施中....");
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA023());
            JSONObject json = jParser.getJSONData(api.getURLA023());
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ReloadBeforeAction();
            processDialog.dismiss();
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
