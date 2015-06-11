package toyoko.inn.com.smartphoneappplus.adapter;

/**
 * Created by Farid on 2014/11/21.
 */

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Farid on 2014/11/12.
 */

public class G10_JSON_Adapter extends BaseAdapter {

    private Activity context;
    private ArrayList<HashMap<String, String>> dataList;
    private List<String> countryListData;

    public G10_JSON_Adapter(Activity context,List<String> countryListParam,ArrayList<HashMap<String, String>> dataListParam) {
        this.context = context;
        this.countryListData = countryListParam;
        this.dataList = dataListParam;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public Object getData(int position, String key) {
        return dataList.get(position).get(key);
    }
}