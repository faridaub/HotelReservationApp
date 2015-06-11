package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import toyoko.inn.com.smartphoneappplus.R;
import toyoko.inn.com.smartphoneappplus.common.ComConstant;
import toyoko.inn.com.smartphoneappplus.common.ImageLoader;

import static toyoko.inn.com.smartphoneappplus.common.ComConstant.*;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_ONE;
import static toyoko.inn.com.smartphoneappplus.common.ComInitData.ST_TWO;

public class G11_ListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public G11_ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
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

        LinearLayout distanceLocation;
        TextView hotel_name;
        TextView memberPrice;
        TextView listPrice;
        TextView listDiscPrice;
        TextView memberDiscPrice;
        ImageView hotel_image;
        TextView remainRooms;
        TextView distanceFromCurrent;


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.g11_item, parent, false);
        resultp = data.get(position);

        distanceLocation = (LinearLayout) itemView.findViewById(R.id.g11_access_layout);


        hotel_name = (TextView) itemView.findViewById(R.id.g11_hotel_name);

        memberPrice = (TextView) itemView.findViewById(R.id.g11_mmbrPrc);
        memberDiscPrice = (TextView) itemView.findViewById(R.id.g11_mmbrOffclWebDscntPrc);
        listPrice = (TextView) itemView.findViewById(R.id.g11_listPrc);
        listDiscPrice = (TextView) itemView.findViewById(R.id.g11_offclWebDscntPrc);

        remainRooms =(TextView)itemView.findViewById(R.id.g11p04_nmbr_of_remans_room);
        distanceFromCurrent =(TextView)itemView.findViewById(R.id.g11p04_current_distance);

        // Locate the ImageView in listview_item.xml
        hotel_image = (ImageView) itemView.findViewById(R.id.g11_hotel_image);

        StringBuilder sb = new StringBuilder();
        if(Integer.valueOf(resultp.get(CT_NMBRRRMS))<11) {
            sb.append("残り");
            sb.append(resultp.get(CT_NMBRRRMS));
            sb.append("室");
        }else{
            sb.append("空室あり");
        }


        if (resultp.get("MOOD").equalsIgnoreCase(ST_ONE) || resultp.get("MOOD").equalsIgnoreCase(ST_TWO)) {
            distanceLocation.setVisibility(View.GONE);
        } else if (resultp.get("MOOD").equalsIgnoreCase("3")) {
            distanceLocation.setVisibility(View.VISIBLE);
        } else {
            distanceLocation.setVisibility(View.VISIBLE);
        }

        StringBuilder sb_dis = new StringBuilder();
        sb_dis.append("現在地から ");
        sb_dis.append(resultp.get(CT_DSTNCCRRNTPSTN));


        // Capture position and set results to the TextViews
        hotel_name.setText(resultp.get(CT_HTLNAME));
        memberPrice.setText(resultp.get(CT_MMBRPRC));
        memberDiscPrice.setText(resultp.get(CT_MMBROFFCLWEBDSCNTPRC));
        listPrice.setText(resultp.get(CT_LISTPRC));
        listDiscPrice.setText(resultp.get(CT_OFFCLWEBDSCNTPRC));
        remainRooms.setText(sb.toString());
        distanceFromCurrent.setText(sb_dis.toString());
        imageLoader.DisplayImage(resultp.get(CT_IMGURL), 0, hotel_image);
        return itemView;
    }
}