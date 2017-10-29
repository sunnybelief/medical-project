package com.yufeimen.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yufeimen.application.mapper.MedicDynamicMenuMapper;
import com.yufeimen.application.mapper.MedicEquipmentCategoryMapper;
import com.yufeimen.application.mapper.MedicEquipmentDetailMapper;
import com.yufeimen.application.mapper.MedicOrganizationMapper;
import com.yufeimen.application.model.MedicEquipmentCategory;
import com.yufeimen.application.model.MedicEquipmentDetail;
import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.service.UtilServiceFacade;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilService implements UtilServiceFacade {

    @Autowired
    private MedicDynamicMenuMapper medicDynamicMenuMapper;
    @Autowired
    private MedicEquipmentCategoryMapper medicEquipmentCategoryMapper;
    @Autowired
    private MedicOrganizationMapper medicOrganizationMapper;
    @Autowired
    private MedicEquipmentDetailMapper medicEquipmentDetailMapper;

    /**
     * 根据账号角色，获取左侧的动态菜单JSON数据
     *
     * @param accountRole : ROLE_PROVINCE_ADMIN
     * @return
     */
    @Override
    public JSONArray getDynamicMenuByRole(String accountRole) {
        String menuData = medicDynamicMenuMapper.getDynamicMenuByRole(accountRole);
        return JSON.parseArray(menuData);
    }

    /**
     * 获取所有的医疗器械分类
     *
     * @return
     */
    @Override
    public List<MedicEquipmentCategory> getAllEquipmentCategory() {
        return medicEquipmentCategoryMapper.getAllEquipmentCategory();
    }

    /**
     * 获取所有的单位信息给Select2控件
     *
     * @return
     */
    @Override
    public List<select2Vo> getAllOrganization(String term, String orgLevel) {
        List<MedicOrganization> dataList = medicOrganizationMapper.getAllOrganization(term, orgLevel);
        List<select2Vo> resultList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            select2Vo temp = new select2Vo();
            temp.setId(dataList.get(i).getId());
            temp.setText(dataList.get(i).getOrgName());
            resultList.add(temp);
        }
        return resultList;
    }

    /**
     * 查询机构单位给Select2控件并携带全部
     *
     * @param term
     * @param orgLevel
     * @return
     */
    @Override
    public List<select2Vo> getAllOrganizationWithBoth(String term, String orgLevel) {
        List<select2Vo> tempList = getAllOrganization(term, orgLevel);
        select2Vo temp = new select2Vo();
        temp.setId(-1);
        temp.setText("全部");
        tempList.add(0, temp);
        return tempList;
    }

    /**
     * 将单位级别转换为中文
     *
     * @param orgLevel PROVINCE——省   CITY-市    HOSPITAL-医院
     * @return
     */
    public String parseOrgLevelToChinese(String orgLevel) {
        if (orgLevel.equalsIgnoreCase("PROVINCE")) {
            return "省";
        } else if (orgLevel.equalsIgnoreCase("CITY")) {
            return "市";
        } else {
            return "医院";
        }
    }

    /**
     * 获取指定单位指定类别的具体器械信息给select2控件
     *
     * @param term
     * @param orgId
     * @param categoryId
     * @return
     */
    @Override
    public List<select2Vo> getEquipmentByOrgIdAndCategory(String term, int orgId, int categoryId) {
        List<MedicEquipmentDetail> equipmentList = medicEquipmentDetailMapper.getEquipmentByOrgIdAndCategory(term, orgId, categoryId);
        List<select2Vo> resultList = new ArrayList<>();
        for (int i = 0; i < equipmentList.size(); i++) {
            select2Vo temp = new select2Vo();
            temp.setId(equipmentList.get(i).getId());
            temp.setText(equipmentList.get(i).getName() + "   器械编号:" + equipmentList.get(i).getProductNumber());
            resultList.add(temp);
        }
        return resultList;
    }


    @Autowired
    AuthenticationManager authenticationManager;
    public void checkNameAndPassword(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            throw new RuntimeException("用户名或密码错误");
        }
    }

}














































