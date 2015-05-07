package com.taobao.jokecollector.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.taobao.jokecollector.utils.LogUtil;

/**
 * Created by Rowandjj on 2015/5/7.
 */
public class EndlessRecyclerView extends RecyclerView
{
    private LinearLayoutManager mLayoutManager;
    private onLoadMoreListener mListener;
    public EndlessRecyclerView(Context context)
    {
        this(context,null);
    }

    public EndlessRecyclerView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public EndlessRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        mLayoutManager = new LinearLayoutManager(context);
        setLayoutManager(mLayoutManager);
        addOnScrollListener(new OnScrollListener());
    }


    class OnScrollListener extends RecyclerView.OnScrollListener
    {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            int totalItemCount = mLayoutManager.getItemCount();

            LogUtil.d("TAG","las:"+lastVisibleItem+","+totalItemCount);
            if(mListener != null && lastVisibleItem >= totalItemCount-4 && dy > 0)
            {
                mListener.onLoadMore();
            }
        }
    }

    public void setOnLoadMoreListener(onLoadMoreListener listener)
    {
        this.mListener = listener;
    }

    public interface onLoadMoreListener
    {
        void onLoadMore();
    }
}























