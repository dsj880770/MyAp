package com.bwf.p1_landz.ui.onevilla.fragment;

import android.view.View;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.utils.StringUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseDetailBean;
import com.bwf.p1_landz.entity.HouseDetailResultBean;

/**
 * Created by Administrator on 2016/12/14.
 */

public class Fragment_detail_fragment04 extends BaseFragment{
    private TextView tv_detail_onedetail;//楼盘名称
    private TextView tv_detail_twomoney;//总价
    private TextView tv_detail_amount; //房子户型
    private TextView tv_detail_unitpr;//房子单价
    private TextView tv_detail_gfa;//建筑面积
    private TextView tv_detail_innenbereichSize;//室内面积
    private TextView tv_detail_roomCode;//房源编号
    private TextView tv_detail_lage;//楼盘位置
    private HouseDetailBean result;
    public void setResult(HouseDetailBean result) {
        this.result = result;
        initData();
    }
    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment_4;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        tv_detail_onedetail = findViewByIdNoCast(R.id.tv_detail_onedetail);
        tv_detail_twomoney = findViewByIdNoCast(R.id.tv_detail_twomoney);
        tv_detail_amount = findViewByIdNoCast(R.id.tv_detail_amount);
        tv_detail_unitpr = findViewByIdNoCast(R.id.tv_detail_unitpr);
        tv_detail_gfa = findViewByIdNoCast(R.id.tv_detail_gfa);
        tv_detail_innenbereichSize = findViewByIdNoCast(R.id.tv_detail_innenbereichSize);
        tv_detail_roomCode = findViewByIdNoCast(R.id.tv_detail_roomCode);
        tv_detail_lage = findViewByIdNoCast(R.id.tv_detail_lage);
    }

    @Override
    public void initData() {
        if (result != null){
            //房源详情
            tv_detail_onedetail.setText(result.resblockOneName);
            tv_detail_twomoney.setText(StringUtils.doubleFormat(result.totalprBegin) + "-" + StringUtils.doubleFormat(result.totalprEnd) + "万");
            tv_detail_amount.setText(result.bedroomAmount + "室" + result.parlorAmount + "厅" + result.parlorAmount + "卫");
            tv_detail_unitpr.setText(result.unitprBegin + "-" + result.unitprEnd + "万/平方");
            tv_detail_gfa.setText("建筑面积：  " + StringUtils.doubleFormat(result.gfa) + "平米");
            tv_detail_innenbereichSize.setText("套内面积：  " + result.innenbereichSize);
            tv_detail_roomCode.setText("房源编号：  " + result.roomCode);
            tv_detail_lage.setText("地址：  " + result.lage);
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }
}
