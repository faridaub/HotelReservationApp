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
import toyoko.inn.com.smartphoneappplus.adapter.G19P27_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G19P27A18AccommodationHistory_AH_1 extends Activity {

    private G01P01ParcelableData obj_g01;
    private TextView la_topTitleBar;
    private TextView la_bottomTitleBar;
    private ListView dataListArray;
    private G19P27_ListViewAdapter adapter;
    private JSONArray jsonData = null;
    private ArrayList<HashMap<String, String>> arraylist;

    private String MOOD;
    private String DESTINATION_KEY;
    private String ROOM_TYPE;
    private String HOTEL_NUM;
    private String CHECK_IN_DATE;
    private String HOTEL_CODE;
    private String NUM_OF_HOTEL;
    private TextView la_num_hotels;
    private String RSRVSPRSNUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G19P27A18AccommodationHistory_AH_1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g19_p27_accommodation_history_ah1);

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
        this.la_topTitleBar = (TextView) findViewById(R.id.g19_top_title_bar);
        this.la_num_hotels   =(TextView)findViewById(R.id.g11p04_num_hotels);
        this.arraylist = new ArrayList<HashMap<String, String>>();

        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.DESTINATION_KEY = new String();
        this.HOTEL_NUM = new String();
        this.CHECK_IN_DATE = new String();
        this.HOTEL_CODE = new String();
        this.NUM_OF_HOTEL = new String();
        this.RSRVSPRSNUID = new String();
    }

    private void SetupToParcel() {
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setNumberOfHotel(NUM_OF_HOTEL);
        obj_g01.setPageFlag("G19P27");

    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getRoomType().isEmpty()) {
                ROOM_TYPE = obj_g01.getRoomType();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }
        }
    }


    private void BackToHomePage() {
        final Button button = (Button) findViewById(R.id.g19_back_toppage_g01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    goTo(G02P20A01AccountInformation.class, COD_BACK);

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
            setApiRequestData_A018();
        }

        private void setApiRequestData_A018() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPageNmbr(ST_ONE);
            Log.e(ComLogMsg.PARAM_G19P27, api.getRequestDataA018().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G19P27A18AccommodationHistory_AH_1.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }
        private String changeMessage(String eCode, String eMessage) {
            if(eCode.equalsIgnoreCase("BCMN1004")){
                eMessage = ERR_ACC_HISTORY;
            }
            return eMessage;
        }

        @Override
        protected Void doInBackground(Void... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA018());
            JSONObject json = jParser.getJSONData(api.getURLA018());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e(ComLogMsg.JSON_G19P27,json.toString());
            if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                errorCode = json.optString(CT_ERRRCODE);
                errorMessage = changeMessage(errorCode, json.optString(CT_ERRRMSSG));
                processDialog.dismiss();
                cancel(true);
            }
            try {
                 NUM_OF_HOTEL = json.optString(CT_NMBRLDGNG);
                jsonData = json.getJSONArray(LT_LDGNGINFRMTN);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_RSRVID, jsonObject.getString(CT_RSRVID));
                    map.put(CT_HTLNAME, jsonObject.getString(CT_HTLNAME));
                    map.put(CT_IMGURL, jsonObject.getString(CT_IMGURL));
                    map.put(CT_FMLYNAME, jsonObject.getString(CT_FMLYNAME));
                    map.put(CT_FRSTNAME, jsonObject.getString(CT_FRSTNAME));
                    map.put(CT_CHCKNDATE, ComLib.dateConvertFormattedDate(jsonObject.getString(CT_CHCKNDATE)));
                    map.put(CT_NMBRNGHTS, jsonObject.getString(CT_NMBRNGHTS));
                    map.put(CT_ROOMTYPE, jsonObject.getString(CT_ROOMTYPE));
                    map.put(CT_ROOMNAME, jsonObject.getString(CT_ROOMNAME));
                    map.put(CT_PYMNTPRC, jsonObject.getString(CT_PYMNTPRC));
                    map.put(CT_PYMNTPRCINCLDNGTAX, jsonObject.getString(CT_PYMNTPRCINCLDNGTAX));
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
            dataListArray = (ListView) findViewById(R.id.g19_list_view);
            adapter = new G19P27_ListViewAdapter(G19P27A18AccommodationHistory_AH_1.this, arraylist);
            dataListArray.setAdapter(adapter);
            dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //Set Data Before Click
                    HOTEL_CODE = (String) adapter.getData(position, CT_HTLCODE);
                    String  rsrvid= (String) adapter.getData(position, CT_RSRVID);
                    obj_g01.setCustRsrvid(rsrvid);

                     //Reload Data Before Action
                    SetupToParcel();

                    //Go To G11P04 --> G10P15
                    goTo(G20P28A19AccommodationHistory_AH_2.class,COD_NEXT);
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

    private void SetupToJson() {
        if (ComLib.isNetworkAvailable(this)) {
            new JSONParse().execute();
        }else{
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
