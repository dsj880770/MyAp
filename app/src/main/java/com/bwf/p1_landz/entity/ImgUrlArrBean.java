package com.bwf.p1_landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by caoye on 2016/7/26 0026.
 * Description: 展示图bean
 */
public class ImgUrlArrBean implements Parcelable {

    public String resourceId;//id
    public String picName;//图片地址
    public String picType;//图片的类型  1外景图 2地理位置图 3座栋分布图 4户型图 5样板间 6 实勘图

    @Override
    public String toString() {
        return "ImgUrlArrBean{" +
                "resourceId='" + resourceId + '\'' +
                ", picName='" + picName + '\'' +
                ", picType='" + picType + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceId);
        dest.writeString(this.picName);
        dest.writeString(this.picType);
    }

    public ImgUrlArrBean() {
    }

    protected ImgUrlArrBean(Parcel in) {
        this.resourceId = in.readString();
        this.picName = in.readString();
        this.picType = in.readString();
    }

    public static final Creator<ImgUrlArrBean> CREATOR = new Creator<ImgUrlArrBean>() {
        @Override
        public ImgUrlArrBean createFromParcel(Parcel source) {
            return new ImgUrlArrBean(source);
        }

        @Override
        public ImgUrlArrBean[] newArray(int size) {
            return new ImgUrlArrBean[size];
        }
    };
}


