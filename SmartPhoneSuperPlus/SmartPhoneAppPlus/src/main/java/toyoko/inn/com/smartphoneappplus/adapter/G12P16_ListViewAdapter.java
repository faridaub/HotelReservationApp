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


public class G12P16_ListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;
    private ImageLoader imageLoader;
    private HashMap<String, String> hashmapResult = new HashMap<String, String>();

    public G12P16_ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
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
        View itemView = inflater.inflate(R.layout.g12_p16_item, parent, false);
        // Get the position
        hashmapResult = data.get(position);

        ImageView la_imageIcon = (ImageView)itemView.findViewById(R.id.g12_p16_smoking_icon);
        TextView la_roomName = (TextView) itemView.findViewById(R.id.g12_p16_room_name);
        TextView la_memberPrice = (TextView)itemView.findViewById(R.id.g12_p16_member_price);
        TextView la_listPrice = (TextView)itemView.findViewById(R.id.g12_p16_listPrc);
        TextView la_nmbrRrms = (TextView)itemView.findViewById(R.id.g12_p16_nmbr_of_remans_room);
        TextView la_numMaxPerson = (TextView)itemView.findViewById(R.id.g12_p16_num_max_person);
        ImageView la_hotelImage = (ImageView) itemView.findViewById(R.id.g12_p16_hotel_image);


       // Get Smoking Flag
        String smkFlg= hashmapResult.get(ComConstant.CT_SMKNGFLAG);
        if(!smkFlg.isEmpty()){
            la_imageIcon.setImageResource(R.drawable.ic_smoking_no);
            if(smkFlg.equalsIgnoreCase("Y")){
               la_imageIcon.setImageResource(R.drawable.ic_smoking_yes);
            }
        }

        //Get Max People Icon
        String maxPPL= hashmapResult.get(ComConstant.CT_MAXPPL);
        if(!maxPPL.isEmpty()){
            la_numMaxPerson.setBackgroundResource(R.drawable.ic_person_icon_02);
            if(maxPPL.equalsIgnoreCase("1")){
                la_numMaxPerson.setBackgroundResource(R.drawable.ic_person_icon_01);
            }
        }

        //String For Number of Remains Rooms
        String numberRoom = hashmapResult.get(ComConstant.CT_NMBRRRMS);

        StringBuilder sb = new StringBuilder();
        if(Integer.valueOf(numberRoom)<11) {
            sb.append("残り");
            sb.append(numberRoom);
            sb.append("室");
        }else{
            sb.append("空室あり");
        }

        la_memberPrice.setText(hashmapResult.get(ComConstant.CT_MMBRPRC));
        la_listPrice.setText(hashmapResult.get(ComConstant.CT_LISTPRC));
        la_roomName.setText(hashmapResult.get(ComConstant.CT_ROOMNAME));
        la_nmbrRrms.setText(sb.toString());
        imageLoader.DisplayImage(hashmapResult.get(ComConstant.CT_IMGURL), 0, la_hotelImage);

        return itemView;
    }
}