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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G24P32A19Settings extends Activity {
    private G01P01ParcelableData obj_g01;
    private String RSRVSPRSNUID;
    private String NEWSPUSHFLAG;
    private String MYFVRTSPUSHFLAG;
    private String NRSTHTLSPUSHFLAG;
    private String DISTANCE;

    private Boolean checkFlag;
    private ToggleButton la_newsPushFlag;
    private ToggleButton la_myFvrtsPushFlag;
    private ToggleButton la_nrstHtlsPushFlag;
    private String PAGE_FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G24P32A19Settings------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g24_p32_settings);

        //Defult Configuration
        InitialSetupConfiguration();

        //GetData
        GetData();

        //Check Json
        SetupToJson();

        //Setup To Variable
        SetupToVariable();

        //Setup Instance
        SetupInstance();

        //Distance Calculation
        GoToDistanceSetting();

        //Back To Home page
        BackToHomePage();

    }

    private void SetupInstance() {

        la_myFvrtsPushFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MYFVRTSPUSHFLAG = "N";
                if (isChecked) {
                    MYFVRTSPUSHFLAG = "Y";
                }
                checkFlag =false;
                SetupToJson();
            }
        });

        la_nrstHtlsPushFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NRSTHTLSPUSHFLAG = "N";
                if (isChecked) {
                    NRSTHTLSPUSHFLAG = "Y";
                }
                checkFlag =false;
                SetupToJson();
            }
        });

        la_newsPushFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NEWSPUSHFLAG = "N";
                if (isChecked) {
                    NEWSPUSHFLAG = "Y";
                }
                checkFlag =false;
                SetupToJson();
            }
        });
    }

    private void SetupToView() {
        TextView distance = (TextView) findViewById(R.id.g24_p32_distance);

        if(DISTANCE.isEmpty()){
            DISTANCE = "5";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("現在地から");
        sb.append(DISTANCE);
        sb.append("km");
        distance.setText(sb.toString());



        if(NEWSPUSHFLAG.equalsIgnoreCase("Y")){
            la_newsPushFlag.setChecked(true);
        }else{
            la_newsPushFlag.setChecked(false);
        }

        if(MYFVRTSPUSHFLAG.equalsIgnoreCase("Y")){
            la_myFvrtsPushFlag.setChecked(true);
        }else{
            la_myFvrtsPushFlag.setChecked(false);
        }

        if(NRSTHTLSPUSHFLAG.equalsIgnoreCase("Y")){
            la_nrstHtlsPushFlag.setChecked(true);
        }else{
            la_nrstHtlsPushFlag.setChecked(false);
        }
    }

    private void SetupToVariable(){
        if(NEWSPUSHFLAG.isEmpty()) {
            NEWSPUSHFLAG = "Y";
        }
        if(MYFVRTSPUSHFLAG.isEmpty()) {
            MYFVRTSPUSHFLAG = "Y";
        }
        if(NRSTHTLSPUSHFLAG.isEmpty()) {
            NRSTHTLSPUSHFLAG = "Y";
        }
    }


    private void SetupToParcel() {
        obj_g01.setDistance(DISTANCE);
        obj_g01.setNewsPushFlag(NEWSPUSHFLAG);
        obj_g01.setMyFavoritesPushFlag(MYFVRTSPUSHFLAG);
        obj_g01.setNearHotelPushFlag(NRSTHTLSPUSHFLAG);
        obj_g01.setPageFlag("G24P32");
    }


    private void GoToDistanceSetting() {
        final LinearLayout button = (LinearLayout) findViewById(R.id.g24_distance_calc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G08P08A02DistanceForSettings.class, "next");
            }
        });
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }

        }
    }

    private void InitialSetupConfiguration() {
        this.checkFlag = true;
        this.RSRVSPRSNUID = new String();
        this.NEWSPUSHFLAG = new String();
        this.MYFVRTSPUSHFLAG = new String();
        this.NRSTHTLSPUSHFLAG = new String();
        this.DISTANCE = new String();
        this.PAGE_FLAG = new String();

        this.la_newsPushFlag = (ToggleButton) findViewById(R.id.g24_p32_pushnews);
        this.la_myFvrtsPushFlag = (ToggleButton) findViewById(R.id.g24_p32_myfavorate);
        this.la_nrstHtlsPushFlag = (ToggleButton) findViewById(R.id.g24_p32_nearhotel);

    }

    private void BackToHomePage() {
        final Button button = (Button) findViewById(R.id.g24_p32_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G02P20A01AccountInformation.class, ComMsg.COD_BACK);
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
            if(checkFlag) {
                setDataForCheckA24();
            }else{
                setDataForRegisterA25();
            }
        }

        private void setDataForCheckA24() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            Log.e(ComLogMsg.PARAM_G24P32A, api.getRequestDataA024().toString());
        }

        private void setDataForRegisterA25() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setNewsPushFlag(NEWSPUSHFLAG);
            api.setMyFvrtsPushFlag(MYFVRTSPUSHFLAG);
            api.setNrstHtlsPushFlag(NRSTHTLSPUSHFLAG);
            api.setDstnc(DISTANCE);
            Log.e(ComLogMsg.PARAM_G24P32B, api.getRequestDataA025().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G24P32A19Settings.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            JSONObject json = new JSONObject();
            if(checkFlag) {
                jParser.setArrayList(api.getRequestDataA024());
                json = jParser.getJSONData(api.getURLA024());
                if (json == null) {
                    json = jsonNullCheck(json);
                }
                Log.e(ComLogMsg.JSON_G24P32A, json.toString());
            }else{
                jParser.setArrayList(api.getRequestDataA025());
                json = jParser.getJSONData(api.getURLA025());
                if (json == null) {
                    json = jsonNullCheck(json);
                }
                Log.e(ComLogMsg.JSON_G24P32B, json.toString());
            }
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            if(checkFlag) {
                NEWSPUSHFLAG = json.optString(ComConstant.CT_NEWSPUSHFLAG);
                MYFVRTSPUSHFLAG = json.optString(ComConstant.CT_MYFVRTSPUSHFLAG);
                NRSTHTLSPUSHFLAG = json.optString(ComConstant.CT_NRSTHTLSPUSHFLAG);
                DISTANCE = json.optString(ComConstant.CT_DSTNC);
            }
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

    private void SetupToJson() {
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
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
