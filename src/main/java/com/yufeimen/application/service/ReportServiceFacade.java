package com.yufeimen.application.service;

import com.yufeimen.application.model.MedicHarmDsa;
import com.yufeimen.application.model.MedicMonitorDsa;
import com.yufeimen.application.vo.DSAObserveDetailListVo;
import com.yufeimen.application.vo.DSAObserveListVo;
import com.yufeimen.application.vo.report2IdVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by roper on 2017/9/2.
 */
@Service
public interface ReportServiceFacade {

    /**
     * 提交一条DSA数字减影血管造影机监测表数据：如果observeId存在，则更新；如果不存在，则插入该记录
     *
     * @return 返回插入的数据的ID, 即监测表ID
     */
    report2IdVo submitDSAObserveReport(MedicMonitorDsa monitorDsa, MedicHarmDsa harmValue, String flowLog);

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
    DSAObserveDetailListVo queryDSAObserveTempReport(String reportor, String hasAccident, String startDate, String endDate, Integer pageStart, Integer pageSize
            , Integer orgId, String orgLevel);

    /**
     * 删除所有的暂存报告，需要修改
     *
     * @return
     */
    boolean deleteAllDSATempReport();

    /**
     * 删除指定ID的暂存监测表和暂存不良事件表
     *
     * @param observeId
     * @param accidentId
     */
    void deleteDSAReportByObserveIdAndAccidentId(Integer observeId, Integer accidentId);

    /**
     * 根据ID查询指定的监测观察表
     *
     * @param observeId
     * @return
     */
    MedicMonitorDsa queryDSAReportById(Integer observeId);

    /**
     * 根据ID查询指定的不良事件表
     *
     * @param accidentId
     * @return
     */
    MedicHarmDsa queryDSAHarmReportById(Integer accidentId);

    DSAObserveListVo queryHaveSendObserveReportByConditions(Integer orgId, String orgLevel, String status, Integer observeId, String patientName, String startDate, String endDate,
                                                            String hasAccident, Integer pageStart, Integer pageSize);

    DSAObserveListVo queryLowerObserveReportByConditions(Integer orgId, String orgLevel, String reportOrgName, String status, Integer observeId, String patientName,
                                                         String startDate, String endDate, String hasAccident, Integer pageStart, Integer pageSize);


    DSAObserveListVo queryAllObserveReportByConditions(Integer cityOrgId, String reportOrgName, Integer observeId,
                                                       String patientName, String startDate,
                                                       String endDate, String hasAccident,
                                                       Integer pageStart, Integer pageSize);

    boolean rejectOrSubmitDSAObserveReportById(String submitType,
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
                                               String technologyTipDescription);

    MedicMonitorDsa getDSAReportByObserveId(Integer observeId);

    /**
     * 更改指定observeId的数字减影观察表的参评人姓名和参评人ID（当报告的状态为待评价的时候，就是锁定该报告）
     *
     * @param observeId
     * @return
     */
    void updateDSAReportEvaluateInfoByObserveId(Integer observeId, Integer evaluatorId, String evaluatorName);

}
