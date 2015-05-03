package com.taobao.jokecollector.app;

import android.app.Application;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class AppApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        AppEnv.init(getApplicationContext());
    }


}
