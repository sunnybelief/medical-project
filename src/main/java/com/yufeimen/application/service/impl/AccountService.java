package com.yufeimen.application.service.impl;

import com.yufeimen.application.mapper.MedicOrganizationMapper;
import com.yufeimen.application.mapper.MedicUserMapper;
import com.yufeimen.application.model.MedicOrganization;
import com.yufeimen.application.model.MedicUser;
import com.yufeimen.application.secruity.JwtAuthenticationResponse;
import com.yufeimen.application.secruity.JwtTokenUtil;
import com.yufeimen.application.secruity.JwtUser;
import com.yufeimen.application.service.AccountServiceFacade;
import com.yufeimen.application.vo.AccountDetailItemVo;
import com.yufeimen.application.vo.AccountDetailListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements AccountServiceFacade {

    @Autowired
    private MedicUserMapper medicUserMapper;
    @Autowired
    private MedicOrganizationMapper medicOrganizationMapper;
    @Autowired
    private UtilService utilService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 注册账号
     *
     * @param register    注册人
     * @param accountRole 账号级别
     * @param orgId       所属单位ID
     * @param name        姓名
     * @param sex         性别
     * @param email       邮箱
     * @param phoneNumber 联系方式
     * @param address     联系地址
     * @return
     */
    @Override
    public boolean registerUserAccount(String register, String accountRole, int orgId, String name,
                                       String sex, String email, String phoneNumber, String address) {
        //判定当前邮箱是否已经注册过
        List<MedicUser> accountList = medicUserMapper.selectUsersByEmail(email);
        if (accountList.size() > 0) {
            return false;
        }

        //默认密码:邮箱
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String accountKey = encoder.encode(email);
        String accountKey = email;

        medicUserMapper.registerUserAccount(register, accountRole, orgId, name, sex, email, phoneNumber, address, accountKey);
        return true;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public JwtAuthenticationResponse login(String username, String password) {
        utilService.checkNameAndPassword(username, password);
        // Reload password post-security so we can generate token
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(user);
        MedicUser temp = medicUserMapper.selectByPrimaryKey(user.getId());

        //查询用户所在的单位信息，将必要的单位信息也返回给前端，以备前端需要
        MedicOrganization orgInfo = medicOrganizationMapper.selectByPrimaryKey(temp.getOrgId());

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token, user.getAuthorities(), user.getId(), user.getUsername());
        response.setAddress(temp.getAddress());
        response.setEmail(temp.getEmail());
        response.setName(temp.getName());
        response.setOrgId(temp.getOrgId());
        response.setSex(temp.getSex());
        response.setPhoneNumber(temp.getPhoneNumber());
        response.setOrgName(orgInfo.getOrgName());
        response.setOrgLevel(orgInfo.getOrgLevel());
        response.setHigherOrg(orgInfo.getHigherOrg()); //上级机构
        response.setAddressCity(orgInfo.getAddressCity()); //所属地区，精确到省|市：辽宁省|沈阳市
        return response;
    }

    /**
     * 获取指定单位的用户账号
     *
     * @param orgId
     * @param orgLevel
     * @return
     */
    @Override
    public AccountDetailListVo queryAccountsInfoById(int orgId, String orgLevel, int pageStart, int pageSize) {
        List<MedicUser> userList = medicUserMapper.queryAccountsInfoById(orgId, orgLevel, pageStart, pageSize);
        List<AccountDetailItemVo> resultList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            MedicOrganization org = medicOrganizationMapper.selectByPrimaryKey(userList.get(i).getOrgId());
            AccountDetailItemVo tempVo = new AccountDetailItemVo();
            tempVo.setId(userList.get(i).getId());
            tempVo.setName(userList.get(i).getName());
            tempVo.setEmail(userList.get(i).getEmail());
            tempVo.setSex(userList.get(i).getSex());
            tempVo.setOrgId(userList.get(i).getOrgId());
            tempVo.setAddress(userList.get(i).getAddress());
            tempVo.setOrgName(org.getOrgName());
            tempVo.setOrgLevel(utilService.parseOrgLevelToChinese(org.getOrgLevel()));
            tempVo.setAccountRole(userList.get(i).getAccountRole().contains("ADMIN") ? "管理员账号" : "普通账号");
            tempVo.setPhoneNumber(userList.get(i).getPhoneNumber());
            tempVo.setAccountName(userList.get(i).getAccountName());
            tempVo.setAccountKey(userList.get(i).getAccountKey());
            tempVo.setIsActive(userList.get(i).getIsActive().equalsIgnoreCase("Y") ? "已启用" : "已停用");
            resultList.add(tempVo);
        }
        AccountDetailListVo resultVo = new AccountDetailListVo();
        resultVo.setTotal(medicUserMapper.countTotalQueryAccounts(orgId, orgLevel));
        resultVo.setAccountList(resultList);
        return resultVo;
    }

    /**
     * 启用或停用某个账号
     *
     * @param accountId
     * @param toggle    Y启用  N停用
     */
    @Override
    public void toggleAccountActive(int accountId, String toggle) {
        medicUserMapper.toggleAccountActive(accountId, toggle);
    }

    /**
     * 修改账户信息
     *
     * @param name
     * @param sex
     * @param email
     * @param phoneNumber
     * @param address
     * @param accountRole
     * @param accountKey
     */
    @Override
    public void modifyAccountData(int accountId, String name, int sex, String email, String phoneNumber,
                                  String address, String accountRole, String accountKey) {
        medicUserMapper.modifyAccountData(accountId, name, sex, email, phoneNumber, address, accountRole, accountKey);
    }

    @Override
    public AccountDetailItemVo getAccountsInfoById(int accountId) {
        MedicUser user = medicUserMapper.selectByPrimaryKey(accountId);
        MedicOrganization org = medicOrganizationMapper.selectByPrimaryKey(user.getOrgId());
        AccountDetailItemVo tempVo = new AccountDetailItemVo();
        tempVo.setId(user.getId());
        tempVo.setName(user.getName());
        tempVo.setEmail(user.getEmail());
        tempVo.setSex(user.getSex());
        tempVo.setOrgId(user.getOrgId());
        tempVo.setAddress(user.getAddress());
        tempVo.setOrgName(org.getOrgName());
        tempVo.setOrgLevel(utilService.parseOrgLevelToChinese(org.getOrgLevel()));
        tempVo.setAccountRole(user.getAccountRole().contains("ADMIN") ? "管理员账号" : "普通账号");
        tempVo.setPhoneNumber(user.getPhoneNumber());
        tempVo.setAccountName(user.getAccountName());
        tempVo.setAccountKey(user.getAccountKey());
        tempVo.setIsActive(user.getIsActive().equalsIgnoreCase("Y") ? "已启用" : "已停用");
        return tempVo;
    }

}














































