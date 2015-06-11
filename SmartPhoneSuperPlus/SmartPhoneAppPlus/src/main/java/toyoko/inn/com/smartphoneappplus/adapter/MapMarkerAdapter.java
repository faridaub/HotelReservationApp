package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import toyoko.inn.com.smartphoneappplus.common.ImageLoader;

public class MapMarkerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String mHotelName;
    private String mHotelCode;
    private String mHotelImage;
    private Double mLatitude;
    private Double mLongitude;

    private String memberPrice;
    private String memberDiscPrice;
    private String listPrice;
    private String listDiscPrice;


    private String mRematinRoom;
    private String mDistance;

    private ImageLoader imageLoader;


    public MapMarkerAdapter(String hotelName,String hotelCode, String hotelIcon, Double latitude, Double longitude, String memberPrice , String memberDiscPrice , String listPrice , String listDiscPrice , String remainRoom, String distance) {
        this.mHotelName = hotelName;
        this.mHotelCode = hotelCode;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mHotelImage = hotelIcon;

        this.memberPrice = memberPrice;
        this.memberDiscPrice = memberDiscPrice;
        this.listPrice = listPrice;
        this.listDiscPrice = listDiscPrice;

        this.imageLoader = new ImageLoader(context);
        this.mRematinRoom = remainRoom;
        this.mDistance = distance;
    }

    public String getmDistance() {
        return mDistance;
    }

    public void setmDistance(String mDistance) {
        this.mDistance = mDistance;
    }

    public String getmRematinRoom() {
        return mRematinRoom;
    }

    public void setmRematinRoom(String mRematinRoom) {
        this.mRematinRoom = mRematinRoom;
    }

    public String getmHotelName() {
        return mHotelName;
    }

    public void setmHotelName(String mHotelName) {
        this.mHotelName = mHotelName;
    }

    public String getmHotelCode() {
        return mHotelCode;
    }

    public void setmHotelCode(String mHotelCode) {
        this.mHotelCode = mHotelCode;
    }

    public String getmHotelImage() {
        return mHotelImage;
    }

    public void setmHotelImage(String icon) {
        this.mHotelImage = icon;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getMemberDiscPrice() {
        return memberDiscPrice;
    }

    public void setMemberDiscPrice(String memberDiscPrice) {
        this.memberDiscPrice = memberDiscPrice;
    }

    public String getListDiscPrice() {
        return listDiscPrice;
    }

    public void setListDiscPrice(String listDiscPrice) {
        this.listDiscPrice = listDiscPrice;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }
}
