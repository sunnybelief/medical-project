package com.yufeimen.application.controller;

import com.yufeimen.application.service.UnitManagerServiceFacade;
import com.yufeimen.application.service.impl.UnitManagerService;
import com.yufeimen.application.vo.MedicOrganizationVo;
import com.yufeimen.application.vo.select2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 各级单位管理
 */
@RestController
public class UnitManagerController {

    @Autowired
    private UnitManagerServiceFacade unitManagerServiceFacade;

    /**
     * 注册机构
     *
     * @param medicOrganizationVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/registerOrganization")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean registerOrganization(@ModelAttribute MedicOrganizationVo medicOrganizationVo) {
        unitManagerServiceFacade.registerOrganization(medicOrganizationVo);
        return true;
    }

    /**
     * 获取所有市级监测机构
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getCityOrganization")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<MedicOrganizationVo> getCityMedicOrganization() {
        MedicOrganizationVo vo = new MedicOrganizationVo();
        vo.setOrgLevel("CITY");
        return unitManagerServiceFacade.getOrganization(vo);
    }

    /**
     * 获取所有器械使用单位
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getHospitalOrganization")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<MedicOrganizationVo> getHospitalOrganization(@ModelAttribute MedicOrganizationVo vo) {
        vo.setOrgLevel("HOSPITAL");
        return unitManagerServiceFacade.getOrganization(vo);
    }

    /**
     * 更新机构信息
     *
     * @param organization
     */
    @ResponseBody
    @RequestMapping("updateOrganization")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean upDateMedicOrganization(@ModelAttribute MedicOrganizationVo organization) {
        unitManagerServiceFacade.upDateOrganization(organization);
        return true;
    }

    /**
     * 提供填表select2数据，暂时返回所有的医院格式[{id:1008,text:辽宁省-沈阳市-沈阳市第一医院},{},{}]
     */
    @ResponseBody
    @RequestMapping(value = "/get/all/hospital")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<select2Vo> getAllHospitalForSelect2(@RequestParam(value = "q", required = false, defaultValue = "") String term,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        return unitManagerServiceFacade.getAllHospitalForSelect2(term);
    }

    /**
     * 获取所有市级监测机构-提供数据抽样中的监测机构范围的select2控件，带有全部选项
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/all/city/org/list/for/select2")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public List<select2Vo> getAllCityOrgListForSelect2() {
        return unitManagerServiceFacade.getAllCityOrgListForSelect2();
    }

}








































