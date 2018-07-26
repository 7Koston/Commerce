package com.scand.commerce;

import android.support.multidex.MultiDexApplication;

import com.scand.commerce.api.RetrofitClient;
import com.squareup.picasso.Picasso;

public class App extends MultiDexApplication {

    private static App singletonApp;

    public static App getInstance() {
        return singletonApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.initialize(getApplicationContext());
        Picasso.get().setLoggingEnabled(true);
        singletonApp = this;
    }
}
