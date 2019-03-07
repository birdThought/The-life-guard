package com.lifeshs.service.alipay.config;

/**
 * 
 *  代理商常量表
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年11月1日 下午5:31:08
 *  com.lifeshs.service.alipay.config.AgentConstant
 */

public class AgentConstant {
    
    /****************代理商分成********************/
    
    /** 用户类型 (1.管理员 2.普通用户 3.财务 4.其它) */
	public static Integer AGENT_USER_ATTRIBUTE_1 = 1;
	public static Integer AGENT_USER_ATTRIBUTE_2 = 2;
	
	/** 用户状态 (0.正常  1.禁用) */
	public static Integer AGENT_USER_STATUS_0 = 0;
	public static Integer AGENT_USER_STATUS_1 = 1;
	
	/** 角色Id */
	public static Integer AGENT_DEFUALT_ROLE_ID_8 = 8;        //代理商管理员
    public static Integer AGENT_SALESMAN_DEFUALT_ROLE_ID_9 = 9;      //业务员
    
	
	public static String AGENT_DEFUALT_PARENT_ID_A2 = "A2";        //默认上级ID为A2 (kf1002)
	public static String AGENT_GZRP_ID_D5 = "D5";        //广州若普 (D5)
	
	public static Integer AGENT_USER_TYPE_1 = 1; //代理商
	public static Integer AGENT_USER_TYPE_2 = 2; //业务员
	public static Integer AGENT_USER_TYPE_0 = 0; //平台用户
	
	public static String AGENT_USER_TYPE_A = "A"; //用户类型，userNo前缀，A代表客服人员
	public static String AGENT_USER_TYPE_D = "D"; //用户类型，userNo前缀，D代表代理商
	public static String AGENT_USER_TYPE_Y = "Y"; //用户类型，userNo前缀，Y代表业务员
	public static String AGENT_USER_TYPE_O = "O"; //用户类型，userNo前缀，O代表机构用户
	
	public static double AGENT_SERVICE_ORG_PROFIT_07 = 0.70;    //服务机构分成比例
	public static double AGENT_PLATFORM_PROFIT_02 = 0.20;       //平台分成比例
	public static double AGENT_AGENT_PROFIT_02 = 0.20;          //代理商分成比例
	public static double AGENT_INTRODUCE_ORG_PROFIT_03 = 0.30;  //引入机构分成比例
	public static double AGENT_SALESMAN_PROFIT_03 = 0.30;       //分销商、业务员分成比例
	
	

	
	
	/****************家政天下分成********************/
	
	public static double JZTX_ORG_PROFIT_06 = 0.60;    //服务机构分成比例
	public static double JZTX_JZTX_PROFIT_02 = 0.20;    //服务机构分成比例
	public static double JZTX_PLATFORM_PROFIT_02 = 0.20;    //服务机构分成比例
	
	
	
	
}

