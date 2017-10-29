package com.yufeimen.application.service.impl;

import com.yufeimen.application.mapper.MedicOrganizationMapper;
import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.service.UnitManagerServiceFacade;
import com.yufeimen.application.vo.MedicOrganizationVo;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitManagerService implements UnitManagerServiceFacade {

    @Autowired
    MedicOrganizationMapper medicOrganizationMapper;

    /**
     * 注册机构
     *
     * @param medicOrganizationVo
     */
    @Override
    public void registerOrganization(MedicOrganizationVo medicOrganizationVo) {
        MedicOrganization organization = medicOrganizationMapper.selectOrganizationByName(medicOrganizationVo.getOrgName());
        if (organization != null) throw new RuntimeException("机构名已存在");
        medicOrganizationVo.setIsDeleted("N");
        medicOrganizationVo.setIsActive("N");
        MedicOrganization upOrganization = medicOrganizationMapper.selectOrganizationByName(medicOrganizationVo.getHigherOrgName());
        medicOrganizationVo.setHigherOrg(upOrganization.getId());
        medicOrganizationMapper.insert(medicOrganizationVo);
    }

    /**
     * 通过级别获取机构
     *
     * @return
     */
    @Override
    public List<MedicOrganizationVo> getOrganization(MedicOrganizationVo vo) {
        List<MedicOrganization> list = medicOrganizationMapper.getOrganizationBySelect(vo);

        List<MedicOrganizationVo> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MedicOrganizationVo medicOrganizationVo = new MedicOrganizationVo().getFromFather(list.get(i));
            medicOrganizationVo.setHigherOrgName(medicOrganizationMapper.selectByPrimaryKey(medicOrganizationVo.getHigherOrg()).getOrgName());
            resultList.add(medicOrganizationVo);
        }
        return resultList;
    }

    /**
     * 更新监测机构
     *
     * @param medicOrganizationVo
     */
    @Override
    public void upDateOrganization(MedicOrganizationVo medicOrganizationVo) {
        medicOrganizationMapper.updateByPrimaryKeySelective(medicOrganizationVo);
    }

    @Override
    public void registerMonitorUnit(MedicOrganization medicOrganization) {
        MedicOrganization organization = medicOrganizationMapper.selectOrganizationByName(medicOrganization.getOrgName());
        if (organization != null) throw new RuntimeException("机构名已存在");
        medicOrganization.setIsDeleted("N");
        medicOrganization.setIsActive("N");
        medicOrganization.setOrgLevel("HOSPITAL");
        medicOrganizationMapper.insert(medicOrganization);
    }

    @Override
    public List<select2Vo> getAllHospitalForSelect2(String term) {
        List<MedicOrganization> orgList = medicOrganizationMapper.getAllHospitalForSelect2(term);
        List<select2Vo> resultList = new ArrayList<>();
        for (int i = 0; i < orgList.size(); i++) {
            select2Vo temp = new select2Vo();
            temp.setId(orgList.get(i).getId());
            String[] addressCity = orgList.get(i).getAddressCity().split("\\|");
            temp.setText(addressCity[0] + "-" + addressCity[1] + "    " + orgList.get(i).getOrgName());
            resultList.add(temp);
        }
        return resultList;
    }

    @Override
    public List<select2Vo> getAllCityOrgListForSelect2() {
        List<MedicOrganization> dataList = medicOrganizationMapper.getAllCityOrgListForSelect2();
        List<select2Vo> tempList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            select2Vo temp = new select2Vo();
            temp.setId(dataList.get(i).getId());
            temp.setText(dataList.get(i).getOrgName());
            tempList.add(temp);
        }

        select2Vo temp = new select2Vo();
        temp.setId(-1);
        temp.setText("全部");
        tempList.add(0, temp);
        return tempList;
    }


















































}
