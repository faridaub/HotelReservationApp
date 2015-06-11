package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;

public class G62P62A00UserRegisSetp3 extends Activity {
    private G01P01ParcelableData obj_g01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G62P62A00UserRegisSetp3------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g63_p63_user_registration_ur_3);
        backToLogin();
    }

    private void backToLogin() {
        Button button = (Button)findViewById(R.id.g62_p62_back_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = intent = new Intent(getApplicationContext(), G50P26A32Login.class);
                intent.putExtra("DATA", obj_g01);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });
    }
}
