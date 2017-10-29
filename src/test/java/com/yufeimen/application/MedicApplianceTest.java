package com.yufeimen.application;

import com.yufeimen.application.model.MedicEquipmentDetail;
import com.yufeimen.application.service.MedicApplianceFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by wangzhen on 2017/10/16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicApplianceTest {

    @Autowired
    MedicApplianceFacade medicApplianceFacade;

    @Test
    public void addMedicAppliance(){
        MedicEquipmentDetail medicEquipmentDetail=new MedicEquipmentDetail();
        medicEquipmentDetail.setAddress("adress");
        medicEquipmentDetail.setGmtCreator("wang");
        medicEquipmentDetail.setGmtUpdator("wang");
        medicEquipmentDetail.setIsDeleted("N");
        medicEquipmentDetail.setAddress("1");
        medicEquipmentDetail.setAddressCity("shenyang");
        medicEquipmentDetail.setBelongOrgId(123);
        medicEquipmentDetail.setCategoryId(123);
        medicEquipmentDetail.setContactor("123");
        medicEquipmentDetail.setEmail("123");
        medicEquipmentDetail.setExtraInfo("1");
        medicEquipmentDetail.setCategoryName("category");
        medicEquipmentDetail.setFactoryName("factory");
        medicEquipmentDetail.setModelVersion("1");
        medicEquipmentDetail.setPhoneNumber("123456");
        medicEquipmentDetail.setRegistNumber("123");
        medicEquipmentDetail.setPostalCode("233500");
        medicEquipmentDetail.setProductNumber("123");
        medicEquipmentDetail.setName("name");
        medicApplianceFacade.addMedicAppliance(medicEquipmentDetail);
    }
}
