package com.bwf.p1_landz.ui.onlinevilla;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.framework.image.ImageLoader;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ImgUrlArrBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cao_Ye on 2016/12/11.
 *
 */
public class ImagePagerAdapter extends PagerAdapter{
    private List<ImgUrlArrBean> imgUrlArr;
    private List<ImageView> imageViews;
    private ImageLoader imageLoader;

    public ImagePagerAdapter(Context context,List<ImgUrlArrBean> imgUrlArr, ImageLoader imageLoader) {
        this.imageViews = new ArrayList<>();
        this.imageLoader = imageLoader;
        this.imgUrlArr = imgUrlArr;
        //初始化ImageView
       if(imgUrlArr != null && imgUrlArr.size() != 0){
           for (ImgUrlArrBean imgUrlArrBean : imgUrlArr){
                   ImageView imageView = new ImageView(context);
                   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                   imageViews.add(imageView);
           }
       }
    }

    //无线循环 增大ViewPager数量
    @Override
    public int getCount() {
        return imageViews.size()== 1?1:imageViews.size()*100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(imageViews.get(position % imageViews.size()));
    }

    //添加View
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViews.get(position % imageViews.size());
        //设置默认图片
        imageView.setImageResource(R.mipmap.defult_onepic);
        //下载
        imageLoader.displayImg(imgUrlArr.get(position % imageViews.size()).picName,imageView);

        //没有被其他的Views使用这可以添加到新的View里面
        if(imageView.getParent() == null){
            //添加图片控件到Viewpager中
            container.addView(imageView);
        }
        return imageView;
    }
}
