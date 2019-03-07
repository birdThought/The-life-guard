package com.lifeshs.controller.userHome;

import com.lifeshs.controller.common.BaseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author Yue.Li
 * @Date 2017/4/18.
 */
@RequestMapping(value =  {
    "/myhome"}
)
public class MyHomeController extends BaseController {
    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("/member/myhome");
    }
}
