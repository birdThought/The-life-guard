package com.lifeshs.controller.org.accountsecurity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.profile.BaseProfileDTO;
import com.lifeshs.pojo.org.profile.ModifyMobileDTO;
import com.lifeshs.pojo.org.profile.ModifyPasswordVO;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.IValidCodeService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 *  个人资料
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月9日 下午4:51:42
 */
@RestController
@RequestMapping({"org/profile"})
public class ProfileController extends BaseController {

    private static final Logger logger = Logger.getLogger(ProfileController.class);
    
    private static final UserType ORG_USER = UserType.orgUser;

    @Autowired
    private IDataAreaService areaService;
    
    @Autowired
    private IOrgUserService orgUserService;
    
    @Autowired
    private IValidCodeService valideCodeService;
    
    @Autowired
    private ISessionManageService sessionService;

    @Autowired
    private IManagerOrgService managerOrgService;
    
    @Resource(name = "v2MessageService")
    private MessageService messageService;

    @Autowired
    private ISessionManageService loginService;

    @RequestMapping("/services")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("platform/org/profile/user-info");
        
        LoginUser loginUser = getLoginUser();
        OrgUserDTO user = orgUserService.getOrgUser(loginUser.getId());
        
        /*OrgDTO org = user.getOrg();
        String p = org.getProvince();
        String c = org.getCity();
        String d = org.getDistrict();

        List<Map<String, String>> cities = new ArrayList<>();
        List<Map<String, String>> district = new ArrayList<>();
        
        if (StringUtils.isNotBlank(p) && StringUtils.isNotBlank(c)) {
            String regex = "^" + p + "[0-9][1-9][0]{2}";
            cities = areaService.findCity(regex);
            district = areaService.findDistrict("^" + p + c + "[0-9][1-9]");
        }*/
        
        Boolean gender = user.getSex();
        Date birthday = user.getBirthday();
        
        String photo = user.getPhoto();
        /*if (StringUtils.isNotBlank(photo)) {
            photo = "/" + photo;
        }*/
        
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("mobile", user.getMobile());
        userMap.put("expertise", user.getExpertise());
        userMap.put("about", user.getAbout());
        userMap.put("photo", photo);
//        userMap.put("cCode", p+c+"00");
//        userMap.put("dCode", p+c+d);
        userMap.put("orgName", user.getOrg().getOrgName());
        userMap.put("realName", user.getRealName());
        userMap.put("gender", (gender != null && gender) ? 1 : 0);
        userMap.put("address", user.getAddress());
        userMap.put("birthday", birthday);
        userMap.put("idCard", user.getIdCard());
        
