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
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;

public class G19P27_ListViewAdapter extends BaseAdapter {

    // Declare Variables
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public G19P27_ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
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
        View itemView = inflater.inflate(R.layout.g19_p27_item, parent, false);
        resultp = data.get(position);

        //Hotel Name
        TextView  hotelName = (TextView) itemView.findViewById(R.id.g19_p27_hotel_name);
        hotelName.setText(resultp.get(ComConstant.CT_HTLNAME));

        //Reservation ID
        TextView  reservationID = (TextView) itemView.findViewById(R.id.g19_p27_reservation_num);
        StringBuilder sb_1 = new StringBuilder();
        sb_1.append("予約番号：");
        sb_1.append(resultp.get(ComConstant.CT_RSRVID));
        reservationID.setText(sb_1.toString());

        //Customer Name
        TextView  custname = (TextView) itemView.findViewById(R.id.g19_p27_customer_name);
        StringBuilder sb_2 = new StringBuilder();
        sb_2.append(resultp.get(ComConstant.CT_FMLYNAME));
        sb_2.append(" ");
        sb_2.append(resultp.get(ComConstant.CT_FRSTNAME));
        sb_2.append("様");
        custname.setText(sb_2.toString());

        //Checking Date
        TextView  checkindate = (TextView) itemView.findViewById(R.id.g19_p27_checkindate);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(resultp.get(ComConstant.CT_CHCKNDATE));
        sb3.append("～");
        sb3.append(resultp.get(ComConstant.CT_NMBRNGHTS));
        sb3.append("拍");
        checkindate.setText(sb3.toString());



        //Image Url
        ImageView hotel_image = (ImageView) itemView.findViewById(R.id.g19_p27_hotel_image);
        imageLoader.DisplayImage(resultp.get(ComConstant.CT_IMGURL), 0, hotel_image);



        return itemView;
    }
}