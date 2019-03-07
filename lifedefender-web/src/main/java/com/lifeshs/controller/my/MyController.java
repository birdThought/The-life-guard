package com.lifeshs.controller.my;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.pojo.client.LoginUser;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author Yue.Li
 * @Date 2017/4/19.
 */
@Controller
@RequestMapping(value =  {
    "/my"}
)
public class MyController extends BaseController {
    private static final Logger logger = Logger.getLogger(MyController.class);

    @RequestMapping(value = "")
    public String index() {
        return "platform/my/index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView myHome(String redirectUrl) {
        LoginUser user = getLoginUser();
        Subject currentUser = SecurityUtils.getSubject();

        if ((user == null) || !currentUser.isAuthenticated()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            modelAndView.addObject("redirectUrl", redirectUrl);

            return modelAndView;
        }

        Boolean isOrg = false;

        if (StringUtils.equals(user.getLut(), "o")) {
            isOrg = true;
        }

        if (isOrg) {
            if ((user.getUserType() == 0) || (user.getUserType() == 2)) { //管理员
            	
            	if(user.getType()==3){
            		 return new ModelAndView("redirect:/store/manageHome");
            	}
                return new ModelAndView("redirect:/store/home");
            }

            if (user.getUserType() == 1) { //服务师

                return new ModelAndView("redirect:/org/services/home");
            }

            //管理员&服务师
            return new ModelAndView("redirect:/org/home");
        } else {
            return new ModelAndView("redirect:/member/home");
        }
    }
}
