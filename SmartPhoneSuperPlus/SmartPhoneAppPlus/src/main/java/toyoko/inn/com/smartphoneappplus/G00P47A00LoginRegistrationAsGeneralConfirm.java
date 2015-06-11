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
import android.widget.TextView;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G00P47A00LoginRegistrationAsGeneralConfirm extends Activity {
    G01P01ParcelableData obj_g01;
    private String RSRVSPRSNUID;
    private String PRCSSNGTYPE;
    private String FMLYNAME;
    private String FRSTNAME;
    private String DATEBIRTH;
    private String SEX;
    private String NTNLTYCODE;
    private String PHNNMBR;
    private String MMBRSHPFLAG;
    private String PCEMLADDRSS1;
    private String PCEMLADDRSS2;
    private String PASSWORD1;
    private String PASSWORD2;
    private String PASSDELFLG;
    private String NWSLTTR;

    private TextView la_family_name;
    private TextView la_frist_name;
    private TextView la_sex;
    private TextView la_birth_date;
    private TextView la_email1;
    private TextView la_email2;
    private TextView la_natilnality;
    private TextView la_password1;
    private TextView la_password2;
    private TextView la_phone_number;
    private TextView la_membership;
    private TextView la_newsletter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G00P47A00LoginRegistrationAsGeneralConfirm------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g00_p47_a00_login_registration_as_general_confirm);

        //Setup Configuration
        SetupConfiguration();

        //Set Parceable Data
        G47_GetDataFrom_G46();

        // Back To Account Page
        G47_backTo_G46();

        // Go To Next Page
        G47_goTo_G48();

        ReloadBeforeAction();



    }


    private void G47_backTo_G46() {
        final Button button = (Button)findViewById(R.id.g00_p47_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G00P46A00LoginRegistrationAsGeneral.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P47A00LoginRegistrationAsGeneralConfirm.this.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
            }
        });
    }

    private void G47_goTo_G48() {
        final Button button = (Button)findViewById(R.id.g00_p47_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G00P48A00LoginRegistrationAsGeneralFinish.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P47A00LoginRegistrationAsGeneralConfirm.this.overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
            }
        });


    }

    private void ReloadBeforeAction() {
        la_membership.setText("東横INN一般");
        if(MMBRSHPFLAG.equalsIgnoreCase("Y")){
            la_membership.setText("東横INNクラブカード会員");
        }

        la_birth_date.setText(DATEBIRTH);
        la_email1.setText(PCEMLADDRSS1);
        la_sex.setText(SEX);
        la_family_name.setText(FMLYNAME);
        la_frist_name.setText(FRSTNAME);
        la_natilnality.setText(NTNLTYCODE);

        if(NWSLTTR.equalsIgnoreCase("Y")){
            la_newsletter.setText("はい");
        }else{
            la_newsletter.setText("いいえ");
        }

        la_password1.setText("ｾｷｭﾘﾃｨの観点から表示しません");
        la_password2.setText("ｾｷｭﾘﾃｨの観点から表示しません");

        la_phone_number.setText(PHNNMBR);
    }

    private void G47_GetDataFrom_G46() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");

            if (!obj_g01.getCustFmlyName().isEmpty()) {
                FMLYNAME = obj_g01.getCustFmlyName();
            }
            if (!obj_g01.getCustRsrvsPrsnUid().isEmpty()) {
                RSRVSPRSNUID = obj_g01.getCustRsrvsPrsnUid();
            }
            if (!obj_g01.getCustFrstName().isEmpty()) {
                FRSTNAME = obj_g01.getCustFrstName();
            }

            if (!obj_g01.getCustSex().isEmpty()) {
                SEX = obj_g01.getCustSex();
            }

            if (!obj_g01.getCustDateBirth().isEmpty()) {
                DATEBIRTH = obj_g01.getCustDateBirth();
            }

            if (!obj_g01.getCustMmbrshpFlag().isEmpty()) {
                MMBRSHPFLAG = obj_g01.getCustMmbrshpFlag();
            }

            if (!obj_g01.getCustPcEmlAddrss().isEmpty()) {
                PCEMLADDRSS1 = obj_g01.getCustPcEmlAddrss();
            }

            if (!obj_g01.getCustNwslttr().isEmpty()) {
                NWSLTTR = obj_g01.getCustNwslttr();
            }

            if (!obj_g01.getCustPhnNmbr().isEmpty()) {
                PHNNMBR = obj_g01.getCustPhnNmbr();
            }

            if (!obj_g01.getCustPsswrd().isEmpty()) {
                PASSWORD1 = obj_g01.getCustPsswrd();
            }

        }

    }

    private void SetupConfiguration() {
        RSRVSPRSNUID = new String();
        PRCSSNGTYPE = new String();
        FMLYNAME = new String();
        FRSTNAME = new String();
        DATEBIRTH = new String();
        SEX = new String();
        NTNLTYCODE = new String();
        PHNNMBR = new String();
        MMBRSHPFLAG = new String();
        PCEMLADDRSS1 = new String();
        PCEMLADDRSS2 = new String();
        NWSLTTR = new String();
        PASSWORD1 = new String();
        PASSWORD2 = new String();
        PASSDELFLG = new String();

        la_membership = (TextView)findViewById(R.id.g00_p47_membership_flag);
        la_family_name = (TextView)findViewById(R.id.g00_p47_family_name);
        la_frist_name = (TextView)findViewById(R.id.g00_p47_first_name);
        la_sex = (TextView)findViewById(R.id.g00_p47_sex);
        la_birth_date = (TextView)findViewById(R.id.g00_p47_birth_date);
        la_email1 = (TextView)findViewById(R.id.g00_p47_email_address_1);
        la_email2 = (TextView)findViewById(R.id.g00_p47_email_address_2);
        la_natilnality = (TextView)findViewById(R.id.g00_p47_natiality);
        la_password1 = (TextView)findViewById(R.id.g00_p47_password_1);
        la_password2 = (TextView)findViewById(R.id.g00_p47_password_2);
        la_phone_number = (TextView)findViewById(R.id.g00_p47_phoneNumber);
        la_newsletter = (TextView)findViewById(R.id.g00_p47_newsletter);

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
