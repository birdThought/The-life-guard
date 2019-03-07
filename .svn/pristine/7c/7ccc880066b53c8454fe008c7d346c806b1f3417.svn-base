package com.lifeshs.app.api.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Report;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.ValidCode;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.entity.log.TLogLogin;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.po.push.OrgUserDeviceTokenPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.data.MeasureSite;
import com.lifeshs.pojo.data.MeasureSiteOpeningTime;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.service1.data.MeasureSiteService;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.utils.HtmlUtils;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PasswordUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.data.area.CodeVO;

import jodd.util.StringUtil;

/**
 * 管理app-个人设置
 *
 * @author yuhang.weng
 * @DateTime 2016年11月18日 下午2:38:12
 */
@Controller
@RequestMapping("mapp/mine")
public class MAppMineController extends AppBaseController {

    private final static Logger logger = Logger.getLogger(MAppMineController.class);

	private static final String String = null;

    @Autowired
    private IManagerOrgService managerOrgService;

    @Autowired
    private IDataAreaService areaService;

    @Autowired
    private ValidCodeServiceImpl validCodeUtil;

    @Resource(name = "pushDataService")
    private PushDataService pushDataService;
    
    @Autowired
    private MeasureSiteService measureSiteService;

    /**
     * 登录
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月21日 下午7:56:00
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject login(@RequestParam String json, HttpServletRequest request) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        String type = appJSON.getType();
        JSONObject data = appJSON.getData();

        String userName = data.getString(OrgUser.USERNAME);
        String password = data.getString(OrgUser.PASSWORD);
        String deviceToken = data.getString(Normal.DEVICE_TOKEN);
        String systemVersion = data.getString(Normal.CURRENT_VERSION);

        TOrgUser user = commonTrans.findUniqueByProperty(TOrgUser.class, "userName", userName);

        // user exist
        if (user == null) {
            return MAppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        // id
        int userId = user.getId();
        // userCode
        String userCode = user.getUserCode();

        // password
        password = MD5Utils.encryptPassword(password);
        boolean isPasswordCorrect = StringUtils.equals(password, user.getPassword());
        if (!isPasswordCorrect) {
            // Start 临时使用，替换旧平台密码完成后清除 dengfeng
            String enpwdOld = PasswordUtil.encrypt("", password, PasswordUtil.getStaticSalt());
            if (enpwdOld.equals(user.getPassword())) {
                orgUserService.modifyPasswordByUserId(userId, password, "127.0.0.1");
            } else {
                return MAppNormalService.success(NormalMessage.PASSWORD_INCORRECT);
            }
        }

        // userStatus
        Integer userStatus = user.getStatus();

        // status
        boolean isValidStatus = userStatus.intValue() == 0;
        if (!isValidStatus) {
            return MAppNormalService.success(NormalMessage.DISABLED_ACCOUNT);
        }

        // token
        String token = user.getToken();

        boolean isTokenNull = StringUtils.isBlank(token);
        String tokenKey = user.getUserCode();
        if (isTokenNull) {
            token = tokenService.createToken(tokenKey, OrgUser.SALT);
            user.setToken(token);
            // update user
            commonTrans.updateEntitie(user);
        } else {
            boolean isTokenOverTime = tokenService.isTokenOverTime(tokenKey, OrgUser.SALT, token);
            if (isTokenOverTime) {
                token = tokenService.createToken(tokenKey, OrgUser.SALT);
                // update user
                user.setToken(token);
                commonTrans.updateEntitie(user);
            }
        }

        // 封装展示数据
        TOrg org = commonTrans.getEntity(TOrg.class, user.getOrgId());

        // mobile email
        boolean mobileVerified = false;
        Boolean mv = user.getMobileVerified();
        if (mv != null && mv.booleanValue()) {
            mobileVerified = true;
        }
        boolean emailVerified = false;
        Boolean ev = user.getEmailVerified();
        if (ev != null && ev.booleanValue()) {
            emailVerified = true;
        }

        // sex address
        boolean sex = BaseDefine.SEX;
        if (user.getSex() != null) {
            sex = user.getSex();
        }
        String pc = org.getProvince();
        String cc = org.getCity();
        String dc = org.getDistrict();
        if (StringUtils.isNotBlank(pc) && StringUtils.isNotBlank(cc) && StringUtils.isNotBlank(dc)) {
            AreaVO area = areaService.getAreaNameByCode(pc, cc, dc);
            pc = area.getProvinceName();
            cc = area.getCityName();
            dc = area.getDistrictName();
        } else {
            pc = "";
            cc = "";
            dc = "";
        }
        String street = org.getStreet();
        if (StringUtils.isBlank(street)) {
            street = "";
        }
        Map<String, String> address = new HashMap<String, String>();
        address.put(Area.PROVINCE, pc);
        address.put(Area.CITY, cc);
        address.put(Area.DISTRICT, dc);
        address.put(Area.STREET, street);

        int orgStatus = -1;
        int orgVerified = org.getOrgVerified();
        String verifiedCause = org.getVerifiedCause();

        if (orgVerified == 2) {
            orgStatus = 2;
        }
        if (orgVerified == 1) {
            orgStatus = 1;
        }
        if (orgVerified == 0) {
            /*boolean isBusinessLicenseBlank = StringUtils.isBlank(org.getBusinessLicense());
            if (!isBusinessLicenseBlank) {
                orgStatus = 0;
            } else {
                orgStatus = -1;
            }*/
            orgStatus = 0;
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgUser.ID, userId + "");
        returnData.put(OrgUser.TOKEN, token);
        returnData.put(OrgUser.CODE, userCode);
        returnData.put(HuanXin.PASSWORD, "123456"); // 默认密码

        returnData.put(OrgUser.NAME, user.getRealName());
        returnData.put(OrgUser.PHOTO, user.getPhoto());
        returnData.put(Org.NAME, org.getOrgName());
        returnData.put(Org.LOGO, org.getLogo());
        returnData.put(OrgUser.MOBILE, user.getMobile());
        returnData.put(OrgUser.MOBILE_VERIFIED, mobileVerified);
        returnData.put(OrgUser.EMAIL, user.getEmail());
        returnData.put(OrgUser.EMAIL_VERIFIED, emailVerified);

        returnData.put(OrgUser.SEX, sex);
        returnData.put(OrgUser.TEL, user.getTel());
        returnData.put(Area.ADDRESS, address);

        returnData.put(Org.ORG_TYPE, org.getOrgType() + "");
        returnData.put(Org.ID, org.getId() + "");
        returnData.put(OrgUser.TYPE, user.getUserType() + "");

        returnData.put(Org.STATUS, orgStatus + "");
        returnData.put(Org.VERIFIED_CAUSE, verifiedCause);
        returnData.put(Org.TYPE, org.getType() + "");

        /*
         * 由于服务器使用nginx，真实IP地址封装在请求头部的"X-Real-IP"中,本地获取的ip为null属于正常现象
         */
        String ip = request.getHeader("X-Real-IP");
        int orgId = user.getOrgId();

        Integer OS = null;
        switch (type) {
            case Normal.ANDROID_TYPE:
                type = "android";
                OS = 1;
                break;
            case Normal.IOS_TYPE:
                type = "ios";
                OS = 2;
                break;
            default:
                type = "";
                break;
        }

        try {
            addALoginLog(user.getId(), user.getUserName(), type, ip, orgId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(deviceToken)) {
            OrgUserDeviceTokenPO tokenPO = new OrgUserDeviceTokenPO();
            tokenPO.setUserId(userId);
            tokenPO.setDeviceToken(deviceToken);
            tokenPO.setOS(OS);
            tokenPO.setSystemVersion(systemVersion);

            try {
                pushDataService.addOrgUserPushToken(tokenPO);
            } catch (BaseException e) {
                logger.error(e);
            }
        }

        return MAppNormalService.success(returnData);
    }

    /**
     * 添加一个登录记录
     *
     * @param userId
     * @param userName
     * @param terminalType
     * @param ip
     * @param orgId
     * @author yuhang.weng
     * @DateTime 2017年2月13日 下午4:41:33
     */
    private void addALoginLog(int userId, String userName, String terminalType, String ip, int orgId) {
        TLogLogin login = new TLogLogin();
        login.setIp(ip);
        login.setLoginTime(new Date());
        login.setTerminalType(terminalType);
        login.setUserId(userId);
        login.setUserName(userName);
        login.setUserType(2);
        login.setOrgId(orgId);

        commonTrans.saveLogin(login);
    }

    /**
     * 注册
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月21日 下午7:56:07
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject register(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        String orgName = data.getString(Org.NAME);
        int type = data.getIntValue(Org.TYPE);
        String orgType = data.getString(Org.ORG_TYPE);
        String userName = data.getString(OrgUser.USERNAME);
        String password = data.getString(OrgUser.PASSWORD);

        // validate
        boolean isVerifyUserName = Toolkits.isVerifyUserName(userName);
        if (!isVerifyUserName) {
            return MAppNormalService.error(Error.USERNAME_UNVERIFY);
        }

        boolean isVerifyPassword = Toolkits.isVerifyPassword(password);
        if (!isVerifyPassword) {
            return MAppNormalService.error(Error.PASSWORD_UNVERIFY);
        }

        boolean isVerifyOrgName = Toolkits.isVerifyOrgName(orgName);
        if (!isVerifyOrgName) {
            return MAppNormalService.error(Error.ORGNAME_UNVERIFY);
        }

        boolean isOrgNameExist = managerOrgService.orgIsExist(orgName);
        if (isOrgNameExist) {
            return MAppNormalService.success(NormalMessage.ORG_EXIST);
        }

        boolean isUserNameExist = orgUserService.userIsExist(userName);
        if (isUserNameExist) {
            return MAppNormalService.success(NormalMessage.USER_EXIST);
        }

        int orgId = managerOrgService.registOrg(orgName, userName, password, type, orgType);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Org.ID, orgId + "");

        return MAppNormalService.success(returnData);
    }

    /**
     * 补充机构信息
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年11月22日 下午3:01:17
     */
    @RequestMapping(value = "updateOrg", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject updateOrg(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        Integer orgId = data.getInteger(Org.ID);
        Integer isImprove = data.getInteger(Normal.IS_IMPROVE);
        if (isImprove == null) {
            isImprove = 1;
        }
        if (isImprove == 0) {
            orgId = appJSON.getAopData().getOrgUser().getOrgId();
        }
        String contacts = data.getString(Org.CONTACTS);
        String contactInformation = data.getString(Org.CONTACTS_INFORMATION);
        String businessLicense = data.getString(Org.BUSINESS_LICENSE);

        String province = data.getString(Area.PROVINCE);
        String city = data.getString(Area.CITY);
        String district = data.getString(Area.DISTRICT);
        String street = data.getString(Area.STREET);

        // 编辑机构信息
        String about = data.getString(Org.ABOUT);
        String detail = data.getString(Org.DETAIL);
        String logo = data.getString(Org.LOGO);

        boolean verifiedCOntactInformation = Toolkits.verifyTel(contactInformation);
        if (!verifiedCOntactInformation) {
            verifiedCOntactInformation = Toolkits.verifyPhone(contactInformation);
        }
        if (!verifiedCOntactInformation) {
            return MAppNormalService.error(Error.CONTACT_INFORMATION_UNVERIFIY);
        }

        if (orgId == null) {
            return MAppNormalService.error(Error.PARAMETER_MISSING);
        }

        TOrg org = commonTrans.getEntity(TOrg.class, orgId);

        // setting the address
        CodeVO codeVO = mappNormalService.setAddress(district, city, province);
        org.setDistrict(codeVO.getCounty());
        org.setCity(codeVO.getCity());
        org.setProvince(codeVO.getProvince());
        org.setStreet(street);

        org.setContacts(contacts);
        org.setContactInformation(contactInformation);
        org.setModifyDate(new Date());

        if (isImprove.intValue() == 1) { // 如果是补充信息，允许修改营业执照，并将企业状态修改为待审核状态
            boolean isBusinessLicenseBlank = StringUtils.isBlank(businessLicense);
            if (!isBusinessLicenseBlank) {
                ImageDTO imageVO = MAppNormalService.uploadPhoto(businessLicense, org.getBusinessLicense(), "license", false);
                if (imageVO.getUploadSuccess()) {
                    // setting the license
                    org.setBusinessLicense(imageVO.getNetPath());
                }
            }

            org.setOrgVerified(0);
        }
        if (isImprove.intValue() == 0) {
            if (logo != null) {
                ImageDTO imageVO = MAppNormalService.uploadPhoto(logo, null, "logo", false);

                if (imageVO.getUploadSuccess()) {
                    org.setLogo(imageVO.getNetPath());
                }
            }
            if (StringUtils.isNotBlank(about) && about.length() <= 300) {
                org.setAbout(about);
            }
            if (StringUtils.isNotBlank(detail) && detail.length() <= 300) {
                org.setDetail(detail);
            }
        }

        int effectRow = commonTrans.updateEntitie(org);
        if (effectRow > 0) {
            return MAppNormalService.success();
        } else {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
    }

    /**
     * 个人信息简介
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午3:37:54
     */
    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject profile(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        TOrgUser user = appJSON.getAopData().getOrgUser();
        // collect the informatin to show
        TOrg org = commonTrans.getEntity(TOrg.class, user.getOrgId());
        // name photo orgName mobile email
        String realName = user.getRealName();
        if (StringUtils.isBlank(realName)) {
            realName = "";
        }
        String mobile = user.getMobile();
        if (StringUtils.isBlank(mobile)) {
            mobile = "";
        }
        String mobileVerified = "0";
        Boolean mv = user.getMobileVerified();
        if (mv != null && mv.booleanValue()) {
            mobileVerified = "1";
        }
        String email = user.getEmail();
        if (StringUtils.isBlank(email)) {
            email = "";
        }
        String emailVerified = "0";
        Boolean ev = user.getEmailVerified();
        if (ev != null && ev.booleanValue()) {
            emailVerified = "1";
        }

        // sex tel address
        int sex = 1;
        Boolean s = user.getSex();
        if (s != null) {
            sex = s.booleanValue() ? 1 : 0;
        }
        String tel = user.getTel();
        if (StringUtils.isBlank(tel)) {
            tel = "";
        }

        String street = org.getStreet();
        if (StringUtils.isBlank(street)) {
            street = "";
        }

        String pc = org.getProvince();
        String cc = org.getCity();
        String dc = org.getDistrict();
        if (StringUtils.isNotBlank(pc) && StringUtils.isNotBlank(cc) && StringUtils.isNotBlank(dc)) {
            AreaVO area = areaService.getAreaNameByCode(pc, cc, dc);
            pc = area.getProvinceName();
            cc = area.getCityName();
            dc = area.getDistrictName();
        } else {
            pc = "";
            cc = "";
            dc = "";
        }
        Map<String, String> address = new HashMap<>();
        address.put(Area.PROVINCE, pc);
        address.put(Area.CITY, cc);
        address.put(Area.DISTRICT, dc);
        address.put(Area.STREET, street);

        Map<String, Object> returnData = new HashMap<>();

        returnData.put(OrgUser.NAME, realName);
        returnData.put(OrgUser.PHOTO, user.getPhoto());
        returnData.put(Org.NAME, org.getOrgName());
        returnData.put(OrgUser.MOBILE, mobile);
        returnData.put(OrgUser.MOBILE_VERIFIED, mobileVerified);
        returnData.put(OrgUser.EMAIL, email);
        returnData.put(OrgUser.EMAIL_VERIFIED, emailVerified);

        returnData.put(OrgUser.SEX, sex + "");
        returnData.put(OrgUser.TEL, tel);
        returnData.put(Area.ADDRESS, address);

        returnData.put(Org.ORG_TYPE, org.getOrgType());

        return MAppNormalService.success(returnData);

    }

    @RequestMapping(value = "modifyProfile", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject modifyProfile(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser user = appJSON.getAopData().getOrgUser();

        String photo = data.getString(OrgUser.PHOTO);
        String realName = data.getString(OrgUser.NAME);
        String sex = data.getString(OrgUser.SEX);
        String tel = data.getString(OrgUser.TEL);

        Map<String, String> returnData = new HashMap<>();

        boolean updateUser = false;

        // 工作电话
        if (StringUtils.isNotBlank(tel)) {

            boolean verifiedTel = Toolkits.verifyTel(tel);
            if (!verifiedTel) {
                verifiedTel = Toolkits.verifyPhone(tel);
            }
            if (!verifiedTel) {
                return MAppNormalService.error(Error.TEL_UNVERIFIY);
            }

            user.setTel(tel);
            updateUser = true;
        }
        // 名字
        if (StringUtils.isNotBlank(realName)) {
            user.setRealName(realName);
            updateUser = true;
        }
        // 性别
        if (StringUtils.isNotBlank(sex)) {
            boolean s = sex.equals("0") ? false : true;
            user.setSex(s);
            updateUser = true;
        }
        // 照片
        if (StringUtils.isNotBlank(photo)) {

            ImageDTO imageVO = MAppNormalService.uploadPhoto(photo, user.getPhoto(), "head", false);
            if (imageVO.getUploadSuccess()) {
                user.setPhoto(imageVO.getNetPath());
                updateUser = true;
            }
        }

        if (updateUser) {
            user.setModifyDate(new Date());
            commonTrans.updateEntitie(user);
        }

        returnData.put(OrgUser.PHOTO, photo);
        return MAppNormalService.success(returnData);
    }

    /**
     * 发送验证码
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午1:41:19
     * @updateBy wuj 2017-08-28 11:01:29
     * @updateReason
     */
    @RequestMapping(value = "sendVerifyCode", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject sendVerifyCode(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        String mobile = data.getString(User.MOBILE);
        String type = data.getString(ValidCode.TYPE);

        String userId = "";
        String cacheKey = "";
        int sendId = data.getIntValue(User.ID);
        CacheType cacheType = null;

        if (StringUtil.isBlank(type)) {
            return MAppNormalService.error(Error.VALID_CODE_CACHE_TYPE_NULL);
        }
        switch (type) {
            case ValidCode.SET_PASSWORD:
                cacheType = CacheType.USER_RESET_CACHE; // 重置密码 验证码
                if (StringUtils.isBlank(mobile)) {
                    return MAppNormalService.error(Error.VALID_CODE_MOBILE_MISSING);
                }
                // 查找手机号是否登记在系统中
                userId = orgUserService.checkMobile(mobile);
                // 企业用户
                if (StringUtils.isBlank(userId)) {
                    return MAppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
                }
                cacheKey = userId;
                break;
            case ValidCode.SET_MOBILE_EMAIL:
                cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY; // 修改手机 验证码
                if (StringUtils.isBlank(mobile)) {
                    return MAppNormalService.error(Error.VALID_CODE_MOBILE_MISSING);
                }
                // 查找手机号是否登记在系统中
                userId = orgUserService.checkMobile(mobile);
                // 企业用户
                if (StringUtils.isBlank(userId)) {
                    return MAppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
                }

                cacheKey = mobile;
                break;
            case ValidCode.SET_NEW_MOBILE_EAMIL:
                /**
                 * 设置新的手机，需要判断该手机是否已被占用 如果被占用了，直接返回错误信息，终止发送验证码请求
                 */
                cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY; // 修改手机 验证码
            /*userId = memberService.checkMobile(mobile);
            if (StringUtils.isNotBlank(userId)) {
                return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
            }*/
                userId = orgUserService.checkMobile(mobile);
                if (StringUtils.isNotBlank(userId)) {
                    return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
                }
                cacheKey = mobile;
                break;
            case ValidCode.REGISTER:
                /**
                 * 注册机构
                 */
                cacheType = CacheType.USER_REGISTERY_CACHE;
                // 查找手机号是否登记在系统中
                userId = orgUserService.checkMobile(mobile);
                if (StringUtils.isNotBlank(userId)) {
                    return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
                }
                cacheKey = mobile;
        }
        if (StringUtils.isBlank(cacheKey) || cacheType == null) {
            return MAppNormalService.error(Error.VALID_CODE_ILLEGAL_ACTION);
        }

        VcodeTerminalType vCodeTerminalType = null;
        switch (appJSON.getType()) {
            case Normal.ANDROID_TYPE:
                vCodeTerminalType = VcodeTerminalType.ANDROID;
                break;
            case Normal.IOS_TYPE:
                vCodeTerminalType = VcodeTerminalType.IOS;
                break;
            default:
                vCodeTerminalType = null;
                break;
        }
        try {
            // 发送验证码
            validCodeUtil.sendToMobile(sendId, mobile, cacheKey, cacheType, vCodeTerminalType, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // return AppNormalService.returnError(AppError.VALID_CODE_UNKNOW);
        }
        // 发送成功

        return MAppNormalService.success();
    }

    /**
     * 核对验证码
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午1:41:32
     */
    @RequestMapping(value = "checkVerifyCode", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject checkVerifyCode(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        String mobile = data.getString(User.MOBILE);
        String type = data.getString(ValidCode.TYPE);
        String verifyCode = data.getString(ValidCode.CODE);

        String cacheKey = "";
        CacheType cacheType = null;
        if (type.equals(ValidCode.REGISTER)) {
            cacheType = CacheType.USER_REGISTERY_CACHE;
            cacheKey = mobile;
        }else if (type.equals(ValidCode.SET_PASSWORD)) {
            cacheType = CacheType.USER_RESET_CACHE;
            cacheKey = orgUserService.checkMobile(mobile);
            if (StringUtils.isBlank(cacheKey)) {
                return MAppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
            }
        }else if (type.equals(ValidCode.SET_MOBILE_EMAIL) || type.equals(ValidCode.SET_NEW_MOBILE_EAMIL)) {
            cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY;
            cacheKey = mobile;
        }
        // 验证不通过
        if (!validCodeUtil.valid(cacheKey, verifyCode, cacheType)) {
            return MAppNormalService.success(NormalMessage.CODE_UNRECOGNIZED);
        }
        // 验证通过
        return MAppNormalService.success();
    }

    /**
     * 重置密码
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午1:41:41
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject resetPassword(@RequestParam String json, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        String mobile = data.getString(User.MOBILE);// 手机号
        String password = data.getString(User.PASSWORD);// 密码       //todo 采取正则判断
        String verifyCode = data.getString(ValidCode.CODE);// 验证码

        String userId = orgUserService.checkMobile(mobile);
        if (StringUtil.isBlank(userId)) {
            return MAppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        boolean bool = validCodeUtil.valid(userId, verifyCode, CacheType.USER_RESET_CACHE);
        if (bool) {
            orgUserService.modifyPasswordByUserId(Integer.valueOf(userId), password, ip);
        } else {
            return MAppNormalService.success(NormalMessage.CODE_UNRECOGNIZED);
        }
        return MAppNormalService.success();
    }

    /**
     * 修改手机号
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午1:41:48
     * @updateBy wuj 2017-08-28 10:40:20
     * @updateReason 需求修改, 用户修改手机号现在无需旧手机号码, 只需要新手机号的验证码即可
     */
    @RequestMapping(value = "modifyMobile", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject modifyMobile(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();
        Integer userId = appJSON.getUser(); // 用户ID

        String newMobile = data.getString(ValidCode.NEW_MOBILE);// 新手机号码
        String newVerifyCode = data.getString(ValidCode.NEW_MOBILE_CODE);// 新手机验证码

        Object oM = data.get(ValidCode.OLD_MOBILE); // 旧手机号码  废弃(2017-08-28 10:44:36)
        Object oV = data.get(ValidCode.OLD_MOBILE_CODE); // 旧手机号验证码 废弃(2017-08-28 10:44:39)

        /*if (!validCodeUtil.valid(newMobile, newVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
            return MAppNormalService.success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_NEW);
        }

        // 修改手机号
        boolean isOk = orgUserService.updateOrgUserMobile(userId, newMobile);
        if (isOk) {
            return MAppNormalService.success();
        } else {
            return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
        }*/

        if (oM != null && oV != null && StringUtils.isNotBlank((String) oM) && StringUtils.isNotBlank((String) oV)) {
            // 包含有原手机号码，以及原手机号码验证码的话，说明本次操作为修改绑定手机
            String oldMobile = (String) oM;
            String oldVerifyCode = (String) oV;

            if (!validCodeUtil.valid(oldMobile, oldVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return MAppNormalService.success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_OLD);
            }
            if (!validCodeUtil.valid(newMobile, newVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return MAppNormalService.success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_NEW);
            }

            // 修改手机号
            boolean isOk = orgUserService.updateOrgUserMobile(userId, newMobile);
            if (isOk) {
                return MAppNormalService.success();
            } else {
                return MAppNormalService.error(Error.FAIL_ACTION);
            }
        } else {
            if (!validCodeUtil.valid(newMobile, newVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return MAppNormalService.success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_NEW);
            }

            // 修改手机号
            boolean isOk = orgUserService.updateOrgUserMobile(userId, newMobile);
            if (isOk) {
                return MAppNormalService.success();
            } else {
                return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
            }
        }
    }

    /**
     * 修改密码
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午1:42:18
     */
    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject modifyPassword(@RequestParam String json, HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        Integer userId = appJSON.getUser();
        JSONObject data = appJSON.getData();

        TOrgUser user = appJSON.getAopData().getOrgUser();

        String oldPassword = data.getString(ValidCode.PASSWORD_OLD);
        String newPassword = data.getString(ValidCode.PASSWORD_NEW);

        boolean isNewPasswordValid = Toolkits.isVerifyPassword(newPassword);
        if (!isNewPasswordValid) {
            return MAppNormalService.error(Error.PASSWORD_UNVERIFY);
        }

        if (StringUtil.isBlank(oldPassword)) {
            return MAppNormalService.error(Error.MODIFY_PASSWORD_OLD_MISSING);
        }
        if (StringUtil.isBlank(newPassword)) {
            return MAppNormalService.error(Error.MODIFY_PASSWORD_NEW_MISSING);
        }

        oldPassword = MD5Utils.encryptPassword(oldPassword);

        if (!user.getPassword().equals(oldPassword)) {
            return MAppNormalService.success(NormalMessage.MODIFY_PASSWORD_UNRECOGNIZED);
        }
        String userName = orgUserService.modifyPasswordByUserId(userId, newPassword, ip);
        if (StringUtils.isNotBlank(userName)) {
            return MAppNormalService.success();
        } else {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }
    }

    /**
     * 意见反馈
     *
     * @param json
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月7日 下午7:23:34
     */
    @RequestMapping(value = "sendReport", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject sendReport(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        String message = data.getString(Report.MESSAGE);
        String contactInformation = data.getString(Report.CONTACT_INFORMATION);

        TReport report = new TReport();
        report.setMessage(message);
        report.setContactInformation(contactInformation);
        report.setCreateDate(new Date());
        report.setIsRead(false);
        report.setUserId(appJSON.getUser());
        report.setUserType(1);

        commonTrans.save(report);

        return MAppNormalService.success();
    }

    @RequestMapping(value = "orgProfile", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject orgProfile(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
        int orgId = orgUser.getOrgId();

        TOrg org = commonTrans.getEntity(TOrg.class, orgId);

        String about = org.getAbout();
        if (StringUtils.isNotBlank(about)) {
            about = HtmlUtils.getTextFromHtml(about);
        }

        String detail = org.getDetail();
        if (StringUtils.isNotBlank(detail)) {
            detail = HtmlUtils.getTextFromHtml(detail);
        }

        String p = org.getProvince();
        String c = org.getCity();
        String d = org.getDistrict();

        AreaVO areaVO = areaService.getAreaNameByCode(p, c, d);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Org.LOGO, org.getLogo());
        returnData.put(Org.NAME, org.getOrgName());
        returnData.put(Org.ORG_TYPE, org.getOrgType());
        returnData.put(Org.BUSINESS_LICENSE, org.getBusinessLicense());
        returnData.put(Org.CONTACTS, org.getContacts());
        returnData.put(Org.CONTACTS_INFORMATION, org.getContactInformation());
        returnData.put(Area.PROVINCE, areaVO.getProvinceName());
        returnData.put(Area.CITY, areaVO.getCityName());
        returnData.put(Area.DISTRICT, areaVO.getDistrictName());
        returnData.put(Area.STREET, org.getStreet());
        returnData.put(Org.ABOUT, about);
        returnData.put(Org.DETAIL, detail);
        
        MeasureSite measureSite = measureSiteService.selectSizeStatusById(orgId);  
        
        if(measureSite !=null){
	        returnData.put("SiteId", measureSite.getId());
	        returnData.put("SiteBanner", measureSite.getBanner());
	       
	    	MeasureSiteOpeningTime time = 	measureSiteService.selectSizeTimeById(measureSite.getId()); 
    		if(time != null){
    			returnData.put("SiteStartTime", time.getStartTime());
    	    	returnData.put("SiteEndTime", time.getEndTime()); 
    		}	    	 
	    	if(measureSite.getStatusMap() == null){
	     	   measureSite.setStatusMap(0+"");
	         }
    	
	    	returnData.put("statusMap", measureSite.getStatusMap());
        }
        
        
       
       
                      
        return MAppNormalService.success(returnData);
    }
    
    
    @RequestMapping(value = "addOrgProfile",method = RequestMethod.POST)
    public @ResponseBody JSONObject addOrgData(@RequestParam String json) throws Exception{    	    	
	    	
    		MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
	        JSONObject data = appJSON.getData();	        
	        Integer orgId = Integer.parseInt((String) data.get("orgId"));	  	        	      
	        
	        ImageDTO image = null;	      
            String productImgPath = null;
            String phont = data.getString("banner");  
            
            if(phont == null){
            	productImgPath = null;
            }else{
            	if(!phont.contains("lifekeepers_files")){
               	 String st = phont.replace("%2B",  "+");
                    if (StringUtils.isNotBlank(st)) {
                        image = MAppNormalService.uploadPhoto(st, null, "measureSite", false);
                        if (image.getUploadSuccess()) {
                            productImgPath = image.getNetPath();
                        }
                    }
               }else{
               	productImgPath = phont;
               }
            }
                                   
            Integer siteId = null;
            String id = data.getString("SiteId");
	       	if(id != null && id != ""){
	       		siteId = Integer.parseInt(id);
	       	}
           
            
	        String startTime = (String)data.get("startTime");	      
	        String endTime =(String)data.get("endTime");
	        String statusMap = (String) data.get("statusMap") ;
				           	   	
	     managerOrgService.addDataSize(siteId,orgId,productImgPath,startTime,endTime,statusMap);	       	
	     return AppNormalService.success();
    }
        
}
