package com.example.gus.places.view;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


public class LeakApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
