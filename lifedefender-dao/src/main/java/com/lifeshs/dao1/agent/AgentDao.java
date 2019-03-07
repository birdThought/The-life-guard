package com.lifeshs.dao1.agent;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.agent.AgentPo;
import com.lifeshs.vo.agent.AgentVo;

/**
 * 代理商Dao
 * 
 * @author shiqiang.zeng
 * @date 2018.1.18 17:54
 */
@Repository(value = "agentDao")
public interface AgentDao {
    
    
    AgentPo findAgentByIdOrName(@Param("id") Integer id,@Param("agentName") String agentName);
    
    AgentPo findAgentByCode(@Param("provinceCode") String provinceCode,@Param("cityCode") String cityCode,@Param("areaCode") String areaCode);
    
	/**
	 * 统计代理商数量
	 * 
	 * @return
	 */
	int countAgent();
	
	/**
     * 获取代理商列表
     * 
     * @param startRow
     * @param pageSize
     * @return
     */
    List<AgentVo> findAgent(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
	/**
	 * 添加代理商
	 * @param agentPo
	 */
	int addAgent(AgentPo agentPo);
}
