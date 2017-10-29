package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicEquipmentDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicEquipmentDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicEquipmentDetail record);

    int insertSelective(MedicEquipmentDetail record);

    MedicEquipmentDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicEquipmentDetail record);

    int updateByPrimaryKeyWithBLOBs(MedicEquipmentDetail record);

    int updateByPrimaryKey(MedicEquipmentDetail record);

    List<MedicEquipmentDetail> getEquipmentByOrgIdAndCategory(@Param("term") String term,
                                                              @Param("orgId") int orgId,
                                                              @Param("categoryId") int categoryId);
}