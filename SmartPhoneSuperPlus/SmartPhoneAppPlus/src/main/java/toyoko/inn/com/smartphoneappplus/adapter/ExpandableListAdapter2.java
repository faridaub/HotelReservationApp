package toyoko.inn.com.smartphoneappplus.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
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

public class ExpandableListAdapter2 extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private HashMap<String, String> resultp = new HashMap<String, String>();

    public ExpandableListAdapter2(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Declaration Class
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.g11_item, parent, false);
        return itemView;
    }
}