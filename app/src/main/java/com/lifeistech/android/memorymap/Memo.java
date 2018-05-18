package com.lifeistech.android.memorymap;

import android.util.Log;

import io.realm.RealmObject;

public class Memo extends RealmObject{

    public String place;
    public String content;
    public String updateDate;
    public String stickername;
    public double longitude;
    public double latitude;




private void check(String place, String content, String updateDate){
    Memo memo = new Memo();

    memo.place=place;
    memo.content=content;
    memo.updateDate=updateDate;
    memo.stickername=stickername;
    memo.longitude=longitude;
    memo.latitude=latitude;

    Log.d("Memo", memo.place);
    Log.d("Memo", memo.content);
    Log.d("Memo",memo.updateDate);
    Log.d("Memo",memo.stickername);
    Log.d("Memo",String.valueOf(memo.longitude));
    Log.d("Memo",String.valueOf(memo.latitude));
}
}

