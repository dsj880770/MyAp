package com.bwf.p1_landz.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by caoye on 2016/7/26 0026.
 * Description:经纬度bean
 */
public class CoordinateBean implements Parcelable {

    /** 经度 */
    public double x;
    /** 纬度 */
    public double y;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.x);
        dest.writeDouble(this.y);
    }

    public CoordinateBean() {
    }

    protected CoordinateBean(Parcel in) {
        this.x = in.readDouble();
        this.y = in.readDouble();
    }

    public static final Creator<CoordinateBean> CREATOR = new Creator<CoordinateBean>() {
        @Override
        public CoordinateBean createFromParcel(Parcel source) {
            return new CoordinateBean(source);
        }

        @Override
        public CoordinateBean[] newArray(int size) {
            return new CoordinateBean[size];
        }
    };

    @Override
    public String toString() {
        return "CoordinateBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
