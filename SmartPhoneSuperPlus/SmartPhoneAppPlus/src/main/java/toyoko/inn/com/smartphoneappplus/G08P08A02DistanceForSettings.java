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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComActivity;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;


public class G08P08A02DistanceForSettings extends Activity {

    private SeekBar la_progressBar = null;
    private TextView la_totalDistance;
    private G01P01ParcelableData obj_g01;


    private Button la_submit;

    private String DISTANCE;
    private String RSRVSPRSNUID;
    private String NEWSPUSHFLAG;
    private String MYFVRTSPUSHFLAG;
    private String NRSTHTLSPUSHFLAG;


    float discrete = 0;
    float start = 1;
    float end = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G08P08A02DistanceForSettings------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g08_p08_distance_settings);

        //== Default Configuration
        SetupDefaultConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //Setup To view
        SetupToView();

        //==Distance Calculation
        DistanceCalculation();

        //==Back To Previous Page
        BackToPreviousPage();

        //==>Set And Go To Next Page
        SetDataAndBack();

        //==>GoTo Filter Page
        GoToSetting();

    }

    private void GoToSetting() {
        la_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToJsonParse();
            }
        });
    }

    private void goToJsonParse() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            obj_g01.setErrrMssg(ERR_CONNECTION);
            goTo(ComActivity.class, COD_NEXT);
        }
    }


    private void BackToPreviousPage() {
        Button button = (Button)findViewById(R.id.g08_p08_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G24P32A19Settings.class, COD_BACK);
            }
        });

    }

    private void SetDataAndBack() {
        la_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G24P32A19Settings.class, COD_NORMAL);
            }
        });
    }

    private void SetupDefaultConfiguration() {
        la_totalDistance = (TextView) findViewById(R.id.g08_se_distance_chk);
        la_progressBar = (SeekBar) findViewById(R.id.g08_se_volume_bar);
        la_submit = (Button) findViewById(R.id.g08_se_setdata);


        this.DISTANCE = new String();
        this.RSRVSPRSNUID = new String();
        this.NEWSPUSHFLAG = new String();
        this.MYFVRTSPUSHFLAG = new String();
        this.NRSTHTLSPUSHFLAG = new String();
    }


    private void SetupToParcel() {
        obj_g01.setDistance(DISTANCE);
    }


    //Retrieve Data From G01
    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getNewsPushFlag().isEmpty()) {
                NEWSPUSHFLAG = obj_g01.getNewsPushFlag();
            }

            if (!obj_g01.getMyFavoritesPushFlag().isEmpty()) {
                MYFVRTSPUSHFLAG = obj_g01.getMyFavoritesPushFlag();
            }

            if (!obj_g01.getNearHotelPushFlag().isEmpty()) {
                NRSTHTLSPUSHFLAG = obj_g01.getNearHotelPushFlag();
            }
        }
    }

    private void DistanceCalculation() {
        float distance = Float.valueOf(DISTANCE) * 10;
        int currentPos=(int)(Math.round(distance));
        la_progressBar.setProgress(currentPos);
        la_progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                float dis = end - start;
                discrete = (start + ((temp / 100) * dis));
                String data = String.format("%.1f", discrete);
                la_totalDistance.setText(data);
                DISTANCE = data;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                DISTANCE = la_totalDistance.getText().toString();
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
                setDataForRegisterA25();
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
            processDialog = new ProgressDialog(G08P08A02DistanceForSettings.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            JSONObject json = new JSONObject();
                jParser.setArrayList(api.getRequestDataA025());
                json = jParser.getJSONData(api.getURLA025());
                Log.e(ComLogMsg.JSON_G24P32B, json.toString());
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
            SetupToParcel();
            goTo(G24P32A19Settings.class, COD_BACK);
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
        }
    }


    private void SetupToView() {
        if(!DISTANCE.isEmpty()) {
            la_totalDistance.setText(DISTANCE);
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
}