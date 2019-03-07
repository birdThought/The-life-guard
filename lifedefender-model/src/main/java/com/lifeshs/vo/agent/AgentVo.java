package com.lifeshs.vo.agent;

import com.lifeshs.po.agent.AgentPo;

import lombok.Data;

@Data
public class AgentVo extends AgentPo {
	/** 省份名 */
	private String provinceName;
	/** 市名 */
	private String cityName;
	
	private String userName;
	
	private String userNo;
}
