package com.taobao.jokecollector.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.taobao.jokecollector.R;
import com.taobao.jokecollector.utils.ActivityManager;
import com.taobao.jokecollector.utils.ScreenUtils;
import com.taobao.jokecollector.utils.VolleyHelper;

/**
 * Created by Rowandjj on 2015/5/3.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityManager.getInstance().add(this);
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
        dialog(this, R.style.AlertDialogStyle, title, msg, "确定", "取消", confirm, cancel);
    }


    protected void request(Request<?> request)
    {
        VolleyHelper.getInstance().request(request, this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ActivityManager.getInstance().remove(this);
        VolleyHelper.getInstance().cancel(this);
    }

    protected void startActivity(Class<? extends Activity> target)
    {
        startActivity(new Intent(this, target));
    }

    protected void startActivity(Class<? extends Activity> target,Bundle bundle)
    {
        Intent intent = new Intent(this,target);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 设置状态栏高度，可以实现沉浸式效果
     * */
    protected void setStatusBarColor(Toolbar mToolbar)
    {
        ColorDrawable colorDrawable = (ColorDrawable) mToolbar.getBackground();
        Window w = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            w.setStatusBarColor(colorDrawable.getColor());
        }
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
        {
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
            mToolbar.setPadding(0, statusBarHeight, 0, 0);
        }
    }

}






