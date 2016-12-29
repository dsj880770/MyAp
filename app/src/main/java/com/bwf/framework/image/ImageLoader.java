package com.bwf.framework.image;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader {
	//线程队列
	private ExecutorService executor;
	//用于缓存图片
	//里面保存一个强引用  用来限制内容数量   每当item被访问的时候，这个item就会移动到队列的头部
	//当Cache满了之后，加入新的item时，在队列的尾部的item会被回收
	//当然也可以根据你的cache值明确释放
	//使用方式类似于HashMap
	private LruCache<String , Bitmap> mLruCache;
	public ImageLoader() {
		//参数  几个数量的线程队列  线程池  线程队列的方式  先进先出
		executor = Executors.newFixedThreadPool(3);
		//初始化mLruCache
//		int mTotalSize = (int) (Runtime.getRuntime().totalMemory()/5);
		int mTotalSize = 5*1024*1024;
		mLruCache = new LruCache<String, Bitmap>(mTotalSize){
			/*获取每个value的大小*/
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}

			/*当缓存大于我们设定的最大值是，会调用这个方法，我们也可以自己去做内存释放*/
			@Override
			protected void entryRemoved(boolean evicted, String key,
										Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
			}
		};
	}

	/**
	 * 设置快速滑动不下载图片
	 */
	boolean isDownload = true;
	public void setIsDownload(boolean isDownload){
		this.isDownload = isDownload;
	}

	/**
	 * 下载显示图片的方法
	 * @param url   图片地址
	 * @param imageView  图片加载的控件
	 */
	public void displayImg(String url,ImageView imageView){
		//设置防错乱标志
		imageView.setTag(url);
		//1.从缓存中获得图片
		Bitmap bitmap = getBitmapFromCache(url);
		if(bitmap != null){
			//显示图片  如果图片不为空 就不往下执行了。
			showBitmap(bitmap, url, imageView);
//			Log.i("msg1", "从缓存中获得的图片："+url);
			return;
		}
		if(isDownload){//快速滑动不下载图片
			//使用子线程做耗时操作
			LoadBitmapRunnable runnable = new LoadBitmapRunnable(url, imageView);
			executor.execute(runnable);
		}
	}

	/**
	 * 加载显示图片线程，网络下载图片必须放到子线程中
	 * @author Cao_Ye
	 *
	 */
	private class LoadBitmapRunnable implements Runnable{
		private String imgUrl ;
		private ImageView imageView;
		public LoadBitmapRunnable(String imgUrl,ImageView imageView) {
			this.imgUrl = imgUrl;
			this.imageView = imageView;
		}
		@Override
		public void run() {
			Bitmap bitmap = null;
			//2.从本地获取图片
			bitmap = getBitmapFromLocal(imgUrl);
			if(bitmap != null){
				//显示图片到ImageView
				showBitmap(bitmap, imgUrl, imageView);
				return;
			}
			//3.从网络中获取图片
			bitmap = getBitmapFromNetWork(imgUrl);
			if(bitmap != null){
				//显示图片到ImageView
				showBitmap(bitmap, imgUrl, imageView);
			}
		}
	}

	/**
	 * 从缓存中获得图片
	 * @param imgurl
	 * @return
	 */
	private Bitmap getBitmapFromCache(String imgurl) {
		return mLruCache.get(imgurl);
	}
	/**
	 * 设置图片到缓存中
	 * @param imgurl
	 * @param bitmap
	 */
	private void addBitmapFromCache(String imgurl,Bitmap bitmap) {
		mLruCache.put(imgurl, bitmap);
	}

	/**
	 * 从本地获取一张图片
	 * @param imgUrl
	 * @return  Bitmap对象
	 */
	private Bitmap getBitmapFromLocal(String imgUrl){
		//从本地中获得一张图片  要存进缓存中
		Bitmap bitmap = BitmapUtil.getBitmapFromSDCard(imgUrl);
		if(bitmap != null){
			addBitmapFromCache(imgUrl, bitmap);
		}
		return bitmap;
	}

	/**
	 * 存一张图片到本地sd中
	 * @param imgurl
	 * @param bitmap
	 */
	private void addBitmapFromLocal(String imgurl,Bitmap bitmap){
		BitmapUtil.saveBitmapToLocal(bitmap, imgurl);
	}
	/**
	 * 从网络中获取图片  返回Bitmap对象
	 * @param imgUrl
	 * @return
	 */
	private Bitmap getBitmapFromNetWork(String imgUrl){
		Bitmap bitmap = null;
		try {
			URL url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(8000);
			conn.setReadTimeout(8000);
			InputStream in = conn.getInputStream();
			//把流转换成图片Bitmap
			bitmap = BitmapFactory.decodeStream(in);
			Log.i("msg1", "原图大小："+bitmap.getByteCount());
			//优化图片
			bitmap = BitmapUtil.storeImage(bitmap, 200, 200);
			Log.i("msg1", "优化后图大小："+bitmap.getByteCount());
			//添加图片到缓存中
			addBitmapFromCache(imgUrl, bitmap);
			//途径图片到本地中
			addBitmapFromLocal(imgUrl, bitmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	private void showBitmap(final Bitmap bitmap,String imgUrl,final ImageView imageView){
		//防止图片错乱
		if(!imageView.getTag().equals(imgUrl)){
			return;
		}
		//先要获得Activity对象  runOnUiThread 方法来至于Activity
//		AppCompatActivity activity = (AppCompatActivity) imageView.getContext();
		//使用主线程的cpu调度来执行这个runnable接口
//		activity.runOnUiThread(new Runnable() {
//
//			@Override
//			public void run() {
//				imageView.setImageBitmap(bitmap);
//			}
//		});
		//使用主线程的cpu调度来执行这个runnable接口
		showBitmapRunnable action = new showBitmapRunnable(imageView, bitmap);
		imageView.post(action);
	}

	class showBitmapRunnable implements Runnable {
		private Bitmap bitmap;
		private ImageView imageView;
		public showBitmapRunnable(ImageView imageView, Bitmap bitmap) {
			this.bitmap = bitmap;
			this.imageView = imageView;
		}

		@Override
		public void run() {
			imageView.setImageBitmap(bitmap);
		}

	}
}