        modelAndView.addObject("user", JSON.toJSON(userMap));
//        modelAndView.addObject("city", JSON.toJSON(cities));
//        modelAndView.addObject("district", JSON.toJSON(district));
        modelAndView.addObject("orgType", loginUser.getType());
        return modelAndView;
    }

    /**
     *  修改基础信息
     *  @author yuhang.weng 
     *	@DateTime 2017年5月11日 上午9:52:19
     *
     *  @param data
     *  @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public AjaxJson modifyBaseProfile(@RequestBody BaseProfileDTO data) {
        AjaxJson result = new AjaxJson();

       /* LoginUser loginUser = getLoginUser();
        Integer userId = loginUser.getId();
        data.setUserId(userId);*/
        orgUserService.updateBaseProfile(data);
        
        result.setSuccess(true);
        return result;
    }
    
    @RequestMapping(value = "/services/mobile")
    public ModelAndView bindMobile() {
        ModelAndView modelAndView = new ModelAndView("platform/org/profile/bind-mobile");
        LoginUser user = getLoginUser();
        OrgUserSharingData orgUser = getOrgUserSharingData();
        Boolean v = orgUser.getMobileVerified();
        String mobile = "";
        if (v != null && v) {
            mobile = orgUser.getMobile();
            int length = mobile.length();
            
            mobile = mobile.substring(0, 3) + "****" + mobile.subSequence(length - 4, length);
        }
        modelAndView.addObject("mobile", mobile);

        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        return modelAndView;
    }
    
    @RequestMapping(value = "mobile", method = RequestMethod.PUT)
    public AjaxJson modifyMobile(@RequestBody ModifyMobileDTO data) {
        AjaxJson result = new AjaxJson();
        result.setSuccess(false);
        
        Integer userId = getLoginUser().getId();
        String password = data.getPassword();
        String code = data.getCode();
        CacheType cacheType = CacheType.USER_MOBILE_MODIFY;    // 修改手机邮箱 验证码
        String mobile = data.getMobile();
        
        OrgUserDTO orgUser = orgUserService.getOrgUser(userId);
        String enPass = MD5Utils.encryptStringWithUTF8(password);
        String oPass = orgUser.getPassword();
        if (!StringUtils.equals(oPass, enPass)) {
            result.setMsg("密码不正确");
            return result;
        }
        
        Boolean valid = valideCodeService.valid(mobile, code, cacheType);
        if (!valid) {
            result.setMsg("验证码不正确或已失效");
            return result;
        }
        
        boolean success = orgUserService.updateOrgUserMobile(userId, mobile);
        if (!success) {
            result.setMsg("修改失败");
            return result;
        }
        
        sessionService.updateLoginUser(userId, 1);
        valideCodeService.destory(mobile, cacheType);
        result.setSuccess(true);
        int length = mobile.length();
        mobile = mobile.substring(0, 3) + "****" + mobile.subSequence(length - 4, length);
        result.setObj(mobile);
        return result;
    }


    /**
     * @Description: 门店信息页面
     * @Author: wenxian.cai
     * @Date: 2017/6/8 16:22
     */
    @RequestMapping(value = "/store")
    public ModelAndView storeInfo() {
        ModelAndView modelAndView = new ModelAndView("platform/org/profile/store-info");
        LoginUser user = getLoginUser();
        int orgId = user.getOrgId();
        OrgProfileDTO org = managerOrgService.getOrgInfo(orgId);
        if (StringUtils.isNotBlank(org.getDetail())) {
            org.setDetail(org.getDetail().replace("\n", ""));
        }
        modelAndView.addObject("org", JSON.toJSON(org));
        modelAndView.addObject("orgType", user.getType());

        return modelAndView;
    }

    /**
     * @Description: 更新门店信息
     * @Author: wenxian.cai
     * @Date: 2017/6/9 14:29
     * todo 判断该用户是否有权限执行此动作
     */
    @RequestMapping(params = "updatestore", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateStoreInfo(@RequestBody OrgProfileDTO org) {
        AjaxJson ajaxJson = new AjaxJson();
        int orgId = getLoginUser().getOrgId();
        try {
            String newRelativePath = null;
            if (StringUtils.isNotBlank(org.getLogo())) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(org.getLogo(), "photo");
                ImageUtilV2.delImg(org.getLogo());
                org.setLogo(newRelativePath);
            }
        } catch (Exception e) {
            logger.error("移动图片失败");
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        org.setId(getLoginUser().getOrgId());
        managerOrgService.updateOrgInfo(org);
        return ajaxJson;
    }

    /**
     * @Description: 获取门店信息
     * @Author: wenxian.cai
     * @Date: 2017/6/10 15:25
     */
    @RequestMapping(params = "storeinfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getStoreInfo() throws Exception{
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        int userId = user.getId();
        int orgId = user.getOrgId();
        OrgProfileDTO org = managerOrgService.getOrgInfo(orgId);
        org.setType(user.getType());    //门店类型
        org.setDetail(null);
        ajaxJson.setObj(org);
        
        int newsCount = messageService.countUnreadMessage(userId, ORG_USER);
        
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("newsCount", newsCount);
        attributes.put("userCode", user.getUserCode());
        attributes.put("userId", userId);
        attributes.put("user", user);
        ajaxJson.setAttributes(attributes);
        return ajaxJson;
    }

    /**
     * @Description: 获取服务师信息
     * @Author: wenxian.cai
     * @Date: 2017/7/12 9:57
     */
    @RequestMapping(params = "services-info", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getServicesInfo() {
        AjaxJson resObject = new AjaxJson();

        LoginUser user = getLoginUser();
        int userId = user.getId();
        String userCode = user.getUserCode();
        OrgUserSharingData data = loginService.getCacheOrgMemberSharingData(userId);

        resObject.setSuccess(true);
        String name = data.getRealName();
        String head = data.getPhotoPath();
        int userTypeValue = data.getUserType();
        Boolean isEmailVerified = data.getEmailVerified();
        Boolean isMobileVerified = data.getMobileVerified();
        String userType = null;
        switch (userTypeValue) {
            case 0:
                userType = "管理员";
                break;
            case 1:
                userType = "服务师";
                break;
            case 2:
                userType = "管理员&服务师";
                break;
        }
        String professionalName = data.getProfessionalName();
        TOrg org = managerOrgService.getOrgMessage(user.getOrgId());
        String orgName = org.getOrgName();

        int newCount = messageService.countSystemUnreadMessage(userId, ORG_USER);
        boolean isQualificated = false;
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userName", name);
        attributes.put("userHead", head);
        attributes.put("orgName", orgName);
        attributes.put("userType", userType);
        attributes.put("isQualificated", isQualificated);
        attributes.put("isMobileVerified", isMobileVerified != null && isMobileVerified);
        attributes.put("isEmailVerified", isEmailVerified != null && isEmailVerified);
        attributes.put("newsCount", newCount);
        attributes.put("userCode", userCode);
        attributes.put("professionalName", professionalName);
        attributes.put("userId", userId);
        attributes.put("user", user);
        resObject.setAttributes(attributes);

        return resObject;
    }

    /**
     * @Description: 服务师账户安全页面
     * @Author: wenxian.cai
     * @Date: 2017/6/14 11:32
     */
    @RequestMapping(value = "/services/accountsecurity")
    public ModelAndView accountSecurity() {
        ModelAndView modelAndView = new ModelAndView("platform/org/accountsecurity/account-security");
        LoginUser loginUser = getLoginUser();
        OrgUserDTO user = orgUserService.getOrgUser(loginUser.getId());
        Map map = new HashMap();
        map.put("mobile", user.getMobile());
        map.put("email", user.getEmail());
        modelAndView.addObject("user", JSON.toJSON(map));
        modelAndView.addObject("orgType", loginUser.getType());
        modelAndView.addObject("userType", user.getUserType());
        return modelAndView;
    }

    /**
     * @Description: 修改密码页面
     * @Author: wenxian.cai
     * @Date: 2017/6/14 13:52
     */
    @RequestMapping(value = "/services/modifypassword")
    public ModelAndView modifyPasswordPage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/accountsecurity/modify-password");
        LoginUser user = getLoginUser();
        modelAndView.addObject("orgType", user.getType());
        modelAndView.addObject("userType", user.getUserType());
        return modelAndView;
    }

    /**
     * @Description: 修改密码
     * @Author: wenxian.cai
     * @Date: 2017/6/14 14:17
     */
    @RequestMapping(params = "modifypassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson modifyPassword(@RequestBody ModifyPasswordVO vo) {
        AjaxJson ajaxJson = new AjaxJson();
        int id = getLoginUser().getId();
        int result = orgUserService.updatePassword(id, StringUtil.decodeStr(vo.getOldPassword()),
                StringUtil.decodeStr(vo.getNewPassword()));
        if (result == 0) {
            ajaxJson.setMsg("操作失败");
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
    
    /**
     * 成为店铺
     * @return
     * @author liu
     * @时间 2019年1月29日 下午2:59:51
     * @remark
     */
    @RequestMapping(value = "/services/beStore")
    public ModelAndView toBeStore() {
    	ModelAndView modelAndView = new ModelAndView("platform/org/profile/tobe-store");
//    	LoginUser user = getLoginUser();
//    	modelAndView.addObject("user", user);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/services/add_shop", method= {RequestMethod.POST})
    @ResponseBody
    public AjaxJson createOneShop(@RequestParam(value = "shopName", required = false) String shopName) {
    	AjaxJson json = new AjaxJson();
    	LoginUser user = getLoginUser();
    	orgUserService.createShop(user, shopName);
    	return json;
    }
}
