package com.bwf.framework.share;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Cao_Ye on 2016/11/28.
 */
public class PrefrenceWrapper {

    private SharedPreferences sharedPreferences;

    private static final String SP_NAME = "share_landz";

    public PrefrenceWrapper(Context mContext){
        sharedPreferences = mContext.getSharedPreferences(SP_NAME , Context.MODE_PRIVATE);
    }

    public void putString(String key,String value){
        sharedPreferences.edit().putString(key,value).commit();
    }

    public String getString(String key){
        return  sharedPreferences.getString(key , "");
    }

    public String getString(String key,String defalutValue){
        return  sharedPreferences.getString(key , defalutValue);
    }


    public void putBoolean(String key,boolean value){
        sharedPreferences.edit().putBoolean(key,value).commit();
    }


    public boolean getBoolean(String key){
        return  sharedPreferences.getBoolean(key,false);
    }

    public boolean getBoolean(String key,boolean value){
        return  sharedPreferences.getBoolean(key,value);
    }

    public void putInteger(String key,int value){
        sharedPreferences.edit().putInt(key,value).commit();
    }


    public int getInteger(String key){
        return  sharedPreferences.getInt(key,0);
    }

    public int getInteger(String key,int value){
        return  sharedPreferences.getInt(key,value);
    }

    /**
     * 清除shareprefrence的内容
     */
    public void clear(){
        sharedPreferences.edit().clear();
    }
}
