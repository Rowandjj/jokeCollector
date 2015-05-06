package com.taobao.jokecollector.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.taobao.jokecollector.ui.fragment.DrawerFragment;
import com.taobao.jokecollector.ui.fragment.JokeFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


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

    @Override
    protected void initData()
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
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.syncState();
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
