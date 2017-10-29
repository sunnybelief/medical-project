package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicQualitySchemeTitle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicQualitySchemeTitleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSchemeTitle(MedicQualitySchemeTitle record);

    MedicQualitySchemeTitle selectByPrimaryKey(Integer id);

    List<MedicQualitySchemeTitle> queryQualityEvaluateSchemeByConditions(@Param("schemeName") String schemeName,
                                                                         @Param("samplingStatus") String samplingStatus,
                                                                         @Param("equipmentCategory") String equipmentCategory,
                                                                         @Param("pageStart") Integer pageStart,
                                                                         @Param("pageSize") Integer pageSize);

    int countQualityEvaluateSchemeByConditions(@Param("schemeName") String schemeName,
                                               @Param("samplingStatus") String samplingStatus,
                                               @Param("equipmentCategory") String equipmentCategory);

    void updateSampleResultById(@Param("id") Integer id,
                                @Param("sampleRangeOrg") Integer sampleRangeOrg,
                                @Param("sampleRangeOrgName") String sampleRangeOrgName,
                                @Param("sampleRangeDuring") String sampleRangeDuring,
                                @Param("sampleRangeHarmOrNot") String sampleRangeHarmOrNot,
                                @Param("sampleCount") Integer sampleCount,
                                @Param("samplingMaker") String samplingMaker,
                                @Param("monitorIds") String monitorIds);

    void updateQualityEvaluateStatusById(@Param("schemeId") Integer schemeId,
                                         @Param("evaluateStatus") String evaluateStatus);

}