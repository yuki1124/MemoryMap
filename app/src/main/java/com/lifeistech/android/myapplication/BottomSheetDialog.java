package com.lifeistech.android.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    RecyclerView mRecyclerView;
    private OnStickerClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.simple_list_item, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setAdapter(new SingleRecyclerAdapter(getContext(), Arrays.asList(Sticker.values()), new SingleRecyclerAdapter.OnClickStickerListener() {
            @Override
            public void onClick(Sticker sticker) {
                if(mListener != null){
                    mListener.onClick(sticker);
                }
            }
        }));

        return v;
    }

    public void setOnStickerClickListener(OnStickerClickListener listener){
        mListener = listener;
    }

    interface OnStickerClickListener {
        void onClick(Sticker sticker);
    }
}
