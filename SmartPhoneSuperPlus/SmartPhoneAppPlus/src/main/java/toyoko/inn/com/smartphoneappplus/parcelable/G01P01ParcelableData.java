package toyoko.inn.com.smartphoneappplus.parcelable;

/**
 * Created by Farid on 2014/11/19.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static toyoko.inn.com.smartphoneappplus.common.ComLib.*;

public class G01P01ParcelableData implements Parcelable {

    //Hotel Information
    //----------------------------------------------------------------------------------------------
    String extraPageData;
    String memberPoints;
    String pageFlag;
    String switchPage;
    String subPageFlag;
    String hotelName;
    String hotelNum;
    String hotelCode;
    String hotelCodeNew;
    String roomType;
    String smokingFlag;
    String mood;
    String destinationKey;
    String longitude;
    String latitude;
    String hdLongitude;
    String hdLatitude;
    String distance;
    String country;
    String area;
    String state;
    String city;
    String countryCode;
    String areaCode;
    String stateCode;
    String cityCode;
    String checkinDate;
    String checkoutDate;
    String numberOfPeople;
    String numberOfRoom;
    String numberOfStayNight;
    String numberOfHotel;
    String numberOfRemainHotel;
    String cancelPolicy;
    String newsPushFlag;
    String myFavoritesPushFlag;
    String nearHotelPushFlag;
    Long calenderDate;
    String doTest;
    String myTestData;
    String room1CheckinTime;
    String creditCardExpireDate;
    String receiptType;
    String receiptName;

    //Room details
    //----------------------------------------------------------------------------------------------
    String rdRoomTypeCode;
    String rdPlanCode;
    String rdPlanName;
    String rdRoomName;
    String rdImageURL;
    String rdNumberOfMaxPeople;
    String rdOfficialWebDisPrice;
    String rdMemberOfficialWebDis;
    String rdSmokingFlag;
    String rdNumberOfRemainRooms;
    String rdLyngofchldrnavlblflag;
    String rdLyngprsns;
    String rdEcoavlblflag;
    String rdEcoFlag;
    String rdVodavlblflag;
    String rdVodFlag;
    String rdBsnsspackavlblflag;
    String rdBsnssPackFlag;
    String rdBsnssPackData;
    String rdBabyBad;
    String rdCheckInTime;
    String rdPrice;
    String rdTestData;
    String rdListPrice;
    String rdTotalListPrice;
    String rdTotalListPriceTax;
    String rdMemberPrice;
    String rdTotalMemberPrice;
    String rdTotalMemberPriceTax;
    String rdTotalPrice;
    String rdTotalPriceTax;
    String rdTotalOptionPrice;


    //Customer Information
    //----------------------------------------------------------------------------------------------
    String custAuthKey;
    String custLgnId;
    String custLgnPsswrd;
    String custRsrvsPrsnUid;
    String custRsrvid;
    String custRsrvtnNmbr;
    String custFmlyName;
    String custFrstName;
    String custDateBirth;
    String custSex;
    String custNtnltyCode;
    String custNtnltyValue;
    String custPhnNmbr;
    String custMmbrshpFlag;
    String custPcEmlAddrss;
    String custMbEmlAddrss;
    String custNwslttr;
    String custPsswrd;
    String custMmbrshpNmbr;
    String custMailSubmitFlag;
    String custProgressType;

    //Customer Information
    //----------------------------------------------------------------------------------------------
    String eatchDistance;
    String eatchRemainRoom;
    String eatchMemberPrice;
    String eatchMemberDiscPrice;
    String eatchListPrice;
    String eatchListDiscPrice;

    //Errors
    //----------------------------------------------------------------------------------------------
    String errrCode;
    String errrMssg;

    //List Data
    //----------------------------------------------------------------------------------------------
    ArrayList<String> lsCheckInDate;
    ArrayList<String> lsCheckOutDate;
    ArrayList<String> lsRoomTypeName;
    ArrayList<String> lsNumberPeople;
    ArrayList<String> lsFamilyName;
    ArrayList<String> lsFirstName;
    ArrayList<String> lsMembershipFlag;
    ArrayList<String> lsMembershipNumber;
    ArrayList<String> lsCountryCode;
    ArrayList<String> lsCountryValue;
    ArrayList<String> lsSex;
    ArrayList<String> lsMemberNo;
    ArrayList<String> lsPhoneNumber;
    ArrayList<String> lsEcoFlag;
    ArrayList<String> lsVodFlag;
    ArrayList<String> lsBusinessFlag;
    ArrayList<String> lsEcoDateSelection;
    ArrayList<String> lsBabyFlag;
    ArrayList<String> lsBusinessData;
    ArrayList<String> lsCheckinTime;
    ArrayList<String> lsEquipmentData;
    ArrayList<String> lsAccessData;
    ArrayList<String> lsLatitude;
    ArrayList<String> lsLongitude;
    ArrayList<String> lsPrc;
    ArrayList<String> lsPrcIncldngTax;
    ArrayList<String> lsEcoDataRoom1;
    ArrayList<String> lsEcoDataRoom2;
    ArrayList<String> lsEcoDataRoom3;
    ArrayList<String> lsEcoDataRoom4;
    ArrayList<String> lsRsrv_nmbr;

    //Search History
    //----------------------------------------------------------------------------------------------
    ArrayList<String> hsKeywordName;
    ArrayList<String> hsNumHotels;

    //Login List HashMap
    //----------------------------------------------------------------------------------------------
    private HashMap map;


    //Constructor
    //----------------------------------------------------------------------------------------------
    public G01P01ParcelableData() {
        super();
        map = new HashMap();
    }

    public G01P01ParcelableData(Parcel in) {
        map = new HashMap();
        readFromParcel(in);
    }


    public ArrayList<String> getLsCheckInDate() {
        return lsCheckInDate;
    }

    public void setLsCheckInDate(ArrayList<String> lsCheckInDate) {
        this.lsCheckInDate = lsCheckInDate;
    }

    public ArrayList<String> getLsCheckOutDate() {
        return lsCheckOutDate;
    }

    public void setLsCheckOutDate(ArrayList<String> lsCheckOutDate) {
        this.lsCheckOutDate = lsCheckOutDate;
    }

    public ArrayList<String> getLsRoomTypeName() {
        return lsRoomTypeName;
    }

    public void setLsRoomTypeName(ArrayList<String> lsRoomTypeName) {
        this.lsRoomTypeName = lsRoomTypeName;
    }

    public ArrayList<String> getLsNumberPeople() {
        return lsNumberPeople;
    }

    public void setLsNumberPeople(ArrayList<String> lsNumberPeople) {
        this.lsNumberPeople = lsNumberPeople;
    }

    public ArrayList<String> getLsMembershipNumber() {
        return lsMembershipNumber;
    }

    public void setLsMembershipNumber(ArrayList<String> lsMembershipNumber) {
        this.lsMembershipNumber = lsMembershipNumber;
    }

    public ArrayList<String> getLsEcoFlag() {
        return lsEcoFlag;
    }

    public void setLsEcoFlag(ArrayList<String> lsEcoFlag) {
        this.lsEcoFlag = lsEcoFlag;
    }

    public ArrayList<String> getLsVodFlag() {
        return lsVodFlag;
    }

    public void setLsVodFlag(ArrayList<String> lsVodFlag) {
        this.lsVodFlag = lsVodFlag;
    }

    public ArrayList<String> getLsBusinessFlag() {
        return lsBusinessFlag;
    }

    public void setLsBusinessFlag(ArrayList<String> lsBusinessFlag) {
        this.lsBusinessFlag = lsBusinessFlag;
    }

    public ArrayList<String> getLsEcoDateSelection() {
        return lsEcoDateSelection;
    }

    public void setLsEcoDateSelection(ArrayList<String> lsEcoDateSelection) {
        this.lsEcoDateSelection = lsEcoDateSelection;
    }

    public ArrayList<String> getLsBabyFlag() {
        return lsBabyFlag;
    }

    public void setLsBabyFlag(ArrayList<String> lsBabyFlag) {
        this.lsBabyFlag = lsBabyFlag;
    }

    public ArrayList<String> getLsBusinessData() {
        return lsBusinessData;
    }

    public void setLsBusinessData(ArrayList<String> lsBusinessData) {
        this.lsBusinessData = lsBusinessData;
    }

    public ArrayList<String> getLsCheckinTime() {
        return lsCheckinTime;
    }

    public void setLsCheckinTime(ArrayList<String> lsCheckinTime) {
        this.lsCheckinTime = lsCheckinTime;
    }

    public ArrayList<String> getLsPhoneNumber() {
        return lsPhoneNumber;
    }

    public void setLsPhoneNumber(ArrayList<String> lsPhoneNumber) {
        this.lsPhoneNumber = lsPhoneNumber;
    }


    public ArrayList<String> getLsFirstName() {
        Log.e("DATAX LS_FIRSTNAME",String.valueOf(this.lsFirstName.size()));
        return lsFirstName;
    }

    public void setLsFirstName(ArrayList<String> lsFirstName) {
        this.lsFirstName = lsFirstName;
    }

    public ArrayList<String> getLsFamilyName() {
        Log.e("DATAX LS_LASTNAME",String.valueOf(this.lsFamilyName.size()));
        return lsFamilyName;
    }

    public void setLsFamilyName(ArrayList<String> lsFamilyName) {
        this.lsFamilyName = lsFamilyName;
    }

    public ArrayList<String> getLsSex() {
        Log.e("DATAX LS_SEX",String.valueOf(this.lsSex.size()));
        return lsSex;
    }

    public void setLsSex(ArrayList<String> lsSex) {
        this.lsSex = lsSex;
    }

    public ArrayList<String> getLsCountryCode() {
        Log.e("DATAX LS_COUNTRY",String.valueOf(this.lsCountryCode.size()));
        return lsCountryCode;
    }

    public void setLsCountryCode(ArrayList<String> lsCountryCode) {
        this.lsCountryCode = lsCountryCode;
    }

    public ArrayList<String> getLsCountryValue() {
        return lsCountryValue;
    }

    public void setLsCountryValue(ArrayList<String> lsCountryValue) {
        this.lsCountryValue = lsCountryValue;
    }

    public ArrayList<String> getLsMembershipFlag() {
        Log.e("DATAX LS_MEMBERFLAG",String.valueOf(this.lsMembershipFlag.size()));
        return lsMembershipFlag;
    }

    public void setLsMembershipFlag(ArrayList<String> lsMembershipFlag) {
        this.lsMembershipFlag = lsMembershipFlag;
    }

    public ArrayList<String> getLsMembershipNo() {
        Log.e("DATAX LS_MEMBERNO",String.valueOf(this.lsMemberNo.size()));
        return lsMemberNo;
    }

    public void setLsMemberNumber(ArrayList<String> lsMemberNo) {
        this.lsMemberNo = lsMemberNo;
    }

    public ArrayList<String> getLsEquipmentData() {
        return lsEquipmentData;
    }

    public void setLsEquipmentData(ArrayList<String> lsEquipmentData) {
        this.lsEquipmentData = lsEquipmentData;
    }

    public ArrayList<String> getLsAccessData() {
        return lsAccessData;
    }

    public void setLsAccessData(ArrayList<String> lsAccessData) {
        this.lsAccessData = lsAccessData;
    }

    public ArrayList<String> getLsLatitude() {
        Log.e("DATAX LSLATITUDE",String.valueOf(lsLatitude.size()));
        return lsLatitude;
    }

    public void setLsLatitude(ArrayList<String> lsLatitude) {
        this.lsLatitude = lsLatitude;
    }

    public ArrayList<String> getLsLongitude() {
        Log.e("DATAX LSLONGITUDE",String.valueOf(lsLongitude.size()));
        return lsLongitude;
    }

    public void setLsLongitude(ArrayList<String> lsLongitude) {
        this.lsLongitude = lsLongitude;
    }

    public ArrayList<String> getLsPrc() {
        return lsPrc;
    }

    public void setLsPrc(ArrayList<String> lsPrc) {
        this.lsPrc = lsPrc;
    }

    public ArrayList<String> getLsPrcIncldngTax() {
        return lsPrcIncldngTax;
    }

    public void setLsPrcIncldngTax(ArrayList<String> lsPrcIncldngTax) {
        this.lsPrcIncldngTax = lsPrcIncldngTax;
    }

    public ArrayList<String> getLsEcoDataRoom1() {
        return lsEcoDataRoom1;
    }

    public void setLsEcoDataRoom1(ArrayList<String> lsEcoDataRoom1) {
        this.lsEcoDataRoom1 = lsEcoDataRoom1;
    }

    public ArrayList<String> getLsEcoDataRoom2() {
        return lsEcoDataRoom2;
    }

    public void setLsEcoDataRoom2(ArrayList<String> lsEcoDataRoom2) {
        this.lsEcoDataRoom2 = lsEcoDataRoom2;
    }

    public ArrayList<String> getLsEcoDataRoom3() {
        return lsEcoDataRoom3;
    }

    public void setLsEcoDataRoom3(ArrayList<String> lsEcoDataRoom3) {
        this.lsEcoDataRoom3 = lsEcoDataRoom3;
    }

    public ArrayList<String> getLsEcoDataRoom4() {
        return lsEcoDataRoom4;
    }

    public void setLsEcoDataRoom4(ArrayList<String> lsEcoDataRoom4) {
        this.lsEcoDataRoom4 = lsEcoDataRoom4;
    }

    public ArrayList<String> getLsRsrv_nmbr() {
        return lsRsrv_nmbr;
    }

    public void setLsRsrv_nmbr(ArrayList<String> lsRsrv_nmbr) {
        this.lsRsrv_nmbr = lsRsrv_nmbr;
    }

    public ArrayList<String> getHsKeywordName() {
        return hsKeywordName;
    }

    public void setHsKeywordName(ArrayList<String> hsKeywordName) {
        this.hsKeywordName = hsKeywordName;
    }

    public ArrayList<String> getHsNumHotels() {
        return hsNumHotels;
    }

    public void setHsNumHotels(ArrayList<String> hsNumHotels) {
        this.hsNumHotels = hsNumHotels;
    }

    //Hotel Information Start
    //----------------------------------------------------------------------------------------------
    public String getHotelName() {
        Log.e("DATAX HOTELNAME",this.hotelName);
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }


    public String getNumberOfHotel() {
        Log.e("DATAX NUMBEROFHOTEL",this.numberOfHotel);
        return numberOfHotel;
    }

    public void setNumberOfHotel(String numberOfHotel) {
        this.numberOfHotel = numberOfHotel;
    }

    public String getNumberOfRemainHotel() {
      //  Log.e("DATAX NUMBEROFREMAINHOTEL",this.numberOfRemainHotel);
        return numberOfRemainHotel;
    }

    public void setNumberOfRemainHotel(String numberOfRemainHotel) {
        this.numberOfRemainHotel = numberOfRemainHotel;
    }

    public String getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public String getNewsPushFlag() {
        return newsPushFlag;
    }

    public void setNewsPushFlag(String newsPushFlag) {
        this.newsPushFlag = newsPushFlag;
    }

    public String getMyFavoritesPushFlag() {
        return myFavoritesPushFlag;
    }

    public void setMyFavoritesPushFlag(String myFavoritesPushFlag) {
        this.myFavoritesPushFlag = myFavoritesPushFlag;
    }

    public String getNearHotelPushFlag() {
        return nearHotelPushFlag;
    }

    public void setNearHotelPushFlag(String nearHotelPushFlag) {
        this.nearHotelPushFlag = nearHotelPushFlag;
    }

    public Long getCalenderDate() {
        return calenderDate;
    }

    public void setCalenderDate(Long calenderDate) {
        this.calenderDate = calenderDate;
    }

    public String getCheckinDate() {
        Log.e("DATAX CHECKINDATE",this.checkinDate);
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        Log.e("DATAX CHECKOUTDATE",this.checkoutDate);
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getExtraPageData() {
        return extraPageData;
    }

    public void setExtraPageData(String extraPageData) {
        this.extraPageData = extraPageData;
    }

    public String getMemberPoints() {
        Log.e("DATAX MEMBERPOINTS",this.memberPoints);
        return memberPoints;
    }

    public void setMemberPoints(String memberPoints) {
        this.memberPoints = memberPoints;
    }

    public String getPageFlag() {
        Log.e("DATAX PAGEFLAG",this.pageFlag);
        return pageFlag;
    }

    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }

    public String getSwitchPage() {
        return switchPage;
    }

    public void setSwitchPage(String switchPage) {
        this.switchPage = switchPage;
    }

    public String getSubPageFlag() {
        return subPageFlag;
    }

    public void setSubPageFlag(String subPageFlag) {
        this.subPageFlag = subPageFlag;
    }

    public String getNumberOfStayNight() {
        Log.e("DATAX NUMBEROFSTAYNIGHT",this.numberOfStayNight);
        return numberOfStayNight;
    }

    public void setNumberOfStayNight(String numberOfStayNight) {
        this.numberOfStayNight = numberOfStayNight;
    }

    public String getMyTestData() {
        Log.e("DATAX MYTESTDATAX",this.myTestData);
        return myTestData;
    }

    public void setMyTestData(String myTestData) {
        this.myTestData = myTestData;
    }

    public String getRoom1CheckinTime() {
        return room1CheckinTime;
    }

    public void setRoom1CheckinTime(String room1CheckinTime) {
        this.room1CheckinTime = room1CheckinTime;
    }

    public String getCreditCardExpireDate() {
        return creditCardExpireDate;
    }

    public void setCreditCardExpireDate(String creditCardExpireDate) {
        this.creditCardExpireDate = creditCardExpireDate;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getDoTest() {
        Log.e("DATAX DOTEST",this.doTest);
        return doTest;
    }

    public void setDoTest(String doTest) {
        this.doTest = doTest;
    }



    public String getNumberOfPeople() {
        Log.e("DATAX NUMBEROFPEOPLE",this.numberOfPeople);
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getNumberOfRoom() {
        Log.e("DATAX NUMBEROFROOM",this.numberOfRoom);
        return numberOfRoom;
    }


    public String getHotelNum() {
        Log.e("DATAX HOTELNUM",this.hotelNum);
        return hotelNum;
    }

    public void setHotelNum(String hotelNum) {
        this.hotelNum = hotelNum;
    }


    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getCountryCode() {
        Log.e("DATAX COUNTRYCODE",this.countryCode);
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        Log.e("DATAX AREACODE",this.areaCode);
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    public String getStateCode() {
        Log.e("DATAX STATECODE",this.stateCode);
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        Log.e("DATAX COUNTRY",this.country);
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        Log.e("DATAX AREA",this.area);
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        Log.e("DATAX STATE",this.state);
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistance() {
        Log.e("DATAX DISTANCE",this.distance);
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLongitude() {
        Log.e("DATAX LONGITUDE",this.longitude);
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHdLongitude() {
        return hdLongitude;
    }

    public void setHdLongitude(String hdLongitude) {
        this.hdLongitude = hdLongitude;
    }

    public String getHdLatitude() {
        return hdLatitude;
    }

    public void setHdLatitude(String hdLatitude) {
        this.hdLatitude = hdLatitude;
    }

    public String getRoomType() {
        Log.e("DATAX ROOMTYPE",this.roomType);
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSmokingFlag() {
        return smokingFlag;
    }

    public void setSmokingFlag(String smokingFlag) {
        this.smokingFlag = smokingFlag;
    }

    public String getMood() {
        Log.e("DATAX MOOD",this.mood);
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDestinationKey() {
        Log.e("DATAX DESTINATIONKEY",this.destinationKey);
        return destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public String getHotelCode() {
        Log.e("DATAX HOTELCODE",this.hotelCode);
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }


    public String getHotelCodeNew() {
        Log.e("DATAX HOTELCODENEW",this.hotelCodeNew);
        return hotelCodeNew;
    }

    public void setHotelCodeNew(String hotelCodeNew) {
        this.hotelCodeNew = hotelCodeNew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Room Details Data
    //----------------------------------------------------------------------------------------------
    public String getRdRoomTypeCode() {
        Log.e("DATAX RDROOMTYPECODE",this.rdRoomTypeCode);
        return rdRoomTypeCode;
    }

    public void setRdRoomTypeCode(String rdRoomTypeCode) {
        this.rdRoomTypeCode = rdRoomTypeCode;
    }

    public String getRdPlanCode() {
        return rdPlanCode;
    }

    public void setRdPlanCode(String rdPlanCode) {
        this.rdPlanCode = rdPlanCode;
    }

    public String getRdPlanName() {
        return rdPlanName;
    }

    public void setRdPlanName(String rdPlanName) {
        this.rdPlanName = rdPlanName;
    }

    public String getRdRoomName() {
        Log.e("DATAX RDROOMNAME",this.rdRoomName);
        return rdRoomName;
    }

    public void setRdRoomName(String rdRoomName) {
        this.rdRoomName = rdRoomName;
    }

    public String getRdImageURL() {
        Log.e("DATAX RDIMAGEURL",this.rdImageURL);
        return rdImageURL;
    }

    public void setRdImageURL(String rdImageURL) {
        this.rdImageURL = rdImageURL;
    }

    public String getRdNumberOfMaxPeople() {
        Log.e("DATAX RDMAXIMUMPEOPLE",this.rdNumberOfMaxPeople);
        return rdNumberOfMaxPeople;
    }

    public void setRdNumberOfMaxPeople(String rdNumberOfMaxPeople) {
        this.rdNumberOfMaxPeople = rdNumberOfMaxPeople;
    }

    public String getRdListPrice() {
        Log.e("DATAX RDLISTPRICE",this.rdListPrice);
        return rdListPrice;
    }

    public void setRdListPrice(String rdListPrice) {
        this.rdListPrice = rdListPrice;
    }

    public String getRdMemberPrice() {
        Log.e("DATAX RDMEMBERPRICE",this.rdMemberPrice);
        return rdMemberPrice;
    }

    public void setRdMemberPrice(String rdMemberPrice) {
        this.rdMemberPrice = rdMemberPrice;
    }

    public String getRdOfficialWebDisPrice() {
        //Log.e("DATAX RDOFFICIALWEBDISPRICE",this.rdOfficialWebDisPrice);
        return rdOfficialWebDisPrice;
    }

    public void setRdOfficialWebDisPrice(String rdOfficialWebDisPrice) {
        this.rdOfficialWebDisPrice = rdOfficialWebDisPrice;
    }

    public String getRdMemberOfficialWebDis() {
        //Log.e("DATAX RDMEMBEROFFICIALWEBDIS",this.rdMemberOfficialWebDis);
        return rdMemberOfficialWebDis;
    }

    public void setRdMemberOfficialWebDis(String rdMemberOfficialWebDis) {
        this.rdMemberOfficialWebDis = rdMemberOfficialWebDis;
    }

    public String getRdSmokingFlag() {
        Log.e("DATAX RDSMOKINGFLAG",this.rdSmokingFlag);
        return rdSmokingFlag;
    }

    public void setRdSmokingFlag(String rdSmokingFlag) {
        this.rdSmokingFlag = rdSmokingFlag;
    }

    public String getRdNumberOfRemainRooms() {
        //Log.e("DATAX RDNUMBEROFREMAINROOMS",this.rdNumberOfRemainRooms);
        return rdNumberOfRemainRooms;
    }

    public void setRdNumberOfRemainRooms(String rdNumberOfRemainRooms) {
        this.rdNumberOfRemainRooms = rdNumberOfRemainRooms;
    }

    public String getRdLyngofchldrnavlblflag() {
        //Log.e("DATAX RDLYNGOFCHLDRNAVLBLFLAG",this.rdLyngofchldrnavlblflag);
        return rdLyngofchldrnavlblflag;
    }

    public void setRdLyngofchldrnavlblflag(String rdLyngofchldrnavlblflag) {
        this.rdLyngofchldrnavlblflag = rdLyngofchldrnavlblflag;
    }

    public String getRdLyngprsns() {
        Log.e("DATAX RDLYNGPRSNS",this.rdLyngprsns);
        return rdLyngprsns;
    }

    public void setRdLyngprsns(String rdLyngprsns) {
        this.rdLyngprsns = rdLyngprsns;
    }

    public String getRdEcoavlblflag() {
        Log.e("DATAX RDECOAVLBLFLAG",this.rdEcoavlblflag);
        return rdEcoavlblflag;
    }

    public void setRdEcoavlblflag(String rdEcoavlblflag) {
        this.rdEcoavlblflag = rdEcoavlblflag;
    }


    public String getRdEcoFlag() {
        Log.e("DATAX RDECOLAG",this.rdEcoFlag);
        return rdEcoFlag;
    }

    public void setRdEcoFlag(String rdEcoFlag) {
        this.rdEcoFlag = rdEcoFlag;
    }

    public String getRdVodavlblflag() {
        Log.e("DATAX RDVODAVLBLFLAG",this.rdVodavlblflag);
        return rdVodavlblflag;
    }

    public void setRdVodavlblflag(String rdVodavlblflag) {
        this.rdVodavlblflag = rdVodavlblflag;
    }

    public String getRdVodFlag() {
        Log.e("DATAX RDVODFLAG",this.rdVodFlag);
        return rdVodFlag;
    }

    public void setRdVodFlag(String rdVodFlag) {
        this.rdVodFlag = rdVodFlag;
    }

    public String getRdBsnsspackavlblflag() {
//        Log.e("DATAX RDBSNSSPACKAVLBLFLAG",this.rdBsnsspackavlblflag);
        return rdBsnsspackavlblflag;
    }

    public void setRdBsnsspackavlblflag(String rdBsnsspackavlblflag) {
        this.rdBsnsspackavlblflag = rdBsnsspackavlblflag;
    }

    public String getRdBsnssPackFlag() {
        Log.e("DATAX RDBSNSSPACKFLAG",this.rdBsnssPackFlag);
        return rdBsnssPackFlag;
    }

    public void setRdBsnssPackFlag(String rdBsnssPackFlag) {
        this.rdBsnssPackFlag = rdBsnssPackFlag;
    }

    public String getRdBsnssPackData() {
        return rdBsnssPackData;
    }

    public void setRdBsnssPackData(String rdBsnssPackData) {
        this.rdBsnssPackData = rdBsnssPackData;
    }

    public String getRdBabyBad() {
        return rdBabyBad;
    }

    public void setRdBabyBad(String rdBabyBad) {
        this.rdBabyBad = rdBabyBad;
    }

    public String getRdCheckInTime() {
        return rdCheckInTime;
    }

    public void setRdCheckInTime(String rdCheckInTime) {
        this.rdCheckInTime = rdCheckInTime;
    }

    public String getRdPrice() {
        Log.e("DATAX RDPRICE",this.rdPrice);
        return rdPrice;
    }

    public void setRdPrice(String rdPrice) {
        this.rdPrice = rdPrice;
    }

    public String getRdTestData() {
        return rdTestData;
    }

    public void setRdTestData(String rdTestData) {
        this.rdTestData = rdTestData;
    }


    public String getRdTotalListPrice() {
        Log.e("DATAX RDTOTALLISTPRICE",this.rdTotalListPrice);
        return rdTotalListPrice;
    }

    public void setRdTotalListPrice(String rdTotalListPrice) {
        this.rdTotalListPrice = rdTotalListPrice;
    }

    public String getRdTotalListPriceTax() {
       // Log.e("DATAX RDTOTALLISTPRICETAX",this.rdTotalListPriceTax);
        return rdTotalListPriceTax;
    }

    public void setRdTotalListPriceTax(String rdTotalListPriceTax) {
        this.rdTotalListPriceTax = rdTotalListPriceTax;
    }

    public String getRdTotalMemberPrice() {
      //  Log.e("DATAX RDTOTALMEMBERPRICE",this.rdTotalMemberPrice);
        return rdTotalMemberPrice;
    }

    public void setRdTotalMemberPrice(String rdTotalMemberPrice) {
        this.rdTotalMemberPrice = rdTotalMemberPrice;
    }

    public String getRdTotalMemberPriceTax() {
      //  Log.e("DATAX RDTOTALMEMBERPRICETAX",this.rdTotalMemberPriceTax);
        return rdTotalMemberPriceTax;
    }

    public void setRdTotalMemberPriceTax(String rdTotalMemberPriceTax) {
        this.rdTotalMemberPriceTax = rdTotalMemberPriceTax;
    }

    public String getRdTotalPrice() {
        Log.e("DATAX RDTOTALPRICE",this.rdTotalPrice);
        return rdTotalPrice;
    }

    public void setRdTotalPrice(String rdTotalPrice) {
        this.rdTotalPrice = rdTotalPrice;
    }

    public String getRdTotalPriceTax() {
        Log.e("DATAX RDTOTALPRICETAX",this.rdTotalPriceTax);
        return rdTotalPriceTax;
    }

    public void setRdTotalPriceTax(String rdTotalPriceTax) {
        this.rdTotalPriceTax = rdTotalPriceTax;
    }

    public String getRdTotalOptionPrice() {
        return rdTotalOptionPrice;
    }

    public void setRdTotalOptionPrice(String rdTotalOptionPrice) {
        this.rdTotalOptionPrice = rdTotalOptionPrice;
    }

    //Customer Information
    //----------------------------------------------------------------------------------------------

    public String getCustAuthKey() {
        Log.e("DATAX CUSTAUTHKEY",this.custAuthKey);
        return custAuthKey;
    }

    public void setCustAuthKey(String custAuthKey) {
        this.custAuthKey = custAuthKey;
    }

    public String getCustLgnId() {
        Log.e("DATAX CUSTLOGINID",this.custLgnId);
        return custLgnId;
    }
    public void setCustLgnId(String custLgnId) {
        this.custLgnId = custLgnId;
    }
    public String getCustLgnPsswrd() {
        Log.e("DATAX CUSTPASSWORD",this.custLgnId);
        return custLgnPsswrd;
    }
    public void setCustLgnPsswrd(String custLgnPsswrd) {
        this.custLgnPsswrd = custLgnPsswrd;
    }
    public String getCustRsrvsPrsnUid() {
        Log.e("DATAX CUSTRSRVSPRSNUID",this.custRsrvsPrsnUid);
        return custRsrvsPrsnUid;
    }
    public void setCustRsrvsPrsnUid(String custRsrvsPrsnUid) {
        this.custRsrvsPrsnUid = custRsrvsPrsnUid;
    }

    public String getCustRsrvid() {
        Log.e("DATAX CUSTRSRVID",this.custRsrvid);
        return custRsrvid;
    }

    public void setCustRsrvid(String custRsrvid) {
        this.custRsrvid = custRsrvid;
    }

    public String getCustRsrvtnNmbr() {
        Log.e("DATAX CUSTRSRVTNNMBR",this.custRsrvtnNmbr);
        return custRsrvtnNmbr;
    }

    public void setCustRsrvtnNmbr(String custRsrvtnNmbr) {
        this.custRsrvtnNmbr = custRsrvtnNmbr;
    }

    public String getCustFmlyName() {
        Log.e("DATAX CUSTFMLYNAME",this.custFmlyName);
        return custFmlyName;
    }
    public void setCustFmlyName(String custFmlyName) {
        this.custFmlyName = custFmlyName;
    }
    public String getCustFrstName() {
        Log.e("DATAX CUSTFRSTNAME",this.custFrstName);
        return custFrstName;
    }
    public void setCustFrstName(String custFrstName) {
        this.custFrstName = custFrstName;
    }
    public String getCustDateBirth() {
        Log.e("DATAX CUSTDATEBIRTH",this.custDateBirth);
        return custDateBirth;
    }
    public void setCustDateBirth(String custDateBirth) {
        this.custDateBirth = custDateBirth;
    }
    public String getCustSex() {
        Log.e("DATAX CUSTSEX",this.custSex);
        return custSex;
    }
    public void setCustSex(String custSex) {
        this.custSex = custSex;
    }
    public String getCustNtnltyCode() {
        Log.e("DATAX CUSTNTNLTYCODE",this.custNtnltyCode);
        return custNtnltyCode;
    }
    public void setCustNtnltyCode(String custNtnltyCode) {
        this.custNtnltyCode = custNtnltyCode;
    }

    public String getCustNtnltyValue() {
        Log.e("DATAX CUSTNTNLTYVALUE",this.custNtnltyValue);
        return custNtnltyValue;
    }

    public void setCustNtnltyValue(String custNtnltyValue) {
        this.custNtnltyValue = custNtnltyValue;
    }

    public String getCustPhnNmbr() {
        Log.e("DATAX CUSTPHNNMBR",this.custPhnNmbr);
        return custPhnNmbr;
    }
    public void setCustPhnNmbr(String custPhnNmbr) {
        this.custPhnNmbr = custPhnNmbr;
    }
    public String getCustMmbrshpFlag() {
        Log.e("DATAX CUSTMMBRSHPFLAG",this.custMmbrshpFlag);
        return custMmbrshpFlag;
    }
    public void setCustMmbrshpFlag(String custMmbrshpFlag) {
        this.custMmbrshpFlag = custMmbrshpFlag;
    }
    public String getCustPcEmlAddrss() {
        Log.e("DATAX CUSTPCEMLADDRSS",this.custPcEmlAddrss);
        return custPcEmlAddrss;
    }
    public void setCustPcEmlAddrss(String custPcEmlAddrss) {
        this.custPcEmlAddrss = custPcEmlAddrss;
    }

    public String getCustMbEmlAddrss() {
        Log.e("DATAX CUSTMBEMLADDRSS",this.custMbEmlAddrss);
        return custMbEmlAddrss;
    }

    public void setCustMbEmlAddrss(String custMbEmlAddrss) {
        this.custMbEmlAddrss = custMbEmlAddrss;
    }

    public String getCustNwslttr() {
        Log.e("DATAX GETCUSTNWSLTTR",this.custNwslttr);
        return custNwslttr;
    }
    public void setCustNwslttr(String custNwslttr) {
        this.custNwslttr = custNwslttr;
    }
    public String getCustPsswrd() {
        Log.e("DATAX CUSTPSSWRD",this.custPsswrd);
        return custPsswrd;
    }
    public void setCustPsswrd(String custPsswrd) {
        this.custPsswrd = custPsswrd;
    }
    public String getCustMmbrshpNmbr() {
        Log.e("DATAX CUSTMMBRSHPNMBR",this.custMmbrshpNmbr);
        return custMmbrshpNmbr;
    }
    public void setCustMmbrshpNmbr(String custMmbrshpNmbr) {
        this.custMmbrshpNmbr = custMmbrshpNmbr;
    }

    public String getCustMailSubmitFlag() {
       // Log.e("DATAX CUSTMAILSUBMITFLAG",this.custMailSubmitFlag);
        return custMailSubmitFlag;
    }

    public void setCustMailSubmitFlag(String custMailSubmitFlag) {
        this.custMailSubmitFlag = custMailSubmitFlag;
    }

    public String getCustProgressType() {
        Log.e("DATAX CUSTPROGRESSTYPE",this.custProgressType);
        return custProgressType;
    }

    public void setCustProgressType(String custProgressType) {
        this.custProgressType = custProgressType;
    }

    public String getEatchDistance() {
        return eatchDistance;
    }

    public void setEatchDistance(String eatchDistance) {
        this.eatchDistance = eatchDistance;
    }

    public String getEatchRemainRoom() {
        return eatchRemainRoom;
    }

    public void setEatchRemainRoom(String eatchRemainRoom) {
        this.eatchRemainRoom = eatchRemainRoom;
    }

    public String getEatchMemberPrice() {
        return eatchMemberPrice;
    }

    public void setEatchMemberPrice(String eatchMemberPrice) {
        this.eatchMemberPrice = eatchMemberPrice;
    }

    public String getEatchMemberDiscPrice() {
        return eatchMemberDiscPrice;
    }

    public void setEatchMemberDiscPrice(String eatchMemberDiscPrice) {
        this.eatchMemberDiscPrice = eatchMemberDiscPrice;
    }

    public String getEatchListPrice() {
        return eatchListPrice;
    }

    public void setEatchListPrice(String eatchListPrice) {
        this.eatchListPrice = eatchListPrice;
    }

    public String getEatchListDiscPrice() {
        return eatchListDiscPrice;
    }

    public void setEatchListDiscPrice(String eatchListDiscPrice) {
        this.eatchListDiscPrice = eatchListDiscPrice;
    }

    //Errror Code
    //----------------------------------------------------------------------------------------------
    public String getErrrCode() {
        return errrCode;
    }
    public void setErrrCode(String errrCode) {
        this.errrCode = errrCode;
    }
    public String getErrrMssg() {
        return errrMssg;
    }
    public void setErrrMssg(String errrMssg) {
        this.errrMssg = errrMssg;
    }

    //HashMap Setter Getter
    //----------------------------------------------------------------------------------------------
    public String get(String key) {
        return String.valueOf(map.get(key));
    }

    public void put(String key, String value) {
        map.put(key, value);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //Hotel Information
        //------------------------------------------------------------------------------------------
        dest.writeString(extraPageData);
        dest.writeString(memberPoints);
        dest.writeString(pageFlag);
        dest.writeString(switchPage);
        dest.writeString(subPageFlag);
        dest.writeString(hotelName);
        dest.writeString(hotelNum);
        dest.writeString(hotelCode);
        dest.writeString(hotelCodeNew);
        dest.writeString(roomType);
        dest.writeString(smokingFlag);
        dest.writeString(mood);
        dest.writeString(destinationKey);
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(hdLongitude);
        dest.writeString(hdLatitude);
        dest.writeString(distance);
        dest.writeString(country);
        dest.writeString(area);
        dest.writeString(state);
        dest.writeString(city);
        dest.writeString(countryCode);
        dest.writeString(areaCode);
        dest.writeString(stateCode);
        dest.writeString(cityCode);
        dest.writeString(checkinDate);
        dest.writeString(checkoutDate);
        dest.writeString(numberOfPeople);
        dest.writeString(numberOfRoom);
        dest.writeString(numberOfStayNight);
        dest.writeString(numberOfHotel);
        dest.writeString(numberOfRemainHotel);
        dest.writeString(cancelPolicy);
        dest.writeString(newsPushFlag);
        dest.writeString(myFavoritesPushFlag);
        dest.writeString(nearHotelPushFlag);
        dest.writeLong(calenderDate);
        dest.writeString(doTest);
        dest.writeString(myTestData);
        dest.writeString(room1CheckinTime);
        dest.writeString(creditCardExpireDate);
        dest.writeString(receiptType);
        dest.writeString(receiptName);



        //Room Details
        //------------------------------------------------------------------------------------------
        dest.writeString(rdRoomTypeCode);
        dest.writeString(rdPlanCode);
        dest.writeString(rdPlanName);
        dest.writeString(rdRoomName);
        dest.writeString(rdImageURL);
        dest.writeString(rdNumberOfMaxPeople);
        dest.writeString(rdOfficialWebDisPrice);
        dest.writeString(rdMemberOfficialWebDis);
        dest.writeString(rdSmokingFlag);
        dest.writeString(rdNumberOfRemainRooms);
        dest.writeString(rdLyngofchldrnavlblflag);
        dest.writeString(rdLyngprsns);
        dest.writeString(rdEcoavlblflag);
        dest.writeString(rdEcoFlag);
        dest.writeString(rdVodavlblflag);
        dest.writeString(rdVodFlag);
        dest.writeString(rdBsnsspackavlblflag);
        dest.writeString(rdBsnssPackFlag);
        dest.writeString(rdBsnssPackData);
        dest.writeString(rdBabyBad);
        dest.writeString(rdCheckInTime);
        dest.writeString(rdPrice);
        dest.writeString(rdTestData);
        dest.writeString(rdListPrice);
        dest.writeString(rdTotalListPrice);
        dest.writeString(rdTotalListPriceTax);
        dest.writeString(rdMemberPrice);
        dest.writeString(rdTotalMemberPrice);
        dest.writeString(rdTotalMemberPriceTax);
        dest.writeString(rdTotalPrice);
        dest.writeString(rdTotalPriceTax);
        dest.writeString(rdTotalOptionPrice);

        //Customer Information
        //------------------------------------------------------------------------------------------
        dest.writeString(custAuthKey);
        dest.writeString(custLgnId);
        dest.writeString(custLgnPsswrd);
        dest.writeString(custRsrvsPrsnUid);
        dest.writeString(custRsrvid);
        dest.writeString(custRsrvtnNmbr);
        dest.writeString(custFmlyName);
        dest.writeString(custFrstName);
        dest.writeString(custDateBirth);
        dest.writeString(custSex);
        dest.writeString(custNtnltyCode);
        dest.writeString(custNtnltyValue);
        dest.writeString(custPhnNmbr);
        dest.writeString(custMmbrshpFlag);
        dest.writeString(custPcEmlAddrss);
        dest.writeString(custMbEmlAddrss);
        dest.writeString(custNwslttr);
        dest.writeString(custPsswrd);
        dest.writeString(custMmbrshpNmbr);
        dest.writeString(custMailSubmitFlag);
        dest.writeString(custProgressType);

        //Each Row
        //------------------------------------------------------------------------------------------
        dest.writeString(eatchDistance);
        dest.writeString(eatchRemainRoom);
         dest.writeString(eatchMemberPrice);
         dest.writeString(eatchMemberDiscPrice);
         dest.writeString(eatchListPrice);
         dest.writeString(eatchListDiscPrice);


        //Errors
        //------------------------------------------------------------------------------------------
        dest.writeString(errrCode);
        dest.writeString(errrMssg);

        //List Data
        //------------------------------------------------------------------------------------------
        dest.writeStringList(lsCheckInDate);
        dest.writeStringList(lsCheckOutDate);
        dest.writeStringList(lsRoomTypeName);
        dest.writeStringList(lsNumberPeople);
        dest.writeStringList(lsFamilyName);
        dest.writeStringList(lsFirstName);
        dest.writeStringList(lsMembershipFlag);
        dest.writeStringList(lsMembershipNumber);
        dest.writeStringList(lsCountryCode);
        dest.writeStringList(lsCountryValue);
        dest.writeStringList(lsSex);
        dest.writeStringList(lsMemberNo);
        dest.writeStringList(lsPhoneNumber);
        dest.writeStringList(lsEcoFlag);
        dest.writeStringList(lsVodFlag);
        dest.writeStringList(lsBusinessFlag);
        dest.writeStringList(lsEcoDateSelection);
        dest.writeStringList(lsBabyFlag);
        dest.writeStringList(lsBusinessData);
        dest.writeStringList(lsCheckinTime);
        dest.writeStringList(lsEquipmentData);
        dest.writeStringList(lsAccessData);
        dest.writeStringList(lsLatitude);
        dest.writeStringList(lsLongitude);
        dest.writeStringList(lsPrc);
        dest.writeStringList(lsPrcIncldngTax);
        dest.writeStringList(lsEcoDataRoom1);
        dest.writeStringList(lsEcoDataRoom2);
        dest.writeStringList(lsEcoDataRoom3);
        dest.writeStringList(lsEcoDataRoom4);
        dest.writeStringList(lsRsrv_nmbr);

        //History Search
        //------------------------------------------------------------------------------------------
        dest.writeStringList(hsKeywordName);
        dest.writeStringList(hsNumHotels);


        //HashMap
        //------------------------------------------------------------------------------------------
        dest.writeInt(map.size());
        for (Object s: map.keySet()) {
            dest.writeString((String) s);
            dest.writeString(String.valueOf(map.get(s)));
        }

    }

    private void readFromParcel(Parcel in) {
        extraPageData = in.readString();
        memberPoints = in.readString();
        pageFlag = in.readString();
        switchPage = in.readString();
        subPageFlag = in.readString();
        hotelName = in.readString();
        hotelNum = in.readString();
        hotelCode = in.readString();
        hotelCodeNew = in.readString();
        roomType = in.readString();
        smokingFlag = in.readString();
        mood = in.readString();
        destinationKey = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        hdLongitude = in.readString();
        hdLatitude = in.readString();
        distance = in.readString();
        country = in.readString();
        area = in.readString();
        state = in.readString();
        city = in.readString();
        countryCode = in.readString();
        areaCode = in.readString();
        stateCode = in.readString();
        cityCode = in.readString();
        checkinDate = in.readString();
        checkoutDate = in.readString();
        numberOfPeople = in.readString();
        numberOfRoom = in.readString();
        numberOfStayNight = in.readString();
        numberOfHotel = in.readString();
        numberOfRemainHotel = in.readString();
        cancelPolicy = in.readString();
        newsPushFlag = in.readString();
        myFavoritesPushFlag = in.readString();
        nearHotelPushFlag = in.readString();
        calenderDate = in.readLong();
        doTest = in.readString();
        myTestData = in.readString();
        room1CheckinTime = in.readString();
        creditCardExpireDate = in.readString();
        receiptType = in.readString();
        receiptName = in.readString();

        //Room Details
        //------------------------------------------------------------------------------------------
        rdRoomTypeCode = in.readString();
        rdPlanCode = in.readString();
        rdPlanName = in.readString();
        rdRoomName = in.readString();
        rdImageURL = in.readString();
        rdNumberOfMaxPeople = in.readString();
        rdOfficialWebDisPrice = in.readString();
        rdMemberOfficialWebDis = in.readString();
        rdSmokingFlag = in.readString();
        rdNumberOfRemainRooms = in.readString();
        rdLyngofchldrnavlblflag = in.readString();
        rdLyngprsns = in.readString();
        rdEcoavlblflag = in.readString();
        rdEcoFlag = in.readString();
        rdVodavlblflag = in.readString();
        rdVodFlag = in.readString();
        rdBsnsspackavlblflag = in.readString();
        rdBsnssPackFlag = in.readString();
        rdBsnssPackData = in.readString();
        rdBabyBad = in.readString();
        rdCheckInTime = in.readString();
        rdPrice = in.readString();
        rdTestData = in.readString();
        rdListPrice = in.readString();
        rdTotalListPrice = in.readString();
        rdTotalListPriceTax = in.readString();
        rdMemberPrice = in.readString();
        rdTotalMemberPrice = in.readString();
        rdTotalMemberPriceTax = in.readString();
        rdTotalPrice = in.readString();
        rdTotalPriceTax = in.readString();
        rdTotalOptionPrice = in.readString();

        //Customer Information
        //------------------------------------------------------------------------------------------
        custAuthKey = in.readString();
        custLgnId = in.readString();
        custLgnPsswrd = in.readString();
        custRsrvsPrsnUid = in.readString();
        custRsrvid = in.readString();
        custRsrvtnNmbr = in.readString();
        custFmlyName = in.readString();
        custFrstName = in.readString();
        custDateBirth = in.readString();
        custSex = in.readString();
        custNtnltyCode = in.readString();
        custNtnltyValue = in.readString();
        custPhnNmbr = in.readString();
        custMmbrshpFlag = in.readString();
        custPcEmlAddrss = in.readString();
        custMbEmlAddrss = in.readString();
        custNwslttr = in.readString();
        custPsswrd = in.readString();
        custMmbrshpNmbr = in.readString();
        custMailSubmitFlag = in.readString();
        custProgressType = in.readString();

        //Eatch Hotel Distance
        //------------------------------------------------------------------------------------------
        eatchDistance = in.readString();
        eatchRemainRoom = in.readString();
        eatchMemberPrice = in.readString();
        eatchMemberDiscPrice = in.readString();
        eatchListPrice = in.readString();
        eatchListDiscPrice = in.readString();

        //Errors
        //------------------------------------------------------------------------------------------
        errrCode = in.readString();
        errrMssg = in.readString();

        //List Data
        //------------------------------------------------------------------------------------------
        lsCheckInDate = in.createStringArrayList();
        lsCheckOutDate = in.createStringArrayList();
        lsRoomTypeName = in.createStringArrayList();
        lsNumberPeople = in.createStringArrayList();
        lsFamilyName = in.createStringArrayList();
        lsFirstName = in.createStringArrayList();
        lsMembershipFlag = in.createStringArrayList();
        lsMembershipNumber = in.createStringArrayList();
        lsCountryCode = in.createStringArrayList();
        lsCountryValue = in.createStringArrayList();
        lsSex = in.createStringArrayList();
        lsMemberNo = in.createStringArrayList();
        lsPhoneNumber = in.createStringArrayList();
        lsEcoFlag = in.createStringArrayList();
        lsVodFlag = in.createStringArrayList();
        lsBusinessFlag = in.createStringArrayList();
        lsEcoDateSelection = in.createStringArrayList();
        lsBabyFlag = in.createStringArrayList();
        lsBusinessData = in.createStringArrayList();
        lsCheckinTime = in.createStringArrayList();
        lsEquipmentData = in.createStringArrayList();
        lsAccessData = in.createStringArrayList();
        lsLatitude = in.createStringArrayList();
        lsLongitude = in.createStringArrayList();
        lsPrc = in.createStringArrayList();
        lsPrcIncldngTax = in.createStringArrayList();
        lsEcoDataRoom1 = in.createStringArrayList();
        lsEcoDataRoom2 = in.createStringArrayList();
        lsEcoDataRoom3 = in.createStringArrayList();
        lsEcoDataRoom4 = in.createStringArrayList();
        lsRsrv_nmbr = in.createStringArrayList();

        //History name
        //------------------------------------------------------------------------------------------
        hsKeywordName = in.createStringArrayList();
        hsNumHotels = in.createStringArrayList();

        //Login List HashMap
        //------------------------------------------------------------------------------------------
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            map.put(in.readString(), in.readString());
        }
    }

    public static final Creator<G01P01ParcelableData> CREATOR = new Creator<G01P01ParcelableData>() {

        @Override
        public G01P01ParcelableData createFromParcel(Parcel source) {
            return new G01P01ParcelableData(source);
        }

        @Override
        public G01P01ParcelableData[] newArray(int size) {
            return new G01P01ParcelableData[size];
        }

    };

    public void initialalize(){
        ArrayList<String> hsKeywordName = new ArrayList<String>();
        ArrayList<String> hsNumHotels = new ArrayList<String>();
        this.setExtraPageData("");
        this.setSwitchPage("");
        this.setHsKeywordName(hsKeywordName);
        this.setHsNumHotels(hsNumHotels);
        this.setRoomType("");
        this.setSmokingFlag("");
        this.setDestinationKey("");
        this.setMood("");
        this.setLatitude("35.559511");
        this.setLongitude("139.712301");
        this.setDistance("5");
        this.setCountry("");
        this.setCountryCode("");
        this.setArea("");
        this.setAreaCode("");
        this.setState("");
        this.setStateCode("");
        this.setCityCode("");
        this.setRoom1CheckinTime("");
        this.setCreditCardExpireDate("");
        this.setReceiptType("");
        this.setReceiptName("");
        this.setNumberOfRemainHotel("0");
        this.setCalenderDate(0L);
        this.setHotelNum("");
        this.setCheckinDate(dateGetCurrentDate());
        this.setCheckoutDate("");
        this.setPageFlag("");
        this.setSubPageFlag("");
        this.setNumberOfStayNight("");
        this.setNumberOfRoom("1");
        this.setNumberOfPeople("1");
        this.setNumberOfHotel("");
        this.setCustRsrvsPrsnUid("");
        this.setCustLgnId("");
        this.setCustFrstName("");
        this.setCustFmlyName("");
        this.setCustDateBirth("");
        this.setCustSex("");
        this.setCustNtnltyCode("");
        this.setCustPhnNmbr("");
        this.setCustMmbrshpFlag("");
        this.setCustPcEmlAddrss("");
        this.setCustNwslttr("");
        this.setCustMmbrshpFlag("N");

        this.setRdPlanCode("");
        this.setRdSmokingFlag("");
        this.setRdImageURL("");
        this.setRdSmokingFlag("");
        this.setRdRoomTypeCode("");

        this.setEatchDistance("");
        this.setEatchMemberPrice("");
        this.setEatchMemberDiscPrice("");
        this.setEatchListPrice("");
        this.setEatchListDiscPrice("");
    }
}
