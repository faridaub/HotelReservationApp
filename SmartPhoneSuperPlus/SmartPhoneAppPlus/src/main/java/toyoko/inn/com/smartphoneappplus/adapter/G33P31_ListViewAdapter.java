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

public class G33P31_ListViewAdapter extends BaseAdapter {

    // Declare Variables
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public G33P31_ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
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
        View itemView = inflater.inflate(R.layout.g33_p31_item, parent, false);
        resultp = data.get(position);

        //Hotel Name
        TextView  hotel_name = (TextView) itemView.findViewById(R.id.g33_p31_hotel_name);
        hotel_name.setText(resultp.get(ComConstant.CT_HTLNAME));

        //Member Price
        TextView  member_room_price = (TextView) itemView.findViewById(R.id.g33_p31_mmbrPrc);
        StringBuilder sb1 = new StringBuilder();
        sb1.append(resultp.get(ComConstant.CT_MMBRSNGLRMPRC));
        sb1.append("(税込");
        sb1.append(resultp.get(ComConstant.CT_MMBRSNGLRMPRCINCLDNGTAX));
        sb1.append(")");
        member_room_price.setText(sb1.toString());

        //Normal Price
        TextView  normal_price = (TextView) itemView.findViewById(R.id.g33_p31_listPrc);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(resultp.get(ComConstant.CT_SNGLRMPRC));
        sb2.append("(税込");
        sb2.append(resultp.get(ComConstant.CT_SNGLRMPRCINCLDNGTAX));
        sb2.append(")");
        normal_price.setText(sb2.toString());

        //Image
        ImageView  hotel_image = (ImageView) itemView.findViewById(R.id.g33_p31_hotel_image);
        imageLoader.DisplayImage(resultp.get(ComConstant.CT_IMGURL), 0, hotel_image);

        return itemView;
    }
}