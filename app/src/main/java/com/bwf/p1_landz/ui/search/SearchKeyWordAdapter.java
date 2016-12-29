package com.bwf.p1_landz.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.SearchKeyWordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cao_Ye on 2016/12/8.
 */
public class SearchKeyWordAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchKeyWordBean.SearchKeyWordDetail> data = new ArrayList<SearchKeyWordBean.SearchKeyWordDetail>();

    public SearchKeyWordAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SearchKeyWordBean.SearchKeyWordDetail> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_search_value, null);
            holder.tv_search_val = (TextView) convertView
                    .findViewById(R.id.tv_search_val);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_search_val.setText(data.get(position).name);
        return convertView;
    }

    class ViewHolder {
        TextView tv_search_val;
    }
}
