package com.yufeimen.application.model;

import java.util.Date;

public class MedicHarmDsa {


    private Integer id; //主键ID，自增

    private Date gmtCreated;//创建时间

    private String gmtCreator;//创建人

    private Date gmtUpdated;//更新时间，系统生成

    private String gmtUpdator;//更新人

    private String isDeleted;//N 否\nY 是

    private Integer observeId;//数字减影日常监测观察表ID

    private Integer equipmentId; //具体的机器ID   MEDIC_EQUIPMENT_DETAIL 表

    private String equipmentCategory;//机器类别，直接存中文：数字减影血管造影机......

    private Integer reportOrgId;//上报单位ID

    private String reportOrgName;//上报单位名称：辽宁省-沈阳市   沈阳市第一医院

    private String patientName;//患者姓名

    private String harmHappenTimeSelect;//不良事件发生时间，直接存中文：临床使用期间、设备日常维护监测期间

    private String harmCourseSelect;//不良事件过程描述-是否涉及造影机故障：直接存中文：是、否

    private String harmCourseDescription; //不良事件过程描述文本

    private String harmProcessSelect;//不良事件处理情况radio：直接存中文：不需处理自动恢复、重启设备后恢复、更换设备后短时暂停、手术中止维修设备

    private String harmProcessStopDuring;//不良事件处理情况中的短时暂停时间

    private String harmProcessDescription; //不良时间处理情况-处理措施具体描述

    private String harmReasonText;//事件发生初步原因分析

    private String harmEquipmentRepair;//设备维修后的情况

    private String harmLevelSelect;//不良事件严重程度radio：直接存中文：对患者无影响、一般、严重',

    private String harmTerribleLevelSelect;//不良事件严重程度-如果是严重：直接存中文：死亡、危及生命、机体功能结构永久性损伤、需要内外科治疗避免上述永久性损伤',

    private String harmResultSelect;//不良事件的结果：痊愈、有后遗症、死亡',

    private String harmResultRecoveryTime;//不良事件的结果-转归时间',

    private String harmResultSequela;//不良事件的结果-后遗症表现',

    private String harmResultDiedTime;//不良事件的结果-死亡时间',

    private String harmResultDiedReason;//不良事件的结果-直接死因',

    private String harmOriginalSelect;//对原患疾病的影响：直接存中文：不明显、病程延长、病情加重、导致后遗症、导致死亡',

    private String harmOriginalSequela;//对原患疾病的影响-后遗症表现',

    private String harmOriginalDiedTime;//对原患疾病的影响-死亡时间',

    private String harmSelfRelativeEvaluate1;//不良事件关联性评价-自评-第一项-直接存中文：是、否',

    private String harmSelfRelativeEvaluate2;//不良事件关联性评价-自评-第二项-直接存中文：是、否',

    private String harmSelfRelativeEvaluate3;//不良事件关联性评价-自评-第三项-直接存中文：是、否',

    private String harmSelfRelativeResult;//自评-与数字减影血管造影机的因果关系评价-直接存中文：很有可能、可能有关、可能无关、无法确定',

    private Integer reportorId;//报告人ID',

    private String reportorName;//'报告人名称',

    private String superiorRelativeEvaluate1;//不良事件关联性评价-上级评-第一项-直接存中文：是、否',

    private String superiorRelativeEvaluate2;//不良事件关联性评价-上级评-第二项-直接存中文：是、否',

    private String superiorRelativeEvaluate3;//不良事件关联性评价-上级评-第三项-直接存中文：是、否',

    private String superiorRelativeResult;//上级评-与数字减影血管造影机的因果关系评价-直接存中文：很有可能、可能有关、可能无关、无法确定',

    private String technologyIsHarmSituation;//上级评-监测技术机构评价意见-是否医疗器械不良事件-直接存中文：是、否',

    private String technologyProcessTip;//上级评-监测技术机构评价意见-处理意见-直接存中文：不符合报告标准、继续监测、进一步处理',

    private String technologyTipDescription;//监测技术机构评价意见-意见陈述',

