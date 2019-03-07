package com.lifeshs.dto.manager.register.store;

import com.lifeshs.dto.manager.register.AccountDTO;

import lombok.Data;

/**
 *  门店注册
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月11日 上午10:58:42
 */
public @Data class StoreDTO {

    /** 公司信息认证 */
    private OrgDTO org;
    /** 银行信息认证 */
    private BankAccountDTO bankAccount;
    /** 法人信息认证 */
    private LegalPersonDTO legalPerson;
    /** 账号申请 */
    private AccountDTO account;
}
