package com.bwf.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cao_Ye on 2016/11/28.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(getResourceId(), null);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        beforInitView();
        initView();
        initData();
        afterInitView();
    }

    /**
     * 返回Layout ID
     * @return
     */
    public abstract int getResourceId();

    /**
     * 初始化View之前的内容
     */
    public abstract void beforInitView();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化之后的执行
     */
    public abstract void afterInitView();


    /**
     * 不用强制转换的findViewById
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id){

        return rootView==null?null: (T)rootView.findViewById(id);
    }

    /**
     * 设置所有需要注册的监听
     * @param ids
     */
    public void setOnClick(int... ids){
        for (int id : ids){
            rootView.findViewById(id).setOnClickListener(this);
        }
    }

    /**
     * 设置所有需要注册的监听
     * @param views
     */
    public void setOnClick(View... views){
        for (View v : views){
            v.setOnClickListener(this);
        }
    }

}
