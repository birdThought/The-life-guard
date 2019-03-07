package com.lifeshs.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.ValidCode;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.support.plantform.security.exception.ValidCodeException;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.utils.RandCodeUtil;
import com.lifeshs.utils.StringUtil;

/**
 * 
 *  登录功能
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2019年1月16日 下午7:17:51
 */
@Controller
@RequestMapping("/shop/shopLogin")
public class ShopLoginController extends BaseController {

    private static final Logger logger = Logger.getLogger(ShopLoginController.class);

    @Resource(name = "loginService")
    public ISessionManageService loginService;

    @Autowired
    public IMemberService memberService;

    @Autowired
    private IOrgUserService orgUserService;
    
    @Autowired
    private ValidCodeServiceImpl validCodeService;
    
    @Autowired
    private ValidCodeServiceImpl validCodeUtil;
    
    @Autowired
    private HttpSession session;
    
    /**
     * 
     *  发送验证码到手机上
     *  @author liaoguo
     *  @DateTime 2019年1月16日 下午7:36:56
     *
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping("sendValidCode")
    @ResponseBody
    public AjaxJson sendValidCode(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "发送验证码失败";
        CacheType cacheType = null;
        String cacheKey = "", userId = "";
        
        // 获取参数
        String mobile = (String) request.getParameter("mobile");
        /********页面固定login参数start**********/
        String cache = (String) request.getParameter("cache");
        /******页面固定login参数end*********/
        

