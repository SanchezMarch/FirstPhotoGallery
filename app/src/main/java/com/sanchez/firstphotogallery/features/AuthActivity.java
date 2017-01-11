package com.sanchez.firstphotogallery.features;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.features.prefs.Preferences;

/**
 * Created by Олександр on 19.12.2016.
 */

public class AuthActivity extends AppCompatActivity {

    public static void openForResult(Activity activity, int requestCode){
        activity.startActivityForResult(
                new Intent(activity, AuthActivity.class),requestCode);
    }

    private static final String VK_FIELD_ACCESS_TOKEN = "access_token";
    private static final String VK_FIELD_USER_ID = "user_id";

    private int client_id = 5754864;
    private String AUTHORIZATION_URL = "https://oauth.vk.com/authorize?client_id="+client_id
            +"&display=page&redirect_uri=https://oauth.vk.com/blank.html"
            +"&scope="+"friends,photos,status"
            +"&response_type=token&v=5.60&state=123456";

    private String accessToken;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        WebView authWebView = (WebView) findViewById(R.id.authWebView);

        authWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (checkIsAuthDone(url)) {
                    setResult(RESULT_OK, getAuthIntent());
                    Preferences.with(AuthActivity.this).setAccessToken(accessToken);
                    Preferences.with(AuthActivity.this).setUser(userId);
                    finish();
                }
            }
        });

        authWebView.loadUrl(AUTHORIZATION_URL);
    }

    public boolean checkIsAuthDone (String url) {
        String[] response_array = url.split("[=#&]");
        if (response_array.length > 6)
        {
            if (response_array[1].equals(VK_FIELD_ACCESS_TOKEN)
                    && response_array[5].equals(VK_FIELD_USER_ID)) {
                this.accessToken = response_array[2];
                this.userId = Integer.parseInt(response_array[6]);
                return true;
            }
        }
        return false;
    }

    public Intent getAuthIntent() {
        Intent data = new Intent();
        data.putExtra(VK_FIELD_ACCESS_TOKEN, this.accessToken);
        data.putExtra(VK_FIELD_USER_ID, this.userId);
        return data;
    }
}
