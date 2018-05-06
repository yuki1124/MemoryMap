package com.lifeistech.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class SingleViewHolder extends RecyclerView.ViewHolder {
    final ImageView mImageView;
    private Context mContext;

    public SingleViewHolder(View v, Context context) {
        super(v);

        mContext=context;
        mImageView=(ImageView)v.findViewById(R.id.imageView);
    }
}

