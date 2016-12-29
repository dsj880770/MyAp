package com.bwf.p1_landz.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Scroller;

import com.bwf.framework.utils.LogUtils;

/**
 * Created by Cao_Ye on 2016/12/7.
 */
public class RefreshListView_Bwf extends ListView{
    //下拉刷新View
    private  RefreshListViewFooter_Bwf mFooterView = null;
    //上拉加载View
    private  RefreshListViewHeader_Bwf mHeaderView = null;

    private int mHeaderViewHeight = 0;//头部的高度
    private int mFooterViewHeight = 0;

    private float mLastY = -1;//y轴的坐标

    private float OFFSET_RADIO = 1.8f;

    private Scroller mScroller;//滑动工具类
    private int SCROLL_DURSTTION = 500;//滑动的时间
    public RefreshListView_Bwf(Context context, AttributeSet attrs) {
        super(context, attrs);

        //添加头部和尾部
        mFooterView = new RefreshListViewFooter_Bwf(context);
        mHeaderView = new RefreshListViewHeader_Bwf(context);
        this.addFooterView(mFooterView);
        this.addHeaderView(mHeaderView);

        //获得头部的高度
        mHeaderView.measure(0,0);
        //头部默认的宽度
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        LogUtils.e("msg",mHeaderViewHeight+"  mHeaderViewHeight");
        //初始化的时候隐藏HeaderView
//        mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
        mHeaderView.setVisiableHeight(0);

        //尾部高度
        mFooterView.measure(0,0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setVisibility(View.GONE);

        mScroller = new Scroller(context);
        //滑动监听 判断已经滑动到了最后面
        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                //判断到了ListView的最后一行 显示加载更多View
                if(i == SCROLL_STATE_IDLE && (getLastVisiblePosition() == getCount()-1)){
                    mFooterView.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    //触摸监听
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            //getRawY()  触摸点相对于屏幕的坐标
            //getY  触摸点相对于父类的View的坐标
            case MotionEvent.ACTION_DOWN://按下
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                //移动Y的距离
                float deltaY = ev.getRawY()-mLastY;
                //获得继续移动的Y的坐标
                mLastY = ev.getRawY();
                //移动
                //判断是下拉刷新移动(当前ListView显示在界面的View第一个是多少,当前移动的方向是向下，)
                if(this.getFirstVisiblePosition() == 0 && (mHeaderView.getVisiableHeight()>0 ||deltaY > 0)){
                        updateHeaderHeight(deltaY/OFFSET_RADIO);
                    return true;
                }else if(getLastVisiblePosition() == getCount()-1 &&(mFooterView.getVisiableHeight() > 0 || deltaY < 0)){
                    updateFooterHeight(-deltaY/OFFSET_RADIO);
                }
                break;
            case MotionEvent.ACTION_UP://抬起
                mLastY = -1;//重置
                //判断下拉刷新抬起
                if(this.getFirstVisiblePosition() == 0){
                    //当前显示的高度大于0
                    if(mHeaderView.getVisiableHeight() > 0){
                        if(mHeaderView.getVisiableHeight() > mHeaderViewHeight){
                            mHeaderView.setmState(RefreshListViewHeader_Bwf.STATE_REFRESHING);//设置成刷新状态
                            if(refreh_listViewListener != null){
                                refreh_listViewListener.onRefresh();//下拉刷新回调
                            }
                        }
                        resetHeaderHeight();//重置回下拉刷新View的显示
                    }
                }else if(getLastVisiblePosition() == getCount()-1){
                    if(mFooterView.getVisiableHeight() > mFooterViewHeight){
                        mFooterView.setState(RefreshListViewFooter_Bwf.STATE_LOADING);
                        if(refreh_listViewListener != null){
                            refreh_listViewListener.onLoadMore();
                        }
                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    //更新头部的高度
    public void updateHeaderHeight(float delta){
        //设置高度
        mHeaderView.setVisiableHeight(mHeaderView.getVisiableHeight()+(int)delta);
        if(mHeaderView.getVisiableHeight() > mHeaderViewHeight+50){
            mHeaderView.setmState(RefreshListViewHeader_Bwf.STATE_READY);//进入到刷新
        }else{
            mHeaderView.setmState(RefreshListViewHeader_Bwf.STATE_NORMAL);
        }
        invalidate();
    }


    /**
     * 重置头部高度
     */
    public void resetHeaderHeight(){
        LogUtils.e("msg",mHeaderView.getVisiableHeight()+"  "+mHeaderViewHeight);
        //判断当前下拉的高度是否大于默认头部的高度的2倍 则启动刷新操作 不然回到原来位置
        if(mHeaderView.getVisiableHeight() > mHeaderViewHeight+50){
            mScroller.startScroll(0, mHeaderView.getVisiableHeight(),0,mHeaderViewHeight-mHeaderView.getVisiableHeight(),SCROLL_DURSTTION);
        }else{
            mScroller.startScroll(0, mHeaderView.getVisiableHeight(),0,-mHeaderView.getVisiableHeight(),SCROLL_DURSTTION);
        }
        invalidate();//刷新View 启动conputeScrool
    }

    //更新尾部的高度
    public void updateFooterHeight(float delta){
        int height = mFooterView.getVisiableHeight()+(int)delta;
        if(height > mFooterViewHeight + 50){
                mFooterView.setState(RefreshListViewFooter_Bwf.STATE_READY);
        }else{
            mFooterView.setState(RefreshListViewFooter_Bwf.STATE_NORMAL);
        }
        mFooterView.setVisiableHeight(height);
    }


    public void resetFooterHeight(){
        if(mFooterView.getVisiableHeight() > mFooterViewHeight+50){
            mScroller.startScroll(0,mFooterView.getVisiableHeight(),0,mFooterViewHeight-mFooterView.getVisiableHeight(),SCROLL_DURSTTION);
        }
        invalidate();
    }
    //当View刷新的时候  这个方法会执行
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){//判断Scroller是否有没有执行完成
            if(!mScroller.isFinished()){
                if(getFirstVisiblePosition() == 0){
                    mHeaderView.setVisiableHeight(mScroller.getCurrY());
                }else if(getLastVisiblePosition() == getCount()-1){
                    mFooterView.setVisiableHeight(mScroller.getCurrY());
                }
            }
            postInvalidate();//刷新View
        }
        super.computeScroll();
    }

    //设置刷新或者回调完成的方法
    public void setOnComplete(){
        //刷新结束
        mHeaderView.setmState(RefreshListViewHeader_Bwf.STATE_NORMAL);//设置回初始状态
        resetHeaderHeight();
        //加载结束
        mFooterView.setState(RefreshListViewFooter_Bwf.STATE_NORMAL);
        resetFooterHeight();
    }

    //声明接口
    private Refreh_ListViewListener refreh_listViewListener;

    public void setRefreh_listViewListener(Refreh_ListViewListener refreh_listViewListener) {
        this.refreh_listViewListener = refreh_listViewListener;
    }

    public interface Refreh_ListViewListener{
        void onRefresh();
        void onLoadMore();
    }
}
