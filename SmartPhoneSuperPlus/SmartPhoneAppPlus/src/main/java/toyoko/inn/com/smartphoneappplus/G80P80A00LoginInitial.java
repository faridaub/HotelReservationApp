package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G80P80A00LoginInitial extends Activity {
    G01P01ParcelableData obj_g01;
    private String ERROR_CODE;
    private String PAGE_FLAG;
    private String LGNID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G80P80A00LoginInitial--------------------------------------");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.g80_p80_a00_login_initial);

        //==Default Configuration
        SetupConfiguration();

        //==>>Get Data From Previous
        GetData();

        //==Login Authontication
        SetupToView();

        //==Go To Customar Information Change
        GoToChangeCustomarInfo();

        //==Go To Applied Before Page
        GoToAppliedBeforePage();

        //==Back To Previous Page
        BackToPreviousPage();

        //==Initial Settings
        GoToInitialSettings();

        //==Go To Registration Page
        GoToNewRegistration();

        //==Back To home Page
        BackToHomePage();

    }

    private void BackToHomePage() {
        Button button = (Button)findViewById(R.id.g80_p80_back_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G01P01A00DashboardActivity.class,ComMsg.COD_BACK);
            }
        });
    }

    private void SetupToView() {

    }

    private void GoToNewRegistration() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g80_p80_new_user);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G60P60A33UserRegisSetp1.class,ComMsg.COD_NEXT);
            }
        });
    }

    private void GoToInitialSettings() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g80_p80_member_card);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G51P51A26HoldingMembershipCard.class, ComMsg.COD_NEXT);
            }
        });
    }


    private void SetupConfiguration() {
        this.ERROR_CODE = new String();
        this.PAGE_FLAG = new String();
        this.LGNID = new String();
    }

    private void GetData() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras().getParcelable(ComMsg.COD_DATA);
            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGE_FLAG = obj_g01.getPageFlag();
            }
        }
    }

    private void GoToAppliedBeforePage() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g80_p80_applied_before);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G58P58A27OftenKnownMembership.class, ComMsg.COD_NEXT);
            }
        });

    }

    private void GoToChangeCustomarInfo() {
        LinearLayout button = (LinearLayout) findViewById(R.id.g80_p80_member_card);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(G00P42A00CustomerInfoChange.class, ComMsg.COD_NEXT);
            }
        });
    }

    private void BackToPreviousPage() {
        Button button = (Button) findViewById(R.id.g80_p80_back_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj_g01.setPageFlag("G50P26");
                if (PAGE_FLAG.equalsIgnoreCase("G13P17")) {
                    finish(ComMsg.COD_BACK);
                } else {
                    finish(ComMsg.COD_BACK);
                }
            }
        });

    }

    private void errorPopup(String eCode, String eMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_json_error);
        TextView la_errorCode = (TextView) dialog.findViewById(R.id.json_error_code);
        la_errorCode.setText(ComMsg.getErrorCode(eCode));
        TextView la_errorMsg = (TextView) dialog.findViewById(R.id.json_error_message);
        la_errorMsg.setText(eMessage);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.show();

        Button no = (Button) dialog.findViewById(R.id.json_submit);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void finish(String forwordState) {
        finish();
        if (forwordState.equalsIgnoreCase(ComMsg.COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void goTo(final Class myClass, String forwordState) {
        Intent intent = new Intent(getApplicationContext(), myClass);
        intent.putExtra(ComMsg.COD_DATA, obj_g01);
        startActivity(intent);
        if (forwordState.equalsIgnoreCase(ComMsg.COD_BACK)) {
            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
        } else if (forwordState.equalsIgnoreCase(ComMsg.COD_NEXT)) {
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        } else {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }



}


