package com.bwf.p1_landz.ui.onevilla.fragment;

import android.view.View;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.request.OnLineHouseRequest;
import com.bwf.p1_landz.ui.onlinevilla.OnLineHouseAdapter;
import com.bwf.p1_landz.view.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class Fragment_detail_fragment07 extends BaseFragment{
    private OnLineHouseAdapter onLineHouseAdapter;
    private OnLineHouseRequest onLineHouseRequest;
    private OnlineTypeBean onlineTypeBean;
    private TextView tv_onemore;
    private List<Object> alllist;
    private RefreshListView lv_moreonehouse;
    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment07;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        lv_moreonehouse = findViewByIdNoCast(R.id.lv_moreonehouse);
        tv_onemore = findViewByIdNoCast(R.id.tv_onemore);
        setOnClick(R.id.tv_onemore);
    }

    @Override
    public void initData() {
        onLineHouseRequest = new OnLineHouseRequest();
        alllist = new ArrayList<>();
        onLineHouseAdapter = new OnLineHouseAdapter(getContext());
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }
}
