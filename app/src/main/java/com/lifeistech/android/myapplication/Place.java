package com.lifeistech.android.myapplication;

import java.util.Date;

import io.realm.RealmObject;

public class Place extends RealmObject{
    public Long latitude;
    public Long longitude;

    public String stampName;

    public String date;

    public String placeName;
    public String memo;
}
