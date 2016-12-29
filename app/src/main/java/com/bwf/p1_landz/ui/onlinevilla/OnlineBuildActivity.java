package com.bwf.p1_landz.ui.onlinevilla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;
import com.bwf.p1_landz.entity.OnLineHouseResult;
import com.bwf.p1_landz.entity.OnLineHouseResultBean;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.request.OnLineHouseRequest;
import com.bwf.p1_landz.ui.onevilla.OneHouseDetailActivity;
import com.bwf.p1_landz.ui.search.SearchActivity;
import com.bwf.p1_landz.view.RefreshListView;
import com.bwf.p1_landz.view.TitlePopupWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OnlineBuildActivity extends BaseActivity{
    private RefreshListView lv_online_house;
    private RelativeLayout  rl_no_house;
    //设置文字
    private TextView tv_location, tv_price, tv_room, tv_type, tv_more;
    private RelativeLayout rl_location, rl_price, rl_room, rl_type, rl_more;
    //选择筛选的数据
    private OnlineTypeBean onlineTypeBean;
    // 地区、价格、居室、类型
    private List<ParamListBean> location_paramList,price_paramList,room_paramList,type_paramList;
    private OnLineHouseAdapter onLineHouseAdapter;
    private List<Object> totalList;
    private OnLineTypeResultBean onLineTypeResultBean;
    //保存筛选请求的内容
    private OnLineHouseRequest onLineHouseRequest;
    private EditText et_two_search;
    private Button compare_add_btn,compare_btn;
    private boolean tag = true;
    @Override
    public int getContentViewId() {
        return R.layout.activity_on_line_house;
    }

    @Override
    public void beforInitView() {
        //获得筛选的数据
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        //进行分类
        if(onlineTypeBean != null){
            for(OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result){
                if(onLineTypeResultBean.paramType.equals("1001")){//地区
                    location_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1008")){//价格
                    price_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1005")){//居室
                    room_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1006")){//类型
                    type_paramList = onLineTypeResultBean.paramList;
                }
            }
        }
    }

    @Override
    public void initView() {
        tv_location = findViewByIdNoCast(R.id.tv_location);
        tv_price = findViewByIdNoCast(R.id.tv_price);
        tv_room = findViewByIdNoCast(R.id.tv_room);
        tv_type = findViewByIdNoCast(R.id.tv_type);
        tv_more = findViewByIdNoCast(R.id.tv_more);
        rl_location = findViewByIdNoCast(R.id.rl_location);
        rl_price = findViewByIdNoCast(R.id.rl_price);
        rl_room = findViewByIdNoCast(R.id.rl_room);
        rl_type = findViewByIdNoCast(R.id.rl_type);
        rl_more = findViewByIdNoCast(R.id.rl_more);
        et_two_search = findViewByIdNoCast(R.id.et_two_search);
        //列表View
        lv_online_house = findViewByIdNoCast(R.id.lv_online_house);

        //没有数据页面
        rl_no_house = findViewByIdNoCast(R.id.rl_no_house);
        compare_btn = findViewByIdNoCast(R.id.compare_btn);
        compare_add_btn = findViewByIdNoCast(R.id.compare_add_btn);
    }

    @Override
    public void initData() {
        //设置监听
        setOnClick(rl_location, rl_price, rl_room, rl_type, rl_more,et_two_search);
        setOnClick(compare_add_btn,compare_btn);
        onLineHouseRequest = new OnLineHouseRequest();
        totalList = new ArrayList<>();
        onLineHouseAdapter = new OnLineHouseAdapter(this);
        lv_online_house.setAdapter(onLineHouseAdapter);
        lv_online_house.setRefreh_ListViewListener(new RefreshListView.Refreh_ListViewListener() {

            //下拉刷新
            @Override
            public void onRefresh() {
                //请求参数的起始值设置为默认初始值
                onLineHouseRequest.pageNo = 0;
                getNetWorkData();
            }

            //加载更多
            @Override
            public void onLoadMore() {
                //请求参数的起始值设置为+1
                onLineHouseRequest.pageNo++;
                getNetWorkData();
            }
        });
        onLineTypeResultBean = new OnLineTypeResultBean();
        //item 跳转
        lv_online_house.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转要看是跳转到一手房源详情还是二手房源详情
                //headerView也占据一格
                if(onLineHouseAdapter.getItem(i-1) instanceof HouseOneArrBean){
                    HouseOneArrBean houseOneArrBean = (HouseOneArrBean) onLineHouseAdapter.getItem(i-1);
                    Bundle bundle  = new Bundle();
                    bundle.putString("houseOneId",houseOneArrBean.houseOneId);
                    bundle.putString("resblockOneId",houseOneArrBean.resblockOneId);
                    IntentUtils.openActivity(OnlineBuildActivity.this,OneHouseDetailActivity.class,
                            bundle);
                }else{
                    HouseArrBean houseArrBean = (HouseArrBean) onLineHouseAdapter.getItem(i-1);
                }
            }
        });
    }

    @Override
    public void afterInitView() {
        onLineHouseRequest.pageNo = 0;
        getNetWorkData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_location://区域
                currentPost = 0;
                TitlePopupWindow titlePopupWindow = new TitlePopupWindow(this,false);
                titlePopupWindow.setParamListBean(location_paramList,pTsCallBack);//设置数据
                titlePopupWindow.showAsDropDown(rl_location);
                break;
            case R.id.rl_price://价格
                currentPost = 1;
                TitlePopupWindow titlePopupWindow1 = new TitlePopupWindow(this,true);
                titlePopupWindow1.setParamListBean(price_paramList,pTsCallBack);//设置数据
                //设置自定义View
                titlePopupWindow1.setPrice_zidingyuView(this,pTsCallBack);
                titlePopupWindow1.showAsDropDown(rl_location);
                break;
            case R.id.rl_room://
                currentPost = 2;
                TitlePopupWindow titlePopupWindow2 = new TitlePopupWindow(this,true);
                titlePopupWindow2.setParamListBean(room_paramList,pTsCallBack);//设置数据
                titlePopupWindow2.showAsDropDown(rl_location);
                break;
            case R.id.rl_type://
                currentPost = 3;
                TitlePopupWindow titlePopupWindow3 = new TitlePopupWindow(this,true);
                titlePopupWindow3.setParamListBean(type_paramList,pTsCallBack);//设置数据
                titlePopupWindow3.showAsDropDown(rl_location);
                break;
            case R.id.rl_more://更多
                Intent intent = new Intent(OnlineBuildActivity.this,SelectMoreActivity.class);
                intent.putExtra("OnLineHouseRequest",onLineHouseRequest);
                startActivityForResult(intent,1);
                break;
            case R.id.et_two_search:
                Intent intent1 = new Intent(OnlineBuildActivity.this, SearchActivity.class);
                intent1.putExtra("intent_type",3);
                startActivity(intent1);
                break;
            case R.id.compare_add_btn:

                if (tag) {
                    tag = false;
                    compare_add_btn.setBackgroundResource(R.mipmap.compare_cancel_btn);
                    compare_btn.setClickable(true);
                    onLineHouseAdapter.setCheckedTextViewVisble(true);
                } else {
                    compare_btn.setClickable(false);
                    tag = true;
                    compare_add_btn.setBackgroundResource(R.mipmap.compare_add_btn);
                    onLineHouseAdapter.setCheckedTextViewVisble(false);
                }
                onLineHouseAdapter.notifyDataSetChanged();
                break;
            case R.id.compare_btn:
                List<Object> objectLists = onLineHouseAdapter.getTotalList();

                OnLineHouseResultBean resultBeannew = new OnLineHouseResultBean();
                List<HouseArrBean> houseArr = new ArrayList<>();
                List<HouseOneArrBean> houseOneArr = new ArrayList<>();
                //提取出选中的对象
                for (Object obj : objectLists) {
                    if (obj instanceof HouseOneArrBean) {
                        HouseOneArrBean houseOneArrBean = (HouseOneArrBean) obj;
                        if (houseOneArrBean.isSelect) {
                            houseOneArr.add(houseOneArrBean);
                        }
                    }
                    if (obj instanceof HouseArrBean) {
                        HouseArrBean houseArrBean = (HouseArrBean) obj;
                        if (houseArrBean.isSelect) {
                            houseArr.add(houseArrBean);
                        }
                    }
                }
                resultBeannew.houseArr = houseArr;
                resultBeannew.houseOneArr = houseOneArr;

                Bundle bundle = new Bundle();
                bundle.putParcelable("resultBeannew",resultBeannew);
                IntentUtils.openActivity(this, CompareActivity.class, bundle);
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
                    tv_location.setText(""+paramListBean.name);
                    onLineHouseRequest.circleTypeCode = paramListBean.key;
                    break;
                case 1://价格
                    tv_price.setText(""+paramListBean.name);
                    onLineHouseRequest.totalPricesBegin = paramListBean.minValue;
                    onLineHouseRequest.totalPricesEnd = paramListBean.maxValue;
                    break;
                case 2://居室
                    tv_room.setText(""+paramListBean.name);
                    onLineHouseRequest.bedroomAmount = paramListBean.value;
                    break;
                case 3://类型
                    tv_type.setText(""+paramListBean.name);
                    onLineHouseRequest.buildingType = paramListBean.name;
                    break;

            }
            onLineHouseRequest.pageNo = 0;
            getNetWorkData();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            onLineHouseRequest = (OnLineHouseRequest) data.getSerializableExtra("OnLineHouseRequest");
            //调用网络请求方法
            onLineHouseRequest.pageNo = 0;
            getNetWorkData();
        }
    }
    public void getNetWorkData(){
        showProgressBarDialog();
        //调用网络请求的方法
        HttpHelper.getOnLineHouseList(this, onLineHouseRequest, new HttpRequestAsyncTask.CallBack(){
            @Override
            public void OnSuccess(String result) {
                dismissProgressBarDialog();
                //解析数据
                OnLineHouseResult resultBean = new Gson().fromJson(result,OnLineHouseResult.class);
                //把两种不同类型的数据合成一个集合对象，方便适配器使用
                totalList = resultBean.initListData();
                //适配器添加数据  不是替换数据
                if(onLineHouseRequest.pageNo == 0){//第一次加载或者是下拉刷新
                    onLineHouseAdapter.setTotalList(totalList);
                }else{
                    onLineHouseAdapter.addTotalList(totalList);
                }
                onLineHouseAdapter.notifyDataSetChanged();

                //加载数据刷新适配器之后，只要发现适配器里面没有数据 就显示暂无数据页面
                if(onLineHouseAdapter.getCount() == 0){
                    rl_no_house.setVisibility(View.VISIBLE);
                }else{
                    rl_no_house.setVisibility(View.GONE);
                }
                //如果是适配器里面有数据，且在上拉加载更多的时候，发现加载不到新的数据
                if((totalList == null ||  totalList.size() == 0) &&onLineHouseAdapter.getCount() > 0){
                    ToastUtil.showToast("没有更多的数据了");
                }
                //刷新完成之后要调用完成的方法
                lv_online_house.setOnComplete();
            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBarDialog();
                ToastUtil.showToast(errMsg);
                if(onLineHouseAdapter.getCount() == 0){
                    rl_no_house.setVisibility(View.VISIBLE);
                }else{
                    rl_no_house.setVisibility(View.GONE);
                }
                //刷新完成之后要调用完成的方法
                lv_online_house.setOnComplete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(int i = 0;i< onlineTypeBean.result.size();i++){
            List<ParamListBean> paramList = onlineTypeBean.result.get(i).paramList;
            for (int j = 0;j < paramList.size();j++){
                paramList.get(j).isSelect = false;
                if(paramList.get(j).childList != null){
                    for( ParamListBean bean :paramList.get(j).childList){
                        bean.isSelect = false;
                    }
                }
            }
        }
    }
}
