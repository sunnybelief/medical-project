package com.yufeimen.application.vo;

import com.yufeimen.application.model.MedicMonitorDsa;
import com.yufeimen.application.model.MedicMonitorDsaWithHigherOrg;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class QualityDSAObserveListVo {

    private List<MedicMonitorDsaWithHigherOrg> reportList;  //DSA监测观察表列表
    private int total;    //总数

    public List<MedicMonitorDsaWithHigherOrg> getReportList() {
        return reportList;
    }

    public void setReportList(List<MedicMonitorDsaWithHigherOrg> reportList) {
        this.reportList = reportList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
