package com.yufeimen.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yufeimen.application.mapper.*;
import com.yufeimen.application.model.MedicHarmDsa;
import com.yufeimen.application.model.MedicMonitorDsa;
import com.yufeimen.application.service.ReportServiceFacade;
import com.yufeimen.application.vo.DSAObserveDetailItemVo;
import com.yufeimen.application.vo.DSAObserveDetailListVo;
import com.yufeimen.application.vo.DSAObserveListVo;
import com.yufeimen.application.vo.report2IdVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements ReportServiceFacade {

    @Autowired
    private MedicMonitorDsaMapper medicMonitorDsaMapper;
    @Autowired
    private MedicHarmDsaMapper medicHarmDsaMapper;
    @Autowired
    private MedicEquipmentDetailMapper medicEquipmentDetailMapper;
    @Autowired
    private MedicUserMapper medicUserMapper;
    @Autowired
    private MedicOrganizationMapper medicOrganizationMapper;

    /**
     * 提交一条DSA数字减影血管造影机监测表数据：如果observeId存在，则更新；如果不存在，则插入该记录
     *
     * @param monitorDsa
     * @return
     */
    @Override
    public report2IdVo submitDSAObserveReport(MedicMonitorDsa monitorDsa, MedicHarmDsa harmDsa, String flowLog) {
        //进行flowLog的拼接：{stepName:XXX市级中心审阅通过提交省级, stepDate:2017-09-13}
        JSONArray log = new JSONArray();
        MedicMonitorDsa temp = medicMonitorDsaMapper.selectDSAObserveReport(monitorDsa.getId());
        if (null != temp) {
            log = JSON.parseArray(temp.getFlowLog());
        }
        if (StringUtils.isNotBlank(flowLog)) {
            log.add(JSON.parse(flowLog));
        }
        monitorDsa.setFlowLog(JSON.toJSONString(log));

        if (null != monitorDsa.getId()) {
            medicMonitorDsaMapper.updateDSAObserveReport(monitorDsa);
        } else {
            medicMonitorDsaMapper.insertDSAObserveReport(monitorDsa);
        }

        if (monitorDsa.getHasAccident().equalsIgnoreCase("Y")) {
            harmDsa.setObserveId(monitorDsa.getId());
            if (null != harmDsa.getId()) {
                medicHarmDsaMapper.updateByPrimaryKey(harmDsa);
            } else {
                medicHarmDsaMapper.insert(harmDsa);
            }
        }

        medicMonitorDsaMapper.updateDSAObserveReportAccidentId(monitorDsa.getId(), harmDsa.getId());

        report2IdVo result = new report2IdVo();
        result.setObserveId(monitorDsa.getId());
        result.setAccidentId(harmDsa.getId());
        return result;
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
    @Override
    public DSAObserveDetailListVo queryDSAObserveTempReport(String reportor, String hasAccident, String startDate, String endDate,
                                                            Integer pageStart, Integer pageSize, Integer orgId, String orgLevel) {
        DSAObserveDetailListVo result = new DSAObserveDetailListVo();
        List<MedicMonitorDsa> observeDsaList = new ArrayList<>();
        if (orgLevel.equalsIgnoreCase("HOSPITAL")) {
            observeDsaList = medicMonitorDsaMapper.queryDSAObserveTempReportByOrgId(orgId, reportor, hasAccident, startDate, endDate, pageStart, pageSize);
            result.setTotal(medicMonitorDsaMapper.countTotalQueryTempReportsByOrgId(orgId, reportor, hasAccident, startDate, endDate));
        } else {
            //查询当前单位的所有用户账号
            List<Long> reportorIds = medicUserMapper.getAllAccountIdsByOrgId(orgId);
            //查询包含在reportorIds中的所有暂存报告
            observeDsaList = medicMonitorDsaMapper.queryDSAObserveTempReportInReportorIds(reportorIds, reportor, hasAccident, startDate, endDate, pageStart, pageSize);
            result.setTotal(medicMonitorDsaMapper.countTotalQueryTempReportsInReportorIds(reportorIds, reportor, hasAccident, startDate, endDate));
        }


        List<DSAObserveDetailItemVo> DSAVoList = new ArrayList<>();
        for (int i = 0; i < observeDsaList.size(); i++) {
            DSAObserveDetailItemVo temp = new DSAObserveDetailItemVo();
            BeanUtils.copyProperties(observeDsaList.get(i), temp);
            if (null != observeDsaList.get(i).getEquipmentId()) {
                temp.setEquipmentProductNumber(medicEquipmentDetailMapper.selectByPrimaryKey(observeDsaList.get(i).getEquipmentId()).getProductNumber());
                temp.setEquipmentRegistNumber(medicEquipmentDetailMapper.selectByPrimaryKey(observeDsaList.get(i).getEquipmentId()).getRegistNumber());
            }
            DSAVoList.add(temp);
        }
        result.setReportList(DSAVoList);
        return result;
    }

    /**
     * 删除所有的暂存监测表和所有的暂存不良事件表
     *
     * @return
     */
    @Override
    public boolean deleteAllDSATempReport() {
        medicHarmDsaMapper.deleteAllDSAHarmTempReport();//先删除所有的暂存不良事件报告
        medicMonitorDsaMapper.deleteAllDSAObserveTempReport();//再删除所有的暂存监测报告
        return true;
    }

    @Override
    public void deleteDSAReportByObserveIdAndAccidentId(Integer observeId, Integer accidentId) {
        medicMonitorDsaMapper.deleteDSAObserveReportById(observeId);
        medicHarmDsaMapper.deleteDSAHarmReportById(accidentId);
    }

    /**
     * 根据ID查询指定的监测表数据
     *
     * @param observeId
     * @return
     */
    @Override
    public MedicMonitorDsa queryDSAReportById(Integer observeId) {
        return medicMonitorDsaMapper.selectDSAObserveReport(observeId);
    }

    /**
     * 根据ID查询指定的不良事件表数据
     *
     * @param accidentId
     * @return
     */
    @Override
    public MedicHarmDsa queryDSAHarmReportById(Integer accidentId) {
        return medicHarmDsaMapper.selectByPrimaryKey(accidentId);
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
    @Override
    public DSAObserveListVo queryHaveSendObserveReportByConditions(Integer orgId, String orgLevel, String status, Integer observeId, String patientName, String startDate, String endDate,
                                                                   String hasAccident, Integer pageStart, Integer pageSize) {

        DSAObserveListVo resultList = new DSAObserveListVo();
        if (orgLevel.equalsIgnoreCase("HOSPITAL")) {
            resultList.setReportList(medicMonitorDsaMapper.queryHaveSendObserveReportByConditionsAndOrgId(orgId, status, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize));
            resultList.setTotal(medicMonitorDsaMapper.countTotalQueryHaveSendReportsByOrgId(orgId, status, observeId, patientName, startDate, endDate, hasAccident));
        } else {
            //查询当前单位的所有用户账号
            List<Long> reportorIds = medicUserMapper.getAllAccountIdsByOrgId(orgId);
            //查询包含在reportorIds中的所有已上报报告
            resultList.setReportList(medicMonitorDsaMapper.queryHaveSendObserveReportInReportorIds(reportorIds, status, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize));
            resultList.setTotal(medicMonitorDsaMapper.countTotalQueryHaveSendReportsInReportorIds(reportorIds, status, observeId, patientName, startDate, endDate, hasAccident));
        }
        return resultList;
    }

    /**
     * 返回当前机构下属机构上报的报告，用来进行报告的评价：
     * 这里的orgId只能是市级机构或者省级机构（有的医院会直接隶属省级）
     */
    @Override
    public DSAObserveListVo queryLowerObserveReportByConditions(Integer orgId, String orgLevel, String reportOrgName, String status, Integer observeId, String patientName,
                                                                String startDate, String endDate, String hasAccident, Integer pageStart, Integer pageSize) {

        if (orgLevel.equalsIgnoreCase("HOSPITAL")) {
            return new DSAObserveListVo();
        }

        //查询当前机构的下属医院机构，因为不论报告是那个级别的人上报的，但是报告的上报单位只能够是医院，只不过有可能上级是代替医院上报
        List<Long> lowHospitalIds = medicOrganizationMapper.getLowHospitalOrgIds(orgId);

        if (null == lowHospitalIds || lowHospitalIds.size() == 0) {
            return new DSAObserveListVo();
        }

        List<MedicMonitorDsa> reportList = medicMonitorDsaMapper.queryLowerObserveReportByConditions(lowHospitalIds, reportOrgName, status, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize);
        int total = medicMonitorDsaMapper.countTotalQueryLowerReports(lowHospitalIds, reportOrgName, status, observeId, patientName, startDate, endDate, hasAccident);
        DSAObserveListVo resultList = new DSAObserveListVo();
        resultList.setReportList(reportList);
        resultList.setTotal(total);
        return resultList;
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
     * @return
     */
    @Override
    public DSAObserveListVo queryAllObserveReportByConditions(Integer cityOrgId, String reportOrgName, Integer observeId,
                                                              String patientName, String startDate,
                                                              String endDate, String hasAccident,
                                                              Integer pageStart, Integer pageSize) {
        List<Long> lowHospitalIds = new ArrayList<>();
        if (null != cityOrgId) {
            lowHospitalIds = medicOrganizationMapper.getLowHospitalOrgIds(cityOrgId);
        }

        List<MedicMonitorDsa> reportList = medicMonitorDsaMapper.queryAllObserveReportByConditions(lowHospitalIds, reportOrgName, observeId, patientName, startDate, endDate, hasAccident, pageStart, pageSize);
        int total = medicMonitorDsaMapper.countTotalQueryAllReports(lowHospitalIds, reportOrgName, observeId, patientName, startDate, endDate, hasAccident);
        DSAObserveListVo resultList = new DSAObserveListVo();
        resultList.setReportList(reportList);
        resultList.setTotal(total);
        return resultList;
    }

    /**
     * 退回或者通过指定的监测观察表和对应的不良事件报告表
     *
     * @param submitType                提交类型：已评价、市级已退回、省级已退回
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
    @Override
    public boolean rejectOrSubmitDSAObserveReportById(String submitType,
                                                      Integer accidentId,
                                                      Integer observeId,
                                                      String evaluateBrief,
                                                      String evaluatorName,
                                                      Integer evaluatorId,
                                                      String superiorRelativeEvaluate1,
                                                      String superiorRelativeEvaluate2,
                                                      String superiorRelativeEvaluate3,
                                                      String superiorRelativeResult,
                                                      String technologyIsHarmSituation,
                                                      String technologyProcessTip,
                                                      String technologyTipDescription) {

        medicMonitorDsaMapper.updateDSAObserveEvaluateInfo(observeId, evaluateBrief, evaluatorName, evaluatorId);
        if (null != accidentId) {
            medicHarmDsaMapper.updateDSAHarmEvaluateInfo(accidentId, evaluatorName, superiorRelativeEvaluate1, superiorRelativeEvaluate2,
                    superiorRelativeEvaluate3, superiorRelativeResult, technologyIsHarmSituation, technologyProcessTip, technologyTipDescription);
        }
        medicMonitorDsaMapper.updateDSAObserveStatus(observeId, submitType); //更改报告的状态
        return true;

    }

    /**
     * 根据ID获取指定的数字剪影观察表model
     *
     * @param observeId
     * @return
     */
    @Override
    public MedicMonitorDsa getDSAReportByObserveId(Integer observeId) {
        return medicMonitorDsaMapper.selectDSAObserveReport(observeId);
    }

    /**
     * 更改指定observeId的数字减影观察表的参评人姓名和参评人ID（当报告的状态为待评价的时候，就是锁定该报告）
     *
     * @param observeId
     * @param evaluatorId
     * @param evaluatorName
     */
    public void updateDSAReportEvaluateInfoByObserveId(Integer observeId, Integer evaluatorId, String evaluatorName) {
        medicMonitorDsaMapper.updateDSAReportEvaluateInfoByObserveId(observeId, evaluatorId, evaluatorName);
    }

}













































