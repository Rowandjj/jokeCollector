package com.taobao.jokecollector.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class SharedPrefUtils
{
    private SharedPrefUtils()
    {
    }

    public static SharedPreferences getSharedPrefByName(Context ctx, String name)
    {
        return ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    public static String getStringByNameAndKey(Context ctx, String name, String key)
    {
        SharedPreferences pref = getSharedPrefByName(ctx, name);
        return pref.getString(key, null);
    }

    public static void cacheStringbyNameAndKey(Context ctx,String name,String key,String val)
    {
        SharedPreferences pref = getSharedPrefByName(ctx,name);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,val);
        editor.commit();
    }

    public static void clearSharedPrefCacheByName(Context ctx,String name)
    {
        SharedPreferences pref = getSharedPrefByName(ctx,name);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
