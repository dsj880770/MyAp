package com.bwf.framework.db.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cao_ye on 2016/8/26 0026.
 * Description: 搜索历史
 */
public class SearchHistoryBean implements Parcelable {

    public String id;
    public String content;
    public String time;

    @Override
    public String toString() {
        return "SearchHistoryBean{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.content);
        dest.writeString(this.time);
    }

    public SearchHistoryBean() {
    }

    protected SearchHistoryBean(Parcel in) {
        this.id = in.readString();
        this.content = in.readString();
        this.time = in.readString();
    }

    public static final Creator<SearchHistoryBean> CREATOR = new Creator<SearchHistoryBean>() {
        @Override
        public SearchHistoryBean createFromParcel(Parcel source) {
            return new SearchHistoryBean(source);
        }

        @Override
        public SearchHistoryBean[] newArray(int size) {
            return new SearchHistoryBean[size];
        }
    };
}
