package com.lifeshs.customer.controller.agent;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.agent.AgentPo;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service1.agent.AgentService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.Toolkits;

/**
 * 代理商管理
 * 
 * @author Administrator
 * @date 14:51
 */
@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController {

	static final Logger logger = Logger.getLogger(AgentController.class);
	static final int AGENT_MANAGE_PAGE_SIZE = 10;

	@Autowired
	AgentService agentService;

	@Autowired
	IDataAreaService dataAreaService;

	@Resource(name = "customerUserService")
	private CustomerUserService customerUserService;

	/**
	 * 代理商管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/page")
	private ModelAndView AgentControllerPage() {
		return new ModelAndView("platform/agent/agent");

	}

	@RequestMapping("/add/page")
	public ModelAndView addAgent(){
		return new ModelAndView("platform/agent/addagent");
	}

	/**
	 * 获取代理商列表
	 * 
	 * @param page
	 * @param page
	 * @return
	 */
	@RequestMapping("/list/{page}")
	@ResponseBody
	public AjaxJson listAgent(@PathVariable("page") int page) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = agentService.findAgentList(page, AGENT_MANAGE_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 添加代理商
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/addAgent", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addAgent(@RequestParam("userName") String userName, @RequestParam("password") String password, AgentPo agentPo) {
		AjaxJson ajaxJson = new AjaxJson();
		
		//代理商名称检查
		AgentPo agentVo = agentService.findAgentByIdOrName(null, agentPo.getAgentName());
		if(agentVo!= null){
		    ajaxJson.setMsg("代理商名称已存在!");
            ajaxJson.setSuccess(false);
            return ajaxJson;
		}
		
		
		//区域判断代理商是否存在
		if(StringUtils.isNotBlank(agentPo.getAreaCode())){
		    String area = null;
		    if(agentPo.getAreaCode().startsWith("8")){
		        area = agentPo.getAreaCode();
		    }
		    
		    //区域检查
		    AgentPo agentVo1 = agentService.findAgentByCode(agentPo.getProvinceCode(), agentPo.getCityCode(), area);
	        if(agentVo1 != null){
	            ajaxJson.setMsg("该区域已存在代理!");
	            ajaxJson.setSuccess(false);
	            return ajaxJson;
	        }
		}
		
		//登录名称
		boolean b = customerUserService.checkUserName(userName);
		if(b) {
			ajaxJson.setMsg("登录账号已存在");
			ajaxJson.setSuccess(false);
			return ajaxJson;
		}
		
		
		boolean isUserNameValid = Toolkits.isVerifyUserName(userName);
        if (!isUserNameValid) {
            ajaxJson.setMsg("请输入6~16位的登录名称");
            return ajaxJson;
        }
        boolean isPasswordValid = Toolkits.isVerifyPassword(password);
        if (!isPasswordValid) {
            ajaxJson.setMsg("请输入6~16位包含大小写英文和数字的密码。");
            return ajaxJson;
        }
        
        agentPo.setContactMan(agentPo.getName());
	    CustomerUserPO customerUserPo = new CustomerUserPO();
	    customerUserPo.setStatus(AgentConstant.AGENT_USER_STATUS_0);
	    customerUserPo.setName(agentPo.getName());
	    customerUserPo.setUserName(userName);
	    customerUserPo.setPassword(password);
	    customerUserPo.setMoblie(agentPo.getPhone());
	    customerUserPo.setParentId(AgentConstant.AGENT_DEFUALT_PARENT_ID_A2); //设置默认引荐人,默认为kf1002
	    customerUserPo.setProvinceCode(agentPo.getProvinceCode());
	    customerUserPo.setCityCode(agentPo.getCityCode());
	    customerUserPo.setAreaCode(agentPo.getAreaCode());
	    customerUserPo.setAddress(agentPo.getAddress());
	    customerUserPo.setPassword(password);
	    customerUserPo.setType(AgentConstant.AGENT_USER_ATTRIBUTE_1); //用户类型 (1.管理员 2.普通用户 3.财务 4.其它)
	    customerUserPo.setAgentId(AgentConstant.AGENT_USER_TYPE_1);
	    
		boolean bol = agentService.addAgentAndCustomerUser(1, customerUserPo, agentPo);
		if(bol){
           ajaxJson.setMsg("保存成功!");
           ajaxJson.setSuccess(true);
        }else{
           ajaxJson.setMsg("保存失败!");
           ajaxJson.setSuccess(false);
        }
		return ajaxJson;
	}

}
