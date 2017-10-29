package com.yufeimen.application.service.impl;

import com.yufeimen.application.mapper.MedicEquipmentDetailMapper;
import com.yufeimen.application.model.MedicEquipmentDetail;
import com.yufeimen.application.service.MedicApplianceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by wangzhen on 2017/10/16
 */

@Service
public class MedicApplianceService implements MedicApplianceFacade {

    @Autowired
    private MedicEquipmentDetailMapper medicEquipmentDetailMapper;

    /**
     * 添加医疗器械
     *
     * @param medicEquipmentDetail
     */
    @Override
    public boolean addMedicAppliance(MedicEquipmentDetail medicEquipmentDetail) {
        medicEquipmentDetailMapper.insert(medicEquipmentDetail);
        return true;
    }

}
