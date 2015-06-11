package toyoko.inn.com.smartphoneappplus.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePair;
import toyoko.inn.com.smartphoneappplus.adapter.KeyValuePairArrayAdapter;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRCODE;
import static toyoko.inn.com.smartphoneappplus.common.ComConstant.CT_ERRRMSSG;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.ERR_ERROR_OCCUR;

/**
 * Created by Farid on 2014/11/25.
 */
public class ComLib {


    //If Json Is Null
    //----------------------------------------------------------------------------------------------
    public static JSONObject jsonNullCheck(JSONObject json) {
        json = new JSONObject();
        try {
            json.put(CT_ERRRCODE, "CUSTOM_JSON_NULL_DATA");
            json.put(CT_ERRRMSSG, ERR_ERROR_OCCUR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    //Concatinate Data
    //----------------------------------------------------------------------------------------------
    public static String concatListData(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int s = list.size() - 1;
        for (String d : list) {
            sb.append(d);
            if (i != s) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }

    //G13P17
    //----------------------------------------------------------------------------------------------
    public String concatListDataByValue(ArrayList<HashMap<String, String>> list, String keyValue) {
        StringBuilder sb = new StringBuilder();
        int j = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            String k = list.get(i).get(keyValue);
            sb.append(k);
            if (i != j) {
                sb.append(",");
            }
        }
        return sb.toString();
    }



    //Left Padding
    //----------------------------------------------------------------------------------------------
    public static String setLpad(String day) {
        StringBuilder sb = new StringBuilder();
        if (day.length() != 2) {
            sb.append("0");
        }
        sb.append(day);
        return sb.toString();
    }

    //Get Current Date
    //----------------------------------------------------------------------------------------------
    public static String dateGetCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    // Return yyyy/MM/dd(E)
    //----------------------------------------------------------------------------------------------
    public static String dateReturnFormettedDate(String selectedDate) {
        SimpleDateFormat receivedFormet = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat convartFormet = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(receivedFormet.parse(selectedDate));
            cal.add(Calendar.DATE,0);
            convertedDate = convartFormet.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }


    //Get Converted Date
    //----------------------------------------------------------------------------------------------
    public static String dateConvertFormattedDate(String selectedDate) {
        /*
        String year = DateData.substring(0, 4);
        String mon = DateData.substring(4, 6);
        String day = DateData.substring(6, 8);
        String date = year + "年" + mon + "月" + day + "日";
          return date;
        */

        SimpleDateFormat receivedFormet = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat convartFormet = new SimpleDateFormat("yyyy年MM月dd日", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(receivedFormet.parse(selectedDate));
            cal.add(Calendar.DATE, 0);
            convertedDate = convartFormet.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;


    }

    //Get Converted Date
    //----------------------------------------------------------------------------------------------
    public static long dateRegurnMilliTime(String DateData) {
        int year = Integer.parseInt(DateData.substring(0, 4));
        int month = Integer.parseInt(DateData.substring(4, 6));
        int day = Integer.parseInt(DateData.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milliTime = calendar.getTimeInMillis();
        return milliTime;
    }


    //Year Month Day Addition Using Number of Days
    //----------------------------------------------------------------------------------------------
    public static String dateYearMonthDayAdditionUsingDays(String curentDate, String numberOfDays) {
        int days = Integer.parseInt(numberOfDays);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(curentDate));
            cal.add(Calendar.DATE, days);
            convertedDate = dateFormat.format(cal.getTime());
        } catch (ParseException e) {
            //Log.e("Exception","Error Form ComLib");
            e.printStackTrace();
        }
        return convertedDate;
    }

    //Return Number of Days From Two Date
    //----------------------------------------------------------------------------------------------
    public static int dateNumberOfDaysFromTwoDateReturnInt(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        Date d1 = null;
        Date d2 = null;
        long diffDays = 0;
        try {
            d1 = format.parse(startDate);
            d2 = format.parse(endDate);
            long diff = d2.getTime() - d1.getTime();
            diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) diffDays;
    }

    //Return Number of Days From Two Date
    //----------------------------------------------------------------------------------------------
    public static String dateNumberOfDaysFromTwoDate(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        Date d1 = null;
        Date d2 = null;
        long diffDays = 0;
        try {
            d1 = format.parse(startDate);
            d2 = format.parse(endDate);
            long diff = d2.getTime() - d1.getTime();
            diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(diffDays);
    }

    //String date to Date
    //----------------------------------------------------------------------------------------------
    public static Date dateReturnStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        Date date = new Date();
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    //Year Month Day Addition Using Number of Days
    //----------------------------------------------------------------------------------------------
    public static String dateSameDataPlus(String currentDate, String number_of_stay_nights) {
        int days = Integer.parseInt(number_of_stay_nights);
        SimpleDateFormat settingFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        SimpleDateFormat gettingDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(settingFormat.parse(currentDate));
            cal.add(Calendar.DATE, days);
            convertedDate = gettingDate.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }


    //Year Month Day Addition Using Number of Days
    //----------------------------------------------------------------------------------------------
    public static String dateMonthDayAdditionUsingDays(String currentDate, String number_of_stay_nights) {
        int days = Integer.parseInt(number_of_stay_nights);
        SimpleDateFormat settingFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        SimpleDateFormat gettingDate = new SimpleDateFormat("MM/dd", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(settingFormat.parse(currentDate));
            cal.add(Calendar.DATE, days);
            convertedDate = gettingDate.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    //From date return Year and Date (20150305 →　03/05)
    //----------------------------------------------------------------------------------------------
    public static String dateReturnYearMonth(String currentDate) {
        SimpleDateFormat settingFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        SimpleDateFormat gettingDate = new SimpleDateFormat("MM/dd", Locale.JAPAN);
        String convertedDate = new String();
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(settingFormat.parse(currentDate));
            convertedDate = gettingDate.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    //Checking Date Between Two Dates
    //----------------------------------------------------------------------------------------------
    public static String dateDateBetweenDates(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        String getdays = new String();
        try {
            Date date1 = format.parse(startDate);
            Date date2 = format.parse(endDate);
            if (date1.compareTo(date2) > 0) {
                getdays = "-1";
                return getdays;
            }
            long diff = date2.getTime() - date1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            getdays = String.valueOf(diffDays);
        } catch (Exception e) {
            return getdays;
        }
        return getdays;
    }



    // Return Date ArrayList from two dates
    //----------------------------------------------------------------------------------------------
    public static ArrayList<Date> dateReturnArrayListFromTwoDate(String dateString1, String dateString2){
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }




    //Checking Date Between Two Dates
    //----------------------------------------------------------------------------------------------
    public static String dateBetweenTwoDateCheck(String startDate, String endDate) {
        String dataString = new String();

        try {
            SimpleDateFormat dateformate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
            Date date1 = dateformate.parse(startDate);
            Date date2 = dateformate.parse(endDate);
            if (date1.compareTo(date2) > 0) {
                dataString = "Date1 is after Date2";
            } else if (date1.compareTo(date2) < 0) {
                dataString = "date2 is Greater than my date1";
            } else if (date1.compareTo(date2) == 0) {
                dataString = "Date1 is equal to Date2";
            } else {
                dataString = "How to get here?";
            }

        } catch (ParseException e) {

            return "0";
        }
        return dataString;
    }

    public static boolean dateBeforeCurrentDateCheck(String startDate, String endDate) {
        SimpleDateFormat mysdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        boolean b = false;
        try {
            Date date1 = mysdf.parse(startDate);
            Date date2 = mysdf.parse(endDate);
            if (date1.before(date2)) {
                b = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }


    //Date After Start Date
    //----------------------------------------------------------------------------------------------
    public static boolean dateAfterCurrentDateAndEqual(String startDate, String endDate) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        boolean b = false;
        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.
            }  else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;  // If start date is equal end Date
            }else{
                b = false; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }

    //Date After Start Date
    //----------------------------------------------------------------------------------------------
    public static boolean dateAfterCurrentDate(String startDate, String endDate) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        boolean b = false;
        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.
            }  else{
                b = false; // If start date is after the end date.
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }

    //Concatinate Data (return 03/05,06/05,07/05)
    //----------------------------------------------------------------------------------------------
    public static String dateCurrentCheckin() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String dateCurrentCheckout(String NumberOfDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, Integer.valueOf(NumberOfDays));
        return dateFormat.format(cal.getTime());
    }

    public static String dateCurrentCheckinFormet() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String dateCurrentCheckoutFormet(String NumberOfDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPAN);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, Integer.valueOf(NumberOfDays));
        return dateFormat.format(cal.getTime());
    }


    //Concatinate Data (return 03/05,06/05,07/05)
    //----------------------------------------------------------------------------------------------
    public static String dateConcatWithDateFormate(List<String> dateArrayList) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int s = dateArrayList.size() - 1;
        for (String d : dateArrayList) {
            d = dateReturnYearMonth(d);
            sb.append(d);
            if (i != s) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }


    //Formetted Date and Number of Nightes
    // 2015年０１月２８日　～　２拍
    //----------------------------------------------------------------------------------------------
    public static String getFormettedDateAndStayNights(String currentDate, String stayNights) {
        StringBuilder acdate = new StringBuilder();
        acdate.append(ComLib.dateConvertFormattedDate(currentDate));
        acdate.append(" ～");
        acdate.append(stayNights);
        acdate.append("拍");
        return acdate.toString();
    }


    //Get Total Price with Tax
    //----------------------------------------------------------------------------------------------
    public static String getSimpleString(String StringLevel, String StringValue) {
        StringBuilder total_price = new StringBuilder();
        total_price.append(StringLevel);
        total_price.append(" :");
        total_price.append(StringValue);
        return total_price.toString();
    }


    //Get Total Price with Tax
    //----------------------------------------------------------------------------------------------
    public static String getTotalPriceWithTax(String totalPrice, String totalPriceIncludingTax) {
        StringBuilder total_price = new StringBuilder();
        total_price.append(totalPrice);
        total_price.append(" ");
        total_price.append("(税込");
        total_price.append(totalPriceIncludingTax);
        total_price.append(")");
        return total_price.toString();
    }


    //Check Error Data
    //----------------------------------------------------------------------------------------------
    public static boolean isDataSuccess(String errorCode) {
        if (errorCode.equalsIgnoreCase("BCMN0000")) {
            return true;
        } else {
            return false;
        }
    }

    //Manshitsu and error
    //----------------------------------------------------------------------------------------------
    public static boolean isDataManshitsu(String errorCode) {
        if (!isDataSuccess(errorCode) && errorCode.equalsIgnoreCase("BAPI1007")) {
            return true;
        } else {
            return false;
        }
    }

    //Check Error Data
    //----------------------------------------------------------------------------------------------
    public static boolean isDataBGNL0004(String errorCode) {
        if (errorCode.equalsIgnoreCase("BGNL0004")) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isDataDuplicate(String errorCode) {
        if (errorCode.equalsIgnoreCase("BRSV0006")) {
            return true;
        } else {
            return false;
        }
    }

    //Check Error Data
    //----------------------------------------------------------------------------------------------
    public static int iData(String dataString) {
        return Integer.valueOf(dataString);
    }

    //Check Network
    //----------------------------------------------------------------------------------------------
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting() && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    //Dynamic Field record
    //----------------------------------------------------------------------------------------------
    public static ArrayList<String> getMultipleRoomsData(JSONObject json, String fieldName, int numberRooms) {
        ArrayList<String> dataArray = new ArrayList<String>();
        for (int i = 0; i < numberRooms; i++) {
            int x = i + 1;
            String rsrv_nmbr = "room" + x + "_" + fieldName;
            String dt = json.optString(rsrv_nmbr);
            if (dt != null && !dt.isEmpty()) {
                dataArray.add(i, dt);
            }
        }
        return dataArray;
    }


    //Dynamic Field record
    //----------------------------------------------------------------------------------------------
    public static ArrayList<String> getEcoDateToRemoveNullData(String[] allElements) {
        ArrayList<String> _localAllElements = new ArrayList<String>();
        int j = 0;
        for (int i = 0; i < allElements.length; i++) {
            if (allElements[i] != null && !allElements[i].equals("")) {
                _localAllElements.add(j, allElements[i]);
                j++;
            }
        }
        return _localAllElements;
    }

    //Check Valid URL
    //----------------------------------------------------------------------------------------------
    public static boolean isValidURL(String url) {
        URL u = null;

        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            u.toURI();
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }

    //Gender
    //----------------------------------------------------------------------------------------------
    public static KeyValuePairArrayAdapter getGenderKeyValueToAdapter(KeyValuePairArrayAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new KeyValuePair("M", "男性"));
        adapter.add(new KeyValuePair("F", "女性"));
        return adapter;
    }

    //Gender Key Value
    //----------------------------------------------------------------------------------------------
    public static String getGenderValueAccCode(String countryCode) {
        String gender = new String();
        if (countryCode.equalsIgnoreCase("M")) {
            gender = "男性";
        } else if (countryCode.equalsIgnoreCase("F")) {
            gender = "女性";
        }
        return gender;
    }


    //Country Key Value
    //----------------------------------------------------------------------------------------------
    public static KeyValuePairArrayAdapter getCountryKeyValueToAdapter(KeyValuePairArrayAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new KeyValuePair("JPN", "日本"));
        adapter.add(new KeyValuePair("CHN", "韓国"));
        adapter.add(new KeyValuePair("USA", "アメリカ合衆国"));
        adapter.add(new KeyValuePair("GBR", "イギリス"));
        adapter.add(new KeyValuePair("DEU", "ドイツ"));
        adapter.add(new KeyValuePair("FRA", "フランス"));
        adapter.add(new KeyValuePair("IND", "インド"));
        adapter.add(new KeyValuePair("AUS", "オーストラリア"));
        adapter.add(new KeyValuePair("NLD", "オランダ"));
        adapter.add(new KeyValuePair("CAN", "カナダ"));
        adapter.add(new KeyValuePair("SGP", "シンガポール"));
        adapter.add(new KeyValuePair("E99", "その他"));
        return adapter;
    }


    //Country Key Value
    //----------------------------------------------------------------------------------------------
    public static String getCountryValueAccCode(String countryCode) {
        String country = new String();
        if (countryCode.equalsIgnoreCase("JPN")) {
            country = "日本";
        } else if (countryCode.equalsIgnoreCase("CHN")) {
            country = "韓国";
        } else if (countryCode.equalsIgnoreCase("USA")) {
            country = "アメリカ合衆国";
        } else if (countryCode.equalsIgnoreCase("GBR")) {
            country = "イギリス";
        } else if (countryCode.equalsIgnoreCase("DEU")) {
            country = "ドイツ";
        } else if (countryCode.equalsIgnoreCase("FRA")) {
            country = "フランス";
        } else if (countryCode.equalsIgnoreCase("IND")) {
            country = "インド";
        } else if (countryCode.equalsIgnoreCase("AUS")) {
            country = "オーストラリア";
        } else if (countryCode.equalsIgnoreCase("NLD")) {
            country = "オランダ";
        } else if (countryCode.equalsIgnoreCase("CAN")) {
            country = "カナダ";
        } else if (countryCode.equalsIgnoreCase("SGP")) {
            country = "シンガポール";
        } else if (countryCode.equalsIgnoreCase("E99")) {
            country = "その他";
        }
        return country;
    }


    //MemberShipCard
    //----------------------------------------------------------------------------------------------
    public static KeyValuePairArrayAdapter setMembershipCardKeyValueToAdapter(KeyValuePairArrayAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new KeyValuePair("I", "I"));
        adapter.add(new KeyValuePair("G", "G"));
        adapter.add(new KeyValuePair("GV", "GV"));
        adapter.add(new KeyValuePair("L", "L"));
        adapter.add(new KeyValuePair("LV", "LV"));
        adapter.add(new KeyValuePair("T", "T"));
        return adapter;
    }


    //Country Key Value
    //----------------------------------------------------------------------------------------------
    public static KeyValuePairArrayAdapter getTimeKeyValueMember(KeyValuePairArrayAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new KeyValuePair("150000", "15:00"));
        adapter.add(new KeyValuePair("153000", "15:30"));
        adapter.add(new KeyValuePair("160000", "16:00"));
        adapter.add(new KeyValuePair("163000", "16:30"));
        adapter.add(new KeyValuePair("170000", "17:00"));
        adapter.add(new KeyValuePair("173000", "17:30"));
        adapter.add(new KeyValuePair("180000", "18:00"));
        adapter.add(new KeyValuePair("183000", "18:30"));
        adapter.add(new KeyValuePair("190000", "19:00"));
        adapter.add(new KeyValuePair("193000", "19:30"));
        adapter.add(new KeyValuePair("200000", "20:00"));
        adapter.add(new KeyValuePair("203000", "20:30"));
        adapter.add(new KeyValuePair("210000", "21:00"));
        adapter.add(new KeyValuePair("213000", "21:30"));
        adapter.add(new KeyValuePair("220000", "22:00"));
        adapter.add(new KeyValuePair("223000", "22:30"));
        adapter.add(new KeyValuePair("230000", "23:00"));
        adapter.add(new KeyValuePair("233000", "23:30"));
        adapter.add(new KeyValuePair("240000", "24:00"));
        return adapter;
    }

