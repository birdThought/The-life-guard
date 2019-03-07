package com.lifeshs.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.ContactTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.log.LoginLogDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.commond.TUserInfoDto;
import com.lifeshs.pojo.member.contact.ContactDto;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.log.ILogService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.service.tool.IValidCodeService;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 版权归 TODO 普通用户的基本控制器
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月13日 上午10:18:33
 */
@Controller
@RequestMapping(value = {"/memberControl","/member"})
public class MemberController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberController.class);
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IMemberService memberService;

    @Autowired
    IContactsService contactsService;

    @Autowired
    private ITerminalService terminalService;

    @Autowired
    private IValidCodeService validCodeService;

    @Autowired
    private ILogService logService;


    @RequestMapping(params = "unBindDevice", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson unBindDevice(@RequestParam(value = "imei", required = true) String imei,
            @RequestParam(value = "terminalType", required = true) String terminalType) throws Exception {

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "解绑失败";
        // 检查参数是否为空
        if (StringUtils.isBlank(imei)) {
            resObject.setMsg(msg + "：参数为空");
            return resObject;
        }

        // 获取当前用户id
        int userId = getLoginUser().getId();
        // try {
        if (terminalService.unBindTerminal(userId, imei, terminalType)) {
            resObject.setSuccess(true);
            msg = "解绑成功";
        } else {
            resObject.setSuccess(false);
            msg = "设备未绑定";
        }
        // } catch (Exception e) {
        // logger.error("解绑失败:" + e.getMessage());
        // }
        updateLoginUserData();
        resObject.setMsg(msg);
        return resObject;
    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年7月19日 上午10:47:23
     * @serverComment 绑定设备
     * 
     * @param imei
     * @param mobile
     * @param sosPhone
     * @param terminalType
     * @return
     */
    @RequestMapping(params = "bindDevice", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson bindDevice(@RequestParam(value = "imei") String imei,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "sosPhone") String sosPhone,
            @RequestParam(value = "terminalType") String terminalType) {

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "绑定失败";

        // 检查参数是否为空
        if (StringUtils.isBlank(imei) || StringUtils.isBlank(mobile) || StringUtils.isBlank(sosPhone)) {
            resObject.setMsg(msg + "：参数为空");
            return resObject;
        }

        // 获取当前用户
        int userId = getLoginUser().getId();
        ServiceMessage sm = new ServiceMessage();
        // 检查 用户是否已绑定了与deivceType相同系列的设备
        try {
            if (!terminalService.isUserBindSameTerminal(userId, terminalType)) {
                sm = terminalService.bindTerminal(userId, imei, "设备备注名", mobile, terminalType);
//              int terminalTypeValues = 0;
//              for (ContactTerminalType name : ContactTerminalType.values()) {
//                  if (name.getName().equals(terminalType)) {
//                      terminalTypeValues = name.value();
//                  }
//              }
                contactsService.addContact(userId, "C3预警号码", sosPhone, ContactTerminalType.C3_SOS.value());
                resObject.setSuccess(sm.isSuccess());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("imei", imei);
                map.put("mobile", mobile);
                resObject.setAttributes(map);
                resObject.setMsg(sm.getMessage());
            } else {
                resObject.setSuccess(false);
                resObject.setMsg("用户绑定了同系列的设备，需解绑后再进行绑定!");
            }
        } catch (Exception e) {
            logger.error("绑定失败:" + sm.getMessage());
        }
        updateLoginUserData();
        return resObject;

    }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年7月19日 上午10:43:42
     * @serverComment 跳转到用户设备列表界面
     * 
     * @param model
     * @return
     */
    @RequestMapping(params = "getDeviceInfo")
    public String getDeviceInfo(Model model) {
        // 获取当前用户Id
        int userId = getLoginUser().getId();
        // 获取当前用户的设备列表
        List<TUserTerminal> userTerminals = null;
        try {
            userTerminals = terminalService.geTUserTerminals(userId);
        } catch (Exception e) {
            logger.error("获取设备列表失败:" + e.getMessage());
        }

        if (!userTerminals.isEmpty()) {
            // 遍历终端列表
            for (TUserTerminal userTerminal : userTerminals) {
                String terminalType = userTerminal.getTerminalType();
                // 分类
                if ("HL-031".equals(terminalType)) {
                    model.addAttribute("HL", userTerminal);
                } else if ("C3".equals(terminalType)) {
                    model.addAttribute("C3", userTerminal);
                }
            }
        }

        return "com/lifeshs/member/wearableDevice";
    }
    /**
     * 注册-提交个人信息
     * @param param
     * @return
     */
    @RequestMapping(params = "submitUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson submitUserInfo(@RequestParam Map<String, Object> param) {
        AjaxJson ajaxJson = new AjaxJson();
        String realName = (String) param.get("username");
        String sex =  (String)param.get("sex");
        boolean isSex = false;
        if ("true".equals(sex)) {
            isSex = true;
        }
        String birthday = (String) param.get("birthday");
        String province = (String) param.get("province");
        String city = (String) param.get("city");
        String country = (String) param.get("country");
        int userId = Integer.parseInt((String) param.get("userId"));
        TUserInfoDto tUser = memberService.getUserInfo(userId);
        tUser.setRealName(realName);
        tUser.setBirthday(DateTimeUtil.strFormatDate3(birthday));
        tUser.setSex(isSex);
        tUser.setProvince(province);
        tUser.setCity(city);
        tUser.setCountry(country);
        if (!memberService.updatetUserInfo(tUser)) {
            ajaxJson.setMsg("操作失败");
            return ajaxJson;
        }
        return ajaxJson;
    }

        /**
         * 跳转到用户个人信息界面
         *
         * @author dachang.luo
         * @DateTime 2016年6月8日下午13:04:55
         *
         * @param model
         * @return
         */
    @RequestMapping(params = "getUserInfor")
    public String getUserInfor(Model model) {
        int userId = getLoginUser().getId();
        TUserInfoDto tUser = memberService.getUserInfo(userId);

        model.addAttribute("user", tUser);
        return "com/lifeshs/member/userInfor";
    }

    /**
     * <p>
     * 修改用户信息
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午1:53:36
     *
     * @param realName
     *            真实姓名
     * @param sex
     *            性别
     * @param birthday
     *            生日
     * @param mobile
     *            手机号码
     * @param mobileVerify
     *            手机验证情况
     * @param email
     *            邮箱
     * @param emailVerify
     *            邮箱验证情况
     * @return
     */
    @RequestMapping(params = "updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateUserInfo(@RequestParam String realName, @RequestParam String sex,
                                   @RequestParam String birthday, @RequestParam String mobile,
                                   @RequestParam boolean mobileVerify, @RequestParam String email,
                                   @RequestParam boolean emailVerify, @RequestParam (required = false) String province,
                                   @RequestParam (required = false) String city, @RequestParam (required = false) String country) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg="修改个人信息失败";
        int userId = getLoginUser().getId();
        // 获取当前用户信息
        TUserInfoDto tUser = new TUserInfoDto();
        tUser.setId(userId);
        tUser.setRealName(realName);
        tUser.setBirthday(DateTimeUtilT.date(birthday));
        tUser.setSex(sex.equals("male"));
        if (!mobileVerify && StringUtils.isNotBlank(mobile)) {
            if (StringUtils.isNotEmpty(memberService.checkMobile(mobile))) {
                resObject.setMsg("该手机号已被验证");
                return resObject;
            }
            tUser.setMobile(mobile);
        }
        if (!emailVerify && StringUtils.isNotBlank(email)) {
            try {
                if (StringUtils.isNotEmpty(memberService.checkEmail(email))) {
                    resObject.setMsg("该邮箱已被验证");
                    return resObject;
                }
            } catch (Exception e) {
                resObject.setMsg("修改个人信息失败,请重试");
                return resObject;
            }
            tUser.setEmail(email);
        }
        // 修改不成功
        if (!memberService.updatetUserInfo(tUser)) {
            resObject.setMsg(msg);
            return resObject;
        }
        // 修改成功
        resObject.setSuccess(true);
        resObject.setMsg("修改个人信息成功");
        updateLoginUserData(); // 更新session中的loginUser数据
        return resObject;
    }

    /**
     * @Description: 更新用户信息（新版本）
     * @author: wenxian.cai
     * @create: 2017/4/24 11:43
     * TODO 全部更新完毕后与updateUserInfo合并
     */
    @RequestMapping(params = "updateUserInfo_new", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateUserInfo_new(@RequestParam String realName, @RequestParam boolean gender,
                                       @RequestParam String birthday, @RequestParam String province,
                                       @RequestParam String city, @RequestParam (required = false) String country,
                                       @RequestParam String height, @RequestParam (required = false) String weight,
                                       @RequestParam String hip, @RequestParam String waist, @RequestParam String bust,
                                       @RequestParam(required = false) String workSpace, @RequestParam String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg="修改个人信息失败";
        int userId = getLoginUser().getId();
        // 获取当前用户信息
        TUserInfoDto tUser = new TUserInfoDto();
        tUser.setId(userId);
        tUser.setRealName(realName);
        tUser.setBirthday(DateTimeUtilT.date(birthday));
        tUser.setSex(gender);
        // 修改不成功
        if (!memberService.updatetUserInfo(tUser)) {
            resObject.setMsg(msg);
            return resObject;
        }
        // 修改成功
        resObject.setSuccess(true);
        resObject.setMsg("修改个人信息成功");
        updateLoginUserData(); // 更新session中的loginUser数据
        return resObject;
    }

    /**
     * <p>
     * 头像修改
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月5日 上午9:39:05
     *
     * @param relativePath
     *            文件相对路径
     * @return
     */
    @RequestMapping(params = "modifyPhoto", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyPhoto(@RequestParam String relativePath, @RequestParam int userId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "提交失败";

        // 将图片从tmp目录转移到upload下
        String newNetPath = null;
        try {
            newNetPath = ImageUtilV2.copyImgFileToUploadFolder(Normal.PHOTO_PREFIX + relativePath, "head");
        } catch (IOException e) {
            logger.error("图片转移地址失败");
            resObject.setMsg("病程图片保存失败");
        }
        // 修改用户头像信息
        if (!memberService.modifyUserPhoto(userId, newNetPath)) {
            resObject.setMsg(msg);
            return resObject;
        }

        // 更新用户缓存信息
        updateLoginUserData();

        resObject.setSuccess(true);
        resObject.setMsg("修改头像成功");
        return resObject;
    }

    /**
     * 进入用户修改手机号码界面
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:58:27
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "enterModifyMobile", method = RequestMethod.GET)
    public String enterModiftMobile(Model model) {
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();

        MemberSharingData memberSharingData = getCacheMemberSharingData(userId);

        String mobile = memberSharingData.getMobile();
        Boolean mobileVerified = memberSharingData.getMobileVerified();
        model.addAttribute("mobile", mobile);
        model.addAttribute("verified", mobileVerified);
        return "com/lifeshs/member/userBphone";
    }

    /**
     * 进入用户修改邮箱界面
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午5:00:07
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "enterModifyEmail", method = RequestMethod.GET)
    public String enterModifyEmail(Model model) {
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();

        MemberSharingData memberSharingData = getCacheMemberSharingData(userId);

        String email = memberSharingData.getEmail();
        Boolean emailVerified = memberSharingData.getEmailVerified();
        model.addAttribute("email", email);
        model.addAttribute("verified", emailVerified);
        return "com/lifeshs/member/userBemail";
    }

    /**
     * 修改手机号码-检查验证码是否正确有效(是否放行到填写新手机页面)
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午3:47:51
     * @serverComment 服务注解
     *
     * @param idcode
     * @param model
     * @return
     */
    @RequestMapping(params = "checkModifyMobileCode", method = RequestMethod.POST)
    public String checkModifyMobileCode(@RequestParam String idcode, Model model) {
        LoginUser user = getLoginUser();
        int userId = user.getId();
        MemberSharingData memberSharingData = getCacheMemberSharingData(userId);

        String mobile = memberSharingData.getMobile();
        if (validCodeService.valid(mobile, idcode, CacheType.USER_MOBILE_MODIFY)) {
            // 将数据保存在model中，保存到页面的隐藏域
            model.addAttribute("validCode", idcode);
            return "com/lifeshs/member/userBphone_2"; // 成功页面
        } else {
            return "redirect:memberControl.do?enterModifyMobile"; // 失败页面
        }
    }

    /**
     * 修改邮箱-检查手机验证码是否正确有效(是否放行到填写新邮箱页面)
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:44:34
     * @serverComment 服务注解
     *
     * @param idcode
     * @param model
     * @return
     */
    @RequestMapping(params = "checkModifyEmailCode", method = RequestMethod.POST)
    public String checkModifyEmailCode(@RequestParam String idcode, Model model) {
        LoginUser user = getLoginUser();
        int userId = user.getId();
        MemberSharingData memberSharingData = getCacheMemberSharingData(userId);

        String email = memberSharingData.getEmail();
        if (validCodeService.valid(email, idcode, CacheType.USER_EMAIL_MODIFY)) {
            // 将数据保存在model中，保存到页面的隐藏域
            model.addAttribute("validCode", idcode);
            return "com/lifeshs/member/userBemail_2"; // 成功页面
        } else {
            return "redirect:memberControl.do?enterModifyEmail"; // 失败页面
        }
    }

    /**
     * 修改用户手机号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:11:53
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "modifyMobile", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson modifyMobile(HttpServletRequest request) {

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改手机失败";
        String userName = "";

        Map<String, Object> param = ParserParaUtil.getParams(request);
        String newMobile = (String) param.get("newMobile");
        String newMobileCode = (String) param.get("newMobileCode");
        String oldMobileCode = (String) param.get("oldMobileCode");

        LoginUser loginUser = getLoginUser();

        int userId = loginUser.getId();

        MemberSharingData data = getCacheMemberSharingData(userId);

        String oldMobile = data.getMobile();

        if (StringUtils.isBlank(oldMobileCode)) {
            resObject.setMsg(msg + ":请勿违规操作");
            return resObject;
        }
        if (StringUtils.equals(newMobile, oldMobile)) {
            resObject.setMsg("请确认新手机号与旧手机号不相同");
            return resObject;
        }
        // 校验原号码的验证码是否正确或者过期
        if (!validCodeService.valid(oldMobile, oldMobileCode, CacheType.USER_MOBILE_MODIFY)) {
            resObject.setMsg(msg + ":原号码验证码已失效");
            return resObject;
        }
        // 校验新号码的验证码是否正确或者过期
        if (!validCodeService.valid(newMobile, newMobileCode, CacheType.USER_MOBILE_MODIFY)) {
            resObject.setMsg(msg + ":新号码验证码不正确或者已失效");
            return resObject;
        }
        // if (StringUtils.isNotEmpty(memberService.checkMobile(newMobile))) {
        // resObject.setMsg(msg + ":新手机号码已被绑定");
        // return resObject;
        // }
        if (!memberService.updateMobile(userId, newMobile)) {
            resObject.setMsg(msg + ":修改手机号码失败");
            return resObject;
        }

        updateLoginUserData(); // 更新session中的loginUser数据

        // 清除缓存
        validCodeService.destory(oldMobile, CacheType.USER_MOBILE_MODIFY);
        validCodeService.destory(newMobile, CacheType.USER_MOBILE_MODIFY);
        msg = "修改手机号码成功";
        resObject.setObj(userName);
        resObject.setSuccess(true);
        resObject.setMsg(msg);
        return resObject;
    }

    /**
     * @Description: 修改手机号码--修改版本
     * @author: wenxian.cai
     * @create: 2017/4/21 9:42
     * TODO 全部更新完毕后与modifyMobile合并
     */
    @RequestMapping(params = "modifyMobile_new")
    @ResponseBody
    public AjaxJson modifyMobile_new(@RequestParam(value = "mobile") String mobile,
                                    @RequestParam(value = "code") String code) {

        AjaxJson resObject = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        if (StringUtils.isBlank(code)) {
            resObject.setMsg("验证码不能为空");
            resObject.setSuccess(false);
            return resObject;
        }
        if (StringUtils.isBlank(mobile)) {
            resObject.setMsg("手机号码不能为空");
            resObject.setSuccess(false);
            return resObject;
        }
        // 校验邮箱的验证码是否正确或者过期
        if (!validCodeService.valid(mobile, code, CacheType.USER_MOBILE_MODIFY)) {
            resObject.setMsg("手机验证码不正确或者已失效");
            resObject.setSuccess(false);
            return resObject;
        }
        try {
            if (!memberService.updateMobile(userId, mobile)) {
                resObject.setMsg("修改手机号码失败");
                resObject.setSuccess(false);
                return resObject;
            }
        } catch (Exception e) {
            logger.error("操作失败:" + e.getMessage());
            resObject.setMsg("操作失败");
            return resObject;
        }

        updateLoginUserData(); // 更新session中的loginUser数据

        // 清除缓存
        validCodeService.destory(mobile, CacheType.USER_MOBILE_MODIFY);
        return resObject;
    }

    /**
     * 修改用户邮箱
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:11:53
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "modifyEmail")
    @ResponseBody
    public AjaxJson modifyEmail(HttpServletRequest request) {

        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改邮箱失败";
        String userName = "";
        Map<String, Object> param = ParserParaUtil.getParams(request);
        String newEmail = (String) param.get("newEmail");
        String newEmailCode = (String) param.get("newEmailCode");
        String emailCode = (String) param.get("emailCode");

        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();

        MemberSharingData data = getCacheMemberSharingData(userId);

        String email = data.getEmail();
        userName = data.getUserName();

        if (StringUtils.isBlank(emailCode)) {
            resObject.setMsg(msg + ":请勿违规操作");
            return resObject;
        }
        // 校验验证码是否正确或者过期
        if (!validCodeService.valid(email, emailCode, CacheType.USER_EMAIL_MODIFY)) {
            resObject.setMsg(msg + ":邮箱验证码已失效");
            return resObject;
        }
        // 校验邮箱的验证码是否正确或者过期
        if (!validCodeService.valid(newEmail, newEmailCode, CacheType.USER_EMAIL_MODIFY)) {
            resObject.setMsg(msg + ":邮箱验证码不正确或者已失效");
            return resObject;
        }
        try {
            if (!memberService.updateEmail(userId, newEmail)) {
                resObject.setMsg(msg + ":修改邮箱失败");
                return resObject;
            }
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());
            return resObject;
        }

        updateLoginUserData(); // 更新session中的loginUser数据

        // 清除缓存
        validCodeService.destory(email, CacheType.USER_EMAIL_MODIFY);
        validCodeService.destory(newEmail, CacheType.USER_EMAIL_MODIFY);
        msg = "修改绑定邮箱成功";
        resObject.setSuccess(true);
        resObject.setObj(userName);
        resObject.setMsg(msg);
        return resObject;
    }

    /**
     * @Description: 修改邮箱--修改版本
     * @author: wenxian.cai
     * @create: 2017/4/21 9:42
     * TODO 全部更新完毕后与modifyEmail合并
     */
    @RequestMapping(params = "modifyEmail_new")
    @ResponseBody
    public AjaxJson modifyEmail_new(@RequestParam(value = "email") String email,
                                    @RequestParam(value = "code") String code) {

        AjaxJson resObject = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        if (StringUtils.isBlank(code)) {
            resObject.setMsg("验证码不能为空");
            resObject.setSuccess(false);
            return resObject;
        }
        if (StringUtils.isBlank(email)) {
            resObject.setMsg("邮箱地址不能为空");
            resObject.setSuccess(false);
            return resObject;
        }
        // 校验邮箱的验证码是否正确或者过期
        if (!validCodeService.valid(email, code, CacheType.USER_EMAIL_MODIFY)) {
            resObject.setMsg("邮箱验证码不正确或者已失效");
            resObject.setSuccess(false);
            return resObject;
        }
        try {
            if (!memberService.updateEmail(userId, email)) {
                resObject.setMsg("修改邮箱失败");
                resObject.setSuccess(false);
                return resObject;
            }
        } catch (Exception e) {
            logger.error("操作失败:" + e.getMessage());
            resObject.setMsg("操作失败");
            return resObject;
        }

        updateLoginUserData(); // 更新session中的loginUser数据

        // 清除缓存
        validCodeService.destory(email, CacheType.USER_EMAIL_MODIFY);
        return resObject;
    }

    /**
     * 成功修改手机号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月15日下午1:53:02
     * @serverComment 服务注解
     *
     * @return
     */
    @RequestMapping(params = "modifyMobileSuccess")
    public String modifyMobileSuccess(Model model, @RequestParam(required = false) Integer result) {
        // 默认为2
        // 0表示首次验证邮箱,验证失败
        // 1表示首次验证邮箱,验证成功
        // 2表示本次页面跳转是修改邮箱操作
        result = result == null ? 2 : result;
        model.addAttribute("result", result);
        return "com/lifeshs/member/userBphone_3";
    }

    /**
     * <p>
     * 第一次验证手机
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月10日 下午2:33:42
     *
     * @param code
     *            验证码
     * @return
     */
    @RequestMapping(params = "modifyMobileFirstTime", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyMobileFirstTime(@RequestParam String idcode) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "";
        LoginUser loginUser = getLoginUser();

        MemberSharingData data = getCacheMemberSharingData(loginUser.getId());
        String mobile = data.getMobile();

        if (!validCodeService.valid(mobile, idcode, CacheType.USER_MOBILE_MODIFY)) {
            resObject.setObj(0);
            resObject.setMsg("验证码错误");
            return resObject;
        } else {
            try {
                memberService.updateMobile(loginUser.getId(), mobile);
            } catch (Exception e) {
                resObject.setObj(0);
                msg = "修改手机失败:" + e.getMessage();
                logger.error(msg);
                resObject.setMsg(msg);
                return resObject;
            }
            updateLoginUserData(); // 更新session中的loginUser数据
        }
        resObject.setObj(1);
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * <p>
     * 成功修改邮箱
     * 
     * @author dachang.luo
     * @DateTime 2016年6月15日下午1:53:05
     *
     * @param model
     * @param result
     *            验证结果
     * @return
     */
    @RequestMapping(params = "modifyEmailSuccess")
    public String modifyEmailSuccess(Model model, @RequestParam(required = false) Integer result) {
        // 默认为2
        // 0表示首次验证邮箱,验证失败
        // 1表示首次验证邮箱,验证成功
        // 2表示本次页面跳转是修改邮箱操作
        result = result == null ? 2 : result;
        model.addAttribute("result", result);
        return "com/lifeshs/member/userBemail_3";
    }

    /**
     * <p>
     * 第一次验证邮箱
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月10日 下午2:33:42
     *
     * @param idcode
     *            验证码
     * @return
     */
    @RequestMapping(params = "modifyEmailFirstTime", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyEmailFirstTime(@RequestParam String idcode) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "";
        LoginUser loginUser = getLoginUser();

        MemberSharingData data = getCacheMemberSharingData(loginUser.getId());
        String email = data.getEmail();

        if (!validCodeService.valid(email, idcode, CacheType.USER_EMAIL_MODIFY)) {
            resObject.setObj(0);
            resObject.setMsg("验证码错误");
            return resObject;
        } else {
            try {
                memberService.updateEmail(loginUser.getId(), email);
            } catch (Exception e) {
                resObject.setObj(0);
                msg = "修改邮箱失败:" + e.getMessage();
                logger.error(msg);
                resObject.setMsg(msg);
                return resObject;
            }
            updateLoginUserData(); // 更新session中的loginUser数据
            resObject.setObj(1);
            resObject.setSuccess(true);
            return resObject;
        }
    }

    /**
     * <p>
     * 用户档案页面跳转
     * 
     * @author yuhang.weng
     * @DateTime 2016年6月8日 上午11:53:28
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "showUserRecord", method = RequestMethod.GET)
    public String showUserRecord(Model model) {
        int userId = getLoginUser().getId(); // 获取用户对象ID
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        Map<String, Object> user = new HashMap<>();
        user.put("height", recordDTO.getHeight());
        user.put("weight", recordDTO.getWeight());
        user.put("waist", recordDTO.getWaist());
        user.put("bust", recordDTO.getBust());
        user.put("hip", recordDTO.getHip());
        model.addAttribute("user", user);
        return "com/lifeshs/member/userRecord";
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月8日 下午1:55:38
     * @serverComment 用户档案信息修改
     *
     * @param
     * @return
     */
    @RequestMapping(params = "updateUserRecord", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateUserRecord(@RequestParam Double height_s, @RequestParam Double weight_s,
            @RequestParam Double waist_s, @RequestParam Double bust_s, @RequestParam Double hip_s) {
        // AjaxJson对象构造
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        int userId = getLoginUser().getId(); // 获取用户对象ID
        memberService.updateUserRecord(userId, height_s.floatValue(), weight_s.floatValue(), waist_s.floatValue(), bust_s.floatValue(), hip_s.floatValue());
        // 修改成功
        resObject.setSuccess(true);
        resObject.setMsg("个人档案修改成功");

        updateLoginUserData(); // 更新session中的loginUser数据
        return resObject;
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月15日 下午1:39:22
     * @serverComment 修改密码页面跳转
     *
     * @return
     */
    @RequestMapping(params = "showUserPwd", method = RequestMethod.GET)
    public String showUpdatePwd() {
        return "com/lifeshs/member/userPwd";
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月15日 上午11:55:13
     * @serverComment 修改密码
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "updateUserPwd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateUserPwd(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改密码失败";

        int userId = getLoginUser().getId(); // 获取当前用户对象ID

        String pwd_old = request.getParameter("password_old");
        String pwd_new = request.getParameter("password_new");
        pwd_old = StringUtil.decodeStr(pwd_old);
        pwd_new = StringUtil.decodeStr(pwd_new);
        // 判断密码是否正确
        if (!memberService.checkPwd(userId, pwd_old)) {
            resObject.setMsg(msg + "，原密码错误");
            return resObject;
        }
        // 修改密码
        if (StringUtils.isEmpty(memberService.modifyPasswordByUserId(String.valueOf(userId), pwd_new, ip))) {
            resObject.setMsg(msg);
            return resObject;
        }
        resObject.setMsg("修改密码成功");
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月15日 下午3:46:32
     * @serverComment 预警与联系人页面跳转
     *
     * @return
     */
    @RequestMapping(params = "showWarn", method = RequestMethod.GET)
    public String showWarn(Model model) throws Exception {
        LoginUser loginUser = getLoginUser();
        List<TUserContacts> contacts = contactsService.findAllContacts(loginUser.getId());

        List<ContactDto> contactDtos = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            ContactDto contactDto = new ContactDto();
            // List<Object> terminalTypeList = new ArrayList<>();
            contactDto.setUserContacts(contacts.get(i));

            // for (ContactTerminalType terminalType :
            // ContactTerminalType.values()) {
            // if ((terminalType.value() | contacts.get(i).getTerminalType()) ==
            // contacts.get(i).getTerminalType()) {
            // terminalTypeList.add(terminalType.getName());
            // }
            // }
            // if (terminalTypeList.size() == 0) {
            // contactDto.setTerminalType(null);
            // } else {
            // contactDto.setTerminalType(terminalTypeList);
            // }
            contactDtos.add(contactDto);
        }
        model.addAttribute("contacts", contactDtos);
        return "com/lifeshs/member/warnAndCont";
    }

    /**
     * <p>
     * 进入号码设置
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日下午5:16:58
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "getContactDetail", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getContactDetail(@RequestParam int id) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        TUserContacts contactDeltail = contactsService.getContactsById(id);
        if (contactDeltail != null) {
            Map<String, Object> contactMap = new HashMap<String, Object>();
            contactMap.put("name", contactDeltail.getName());
            contactMap.put("contactNumber", contactDeltail.getContactNumber());
            contactMap.put("id", contactDeltail.getId());
            resObject.setSuccess(true);
            resObject.setAttributes(contactMap);
        }
        return resObject;
    }

    /**
     * <p>
     * 更新联系人
     * 
     * @author dachang.luo
     * @DateTime 2016年6月17日下午5:16:58
     *
     * @param userContact
     * @return
     */
    @RequestMapping(params = "updateContact", method = RequestMethod.POST)
    public @ResponseBody AjaxJson updateContact(TUserContacts userContact) throws OperationException {
        LoginUser user = getLoginUser();
        userContact.setUserId(user.getId());
        userContact.setContactType(0);
        contactsService.modifyContacts(userContact);
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * <p>
     * 添加联系人或者预警号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月24日 上午10:51:31
     *
     * @param name
     *            姓名
     * @param contactNumber
     *            联系人号码
     * @param isSOS
     *            是否为预警号码
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "addContact", method = RequestMethod.POST)
    public @ResponseBody AjaxJson addContact(@RequestParam String name, @RequestParam String contactNumber,
            @RequestParam String isSOS) throws Exception {

        AjaxJson resObject = new AjaxJson();
        if (!Toolkits.verifyPhone(contactNumber)) {
            resObject.setMsg("手机号码格式不正确");
            return resObject;
        }
        // 获取当前用户
        int userId = getLoginUser().getId();
        /* 默认不设置类型 */
        isSOS = "-1";
        /* 默认不设置终端类型 */
        int terminalType = 0;
        int contactId = contactsService.addContact(userId, name, contactNumber, terminalType);
        resObject.setSuccess(true);
        resObject.setObj(contactId);
        return resObject;
    }

    /**
     * 意见反馈页面
     *
     * @return
     */
    @RequestMapping(params = "suggestionPage")
    public ModelAndView suggestionPage() {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/suggesttion");
        PaginationDTO dto=memberService.getUserSuggestionRecords(getLoginUser().getId());
        modelAndView.addObject("totalPage", dto.getTotalPage());
        modelAndView.addObject("data" ,dto.getObj());
        
        return modelAndView;
    }

    /**
     * <p>
     * 删除预警号码或者联系人
     * 
     * @author dachang.luo
     * @DateTime 2016年6月24日 上午11:26:31
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "deleteContact", method = RequestMethod.POST)
    public @ResponseBody AjaxJson deleteContact(@RequestParam int id) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除预警号码或联系人失败";
        boolean bool = false;
        try {
            bool = contactsService.deleteContacts(id);
        } catch (Exception e) {
            resObject.setMsg(msg + ":" + e.getMessage());
            logger.error(msg + ":" + e.getMessage());
            return resObject;
        }
        if (!bool) {
            resObject.setMsg(msg);
            return resObject;
        }
        resObject.setSuccess(true);
        resObject.setMsg("删除预警号码或联系人成功");
        logger.info("删除预警号码或联系人成功");
        return resObject;

    }

    /**
     * 获取用户信息(名称)
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月5日 下午1:38:49
     *
     * @return
     */
    @RequestMapping(params = "user", method = RequestMethod.GET)
    public @ResponseBody AjaxJson user() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();

        MemberSharingData data = getCacheMemberSharingData(user.getId());

        resObject.setSuccess(true);
        String name = data.getRealName();
        String head = data.getPhotoPath();

        Map<String, Object> obj = new HashMap<>();
        obj.put("userName", name);
        obj.put("userHead", head);
        int age = 0;
        Date birthday = data.getBirthday();
        if (birthday != null) {
            age = DateTimeUtilT.calculateAge(birthday);
        }
        obj.put("age", age);
        obj.put("mobileVerified", data.getMobileVerified());
        obj.put("emailVerified", data.getEmailVerified());
        obj.put("news", 8); //测试 TODO
        Date lastLogin = null;
        LoginLogDTO logDTO = logService.getLastLoginLog(user.getId());
        if (logDTO == null) {
            lastLogin = new Date();
        } else {
            lastLogin = logDTO.getLoginTime();
        }
        obj.put("lastLogin", lastLogin);
        resObject.setMsg("获取用户信息成功");
        resObject.setObj(obj);

        return resObject;
    }
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月3日下午5:18:55
     * @serverComment 获取短信记录列表
     * @param 
     */
    @RequestMapping(params = "interSmsRecord")
    public ModelAndView interSmsRecord() {
        int PAGE_SIZE = 8;
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/smsRecord");
        PaginationDTO paginationDTO = new PaginationDTO();
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        paginationDTO = memberService.getSmsRecordList(userId, 1, PAGE_SIZE);
        modelAndView.addObject("pageCount", paginationDTO.getTotalPage());
        modelAndView.addObject("data", JSONObject.toJSON(paginationDTO.getData()));
        return modelAndView;
    }
    
    @RequestMapping(params = "getSmsRecordList", method = RequestMethod.POST)
    public @ResponseBody AjaxJson getSmsRecordList(@RequestParam(value = "nowPage") int nowPage) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        int PAGE_SIZE = 8;
//        String msg = "获取短信记录失败";
        PaginationDTO paginationDTO = new PaginationDTO();
        LoginUser loginUser = getLoginUser();
        int userId = loginUser.getId();
        paginationDTO = memberService.getSmsRecordList(userId, nowPage, PAGE_SIZE);
        Map<String, Object> map = new HashMap<>();
        map.put("totalPage", paginationDTO.getTotalPage());
        map.put("totalSize", paginationDTO.getTotalSize());
        resObject.setObj(paginationDTO.getData());
        resObject.setAttributes(map);
        resObject.setSuccess(true);
        return resObject;
    }

    /**
     * 我的订单页面
     *
     * @return
     */
    @RequestMapping(params = "myOrders")
    public ModelAndView userOrdersPage(@RequestParam(required = false, defaultValue = "1") int cur,
                                       @RequestParam(required = false) Integer status, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/myOrders");
        LoginUser user = getLoginUser();
        List<Integer> list = null;
        if (status != null) {
            list = new ArrayList<Integer>();
            list.add(status);
        }
        PaginationDTO to = orderService.getOrderListWithPageSplit(list, null, user.getId(), cur, 10);
        modelAndView.addObject("data", to);
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    /**
     * 控制订单：删除订单、取消订单等操作
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "delOrder")
    public @ResponseBody AjaxJson delOrder(Integer id) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        orderService.delOrder(user.getId(), id);
        return ajaxJson;
    }

    /**
     * 订单详情页面
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "orderDetailPage")
    public ModelAndView orderDetailPage(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/service/orderDetails_2");
        LoginUser user = getLoginUser();
        Map<String, Object> orderDetail = orderService.getOrder(user.getId(), id);
        modelAndView.addObject("data", orderDetail);
        return modelAndView;
    }

    /**
     * 支付订单页面
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "orderPayPage")
    public ModelAndView orderPayPage(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/service/orderDetails_3");
        LoginUser user = getLoginUser();
        Map<String, Object> orderDetail = orderService.getOrder(user.getId(), id);
        modelAndView.addObject("data", orderDetail);
        return modelAndView;
    }

    /**
     * 支付成功回调页面&&完成订单页面
     *
     * @return
     */
    @RequestMapping(params = "finishOrde")
    public ModelAndView finishOrde(@RequestParam Integer order) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/service/orderDetails_4");
        LoginUser user = getLoginUser();
        Map<String, Object> map = orderService.getOrder(user.getId(), order);
        Date endDate = (Date) map.get("endDate");
        if (endDate != null) {
            int remainDays = DateTimeUtilT.calculateDayInterval(new Date(), endDate);
            map.put("remain", remainDays);
        }else{
            map.put("remain", "无限期");
        }
        modelAndView.addObject("data", map);
        return modelAndView;
    }

    /**
     * 改变一个订单（包括方式、次数等）
     *
     * @param params
     * @return
     */
    @RequestMapping(params = "changeOrderData")
    public @ResponseBody AjaxJson changeOrderData(@RequestBody Map<String, Object> params) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if (params.containsKey("action")) {
            switch ((String) params.get("action")) {
                case "count":// 改变订购数量
                    orderService.updateOrderNumber((Integer) params.get("orderId"), user.getId(),(Integer) params.get("count"));// 目前只有改变次数
                    break;
                case "pay":// 0元的订单下单，直接改为已支付
                    Integer charMode = (Integer) params.get("charMode");
                    boolean b = orderService.modifyOrderStatusToValid(user.getId(), (String) params.get("orderNumer"),(String) params.get("cash"), charMode == 0 ? true : false);
                    ajaxJson.setSuccess(b);
                    break;
            }
        }
        return ajaxJson;
    }

    /**
     * 添加一个订单
     *
     * @param params
     * @return
     */
    @RequestMapping(params = "addOrder")
    public @ResponseBody AjaxJson createOrder(@RequestBody Map<String, Object> params) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("");
        LoginUser user = getLoginUser();
        int userId = user.getId();
        int orgServeId = (Integer) params.get("orgServeId");
        String orderNumber = (String) params.get("orderNumber");
        int priceType = (Integer) params.get("chargeMode");
        int count = (Integer) params.get("count");
        String subject = (String) params.get("title");
        String body = (String) params.get("body");
        Integer groupId = (Integer) params.get("groupId");
        ServiceMessage result = orderService.addServeOrder(userId, orgServeId, orderNumber, priceType, count, subject, body, 1, groupId);
        Boolean success = result.isSuccess();
        ajaxJson.setSuccess(success);
        ajaxJson.setMsg(result.getMessage());
        if (success) {
            ajaxJson.setObj(result.getAttributes().get("id"));
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String  home() {
        return"com/lifeshs/member/userPage";
    }
}
