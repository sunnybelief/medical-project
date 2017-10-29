package com.yufeimen.application.secruity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    Collection<? extends GrantedAuthority> roles;

    private final int id;

    private final String username;

    private String name;

    private Integer sex;

    private Integer orgId;

    private String email;

    private String phoneNumber;

    private String address;

    private String orgName; //所在单位名称

    private String orgLevel; //机构级别  HOSPITAL  CITY  PROVINCE

    private Integer higherOrg; //上级机构

    private String addressCity; //所属地区，精确到省|市：辽宁省|沈阳市

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getHigherOrg() {
        return higherOrg;
    }

    public void setHigherOrg(Integer higherOrg) {
        this.higherOrg = higherOrg;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public JwtAuthenticationResponse(String token, Collection<? extends GrantedAuthority> roles, int id, String username) {
        this.token = token;
        this.roles = roles;
        this.id = id;
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
