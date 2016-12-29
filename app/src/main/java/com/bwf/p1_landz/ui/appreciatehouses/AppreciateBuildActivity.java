package com.bwf.p1_landz.ui.appreciatehouses;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.R;

/**
 * Created by Cao_Ye on 2016/11/30.
 * 楼盘鉴赏
 */
public class AppreciateBuildActivity extends BaseActivity{
    private EditText et_two_search;
    private TextView tv_two_map;
    private RelativeLayout appreciate_rl_location,appreciate_rl_price,
            appreciate_rl_room,appreciate_rl_type,appreciate_rl_more;
    @Override
    public int getContentViewId() {
        return R.layout.activity_appreciate;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        et_two_search = findViewByIdNoCast(R.id.et_two_search);
        tv_two_map = findViewByIdNoCast(R.id.tv_two_map);
        appreciate_rl_location = findViewByIdNoCast(R.id.appreciate_rl_location);
        appreciate_rl_price = findViewByIdNoCast(R.id.appreciate_rl_price);
        appreciate_rl_room = findViewByIdNoCast(R.id.appreciate_rl_room);
        appreciate_rl_type = findViewByIdNoCast(R.id.appreciate_rl_type);
        appreciate_rl_more = findViewByIdNoCast(R.id.appreciate_rl_more);
    }

    @Override
    public void initData() {
        setOnClick(appreciate_rl_location,appreciate_rl_price,
                appreciate_rl_room,appreciate_rl_type,appreciate_rl_more);
        setOnClick(et_two_search,tv_two_map);
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }
}
