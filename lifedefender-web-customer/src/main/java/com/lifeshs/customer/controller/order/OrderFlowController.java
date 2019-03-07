package com.lifeshs.customer.controller.order;

import java.util.Date;

import javax.annotation.Resource;

import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.service1.order.OrderFlowService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.util.sms.DateTimeUtilT;
import com.lifeshs.vo.order.OrderFlowVO;

/**
 * 订单Controller
 * @author zizhen.huang
 * @DateTime 2018年1月29日14:17:44
 */
@RestController
@RequestMapping(value = "/order/flow")
public class OrderFlowController extends BaseController {

    private static final int PAGESIZE = 10;
    
    @Resource(name = "orderFlowService")
    private OrderFlowService orderFlowService;
    
    /**
     * 交易流水页面
     * @author zizhen.huang
     * @DateTime 2018年1月29日14:22:38
     * @return
     */
    @RequestMapping(value = "/page")
    public ModelAndView orderFlowPage() {
    	return new ModelAndView("platform/order/orderFlow");
    }
    
    /**
     * 交易流水统计页面
     * @author zizhen.huang
     * @DateTime 2018年1月30日09:57:42
     * @return
     */
    @RequestMapping(value = "/count/page")
    public ModelAndView flowCountPage() {
    	return new ModelAndView("platform/order/flowCount");
    }
    
    /**
     * 交易流水列表
     * @author zizhen.huang
     * @DateTime 2018年1月29日14:23:45
     * @return
     */
    @RequestMapping(value = "/data/list/{curPage}", method = RequestMethod.GET)
    public AjaxJsonV2 orderFlowList(@RequestParam(value = "orgName", required = false) String orgName,
    		@RequestParam(value = "realName", required = false) String realName,
    		@RequestParam(value = "orderNumber", required = false) String orderNumber,
    		@RequestParam(value = "serveName", required = false) String serveName,
    		@RequestParam(value = "startDate", required = false) String startDate,
    		@RequestParam(value = "endDate", required = false) String endDate,
    		@PathVariable("curPage") int curPage) {
    	Date startTime = null;
    	if(startDate != null && !startDate.trim().equals("")) {
    		startTime =  DateTimeUtilT.date(startDate);
    	}
    	Date endTime = null;
    	if(endDate != null && !endDate.trim().equals("")) {
    		endTime =  DateTimeUtilT.date(endDate);
    	}
    	orgName = StringUtils.isBlank(orgName) ? null : orgName;
    	realName = StringUtils.isBlank(realName) ? null : realName;
    	orderNumber = StringUtils.isBlank(orderNumber) ? null : orderNumber;
    	serveName = StringUtils.isBlank(serveName) ? null : serveName.split("-")[1];
		CustomerSharingDataVO user = getUserSharingData();
    	Paging<OrderFlowVO> paging = orderFlowService.findOrderFlowList(user.getUserNo(), orgName, realName, orderNumber, serveName, startTime, endTime, curPage, PAGESIZE);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(paging.getPagination());
    	return ajaxJsonV2;
    }
    
    /**
     * 查看明细
     * @author zizhen.huang
     * @DateTime 2018年1月30日14:10:40
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public AjaxJsonV2 getFlowDetails(@RequestParam(value = "id") Integer id) {
    	OrderFlowVO flowDetails = orderFlowService.getOrderFlowById(id);
    	AjaxJsonV2 ajaxJsonV2 = new AjaxJsonV2();
    	ajaxJsonV2.setObj(flowDetails);
    	return ajaxJsonV2;
    }
}
