package com.bwf.p1_landz.entity;


import com.bwf.framework.base.BaseBean;

import java.util.List;

/**
 * Created by caoye on 2016/7/27 0027.
 * Description: 本房顾问bean
 */
public class GuWenResultBean extends BaseBean {

    public GuWenBean result;

    public class GuWenBean {
        public String totalAmount;//熟悉本房的顾问总数
        public List<ShowArr> showArr;//顾问列表

        @Override
        public String toString() {
            return "GuWenBean{" +
                    "totalAmount='" + totalAmount + '\'' +
                    ", showArr=" + showArr +
                    '}';
        }
    }

    public class ShowArr {
//        "resourceId":"f1292",
//                "showStartTime":1469359200000,
//                "createdTime":1469359200000,
//                "createdBy":"125720",
//                "createName":"吴波",
//                "phone":"18710286670",
//                "photo":"http://119.254.70.187/group1/M00/07/59/rBAOJ1dMXnKAIRKbAAScBkGh52s718.jpg",
//                "inductionDate":"2年11个月",
//                "totalShowing":12

        public String resourceId;//id
        public String showStartTime;
        public String createdTime;
        public String createdBy;
        public String createName;//顾问姓名
        public String phone;//电话
        public String photo;//头像
        public String inductionDate;//从业时间
        public String totalShowing;//本房看了多少次

        @Override
        public String toString() {
            return "ShowArr{" +
                    "resourceId='" + resourceId + '\'' +
                    ", showStartTime='" + showStartTime + '\'' +
                    ", createdTime='" + createdTime + '\'' +
                    ", createdBy='" + createdBy + '\'' +
                    ", createName='" + createName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", photo='" + photo + '\'' +
                    ", inductionDate='" + inductionDate + '\'' +
                    ", totalShowing='" + totalShowing + '\'' +
                    '}';
        }
    }

}
