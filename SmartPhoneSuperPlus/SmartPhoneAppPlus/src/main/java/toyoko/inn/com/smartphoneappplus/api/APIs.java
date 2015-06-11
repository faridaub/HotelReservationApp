package toyoko.inn.com.smartphoneappplus.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import toyoko.inn.com.smartphoneappplus.common.ComConstant;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
/**
 *  API Parametter Method
 *  @version 1.0
 *  @author Anamul Haq Farid
 *  @since 2015-05-20
 */
public class APIs {
    private int serviceMood;

    private String dvcTkn;
    private String osType;
    private String applctnVrsnNmbr;
    private String key;
    private String osVrsn;
    private String mdl;
    private String lngg;
    private String mood;
    private String lngtd;
    private String lttd;
    private String dstnc;
    private String cntryCode;
    private String areaCode;
    private String sttCode;
    private String cityCode;
    private String hotelCode;
    private String kywrd;
    private String checkInDate;
    private String chcktDate;
    private String nmbrNghts;
    private String nmbrPpl;
    private String nmbrRms;
    private String smkngFlag;
    private String mmbrshpFlag;
    private String roomType;
    private String planCode;
    private String ecoFlag;
    private ArrayList<String> ecoDtsList1;
    private ArrayList<String> ecoDtsList2;
    private ArrayList<String> ecoDtsList3;
    private ArrayList<String> ecoDtsList4;
    private String vodFlag;
    private String bsnssPackFlag;
    private String bsnssPackType;
    private String rsrvsPrsnUid;
    private String prcssngType;
    private String fmlyName;
    private String frstName;
    private String dateBirth;
    private String sex;
    private String ntnltyCode;
    private String phnNmbr;
    private String pcEmlAddrss;
    private String emlAddrss;
    private String emlAddrss2;
    private String nwslttr;
    private String psswrd;
    private String mmbrshpNmbr;
    private String pageNmbr;
    private String fvrtHtlCode;
    private String ecoChckn;
    private String chldrnShrngBed;
    private String chcknTime;
    private String prcList;
    private String prcIncldngTaxList;
    private String optnPrc;
    private String sbttlPrc;
    private String sbttlPrcIncldngTax;
    private String ttlPrc;
    private String ttlPrcIncldngTax;
    private String nmbrRsrvtns;
    private String receiptType;
    private String receiptName;
    private String newsPushFlag;
    private String myFvrtsPushFlag;
    private String nrstHtlsPushFlag;
    private String dltFlag;

    //Login
    //----------------------------------------------------------------------------------------------
    private String lgnId;
    private String lgnPsswrd;
    private String rgstrtnId;
    private String athntctnKey;
    private String rsrvId;
    private String rsrvtnNmbr;
    private String room1_chcknTime;


    //Credit Information
    //----------------------------------------------------------------------------------------------
    private String crdtCardNmbr;
    private String crdtCardHldr;
    private String crdtCardexprtnDate;


    //URL
    //----------------------------------------------------------------------------------------------
    //private static String DOMAIN = "http://testwebapi1.cloudapp.net/smart_phone/";
    private static String DOMAIN ="http://webapi.toyoko-inn.com/smart_phone/";
    private static String URLA001 = DOMAIN + "search_point_api";
    private static String URLA002 = DOMAIN + "search_hotel_coordinate_api";
    private static String URLA003 = DOMAIN + "search_hotel_keyword_api";
    private static String URLA004 = DOMAIN + "search_hotel_area_api";
    private static String URLA005 = DOMAIN + "search_hotel_api";
    private static String URLA006 = DOMAIN + "search_room_type_api";
    private static String URLA007 = DOMAIN + "search_hotel_details_api";
    private static String URLA008 = DOMAIN + "search_hotel_vacant_api";
    private static String URLA009 = DOMAIN + "search_room_type_vacant_api";
    private static String URLA010 = DOMAIN + "search_room_type_details_api";
    private static String URLA011 = DOMAIN + "search_room_type_price_api";
    private static String URLA012 = DOMAIN + "check_booking_api";
    private static String URLA013 = DOMAIN + "register_reservation_api";
    private static String URLA014 = DOMAIN + "search_booking_api";
    private static String URLA015 = DOMAIN + "search_booking_details_api";
    private static String URLA016 = DOMAIN + "cancel_reservation_api";
    private static String URLA017 = DOMAIN + "change_reservation_api";
    private static String URLA018 = DOMAIN + "search_stay_history_api";
    private static String URLA019 = DOMAIN + "search_stay_history_details_api";
    private static String URLA020 = DOMAIN + "search_favorite_hotel_api";
    private static String URLA021 = DOMAIN + "entry_favorite_hotel_api";
    private static String URLA022 = DOMAIN + "search_customer_information_api";
    private static String URLA023 = DOMAIN + "change_customer_information_api";
    private static String URLA024 = DOMAIN + "search_customer_setting_api";
    private static String URLA025 = DOMAIN + "change_customer_setting_api";
    private static String URLA026 = DOMAIN + "attests_member_api";
    private static String URLA027 = DOMAIN + "attests_name_birth_date_api";
    private static String URLA028 = DOMAIN + "attests_birth_date_password_api";
    private static String URLA029 = DOMAIN + "entry_personal_information_api";
    private static String URLA030 = DOMAIN + "search_browsing_history_api";
    private static String URLA032 = DOMAIN + "login_api";
    private static String URLA033 = DOMAIN + "check_initialization_api";
    private static String URLA034 = DOMAIN + "entry_initialization_api";
    private static String URLA035 = DOMAIN + "send_password_forgotten_mail_api";
    private static String URLA036 = DOMAIN + "reset_password_api";

