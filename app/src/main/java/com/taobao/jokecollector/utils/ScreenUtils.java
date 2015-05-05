package com.taobao.jokecollector.utils;

import android.content.Context;

public class ScreenUtils
{
    private ScreenUtils(){}
    public static int getStatusBarHeight(Context context)
    {
        Context appContext = context.getApplicationContext();
        int result = 0;
        int resourceId = appContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = appContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}