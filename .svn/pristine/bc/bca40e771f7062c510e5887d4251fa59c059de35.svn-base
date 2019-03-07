package com.lifeshs.controller.org.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.OrderType;
import com.lifeshs.pojo.client.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.pojo.org.profit.ProfitDTO;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.org.manage.IManageOrgService;
import com.lifeshs.service.org.member.IMemberManageService;
import com.lifeshs.service.org.service.IOrgServiceManage;

@RestController(value = "storeHomeController")
@RequestMapping(value = "store")
public class StoreHomeController extends BaseController{

    @Autowired
    private IEmployeeManageService employeeManageService;
    
    @Autowired
    private IMemberManageService memberManageService;
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IOrgServiceManage orgServiceManage;
    
    @Autowired
    private IManageOrgService iManageOrgService;
    
    @RequestMapping(value = "home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/store-home");

        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        int orgType = user.getType();
        int userCount = employeeManageService.countEmployee(orgId);
        int memberCount = memberManageService.countMemberByOrgId(orgId);
        int projectCount = orgServiceManage.countOrgOnlineProject(orgId);
        int orderTodoCount = orderService.getCountOfServiceOrderByOrgUser(user.getId(), OrderStatus.VALID.getStatus(), null, "all");
//        ProfitDTO profitDTO = orderService.countWeekProfit(orgId);
        ProfitDTO profitDayDTO = orderService.countDayProfit(orgId);
        
        List<String> gongao = new ArrayList<>();
        gongao.add("-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。");
        gongao.add("-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。");
        gongao.add("-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。");
        gongao.add("-【公告】生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。生命守护全面招商，赶紧叫上伙伴。");
        List<String> tuisong = new ArrayList<>();
        tuisong.add("今日头条大揭秘！10w+推荐，算个pi？");
        tuisong.add("今日头条大揭秘！10w+推荐，算个pi？");
        tuisong.add("运营内行人才知道的“微信群搜索大法”...");
        tuisong.add("运营内行人才知道的“微信群搜索大法”...");
        tuisong.add("微信运营人必备的四大装逼神器");
        tuisong.add("微信运营人必备的四大装逼神器");
        tuisong.add("做活动，到底能不能袭来高质用户");
        tuisong.add("做活动，到底能不能袭来高质用户");
        tuisong.add("今日头条大揭秘！10w+推荐，算个pi？");
        tuisong.add("今日头条大揭秘！10w+推荐，算个pi？");
        
        int todayVisit = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int visitor_1 = random.nextInt(0, 1000);
        int visitor_2 = random.nextInt(0, 1000);
        int visitor_3 = random.nextInt(0, 1000);
        int visitor_4 = random.nextInt(0, 1000);
        int visitor_5 = random.nextInt(0, 1000);
        int visitor_6 = random.nextInt(0, 1000);
        int visitor_7 = random.nextInt(0, 1000);
        todayVisit = visitor_1 + visitor_2 + visitor_3 + visitor_4 + visitor_5 + visitor_6 + visitor_7;
        
        List<Integer> flowRate = new ArrayList<>();
        flowRate.add(visitor_1);
        flowRate.add(visitor_2);
        flowRate.add(visitor_3);
        flowRate.add(visitor_4);
        flowRate.add(visitor_5);
        flowRate.add(visitor_6);
        flowRate.add(visitor_7);
        
        Map<String, Object> map = new HashMap<>();
        map.put("userCount", userCount);
        map.put("memberCount", memberCount);
        map.put("serviceCount", projectCount);
        map.put("todayProfit", profitDayDTO.getEarning());
        map.put("todayOrder", profitDayDTO.getOrderCount());
        map.put("todayVisit", todayVisit);
        map.put("gongao", JSON.toJSON(gongao));
        map.put("tuisong", JSON.toJSON(tuisong));
        map.put("flowRate", JSON.toJSON(flowRate));
        map.put("orgType", orgType);
        map.put("orderTodoCount", orderTodoCount);
        modelAndView.addObject("data", map);
        
        return modelAndView;
    }
    
    
    
    @RequestMapping(value= "/manageHome")
    public ModelAndView manageHome() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/store-manage-home");   
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        TOrg org = iManageOrgService.getOrgById(orgId);
        modelAndView.addObject("org", org);
       /* modelAndView.addObject("orgName", org.getOrgName());*/
        return modelAndView;
    }

    /**
     * @Description: 今日收益界面
     * @Author: wenxian.cai
     * @Date: 2017/6/28 17:19
     */
    @RequestMapping(value = "/home/todayprofit")
    public ModelAndView todayProfit() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/today-profit");
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        Double dayProfitSum = orderService.countDayProfit(orgId).getEarning();
        Double weekProfitSum = orderService.countThisMonthProfit(orgId).getEarning();

        Map<String, Object> attributeValue = new HashMap<>();
        attributeValue.put("dayProfitSum", dayProfitSum);
        attributeValue.put("weekProfitSum", weekProfitSum);

        modelAndView.addObject("data", JSON.toJSON(attributeValue));
        modelAndView.addObject("orgType", user.getType());
        return modelAndView;
    }

    /**
     * @Description: 今日订单界面
     * @Author: wenxian.cai
     * @Date: 2017/6/28 17:20
     */
    @RequestMapping(value = "/home/todayorder")
    public ModelAndView todayOrder() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/today-order");
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        int dayOrderSum = orderService.countDayProfit(orgId).getOrderCount();
        int monthOrderSum = orderService.countThisMonthProfit(orgId).getOrderCount();

        Map<String, Object> attributeValue = new HashMap<>();
        attributeValue.put("dayOrderSum", dayOrderSum);
        attributeValue.put("monthOrderSum", monthOrderSum);

        modelAndView.addObject("data", JSON.toJSON(attributeValue));
        modelAndView.addObject("orgType", user.getType());
        return modelAndView;
    }
}
