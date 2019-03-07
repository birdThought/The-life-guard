package com.lifeshs.pojo.org.bank;

import lombok.Data;

/**
 *  银行信息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月12日 下午1:56:31
 */
public @Data class BankInfoDTO {

    /** 机构ID */
    private Integer orgId;
    /** 公司对公账号 */
    private String bankAccount;
    /** 开户所在地区 */
    private String bankDistrict;
    /** 开户行支行名称 */
    private String bankBranch;
}
