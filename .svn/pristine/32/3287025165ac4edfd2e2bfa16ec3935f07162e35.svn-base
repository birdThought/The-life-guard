package com.lifeshs.controller.account;

import static com.lifeshs.common.constants.common.CacheType.OAUTH_CACHE;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.data.LoginData;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserPcLoginDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.support.plantform.security.exception.ValidCodeException;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.utils.StringUtil;

/**
 * @author lifekeepers
 * @DateTime 2016年4月20日 下午5:21:17
 *  登录功能
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Resource(name = "loginService")
    public ISessionManageService loginService;

    @Autowired
    public IMemberService memberService;

    /**
     * @author duosheng.mo
     * @DateTime 2016年4月25日 下午2:33:32
     * @serverCode 服务代码
     * @serverComment 登录前用户信息验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value="/checkuser")
    @ResponseBody
    public AjaxJson checkUser(String userName, String pwd, String randCode, Boolean rememberMe, String redirectURL,
            HttpServletRequest request) {
        AjaxJson resObj = new AjaxJson();
        resObj.setSuccess(false);
        String msg = "登录成功";
        // Map<String,Object> param = ParserParaUtil.getParams(request);
        // String userName = (String)param.get("userName");
        // String pwd = (String)param.get("pwd");
        // String randCode = (String)param.get("randCode");
        // String lut = (String)param.get("lut");
        // Boolean rememberMe =
        // Boolean.valueOf((String)param.get("rememberMe"));

        String ip = request.getHeader("X-Real-IP");
        userName = StringUtil.decodeStr(userName);
        pwd = StringUtil.decodeStr(pwd);

        boolean flag = false;

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
            resObj.setSuccess(flag);
            resObj.setMsg("用户名或密码为空.");
            return resObj;
        }

        try {
            flag = loginService.checkUser(userName, pwd, randCode, rememberMe, ip);
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. ";
            logger.error(e.getMessage(), e);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多,5分钟后再试.";
            logger.error(e.getMessage(), e);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定.";
            logger.error(e.getMessage(), e);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用.";
            logger.error(e.getMessage(), e);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期.";
            logger.error(e.getMessage(), e);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在.";
            logger.error(e.getMessage(), e);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            logger.error(e.getMessage(), e);
        } catch (ValidCodeException e) {
            msg = e.getMessage();
            logger.error(e.getMessage(), e);
        } catch (AuthenticationException e) {
            msg = "您身份验证令牌提交失败！";
            logger.error(e.getMessage(), e);
        }

        if (flag) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("href", "/my/home");

            LoginUser user = getLoginUser();
            
            if (StringUtils.equals(user.getLut(), "o")) {

                loginService.updateLoginUser(user.getId(), 1);

                OrgUserSharingData sharingData = loginService.getCacheOrgMemberSharingData(user.getId());
                Integer orgVerified = sharingData.getOrgVerified();
                String verifiedCause = sharingData.getVerifiedCause();

                Map<String, String> verifiedResult = new HashMap<>();

                if (orgVerified == null) {
                    loginService.logout();
                    // 页面跳转，填写信息
                    verifiedResult.put("dataComplete", "0");
                    verifiedResult.put("orgId", user.getOrgId() + "");
                } else {
                    switch (orgVerified.intValue()) {
                    case 0:
                        loginService.logout();
                        verifiedResult.put("dataComplete", "1");
                        verifiedResult.put("rewrite", "0");

                        break;
                    case 1:
                        verifiedResult.put("dataComplete", "1");
                        verifiedResult.put("rewrite", "0");
                        verifiedResult.put("showMsg", "0");
                        break;
                    case 2:
                        loginService.logout();
                        verifiedResult.put("dataComplete", "1");
                        verifiedResult.put("msg", "机构审核不通过，理由：<br/>" + verifiedCause);
                        verifiedResult.put("rewrite", "1");
                        verifiedResult.put("orgId", user.getOrgId() + "");

                        break;
                    }
                }

                attributes.put("verifiedResult", verifiedResult);
            } else {
                MemberSharingData memberSharingData = getCacheMemberSharingData(user.getId());
                Boolean bindC3 = memberSharingData.isBindC3();
                if (bindC3 != null && bindC3) {
                    attributes.put("redirectUrl", "member/terminalWebControl?cseries");
                } else {
                    attributes.put("userMessage", "请使用app登录");
                    loginService.logout();
                }
                
//                attributes.put("userMessage", "请使用app登录");
//                loginService.logout();
            }

            resObj.setAttributes(attributes);
        }
        resObj.setSuccess(flag);

        resObj.setMsg(msg);
        return resObj;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年4月27日 下午1:46:20
     * @serverCode 服务代码
     * @serverComment 通过用户验证进入系统首页
     *
     * @return
     */

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView login(String redirectUrl){
        ModelAndView modelAndView = new ModelAndView("platform/login/login");
        modelAndView.addObject("redirectUrl", redirectUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/checkloginstatus", method = RequestMethod.POST)
    public @ResponseBody AjaxJson checkLoginStatus(LoginData data) {
        AjaxJson resObject = new AjaxJson();
        Boolean success = false;
        LoginUser user = getLoginUser();
        Subject currentUser = SecurityUtils.getSubject();

        Map<String, Object> attributes = new HashMap<>();
        Boolean access = false;
        
        if (user != null && currentUser.isAuthenticated()) {    // 判断session中是否保存有登录用户的信息
            Boolean isOrg = false;
            if (StringUtils.equals(user.getLut(), "o")) {
                isOrg = true;
            }
            
            Integer userId = user.getId();
            String name = "";
            String photo = "";
            String realName = "";
            
            if (!isOrg) {
                MemberSharingData memberSharingData = getCacheMemberSharingData(userId);
                realName = memberSharingData.getRealName();
                photo = memberSharingData.getPhotoPath();
            } else {
                OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
                realName = orgUserSharingData.getRealName();
                photo = orgUserSharingData.getPhotoPath();
            }
            
            if (StringUtils.isNotBlank(realName)) {
                if (realName.length() > 8) {
                    realName = realName.substring(0, 7);
                }
                name = realName;
            } else {
                String userName = user.getUserName();
                if (userName.length() > 8) {
                    userName = userName.substring(0, 7);
                }
                name = userName;
            }
            name += "...";
            
            if (StringUtils.isBlank(photo)) {
                photo = "static/images/header/default_touxiangxiao.png";
            }
            
            attributes.put("name", name);
            attributes.put("photo", photo);
            attributes.put("isOrg", isOrg);
            
            success = true;
            if (data.getRedirect()) { // 判断是否需要重定向
                if (data.getCheckUserType()) {  // 判断是否需要检查用户类型
                    access = true;
                    
                    if (!isOrg && !data.getAccessUser()) {
                        success = false;
                        access = false;
                    }
                    if (isOrg && !data.getAccessOrgUser()) {
                        success = false;
                        access = false;
                    }
                }
            }
        } else {
            success = false;
        }

        attributes.put("access", access);
        
        resObject.setSuccess(success);
        resObject.setAttributes(attributes);
        return resObject;
    }

    @RequestMapping(value = "/error404")
    public String error404(HttpServletRequest request) {
        logger.info("错误");
        return "view/error/404";
    }
    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request) {
        logger.info("错误");
        return "view/error/500";
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年4月26日 上午11:10:14
     * @serverCode 服务代码
     * @serverComment 退出系统
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public AjaxJson logout() {
        AjaxJson res = new AjaxJson();
        res.setMsg("退出系统成功");
        try {
            boolean bool = loginService.logout();
            res.setSuccess(bool);
        } catch (Exception e) {
            // TODO: handle exception
            res.setSuccess(false);
            res.setMsg("退出系统失败");
            logger.error(e.getMessage(), e);
        }
        return res;
    }


    /**
     * @Description: token认证（APP扫一扫登录pc）,并尝试进行shiro认证
     * @author: wenxian.cai
     * @create: 2017/4/17 15:03
     */
    @RequestMapping(value = "/checktoken")
    @ResponseBody
    public AjaxJson checkToken(@RequestParam(value = "token") String token, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        UserPcLoginDTO loginDTO = memberService.getUserPcLogin(token);
        if (loginDTO == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("APP未确认登录");
            return ajaxJson;
        }
        int userId = loginDTO.getUserId();
//      int userId = 1330;  //TODO 测试
        UserDTO user = memberService.getUser(userId);
        if (user == null) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("APP返回数据出错");
            return ajaxJson;
        }
        if (!user.getToken().equals(loginDTO.getUserToken())) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("APP返回userToken失效");
            return ajaxJson;
        }
        String userName = user.getUserName();
        String password = user.getPassword();
        cacheService.saveKeyValue(OAUTH_CACHE, "oauth" + userName, password);
        AjaxJson json = checkUser(userName, password, "", false, "", request);
        Map<String, Object> params = new HashMap<>();
        params.put("href", json.getAttributes().get("href"));
        ajaxJson.setSuccess(json.isSuccess());
        ajaxJson.setAttributes(params);
        ajaxJson.setMsg(json.getMsg());
        return  ajaxJson;
    }
}