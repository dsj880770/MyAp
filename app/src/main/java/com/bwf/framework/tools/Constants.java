package com.bwf.framework.tools;

import com.bwf.framework.base.BaseModel;
import com.bwf.framework.db.model.SearchHistotyModel;

/**
 * Created by cao_ye on 2016/7/13 0013.
 * Description: 常量类
 */
public class Constants {

    /**
     * 二手列表用
     */
    public static final String IMG_URL_SUFFIX_ONLINE_LIST_TWO = ".300x200.jpg";
    /**
     * 只有一手楼盘列表用
     */
    public static final String IMG_URL_SUFFIX_ONLINE_LIST_ONE = ".720x405.jpg";

    /*  页数 */
    public static final int PAGE_SIZE = 10;


    public static final String DB_NAME = "tuanche_db";//数据库名称

    public static final int DB_VERSION = 1;//数据库版本

    public static final int ORIENTATION_GRID = 100;//表格布局分割线

    //数据库所有的表
//    public static String[] TABLES = new String[]{SearchHistotyModel.class.getName()};

    public static BaseModel[] TABLES = new BaseModel[]{new SearchHistotyModel()};
}
