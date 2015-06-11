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
import toyoko.inn.com.smartphoneappplus.adapter.G16P21_ListViewAdapter2;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComLogMsg;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class G16P21A14ReservListView_RCED_1 extends Activity {
    //View Property
    //private TextView la_topTitleBar;
    // private TextView la_bottomTitleBar;

    //Common Setup
    ListView dataListArray;
    G16P21_ListViewAdapter2 adapter;
    JSONArray jsonData = null;
    ArrayList<HashMap<String, String>> arraylist;

    //Object
    G01P01ParcelableData obj_g01;

    //Local Data Received
    private String MOOD;
    private String DESTINATION_KEY;
    private String ROOM_TYPE;
    private String LONGITUDE;
    private String LATITUDE;
    private String DISTANCE;
    private String COUNTRY;
    private String COUNTRY_CODE;
    private String AREA;
    private String AREA_CODE;
    private String STATE;
    private String STATE_CODE;
    private String HOTEL_NUM;
    private String CHECK_IN_DATE;
    private String CHECK_OUT_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private String HOTEL_CODE;
    private String SMOKING_FLAG;
    private String page_number;
    private String NUM_OF_HOTEL;
    private TextView la_num_hotels;
    private String RSRVSPRSNUID;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G16P21A14ReservListView_RCED_1------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g16_p21_reserv_confirm_edit_delete_rced_1);

        //==Setup Data
        InitialSetupConfiguration();

        //<<== Get Data From G01 & G06
        GetData();

        //==>> Back To Home Page G01
        BackToHomePage();

        //==Loading Jason Parse Data
        SetupToJson();

        //== Back To Account Pgae
        BackToAccount();
    }

    private void BackToAccount() {
        Button buton = (Button) findViewById(R.id.g16_p21_back);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PAGE_FLAG.equalsIgnoreCase("G15P35")) {
                    goTo(G02P20A01AccountInformation.class, COD_BACK);
                } else if (PAGE_FLAG.equalsIgnoreCase("G28P36")) {
                    goTo(G02P20A01AccountInformation.class, COD_BACK);
                } else {
                    finish(COD_BACK);
                }
            }
        });
    }


    private void SetupToParcel() {
        obj_g01.setHotelNum(HOTEL_NUM);
        obj_g01.setHotelCode(HOTEL_CODE);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setNumberOfHotel(NUM_OF_HOTEL);
        obj_g01.setRdBsnsspackavlblflag("N");
        obj_g01.setPageFlag("G19P27");
    }

    private void SetupToView() {
        if (!NUM_OF_HOTEL.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(NUM_OF_HOTEL);
            sb.append("軒");
        }


        String room_title = new String();
        String date_title = new String();
        if (!ROOM_TYPE.isEmpty()) {
            if (ROOM_TYPE.equalsIgnoreCase("1")) {
                room_title = "1名ｘ1室";
            } else if (ROOM_TYPE.equalsIgnoreCase("2")) {
                room_title = "2名ｘ1室";
            } else if (ROOM_TYPE.equalsIgnoreCase("3")) {
                room_title = "1名ｘ2室";
            } else {
                room_title = "1名ｘ1室";
            }
        }
        if (!CHECK_IN_DATE.isEmpty()) {
            date_title = "2014年11月21日";
        }

        //Concatinate Two String
        StringBuilder title = new StringBuilder();
        title.append(date_title);
        title.append("～1泊");
        title.append("  ");
        title.append(room_title);

        //Setting Mood
        String mood_title = new String();
        if (MOOD.equalsIgnoreCase("1") || MOOD.equalsIgnoreCase("2")) {
            if (!arraylist.isEmpty()) {
                HOTEL_NUM = String.valueOf(arraylist.size());
            }
            StringBuilder str = new StringBuilder();
            str.append("キーワード");
            str.append(":");
            str.append(DESTINATION_KEY);
            str.append(" ");
            str.append(HOTEL_NUM);
            str.append("軒");
            mood_title = str.toString();
        } else if (MOOD.equalsIgnoreCase("3")) {
            mood_title = "現在地からの検索";
        } else {
            mood_title = "現在地からの検索";
        }
    }

    private void InitialSetupConfiguration() {
        arraylist = new ArrayList<HashMap<String, String>>();
        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.DESTINATION_KEY = new String();
        this.LONGITUDE = new String();
        this.LATITUDE = new String();
        this.DISTANCE = new String();
        this.COUNTRY = new String();
        this.COUNTRY_CODE = new String();
        this.AREA = new String();
        this.AREA_CODE = new String();
        this.STATE = new String();
        this.STATE_CODE = new String();
        this.HOTEL_NUM = new String();
        this.CHECK_IN_DATE = new String();
        this.CHECK_OUT_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.HOTEL_CODE = new String();
        this.SMOKING_FLAG = new String();
        this.page_number = new String();
        this.NUM_OF_HOTEL = new String();
        this.RSRVSPRSNUID = new String();
        this.PAGE_FLAG = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

        }
    }

    private void BackToHomePage() {

    }

    //JSON Parse Data
    private class JSONParse extends AsyncTask<Void, Void, Void> {
        protected Exception cancelledForEx = null;
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode = new String();
        private String errorMessage = new String();


        public JSONParse() {
            super();
            setApiRequestData_A014();
        }

        private void setApiRequestData_A014() {
            api.setRsrvsPrsnUid(RSRVSPRSNUID);
            api.setPageNmbr(ComInitData.ST_ONE);
            Log.e(ComLogMsg.PARAM_G16P21A14, api.getRequestDataA014().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(G16P21A14ReservListView_RCED_1.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        private String changeMessage(String eCode, String eMessage) {
            if (eCode.equalsIgnoreCase("BCMN1004")) {
                eMessage = ERR_ACC_RESERV;
            }
            return eMessage;
        }

        @Override
        protected Void doInBackground(Void... args) {
            try {
                CommonJSONParser jParser = new CommonJSONParser();
                jParser.setArrayList(api.getRequestDataA014());
                JSONObject json = jParser.getJSONData(api.getURLA014());
                if (json == null) {
                    json = jsonNullCheck(json);
                }
                Log.e(ComLogMsg.JSON_G16P21A14, json.toString());
                if (!ComLib.isDataSuccess(json.optString(CT_ERRRCODE))) {
                    errorCode = json.optString(CT_ERRRCODE);
                    errorMessage = changeMessage(errorCode, json.optString(CT_ERRRMSSG));
                    processDialog.dismiss();
                    cancel(true);
                }

                NUM_OF_HOTEL = json.optString(CT_NMBRRSRVTNS);
                jsonData = json.getJSONArray(LT_RSRVTNINFRMTN);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(CT_RSRVID, jsonObject.getString(CT_RSRVID));
                    map.put(CT_HTLCODE, jsonObject.getString(CT_HTLCODE));
                    map.put(CT_HTLNAME, jsonObject.getString(CT_HTLNAME));
                    map.put(CT_IMGURL, jsonObject.getString(CT_IMGURL));
                    map.put(CT_RSRVTNNMBR, jsonObject.getString(CT_RSRVTNNMBR));
                    map.put(CT_FMLYNAME, jsonObject.getString(CT_FMLYNAME));
                    map.put(CT_FRSTNAME, jsonObject.getString(CT_FRSTNAME));
                    map.put(CT_ROOMTYPE, jsonObject.getString(CT_ROOMTYPE));
                    map.put(CT_PLANCODE, jsonObject.getString(CT_PLANCODE));
                    map.put(CT_PLANNAME, jsonObject.getString(CT_PLANNAME));
                    map.put(CT_NMBRNGHTS, jsonObject.getString(CT_NMBRNGHTS));
                    map.put(CT_ROOMNAME, jsonObject.getString(CT_ROOMNAME));
                    map.put(CT_CHCKNDATE, jsonObject.getString(CT_CHCKNDATE));
                    map.put(CT_NMBRNGHTS, jsonObject.getString(CT_NMBRNGHTS));
                    map.put(CT_PYMNTPRC, jsonObject.getString(CT_PYMNTPRC));
                    map.put(CT_PYMNTPRCINCLDNGTAX, jsonObject.getString(CT_PYMNTPRCINCLDNGTAX));
                    map.put(CT_MDFYLCKFLAG, jsonObject.getString(CT_MDFYLCKFLAG));
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                errorMessage = ComMsg.ERR_ERROR_OCCUR;
                cancel(false);
                processDialog.dismiss();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            SetupToView();
            processDialog.dismiss();
            dataListArray = (ListView) findViewById(R.id.g16_p21_list_view);
            adapter = new G16P21_ListViewAdapter2(G16P21A14ReservListView_RCED_1.this, arraylist);
            dataListArray.setAdapter(adapter);
            dataListArray.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SetupToParcel();
                    obj_g01.setHotelCode((String) adapter.getData(position, CT_HTLCODE));
                    obj_g01.setCustRsrvid((String) adapter.getData(position, CT_RSRVID));
                    obj_g01.setCustRsrvtnNmbr((String) adapter.getData(position, CT_RSRVTNNMBR));
                    obj_g01.setHotelName((String) adapter.getData(position, CT_HTLNAME));
                    obj_g01.setNumberOfStayNight((String) adapter.getData(position, CT_NMBRNGHTS));
                    obj_g01.setRdRoomName((String) adapter.getData(position, CT_ROOMNAME));
                    obj_g01.setRdRoomTypeCode((String) adapter.getData(position, CT_ROOMTYPE));
                    obj_g01.setRoomType((String) adapter.getData(position, CT_ROOMTYPE));
                    obj_g01.setRdPlanCode((String) adapter.getData(position, CT_PLANCODE));
                    obj_g01.setRdPlanName((String) adapter.getData(position, CT_PLANNAME));
                    obj_g01.setCheckinDate((String) adapter.getData(position, CT_CHCKNDATE));
                    obj_g01.setCustFrstName((String) adapter.getData(position, CT_FRSTNAME));
                    obj_g01.setCustFmlyName((String) adapter.getData(position, CT_FMLYNAME));
                    obj_g01.setRdTotalPrice((String) adapter.getData(position, CT_PYMNTPRC));
                    obj_g01.setRdTotalPriceTax((String) adapter.getData(position, CT_PYMNTPRCINCLDNGTAX));
                    obj_g01.setCustRsrvsPrsnUid(RSRVSPRSNUID);
                    goTo(G17P22A15ReservConfirm_RCED_2.class, COD_NEXT);
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
        } else {
            errorPopup(null, ERR_EMPTYCHECK);
        }
    }

}
