package com.lifeshs.business.controller.business;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.business.BusinessAccountPO;
import com.lifeshs.service1.business.BusinessBindUserService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.vo.business.BusinessUserVO;
import com.lifeshs.vo.business.ExpenseDetailVO;
import com.lifeshs.vo.business.StatisticVO;

/**
 * 推广渠道商
 * 
 * @author zizhen.huang
 * @DateTime 2018年1月4日18:14:13
 */
@Controller
@RequestMapping("/business")
public class BusinessController extends BaseController {

	private static final int PAGE_SIZE = 10;
	
    @Resource(name = "businessBindUserService")
    private BusinessBindUserService businessBindUserService;
    
    /**
     * 用户管理界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月4日20:28:38
     * 
     * @return
     */
    @RequestMapping(value = "/toUserManager")
    public ModelAndView toUserManager() {
    	return new ModelAndView("platform/business/user-manager");
    }
    
    /**
     * 用户管理查看界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日11:50:25
     * 
     * @return
     */
    @RequestMapping(value = "/toCheckDetail")
    public ModelAndView toCheckDetail() {
    	return new ModelAndView("platform/business/check-detail");
    }
    
    /**
     * 本月费用界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日17:05:00
     * 
     * @return
     */
    @RequestMapping(value = "/toMonthCost")
    public ModelAndView toMonthCost() {
    	return new ModelAndView("platform/business/month-cost");
    }
    
    /**
     * 本月消费清单界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日17:49:17
     * 
     * @return
     */
    @RequestMapping(value = "/toDetailList")
    public ModelAndView toDetailList() {
    	return new ModelAndView("platform/business/detail-list");
    }
    
    /**
     * 结算记录界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日11:52:56
     * 
     * @return
     */
    @RequestMapping(value = "/toAccountList")
    public ModelAndView toAccountList() {
    	return new ModelAndView("platform/business/account-list");
    }
    
    /**
     * 结算记录查看界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日14:49:22
     * 
     * @return
     */
    @RequestMapping(value = "/toCheckAccount")
    public ModelAndView toCheckAccount() {
    	return new ModelAndView("platform/business/check-account");
    }
    
    /**
     * 用户统计界面
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日15:55:25
     * 
     * @return
     */
    @RequestMapping(value = "/toUserStatistic")
    public ModelAndView toUserStatistic() {
    	return new ModelAndView("platform/business/user-statistic");
    }
    
    /**
     * 获取渠道商绑定的用户列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月4日20:05:44
     * 
     * @return
     */
    @RequestMapping(value = "/listBusinessBindUser/{curPage}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listBusinessBindUser(@RequestParam(value = "realName", required = false) String realName, @PathVariable(value = "curPage") int curPage) {
        int businessId = getLoginUser().getId();
        Paging<BusinessUserVO> paging = businessBindUserService.findBusinessBindUserList(businessId,realName, curPage, PAGE_SIZE);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 查看该用户当月消费清单列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日11:45:39
     * 
     * @return
     */
    @RequestMapping(value = "/checkExpenseDetail/{curPage}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkExpenseDetail(@RequestParam(value = "userId") int userId, @PathVariable("curPage") int curPage) {
        int businessId = getLoginUser().getId();
    	Paging<ExpenseDetailVO> paging = businessBindUserService.findExpenseDetailList(businessId, userId, curPage, PAGE_SIZE);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 显示本月统计费用
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日13:47:33
     * 
     * @return
     */
    @RequestMapping(value = "/showCountMonthCost", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson showCountMonthCost() {
    	int businessId = getLoginUser().getId();
    	ExpenseDetailVO expenseDetailVO = businessBindUserService.countMonthCost(businessId);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(expenseDetailVO);
    	return ajaxJson;
    }
    
    /**
     * 本月消费清单列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日15:22:48
     * 
     * @return
     */
    @RequestMapping(value = "/listDetail/{curPage}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listDetail(@PathVariable("curPage") int curPage) {
    	int businessId = getLoginUser().getId();
    	Paging<ExpenseDetailVO> paging = businessBindUserService.findMonthCostList(businessId, curPage, PAGE_SIZE);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 获取结算列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日09:36:02
     * 
     * @return
     */
    @RequestMapping(value = "/listAccount/{curPage}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson listAccount(@PathVariable("curPage") int curPage) {
    	int businessId = getLoginUser().getId();
    	Paging<BusinessAccountPO> paging = businessBindUserService.findAccountList(businessId, curPage, PAGE_SIZE);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 查看当前月份所有用户的结算列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日11:56:41
     * 
     * @return
     */
    @RequestMapping(value = "/checkAccount/{curPage}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkAccount(@RequestParam("month") String month, @PathVariable("curPage") int curPage) {
    	int businessId = getLoginUser().getId();
    	Date months = DateTimeUtilT.date(month);
    	Paging<ExpenseDetailVO> paging = businessBindUserService.findAllUserList(businessId, months, curPage, PAGE_SIZE);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(paging.getPagination());
    	return ajaxJson;
    }
    
    /**
     * 获取本月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日17:28:49
     * 
     * @return
     */
    @RequestMapping(value = "/getLastMonth", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getLastMonth() {
    	int businessId = getLoginUser().getId();
    	List<StatisticVO>  statisticList = businessBindUserService.getLastMonth(businessId);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(statisticList);
    	return ajaxJson;
    }
    
    /**
     * 获取近三个月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:25:073
     * 
     * @return
     */
    @RequestMapping(value = "/getRecentlyThreeMonths", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getRecentlyThreeMonths() {
    	int businessId = getLoginUser().getId();
    	List<StatisticVO> statisticList = businessBindUserService.getRecentlyThreeMonths(businessId);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(statisticList);
    	return ajaxJson;
    }
    
    /**
     * 获取近半年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:36:35
     * 
     * @return
     */
    @RequestMapping(value = "/getHalfYear", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHalfYear() {
    	int businessId = getLoginUser().getId();
    	List<StatisticVO> statisticList = businessBindUserService.getHalfYear(businessId);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(statisticList);
    	return ajaxJson;
    }
    
    /**
     * 获取近一年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:40:24
     * 
     * @return
     */
    @RequestMapping(value = "/getAlmostYear", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getAlmostYear() {
    	int businessId = getLoginUser().getId();
    	List<StatisticVO> statisticList = businessBindUserService.getAlmostYear(businessId);
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.setObj(statisticList);
    	return ajaxJson;
    }
}
