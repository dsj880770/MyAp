package com.bwf.p1_landz.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.p1_landz.R;

/**
 * Created by Cao_Ye on 2016/12/7.
 */
public class RefreshListViewFooter_Bwf extends LinearLayout{
    private LinearLayout mContentView;
    private ProgressBar mProgressBar;
    private TextView mHintView;
    private int mState = STATE_NORMAL;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;
    public RefreshListViewFooter_Bwf(Context context) {
        super(context);
        mContentView = (LinearLayout) View.inflate(context, R.layout.xlistview_footer,null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //设置宽高  添加VIew
        this.addView(mContentView,lp);
        //初始化View
        mHintView = (TextView) findViewById(R.id.xlistview_footer_hint_textview);
        mProgressBar = (ProgressBar) findViewById(R.id.xlistview_footer_progressbar);
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
                mHintView.setText(R.string.xlistview_header_hint_loading);
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
