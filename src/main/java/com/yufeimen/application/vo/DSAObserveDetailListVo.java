package com.yufeimen.application.vo;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class DSAObserveDetailListVo {

    private List<DSAObserveDetailItemVo> reportList;  //DSA监测观察表列表
    private int total;    //总数

    public List<DSAObserveDetailItemVo> getReportList() {
        return reportList;
    }

    public void setReportList(List<DSAObserveDetailItemVo> reportList) {
        this.reportList = reportList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
