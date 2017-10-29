package com.yufeimen.application.vo;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class AccountDetailListVo {

    private List<AccountDetailItemVo> AccountList;  //用户列表
    private int total;    //总数

    public List<AccountDetailItemVo> getAccountList() {
        return AccountList;
    }

    public void setAccountList(List<AccountDetailItemVo> accountList) {
        AccountList = accountList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
