package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.R;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;

public class G16P21_ListViewAdapter2 extends BaseAdapter {

    // Declare Variables
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public G16P21_ListViewAdapter2(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Object getData(int position, String key) {
        return data.get(position).get(key);
    }

    //Declaration Class
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.g16_p21_item, parent, false);
        resultp = data.get(position);

        //Hotel Name
        TextView  hotelName = (TextView) itemView.findViewById(R.id.g16_p21_hotel_name);
        hotelName.setText(resultp.get(ComConstant.CT_HTLNAME));

        //Reservation ID
        TextView  reservationID = (TextView) itemView.findViewById(R.id.g16_p21_reservation_num);
        StringBuilder sb_1 = new StringBuilder();
        sb_1.append("予約番号：");
        sb_1.append(resultp.get(ComConstant.CT_RSRVTNNMBR));
        reservationID.setText(sb_1.toString());

        //Customer Name
        TextView  custname = (TextView) itemView.findViewById(R.id.g16_p21_name);
        StringBuilder sb_2 = new StringBuilder();
        sb_2.append(resultp.get(ComConstant.CT_FMLYNAME));
        sb_2.append(" ");
        sb_2.append(resultp.get(ComConstant.CT_FRSTNAME));
        sb_2.append("様");
        custname.setText(sb_2.toString());

        //Checking Date
        TextView  checkindate = (TextView) itemView.findViewById(R.id.g16_p21_acco_date);
        StringBuilder acdate = new StringBuilder();
        acdate.append(ComLib.dateConvertFormattedDate(resultp.get(ComConstant.CT_CHCKNDATE)));
        acdate.append("～");
        acdate.append(resultp.get(ComConstant.CT_NMBRNGHTS));
        acdate.append("拍");
        checkindate.setText(acdate.toString());

        //Checking Date
        TextView  room_name = (TextView) itemView.findViewById(R.id.g16_p21_room_name);
        room_name.setText(resultp.get(ComConstant.CT_ROOMNAME));

        //Total Price
        TextView  total_price = (TextView) itemView.findViewById(R.id.g16_p21_total_price);
        StringBuilder sb_total_price = new StringBuilder();
        sb_total_price.append(" ");
        sb_total_price.append(resultp.get(ComConstant.CT_PYMNTPRC));
        sb_total_price.append("(税込");
        sb_total_price.append(resultp.get(ComConstant.CT_PYMNTPRCINCLDNGTAX));
        sb_total_price.append(")");
        total_price.setText(sb_total_price.toString());


        //Image Url
        ImageView hotel_image = (ImageView) itemView.findViewById(R.id.g16_p21_hotel_image);

        imageLoader.DisplayImage(resultp.get(ComConstant.CT_IMGURL), R.drawable.no_image, hotel_image);

        return itemView;
    }
}