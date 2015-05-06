package com.taobao.jokecollector.model;

/**
 * Created by Rowandjj on 2015/5/6.
 */
public class NetworkEvent
{
    public int type;

    public static final int TYPE_AVAILABLE = 1;
    public static final int TYPE_UNAVAILABLE = -1;

    public NetworkEvent(int type)
    {
        this.type = type;
    }
}
