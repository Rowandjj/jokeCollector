package com.taobao.jokecollector.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BounceAnimationAdaptor<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>  {
    private int mSwingDistance = 300;
    private int mSwingItemCount = -1;
    private int mDuration = 500;
    public void setSwingDistance(int distance){
        mSwingDistance = distance;
    }
    public void setSwingDuration(int duration){
        mDuration = duration;
    }
    @Override
    public void onBindViewHolder(T holder, int position) {
        if(position > mSwingItemCount){
            swingView(holder.itemView);
            mSwingItemCount = position;
        }
    }
    private void swingView(View view){
        view.setAlpha(0f);
        view.setTranslationY(mSwingDistance);
        view.animate().alpha(1.0f).translationY(0f).setDuration(mDuration).start();
    }
}