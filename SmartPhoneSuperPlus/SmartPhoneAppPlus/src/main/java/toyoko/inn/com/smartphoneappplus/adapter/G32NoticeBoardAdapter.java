package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import toyoko.inn.com.smartphoneappplus.R;

public class G32NoticeBoardAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList< String> dateArray;

    public G32NoticeBoardAdapter(Context context, ArrayList<String> dateArray) {
        this.context = context;
        this.dateArray = dateArray;
    }

    @Override
    public int getCount() {
        return dateArray.size();
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
        return dateArray.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.g32_p14_item, parent, false);
        TextView hotel_name;
        hotel_name = (TextView) itemView.findViewById(R.id.g32_p14_date);
        hotel_name.setText(dateArray.get(position));
        return itemView;
    }
}