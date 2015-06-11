package toyoko.inn.com.smartphoneappplus.adapter;

/**
 * Created by Farid on 2014/11/12.
 */

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import toyoko.inn.com.smartphoneappplus.R;

public class G05_ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> prefectureCollections;
    private List<String> countryListData;
    private List<String> countryCodeListData;
    private List<String> areaListData;
    private List<String> areaCodeListData;
    private List<String> stateListData;
    private List<String> stateCodeListData;
    private List<String> numHoelListData;

    public G05_ExpandableListAdapter(Activity context,List<String> countryListPram,List<String> countryCodeListPram, List<String> areaListPram, List<String> areaCodeListPram, List<String> stateListPram,List<String> stateCodeListPram, List<String> hotelnumListPram, Map<String, List<String>> fulldataCollections) {
        this.context = context;
        this.countryListData = countryListPram;
        this.countryCodeListData=countryCodeListPram;
        this.areaListData = areaListPram;
        this.areaCodeListData =areaCodeListPram;
        this.stateListData = stateListPram;
        this.stateCodeListData = stateCodeListPram;
        this.numHoelListData =hotelnumListPram;
        this.prefectureCollections = fulldataCollections;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return prefectureCollections.get(areaListData.get(groupPosition)).get(childPosition);
    }
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String prefecture = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            //  convertView = inflater.inflate(R.layout.child_item, null);
            convertView = inflater.inflate(R.layout.g05_child,null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.g05_child_area_name);
        item.setTextColor(Color.BLACK);

        ImageView delete = (ImageView) convertView.findViewById(R.id.g05_child_right_arrow);
        delete.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                        prefectureCollections.get(areaListData.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        item.setText(prefecture);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return prefectureCollections.get(areaListData.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return areaListData.get(groupPosition);
    }

    public Object getAreaCode(int groupPosition) {
        return areaCodeListData.get(groupPosition);
    }

    public Object getGroupHotelNum(int groupPosition) {
        return numHoelListData.get(groupPosition);
    }

    public Object getCountry(int groupPosition) {
        return countryListData.get(groupPosition);
    }
    public Object getCountryCode(int groupPosition) {
        return countryCodeListData.get(groupPosition);
    }

    public Object getState(int groupPosition) {
        return stateListData.get(groupPosition);
    }

    public Object getStateCode(int groupPosition) {
        return stateCodeListData.get(groupPosition);
    }

    public int getGroupCount() {
        return areaListData.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //Group Method
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        String areaName = (String) getGroup(groupPosition);
        String numHotel = (String) getGroupHotelNum(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.g05_group,
                    null);
        }
        //Number Of Group
        TextView groupName = (TextView) convertView.findViewById(R.id.g05_group_area_name);
        groupName.setTypeface(null, Typeface.BOLD);
        groupName.setTextColor(Color.BLACK);
        groupName.setText(areaName);

        //Number of Hotel
        TextView nmbrHotel = (TextView)convertView.findViewById(R.id.g05_group_numberofhotel);
        nmbrHotel.setTypeface(null, Typeface.BOLD);
        nmbrHotel.setTextColor(Color.RED);
        nmbrHotel.setText(numHotel);

        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}