package com.lifeshs.service1.agent;

import com.lifeshs.po.agent.AgentPo;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.agent.AgentVo;

public interface AgentService {
	
    AgentPo findAgentByIdOrName(Integer id,String agentName);
    
    AgentPo findAgentByCode(String provinceCode, String cityCode, String areaCode);

	/**
	 * 获取用户
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<AgentVo> findAgentList(int curPage, int pageSize);
	
	/**
	 * 添加渠道商
	 * @param agentVo
	 * @return 
	 */
//	void addAgent(AgentPo agentPo, Integer roleId, String userName, String password, int agentId)throws OperationException;
	boolean addAgentAndCustomerUser(Integer flag, CustomerUserPO customerUserPo, AgentPo agentPo);
	
}
