package toyoko.inn.com.smartphoneappplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import toyoko.inn.com.smartphoneappplus.JSONParser.CommonJSONParser;
import toyoko.inn.com.smartphoneappplus.adapter.ExpandableListAdapter2;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComActivity.addCross;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.*;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.COD_NORMAL;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.MSG_PROCESSING;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.SW_KEN;

public class G05P03A04ExpandableListMain extends Activity {

    private G01P01ParcelableData obj_g01;
    private LevalOneArea listAdapter;
    private ExpandableListAdapter2 listAdapter2;
    private ExpandableListView la_expListView;

    private List<String> topCountryCode = new ArrayList<String>();
    private List<String> topAreaCode = new ArrayList<String>();
    private List<String> topAreaName = new ArrayList<String>();
    private List<String> topStateCode = new ArrayList<String>();
    private List<String> topStateName = new ArrayList<String>();
    private List<String> topAreaHotel = new ArrayList<String>();
    private List<String> stateHotelEx = new ArrayList<String>();
    private HashMap<String, List<String>> stateNameByAreaCode;
    private HashMap<String, String> stateHotelsByAreaCode;
    private HashMap<String, List<String>> stateCodeByAreaCode;
    private HashMap<String, List<String>> cityNameByStateCode;
    private HashMap<String, List<String>> cityCodeByStateCode;
    private HashMap<String, List<String>> cityHotelByStateCode;

    private HashMap<String, List<String>> childCountryCode;
    private HashMap<String, List<String>> childAreaCode;
    private HashMap<String, List<String>> _cityName;
    private HashMap<String, List<String>> _cityCode;



    //City Name
    private  HashMap<String, String> __cityName = new HashMap<String,String>();
    private HashMap<String,List<String>> __cityCode = new HashMap<String,List<String>>();


