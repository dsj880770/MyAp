package com.bwf.p1_landz.ui.onevilla;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GuWenResultBean;
import com.bwf.p1_landz.entity.HouseDetailResultBean;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment01;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment02;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment03;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment04;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment05;
import com.bwf.p1_landz.ui.onevilla.fragment.Fragment_detail_fragment06;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/13.
 */

public class OneHouseDetailActivity extends BaseActivity{
    private TextView tvTitleDetail;
    private TextView tvDecriptionDetail;
    private ImageView shareImg;
    private ImageView shouchangImg;
    private ImageView imDetailPhoto;
    private TextView tvHuxingName;
    private TextView tvHuxingPhone;
    private ImageView imDetailPhone;
    private ImageView imDetailSms;
    //房源ID  楼盘ID
    private String houseOneId, resblockId;

//    //图片下载器
    private ImageLoader imageLoader;
//    //一手房源的logo图片和房源描述
    private Fragment_detail_fragment01 fragment01;

//    //样板间
    private Fragment_detail_fragment02 fragment02;
//
//    //本房顾问
    private Fragment_detail_fragment03 fragment03;

//    //房源基本信息
    private Fragment_detail_fragment04 fragment04;
    private Fragment_detail_fragment05 fragment05;
    private Fragment_detail_fragment06 fragment06;

    private GuWenResultBean.ShowArr show;// 最底下联系人对象

    private HouseDetailResultBean resultBean;//详情信息对象

    @Override
    public int getContentViewId() {
        return R.layout.onehousedetail;
    }

    @Override
    public void beforInitView() {
        houseOneId = getIntent().getStringExtra("houseOneId");
        resblockId = getIntent().getStringExtra("resblockId");
        tvTitleDetail = findViewByIdNoCast(R.id.tv_title_detail);
        tvDecriptionDetail = findViewByIdNoCast(R.id.tv_decription_detail);
        shareImg = findViewByIdNoCast(R.id.share_img);
        shouchangImg = findViewByIdNoCast(R.id.shouchang_img);
        tvHuxingName = findViewByIdNoCast(R.id.tv_huxing_name);
        tvHuxingPhone = findViewByIdNoCast(R.id.tv_huxing_phone);
//        imDetailPhoto = findViewByIdNoCast(R.id.);

    }

    @Override
    public void initView() {
        fragment01 = (Fragment_detail_fragment01) getSupportFragmentManager().findFragmentById(R.id.detail_frament1);
        fragment02 = (Fragment_detail_fragment02) getSupportFragmentManager().findFragmentById(R.id.detail_frament2);
        fragment03 = (Fragment_detail_fragment03) getSupportFragmentManager().findFragmentById(R.id.detail_frament3);
        fragment04 = (Fragment_detail_fragment04) getSupportFragmentManager().findFragmentById(R.id.detail_frament4);
        fragment05 = (Fragment_detail_fragment05) getSupportFragmentManager().findFragmentById(R.id.detail_frament5);
        fragment06 = (Fragment_detail_fragment06) getSupportFragmentManager().findFragmentById(R.id.detail_frament6);
    }

    @Override
    public void initData() {
        imageLoader = new ImageLoader();
        getNetWorkData();
        getGuWenData();
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }

    /***
     * 设置标题内容
     * @param resultBean
     */
    private void setTitle_Content(HouseDetailResultBean resultBean) {
        tvTitleDetail.setText(resultBean.result.resblockOneName);
        StringBuffer sb = new StringBuffer();
        sb.append(resultBean.result.totalprBegin)
                .append("-")
                .append(resultBean.result.totalprEnd)
                .append("万  ")
                .append((int) (resultBean.result.buildSize) + "平米  ")
                .append((int) resultBean.result.bedroomAmount + "室")
                .append((int) resultBean.result.parlorAmount + "厅")
                .append((int) resultBean.result.toiletAmount + "卫");
        tvDecriptionDetail.setText(sb.toString());
    }
    public void getNetWorkData(){
        if (TextUtils.isEmpty(houseOneId)){
            finish();
            return;
        }
        showProgressBarDialog();
        HttpHelper.getDetail(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                dismissProgressBarDialog();
                resultBean  = new Gson().fromJson(result,HouseDetailResultBean.class);
                if (resultBean.result != null){
                    setTitle_Content(resultBean);
                    fragment01.setResultBean(resultBean,imageLoader);
                    fragment02.setImgUrlArr(resultBean.result.imgUrlArr,true,imageLoader);
                    fragment04.setResult(resultBean.result);
                    fragment05.setApartmentImgVos(resultBean.result.apartmentImgVos,imageLoader,findViewByIdNoCast(R.id.title_Bar_detail));
                    fragment06.setResult(resultBean.result,imageLoader);
                }
            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBarDialog();
                ToastUtil.showToast(errMsg);
            }
        });
    }
    public void getGuWenData() {
        HttpHelper.getOneDetailLook(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                GuWenResultBean resultBean = new Gson().fromJson(result, GuWenResultBean.class);
                if (resultBean != null) {
                    if (resultBean.result.showArr != null && !resultBean.result.showArr.isEmpty()){
                        fragment03.setResult(resultBean.result, houseOneId, imageLoader);
                        //设置最底下的顾问信息
                        show = resultBean.result.showArr.get(0);
                        tvHuxingName.setText(show.createName);
                        tvHuxingPhone.setText(show.phone);
                        imageLoader.displayImg(show.photo,imDetailPhoto);
                    }
                }
            }

            @Override
            public void OnFailed(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });
    }
}
