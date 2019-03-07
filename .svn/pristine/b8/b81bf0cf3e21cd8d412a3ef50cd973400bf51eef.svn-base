package com.lifeshs.customer.controller.account;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.customer.security.realm.UserRealm;
import com.lifeshs.customer.security.token.UserToken;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.pojo.client.Client;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.exception.ValidCodeException;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.vo.customer.CustomerSharingDataVO;


/**
 * 登录控制器
 * author: wenxian.cai
 * date: 2017/9/22 14:23
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    Logger logger = Logger.getLogger(LoginController.class);
    final UserRealm userRealm;

    @Autowired
    IDataAreaService areaService;

    @Autowired
    CustomerUserService userService;

    @Autowired
    public LoginController(UserRealm shiroRealm) {
        this.userRealm = shiroRealm;
    }

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login/login");

        return modelAndView;
    }
    
//    /**
//     * 进入注册界面
//     * @return
//     */
//    @RequestMapping("/register")
//    public ModelAndView register() {
//        ModelAndView modelAndView = new ModelAndView("login/register");
//        List<Map<String, String>> province = areaService.findAllProvince();
//        List<Map<String, String>> city = areaService.findCity("^11[0-9]{2}[0]{2}");// 默认为北京
//        List<Map<String, String>> district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
//        modelAndView.addObject("province", province);
//        modelAndView.addObject("city", city);
//        modelAndView.addObject("area", district);
//        
//        return modelAndView;
//    }

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
        CustomerUserPO user = userService.getUserByUserName(userName);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setUserNo(user.getUserNo());
        loginUser.setUserName(userName);
        loginUser.setAgentId(user.getAgentId());
        client.setUser(loginUser);
       // Client client = userService.saveCustomerUserRecord(userName);
        ClientManager.getInstance().addClinet((String) subject.getSession().getId(), client);
        try {
            CustomerSharingDataVO vo = new CustomerSharingDataVO();
            vo.setStatus(user.getStatus());
            vo.setCreateDate(user.getCreateDate());
            vo.setId(user.getId());
            vo.setUserNo(user.getUserNo());
            vo.setAgentId(user.getAgentId());
            vo.setModifyDate(user.getModifyDate());
            vo.setName(user.getName());
            vo.setPhoto(user.getPhoto());
            vo.setUserCode(user.getUserCode());
            vo.setUserName(user.getUserName());
            vo.setAgentNum(user.getAgentNum());
            //应该从shiro缓存取（未解决），doGetAuthorizationInfo又查了一次数据库，后期如果不能解决从shiro取，可以在doGetAuthorizationInfo里放session，session有则不查数据库
            AuthorizationInfo authorizationInfo = userRealm.doGetAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());

            vo.setRoles(authorizationInfo.getRoles().toArray(new String[authorizationInfo.getRoles().size()]));
            vo.setPerms(authorizationInfo.getStringPermissions().toArray(new String[authorizationInfo.getStringPermissions().size()]));
            userService.saveUserSharingData(vo);
        } catch (OperationException e) {
            logger.error(e.getMessage());
        }
        return ajaxJson;
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
