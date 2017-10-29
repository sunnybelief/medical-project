package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicHarmDsa;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface MedicHarmDsaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicHarmDsa record);

    MedicHarmDsa selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(MedicHarmDsa record);

    void deleteAllDSAHarmTempReport(); //目前是删除所有报告

    void deleteDSAHarmReportById(@Param("accidentId") Integer accidentId); //删除指定ID的不良事件报告表

    void updateDSAHarmEvaluateInfo(@Param("accidentId") Integer accidentId,
                                   @Param("evaluatorName") String evaluatorName,
                                   @Param("superiorRelativeEvaluate1") String superiorRelativeEvaluate1,
                                   @Param("superiorRelativeEvaluate2") String superiorRelativeEvaluate2,
                                   @Param("superiorRelativeEvaluate3") String superiorRelativeEvaluate3,
                                   @Param("superiorRelativeResult") String superiorRelativeResult,
                                   @Param("technologyIsHarmSituation") String technologyIsHarmSituation,
                                   @Param("technologyProcessTip") String technologyProcessTip,
                                   @Param("technologyTipDescription") String technologyTipDescription);
}