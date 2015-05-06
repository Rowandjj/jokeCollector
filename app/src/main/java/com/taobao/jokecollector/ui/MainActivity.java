package com.taobao.jokecollector.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.jokecollector.R;
import com.taobao.jokecollector.app.BaseActivity;
import com.taobao.jokecollector.model.NetworkEvent;
import com.taobao.jokecollector.model.RequestDataEvent;
import com.taobao.jokecollector.ui.fragment.DrawerFragment;
import com.taobao.jokecollector.ui.fragment.JokeFragment;
import com.taobao.jokecollector.utils.ActivityManager;
import com.taobao.jokecollector.utils.NetworkUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;


public final class MainActivity extends BaseActivity
{
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.container)
    ViewGroup mContainer;

    @InjectView(R.id.menu_container)
    ViewGroup mMenuContainer;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerFragment mDrawerFragment;
    private JokeFragment mJokeFragment;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initView()
    {
        ButterKnife.inject(this);
        setSupportActionBar(mToolBar);
        setStatusBarColor(mToolBar);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,mToolBar,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                mToolBar.setTitle(R.string.menu);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                mToolBar.setTitle(R.string.app_name);
            }
        };

        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void inflateFrament()
    {
        FragmentManager manager = getSupportFragmentManager();
        mDrawerFragment = (DrawerFragment) manager.findFragmentById(R.id.menu_container);
        if(mDrawerFragment == null)
        {
            mDrawerFragment = new DrawerFragment();
            manager.beginTransaction().replace(R.id.menu_container,mDrawerFragment).commit();
        }

        mJokeFragment = (JokeFragment) manager.findFragmentById(R.id.container);
        if(mJokeFragment == null)
        {
            mJokeFragment = new JokeFragment();
            manager.beginTransaction().replace(R.id.container,mJokeFragment).commit();
        }
    }


    @Override
    protected void initData()
    {
        inflateFrament();
        /*监听网络状态变化*/
        mNetworkReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
               /* 这里之所以使用Eventbus，是因为broadcastReceiver的回调函数执行时间不能过长，否则会出现anr*/
                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION))
                {
                    if(NetworkUtils.isConnected(context))
                        EventBus.getDefault().post(new NetworkEvent(NetworkEvent.TYPE_AVAILABLE));
                    else
                        EventBus.getDefault().post(new NetworkEvent(NetworkEvent.TYPE_UNAVAILABLE));
                }
            }
        };
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void onEventMainThread(NetworkEvent event)
    {
        if(event != null && event.type == NetworkEvent.TYPE_UNAVAILABLE)
        {/*网络不可用*/
            dialog("提示", "是否要开启网络？", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            }, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                }
            });
        }else
        {
            //TODO 通知fragment加载数据
            EventBus.getDefault().post(new RequestDataEvent());
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(mNetworkReceiver);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.syncState();
    }

    private long exitTime;
    @Override
    public void onBackPressed()
    {
        long curTime = System.currentTimeMillis();
        if(curTime - exitTime > 1500)
        {
            exitTime = curTime;
            toast("再按一次退粗应用:)");
        }else
        {
            ActivityManager.getInstance().clearAndExit();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
