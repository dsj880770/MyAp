package com.bwf.p1_landz.ui.onevilla.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.StringUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseDetailBean;

/**
 * Created by Administrator on 2016/12/15.
 */

public class Fragment_detail_fragment06 extends BaseFragment {
    private TextView tv_onedetail_zhuangxiu;
    private TextView tv_onedetail_zhuangxiumoney;
    private TextView tv_onedetail_zhuangxiuarea;
    private TextView tv_onedetail_jiaofangtime;
    private TextView tv_gone_weizhi, tv_gone_wuye, tv_gone_kaifashang, tv_gone_caizhi, tv_gone_cheweishu, tv_gone_jianzhufenge,
            tv_gone_loujianju, tv_gone_cainuan, tv_gone_gongnuan, tv_gone_wuyefei, tv_gone_area, tv_onedetail_floor,
            tv_onedetail_rongji, tv_onedetail_lvhua;

    private RelativeLayout rl_gone_text;//可隐藏的部分
    private RelativeLayout rl_gone_down;//控制显示与隐藏
    private ImageView jiantou;//指示箭头

    /*****************项目特点 ***********************/
    private TextView tv_house_project_feature;

    /*****************交通配套 ***********************/
    private TextView tv_detail_mapName;//地址
    private ImageView iv_map;//地址图片

    private HouseDetailBean result;
    private ImageLoader imageLoader;
    public void setResult(HouseDetailBean result, ImageLoader imageLoader) {
        this.result = result;
        this.imageLoader = imageLoader;
        initData();
    }
    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment_6;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        tv_onedetail_zhuangxiu = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiu);
        tv_onedetail_zhuangxiumoney = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiumoney);
        tv_onedetail_zhuangxiuarea = findViewByIdNoCast(R.id.tv_onedetail_zhuangxiuarea);
        tv_onedetail_jiaofangtime = findViewByIdNoCast(R.id.tv_onedetail_jiaofangtime);
        tv_gone_weizhi = findViewByIdNoCast(R.id.tv_gone_weizhi);
        tv_gone_wuye = findViewByIdNoCast(R.id.tv_gone_wuye);
        tv_gone_kaifashang = findViewByIdNoCast(R.id.tv_gone_kaifashang);
        tv_gone_caizhi = findViewByIdNoCast(R.id.tv_gone_caizhi);
        tv_gone_cheweishu = findViewByIdNoCast(R.id.tv_gone_cheweishu);
        tv_gone_jianzhufenge = findViewByIdNoCast(R.id.tv_gone_jianzhufenge);
        tv_gone_loujianju = findViewByIdNoCast(R.id.tv_gone_loujianju);
        tv_gone_cainuan = findViewByIdNoCast(R.id.tv_gone_cainuan);
        tv_gone_gongnuan = findViewByIdNoCast(R.id.tv_gone_gongnuan);
        tv_gone_wuyefei = findViewByIdNoCast(R.id.tv_gone_wuyefei);
        tv_gone_area = findViewByIdNoCast(R.id.tv_gone_area);
        tv_onedetail_floor = findViewByIdNoCast(R.id.tv_onedetail_floor);
        tv_onedetail_rongji = findViewByIdNoCast(R.id.tv_onedetail_rongji);
        tv_onedetail_lvhua = findViewByIdNoCast(R.id.tv_onedetail_lvhua);
        rl_gone_text = findViewByIdNoCast(R.id.rl_gone_text);
        rl_gone_down = findViewByIdNoCast(R.id.rl_gone_down);
        jiantou = findViewByIdNoCast(R.id.imageView3);

        /*****************项目特点 ***********************/
        tv_house_project_feature = findViewByIdNoCast(R.id.tv_house_project_feature);
        /*****************交通 ***********************/
        tv_detail_mapName = findViewByIdNoCast(R.id.tv_detail_mapName);
        iv_map = findViewByIdNoCast(R.id.iv_map);

        setOnClick(R.id.rl_gone_down);
    }

    @Override
    public void initData() {
        if (result != null) {
            //装修标准
            tv_onedetail_zhuangxiu.setText(result.decorationName);
            //单平方装修标准元
            tv_onedetail_zhuangxiumoney.setText(StringUtils.doubleFormat(result.metersPrice) + "元");
            tv_onedetail_zhuangxiuarea.setText(StringUtils.doubleFormat(result.covers) + "平米");
            tv_onedetail_jiaofangtime.setText(StringUtils.longToStrng(StringUtils.isEmpty(result.launchTime) ? 0 : Long.parseLong(result.launchTime), "yyyy-MM-dd"));
            tv_onedetail_rongji.setText(StringUtils.doubleFormat(result.volumeRate) + "%");
            tv_onedetail_lvhua.setText(StringUtils.doubleFormat(result.greeningRate) + "%");
            tv_onedetail_floor.setText(StringUtils.doubleFormat(result.storey) + "m");
            //建筑面积
            tv_gone_area.setText(StringUtils.doubleFormat(result.gfa) + "㎡");
            tv_gone_wuyefei.setText(StringUtils.doubleFormat(result.propertyCosts) + "元/月/㎡");
            tv_gone_gongnuan.setText(result.heating);
            tv_gone_cainuan.setText(result.heating1);
            //楼间距
            tv_gone_loujianju.setText(StringUtils.doubleFormat(result.floorSpace) + "m");
            //建筑风格
            tv_gone_jianzhufenge.setText(result.buildingType);
            tv_gone_cheweishu.setText(result.parkingNum + "个");
            //材质
            tv_gone_caizhi.setText(result.facadeMaterial);
            //开发商
            tv_gone_kaifashang.setText(result.developers);
            //物业
            tv_gone_wuye.setText(result.immobilien);
            //位置
            tv_gone_weizhi.setText(result.lage);

            //项目特点
            tv_house_project_feature.setText(result.formType);

            //地址
            tv_detail_mapName.setText("[" + result.districtName + " "
                    + result.circleTypeName + "] " + result.lage);
            //地图图片
            getMapPostion();
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_gone_down:
                if (rl_gone_text.getVisibility() == View.GONE) {
                    rl_gone_text.setVisibility(View.VISIBLE);
                    jiantou.setBackgroundResource(R.mipmap.content_up);
                } else {
                    rl_gone_text.setVisibility(View.GONE);
                    jiantou.setBackgroundResource(R.mipmap.first_down);
                }
                break;
        }
    }
    private void getMapPostion() {
        String imgUrl = "http://api.map.baidu.com/staticimage?center="
                + result.lage
                + "&width=480&height=270&zoom=15&scale=1"
                +"&markers="+result.lage;
        imageLoader.displayImg(imgUrl, iv_map);
    }
}
