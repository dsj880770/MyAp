package com.bwf.p1_landz.ui.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.SearchKeyWordBean;
import com.google.gson.Gson;

/**
 * Created by Cao_Ye on 2016/11/30.
 * 搜索房源
 */
public class SearchActivity extends BaseActivity {
    /**
     * type=1 地图过来的 type=2,二手楼盘过来的 type=3为买卖 type=4 租赁列表过来 type=5 首页过来
     * type=6 从一手豪宅过来的
     */
    private int type;
    private EditText et_search;
    private ListView lv_think_data;
    private SearchKeyWordAdapter adapter;
    private SearchKeyWordBean bean;


    @Override
    public int getContentViewId() {
        return R.layout.search_activity;
    }

    @Override
    public void beforInitView() {
        type = getIntent().getIntExtra("intent_type",0);
    }

    @Override
    public void initView() {
        et_search = findViewByIdNoCast(R.id.et_search);
        lv_think_data = findViewByIdNoCast(R.id.lv_history_data);
    }

    @Override
    public void initData() {
        setEditTextHit();
        adapter = new SearchKeyWordAdapter(this);
        lv_think_data.setAdapter(adapter);
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }
    private void setEditTextHit() {
        switch (type) {
            case 1:
            case 2:
                et_search.setHint("请输入楼盘或商圈名称...");
                break;
            case 3:
                et_search.setHint("请输入房源或商圈名称...");
                break;
            case 4:
                et_search.setHint("请输入房源名称...");
                break;
            case 5:
                et_search.setHint("请输入楼盘名称或房源特征...");
                break;
            case 6:
                et_search.setHint("请输入楼盘名称...");
                break;
        }
    }
    int count = 0;
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (type == 1) {
                //地图
            } else if (type == 2 || type == 5) {
                count = 0;

            } else if (type == 3) {
                count = 1;
            } else if (type == 4) {
                count = 2;
            }
            String content = et_search.getText().toString().trim();
            if(!TextUtils.isEmpty(content)){
                HttpHelper.getResblockListByKeyWord(SearchActivity.this,et_search.getText().toString(),
                        count,callBack);
            }else{
                bean.result.clear();
                adapter.setData(bean.result);
                adapter.notifyDataSetChanged();
            }

        }

    };

    private HttpRequestAsyncTask.CallBack callBack = new HttpRequestAsyncTask.CallBack() {
        @Override
        public void OnSuccess(String result) {
            bean = new Gson().fromJson(result, SearchKeyWordBean.class);
            if(bean.result !=null){
                adapter.setData(bean.result);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void OnFailed(String errMsg) {
            ToastUtil.showToast(errMsg);
        }
    };
}
