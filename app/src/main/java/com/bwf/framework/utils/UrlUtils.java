package com.bwf.framework.utils;

/**
 * Created by Lizhangfeng on 2016/7/19 0019.
 * Description:
 */
public class UrlUtils {

    public static final String BASE_URL = "http://119.254.70.199:8080/landz-app";

    /*  在线房源*/
    public static final String ONLINE_HOUSE = BASE_URL + "/house/houseBuySellList";

    /* 搜索 */
    public static final String SEARCH_URL = BASE_URL + "/homePage/getResblockListByKeyWords";//"keyWords", keyWords type

    /* 一手房子详情 */
    public static final String HOUSE_DETAIL = BASE_URL + "/houseOne/houseOneDetail";

    /* 一手房子详情-看房记录 */
    public static final String HOUSE_DETAIL_LOOK = BASE_URL + "/see/houseOneDetailSeeHistoryList";

    /* 一手房源详情更多一手房源推荐(一手房源推荐列表) */
    public static final String HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL = BASE_URL + "/houseOne/houseOneRecommendList";
    /* 顾问带看历史列表 */
    public static final String SEE_HISTORY_LIST_URL = BASE_URL + "/see/seeHistoryList";
    /*  豪宅研究*/
    public static final String STUDY = BASE_URL + "/houseReport/getHouseResportList";

}
