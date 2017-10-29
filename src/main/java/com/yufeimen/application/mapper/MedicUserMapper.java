package com.yufeimen.application.mapper;

import com.yufeimen.application.model.MedicUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicUser record);

    int insertSelective(MedicUser record);

    MedicUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicUser record);

    int updateByPrimaryKey(MedicUser record);

    void registerUserAccount(@Param("register") String register,
                             @Param("accountRole") String accountRole,
                             @Param("orgId") int orgId,
                             @Param("name") String name,
                             @Param("sex") String sex,
                             @Param("email") String email,
                             @Param("phoneNumber") String phoneNumber,
                             @Param("address") String address,
                             @Param("accountKey") String accountKey);

    List<MedicUser> selectUsersByEmail(@Param("email") String email);

    MedicUser selectUserByAccountName(String accountName);

    List<MedicUser> queryAccountsInfoById(@Param("orgId") int orgId,
                                          @Param("orgLevel") String orgLevel,
                                          @Param("pageStart") int pageStart,
                                          @Param("pageSize") int pageSize);

    int countTotalQueryAccounts(@Param("orgId") int orgId,
                                @Param("orgLevel") String orgLevel);

    void toggleAccountActive(@Param("accountId") int accountId,
                             @Param("toggle") String toggle);

    void modifyAccountData(@Param("accountId") int accountId,
                           @Param("name") String name,
                           @Param("sex") int sex,
                           @Param("email") String email,
                           @Param("phoneNumber") String phoneNumber,
                           @Param("address") String address,
                           @Param("accountRole") String accountRole,
                           @Param("accountKey") String accountKey);

    List<Long> getAllAccountIdsByOrgId(@Param("orgId") Integer orgId);
}