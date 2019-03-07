package com.lifeshs.service1.agent.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.agent.AgentDao;
import com.lifeshs.dao1.user.AdminUserRoleDao;
import com.lifeshs.po.agent.AgentPo;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service1.agent.AgentService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.agent.AgentVo;

/**
 * 代理商管理
 * @author shiqiang.zeng
 * @date 2018.1.19 15:41
 */
@Service("agentService")
public class AgentServiceImpl implements AgentService{
	
	@Resource(name = "agentDao")
	AgentDao agentDao;

	@Resource(name = "customerUserService")
	private CustomerUserService customerUserService;
	
	@Resource(name="adminUserRoleDao")
	private AdminUserRoleDao adminUserRoleDao;
	
	
	

	@Override
	public Paging<AgentVo> findAgentList(int curPage, int pageSize) {
		
		Paging<AgentVo> p =new Paging<>(curPage,pageSize);
		p.setQueryProc(new IPagingQueryProc<AgentVo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return agentDao.countAgent();
			}

			@Override
			public List<AgentVo> queryData(int startRow, int pageSize) {
				return agentDao.findAgent(startRow, pageSize);
			}
		});
		return p;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public boolean addAgentAndCustomerUser(Integer flag, CustomerUserPO customerUserPo, AgentPo agentPo) {
        int roleId = AgentConstant.AGENT_SALESMAN_DEFUALT_ROLE_ID_9; //初始化默认为业务员
	    //添加业务员
	    if(flag == 2){
	        customerUserService.addUser(customerUserPo);
	    }else{
	        //代理商
	        roleId = AgentConstant.AGENT_DEFUALT_ROLE_ID_8; //管理员
	        agentDao.addAgent(agentPo);
	        customerUserPo.setAgentNum(agentPo.getId());
            customerUserService.addUser(customerUserPo);
	    }
	    
	    //添加用户角色
	    int result = adminUserRoleDao.addAdminUserRole(customerUserPo.getId(), roleId);
	    
	    return result == 1;
    }

    @Override
    public AgentPo findAgentByIdOrName(Integer id, String agentName) {
        // TODO Auto-generated method stub
        return agentDao.findAgentByIdOrName(id, agentName);
    }

    @Override
    public AgentPo findAgentByCode(String provinceCode, String cityCode, String areaCode) {
        // TODO Auto-generated method stub
        return agentDao.findAgentByCode(provinceCode, cityCode, areaCode);
    }
}
