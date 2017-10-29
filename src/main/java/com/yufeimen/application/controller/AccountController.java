package com.yufeimen.application.controller;

import com.yufeimen.application.secruity.JwtAuthenticationResponse;
import com.yufeimen.application.service.AccountServiceFacade;
import com.yufeimen.application.vo.AccountDetailItemVo;
import com.yufeimen.application.vo.AccountDetailListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceFacade accountServiceFacade;

    /**
     * 账号注册
     *
     * @param register    注册人
     * @param accountRole 账号类型
     * @param orgId       所属单位ID
     * @param name        名字
     * @param sex         性别
     * @param email       邮箱
     * @param phoneNumber 联系电话
     * @param address     联系地址
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/account/register")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean registerUserAccount(@RequestParam("register") String register,
                                       @RequestParam("accountRole") String accountRole,
                                       @RequestParam("orgId") int orgId,
                                       @RequestParam("name") String name,
                                       @RequestParam("sex") String sex,
                                       @RequestParam("email") String email,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("address") String address,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        return accountServiceFacade.registerUserAccount(register, accountRole, orgId, name, sex, email, phoneNumber, address);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtAuthenticationResponse createAuthenticationToken(@RequestParam("username") String username, @RequestParam("password")String password) throws AuthenticationException {
        return accountServiceFacade.login(username,password);
    }


    /**
     * 获取指定单位的用户账号
     *
     * @param orgId
     * @param orgLevel
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/accounts/info/by/orgId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public AccountDetailListVo queryAccountsInfoById(@RequestParam(value = "orgId", required = false, defaultValue = "-1") int orgId,
                                                     @RequestParam(value = "orgLevel", required = false) String orgLevel,
                                                     @RequestParam(value = "pageStart") int pageStart,
                                                     @RequestParam(value = "pageSize") int pageSize,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {
        return accountServiceFacade.queryAccountsInfoById(orgId, orgLevel, pageStart, pageSize);
    }

    /**
     * 获取指定Id的用户账号
     *
     * @param accountId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/accounts/info/by/accountId")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public AccountDetailItemVo getAccountsInfoById(@RequestParam(value = "accountId") int accountId,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) {
        return accountServiceFacade.getAccountsInfoById(accountId);
    }

    /**
     * 启用或停用某个账号
     *
     * @param accountId
     * @param toggle    Y启用  N停用
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toggle/accounts/active")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean toggleAccountActive(@RequestParam(value = "accountId") int accountId,
                                       @RequestParam(value = "toggle") String toggle,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        accountServiceFacade.toggleAccountActive(accountId, toggle);
        return true;
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
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modify/accounts/data")
    @PreAuthorize("hasAnyRole('HOSPITAL_ADMIN','HOSPITAL_NORMAL','CITY_ADMIN','CITY_NORMAL','PROVINCE_ADMIN','PROVINCE_NORMAL')")
    public boolean modifyAccountData(@RequestParam(value = "accountId") int accountId,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "sex") int sex,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "phoneNumber") String phoneNumber,
                                     @RequestParam(value = "address") String address,
                                     @RequestParam(value = "accountRole") String accountRole,
                                     @RequestParam(value = "accountKey") String accountKey,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        accountServiceFacade.modifyAccountData(accountId, name, sex, email, phoneNumber, address, accountRole, accountKey);
        return true;
    }
}













































