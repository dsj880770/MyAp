package com.bwf.framework.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class BitmapUtil {

	//图片保存路径 sdCard的路径
	private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath()+
			File.separator+"bwf"+File.separator+"cache_img"+File.separator;

	/**
	 * 创建存图片的文件夹
	 */
	private static void createCacheDir(){
		File file = new File(CACHE_PATH);
		if(!file.exists()){//当前文件夹不存在则创建
			file.mkdirs();
		}
	}

	/**
	 * 保存一张图片
	 * @param bitmap
	 * @param imgUrl
	 */
	public static void saveBitmapToLocal(Bitmap bitmap,String imgUrl){
		Log.i("msg", "存图片："+imgUrl);
		createCacheDir();
		//这个图片不存在与该文件路径则保存
		//先判断文件是否存在
		//先获得图片的名字  1.截取图片真正的名字  2.使用url作为图片的名字（需要加密，去掉特殊字符）
		File img_file = new File(CACHE_PATH,getImg_FileName(imgUrl));
		if(img_file.exists()){//如果图片存在则不报存
			return;
		}
		//保存图片
		OutputStream out = null;
		try {
			out =new FileOutputStream(img_file);
			bitmap.compress(CompressFormat.JPEG, 80, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 获得图片
	 * @param imgUrl
	 * @return
	 */
	public static Bitmap getBitmapFromSDCard(String imgUrl){
		Log.i("msg", "取图片："+imgUrl);
		//图片的路径
		String img_path =CACHE_PATH+getImg_FileName(imgUrl);
		return BitmapFactory.decodeFile(img_path);
	}
	/**
	 * 使用图片的网络地址 获取图片的文件名
	 */
	private static String getImg_FileName(String imgUrl){
		//先要加密去掉特殊字符
		String fileName = MD5(imgUrl)+".jpeg";
		Log.i("msg", "MD5："+fileName);
		return fileName;
	}

	/**
	 * MD5加密算法    为了格式化图片网络路径，转换成加密方式
	 * 因为要作为文件名  所以  加密之后要进行去掉特殊字符
	 * @param msg
	 * @return
	 */
	private static String MD5(String msg){
		try {
			byte[] btinput = msg.getBytes();
			//获得一个Md5加密对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			//把要加密的字节数组放入到加密对象中  返回加密之后的字节数组
			byte[] md_by = md.digest(btinput);
			StringBuffer sb = new StringBuffer();
			//去掉特殊字符
			for (int i = 0; i < md_by.length; i++) {
				//位运算& 两个都是1 才是1     0xff= 1111 1111
				int val = ((int)md_by[i])& 0xff;
				if( val < 16){//小于16的都是特殊字符
					sb.append("0");
				}else{
					sb.append(Integer.toHexString(val));
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 压缩  优化 图片
	 *  优化图片大小 和质量
	 * @param bitmap
	 * @param pixelW  要优化的宽度
	 * @param pixelH  要优化的高度
	 * @return
	 */
	public static Bitmap storeImage(Bitmap bitmap,float pixelW, float pixelH){
		/******************质量压缩*************************/
		//字节缓冲流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//给字节缓冲流读入数据
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
		//如果图片大于200kb 就进行质量压缩  bitmap.getByteCount();
		if( os.toByteArray().length / 1024>1024/5) {//判断如果图片大于200kb,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			os.reset();//重置baos即清空baos
			bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
		}
		/******************大小压缩*************************/
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		//设置颜色值（样式）
		newOpts.inPreferredConfig = Config.RGB_565;
		/**************设置比例压缩***********************/
		int w =bitmap.getWidth();
		int h = bitmap.getHeight();
		float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
		float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (w / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (h / hh);
		}
		if (be <= 0){
			be = 1;
		}
		newOpts.inSampleSize = be;//设置缩放比例
		/*************************************/
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		newOpts.inJustDecodeBounds = false;
		//给Options赋值
		Bitmap bitmap1 = BitmapFactory.decodeStream(is, null, newOpts);
		return bitmap1;
	}

}
