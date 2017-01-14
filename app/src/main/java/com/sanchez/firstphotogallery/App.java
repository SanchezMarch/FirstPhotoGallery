package com.sanchez.firstphotogallery;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

/**
 * Created by Олександр on 16.12.2016.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        Realm.init(this);
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
