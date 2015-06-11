package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComPolicy.*;

public class G13P17ListDetailsViews extends Activity {
    private G01P01ParcelableData obj_g01;
    private TextView la_extra_data;
    private TextView la_title;

    private String CANCEL_POLICY;
    private String SUB_PAGE_FLAG;
    private ArrayList<String> EQUIPMENT_LIST;
    private ArrayList<String> ACCESS_LIST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G13P17ListDetailsViews------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g13_p17_list_details);

        //==Constant Setup
        InitialSetupConfiguration();

        //== Get Data From G11P04
        GetData();

        //==Reload Data To View
        SetupToView();

        //<== Back To Details Page
        BackToPage();

    }

    private void InitialSetupConfiguration() {
        this.la_title = (TextView) findViewById(R.id.g10_p15_toptitle);
        this.la_extra_data = (TextView) findViewById(R.id.g10_p15_extra_data);
        this.EQUIPMENT_LIST = new ArrayList<String>();
        this.ACCESS_LIST = new ArrayList<String>();
        this.CANCEL_POLICY = new String();
        this.SUB_PAGE_FLAG = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(COD_DATA);

            if (!obj_g01.getPageFlag().isEmpty()) {
                SUB_PAGE_FLAG = obj_g01.getSubPageFlag();
            }

            if (!obj_g01.getLsEquipmentData().isEmpty()) {
                EQUIPMENT_LIST = obj_g01.getLsEquipmentData();
            }

            if (!obj_g01.getLsAccessData().isEmpty()) {
                ACCESS_LIST = obj_g01.getLsAccessData();
            }

            if (!obj_g01.getCancelPolicy().isEmpty()) {
                CANCEL_POLICY = obj_g01.getCancelPolicy();
            }

        }
    }

    private void SetupToView() {
        if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G13P17EQP)) {
            la_title.setText(TTL_G10P15_EQUIPMENT);
            la_extra_data.setText(ComLib.concatListData(EQUIPMENT_LIST));
        }

        else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P1511)) {
            la_title.setText(TTL_G10P15_EQUIPMENT);
            la_extra_data.setText(ComLib.concatListData(EQUIPMENT_LIST));
        }
        // Cancel Polity
        else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G13P17CP)) {
            la_title.setText(TTL_G10P15_CANCEL_POLICY);
            la_extra_data.setText(TERMS_CANCEL_POLICY);
        }
    }

    private void BackToPage() {
        Button extra_back = (Button) findViewById(R.id.g10_p15_extra_back);
        extra_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(COD_BACK);
            }
        });
    }

    private void finish(String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase(COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }


}
