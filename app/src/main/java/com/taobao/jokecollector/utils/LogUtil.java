package com.taobao.jokecollector.utils;

import android.util.Log;

import com.taobao.jokecollector.BuildConfig;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class LogUtil
{
    private LogUtil(){}

    public static final boolean isDebugMode()
    {
        return BuildConfig.DEBUG;
    }

    public static final void i(String TAG,String msg)
    {
        if(BuildConfig.DEBUG && msg != null)
           Log.i(TAG,msg);
    }

    public static final void i(Object a,String msg)
    {
        if(a == null)
            return;
        i(a.getClass().getSimpleName(),msg);
    }

    public static final void d(String TAG,String msg)
    {
        if(BuildConfig.DEBUG && msg != null)
            Log.d(TAG, msg);
    }

    public static final void d(Object a,String msg)
    {
        if(a == null)
            return;
        d(a.getClass().getSimpleName(),msg);
    }

    public static final void e(String TAG,String msg)
    {
        if(BuildConfig.DEBUG && msg != null)
           Log.e(TAG, msg);
    }

    public static final void e(Object a,String msg)
    {
        if(a == null)
            return;
        e(a.getClass().getSimpleName(),msg);
    }

    public static final void v(String TAG,String msg)
    {
        if(BuildConfig.DEBUG && msg != null)
           Log.v(TAG, msg);
    }

    public static final void v(Object a,String msg)
    {
        if(a == null)
            return;
        v(a.getClass().getSimpleName(),msg);
    }

    public static final void w(String TAG,String msg)
    {
        if(BuildConfig.DEBUG && msg != null)
           Log.w(TAG,msg);
    }

    public static final void w(Object a,String msg)
    {
        if(a == null)
            return;
        w(a.getClass().getSimpleName(),msg);
    }

}
