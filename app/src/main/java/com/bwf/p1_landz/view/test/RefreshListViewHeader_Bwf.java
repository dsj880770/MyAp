package com.bwf.p1_landz.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;

import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;

/**
 * Created by Cao_Ye on 2016/12/7.
 */
public class RefreshListViewHeader_Bwf extends LinearLayout{
    private LinearLayout mContaniner;//表示整个下拉刷新的View
    private ImageView mArrowImageView;//下拉箭头图片
    private ProgressBar mProgressBar;//刷新时候的进度条
    private TextView mHintTextView;

    private RotateAnimation mRotateUpAnim;//图片向上动画
    private RotateAnimation mRotateDownAnim;//图片向下动画

    //下拉刷新的状态
    private int mState = STATE_NORMAL;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;
    public RefreshListViewHeader_Bwf(Context context) {
        super(context);
        mContaniner = (LinearLayout) View.inflate(context, R.layout.xlistview_header,null);

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //设置宽高  添加VIew
        this.addView(mContaniner,lp);
        //初始化View
        mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);

        //初始化箭头图片动画
        //箭头向上
        mRotateUpAnim = new RotateAnimation(0.0f,-180.0f, Animation.RELATIVE_TO_SELF,0.5f
        ,Animation.RELATIVE_TO_SELF,0.5f);
        mRotateUpAnim.setDuration(200);
        mRotateUpAnim.setFillAfter(true);//播放完成之后停在原地
        //箭头向下
        mRotateDownAnim = new RotateAnimation(-180.0f,0.0f, Animation.RELATIVE_TO_SELF,0.5f
                ,Animation.RELATIVE_TO_SELF,0.5f);
        mRotateDownAnim.setDuration(200);
        mRotateDownAnim.setFillAfter(true);//播放完成之后停在原地

    }

    public void setVisiableHeight(int height){
        LayoutParams lp = (LayoutParams) mContaniner.getLayoutParams();
        lp.height = height;
        mContaniner.setLayoutParams(lp);
    }

    public int getVisiableHeight(){
        return  mContaniner.getHeight();
    }


    public  void setmState(int state){
        if(state == mState){//不能连续调用同一种状态
            return;
        }
        LogUtils.e("msg",state+"");
        switch (state){
            case STATE_NORMAL://初始状态
                if(mState == STATE_READY){
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                mHintTextView.setText(R.string.xlistview_header_hint_normal);
                mArrowImageView.setVisibility(VISIBLE);
                mProgressBar.setVisibility(GONE);
                break;
            case STATE_READY://正在刷新
                    mHintTextView.setText(R.string.xlistview_header_hint_ready);
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);//启动动画
                break;
            case STATE_REFRESHING://抬起
                mHintTextView.setText(R.string.xlistview_header_hint_loading);
                mArrowImageView.setVisibility(GONE);
                mArrowImageView.clearAnimation();
                mProgressBar.setVisibility(VISIBLE);
                break;
        }
        mState = state;
    }
}
