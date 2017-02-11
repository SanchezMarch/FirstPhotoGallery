package com.sanchez.firstphotogallery.features.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.features.authorization.AuthActivity;
import com.sanchez.firstphotogallery.features.prefs.Preferences;
import com.sanchez.firstphotogallery.features.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_AUTHORIZATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.with(this).getAccessToken() == null
                || Preferences.with(this).getUser() == 0) {
            AuthActivity.openForResult(this, REQUEST_CODE_AUTHORIZATION);
        }

        if(getBackStackCount() == 0){
            replaceFragment(ProfileFragment.newInstance());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_AUTHORIZATION:
                if (resultCode != RESULT_OK)
                    finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getBackStackCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    private int getBackStackCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }
}
