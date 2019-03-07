package com.lifeshs.service.terminal.app.user.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Area;
import com.lifeshs.common.constants.app.Contact;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Oauth;
import com.lifeshs.common.constants.app.Option;
import com.lifeshs.common.constants.app.Record;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.common.constants.app.ValidCode;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.app.hobby.Hobby;
import com.lifeshs.common.constants.app.hobby.UserHobby;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.ContactTerminalType;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.po.user.UserHobbyPO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.data.AddressDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserOauthDTO;
import com.lifeshs.pojo.member.UserPcLoginDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;
import com.lifeshs.pojo.paper.PaperOptionDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.paper.IPaperService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.user.IAppUserService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PasswordUtil;
import com.lifeshs.utils.RandCodeUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.image.ImageUtilV2;

import jodd.util.StringUtil;

/**
 * 应用app个人设置实现类
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月6日 下午8:37:28
 */
@Service(value = "appUserService")
public class AppUserServiceImpl extends AppNormalService implements IAppUserService {

    private static final Logger logger = Logger.getLogger(AppUserServiceImpl.class);
    
    @Autowired
    private IOrgUserService orgUserService;

    @Autowired
    private ValidCodeServiceImpl validCodeUtil;

    @Autowired
    private IContactsService contactsService;

    @Autowired
    private IPaperService paperService;

    @Autowired
    private ICacheService cacheService;
    
    @Override
    public JSONObject login(String json, String ip) throws Exception {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        String ver = appJSON.getVer();
        String type = appJSON.getType();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        String password = mm_0.getString(User.PASSWORD);
        
        String deviceToken = mm_0.getString(Normal.DEVICE_TOKEN);
        String currentVersion = mm_0.getString(Normal.CURRENT_VERSION);

        if (StringUtils.isBlank(password) || StringUtils.isBlank(userName)) {
            return AppNormalService.error(Error.LOGIN_PARAMETER_MISSING);
        }

        // 查找用户
        UserDTO user = memberService.getUser(userName);
        // 用户不存在
        if (user == null) {
            return AppNormalService.success(NormalMessage.NO_SUCH_ACCOUNT);
        }
        int userId = user.getId();

        // Start 临时使用，替换旧平台密码完成后清除 dengfeng
        String enpwdNew = MD5Utils.encryptPassword(password);
        if (!enpwdNew.equals(user.getPassword())) {
            String enpwdOld = PasswordUtil.encrypt("", password, PasswordUtil.getStaticSalt());
            if (enpwdOld.equals(user.getPassword())) {
                memberService.modifyPasswordByUserId(userId + "", password, "127.0.0.1");
            } else {
                return AppNormalService.success(NormalMessage.PASSWORD_INCORRECT);
            }
        }

        // 用户状态码不正常
        if (UserStatus.normal.value() != user.getStatus().intValue()) {
            return AppNormalService.success(NormalMessage.DISABLED_ACCOUNT);
        }

//        Map<String, Object> params = new HashMap<>();
//        params.put("sex", user.getRecordDTO().getGender());
//        List<Map<String, Object>> describtions = terminal.getCommonTrans().findByMap(TDataHealthDescribe.class, params);
//
//        for (Map<String, Object> describtion : describtions) {
//            for (HealthType healthType : HealthType.values()) {
//                if (StringUtils.equals(healthType.getName(), (String) describtion.get("param"))) {
//                    describtion.put(HealthDescription.PARAM, healthType.name());
//                }
//            }
//
//            describtion.remove("id");
//            describtion.remove("sex");
//        }

        Map<String, Object> returnData = loginAction(user, type, ip, ver, currentVersion, deviceToken);
        // 健康范围
        Map<String, Object> healthArea = getSystemCalculateHealthArea(user, 2);
        // 发布新版本前时候可以注释掉下面这段代码
//        healthArea.put(HealthDescription.HEALTH_DESCRIPTION, describtions);
        returnData.put("healthArea", healthArea);
        
        return AppNormalService.success(returnData);
    }


