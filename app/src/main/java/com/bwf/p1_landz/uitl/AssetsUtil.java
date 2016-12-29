package com.bwf.p1_landz.uitl;

import android.content.Context;
import android.content.res.AssetManager;

import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Cao_Ye on 2016/11/30.
 */
public class AssetsUtil {

    /**
     * 获得Assets里面的在线豪宅的样式内容  存在MyApplliction里面
     * @param context
     */
    public static void  getOnlineTypeData(final Context context){

            //耗时操作 使用子线程
                 new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = context.getAssets();
                try {
                    InputStream in = assetManager.open("study_type.txt");
                    //转换成字符串
                    byte[] bytes = new byte[in.available()];//in.available()  获得输入流的长度
                    in.read(bytes);
                    String result = new String(bytes,"utf-8");//把流转换成字符串
                    //解析
                    OnlineTypeBean  onlineTypeBean = new Gson().fromJson(result,OnlineTypeBean.class);
                    //保存到MyApplication里面
                    MyApplication.getApplication().setOnlineTypeBean(onlineTypeBean);
                    LogUtils.e("msg",onlineTypeBean.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
