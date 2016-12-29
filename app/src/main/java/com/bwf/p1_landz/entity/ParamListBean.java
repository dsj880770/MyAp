package com.bwf.p1_landz.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */
public class ParamListBean implements Serializable {
    public String key;
    public String name;
    public String value;
    public String minValue;
    public String maxValue;
    public List<ParamListBean> childList;
    public boolean isSelect;
    public ParamListBean() {
    }
    public ParamListBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ParamListBean(String name, String value, boolean isSelect) {
        this.name = name;
        this.value = value;
        this.isSelect = isSelect;
    }

    public ParamListBean(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public ParamListBean(String name, boolean isSelect, List<ParamListBean> childList) {
        this.name = name;
        this.isSelect = isSelect;
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "ParamListBean{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", childList=" + childList +
                '}';
    }
}
