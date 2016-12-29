package com.bwf.p1_landz.ui.onlinevilla;

import android.view.View;
import android.widget.LinearLayout;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;
import com.bwf.p1_landz.entity.OnLineHouseResult;
import com.bwf.p1_landz.entity.OnLineHouseResultBean;
import com.bwf.p1_landz.view.MyCompareItem;
import com.bwf.p1_landz.view.MyCompareTitle;
import com.bwf.p1_landz.view.MyHorizontalScrollView;
import com.bwf.p1_landz.view.MyScrollViewListener;
import com.bwf.p1_landz.view.MyViewClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CompareActivity extends BaseActivity implements MyViewClickListener,MyScrollViewListener {
    private OnLineHouseResultBean resultBeannew;
    private LinearLayout ll_compare_title;
    private List<Object> objectList;//总数据集合  排序了的
    private LinearLayout ll_compare;

    private MyHorizontalScrollView scroll_title,scroll_item;
    @Override
    public int getContentViewId() {
        return R.layout.activity_compare;
    }

    @Override
    public void beforInitView() {
        resultBeannew = getIntent().getParcelableExtra("resultBeannew");
        OnLineHouseResult onLineHouseResult = new OnLineHouseResult();
        onLineHouseResult.result = resultBeannew;
        onLineHouseResult.initListData();
    }

    @Override
    public void initView() {
        ll_compare_title = findViewByIdNoCast(R.id.ll_compare_title);
        ll_compare = findViewByIdNoCast(R.id.ll_compare);

        scroll_title = findViewByIdNoCast(R.id.scroll_title);
        scroll_item = findViewByIdNoCast(R.id.scroll_item);
        scroll_title.setScrollViewListener(this);
        scroll_item.setScrollViewListener(this);
    }

    @Override
    public void initData() {
        //初始化Title
        if(objectList != null){
            for(int i = 0;i<objectList.size();i++){
                MyCompareTitle title=new MyCompareTitle(this, null, 0);
                title.setClickable(true);
                if(objectList.get(i) instanceof HouseOneArrBean){
                    HouseOneArrBean houseOneArrBean = (HouseOneArrBean) objectList.get(i);
                    title.setInfo(houseOneArrBean.resblockOneName,i);

                }
                if(objectList.get(i) instanceof HouseArrBean){
                    HouseArrBean houseArrBean = (HouseArrBean) objectList.get(i);
                    title.setInfo(houseArrBean.resblockName,i);

                }
                ll_compare_title.addView(title);
            }
        }
        //初始化Item
        //初始化Title
        if(objectList != null){
            for(int i = 0;i<objectList.size();i++){
                MyCompareItem item=new MyCompareItem(this, null, 0);
                if(objectList.get(i) instanceof HouseOneArrBean){
                    HouseOneArrBean houseOneArrBean = (HouseOneArrBean) objectList.get(i);
                    item.setInfo(houseOneArrBean);

                }
                if(objectList.get(i) instanceof HouseArrBean){
                    HouseArrBean houseArrBean = (HouseArrBean) objectList.get(i);
                    item.setInfo(houseArrBean);

                }
                ll_compare.addView(item);
            }
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScrolling(MyHorizontalScrollView scrollView, int l, int t, int oldl, int oldt) {
        if(scrollView == scroll_item) {
            scroll_title.scrollTo(l, t);
        } else if(scrollView == scroll_title) {
            scroll_item.scrollTo(l, t);
        }
    }

    @Override
    public void onViewClicked(int itemId) {
        ll_compare.removeViewAt(itemId);
        ll_compare_title.removeViewAt(itemId);
    }
}