    //Country Key Value
    //----------------------------------------------------------------------------------------------
    public static KeyValuePairArrayAdapter getTimeKeyValueNonMember(KeyValuePairArrayAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new KeyValuePair("160000", "16:00"));
        adapter.add(new KeyValuePair("163000", "16:30"));
        adapter.add(new KeyValuePair("170000", "17:00"));
        adapter.add(new KeyValuePair("173000", "17:30"));
        adapter.add(new KeyValuePair("180000", "18:00"));
        adapter.add(new KeyValuePair("183000", "18:30"));
        adapter.add(new KeyValuePair("190000", "19:00"));
        adapter.add(new KeyValuePair("193000", "19:30"));
        adapter.add(new KeyValuePair("200000", "20:00"));
        adapter.add(new KeyValuePair("203000", "20:30"));
        adapter.add(new KeyValuePair("210000", "21:00"));
        adapter.add(new KeyValuePair("213000", "21:30"));
        adapter.add(new KeyValuePair("220000", "22:00"));
        adapter.add(new KeyValuePair("223000", "22:30"));
        adapter.add(new KeyValuePair("230000", "23:00"));
        adapter.add(new KeyValuePair("233000", "23:30"));
        adapter.add(new KeyValuePair("240000", "24:00"));
        return adapter;
    }


    //timeFormet
    //----------------------------------------------------------------------------------------------
    public static String getTimeValueAccCode(String timeFormet) {
        String time = new String();
        if (timeFormet.equalsIgnoreCase("150000")) {
            time = "15:00";
        } else if (timeFormet.equalsIgnoreCase("153000")) {
            time = "15:30";
        } else if (timeFormet.equalsIgnoreCase("160000")) {
            time = "16:00";
        } else if (timeFormet.equalsIgnoreCase("163000")) {
            time = "16:30";
        } else if (timeFormet.equalsIgnoreCase("170000")) {
            time = "17:00";
        } else if (timeFormet.equalsIgnoreCase("173000")) {
            time = "17:30";
        } else if (timeFormet.equalsIgnoreCase("180000")) {
            time = "18:00";
        } else if (timeFormet.equalsIgnoreCase("183000")) {
            time = "18:30";
        } else if (timeFormet.equalsIgnoreCase("190000")) {
            time = "19:00";
        } else if (timeFormet.equalsIgnoreCase("193000")) {
            time = "19:30";
        } else if (timeFormet.equalsIgnoreCase("200000")) {
            time = "20:00";
        } else if (timeFormet.equalsIgnoreCase("203000")) {
            time = "20:30";
        } else if (timeFormet.equalsIgnoreCase("210000")) {
            time = "21:00";
        } else if (timeFormet.equalsIgnoreCase("213000")) {
            time = "21:30";
        } else if (timeFormet.equalsIgnoreCase("220000")) {
            time = "22:00";
        } else if (timeFormet.equalsIgnoreCase("223000")) {
            time = "22:30";
        } else if (timeFormet.equalsIgnoreCase("230000")) {
            time = "23:00";
        } else if (timeFormet.equalsIgnoreCase("233000")) {
            time = "23:30";
        } else if (timeFormet.equalsIgnoreCase("240000")) {
            time = "24:00";
        }
        return time;
    }


    //Sex
    //----------------------------------------------------------------------------------------------
    public static String getSex(String Sex) {
        String sexData = new String();
        sexData = "男性";
        if (Sex.equalsIgnoreCase("F")) {
            sexData = "男性";
        }
        return sexData;
    }

    //Password Validation
    //----------------------------------------------------------------------------------------------
    public static boolean getPassValidation(String password) {
        String PASSWORD_PATTERN = "(?=.*[0-9a-zA-Z]).{6,}";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    //Remove Duplicate Value To AarrayList
    //----------------------------------------------------------------------------------------------
    public static ArrayList<String> getDistinctArrayList(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    //Remove Duplicate Value To AarrayList
    //----------------------------------------------------------------------------------------------
    public static Boolean chkArrayListduplicate(ArrayList<String> keywordName, String keyword) {
        boolean data = false;
        for (String k : keywordName) {
            if (k.equalsIgnoreCase(keyword)) {
                data = true;
            }
        }
        return data;
    }

    //Remove Duplicate Value To AarrayList
    //----------------------------------------------------------------------------------------------
    public static int getArrayListPosition(ArrayList<String> keywordName, String keyword) {
        int p = 0;
        for (String k : keywordName) {
            if (k.equalsIgnoreCase(keyword)) {
                p = keywordName.indexOf(k);
            }
        }
        return p;
    }

    //Ordering Map Values
    //----------------------------------------------------------------------------------------------
    public static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    //Remove Duplicate ArrayList
    //----------------------------------------------------------------------------------------------
    public static void getRemoveDuplicateData(List<String> lst) {
        List<String> data = new ArrayList<String>();
        Object[] st = lst.toArray();
        for (Object s : st) {
            if (lst.indexOf(s) != lst.lastIndexOf(s)) {
                lst.remove(lst.lastIndexOf(s));
            }
            data.add(s.toString());
        }
    }

    //Check ArrayList Size and Put 30 Par lise
    //----------------------------------------------------------------------------------------------
    public static String SubString(String dataString) {
        String fData = new String();
        if (dataString.length() > 30) {
            fData = dataString.substring(0, 30);
        } else {
            fData = dataString;
        }
        return fData;
    }

    //Replace DUpllicate From Arraylist
    //----------------------------------------------------------------------------------------------
    public static ArrayList<String> getReplaceDuplicate(ArrayList<String> arrList ,String dataString , int position){
        if(arrList != null) {
            int i = 0;
            for (String a : arrList) {
                if (arrList.get(position).equalsIgnoreCase(a)) {
                    if (a != null) {
                        arrList.set(i,dataString);
                    }else {
                        arrList.add(dataString);
                    }
                }
                i++;
            }
        }else{
            arrList.add(dataString);
        }
        return arrList;
    }


    //add 0 before 1 -9
    //----------------------------------------------------------------------------------------------
    public static String addZero19(int num) {
        String data = new String();
        data = String.valueOf(num);
        for (int x = 1; x < 10; x++) {
            if (num == x) {
                data = "0" + String.valueOf(num);
            }
        }
        return data;
    }

    //Display Size
    //----------------------------------------------------------------------------------------------
    public static String getDensityName(Context context) {
        float density = context.getResources().getDisplayMetrics().densityDpi;
        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }

    //Display Size
    //----------------------------------------------------------------------------------------------
    public static String getScreenSize(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }
    }

    //Display Size
    //----------------------------------------------------------------------------------------------
    public static HashMap<String, String> getScreen(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        HashMap<String, String> map = new HashMap<String, String>();
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                map.put("size", "MDPI");
                map.put("ecoWidth", "80");
                map.put("ecoHeight", "180");
                map.put("leftwidth", "260");
                return map;
            case DisplayMetrics.DENSITY_HIGH:
                map.put("size", "HDPI");
                map.put("ecoWidth", "70");
                map.put("ecoHeight", "140");
                map.put("leftwidth", "140");
                return map;
            case DisplayMetrics.DENSITY_LOW:
                map.put("size", "LDPI");
                map.put("ecoWidth", "80");
                map.put("ecoHeight", "180");
                map.put("leftwidth", "260");
                return map;
            case DisplayMetrics.DENSITY_XHIGH:
                map.put("size", "XHDPI");
                map.put("ecoWidth", "90");
                map.put("ecoHeight", "190");
                map.put("leftwidth", "260");
                return map;
            case DisplayMetrics.DENSITY_TV:
                map.put("size", "TV");
                map.put("ecoWidth", "80");
                map.put("ecoHeight", "180");
                map.put("leftwidth", "260");
                return map;
            case DisplayMetrics.DENSITY_XXHIGH:
                map.put("size", "XXHDPI");
                map.put("ecoWidth", "130");
                map.put("ecoHeight", "280");
                map.put("leftwidth", "350");
                return map;
            case DisplayMetrics.DENSITY_XXXHIGH:
                map.put("size", "XXXHDPI");
                map.put("ecoWidth", "130");
                map.put("ecoHeight", "280");
                map.put("leftwidth", "315");
                return map;
            default:
                map.put("size", "UNKNOWN");
                map.put("ecoWidth", "80");
                map.put("ecoHeight", "180");
                map.put("leftwidth", "315");
                return map;
        }
    }

    public static int equilibrium(int arr[], int n) {
        int i, j;
        int leftsum, rightsum;
        for (i = 0; i < n; ++i) {
            leftsum = 0;  // initialize left sum for current index i
            rightsum = 0; // initialize right sum for current index i

            /* get left sum */
            for (j = 0; j < i; j++)
                leftsum += arr[j];

            /* get right sum */
            for (j = i + 1; j < n; j++)
                rightsum += arr[j];

            /* if leftsum and rightsum are same, then we are done */
            if (leftsum == rightsum)
                return i;
        }
         /* return -1 if no equilibrium index is found */
        return -1;
    }

    public static int listWidth(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                //"MDPI";
                return 653;
            case DisplayMetrics.DENSITY_HIGH:
                //"HDPI";
            return 653;
            case DisplayMetrics.DENSITY_LOW:
                //"LDPI";
            return 653;
            case DisplayMetrics.DENSITY_XHIGH:
                //"XHDPI";
            return 653;
            case DisplayMetrics.DENSITY_TV:
                //"TV";
            return 653;
            case DisplayMetrics.DENSITY_XXHIGH:
                //"XXHDPI";
            return 1000;
            case DisplayMetrics.DENSITY_XXXHIGH:
                //"XXXHDPI";
            return 653;
            default:
                return 653;
        }
    }


    public static boolean mailCheck(String email){
        String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
        if( email.matches(mailFormat) ) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean alphabateOnly(String string){
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(string);
        return ms.matches();
    }

}



