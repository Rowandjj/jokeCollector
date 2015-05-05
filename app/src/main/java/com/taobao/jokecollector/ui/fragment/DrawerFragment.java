package com.taobao.jokecollector.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.taobao.jokecollector.R;
import com.taobao.jokecollector.app.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 菜单
 */
public class DrawerFragment extends BaseFragment implements View.OnClickListener
{
    @InjectView(R.id.menu_about)
    LinearLayout mAboutBtn;

    @InjectView(R.id.menu_settings)
    LinearLayout mSettingBtn;

    @InjectView(R.id.menu_feedback)
    LinearLayout mFeedbackBtn;

    public DrawerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView =  inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.inject(this, rootView);

        addCallback();
        return rootView;
    }

    private void addCallback()
    {
        mAboutBtn.setOnClickListener(this);
        mFeedbackBtn.setOnClickListener(this);
        mSettingBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.menu_settings:
                dialog("标题", "设置", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("确定");
                    }
                }, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("取消");
                    }
                });
                break;
            case R.id.menu_feedback:
                dialog("标题", "反馈", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("确定");
                    }
                }, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("取消");
                    }
                });
                break;
            case R.id.menu_about:
                dialog("标题", "关于", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("确定");
                    }
                }, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        toast("取消");
                    }
                });
                break;
        }
    }
}
