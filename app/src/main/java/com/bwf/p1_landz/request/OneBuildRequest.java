package com.bwf.p1_landz.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */

public class OneBuildRequest {
    public String resblockOneId;
    /** 城区ID */
    public String districtId;
    /** 商圈ID */
    public String circleTypeCode;
    /** 均价开始 */
    public double avergPriceBegin = -1;
    /** 均价结束 */
    public double avergPriceEnd = -1;
    /**
     * 排序规则:1均价从高到低（降序） 2均价从低到高（升序）
     */
    public int avergPriceSort = -1;
    /** 是否优惠:2 不优惠 1优惠 */
    public int isFavorable = -1;
    public int pageNo = 0;
    public int pageSize = 10;
    /** 经度 */
    public double longitude;
    /** 纬度 */
    public double latitude;
    public Map<String,String> getRequest(){
        Map<String,String> map = new HashMap<>();
        map.put("pageNo",""+pageNo);
        map.put("pageSize",""+pageSize);
        if (!TextUtils.isEmpty(resblockOneId))
            map.put("resblockOneId",""+resblockOneId);
        if (!TextUtils.isEmpty(districtId))
            map.put("districtId",""+districtId);
        if (!TextUtils.isEmpty(circleTypeCode))
            map.put("circleTypeCode",""+circleTypeCode);
        if (!TextUtils.isEmpty(""+avergPriceBegin))
            map.put("avergPriceBegin",""+avergPriceBegin);
        if (!TextUtils.isEmpty(""+avergPriceEnd))
            map.put("avergPriceEnd",""+avergPriceEnd);
        if (!TextUtils.isEmpty(""+avergPriceSort))
            map.put("avergPriceSort",""+avergPriceSort);
        return map;
    }
}
