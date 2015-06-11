package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import toyoko.inn.com.smartphoneappplus.R;

public class G04_HistoryAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> keywordName;
    private ArrayList<String> numberHotel;

    public G04_HistoryAdapter(Context context, ArrayList<String> keywordName,ArrayList<String> numberHotel) {
        this.context = context;
        this.keywordName = keywordName;
        this.numberHotel = numberHotel;
    }

    @Override
    public int getCount() {
        return keywordName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Object getData(int position) {
        return keywordName.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.g04_item, parent, false);
        TextView hotel_name = (TextView) itemView.findViewById(R.id.g04_p02_maintext);
        hotel_name.setText(keywordName.get(position));
        TextView numHotel = (TextView)itemView.findViewById(R.id.la_p02_num);

        if(Integer.valueOf(numberHotel.get(position))<1){
            numHotel.setBackgroundResource(R.drawable.util_gra_grey_rectangle);
        }else{
            numHotel.setBackgroundResource(R.drawable.util_gra_yellow_rectangle);
        }
        numHotel.setText(numberHotel.get(position) + "軒");
        return itemView;
    }
}