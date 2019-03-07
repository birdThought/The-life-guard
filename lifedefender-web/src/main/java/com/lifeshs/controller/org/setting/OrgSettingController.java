package com.lifeshs.controller.org.setting;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.IValidCodeService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;

/**
 * Created by XuZhanSi on 2016/12/10 0010.
 */
@Controller
@RequestMapping("/orgSetControl")
public class OrgSettingController extends BaseController {

    @Autowired
    private IOrgUserService userService;
    
    @Autowired
    private ValidCodeServiceImpl validCodeUtil;

    @Autowired
    private ISessionManageService sessionManageService;
    @Autowired
    private IValidCodeService validCodeService;

    /**
     * 安全设置页面
     *
     * @return
     */
    @RequestMapping(params = "security")
    public ModelAndView securitySetPage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/securitySetting");
        int userId = getLoginUser().getId();
        OrgUserSharingData orgUserSharingData = sessionManageService.getCacheOrgMemberSharingData(userId);
        DataResult data = userService.getUserSecurityData(orgUserSharingData);
        modelAndView.addObject("data", data);
        return modelAndView;
    }

    /**
     * 验证手机页面
     *
     * @return
     */
    @RequestMapping(params = "veriMobile")
    public ModelAndView veriMobilePage(@RequestParam(required = false, defaultValue = "false") Boolean bindNew) {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/veriMobile1");
        
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        
        String mobile = orgUserSharingData.getMobile();
        modelAndView.addObject("mobile", mobile.substring(0, 3) + "***" + mobile.substring(8));
        modelAndView.addObject("isBindNew", bindNew);
        return modelAndView;
    }

    /**
     * 发送验证码
     *
     * @return
     */
    @RequestMapping(params = "sendVerify")
    public @ResponseBody AjaxJson sendVerifyCode() {
        AjaxJson ajaxJson = new AjaxJson();
        
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        int sendId = orgUserSharingData.getId();
        String mobile = orgUserSharingData.getMobile();
        try {
            if (StringUtils.isEmpty(validCodeUtil.sendToMobile(sendId, mobile, "mobile", CacheType.USER_MOBILE_MODIFY, VcodeTerminalType.ORG_PLATFORM, false))) {
                ajaxJson.setSuccess(false);
                return ajaxJson;
            }
        } catch (SMSException e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 验证手机短信验证码
     *
     * @param code
     *            验证码
     * @param newMobile
     *            如果是新手机这里不会为空
     * @return
     */
    @RequestMapping(params = "verifyCode")
    public @ResponseBody AjaxJson verifyCode(@RequestParam String code,
            @RequestParam(required = false) String newMobile) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        
        String mobile = newMobile == null ? orgUserSharingData.getMobile() : newMobile;
        if (!validCodeService.valid(mobile, code, CacheType.USER_MOBILE_MODIFY)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("验证码错误");
            return ajaxJson;
        }
        ajaxJson.setSuccess(userService.updateOrgUserMobile(user.getId(), mobile));
        return ajaxJson;
    }

    /**
     * 绑定手机成功的页面
     * 
     * @param bindNew
     * @return
     */
    @RequestMapping(params = "verifySuccess")
    public ModelAndView verifySuccessPage(@RequestParam(required = false, defaultValue = "false") Boolean bindNew) {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/veriMobile3");
        modelAndView.addObject("isBindNew", bindNew);
        return modelAndView;
    }

    /**
     * 绑定新手机页面
     * 
     * @return
     */
    @RequestMapping(params = "bindNewMobile")
    public ModelAndView bindNewMobilePage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/veriMobile2");
        return modelAndView;
    }

    /**
     * 绑定邮箱页面
     * 
     * @return
     */
    @RequestMapping(params = "bindEmailPage")
    public ModelAndView bindEmailPage() {
        OrgUserSharingData orgUserSharingData = getOrgUserSharingData();
        
        ModelAndView modelAndView = new ModelAndView("com/QYPart/verifyEmail");
        modelAndView.addObject("email", orgUserSharingData.getEmail());
        return modelAndView;
    }

    /**
     * 绑定邮箱
     * 
     * @return
     */
    @RequestMapping(params = "bindEmail")
    public @ResponseBody AjaxJson checkEmailCode(@RequestBody Map<String, Object> params) {
        AjaxJson ajaxJson = new AjaxJson();
        String email = (String) params.get("email");
        String code = (String) params.get("code");
        if (!validCodeService.valid(email, code, CacheType.USER_EMAIL_MODIFY)) {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("邮箱验证码有误，请重新输入");
            return ajaxJson;
        }
        ajaxJson.setSuccess(userService.updateOrgUserEmail(getLoginUser().getId(), email));
        return ajaxJson;
    }

    /**
     * 意见反馈页面
     * 
     * @return
     */
    @RequestMapping(params = "suggestionPage")
    public ModelAndView suggestionPage() {
        ModelAndView modelAndView = new ModelAndView("com/QYPart/suggestion");
        Map<String,Object> condition=new HashMap<>();
        condition.put("userId",getLoginUser().getId());
        int count= commonTrans.getCount(TReport.class,condition);
        modelAndView.addObject("pageCount",(int) (count % 10 == 0 ? count / 10 : Math.floor(count / 10) + 1));
        modelAndView.addObject("data", JSONObject.toJSON(commonTrans.findEntityByPageDesc(TReport.class,condition,1,10,"createDate")));
        return modelAndView;
    }

}
