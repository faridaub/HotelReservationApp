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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.isDataSuccess;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.isNetworkAvailable;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;


public class G02P20A01AccountInformation extends Activity {
    //Data Pass Property
    G01P01ParcelableData obj_g01;
    private String FAMILY_NAME;
    private String FIRST_NAME;
    private TextView la_customer_name;
    private String POINTS;
    private TextView la_member_points;
    private String MEMBERSHIP_NUMBER;
    private String MEMBERSHIP_FLAG;
    private String DISTANCE;
    private String NEWSPUSHFLAG;
    private String MYFVRTSPUSHFLAG;
    private String NRSTHTLSPUSHFLAG;
    private String RSRVSPRSNUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G02P20A01AccountInformation------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g02_account_information);

        //Initial Setup
        InitialSetupConfiguration();

        //Get Data From Different Page
        GetDataFromOthers();

        //Set Data To View
        SetDataToView();

        //Go To Reservation List
        GoToReservationList_G16();

        //Go To Accomodation History
        GoToAccomodationHistory_G19();

        //Go To Favorate List
        GoToFavorateList_G21();

        //Go To Favorate List
        GoToCustomerInfoUpdate_G22();

        //Go To Details Setting
        GoToSettings_G24();

        //Go To Details Setting
        GoToBrowsingHistory_G33();

        //Go To Home Page
        BackToHomePage_G01();

        //Go To Logout Page
        LogoutAction();

        //Go To Json Action
        SetupToJson1();

        //Go To Json Action
        SetupToJson2();
    }

    private void SetupToParcel() {
        obj_g01.setDistance(DISTANCE);
        Log.e("DISTANCE",DISTANCE);
    }

    private void SetupToJson1() {
        if(MEMBERSHIP_FLAG.equalsIgnoreCase("Y")) {
            SetupToJson();
        }else{
            POINTS = "0";
            ReloadBeforeAction();
        }
    }

    private void SetDataToView() {
        //Set Name To View
        if(!FAMILY_NAME.isEmpty() && !FIRST_NAME.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(FAMILY_NAME);
            sb.append(" ");
            sb.append(FIRST_NAME);
            la_customer_name.setText(sb);
        }
    }

    private void LogoutAction() {
        final Button button = (Button) findViewById(R.id.g02_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setCustRsrvsPrsnUid("");
                goTo(G01P01A00DashboardActivity.class,"back");
            }
        });
    }

    private void GetDataFromOthers() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if(!obj_g01.getCustMmbrshpFlag().isEmpty()){
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
                Log.e("MYMEMBERSHIP FLAG",MEMBERSHIP_FLAG);
            }

            if(MEMBERSHIP_FLAG.equalsIgnoreCase("Y")){
                if(!obj_g01.getCustMmbrshpNmbr().isEmpty()){
                    MEMBERSHIP_NUMBER = obj_g01.getCustMmbrshpNmbr();
                }
            }

            if(!obj_g01.getCustFmlyName().isEmpty()){
                FAMILY_NAME = obj_g01.getCustFmlyName();
            }
            if(!obj_g01.getCustFrstName().isEmpty()){
                FIRST_NAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getDistance().isEmpty()) {
            }
            if(!obj_g01.getCustMmbrshpNmbr().isEmpty()){
                MEMBERSHIP_NUMBER = obj_g01.getCustMmbrshpNmbr();

            }
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
        }
    }

    private void ReloadBeforeAction() {
        la_member_points.setText(POINTS);
        obj_g01.setMemberPoints(POINTS);
        obj_g01.setPageFlag("G02P20");
    }


    private void GoToBrowsingHistory_G33() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_browsing_history);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G33P31A30BrowsingHistory.class,"next");
            }
        });
    }

    private void GoToCustomerInfoUpdate_G22() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_customerinfo_update);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G22P29A22CustomerInfoUpdateSetp1.class,"next");
            }
        });
    }

    private void GoToFavorateList_G21() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_favorate_list);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G21P31A20FavoriteHotels.class,"next");
            }
        });
    }

    private void GoToReservationList_G16() {

        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_reservation_confirm_update);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G16P21A14ReservListView_RCED_1.class,"next");
            }
        });
    }

    private void GoToAccomodationHistory_G19() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_accmodation_history);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G19P27A18AccommodationHistory_AH_1.class,"next");
            }
        });
    }

    private void GoToSettings_G24() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.g24_settings);
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G24P32A19Settings.class,"next");
            }
        });
    }

    private void InitialSetupConfiguration() {
        this.FAMILY_NAME = new String();
        this.FIRST_NAME = new String();
        this.POINTS = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.MEMBERSHIP_NUMBER = new String();
        this.DISTANCE = new String();
        this.NEWSPUSHFLAG = new String();
        this.MYFVRTSPUSHFLAG = new String();
        this.NRSTHTLSPUSHFLAG = new String();

        la_member_points = (TextView)findViewById(R.id.g02_p20_member_points);
        la_customer_name = (TextView)findViewById(R.id.g02_customer_name);
    }

    private void BackToHomePage_G01() {
        final Button button = (Button) findViewById(R.id.g02_p14_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                goTo(G01P01A00DashboardActivity.class,"back");
            }
        });
    }

    public class JSONParseA01 extends AsyncTask<Void,Void,Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParseA01(){
            settingApiParametter();
        }

        private void settingApiParametter() {
            api.setMmbrshpNmbr(MEMBERSHIP_NUMBER);
            Log.e("PARAM-G02P20", api.getRequestDataA001().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G02P20A01AccountInformation.this);
            processDialog.setMessage("実施中....");
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA001());
            JSONObject json = jParser.getJSONData(api.getURLA001());
            Log.e("JSON-G02P20", json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            POINTS = json.optString(ComConstant.CT_NMBRPOINTS);
            Log.e("JSON-G02P20",json.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ReloadBeforeAction();
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
    }

    private class JSONParse2 extends AsyncTask<Void, Void, Void> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse2() {
            super();
            setDataForCheckA24();
        }

        private void setDataForCheckA24() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            Log.e(ComLogMsg.PARAM_G24P32A, api.getRequestDataA024().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G02P20A01AccountInformation.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            JSONObject json = new JSONObject();
            jParser.setArrayList(api.getRequestDataA024());
            json = jParser.getJSONData(api.getURLA024());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G24P32A, json.toString());

            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }

            NEWSPUSHFLAG = json.optString(ComConstant.CT_NEWSPUSHFLAG);
            MYFVRTSPUSHFLAG = json.optString(ComConstant.CT_MYFVRTSPUSHFLAG);
            NRSTHTLSPUSHFLAG = json.optString(ComConstant.CT_NRSTHTLSPUSHFLAG);
            DISTANCE = json.optString(ComConstant.CT_DSTNC);

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            processDialog.dismiss();
            //Set Data To Parcel
            SetupToParcel();
        }

        @Override
        protected void onCancelled() {
            errorPopup(errorCode, errorMessage);
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
        if (isNetworkAvailable(this)) {
            new JSONParseA01().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }

    private void SetupToJson2() {
        if (isNetworkAvailable(this)) {
            new JSONParse2().execute();
        }else{
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }





}
