package com.bwf.p1_landz.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */
public  class OnLineTypeResultBean{
    public String paramType;
    public List<ParamListBean> paramList;

    @Override
    public String toString() {
        return "OnLineTypeResultBean{" +
                "paramType='" + paramType + '\'' +
                ", paramList=" + paramList +
                '}';
    }
}
