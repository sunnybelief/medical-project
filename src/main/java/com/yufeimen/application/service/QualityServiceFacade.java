package com.yufeimen.application.service;

import com.yufeimen.application.model.MedicQualitySchemeTitle;
import com.yufeimen.application.vo.QualityDSAObserveListVo;
import com.yufeimen.application.vo.QualitySchemeListVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by roper on 2017/9/2.
 */
@Service
public interface QualityServiceFacade {

    QualitySchemeListVo queryQualityEvaluateSchemeByConditions(String schemeName, String samplingStatus, String equipmentCategory, Integer pageStart, Integer pageSize);

    boolean insertQualityEvaluateSchemeTitle(String name, String equipmentCategory, Integer totalGrade, String schemeMaker, String ruleGradeJSON, String extraInfo);

    MedicQualitySchemeTitle getQualityEvaluateSchemeTitle(Integer id);

    void deleteQualityEvaluateSchemeTitle(Integer id);

    int doSampleAction(Integer id, String equipmentCategory, Integer orgRange, String startDate,
                       String endDate, String hasAccident, Integer sampleCount, String sampleRangeOrgName, String samplingMaker);

    QualityDSAObserveListVo querySampleReportListBySchemeId(Integer schemeId, String evaluateStatus, Integer pageStart, Integer pageSize);

    void updateDSAReportQualityInfoByObserveId(Integer observeId, Integer qualityEvaluateId, String qualityEvaluateName);

    void submitDSAReportQualityEvaluateDataByObserveId(Integer schemeId, Integer observeId, Integer qualityHandGradeTotal, String qualityHandGradeJSON);


}
