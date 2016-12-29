package com.bwf.p1_landz.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;

public class MyCompareItem extends LinearLayout {
	private LayoutInflater inflater;
	private ImageView iv_actual,iv_house_type,iv_advicer;
	private TextView tv_price,tv_building_name,tv_single_price,tv_price_coast,tv_first_can_loan,tv_second_can_loan,
	tv_room_type,tv_area,tv_floor,tv_toward,tv_build_finished,tv_house_type,tv_decorate,tv_city_proper,tv_business,
	tv_address,tv_phone_number;
	
	
	
	public MyCompareItem(Context context) {
		this(context,null);
	}
	public MyCompareItem(Context context, AttributeSet attrs) { 
        this(context, attrs,0); 
    } 
	
	public MyCompareItem(Context context, AttributeSet attrs, 
            int defStyle) { 
		super(context, attrs, defStyle); 
		setOrientation(VERTICAL);
		inflater=LayoutInflater.from(context);
		View v=inflater.inflate(R.layout.my_compare_item, null);
		iv_actual=(ImageView) v.findViewById(R.id.iv_actual);
		iv_house_type=(ImageView) v.findViewById(R.id.iv_house_type);
		iv_advicer=(ImageView) v.findViewById(R.id.iv_advicer);
		tv_price=(TextView) v.findViewById(R.id.tv_price);
		tv_building_name=(TextView) v.findViewById(R.id.tv_building_name);
		tv_single_price=(TextView) v.findViewById(R.id.tv_single_price);
		tv_price_coast=(TextView) v.findViewById(R.id.tv_price_coast);
		tv_first_can_loan=(TextView) v.findViewById(R.id.tv_first_can_loan);
		tv_second_can_loan=(TextView) v.findViewById(R.id.tv_second_can_loan);
		tv_room_type=(TextView) v.findViewById(R.id.tv_room_type);
		tv_area=(TextView) v.findViewById(R.id.tv_area);
		tv_floor=(TextView) v.findViewById(R.id.tv_floor);
		tv_toward=(TextView) v.findViewById(R.id.tv_toward);
		tv_build_finished=(TextView) v.findViewById(R.id.tv_build_finished);
		tv_house_type=(TextView) v.findViewById(R.id.tv_house_type);
		tv_decorate=(TextView) v.findViewById(R.id.tv_decorate);
		tv_city_proper=(TextView) v.findViewById(R.id.tv_city_proper);
		tv_business=(TextView) v.findViewById(R.id.tv_business);
		tv_address=(TextView) v.findViewById(R.id.tv_address);
		tv_phone_number=(TextView) v.findViewById(R.id.tv_phone_number);
		this.addView(v);
		
		
	} 
	public void setInfo(HouseArrBean cb){
		iv_actual.setImageResource(R.mipmap.ic_launcher);
		iv_house_type.setImageResource(R.mipmap.ic_launcher);
		iv_advicer.setImageResource(R.mipmap.ic_launcher);
		tv_price.setText(cb.totalPrices==null?"/":cb.totalPrices);
		tv_building_name.setText(cb.buildSize==null?"/":cb.buildSize);
		tv_single_price.setText("核算中");
		tv_price_coast.setText("核算中");
		tv_first_can_loan.setText("核算中");
		tv_second_can_loan.setText("核算中");
		tv_room_type.setText("核算中");
		tv_area.setText("核算中");
		tv_floor.setText("核算中");
		tv_toward.setText("核算中");
		tv_build_finished.setText("核算中");
		tv_house_type.setText("核算中");
		tv_decorate.setText("核算中");
		tv_city_proper.setText("核算中");
		tv_business.setText("核算中");
		tv_address.setText("核算中");
		tv_phone_number.setText("核算中");
		
	}
	public void setInfo(HouseOneArrBean cb){
		iv_actual.setImageResource(R.mipmap.ic_launcher);
		iv_house_type.setImageResource(R.mipmap.ic_launcher);
		iv_advicer.setImageResource(R.mipmap.ic_launcher);
		tv_price.setText(cb.totalprBegin==null?"/":cb.totalprBegin);
		tv_building_name.setText(cb.buildSize==null?"/":cb.buildSize);
		tv_single_price.setText("核算中");
		tv_price_coast.setText("核算中");
		tv_first_can_loan.setText("核算中");
		tv_second_can_loan.setText("核算中");
		tv_room_type.setText("核算中");
		tv_area.setText("核算中");
		tv_floor.setText("核算中");
		tv_toward.setText("核算中");
		tv_build_finished.setText("核算中");
		tv_house_type.setText("核算中");
		tv_decorate.setText("核算中");
		tv_city_proper.setText("核算中");
		tv_business.setText("核算中");
		tv_address.setText("核算中");
		tv_phone_number.setText("核算中");

	}

}
