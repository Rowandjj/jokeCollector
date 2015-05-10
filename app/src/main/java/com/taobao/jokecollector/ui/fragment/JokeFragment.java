package com.taobao.jokecollector.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.taobao.jokecollector.R;
import com.taobao.jokecollector.app.BaseFragment;
import com.taobao.jokecollector.component.JokeRequest;
import com.taobao.jokecollector.model.Joke;
import com.taobao.jokecollector.model.RequestDataEvent;
import com.taobao.jokecollector.ui.adapter.JokeAdapter;
import com.taobao.jokecollector.ui.view.EndlessRecyclerView;

import java.util.List;

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
    EndlessRecyclerView mJokeList;

    @InjectView(R.id.swipe_refresh_joke)
    SwipeRefreshLayout mRefreshView;

    @InjectView(R.id.view_error_layout)
    ViewStub mViewStub;

    private View mErrorView;
    private JokeAdapter mJokeAdapter;

    private int curPage = 1;

    public JokeFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        ButterKnife.inject(this, rootView);

        initView();
        initData();

        return rootView;
    }

    private void initView()
    {
        mJokeList.setHasFixedSize(false);
        mJokeAdapter = new JokeAdapter();
        mJokeList.setAdapter(mJokeAdapter);
        mJokeList.setOnLoadMoreListener(new EndlessRecyclerView.onLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                curPage++;
                requestData();
            }
        });
        mRefreshView.setColorSchemeResources(R.color.pink_primary, R.color.purple_primary, R.color.red_primary);
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                curPage = 1;
                mJokeAdapter.removeAll();
                requestData();
            }
        });
    }

    private void initData()
    {
        //TODO 请求网络 成功：hide waitingview，显示数据 失败：hide waitingview，显示errorlayout

        //TODO 先从缓存里面获取

        requestData();

//        mWaitingView.setVisibility(View.GONE);
//        showErrorView();

    }

    private void requestData()
    {
        request(new JokeRequest(Joke.getRequestUrl(curPage), new Response.Listener<List<Joke>>()
        {
            @Override
            public void onResponse(List<Joke> response)
            {
                mWaitingView.setVisibility(View.GONE);
                mJokeAdapter.addJokes(response);
                if(mRefreshView.isRefreshing())
                    mRefreshView.setRefreshing(false);
                mJokeList.loadFinish();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                mWaitingView.setVisibility(View.GONE);
                toast(error.toString());

                if(mRefreshView.isRefreshing())
                    mRefreshView.setRefreshing(false);
                mJokeList.loadFinish();
            }
        }));
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
        {
            mErrorView.setVisibility(View.GONE);
        }
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
