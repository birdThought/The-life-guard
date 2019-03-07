package com.lifeshs.app.api.manager;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.common.constants.app.*;
import com.lifeshs.common.constants.app.Error;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.pojo.org.management.ManagementVO;
import com.lifeshs.pojo.org.server.OrgMember;
import com.lifeshs.pojo.org.server.OrgServer;
import com.lifeshs.pojo.org.server.OrgServerGroupBase;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.utils.*;
import com.lifeshs.utils.image.ImageUtilV2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 管理app-管理
 *
 * @author yuhang.weng
 * @DateTime 2016年11月18日 下午2:38:48
 */
@Controller
@RequestMapping("mapp/manager")
public class MAppManagerController  extends AppBaseController {

    @Autowired
    private IServiceOrgService serviceOrgService;

    @Autowired
    private IManagerOrgService managerOrgService;

    @Autowired
    private IDataAreaService areaService;

    @Autowired
    private com.lifeshs.service.org.impl.user.OrgUser orgUser;
    
    @Autowired
    private IEmployeeManageService employeeManageService;

    @RequestMapping(value = "addOrgUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject addOrgUser(@RequestParam String json) throws Exception {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
        TOrg org = commonTrans.getEntity(TOrg.class, orgUser.getOrgId());

        String userName = data.getString(OrgUser.USERNAME);
        String password = data.getString(OrgUser.PASSWORD);

        String realName = data.getString(OrgUser.NAME);
        String mobile = data.getString(OrgUser.MOBILE);
        int sex = data.getIntValue(OrgUser.SEX);
        String birthday = data.getString(OrgUser.BIRTHDAY);
        String address = data.getString(Area.ADDRESS);
        String photo = data.getString(OrgUser.PHOTO);
        String email = data.getString(OrgUser.EMAIL);
        String tel = data.getString(OrgUser.TEL);

        Integer userType = data.getInteger(OrgUser.TYPE);
        switch (org.getType().intValue()) {
        case 0:
            /** 管理机构中的用户都是管理员 */
            userType = 0;
            break;
        case 1:
            /** 服务机构，如果没有说明用户的类型，默认为服务师 */
            if (userType == null) {
                userType = 1;
            }
            break;
        case 2:
            /** 个体机构不存在添加用户选项，直接返回操作失败 */
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        Map<String, Object> user = new HashMap<>();

        if (!Toolkits.isVerifyUserName(userName)) {
            return MAppNormalService.error(Error.USERNAME_UNVERIFY);
        }
        if (!Toolkits.isVerifyPassword(password)) {
            return MAppNormalService.error(Error.PASSWORD_UNVERIFY);
        }
        if (StringUtils.isNotBlank(mobile)) {
            if (!Toolkits.verifyPhone(mobile)) {
                return MAppNormalService.error(Error.MOBILE_UNVERIFIY);
            }
            String mobileOwnerUserId = orgUserService.checkMobile(mobile);
            if (StringUtils.isNotBlank(mobileOwnerUserId)) {
                return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
            }
            user.put("mobile", mobile);
        }

        if (StringUtils.isNotBlank(email)) {
            if (!Toolkits.isEmail(email)) {
                return MAppNormalService.error(Error.EMAIL_UNVERIFIY);
            }
            String emailOwnerUserId = orgUserService.checkEmail(email);
            if (StringUtils.isNotBlank(emailOwnerUserId)) {
                return MAppNormalService.success(NormalMessage.EMAIL_OCCUPIED);
            }
            user.put("email", email);
        }

        if (StringUtils.isNotBlank(tel) && !Toolkits.verifyTel(tel)) {
            return MAppNormalService.error(Error.TEL_UNVERIFIY);
        }

        if (StringUtils.isNotBlank(photo)) {
            ImageDTO imageVO = MAppNormalService.uploadPhoto(photo, null, "head", false);
            if (imageVO.getUploadSuccess()) {
                user.put("photo", imageVO.getNetPath());
            }
        }

        user.put("userName", userName);
        user.put("password", password);
        user.put("realName", realName);
        user.put("sex", sex == 0 ? false : true);
        user.put("birthday", birthday);
        user.put("address", address);
        user.put("tel", tel);
        if (userType != null) {
            user.put("userType", userType);
        }

        Integer id = orgUserService.addEmploy(user, orgUser.getOrgId());

        if (id == null) {
            return MAppNormalService.success(NormalMessage.USER_EXIST);
        }

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(OrgUser.ID, id);

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "modifyOrgUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject modifyOrgUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        int userId = data.getIntValue(OrgUser.ID);
        String photo = data.getString(OrgUser.PHOTO);
        String realName = data.getString(OrgUser.NAME);
        String birthday = data.getString(OrgUser.BIRTHDAY);
        int sex = data.getIntValue(OrgUser.SEX);
        int userType = data.getIntValue(OrgUser.TYPE);
        String mobile = data.getString(OrgUser.MOBILE);
        String tel = data.getString(OrgUser.TEL);
        String email = data.getString(OrgUser.EMAIL);

        Date birthday_d = null;

        TOrgUser user = commonTrans.getEntity(TOrgUser.class, userId);

        if (StringUtils.isNotBlank(photo)) {

            ImageDTO imageVO = MAppNormalService.uploadPhoto(photo, user.getPhoto(), "head", false);
            if (imageVO.getUploadSuccess()) {
                user.setPhoto(imageVO.getNetPath());
            }
        }
        if (sex == 0) {
            user.setSex(false);
        } else {
            user.setSex(true);
        }
        if (StringUtils.isNotBlank(birthday)) {
            birthday_d = DateTimeUtilT.date(birthday);
            user.setBirthday(birthday_d);
        }
        if (StringUtils.isNotBlank(mobile)) {
            if (!Toolkits.verifyPhone(mobile)) {
                return MAppNormalService.error(Error.MOBILE_UNVERIFIY);
            }
            String mobileOwnerUserId = orgUserService.checkMobile(mobile);
            if (StringUtils.isNotBlank(mobileOwnerUserId)) {
                return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
            }
            user.setMobile(mobile);
        }
        if (StringUtils.isNotBlank(tel) && !Toolkits.verifyTel(tel)) {
            return MAppNormalService.error(Error.TEL_UNVERIFIY);
        } else {
            user.setTel(tel);
        }
        if (StringUtils.isNotBlank(email)) {
            if (!Toolkits.isEmail(email)) {
                return MAppNormalService.error(Error.EMAIL_UNVERIFIY);
            }
            String emailOwnerUserId = orgUserService.checkEmail(email);
            if (StringUtils.isNotBlank(emailOwnerUserId)) {
                return MAppNormalService.success(NormalMessage.EMAIL_OCCUPIED);
            }
            user.setEmail(email);
        }

        user.setRealName(realName);
        user.setUserType(userType);
        user.setModifyDate(new Date());

        if (userType == 1) {
            if (orgUserService.isOrgRemainOneAdmin(orgUser.getOrgId())) {
                return MAppNormalService.error("该机构至少需要一个管理员");
            }
        }

        int orgId = orgUser.getOrgId();

        int orgId2 = user.getOrgId();

        if (orgId != orgId2) {
            return MAppNormalService.error(Error.FAIL_ACTION);
        }

        commonTrans.updateEntitie(user);

        Map<String, String> returnData = new HashMap<>();
        returnData.put(OrgUser.PHOTO, photo);

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "stopOrgUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject stopOrgUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        int userId = data.getIntValue(OrgUser.ID);

        TOrgUser user = commonTrans.getEntity(TOrgUser.class, userId);

        if (orgUser.getOrgId().equals(user.getOrgId())) {
            user.setStatus(UserStatus.leaveoff.value());
            user.setModifyDate(new Date());
            commonTrans.updateEntitie(user);
        }

        return MAppNormalService.success();
    }

    @RequestMapping(value = "searchOrgUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject searchOrgUser(@RequestParam String json) {
        return MAppNormalService.error(Error.ABOLISHED_METHOD);
//        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
//
//        JSONObject data = appJSON.getData();
//
//        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
//
//        String realName = data.getString(OrgUser.NAME);
//        int pageIndex = data.getIntValue(Page.INDEX);
//        int pageSize = data.getIntValue(Page.SIZE);
//
//        int orgId = orgUser.getOrgId();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("begin", pageIndex);
//        params.put("size", pageSize);
//        params.put("realName", realName);
//        params.put("orgId", orgId);
//
//        List<TOrgUser> users = orgUserService.getEmployList(params);
//        List<Map<String, Object>> returnDatas = searchOrgUserList(users, null);
//
//        return MAppNormalService.success(returnDatas);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject addUser(@RequestParam String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);

        String realName = data.getString(User.REALNAME);
        String mobile = data.getString(User.MOBILE);
        Integer sex = data.getInteger(User.SEX);
        String photo = data.getString(User.PHOTO);
        String userName = data.getString(User.USERNAME);
        String password = data.getString(User.PASSWORD);

        Integer orgServeId = data.getInteger(OrgServe.ID);
        Integer serveGroupId = data.getInteger(OrgServeGroup.ID);
        Integer chargeMode = data.getInteger(Order.CHARGE_MODE);
        Integer number = data.getInteger(Order.COUNT);

        // validate
        if (!Toolkits.isVerifyUserName(userName)) {
            return MAppNormalService.error(Error.USERNAME_UNVERIFY);
        }
        if (!Toolkits.isVerifyPassword(password)) {
            return MAppNormalService.error(Error.PASSWORD_UNVERIFY);
        }
        if (StringUtils.isNotBlank(mobile)) {
            String mobileOwnerUserId = orgUserService.checkMobile(mobile);
            if (StringUtils.isNotBlank(mobileOwnerUserId)) {
                return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
            }
        }

        // upload photo
        String newPhotoPath = "";
        if (StringUtils.isNotBlank(photo)) {
            ImageDTO imageVO = MAppNormalService.uploadPhoto(photo, null, "head", false);
            if (imageVO.getUploadSuccess()) {
                newPhotoPath = imageVO.getNetPath();
            }
        }

        if (realName.length() > 18) {
            return MAppNormalService.success("名字长度不能超过18个字符");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        params.put("realName", realName);
        params.put("sex", sex == 0 ? false : true);
        params.put("mobile", mobile);
        params.put("serveId", orgServeId + ""); // 这里没有错
        params.put("groupId", serveGroupId + "");
        params.put("count", number);
        params.put("chargeMode", chargeMode);

        int userId = orgUserService.addUser(params);

        if (userId > 0 && StringUtils.isNotBlank(newPhotoPath)) {
            Map<String, Object> c = new HashMap<>();

            Map<String, Object> column = new HashMap<>();

            column.put("photo", newPhotoPath);

            Map<String, Object> condition = new HashMap<>();
            condition.put("id", userId);

            String table = ReflectUtils.reflectTableName(TUser.class);

            c.put("column", column);
            c.put("condition", condition);
            c.put("table", table);
            commonTrans.updateByMap(c);
        }

        if (userId == 0) {
            return MAppNormalService.success(NormalMessage.USER_EXIST);
        }

        Map<String, Integer> returnData = new HashMap<>();
        returnData.put(User.ID, userId);

        return MAppNormalService.success(returnData);
    }

    @RequestMapping(value = "getOrgAllServeList", method = RequestMethod.POST)
    public @ResponseBody JSONObject getOrgAllServeList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        List<Map<String, Object>> returnDatas = new ArrayList<>();

        List<OrgServer> serList = serviceOrgService.getOrgServiceListAndMemberCount(orgUser.getOrgId(), null);

        for (OrgServer s : serList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(OrgServe.ID, s.getOrgServeId());
            returnData.put(Serve.NAME, s.getServiceName());

            List<Map<String, Object>> groups = new ArrayList<>();

            List<OrgServerGroupBase> bases = serviceOrgService.findGroupByKey(orgUser.getOrgId(), s.getId(), null);
            for (OrgServerGroupBase base : bases) {
                Map<String, Object> group = new HashMap<>();
                group.put(OrgServeGroup.ID, base.getId());
                group.put(OrgServeGroup.NAME, base.getName());
                groups.add(group);
            }

            returnData.put(OrgServeGroup.GROUPS, groups);

            List<Map<String, Object>> chargeDetails = new ArrayList<>();

            Boolean hasFree = s.getHasFree();
            if (hasFree != null && hasFree) {
                Map<String, Object> cd = new HashMap<>();
                cd.put("name", "免费");
                cd.put("chargeMode", 0);
                chargeDetails.add(cd);
            }

            Boolean hasTime = s.getHasTime();
            if (hasTime != null && hasTime) {
                Map<String, Object> cd = new HashMap<>();
                cd.put("name", NumberUtils.changeF2Y(s.getTimePrice() + "") + "元/次");
                cd.put("chargeMode", 1);
                chargeDetails.add(cd);
            }

            Boolean hasMonth = s.getHasMonth();
            if (hasMonth != null && hasMonth) {
                Map<String, Object> cd = new HashMap<>();
                cd.put("name", NumberUtils.changeF2Y(s.getMonthPrice() + "") + "元/月");
                cd.put("chargeMode", 2);
                chargeDetails.add(cd);
            }

            Boolean hasYear = s.getHasYear();
            if (hasYear != null && hasYear) {
                Map<String, Object> cd = new HashMap<>();
                cd.put("name", NumberUtils.changeF2Y(s.getYearPrice() + "") + "元/年");
                cd.put("chargeMode", 3);
                chargeDetails.add(cd);
            }

            returnData.put("chargeDetail", chargeDetails);

            returnDatas.add(returnData);
        }

        return MAppNormalService.success(returnDatas);
    }
    
    
    @RequestMapping(value = "addemployee", method = RequestMethod.POST)
    public @ResponseBody JSONObject addemployee(@RequestParam String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        EmployeeRegisterDTO employee = new EmployeeRegisterDTO();
        
        String photo = data.getString("photo");
        String realName = data.getString("realName");
        String idCard = data.getString("idCard");
        Boolean gender = data.getBoolean("gender");
        Date birthday = data.getDate("birthday");
        String idCardPicOne = data.getString("idCardPicOne");
        String idCardPicTwo = data.getString("idCardPicTwo");
        Integer userType = data.getInteger("userType");
        String professionalName = data.getString("professionalName");
        String professionalPic = data.getString("professionalPic");
        String userName = data.getString("userName");
        String password = data.getString("password");
        String initPassword = data.getString("initPassword");
        String mobile = data.getString("mobile");
        Integer orgId = data.getInteger("orgId");
        Integer orgUserId = data.getInteger("orgUserId");
        
        //todo 判断员工信息的完整性、正确性
        boolean isExist = orgUser.userIsExist(userName);
        if (isExist) {
            return MAppNormalService.success("账户已存在!");
        }
        
        //两次密码输入不一致
        if (!password.equals(initPassword)) {
            return MAppNormalService.error(Error.PASSWORD_ATYPISM);
        }
        
        if (realName.length() > 18) {
            return MAppNormalService.success("名字长度不能超过18个字符");
        }
        
        employee.setRealName(realName);
        employee.setIdCard(idCard);
        employee.setGender(gender);
        employee.setBirthday(birthday);
        employee.setUserType(userType);
        employee.setProfessionalName(professionalName);
        employee.setUserName(userName);
        employee.setPassword(password);
        employee.setMobile(mobile);
        
        try {
            String newRelativePath = null;

            if (StringUtils.isNotBlank(photo)) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(photo, "photo");
                ImageUtilV2.delImg(photo);
                employee.setPhoto(newRelativePath);
            }

            if (StringUtils.isNotBlank(idCardPicOne)) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(idCardPicOne, "photo");
                ImageUtilV2.delImg(idCardPicOne);
                employee.setIdCardPicOne(newRelativePath);
            }

            if (StringUtils.isNotBlank(idCardPicTwo)) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(idCardPicTwo, "photo");
                ImageUtilV2.delImg(idCardPicTwo);
                employee.setIdCardPicTwo(newRelativePath);
            }

