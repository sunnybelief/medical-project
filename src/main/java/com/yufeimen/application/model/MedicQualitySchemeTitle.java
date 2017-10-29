package com.yufeimen.application.model;

import java.util.Date;

public class MedicQualitySchemeTitle {
    private Integer id;

    private Date gmtCreated;

    private String gmtCreator;

    private Date gmtUpdated;

    private String gmtUpdator;

    private String isDeleted;

    private String name; //方案名称

    private String equipmentCategory; //机器类别，直接存中文：数字减影血管造影机......

    private Integer totalGrade; //'总分值'

    private String schemeMaker; //'方案制定人',

    private String samplingMaker; //'抽样人',

    private String samplingStatus;//DEFAULT '未抽样' COMMENT '抽样状态： 已抽样、未抽样',

    private String evaluateStatus; // DEFAULT '未完成' COMMENT '质量评估状态： 已完成、未完成',

    private String ruleGradeJSON; // 自定义评分项和分值：[{itemName:'医院名称',itemInfo:'评分项说明',itemRuleGrade:'评分项设定分值'，itemHandGrade:'评分项打分分数'},{},{},...]

    private String monitorIds; //'抽取的监测观察表ID序列(逗号隔开)：1,2,3,4',

    private Integer sampleCount; //抽样总数

    private String sampleRangeDuring;//抽样的时间范围：2017年09月25日-2017年09月30日

    private String sampleRangeHarmOrNot;//是否发生不良事件：ALL，Y，N

    private Integer sampleRangeOrg;//抽取样本的监测机构范围：-1全部、市级机构ID

    private String sampleRangeOrgName; // 抽取样本的监测机构名称

    private String extraInfo; // '预留字段：作为方案说明',


    public String getSampleRangeOrgName() {
        return sampleRangeOrgName;
    }

    public void setSampleRangeOrgName(String sampleRangeOrgName) {
        this.sampleRangeOrgName = sampleRangeOrgName;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getSampleRangeDuring() {
        return sampleRangeDuring;
    }

    public void setSampleRangeDuring(String sampleRangeDuring) {
        this.sampleRangeDuring = sampleRangeDuring;
    }

    public String getSampleRangeHarmOrNot() {
        return sampleRangeHarmOrNot;
    }

    public void setSampleRangeHarmOrNot(String sampleRangeHarmOrNot) {
        this.sampleRangeHarmOrNot = sampleRangeHarmOrNot;
    }

    public Integer getSampleRangeOrg() {
        return sampleRangeOrg;
    }

    public void setSampleRangeOrg(Integer sampleRangeOrg) {
        this.sampleRangeOrg = sampleRangeOrg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getGmtCreator() {
        return gmtCreator;
    }

    public void setGmtCreator(String gmtCreator) {
        this.gmtCreator = gmtCreator;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }

    public String getGmtUpdator() {
        return gmtUpdator;
    }

    public void setGmtUpdator(String gmtUpdator) {
        this.gmtUpdator = gmtUpdator;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public Integer getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Integer totalGrade) {
        this.totalGrade = totalGrade;
    }

    public String getSchemeMaker() {
        return schemeMaker;
    }

    public void setSchemeMaker(String schemeMaker) {
        this.schemeMaker = schemeMaker;
    }

    public String getSamplingMaker() {
        return samplingMaker;
    }

    public void setSamplingMaker(String samplingMaker) {
        this.samplingMaker = samplingMaker;
    }

    public String getSamplingStatus() {
        return samplingStatus;
    }

    public void setSamplingStatus(String samplingStatus) {
        this.samplingStatus = samplingStatus;
    }

    public String getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(String evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public String getRuleGradeJSON() {
        return ruleGradeJSON;
    }

    public void setRuleGradeJSON(String ruleGradeJSON) {
        this.ruleGradeJSON = ruleGradeJSON;
    }

    public String getMonitorIds() {
        return monitorIds;
    }

    public void setMonitorIds(String monitorIds) {
        this.monitorIds = monitorIds;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}