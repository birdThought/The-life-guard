package com.lifeshs.controller.app.h5;

import static com.lifeshs.common.constants.common.CacheType.OAUTH_CACHE;
import static com.lifeshs.utils.JSONHelper.toHashMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.alipay.util.WechatMessageUtil;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.support.plantform.security.exception.ValidCodeException;
import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.thirdservice.WeiXinGZPTApi;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.HttpUtils;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PropertiesUtil;

/**
 * 微信公众号相关操作
 * author: wenxian.cai
 * date: 2017/11/7 17:30
 */
@RequestMapping("wechat")
@Controller
public class WeChatController extends BaseController{
	final static Logger logger = Logger.getLogger(WeChatController.class);
	@Autowired
	IMemberService memberService;
	@Autowired
	public ISessionManageService loginService;
	@Autowired
    private WeiXinGZPTApi weixinApi;

	/**
	 * 微信自动登录
	 * author: wenxian.cai
	 * date: 2017/11/8 16:51
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView oauthWeiXin(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		logger.info("链接带着一个参数叫做code:" + code);
		PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
        String appId = pro.readProperty("weixin.appId");
        String secret = pro.readProperty("weixin.secret");
//		String appId = "wx491346093dceed17"; //测试号
//		String secret ="134b9bc3a1259102e3eff96aea781420"; //测试号
		String grantType = pro.readProperty("weixin.grantType");
		String[] result = getAccessToken("weixin", appId, secret, code,
				grantType, null);
		String openId = result[0];
		logger.info("查询这个code对应的授权openId:" + openId);

		if (openId == null) {
			logger.error("获取用户openId失败");
			openId = request.getParameter("openId");	//获取绑定openId成功之后跳转带的openId
		}
		//openId = "onnRhwyvtMOkTt1V2uBbxfSB2qvk"; //测试账号
		UserDTO user = memberService.getUserByOpenId(openId);
		if (user == null) { //用户不存在，跳转到绑定界面
			ModelAndView modelAndView = new ModelAndView("mobile/wechat/account-bind");
			modelAndView.addObject("openId", openId);
			return modelAndView;
		}
		cacheService.saveKeyValue(OAUTH_CACHE, "oauth" + user.getUserName(), user.getPassword());
		//尝试登陆
		try {
			loginService.checkUser(user.getUserName(), user.getPassword(), "", false, null);
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
		Subject currentUser = SecurityUtils.getSubject();
		if ((user == null) || !currentUser.isAuthenticated()) {
			return new ModelAndView(""); //重新进入授权url
		}

		return new ModelAndView(new RedirectView("/wechat/health-record-page"));
	}

	/**
	 * 普通登录页，供第三方使用，无需绑定openid，每次进入都需要在此登录
	 * author: dengfeng
	 */
	@RequestMapping(value = "/generallogin/{org}", method = RequestMethod.GET)
	public ModelAndView generallogin(@PathVariable("org") String org){
		ModelAndView modelAndView = new ModelAndView("mobile/wechat/general-login");
		modelAndView.addObject("org", org);
		return modelAndView;
	}

