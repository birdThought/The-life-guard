package com.lifeshs.customer.controller.test;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.service1.order.reportAnalysis.ReportAnalysisOrderService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.jar.JarEntry;


/**
 * 测试控制器
 * author: wenxian.cai
 * date: 2017/9/22 14:24
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    ReportAnalysisOrderService reportAnalysisOrderService;

    @Autowired
    IVipUserService vipUserService;

    @RequestMapping("test")
    public ModelAndView test() {
        return new ModelAndView("test/test");
    }

    @RequestMapping("test-login")
    public ModelAndView testLogin() {
        return new ModelAndView("login/login");
    }

    @RequestMapping("test-an")
    public ModelAndView testAn() {
        return new ModelAndView("delaer/index");
    }

    @RequestMapping("test-index")
    public ModelAndView testIndex() {
        return new ModelAndView("platform/index");
    }
    @RequestMapping("test-request")
    public ModelAndView testRequest() {
        return new ModelAndView("platform/home/request");
    }

    @RequestMapping("test-ajax")
    @ResponseBody
    public AjaxJson testAjax() {
        AjaxJson ajaxJson = new AjaxJson();
        return ajaxJson;
    }

    @RequestMapping("test-order")
    @ResponseBody
    public AjaxJson findOrderList() {
        AjaxJson ajaxJson = new AjaxJson();
//        Paging paging = reportAnalysisOrderService.findOrderList("tb", 3, 1, 8);
//        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    @RequestMapping("test-vip-user")
    @ResponseBody
    public AjaxJson user() {
        Paging paging = vipUserService.listVipUserByCustomer(null, null, null, null, null, null, null, null, 0, null, 1, 10);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    public static void main(String arg[]) {
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        ClientManager.getInstance().removeClinet((String) session.getId());

    }
}
