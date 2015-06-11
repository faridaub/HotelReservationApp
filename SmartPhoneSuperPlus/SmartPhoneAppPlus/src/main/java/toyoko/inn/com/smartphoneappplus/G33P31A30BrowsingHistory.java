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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.G33P31_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_ONE;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G33P31A30BrowsingHistory extends Activity {

    private G01P01ParcelableData obj_g01;
    private ListView la_dataListArray;
    private G33P31_ListViewAdapter adapter;
    private ArrayList<HashMap<String, String>> arraylist;
    private String HOTEL_NUM;
    private String HOTEL_CODE;
    private String NUM_OF_HOTEL;
    private String RSRVSPRSNUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G33P31A30BrowsingHistory------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g33_p30_browsing_history);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //==>> Back To Home Page G01
        BackToHomePage();

        //==Loading Jason Parse Data
        SetupToJson();

    }



    //Setup Initial Data
    private void InitialSetupConfiguration() {
        this.arraylist = new ArrayList<HashMap<String, String>>();
        this.HOTEL_NUM = new String();
        this.HOTEL_CODE = new String();
        this.NUM_OF_HOTEL = new String();
        this.RSRVSPRSNUID = new String();

    }

    private void SetupToParcel() {
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setHotelCode(HOTEL_CODE);
        //Set Common Place
        obj_g01.setNumberOfStayNight(ST_ONE);
        obj_g01.setNumberOfRoom(ST_ONE);
        obj_g01.setNumberOfPeople(ST_ONE);
        obj_g01.setPageFlag("G33P31");

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

        }
    }



    private void BackToHomePage() {
        final Button button = (Button) findViewById(R.id.g33_p30_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G02P20A01AccountInformation.class,  COD_BACK);

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
            setApiRequestData_A030();
        }

        private void setApiRequestData_A030() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPageNmbr(ComInitData.ST_ONE);
            Log.e("PARAM-G33P31", api.getRequestDataA030().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G33P31A30BrowsingHistory.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        private String changeMessage(String eCode, String eMessage) {
            if(eCode.equalsIgnoreCase("BCMN1004")){
                eMessage = ERR_BROWSING_HISTORY;
            }
            return eMessage;
        }


        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA030());
            JSONObject json = jParser.getJSONData(api.getURLA030());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G33P31", json.toString());
            if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = changeMessage(errorCode,json.optString(CT_ERRRMSSG));
                processDialog.dismiss();
                cancel(true);
            }
            try {
                NUM_OF_HOTEL = json.optString(CT_NMBRLDGNG);
                JSONArray jsonData = json.getJSONArray(LT_BRWSNGHSTRYINFRMTN);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    String hotelCode = jsonObject.getString(CT_HTLCODE);
                    String hotelName = jsonObject.getString(CT_HTLNAME);
                    String imgurl = jsonObject.getString(CT_IMGURL);
                    String snglrmprc = jsonObject.getString(CT_SNGLRMPRC);
                    String snglrmprcincldngtax = jsonObject.getString(CT_SNGLRMPRCINCLDNGTAX);
                    String mmbrsnglrmprc = jsonObject.getString(CT_MMBRSNGLRMPRC);
                    String mmbrsnglrmprcincldngtax = jsonObject.getString(CT_MMBRSNGLRMPRCINCLDNGTAX);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_HTLCODE, hotelCode);
                    map.put(CT_HTLNAME, hotelName);
                    map.put(CT_IMGURL, imgurl);
                    map.put(CT_SNGLRMPRC, snglrmprc);
                    map.put(CT_SNGLRMPRCINCLDNGTAX, snglrmprcincldngtax);
                    map.put(CT_MMBRSNGLRMPRC, mmbrsnglrmprc);
                    map.put(CT_MMBRSNGLRMPRCINCLDNGTAX, mmbrsnglrmprcincldngtax);
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToParcel();
            processDialog.dismiss();
            la_dataListArray = (ListView) findViewById(R.id.g33_p31_list_view);
            adapter = new G33P31_ListViewAdapter(G33P31A30BrowsingHistory.this, arraylist);
            la_dataListArray.setAdapter(adapter);
            la_dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HOTEL_CODE = (String) adapter.getData(position, CT_HTLCODE);
                    SetupToParcel();
                    goTo(G10P15A07A21HotelInfoScrollView.class,  COD_BACK);
                }
            });
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
        la_errorCode.setText(eCode);
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
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }
}