            if (StringUtils.isNotBlank(professionalPic)) {
                newRelativePath = ImageUtilV2.copyImgFileToUploadFolder(professionalPic, "photo");
                ImageUtilV2.delImg(professionalPic);
                employee.setProfessionalPic(newRelativePath);
            }
        } catch (Exception e) {
            return MAppNormalService.success("移动图片失败");
        }
        
        employee.setOrgId(orgId);
        employee.setStatus(0);
        employee.setParentId(AgentConstant.AGENT_USER_TYPE_O + orgUserId);

        boolean bool = employeeManageService.addEmployee(employee);

        if (!bool) {
            return MAppNormalService.error("操作失败!");
        }
        
        return MAppNormalService.success("操作成功操作失败!");
    }
    
    

    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    public @ResponseBody JSONObject getUserList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        int pageIndex = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);
        Integer orgServeId = data.getInteger(OrgServe.ID);

        List<Map<String, Object>> returnDatas = searchUserList(pageIndex, pageSize, orgUser.getOrgId(), orgServeId, "");

        return MAppNormalService.success(returnDatas);
    }

    @RequestMapping(value = "searchUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject searchUser(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();

        String realName = data.getString(User.REALNAME);
        int pageIndex = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);

        List<Map<String, Object>> returnDatas = new ArrayList<>();

        if (StringUtils.isNotBlank(realName)) {
            returnDatas = searchUserList(pageIndex, pageSize, orgUser.getOrgId(), null, realName);
        }

        return MAppNormalService.success(returnDatas);
    }

    private List<Map<String, Object>> searchUserList(int pageIndex, int pageSize, int orgId, Integer orgServeId,
            String userRealName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orgId", orgId);
        params.put("page", pageIndex);
        params.put("dPage", pageSize);

        Map<String, Object> condition = new HashMap<>();
        if (orgServeId != null) {
            condition.put("curServiceId", orgServeId);
        }

        if (StringUtils.isNotBlank(userRealName)) {
            condition.put("userRealName", userRealName);
        }

        if (!condition.isEmpty()) {
            params.put("condition", condition);
        }

        List<OrgMember> members = serviceOrgService.findMemberList(params);

        List<Map<String, Object>> returnDatas = new ArrayList<>();
        for (OrgMember member : members) {
            Map<String, Object> returnData = new HashMap<>();

            Boolean sex = member.getSex();
            int sex_i = 0;
            if (sex != null && sex) {
                sex_i = 1;
            }

            returnData.put(User.ID, member.getUserId());
            returnData.put(User.REALNAME, member.getRealName());
            returnData.put(User.MOBILE, member.getMobile());
            returnData.put(User.SEX, sex_i);
            returnData.put(User.BIRTHDAY, member.getBirthday());
            returnData.put(Serve.SERVE_NAME, member.getCurService());
            returnData.put(OrgServeGroup.NAME, member.getGroupName());
            returnData.put(User.PHOTO, member.getPhoto());
            returnData.put(OrgServeGroup.ID, member.getGroupId());
            returnData.put(Order.HAS_WARNING, member.getHasWarning());

            returnDatas.add(returnData);
        }

        return returnDatas;
    }

    @RequestMapping(value = "addSubOrg", method = RequestMethod.POST)
    public @ResponseBody JSONObject addSubOrg(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();

        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
        int orgId = orgUser.getOrgId();

        String orgName = data.getString(Org.NAME);
        int type = data.getIntValue(Org.TYPE);
        String orgType = data.getString(Org.ORG_TYPE);
        String userName = data.getString(OrgUser.USERNAME);
        String password = data.getString(OrgUser.PASSWORD);

        String contacts = data.getString(Org.CONTACTS);
        String contactInformation = data.getString(Org.CONTACTS_INFORMATION);
        String businessLicense = data.getString(Org.BUSINESS_LICENSE);

        String province = data.getString(Area.PROVINCE);
        String city = data.getString(Area.CITY);
        String district = data.getString(Area.DISTRICT);
        String street = data.getString(Area.STREET);

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

        boolean verifiedCOntactInformation = Toolkits.verifyTel(contactInformation);
        if (!verifiedCOntactInformation) {
            verifiedCOntactInformation = Toolkits.verifyPhone(contactInformation);
        }
        if (!verifiedCOntactInformation) {
            return MAppNormalService.error(Error.CONTACT_INFORMATION_UNVERIFIY);
        }

        String realativePath = "";
        boolean isBusinessLicenseBlank = StringUtils.isBlank(businessLicense);
        if (!isBusinessLicenseBlank) {
            ImageDTO imageVO = MAppNormalService.uploadPhoto(businessLicense, null, "license", false);
            if (imageVO.getUploadSuccess()) {
                realativePath = imageVO.getNetPath();
            }
        }

        ManagementVO orgVO = new ManagementVO();
        // setting the address
        orgVO = setAddress(orgVO, district, city, province, street);

        orgVO.setOrgType(orgType);
        orgVO.setContact(contacts);
        orgVO.setContactInformation(contactInformation);
        orgVO.setBusinessLicense(realativePath);
        orgVO.setType(type);
        orgVO.setOrgName(orgName);
        orgVO.setUserName(userName);
        orgVO.setPassword(password);

        Integer childOrgId = managerOrgService.registChildOrg(orgVO, orgId);
        if (childOrgId != null) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put(Org.ID, childOrgId);
            return MAppNormalService.success(returnData);
        }

        return MAppNormalService.error(Error.FAIL_ACTION);
    }

    private ManagementVO setAddress(ManagementVO org, String district, String city, String province, String street) {
        // district is setting?
        String code = "";
        String provinceCode = null;
        String cityCode = null;
        String districtCode = null;
        boolean isDistrictBlank = StringUtils.isBlank(district);
        if (!isDistrictBlank) {

            if (district.equals("其它") || district.equals("其他")) {

            } else {

            }

            List<String> codes = areaService.getAreaCode(district);
            if (codes.size() == 0) {

            }
            if (codes.size() == 1) {
                code = codes.get(0);
            }
            if (codes.size() > 1) {
                for (String c : codes) {
                    String c_city = areaService.getAreaCode(city).get(0).substring(0, 4);
                    String d_city = c.substring(0, 4);
                    if (StringUtils.equals(c_city, d_city)) {
                        code = c;
                        break;
                    }
                }
            }
            boolean isCodeBlank = StringUtils.isBlank(code);
            if (!isCodeBlank) {
                provinceCode = code.substring(0, 2);
                cityCode = code.substring(2, 4);
                districtCode = code.substring(4, 6);
            }
        }

        org.setProvince(provinceCode);
        org.setCity(cityCode);
        org.setDistrict(districtCode);
        org.setStreet(street);

        return org;
    }

    @RequestMapping(value = "getOrgList", method = RequestMethod.POST)
    public @ResponseBody JSONObject getOrgList(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);

        JSONObject data = appJSON.getData();
        Integer oId = data.getInteger(Org.ID);

        if (oId == null) {
            TOrgUser orgUser = appJSON.getAopData().getOrgUser();
            oId = orgUser.getOrgId();
        }

        /**
         *
         * [{orgName=嘎嘎嘎, amountOfManagement=0, contact=张生, amountOfService=0, contactNumber=暂未填写, hasNext=false, id=163, type=0}]
         *
         * [{orgName=吱吱吱, contact=黄生, contactNumber=暂未填写, id=164, amountOfMemeber=0}]
         *
         **/

        List<Map<String, Object>> subOrgs = managerOrgService.getChildOrgMessage(oId);
        for (Map<String, Object> subOrg : subOrgs) {
            int orgId = (int) subOrg.get("id");
            String orgName = (String) subOrg.get("orgName");
            String contacts = (String) subOrg.get("contact");
            String contactInformation = (String) subOrg.get("contactNumber");
            int subOrgCount = (int) subOrg.get("amountOfManagement");
            int subStoreCount = (int) subOrg.get("amountOfService");

            subOrg.clear();
            subOrg.put(Org.ID, orgId);
            subOrg.put(Org.NAME, orgName);
            subOrg.put(Org.CONTACTS, contacts);
            subOrg.put(Org.CONTACTS_INFORMATION, contactInformation);
            subOrg.put(Org.SUB_ORG_COUNT, subOrgCount);
            subOrg.put(Org.SUB_STORE_COUNT, subStoreCount);
        }

        List<Map<String, Object>> subStores = managerOrgService.getChildOrgServiceMessage(oId);
        for (Map<String, Object> subStore : subStores) {
            int orgId = (int) subStore.get("id");
            String orgName = (String) subStore.get("orgName");
            String contacts = (String) subStore.get("contact");
            String contactInformation = (String) subStore.get("contactNumber");
            int userCount = (int) subStore.get("amountOfMemeber");

            Map<String, Object> condition = new HashMap<>();
            condition.put("orgId", orgId);
            condition.put("userType", 0);
            int managerUserCount = commonTrans.getCount(TOrgUser.class, condition);

            condition.put("userType", 1);
            int serveUserCount = commonTrans.getCount(TOrgUser.class, condition);
            
            subStore.clear();
            subStore.put(Org.ID, orgId);
            subStore.put(Org.NAME, orgName);
            subStore.put(Org.CONTACTS, contacts);
            subStore.put(Org.CONTACTS_INFORMATION, contactInformation);
            subStore.put(OrgServe.MEMBER_COUNT, userCount);
            subStore.put(OrgUser.MANAGER_USER_COUNT, managerUserCount);
            subStore.put(OrgUser.SERVE_USER_COUNT, serveUserCount);
        }
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Org.SUB_ORGS, subOrgs);
        returnData.put(Org.SUB_STORES, subStores);
        
        return MAppNormalService.success(returnData);
    }
}
