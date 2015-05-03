package com.taobao.jokecollector.utils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.taobao.jokecollector.app.AppEnv;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class VolleyHelper
{
    private static VolleyHelper instance = null;
    private static final RequestQueue queue = Volley.newRequestQueue(AppEnv.getAppContext());
    private VolleyHelper(){}

    public static final VolleyHelper getInstance()
    {
        if(instance == null)
        {
            synchronized (VolleyHelper.class)
            {
                if(instance == null)
                {
                    instance = new VolleyHelper();
                }
            }
        }
        return instance;
    }

    public RequestQueue requestQueue()
    {
        return queue;
    }

    public void request(Request<?> request,Object tag)
    {
        if(request == null || tag == null)
            return;
        request.setTag(tag);
        queue.add(request);
    }

    public void cancel(Object tag)
    {
        queue.cancelAll(tag);
    }

    public void stop()
    {
        queue.stop();
    }

}


















