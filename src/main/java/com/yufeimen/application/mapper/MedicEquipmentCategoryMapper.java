package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicEquipmentCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicEquipmentCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicEquipmentCategory record);

    int insertSelective(MedicEquipmentCategory record);

    MedicEquipmentCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicEquipmentCategory record);

    int updateByPrimaryKey(MedicEquipmentCategory record);

    List<MedicEquipmentCategory> getAllEquipmentCategory();
}