package com.lifeistech.android.myapplication;

import android.app.Application;

import io.realm.Realm;

public class MemorymapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
    }
}