    //Common
    //----------------------------------------------------------------------------------------------
    private void AddCommonFields(ArrayList<NameValuePair> pairs) {
        setServiceMood(1);
        if (this.getServiceMood() == 0) {
            this.setDvcTkn("FAD702E7-15B0-4019-8B93-2EAFCE85241");
            this.setOsType("I");
            this.setApplctnVrsnNmbr("1");
            this.setKey("funtion_test1.01");
            this.setOsVrsn("7.1");
            this.setMdl("iPhone%20Simulator");
            this.setLngg("ja");
        } else if (this.getServiceMood() == 1) {
            this.setDvcTkn("FAD702E7-15B0-4019-8B93-2EAFCE85241");
            this.setOsType("I");
            this.setApplctnVrsnNmbr("1");
            this.setKey("webapi.toyoko-inn.com");
            this.setOsVrsn("7.1");
            this.setMdl("iPhone%20Simulator");
            this.setLngg("ja");
        } else {
            this.setDvcTkn("FAD702E7-15B0-4019-8B93-2EAFCE85241");
            this.setOsType("I");
            this.setApplctnVrsnNmbr("1");
            this.setKey("webapi.toyoko-inn.com");
            this.setOsVrsn("7.1");
            this.setMdl("iPhone%20Simulator");
            this.setLngg("ja");
        }

        pairs.add(new BasicNameValuePair("dvcTkn", this.getDvcTkn()));
        pairs.add(new BasicNameValuePair("osType", this.getOsType()));
        pairs.add(new BasicNameValuePair("applctnVrsnNmbr", this.getApplctnVrsnNmbr()));
        pairs.add(new BasicNameValuePair("osVrsn", this.getOsVrsn()));
        pairs.add(new BasicNameValuePair("mdl", this.getMdl()));
        pairs.add(new BasicNameValuePair("lngg", this.getLngg()));
        pairs.add(new BasicNameValuePair("key", this.getKey()));
    }


    //URL
    //----------------------------------------------------------------------------------------------
    public static String getURLA001() {
        return URLA001;
    }

    public static String getURLA002() {
        return URLA002;
    }

    public static String getURLA003() {
        return URLA003;
    }

    public static String getURLA004() {
        return URLA004;
    }

    public static String getURLA005() {
        return URLA005;
    }

    public static String getURLA006() {
        return URLA006;
    }

    public static String getURLA008() {
        return URLA008;
    }

    public static String getURLA007() {
        return URLA007;
    }

    public static String getURLA009() {
        return URLA009;
    }

    public static String getURLA010() {
        return URLA010;
    }

    public static String getURLA011() {
        return URLA011;
    }

    public static String getURLA012() {
        return URLA012;
    }

    public static String getURLA013() {
        return URLA013;
    }

    public static String getURLA014() {
        return URLA014;
    }

    public static String getURLA015() {
        return URLA015;
    }

    public static String getURLA016() {
        return URLA016;
    }

    public static String getURLA017() {
        return URLA017;
    }

    public static String getURLA018() {
        return URLA018;
    }

    public static String getURLA019() {
        return URLA019;
    }

    public static String getURLA020() {
        return URLA020;
    }

    public static String getURLA021() {
        return URLA021;
    }

    public static String getURLA022() {
        return URLA022;
    }

    public static String getURLA023() {
        return URLA023;
    }

    public static String getURLA024() {
        return URLA024;
    }

    public static String getURLA025() {
        return URLA025;
    }

    public static String getURLA026() {
        return URLA026;
    }

    public static String getURLA027() {
        return URLA027;
    }

    public static String getURLA028() {
        return URLA028;
    }

    public static String getURLA029() {
        return URLA029;
    }

    public static String getURLA030() {
        return URLA030;
    }

    public static String getURLA032() {
        return URLA032;
    }

    public static String getURLA033() {
        return URLA033;
    }

    public static String getURLA034() {
        return URLA034;
    }

    public static String getURLA035() {
        return URLA035;
    }

    public static String getURLA036() {
        return URLA036;
    }

    //Parametter
    //----------------------------------------------------------------------------------------------

    public int getServiceMood() {
        return serviceMood;
    }

    public void setServiceMood(int serviceMood) {
        this.serviceMood = serviceMood;
    }

    public String getKywrd() {
        return kywrd;
    }

