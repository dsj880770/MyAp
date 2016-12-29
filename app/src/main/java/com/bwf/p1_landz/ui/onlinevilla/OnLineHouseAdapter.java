package com.bwf.p1_landz.ui.onlinevilla;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.tools.Constants;
import com.bwf.framework.utils.StringUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;

import java.util.List;

/**
 * Created by cao_ye on 2016/7/20 0020.
 * Description:
 */
public class OnLineHouseAdapter extends BaseAdapter {

    private Context context;

    private List<Object> totalList;

    private int type_01 = 0;//houseArr

    private int type_02 = 1;//houseOneArr

    private ImageLoader loader;
    private boolean flag_visble = false;

    private  int count = 0;//记录点击选中的数量
    public OnLineHouseAdapter(Context context) {
        this.context = context;
        loader = new ImageLoader();
    }

    public void addTotalList(List<Object> totalList) {
        this.totalList.addAll(totalList);
    }

    public void setTotalList(List<Object> totalList) {
        this.totalList = totalList;
    }

    public List<Object> getTotalList() {
        return totalList;
    }

    @Override
    public int getCount() {
        return totalList == null ? 0 : totalList.size();
    }

    @Override
    public Object getItem(int position) {
        return totalList == null ? null : totalList.get(position);
    }

    //获得列表View的样式类型
    @Override
    public int getItemViewType(int position) {

        if (totalList.get(position) instanceof HouseArrBean)
            return type_01;
        else
            return type_02;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            if (type == type_01) {//houseArr
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_twohand_one, null);
                viewHolder.im_two_twopic = (ImageView) convertView.findViewById(R.id.im_two_twopic);
                viewHolder.tv_two_twocontent = (TextView) convertView.findViewById(R.id.tv_two_twocontent);
                viewHolder.tv_two_twoName = (TextView) convertView.findViewById(R.id.tv_two_twoName);
                viewHolder.ll_bottom_label = (LinearLayout) convertView.findViewById(R.id.ll_bottom_label);
                viewHolder.tv_detail_tese1 = (TextView) convertView.findViewById(R.id.tv_detail_tese1);
                viewHolder.tv_detail_tese2 = (TextView) convertView.findViewById(R.id.tv_detail_tese2);
                viewHolder.tv_detail_tese3 = (TextView) convertView.findViewById(R.id.tv_detail_tese3);
                viewHolder.tv_two_twoarea = (TextView) convertView.findViewById(R.id.tv_two_twoarea);
                viewHolder.tv_two_twomoney = (TextView) convertView.findViewById(R.id.tv_two_twomoney);
                //是否选中
                viewHolder.btn_newhouse_compare = (CheckedTextView) convertView.findViewById(R.id.btn_newhouse_compare);

                convertView.setTag(viewHolder);
            } else if (type == type_02) {
                viewHolder2 = new ViewHolder2();
                convertView = View.inflate(context, R.layout.item_twohand_two, null);
                viewHolder2.im_two_onepic = (ImageView) convertView.findViewById(R.id.im_two_onepic);
                viewHolder2.tv_two_onedetail = (TextView) convertView.findViewById(R.id.tv_two_onedetail);
                viewHolder2.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                viewHolder2.tv_one_detail = (TextView) convertView.findViewById(R.id.tv_one_detail);
                viewHolder2.tv_two_onemoney = (TextView) convertView.findViewById(R.id.tv_two_onemoney);
                viewHolder2.btn_newhouse_compare = (CheckedTextView) convertView.findViewById(R.id.btn_newhouse_compare);

                convertView.setTag(viewHolder2);
            }

        } else {
            if (type == type_01) {//houseArr
                viewHolder = (ViewHolder) convertView.getTag();
            } else if (type == type_02)
                viewHolder2 = (ViewHolder2) convertView.getTag();
        }

        if (type == type_01) {//houseArr
            final HouseArrBean twoModel = (HouseArrBean) totalList.get(position);
            viewHolder.tv_two_twocontent.setText(twoModel.salesTrait);
            viewHolder.tv_two_twoName.setText(twoModel.resblockName + "  "
                    + twoModel.circleTypeName);
            viewHolder.tv_two_twomoney.setText(StringUtils
                    .doubleFormat(twoModel.totalPrices) + "万");
            viewHolder.tv_two_twoarea.setText(twoModel.bedroomAmount + "室"
                    + twoModel.parlorAmount + "厅" + "  "
                    + StringUtils.doubleFormat(twoModel.buildSize) + "㎡");
            showLabel(twoModel.houseLabel, viewHolder);
            viewHolder.im_two_twopic.setImageResource(R.mipmap.defult_twopic);
            loader.displayImg(twoModel.titleImg
                            + Constants.IMG_URL_SUFFIX_ONLINE_LIST_TWO,
                    viewHolder.im_two_twopic);

            if(flag_visble){
                viewHolder.btn_newhouse_compare.setVisibility(View.VISIBLE);
                viewHolder.btn_newhouse_compare.setVisibility(View.VISIBLE);
                if(twoModel.isSelect){
                    viewHolder.btn_newhouse_compare.setChecked(true);
                }else{
                    viewHolder.btn_newhouse_compare.setChecked(false);
                }
            }else{
                viewHolder.btn_newhouse_compare.setVisibility(View.GONE);
            }
            viewHolder.btn_newhouse_compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CheckedTextView ct = (CheckedTextView) view;
                    if(ct.isChecked()){
                        ct.setChecked(false);
                        twoModel.isSelect = false;
                        count--;
                    }else{
                        //点击添加之前判断是否超出了数量
                        if(count >= 5){
                            ToastUtil.showToast("对比的数量不超过5个");
                            return;
                        }
                        ct.setChecked(true);
                        twoModel.isSelect = true;
                        count++;
                    }
//                    notifyDataSetChanged();
                }
            });

        } else if (type == type_02) {//
            final HouseOneArrBean oneModel = (HouseOneArrBean) totalList.get(position);
            viewHolder2.tv_two_onemoney.setText(StringUtils
                    .doubleFormat(oneModel.totalprBegin)
                    + "-"
                    + StringUtils.doubleFormat(oneModel.totalprEnd) + "万");
            viewHolder2.tv_type.setText(oneModel.resblockType);
            viewHolder2.tv_two_onedetail.setText(oneModel.resblockOneName);
            viewHolder2.tv_one_detail.setText(oneModel.bedroomAmount + "室"
                    + oneModel.parlorAmount + "厅" + "  "
                    + StringUtils.doubleFormat(oneModel.buildSize) + "㎡  "
                    + StringUtils.doubleFormat(oneModel.unitprBegin) + "-"
                    + StringUtils.doubleFormat(oneModel.unitprEnd) + "万/㎡");
            viewHolder2.im_two_onepic.setImageResource(R.mipmap.defult_onepic);
            loader.displayImg(oneModel.titlepicImg
                            + Constants.IMG_URL_SUFFIX_ONLINE_LIST_ONE,
                    viewHolder2.im_two_onepic);
            if(flag_visble){
                viewHolder2.btn_newhouse_compare.setVisibility(View.VISIBLE);
                if(oneModel.isSelect){
                    viewHolder2.btn_newhouse_compare.setChecked(true);
                }else{
                    viewHolder2.btn_newhouse_compare.setChecked(false);
                }
            }else{
                viewHolder2.btn_newhouse_compare.setVisibility(View.GONE);
            }


            viewHolder2.btn_newhouse_compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckedTextView ct = (CheckedTextView) view;
                    if(ct.isChecked()){
                        ct.setChecked(false);
                        oneModel.isSelect = false;
                        count--;
                    }else{
                        //点击添加之前判断是否超出了数量
                        if(count >= 5){
                            ToastUtil.showToast("对比的数量不超过5个");
                            return;
                        }
                        ct.setChecked(true);
                        oneModel.isSelect = true;
                        count++;
                    }
//                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }

    /**
     * houseArr显示
     */
    private class ViewHolder {
        ImageView im_two_twopic;//大图
        TextView tv_two_twocontent;//
        TextView tv_two_twoName;//
        LinearLayout ll_bottom_label;//
        TextView tv_detail_tese1;//
        TextView tv_detail_tese2;//
        TextView tv_detail_tese3;//
        TextView tv_two_twoarea;//
        TextView tv_two_twomoney;//
        CheckedTextView btn_newhouse_compare;
    }

    /**
     * houseOneArr显示
     */
    private class ViewHolder2 {
        ImageView im_two_onepic;//大图
        TextView tv_two_onedetail;// 昆泰家润中心
        TextView tv_type;//  公寓
        TextView tv_one_detail;//  两室两厅
        TextView tv_two_onemoney;//  价格
        CheckedTextView btn_newhouse_compare;//判断是否选中
    }


    // 显示标签内容
    private void showLabel(String label, ViewHolder holder) {
        if (!TextUtils.isEmpty(label)) {
            String[] arr = label.trim().split(" ");
            if (arr.length >= 3) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
                holder.tv_detail_tese3.setText(arr[2]);
                holder.tv_detail_tese3.setVisibility(View.VISIBLE);
            } else if (arr.length == 2) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
            } else if (arr.length == 1) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
            }
        }
    }


    public void  setCheckedTextViewVisble(boolean visible){
        flag_visble = visible;
    }

}