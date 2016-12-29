package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;
import com.bwf.framework.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cao_Ye on 2016/12/8.
 * 豪宅研究结果集
 */
public class StudyResultBean extends BaseBean{

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * pkid : 251
         * title : 中央别墅区商圈11月市场分析报告
         * wordPath : http://119.254.70.187/group1/M00/06/45/rBAOJ1Zzo8WEEInxAAAAAAIBZTI46.html
         * showImgPath : http://119.254.70.187/group1/M00/06/45/rBAOJ1Zzo7uEGX0zAAAAAHDx_IA320.png
         * describe : 根据丽兹行豪宅研究院统计数据显示，中央别墅区商圈11月份共成交135套，环比上涨40.63%，同比上涨145%。11月份中央别墅区各大别墅楼盘成交量数据显示，观承、新世界丽樽、誉天下涨幅较大
         * reportList : [{"pkId":"251","title":"中央别墅区商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，中央别墅区商圈11月份共成交135套，环比上涨40.63%，同比上涨145%。11月份中央别墅区各大别墅楼盘成交量数据显示，观承、新世界丽樽、誉天下涨幅较大","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOJ1Zzo8WEEInxAAAAAAIBZTI46.html","createDate":1450419140000},{"pkId":"250","title":"燕莎商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，燕莎商圈11月份共成交2套，环比下降60%，同比下降60%。燕莎商圈共6大高端住宅，各楼盘共性为总户数少，成交量低，燕莎商圈各楼盘总户数多在200户以下","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOKFZzo2mEYnTRAAAAABKMnQM18.html","createDate":1450419048000},{"pkId":"249","title":"亚奥商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，亚奥商圈11月份共成交41套，环比上涨24.24%，同比上涨41.4%。亚奥商圈11月份成交量上涨，涨幅主要为万和城、亚运新新及紫御华府成交，成交量相对稳定。","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOJ1ZzoxaEY3utAAAAAIMz_xY76.html","createDate":1450418966000},{"pkId":"248","title":"西山商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，西山商圈11月份共成交31套，环比上涨19.23%，同比下降3.1%。西山商圈三大楼盘万科如园南区二手房逐步有成交，新房在售，西山壹号院偏重自住","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOKFZzoquETfB5AAAAALCjoN437.html","createDate":1450418858000},{"pkId":"247","title":"西山别墅区商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，西山别墅区商圈11月份共成交21套，环比上涨133%，同比上涨250%。西山别墅区向来成交量持续处于低位，自住率高，挂牌出售量低","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOJ1ZzolqEPhv_AAAAAOmXvME79.html","createDate":1450418778000},{"pkId":"246","title":"望京商圈11月份市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，望京商圈11月份共成交17套，环比下降29.17%，同比下降70.18%。11月份望京商圈仅臻园、万和公馆及CLASS有成交","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOKFZzofiEWhTiAAAAAOhQYzg40.html","createDate":1450418680000},{"pkId":"245","title":"万柳商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，万柳商圈11月份共成交15套，环比上涨25%，同比上涨15.38%。11月份万柳书院没有成交，整个万柳商圈各楼盘成交量均处于低基数水平","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOJ1ZzoauEID_sAAAAAOBRhNg53.html","createDate":1450418603000},{"pkId":"244","title":"唐宁one 11月份市场分析报告","describe":"根据北京交易管理网统计数据，唐宁one11月份成4套（不含1层商业），环比减少1套，唐宁one住宅与商业立项成交量分别为1套及3套。 ","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOKFZzoWqEelZWAAAAAAGMgWg99.html","createDate":1450418538000},{"pkId":"243","title":"太阳宫商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，太阳宫商圈11月份共成交45套，环比上涨36.36%，同比上涨104%。凤凰城以单月18套成交量位居前列，红玺台新房集中成交","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOKFZzoR6Eae7vAAAAAA7gktk94.html","createDate":1450418461000},{"pkId":"242","title":"丽都商圈11月市场分析报告","describe":"根据丽兹行豪宅研究院统计数据显示，丽都商圈11月份共成交16套，环比下降27.27%，同比上涨60%。丽都水岸成交量稳定、阳光上东成交量起伏来看，呈现倒V趋势。","wordPath":"http://119.254.70.187/group1/M00/06/45/rBAOJ1ZzoMOEfC_cAAAAAMh0ces45.html","createDate":1450418370000}]
         */

        private String pkid;
        private String title;
        private String wordPath;
        private String showImgPath;
        private String describe;
        private List<ReportListBean> reportList;

        public String getPkid() {
            return pkid;
        }

        public void setPkid(String pkid) {
            this.pkid = pkid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWordPath() {
            return wordPath;
        }

        public void setWordPath(String wordPath) {
            this.wordPath = wordPath;
        }

        public String getShowImgPath() {
            return showImgPath;
        }

        public void setShowImgPath(String showImgPath) {
            this.showImgPath = showImgPath;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public List<ReportListBean> getReportList() {
            return reportList;
        }

        public void setReportList(List<ReportListBean> reportList) {
            this.reportList = reportList;
        }

        public static class ReportListBean implements Serializable{
            /**
             * pkId : 251
             * title : 中央别墅区商圈11月市场分析报告
             * describe : 根据丽兹行豪宅研究院统计数据显示，中央别墅区商圈11月份共成交135套，环比上涨40.63%，同比上涨145%。11月份中央别墅区各大别墅楼盘成交量数据显示，观承、新世界丽樽、誉天下涨幅较大
             * wordPath : http://119.254.70.187/group1/M00/06/45/rBAOJ1Zzo8WEEInxAAAAAAIBZTI46.html
             * createDate : 1450419140000
             */

            private String pkId;
            private String title;
            private String describe;
            private String wordPath;
            private long createDate;

            public String getPkId() {
                return pkId;
            }

            public void setPkId(String pkId) {
                this.pkId = pkId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getWordPath() {
                return wordPath;
            }

            public void setWordPath(String wordPath) {
                this.wordPath = wordPath;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }
        }
    }
}
