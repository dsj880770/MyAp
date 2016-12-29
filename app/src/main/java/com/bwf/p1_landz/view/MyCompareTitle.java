package com.bwf.p1_landz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p1_landz.R;

public class MyCompareTitle extends RelativeLayout implements View.OnClickListener   {
	private LayoutInflater inflater;
	private TextView tv_build_name_title;
	private ImageView iv_close_title;
	private MyViewClickListener listener;
	private int id=-1;

	public MyCompareTitle(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	public MyCompareTitle(Context context, AttributeSet attrs) { 
        this(context, attrs,0); 
    }
	public MyCompareTitle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflater=LayoutInflater.from(context);
		View v=inflater.inflate(R.layout.my_compare_title, null);
		tv_build_name_title=(TextView) v.findViewById(R.id.tv_build_name_titile);
		iv_close_title=(ImageView) v.findViewById(R.id.iv_close_title);
		iv_close_title.setOnClickListener(this);
		this.addView(v);
	}
	public void setInfo(String buildname,int id){
		tv_build_name_title.setText(buildname==null?"/":buildname);
		this.id=id;
	}
	public void setViewClickListener(MyViewClickListener listener){
		this.listener =listener;
	}
	
	@Override
	public void onClick(View v) {
		if(v==iv_close_title){
			if(id==-1) return;
			if(listener!=null){
				listener.onViewClicked(id);
			}
		}
	} 
	
}
