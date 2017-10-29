package com.yufeimen.application.vo;

import com.yufeimen.application.model.MedicOrganization;

/**
 * created by wangzhen on 2017/9/8
 */
public class MedicOrganizationVo extends MedicOrganization {
    String higherOrgName="";

    public String getHigherOrgName() {
        return higherOrgName;
    }

    public void setHigherOrgName(String higherOrgName) {
        this.higherOrgName = higherOrgName;
    }

    public MedicOrganizationVo getFromFather(MedicOrganization medicOrganization){
       MedicOrganizationVo medicOrganizationVo=new MedicOrganizationVo();
       medicOrganizationVo.setAddress(medicOrganization.getAddress());
       medicOrganizationVo.setAddressCity(medicOrganization.getAddressCity());
       medicOrganizationVo.setContacts(medicOrganization.getContacts());
       medicOrganizationVo.setEmail(medicOrganization.getEmail());
       medicOrganizationVo.setOrgLevel(medicOrganization.getOrgLevel());
       medicOrganizationVo.setOrgName(medicOrganization.getOrgName());
       medicOrganizationVo.setFax(medicOrganization.getFax());
       medicOrganizationVo.setExtraInfo(medicOrganization.getExtraInfo());
       medicOrganizationVo.setPostalCode(medicOrganization.getPostalCode());
       medicOrganizationVo.setPhoneNumber(medicOrganization.getPhoneNumber());
       medicOrganizationVo.setLegalPerson(medicOrganization.getLegalPerson());
       medicOrganizationVo.setId(medicOrganization.getId());
       medicOrganizationVo.setHospitalDegree(medicOrganization.getHospitalDegree());
       medicOrganizationVo.setHigherOrg(medicOrganization.getHigherOrg());
       medicOrganizationVo.setIsActive(medicOrganization.getIsActive());
       return medicOrganizationVo;
    }
}
