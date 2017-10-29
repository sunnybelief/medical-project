package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicDynamicMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface MedicDynamicMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicDynamicMenu record);

    int insertSelective(MedicDynamicMenu record);

    MedicDynamicMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicDynamicMenu record);

    int updateByPrimaryKeyWithBLOBs(MedicDynamicMenu record);

    int updateByPrimaryKey(MedicDynamicMenu record);

    String getDynamicMenuByRole(@Param("accountRole") String accountRole);

}