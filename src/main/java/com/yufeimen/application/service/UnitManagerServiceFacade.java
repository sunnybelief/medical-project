package com.yufeimen.application.service;

import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.vo.MedicOrganizationVo;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnitManagerServiceFacade {

    public void registerOrganization(MedicOrganizationVo medicOrganizationVo);

    public List<MedicOrganizationVo> getOrganization(MedicOrganizationVo vo);

    public void upDateOrganization(MedicOrganizationVo medicOrganizationVo);

    void registerMonitorUnit(MedicOrganization medicOrganization);

    List<select2Vo> getAllHospitalForSelect2(String term);

    List<select2Vo> getAllCityOrgListForSelect2();
}
