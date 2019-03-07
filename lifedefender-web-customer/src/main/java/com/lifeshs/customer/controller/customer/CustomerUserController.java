package com.lifeshs.customer.controller.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.page.Paging;

/**
 * 后台用户控制器
 * @author zizhen.huang
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class CustomerUserController extends BaseController {

    private static final int PAGE_SIZE = 10;
	
    @Resource(name = "customerUserService")
    private CustomerUserService customerUserService;
    
    /**
     * 客服管理界面
     * @author zizhen.huang
     * @DateTime 2018年1月10日14:32:38
     * @return
     */
    @RequestMapping(value = "/page")
    public ModelAndView toCustomerManager() {
    	return new ModelAndView("platform/customer/customer-manager");
    }

    /**
     * 获取客服列表
     * @author zizhen.huang
     * @DateTime 2018年1月10日14:39:26
     * @param curPage
     * @return
     */
    @RequestMapping(value = "/list/{curPage}")
    @ResponseBody
    public AjaxJson listCustomer(@PathVariable("curPage") int curPage) {
    	Paging<CustomerUserPO> paging = customerUserService.findUserList(curPage, PAGE_SIZE);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 添加客服
     * @author zizhen.huang
     * @DateTime 2018年1月10日17:20:29
     * 
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addCustomer(@RequestBody CustomerUserPO customerUserPO) {
    	AjaxJson ajaxJson = new AjaxJson();
    	String userName = customerUserPO.getUserName();
    	boolean exist = customerUserService.checkUserName(userName);
    	if(exist) {
    		 ajaxJson.setMsg("账号名已存在");
             ajaxJson.setSuccess(false);
             return ajaxJson;
    	}
    	customerUserPO.setAgentId(AgentConstant.AGENT_USER_TYPE_0);
    	boolean success = customerUserService.addUser(customerUserPO);
    	if(!success) {
    		ajaxJson.setMsg("添加失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
    	}
    	return ajaxJson;
    }

}
