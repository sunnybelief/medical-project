package com.yufeimen.application;

import com.alibaba.fastjson.JSON;
import com.yufeimen.application.mapper.MedicOrganizationMapper;
import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.service.impl.UnitManagerService;
import com.yufeimen.application.vo.MedicOrganizationVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * created by wangzhen on 2017/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitManagerServiceTest {
    @Autowired
    UnitManagerService unitManagerService;
    @Autowired
    MedicOrganizationMapper medicOrganizationMapper;

    @Test
    public void registerMonitorUnit(){
        MedicOrganization medicOrganization=medicOrganizationMapper.selectOrganizationByName("辽宁省药监局");
        System.out.println(JSON.toJSONString(medicOrganization));
    }

    @Test
    public void getOrganizationBySelect(){
        MedicOrganizationVo vo=new MedicOrganizationVo();
        vo.setOrgLevel("HOSPITAL");
        vo.setIsActive("Y");
        List<MedicOrganizationVo> medicOrganizationList =unitManagerService.getOrganization(vo);
        for(MedicOrganizationVo organization:medicOrganizationList) {
            System.out.println(JSON.toJSONString(organization));
        }
    }

}
