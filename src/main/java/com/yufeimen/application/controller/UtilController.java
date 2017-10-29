package com.yufeimen.application.controller;

import com.alibaba.fastjson.JSONArray;
import com.yufeimen.application.model.MedicEquipmentCategory;
import com.yufeimen.application.service.UtilServiceFacade;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UtilController {

    @Autowired
    private UtilServiceFacade utilServiceFacade;

    /**
     * 根据指定的账号角色，获取左侧动态菜单
     *
     * @param accountRole
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDynamicMenu/by/role")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public JSONArray getDynamicMenuByRole(@RequestParam("accountRole") String accountRole,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        return utilServiceFacade.getDynamicMenuByRole(accountRole);
    }

    /**
     * 获取所有的医疗器械大分类：数字剪影血管造影机、纳米凝胶......
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/all/equipmentCategory")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<MedicEquipmentCategory> getAllEquipmentCategory(HttpServletRequest request,
                                                                HttpServletResponse response) {
        return utilServiceFacade.getAllEquipmentCategory();
    }

    /**
     * 返回所有的单位
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/all/organization")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<select2Vo> getAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String term,
                                              @RequestParam("orgLevel") String orgLevel,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        return utilServiceFacade.getAllOrganization(term, orgLevel);
    }

    /**
     * 给Select2控件返回查询的单位，带有全部标识
     *
     * @param term
     * @param orgLevel
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/all/organization/with/both")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<select2Vo> getAllOrganizationWithBoth(@RequestParam(value = "q", required = false, defaultValue = "") String term,
                                                      @RequestParam("orgLevel") String orgLevel,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        return utilServiceFacade.getAllOrganizationWithBoth(term, orgLevel);
    }

    /**
     * 获取指定单位指定类别的具体器械信息给select2控件
     *
     * @param term
     * @param orgId
     * @param categoryId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/all/equipment/by/orgIdAndCategory")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<select2Vo> getEquipmentByOrgIdAndCategory(@RequestParam(value = "q", required = false, defaultValue = "") String term,
                                                          @RequestParam("orgId") int orgId,
                                                          @RequestParam("categoryId") int categoryId,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {
        return utilServiceFacade.getEquipmentByOrgIdAndCategory(term, orgId, categoryId);
    }


}
