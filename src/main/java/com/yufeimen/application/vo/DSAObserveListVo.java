package com.yufeimen.application.vo;

import com.yufeimen.application.model.MedicMonitorDsa;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class DSAObserveListVo {

    private List<MedicMonitorDsa> reportList;  //DSA监测观察表列表
    private int total;    //总数

    public List<MedicMonitorDsa> getReportList() {
        return reportList;
    }

    public void setReportList(List<MedicMonitorDsa> reportList) {
        this.reportList = reportList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
