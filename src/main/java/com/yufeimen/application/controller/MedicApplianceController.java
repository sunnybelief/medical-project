package com.yufeimen.application.controller;

import com.yufeimen.application.model.MedicEquipmentDetail;
import com.yufeimen.application.service.MedicApplianceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by wangzhen on 2017/10/16
 */
@RestController
public class MedicApplianceController {

    @Autowired
    MedicApplianceFacade medicApplianceFacade;

    @ResponseBody
    @RequestMapping("/medicAppliance/add")
    public boolean addMedicAppliance(@ModelAttribute MedicEquipmentDetail medicEquipmentDetail){
        return medicApplianceFacade.addMedicAppliance(medicEquipmentDetail);
    }

}
