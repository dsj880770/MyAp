package com.bwf.p1_landz.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.framework.utils.DisplayUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.ui.onlinevilla.LocationAdapter;

import java.util.List;

/**
 * Created by Cao_Ye on 2016/12/1.
 *  筛选的PopupWindow
 */
public class TitlePopupWindow extends PopupWindow{

    private boolean isOneList;//是否是一个列表  true 为一个类别
    private ListView lv_location,lv_location2;
    private LocationAdapter adapter,adapter2;
    public TitlePopupWindow(Context context,boolean isOneList) {
        super(context);
        this.isOneList = isOneList;
        //添加View
        View view = View.inflate(context, R.layout.pop_location,null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context)/2+50);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());

        //初始化ListView
        initView(view,context);
    }

    public void initView(View view,Context context){
        lv_location = (ListView) view.findViewById(R.id.lv_location);
        lv_location2 = (ListView) view.findViewById(R.id.lv_location2);
        //设置第一个列表的适配器
        adapter = new LocationAdapter(context,false);
        lv_location.setAdapter(adapter);
        //查看是否需要设置第二个列表
        if(isOneList){//不需要设置
            lv_location2.setVisibility(View.GONE);
        }else{//需要则显示出来并设置适配器
            lv_location2.setVisibility(View.VISIBLE);
            adapter2 = new LocationAdapter(context,true);
            lv_location2.setAdapter(adapter2);
        }
    }

    /**
     * 放adapter的数据 并刷新适配器
     */
    public void setParamListBean(final List<ParamListBean>  bean,final PupupWindowItemClickCallBack  pTsCallBack){
        if(bean == null && bean.isEmpty()){
            return;
        }
        //设置第一列数据
        adapter.setItems(bean);
        adapter.notifyDataSetChanged();

        lv_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置选择效果
                setSelectDatas(bean,i);
                adapter.notifyDataSetChanged();
                //然后查看是否需要弹出第二列数据1
                //如果第一列选择了再去判断是否给第二列添加数据
                if(!isOneList){//代表有第二列数据
                    //获得第一列选中的第二列数据
                    List<ParamListBean>  bean2 = bean.get(i).childList;
                    adapter2.setItems(bean2);
                    adapter2.notifyDataSetChanged();
                }
                //回调接口接收内容  第一列要判断是否有第二列数据  没有则回调
                if(isOneList){//没有第二列数据
                    if(pTsCallBack != null){
                        //回调回去点击的改对象
                        pTsCallBack.onItemClickCallBack(bean.get(i));
                        dismiss();
                    }
                }
            }
        });

        lv_location2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置选择效果
                setSelectDatas(adapter2.getItems(),i);
                adapter2.notifyDataSetChanged();

                if(pTsCallBack != null){
                    //回调回去点击的改对象
                    pTsCallBack.onItemClickCallBack(adapter2.getItems().get(i));
                    dismiss();
                }
            }
        });
    }

    public void setSelectDatas(List<ParamListBean> bean,int position){
        for(int i = 0;i< bean.size();i++){
            if(i == position){
                bean.get(i).isSelect = true;
            }else{
                bean.get(i).isSelect = false;
            }
        }

    }

    /**
     * 价格自定义
     * @param context
     * @param pTsCallBack
     */
    public void setPrice_zidingyuView(Context context,final PupupWindowItemClickCallBack  pTsCallBack){
        View footer_price = View.inflate(context,R.layout.select_custom_price_item,null);
        lv_location.addFooterView(footer_price);
        //设置数据获取 点击监听
        final  EditText tv_min = (EditText) footer_price.findViewById(R.id.et_min);
        Button button = (Button) footer_price.findViewById(R.id.money_ok);

        final ParamListBean paramListBean = new ParamListBean();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String min_v = tv_min.getText().toString();
                if(min_v != null && min_v.equals("")){
                    paramListBean.minValue = min_v;
                }
                //最大值

                //回调

                if(pTsCallBack != null){
                    pTsCallBack.onItemClickCallBack(paramListBean);
                }

            }
        });
        }
    /**
     * 显示pupopWindow
     */
    public void showPopupWindow(View view){
        if(!this.isShowing()){
            this.showAsDropDown(view);
        }
    }


    /**
     * 一级或二级点击item回调
     */
    public interface PupupWindowItemClickCallBack{
        void onItemClickCallBack(ParamListBean paramListBean);
    }
}
