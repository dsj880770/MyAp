package com.bwf.p1_landz.ui.onlinevilla;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ParamListBean;

import java.util.List;

/**
 * Created by Cao_Ye on 2016/12/1.
 */
public class LocationAdapter extends BaseAdapter{
    private List<ParamListBean>  items;
    private Context context;
    private boolean isSecond;//是否是二级列表
    private boolean isMore = false;
    public LocationAdapter(Context context, boolean isSecond){
        this.context = context;
        this.isSecond = isSecond;
    }
    public LocationAdapter(Context context, boolean isSecond,boolean isMore) {
        this.context = context;
        this.isSecond = isSecond;
        this.isMore = isMore;
    }

    public List<ParamListBean> getItems() {
        return items;
    }

    public void setItems(List<ParamListBean> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null?0:items.size();
    }

    @Override
    public ParamListBean getItem(int i) {
        return items == null?null:items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = View.inflate(context, R.layout.item_location,null);
            viewHolder = new ViewHolder();
            viewHolder.ll_location = (LinearLayout) view.findViewById(R.id.ll_location);
            viewHolder.tv_item_loaction = (TextView) view.findViewById(R.id.tv_itme_loaction);
            viewHolder.img_nike = (ImageView) view.findViewById(R.id.img_nike);//更多里面使用
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ParamListBean bean = items.get(i);
        //设置内容
        viewHolder.tv_item_loaction.setText(bean.name);
        if(isSecond){//二级列表
            viewHolder.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));//设置成灰色
            if(bean.isSelect){//选择
                viewHolder.tv_item_loaction.setTextColor(Color.parseColor("#4a2450"));
                if (isMore){
                    viewHolder.img_nike.setVisibility(View.VISIBLE);
                }
            }else{
                viewHolder.tv_item_loaction.setTextColor(Color.BLACK);
                if (isMore){
                    viewHolder.img_nike.setVisibility(View.GONE);
                }
            }
        }else{
            if(bean.isSelect){//选择
                viewHolder.ll_location.setBackgroundColor(Color.parseColor("#EEEEEE"));
                viewHolder.tv_item_loaction.setTextColor(Color.parseColor("#4a2450"));
            }else{
                viewHolder.ll_location.setBackgroundColor(Color.WHITE);
                viewHolder.tv_item_loaction.setTextColor(Color.BLACK);
            }
        }
        return view;
    }


    private class ViewHolder{
        LinearLayout ll_location;
        TextView tv_item_loaction;
        ImageView img_nike;
    }
}
