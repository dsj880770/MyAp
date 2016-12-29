package com.bwf.p1_landz.ui.studyvilla.datas;

/**
 * Created by Administrator on 2016/12/8.
 */

import java.util.List;
public class Result {
    private String pkid;

    private String title;

    private String wordPath;

    private String showImgPath;

    private String describe;

    private List<ReportList> reportList ;

    public void setPkid(String pkid){
        this.pkid = pkid;
    }
    public String getPkid(){
        return this.pkid;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setWordPath(String wordPath){
        this.wordPath = wordPath;
    }
    public String getWordPath(){
        return this.wordPath;
    }
    public void setShowImgPath(String showImgPath){
        this.showImgPath = showImgPath;
    }
    public String getShowImgPath(){
        return this.showImgPath;
    }
    public void setDescribe(String describe){
        this.describe = describe;
    }
    public String getDescribe(){
        return this.describe;
    }
    public void setReportList(List<ReportList> reportList){
        this.reportList = reportList;
    }
    public List<ReportList> getReportList(){
        return this.reportList;
    }

}