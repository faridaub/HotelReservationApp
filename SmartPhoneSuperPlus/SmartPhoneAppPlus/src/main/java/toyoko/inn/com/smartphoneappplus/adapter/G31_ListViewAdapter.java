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

public class G31_ListViewAdapter extends BaseAdapter {

    // Declare Variables
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public G31_ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
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

        // Declare Variables
        TextView hotel_name;
        TextView mmbr_price;
        TextView list_price;
        TextView offclWebDscntPrc;
        TextView mmbrOffclWebDscntPrc;
        ImageView hotel_image;
        TextView numberOfRemainsRoom;
        TextView distanceFromCurrent;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.g31_p14_item, parent, false);

        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        hotel_name = (TextView) itemView.findViewById(R.id.g11_hotel_name);
        mmbr_price = (TextView) itemView.findViewById(R.id.g11_mmbrPrc);
        list_price = (TextView) itemView.findViewById(R.id.g11_listPrc);
        offclWebDscntPrc = (TextView) itemView.findViewById(R.id.g11_offclWebDscntPrc);
        mmbrOffclWebDscntPrc = (TextView) itemView.findViewById(R.id.g11_mmbrOffclWebDscntPrc);
        numberOfRemainsRoom =(TextView)itemView.findViewById(R.id.g11p04_nmbr_of_remans_room);
        distanceFromCurrent =(TextView)itemView.findViewById(R.id.g11p04_current_distance);

        // Locate the ImageView in listview_item.xml
        hotel_image = (ImageView) itemView.findViewById(R.id.g11_hotel_image);

        StringBuilder sb = new StringBuilder();
        sb.append("残り");
        sb.append(resultp.get(ComConstant.CT_NMBRRRMS));
        sb.append("室");


        StringBuilder sb_dis = new StringBuilder();
        sb_dis.append("現在地から ");
        sb_dis.append(resultp.get(ComConstant.CT_DSTNCCRRNTPSTN));


        // Capture position and set results to the TextViews
        hotel_name.setText(resultp.get(ComConstant.CT_HTLNAME));
        mmbr_price.setText(resultp.get(ComConstant.CT_MMBRPRC));
        list_price.setText(resultp.get(ComConstant.CT_LISTPRC));
        offclWebDscntPrc.setText(resultp.get(ComConstant.CT_OFFCLWEBDSCNTPRC));
        mmbrOffclWebDscntPrc.setText(resultp.get(ComConstant.CT_MMBROFFCLWEBDSCNTPRC));
        numberOfRemainsRoom.setText(sb.toString());
        distanceFromCurrent.setText(sb_dis.toString());

        // Capture position and set results to the ImageView
        // Passes hotel_image images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(ComConstant.CT_IMGURL), 0, hotel_image);
        // Capture ListView item click


        return itemView;
    }
}