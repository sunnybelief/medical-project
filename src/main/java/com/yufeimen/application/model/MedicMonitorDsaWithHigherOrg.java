package com.yufeimen.application.model;

import java.util.Date;

public class MedicMonitorDsaWithHigherOrg extends MedicMonitorDsa {

    private String higherOrgName; //上级监测机构名称

    public String getHigherOrgName() {
        return higherOrgName;
    }

    public void setHigherOrgName(String higherOrgName) {
        this.higherOrgName = higherOrgName;
    }
}