package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cao_ye on 2016/7/19 0019.
 * Description:
 */
public class OnLineHouseResult extends BaseBean{

    public OnLineHouseResultBean result;

    /**
     * 合并两种集合
     *
     */
    public List<Object> initListData() {
        if (this != null) {
            List<HouseArrBean> houseArr = this.result.houseArr;
            List<HouseOneArrBean> houseOneArr = this.result.houseOneArr;

            List<Object> newTotalList = new ArrayList<>();
            //合并两种类型的集合
            if (houseArr != null && !houseArr.isEmpty())
                newTotalList.addAll(houseArr);
            if (houseOneArr != null && !houseOneArr.isEmpty())
                newTotalList.addAll(houseOneArr);

            Collections.sort(newTotalList, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {

                    int a, b = 0;

                    if (o1 instanceof HouseArrBean)
                        a = Integer.parseInt(((HouseArrBean) o1).sortNum);
                    else
                        a = Integer.parseInt(((HouseOneArrBean) o1).sortNum);

                    if (o2 instanceof HouseArrBean)
                        b = Integer.parseInt(((HouseArrBean) o2).sortNum);
                    else
                        b = Integer.parseInt(((HouseOneArrBean) o2).sortNum);

                    if (a > b)//前面大于后面的返回正数
                        return 1;
                    if (a < b)//前面小于后面的返回负数
                        return -1;

                    return 0;
                }
            });
            return  newTotalList;
        }
        return  null;
    }

    @Override
    public String toString() {
        return "OnLineHouseResult{" +
                "result=" + result +
                '}';
    }
}