    private String DESTINATION_KEY;
    private String COUNTRY_CODE;
    private String AREA_CODE;
    private String STATE_CODE;
    private String MEMBERSHIP_FLAG;
    private String CHECKIN_DATE;
    private String NUMBER_OF_PEOPLE;
    private String NUMBER_OF_ROOM;
    private String NUMBER_OF_NIGHT;
    private EditText la_destinationKey;
    private String PAGE_FLAG;
    private String SWITCH_PAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G05P03A04ExpandableListMain------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g05_p03_expendablelist_main);

        //Setup To Configuration
        SetupToConfiguration();

        //Get Data
        GetData();

        //Setup To view
        SetupToView();

        //Back To Previous Page
        BackToSwitchButton();

        //Back To Previous Page
        CancelToHomePage();

        //Back To Previous Page
        BackToHomePage();

        //Setup To Json
        SetupToJson();
    }

    private void CancelToHomePage() {
        Button cancel = (Button) findViewById(R.id.g05_backto_G01_G06);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NORMAL);
            }
        });
    }

    private void BackToHomePage() {
        Button cancel = (Button) findViewById(R.id.g05_back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NORMAL);
            }
        });
    }

    private void BackToSwitchButton() {

        Button btn = (Button) findViewById(R.id.g05_p03_backswitch);
        // btn.setCompoundDrawablesWithIntrinsicBounds( R.drawable.smiley, 0, 0, 0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupToParcel();
                goTo(G04P02A03DestinationSearch.class,COD_NORMAL);
            }
        });
    }

    private void SetupToParcel() {
        obj_g01.setSwitchPage("Y");
        obj_g01.setDestinationKey(DESTINATION_KEY);
        obj_g01.setCountryCode(COUNTRY_CODE);
        obj_g01.setAreaCode(AREA_CODE);
        obj_g01.setStateCode(STATE_CODE);
        obj_g01.setMood(ST_TWO);
    }

    private void SetupToConfiguration() {
        this.DESTINATION_KEY = new String();
        this.DESTINATION_KEY = new String();
        this.COUNTRY_CODE = new String();
        this.AREA_CODE = new String();
        this.STATE_CODE = new String();
        this.MEMBERSHIP_FLAG = new String();
        this.CHECKIN_DATE = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.PAGE_FLAG = new String();
        this.SWITCH_PAGE = new String();

        this.la_expListView = (ExpandableListView) findViewById(R.id.g05_p03_exp_lv);
        this.la_destinationKey = (EditText) findViewById(R.id.g05_destination_key_edittaxt);

        this.stateNameByAreaCode = new HashMap<String, List<String>>();
        this.stateCodeByAreaCode = new HashMap<String, List<String>>();
        this.cityNameByStateCode = new HashMap<String, List<String>>();
        this.cityCodeByStateCode = new HashMap<String, List<String>>();
        this.cityHotelByStateCode = new HashMap<String, List<String>>();
        this.childCountryCode = new HashMap<String, List<String>>();
        this.childAreaCode = new HashMap<String, List<String>>();
        this.stateHotelsByAreaCode = new HashMap<String,String>();
        this._cityName = new HashMap<String, List<String>>();
        this._cityCode = new HashMap<String, List<String>>();
    }

    private void SetupToView() {
        addCross(getApplicationContext(), la_destinationKey);
        la_expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                DESTINATION_KEY = stateNameByAreaCode.get(topAreaName.get(groupPosition)).get(childPosition);
/*                COUNTRY_CODE = childCountryCode.get(topAreaName.get(groupPosition)).get(childPosition);
                AREA_CODE = childAreaCode.get(topAreaName.get(groupPosition)).get(childPosition);
                STATE_CODE = childStateCode.get(topAreaName.get(groupPosition)).get(childPosition);*/

                //listAdapter2 = new ExpandableListAdapter2(getApplicationContext());
                //la_expListView.setAdapter(listAdapter2);

/*                SetupToParcel();
                obj_g01.setSwitchPage("");
                if(PAGE_FLAG.equalsIgnoreCase("G06")) {
                    goTo(G06P09A00SearchByCondition.class, ComMsg.COD_NORMAL);
                }else if(PAGE_FLAG.equalsIgnoreCase("G01")) {
                    goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NORMAL);
                }else{
                    goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NORMAL);
                }*/
                return false;
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

            if (!obj_g01.getSwitchPage().isEmpty()) {
                SWITCH_PAGE = obj_g01.getSwitchPage();
            }

            if (!obj_g01.getDestinationKey().isEmpty()) {
                DESTINATION_KEY = obj_g01.getDestinationKey();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECKIN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getNumberOfPeople().isEmpty()) {
                NUMBER_OF_PEOPLE = obj_g01.getNumberOfPeople();
            }

            if (!obj_g01.getNumberOfRoom().isEmpty()) {
                NUMBER_OF_ROOM = obj_g01.getNumberOfRoom();
            }

            if (!obj_g01.getNumberOfStayNight().isEmpty()) {
                NUMBER_OF_NIGHT = obj_g01.getNumberOfStayNight();
            }
        }
    }

    public class JSONParse extends AsyncTask<String, String, JSONObject> {
        private CommonJSONParser jParser;
        private ProgressDialog processDialog;
        private APIs api = new APIs();
        private String errorMessage;

        public JSONParse() {
            super();
            jParser = new CommonJSONParser();
            setRequestDataA004();
        }

        private void setRequestDataA004() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG);
            api.setCheckInDate(CHECKIN_DATE);
            api.setNmbrPpl(NUMBER_OF_PEOPLE);
            api.setNmbrRms(NUMBER_OF_ROOM);
            api.setNmbrNghts(NUMBER_OF_NIGHT);
            Log.e("PARAM_G05P03A04", api.getRequestDataA004().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(
                    G05P03A04ExpandableListMain.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        //Set API request dataArray
        protected JSONObject doInBackground(String... args) {
            jParser.setArrayList(api.getRequestDataA004());
            JSONObject json = jParser.getJSONData(api.getURLA004());
            if (json == null) {
                processDialog.dismiss();
                cancel(true);
            }
            Log.e("JSON-G05P03A04", json.toString());
            if (!isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            //Temp Data
            String tempAreaCode = new String();
            String tempStateCode = new String();

            //Child - City Name
            ArrayList[] cityName = new ArrayList[100];
            ArrayList[] cityCode = new ArrayList[100];
            ArrayList[] cityHotel = new ArrayList[100];
            String[] tempCity = new String[100];
            for(int i=0; i<100; i++){
                cityName[i] = new ArrayList<String>();
                cityCode[i] = new ArrayList<String>();
                cityHotel[i] = new ArrayList<String>();
            }

            //Child - State Name
            ArrayList[] stateName = new ArrayList[100];
            ArrayList[] stateCode = new ArrayList[100];
            ArrayList[] stateHotel = new ArrayList[100];

            String[] tempState = new String[100];
            int sHotel[] = new int[100];
            for(int i=0; i<100; i++){
                stateName[i] = new ArrayList<String>();
                stateCode[i] = new ArrayList<String>();
                stateHotel[i] = new ArrayList<String>();
            }

            //Area Code
            ArrayList[] areaHotel = new ArrayList[10];
            int aHotel[] = new int[10];
            for(int i=0; i<9; i++){
                areaHotel[i] = new ArrayList<String>();
            }

            try {
                JSONArray jsonData = json.getJSONArray("areaInfrmtn");

                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject cdata = jsonData.getJSONObject(i);
                    String country_code = cdata.getString("cntryCode");
                    String area_name = cdata.getString("areaName");
                    String area_code = cdata.getString("areaCode");
                    String state_name = cdata.getString("sttName");
                    String state_code = cdata.getString("sttCode");
                    String city_code = cdata.getString("cityCode");
                    String city_name = cdata.getString("cityName");
                    String number_hotel = cdata.getString("nmbrHtl");

                    for(int p=1;p<9;p++) {
                        if (area_code.equalsIgnoreCase(String.valueOf(p))) {

                            //Area Name Code
                            aHotel[p] = aHotel[p] + Integer.valueOf(number_hotel);
                            if (!area_code.equalsIgnoreCase(tempAreaCode)) {
                                topCountryCode.add(country_code);
                                topAreaCode.add(area_code);
                                topAreaName.add(area_name);
                                tempAreaCode = area_code;
                            }

                            //State Name and Code
                            if (!state_code.equalsIgnoreCase(tempStateCode)) {
                                topStateCode.add(state_code);
                                topStateName.add(state_name);
                                tempStateCode = state_code;
                            }

                            if (!state_name.equalsIgnoreCase(tempState[p])) {
                                stateName[p].add(state_name);
                                stateCode[p].add(state_code);
                                stateHotel[p].add(number_hotel);
                                tempState[p] = state_name;
                            }

                            //City name Count
                            for (int xy = 1; xy < 63; xy++) {
                                if (state_code.equalsIgnoreCase(String.valueOf(xy))) {
                                    sHotel[xy] = sHotel[xy] + Integer.valueOf(number_hotel);
                                    if (!city_name.equalsIgnoreCase(tempCity[xy]))
                                        cityName[xy].add(city_name);
                                        cityCode[xy].add(city_code);
                                        cityHotel[xy].add(number_hotel);
                                    tempCity[xy] = city_name;
                                    continue;
                                }
                            }
                            continue;
                        }
                    }
                }

                //Area - StateName
                for(int p=1; p<9 ;p++){
                    topAreaHotel.add(String.valueOf(aHotel[p]));
                }

                for(int p=0;p< topAreaCode.size();p++){
                    int y = Integer.valueOf(topAreaCode.get(p));
                    stateNameByAreaCode.put(topAreaCode.get(p), stateName[y]);
                    stateCodeByAreaCode.put(topAreaCode.get(p), stateCode[y]);
                }

                //State - City
                for(int p=1;p< 63; p++){
                    stateHotelEx.add(String.valueOf(sHotel[p]));
                }

                for(int p=0;p< topStateCode.size();p++){
                    int z = p +1;
                    int y = Integer.valueOf(topStateCode.get(p));
                    cityNameByStateCode.put(topStateCode.get(p), cityName[y]);
                    cityCodeByStateCode.put(topStateCode.get(p), cityCode[y]);
                    cityHotelByStateCode.put(topStateCode.get(p), cityHotel[y]);
                    stateHotelsByAreaCode.put(topStateCode.get(p),String.valueOf(sHotel[z]));
                }

                LoadToAdapter();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
    }

    private void LoadToAdapter() {

        listAdapter = new LevalOneArea(
                this,
                this.topCountryCode,
                this.topAreaName,
                this.topAreaCode,
                this.topAreaHotel,
                this.stateNameByAreaCode,
                this.stateCodeByAreaCode,
                this.stateHotelsByAreaCode,
                this.stateHotelEx,
                this.cityNameByStateCode,
                this.cityCodeByStateCode,
                this.cityHotelByStateCode
        );

        // setting list adapter
        la_expListView.setAdapter(listAdapter);
        la_expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < topAreaCode.size(); i++) {
                    if (i != groupPosition) {
                        la_expListView.collapseGroup(i);
                    }
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
        if (isNetworkAvailable(this)) {
            new JSONParse().execute();
        } else {
            errorPopup(null, ComMsg.ERR_EMPTYCHECK);
        }
    }


    // Adapter Cust Example
    public class CustExpListview extends ExpandableListView {
        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(G05P03A04ExpandableListMain context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            int myWidth = (int) (parentHeight * 0.5);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(listWidth(getApplicationContext()), MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class CustExpListview2 extends ExpandableListView {
        int intGroupPosition2, intChildPosition2, intGroupid2;

        public CustExpListview2(Context context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class CustExpListview3 extends ExpandableListView {
        int intGroupPosition2, intChildPosition2, intGroupid2;

        public CustExpListview3(Context context) {
            super(context);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    //Level 1
    public class LevalOneArea extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> __topCountryCode;
        private List<String> __topAreaName;
        private List<String> __topAreaCode;
        private List<String> __topAreaHotel;
        private HashMap<String, List<String>> __stateNameByAreaCode;
        private HashMap<String, List<String>> __stateCodeByAreaCode;
        private HashMap<String, List<String>> __cityNameByStateCode;
        private HashMap<String, List<String>> __cityCodeByStateCode;
        private HashMap<String, List<String>> __cityHotelByStateCode;
        private HashMap<String, String> __stateHotelByAreaCode;
        private List<String> __stateHotelEx;


        //Level 1
        public LevalOneArea(
                Context context,
                List<String> _topCountryCode,
                List<String> _topAreaName,
                List<String> _topAreaCode,
                List<String> _topAreaHotel,
                HashMap<String, List<String>> _stateNameByAreaCode,
                HashMap<String, List<String>> _stateCodeByAreaCode,
                HashMap<String, String> _stateHotelByAreaCode,
                List<String> _stateHotelEx,
                HashMap<String, List<String>> _cityNameByStateCode,
                HashMap<String, List<String>> _cityCodeByStateCode,
                HashMap<String, List<String>> _cityHotelByStateCode

        ) {
            this._context = context;
            this.__topCountryCode = _topCountryCode;
            this.__topAreaName = _topAreaName;
            this.__topAreaCode = _topAreaCode;
            this.__topAreaHotel = _topAreaHotel;
            this.__stateNameByAreaCode = _stateNameByAreaCode;
            this.__stateCodeByAreaCode = _stateCodeByAreaCode;
            this.__stateHotelByAreaCode = _stateHotelByAreaCode;
            this.__stateHotelEx = _stateHotelEx;
            this.__cityNameByStateCode = _cityNameByStateCode;
            this.__cityCodeByStateCode = _cityCodeByStateCode;
            this.__cityHotelByStateCode = _cityHotelByStateCode;

        }

        @Override //Level 1
        public Object getChild(int groupPosition, int childPosititon) {
            return this.__stateNameByAreaCode.get(this.__topAreaCode.get(groupPosition))
                    .get(childPosititon);
        }

        public Object getChild2(int groupPosition, int childPosititon) {
            return this.__stateCodeByAreaCode.get(this.__topAreaCode.get(groupPosition))
                    .get(childPosititon);
        }

        public Object getChild5(int groupPosition, int childPosititon) {
            return this.__stateHotelByAreaCode.get(getChild2(groupPosition,childPosititon));
        }

        @Override //Level 1
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override //Level 1
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {


            final String CountryCode  = (String) getGroupCountryCode(groupPosition);
            final String AreaCode  = (String) getGroupAreaCode(groupPosition);
            final String stateName = (String) getChild(groupPosition, childPosition);
            final String stateCode = (String) getChild2(groupPosition, childPosition);
            final String stateHotel = (String) getChild5(groupPosition, childPosition);


            CustExpListview SecondLevelexplv = new CustExpListview(G05P03A04ExpandableListMain.this);
            LevalTwoState levelTwo = new LevalTwoState(
                    this.__cityNameByStateCode,
                    this.__cityCodeByStateCode,
                    this.__cityHotelByStateCode,
                    CountryCode,
                    AreaCode,
                    stateName,
                    stateCode,
                    stateHotel
            );
            SecondLevelexplv.setAdapter(levelTwo);
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }



        @Override //Level 1
        public int getChildrenCount(int groupPosition) {
            return this.__stateNameByAreaCode.get(this.__topAreaCode.get(groupPosition))
                    .size();
        }

        @Override  //Level 1
        public Object getGroup(int groupPosition) {
            return this.__topAreaName.get(groupPosition);
        }

        public Object getGroupCountryCode(int groupPosition) {
            return this.__topCountryCode.get(groupPosition);
        }

        public Object getGroupAreaCode(int groupPosition) {
            return this.__topAreaCode.get(groupPosition);
        }


        public Object getGroupAreaHotel(int groupPosition) {
            return this.__topAreaHotel.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this.__topAreaName.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //Level 1
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String areaName = (String) getGroup(groupPosition);
            String areaHotel = (String) getGroupAreaHotel(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.g05_p03_expendablelist_group, null);
            }

            TextView area_name_top = (TextView) convertView
                    .findViewById(R.id.g05_area_name_top);
            area_name_top.setTypeface(null, Typeface.BOLD);
            area_name_top.setText(areaName);

            TextView area_name_main = (TextView) convertView
                    .findViewById(R.id.g05_area_name_main);
            area_name_main.setTypeface(null, Typeface.BOLD);
            area_name_main.setText(areaName);

            TextView hotelNumber = (TextView) convertView
                    .findViewById(R.id.hotel_number);

            hotelNumber.setTypeface(null, Typeface.BOLD);
            if(Integer.valueOf(areaHotel)<1){
                hotelNumber.setBackgroundResource(R.drawable.util_gra_grey_rectangle);
            }else{
                hotelNumber.setBackgroundResource(R.drawable.util_gra_yellow_rectangle);
            }
            StringBuilder sbn = new StringBuilder();
            sbn.append(areaHotel);
            sbn.append(SW_KEN);
            hotelNumber.setText(sbn.toString());

            ImageView arrow = (ImageView) convertView
                    .findViewById(R.id.g05_arrow);
            if(isExpanded){
                arrow.setBackgroundResource(R.drawable.ic_arrow_down);
            }else{
                arrow.setBackgroundResource(R.drawable.ic_arrow_next);
            }
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    //Level 2
    public class LevalTwoState extends BaseExpandableListAdapter {
        private HashMap<String, List<String>> ___cityNameByStateCode;
        private HashMap<String, List<String>> ___cityCodeByStateCode;
        private HashMap<String, List<String>> ___cityHotelByStateCode;
        private String _countryCode;
        private String _areaCode;
        private String _stateName;
        private String _stateCode;
        private String _stateHotel;
        private int positionCount;

        //Level 2
        public LevalTwoState(
                HashMap<String, List<String>> __cityNameByStateCode,
                HashMap<String, List<String>> __cityCodeByStateCode,
                HashMap<String, List<String>> __cityHotelByStateCode,
                String countryCode ,
                String areaCode ,
                String stateName ,
                String stateCode ,
                String stateHotel
        ) {
              this.___cityNameByStateCode =  __cityNameByStateCode;
              this.___cityCodeByStateCode =  __cityCodeByStateCode;
              this.___cityHotelByStateCode =  __cityHotelByStateCode;
              this._countryCode = countryCode;
              this._areaCode = areaCode;
              this._stateName = stateName;
              this._stateCode = stateCode;
              this._stateHotel = stateHotel;
        }

        @Override //Level 2
        public Object getChild(int groupPosition, int childPosition) {
            return ___cityNameByStateCode.get(this._stateCode).get(childPosition);
        }

        public Object getChildCityCode(int groupPosition, int childPosition) {
            return ___cityCodeByStateCode.get(this._stateCode).get(childPosition);
        }

        public Object getChildHotel(int groupPosition, int childPosition) {
            return ___cityHotelByStateCode.get(this._stateCode).get(childPosition);
        }

        public Object getChildCountryCode() {
            return _countryCode;
        }

        public Object getChildAreaCode() {
            return _areaCode;
        }

        public Object getChildStateCode() {
            return _stateCode;
        }


        @Override //Level 2
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override  //Level 2
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            String countryCode = (String) getChildCountryCode();
            String areaCode = (String) getChildAreaCode();
            String stateCode = (String) getChildStateCode();
            String cityName = (String) getChild(groupPosition, childPosition);
            String cityCode = (String) getChildCityCode(groupPosition, childPosition);
            String cityHotel = (String) getChildHotel(groupPosition, childPosition);

            final CustExpListview2 SecondLevelexplv = new CustExpListview2(G05P03A04ExpandableListMain.this);
            LevalThreeCity threeTwo = new LevalThreeCity(countryCode, areaCode, stateCode , cityName,cityCode,cityHotel);
            SecondLevelexplv.setAdapter(threeTwo);
            SecondLevelexplv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    for (int i = 0; i < ___cityNameByStateCode.get(_stateCode).size(); i++) {
                        if (i != groupPosition) {
                            SecondLevelexplv.collapseGroup(i);
                        }
                    }
                }
            });
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return ___cityNameByStateCode.get(this._stateCode).size();
        }
        @Override  // Level 2
        public Object getGroup(int groupPosition) {
            return this._stateName;
        }

        public Object getGroupStateHotel(int groupPosition) {
            return this._stateHotel;
        }


        @Override // Level 2
        public int getGroupCount() {
            return 1;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override  //Level 2
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String stateName = (String) getGroup(groupPosition);
            String stateHotel = (String) getGroupStateHotel(groupPosition);

            LinearLayout parentData = new LinearLayout(G05P03A04ExpandableListMain.this);
            //LinearLayout.LayoutParams p_parent = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            parentData.setOrientation(LinearLayout.HORIZONTAL);
           // parentData.setLayoutParams(p_parent);
            //parentData.setBackgroundResource(R.drawable.util_gra_whitelite_1);
            parentData.setGravity(Gravity.CENTER | Gravity.LEFT);

            TextView text1 = new TextView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.setMargins(30, 0, 5, 0);
            text1.setText(stateName);
            text1.setTextSize(15);
            text1.setPadding(10, 20, 10, 20);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLACK);
            parentData.addView(text1);

            TextView text2 = new TextView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            text2.setLayoutParams(p4);
            text2.setText(stateHotel + SW_KEN);
            text2.setPadding(20, 20, 20, 20);
            text2.setBackgroundResource(R.drawable.util_gra_yellow_rectangle);
            text2.setTextColor(Color.BLACK);
            if(Integer.valueOf(stateHotel)<1) {
                text2.setBackgroundResource(R.drawable.util_gra_grey_rectangle);
                text2.setTextColor(Color.WHITE);
            }
            parentData.addView(text2);
            TextView text3 = new TextView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p4_1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p4_1.weight =1f;
            text3.setLayoutParams(p4_1);
            parentData.addView(text3);

            ImageView imageView = new ImageView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p2.setMargins(0, 0, 20, 0);
            imageView.setPadding(10,10,10,10);
            if(isExpanded){
                imageView.setBackgroundResource(R.drawable.ic_arrow_down);
            }else{
                imageView.setBackgroundResource(R.drawable.ic_arrow_next);
            }
            imageView.setLayoutParams(p2);
            parentData.addView(imageView);


            return parentData;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    //Level 3
    public class LevalThreeCity extends BaseExpandableListAdapter {
        private String _countryCode;
        private String _areaCode;
        private String _stateCode;
        private String _cityName;
        private String _cityCode;
        private String _cityHotel;
        public LevalThreeCity(String coCode,String aCode,String sCode,String cName,String cCode,String cHotel) {
            _countryCode = coCode;
            _areaCode = aCode;
            _stateCode = sCode;
            _cityName = cName;
            _cityCode = cCode;
            _cityHotel = cHotel;
        }
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override //Level 3
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview3 SecondLevelexplv = new CustExpListview3(G05P03A04ExpandableListMain.this);
            SecondLevelexplv.setGroupIndicator(null);

            obj_g01.setSwitchPage("Y");
            obj_g01.setMood(ST_TWO);
            obj_g01.setCountryCode(this.getGroupCountryCode());
            obj_g01.setAreaCode(this.getGroupAreaCode());
            obj_g01.setStateCode(this.getGroupStateCode());
            obj_g01.setCity(this.getGroupCityName());
            obj_g01.setCityCode(this.getGroupCityCode());
            obj_g01.setDestinationKey(this.getGroupCityName());


            Log.e("uuuuu CountryCode",this.getGroupCountryCode());
            Log.e("uuuuu AreaCode",this.getGroupAreaCode());
            Log.e("uuuuu setStateCode",this.getGroupStateCode());
            Log.e("uuuuu setCity",this.getGroupCityName());
            Log.e("uuuuu setCityCode",this.getGroupCityCode());
            Log.e("uuuuu setDestinationKey",this.getGroupCityName());

            obj_g01.setSwitchPage("");
            if(PAGE_FLAG.equalsIgnoreCase("G06")) {
                goTo(G06P09A00SearchByCondition.class, ComMsg.COD_NEXT);
            }else if(PAGE_FLAG.equalsIgnoreCase("G01")) {
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NEXT);
            }else{
                goTo(G01P01A00DashboardActivity.class, ComMsg.COD_NEXT);
            }
            return SecondLevelexplv;
        }
        @Override //Level 3
        public int getChildrenCount(int groupPosition) {
            return 5;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return this._cityName;
        }
        public Object getGroupCityHotel(int groupPosition) {
            return this._cityHotel;
        }

        public String getGroupCountryCode() {
            return this._countryCode;
        }

        public String getGroupAreaCode() {
            return this._areaCode;
        }

        public String getGroupStateCode() {
            return this._stateCode;
        }

        public String getGroupCityName() {
            return this._cityName;
        }

        public String getGroupCityCode() {
            return this._cityCode;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override  //Level 3
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String myCityName = (String) getGroup(groupPosition);
            String myCityHotel = (String) getGroupCityHotel(groupPosition);
            LinearLayout parentData = new LinearLayout(G05P03A04ExpandableListMain.this);
          //  LinearLayout.LayoutParams p_parent = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
          //  p_parent.setMargins(25, 0, 0, 0);
            parentData.setOrientation(LinearLayout.HORIZONTAL);
         //   parentData.setBackgroundResource(R.drawable.util_gra_whitelite_1);
            parentData.setPadding(5,5,5,5);
          //  parentData.setLayoutParams(p_parent);
            parentData.setGravity(Gravity.CENTER | Gravity.LEFT);

            TextView text1 = new TextView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            p3.setMargins(100, 0, 5, 0);
            text1.setText(myCityName);
            text1.setTextSize(15);
            text1.setPadding(10, 20, 10, 20);
            text1.setLayoutParams(p3);
            text1.setTextColor(Color.BLUE);
            parentData.addView(text1);

            TextView text2 = new TextView(G05P03A04ExpandableListMain.this);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
         //   p4.weight = 1f;
            text2.setLayoutParams(p4);
            text2.setText(myCityHotel + SW_KEN);
            text2.setPadding(20, 20, 20, 20);
            text2.setBackgroundResource(R.drawable.util_gra_yellow_rectangle);
            text2.setTextColor(Color.BLACK);
            if(Integer.valueOf(myCityHotel)<1) {
                text2.setBackgroundResource(R.drawable.util_gra_grey_rectangle);
                text2.setTextColor(Color.WHITE);
            }
            parentData.addView(text2);

            return parentData;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}