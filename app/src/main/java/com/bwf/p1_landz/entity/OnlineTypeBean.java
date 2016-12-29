package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cao_Ye on 2016/11/30.
 * 获取筛选豪宅类型对象
 */
public class OnlineTypeBean extends BaseBean{

    public List<OnLineTypeResultBean> result;

    @Override
    public String toString() {
        return "OnlineTypeBean{" +
                "result=" + result +
                '}';
    }
}

