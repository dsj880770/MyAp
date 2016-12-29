package com.bwf.p1_landz.ui.studyvilla.datas;

/**
 * Created by Administrator on 2016/12/8.
 */

public class Datas {
    private int resultStatus;

    private String resultMsg;

    private Result result;

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
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }

}
