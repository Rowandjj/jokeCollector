package com.taobao.jokecollector.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.taobao.jokecollector.R;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected void initData(){}
    protected abstract int getLayoutId();
    protected abstract void initView();

    protected void toast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
        dialog(this,R.style.AlertDialogStyle,title,msg,"确定","取消",confirm,cancel);
    }



}






