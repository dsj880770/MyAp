package com.bwf.p1_landz.ui.onevilla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.image.ImageLoader;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseDetailResultBean;



/**
 * Created by Administrator on 2016/12/13.
 */

public class Fragment_detail_fragment01 extends BaseFragment {
    private ImageView imgTitlePic;
    private TextView tvHouseDesc;
    private ImageView imgDown;
    private HouseDetailResultBean resultBean;
    private ImageLoader imageLoader;

    private int lineNum = 0;
    public void setResultBean(HouseDetailResultBean resultBean, ImageLoader imageLoader){
        this.resultBean = resultBean;
        this.imageLoader = imageLoader;
        initData();
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment_1;
    }

    @Override
    public void beforInitView() {
        imgTitlePic = findViewByIdNoCast(R.id.img_titlePic);
        tvHouseDesc = findViewByIdNoCast(R.id.tv_house_desc);
        imgDown = findViewByIdNoCast(R.id.img_down);
    }

    @Override
    public void initView() {
        setOnClick(imgTitlePic,tvHouseDesc,imgDown);
    }

    @Override
    public void initData() {
        if (resultBean != null){
            imageLoader.displayImg(resultBean.result.titlepicImg,imgTitlePic);
            tvHouseDesc.setText(resultBean.result.roomDescripe);
            tvHouseDesc.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lineNum = tvHouseDesc.getLineCount();
                    if (lineNum > 5){
                        tvHouseDesc.setLines(5);
                    }else {
                        tvHouseDesc.setLines(lineNum);
                    }
                }
            },100);
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }


}
