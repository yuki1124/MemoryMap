package com.lifeistech.android.memorymap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SingleRecyclerAdapter extends RecyclerView.Adapter<SingleViewHolder>{
    private final Context mContext;
    private List<Sticker> mItemList = new ArrayList<>();
    private OnClickStickerListener mListener;

    public SingleRecyclerAdapter(final Context context,final List itemList, OnClickStickerListener listener){
        mContext=context;
        mItemList=itemList;
        mListener = listener;
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SingleViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder holder, final int position) {
        holder.mImageView.setImageResource(mItemList.get(position).id);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(mItemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    interface OnClickStickerListener{
        void onClick(Sticker sticker);
    }
}
