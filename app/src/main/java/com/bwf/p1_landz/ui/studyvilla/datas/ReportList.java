package com.bwf.p1_landz.ui.studyvilla.datas;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReportList {
    private String pkId;

    private String title;

    private String describe;

    private String wordPath;

    private int createDate;

    public void setPkId(String pkId){
        this.pkId = pkId;
    }
    public String getPkId(){
        return this.pkId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }
    public String getDescribe(){
        return this.describe;
    }
    public void setWordPath(String wordPath){
        this.wordPath = wordPath;
    }
    public String getWordPath(){
        return this.wordPath;
    }
    public void setCreateDate(int createDate){
        this.createDate = createDate;
    }
    public int getCreateDate(){
        return this.createDate;
    }

}
