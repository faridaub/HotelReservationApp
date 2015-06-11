package toyoko.inn.com.smartphoneappplus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import toyoko.inn.com.smartphoneappplus.common.ComMsg;
import toyoko.inn.com.smartphoneappplus.parcelable.G01P01ParcelableData;


public class G22P29A00PrivacyPolicyWebView extends Activity {

    private WebView webView;
    private G01P01ParcelableData obj_g01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("DATAX PAGE", "------------------------------------G22P29A00PrivacyPolicyWebView------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g22_p29_privicy_policy_webview);

        //==LoadWebView
        setupWebViewImplementation();

        //<==BackTo Previous Page
        goBackToPriveousPage();

    }

    private void goBackToPriveousPage() {
        Button webviw_back = (Button) findViewById(R.id.g22_p29_webviw_back);
        webviw_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(ComMsg.COD_BACK);
            }
        });
    }

    private void setupWebViewImplementation() {
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        webView = (WebView) findViewById(R.id.webView2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(getMapUrl());

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog = new ProgressDialog(G22P29A00PrivacyPolicyWebView.this);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage(ComMsg.MSG_PROCESSING);
                progressDialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });
    }

    private String getMapUrl() {
        String url = "http://www.toyoko-inn.com/yoyaku/praivacy/policy_ja.html";
        return url;
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
}
