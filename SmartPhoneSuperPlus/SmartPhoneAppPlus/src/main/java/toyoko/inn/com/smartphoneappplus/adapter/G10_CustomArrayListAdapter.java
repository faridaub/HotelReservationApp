package toyoko.inn.com.smartphoneappplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import toyoko.inn.com.smartphoneappplus.R;

/**
 * Created by Farid on 2014/11/07.
 */
public class G10_CustomArrayListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] data;

    public G10_CustomArrayListAdapter(Context context, String[] values) {
        super(context, R.layout.g10_item, values);
        this.context = context;
        this.data = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.g10_item, parent, false);
      //  TextView textView = (TextView) rowView.findViewById(R.id.txt_g10_textview);
       // textView.setText(data[position]);
        return rowView;
    }
}