	/**
	 * 普通登录页响应
	 * author: wenxian.cai
	 * date: 2017/11/8 16:51
	 */
	@RequestMapping(value = "/general-login-do", method = RequestMethod.PATCH)
	@ResponseBody
	public AjaxJson generallogindo(@RequestBody Map<String, String> map){
		String userName = String.valueOf(map.get("userName"));
		String password = String.valueOf(map.get("password"));
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(true);
		UserDTO user = memberService.getUser(userName);
		if (user == null) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("用户不存在");
			return ajaxJson;
		}
		if (!MD5Utils.encryptPassword(password).equals(user.getPassword())) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("账户或者密码不正确");
			return ajaxJson;
		}

		cacheService.saveKeyValue(OAUTH_CACHE, "oauth" + user.getUserName(), user.getPassword());
		//尝试登陆
		try {
			loginService.checkUser(user.getUserName(), user.getPassword(), "", false, null);
		} catch (IncorrectCredentialsException e) {
			ajaxJson.setMsg("登录密码错误");
		} catch (ExcessiveAttemptsException e) {
			ajaxJson.setMsg("登录失败次数过多. ");
		} catch (LockedAccountException e) {
			ajaxJson.setMsg("帐号已被锁定. ");
		} catch (DisabledAccountException e) {
			ajaxJson.setMsg("帐号已被禁用. ");
		} catch (ExpiredCredentialsException e) {
			ajaxJson.setMsg("帐号已过期. ");
		} catch (UnknownAccountException e) {
			ajaxJson.setMsg("帐号不存在. ");
		} catch (UnauthorizedException e) {
			ajaxJson.setMsg("您没有得到相应的授权. ");
		} catch (ValidCodeException e) {
			ajaxJson.setMsg("用户名或密码异常. ");
		} catch (AuthenticationException e) {
			ajaxJson.setMsg("您身份验证令牌提交失败. ");
		}
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 微信健康档案主界面
	 * author: wenxian.cai
	 * date: 2017/11/9 14:42
	 */
	@RequestMapping(value = "/health-record-page", method = RequestMethod.GET)
	public ModelAndView healthRecord() {
		return new ModelAndView("mobile/wechat/health-record");
	}

	/**
	 * 绑定用户微信openId
	 * author: wenxian.cai
	 * date: 2017/11/7 19:36
	 */
	@RequestMapping(value = "/bind-account", method = RequestMethod.PATCH)
	@ResponseBody
	public AjaxJson bingWeChat(@RequestBody Map<String, String> map) {
		AjaxJson ajaxJson = new AjaxJson();
		String userName = String.valueOf(map.get("userName"));
		String password = String.valueOf(map.get("password"));
		String openId = String.valueOf(map.get("openId"));
		UserDTO user = memberService.getUser(userName);
		if (user == null) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("用户不存在");
			return ajaxJson;
		}
		if (!MD5Utils.encryptPassword(password).equals(user.getPassword())) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("账户或者密码不正确");
			return ajaxJson;
		}
		try {
			memberService.updateOpenId(user.getId(), openId);
		} catch (OperationException e) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}
	
	@RequestMapping(value="/unbind-account",method=RequestMethod.GET)
	public ModelAndView cancelWeChat(){
            try {
                
                int userId = getMemberSharingData().getId();
                int resultNum = memberService.updateOpenId(userId, "");
                if(1 == resultNum){
                    //清除缓存
                    Subject subject = SecurityUtils.getSubject();
                    Session session = subject.getSession();
                    ClientManager.getInstance().removeClinet((String) session.getId());
                    
                    ModelAndView modelAndView = new ModelAndView("mobile/wechat/account-bind");
                    modelAndView.addObject("openId", null);
                    return modelAndView;
                    
                }
            } catch (OperationException e) {
                e.printStackTrace();
            }
	    
            return new ModelAndView("");
	}

	/**
	 * 获取个人档案
	 * author: wenxian.cai
	 * date: 2017/11/9 11:47
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getUserInfo() {
		AjaxJson ajaxJson = new AjaxJson();
//		UserRecordDTO dto = memberService.getRecord(getLoginUser().getId());
		Map map = new HashMap();
		map.put("photo", getMemberSharingData().getPhotoPath());
		map.put("userName", getMemberSharingData().getUserName());
		map.put("realName", getMemberSharingData().getRealName());
		map.put("gender", getMemberSharingData().getSex());
		map.put("age", DateTimeUtilT.calculateAge(getMemberSharingData().getBirthday()));
		map.put("height", getMemberSharingData().getHeight());
		map.put("weight", getMemberSharingData().getWeight());
		map.put("waist", getMemberSharingData().getWaist());
		map.put("bust", getMemberSharingData().getBust());
		map.put("hip", getMemberSharingData().getHip());
		ajaxJson.setObj(map);
		return ajaxJson;
	}


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
     * 
     *  微信公众号聊天功能
     *  @author liaoguo
     *  @DateTime 2018年10月26日 上午9:02:57
     *
     *  @param request
     *  @param response
     *  @throws IOException
     */
	@RequestMapping(value = "/chat",method = RequestMethod.GET)
    public void callback(PrintWriter out, HttpServletResponse response,
                       @RequestParam(value = "signature", required = false) String signature, @RequestParam String timestamp,
                       @RequestParam String nonce, @RequestParam String echostr) {
    logger.info("signature：" + signature + "\ntimestamp：" + timestamp + "\nnonce：" + nonce + "\nechostr：" + echostr);
    if (WechatMessageUtil.checkSignature(signature, timestamp, nonce)) {
        logger.info(echostr);
        out.print(echostr);
    }
}
	
	/**
	 * 
	 *  微信公众号聊天功能
	 *  @author liaoguo
	 *  @DateTime 2018年10月26日 上午9:02:57
	 *
	 *  @param request
	 *  @param response
	 *  @throws IOException
	 */
    @RequestMapping(value = "/chat",method = RequestMethod.POST)
    @ResponseBody
    public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseMessage = weixinApi.processRequest(request);
        logger.info("**********responseMessage:"+responseMessage);
        System.out.println(responseMessage);
        out.print(responseMessage);
        out.flush();
    }
}
