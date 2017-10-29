package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.vo.MedicOrganizationVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicOrganization record);

    int insertSelective(MedicOrganization record);

    MedicOrganization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicOrganization record);

    int updateByPrimaryKeyWithBLOBs(MedicOrganization record);

    int updateByPrimaryKey(MedicOrganization record);

    List<MedicOrganization> getAllOrganization(@Param("term") String term,
                                               @Param("orgLevel") String orgLevel);

    @Select("select * from MEDIC_ORGANIZATION where org_name=#{name}")
    MedicOrganization selectOrganizationByName(String name);

    @Select("select * from MEDIC_ORGANIZATION where org_level= 'CITY'")
    List<MedicOrganization> getOrganizationByCity();

    List<MedicOrganization> getOrganizationBySelect(MedicOrganizationVo medicOrganizationVo);

    List<MedicOrganization> getOrganizationByLevel(String level);

    List<MedicOrganization> getAllHospitalForSelect2(@Param("term") String term);

    List<MedicOrganization> getAllCityOrgListForSelect2();

    List<Long> getLowHospitalOrgIds(@Param("orgId") Integer orgId);

}