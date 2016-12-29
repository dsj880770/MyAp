package com.bwf.p1_landz.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.p1_landz.R;

/**
 * @author freethinker 类说明 自定义ListView的尾标View
 */
public class Refresh_ListViewFooter extends LinearLayout {
	private Context mContext;
	private int mState = STATE_NORMAL;
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;
	private LinearLayout mContentView;// 整个View
	private ProgressBar mProgressBar;// 进度条
	private TextView mHintView;// 文本信息

	public Refresh_ListViewFooter(Context context) {
		super(context);
		mContentView = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.xlistview_footer, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		addView(mContentView, params);

		mProgressBar = (ProgressBar) findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView)findViewById(R.id.xlistview_footer_hint_textview);
	}


	public void setVisiableHeight(int height) {
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.height = height;
		mContentView.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContentView.getHeight();
	}

	public void setState(int state){
		if (state == mState) {
			return;
		}
		switch (state) {
		case STATE_NORMAL:
			mHintView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
			break;
		case STATE_LOADING:
			mProgressBar.setVisibility(View.VISIBLE);
			mHintView.setVisibility(View.INVISIBLE);
			break;
		case STATE_READY:
			if(mState != STATE_READY){
				mHintView.setVisibility(View.VISIBLE);
				mProgressBar.setVisibility(View.INVISIBLE);
				mHintView.setText(R.string.xlistview_footer_hint_ready);
			}
			break;
		}
		mState = state;
	}
}
