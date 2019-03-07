package com.lifeshs.pojo.org.server;

import java.util.HashMap;

import com.lifeshs.entity.org.TOrg;

/**
 *  门店数据统计
 *  版权归
 *  TODO 
 *  @author wenxian.cai 
 *  @datetime 2016年9月13日下午2:43:36
 */
public class OrgDataCount {
	
	/*门店基本信息map*/
	HashMap<String, Object> orgMap;
	
    /*交易数据map*/
	HashMap<String, Object> tradeMap;
	
	/*流量统计*/
	String traffic;
	
	/*个人会员信息map*/
	HashMap<String, Object> memberMap;
	
	/*其他信息map*/
	HashMap<String, Object> otherMap;

	@Override
	public String toString() {
		return "OrgDataCount [orgMap=" + orgMap + ", tradeMap=" + tradeMap + ", traffic=" + traffic + ", memberMap="
				+ memberMap + ", otherMap=" + otherMap + "]";
	}

	public HashMap<String, Object> getOrgMap() {
		return orgMap;
	}


	public void setOrgMap(HashMap<String, Object> orgMap) {
		this.orgMap = orgMap;
	}


	public HashMap<String, Object> getTradeMap() {
		return tradeMap;
	}

	public void setTradeMap(HashMap<String, Object> tradeMap) {
		this.tradeMap = tradeMap;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public HashMap<String, Object> getMemberMap() {
		return memberMap;
	}

	public void setMemberMap(HashMap<String, Object> memberMap) {
		this.memberMap = memberMap;
	}

	public HashMap<String, Object> getOtherMap() {
		return otherMap;
	}

	public void setOtherMap(HashMap<String, Object> otherMap) {
		this.otherMap = otherMap;
	}
	
	
}
