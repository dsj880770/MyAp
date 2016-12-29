package com.bwf.p1_landz.ui.onevilla.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bwf.framework.base.BaseFragment;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.DisplayUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ApartmentImgVoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class Fragment_detail_fragment05 extends BaseFragment{
    private LinearLayout ll_other_house;

    public List<ApartmentImgVoBean> apartmentImgVos;
    private ImageLoader imageLoader;
    private View title;
    @Override
    public int getResourceId() {
        return R.layout.fragment_detail_fragment_5;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        ll_other_house = findViewByIdNoCast(R.id.ll_other_house );
    }

    @Override
    public void initData() {
        if (apartmentImgVos != null){
            for (final ApartmentImgVoBean apartmentImgVo : apartmentImgVos){
                final ImageView imageView = new ImageView(this.getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(10,0,0,0);
                imageLoader.displayImg(apartmentImgVo.imgUrl,imageView);
                ll_other_house.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(title != null){

                            final PopupWindow popupWindow = new PopupWindow(getContext());
                            View pop_View = View.inflate(getContext(),R.layout.pop_other_house,null);
                            popupWindow.setContentView(pop_View);

                            ImageView pop_Img = (ImageView) pop_View.findViewById(R.id.img_pop_other_house);
                            imageLoader.displayImg(apartmentImgVo.imgUrl,pop_Img);

                            popupWindow.setWidth(DisplayUtil.getDensity_Width(getContext()));
                            popupWindow.setHeight(DisplayUtil.getDensity_Height(getContext())-title.getHeight());
//                            popupWindow.setOutsideTouchable(true);
//                            popupWindow.setFocusable(true);
                            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.setAnimationStyle(R.style.PopupAnimation);//设置popWindow动画
                            popupWindow.showAsDropDown(title);
                            pop_View.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View v) {

    }
    public void setApartmentImgVos(List<ApartmentImgVoBean> apartmentImgVos, ImageLoader imageLoader,View view) {
        this.apartmentImgVos = apartmentImgVos;
        this.imageLoader = imageLoader;
        this.title = view;
        initData();
    }
}
