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
import toyoko.inn.com.smartphoneappplus.adapter.G12P16_ListViewAdapter;
import toyoko.inn.com.smartphoneappplus.api.APIs;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.dateConvertFormattedDate;
import static toyoko.inn.com.smartphoneappplus.common.ComLib.jsonNullCheck;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.Config.*;


public class G12P16A09ChooseRoomList extends Activity {

    //Internal Values
    private String HOTEL_NAME;
    private String ROOM_NAME;
    private String ROOM_TYPE;
    private String MOOD;
    private String MMBRSHPFLAG;
    private String SMOKING_FLAG;
    private String NUMBER_OF_ROOM;
    private String CHECK_IN_DATE;
    private String HOTEL_CODE;
    private String NMBRNGHTS;
    private String NUMBER_OF_PEOPLE;

    //Parceable Data
    G01P01ParcelableData obj_g01;

    //List View Array
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private JSONArray jsonData = null;
    private TextView la_topTitleHotelName;
    private TextView la_subTitle;
    private String DESTINATION_KEY;
    private String LATITUDE;
    private String LONGITUDE;
    private String DISTANCE;
    private String COUNTRY;
    private String COUNTRY_CODE;
    private String AREA;
    private String AREA_CODE;
    private String STATE;
    private String STATE_CODE;
    private String HOTEL_NUM;
    private String ROOM_TYPE_CODE;
    private String ROOM_IMAGE_URL;
    private String RD_NUMBER_OF_MAX_PEOPLE;
    private String RD_LIST_PRICE;
    private String RD_MEMBER_PRICE;
    private String RD_OFFICIAL_WEB_DIS_PRICE;
    private String RD_MEMBER_OFFICIAL_WEB_DIS_PRICE;
    private String RD_SMOKING_FLAG;
    private String RD_NUMBER_OF_REMAIN_ROOMS;
    private String RD_LYNGOFCHLDRNAVLBLFLAG;
    private String RD_LYNGPRSNS;
    private String RD_ECOAVLBLFLAG;
    private String RD_VODAVLBLFLAG;
    private String RD_BSNSSPACKAVLBLFLAG;
    private String RD_PRICE;
    private String NUMBER_OF_NIGHT;
    private String RD_ECO_FLG;
    private String RD_VOD_FLG;
    private String RD_BSNSSPACKFLAG;
    private String MEMBERSHIP_FLAG;
    private String PAGE_FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G12P16A09ChooseRoomList------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g12_p16_choose_room);

        //== Configuration
        InitialSetupConfiguration();

        //==>Get dataArray From Hotel Details Page G10P15
        GetData();

        //==SetDataToView
        SetupToView();

        //==Back To G10P15
        BackToPreviousPage();

        //==Go To Syncronize JSon
        SetupToJson();
    }

    private void InitialSetupConfiguration() {
        //View Setting
        la_topTitleHotelName = (TextView) findViewById(R.id.g12_p16_hotel_name);
        la_subTitle = (TextView) findViewById(R.id.g12_p16_subtitle);

        //Constant Setting
        this.HOTEL_NAME = new String();
        this.ROOM_NAME = new String();
        this.ROOM_TYPE = new String();
        this.MOOD = new String();
        this.MMBRSHPFLAG = new String("Y");
        this.SMOKING_FLAG = new String();
        this.CHECK_IN_DATE = new String();
        this.NMBRNGHTS = new String();
        this.HOTEL_CODE = new String();
        this.NUMBER_OF_ROOM = new String();
        this.NUMBER_OF_PEOPLE = new String();
        this.NUMBER_OF_NIGHT = new String();
        this.ROOM_TYPE_CODE = new String();
        this.ROOM_NAME = new String();
        this.ROOM_IMAGE_URL = new String();
        this.RD_NUMBER_OF_MAX_PEOPLE = new String();
        this.RD_LIST_PRICE = new String();
        this.RD_MEMBER_PRICE = new String();
        this.RD_OFFICIAL_WEB_DIS_PRICE = new String();
        this.RD_MEMBER_OFFICIAL_WEB_DIS_PRICE = new String();
        this.RD_SMOKING_FLAG = new String();
        this.RD_NUMBER_OF_REMAIN_ROOMS = new String();
        this.RD_LYNGOFCHLDRNAVLBLFLAG = new String();
        this.RD_LYNGPRSNS = new String();
        this.RD_ECOAVLBLFLAG = new String();
        this.RD_VODAVLBLFLAG = new String();
        this.RD_BSNSSPACKAVLBLFLAG = new String();
        this.RD_BSNSSPACKFLAG = new String();
        this.RD_PRICE = new String();
        this.RD_ECO_FLG= new String();
        this.RD_VOD_FLG= new String();
        this.MEMBERSHIP_FLAG = new String();
        this.PAGE_FLAG = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }

            if (!obj_g01.getCheckinDate().isEmpty()) {
                CHECK_IN_DATE = obj_g01.getCheckinDate();
            }

            if (!obj_g01.getHotelName().isEmpty()) {
                HOTEL_NAME = obj_g01.getHotelName();
            }

            if (!obj_g01.getRoomType().isEmpty()) {
                ROOM_TYPE = obj_g01.getRoomType();
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

            if (!obj_g01.getSmokingFlag().isEmpty()) {
                SMOKING_FLAG = obj_g01.getSmokingFlag();
            }

            if (!obj_g01.getHotelCodeNew().isEmpty()) {
                HOTEL_CODE = obj_g01.getHotelCodeNew();
            }

            if (!obj_g01.getMood().isEmpty()) {
                MOOD = obj_g01.getMood();
            }

            if (!obj_g01.getDistance().isEmpty()) {
                DISTANCE = obj_g01.getDistance();
            }

            if (!obj_g01.getCountry().isEmpty()) {
                COUNTRY = obj_g01.getCountry();
            }

            if (!obj_g01.getCountryCode().isEmpty()) {
                COUNTRY_CODE = obj_g01.getCountryCode();
            }

            if (!obj_g01.getArea().isEmpty()) {
                AREA = obj_g01.getArea();
            }

            if (!obj_g01.getAreaCode().isEmpty()) {
                AREA_CODE = obj_g01.getAreaCode();
            }

            if (!obj_g01.getState().isEmpty()) {
                STATE = obj_g01.getState();
            }

            if (!obj_g01.getStateCode().isEmpty()) {
                STATE_CODE = obj_g01.getStateCode();
            }

            if (!obj_g01.getHotelNum().isEmpty()) {
                HOTEL_NUM = obj_g01.getHotelNum();
            }
            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MEMBERSHIP_FLAG = obj_g01.getCustMmbrshpFlag();
            }
        }
    }

    private void SetupToView() {
        la_topTitleHotelName.setText(HOTEL_NAME);

        StringBuilder numNights = new StringBuilder();
        numNights.append("～");
        numNights.append(NUMBER_OF_NIGHT);
        numNights.append("泊");

        StringBuilder roomPeople = new StringBuilder();
        roomPeople.append(NUMBER_OF_PEOPLE);
        roomPeople.append("名");
        roomPeople.append("X");
        roomPeople.append(NUMBER_OF_ROOM);
        roomPeople.append("室");

        String date_title = new String();
        if (!CHECK_IN_DATE.isEmpty()) {
            date_title = dateConvertFormattedDate(CHECK_IN_DATE);
        }

        //Concatinate Two String
        StringBuilder title = new StringBuilder();
        title.append(date_title);
        title.append(numNights.toString());
        title.append("  ");
        title.append(roomPeople.toString());
        la_subTitle.setText(title.toString());

       // la_subTitle.setText(subTitle.toString());

    }

    private void SetupToParcel() {
        RD_ECO_FLG = "N";
        RD_VOD_FLG ="N";
        RD_BSNSSPACKFLAG="N";

        if(RD_ECOAVLBLFLAG.equalsIgnoreCase("Y")){
            RD_ECO_FLG = "Y";
        }

        if(RD_VODAVLBLFLAG.equalsIgnoreCase("Y")){
            RD_VOD_FLG ="Y";
        }

        if(RD_BSNSSPACKAVLBLFLAG.equalsIgnoreCase("Y")){
            RD_BSNSSPACKFLAG ="Y";
        }

        obj_g01.setNumberOfStayNight(NUMBER_OF_NIGHT);
        obj_g01.setHotelCodeNew(HOTEL_CODE);
        obj_g01.setRdRoomTypeCode(ROOM_TYPE_CODE);
        obj_g01.setRdRoomName(ROOM_NAME);
        obj_g01.setRdNumberOfMaxPeople(RD_NUMBER_OF_MAX_PEOPLE);
        obj_g01.setRdListPrice(RD_LIST_PRICE);
        obj_g01.setRdMemberPrice(RD_MEMBER_PRICE);
        obj_g01.setRdOfficialWebDisPrice(RD_OFFICIAL_WEB_DIS_PRICE);
        obj_g01.setRdMemberOfficialWebDis(RD_MEMBER_OFFICIAL_WEB_DIS_PRICE);
        obj_g01.setRdSmokingFlag(RD_SMOKING_FLAG);
        obj_g01.setRdNumberOfRemainRooms(RD_NUMBER_OF_REMAIN_ROOMS);
        obj_g01.setRdLyngofchldrnavlblflag(RD_LYNGOFCHLDRNAVLBLFLAG);
        obj_g01.setRdLyngprsns(RD_LYNGPRSNS);
        obj_g01.setRdEcoavlblflag(RD_ECOAVLBLFLAG);
        obj_g01.setRdEcoFlag(RD_ECO_FLG);
        obj_g01.setRdVodavlblflag(RD_VODAVLBLFLAG);
        obj_g01.setRdVodFlag(RD_VOD_FLG);
        obj_g01.setRdBsnsspackavlblflag(RD_BSNSSPACKAVLBLFLAG);
        obj_g01.setRdBsnssPackFlag(RD_BSNSSPACKFLAG);
        obj_g01.setRdImageURL(ROOM_IMAGE_URL);
        obj_g01.setRdPrice(RD_PRICE);
    }

    private void BackToPreviousPage() {
        final Button button = (Button) findViewById(R.id.g12_p16_back_toppage_g10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PAGE_FLAG.contentEquals("G06_G10")){
                    goTo(G10P15A07A21HotelInfoScrollView.class, COD_BACK);
                }else if(PAGE_FLAG.contentEquals("G01_G10")){
                    goTo(G10P15A07A21HotelInfoScrollView.class, COD_BACK);
                }else {
                    finish(COD_BACK);
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
            settingParameterValueA09();
        }

        private void settingParameterValueA09() {
            api.setMmbrshpFlag(MEMBERSHIP_FLAG); //会員フラグ(Y:会員　/　N:非会員)
            api.setCheckInDate(CHECK_IN_DATE);//チェックイン日付。（形式：YYYYMMDD形式）
            api.setHotelCode(HOTEL_CODE);//ホテルコード
            api.setNmbrNghts(NUMBER_OF_NIGHT); //宿泊日数
            api.setNmbrPpl(NUMBER_OF_PEOPLE);//宿泊者数
            api.setNmbrRms(NUMBER_OF_ROOM); //部屋数
            api.setSmkngFlag(SMOKING_FLAG); //喫煙・禁煙区分（禁煙：N、喫煙：Y）
            Log.e("PARAM-G12P16A09", api.getRequestDataA009().toString());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(
                    G12P16A09ChooseRoomList.this);
            processDialog.setMessage(MSG_PROCESSING);
            processDialog.setIndeterminate(false);
            processDialog.setCancelable(true);
            processDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            CommonJSONParser jParser = new CommonJSONParser();
            jParser.setArrayList(api.getRequestDataA009());
            JSONObject json = jParser.getJSONData(api.getURLA009());
            if (json == null) {
                json = jsonNullCheck(json);
            }
            Log.e("JSON-G12P16A09", json.toString());
            if (!ComLib.isDataSuccess(json.optString(ComConstant.CT_ERRRCODE))) {
                errorCode = json.optString(ComConstant.CT_ERRRCODE);
                errorMessage = json.optString(ComConstant.CT_ERRRMSSG);
                processDialog.dismiss();
                cancel(true);
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            //Set Json Data
            LoadDataToListView(json);
            SetDataToListView();
            SetupToParcel();
            processDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            errorPopup(null, errorMessage);
        }
    }


    private void LoadDataToListView(JSONObject json) {
        try {
            jsonData = json.getJSONArray(ComConstant.LT_ROOM_LIST);

            for (int i = 0; i < jsonData.length(); i++) {
                JSONObject cdata = jsonData.getJSONObject(i);
                String roomType = cdata.getString(ComConstant.CT_ROOMTYPE);
                String roomName = cdata.getString(ComConstant.CT_ROOMNAME);
                String roomTypeImage = cdata.getString(ComConstant.CT_IMGURL);
                if(roomTypeImage.equalsIgnoreCase("") || roomTypeImage.isEmpty() || roomTypeImage.equalsIgnoreCase(" ")){
                    roomTypeImage = CON_ROOM_IMG;
                }
                String maxPpl = cdata.getString(ComConstant.CT_MAXPPL);
                String listPrc = cdata.getString(ComConstant.CT_LISTPRC);
                String mmbrPrc = cdata.getString(ComConstant.CT_MMBRPRC);
                String offclWebDscntPrc = cdata.getString(ComConstant.CT_OFFCLWEBDSCNTPRC);
                String mmbrOffclWebDscntPrc = cdata.getString(ComConstant.CT_MMBROFFCLWEBDSCNTPRC);
                String smkngFlag = cdata.getString(ComConstant.CT_SMKNGFLAG);
                String nmbrRrms = cdata.getString(ComConstant.CT_NMBRRRMS);
                String lyngOfChldrnAvlblFlag = cdata.getString(ComConstant.CT_LYNGOFCHLDRNAVLBLFLAG);
                String lyngPrsns = cdata.getString(ComConstant.CT_LYNGPRSNS);
                String ecoAvlblFlag = cdata.getString(ComConstant.CT_ECOAVLBLFLAG);
                String vodAvlblFlag = cdata.getString(ComConstant.CT_VODAVLBLFLAG);
                String bsnssPackAvlblFlag = cdata.getString(ComConstant.CT_BSNSSPACKAVLBLFLAG);
                String prc = cdata.getString(ComConstant.CT_PRC);

                //Set To HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ComConstant.CT_ROOMTYPE, roomType);
                map.put(ComConstant.CT_ROOMNAME, roomName);
                map.put(ComConstant.CT_IMGURL, roomTypeImage);
                map.put(ComConstant.CT_MAXPPL, maxPpl);
                map.put(ComConstant.CT_LISTPRC, listPrc);
                map.put(ComConstant.CT_MMBRPRC, mmbrPrc);
                map.put(ComConstant.CT_OFFCLWEBDSCNTPRC, offclWebDscntPrc);
                map.put(ComConstant.CT_MMBROFFCLWEBDSCNTPRC, mmbrOffclWebDscntPrc);
                map.put(ComConstant.CT_SMKNGFLAG, smkngFlag);
                map.put(ComConstant.CT_NMBRRRMS, nmbrRrms);
                map.put(ComConstant.CT_LYNGOFCHLDRNAVLBLFLAG, lyngOfChldrnAvlblFlag);
                map.put(ComConstant.CT_LYNGPRSNS, lyngPrsns);
                map.put(ComConstant.CT_ECOAVLBLFLAG, ecoAvlblFlag);
                map.put(ComConstant.CT_VODAVLBLFLAG, vodAvlblFlag);
                map.put(ComConstant.CT_BSNSSPACKAVLBLFLAG, bsnssPackAvlblFlag);
                map.put(ComConstant.CT_PRC, prc);
                //Set To Array List
                arrayList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Set Data To List View
    private void SetDataToListView() {
        ListView listView = (ListView) findViewById(R.id.g12_p16_list);
        final G12P16_ListViewAdapter adapter = new G12P16_ListViewAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set New Type
                ROOM_TYPE_CODE = (String) adapter.getData(position, ComConstant.CT_ROOMTYPE);
                ROOM_NAME = (String) adapter.getData(position, ComConstant.CT_ROOMNAME);
                ROOM_IMAGE_URL = (String) adapter.getData(position, ComConstant.CT_IMGURL);
                RD_NUMBER_OF_MAX_PEOPLE = (String) adapter.getData(position, ComConstant.CT_MAXPPL);
                RD_LIST_PRICE = (String) adapter.getData(position, ComConstant.CT_LISTPRC);
                RD_MEMBER_PRICE = (String) adapter.getData(position, ComConstant.CT_MMBRPRC);
                RD_OFFICIAL_WEB_DIS_PRICE = (String) adapter.getData(position, ComConstant.CT_OFFCLWEBDSCNTPRC);
                RD_MEMBER_OFFICIAL_WEB_DIS_PRICE = (String) adapter.getData(position, ComConstant.CT_MMBROFFCLWEBDSCNTPRC);
                RD_SMOKING_FLAG = (String) adapter.getData(position, ComConstant.CT_SMKNGFLAG);
                RD_NUMBER_OF_REMAIN_ROOMS = (String) adapter.getData(position, ComConstant.CT_NMBRRRMS);
                RD_LYNGOFCHLDRNAVLBLFLAG = (String) adapter.getData(position, ComConstant.CT_LYNGOFCHLDRNAVLBLFLAG);
                RD_LYNGPRSNS = (String) adapter.getData(position, ComConstant.CT_LYNGPRSNS);
                RD_ECOAVLBLFLAG = (String) adapter.getData(position, ComConstant.CT_ECOAVLBLFLAG);
                RD_VODAVLBLFLAG = (String) adapter.getData(position, ComConstant.CT_VODAVLBLFLAG);
                RD_BSNSSPACKAVLBLFLAG = (String) adapter.getData(position, ComConstant.CT_BSNSSPACKAVLBLFLAG);
                RD_PRICE = (String) adapter.getData(position, ComConstant.CT_PRC);
                SetupToParcel();
                if(PAGE_FLAG.contentEquals("G01_G10")){
                    obj_g01.setPageFlag("G01_G10_G13");
                }else if(PAGE_FLAG.contentEquals("G06_G10")){
                    obj_g01.setPageFlag("G06_G10_G13");
                }
                goTo(G13P17A10RoomDetailsSetting.class,COD_NEXT);
            }
        });
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
