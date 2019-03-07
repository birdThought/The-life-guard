package com.lifeshs.controller.account;

import static com.lifeshs.common.constants.common.CacheType.OAUTH_CACHE;

import com.lifeshs.po.user.WeiXinUserInfoPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserOauthDTO;

import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.tool.ICacheService;

import com.lifeshs.support.plantform.security.exception.ValidCodeException;
import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;

import com.lifeshs.utils.HttpUtils;
import static com.lifeshs.utils.JSONHelper.toHashMap;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.PropertiesUtil;
import com.lifeshs.utils.RandCodeUtil;

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

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by wenxian.cai on 2017/4/10.
 * 第三方登录（微信、QQ）
 */
@Controller
@RequestMapping("/oauthLoginControl")
public class OauthLoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private IMemberService memberService;
    @Autowired
    public ISessionManageService loginService;
    @Autowired
    public ICacheService cacheService;

    @RequestMapping("")
    public ModelAndView authLogin(final HttpServletRequest request,
        HttpServletResponse response) {
        Map<String, Object> param = ParserParaUtil.getParams(request);
        System.out.println("code:" + param.get("code"));

        String code = (String) param.get("code");
        PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
        String appId = pro.readProperty("weixin.appId");
        String secret = pro.readProperty("weixin.secret");
//        String appId = "wx491346093dceed17"; //测试号
//        String secret ="134b9bc3a1259102e3eff96aea781420"; //测试号
        String grantType = pro.readProperty("weixin.grantType");
        String oauthType = "weixin";

        //http请求获取access_token、openId
        String[] result = getAccessToken(oauthType, appId, secret, code,
                grantType, null);

        if (result == null || result.length == 0) {
            return new ModelAndView("login/login");
        }

        String openId = result[0];
        String uId = result[1];
        if (uId == null) {
            return new ModelAndView("login/login");
        }

        //        openId = "obq_D0mk00EJbkjW4zdvn_Ixmc-k";  //测j试 TODO
        //        uId = "obq_D0mk00EJbkjW4zdvn_Ixmc-j";  //测试 TODO
        String userName = "weixin_" +
            RandCodeUtil.randNumberCodeByCustom("1", 8);
        String password = RandCodeUtil.randNumberCodeByCustom("1", 8);

        UserDTO user = memberService.getUserByUId(uId);
        int userId = 0;

        if (user == null) {
            userId = memberService.registMember(userName, password);

            UserOauthDTO oauth = new UserOauthDTO();
            oauth.setOauthType(oauthType);
            oauth.setOpenId(openId);
            oauth.setUserId(userId);
            oauth.setuId(uId);
            // oauth.setCreateDate(new Date());// 把创建日期放到xml里面
            memberService.addOauthUser(oauth);
        } else {
            userName = user.getUserName();
            password = user.getPassword();
            cacheService.saveKeyValue(OAUTH_CACHE, "oauth" + userName, password);
        }

        if (userId != -1) {
            boolean flag = false;

            try {
                flag = loginService.checkUser(userName, password, "", false,
                        null);
            } catch (IncorrectCredentialsException e) {
                System.out.println("登录密码错误. ");
            } catch (ExcessiveAttemptsException e) {
                System.out.println("登录失败次数过多. ");
            } catch (LockedAccountException e) {
                System.out.println("帐号已被锁定. ");
            } catch (DisabledAccountException e) {
                System.out.println("帐号已被禁用. ");
            } catch (ExpiredCredentialsException e) {
                System.out.println("帐号已过期. ");
            } catch (UnknownAccountException e) {
                System.out.println("帐号不存在. ");
            } catch (UnauthorizedException e) {
                System.out.println("您没有得到相应的授权. ");
            } catch (ValidCodeException e) {
            } catch (AuthenticationException e) {
                System.out.println("您身份验证令牌提交失败. ");
            }

            if (flag) {
                System.out.println("认证成功");
            }
        }

        LoginUser userLogin = getLoginUser();

        Subject currentUser = SecurityUtils.getSubject();

        if ((user == null) || !currentUser.isAuthenticated()) {
            return new ModelAndView("login/login");
        }

        Boolean isOrg = false;

        if (StringUtils.equals(userLogin.getLut(), "o")) {
            isOrg = true;
        }

        if (isOrg) {
            return new ModelAndView("redirect:/orgControl.do?home");
        } else {
            return new ModelAndView("com/lifeshs/member/userPage");
        }
    }

    /**
     * qq第三方登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "qq")
    public ModelAndView qqOauthLogin(HttpServletRequest request,
        HttpServletResponse response) {
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String code = (String) param.get("code");
        PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
        String appId = pro.readProperty("qq.appId");
        String secret = pro.readProperty("qq.secret");
        String grantType = pro.readProperty("qq.grant_type");
        String redirect_uri = pro.readProperty("qq.redirect_uri");
        String oauthType = "qq";

        //http请求获取openId
        String[] result = getAccessToken(oauthType, appId, secret, code,
                grantType, redirect_uri);

        if (result == null) {
            return new ModelAndView("login/login");
        }

        String openId = result[0];

        //        openId = "obq_D0mk00EJbkjW4zdvn_Ixmc-k";  //测试 TODO
        String uId = result[1];
        String userName = "qq_" + RandCodeUtil.randNumberCodeByCustom("1", 8);
        String password = RandCodeUtil.randNumberCodeByCustom("1", 8);

        UserDTO user = memberService.getUserByUId(uId);
        int userId = 0;

        if (user == null) {
            userId = memberService.registMember(userName, password);

            UserOauthDTO oauth = new UserOauthDTO();
            oauth.setOauthType(oauthType);
            oauth.setOpenId(openId);
            oauth.setUserId(userId);
            oauth.setuId(uId);
            // oauth.setCreateDate(new Date());// 把创建日期放到xml里面
            memberService.addOauthUser(oauth);
        } else {
            userName = user.getUserName();
            password = user.getPassword();
            cacheService.saveKeyValue(OAUTH_CACHE, "oauth" + userName, password);
        }

        if (userId != -1) {
            boolean flag = false;

            try {
                flag = loginService.checkUser(userName, password, "", false,
                        null);
            } catch (IncorrectCredentialsException e) {
                System.out.println("登录密码错误. ");
            } catch (ExcessiveAttemptsException e) {
                System.out.println("登录失败次数过多. ");
            } catch (LockedAccountException e) {
                System.out.println("帐号已被锁定. ");
            } catch (DisabledAccountException e) {
                System.out.println("帐号已被禁用. ");
            } catch (ExpiredCredentialsException e) {
                System.out.println("帐号已过期. ");
            } catch (UnknownAccountException e) {
                System.out.println("帐号不存在. ");
            } catch (UnauthorizedException e) {
                System.out.println("您没有得到相应的授权. ");
            } catch (ValidCodeException e) {
            } catch (AuthenticationException e) {
                System.out.println("您身份验证令牌提交失败. ");
            }

            if (flag) {
                System.out.println("认证成功");
            }
        }

        LoginUser userLogin = getLoginUser();

        Subject currentUser = SecurityUtils.getSubject();

        if ((user == null) || !currentUser.isAuthenticated()) {
            return new ModelAndView("login/login");
        }

        Boolean isOrg = false;

        if (StringUtils.equals(userLogin.getLut(), "o")) {
            isOrg = true;
        }

        if (isOrg) {
            return new ModelAndView("redirect:/orgControl.do?home");
        } else {
            return new ModelAndView("com/lifeshs/member/userPage");
        }
    }





    /**
     * 获取openId、unionId
     * @param appid
     * @param secret
     * @param code
     * @param grant_type
     * @return
     */
    protected String[] getAccessToken(String oauthType, String appid,
        String secret, String code, String grant_type, String redirect_uri) {
        String url = null;
        final String[] results;

        switch (oauthType) {
        case "weixin":
            url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                appid + "&secret=" + secret + "&code=" + code + "&grant_type=" +
                grant_type;
            results = new String[2];
            HttpUtils.getResultEntity(url, null, null, HttpUtils.GET, true,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        System.out.println("请求weixin:openId完成");

                        Map map = toHashMap(result);
                        results[0] = (String) map.get("openid");
                        results[1] = (String) map.get("unionid");
                        results[2] = (String) map.get("access_token");
                    }
                });


            return results;

        case "qq":
            url = "https://graph.qq.com/oauth2.0/token?grant_type=" +
                grant_type + "&client_id=" + appid + "&client_secret=" +
                secret + "&code=" + code + "&redirect_uri=" + redirect_uri;

            final String[] access_token = new String[1];
            HttpUtils.getResultEntity(url, null, null, HttpUtils.GET, true,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        System.out.println("请求qq:access_token完成");

                        String[] arr = String.valueOf(result).split("&");
                        String[] arr1 = arr[0].split("=");
                        access_token[0] = arr1[1];
                    }
                });
            url = "https://graph.qq.com/oauth2.0/me?access_token=" +
                access_token[0] + "&unionid=1";
            results = new String[2];
            HttpUtils.getResultEntity(url, null, null, HttpUtils.GET, true,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        System.out.println("请求qq:openId、unionid完成");

                        String[] arr = String.valueOf(result).split(" ");
                        Map map = toHashMap(arr[1]);
                        results[0] = (String) map.get("openid");
                        results[1] = (String) map.get("unionid");
                    }
                });
            return results;
        default:
            return null;
        }
    }

    /**
     * 获取微信个人信息
     * author: wenxian.cai
     * date: 2017/11/7 14:29
     */
    /*protected WeiXinUserInfoPO getUserInfo(String accessToken, String openId) {
        final WeiXinUserInfoPO po = new WeiXinUserInfoPO();
        String url = null;
        final String[] results;
        url = "https://api.weixin.qq.com/sns/userinfo?access_token="+ accessToken +"&openid="+ openId +"&lang=zh_CN";
        results = new String[2];
        HttpUtils.getResultEntity(url, null, null, HttpUtils.GET, true,
                new HttpUtils.HttpCallBack() {
                    @Override
                    public void resultCallBack(Object result) {
                        System.out.println("请求weixin个人信息完成");

                        Map map = toHashMap(result);
                        results[0] = (String) map.get("openid");
                        results[1] = (String) map.get("unionid");
                        po.setUnionid((String) map.get("unionid"));
                        po.setOpenid((String) map.get("openid"));
                        po.setNickname((String) map.get("nickname"));
                        po.setSex((String) map.get("sex"));
                        po.setProvince((String) map.get("province"));
                        po.setCity((String) map.get("city"));
                        po.setCountry((String) map.get("country"));
                        po.setHeadimgurl((String) map.get("headimgurl"));
//                        po.setPrivilege((String) map.get("privilege"));
                    }
                });
        return po;
    }*/

    protected LoginUser getLoginUser() {
        return ClientManager.getSessionUser();
    }
}
