package com.yufeimen.application.service;

import com.yufeimen.application.secruity.JwtAuthenticationResponse;
import com.yufeimen.application.vo.AccountDetailItemVo;
import com.yufeimen.application.vo.AccountDetailListVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by roper on 2017/9/2.
 */
@Service
public interface AccountServiceFacade {

    //注册单位账号
    boolean registerUserAccount(String register, String accountRole, int orgId, String name, String sex, String email, String phoneNumber, String address);

    JwtAuthenticationResponse login(String username, String password);

    AccountDetailListVo queryAccountsInfoById(int orgId, String orgLevel, int pageStart, int pageSize);

    void toggleAccountActive(int accountId, String toggle);

    void modifyAccountData(int accountId, String name, int sex, String email, String phoneNumber,
                           String address, String accountRole, String accountKey);

    AccountDetailItemVo getAccountsInfoById(int accountId);

}