    private String evaluatorName; //评价人

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public void setEvaluatorName(String evaluatorName) {
        this.evaluatorName = evaluatorName;
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

    public Integer getObserveId() {
        return observeId;
    }

    public void setObserveId(Integer observeId) {
        this.observeId = observeId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHarmHappenTimeSelect() {
        return harmHappenTimeSelect;
    }

    public void setHarmHappenTimeSelect(String harmHappenTimeSelect) {
        this.harmHappenTimeSelect = harmHappenTimeSelect;
    }

    public String getHarmCourseSelect() {
        return harmCourseSelect;
    }

    public void setHarmCourseSelect(String harmCourseSelect) {
        this.harmCourseSelect = harmCourseSelect;
    }

    public String getHarmProcessSelect() {
        return harmProcessSelect;
    }

    public void setHarmProcessSelect(String harmProcessSelect) {
        this.harmProcessSelect = harmProcessSelect;
    }

    public String getHarmProcessStopDuring() {
        return harmProcessStopDuring;
    }

    public void setHarmProcessStopDuring(String harmProcessStopDuring) {
        this.harmProcessStopDuring = harmProcessStopDuring;
    }

    public String getHarmLevelSelect() {
        return harmLevelSelect;
    }

    public void setHarmLevelSelect(String harmLevelSelect) {
        this.harmLevelSelect = harmLevelSelect;
    }

    public String getHarmTerribleLevelSelect() {
        return harmTerribleLevelSelect;
    }

    public void setHarmTerribleLevelSelect(String harmTerribleLevelSelect) {
        this.harmTerribleLevelSelect = harmTerribleLevelSelect;
    }

    public String getHarmResultSelect() {
        return harmResultSelect;
    }

    public void setHarmResultSelect(String harmResultSelect) {
        this.harmResultSelect = harmResultSelect;
    }

    public String getHarmResultRecoveryTime() {
        return harmResultRecoveryTime;
    }

    public void setHarmResultRecoveryTime(String harmResultRecoveryTime) {
        this.harmResultRecoveryTime = harmResultRecoveryTime;
    }

    public String getHarmResultDiedTime() {
        return harmResultDiedTime;
    }

    public void setHarmResultDiedTime(String harmResultDiedTime) {
        this.harmResultDiedTime = harmResultDiedTime;
    }

    public String getHarmOriginalSelect() {
        return harmOriginalSelect;
    }

    public void setHarmOriginalSelect(String harmOriginalSelect) {
        this.harmOriginalSelect = harmOriginalSelect;
    }

    public String getHarmOriginalDiedTime() {
        return harmOriginalDiedTime;
    }

    public void setHarmOriginalDiedTime(String harmOriginalDiedTime) {
        this.harmOriginalDiedTime = harmOriginalDiedTime;
    }

    public String getHarmSelfRelativeEvaluate1() {
        return harmSelfRelativeEvaluate1;
    }

    public void setHarmSelfRelativeEvaluate1(String harmSelfRelativeEvaluate1) {
        this.harmSelfRelativeEvaluate1 = harmSelfRelativeEvaluate1;
    }

    public String getHarmSelfRelativeEvaluate2() {
        return harmSelfRelativeEvaluate2;
    }

    public void setHarmSelfRelativeEvaluate2(String harmSelfRelativeEvaluate2) {
        this.harmSelfRelativeEvaluate2 = harmSelfRelativeEvaluate2;
    }

    public String getHarmSelfRelativeEvaluate3() {
        return harmSelfRelativeEvaluate3;
    }

    public void setHarmSelfRelativeEvaluate3(String harmSelfRelativeEvaluate3) {
        this.harmSelfRelativeEvaluate3 = harmSelfRelativeEvaluate3;
    }

    public String getHarmSelfRelativeResult() {
        return harmSelfRelativeResult;
    }

    public void setHarmSelfRelativeResult(String harmSelfRelativeResult) {
        this.harmSelfRelativeResult = harmSelfRelativeResult;
    }

    public String getHarmCourseDescription() {
        return harmCourseDescription;
    }

    public void setHarmCourseDescription(String harmCourseDescription) {
        this.harmCourseDescription = harmCourseDescription;
    }

    public String getHarmProcessDescription() {
        return harmProcessDescription;
    }

    public void setHarmProcessDescription(String harmProcessDescription) {
        this.harmProcessDescription = harmProcessDescription;
    }

    public String getHarmReasonText() {
        return harmReasonText;
    }

    public void setHarmReasonText(String harmReasonText) {
        this.harmReasonText = harmReasonText;
    }

    public String getHarmEquipmentRepair() {
        return harmEquipmentRepair;
    }

    public void setHarmEquipmentRepair(String harmEquipmentRepair) {
        this.harmEquipmentRepair = harmEquipmentRepair;
    }

    public String getHarmResultSequela() {
        return harmResultSequela;
    }

    public void setHarmResultSequela(String harmResultSequela) {
        this.harmResultSequela = harmResultSequela;
    }

    public String getHarmResultDiedReason() {
        return harmResultDiedReason;
    }

    public void setHarmResultDiedReason(String harmResultDiedReason) {
        this.harmResultDiedReason = harmResultDiedReason;
    }

    public String getHarmOriginalSequela() {
        return harmOriginalSequela;
    }

    public void setHarmOriginalSequela(String harmOriginalSequela) {
        this.harmOriginalSequela = harmOriginalSequela;
    }

    public String getTechnologyTipDescription() {
        return technologyTipDescription;
    }

    public void setTechnologyTipDescription(String technologyTipDescription) {
        this.technologyTipDescription = technologyTipDescription;
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

    public String getSuperiorRelativeEvaluate1() {
        return superiorRelativeEvaluate1;
    }

    public void setSuperiorRelativeEvaluate1(String superiorRelativeEvaluate1) {
        this.superiorRelativeEvaluate1 = superiorRelativeEvaluate1;
    }

    public String getSuperiorRelativeEvaluate2() {
        return superiorRelativeEvaluate2;
    }

    public void setSuperiorRelativeEvaluate2(String superiorRelativeEvaluate2) {
        this.superiorRelativeEvaluate2 = superiorRelativeEvaluate2;
    }

    public String getSuperiorRelativeEvaluate3() {
        return superiorRelativeEvaluate3;
    }

    public void setSuperiorRelativeEvaluate3(String superiorRelativeEvaluate3) {
        this.superiorRelativeEvaluate3 = superiorRelativeEvaluate3;
    }

    public String getSuperiorRelativeResult() {
        return superiorRelativeResult;
    }

    public void setSuperiorRelativeResult(String superiorRelativeResult) {
        this.superiorRelativeResult = superiorRelativeResult;
    }

    public String getTechnologyIsHarmSituation() {
        return technologyIsHarmSituation;
    }

    public void setTechnologyIsHarmSituation(String technologyIsHarmSituation) {
        this.technologyIsHarmSituation = technologyIsHarmSituation;
    }

    public String getTechnologyProcessTip() {
        return technologyProcessTip;
    }

    public void setTechnologyProcessTip(String technologyProcessTip) {
        this.technologyProcessTip = technologyProcessTip;
    }
}