package com.lifeshs.business.controller.account;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.business.security.token.UserToken;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.pojo.client.Client;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.exception.ValidCodeException;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.vo.business.BusinessSharingDataVO;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


/**
 * 登录控制器
 * author: wenxian.cai
 * date: 2017/9/22 14:23
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login/login");

        return modelAndView;
    }

    /**
     * 登录验证
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson checkUser(@RequestParam("userName")
    String userName, @RequestParam("password")
    String password) {
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtil.isBlank(userName) || StringUtil.isBlank(userName)) {
            ajaxJson.setMsg("请填写登录信息");
            ajaxJson.setSuccess(false);

            return ajaxJson;
        }
        UserToken token = new UserToken(userName, password, false, null, null);
        Subject subject = SecurityUtils.getSubject();
        String msg = null;
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            msg="密码错误";
        }catch (ExcessiveAttemptsException e){
            msg = "登录失败次数过多,5分钟后再试.";
        }catch (UnknownAccountException e) {
            msg = "帐号不存在.";
        }catch (ValidCodeException e){
            msg="验证码错误，请重新输入";
        }
        if(!subject.isAuthenticated()){
            ajaxJson.setMsg(msg);
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }

        Client client = new Client();
        client.setLogindatetime(new Date());
        BusinessUserPO user = userService.getUserByUserName(userName);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setSuperior(user.getSuperior());
        loginUser.setUserName(userName);
//        loginUser.setHead(user.getHead());
        client.setUser(loginUser);
        ClientManager.getInstance().addClinet((String) subject.getSession().getId(), client);
        try {
            BusinessSharingDataVO vo = new BusinessSharingDataVO();
            vo.setStatus(user.getStatus());
            vo.setCreateDate(user.getCreateDate());
            vo.setId(user.getId());
            vo.setModifyDate(user.getModifyDate());
            vo.setName(user.getName());
            vo.setUserName(user.getUserName());
            vo.setType(user.getType());

            userService.saveUserSharingData(vo);
        } catch (OperationException e) {
            logger.error(e.getMessage());
        }
        return ajaxJson;
    }

    /**
     * 进入系统
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("platform/index");

        return modelAndView;
    }

    /**
     * 退出系统
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson logout() {
        AjaxJson res = new AjaxJson();
        res.setMsg("退出系统成功");
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            ClientManager.getInstance().removeClinet((String) session.getId());
            subject.logout();
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("退出系统失败");
            logger.error(e.getMessage(), e);
        }
        return res;
    }



}
