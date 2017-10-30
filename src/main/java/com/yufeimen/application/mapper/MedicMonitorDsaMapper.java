package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicMonitorDsa;
import com.yufeimen.application.model.MedicMonitorDsaWithHigherOrg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数字减影血管造影机
 */
@Service
public interface MedicMonitorDsaMapper {

    void insertDSAObserveReport(MedicMonitorDsa monitorDsa);

    void updateDSAObserveReport(MedicMonitorDsa monitorDsa);

    MedicMonitorDsa selectDSAObserveReport(@Param("id") Integer id);

    void updateDSAObserveReportAccidentId(@Param("id") Integer id, @Param("accidentId") Integer accidentId); //更新监测表中的accidentId

    List<MedicMonitorDsa> queryDSAObserveTempReportByOrgId(@Param("orgId") Integer orgId,
                                                           @Param("reportor") String reportor,
                                                           @Param("hasAccident") String hasAccident,
                                                           @Param("startDate") String startDate,
                                                           @Param("endDate") String endDate,
                                                           @Param("pageStart") Integer pageStart,
                                                           @Param("pageSize") Integer pageSize);

    int countTotalQueryTempReportsByOrgId(@Param("orgId") Integer orgId,
                                          @Param("reportor") String reportor,
                                          @Param("hasAccident") String hasAccident,
                                          @Param("startDate") String startDate,
                                          @Param("endDate") String endDate);

    List<MedicMonitorDsa> queryDSAObserveTempReportInReportorIds(@Param("reportorIds") List<Long> reportorIds,
                                                                 @Param("reportor") String reportor,
                                                                 @Param("hasAccident") String hasAccident,
                                                                 @Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("pageStart") Integer pageStart,
                                                                 @Param("pageSize") Integer pageSize);

    int countTotalQueryTempReportsInReportorIds(@Param("reportorIds") List<Long> reportorIds,
                                                @Param("reportor") String reportor,
                                                @Param("hasAccident") String hasAccident,
                                                @Param("startDate") String startDate,
                                                @Param("endDate") String endDate);

    void deleteAllDSAObserveTempReport();  //目前是删除所有的监测表暂存报告

    void deleteDSAObserveReportById(@Param("observeId") Integer observeId); //删除指定Id的监测表

    List<MedicMonitorDsa> queryHaveSendObserveReportByConditionsAndOrgId(@Param("orgId") Integer orgId,
                                                                         @Param("status") String status,
                                                                         @Param("observeId") Integer observeId,
                                                                         @Param("patientName") String patientName,
                                                                         @Param("startDate") String startDate,
                                                                         @Param("endDate") String endDate,
                                                                         @Param("hasAccident") String hasAccident,
                                                                         @Param("pageStart") Integer pageStart,
                                                                         @Param("pageSize") Integer pageSize);

    int countTotalQueryHaveSendReportsByOrgId(@Param("orgId") Integer orgId,
                                              @Param("status") String status,
                                              @Param("observeId") Integer observeId,
                                              @Param("patientName") String patientName,
                                              @Param("startDate") String startDate,
                                              @Param("endDate") String endDate,
                                              @Param("hasAccident") String hasAccident);


    List<MedicMonitorDsa> queryHaveSendObserveReportInReportorIds(@Param("reportorIds") List<Long> reportorIds,
                                                                  @Param("status") String status,
                                                                  @Param("observeId") Integer observeId,
                                                                  @Param("patientName") String patientName,
                                                                  @Param("startDate") String startDate,
                                                                  @Param("endDate") String endDate,
                                                                  @Param("hasAccident") String hasAccident,
                                                                  @Param("pageStart") Integer pageStart,
                                                                  @Param("pageSize") Integer pageSize);

    int countTotalQueryHaveSendReportsInReportorIds(@Param("reportorIds") List<Long> reportorIds,
                                                    @Param("status") String status,
                                                    @Param("observeId") Integer observeId,
                                                    @Param("patientName") String patientName,
                                                    @Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("hasAccident") String hasAccident);


