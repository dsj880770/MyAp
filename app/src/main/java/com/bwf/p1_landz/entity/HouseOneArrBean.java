package com.bwf.p1_landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cao_ye on 2016/7/19 0019.
 * Description: 房源信息bean 02
 */
public class HouseOneArrBean implements Parcelable {

    public String resblockOneId;
    public String houseOneId;
    public String resblockOneName;//房源名称  东山公寓
    public String bedroomAmount;// 室
    public String parlorAmount;//厅
    public String buildSize;//豪宅大小：310平
    public String totalprBegin;//价格区间的开始
    public String totalprEnd;//价格区间的结束  如：2800-3500万
    public String titlepicImg;//豪宅图片地址
    public String circleTypeName;//划分类型 ： 朝阳公园
    public String resblockType;//筛选类型： 公寓
    public String totalShowing;//总看房次数
    public String sortNum;//排序的num
    public String unitprBegin;
    public String unitprEnd;
    public boolean isSelect = false;
    public String totalNumber;

    @Override
    public String toString() {
        return "HouseOneArrBean{" +
                "resblockOneId='" + resblockOneId + '\'' +
                ", houseOneId='" + houseOneId + '\'' +
                ", resblockOneName='" + resblockOneName + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", buildSize='" + buildSize + '\'' +
                ", totalprBegin='" + totalprBegin + '\'' +
                ", totalprEnd='" + totalprEnd + '\'' +
                ", titlepicImg='" + titlepicImg + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", resblockType='" + resblockType + '\'' +
                ", totalShowing='" + totalShowing + '\'' +
                ", sortNum='" + sortNum + '\'' +
                ", unitprBegin='" + unitprBegin + '\'' +
                ", unitprEnd='" + unitprEnd + '\'' +
                ", totalNumber='" + totalNumber + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resblockOneId);
        dest.writeString(this.houseOneId);
        dest.writeString(this.resblockOneName);
        dest.writeString(this.bedroomAmount);
        dest.writeString(this.parlorAmount);
        dest.writeString(this.buildSize);
        dest.writeString(this.totalprBegin);
        dest.writeString(this.totalprEnd);
        dest.writeString(this.titlepicImg);
        dest.writeString(this.circleTypeName);
        dest.writeString(this.resblockType);
        dest.writeString(this.totalShowing);
        dest.writeString(this.sortNum);
        dest.writeString(this.unitprBegin);
        dest.writeString(this.unitprEnd);
        dest.writeString(this.totalNumber);
    }

    public HouseOneArrBean() {
    }

    protected HouseOneArrBean(Parcel in) {
        this.resblockOneId = in.readString();
        this.houseOneId = in.readString();
        this.resblockOneName = in.readString();
        this.bedroomAmount = in.readString();
        this.parlorAmount = in.readString();
        this.buildSize = in.readString();
        this.totalprBegin = in.readString();
        this.totalprEnd = in.readString();
        this.titlepicImg = in.readString();
        this.circleTypeName = in.readString();
        this.resblockType = in.readString();
        this.totalShowing = in.readString();
        this.sortNum = in.readString();
        this.unitprBegin = in.readString();
        this.unitprEnd = in.readString();
        this.totalNumber = in.readString();
    }

    public static final Creator<HouseOneArrBean> CREATOR = new Creator<HouseOneArrBean>() {
        @Override
        public HouseOneArrBean createFromParcel(Parcel source) {
            return new HouseOneArrBean(source);
        }

        @Override
        public HouseOneArrBean[] newArray(int size) {
            return new HouseOneArrBean[size];
        }
    };
}
