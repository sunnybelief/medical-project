package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicAuthority;
import org.springframework.stereotype.Service;

@Service
public interface MedicAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicAuthority record);

    int insertSelective(MedicAuthority record);

    MedicAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicAuthority record);

    int updateByPrimaryKey(MedicAuthority record);
}