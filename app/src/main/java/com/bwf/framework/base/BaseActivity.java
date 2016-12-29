package com.bwf.framework.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bwf.framework.tools.AppManager;
import com.bwf.p1_landz.R;

/**
 * Created by Cao_Ye on 2016/11/28.
 *  Activity的基本类
 */
public abstract  class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    public Dialog dialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setTranslucentStatus();
        //添加该Activity到栈里面
        AppManager.getInstance().addActivity(this);
        beforInitView();
        initView();
        initData();
        afterInitView();
    }

    /**
     * //获得LayoutId
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 初始化View之前的内容
     */
    public abstract void beforInitView();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化之后的执行
     */
    public abstract void afterInitView();


    /**
     * 不用强制转换的findViewById
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id){
        return (T)findViewById(id);
    }

    /**
     * 设置所有需要注册的监听
     * @param ids
     */
    public void setOnClick(int... ids){
        for (int id : ids){
            findViewById(id).setOnClickListener(this);
        }
    }

    /**
     * 设置所有需要注册的监听
     * @param views
     */
    public void setOnClick(View... views){
        for (View v : views){
            v.setOnClickListener(this);
        }
    }

    /**
     * 显示进度条
     */
    public void showProgressBarDialog(){
        if(dialog == null){
            //没有标题的dialog
            dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        }
        View view = View.inflate(this, R.layout.dialog_progressbar,null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 关闭进度条
     */
    public void dismissProgressBarDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 销毁该Activity的时候  把该Activity剔除出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }
}
