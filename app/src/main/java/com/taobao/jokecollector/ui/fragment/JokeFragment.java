package com.taobao.jokecollector.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.taobao.jokecollector.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 */
public class JokeFragment extends Fragment
{
    @InjectView(R.id.waiting_progressbar)
    ProgressWheel mWaitingView;

    @InjectView(R.id.rv_joke_list)
    RecyclerView mJokeList;

    @InjectView(R.id.swipe_refresh_joke)
    SwipeRefreshLayout mRefreshView;


    public JokeFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView =  inflater.inflate(R.layout.fragment_joke, container, false);
        ButterKnife.inject(this,rootView);


        return rootView;
    }


}
