package com.bwf.p1_landz.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.utils.DrawableUtils;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.ui.map.BaiduMapActivity;
import com.bwf.p1_landz.ui.onevilla.OneBuildingActivity;
import com.bwf.p1_landz.ui.onlinevilla.OnlineBuildActivity;
import com.bwf.p1_landz.ui.search.SearchActivity;
import com.bwf.p1_landz.ui.studyvilla.StudyActivity;
import com.bwf.p1_landz.uitl.AssetsUtil;


public class MainActivity extends BaseActivity {
    private TextView tv_main_online;//在售豪宅
    private TextView tv_main_seebuild;//楼盘鉴赏
    private TextView tv_main_wait_rent;//待租豪宅
    private TextView tv_main_onehouse;//一手豪宅
    private TextView tv_main_map;//地图找房
    private TextView tv_main_study;//豪宅研究
    private TextView tv_main_man;//豪宅顾问
    private TextView tv_main_myhouse;//我的豪宅
    private EditText et_main_search;//豪宅搜索
    private TextView tv_map;
    private LinearLayout ll_online_house,ll_wait_rent_luxuryhouse,ll_house_appreciate,
            ll_new_luxuryhouse;
    private RelativeLayout ll_map_find_house,ll_luxuryhouse_research,ll_luxuryhouse_consultant
            ,ll_my_luxuryhouse;
    private ViewGroup[] ll_ids ;

    private TextView[] textViews;
    private Integer[] normal_ids = new Integer[]{R.mipmap.main_online_normal, R.mipmap.main_seebuild_normal
            , R.mipmap.main_wait_rent_normal, R.mipmap.main_onehouse_normal,R.mipmap.main_map_normal,
            R.mipmap.main_study_normal,R.mipmap.main_man_normal,R.mipmap.main_myhouse_normal};
    private Integer[] select_ids = new Integer[]{R.mipmap.main_online, R.mipmap.main_seebuild
            , R.mipmap.main_wait_rent, R.mipmap.main_onehouse,R.mipmap.main_map,
            R.mipmap.main_study,R.mipmap.main_man,R.mipmap.main_myhouse};

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforInitView() {

    }
    @Override
    public void initView() {
        //图标
        tv_main_online = findViewByIdNoCast(R.id.tv_main_online);
        tv_main_seebuild = findViewByIdNoCast(R.id.tv_main_seebuild);
        tv_main_wait_rent = findViewByIdNoCast(R.id.tv_main_wait_rent);
        tv_main_onehouse = findViewByIdNoCast(R.id.tv_main_onehouse);
        tv_main_map = findViewByIdNoCast(R.id.tv_main_map);
        tv_main_study = findViewByIdNoCast(R.id.tv_main_study);
        tv_main_man = findViewByIdNoCast(R.id.tv_main_man);
        tv_main_myhouse = findViewByIdNoCast(R.id.tv_main_myhouse);
        //搜索
        et_main_search = findViewByIdNoCast(R.id.et_main_search);
        tv_map = findViewByIdNoCast(R.id.tv_map);
        //布局初始化
        ll_online_house = findViewByIdNoCast(R.id.ll_online_house);
        ll_wait_rent_luxuryhouse = findViewByIdNoCast(R.id.ll_wait_rent_luxuryhouse);
        ll_house_appreciate = findViewByIdNoCast(R.id.ll_house_appreciate);
        ll_new_luxuryhouse = findViewByIdNoCast(R.id.ll_new_luxuryhouse);
        ll_map_find_house = findViewByIdNoCast(R.id.ll_map_find_house);
        ll_luxuryhouse_research = findViewByIdNoCast(R.id.ll_luxuryhouse_research);
        ll_luxuryhouse_consultant = findViewByIdNoCast(R.id.ll_luxuryhouse_consultant);
        ll_my_luxuryhouse = findViewByIdNoCast(R.id.ll_my_luxuryhouse);
    }

    @Override
    public void initData() {

        textViews = new TextView[]{tv_main_online,tv_main_seebuild,tv_main_wait_rent,tv_main_onehouse,
                tv_main_map,tv_main_study,tv_main_man,tv_main_myhouse};
        ll_ids = new ViewGroup[]{ll_online_house,ll_wait_rent_luxuryhouse,ll_house_appreciate,
                ll_new_luxuryhouse,ll_map_find_house,ll_luxuryhouse_research,ll_luxuryhouse_consultant
                ,ll_my_luxuryhouse};
        //设置监听
        setOnClick(ll_ids);
        setOnClick(tv_map);
        setOnClick(et_main_search);
    }

    @Override
    public void afterInitView() {
        //解析并保存在线豪宅的样式数据
        AssetsUtil.getOnlineTypeData(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_online_house://在售豪宅
                setSelect(0);
                IntentUtils.openActivity(MainActivity.this,OnlineBuildActivity.class);
                break;
            case R.id.ll_wait_rent_luxuryhouse://楼盘鉴赏
                setSelect(2);
                break;
            case R.id.ll_house_appreciate://待租豪宅
                setSelect(1);
                break;
            case R.id.ll_new_luxuryhouse://一手豪宅
                setSelect(3);
                IntentUtils.openActivity(MainActivity.this, OneBuildingActivity.class);
                break;
            case R.id.ll_map_find_house://地图找房
                setSelect(4);
                break;
            case R.id.ll_luxuryhouse_research://豪宅研究
                setSelect(5);
                IntentUtils.openActivity(MainActivity.this, StudyActivity.class);
                break;
            case R.id.ll_luxuryhouse_consultant://豪宅顾问
                setSelect(6);
                break;
            case R.id.ll_my_luxuryhouse://我的豪宅
                setSelect(7);
                break;
            case R.id.et_main_search://豪宅搜索
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("intent_type",5);
                startActivity(intent);
                break;
            case R.id.tv_map:
                Intent intent1 = new Intent(MainActivity.this, BaiduMapActivity.class);
                startActivity(intent1);
        }
    }

    /**
     * 设置点击的效果
     * @param position
     */
    public void setSelect(int position){
        for (int i = 0;i< textViews.length;i++){
                          if(i == position){
                    textViews[i].setTextColor(Color.parseColor("#fff0cb7e"));//设置选中的颜色
                    //工具类设置  选中的图片
                    DrawableUtils.drawableTop(MainActivity.this , textViews[i] , select_ids[i]);
                }else{
                    textViews[i].setTextColor(Color.WHITE);//设置没选中的颜色
                    //工具类设置  没选中的图片
                    DrawableUtils.drawableTop(MainActivity.this , textViews[i] , normal_ids[i]);

            }
        }
    }

    private static final int TIEMS = 2000;

    private boolean isBack = true;
    /**
     * 监听back 再2s内再次点击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){//按下返回键
            if(isBack){//第一次进来
                ToastUtil.showToast("再次点击退出");
                isBack = false;
                //发一个延迟消息 通知handler 2s之后改变isBack的值
                mHandler.sendEmptyMessageDelayed(10001,TIEMS);
            }else{
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 10001:
                    isBack = true;//回到true 需要再点击两次才能退出
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

}
