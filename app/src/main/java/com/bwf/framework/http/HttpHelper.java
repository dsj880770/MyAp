package com.bwf.framework.http;

import android.app.Activity;


import com.bwf.framework.utils.UrlUtils;
import com.bwf.p1_landz.request.OnLineHouseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cao_ye on 2016/7/13 0013.
 * Description:
 */
public class HttpHelper {

    public static void login(Activity activity, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "landz");
        map.put("password", "123456");
        Request request = new Request("http://baidu.com", Request.Method.POST, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }
    public static void getOnLineHouseList(Activity activity, OnLineHouseRequest requestBean, HttpRequestAsyncTask.CallBack callBack) {

        Request request = new Request(UrlUtils.ONLINE_HOUSE, Request.Method.POST, requestBean == null ? null : requestBean.getRequestMap());
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }
    public static void getStudy(Activity activity, String reportType, HttpRequestAsyncTask.CallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("pageNo",0+"");
        map.put("pageSize",10+"");
        if(reportType != null){
            map.put("reportType",reportType);
        }
        Request request = new Request(UrlUtils.STUDY, Request.Method.POST, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }
    public static void getResblockListByKeyWord(Activity activity, String keyWords,int type, HttpRequestAsyncTask.CallBack callBack) {
        //请求数据转换成map集合 供网络框架使用
        Map<String,String> map = new HashMap<String, String>();
        map.put("keyWords", keyWords);
        map.put("type", type+"");
        Request request = new Request(UrlUtils.SEARCH_URL, Request.Method.POST, map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestAsyncTask.execute(request);
    }
    /**
     * 一手房源详情
     * @param activity
     * @param houseOneId
     * @param callBack
     */
    public static void getDetail(Activity activity, String houseOneId, HttpRequestAsyncTask.CallBack callBack){
        Map<String,String> map = new HashMap<>();
        map.put("houseOneId",houseOneId);
        Request request = new Request(UrlUtils.HOUSE_DETAIL, Request.Method.POST,map);
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(activity,callBack);
        httpRequestAsyncTask.execute(request);
    }

    /**
     * 一手房看房记录_本房顾问列表
     *
     * @param houseOneId
     */
    public static void getOneDetailLook(Activity activity, String houseOneId, HttpRequestAsyncTask.CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseOneId", houseOneId);
        Request request = new Request(UrlUtils.HOUSE_DETAIL_LOOK, Request.Method.GET, map);
        HttpRequestAsyncTask httpRequestTask = new HttpRequestAsyncTask(activity, callBack);
        httpRequestTask.execute(request);
    }
}
