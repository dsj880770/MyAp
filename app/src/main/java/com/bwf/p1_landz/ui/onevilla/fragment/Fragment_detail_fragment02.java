package com.bwf.p1_landz.ui.onevilla.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.image.ImageLoader;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ImgUrlArrBean;
import com.bwf.p1_landz.ui.onlinevilla.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/13.
 */

public class Fragment_detail_fragment02 extends BaseFragment {
//    @Bind(R.id.yangbanjian)
    private TextView yangbanjian;
//    @Bind(R.id.detail_viewPager)
    private ViewPager detailViewPager;
    private static final int MSG_DELAY = 3000;//循环时间
    private int currentItem = -1;//当前ViewPager的位置
    private boolean canAutoScroll ;//自动与手动冲突解决
    private List<ImgUrlArrBean> imgUrlArr;
    private ImageLoader imageLoader;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://切换到自动
                    currentItem++;
                    detailViewPager.setCurrentItem(currentItem);
                    handler.sendEmptyMessageDelayed(1,MSG_DELAY);
                    break;
                case 2://自动播放停止
                    handler.removeMessages(1);
                    break;
            }
        }
    };
    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment_2;
    }

    @Override
    public void beforInitView() {
        yangbanjian = findViewByIdNoCast(R.id.yangbanjian);
        detailViewPager = findViewByIdNoCast(R.id.detail_viewPager);
    }

    @Override
    public void initView() {
        setOnClick(yangbanjian,detailViewPager);
        detailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    if(canAutoScroll){
                        canAutoScroll = false;
                        handler.sendEmptyMessageDelayed(1,MSG_DELAY);
                    }
                }
                if (state == ViewPager.SCROLL_STATE_DRAGGING){
                    handler.sendEmptyMessage(2);
                    canAutoScroll = true;
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }


    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr, boolean isYangbanjian, ImageLoader imageLoader){

        this.imageLoader = imageLoader;
        if(isYangbanjian){
            yangbanjian.setVisibility(View.VISIBLE);
            this.imgUrlArr = new ArrayList<>();
            for(ImgUrlArrBean imgUrlArrBean : imgUrlArr){
                if(imgUrlArrBean.picType.equals("5")) {//判断是否是样板间图片
                    this.imgUrlArr.add(imgUrlArrBean);
                }
            }
        }else{
            this.imgUrlArr = imgUrlArr;
        }
        ImagePagerAdapter adapter;
        adapter = new ImagePagerAdapter(this.getContext(),this.imgUrlArr,imageLoader);
        detailViewPager.setAdapter(adapter);
        //启动VIewPager自动滑动
        handler.sendEmptyMessageDelayed(1,MSG_DELAY);
    }

}
