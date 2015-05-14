package com.taobao.jokecollector.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class ActivityManager
{
    private static final int DEFAULT_SIZE = 10;
    private static List<Activity> stack = null;
    private static final Object lock = new Object();
    private static ActivityManager instance;
    private ActivityManager(){}

    public static final ActivityManager getInstance()
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public void add(Activity a)
    {
        if(stack == null)
        {
            stack = new ArrayList<>(DEFAULT_SIZE);
        }
        stack.add(a);
        LogUtil.d(this,"add activity:"+a);
    }

    public void remove(Activity a)
    {
        if(stack == null || a == null)
            return;
        stack.remove(a);
        LogUtil.d(this,"remove activity from stack:"+a);
    }

    public void finishActivity(Activity a)
    {
        if(a == null)
            return;
        remove(a);
        a.finish();
        LogUtil.d(this,"finish activity:"+a);
    }

    public void clearAndExit()
    {
        if (null != stack)
        {
            for (int i = 0, size = stack.size(); i < size; i++)
            {
                if (null != stack.get(i))
                {
                    stack.get(i).finish();
                }
            }
            stack.clear();
        }
    }


}