    @Override
    public JSONObject register(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        String password = mm_0.getString(User.PASSWORD);

        // 判断用户名密码是否合法
        boolean isValidUserName = Toolkits.isVerifyUserName(userName);
        boolean isValidPassword = Toolkits.isVerifyPassword(password);
        if (!isValidUserName) {
            return error(Error.USERNAME_UNVERIFY);
        }
        if (!isValidPassword) {
            return error(Error.PASSWORD_UNVERIFY);
        }

        int registerMemberId = memberService.registMember(userName, password);
        if (registerMemberId == -1) {
            return success(NormalMessage.USER_EXIST);
        }
        
        String rToken = RandCodeUtil.randNumberCodeByCustom("5", 12);
        // 保存token
        cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + registerMemberId, rToken);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(User.ID, registerMemberId);
        returnData.put(Normal.TOKEN, rToken);
        return success(returnData);
    }

    @Override
    public JSONObject registerMobile(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String userName = mm_0.getString(User.USERNAME);
        String password = mm_0.getString(User.PASSWORD);
        
        // 注册时需要引荐人ID parentId
        String parentId = mm_0.getString(User.PARENTID);
        
        String verifyCode = mm_0.getString("verifyCode");

        //验证手机号
        boolean verifyMobile = Toolkits.verifyPhone(userName);
        if (!verifyMobile) {
            return error(Error.MOBILE_UNVERIFIY);
        }

        // 验证验证码
        if (!validCodeUtil.valid(userName, verifyCode, CacheType.USER_REGISTERY_CACHE)) {
            return error(NormalMessage.CODE_UNRECOGNIZED);
        }

        // 判断用户名密码是否合法
        boolean isValidUserName = Toolkits.isVerifyUserName(userName);
        boolean isValidPassword = Toolkits.isVerifyPassword(password);
        if (!isValidUserName) {
            return error(Error.USERNAME_UNVERIFY);
        }
        if (!isValidPassword) {
            return error(Error.PASSWORD_UNVERIFY);
        }
        
       // 判断是否有引荐人
        if(StringUtils.isNotBlank(parentId)) {
            // 查找引荐人ID否登记在系统中
            String userNo = parentId.toUpperCase();
        	//获取字符串的第一个字符
        	Object fir = userNo.subSequence(0, 1);//获取字符串的第一个字符
        	if(fir.equals("A") || fir.equals("Y") || fir.equals("D")) {
        		AdminUserDTO AdminUserDTO= memberService.getAdminUserUserNo(userNo);
	        	 if(AdminUserDTO ==null) {
	             	return error(Error.MOBILE_ParentId);
	             }
        	}else if(fir.equals("O")) {
        	    OrgUserDTO orgUserDTO= memberService.getOrgUserNo(userNo);
	        	 if(orgUserDTO ==null) {
	             	return error(Error.MOBILE_ParentId);
	             }
        	}else {
        		return error(Error.MOBILE_ParentId);
        	}
        }else {
        	//设置默认推荐人ID
        	parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setPassword(password);
        userDTO.setMobile(userName);
        // 注册时需要引荐人ID parentId
        userDTO.setParentId(parentId);
        userDTO.setMobileVerified(true);
        int registerMemberId = memberService.registMember(userDTO);
        if (registerMemberId == -1) {
            return error(NormalMessage.USER_EXIST);
        }

        String rToken = RandCodeUtil.randNumberCodeByCustom("5", 12);
        // 保存token
        cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + registerMemberId, rToken);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(User.ID, registerMemberId);
        returnData.put(Normal.TOKEN, rToken);
        return success(returnData);
    }

    @Override
    public JSONObject sendVerifyCode(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String mobile = mm_0.getString(User.MOBILE);
        String type = mm_0.getString(ValidCode.TYPE);

        boolean verifyMobile = Toolkits.verifyPhone(mobile);
        if (!verifyMobile) {
            return error(Error.MOBILE_UNVERIFIY);
        }
        
        String userId = "";
        String cacheKey = "";
        Integer sendId = appJSON.getData().getUserId();
        CacheType cacheType = null;

        if (StringUtil.isBlank(type)) {
            return error(Error.VALID_CODE_CACHE_TYPE_NULL);
        }
        switch (type) {
        case ValidCode.REGISTER:
            cacheType = CacheType.USER_REGISTERY_CACHE; // 注册 验证码
            cacheKey = mobile;
            userId = memberService.checkMobile(mobile);
            if (StringUtil.isNotBlank(userId)) {
                return success(NormalMessage.MOBILE_OCCUPIED);
            }
            break;
        case ValidCode.SET_PASSWORD:
            cacheType = CacheType.USER_RESET_CACHE; // 重置密码 验证码
            if (StringUtils.isBlank(mobile)) {
                return error(Error.VALID_CODE_MOBILE_MISSING);
            }
            // 查找手机号是否登记在系统中
            userId = memberService.checkMobile(mobile);
            // 普通用户
            if (StringUtils.isBlank(userId)) {
                return success(NormalMessage.NO_SUCH_ACCOUNT);
            }
            cacheKey = userId;
            break;
        case ValidCode.SET_MOBILE_EMAIL:
            cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY; // 修改手机 验证码
            if (StringUtils.isBlank(mobile)) {
                return error(Error.VALID_CODE_MOBILE_MISSING);
            }
            // 查找手机号是否登记在系统中
            userId = memberService.checkMobile(mobile);
            // 普通用户
            if (StringUtils.isEmpty(userId)) {
                return success(NormalMessage.NO_SUCH_ACCOUNT);
            }
            cacheKey = mobile;
            break;
        case ValidCode.SET_NEW_MOBILE_EAMIL:
            /**
             * 设置新的手机，需要判断该手机是否已被占用 如果被占用了，直接返回错误信息，终止发送验证码请求
             */
            cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY; // 修改手机 验证码
            userId = memberService.checkMobile(mobile);
            if (StringUtils.isNotBlank(userId)) {
                return success(NormalMessage.MOBILE_OCCUPIED);
            }
            userId = orgUserService.checkMobile(mobile);
            if (StringUtils.isNotBlank(userId)) {
                return success(NormalMessage.MOBILE_OCCUPIED);
            }
            cacheKey = mobile;
            break;
        case ValidCode.LOGIN:
            cacheType = CacheType.OAUTH_CACHE;  // 共用认证缓存，保存手机登录验证码
            /**
             * 查找手机号是否已经注册，如果不存在，就注册一个新的账号，账号名为
            */
            userId = memberService.checkMobile(mobile);
            if (StringUtil.isBlank(userId)) {
                UserDTO user = new UserDTO();
                String userName = "m_" + RandCodeUtil.randNumberCodeByCustom("1", 8);
                String password = RandCodeUtil.randNumberCodeByCustom("1", 10);
                user.setUserName(userName);
                user.setPassword(password);
                user.setMobile(mobile);
                user.setMobileVerified(true);
                memberService.registMember(user);
            }
            cacheKey = "mobile" + mobile;
            break;
        }
        if (StringUtils.isBlank(cacheKey) || cacheType == null) {
            return error(Error.VALID_CODE_ILLEGAL_ACTION);
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
        } catch (SMSException e) {
            return error(e.getMessage());
        }
        /*// TODO 测试关闭验证码是否发送校验
        if (StringUtils.isEmpty(code)) {
            // 发送失败
            return error(Error.VALID_CODE_SEND_FAILED);
        }*/
        // 发送成功
        return success();
    }

    @Override
    public JSONObject checkVerifyCode(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String mobile = mm_0.getString(User.MOBILE);
        String type = mm_0.getString(ValidCode.TYPE);
        String verifyCode = mm_0.getString(ValidCode.CODE);

        String cacheKey = "";

        CacheType cacheType = null;
        if (type.equals(ValidCode.REGISTER)) {
            cacheType = CacheType.USER_REGISTERY_CACHE;
            cacheKey = mobile;
        }
        if (type.equals(ValidCode.SET_PASSWORD)) {
            cacheType = CacheType.USER_RESET_CACHE;
            cacheKey = memberService.checkMobile(mobile);
            if (StringUtils.isBlank(cacheKey)) {
                return success(NormalMessage.NO_SUCH_ACCOUNT);
            }
        }
        if (type.equals(ValidCode.SET_MOBILE_EMAIL) || type.equals(ValidCode.SET_NEW_MOBILE_EAMIL)) {
            cacheType = CacheType.APP_MOBILE_EMAIL_MODIFY;
            cacheKey = mobile;
        }
        /*if (type.equals(ValidCode.LOGIN)) {
            cacheType = CacheType.OAUTH_CACHE;
            cacheKey = "mobile" + mobile;
        }*/

        // 验证不通过
        if (!validCodeUtil.valid(cacheKey, verifyCode, cacheType)) {
            return success(NormalMessage.CODE_UNRECOGNIZED);
        }
        // 验证通过
        return success();
    }

    @Override
    public JSONObject setPasswod(String json, String ip) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String mobile = mm_0.getString(User.MOBILE);// 手机号
        String password = mm_0.getString(User.PASSWORD);// 密码
        String verifyCode = mm_0.getString(ValidCode.CODE);// 验证码

        String userId = memberService.checkMobile(mobile);
        if (StringUtil.isBlank(userId)) {
            return success(NormalMessage.NO_SUCH_ACCOUNT);
        }

        // 密码格式验证
        boolean isValidPassword = Toolkits.isVerifyPassword(password);
        if (!isValidPassword) {
            return error(Error.PASSWORD_UNVERIFY);
        }

        boolean bool = validCodeUtil.valid(userId, verifyCode, CacheType.USER_RESET_CACHE);
        if (bool) {
            // 修改密码
            memberService.modifyPasswordByUserId(userId, password, ip);
            // 绑定手机号码
            memberService.updateMobile(Integer.valueOf(userId), mobile);
        } else {
            return success(NormalMessage.CODE_UNRECOGNIZED);
        }
        return success();
    }

    @Override
    public JSONObject modifyMobile(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        int userId = appJSON.getData().getUserId();// 用户id

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String newMobile = mm_0.getString(ValidCode.NEW_MOBILE);// 新手机号码
        String newVerifyCode = mm_0.getString(ValidCode.NEW_MOBILE_CODE);// 新手机验证码

        Object oM = mm_0.get(ValidCode.OLD_MOBILE);
        Object oV = mm_0.get(ValidCode.OLD_MOBILE_CODE);

        if (oM != null && oV != null && StringUtils.isNotBlank((String) oM) && StringUtils.isNotBlank((String) oV)) {
            // 包含有原手机号码，以及原手机号码验证码的话，说明本次操作为修改绑定手机
            String oldMobile = (String) oM;
            String oldVerifyCode = (String) oV;

            if (!validCodeUtil.valid(oldMobile, oldVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_OLD);
            }
            if (!validCodeUtil.valid(newMobile, newVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_NEW);
            }

            // 修改手机号
            boolean isOk = memberService.updateMobile(userId, newMobile);
            if (isOk) {
                return success();
            } else {
                return error(Error.FAIL_ACTION);
            }
        } else {
            if (!validCodeUtil.valid(newMobile, newVerifyCode, CacheType.APP_MOBILE_EMAIL_MODIFY)) {
                return success(NormalMessage.MODIFY_MOBILE_UNRECOGNIZED_NEW);
            }

            // 修改手机号
            boolean isOk = memberService.updateMobile(userId, newMobile);
            if (isOk) {
                return success();
            } else {
                return success(NormalMessage.MOBILE_OCCUPIED);
            }
        }
    }
    
    @Override
    public JSONObject userLoginType(String json) {
    	AppJSON appJSON = parseAppJSON(json);
    	int userId = appJSON.getData().getUserId();
    	Map<String, Object> oauthUser = memberService.getUserByUserId(userId);
    	Map<String, Object> retData = new HashMap<>();
    	if(oauthUser != null) {
    		retData.put("oauthLogin", 1); // 是否第三方认证登录
    		retData.put("firstModify", MD5Utils.encryptPassword(Normal.OAUTH_LOGIN_FIX_PASSWORD)
    				.equals(oauthUser.get("password"))?1:0); // 是否第一次修改密码
    		retData.put("oauthType", oauthUser.get("oauthType")); // 认证方式                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
    	} else {
    		retData.put("oauthLogin", 0);
    		retData.put("firstModify", 0);
    		retData.put("oauthType", "帐号密码认证");
    	}
    	return success(retData);
    }

    @Override
    public JSONObject modifyPassword(String json, String ip) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String oldPassword = mm_0.getString(ValidCode.PASSWORD_OLD);
        String newPassword = mm_0.getString(ValidCode.PASSWORD_NEW);

        boolean isNewPasswordValid = Toolkits.isVerifyPassword(newPassword);
        if (!isNewPasswordValid) {
            return error(Error.PASSWORD_UNVERIFY);
        }

        UserDTO user = appJSON.getAopData().getUser();
        if(MD5Utils.encryptPassword(Normal.OAUTH_LOGIN_FIX_PASSWORD).equals(user.getPassword())) { // 第三方认证登录用户,并且是第一次修改密码
        	// 不做旧密码验证
        } else {
        	
        	if (StringUtil.isBlank(oldPassword)) {
        		return error(Error.MODIFY_PASSWORD_OLD_MISSING);
        	}
        	oldPassword = MD5Utils.encryptPassword(oldPassword);
        	
        	if (!user.getPassword().equals(oldPassword)) {
        		return success(NormalMessage.MODIFY_PASSWORD_UNRECOGNIZED);
        	}
        }

        if (StringUtil.isBlank(newPassword)) {
            return error(Error.MODIFY_PASSWORD_NEW_MISSING);
        }

        String userName = memberService.modifyPasswordByUserId(String.valueOf(userId), newPassword, ip);
        if (StringUtils.isNotBlank(userName)) {
            return success();
        }
        return error(Error.FAIL_ACTION);
    }

    @Override
    public JSONObject getUserInfo(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        UserDTO user = appJSON.getAopData().getUser();
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(User.MOBILE, user.getMobile());
        userMap.put(User.MOBILE_VERIFIY, user.getMobileVerified());
        userMap.put(User.PHOTO, user.getPhoto());
        userMap.put(User.REALNAME, user.getRealName());
        userMap.put(Record.HEIGHT, user.getRecordDTO().getHeight());
        userMap.put(Record.WEIGHT, user.getRecordDTO().getWeight());
        userMap.put(Record.WAIST, user.getRecordDTO().getWaist());
        userMap.put(Record.BUST, user.getRecordDTO().getBust());
        userMap.put(Record.HIP, user.getRecordDTO().getHip());
        /*
         * 性别与生日默认值为空，如果是空
         */
        boolean sexs = BaseDefine.SEX;
        if (user.getRecordDTO().getGender() != null) {
            sexs = user.getRecordDTO().getGender();
        }
        userMap.put(User.SEX, sexs);

        String birthday = "";
        if (user.getRecordDTO().getBirthday() != null) {
            birthday = DateTimeUtilT.date(user.getRecordDTO().getBirthday());
        }
        userMap.put(User.BIRTHDAY, birthday);

        Map<String, Object> address = new HashMap<String, Object>();
        address.put(Area.PROVINCE, user.getProvince());
        address.put(Area.CITY, user.getCity());
        address.put(Area.COUNTY, user.getCounty());
        address.put(Area.STREET, user.getStreet());
        userMap.put(Area.ADDRESS, address);

        List<UserHobbyPO> userHobbyList = hobbyService.listUserHobby(user.getId());
        List<Map<String, Object>> hobbyList = new ArrayList<>();
        for (UserHobbyPO h : userHobbyList) {
            Map<String, Object> hobby = new HashMap<>();
            hobby.put(Hobby.ID, h.getHobbyId());
            hobby.put(Hobby.NAME, h.getHobbyName());
            hobbyList.add(hobby);
        }
        userMap.put(UserHobby.HOBBY, hobbyList);
        
        UserRecordDTO recordDTO = user.getRecordDTO();
        String corporeityResult = recordDTO.getCorporeityResult();
        Integer strokeRiskScore = recordDTO.getStrokeRiskScore();
        Integer subHealthSymptomScore = recordDTO.getSubHealthSymptomScore();
        
        userMap.put(Record.CORPOREITY_RESULT, corporeityResult);
        userMap.put(Record.STROKE_RISK_SCORE, strokeRiskScore);
        userMap.put(Record.SUB_HEALTH_SYMPTOM_SCORE, subHealthSymptomScore);
        
        Map<String, String> extraMap = new HashMap<>();
        extraMap.put("method", "getUserInfo");

        return success(userMap, extraMap);
    }

    @Override
    public JSONObject modifyUserBaseInfo(String json) throws Exception {
        Map<String, Object> healthArea = modifyUserBaseInfo(json, 1);
        if (healthArea == null) {
            return error(Error.FAIL_ACTION);
        }
        return success(healthArea);
    }

    @Override
    public JSONObject modifyUserBaseInfo2(String json) {
        Map<String, Object> healthArea = modifyUserBaseInfo(json, 2);
        if (healthArea == null) {
            return error(Error.FAIL_ACTION);
        }
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("healthArea", healthArea);
        return success(returnData, true);
    }

    /**
     * 修改用户基本信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午5:30:35
     *
     * @param json
     * @param ver
     * @return
     */
    private Map<String, Object> modifyUserBaseInfo(String json, int ver) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();// 用户id
        UserDTO user = appJSON.getAopData().getUser();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        JSONObject address = mm_0.getJSONObject(Area.ADDRESS);
        String photo = mm_0.getString(User.PHOTO);
        String name = mm_0.getString(User.REALNAME);
        String sex = mm_0.getString(User.SEX);
        String birthday = mm_0.getString(User.BIRTHDAY);

        AddressDTO addressDTO = new AddressDTO();
        if (address != null) {
            String province = address.getString(Area.PROVINCE);
            String city = address.getString(Area.CITY);
            String county = address.getString(Area.COUNTY);
            String street = address.getString(Area.STREET);
            addressDTO.setProvince(province);
            addressDTO.setCity(city);
            addressDTO.setCountry(county);
            addressDTO.setStreet(street);
        }

        // // 修改用户个人信息
        Boolean gender = null;
        if (Normal.TRUE.equals(sex)) {
            gender = true;
        }
        if (Normal.FALSE.equals(sex)) {
            gender = false;
        }
        user.getRecordDTO().setGender(gender);

        String netPath = null;
        if (photo != null && StringUtil.isNotBlank(photo)) {
            String old_photo = user.getPhoto();
            ImageDTO imageVO = uploadPhoto(photo, old_photo, "head", false);
            if (imageVO.getUploadSuccess()) {
                netPath = imageVO.getNetPath();
            } else {
                return null;
            }
        }

        Date userBirthday = null;
        if (StringUtils.isNotBlank(birthday)) {
            userBirthday = DateTimeUtilT.date(birthday);
            user.getRecordDTO().setBirthday(userBirthday);
        }

        memberService.updateUserBaseInfo(userId, userBirthday, gender, name, netPath, addressDTO);
        return getSystemCalculateHealthArea(user, ver);
    }

    @Override
    public JSONObject modifyUserBodyInfo(String json) {
        Map<String, Object> healthArea = modifyUserBodyInfo(json, 1);
        if (healthArea == null) {
            return success(NormalMessage.NO_DATA);
        }
        return success(healthArea);
    }

    @Override
    public JSONObject modifyUserBodyInfo2(String json) {
        Map<String, Object> healthArea = modifyUserBodyInfo(json, 2);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put("healthArea", healthArea);
        return success(returnData, true);
    }

    private Map<String, Object> modifyUserBodyInfo(String json, int ver) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();// 用户id
        UserDTO user = appJSON.getAopData().getUser();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        Float height = mm_0.getFloat(Record.HEIGHT);
        Float weight = mm_0.getFloat(Record.WEIGHT);
        Float waist = mm_0.getFloat(Record.WAIST);
        Float bust = mm_0.getFloat(Record.BUST);
        Float hip = mm_0.getFloat(Record.HIP);

        // 保存用户的身体信息
        memberService.updateUserRecord(userId, height, weight, waist, bust, hip);

        if (height != null) {
            user.getRecordDTO().setHeight(height);
        }
        if (weight != null) {
            user.getRecordDTO().setWeight(weight);
        }
        if (waist != null) {
            user.getRecordDTO().setWaist(waist);
        }
        if (bust != null) {
            user.getRecordDTO().setBust(bust);
        }
        if (hip != null) {
            user.getRecordDTO().setHip(hip);
        }
        return getSystemCalculateHealthArea(user, ver);
    }

    @Override
    public JSONObject getUserContacts(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();// 用户id
        UserDTO user = appJSON.getAopData().getUser();
        Integer userHealthWarning = user.getHealthWarning() == null ? 0 : user.getHealthWarning();

        int hf = ContactTerminalType.HEALTH_PACKAGE_FAMILY.value();
        int cf = ContactTerminalType.C3_FAMILY.value();
        int cs = ContactTerminalType.C3_SOS.value();

        // 获取用户所有联系人
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        List<TUserContacts> contactList = contactsService.findFamilyAndSos(userId);
        for (TUserContacts contact : contactList) {
            int contactType = contact.getContactType();
            
            // 2017年9月7日11:52:34 取消C3
            if ((contactType & 2) == 2) {
                contactType -= 2;
            }
            if ((contactType & 4) == 4) {
                contactType -= 4;
            }
            
            int deviceType = 0;
            boolean isSOS = false;
            boolean isFam = false;
            int healthWarning = 0;
            if ((contactType & hf) == hf) {
                deviceType |= 1;
                isFam = true;
            }
            if ((contactType & cf) == cf) {
                deviceType |= 2;
                isFam = true;
            }
            if ((contactType & cs) == cs) {
                deviceType |= 2;
                isSOS = true;
            }
            
            Boolean receiveSMS = contact.getReceiveSMS();
            int rs = 0;
            if (receiveSMS != null && receiveSMS) {
                rs = 1;
                healthWarning = userHealthWarning;
            }
            
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Contact.ID, contact.getId());
            returnData.put(Contact.NAME, contact.getName());
            returnData.put(Contact.NUMBER, contact.getContactNumber());
            returnData.put(Contact.IS_SOS, isSOS);
            returnData.put(Contact.IS_FAMILY, isFam);
            returnData.put(Contact.DEVICE_TYPE, deviceType);
            returnData.put(Contact.TERMINAL_TYPE, contactType);
            returnData.put(User.HEALTH_WARNING, healthWarning);
            returnData.put(Contact.RECEIVE_SMS, rs);
            returnDataList.add(returnData);
        }
        return success(returnDataList);
    }

    @Override
    public JSONObject addUserContacts(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();// 用户id
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String name = mm_0.getString(Contact.NAME);
        String contactNumber = mm_0.getString(Contact.NUMBER);
        int tt = mm_0.getInteger(Contact.TERMINAL_TYPE);
        Integer healthWarning = mm_0.getInteger(User.HEALTH_WARNING);
        
        Integer nwContactId = contactsService.addContact(userId, name, contactNumber, tt);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Contact.ID, nwContactId);

        // 如果是健康包，需要把这个号码修改为接受短信号码，并且更新用户的预警项
        int hfV = ContactTerminalType.HEALTH_PACKAGE_FAMILY.value();
        if ((tt & hfV) == hfV) {
            contactsService.modifyContactReceiveSMS(nwContactId, userId);
            memberService.updateUserHealthWarning(userId, healthWarning);
        }
        return success(returnData);
    }

    @Override
    public JSONObject modifyUserContacts(String json) throws Exception {
        AppJSON appJSON = parseAppJSON(json);

        int userId = appJSON.getData().getUserId();

        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(Contact.ID);
        String name = mm_0.getString(Contact.NAME);
        String contactNumber = mm_0.getString(Contact.NUMBER);
        int tt = mm_0.getIntValue(Contact.TERMINAL_TYPE);

        Integer healthWarning = mm_0.getInteger(User.HEALTH_WARNING);

        TUserContacts userContacts = new TUserContacts();
        userContacts.setId(id);
        userContacts.setName(name);
        userContacts.setContactNumber(contactNumber);
        userContacts.setUserId(userId);
        userContacts.setContactType(tt);

        contactsService.modifyContacts(userContacts);
        memberService.updateUserHealthWarning(userId, healthWarning);
        return success();
    }

    @Override
    public JSONObject delUserContacts(String json) throws Exception {

        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int id = mm_0.getIntValue(Contact.ID);// 联系人id

        int csV = ContactTerminalType.C3_SOS.value();

        TUserContacts contacts = contactsService.getContactsById(id);
        if ((contacts.getContactType() & csV) == csV) {
            return success("C3的SOS号码不能删除");
        }

        boolean bool = contactsService.deleteContacts(id);
        if (bool) {
            return success();
        }
        return error(Error.FAIL_ACTION);
    }

    @Override
    public JSONObject getHealthWarningItem(String json) {
        List<Map<String, String>> returnDatas = new ArrayList<>();

        Map<String, String> returnData1 = new HashMap<>();
        returnData1.put("param", "心率");
        returnData1.put("healthWarning", HealthType.heartRate.value() + "");
        returnDatas.add(returnData1);

        Map<String, String> returnData2 = new HashMap<>();
        returnData2.put("param", "血压");
        returnData2.put("healthWarning", (HealthType.systolic.value() + HealthType.diastolic.value()) + "");
        returnDatas.add(returnData2);

        Map<String, String> returnData3 = new HashMap<>();
        returnData3.put("param", "血糖");
        returnData3.put("healthWarning", HealthType.bloodSugar.value() + "");
        returnDatas.add(returnData3);

        Map<String, String> returnData4 = new HashMap<>();
        returnData4.put("param", "血氧");
        returnData4.put("healthWarning", HealthType.saturation.value() + "");
        returnDatas.add(returnData4);

        Map<String, String> returnData5 = new HashMap<>();
        returnData5.put("param", "体温");
        returnData5.put("healthWarning", HealthType.temperature.value() + "");
        returnDatas.add(returnData5);

        Map<String, String> returnData6 = new HashMap<>();
        returnData6.put("param", "心电");
        returnData6.put("healthWarning", HealthType.ECG.value() + "");
        returnDatas.add(returnData6);

        return success(returnDatas);
    }

    @Override
    public JSONObject getDietQuestionnaire(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        Integer optionId = recordDTO.getDietQuestionnaireOptionId();
        List<Map<String, Object>> options = listQuestionnaireOption("饮食", userId, optionId);
        return success(options);
    }

    @Override
    public JSONObject getSportQuestionnaire(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        Integer optionId = recordDTO.getSportQuestionnaireOptionId();
        List<Map<String, Object>> options = listQuestionnaireOption("运动", userId, optionId);
        return success(options);
    }

    @Override
    public JSONObject getMentalHealthQuestionnaire(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        Integer optionId = recordDTO.getMentalHealthQuestionnaireOptionId();
        List<Map<String, Object>> options = listQuestionnaireOption("心理健康", userId, optionId);
        return success(options);
    }

    /**
     * 获取问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 上午11:53:31
     *
     * @param typeName
     * @param userId
     * @param optionId
     * @return
     */
    private List<Map<String, Object>> listQuestionnaireOption(String typeName, int userId, Integer optionId) {
        List<PaperOptionDTO> optionDTOs = paperService.listPaperOption(typeName);
        List<Map<String, Object>> options = new ArrayList<>();
        for (PaperOptionDTO paperOptionDTO : optionDTOs) {
            boolean selected = false;
            if (optionId != null && optionId.equals(paperOptionDTO.getId())) {
                selected = true;
            }

            Map<String, Object> option = new HashMap<>();
            option.put(Option.ID, paperOptionDTO.getId());
            option.put(Option.NAME, paperOptionDTO.getName());
            option.put(Option.SELECTED, selected);
            options.add(option);
        }
        return options;
    }

    @Override
    public JSONObject tickDietQuestinonnaireOption(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int optionId = appJSON.getData().getFirstJSONObject().getIntValue(Option.ID);
        memberService.tickDietQuestionnaireOption(userId, optionId);
        return success();
    }

    @Override
    public JSONObject tickSportQuestionnaireOption(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int optionId = appJSON.getData().getFirstJSONObject().getIntValue(Option.ID);
        memberService.tickSportQuestionnaireOption(userId, optionId);
        return success();
    }

    @Override
    public JSONObject tickMentalHealthQuestionnaireOption(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int optionId = appJSON.getData().getFirstJSONObject().getIntValue(Option.ID);
        memberService.tickMentalHealthQuestionnaireOption(userId, optionId);
        return success();
    }

    @Override
    public JSONObject getUserRecord(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        String age = "";
        Date birthday = recordDTO.getBirthday();
        if (birthday != null) {
            age = String.valueOf(DateTimeUtilT.calculateAge(birthday));
        }
        String dietHabit = "";
        PaperOptionDTO dietOption = recordDTO.getDietQuestionnaireOption();
        if (dietOption != null) {
            dietHabit = dietOption.getName();
        }
        String mentalHealth = "";
        PaperOptionDTO mentalHealthOption = recordDTO.getMentalHealthQuestionnaireOption();
        if (mentalHealthOption != null) {
            mentalHealth = mentalHealthOption.getName();
        }
        String sportFrequency = "";
        PaperOptionDTO sportFrequencyOption = recordDTO.getSportQuestionnaireOption();
        if (sportFrequencyOption != null) {
            sportFrequency = sportFrequencyOption.getName();
        }
        String subHealthSymptomStatus = "";
        Integer subHealthSymptomScore = recordDTO.getSubHealthSymptomScore();
        if (subHealthSymptomScore != null) {
            subHealthSymptomStatus = String.valueOf(subHealthSymptomScore);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(User.AGE, age);
        returnData.put(Record.HEIGHT, recordDTO.getHeight());
        returnData.put(Record.WEIGHT, recordDTO.getWeight());
        returnData.put(Record.WAIST, recordDTO.getWaist());
        returnData.put(Record.BUST, recordDTO.getBust());
        returnData.put(Record.HIP, recordDTO.getHip());
        returnData.put(HealthPackage.SATURATION, recordDTO.getSaturation());
        returnData.put(HealthPackage.VITALCAPACITY, recordDTO.getVitalCapacity());
        returnData.put(HealthPackage.DIASTOLIC, recordDTO.getDiastolic());
        returnData.put(HealthPackage.SYSTOLIC, recordDTO.getSystolic());
        returnData.put(HealthPackage.HEARTRATE, recordDTO.getHeartRate());
        returnData.put(HealthPackage.BASE_METABOLISM, recordDTO.getBaseMetabolism());
        returnData.put(Record.DIET_HABIT, dietHabit);
        returnData.put(Record.CORPOREITY_RESULT, recordDTO.getCorporeityResult());
        returnData.put(Record.MENTAL_HEALTH, mentalHealth);
        returnData.put(Record.SPORT_FREQUENCY, sportFrequency);
        returnData.put(Record.SLEEP_HOUR, recordDTO.getSleepHour());
        returnData.put(Record.SUB_HEALTH_STATUS, subHealthSymptomStatus);
        return success(returnData);
    }

    @Override
    public JSONObject setSleepHour(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        float sleepHour = mm_0.getFloatValue(Record.SLEEP_HOUR);
        memberService.addSleepHourRecord(userId, sleepHour);
        return success();
    }

    @Override
    public JSONObject setCorporeityResult(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String corporeityResult = mm_0.getString(Record.CORPOREITY_RESULT);
        String corporeityScore = mm_0.getString(Record.CORPOREITY_SCORE);
        memberService.setCorporeityResult(userId, corporeityResult, corporeityScore);
        return success();
    }

    @Override
    public JSONObject setSubHealthSymptom(String json) {
        AppJSON appJSON = parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int subHealthSymptomHealthPoint = mm_0.getIntValue(Record.SUB_HEALTH_SYMPTOM_HEALTH_POINT);
        int subHealthSymptomScore = mm_0.getIntValue(Record.SUB_HEALTH_SYMPTOM_SCORE);
        subHealthSymptomHealthPoint = -subHealthSymptomHealthPoint; // 变成负数减分
        memberService.setSubHealthSymptom(userId, subHealthSymptomHealthPoint, subHealthSymptomScore);
        return success();
    }

    @Override
    public JSONObject oauthLogin(String json, String ip) {
        AppJSON appJSON = parseAppJSON(json);
        String terminalType = appJSON.getType();
        String ver = appJSON.getVer();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String uId = mm_0.getString(Oauth.U_ID);
        String openId = mm_0.getString(Oauth.OPEN_ID);
        String oauthType = mm_0.getString(Oauth.OAUTH_TYPE);
        String name = mm_0.getString(Oauth.NAME);
        String photoUrl = mm_0.getString(Oauth.PHOTO_URL);
        String currentVersion = mm_0.getString(Normal.CURRENT_VERSION);
        String deviceToken = mm_0.getString(Normal.DEVICE_TOKEN);

        if (StringUtils.isBlank(uId)) {
            return error(Error.PARAMETER_MISSING, 400);
        }
        
        boolean exist = true;
        String completeProfileToken = null;
        UserDTO user = memberService.getUserByUId(uId);
        String userName = "";
        int userId = 0;
        if (user == null) {
            exist = false;
            
            String netPath = null;
            try {
                netPath = ImageUtilV2.saveUrl(photoUrl, "head", false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 用户首次使用第三方的方式登录，需要注册新的账号
            userName = oauthType + "_" + RandCodeUtil.randNumberCodeByCustom("1", 8);
//            String password = RandCodeUtil.randNumberCodeByCustom("1", 8);
            String password = Normal.OAUTH_LOGIN_FIX_PASSWORD;
            user = new UserDTO();
            user.setUserName(userName);
            user.setPassword(password);
            
            // 如果用户的名字不合法，就使用临时名称让用户自己去修改
            boolean isVerifyRealName = Toolkits.isVerifyRealName(name);
            if (!isVerifyRealName) {
                name = "用户临时名字";
            }
            user.setRealName(name);
            
            user.setPhoto(netPath);
            user.setParentId(AgentConstant.AGENT_DEFUALT_PARENT_ID_A2);
            userId = memberService.registMember(user);
            if (userId == -1) {
                return success("请重新登录");
            }
            // 添加第三方登录记录
            UserOauthDTO oauth = new UserOauthDTO();
            oauth.setOauthType(oauthType);
            oauth.setOpenId(openId);
            oauth.setUserId(userId);
            oauth.setuId(uId);
            memberService.addOauthUser(oauth);
            
            completeProfileToken = RandCodeUtil.randNumberCodeByCustom("5", 12);
            // 保存token
            cacheService.saveKeyValue(CacheType.REGISTER_TOKEN_CACHE, "m_" + userId, completeProfileToken);
        } else {
            userName = user.getUserName();
        }

        if (UserStatus.normal.value() != user.getStatus().intValue()) {
            return AppNormalService.success(NormalMessage.DISABLED_ACCOUNT);
        }

        Map<String, Object> returnData = loginAction(user, terminalType, ip, ver, currentVersion, deviceToken);
        // 健康范围
        Map<String, Object> healthArea = getSystemCalculateHealthArea(user, 2);
        returnData.put("healthArea", healthArea);
        returnData.put(Normal.LOGIN_TYPE, "oauth");
        returnData.put(User.USERNAME, userName);
        returnData.put(Normal.IS_EXIST, exist);
        
        // 如果是第一次第三方登录，就返回一个token，用于用户第一次登录完善信息
        if (completeProfileToken != null) {
            returnData.put("completeProfileToken", completeProfileToken);
        }
        
        return success(returnData);
    }

    @Override
    public JSONObject pcLogin(String json) {
        AppJSON appJSON = parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        String lgToken = mm_0.getString(User.LOGIN_TOKEN);
        String userToken = appJSON.getData().getToken();
//        System.out.println(userId + "  " + lgToken + "  " + userToken);
//        cacheService.saveKeyValue(CacheType.PC_LOGIN_CACHE, lgToken, userId);     //换成存储在数据库的方式
        UserPcLoginDTO loginDTO = new UserPcLoginDTO();
        loginDTO.setUserId(userId);
        loginDTO.setLoginToken(lgToken);
        loginDTO.setUserToken(userToken);
        boolean bool = memberService.addUserPcLogin(loginDTO);
        if (!bool) {
            return error("存储pc登录信息失败");
        }
//        loginAction(user, terminalType, ip, ver);
        return success();
    }

    @Override
    public JSONObject smsLogin(String json, String ip) {
        AppJSON appJson = parseAppJSON(json);
        String terminalType = appJson.getType();
        String ver = appJson.getVer();
        JSONObject mm_0 = appJson.getData().getFirstJSONObject();
        String mobile = mm_0.getString(Normal.MOBILE);
        String verifyCode = mm_0.getString("verifyCode");
        String currentVersion = mm_0.getString(Normal.CURRENT_VERSION);
        String deviceToken = mm_0.getString(Normal.DEVICE_TOKEN);
        
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(verifyCode)) {
            return error(Error.PARAMETER_MISSING, 400);
        }
        
        String cacheKey = "mobile" + mobile;
        // 验证不通过
        if (!validCodeUtil.valid(cacheKey, verifyCode, CacheType.OAUTH_CACHE)) {
            return success(NormalMessage.CODE_UNRECOGNIZED);
        }
        // 验证通过后消除该验证码的缓存
        validCodeUtil.destory(cacheKey, CacheType.OAUTH_CACHE);
        
        UserDTO user = memberService.getUserByMobile(mobile);
        if (UserStatus.normal.value() != user.getStatus().intValue()) {
            return AppNormalService.success(NormalMessage.DISABLED_ACCOUNT);
        }
        Map<String, Object> returnData = loginAction(user, terminalType, ip, ver, currentVersion, deviceToken);
        // 健康范围
        Map<String, Object> healthArea = getSystemCalculateHealthArea(user, 2);
        returnData.put("healthArea", healthArea);
        returnData.put(Normal.LOGIN_TYPE, "mobile");
        returnData.put(User.USERNAME, user.getUserName());
        return success(returnData);
    }

    @Override
    public JSONObject logout(String json) {
        AppJSON appJson = parseAppJSON(json);
        Integer userId = appJson.getData().getUserId();
        try {
            pushDataService.deleteUserPushToken(userId);
        } catch (OperationException e) {
            logger.error(e);
        }
        return success();
    }
}
