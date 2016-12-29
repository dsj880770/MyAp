package com.bwf.p1_landz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016/12/22.
 */

public class MyHorizontalScrollView extends HorizontalScrollView{
    private MyScrollViewListener listener;


    public MyHorizontalScrollView(Context context) {
        super(context);

    }
    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyHorizontalScrollView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setScrollViewListener(MyScrollViewListener listener){
        this.listener=listener;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            listener.onScrolling(this, l, t, oldl, oldt);
        }
    }

}
