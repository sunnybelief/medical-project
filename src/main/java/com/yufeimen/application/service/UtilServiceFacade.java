package com.yufeimen.application.service;

import com.alibaba.fastjson.JSONArray;
import com.yufeimen.application.model.MedicEquipmentCategory;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by roper on 2017/9/2.
 */
@Service
public interface UtilServiceFacade {

    JSONArray getDynamicMenuByRole(String accountRole); //通过账号角色获取左侧的动态菜单数据

    List<MedicEquipmentCategory> getAllEquipmentCategory(); //获取所有的医疗器械分类：数字剪影血管造影机、纳米凝胶.....

    List<select2Vo> getAllOrganization(String term, String orgLevel); //查询机构单位给Select2控件

    List<select2Vo> getAllOrganizationWithBoth(String term, String orgLevel); //查询机构单位给Select2控件并携带全部

    List<select2Vo> getEquipmentByOrgIdAndCategory(String term, int orgId, int categoryId); //获取指定单位指定类别的具体器械信息给select2控件
}
