package com.bwf.p1_landz.ui.studyvilla;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.StudyResultBean;

/**
 * Created by Cao_Ye on 2016/12/8.
 */
public class StudyDetailActivity extends BaseActivity{
    private TextView title_tx;
    private ImageView im_shoucang;
    private WebView webView;
    private StudyResultBean.ResultBean.ReportListBean reportListBean;
    @Override
    public int getContentViewId() {
        return R.layout.study_detail;
    }

    @Override
    public void beforInitView() {
        reportListBean = (StudyResultBean.ResultBean.ReportListBean) this.getIntent().getSerializableExtra("ReportListBean");

    }

    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.webView);
        title_tx = (TextView) findViewById(R.id.tv_one_map);
        im_shoucang = (ImageView) findViewById(R.id.im_shoucang);

    }

    @Override
    public void initData() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setBuiltInZoomControls(true); // 页面添加缩放按钮
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
        webSetting.setUseWideViewPort(true);// 设置此属性，可任意比例缩放。
        webSetting.setLoadWithOverviewMode(true);// 可以使之充满全屏

        if (reportListBean != null) {
            title_tx.setText(reportListBean.getTitle());
            webView.loadUrl(reportListBean.getWordPath());
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgressBarDialog();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress >= 50) {
                    // 网页加载完成
                    dismissProgressBarDialog();
                }

            }
        });
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();// 返回上一页面
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
