package com.bwf.framework.share;

import android.content.Context;

/**
 * Created by Cao_Ye on 2016/11/28.
 */
public class SharedHelper extends PrefrenceWrapper{

    private static SharedHelper sharedHelper;
    private SharedHelper(Context context){
        super(context);
    }

    public static SharedHelper getInstance(Context mContext){
        if( sharedHelper == null){
            sharedHelper = new SharedHelper(mContext);
        }
        return  sharedHelper;
    }

    public void setIsFirst(boolean isFirst){
        putBoolean("isFirst",isFirst);
    }

    public boolean getIsFirst(){
        return getBoolean("isFirst",true);
    }

    public void setUserName(String userName){
        putString("userName",userName);
    }
}
