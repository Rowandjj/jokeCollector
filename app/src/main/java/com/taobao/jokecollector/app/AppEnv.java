package com.taobao.jokecollector.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class AppEnv
{
    private static Context mAppContext;
    private static String mVersionName;
    private static int mVersionCode;
    private static String mPackName;

    private static boolean isInit = false;

    public static final void init(Context context)
    {
        AppEnv.mAppContext = context;
        initVersion();
        isInit = true;
    }

    public static final boolean isInit()
    {
        return isInit;
    }

    private static void initVersion()
    {
        if(mVersionName == null)
        {
            PackageManager manager = mAppContext.getPackageManager();
            try
            {
                PackageInfo info = manager.getPackageInfo(mAppContext.getPackageName(), 0);
                mVersionName = info.versionName;
                mVersionCode = info.versionCode;
            }catch (Exception e)
            {
              e.printStackTrace();
            }
        }
    }

    public static String getAppVersionName()
    {
        return mVersionName;
    }

    public static int getAppVersionCode()
    {
        return mVersionCode;
    }

    public static Context getAppContext()
    {
        if(!isInit)
            throw new RuntimeException("app env not init..");
        return mAppContext;
    }

    public static String getPackName()
    {
        mPackName = mAppContext.getPackageName();
        return mPackName;
    }
}
