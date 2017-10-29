package com.yufeimen.application.vo;

import java.util.Date;

/**
 * Created by roper on 2017/6/9.
 */
public class DSAObserveDetailItemVo {

    private Integer id;
    private Date gmtCreated;
    private String gmtCreator;
    private Date gmtUpdated;
    private String gmtUpdator;
    private String isDeleted;
    private Integer accidentId;  //数字减影不良事件观察表ID
    private String status;       //报告状态直接存中文：暂存、待评价、已评价、市级已退回、省级已退回
    private String hasAccident;  //是否发生不良事件：N 否\nY 是
    private Integer equipmentId; //具体的机器ID   MEDIC_EQUIPMENT_DETAIL 表
    private String EquipmentProductNumber;//具体医疗器械编号
    private String EquipmentRegistNumber; //具体医疗器械的注册证号

    private String equipmentCategory; //机器类别，直接存中文：数字减影血管造影机......
    private Integer reportOrgId;     //上报单位ID
    private String reportOrgName;    //上报单位名称：辽宁省-沈阳市 沈阳市第一医院
    private String flowLog; // 审批流程日志JSON:  [{stepName:XXX市级中心审阅通过提交省级, stepDate:2017-09-13},{}]
    private String evaluateBrief; //评价意见
    private String usedOfficeSelect; //使用科室：直接存中文：介入科导管室、影像科放射科、其他
    private String usedOfficeOther; //使用科室-其他
    private String patientName; //患者姓名
    private Integer patientSex; //0 女\n1 男
    private String patientAgeSelect; //患者年龄：直接存中文：年龄、出生日期
    private Integer patientAgeY; //年龄选项：23  不论年龄选项是否选择，这一项都要实时计算录入，数据统计会用到
    private String patientAgeU; //年龄单位：岁、周，月   不论年龄选项是否选择，这一项都要实时计算录入，数据统计会用到
    private String patientAgeB; //出生日期选项：2017-09-18
    private String checkInNumber; //住院号
    private String diagnosis; //临床诊断
    private String operationName; //手术名称
    private String equipmentExposure; //曝光量
    private String equipmentDuration; //使用时长
    private String equipmentStartTime; //开始时间
    private String equipmentEndTime; //结束时间
    private String injectorName; //注射器设备名称
    private String injectorFactory; //注射器生产企业
    private String injectorSpec;  //注射器规格型号
    private String injectorHowUse;  //注射器使用方法
    private String injectorStartTime; //开始时间
    private String injectorEndTime; //结束时间
    private String accidentDesktopSelect; //造影机操作台：直接存中文：无法启动、跳闸自动关机、高温报警、启动后无法工作、其他
    private String accidentDesktopOther; //造影机操作台其他
    private String accidentCSelect; //C型臂球馆：直接存中文：无法移动旋转、球管过热、球管出现打火现象、无射线不曝光、光圈异常、其他
    private String accidentCOther; //C型臂球馆其他
    private String accidentImgSelect; //图像情况：直接存中文：图像不能保存、有射线无图像、出现伪影、出现亮线、图像模糊难以识别、其他
    private String accidentImgOther;  //图像情况其他
    private String accidentBedSelect; //手术床：直接存中文：床面断电、无法移动、不能锁定、突然解锁移动、输液架无法固定
    private String accidentPartSelect;  //设备部件：直接存中文：防护铅板脱落、C型臂脱落、其他
    private String accidentPartOther; //设备部件其他
    private String accident6Select; //6：直接存中文：医患出现明显的射线灼伤，其他
    private String accident6Other; //6其他
    private String accident7Select; //7：直接存中文：机器卷入异物无法正常运转，其他
    private String accident7Other; //7其他
    private Integer reportorId; //报告人ID
    private String reportorName;  //报告人名称

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

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasAccident() {
        return hasAccident;
    }

