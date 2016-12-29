package com.bwf.p1_landz.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class OneBuidlData {
    private int resultStatus;

    private String resultMsg;

    private List<OneBuildingResult> result ;

    public void setResultStatus(int resultStatus){
        this.resultStatus = resultStatus;
    }
    public int getResultStatus(){
        return this.resultStatus;
    }
    public void setResultMsg(String resultMsg){
        this.resultMsg = resultMsg;
    }
    public String getResultMsg(){
        return this.resultMsg;
    }
    public void setResult(List<OneBuildingResult> result){
        this.result = result;
    }
    public List<OneBuildingResult> getResult(){
        return this.result;
    }
}
