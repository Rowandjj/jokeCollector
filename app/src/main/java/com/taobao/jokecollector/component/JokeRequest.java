package com.taobao.jokecollector.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.taobao.jokecollector.model.Joke;
import com.taobao.jokecollector.utils.LogUtil;

import java.util.List;

/**
 * Created by Rowandjj on 2015/5/7.
 */
public class JokeRequest extends Request<List<Joke>>
{

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
            List<Joke> result = JSON.parseArray(array.toJSONString(),Joke.class);

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
}
