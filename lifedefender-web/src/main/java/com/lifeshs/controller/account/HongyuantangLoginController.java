package com.lifeshs.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("hongyuantang")
public class HongyuantangLoginController {

    /**
     *  宏元堂登录
     *  @author yuhang.weng 
     *  @DateTime 2018年1月25日 上午11:58:41
     *
     *  @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("platform/login/hongyuantang-login");
        return modelAndView;
    }
}
