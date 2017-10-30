package com.yufeimen.application.controller;

import com.yufeimen.application.model.MedicQualitySchemeTitle;
import com.yufeimen.application.service.QualityServiceFacade;
import com.yufeimen.application.vo.QualityDSAObserveListVo;
import com.yufeimen.application.vo.QualitySchemeListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class QualityController {

    @Autowired
    private QualityServiceFacade qualityServiceFacade;

    /**
     * 根据条件查询质量评估方案
     *
     * @param schemeName
     * @param samplingStatus
     * @param equipmentCategory
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/quality/evaluate/scheme/by/conditions")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    QualitySchemeListVo queryQualityEvaluateSchemeByConditions(@RequestParam(value = "schemeName", required = false, defaultValue = "") String schemeName,
                                                               @RequestParam(value = "samplingStatus", required = false, defaultValue = "ALL") String samplingStatus,
                                                               @RequestParam(value = "equipmentCategory", required = false, defaultValue = "ALL") String equipmentCategory,
                                                               @RequestParam(value = "pageStart") Integer pageStart,
                                                               @RequestParam(value = "pageSize") Integer pageSize,
                                                               HttpServletRequest request, HttpServletResponse response) {
        return qualityServiceFacade.queryQualityEvaluateSchemeByConditions(schemeName, samplingStatus, equipmentCategory, pageStart, pageSize);

    }

    /**
     * 插入一条评估方案
     *
     * @param name              方案名称
     * @param equipmentCategory 机器类别，直接存中文：数字减影血管造影机......
     * @param totalGrade        总分值
     * @param schemeMaker       方案制定人
     * @param ruleGradeJSON     自定义评分项和分值：[{itemName:'医院名称',itemInfo:'评分项说明',itemRuleGrade:'评分项设定分值'，itemHandGrade:'评分项打分分数'},{},{},...]
     * @param extraInfo         预留字段：作为方案说明',
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert/quality/evaluate/scheme/title")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean insertQualityEvaluateSchemeTitle(@RequestParam(value = "name") String name,
                                             @RequestParam(value = "equipmentCategory") String equipmentCategory,
                                             @RequestParam(value = "totalGrade") Integer totalGrade,
                                             @RequestParam(value = "schemeMaker") String schemeMaker,
                                             @RequestParam(value = "ruleGradeJSON") String ruleGradeJSON,
                                             @RequestParam(value = "extraInfo") String extraInfo,
                                             HttpServletRequest request, HttpServletResponse response) {
        return qualityServiceFacade.insertQualityEvaluateSchemeTitle(name, equipmentCategory, totalGrade, schemeMaker, ruleGradeJSON, extraInfo);

    }

    /**
     * 根据ID查询指定的评估方案信息
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/quality/evaluate/scheme/title")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    MedicQualitySchemeTitle getQualityEvaluateSchemeTitle(@RequestParam(value = "id") Integer id,
                                                          HttpServletRequest request, HttpServletResponse response) {
        return qualityServiceFacade.getQualityEvaluateSchemeTitle(id);

    }

    /**
     * 删除指定的评估方案：id
     * 删除评估方案之后，应该将该评估方案下的所有检测报告的状态改为未评估
     * `quality_evaluate_name` varchar(128) DEFAULT NULL COMMENT '质量评估人姓名',
     * `quality_evaluate_id` int(10) DEFAULT NULL COMMENT '质量评估人ID',
     * `quality_status` varchar(128) NOT NULL DEFAULT '未评估' COMMENT '质量评估状态，直接存中文:未评估，已评估',
     * `quality_hand_grade_JSON` text COMMENT '质量评估人工打分JSON串',
     * `quality_hand_grade_total` int(10) DEFAULT NULL COMMENT '质量评估人工打分总分',
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/quality/evaluate/scheme/title")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean deleteQualityEvaluateSchemeTitle(@RequestParam(value = "id") Integer id,
                                             HttpServletRequest request, HttpServletResponse response) {
        qualityServiceFacade.deleteQualityEvaluateSchemeTitle(id);

        return true;

    }

    /**
     * 执行抽样，这里有两个动作：1、将对应的评估方案基本信息更新   2、在监测观察表中按照规则进行随机抽样
     *
     * @param id                 评估方案ID
     * @param equipmentCategory  器械类别，根据类别去对应的监测观察表中进行抽样
     * @param orgRange           市监测单位范围： -1：全部， 否则是市级监测单位的ID
     * @param startDate          报告开始时间：如果为空则说明不限定报告的时间范围
     * @param endDate            报告结束时间
     * @param hasAccident        是否发生不良事件：ALL全部  Y发生  N未发生
     * @param sampleCount        样本数量
     * @param sampleRangeOrgName 市级检测机构名称：全部，某个具体名称
     * @param samplingMaker      抽样人
     * @return 返回抽取的样本数量: -1 表示抽取失败
     */
    @ResponseBody
    @RequestMapping(value = "/do/sample/action")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    int doSampleAction(@RequestParam(value = "id") Integer id,
                       @RequestParam(value = "equipmentCategory") String equipmentCategory,
                       @RequestParam(value = "orgRange") Integer orgRange,
                       @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                       @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                       @RequestParam(value = "hasAccident") String hasAccident,
                       @RequestParam(value = "sampleCount") Integer sampleCount,
                       @RequestParam(value = "sampleRangeOrgName") String sampleRangeOrgName,
                       @RequestParam(value = "samplingMaker") String samplingMaker,
                       HttpServletRequest request, HttpServletResponse response) {
        return qualityServiceFacade.doSampleAction(id, equipmentCategory, orgRange, startDate, endDate, hasAccident, sampleCount, sampleRangeOrgName, samplingMaker);

    }

    /**
     * 获取已抽样的指定的评估方案ID的样本报告
     *
     * @param schemeId       评估方案ID
     * @param evaluateStatus 报告评估状态
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/sample/report/list/by/schemeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    QualityDSAObserveListVo querySampleReportListBySchemeId(@RequestParam(value = "schemeId") Integer schemeId,
                                                            @RequestParam(value = "evaluateStatus") String evaluateStatus,
                                                            @RequestParam(value = "pageStart") Integer pageStart,
                                                            @RequestParam(value = "pageSize") Integer pageSize,
                                                            HttpServletRequest request, HttpServletResponse response) {
        return qualityServiceFacade.querySampleReportListBySchemeId(schemeId, evaluateStatus, pageStart, pageSize);

    }

    /**
     * 更改指定observeId的数字减影观察表的参评人姓名和参评人ID（当报告的状态为待评价的时候，就是锁定该报告）
     *
     * @param observeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update/DSA/report/quality/evaluate/info/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean updateDSAReportQualityInfoByObserveId(@RequestParam(value = "observeId") Integer observeId,
                                                  @RequestParam(value = "qualityEvaluateId") Integer qualityEvaluateId,
                                                  @RequestParam(value = "qualityEvaluateName") String qualityEvaluateName,
                                                  HttpServletRequest request, HttpServletResponse response) {
        qualityServiceFacade.updateDSAReportQualityInfoByObserveId(observeId, qualityEvaluateId, qualityEvaluateName);
        return true;
    }

    /**
     * 提交质量评估数据，需要做的工作：1、存储数据；2、更改报告的评估状态；3、查询所在评估方案是否评价完毕，更新状态
     *
     * @param schemeId              对应的评估方案的ID
     * @param observeId             数字减影观测标ID
     * @param qualityHandGradeTotal 质量评估手工总分
     * @param qualityHandGradeJSON  评分规则和打分JSON串 [{"itemName":"评分项1","itemRuleGrade":"12","itemInfo":"说明项1","itemHandGrade":"10"},{},...]
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submit/DSA/report/quality/evaluate/data/by/observeId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    boolean submitDSAReportQualityEvaluateDataByObserveId(@RequestParam(value = "schemeId") Integer schemeId,
                                                          @RequestParam(value = "observeId") Integer observeId,
                                                          @RequestParam(value = "qualityHandGradeTotal") Integer qualityHandGradeTotal,
                                                          @RequestParam(value = "qualityHandGradeJSON") String qualityHandGradeJSON,
                                                          HttpServletRequest request, HttpServletResponse response) {
        qualityServiceFacade.submitDSAReportQualityEvaluateDataByObserveId(schemeId, observeId, qualityHandGradeTotal, qualityHandGradeJSON);
        return true;
    }


}













