    List<MedicMonitorDsa> queryLowerObserveReportByConditions(@Param("lowHospitalIds") List<Long> lowHospitalIds,
                                                              @Param("reportOrgName") String reportOrgName,
                                                              @Param("status") String status,
                                                              @Param("observeId") Integer observeId,
                                                              @Param("patientName") String patientName,
                                                              @Param("startDate") String startDate,
                                                              @Param("endDate") String endDate,
                                                              @Param("hasAccident") String hasAccident,
                                                              @Param("pageStart") Integer pageStart,
                                                              @Param("pageSize") Integer pageSize);

    int countTotalQueryLowerReports(@Param("lowHospitalIds") List<Long> lowHospitalIds,
                                    @Param("reportOrgName") String reportOrgName,
                                    @Param("status") String status,
                                    @Param("observeId") Integer observeId,
                                    @Param("patientName") String patientName,
                                    @Param("startDate") String startDate,
                                    @Param("endDate") String endDate,
                                    @Param("hasAccident") String hasAccident);


    List<MedicMonitorDsa> queryAllObserveReportByConditions(@Param("lowHospitalIds") List<Long> lowHospitalIds,
                                                            @Param("reportOrgName") String reportOrgName,
                                                            @Param("observeId") Integer observeId,
                                                            @Param("patientName") String patientName,
                                                            @Param("startDate") String startDate,
                                                            @Param("endDate") String endDate,
                                                            @Param("hasAccident") String hasAccident,
                                                            @Param("pageStart") Integer pageStart,
                                                            @Param("pageSize") Integer pageSize);

    int countTotalQueryAllReports(@Param("lowHospitalIds") List<Long> lowHospitalIds,
                                  @Param("reportOrgName") String reportOrgName,
                                  @Param("observeId") Integer observeId,
                                  @Param("patientName") String patientName,
                                  @Param("startDate") String startDate,
                                  @Param("endDate") String endDate,
                                  @Param("hasAccident") String hasAccident);


    void updateDSAObserveEvaluateInfo(@Param("observeId") Integer observeId,
                                      @Param("evaluateBrief") String evaluateBrief,
                                      @Param("evaluatorName") String evaluatorName,
                                      @Param("evaluatorId") Integer evaluatorId);


    void updateDSAObserveStatus(@Param("observeId") Integer observeId,
                                @Param("status") String status);

    void updateDSAReportEvaluateInfoByObserveId(@Param("observeId") Integer observeId,
                                                @Param("evaluatorId") Integer evaluatorId,
                                                @Param("evaluatorName") String evaluatorName);

    void updateDSAReportQualityInfoByObserveId(@Param("observeId") Integer observeId,
                                               @Param("qualityEvaluateId") Integer qualityEvaluateId,
                                               @Param("qualityEvaluateName") String qualityEvaluateName);

    List<Integer> doSampleAction(@Param("orgRange") Integer orgRange,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate,
                                 @Param("hasAccident") String hasAccident,
                                 @Param("sampleCount") Integer sampleCount);

    List<MedicMonitorDsaWithHigherOrg> queryDSAObserveReportInIds(@Param("reportIds") List<Long> reportIds,
                                                                  @Param("evaluateStatus") String evaluateStatus,
                                                                  @Param("pageStart") Integer pageStart,
                                                                  @Param("pageSize") Integer pageSize);

    int countDSAObserveReportInIds(@Param("reportIds") List<Long> reportIds,
                                   @Param("evaluateStatus") String evaluateStatus);

    void submitDSAReportQualityEvaluateDataByObserveId(@Param("observeId") Integer observeId,
                                                       @Param("qualityHandGradeTotal") Integer qualityHandGradeTotal,
                                                       @Param("qualityHandGradeJSON") String qualityHandGradeJSON);

    int countQualityEvaluateNotFinishedNumber(@Param("reportIds") List<Long> reportIds);

    void resetQualityStatusData(@Param("reportIds") List<Long> reportIds);


}