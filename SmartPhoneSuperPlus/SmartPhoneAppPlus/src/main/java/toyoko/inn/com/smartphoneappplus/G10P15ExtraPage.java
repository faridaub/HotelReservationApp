package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import toyoko.inn.com.smartphoneappplus.common.ComLib;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

import static toyoko.inn.com.smartphoneappplus.common.ComMsg.*;
import static toyoko.inn.com.smartphoneappplus.common.ComMsg.TAG_G10P15_ADDRESS;

public class G10P15ExtraPage extends Activity {
    G01P01ParcelableData obj_g01;
    private String SUB_PAGE_FLAG;
    private ArrayList<String> EQUIPMENT_LIST;
    private ArrayList<String> ACCESS_LIST;
    private TextView la_extra_data;
    private TextView la_title;
    private String EXTRA_DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G10P15ExtraPage------------------------------------");
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
        this.SUB_PAGE_FLAG = new String();
        this.EXTRA_DATA = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable("DATA");

            if (!obj_g01.getSubPageFlag().isEmpty()) {
                SUB_PAGE_FLAG = obj_g01.getSubPageFlag();
            }

            if (!obj_g01.getExtraPageData().isEmpty()) {
                EXTRA_DATA = obj_g01.getExtraPageData();
            }

        }
    }

    private void SetupToView() {
        if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_ADDRESS)) {
            la_title.setText(TTL_ADDRESS);
        } else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_ACCESS)) {
            la_title.setText(TTL_G10P15_ACCESS);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_PARK)) {
            la_title.setText(TTL_G10P15_PARK);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_BUS_PARK)) {
            la_title.setText(TTL_G10P15_BUS_PARK);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_PICKUP)) {
            la_title.setText(TTL_G10P15_PICKUP);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_BUS_RENTAL)) {
            la_title.setText(TTL_G10P15_BUS_RENTAL);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_CHECKIN)) {
            la_title.setText(TTL_G10P15_CHECKIN);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_CHECKOUT)) {
            la_title.setText(TTL_G10P15_CHECKOUT);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_BREAKFAST)) {
            la_title.setText(TTL_G10P15_BREAKFAST);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_EQUIPMENT)) {
            la_title.setText(TTL_G10P15_EQUIPMENT);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_BARRIER_FEE)) {
            la_title.setText(TTL_G10P15_BARRIER_FEE);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_IOS)) {
            la_title.setText(TTL_G10P15_IOS);
        }else if (SUB_PAGE_FLAG.equalsIgnoreCase(TAG_G10P15_PHONE_NUM)) {
            la_title.setText(TTL_G10P15_PHONE_NUM);
        }
        la_extra_data.setText(EXTRA_DATA);
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
