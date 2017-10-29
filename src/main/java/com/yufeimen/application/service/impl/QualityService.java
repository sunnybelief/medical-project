package com.yufeimen.application.service.impl;

import com.yufeimen.application.mapper.MedicMonitorDsaMapper;
import com.yufeimen.application.mapper.MedicQualitySchemeTitleMapper;
import com.yufeimen.application.model.MedicMonitorDsaWithHigherOrg;
import com.yufeimen.application.model.MedicQualitySchemeTitle;
import com.yufeimen.application.service.QualityServiceFacade;
import com.yufeimen.application.vo.QualityDSAObserveListVo;
import com.yufeimen.application.vo.QualitySchemeListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityService implements QualityServiceFacade {

    @Autowired
    private MedicQualitySchemeTitleMapper medicQualitySchemeTitleMapper;
    @Autowired
    private MedicMonitorDsaMapper medicMonitorDsaMapper;

    /**
     * 根据条件查询质量评估方案
     *
     * @param schemeName
     * @param samplingStatus
     * @param equipmentCategory
     * @return
     */
    @Override
    public QualitySchemeListVo queryQualityEvaluateSchemeByConditions(String schemeName, String samplingStatus, String equipmentCategory, Integer pageStart, Integer pageSize) {
        List<MedicQualitySchemeTitle> list = medicQualitySchemeTitleMapper.queryQualityEvaluateSchemeByConditions(schemeName, samplingStatus, equipmentCategory, pageStart, pageSize);
        int total = medicQualitySchemeTitleMapper.countQualityEvaluateSchemeByConditions(schemeName, samplingStatus, equipmentCategory);
        QualitySchemeListVo result = new QualitySchemeListVo();
        result.setQualitySchemeTitle(list);
        result.setTotal(total);
        return result;
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
    @Override
    public boolean insertQualityEvaluateSchemeTitle(String name, String equipmentCategory, Integer totalGrade, String schemeMaker, String ruleGradeJSON, String extraInfo) {
        MedicQualitySchemeTitle item = new MedicQualitySchemeTitle();
        item.setName(name);
        item.setEquipmentCategory(equipmentCategory);
        item.setTotalGrade(totalGrade);
        item.setSchemeMaker(schemeMaker);
        item.setRuleGradeJSON(ruleGradeJSON);
        item.setExtraInfo(extraInfo);
        medicQualitySchemeTitleMapper.insertSchemeTitle(item);
        return true;
    }

    /**
     * 根据ID获取指定的质量评估方案
     *
     * @param id
     * @return
     */
    @Override
    public MedicQualitySchemeTitle getQualityEvaluateSchemeTitle(Integer id) {
        return medicQualitySchemeTitleMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除指定ID的评估方案
     *
     * @param id
     */
    @Override
    public void deleteQualityEvaluateSchemeTitle(Integer id) {
        medicQualitySchemeTitleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 执行抽样动作
     *
     * @param id                 评估方案ID
     * @param equipmentCategory  器械类别，根据类别去对应的监测观察表中进行抽样
     * @param orgRange           市监测单位范围： -1：全部， 否则是市级监测单位的ID
     * @param startDate          报告开始时间：如果为空则说明不限定报告的时间范围
     * @param endDate            报告结束时间
     * @param hasAccident        是否发生不良事件：ALL全部  Y发生  N未发生
     * @param sampleCount        样本数量
     * @param sampleRangeOrgName 监测机构名称
     */
    @Override
    public int doSampleAction(Integer id, String equipmentCategory, Integer orgRange, String startDate,
                              String endDate, String hasAccident, Integer sampleCount, String sampleRangeOrgName, String samplingMaker) {
        if (equipmentCategory.equalsIgnoreCase("数字减影血管造影机")) {
            //首先执行抽样动作
            List<Integer> observeIsList = medicMonitorDsaMapper.doSampleAction(orgRange, startDate, endDate, hasAccident, sampleCount);
            if (observeIsList.size() == 0) {
                return 0;
            } else {
                //对Id指定的评估方案做更新
                String sampleRangeDuring;
                if (StringUtils.isBlank(startDate)) {
                    sampleRangeDuring = "时间不限";
                } else {
                    sampleRangeDuring = startDate + " - " + endDate;
                }
                medicQualitySchemeTitleMapper.updateSampleResultById(id, orgRange, sampleRangeOrgName,
                        sampleRangeDuring, hasAccident, observeIsList.size(), samplingMaker, StringUtils.join(observeIsList.toArray(), ','));
                ;
                return observeIsList.size();
            }
        } else {
            return -1;
        }
    }

    /**
     * 获取已抽样的指定的评估方案ID的样本报告
     *
     * @param schemeId       评估方案ID
     * @param evaluateStatus 报告评估状态
     * @return
     */
    @Override
    public QualityDSAObserveListVo querySampleReportListBySchemeId(Integer schemeId, String evaluateStatus, Integer pageStart, Integer pageSize) {
        //首先查询到指定ID的评估方案对应的报告序列
        MedicQualitySchemeTitle schemeTitle = medicQualitySchemeTitleMapper.selectByPrimaryKey(schemeId);
        List<MedicMonitorDsaWithHigherOrg> resultList = new ArrayList<>();
        if (null == schemeTitle) {
            return new QualityDSAObserveListVo();
        }
        String[] idList = schemeTitle.getMonitorIds().split(",");
        List<Long> reportIds = new ArrayList<>();
        for (int i = 0; i < idList.length; i++) {
            reportIds.add(Long.parseLong(idList[i]));
        }
        QualityDSAObserveListVo resultVo = new QualityDSAObserveListVo();
        resultVo.setReportList(medicMonitorDsaMapper.queryDSAObserveReportInIds(reportIds, evaluateStatus, pageStart, pageSize));
        resultVo.setTotal(medicMonitorDsaMapper.countDSAObserveReportInIds(reportIds, evaluateStatus));
        return resultVo;
    }

    /**
     * 更新监测表的质量评估信息
     *
     * @param observeId           监测表Id
     * @param qualityEvaluateId   评估人ID
     * @param qualityEvaluateName 评估人姓名
     */
    @Override
    public void updateDSAReportQualityInfoByObserveId(Integer observeId, Integer qualityEvaluateId, String qualityEvaluateName) {
        medicMonitorDsaMapper.updateDSAReportQualityInfoByObserveId(observeId, qualityEvaluateId, qualityEvaluateName);
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
    @Override
    public void submitDSAReportQualityEvaluateDataByObserveId(Integer schemeId, Integer observeId,
                                                              Integer qualityHandGradeTotal, String qualityHandGradeJSON) {
        //更新对应的监测观察表的质量评估数据和评估状态
        medicMonitorDsaMapper.submitDSAReportQualityEvaluateDataByObserveId(observeId, qualityHandGradeTotal, qualityHandGradeJSON);

        //查询到指定ID的评估方案对应的报告序列
        MedicQualitySchemeTitle schemeTitle = medicQualitySchemeTitleMapper.selectByPrimaryKey(schemeId);
        if (null == schemeTitle) {
            return;
        }
        String[] idList = schemeTitle.getMonitorIds().split(",");
        List<Long> reportIds = new ArrayList<>();
        for (int i = 0; i < idList.length; i++) {
            reportIds.add(Long.parseLong(idList[i]));
        }

        //计算未完成评估的报告的数量
        int count = medicMonitorDsaMapper.countQualityEvaluateNotFinishedNumber(reportIds);
        String evaluateStatus = "未完成";
        if (count == 0) {
            evaluateStatus = "已完成";
        }

        //更新评估方案的完成状态
        medicQualitySchemeTitleMapper.updateQualityEvaluateStatusById(schemeId, evaluateStatus);

    }

}













