    public void setKywrd(String kywrd) {
        this.kywrd = kywrd;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVrsn() {
        return osVrsn;
    }

    public void setOsVrsn(String osVrsn) {
        this.osVrsn = osVrsn;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getLngg() {
        return lngg;
    }

    public void setLngg(String lngg) {
        this.lngg = lngg;
    }

    public String getDvcTkn() {
        return dvcTkn;
    }

    public void setDvcTkn(String dvcTkn) {
        this.dvcTkn = dvcTkn;
    }

    public String getApplctnVrsnNmbr() {
        return applctnVrsnNmbr;
    }

    public void setApplctnVrsnNmbr(String applctnVrsnNmbr) {
        this.applctnVrsnNmbr = applctnVrsnNmbr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getChcktDate() {
        return chcktDate;
    }

    public void setChcktDate(String chcktDate) {
        this.chcktDate = chcktDate;
    }

    public String getNmbrPpl() {
        return nmbrPpl;
    }

    public void setNmbrPpl(String nmbrPpl) {
        this.nmbrPpl = nmbrPpl;
    }

    public String getNmbrRms() {
        return nmbrRms;
    }

    public void setNmbrRms(String nmbrRms) {
        this.nmbrRms = nmbrRms;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getCntryCode() {
        return cntryCode;
    }

    public void setCntryCode(String cntryCode) {
        this.cntryCode = cntryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSttCode() {
        return sttCode;
    }

    public void setSttCode(String sttCode) {
        this.sttCode = sttCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDstnc() {
        return dstnc;
    }

    public void setDstnc(String dstnc) {
        this.dstnc = dstnc;
    }

    public String getLngtd() {
        return lngtd;
    }

    public void setLngtd(String lngtd) {
        this.lngtd = lngtd;
    }

    public String getLttd() {
        return lttd;
    }

    public void setLttd(String lttd) {
        this.lttd = lttd;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNmbrNghts() {
        return nmbrNghts;
    }

    public void setNmbrNghts(String nmbrNghts) {
        this.nmbrNghts = nmbrNghts;
    }

    public String getSmkngFlag() {
        return smkngFlag;
    }

    public void setSmkngFlag(String smkngFlag) {
        this.smkngFlag = smkngFlag;
    }

    public String getMmbrshpFlag() {
        return mmbrshpFlag;
    }

    public void setMmbrshpFlag(String mmbrshpFlag) {
        this.mmbrshpFlag = mmbrshpFlag;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getEcoFlag() {
        return ecoFlag;
    }

    public void setEcoFlag(String ecoFlag) {
        this.ecoFlag = ecoFlag;
    }

    public ArrayList<String> getEcoDtsList1() {
        return ecoDtsList1;
    }

    public void setEcoDtsList1(ArrayList<String> ecoDtsList1) {
        this.ecoDtsList1 = ecoDtsList1;
    }

    public ArrayList<String> getEcoDtsList2() {
        return ecoDtsList2;
    }

    public void setEcoDtsList2(ArrayList<String> ecoDtsList2) {
        this.ecoDtsList2 = ecoDtsList2;
    }

    public ArrayList<String> getEcoDtsList3() {
        return ecoDtsList3;
    }

    public void setEcoDtsList3(ArrayList<String> ecoDtsList3) {
        this.ecoDtsList3 = ecoDtsList3;
    }

    public ArrayList<String> getEcoDtsList4() {
        return ecoDtsList4;
    }

    public void setEcoDtsList4(ArrayList<String> ecoDtsList4) {
        this.ecoDtsList4 = ecoDtsList4;
    }

    public String getVodFlag() {
        return vodFlag;
    }

    public void setVodFlag(String vodFlag) {
        this.vodFlag = vodFlag;
    }

    public String getBsnssPackFlag() {
        return bsnssPackFlag;
    }

    public void setBsnssPackFlag(String bsnssPackFlag) {
        this.bsnssPackFlag = bsnssPackFlag;
    }

    public String getBsnssPackType() {
        return bsnssPackType;
    }

    public void setBsnssPackType(String bsnssPackType) {
        this.bsnssPackType = bsnssPackType;
    }

    public String getRsrvsPrsnUid() {
        return rsrvsPrsnUid;
    }

    public void setRsrvsPrsnUid(String rsrvsPrsnUid) {
        this.rsrvsPrsnUid = rsrvsPrsnUid;
    }

    public String getPrcssngType() {
        return prcssngType;
    }

    public void setPrcssngType(String prcssngType) {
        this.prcssngType = prcssngType;
    }

    public String getFmlyName() {
        return fmlyName;
    }

    public void setFmlyName(String fmlyName) {
        this.fmlyName = fmlyName;
    }

    public String getFrstName() {
        return frstName;
    }

    public void setFrstName(String frstName) {
        this.frstName = frstName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNtnltyCode() {
        return ntnltyCode;
    }

    public void setNtnltyCode(String ntnltyCode) {
        this.ntnltyCode = ntnltyCode;
    }

    public String getPhnNmbr() {
        return phnNmbr;
    }

    public void setPhnNmbr(String phnNmbr) {
        this.phnNmbr = phnNmbr;
    }

    public String getPcEmlAddrss() {
        return pcEmlAddrss;
    }

    public void setPcEmlAddrss(String pcEmlAddrss) {
        this.pcEmlAddrss = pcEmlAddrss;
    }

    public String getEmlAddrss() {
        return emlAddrss;
    }

    public void setEmlAddrss(String emlAddrss) {
        this.emlAddrss = emlAddrss;
    }

    public String getEmlAddrss2() {
        return emlAddrss2;
    }

    public void setEmlAddrss2(String emlAddrss2) {
        this.emlAddrss2 = emlAddrss2;
    }

    public String getNwslttr() {
        return nwslttr;
    }

    public void setNwslttr(String nwslttr) {
        this.nwslttr = nwslttr;
    }

    public String getPsswrd() {
        return psswrd;
    }

    public void setPsswrd(String psswrd) {
        this.psswrd = psswrd;
    }

    public String getMmbrshpNmbr() {
        return mmbrshpNmbr;
    }

    public void setMmbrshpNmbr(String mmbrshpNmbr) {
        this.mmbrshpNmbr = mmbrshpNmbr;
    }

    public String getPageNmbr() {
        return pageNmbr;
    }

    public void setPageNmbr(String pageNmbr) {
        this.pageNmbr = pageNmbr;
    }

    public String getFvrtHtlCode() {
        return fvrtHtlCode;
    }

    public void setFvrtHtlCode(String fvrtHtlCode) {
        this.fvrtHtlCode = fvrtHtlCode;
    }

    public String getEcoChckn() {
        return ecoChckn;
    }

    public void setEcoChckn(String ecoChckn) {
        this.ecoChckn = ecoChckn;
    }

    public String getChldrnShrngBed() {
        return chldrnShrngBed;
    }

    public void setChldrnShrngBed(String chldrnShrngBed) {
        this.chldrnShrngBed = chldrnShrngBed;
    }

    public String getChcknTime() {
        return chcknTime;
    }

    public void setChcknTime(String chcknTime) {
        this.chcknTime = chcknTime;
    }

    public String getPrcList() {
        return prcList;
    }

    public void setPrcList(String prcList) {
        this.prcList = prcList;
    }

    public String getPrcIncldngTaxList() {
        return prcIncldngTaxList;
    }

    public void setPrcIncldngTaxList(String prcIncldngTaxList) {
        this.prcIncldngTaxList = prcIncldngTaxList;
    }

    public String getOptnPrc() {
        return optnPrc;
    }

    public void setOptnPrc(String optnPrc) {
        this.optnPrc = optnPrc;
    }

    public String getSbttlPrc() {
        return sbttlPrc;
    }

    public void setSbttlPrc(String sbttlPrc) {
        this.sbttlPrc = sbttlPrc;
    }

    public String getSbttlPrcIncldngTax() {
        return sbttlPrcIncldngTax;
    }

    public void setSbttlPrcIncldngTax(String sbttlPrcIncldngTax) {
        this.sbttlPrcIncldngTax = sbttlPrcIncldngTax;
    }

    public String getTtlPrc() {
        return ttlPrc;
    }

    public void setTtlPrc(String ttlPrc) {
        this.ttlPrc = ttlPrc;
    }

    public String getTtlPrcIncldngTax() {
        return ttlPrcIncldngTax;
    }

    public void setTtlPrcIncldngTax(String ttlPrcIncldngTax) {
        this.ttlPrcIncldngTax = ttlPrcIncldngTax;
    }

    public String getNmbrRsrvtns() {
        return nmbrRsrvtns;
    }

    public void setNmbrRsrvtns(String nmbrRsrvtns) {
        this.nmbrRsrvtns = nmbrRsrvtns;
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

    public String getNewsPushFlag() {
        return newsPushFlag;
    }

    public void setNewsPushFlag(String newsPushFlag) {
        this.newsPushFlag = newsPushFlag;
    }

    public String getMyFvrtsPushFlag() {
        return myFvrtsPushFlag;
    }

    public void setMyFvrtsPushFlag(String myFvrtsPushFlag) {
        this.myFvrtsPushFlag = myFvrtsPushFlag;
    }

    public String getNrstHtlsPushFlag() {
        return nrstHtlsPushFlag;
    }

    public void setNrstHtlsPushFlag(String nrstHtlsPushFlag) {
        this.nrstHtlsPushFlag = nrstHtlsPushFlag;
    }

    public String getDltFlag() {
        return dltFlag;
    }

    public void setDltFlag(String dltFlag) {
        this.dltFlag = dltFlag;
    }

    public String getRsrvId() {
        return rsrvId;
    }

    public void setRsrvId(String rsrvId) {
        this.rsrvId = rsrvId;
    }

    public String getRsrvtnNmbr() {
        return rsrvtnNmbr;
    }

    public void setRsrvtnNmbr(String rsrvtnNmbr) {
        this.rsrvtnNmbr = rsrvtnNmbr;
    }


    public String getRoom1_chcknTime() {
        return room1_chcknTime;
    }

    public void setRoom1_chcknTime(String room1_chcknTime) {
        this.room1_chcknTime = room1_chcknTime;
    }


    //Credit Info
    //----------------------------------------------------------------------------------------------
    public String getCrdtCardNmbr() {
        return crdtCardNmbr;
    }

    public void setCrdtCardNmbr(String crdtCardNmbr) {
        this.crdtCardNmbr = crdtCardNmbr;
    }

    public String getCrdtCardHldr() {
        return crdtCardHldr;
    }

    public void setCrdtCardHldr(String crdtCardHldr) {
        this.crdtCardHldr = crdtCardHldr;
    }

    public String getCrdtCardexprtnDate() {
        return crdtCardexprtnDate;
    }

    public void setCrdtCardexprtnDate(String crdtCardexprtnDate) {
        this.crdtCardexprtnDate = crdtCardexprtnDate;
    }

    //Login Setter Getter
    //----------------------------------------------------------------------------------------------
    public String getLgnId() {
        return lgnId;
    }

    public void setLgnId(String lgnId) {
        this.lgnId = lgnId;
    }

    public String getLgnPsswrd() {
        return lgnPsswrd;
    }

    public void setLgnPsswrd(String lgnPsswrd) {
        this.lgnPsswrd = lgnPsswrd;
    }

    public String getRgstrtnId() {
        return rgstrtnId;
    }

    public void setRgstrtnId(String rgstrtnId) {
        this.rgstrtnId = rgstrtnId;
    }

    public String getAthntctnKey() {
        return athntctnKey;
    }

    public void setAthntctnKey(String athntctnKey) {
        this.athntctnKey = athntctnKey;
    }


    //A001 ポイント検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA001() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, getMmbrshpNmbr()));
        return pairs;
    }

    //A002 空室ホテルの座標値検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA002() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        return pairs;
    }


    //A003 空室ホテルをキーワード検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA003() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_KYWRD, this.getKywrd()));
        Log.e("PARAM A003", pairs.toString());
        return pairs;
    }

    //A004 空室ホテルをエリア検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA004() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        return pairs;
    }

    //A005 空室ホテル数検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA005() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_SMKNGFLAG, this.getSmkngFlag()));
        pairs.add(new BasicNameValuePair(CT_LNGTD, this.getLngtd()));
        pairs.add(new BasicNameValuePair(CT_LTTD, this.getLttd()));
        pairs.add(new BasicNameValuePair(CT_DSTNC, this.getDstnc()));
        pairs.add(new BasicNameValuePair(CT_ROOMTYPE, this.getRoomType()));
        return pairs;
    }

    //A006 部屋タイプの空室数検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA006() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_SMKNGFLAG, this.getSmkngFlag()));
        pairs.add(new BasicNameValuePair(CT_MODE, this.getMood()));
        pairs.add(new BasicNameValuePair(CT_KYWRD, this.getKywrd()));
        pairs.add(new BasicNameValuePair(CT_CNTRYCODE, this.getCntryCode()));
        pairs.add(new BasicNameValuePair(CT_AREACODE, this.getAreaCode()));
        pairs.add(new BasicNameValuePair(CT_STTCODE, this.getSttCode()));
        pairs.add(new BasicNameValuePair(CT_CITYCODE, this.getCityCode()));
        pairs.add(new BasicNameValuePair(CT_LNGTD, this.getLngtd()));
        pairs.add(new BasicNameValuePair(CT_LTTD, this.getLttd()));
        pairs.add(new BasicNameValuePair(CT_DSTNC, this.getDstnc()));
        pairs.add(new BasicNameValuePair(CT_ROOMTYPE, this.getRoomType()));
        return pairs;
    }

    //A007 ホテル詳細情報検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA007() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        return pairs;
    }

    //A008 ホテル空室数検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA008() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_SMKNGFLAG, this.getSmkngFlag()));
        pairs.add(new BasicNameValuePair(CT_MODE, this.getMood()));
        pairs.add(new BasicNameValuePair(CT_KYWRD, this.getKywrd()));
        pairs.add(new BasicNameValuePair(CT_CNTRYCODE, this.getCntryCode()));
        pairs.add(new BasicNameValuePair(CT_AREACODE, this.getAreaCode()));
        pairs.add(new BasicNameValuePair(CT_STTCODE, this.getSttCode()));
        pairs.add(new BasicNameValuePair(CT_CITYCODE, this.getCityCode()));
        pairs.add(new BasicNameValuePair(CT_LNGTD, this.getLngtd()));
        pairs.add(new BasicNameValuePair(CT_LTTD, this.getLttd()));
        pairs.add(new BasicNameValuePair(CT_DSTNC, this.getDstnc()));
        pairs.add(new BasicNameValuePair(CT_ROOMTYPE, this.getRoomType()));
        return pairs;
    }

    //A009 部屋タイプ別空室数検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA009() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_SMKNGFLAG, this.getSmkngFlag()));
        return pairs;
    }

    //A010 部屋タイプ詳細検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA010() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_ROOMTYPE, this.getRoomType()));
        pairs.add(new BasicNameValuePair(CT_PLANCODE, this.getPlanCode()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_SMKNGFLAG, this.getSmkngFlag()));
        return pairs;
    }

