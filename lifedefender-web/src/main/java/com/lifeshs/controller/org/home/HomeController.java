package com.lifeshs.controller.org.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.org.OrgIndexVO;
import com.lifeshs.pojo.org.profit.ProfitDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.member.IMemberManageService;

@RestController(value = "orgHomeController")
@RequestMapping(value = "org/services/home")
public class HomeController extends BaseController{

    @Autowired
    IMemberManageService memberManageService;

    @Autowired
    IOrderService orderService;


    /**
     * @Description: 服务师主页
     * @Author: wenxian.cai
     * @Date: 2017/6/13 15:40
     */
    @RequestMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/home");
        return modelAndView;
    }

    /**
     * @Description: 获取主页数据
     * @Author: wenxian.cai
     * @Date: 2017/6/13 15:40
     */
    @RequestMapping(params = "gethomedata", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getHomeData() {
        AjaxJson ajaxJson = new AjaxJson();
        int orgUserId = getLoginUser().getId();
        int countOfMember = memberManageService.getCountOfMemberByOrgUser(orgUserId, OrderStatus.VALID.getStatus());
        int countOfTask = orderService.getCountOfServiceOrderByOrgUser(orgUserId, OrderStatus.VALID.getStatus(), null, "all");
        ProfitDTO profitDTO = orderService.countDayProfitByServices(orgUserId);
        double countOfEarning = profitDTO.getEarning();
        int countOfOrder = profitDTO.getOrderCount();
        int visitorCount = 0;
        visitorCount = (int)(Math.random() * 100);
        List visitor = new ArrayList();
        visitorCount = visitorCount/2;
        int visitor_1 = (int)(Math.random() * visitorCount);
        int visitor_2 = (int)(Math.random() * visitorCount);
        int visitor_3 = (int)(Math.random() * visitorCount);
        int visitor_4 = (int)(Math.random() * visitorCount);
        int visitor_5 = (int)(Math.random() * visitorCount);
        int visitor_6 = (int)(Math.random() * visitorCount);
        int visitor_7 = (int)(Math.random() * visitorCount);
        visitor.add(visitor_1);
        visitor.add(visitor_2);
        visitor.add(visitor_3);
        visitor.add(visitor_4);
        visitor.add(visitor_5);
        visitor.add(visitor_6);
        visitor.add(visitor_7);

        OrgIndexVO orgIndexVO = new OrgIndexVO();
        orgIndexVO.setMemberCount(countOfMember);
        orgIndexVO.setTaskCount(countOfTask);
        orgIndexVO.setEarning(countOfEarning);
        orgIndexVO.setOrderCount(countOfOrder);
        orgIndexVO.setVisitor(visitor); //TODO 模拟数据
        orgIndexVO.setVisitorCount(visitorCount * 2);   //TODO 模拟数据
        ajaxJson.setObj(orgIndexVO);
        return ajaxJson;
    }


    /**
     * @Description: 今日收益界面
     * @Author: wenxian.cai
     * @Date: 2017/6/28 17:19
     */
    @RequestMapping(value = "/todayprofit")
    public ModelAndView todayProfit() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/today-profit");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        Double dayProfitSum = orderService.countDayProfitByServices(orgUserId).getEarning();
        Double weekProfitSum = orderService.countThisMonthProfitByServices(orgUserId).getEarning();

        Map<String, Object> attributeValue = new HashMap<>();
        attributeValue.put("dayProfitSum", dayProfitSum);
        attributeValue.put("weekProfitSum", weekProfitSum);

        modelAndView.addObject("data", JSON.toJSON(attributeValue));
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        return modelAndView;
    }

    /**
     * @Description: 今日订单界面
     * @Author: wenxian.cai
     * @Date: 2017/6/28 17:20
     */
    @RequestMapping(value = "/todayorder")
    public ModelAndView todayOrder() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/today-order");
        LoginUser user = getLoginUser();
        int orgUserId = user.getId();
        int dayOrderSum = orderService.countDayProfitByServices(orgUserId).getOrderCount();
        int weekOrderSum = orderService.countThisMonthProfitByServices(orgUserId).getOrderCount();

        Map<String, Object> attributeValue = new HashMap<>();
        attributeValue.put("dayOrderSum", dayOrderSum);
        attributeValue.put("monthOrderSum", weekOrderSum);

        modelAndView.addObject("data", JSON.toJSON(attributeValue));
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        return modelAndView;
    }
}