        switch (cache) {
            case "register":
                String userType = null;
                if (request.getParameter("userType") != null) {
                    userType = (String) request.getParameter("userType");
                }
                if (!"org".equals(userType)) {
                    if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
                        resObject.setMsg("该手机号已被注册");
                        return resObject;
                    }
                }
                userId = "100000";  //系统发送
                cacheType = CacheType.USER_REGISTERY_CACHE;    // 注册 验证码
                cacheKey = mobile;
                break;
            case "login":
                userId = "100000";  //系统发送
                cacheType = CacheType.USER_REGISTERY_CACHE;    // 注册 验证码
                cacheKey = mobile;
                break;
            case "reset":
                cacheType = CacheType.USER_RESET_CACHE;    // 重置密码 验证码
                if (StringUtils.isBlank(mobile)) {
                    resObject.setMsg(msg + ":手机号码为空");
                    return resObject;
                }
                // 查找手机号是否登记在系统中
                try {

                    // 普通用户
                    userId = memberService.checkMobile(mobile);

                    // 企业用户
                    if (StringUtils.isEmpty(userId)) {
                        userId = orgUserService.checkMobile(mobile);
                    }

                    if (StringUtils.isEmpty(userId)) {
                        resObject.setMsg(msg + "手机号不存在或未验证");
                        return resObject;
                    }
                    cacheKey = userId;
                    break;
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    resObject.setMsg(msg + ":" + e.getMessage());
                    return resObject;
                }
            case "mobile":
                // TODO 改为验证该手机号是否被验证，而不是被注册
                if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
                    resObject.setMsg("该手机号已被注册");
                    return resObject;
                }
                cacheType = CacheType.USER_MOBILE_MODIFY;    // 修改手机邮箱 验证码
                cacheKey = mobile;
                userId = String.valueOf(getLoginUser().getId());
                break;
        }
        if (StringUtils.isBlank(cacheKey) || cacheType == null) {
            resObject.setMsg(msg + ":非法操作");
            return resObject;
        }
        try {
            // 发送验证码
            if (StringUtils.isEmpty(validCodeService.sendToMobile(Integer.parseInt(userId), mobile, cacheKey, cacheType, VcodeTerminalType.USER_PLATFORM, false))) {
                // 发送失败
                resObject.setMsg(msg);
                return resObject;
            }
            // 发送成功
            resObject.setObj(cacheKey);    // 保存userId到页面上
            resObject.setMsg("手机验证码已发送");
            resObject.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resObject.setMsg(msg + ":" + e.getMessage());
        }

        return resObject;
    }
    
    /**
     * 
     *  校验验证码
     *  @author liaoguo
     *  @DateTime 2019年1月17日 上午11:59:10
     *
     *  @param request
     *  @param response
     *  @return
     */
    @RequestMapping(value = {"/checkVerifyCode", "/user/checkVerifyCode"})
    @ResponseBody
    public AjaxJson checkVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson ajaxJson = new AjaxJson(); 
        String cacheKey = "";
        String mobile = (String) request.getParameter("mobile");
        String verifyCode = (String) request.getParameter("verifyCode");
        /********页面固定login参数start**********/
        String type = (String) request.getParameter("type");
        /********页面固定login参数end**********/
        
        CacheType cacheType = null;
        if (type.equals(ValidCode.REGISTER)) {
            cacheType = CacheType.USER_REGISTERY_CACHE;
            cacheKey = mobile;
        }
        if (type.equals(ValidCode.LOGIN)) {
            cacheType = CacheType.USER_REGISTERY_CACHE;
            cacheKey = mobile;
        }
        if (type.equals(ValidCode.SET_PASSWORD)) {
            cacheType = CacheType.USER_RESET_CACHE;
            cacheKey = memberService.checkMobile(mobile);
            if (StringUtils.isBlank(cacheKey)) {
                ajaxJson.setMsg(NormalMessage.NO_SUCH_ACCOUNT);
                return ajaxJson;
            }
        }
        if (type.equals(ValidCode.SET_MOBILE_EMAIL) || type.equals(ValidCode.SET_NEW_MOBILE_EAMIL)) {
            cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY;
            cacheKey = mobile;
        }

        // 验证不通过
        if (!validCodeUtil.valid(cacheKey, verifyCode, cacheType)) {
            ajaxJson.setMsg(NormalMessage.CODE_UNRECOGNIZED);
            return ajaxJson;
        }
        
        int registerMemberId = 0;
        String rToken = "";
        
        //验证通过后，查找是否存在该用户
        Map<String, Object> returnData = new HashMap<>();
        UserDTO user = memberService.getUserByMobile(mobile);
        //如果不存在则新增加用户
        if(user == null){
            registerMemberId = memberService.registMember(mobile, Constant.DEFAULT_USER_PWD);
            rToken = RandCodeUtil.randNumberCodeByCustom("5", 12);
            // 保存token
            cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + registerMemberId, rToken);
        }else{
            registerMemberId = user.getId();
            rToken = user.getToken();
        }       
        returnData.put(User.ID, registerMemberId);
        returnData.put(Normal.TOKEN, rToken);
        ajaxJson.setObj(returnData);
       
        session.setAttribute("userId", registerMemberId);
        // 验证通过
        logger.info("----------------加入session后-- ");
        return ajaxJson;
    }
    

    /**
     * 
     *  通过用户验证进入系统首页
     *  @author liaoguo
     *  @DateTime 2019年1月16日 下午7:23:18
     *
     *  @param redirectUrl
     *  @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView login(String redirectUrl){
        ModelAndView modelAndView = new ModelAndView("platform/login/login");
        modelAndView.addObject("redirectUrl", redirectUrl);
        return modelAndView;
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
     * 
     *  退出系统
     *  @author liaoguo
     *  @DateTime 2019年1月16日 下午7:20:22
     *
     *  @return
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
     * 
     *  检查用户
     *  @author NaN
     *  @DateTime 2019年1月16日 下午7:22:41
     *
     *  @param userName
     *  @param pwd
     *  @param randCode
     *  @param rememberMe
     *  @param redirectURL
     *  @param request
     *  @return
     */
    @RequestMapping(value="/checkuser")
    @ResponseBody
    public AjaxJson checkUser(String userName, String pwd, String randCode, Boolean rememberMe, String redirectURL,
            HttpServletRequest request) {
        AjaxJson resObj = new AjaxJson();
        resObj.setSuccess(false);
        String msg = "登录成功";
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

}