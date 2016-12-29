package com.bwf.p1_landz.ui.welcome;

import android.view.View;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.share.SharedHelper;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.ui.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cao_Ye on 2016/11/29.
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity{

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void afterInitView() {
        //判断延迟跳转到引导页面或者是首页
        //使用Handler 或者Timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //判断是否是第一次进入   如果为true 则是第一次进入  跳转到引导页面
                if(SharedHelper.getInstance(WelcomeActivity.this).getIsFirst()){
                    IntentUtils.openActivity(WelcomeActivity.this,GuidActivity.class);
                }else{
                    //跳转到首页
                    IntentUtils.openActivity(WelcomeActivity.this,MainActivity.class);
                }
                finish();
            }
        };
        timer.schedule(task , 2000);//延迟两秒值之后执行
    }

    @Override
    public void onClick(View view) {

    }
}
