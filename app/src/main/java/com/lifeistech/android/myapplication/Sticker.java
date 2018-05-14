package com.lifeistech.android.myapplication;

import android.support.annotation.DrawableRes;

public enum  Sticker {
    HEART(R.drawable.heart),
    DROP(R.drawable.drop),
    FOOD(R.drawable.delicious),
    LUCKY(R.drawable.music),
    SURPRISE(R.drawable.surprise);

    int id;

    Sticker(@DrawableRes int id){
        this.id = id;
    }
}
