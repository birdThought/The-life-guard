package com.lifeshs.app.api.store.release;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.model.idCard.IdCardReturn;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.register.AccountDTO;
import com.lifeshs.dto.manager.register.AreaDTO;
import com.lifeshs.dto.manager.register.WorkAddressDTO;
import com.lifeshs.dto.manager.register.individualStore.BasicDTO;
import com.lifeshs.dto.manager.register.individualStore.IndividualStoreDTO;
import com.lifeshs.dto.manager.register.individualStore.PersonalQulificationDTO;
import com.lifeshs.dto.manager.register.individualStore.StoreDTO;
import com.lifeshs.dto.manager.register.store.BankAccountDTO;
import com.lifeshs.dto.manager.register.store.LegalPersonDTO;
import com.lifeshs.dto.manager.register.store.OrgDTO;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.pojo.app.ImageDTO;
import com.lifeshs.pojo.org.OrgRegisterDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.tool.impl.ValidCodeServiceImpl;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.validator.IdCard;
import com.lifeshs.vo.data.area.CodeVO;

/**
 *  管理app注册
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月10日 下午1:55:28
 */
@RestController
@RequestMapping("/mapp/register")
public class RegisterController extends AppBaseController {

    @Autowired
    private ValidCodeServiceImpl validCodeService;
    
    @Autowired
    private IManagerOrgService managerOrgService;
    
    @Autowired
    private IEmployeeManageService employeeManageService;
    
    
    @Autowired
    protected IMemberService memberService;
    
