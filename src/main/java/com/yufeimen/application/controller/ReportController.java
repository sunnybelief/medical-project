package com.yufeimen.application.controller;

import com.yufeimen.application.model.MedicHarmDsa;
import com.yufeimen.application.model.MedicMonitorDsa;
import com.yufeimen.application.service.ReportServiceFacade;
import com.yufeimen.application.vo.DSAObserveDetailListVo;
import com.yufeimen.application.vo.DSAObserveListVo;
import com.yufeimen.application.vo.report2IdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class ReportController {

    @Autowired
    private ReportServiceFacade reportServiceFacade;

    /**
     * 提交一条DSA数字减影血管造影机监测表和不良时间报告表数据：如果observeId accidentId存在，则更新；如果不存在，则插入该记录
     *
     * @return 返回插入的数据的ID, 即监测表ID
     */
    @ResponseBody
    @RequestMapping(value = "/submit/DSA/report")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public report2IdVo submitDSAReport(
            @RequestParam(value = "observeId", required = false) Integer observeId,
            @RequestParam(value = "isDeleted", required = false, defaultValue = "N") String isDeleted,
            @RequestParam(value = "accidentId", required = false) Integer accidentId,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "hasAccident") String hasAccident,
            @RequestParam(value = "equipmentId", required = false) Integer equipmentId,
            @RequestParam(value = "equipmentCategory") String equipmentCategory,
            @RequestParam(value = "reportOrgId") Integer reportOrgId,
            @RequestParam(value = "reportOrgName") String reportOrgName,
            @RequestParam(value = "flowLog", required = false) String flowLog,
            @RequestParam(value = "evaluateBrief", required = false) String evaluateBrief,
            @RequestParam(value = "usedOfficeSelect", required = false) String usedOfficeSelect,
            @RequestParam(value = "usedOfficeOther", required = false) String usedOfficeOther,
            @RequestParam(value = "patientName", required = false) String patientName,
            @RequestParam(value = "patientSex", required = false) Integer patientSex,
            @RequestParam(value = "patientAgeSelect", required = false) String patientAgeSelect,
            @RequestParam(value = "patientAgeY", required = false) Integer patientAgeY,
            @RequestParam(value = "patientAgeU", required = false) String patientAgeU,
            @RequestParam(value = "patientAgeB", required = false) String patientAgeB,
            @RequestParam(value = "checkInNumber", required = false) String checkInNumber,
            @RequestParam(value = "diagnosis", required = false) String diagnosis,
            @RequestParam(value = "operationName", required = false) String operationName,
            @RequestParam(value = "equipmentExposure", required = false) String equipmentExposure,
            @RequestParam(value = "equipmentDuration", required = false) String equipmentDuration,
            @RequestParam(value = "equipmentStartTime", required = false) String equipmentStartTime,
            @RequestParam(value = "equipmentEndTime", required = false) String equipmentEndTime,
            @RequestParam(value = "injectorName", required = false) String injectorName,
            @RequestParam(value = "injectorFactory", required = false) String injectorFactory,
            @RequestParam(value = "injectorSpec", required = false) String injectorSpec,
            @RequestParam(value = "injectorHowUse", required = false) String injectorHowUse,
            @RequestParam(value = "injectorStartTime", required = false) String injectorStartTime,
            @RequestParam(value = "injectorEndTime", required = false) String injectorEndTime,
            @RequestParam(value = "accidentDesktopSelect", required = false) String accidentDesktopSelect,
            @RequestParam(value = "accidentDesktopOther", required = false) String accidentDesktopOther,
            @RequestParam(value = "accidentCSelect", required = false) String accidentCSelect,
            @RequestParam(value = "accidentCOther", required = false) String accidentCOther,
            @RequestParam(value = "accidentImgSelect", required = false) String accidentImgSelect,
            @RequestParam(value = "accidentImgOther", required = false) String accidentImgOther,
            @RequestParam(value = "accidentBedSelect", required = false) String accidentBedSelect,
            @RequestParam(value = "accidentPartSelect", required = false) String accidentPartSelect,
            @RequestParam(value = "accidentPartOther", required = false) String accidentPartOther,
            @RequestParam(value = "accident6Select", required = false) String accident6Select,
            @RequestParam(value = "accident6Other", required = false) String accident6Other,
            @RequestParam(value = "accident7Select", required = false) String accident7Select,
            @RequestParam(value = "accident7Other", required = false) String accident7Other,
            @RequestParam(value = "reportorId") Integer reportorId,
            @RequestParam(value = "reportorName") String reportorName,

            @RequestParam(value = "harmHappenTimeSelect", required = false) String harmHappenTimeSelect,
            @RequestParam(value = "harmCourseSelect", required = false) String harmCourseSelect,
            @RequestParam(value = "harmCourseDescription", required = false) String harmCourseDescription,
            @RequestParam(value = "harmProcessSelect", required = false) String harmProcessSelect,
            @RequestParam(value = "harmProcessStopDuring", required = false) String harmProcessStopDuring,
            @RequestParam(value = "harmProcessDescription", required = false) String harmProcessDescription,
            @RequestParam(value = "harmReasonText", required = false) String harmReasonText,
            @RequestParam(value = "harmEquipmentRepair", required = false) String harmEquipmentRepair,
            @RequestParam(value = "harmLevelSelect", required = false) String harmLevelSelect,
            @RequestParam(value = "harmTerribleLevelSelect", required = false) String harmTerribleLevelSelect,
            @RequestParam(value = "harmResultSelect", required = false) String harmResultSelect,
            @RequestParam(value = "harmResultRecoveryTime", required = false) String harmResultRecoveryTime,
            @RequestParam(value = "harmResultSequela", required = false) String harmResultSequela,
            @RequestParam(value = "harmResultDiedTime", required = false) String harmResultDiedTime,
            @RequestParam(value = "harmResultDiedReason", required = false) String harmResultDiedReason,
            @RequestParam(value = "harmOriginalSelect", required = false) String harmOriginalSelect,
            @RequestParam(value = "harmOriginalSequela", required = false) String harmOriginalSequela,
            @RequestParam(value = "harmOriginalDiedTime", required = false) String harmOriginalDiedTime,
            @RequestParam(value = "harmSelfRelativeEvaluate1", required = false) String harmSelfRelativeEvaluate1,
            @RequestParam(value = "harmSelfRelativeEvaluate2", required = false) String harmSelfRelativeEvaluate2,
            @RequestParam(value = "harmSelfRelativeEvaluate3", required = false) String harmSelfRelativeEvaluate3,
            @RequestParam(value = "harmSelfRelativeResult", required = false) String harmSelfRelativeResult,
            HttpServletRequest request,
            HttpServletResponse response) {

        MedicMonitorDsa observeValue = new MedicMonitorDsa();
        MedicHarmDsa harmValue = new MedicHarmDsa();
        observeValue.setId(observeId);
        observeValue.setGmtCreated(new Date());
        observeValue.setGmtCreator(reportorName);
        observeValue.setGmtUpdated(new Date());
        observeValue.setGmtUpdator(reportorName);
        observeValue.setIsDeleted(isDeleted);
        observeValue.setAccidentId(accidentId);
        observeValue.setStatus(status);
        observeValue.setHasAccident(hasAccident);
        observeValue.setEquipmentId(equipmentId);
        observeValue.setEquipmentCategory(equipmentCategory);
        observeValue.setReportOrgId(reportOrgId);
        observeValue.setReportOrgName(reportOrgName);
        observeValue.setEvaluateBrief(evaluateBrief);
        observeValue.setUsedOfficeSelect(usedOfficeSelect);
        observeValue.setUsedOfficeOther(usedOfficeOther);
        observeValue.setPatientName(patientName);
        observeValue.setPatientSex(patientSex);
        observeValue.setPatientAgeSelect(patientAgeSelect);
        observeValue.setPatientAgeY(patientAgeY);
        observeValue.setPatientAgeU(patientAgeU);
        observeValue.setPatientAgeB(patientAgeB);
        observeValue.setCheckInNumber(checkInNumber);
        observeValue.setDiagnosis(diagnosis);
        observeValue.setOperationName(operationName);
        observeValue.setEquipmentExposure(equipmentExposure);
        observeValue.setEquipmentDuration(equipmentDuration);
        observeValue.setEquipmentStartTime(equipmentStartTime);
        observeValue.setEquipmentEndTime(equipmentEndTime);
        observeValue.setInjectorName(injectorName);
        observeValue.setInjectorFactory(injectorFactory);
        observeValue.setInjectorSpec(injectorSpec);
        observeValue.setInjectorHowUse(injectorHowUse);
        observeValue.setInjectorStartTime(injectorStartTime);
        observeValue.setInjectorEndTime(injectorEndTime);
        observeValue.setAccidentDesktopSelect(accidentDesktopSelect);
        observeValue.setAccidentDesktopOther(accidentDesktopOther);
        observeValue.setAccidentCSelect(accidentCSelect);
        observeValue.setAccidentCOther(accidentCOther);
        observeValue.setAccidentImgSelect(accidentImgSelect);
        observeValue.setAccidentImgOther(accidentImgOther);
        observeValue.setAccidentBedSelect(accidentBedSelect);
        observeValue.setAccidentPartSelect(accidentPartSelect);
        observeValue.setAccidentPartOther(accidentPartOther);
        observeValue.setAccident6Select(accident6Select);
        observeValue.setAccident6Other(accident6Other);
        observeValue.setAccident7Select(accident7Select);
        observeValue.setAccident7Other(accident7Other);
        observeValue.setReportorId(reportorId);
        observeValue.setReportorName(reportorName);

        if (hasAccident.equalsIgnoreCase("Y")) {
            harmValue.setId(accidentId);
            harmValue.setGmtCreated(new Date());
            harmValue.setGmtCreator(reportorName);
            harmValue.setGmtUpdated(new Date());
            harmValue.setGmtUpdator(reportorName);
            harmValue.setIsDeleted(isDeleted);
            harmValue.setObserveId(observeId);
            harmValue.setEquipmentId(equipmentId);
            harmValue.setEquipmentCategory(equipmentCategory);
            harmValue.setReportOrgId(reportOrgId);
            harmValue.setReportOrgName(reportOrgName);
            harmValue.setPatientName(patientName);
            harmValue.setHarmHappenTimeSelect(harmHappenTimeSelect);
            harmValue.setHarmCourseSelect(harmCourseSelect);
            harmValue.setHarmCourseDescription(harmCourseDescription);
            harmValue.setHarmProcessSelect(harmProcessSelect);
            harmValue.setHarmProcessStopDuring(harmProcessStopDuring);
            harmValue.setHarmProcessDescription(harmProcessDescription);
            harmValue.setHarmReasonText(harmReasonText);
            harmValue.setHarmEquipmentRepair(harmEquipmentRepair);
            harmValue.setHarmLevelSelect(harmLevelSelect);
            harmValue.setHarmTerribleLevelSelect(harmTerribleLevelSelect);
            harmValue.setHarmResultSelect(harmResultSelect);
            harmValue.setHarmResultRecoveryTime(harmResultRecoveryTime);
            harmValue.setHarmResultSequela(harmResultSequela);
            harmValue.setHarmResultDiedTime(harmResultDiedTime);
            harmValue.setHarmResultDiedReason(harmResultDiedReason);
            harmValue.setHarmOriginalSelect(harmOriginalSelect);
            harmValue.setHarmOriginalSequela(harmOriginalSequela);
            harmValue.setHarmOriginalDiedTime(harmOriginalDiedTime);
            harmValue.setHarmSelfRelativeEvaluate1(harmSelfRelativeEvaluate1);
            harmValue.setHarmSelfRelativeEvaluate2(harmSelfRelativeEvaluate2);
            harmValue.setHarmSelfRelativeEvaluate3(harmSelfRelativeEvaluate3);
            harmValue.setHarmSelfRelativeResult(harmSelfRelativeResult);
            harmValue.setReportorId(reportorId);
            harmValue.setReportorName(reportorName);
        }

        return reportServiceFacade.submitDSAObserveReport(observeValue, harmValue, flowLog);
    }

    /**
     * 返回暂存的报告列表：
     * 1、如果 orgLevel == HOSPITAL ， 则返回当前机构ordId对应的所有暂存报告
     * 2、如果 orgLevel != HOSPITAL，  说明是省级或者市级代替医院提交的报告，则查出当前机构对应的所有账号，在监测表中查询所有含有这些上报账号的暂存报告
     *
     * @param reportor
     * @param hasAccident
     * @param startDate
     * @param endDate
     * @param pageStart
     * @param pageSize
     * @param orgId       当前机构ID
     * @param orgLevel    当前机构Name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/DSA/observe/temp/report")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    DSAObserveDetailListVo queryDSAObserveTempReport(@RequestParam(value = "reportor", required = false, defaultValue = "") String reportor,
                                                     @RequestParam(value = "hasAccident") String hasAccident,
                                                     @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                     @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                     @RequestParam(value = "pageStart") Integer pageStart,
                                                     @RequestParam(value = "pageSize") Integer pageSize,
                                                     @RequestParam(value = "orgId") Integer orgId,
                                                     @RequestParam(value = "orgLevel") String orgLevel,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {
        return reportServiceFacade.queryDSAObserveTempReport(reportor, hasAccident, startDate, endDate, pageStart, pageSize, orgId, orgLevel);
    }

    /**
     * 目前是删除全部的暂存报告,需要修改
     */
    @ResponseBody
    @RequestMapping(value = "/delete/all/DSA/temp/report")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean deleteAllDSATempReport(HttpServletRequest request,
                                   HttpServletResponse response) {
        return reportServiceFacade.deleteAllDSATempReport();
    }

    /**
     * 删除指定ID的监测表和不良事件表
     *
     * @param observeId
     * @param accidentId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/DSA/report/by/observeIdAndAccidentId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean deleteDSAReportByObserveIdAndAccidentId(@RequestParam(value = "observeId") Integer observeId,
                                                    @RequestParam(value = "accidentId") Integer accidentId,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        reportServiceFacade.deleteDSAReportByObserveIdAndAccidentId(observeId, accidentId);
        return true;
    }

    /**
     * 查询指定ID的监测表数据
     *
     * @param observeId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/DSA/report/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    MedicMonitorDsa queryDSAReportById(@RequestParam(value = "observeId") Integer observeId,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        return reportServiceFacade.queryDSAReportById(observeId);
    }

    /**
     * 查询指定ID的不良事件表数据
     *
     * @param accidentId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/DSA/harm/report/by/accidentId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    MedicHarmDsa queryDSAHarmReportById(@RequestParam(value = "accidentId") Integer accidentId,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        return reportServiceFacade.queryDSAHarmReportById(accidentId);
    }

    /**
     * 返回已上报的报告，包括 待评价、已评价、市级已退回、省级已退回
     * 1、如果 orgLevel == HOSPITAL ， 则返回当前机构ordId对应的所有已上报报告
     * 2、如果 orgLevel != HOSPITAL，  说明是省级或者市级代替医院提交的报告，则查出当前机构对应的所有账号，在监测表中查询所有含有这些上报账号的已上报报告
     *
     * @param orgId       当前单位ID
     * @param orgLevel    当前单位级别
     * @param status
     * @param observeId
     * @param patientName
     * @param startDate
     * @param endDate
     * @param hasAccident
     * @param pageStart
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/haveSend/DSA/observe/report/by/conditions")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    DSAObserveListVo queryHaveSendObserveReportByConditions(@RequestParam(value = "orgId") Integer orgId,
                                                            @RequestParam(value = "orgLevel") String orgLevel,
                                                            @RequestParam(value = "status", required = false, defaultValue = "全部状态") String status,
                                                            @RequestParam(value = "observeId", required = false, defaultValue = "") Integer observeId,
                                                            @RequestParam(value = "patientName", required = false, defaultValue = "") String patientName,
                                                            @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                            @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                            @RequestParam(value = "hasAccident", required = false, defaultValue = "ALL") String hasAccident,
                                                            @RequestParam(value = "pageStart") Integer pageStart,
                                                            @RequestParam(value = "pageSize") Integer pageSize,
                                                            HttpServletRequest request,
                                                            HttpServletResponse response) {
        return reportServiceFacade.queryHaveSendObserveReportByConditions(orgId, orgLevel, status, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize);
    }

    /**
     * 返回当前机构下属机构上报的报告，用来进行报告的评价：
     * 这里的orgId只能是市级机构或者省级机构（有的医院会直接隶属省级）
     *
     * @param orgId
     * @param orgLevel
     * @param reportOrgName
     * @param status
     * @param observeId
     * @param patientName
     * @param startDate
     * @param endDate
     * @param hasAccident
     * @param pageStart
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/lower/DSA/observe/report/by/conditions")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    DSAObserveListVo queryLowerObserveReportByConditions(@RequestParam(value = "orgId") Integer orgId,
                                                         @RequestParam(value = "orgLevel") String orgLevel,
                                                         @RequestParam(value = "reportOrgName", required = false, defaultValue = "") String reportOrgName,
                                                         @RequestParam(value = "status", required = false, defaultValue = "全部状态") String status,
                                                         @RequestParam(value = "observeId", required = false, defaultValue = "") Integer observeId,
                                                         @RequestParam(value = "patientName", required = false, defaultValue = "") String patientName,
                                                         @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                         @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                         @RequestParam(value = "hasAccident", required = false, defaultValue = "ALL") String hasAccident,
                                                         @RequestParam(value = "pageStart") Integer pageStart,
                                                         @RequestParam(value = "pageSize") Integer pageSize,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) {
        return reportServiceFacade.queryLowerObserveReportByConditions(orgId, orgLevel, reportOrgName, status, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize);
    }

    /**
     * 获取所有的监测报告，出去暂存的报告
     *
     * @param cityOrgId     市级监测机构ID
     * @param reportOrgName
     * @param observeId
     * @param patientName
     * @param startDate
     * @param endDate
     * @param hasAccident
     * @param pageStart
     * @param pageSize
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/all/DSA/observe/report/by/conditions")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    DSAObserveListVo queryAllObserveReportByConditions(@RequestParam(value = "cityOrgId", required = false) Integer cityOrgId,
                                                       @RequestParam(value = "reportOrgName", required = false, defaultValue = "") String reportOrgName,
                                                       @RequestParam(value = "observeId", required = false, defaultValue = "") Integer observeId,
                                                       @RequestParam(value = "patientName", required = false, defaultValue = "") String patientName,
                                                       @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                                       @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                                       @RequestParam(value = "hasAccident", required = false, defaultValue = "ALL") String hasAccident,
                                                       @RequestParam(value = "pageStart") Integer pageStart,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) {
        return reportServiceFacade.queryAllObserveReportByConditions(cityOrgId, reportOrgName, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize);
    }


    /**
     * 退回或者通过指定的监测观察表和对应的不良事件报告表
     *
     * @param submitType                提交类型：REJECT-退回   PASS-通过
     * @param accidentId                不良事件报告表ID
     * @param observeId                 监测观察表ID
     * @param evaluateBrief             监测观察表评价简述
     * @param evaluatorName             参评人
     * @param superiorRelativeEvaluate1 不良事件关联性评价-上级评-第一项-直接存中文：是、否
     * @param superiorRelativeEvaluate2 不良事件关联性评价-上级评-第二项-直接存中文：是、否
     * @param superiorRelativeEvaluate3 不良事件关联性评价-上级评-第三项-直接存中文：是、否
     * @param superiorRelativeResult    上级评-与数字减影血管造影机的因果关系评价-直接存中文：很有可能、可能有关、可能无关、无法确定
     * @param technologyIsHarmSituation 上级评-监测技术机构评价意见-是否医疗器械不良事件-直接存中文：是、否
     * @param technologyProcessTip      上级评-监测技术机构评价意见-处理意见-直接存中文：不符合报告标准、继续监测、进一步处理
     * @param technologyTipDescription  监测技术机构评价意见-意见陈述
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submit/DSA/evaluate/report/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean rejectOrSubmitDSAObserveReportById(@RequestParam(value = "submitType") String submitType,
                                               @RequestParam(value = "accidentId") Integer accidentId,
                                               @RequestParam(value = "observeId") Integer observeId,
                                               @RequestParam(value = "evaluateBrief") String evaluateBrief,
                                               @RequestParam(value = "evaluatorName") String evaluatorName,
                                               @RequestParam(value = "evaluatorId") Integer evaluatorId,
                                               @RequestParam(value = "superiorRelativeEvaluate1", required = false, defaultValue = "") String superiorRelativeEvaluate1,
                                               @RequestParam(value = "superiorRelativeEvaluate2", required = false, defaultValue = "") String superiorRelativeEvaluate2,
                                               @RequestParam(value = "superiorRelativeEvaluate3", required = false, defaultValue = "") String superiorRelativeEvaluate3,
                                               @RequestParam(value = "superiorRelativeResult", required = false, defaultValue = "") String superiorRelativeResult,
                                               @RequestParam(value = "technologyIsHarmSituation", required = false, defaultValue = "") String technologyIsHarmSituation,
                                               @RequestParam(value = "technologyProcessTip", required = false, defaultValue = "") String technologyProcessTip,
                                               @RequestParam(value = "technologyTipDescription", required = false, defaultValue = "") String technologyTipDescription,
                                               HttpServletRequest request, HttpServletResponse response) {
        return reportServiceFacade.rejectOrSubmitDSAObserveReportById(submitType, accidentId, observeId, evaluateBrief, evaluatorName, evaluatorId, superiorRelativeEvaluate1, superiorRelativeEvaluate2,
                superiorRelativeEvaluate3, superiorRelativeResult, technologyIsHarmSituation, technologyProcessTip, technologyTipDescription);
    }


    /**
     * 根据ID获取指定的数字剪影观察表model
     *
     * @param observeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/DSA/report/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    MedicMonitorDsa getDSAReportByObserveId(@RequestParam(value = "observeId") Integer observeId,
                                            HttpServletRequest request, HttpServletResponse response) {
        return reportServiceFacade.getDSAReportByObserveId(observeId);
    }

    /**
     * 更改指定observeId的数字减影观察表的参评人姓名和参评人ID（当报告的状态为待评价的时候，就是锁定该报告）
     *
     * @param observeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update/DSA/report/evaluate/info/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean updateDSAReportEvaluateInfoByObserveId(@RequestParam(value = "observeId") Integer observeId,
                                                   @RequestParam(value = "evaluatorId") Integer evaluatorId,
                                                   @RequestParam(value = "evaluatorName") String evaluatorName,
                                                   HttpServletRequest request, HttpServletResponse response) {
        reportServiceFacade.updateDSAReportEvaluateInfoByObserveId(observeId, evaluatorId, evaluatorName);
        return true;
    }

}













































