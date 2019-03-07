package com.lifeshs.vo.agent;

import lombok.Data;

@Data
public class AgetnIncomeVo {
    private String sysPlatformId;   //平台id
    private String agentId; //代理商id
    private String serviceOrgId; //服务机构id
    private String introduceOrgId; //引入机构id
    private String salesmanId; //业务员id
    private String orgUserUserId; //机构用户id
    
    
    private int sysIncome;//平台分成
    private int agentIncome; //代理商分成
    private int serviceOrgIncome;//服务机构分成
    private int introduceOrgIncome;//引入机构分成
    private int salesmanIncome;//业务员分成
    private int orgUserUserIncome;//机构用户分成(预留字段)
    
    private int sysProfitShare;  //平台分成比例
    private int agentProfitShare; //代理商分成比例
    private int serviceOrgProfitShare; //服务机构分成比例
    private int introduceOrgProfitShare; //引入机构分成比例
    private int salesmanProfitShare; //业务员分成比例
    
    
    @Override
    public String toString() {
        return "SumbitDTO [sysPlatformId=" + sysPlatformId + ", agentId=" + agentId + ", serviceOrgId=" + serviceOrgId + 
                ", introduceOrgId=" + introduceOrgId + ", salesmanId=" + salesmanId + ", orgUserUserId=" + orgUserUserId +
                ", sysIncome=" + sysIncome + ", agentIncome=" + agentIncome + ", serviceOrgIncome=" + serviceOrgIncome +
                ", introduceOrgIncome=" + introduceOrgIncome + ", salesmanIncome=" + salesmanIncome + ", orgUserUserIncome=" + orgUserUserIncome +
                ", sysProfitShare=" + sysProfitShare + ", agentProfitShare=" + agentProfitShare + ", serviceOrgProfitShare=" + serviceOrgProfitShare + 
                ", introduceOrgProfitShare=" + introduceOrgProfitShare +", salesmanProfitShare=" + salesmanProfitShare+"]";
    }
}
