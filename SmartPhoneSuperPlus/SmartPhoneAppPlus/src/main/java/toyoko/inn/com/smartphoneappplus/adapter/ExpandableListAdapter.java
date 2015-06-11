package toyoko.inn.com.smartphoneappplus.adapter;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.R;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _parentGroupName;
    private List<String> _parentNumHotel;
    private HashMap<String, List<String>> _childStateName;
    private HashMap<String, List<String>> _childNumHotel;

    private HashMap<String, List<String>> _childCountryCode;
    private HashMap<String, List<String>> _childAreaCode;
    private HashMap<String, List<String>> _childStateCode;

    //private HashMap<String,String> _cityCode;
    //private HashMap<String,String> _cityName;

    public ExpandableListAdapter(Context context, List<String> parentGroupName,List<String> parentNumHotel,
                                 HashMap<String, List<String>> childStateName,HashMap<String, List<String>> childNumHotel,HashMap<String, List<String>> childCountryCode,HashMap<String, List<String>> childAreaCode,HashMap<String, List<String>> childStateCode) {
        this._context = context;
        this._parentGroupName = parentGroupName;
        this._parentNumHotel = parentNumHotel;

        this._childStateName = childStateName;
        this._childNumHotel = childNumHotel;
        this._childCountryCode = childCountryCode;
        this._childAreaCode = childAreaCode;
        this._childStateCode = childStateCode;

        //  this._cityName = childCityName;
        //  this._cityCode = childCityCode;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._childStateName.get(this._parentGroupName.get(groupPosition))
                .get(childPosititon);
    }

    public Object getChildHtlNum(int groupPosition, int childPosititon) {
        return this._childNumHotel.get(this._parentGroupName.get(groupPosition))
                .get(childPosititon);
    }

    public Object getChildCountryCode(int groupPosition, int childPosititon) {
        return this._childCountryCode.get(this._parentGroupName.get(groupPosition))
                .get(childPosititon);
    }

    public Object getChildAreaCode(int groupPosition, int childPosititon) {
        return this._childAreaCode.get(this._parentGroupName.get(groupPosition))
                .get(childPosititon);
    }

    public Object getChildStateCode(int groupPosition, int childPosititon) {
        return this._childStateCode.get(this._parentGroupName.get(groupPosition))
                .get(childPosititon);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childTitle = (String) getChild(groupPosition, childPosition);
        final String childNumHotel = (String) getChildHtlNum(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.g05_p03_expendablelist_state_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childTitle);

        TextView totalNumhtl = (TextView) convertView
                .findViewById(R.id.totalHotelcount);
        totalNumhtl.setText(childNumHotel);

        if(isLastChild){
            Log.e("Last","this is last data...");

        }else{
            //convertView = infalInflater.inflate(R.layout.g05_p03_expendablelist_state_item, null);
            Log.e("Last","no this is last data...");
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._childStateName.get(this._parentGroupName.get(groupPosition))
                .size();
    }

    public int getChildrenHotelCount(int groupPosition) {
        return this._childNumHotel.get(this._parentGroupName.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._parentGroupName.get(groupPosition);
    }


    public Object getNumHotel(int groupPosition) {
        return this._parentNumHotel.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._parentGroupName.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        String groupHtlNum = (String) getNumHotel(groupPosition);



        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.g05_p03_expendablelist_group, null);
        }

        TextView area_name_top = (TextView) convertView
                .findViewById(R.id.g05_area_name_top);
        area_name_top.setTypeface(null, Typeface.BOLD);
        area_name_top.setText(groupTitle);

        TextView area_name_main = (TextView) convertView
                .findViewById(R.id.g05_area_name_main);
        area_name_main.setTypeface(null, Typeface.BOLD);
        area_name_main.setText(groupTitle);

        TextView hotelNumber = (TextView) convertView
                .findViewById(R.id.hotel_number);

        hotelNumber.setTypeface(null, Typeface.BOLD);
        if(Integer.valueOf(groupHtlNum)<1){
            hotelNumber.setBackgroundResource(R.drawable.util_gra_grey_rectangle);
        }else{
            hotelNumber.setBackgroundResource(R.drawable.util_gra_yellow_rectangle);
        }
        StringBuilder sbn = new StringBuilder();
        sbn.append(groupHtlNum);
        sbn.append(SW_KEN);
        hotelNumber.setText(sbn.toString());

        ImageView arrow = (ImageView) convertView
                .findViewById(R.id.g05_arrow);



        if(isExpanded){
            arrow.setBackgroundResource(R.drawable.ic_arrow_down);
        }else{
            arrow.setBackgroundResource(R.drawable.ic_arrow_next);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
