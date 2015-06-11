package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import toyoko.inn.com.smartphoneappplus.adapter.ExpandableListAdapter;


public class wwwwMainActivity extends Activity {
    ExpandableListView explvlist;
    ArrayList<String> firstLevel = new ArrayList<String>();
    ArrayList<String> secondLevel = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wwwwactivity_main);


        firstLevel.add("-->1One");
        firstLevel.add("-->2One");
        firstLevel.add("-->3One");
        firstLevel.add("-->4One");
        firstLevel.add("-->5One");
        firstLevel.add("-->6One");
        firstLevel.add("-->7One");

        secondLevel.add("-->1Second");
        secondLevel.add("-->2Second");
        secondLevel.add("-->3Second");
        secondLevel.add("-->4Second");
        secondLevel.add("-->5Second");
        secondLevel.add("-->6Second");
        secondLevel.add("-->7Second");


        explvlist = (ExpandableListView) findViewById(R.id.expandableListView);
        ParentLevel adapter = new ParentLevel(this, firstLevel ,secondLevel);
        explvlist.setAdapter(adapter);

        explvlist.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < firstLevel.size(); i++) {
                    if (i != groupPosition) {
                        explvlist.collapseGroup(i);
                    }
                }
            }
        });
    }

    public class ParentLevel extends BaseExpandableListAdapter {
        private Context _context;
        private List<String> _levelOne;
        private List<String> _levelTwo;
        public ParentLevel(Context context, List<String> levelOne, List<String> levelTwo) {
            this._context = context;
            this._levelOne = levelOne;
            this._levelTwo = levelTwo;
        }

        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview SecondLevelexplv = new CustExpListview(wwwwMainActivity.this);
            SecondLevelexplv.setAdapter(new SecondLevelAdapter());
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return this._levelTwo.size();
        }
        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }
        @Override
        public int getGroupCount() {
            return this._levelOne.size();
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView tv = new TextView(wwwwMainActivity.this);
            tv.setText(_levelOne.get(groupPosition));
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setBackgroundColor(Color.BLUE);
            tv.setPadding(10, 7, 7, 7);
            return tv;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class CustExpListview extends ExpandableListView {
        int intGroupPosition, intChildPosition, intGroupid;
        public CustExpListview(wwwwMainActivity context) {
            super(context);
        }
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    public class CustExpListview2 extends ExpandableListView {
        int intGroupPosition2, intChildPosition2, intGroupid2;
        public CustExpListview2(Context context) {
            super(context);
        }
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    public class CustExpListview3 extends ExpandableListView {
        int intGroupPosition2, intChildPosition2, intGroupid2;
        public CustExpListview3(Context context) {
            super(context);
        }
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview2 SecondLevelexplv2 = new CustExpListview2(wwwwMainActivity.this);
            SecondLevelexplv2.setAdapter(new ThreeLevelAdapter());
            SecondLevelexplv2.setGroupIndicator(null);
            return SecondLevelexplv2;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }
        @Override
        public int getGroupCount() {
            return 1;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView tv = new TextView(wwwwMainActivity.this);
            tv.setText("-->Second Level");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setPadding(12, 7, 7, 7);
            tv.setBackgroundColor(Color.RED);
            return tv;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class ThreeLevelAdapter extends BaseExpandableListAdapter {
        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            CustExpListview3 SecondLevelexplv3 = new CustExpListview3(wwwwMainActivity.this);
            SecondLevelexplv3.setAdapter(new FourLevelAdapter());
            SecondLevelexplv3.setGroupIndicator(null);
            return SecondLevelexplv3;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return 3;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }
        @Override
        public int getGroupCount() {
            return 1;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView tv = new TextView(wwwwMainActivity.this);
            tv.setText("Level --Three");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setPadding(15, 5, 5, 5);
            tv.setBackgroundColor(Color.YELLOW);
            return tv;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class FourLevelAdapter extends BaseExpandableListAdapter {
        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView tv = new TextView(wwwwMainActivity.this);
            tv.setText("->five level");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setBackgroundColor(Color.MAGENTA);
            tv.setPadding(24, 5, 5, 5);
            return tv;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return 3;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }
        @Override
        public int getGroupCount() {
            return 1;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView tv = new TextView(wwwwMainActivity.this);
            tv.setText("->four level");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);
            tv.setBackgroundColor(Color.CYAN);
            tv.setPadding(21, 5, 5, 5);
            return tv;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