    //A011 部屋タイプ価格検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA011() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_CHCKNDATE, this.getCheckInDate()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_ROOMTYPE, this.getRoomType()));
        pairs.add(new BasicNameValuePair(CT_NMBRNGHTS, this.getNmbrNghts()));
        pairs.add(new BasicNameValuePair(CT_NMBRPPL, this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_ECOFLAG, this.getEcoFlag()));
        //pairs.add(new BasicNameValuePair("ecoDtsList1", this.getEcoDtsList1()));
        pairs.add(new BasicNameValuePair(CT_VODFLAG, this.getVodFlag()));
        pairs.add(new BasicNameValuePair(CT_BSNSSPACKFLAG, this.getBsnssPackFlag()));
        pairs.add(new BasicNameValuePair(CT_BSNSSPACKTYPE, this.getBsnssPackType()));
        return pairs;
    }

    //A012 予約チェック
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA012(int numRooms, ArrayList<Map<String, ArrayList<String>>> data) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair(CT_MODE, this.getMood()));

        getExtraLoopDataA012(pairs, numRooms, data);

        pairs.add(new BasicNameValuePair("room1_chcknTime", this.getRoom1_chcknTime()));
        pairs.add(new BasicNameValuePair(CT_TTLPRC, "5000"));
        pairs.add(new BasicNameValuePair(CT_TTLPRCINCLDNGTAX, "5400"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDNMBR, "123456789012345"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDHLDR, "HOYOKO TESTA"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDEXPRTNDATE, "20151231"));

        return pairs;
    }

    private void getExtraLoopDataA012(ArrayList<NameValuePair> pairs, int max, ArrayList<Map<String, ArrayList<String>>> data) {

        for (int rm = 0; rm < max; rm++) {
            int x = rm + 1;
            String rsrvtnNmbr = "room" + x + "_rsrvtnNmbr";
            pairs.add(new BasicNameValuePair(rsrvtnNmbr, data.get(0).get(CT_RSRVTNNMBR).get(rm)));

            String checkindate = "room" + x + "_chcknDate";
            pairs.add(new BasicNameValuePair(checkindate, data.get(0).get(CT_CHCKNDATE).get(rm)));

            String checkout = "room" + x + "_chcktDate";
            pairs.add(new BasicNameValuePair(checkout, data.get(0).get(CT_CHCKTDATE).get(rm)));

            String roomType = "room" + x + "_roomType";
            pairs.add(new BasicNameValuePair(roomType, data.get(0).get(CT_ROOMTYPE).get(rm)));

            String planCode = "room" + x + "_planCode";
            pairs.add(new BasicNameValuePair(planCode, data.get(0).get(CT_PLANCODE).get(rm)));

            String nmbrPpl = "room" + x + "_nmbrPpl";
            pairs.add(new BasicNameValuePair(nmbrPpl, data.get(0).get(CT_NMBRPPL).get(rm)));

            String fmlyName = "room" + x + "_fmlyName";
            pairs.add(new BasicNameValuePair(fmlyName, data.get(0).get(CT_FMLYNAME).get(rm)));

            String frstName = "room" + x + "_frstName";
            pairs.add(new BasicNameValuePair(frstName, data.get(0).get(CT_FRSTNAME).get(rm)));

            String sex = "room" + x + "_sex";
            pairs.add(new BasicNameValuePair(sex, data.get(0).get(CT_SEX).get(rm)));

            String mmbrshpFlag = "room" + x + "_mmbrshpFlag";
            pairs.add(new BasicNameValuePair(mmbrshpFlag, data.get(0).get(CT_MMBRSHPFLAG).get(rm)));

            String mmbrshpNmbr = "room" + x + "_mmbrshpNmbr";
            pairs.add(new BasicNameValuePair(mmbrshpNmbr, data.get(0).get(CT_MMBRSHPNMBR).get(rm)));

            String ntnltyCode = "room" + x + "_ntnltyCode";
            pairs.add(new BasicNameValuePair(ntnltyCode, data.get(0).get(CT_NTNLTYCODE).get(rm)));

            String phnNmbr = "room" + x + "_phnNmbr";
            pairs.add(new BasicNameValuePair(phnNmbr, data.get(0).get(CT_PHNNMBR).get(rm)));

            String ecoFlag = "room" + x + "_ecoFlag";
            pairs.add(new BasicNameValuePair(ecoFlag, data.get(0).get(CT_ECOFLAG).get(rm)));

            getEcoDtsListData(pairs, rm);

            String ecoChckn = "room" + x + "_ecoChckn";
            pairs.add(new BasicNameValuePair(ecoChckn, data.get(0).get(CT_ECOCHCKN).get(rm)));

            String vodFlag = "room" + x + "_vodFlag";
            pairs.add(new BasicNameValuePair(vodFlag, data.get(0).get(CT_VODFLAG).get(rm)));

            String bsnssPackFlag = "room" + x + "_bsnssPackFlag";
            pairs.add(new BasicNameValuePair(bsnssPackFlag, data.get(0).get(CT_BSNSSPACKFLAG).get(rm)));

            String bsnssPackType = "room" + x + "_bsnssPackType";
            pairs.add(new BasicNameValuePair(bsnssPackType, data.get(0).get(CT_BSNSSPACKTYPE).get(rm)));

            String chldrnShrngBed = "room" + x + "_chldrnShrngBed";
            pairs.add(new BasicNameValuePair(chldrnShrngBed, data.get(0).get(CT_CHLDRNSHRNGBED).get(rm)));

/*            String chcknTimedata = "room" + x + "_chcknTime";
            pairs.add(new BasicNameValuePair(chcknTimedata, data.get(0).get("chcknTime").get(rm)));*/

        }
    }

    //A13 予約登録
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA013(int max, ArrayList<Map<String, ArrayList<String>>> data) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        this.setApplctnVrsnNmbr("1");
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        getExtraLoopDataA013(pairs, max, data);
        pairs.add(new BasicNameValuePair(CT_TTLPRC, "5000"));
        pairs.add(new BasicNameValuePair(CT_TTLPRCINCLDNGTAX, "5400"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDNMBR, "123456789012345"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDHLDR, "HOYOKO TESTA"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDEXPRTNDATE, "20151231"));
        return pairs;
    }

    private void getExtraLoopDataA013(ArrayList<NameValuePair> pairs, int max, ArrayList<Map<String, ArrayList<String>>> data) {

        for (int i = 0; i < max; i++) {
            int x = i + 1;

            String checkindate = "room" + x + "_chcknDate";
            pairs.add(new BasicNameValuePair(checkindate, data.get(0).get(CT_CHCKNDATE).get(i)));

            String checkout = "room" + x + "_chcktDate";
            pairs.add(new BasicNameValuePair(checkout, data.get(0).get(CT_CHCKTDATE).get(i)));

            String roomType = "room" + x + "_roomType";
            pairs.add(new BasicNameValuePair(roomType, data.get(0).get(CT_ROOMTYPE).get(i)));

            String planCode = "room" + x + "_planCode";
            pairs.add(new BasicNameValuePair(planCode, data.get(0).get(CT_PLANCODE).get(i)));

            String nmbrPpl = "room" + x + "_nmbrPpl";
            pairs.add(new BasicNameValuePair(nmbrPpl, data.get(0).get(CT_NMBRPPL).get(i)));

            String fmlyName = "room" + x + "_fmlyName";
            pairs.add(new BasicNameValuePair(fmlyName, data.get(0).get(CT_FMLYNAME).get(i)));

            String frstName = "room" + x + "_frstName";
            pairs.add(new BasicNameValuePair(frstName, data.get(0).get(CT_FRSTNAME).get(i)));

            String sex = "room" + x + "_sex";
            pairs.add(new BasicNameValuePair(sex, data.get(0).get(CT_SEX).get(i)));

            String mmbrshpFlag = "room" + x + "_mmbrshpFlag";
            pairs.add(new BasicNameValuePair(mmbrshpFlag, data.get(0).get(CT_MMBRSHPFLAG).get(i)));

            String mmbrshpNmbr = "room" + x + "_mmbrshpNmbr";
            pairs.add(new BasicNameValuePair(mmbrshpNmbr, data.get(0).get(CT_MMBRSHPNMBR).get(i)));

            String ntnltyCode = "room" + x + "_ntnltyCode";
            pairs.add(new BasicNameValuePair(ntnltyCode, data.get(0).get(CT_NTNLTYCODE).get(i)));

            String phnNmbr = "room" + x + "_phnNmbr";
            pairs.add(new BasicNameValuePair(phnNmbr, data.get(0).get(CT_PHNNMBR).get(i)));

            String ecoFlag = "room" + x + "_ecoFlag";
            pairs.add(new BasicNameValuePair(ecoFlag, data.get(0).get(CT_ECOFLAG).get(i)));

            getEcoDtsListData(pairs, i);

            String ecoChckn = "room" + x + "_ecoChckn";
            pairs.add(new BasicNameValuePair(ecoChckn, data.get(0).get(CT_ECOCHCKN).get(i)));

            String vodFlag = "room" + x + "_vodFlag";
            pairs.add(new BasicNameValuePair(vodFlag, data.get(0).get(CT_VODFLAG).get(i)));

            String bsnssPackFlag = "room" + x + "_bsnssPackFlag";
            pairs.add(new BasicNameValuePair(bsnssPackFlag, data.get(0).get(CT_BSNSSPACKFLAG).get(i)));

            String bsnssPackType = "room" + x + "_bsnssPackType";
            pairs.add(new BasicNameValuePair(bsnssPackType, data.get(0).get(CT_BSNSSPACKTYPE).get(i)));

            String chldrnShrngBed = "room" + x + "_chldrnShrngBed";
            pairs.add(new BasicNameValuePair(chldrnShrngBed, data.get(0).get(CT_CHLDRNSHRNGBED).get(i)));

            String chcknTime = "room" + x + "_chcknTime";
            pairs.add(new BasicNameValuePair(chcknTime, data.get(0).get(CT_CHCKNTIME).get(i)));

            String prcList = "room" + x + "_prcList";
            pairs.add(new BasicNameValuePair(prcList, data.get(0).get(CT_PRCLIST).get(i)));

            String prcIncldngTaxList = "room" + x + "_prcIncldngTaxList";
            pairs.add(new BasicNameValuePair(prcIncldngTaxList, data.get(0).get(CT_PRCINCLDNGTAXLIST).get(i)));

            String optnPrc = "room" + x + "_optnPrc";
            pairs.add(new BasicNameValuePair(optnPrc, data.get(0).get(CT_OPTNPRC).get(i)));

            String sbttlPrc = "room" + x + "_sbttlPrc";
            pairs.add(new BasicNameValuePair(sbttlPrc, data.get(0).get(CT_SBTTLPRC).get(i)));

            String sbttlPrcIncldngTax = "room" + x + "_sbttlPrcIncldngTax";
            pairs.add(new BasicNameValuePair(sbttlPrcIncldngTax, data.get(0).get(CT_SBTTLPRCINCLDNGTAX).get(i)));

            String receiptType = "room" + x + "_receiptType";
            pairs.add(new BasicNameValuePair(receiptType, data.get(0).get(CT_RECEIPTTYPE).get(i)));

            String receiptName = "room" + x + "_receiptName";
            pairs.add(new BasicNameValuePair(receiptName, data.get(0).get(CT_RECEIPTNAME).get(i)));

        }
    }

    //A014 予約検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA014() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_PAGENMBR, this.getPageNmbr()));
        return pairs;
    }

    //A015 予約詳細検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA015() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_RSRVID, this.getRsrvId()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_RSRVTNNMBR, this.getRsrvtnNmbr()));
        return pairs;
    }

    //A016 予約キャンセル
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA016() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_NMBRRSRVTNS,this.getNmbrRsrvtns()));
        pairs.add(new BasicNameValuePair("rsrvId[0]", this.getRsrvId()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair("rsrvtnNmbr[0]", this.getRsrvtnNmbr()));
        return pairs;
    }

    //A17 - (予約変更)
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA017() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_NMBRRMS, this.getNmbrRms()));
        pairs.add(new BasicNameValuePair("room1_htlCode", this.getHotelCode()));
        pairs.add(new BasicNameValuePair("room1_rsrvId", this.getRsrvId()));
        pairs.add(new BasicNameValuePair("room1_rsrvtnNmbr", this.getRsrvtnNmbr()));
        pairs.add(new BasicNameValuePair("room1_chcknDate", this.getCheckInDate()));
        pairs.add(new BasicNameValuePair("room1_chcktDate", this.getChcktDate()));
        pairs.add(new BasicNameValuePair("room1_roomType", this.getRoomType()));
        pairs.add(new BasicNameValuePair("room1_planCode", this.getPlanCode()));
        pairs.add(new BasicNameValuePair("room1_nmbrPpl", this.getNmbrPpl()));
        pairs.add(new BasicNameValuePair("room1_fmlyName", this.getFmlyName()));
        pairs.add(new BasicNameValuePair("room1_frstName", this.getFrstName()));
        pairs.add(new BasicNameValuePair("room1_sex", this.getSex()));
        pairs.add(new BasicNameValuePair("room1_mmbrshpFlag", this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair("room1_mmbrshpNmbr", this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair("room1_ntnltyCode", this.getNtnltyCode()));
        pairs.add(new BasicNameValuePair("room1_phnNmbr", this.getPhnNmbr()));
        pairs.add(new BasicNameValuePair("room1_ecoFlag", this.getEcoFlag()));
        getEcoDtsListData(pairs, 0);
        pairs.add(new BasicNameValuePair("room1_ecoChckn", this.getEcoChckn()));
        pairs.add(new BasicNameValuePair("room1_vodFlag", this.getVodFlag()));
        pairs.add(new BasicNameValuePair("room1_bsnssPackFlag", this.getBsnssPackFlag()));
        pairs.add(new BasicNameValuePair("room1_bsnssPackType", this.getBsnssPackType()));
        pairs.add(new BasicNameValuePair("room1_chldrnShrngBed", this.getChldrnShrngBed()));
        pairs.add(new BasicNameValuePair("room1_chcknTime", this.getChcknTime()));
        pairs.add(new BasicNameValuePair("room1_prcList", this.getPrcList()));
        pairs.add(new BasicNameValuePair("room1_prcIncldngTaxList", this.getPrcIncldngTaxList()));
        pairs.add(new BasicNameValuePair("room1_optnPrc", this.getOptnPrc()));
        pairs.add(new BasicNameValuePair("room1_sbttlPrc", this.getSbttlPrc()));
        pairs.add(new BasicNameValuePair("room1_sbttlPrcIncldngTax", this.getSbttlPrcIncldngTax()));
        pairs.add(new BasicNameValuePair("room1_receiptType", this.getReceiptType()));
        pairs.add(new BasicNameValuePair("room1_receiptName", this.getReceiptName()));

        pairs.add(new BasicNameValuePair(CT_TTLPRC, this.getTtlPrc()));
        pairs.add(new BasicNameValuePair(CT_TTLPRCINCLDNGTAX, this.getTtlPrcIncldngTax()));

        pairs.add(new BasicNameValuePair(CT_CRDTCARDNMBR, "123456789012345"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDHLDR, "HOYOKO TESTA"));
        pairs.add(new BasicNameValuePair(CT_CRDTCARDEXPRTNDATE, "20151231"));
        return pairs;
    }

    private void getEcoDtsListData(ArrayList<NameValuePair> pairs, int numberRoom) {
        int room = numberRoom + 1;
        if (room == 1) {
            if (this.getEcoDtsList1().size() != 0) {
                for (int i = 0; i < this.getEcoDtsList1().size(); i++) {
                    String ecoList = "room" + room + "_ecoDtsList[" + i + "]";
                    pairs.add(new BasicNameValuePair(ecoList, getEcoDtsList1().get(i)));
                }
            }else{
                String ecoList = "room" + room + "_ecoDtsList";
                pairs.add(new BasicNameValuePair(ecoList, ""));
            }
        }
        if (room == 2) {
            if (this.getEcoDtsList2().size() != 0) {
                for (int i = 0; i < this.getEcoDtsList2().size(); i++) {
                    String ecoList = "room" + room + "_ecoDtsList[" + i + "]";
                    pairs.add(new BasicNameValuePair(ecoList, getEcoDtsList2().get(i)));
                }
            }else{
                String ecoList = "room" + room + "_ecoDtsList";
                pairs.add(new BasicNameValuePair(ecoList, ""));
            }
        }
        if (room == 3) {
            if (this.getEcoDtsList3().size() != 0) {
                for (int i = 0; i < this.getEcoDtsList3().size(); i++) {
                    String ecoList = "room" + room + "_ecoDtsList[" + i + "]";
                    pairs.add(new BasicNameValuePair(ecoList, getEcoDtsList3().get(i)));
                }
            }else{
                String ecoList = "room" + room + "_ecoDtsList";
                pairs.add(new BasicNameValuePair(ecoList, ""));
            }
        }
        if (room == 4) {
            if (this.getEcoDtsList4().size() != 0) {
                for (int i = 0; i < this.getEcoDtsList4().size(); i++) {
                    String ecoList = "room" + room + "_ecoDtsList[" + i + "]";
                    pairs.add(new BasicNameValuePair(ecoList, getEcoDtsList4().get(i)));
                }
            }else{
                String ecoList = "room" + room + "_ecoDtsList";
                pairs.add(new BasicNameValuePair(ecoList, ""));
            }
        }
    }

    //A018 宿泊履歴検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA018() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_PAGENMBR, this.getPageNmbr()));
        return pairs;
    }

    //A019 宿泊履歴詳細検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA019() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_RSRVID, this.getRsrvId()));
        pairs.add(new BasicNameValuePair(CT_HTLCODE, this.getHotelCode()));
        pairs.add(new BasicNameValuePair(CT_RSRVTNNMBR, this.getRsrvtnNmbr()));
        return pairs;
    }

    //A020 お気に入りホテル検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA020() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_PAGENMBR, this.getPageNmbr()));
        pairs.add(new BasicNameValuePair(CT_FVRTHTLCODE, this.getFvrtHtlCode()));
        return pairs;
    }

    //A021 お気に入りホテル登録
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA021() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_FVRTHTLCODE, this.getFvrtHtlCode()));
        pairs.add(new BasicNameValuePair(CT_DLTFLAG, this.getDltFlag()));
        return pairs;
    }


    //A022 お客様情報検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA022() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        return pairs;
    }

    //A023 お客様情報変更
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA023() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_PRCSSNGTYPE, this.getPrcssngType()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        pairs.add(new BasicNameValuePair(CT_SEX, this.getSex()));
        pairs.add(new BasicNameValuePair(CT_NTNLTYCODE, this.getNtnltyCode()));
        pairs.add(new BasicNameValuePair(CT_PHNNMBR, this.getPhnNmbr()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair(CT_PCEMLADDRSS, this.getPcEmlAddrss()));
        pairs.add(new BasicNameValuePair(CT_NWSLTTR, this.getNwslttr()));
        pairs.add(new BasicNameValuePair(CT_PSSWRD, this.getPsswrd()));
        pairs.add(new BasicNameValuePair(CT_LGNID, this.getLgnId()));
        pairs.add(new BasicNameValuePair(CT_LGNPSSWRD, this.getLgnPsswrd()));

        return pairs;
    }

    //A024 お客様設定情報検索
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA024() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        return pairs;
    }


    //A025 お客様設定情報変更
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA025() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_NEWSPUSHFLAG, this.getNewsPushFlag()));
        pairs.add(new BasicNameValuePair(CT_MYFVRTSPUSHFLAG, this.getMyFvrtsPushFlag()));
        pairs.add(new BasicNameValuePair(CT_NRSTHTLSPUSHFLAG, this.getNrstHtlsPushFlag()));
        pairs.add(new BasicNameValuePair(CT_DSTNC, this.getDstnc()));
        return pairs;
    }


    //A026 会員認証
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA026() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        return pairs;
    }

    //A027 姓名・生年月日認証
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA027() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        pairs.add(new BasicNameValuePair(CT_PHNNMBR, this.getPhnNmbr()));
        return pairs;
    }


    //A029 個人情報登録
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA029() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_PRCSSNGTYPE, this.getPrcssngType()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        pairs.add(new BasicNameValuePair(CT_SEX, this.getSex()));
        pairs.add(new BasicNameValuePair(CT_NTNLTYCODE, this.getNtnltyCode()));
        pairs.add(new BasicNameValuePair(CT_PHNNMBR, this.getPhnNmbr()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair(CT_PCEMLADDRSS, this.getPcEmlAddrss()));
        pairs.add(new BasicNameValuePair(CT_NWSLTTR, this.getNwslttr()));
        pairs.add(new BasicNameValuePair(CT_PSSWRD, this.getPsswrd()));
        return pairs;
    }

    //A030
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA030() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_PAGENMBR, this.getPageNmbr()));
        return pairs;
    }

    //A032
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA032() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_RGSTRTNID, this.getRgstrtnId()));
        pairs.add(new BasicNameValuePair(CT_LGNID, this.getLgnId()));
        pairs.add(new BasicNameValuePair(CT_LGNPSSWRD, this.getLgnPsswrd()));
        return pairs;
    }

    //A033
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA033() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_PRCSSNGTYPE, this.getPrcssngType()));
        pairs.add(new BasicNameValuePair(CT_LGNID, this.getLgnId()));
        pairs.add(new BasicNameValuePair(CT_LGNPSSWRD, this.getLgnPsswrd()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        pairs.add(new BasicNameValuePair(CT_NTNLTYCODE, this.getNtnltyCode()));
        pairs.add(new BasicNameValuePair(CT_SEX, this.getSex()));
        pairs.add(new BasicNameValuePair(CT_PHNNMBR, this.getPhnNmbr()));
        pairs.add(new BasicNameValuePair(CT_EMLADDRSS, this.getEmlAddrss()));
        pairs.add(new BasicNameValuePair(CT_NWSLTTR, this.getNwslttr()));
        pairs.add(new BasicNameValuePair(CT_EMLADDRSS2, this.getEmlAddrss2()));
        return pairs;
    }

    //A034
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA034() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_PRCSSNGTYPE, this.getPrcssngType()));
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_LGNID, this.getLgnId()));
        pairs.add(new BasicNameValuePair(CT_LGNPSSWRD, this.getLgnPsswrd()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPFLAG, this.getMmbrshpFlag()));
        pairs.add(new BasicNameValuePair(CT_MMBRSHPNMBR, this.getMmbrshpNmbr()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        pairs.add(new BasicNameValuePair(CT_NTNLTYCODE, this.getNtnltyCode()));
        pairs.add(new BasicNameValuePair(CT_SEX, this.getSex()));
        pairs.add(new BasicNameValuePair(CT_PHNNMBR, this.getPhnNmbr()));
        pairs.add(new BasicNameValuePair(CT_EMLADDRSS, this.getEmlAddrss()));
        pairs.add(new BasicNameValuePair(CT_NWSLTTR, this.getNwslttr()));
        pairs.add(new BasicNameValuePair(CT_EMLADDRSS2, this.getEmlAddrss2()));
        return pairs;
    }

    //A035
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA035() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_EMLADDRSS, this.getPcEmlAddrss()));
        pairs.add(new BasicNameValuePair(CT_FMLYNAME, this.getFmlyName()));
        pairs.add(new BasicNameValuePair(CT_FRSTNAME, this.getFrstName()));
        pairs.add(new BasicNameValuePair(CT_DATEBIRTH, this.getDateBirth()));
        return pairs;
    }

    //A036
    //----------------------------------------------------------------------------------------------
    public ArrayList<NameValuePair> getRequestDataA036() {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        AddCommonFields(pairs);
        pairs.add(new BasicNameValuePair(CT_ATHNTCTNKEY, this.getAthntctnKey()));
        pairs.add(new BasicNameValuePair(CT_RSRVSPRSNUID, this.getRsrvsPrsnUid()));
        pairs.add(new BasicNameValuePair(CT_LGNID, this.getLgnId()));
        pairs.add(new BasicNameValuePair(CT_LGNPSSWRD, this.getLgnPsswrd()));
        return pairs;
    }
}
