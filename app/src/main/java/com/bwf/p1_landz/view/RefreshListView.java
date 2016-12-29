package com.bwf.p1_landz.view;




import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout.LayoutParams;
   
/**
 * @author freethinker 类说明
 */
public class RefreshListView extends ListView {
	private float mLastY = -1; // 监听移动Y坐标
	// footer view
	private Refresh_ListViewFooter mFooterView;
	// header view
	private Refresh_ListViewHeader mHeaderView;

	private int mHeaderViewHeight = 0; // 头标的高度
	private int mFooterViewHeight = 0; // 头标的高度
	private final static float OFFSET_RADIO = 1.8f;// 移动的比例
	private Scroller mScroller;// 滑动工具类
	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	private final static int SCROLL_DURATION = 400;

	private int mTotalItemCount;// 滚动的位置
	boolean mPullRefreshing = false;
	//初始化参数   加载headerView and footerView
	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context, new DecelerateInterpolator());

		mFooterView = new Refresh_ListViewFooter(context);
		mHeaderView = new Refresh_ListViewHeader(context);
		addFooterView(mFooterView);
		addHeaderView(mHeaderView);

		// 获得头标的高度
		mHeaderView.measure(0, 0);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		Log.i("msg", "mHeaderViewHeight:" + mHeaderViewHeight);
		// 隐藏mHeaderView
		mHeaderView.setVisiableHeight(0);
		
		mFooterView.measure(0, 0);
		mFooterViewHeight = mFooterView.getMeasuredHeight()+30;
		mFooterView.setVisibility(View.INVISIBLE);
		Log.i("msg", "mFooterViewHeight:" + mFooterViewHeight);
		this.setOnScrollListener(scrollListener);
	}

	
	// 监听触摸
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {//获得触摸的状态
		case MotionEvent.ACTION_DOWN:// 按下
			// getRawX：触摸点相对于屏幕的坐标
			// getX： 触摸点相对于父类View的坐标
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:// 移动
			float deltaY = ev.getRawY() - mLastY;// 移动的距离
			mLastY = ev.getRawY();// 重新定义当前坐标
			// 如果是第一个item 且当前高度大于0 或者移动的距离大于0 那么开始打开headerView
			if (this.getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
				//更新headerView的高度
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getVisiableHeight() > 0 || deltaY < 0)) {
				//当前获得ListVIew显示的最后一个item的索引  如果等当前adapter的最后一个索引 则开始移动footerVIew
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
			break;
		case MotionEvent.ACTION_UP:// 抬起
			mLastY = -1; // reset
			// header
			if (getFirstVisiblePosition() == 0) {
				// invoke refresh 如果内容超过本来的宽度 则变成正在加载中
				if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
					mHeaderView
							.setState(Refresh_ListViewHeader.STATE_REFRESHING);
					if (refreh_ListViewListener != null) {
						refreh_ListViewListener.onRefresh();// 回调刷新
					}
				}
				//重置 headerView的高度
				resetHeaderHeight();
			}else if (getLastVisiblePosition() == mTotalItemCount - 1)
			{
				// invoke load more.
				if (mFooterView.getVisiableHeight() > mFooterViewHeight)
				{
					mFooterView.setState(Refresh_ListViewFooter.STATE_LOADING);
					if (refreh_ListViewListener != null)
					{
						refreh_ListViewListener.onLoadMore();
					}
				}
				resetFooterHeight();
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	//覆写View的方法  当View刷新的时候  这个方法会执行
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {//判断 scrooller有没有执行完 
			if (mScrollBack == SCROLLBACK_HEADER) {
				//设置headerVIew当前的高度为 scroller滑动的位置
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			}else if(mScrollBack == SCROLLBACK_FOOTER)
			{
				mFooterView.setVisiableHeight(mScroller.getCurrY());
			}
			postInvalidate();//重复刷新  直到mScroller 执行完
		}
		super.computeScroll();
	}

	/**
	 * 更新头部高度  float delta  要移动的距离
	 */
	private void updateHeaderHeight(float delta) {
		// 设置高度   移动的距离+原有的高度
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		// 如果当前高度 大于 本来的高度 变成松开刷新数据 否则是下拉刷新
		if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
			mHeaderView.setState(Refresh_ListViewHeader.STATE_READY);
		}else{
			
		}
		//刷新View
		setSelection(0); // scroll to top each time 刷新View
	}

	/**
	 * 重置 headerView的高度
	 */
	private void resetHeaderHeight() {
		mScrollBack = SCROLLBACK_HEADER;// 设置头标滑动
		int height = mHeaderView.getVisiableHeight();//获得当前的高度
		int finalHeight = mHeaderViewHeight;//默认的高度
		if (height > mHeaderViewHeight) {
			mScroller.startScroll(0, height, 0, -finalHeight - height,
					SCROLL_DURATION);
		} else {
			mScroller.startScroll(0, height, 0, -height, SCROLL_DURATION);
		}
		// trigger computeScroll
		invalidate();//刷新View 就会响应computeScroll
	}

	private void updateFooterHeight(float delta)
	{
		//获得当前的高度 加上  移动的距离
		int height = mFooterView.getVisiableHeight() + (int) delta;
			if (height > mFooterViewHeight+50)
			{ // height enough to invoke load
				// more.
				//拉升的高度 大于 本来的高度+50  就进入到准备加载数据中
				mFooterView.setState(Refresh_ListViewFooter.STATE_READY);
			}
			else
			{
				mFooterView.setState(Refresh_ListViewFooter.STATE_NORMAL);
			}
			//设置footerVIew的高度
		mFooterView.setVisiableHeight(height);

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}
	private void resetFooterHeight()
	{
		
		int bottomMargin = mFooterView.getVisiableHeight();
		Log.i("msg", "bottomMargin: "+bottomMargin);
		if (bottomMargin > mFooterViewHeight+50)
		{
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
			invalidate();
		}
	}
	
	//回调的接口
	private Refreh_ListViewListener refreh_ListViewListener;

	public void setRefreh_ListViewListener(
			Refreh_ListViewListener refreh_ListViewListener) {
		this.refreh_ListViewListener = refreh_ListViewListener;
	}

	public interface Refreh_ListViewListener {
		public void onRefresh();

		public void onLoadMore();
	}

	// 加载完成
	public void setOnComplete() {
		mHeaderView.setState(Refresh_ListViewHeader.STATE_NORMAL);
		mFooterView.setState(Refresh_ListViewFooter.STATE_NORMAL);
		resetHeaderHeight();
		resetFooterHeight();
	}


	/**
	 * 监听ListView的滚动
	 */
	OnScrollListener scrollListener = new OnScrollListener() {
		// ListView的滚动有三种状态
		// 第一是静止状态，SCROLL_STATE_IDLE
		// 第二是手指滚动状态，SCROLL_STATE_TOUCH_SCROLL
		// 第三是手指不动了，但是屏幕还在滚动状态。SCROLL_STATE_FLING
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
//			// 当不滚动时
//			if (scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
				if(mFooterView.getVisibility() == View.INVISIBLE){
//					Log.i("msg", mFooterView.getVisibility()+"  mFooterView.getVisibility() ");
					mFooterView.setVisibility(View.VISIBLE);//提前在滑到底部的时候 设置显示
				}
//			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			mTotalItemCount = totalItemCount;
		}
	};
}
