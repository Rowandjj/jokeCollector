package com.taobao.jokecollector.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.taobao.jokecollector.R;
import com.taobao.jokecollector.app.BaseFragment;
import com.taobao.jokecollector.model.RequestDataEvent;
import com.taobao.jokecollector.ui.adapter.BounceAnimationAdaptor;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * 段子
 */
public class JokeFragment extends BaseFragment
{
    @InjectView(R.id.waiting_progressbar)
    ProgressWheel mWaitingView;

    @InjectView(R.id.rv_joke_list)
    RecyclerView mJokeList;

    @InjectView(R.id.swipe_refresh_joke)
    SwipeRefreshLayout mRefreshView;

    @InjectView(R.id.view_error_layout)
    ViewStub mViewStub;

    private View mErrorView;

    public JokeFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView =  inflater.inflate(R.layout.fragment_joke, container, false);
        ButterKnife.inject(this, rootView);

        initView();
        initData();

        return rootView;
    }

    private void initView()
    {

        mJokeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mJokeList.setHasFixedSize(false);
        mJokeList.setAdapter(new BounceAnimationAdaptor()
        {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
            {
                return null;
            }

            @Override
            public int getItemCount()
            {
                return 0;
            }
        });
//        mRefreshView.setColorSchemeColors();
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {

            }
        });
    }

    private void initData()
    {
        //TODO 请求网络 成功：hide waitingview，显示数据 失败：hide waitingview，显示errorlayout
//        request();

        mWaitingView.setVisibility(View.GONE);
        showErrorView();

    }

    private void showErrorView()
    {
        if(mErrorView == null)
        {
            mErrorView = mViewStub.inflate();
            mErrorView.setVisibility(View.VISIBLE);
        }else
        {
            mErrorView.setVisibility(View.VISIBLE);
        }
    }

    private void hideErrorView()
    {
        if(mErrorView != null)
            mErrorView.setVisibility(View.GONE);
    }


    public void onEventMainThread(RequestDataEvent event)
    {
//        if()//如果数据没有加载成功
//        {
//            //TODO 加载数据
//        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
