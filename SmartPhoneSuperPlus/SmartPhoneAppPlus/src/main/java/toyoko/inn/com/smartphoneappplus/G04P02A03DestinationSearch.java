package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.G04_HistoryAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComInitData;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_NORMAL;

//We have to make android-expandable-list-view-tutorial
public class G04P02A03DestinationSearch extends Activity {
    private G01P01ParcelableData obj_g01;
    private G04_HistoryAdapter hotelHistoryAdapter;

    private ArrayList<String> HS_KEYWORDNAME;
    private ArrayList<String> HS_NUMHOTEL;
    private ArrayList<String> HS_SRC_KEYWORDNAME;
    private ArrayList<String> HS_SRC_NUMHOTEL;

    private String MOOD;
    private String NUMBER_OF_ROOMS;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_NIGHTS;
    private String MEMBERSHIP_FLAG;
    private String CHECK_IN_DATE;
    private String DESTINATION_KEY;

    private EditText la_destinationKey;
    private ListView la_historyData;
    private LinearLayout la_switchButton;
    private String PAGE_FLAG;
    private String SWITCH_PAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G04P02A03DestinationSearch------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g04_destination_search);

        //Setup Constaint
        InitialSetupConfiguration();

        //==> Get Data From G01
        GetData();

        //Keyword History
        SetupToView();

        // <<== Back To Page G01
        CancelToHome();

        // <<== Back To Page G01
        BackToHome();

        // ==> Go To Page G05
        GoToSwitchPage();

        // <<== Back To Page G04
        GetDataFrom_G05();

        // Go To Setup To Json
        //SetupToJson();

    }


    private void SetupToView() {
        addCross(getApplicationContext(), la_destinationKey);
        //Local Data
        if (HS_KEYWORDNAME.size() != 0) {
            Collections.reverse(HS_KEYWORDNAME);
            Collections.reverse(HS_NUMHOTEL);
            hotelHistoryAdapter = new G04_HistoryAdapter(G04P02A03DestinationSearch.this, HS_KEYWORDNAME, HS_NUMHOTEL);
            la_historyData.setAdapter(hotelHistoryAdapter);
            la_historyData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DESTINATION_KEY = HS_KEYWORDNAME.get(+position).toString();
                    goTo(G01P01A00DashboardActivity.class, COD_NORMAL);
                }
            });
        }

        //Button Click Action
        la_destinationKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    DESTINATION_KEY = v.getText().toString();
                    new JSONParse().execute();
                    return true;
                }
                return false;
            }
        });

        //IF Textview Empty then Show Switch Button
        la_destinationKey.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(count<1) {
                    la_switchButton.setVisibility(View.VISIBLE);
                }else{
                    la_switchButton.setVisibility(View.GONE);
                }
            }
        });



        //Keyword Check
        if (DESTINATION_KEY.isEmpty()) {
            DESTINATION_KEY = la_destinationKey.getText().toString();
        }
    }


    private void InitialSetupConfiguration() {
        this.la_switchButton = (LinearLayout)findViewById(R.id.g04_p02_SwitchButton);
        this.la_destinationKey = (EditText) findViewById(R.id.g04_p02_keyword);
        this.la_historyData = (ListView) findViewById(R.id.g04_p02_historydata);
        this.HS_NUMHOTEL = new ArrayList<String>();
        this.HS_KEYWORDNAME = new ArrayList<String>();
        this.HS_SRC_KEYWORDNAME = new ArrayList<String>();
        this.HS_SRC_NUMHOTEL = new ArrayList<String>();

        this.DESTINATION_KEY = new String();
        this.NUMBER_OF_ROOMS = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_NIGHTS = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.CHECK_IN_DATE = new String();
        this.PAGE_FLAG = new String();
        this.MOOD = new String();
        this.SWITCH_PAGE = new String();
    }

    private void SetupToParcel() {
        MOOD = "1";
        obj_g01.setCountryCode("");
        obj_g01.setStateCode("");
        obj_g01.setAreaCode("");
        obj_g01.setHsKeywordName(HS_KEYWORDNAME);
        obj_g01.setHsNumHotels(HS_NUMHOTEL);
        obj_g01.setDestinationKey(DESTINATION_KEY);
        obj_g01.setMood(MOOD);
    }


    //Retrieve Data From G01
    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
                obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getSwitchPage().isEmpty()) {
                SWITCH_PAGE = obj_g01.getSwitchPage();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOMS = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHTS = obj_g01.getNumberOfStayNight();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getHsKeywordName().isEmpty()) {
                HS_KEYWORDNAME = obj_g01.getHsKeywordName();
            }

            if (!obj_g01.getHsNumHotels().isEmpty()) {
                HS_NUMHOTEL = obj_g01.getHsNumHotels();
            }
        }
    }

    private void GetDataFrom_G05() {
        Bundle extras = getIntent().getExtras();
        String value;
        if (extras != null) {
            value = extras.getString("g05InputData");
        }
    }

    private void GoToSwitchPage() {
        Button button = (Button) findViewById(R.id.goto_g05_search_by_area);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                if(SWITCH_PAGE.isEmpty() || SWITCH_PAGE.equalsIgnoreCase(ST_NULL)) {
                    goTo(G05P03A04ExpandableListMain.class, COD_NORMAL);
                }else if(!SWITCH_PAGE.isEmpty()) {
                        finish(COD_NORMAL);
                }else{
                    finish(COD_NORMAL);
                }
            }
        });

    }

    private void CancelToHome() {
        Button backButton = (Button) findViewById(R.id.g04_p02_btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DESTINATION_KEY = "";
                SetupToParcel();
                obj_g01.setSwitchPage("");
                if (obj_g01.getPageFlag().equalsIgnoreCase("G06")) {
                    goTo(G06P09A00SearchByCondition.class, ComMsg.COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G06_G04")) {
                    goTo(G06P09A00SearchByCondition.class, ComMsg.COD_BACK);
                }else{
                    goTo(G01P01A00DashboardActivity.class, ComMsg.COD_BACK);
                }
            }
        });
    }

    private void BackToHome() {
        Button backButton = (Button) findViewById(R.id.g04_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DESTINATION_KEY = "";
                SetupToParcel();
                obj_g01.setSwitchPage("");
                if (obj_g01.getPageFlag().equalsIgnoreCase("G06")) {
                    goTo(G06P09A00SearchByCondition.class, ComMsg.COD_BACK);
                }else if (obj_g01.getPageFlag().equalsIgnoreCase("G06_G04")) {
                    goTo(G06P09A00SearchByCondition.class, ComMsg.COD_BACK);
                }else{
                    goTo(G01P01A00DashboardActivity.class, ComMsg.COD_BACK);
                }
            }
        });
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorCode;
        private String errorMessage;

        public JSONParse() {
            super();
            SetDataForA03();
        }

        private void SetDataForA03() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECK_IN_DATE);
            api.setNmbrNghts(NUMBER_OF_NIGHTS);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOMS);
            api.setKywrd(DESTINATION_KEY);
            Log.e("PARAM-G04P02", api.getRequestDataA003().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(
                    G04P02A03DestinationSearch.this);
            processDialog.setMessage(ComMsg.MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA003());
            JSONObject json = jParser.getJSONData(api.getURLA003());
            Log.e("JSON-G04P02", json.toString());
            if (isDataManshitsu(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            processDialog.dismiss();
            try {
                JSONArray  jsonData;
                jsonData = json.getJSONArray(ComConstant.LT_KEYWORD_INFO);
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject c = jsonData.getJSONObject(i);
                    HS_SRC_KEYWORDNAME.add(c.getString(ComConstant.CT_KYWRD));
                    HS_SRC_NUMHOTEL.add(c.getString(ComConstant.CT_NUM_HOTEL));
                }
                SearchHotelAdapter();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }

    }

    private void SearchHotelAdapter() {
        hotelHistoryAdapter = new G04_HistoryAdapter(G04P02A03DestinationSearch.this, HS_SRC_KEYWORDNAME, HS_SRC_NUMHOTEL);
        la_historyData.setAdapter(hotelHistoryAdapter);
        la_historyData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DESTINATION_KEY = HS_SRC_KEYWORDNAME.get(+position).toString();
                String HotelName = HS_SRC_NUMHOTEL.get(+position).toString();
                if(ComLib.chkArrayListduplicate(HS_KEYWORDNAME,DESTINATION_KEY)){
                    int positionx = ComLib.getArrayListPosition(HS_KEYWORDNAME, DESTINATION_KEY);
                    HS_KEYWORDNAME.set(positionx, DESTINATION_KEY);
                    HS_NUMHOTEL.set(positionx, HotelName);
                }else{
                    HS_KEYWORDNAME.add(DESTINATION_KEY);
                    HS_NUMHOTEL.add(HotelName);
                }
                SetupToParcel();
                obj_g01.setSwitchPage("");
                if(PAGE_FLAG.equalsIgnoreCase("G06")) {
                    goTo(G06P09A00SearchByCondition.class, COD_NORMAL);
                }else{
                    goTo(G01P01A00DashboardActivity.class, COD_NORMAL);
                }
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
        } else {
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }
}
