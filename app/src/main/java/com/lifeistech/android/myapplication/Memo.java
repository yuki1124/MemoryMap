package com.lifeistech.android.myapplication;

import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;

public class Memo extends RealmObject{

    public String title;
    public String updateDate;
    public String content;




private void check(String title, String updateDate, String content){
    Memo memo = new Memo();

    memo.title=title;
    memo.updateDate=updateDate;
    memo.content=content;

    Log.d("Memo", memo.title);
    Log.d("Memo", memo.updateDate);
    Log.d("Memo", memo.content);
}
}

