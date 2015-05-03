package com.taobao.jokecollector.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.taobao.jokecollector.R;
import com.taobao.jokecollector.utils.VolleyHelper;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public class BaseFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void toast(String msg)
    {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    protected void dialog(Context ctx,int theme,String title,String msg,String posMsg,String negMsg,DialogInterface.OnClickListener confirm,DialogInterface.OnClickListener cancel)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx,theme);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(posMsg, confirm);
        builder.setNegativeButton(negMsg, cancel);
        builder.show();
    }

    protected void dialog(String title,String msg,DialogInterface.OnClickListener confirm,DialogInterface.OnClickListener cancel)
    {
        dialog(this.getActivity(), R.style.AlertDialogStyle,title,msg,"确定","取消",confirm,cancel);
    }

    protected void request(Request<?> request)
    {
        VolleyHelper.getInstance().request(request,this);
    }
}
