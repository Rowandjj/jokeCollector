package com.taobao.jokecollector.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.taobao.jokecollector.app.AppEnv;
import com.taobao.jokecollector.model.Joke;
import com.taobao.jokecollector.utils.LogUtil;
import com.taobao.jokecollector.utils.SharedPrefUtils;

import java.util.List;

/**
 * Created by Rowandjj on 2015/5/7.
 */
public class JokeRequest extends Request<List<Joke>>
{
    private static final String CACHE_HOME = "joke";
    private static final String CACHE_KEY = "data";
    private Response.Listener<List<Joke>> mListener;

    public JokeRequest(String url,Response.Listener<List<Joke>> listener,Response.ErrorListener errorListener)
    {
        super(Method.GET, url, errorListener);
        this.mListener = listener;
    }

    @Override
    protected Response<List<Joke>> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject object = JSON.parseObject(jsonStr);
            JSONArray array = object.getJSONArray("comments");
            String responseJson = array.toJSONString();
            //每次请求都把数据加入缓存
            SharedPrefUtils.cacheStringbyNameAndKey(AppEnv.getAppContext(),CACHE_HOME,CACHE_KEY,responseJson);
            List<Joke> result = JSON.parseArray(responseJson,Joke.class);

            return Response.success(result,HttpHeaderParser.parseCacheHeaders(response));

        }catch (Exception e)
        {
            LogUtil.e(this,e.getMessage());
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(List<Joke> response)
    {
        if(mListener != null)
            mListener.onResponse(response);
        else
            throw new RuntimeException("no listener found");
    }

    /**
     * 获取缓存
     * */
    public static List<Joke> getCache()
    {
        String data = SharedPrefUtils.getStringByNameAndKey(AppEnv.getAppContext(),CACHE_HOME,CACHE_KEY);
        if(data == null)
            return null;
        else
            return JSON.parseArray(data,Joke.class);
    }
}
