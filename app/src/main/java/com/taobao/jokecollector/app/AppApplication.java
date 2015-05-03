package com.taobao.jokecollector.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class AppApplication extends Application
{
    private static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static final Context getAppContext()
    {
        return mContext;
    }

}
