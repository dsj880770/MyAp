package com.bwf.p1_landz.ui.studyvilla;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.entity.StudyResultBean;
import com.bwf.p1_landz.ui.search.SearchActivity;
import com.bwf.p1_landz.view.TitlePopupWindow;
import com.bwf.p1_landz.view.test.RefreshListView_Bwf;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cao_Ye on 2016/11/30.
 * 豪宅研究
 */
public class StudyActivity extends BaseActivity {
    private OnlineTypeBean onlineTypeBean;
    private List<ParamListBean> studyreportlist;
    private RefreshListView_Bwf refresh_ListView;
    private TextView study_title_text;
    @Override
    public int getContentViewId() {
        return R.layout.house_study;
    }
    private RelativeLayout study_title_rl,rl_no_house;
    private ImageView study_finish,study_search;
    private StudyResultBean studyResultBean;
    private StudyAdapter studyAdapter;
    @Override
    public void beforInitView() {
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        for (OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result){
            if(onLineTypeResultBean.paramType.equals("1009")){//地区
                studyreportlist = onLineTypeResultBean.paramList;
            }
        }
    }

    @Override
    public void initView() {
        study_title_rl = findViewByIdNoCast(R.id.study_title_rl);
        study_finish = findViewByIdNoCast(R.id.study_finish);
        study_search = findViewByIdNoCast(R.id.study_search);
        rl_no_house = findViewByIdNoCast(R.id.rl_no_house);
        study_title_text = findViewByIdNoCast(R.id.study_title_text);
        refresh_ListView = findViewByIdNoCast(R.id.seacher_list);
    }

    @Override
    public void initData() {
        setOnClick(study_title_rl,study_finish,study_search);
        studyAdapter = new StudyAdapter();
        refresh_ListView.setAdapter(studyAdapter);
        refresh_ListView.setRefreh_listViewListener(new RefreshListView_Bwf.Refreh_ListViewListener() {
            @Override
            public void onRefresh() {
                getNetWorkData(null);
            }

            @Override
            public void onLoadMore() {
                getNetWorkData(null);
            }
        });

    }

    @Override
    public void afterInitView() {
        getNetWorkData(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.study_title_rl:
                TitlePopupWindow titlePopupWindow = new TitlePopupWindow(this,true);
                titlePopupWindow.setParamListBean(studyreportlist,pTsCallBack);//设置数据
                titlePopupWindow.showAsDropDown(study_title_rl);
                break;
            case R.id.study_finish:
                finish();
                break;
//            case R.id.study_search:
//                Intent intent = new Intent(StudyActivity.this, SearchActivity.class);
//                intent.putExtra("intent_type",2);
//                break;

        }
    }
    private TitlePopupWindow.PupupWindowItemClickCallBack pTsCallBack = new TitlePopupWindow.PupupWindowItemClickCallBack(){

        @Override
        public void onItemClickCallBack(ParamListBean paramListBean) {
            if(paramListBean == null){
                return;
            }
            study_title_text.setText(""+paramListBean.name);

        }
    };
    private void getNetWorkData(String reportType){
        showProgressBarDialog();
        HttpHelper.getStudy(this,reportType,new  HttpRequestAsyncTask.CallBack(){

            @Override
            public void OnSuccess(String result) {
                dismissProgressBarDialog();
                studyResultBean = new Gson().fromJson(result,StudyResultBean.class);
                if (studyResultBean.getResult().getReportList() != null){
                    studyAdapter.setDatas(studyResultBean.getResult().getReportList());
                    studyAdapter.notifyDataSetChanged();
                }
                //加载数据刷新适配器之后，只要发现适配器里面没有数据 就显示暂无数据页面
                if(studyAdapter.getCount() == 0){
                    rl_no_house.setVisibility(View.VISIBLE);
                }else{
                    rl_no_house.setVisibility(View.GONE);
                }
                //如果是适配器里面有数据，且在上拉加载更多的时候，发现加载不到新的数据
                if(studyResultBean == null &&studyAdapter.getCount() > 0){
                    ToastUtil.showToast("没有更多的数据了");
                }
                refresh_ListView.setOnComplete();
            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBarDialog();
                ToastUtil.showToast(errMsg);
                if(studyAdapter.getCount() == 0){
                    rl_no_house.setVisibility(View.VISIBLE);
                }else{
                    rl_no_house.setVisibility(View.GONE);
                }
                refresh_ListView.setOnComplete();
            }

        });
    }
    class StudyAdapter extends BaseAdapter {
        private List<StudyResultBean.ResultBean.ReportListBean> datas = new ArrayList<StudyResultBean.ResultBean.ReportListBean>();

        public void setDatas(List<StudyResultBean.ResultBean.ReportListBean> datas) {
            this.datas = datas;
        }
        public void updateDatas(List<StudyResultBean.ResultBean.ReportListBean> datas) {
            this.datas.addAll(datas);
        }
        @Override
        public int getCount() {
            if (datas != null) {
                return datas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(StudyActivity.this,R.layout.study_item, null);
            ImageView img = (ImageView) convertView.findViewById(R.id.study_top_img);
            TextView tx = (TextView) convertView.findViewById(R.id.study_top_tx);
            TextView title_tx = (TextView) convertView.findViewById(R.id.study_title_tx);
            TextView des_tx = (TextView) convertView.findViewById(R.id.study_des_tx);
            StudyResultBean.ResultBean.ReportListBean reportListBean = datas.get(position);
            if (position == 0)
            {
                tx.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
                tx.setText(reportListBean.getTitle());
                if(studyResultBean.getResult().getShowImgPath() != null){
                    new ImageLoader().displayImg(studyResultBean.getResult().getShowImgPath(), img);
                }
            }
            else
            {
                title_tx.setVisibility(View.VISIBLE);
                des_tx.setVisibility(View.VISIBLE);
                title_tx.setText(datas.get(position).getTitle());
                des_tx.setText(datas.get(position).getDescribe());
            }

            convertView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view){
                    Intent intent = new Intent(StudyActivity.this, StudyDetailActivity.class);
                    intent.putExtra("ReportListBean", datas.get(position));
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }
}