    public void setHasAccident(String hasAccident) {
        this.hasAccident = hasAccident;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentProductNumber() {
        return EquipmentProductNumber;
    }

    public void setEquipmentProductNumber(String equipmentProductNumber) {
        EquipmentProductNumber = equipmentProductNumber;
    }

    public String getEquipmentRegistNumber() {
        return EquipmentRegistNumber;
    }

    public void setEquipmentRegistNumber(String equipmentRegistNumber) {
        EquipmentRegistNumber = equipmentRegistNumber;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public Integer getReportOrgId() {
        return reportOrgId;
    }

    public void setReportOrgId(Integer reportOrgId) {
        this.reportOrgId = reportOrgId;
    }

    public String getReportOrgName() {
        return reportOrgName;
    }

    public void setReportOrgName(String reportOrgName) {
        this.reportOrgName = reportOrgName;
    }

    public String getFlowLog() {
        return flowLog;
    }

    public void setFlowLog(String flowLog) {
        this.flowLog = flowLog;
    }

    public String getEvaluateBrief() {
        return evaluateBrief;
    }

    public void setEvaluateBrief(String evaluateBrief) {
        this.evaluateBrief = evaluateBrief;
    }

    public String getUsedOfficeSelect() {
        return usedOfficeSelect;
    }

    public void setUsedOfficeSelect(String usedOfficeSelect) {
        this.usedOfficeSelect = usedOfficeSelect;
    }

    public String getUsedOfficeOther() {
        return usedOfficeOther;
    }

    public void setUsedOfficeOther(String usedOfficeOther) {
        this.usedOfficeOther = usedOfficeOther;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAgeSelect() {
        return patientAgeSelect;
    }

    public void setPatientAgeSelect(String patientAgeSelect) {
        this.patientAgeSelect = patientAgeSelect;
    }

    public Integer getPatientAgeY() {
        return patientAgeY;
    }

    public void setPatientAgeY(Integer patientAgeY) {
        this.patientAgeY = patientAgeY;
    }

    public String getPatientAgeU() {
        return patientAgeU;
    }

    public void setPatientAgeU(String patientAgeU) {
        this.patientAgeU = patientAgeU;
    }

    public String getPatientAgeB() {
        return patientAgeB;
    }

    public void setPatientAgeB(String patientAgeB) {
        this.patientAgeB = patientAgeB;
    }

    public String getCheckInNumber() {
        return checkInNumber;
    }

    public void setCheckInNumber(String checkInNumber) {
        this.checkInNumber = checkInNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getEquipmentExposure() {
        return equipmentExposure;
    }

    public void setEquipmentExposure(String equipmentExposure) {
        this.equipmentExposure = equipmentExposure;
    }

    public String getEquipmentDuration() {
        return equipmentDuration;
    }

    public void setEquipmentDuration(String equipmentDuration) {
        this.equipmentDuration = equipmentDuration;
    }

    public String getEquipmentStartTime() {
        return equipmentStartTime;
    }

    public void setEquipmentStartTime(String equipmentStartTime) {
        this.equipmentStartTime = equipmentStartTime;
    }

    public String getEquipmentEndTime() {
        return equipmentEndTime;
    }

    public void setEquipmentEndTime(String equipmentEndTime) {
        this.equipmentEndTime = equipmentEndTime;
    }

    public String getInjectorName() {
        return injectorName;
    }

    public void setInjectorName(String injectorName) {
        this.injectorName = injectorName;
    }

    public String getInjectorFactory() {
        return injectorFactory;
    }

    public void setInjectorFactory(String injectorFactory) {
        this.injectorFactory = injectorFactory;
    }

    public String getInjectorSpec() {
        return injectorSpec;
    }

    public void setInjectorSpec(String injectorSpec) {
        this.injectorSpec = injectorSpec;
    }

    public String getInjectorHowUse() {
        return injectorHowUse;
    }

    public void setInjectorHowUse(String injectorHowUse) {
        this.injectorHowUse = injectorHowUse;
    }

    public String getInjectorStartTime() {
        return injectorStartTime;
    }

    public void setInjectorStartTime(String injectorStartTime) {
        this.injectorStartTime = injectorStartTime;
    }

    public String getInjectorEndTime() {
        return injectorEndTime;
    }

    public void setInjectorEndTime(String injectorEndTime) {
        this.injectorEndTime = injectorEndTime;
    }

    public String getAccidentDesktopSelect() {
        return accidentDesktopSelect;
    }

    public void setAccidentDesktopSelect(String accidentDesktopSelect) {
        this.accidentDesktopSelect = accidentDesktopSelect;
    }

    public String getAccidentDesktopOther() {
        return accidentDesktopOther;
    }

    public void setAccidentDesktopOther(String accidentDesktopOther) {
        this.accidentDesktopOther = accidentDesktopOther;
    }

    public String getAccidentCSelect() {
        return accidentCSelect;
    }

    public void setAccidentCSelect(String accidentCSelect) {
        this.accidentCSelect = accidentCSelect;
    }

    public String getAccidentCOther() {
        return accidentCOther;
    }

    public void setAccidentCOther(String accidentCOther) {
        this.accidentCOther = accidentCOther;
    }

    public String getAccidentImgSelect() {
        return accidentImgSelect;
    }

    public void setAccidentImgSelect(String accidentImgSelect) {
        this.accidentImgSelect = accidentImgSelect;
    }

    public String getAccidentImgOther() {
        return accidentImgOther;
    }

    public void setAccidentImgOther(String accidentImgOther) {
        this.accidentImgOther = accidentImgOther;
    }

    public String getAccidentBedSelect() {
        return accidentBedSelect;
    }

    public void setAccidentBedSelect(String accidentBedSelect) {
        this.accidentBedSelect = accidentBedSelect;
    }

    public String getAccidentPartSelect() {
        return accidentPartSelect;
    }

    public void setAccidentPartSelect(String accidentPartSelect) {
        this.accidentPartSelect = accidentPartSelect;
    }

    public String getAccidentPartOther() {
        return accidentPartOther;
    }

    public void setAccidentPartOther(String accidentPartOther) {
        this.accidentPartOther = accidentPartOther;
    }

    public String getAccident6Select() {
        return accident6Select;
    }

    public void setAccident6Select(String accident6Select) {
        this.accident6Select = accident6Select;
    }

    public String getAccident6Other() {
        return accident6Other;
    }

    public void setAccident6Other(String accident6Other) {
        this.accident6Other = accident6Other;
    }

    public String getAccident7Select() {
        return accident7Select;
    }

    public void setAccident7Select(String accident7Select) {
        this.accident7Select = accident7Select;
    }

    public String getAccident7Other() {
        return accident7Other;
    }

    public void setAccident7Other(String accident7Other) {
        this.accident7Other = accident7Other;
    }

    public Integer getReportorId() {
        return reportorId;
    }

    public void setReportorId(Integer reportorId) {
        this.reportorId = reportorId;
    }

    public String getReportorName() {
        return reportorName;
    }

    public void setReportorName(String reportorName) {
        this.reportorName = reportorName;
    }
}
