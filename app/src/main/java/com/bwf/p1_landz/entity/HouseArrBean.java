package com.bwf.p1_landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cao_ye on 2016/7/19 0019.
 * Description: 房源信息对象
 */
public class HouseArrBean implements Parcelable {
    public boolean isSelect = false;
    public String housedelId;//房源id
    public String resblockId;//
    public String resblockName;//房源名称  龙湾别墅
    public String circleTypeCode;//划分类型id
    public String circleTypeName;//划分类型  中央别墅区
    public String totalPrices;//总价
    public String bedroomAmount;// 室
    public String parlorAmount;//厅
    public String buildSize;//豪宅大小：310平
    public String houseLabel;
    public String salesTrait;//销售特点： 龙湾别墅  4室2厅
    public String titleImg;//豪宅图片地址
    public String isFocus;
    public String isBasilic;
    public String isKey;
    public String maxCanLookTime;
    public String totalShowing;//总看房次数
    public String score;//分数
    public String orientation;//豪宅方向
    public String usageType;//筛选的类型： 别墅
    public String sortNum;//排序num
    public String totalNumber;

    @Override
    public String toString() {
        return "HouseArrBean{" +
                "housedelId='" + housedelId + '\'' +
                ", resblockId='" + resblockId + '\'' +
                ", resblockName='" + resblockName + '\'' +
                ", circleTypeCode='" + circleTypeCode + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", totalPrices='" + totalPrices + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", buildSize='" + buildSize + '\'' +
                ", houseLabel='" + houseLabel + '\'' +
                ", salesTrait='" + salesTrait + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", isFocus='" + isFocus + '\'' +
                ", isBasilic='" + isBasilic + '\'' +
                ", isKey='" + isKey + '\'' +
                ", maxCanLookTime='" + maxCanLookTime + '\'' +
                ", totalShowing='" + totalShowing + '\'' +
                ", score='" + score + '\'' +
                ", orientation='" + orientation + '\'' +
                ", usageType='" + usageType + '\'' +
                ", sortNum='" + sortNum + '\'' +
                ", totalNumber='" + totalNumber + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.housedelId);
        dest.writeString(this.resblockId);
        dest.writeString(this.resblockName);
        dest.writeString(this.circleTypeCode);
        dest.writeString(this.circleTypeName);
        dest.writeString(this.totalPrices);
        dest.writeString(this.bedroomAmount);
        dest.writeString(this.parlorAmount);
        dest.writeString(this.buildSize);
        dest.writeString(this.houseLabel);
        dest.writeString(this.salesTrait);
        dest.writeString(this.titleImg);
        dest.writeString(this.isFocus);
        dest.writeString(this.isBasilic);
        dest.writeString(this.isKey);
        dest.writeString(this.maxCanLookTime);
        dest.writeString(this.totalShowing);
        dest.writeString(this.score);
        dest.writeString(this.orientation);
        dest.writeString(this.usageType);
        dest.writeString(this.sortNum);
        dest.writeString(this.totalNumber);
    }

    public HouseArrBean() {
    }

    protected HouseArrBean(Parcel in) {
        this.housedelId = in.readString();
        this.resblockId = in.readString();
        this.resblockName = in.readString();
        this.circleTypeCode = in.readString();
        this.circleTypeName = in.readString();
        this.totalPrices = in.readString();
        this.bedroomAmount = in.readString();
        this.parlorAmount = in.readString();
        this.buildSize = in.readString();
        this.houseLabel = in.readString();
        this.salesTrait = in.readString();
        this.titleImg = in.readString();
        this.isFocus = in.readString();
        this.isBasilic = in.readString();
        this.isKey = in.readString();
        this.maxCanLookTime = in.readString();
        this.totalShowing = in.readString();
        this.score = in.readString();
        this.orientation = in.readString();
        this.usageType = in.readString();
        this.sortNum = in.readString();
        this.totalNumber = in.readString();
    }

    public static final Creator<HouseArrBean> CREATOR = new Creator<HouseArrBean>() {
        @Override
        public HouseArrBean createFromParcel(Parcel source) {
            return new HouseArrBean(source);
        }

        @Override
        public HouseArrBean[] newArray(int size) {
            return new HouseArrBean[size];
        }
    };
}
