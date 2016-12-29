package com.bwf.p1_landz.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.p1_landz.R;

/**
 * @author freethinker 类说明 自定义ListView的头标View
 */
public class Refresh_ListViewHeader extends LinearLayout {
	private LinearLayout mContainer;
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;
	private TextView mHintTextView;

	private int mState = STATE_NORMAL;
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	// 抬起 按下动画
	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;
	private final int ROTATE_ANIM_DURATION = 180;// 动画时间

	public Refresh_ListViewHeader(Context context) {
		super(context);
		//给当前线性布局 加载View
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.xlistview_header, null);
		//设置宽高
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//把View添加到当前对象里面
		this.addView(mContainer, lp);
		
		mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);

		// 初始化动画
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}


	//设置header显示的高度
	public void setVisiableHeight(int height) {
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContainer.getHeight();
	}
	//设置不同的状态
	public void setState(int state) {
		if (state == mState) {
			return;
		}
		switch (state) {
		case STATE_NORMAL://初始状态
			if (mState != STATE_REFRESHING) {//如果是第一次启动就开始动画
				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			mHintTextView.setText(R.string.xlistview_header_hint_normal);
			
			mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
			break;
		case STATE_READY://正在刷新 
			if (mState != STATE_READY) {//移动调用一次
				mArrowImageView.clearAnimation();
				//默认图片方向是向下   启动动画  把图片方向向上
				mArrowImageView.startAnimation(mRotateUpAnim);
				//修改文字信息
				mHintTextView.setText(R.string.xlistview_header_hint_ready);
			}
			break;
		case STATE_REFRESHING://抬起
			mHintTextView.setText(R.string.xlistview_header_hint_loading);
			mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.INVISIBLE);
			mProgressBar.setVisibility(View.VISIBLE);
			
			break;
		default:
		}
		mState = state;
	}
	
}
