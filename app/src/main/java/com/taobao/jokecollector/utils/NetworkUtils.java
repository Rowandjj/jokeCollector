package com.taobao.jokecollector.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class NetworkUtils
{
    private NetworkUtils(){}

    public static final boolean isConnected(Context ctx)
    {
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info!=null && info.isConnected();
    }

    public static final boolean isWifi(Context ctx)
    {
        if(!isConnected(ctx))
            return false;
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.isConnected();
    }

}
