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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G00P46A00LoginRegistrationAsGeneral extends Activity {
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
    private String PAGEFLAG;


    private RadioButton rd_a1;
    private RadioButton rd_a2;
    private EditText familyName;
    private EditText firstName;
    private EditText mail1;
    private EditText mail2;
    private RadioButton sex_1;
    private RadioButton sex_2;
    private EditText password1;
    private EditText password2;
    private Spinner national;
    private EditText phoneNumber;
    private RadioButton nwslttr_1;
    private RadioButton nwslttr_2;
    private RadioButton passdel_1;
    private RadioButton passdel_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G00P46A00LoginRegistrationAsGeneral------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g00_p46_a00_login_registration_as_general);

        SetupConfiguration();

        G22_GetDataFrom_G02();

        G22_goBack_G02();

        G22_goTo_G23();

        G22_executeJsonData();
    }

    private void G22_executeJsonData() {
        if(!PAGEFLAG.equalsIgnoreCase("G00P26")) {
            UpdateBeforeAction();
            ReloadBeforeAction();
        }
    }

    private void G22_goTo_G23() {
        final Button button = (Button)findViewById(R.id.g22p29_confirm_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBeforeAction();
                ReloadBeforeAction();
                Intent intent = new Intent(getApplicationContext(), G23P30A23CustomerInfoUpdateSetp2.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P46A00LoginRegistrationAsGeneral.this.overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);

            }
        });
    }

    private void UpdateBeforeAction() {

        //Membership Flag
        if(rd_a1.isChecked()){
            MMBRSHPFLAG = "Y";
        }else{
            MMBRSHPFLAG = "N";
        }

        //Family Name
        if(!familyName.getText().toString().isEmpty()){
            FMLYNAME = familyName.getText().toString();
        }

        //First name
        if(!firstName.getText().toString().isEmpty()){
            FRSTNAME = firstName.getText().toString();
        }

        //Phone Number
        if(!phoneNumber.getText().toString().isEmpty()){
            PHNNMBR = phoneNumber.getText().toString();
        }

        //Email 1
        if(!mail1.getText().toString().isEmpty()){
            PCEMLADDRSS1 = mail1.getText().toString();
        }

        if(!mail2.getText().toString().isEmpty()){
            PCEMLADDRSS2 = mail2.getText().toString();
        }

        if(!PCEMLADDRSS1.contentEquals(PCEMLADDRSS2)){
            Log.e("Email Error", "Content Not Matched...Please Try again");
        }


        if(sex_1.isChecked()){
            SEX = "M";
        }else{
            SEX = "F";
        }

        if(!password1.getText().toString().isEmpty()){
            PASSWORD1 = password1.getText().toString();
        }

        if(!password2.getText().toString().isEmpty()){
            PASSWORD2 = password2.getText().toString();
        }

        if(!nwslttr_1.isChecked()){
            NWSLTTR = "Y";
        }else{
            NWSLTTR = "N";
        }

        if(!passdel_1.isChecked()){
            PASSDELFLG = "Y";
        }else{
            PASSDELFLG = "N";
        }

    }


    private void ReloadBeforeAction() {
        if(!MMBRSHPFLAG.isEmpty() && MMBRSHPFLAG.equalsIgnoreCase("Y")){
            rd_a1.setChecked(true);
            rd_a2.setChecked(false);
        }else{
            rd_a1.setChecked(false);
            rd_a2.setChecked(true);
        }

        rd_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_a1.setChecked(true);
                rd_a2.setChecked(false);
            }
        });

        rd_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_a1.setChecked(false);
                rd_a2.setChecked(true);
            }
        });

        if(!FMLYNAME.isEmpty()) {
            familyName.setText(FMLYNAME);
        }

        if(!FRSTNAME.isEmpty()){
            firstName.setText(FRSTNAME);
        }

        if(!PHNNMBR.isEmpty()){
            phoneNumber.setText(PHNNMBR);
        }

        if(!PCEMLADDRSS1.isEmpty()){
            mail1.setText(PCEMLADDRSS1);
            mail2.setText(PCEMLADDRSS1);
        }

        if(!SEX.isEmpty() && SEX.equalsIgnoreCase("M")){
            sex_1.setChecked(true);
            sex_2.setChecked(false);
        }else{
            sex_1.setChecked(false);
            sex_2.setChecked(true);
        }

        sex_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex_1.setChecked(true);
                sex_2.setChecked(false);
            }
        });

        sex_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex_2.setChecked(true);
                sex_1.setChecked(false);
            }
        });


        if(!PHNNMBR.isEmpty()){
            phoneNumber.setText(PHNNMBR);
        }

        if(!PASSWORD1.isEmpty()){
            password1.setText(PASSWORD1);
            password2.setText(PASSWORD1);
        }

        if(!NWSLTTR.isEmpty() && NWSLTTR.equalsIgnoreCase("Y")){
            nwslttr_1.setChecked(true);
            nwslttr_2.setChecked(false);
        }else{
            nwslttr_1.setChecked(false);
            nwslttr_2.setChecked(true);
        }

        nwslttr_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nwslttr_1.setChecked(true);
                nwslttr_2.setChecked(false);
            }
        });

        nwslttr_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nwslttr_1.setChecked(false);
                nwslttr_2.setChecked(true);
            }
        });

        //Password Delete Flag
        if(!PASSDELFLG.isEmpty() && PASSDELFLG.equalsIgnoreCase("Y")){
            passdel_1.setChecked(true);
            passdel_2.setChecked(false);
        }else{
            passdel_1.setChecked(false);
            passdel_2.setChecked(true);
        }

        passdel_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passdel_1.setChecked(true);
                passdel_2.setChecked(false);
            }
        });

        passdel_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passdel_1.setChecked(false);
                passdel_2.setChecked(true);
            }
        });


        //Load To Parceable Data
        obj_g01.setCustDateBirth(DATEBIRTH);
        obj_g01.setCustFmlyName(FMLYNAME);
        obj_g01.setCustFrstName(FRSTNAME);
        obj_g01.setCustMmbrshpFlag(MMBRSHPFLAG);
        obj_g01.setCustNtnltyCode(NTNLTYCODE);
        obj_g01.setCustNwslttr(NWSLTTR);
        obj_g01.setCustPcEmlAddrss(PCEMLADDRSS1);
        obj_g01.setCustPhnNmbr(PHNNMBR);
        obj_g01.setCustPsswrd(PASSWORD1);
        obj_g01.setCustSex(SEX);
        obj_g01.setCustRsrvsPrsnUid(RSRVSPRSNUID);


        Log.e("#V RSRVSPRSNUID",RSRVSPRSNUID);
        Log.e("#V PRCSSNGTYPE",PRCSSNGTYPE);
        Log.e("#V FMLYNAME",FMLYNAME);
        Log.e("#V FRSTNAME",FRSTNAME);
        Log.e("#V SEX",SEX);
        Log.e("#V NTNLTYCODE",NTNLTYCODE);
        Log.e("#V PHNNMBR",PHNNMBR);
        Log.e("#V MMBRSHPFLAG",MMBRSHPFLAG);
        Log.e("#V PCEMLADDRSS1", PCEMLADDRSS1);
        Log.e("#V PASSWORD1",PASSWORD1);
        Log.e("#V PASSWORD2",PASSWORD2);
        Log.e("#V NWSLTTR",NWSLTTR);

    }

    private void G22_GetDataFrom_G02() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            obj_g01 = (G01P01ParcelableData) getIntent().getExtras() .getParcelable("DATA");

            if (!obj_g01.getPageFlag().isEmpty()) {
                PAGEFLAG = obj_g01.getPageFlag();
            }

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
        PAGEFLAG = new String();

        rd_a1 = (RadioButton)findViewById(R.id.g00_p46_membership_flag_1);
        rd_a2 = (RadioButton)findViewById(R.id.g00_p46_membership_flag_2);
        familyName = (EditText)findViewById(R.id.g00_p46_family_name);
        firstName = (EditText)findViewById(R.id.g00_p46_first_name);
        phoneNumber = (EditText)findViewById(R.id.g00_p46_phonenumber);
        mail1 = (EditText)findViewById(R.id.g00_p46_email_1);
        mail2 = (EditText)findViewById(R.id.g00_p46_email_2);
        sex_1 = (RadioButton)findViewById(R.id.g00_p46_sex_1);
        sex_2 = (RadioButton)findViewById(R.id.g00_p46_sex_2);
        password1 = (EditText)findViewById(R.id.g00_p46_pass_1);
        password2 = (EditText)findViewById(R.id.g00_p46_pass_2);
        national = (Spinner)findViewById(R.id.g00_p46_spinner);
        nwslttr_1 = (RadioButton)findViewById(R.id.g00_p46_newsletter_1);
        nwslttr_2 = (RadioButton)findViewById(R.id.g00_p46_newsletter_2);
        passdel_1 = (RadioButton)findViewById(R.id.g00_p46_pass_del_1);
        passdel_2 = (RadioButton)findViewById(R.id.g00_p46_pass_del_2);

    }

    private void G22_goBack_G02() {
        final Button button = (Button)findViewById(R.id.g00_p46_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), G02P20A01AccountInformation.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                G00P46A00LoginRegistrationAsGeneral.this.overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
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
