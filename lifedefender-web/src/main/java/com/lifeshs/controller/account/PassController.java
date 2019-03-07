package com.lifeshs.controller.account;

import com.lifeshs.controller.common.BaseController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author Yue.Li
 * @Date 2017/4/20.
 *  密码相关功能
 *   1、找回密码
 *   2、重置密码
 */
@Controller
@RequestMapping(value =  {
    "/pass"}
)
public class PassController extends BaseController {
    /**
     * @author duosheng.mo
     * @DateTime 2016年5月19日 下午2:12:36
     * @serverComment 跳转找回密码页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/forgotpwd")
    public String forgotPwd(HttpServletRequest request) {
        return "com/lifeshs/member/findPwd_step_one";
    }
}