    /**
     *  个体门店注册
     *  @author yuhang.weng 
     *  @DateTime 2017年8月10日 下午1:55:19
     *
     *  @param sumbitDTO
     *  @param store
     *  @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "individualStore", method = RequestMethod.POST) 
    public JSONObject individualStore(SubmitDTO sumbitDTO, IndividualStoreDTO individualStore) {
        BasicDTO basic = individualStore.getBasic();
        PersonalQulificationDTO personalQulification = individualStore.getPersonalQulification();
        StoreDTO store = individualStore.getStore();
        AccountDTO account = individualStore.getAccount();
        
        /** 非空判断开始 */
        if (basic == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "用户基础信息"));
        }
        if (personalQulification == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "资格认证信息"));
        }
        if (store == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "店铺简介信息"));
        }
        if (account == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "账号申请信息"));
        }
        
        // 实名认证用户基础信息
        String photo = basic.getPhoto();
        String realName = basic.getRealName();
        String idCardNumber = basic.getIdCardNumber();
        String idCardPicFront = basic.getIdCardPicFront();
        String idCardPicBack = basic.getIdCardPicBack();
        if (StringUtils.isBlank(photo)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "头像"));
        }
        if (StringUtils.isBlank(realName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "姓名"));
        }
        if (StringUtils.isBlank(idCardNumber)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证号码"));
        }
        if (StringUtils.isBlank(idCardPicFront)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证正面照"));
        }
        if (StringUtils.isBlank(idCardPicBack)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证反面照"));
        }
        
        // 个人资格认证
        // 工作地址
        WorkAddressDTO workAddress = personalQulification.getWorkAddress();
        if (workAddress == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "工作地址"));
        }
        AreaDTO area = workAddress.getArea();
        if (area == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "地区"));
        }
        String province = area.getProvince();
        String city = area.getCity();
        String county = area.getCounty();
        if (StringUtils.isBlank(province)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "省份"));
        }
        if (StringUtils.isBlank(city)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "市区"));
        }
        if (StringUtils.isBlank(county)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "县城"));
        }
        String address = workAddress.getAddress();
        if (StringUtils.isBlank(address)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "详细地址"));
        }
        Double longitude = workAddress.getLongitude();
        Double latitude = workAddress.getLatitude();
        if (longitude == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "经度"));
        }
        if (latitude == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "纬度"));
        }
        String expertField = personalQulification.getExpertField();
        String userAbout = personalQulification.getAbout();
        String professionalName = personalQulification.getProfessionalName();
        String professionPic = personalQulification.getProfessionPic();
        if (StringUtils.isBlank(expertField)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "专业擅长"));
        }
        if (StringUtils.isBlank(userAbout)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "个人简介"));
        }
        if (StringUtils.isBlank(professionalName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "职称"));
        }
        if (StringUtils.isBlank(professionPic)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "职业资格照"));
        }
        
        //店铺
        String orgName = store.getOrgName();
        String orgType = store.getOrgType();
        String workField = store.getWorkField();
        String orgAbout = store.getAbout();
        if (StringUtils.isBlank(orgName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "店铺名称"));
        }
        if (StringUtils.isBlank(orgType)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "店铺分类"));
        }
        if (StringUtils.isBlank(workField)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "店铺从事领域"));
        }
        if (StringUtils.isBlank(orgAbout)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "店铺简介"));
        }
        
        //  帐号 密码
        String userName = account.getUserName();
        String password = account.getPassword();
        String mobile = account.getMobile();
        String verifyCode = account.getVerifyCode();
        Integer userType = account.getUserType();
        //引荐人ID
        String parentId = account.getParentId();
        
        if (StringUtils.isBlank(userName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "登录账号"));
        }
        if (StringUtils.isBlank(password)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "登录密码"));
        }
        if (StringUtils.isBlank(mobile)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "手机号码"));
        }
        if (StringUtils.isBlank(verifyCode)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "手机验证码"));
        }
        if (userType == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "账号申请类型"));
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
	        		 return ReturnDataAgent.error(String.format(Error.NOT_PARENTID, "引荐人ID"));
	             }
            }else {
        		return ReturnDataAgent.error(String.format(Error.NOT_PARENTID, "引荐人ID"));
        	}
        }else {
        	//设置默认推荐人ID
        	parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }
        
        /** 非空判断结束 */
        
        /** 条件限制开始 */
        // 身份证
        IdCardReturn idCardReturn = IdCard.validateAllIdcard(idCardNumber);
        if (!idCardReturn.isValid()) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "身份证号码"));
        }
        if (expertField.length() > 200) {
            return ReturnDataAgent.error(String.format(Error.LENGTH, "专业擅长", 200));
        }
        if (userAbout.length() > 200) {
            return ReturnDataAgent.error(String.format(Error.LENGTH, "个人简介", 200));
        }
        if (orgAbout.length() > 200) {
            return ReturnDataAgent.error(String.format(Error.LENGTH, "店铺简介", 200));
        }
        boolean userNameValid = Toolkits.isVerifyUserName(userName);
        if (!userNameValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "登录账号"));
        }
        boolean passowrdValid = Toolkits.isVerifyPassword(password);
        if (!passowrdValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "登录密码"));
        }
        boolean mobileValid = Toolkits.verifyPhone(mobile);
        if (!mobileValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "手机号码"));
        }
        // 检查手机号是否被占用
        String userId = orgUserService.checkMobile(mobile);
        if (StringUtils.isNotBlank(userId)) {
            return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
        }
        // 检查用户名是否被占用
        if (orgUserService.userIsExist(userName)) {
            return ReturnDataAgent.error("登录账号已被注册");
        }
        // 校验验证码是否正确或者过期
        if (!validCodeService.valid(mobile, verifyCode, CacheType.USER_REGISTERY_CACHE)) {
            return ReturnDataAgent.error(String.format(Error.MISTAKE, "验证码"));
        }
        /** 条件限制结束 */
        
        /** 图片上传开始 */
        ImageDTO image = null;
        
        String headNetPath = null;
        image = MAppNormalService.uploadPhoto(photo, null, "head", false);
        if (image.getUploadSuccess()) {
            headNetPath = image.getNetPath();
        }
        String idCardFrontNetPath = null;
        image = MAppNormalService.uploadPhoto(idCardPicFront, null, "idCard", false);
        if (image.getUploadSuccess()) {
            idCardFrontNetPath = image.getNetPath();
        }
        String idCardBackNetPath = null;
        image = MAppNormalService.uploadPhoto(idCardPicBack, null, "idCard", false);
        if (image.getUploadSuccess()) {
            idCardBackNetPath = image.getNetPath();
        }
        String professionNetPath = null;
        image = MAppNormalService.uploadPhoto(professionPic, null, "professional", false);
        if (image.getUploadSuccess()) {
            professionNetPath = image.getNetPath();
        }
        /** 图片上传结束 */
        
        /** 地址处理开始*/
        CodeVO codeVO = mappNormalService.setAddress(county, city, province);
        /** 地址处理结束 */
        
        /** 注册开始 */
        OrgRegisterDTO org = new OrgRegisterDTO();
        org.setType(2); // 默认为2
        org.setOrgName(orgName);
        org.setOrgType(orgType);
        org.setWorkField(workField);
        org.setAbout(orgAbout);
        org.setMobile(mobile);
        org.setProvince(codeVO.getProvince());
        org.setCity(codeVO.getCity());
        org.setDistrict(codeVO.getCounty());
        org.setAddress(address);
        org.setLongitude(longitude.toString());
        org.setLatitude(latitude.toString());
        org.setAbout(orgAbout);
        org.setLogo(headNetPath);
        org.setParentId(parentId);
        int orgId = managerOrgService.registOrg(org);
        if (orgId == 0) {
            return ReturnDataAgent.error("机构注册失败");
        }
        
        EmployeeRegisterDTO employee = new EmployeeRegisterDTO();
        employee.setUserName(userName);
        employee.setPassword(password);
        //引荐人ID
        employee.setParentId(AgentConstant.AGENT_USER_TYPE_O + orgId);
        employee.setRealName(realName);
        employee.setUserType(userType);
        employee.setPhoto(headNetPath);
        employee.setIdCard(idCardNumber);
        employee.setIdCardPicOne(idCardFrontNetPath);
        employee.setIdCardPicTwo(idCardBackNetPath);
        employee.setAddress(address);
        employee.setProfessionalName(professionalName);
        employee.setProfessionalPic(professionNetPath);
        employee.setExpertise(expertField);
        employee.setAbout(userAbout);
        employee.setOrgId(orgId);
        employee.setGender(idCardReturn.getGender());
        employee.setBirthday(idCardReturn.getBirthday());
        employee.setMobile(mobile);
        employee.setMobileVerified(true);
        boolean bool = employeeManageService.addEmployee(employee);
        if (!bool) {
            return ReturnDataAgent.error("用户注册失败");
        }
        
        /** 注册结束 */
        
        // 销毁验证码
        validCodeService.destory(mobile, CacheType.USER_REGISTERY_CACHE);
        return ReturnDataAgent.success();
    }
    
    /**
     *  注册门店
     *  @author yuhang.weng 
     *  @DateTime 2017年8月11日 上午11:11:38
     *
     *  @param sumbitDTO
     *  @param store
     *  @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public JSONObject store(SubmitDTO sumbitDTO, com.lifeshs.dto.manager.register.store.StoreDTO store) {
        OrgDTO org = store.getOrg();
        BankAccountDTO bankAccount = store.getBankAccount();
        LegalPersonDTO legalPerson = store.getLegalPerson();
        AccountDTO account = store.getAccount();
        
        /** 非空判断开始 */
        if (org == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司信息认证信息"));
        }
        if (legalPerson == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "法人认证信息"));
        }
        if (account == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "申请账号信息"));
        }
        String bAccount = null;
        String bBranch = null;
        String bDistrict = null;
        if (bankAccount != null) {
            bAccount = bankAccount.getAccount();
            bBranch = bankAccount.getBankBranch();
            bDistrict = bankAccount.getBankDistrict();
        }
        
        // 门店信息相关
        String businessLicenseNumber = org.getBusinessLicenseNumber();
        String businessLicensePic = org.getBusinessLicensePic();
        String logo = org.getLogo();
        String orgName = org.getOrgName();
        String orgType = org.getOrgType();
        String tel = org.getTel();
        String workField = org.getWorkField();
        if (StringUtils.isBlank(businessLicenseNumber)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "营业执照注册号"));
        }
        if (StringUtils.isBlank(businessLicensePic)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "营业执照图片"));
        }
        if (StringUtils.isBlank(logo)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司logo"));
        }
        if (StringUtils.isBlank(orgName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司名称"));
        }
        if (StringUtils.isBlank(orgType)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司性质"));
        }
        if (StringUtils.isBlank(tel)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司电话"));
        }
        if (StringUtils.isBlank(workField)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "从事领域"));
        }
        
        WorkAddressDTO workAddress = org.getWorkAddress();
        if (workAddress == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "公司所在地区信息"));
        }
        AreaDTO area = workAddress.getArea();
        if (area == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "地区"));
        }
        String province = area.getProvince();
        String city = area.getCity();
        String county = area.getCounty();
        if (StringUtils.isBlank(province)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "省份"));
        }
        if (StringUtils.isBlank(city)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "市区"));
        }
        if (StringUtils.isBlank(county)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "县城"));
        }
        String address = workAddress.getAddress();
        if (StringUtils.isBlank(address)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "详细地址"));
        }
        Double longitude = workAddress.getLongitude();
        Double latitude = workAddress.getLatitude();
        if (longitude == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "经度"));
        }
        if (latitude == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "纬度"));
        }
        
        // 法人信息相关
        String idCardNumber = legalPerson.getIdCardNumber();
        String idCardPicFront = legalPerson.getIdCardPicFront();
        String idCardPicBack = legalPerson.getIdCardPicBack();
        String legalPersonName = legalPerson.getName();
        if (StringUtils.isBlank(idCardNumber)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证号码"));
        }
        if (StringUtils.isBlank(idCardPicFront)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证正面照"));
        }
        if (StringUtils.isBlank(idCardPicBack)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "身份证反面照"));
        }
        if (StringUtils.isBlank(legalPersonName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "法人名字"));
        }
        
        // 账号相关
        String userName = account.getUserName();
        String password = account.getPassword();
        String mobile = account.getMobile();
        String verifyCode = account.getVerifyCode();
        Integer userType = account.getUserType();
        
        //引荐人ID
        String parentId = account.getParentId();
        
     // 判断是否有引荐人
        if(StringUtils.isNotBlank(parentId)) {
            // 查找引荐人ID否登记在系统中
            String userNo = parentId.toUpperCase();
        	//获取字符串的第一个字符
        	Object fir = userNo.subSequence(0, 1);//获取字符串的第一个字符
        	if(fir.equals("A") || fir.equals("Y") || fir.equals("D")) {
        		AdminUserDTO AdminUserDTO= memberService.getAdminUserUserNo(userNo);
	        	 if(AdminUserDTO ==null) {
	        		 return ReturnDataAgent.error(String.format(Error.NOT_PARENTID, "引荐人ID"));
	             }
            }else {
        		return ReturnDataAgent.error(String.format(Error.NOT_PARENTID, "引荐人ID"));
        	}
        }else {
        	//设置默认推荐人ID
        	parentId = AgentConstant.AGENT_DEFUALT_PARENT_ID_A2;
        }
        
        if (StringUtils.isBlank(userName)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "登录账号"));
        }
        if (StringUtils.isBlank(password)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "登录密码"));
        }
        if (StringUtils.isBlank(mobile)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "手机号码"));
        }
        if (StringUtils.isBlank(verifyCode)) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "手机验证码"));
        }
        if (userType == null) {
            return ReturnDataAgent.error(String.format(Error.MISSING, "账号申请类型"));
        }
        /** 非空判断结束 */
        
        /** 条件限制开始 */
        // 身份证
        IdCardReturn idCardReturn = IdCard.validateAllIdcard(idCardNumber);
        if (!idCardReturn.isValid()) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "身份证号码"));
        }
        // 如果用户填写了开户账号，需要校验账号是否合法
        if (StringUtils.isNotBlank(bAccount)) {
            boolean bAccountValid = Toolkits.isVerifyBankAccount(bAccount);
            if (!bAccountValid) {
                return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "公司对公账号"));
            }
        }
        
        boolean isTelValid = Toolkits.verifyTel(tel);
        if (!isTelValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "公司电话"));
        }
        boolean userNameValid = Toolkits.isVerifyUserName(userName);
        if (!userNameValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "登录账号"));
        }
        boolean passowrdValid = Toolkits.isVerifyPassword(password);
        if (!passowrdValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "登录密码"));
        }
        boolean mobileValid = Toolkits.verifyPhone(mobile);
        if (!mobileValid) {
            return ReturnDataAgent.error(String.format(Error.UNVERIFIY, "手机号码"));
        }
        // 检查手机号是否被占用
        String userId = orgUserService.checkMobile(mobile);
        if (StringUtils.isNotBlank(userId)) {
            return MAppNormalService.success(NormalMessage.MOBILE_OCCUPIED);
        }
        // 检查用户名是否被占用
        if (orgUserService.userIsExist(userName)) {
            return ReturnDataAgent.error("登录账号已被注册");
        }
        // 校验验证码是否正确或者过期
        if (!validCodeService.valid(mobile, verifyCode, CacheType.USER_REGISTERY_CACHE)) {
            return ReturnDataAgent.error(String.format(Error.MISTAKE, "验证码"));
        }
        /** 条件限制结束 */
        
        /** 上传图片开始 */
        ImageDTO image = null;
        
        String logoNetPath = null;
        image = MAppNormalService.uploadPhoto(logo, null, "logo", false);
        if (image.getUploadSuccess()) {
            logoNetPath = image.getNetPath();
        }
        String businessLicensePicNetPath = null;
        image = MAppNormalService.uploadPhoto(businessLicensePic, null, "license", false);
        if (image.getUploadSuccess()) {
            businessLicensePicNetPath = image.getNetPath();
        }
        String idCardFrontNetPath = null;
        image = MAppNormalService.uploadPhoto(idCardPicFront, null, "idCard", false);
        if (image.getUploadSuccess()) {
            idCardFrontNetPath = image.getNetPath();
        }
        String idCardBackNetPath = null;
        image = MAppNormalService.uploadPhoto(idCardPicBack, null, "idCard", false);
        if (image.getUploadSuccess()) {
            idCardBackNetPath = image.getNetPath();
        }
        /** 上传图片结束 */
        
        /** 地址处理开始*/
        CodeVO codeVO = mappNormalService.setAddress(county, city, province);
        /** 地址处理结束 */
        
        /** 注册开始 */
        OrgRegisterDTO orgRegister = new OrgRegisterDTO();
        orgRegister.setOrgName(orgName);
        orgRegister.setBusinessLicenseNumber(businessLicenseNumber);
        orgRegister.setBusinessPic1(businessLicensePicNetPath);
        orgRegister.setType(1);
        orgRegister.setOrgType(orgType);
        orgRegister.setProvince(codeVO.getProvince());
        orgRegister.setCity(codeVO.getCity());
        orgRegister.setDistrict(codeVO.getCounty());
        orgRegister.setAddress(address);
        orgRegister.setWorkField(workField);
        orgRegister.setMobile(tel);
        orgRegister.setLongitude(longitude.toString());
        orgRegister.setLatitude(latitude.toString());
        orgRegister.setLogo(logoNetPath);
        
        orgRegister.setBankAccount(bAccount);
        orgRegister.setBankDistrict(bDistrict);
        orgRegister.setBankBranch(bBranch);
        orgRegister.setLegalPerson(legalPersonName);
        orgRegister.setLegalPersonGender(idCardReturn.getGender());
        orgRegister.setLegalPersonIdCard(idCardNumber);
        orgRegister.setLegalPersonPicOne(idCardFrontNetPath);
        orgRegister.setLegalPersonPicTwo(idCardBackNetPath);
        orgRegister.setParentId(parentId);
        
        int orgId = managerOrgService.registOrg(orgRegister);
        if (orgId == 0) {
            return ReturnDataAgent.error("机构注册失败");
        }
        
        EmployeeRegisterDTO employee = new EmployeeRegisterDTO();
        employee.setUserName(userName);
        employee.setPassword(password);
        employee.setUserType(0);
        employee.setOrgId(orgId);
        employee.setMobile(mobile);
        employee.setMobileVerified(true);
        employee.setParentId(AgentConstant.AGENT_USER_TYPE_O + orgId);
        
        boolean bool = employeeManageService.addEmployee(employee);
        if (!bool) {
            return ReturnDataAgent.error("用户注册失败");
        }
        /** 注册结束 */
        
        // 销毁验证码
        validCodeService.destory(mobile, CacheType.USER_REGISTERY_CACHE);
        return ReturnDataAgent.success();
    }
}
