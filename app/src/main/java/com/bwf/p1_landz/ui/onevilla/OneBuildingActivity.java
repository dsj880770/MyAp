package com.bwf.p1_landz.ui.onevilla;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.request.OnLineHouseRequest;
import com.bwf.p1_landz.ui.onlinevilla.OnLineHouseAdapter;
import com.bwf.p1_landz.ui.search.SearchActivity;
import com.bwf.p1_landz.view.TitlePopupWindow;

import java.util.List;

/**
 * Created by Cao_Ye on 2016/11/30.
 * 一手豪宅
 */
public class OneBuildingActivity extends BaseActivity{
    private EditText onehouse_edt;
    private RelativeLayout onehouse_area,onehouse_price,onehouse_more;
    private TextView area_tv,price_tv,more_tv;
    private OnLineHouseRequest onLineHouseRequest;
    private OnlineTypeBean onlineTypeBean;
    private OnLineHouseAdapter onLineHouseAdapter;
    private List<ParamListBean> location_paramList,price_paramList;
    @Override
    public int getContentViewId() {
        return R.layout.onehouse    ;
    }

    @Override
    public void beforInitView() {
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        //进行分类
        if(onlineTypeBean != null) {
            for (OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result) {
                if (onLineTypeResultBean.paramType.equals("1001")) {//地区
                    location_paramList = onLineTypeResultBean.paramList;
                }
                if (onLineTypeResultBean.paramType.equals("1003")) {//价格
                    price_paramList = onLineTypeResultBean.paramList;
                }
            }
        }
    }

    @Override
    public void initView() {
        onehouse_edt = findViewByIdNoCast(R.id.et_onehouse_search);
        onehouse_area = findViewByIdNoCast(R.id.onehouse_area);
        onehouse_price = findViewByIdNoCast(R.id.onehouse_price);
        onehouse_more = findViewByIdNoCast(R.id.onehouse_more);
        price_tv = findViewByIdNoCast(R.id.price_tv);
        area_tv = findViewByIdNoCast(R.id.area_tv);
        more_tv = findViewByIdNoCast(R.id.more_tv);
    }

    @Override
    public void initData() {
        setOnClick(onehouse_edt,more_tv);
        setOnClick(onehouse_area,onehouse_more,onehouse_price);
        onLineHouseRequest = new OnLineHouseRequest();
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.onehouse_area://区域
                    currentPost = 0;
                    TitlePopupWindow titlePopupWindow = new TitlePopupWindow(this, false);
                    titlePopupWindow.setParamListBean(location_paramList, pTsCallBack);//设置数据
                    titlePopupWindow.showAsDropDown(onehouse_area);
                    break;
                case R.id.onehouse_price://价格
                    currentPost = 1;
                    TitlePopupWindow titlePopupWindow1 = new TitlePopupWindow(this, true);
                    titlePopupWindow1.setParamListBean(price_paramList, pTsCallBack);//设置数据
                    //设置自定义View
                    titlePopupWindow1.setPrice_zidingyuView(this, pTsCallBack);
                    titlePopupWindow1.showAsDropDown(onehouse_price);
                    break;
                case R.id.et_onehouse_search:
                    Intent intent = new Intent(OneBuildingActivity.this, SearchActivity.class);
                    intent.putExtra("intent_type",6);
                    startActivity(intent);
                    break;
                case R.id.more_tv:
                    Intent intent1 = new Intent(OneBuildingActivity.this,OneHouseSelectActivity.class);
                    intent1.putExtra("OnLineHouseRequest",onLineHouseRequest);
                    startActivity(intent1);
                    break;

            }
    }
    int currentPost = 0;//存储点击的哪个pupop
    /**
     * 筛选后回调的返回值接收
     */
    private TitlePopupWindow.PupupWindowItemClickCallBack  pTsCallBack = new TitlePopupWindow.PupupWindowItemClickCallBack() {
        @Override
        public void onItemClickCallBack(ParamListBean paramListBean) {
            if(paramListBean == null){
                return;
            }
            switch(currentPost){
                case 0://区域
                    area_tv.setText(""+paramListBean.name);
                    onLineHouseRequest.circleTypeCode = paramListBean.key;
                    break;
                case 1://价格
                    price_tv.setText(""+paramListBean.name);
                    onLineHouseRequest.totalPricesBegin = paramListBean.minValue;
                    onLineHouseRequest.totalPricesEnd = paramListBean.maxValue;
                    break;

            }
        }
    };
}