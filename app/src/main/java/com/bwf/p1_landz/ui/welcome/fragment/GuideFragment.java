package com.bwf.p1_landz.ui.welcome.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.share.SharedHelper;
import com.bwf.framework.utils.DisplayUtil;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.ui.MainActivity;

/**
 * Created by Cao_Ye on 2016/11/29.
 */
public class GuideFragment extends BaseFragment{
    private int positon;
    private LinearLayout ll_description;
    private TextView tv_title,tv_content;
    private Button btn_tiyan;
    public static GuideFragment newInstance(int positon){
        GuideFragment guideFragment  = new GuideFragment();
        guideFragment.positon = positon;
        return  guideFragment;
    }
    @Override
    public int getResourceId() {
        return R.layout.fragment_guide;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        ll_description = findViewByIdNoCast(R.id.ll_description);
        tv_title = findViewByIdNoCast(R.id.tv_title);
        tv_content = findViewByIdNoCast(R.id.tv_content);
        btn_tiyan = findViewByIdNoCast(R.id.btn_tiyan);
    }

    @Override
    public void initData() {
        //设置文字的边框的宽度
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll_description.getLayoutParams();
        params.width = DisplayUtil.getDensity_Width(getContext());//设置成屏幕的宽度
        ll_description.setLayoutParams(params);
        switch (positon){
            case 0:
                tv_title.setText(getString(R.string.splash_tip1));
                tv_content.setText(getString(R.string.splash_tip01));
                break;
            case 1:
                tv_title.setText(getString(R.string.splash_tip2));
                tv_content.setText(getString(R.string.splash_tip02));
                break;
            case 2:
                tv_title.setText(getString(R.string.splash_tip3));
                tv_content.setText(getString(R.string.splash_tip03));
                btn_tiyan.setVisibility(View.VISIBLE);
                //延时操作  因为这个时候Button没有宽度
                btn_tiyan.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //设置体验Button的位置
                        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) btn_tiyan.getLayoutParams();
                        //设置成屏幕的宽度减去自己的宽度除以2
                        params1.leftMargin = (DisplayUtil.getDensity_Width(getContext())-btn_tiyan.getWidth())/2;
                        btn_tiyan.setLayoutParams(params1);
                    }
                },500);
                break;
        }
    }

    @Override
    public void afterInitView() {
        //设置体验的监听
        setOnClick(R.id.btn_tiyan);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tiyan://跳转到首页
                //设置已经进入过引导页面啦
                SharedHelper.getInstance(getContext()).setIsFirst(false);
                IntentUtils.openActivity(getContext(), MainActivity.class);
                getActivity().finish();
            break;
        }
    }
}
