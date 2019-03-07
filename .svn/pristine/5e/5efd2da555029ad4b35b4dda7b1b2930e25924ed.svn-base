package com.lifeshs.business.controller.test;

import com.lifeshs.security.sessionmgr.ClientManager;

import java.time.LocalDate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 测试控制器
 * author: wenxian.cai
 * date: 2017/9/22 14:24
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping("test")
    public ModelAndView test() {
        return new ModelAndView("test/test");
    }

    @RequestMapping("test-login")
    public ModelAndView testLogin() {
        return new ModelAndView("login/login");
    }

    public static void main(String arg[]) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
//        ClientManager.getInstance().removeClinet((String) session.getId());
    }
}
