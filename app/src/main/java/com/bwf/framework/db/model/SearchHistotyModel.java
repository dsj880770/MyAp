package com.bwf.framework.db.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.bwf.framework.base.BaseModel;
import com.bwf.framework.db.test.SearchHistoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cao_ye on 2016/8/26 0026.
 * Description:
 */
public class SearchHistotyModel extends BaseModel {


    public static final String TABLE_NAME = "search_history";//表名

    private static Map<String, String> paramsMap = new HashMap<>();

    static {
        paramsMap.put(_ID, "integer primary key autoincrement");//
        paramsMap.put("content", "TEXT NOT NULL");//内容
        paramsMap.put("time", "TEXT NOT NULL");//时间
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, String> getParamsMap() {
        return paramsMap;
    }

    /**
     * 插入一条搜索历史
     */
    public void insertHistory(SearchHistoryBean searchHistoryBean) {
        if (searchHistoryBean == null)
            return;
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", searchHistoryBean.content);
        contentValues.put("time", searchHistoryBean.time);

        if (getCount() == 6) {//如果数据库条数等于6了则删除最后一条数据
            String id = "";
            String sql = "select * from " + TABLE_NAME + " order by time asc";
            Cursor cursor = select(sql);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex(_ID));
                }
                cursor.close();
            }

            delete(TABLE_NAME, "_id=?", new String[]{id});
        }

        insert(TABLE_NAME, contentValues);
    }

    public void updateData(SearchHistoryBean searchHistoryBean){
        if (searchHistoryBean == null)
            return;
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", searchHistoryBean.content);
        contentValues.put("time", searchHistoryBean.time);
        update(TABLE_NAME, contentValues, "content=?", new String[]{searchHistoryBean.content});
    }

    /**
     * 清空搜索历史表
     */
    public void deleteAllData() {
        clear(TABLE_NAME);
    }


    /**
     * 查找所有搜索历史
     *
     * @return
     */
    public List<SearchHistoryBean> getAllHistory() {

        List<SearchHistoryBean> historyBeanList = new ArrayList<>();

        String sql = "select * from " + TABLE_NAME + " order by time desc";
        Cursor cursor = select(sql);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                SearchHistoryBean searchHistoryBean = new SearchHistoryBean();
                searchHistoryBean.content = cursor.getString(cursor.getColumnIndex("content"));
                searchHistoryBean.time = cursor.getString(cursor.getColumnIndex("time"));
                historyBeanList.add(searchHistoryBean);
            }
            cursor.close();
        }

        return historyBeanList;
    }

    /**
     * 根据条件查找是否存在改model
     */
    public boolean exists(String content) {

        Cursor cursor = select("select * from " + TABLE_NAME + " where content = '" + content + "'");
        if (cursor == null)
            return false;
        return cursor.moveToNext();

    }

}
