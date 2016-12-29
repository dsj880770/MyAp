package com.bwf.p1_landz.ui.onlinevilla;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwf.framework.image.ImageLoader;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GuWenResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoye on 2016/7/27 0027.
 * Description: 顾问列表adapter
 */
public class GuWenAdapter extends BaseAdapter {

    private Context context;

    private List<GuWenResultBean.ShowArr> showArrs;

    private ImageLoader imageLoader;

    public GuWenAdapter(Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.showArrs = new ArrayList<>();
    }

    public GuWenAdapter(Context context, List<GuWenResultBean.ShowArr> showArrs, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.showArrs = new ArrayList<>();
        if (showArrs != null && !showArrs.isEmpty()) {
            for (int i = 1; i < showArrs.size(); i++) {
                this.showArrs.add(showArrs.get(i));
            }
        }

    }

    public void setShowArrs(List<GuWenResultBean.ShowArr> showArrs) {
        if (showArrs != null && !showArrs.isEmpty()) {
            for (int i = 1; i < showArrs.size(); i++) {
                this.showArrs.add(showArrs.get(i));
            }
        }
    }

    @Override
    public int getCount() {
        return showArrs == null ? 0 : showArrs.size();
    }

    @Override
    public Object getItem(int position) {
        return showArrs == null ? null : showArrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_guwen, null);
            viewHolder.img_photo = (ImageView) convertView.findViewById(R.id.im_manpic);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_guwen_name);
            viewHolder.tv_man_period = (TextView) convertView.findViewById(R.id.tv_man_period);
            viewHolder.tv_guwen_desc = (TextView) convertView.findViewById(R.id.tv_guwen_desc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GuWenResultBean.ShowArr showArr = showArrs.get(position);
        if (showArr != null) {
            imageLoader.displayImg(showArr.photo, viewHolder.img_photo);
            viewHolder.tv_name.setText(showArr.createName);
            viewHolder.tv_man_period.setText(showArr.inductionDate);
            String desc = "60天内带看本方<font color='red'>" + showArr.totalShowing + "</font>次";
            viewHolder.tv_guwen_desc.setText(Html.fromHtml(desc));
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView img_photo;//顾问头像
        TextView tv_name;//顾问名字
        TextView tv_man_period;//从业时间
        TextView tv_guwen_desc;//带看房次数
    }

}
