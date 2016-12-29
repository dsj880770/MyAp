package com.bwf.p1_landz.entity;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by cao_ye on 2016/7/19 0019.
 * Description:
 */
public class OnLineHouseResultBean implements Parcelable {

    public List<HouseArrBean> houseArr;
    public List<HouseOneArrBean> houseOneArr;

    protected OnLineHouseResultBean(Parcel in) {
        houseArr = in.createTypedArrayList(HouseArrBean.CREATOR);
        houseOneArr = in.createTypedArrayList(HouseOneArrBean.CREATOR);
    }

    public OnLineHouseResultBean(){

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(houseArr);
        dest.writeTypedList(houseOneArr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OnLineHouseResultBean> CREATOR = new Creator<OnLineHouseResultBean>() {
        @Override
        public OnLineHouseResultBean createFromParcel(Parcel in) {
            return new OnLineHouseResultBean(in);
        }

        @Override
        public OnLineHouseResultBean[] newArray(int size) {
            return new OnLineHouseResultBean[size];
        }
    };

    @Override
    public String toString() {
        return "OnLineHouseResultBean{" +
                "houseArr=" + houseArr +
                ", houseOneArr=" + houseOneArr +
                '}';
    }
}
