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
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.G32NoticeBoardAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;

public class G32P14NoticeBoard extends Activity {
    private G01P01ParcelableData obj_g01;
    private G32NoticeBoardAdapter adapter;
    private ListView la_dataListArray;
    ArrayList<String> ar_dateArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G32P14NoticeBoard------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g32_p14_notice_board);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //Setup To View
        SetupToView();

        //==>> Back To Home Page G01
        BackToHomePage();

        //== Check Server Connection
        SetupDataToAdapater();

    }

    private void SetupToView() {

        ar_dateArray.add("20141123");
        ar_dateArray.add("20141123");
        ar_dateArray.add("20141123");
        ar_dateArray.add("20141123");
    }

    private void InitialSetupConfiguration() {
        la_dataListArray = (ListView) findViewById(R.id.g32_p14_list_view);
        this.ar_dateArray = new ArrayList<String>();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");
        }
    }

    private void BackToHomePage() {
        Button button = (Button) findViewById(R.id.g32_P14_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_BACK);
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
            // checkURLValidation();
            setApiRequestData_A008();
        }

        private void setApiRequestData_A008() {
            Log.e(ComLogMsg.PARAM_G11P04, api.getRequestDataA008().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G32P14NoticeBoard.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA008());
            JSONObject json = jParser.getJSONData(api.getURLA008());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            Log.e(ComLogMsg.JSON_G11P04, json.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            processDialog.dismiss();
            errorPopup(errorCode, errorMessage);
        }
    }


    private void SetupDataToAdapater() {
        adapter = new G32NoticeBoardAdapter(getApplicationContext(), ar_dateArray);
        la_dataListArray.setAdapter(adapter);
        la_dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = (String) adapter.getData(position);
                goTo(G10P15A07A21HotelInfoScrollView.class, ComMsg.COD_NEXT);
            }
        });
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
