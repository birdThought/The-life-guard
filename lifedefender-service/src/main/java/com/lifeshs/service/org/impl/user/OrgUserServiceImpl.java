package com.lifeshs.service.org.impl.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.AgentType;
import com.lifeshs.common.constants.common.SensitiveOperationType;
import com.lifeshs.common.constants.common.SensitiveUserType;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.common.constants.common.shop.ShopConstants.ShopState;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.org.manage.OrgManageDao;
import com.lifeshs.dao.org.manage.OrgServiceDao;
import com.lifeshs.dao.org.user.OrgUserDao;
import com.lifeshs.dao.shop.ShopDao;
import com.lifeshs.dao1.admin.adminDao;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.TOrgGroupOrguser;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.po.admin.adminPO;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.group.GroupDTO;
import com.lifeshs.pojo.org.profile.BaseProfileDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.order.impl.OrderServiceImpl;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.shop.ShopDTO;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.ReflectUtils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 版权归 TODO 企业用户的基本操作类
 *
 * @author yuhang.weng
 * @DateTime 2016年5月16日 下午5:00:08
 */
@Service("orgUserService")
public class OrgUserServiceImpl extends CommonServiceImpl implements IOrgUserService {

    @Autowired
    private OrgUser orgUser;

    @Autowired
    private OrgManageDao orgManageDao;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private OrderServiceImpl orderService;
    
    @Autowired
    private OrgServiceDao orgServiceDao;
    
    @Autowired @Qualifier("shop_dao")
    private ShopDao shopDao;
    
    @Resource(name = "orgUserDao")
    private OrgUserDao orgUserDao;
    
    @Resource(name = "adminDao")
    private adminDao adminDao;

    @Override
    public ServiceMessage basicInformation(TOrgUser tBaseOrgUser) throws Exception {
        return null;
    }

    @Override
    public boolean userIsExist(String userName) {
        return orgUser.userIsExist(userName);
    }

    @Override
    public String checkEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        return orgUser.checkEmailReturnUserId(email);
    }
    
    @Override
    public List<TOrgUser> getEmployListByRealName(String rName, int orgId){
        
        return orgUserDao.getEmployListByRealName(rName, orgId);
    }
    
    @Override
    public List<OrgUserPO> getOrgUserByRealName(String rName){
        
        return orgUserDao.getOrgUserByRealName(rName);
    }
    @Override
    public List<OrgUserPO> findComboOrgUserRelation(int comboId,int vipComboItemId){
        return orgUserDao.findComboOrgUserRelation(comboId,vipComboItemId);
    }
    @Override
    public List<OrgUserDTO> findComboServeUserRelation(int comboId,int vipComboItemId){
        return orgUserDao.findComboServeUserRelation(comboId,vipComboItemId);
    }
    
    @Override
    public List<TOrgUser> findOrgUserListByOrgId(Integer orgId){
         return orgUserDao.findOrgUserByOrgId(orgId);
    }
    
    @Override
    public Integer updateOrgUserPwdByUserId(Integer id, String pwd){
        return orgUserDao.updateOrgUserPwdByUserId(id, pwd);
    }
    

    @Override
    public String checkMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return "";
        }
        return orgUser.checkMobileReturnUserId(mobile);
    }

    @Override
    public String modifyPasswordByUserId(Integer userId, String password, String ip) {
        OrgUserDTO user = orgServiceDao.getOrgUser(userId);
        // 查找不到用户信息直接返回null
        if (user == null) {
            return null;
        }
        String newPassword = MD5Utils.encryptPassword(password);
        OrgUserDTO orgUserDTO = new OrgUserDTO();
        orgUserDTO.setId(userId);
        orgUserDTO.setPassword(newPassword);
        
        orgServiceDao.updateOrgUser(orgUserDTO);
        
        SensitiveOperationLogDTO log = new SensitiveOperationLogDTO();
        log.setUserId(userId);
        log.setGenerateData(user.getPassword());
        log.setNewData(newPassword);
        log.setOperationType(SensitiveOperationType.MODIFY_PASSWORD);
        log.setUserType(SensitiveUserType.ORG_USER);
        orgUser.saveSensitiveLog(log);
        
        return user.getUserName();
    }

    @Override
    public Integer addEmploy(Map<String, Object> user, Integer parentId) throws Exception {
        
        TOrgUser employ = ReflectUtils.getBean(user, TOrgUser.class);
        
        String userName = employ.getUserName();
        if (memberService.userIsExist(userName)) {
            return null;
        }
        
        employ.setUserName(StringUtil.decodeStr(employ.getUserName()));
        if (employ.getDetail() != null)
            employ.setDetail(ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml(employ.getDetail())));// 过滤掉危险脚本并进行对里面的图片进行转换
        employ.setUserType(user.containsKey("userType") ? (Integer) user.get("userType") : 0);// 如果是管理机构的用户默认给他来个管理员
        if (user.containsKey("photo")) {
            try {
                employ.setPhoto(ImageUtilV2.copyImgFileToUploadFolder((String) user.get("photo"), "photo"));
            } catch (IOException e) {
            }
        }
        employ.setUserCode(orgUser.getUserCode());
        employ.setPassword(MD5Utils.encryptPassword(StringUtil.decodeStr((String) user.get("password"))));
        employ.setStatus(UserStatus.normal.value());
        employ.setCreateDate(new Date());
        employ.setMobileVerified(false);
        employ.setEmailVerified(false);
        employ.setOrgId(parentId);// 日后要带一个机构id过来
        Integer id = orgUser.addUser2(employ);// 返回一个主键Id
        registHxUser(employ.getUserCode());// 注册一个环信用户
        if (user.containsKey("groupId")) {// 如果这个服务机构已经开通过服务，那么id必定带进来
            Integer groupId = Integer.parseInt((String) user.get("groupId"));
            TOrgGroupOrguser groupUser = new TOrgGroupOrguser();
            groupUser.setCreateDate(new Date());
            groupUser.setGroupId(groupId);
            groupUser.setOrgUserId(employ.getId());
            commonTrans.save(groupUser);// 新增群组服务师
        }
        
        return id;
    }

    @Override
    public int getOrgType(int orgId) {
        return orgManageDao.findOrgType(orgId);
    }

    @Override
    public List<TOrgUser> getEmployList(Map<String, Object> params) {
        int page = (int) params.get("begin");
        int pageSize = 15;
        
        if (params.containsKey("size")) {
            pageSize = (Integer) params.get("size");
            params.remove("size");
        }
        
        params.remove("begin");
        if (params.containsKey("realName")) {
            params.put("realName", "%" + params.get("realName") + "%");
        }
        params = ReflectUtils.queryEntityEnclosureParamsWholeColumn(TOrgUser.class, params);
        params.put("begin", (page - 1) * pageSize);
        params.put("dPage", pageSize);
        List<Map<String, Object>> list = orgManageDao.getEmployList(params);
        List<TOrgUser> users = new ArrayList<TOrgUser>();
        for (Map<String, Object> map : list) {
            users.add((TOrgUser) ReflectUtils.getBean(map, TOrgUser.class));
        }
        return users;
    }

    @Override
    public List<TOrgUser> getEmployList2(int curPage, int pageSize, Integer status, Integer userType, int orgId) {

        List<TOrgUser> users = new ArrayList<>();
        
        Map<String, Object> condition = new HashMap<>();
        
        condition.put("orgId", orgId);
        
        if (userType != null) {
            condition.put("userType", userType);
        }
        if (status != null) {
            condition.put("status", status);
        }

        int count = commonTrans.getCount(TOrgUser.class, condition);
        if ((curPage - 1) * pageSize >= count) {
            return users;
        }
        
        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        curPage = queryPageData.getCurPage();
        
        Map<String, Object> params = new HashMap<>();
        
        params = ReflectUtils.queryEntityEnclosureParamsWholeColumn(TOrgUser.class, condition);
        
        params.put("begin", startIndex);
        params.put("dPage", pageSize);
        
        List<Map<String, Object>> list = orgManageDao.getEmployList(params);
        
        for (Map<String, Object> map : list) {
            users.add(ReflectUtils.getBean(map, TOrgUser.class));
        }
        
        return users;
    }

    @Override
    public int controlEmploy(Map<String, Object> params) {
        int target = (int) params.get("target");
        int userId = 0;
        params.remove("target");
        switch (target) {
            case 0:// 员工离职
//                params.put("table", "t_org_user");
                @SuppressWarnings("unchecked")
                Map<String, Object> columnMap = (Map<String, Object>) params.get("column");
                userId = (int) columnMap.get("id");
//                int status = (int) columnMap.get("status");
                
                /*Map<String, Object> conditionMap = new HashMap<String, Object>();// 条件语句
                userId = Integer.parseInt((String) columnMap.get("id"));
                conditionMap.put("id", userId);
                columnMap.remove("id");
                params.put("condition", conditionMap);
                commonTrans.updateByMap(params);*/
                // TODO 
//                TOrgUser orgUser = commonTrans.getEntity(TOrgUser.class, userId);
                OrgUserDTO user = orgServiceDao.getOrgUser(userId);
                String userCode = user.getUserCode();
                for (GroupDTO group : user.getGroups()) {
                    String groupHuanxinId = group.getHuanxinId();
                    /** 退出环信群组 */
                    if (StringUtils.isNotBlank(groupHuanxinId)) {
                        huanXinService.removeGroupUser(groupHuanxinId, userCode);
                    }
                }
                
                // 更新用户状态为离职
                OrgUserDTO userTmp = new OrgUserDTO();
                userTmp.setId(userId);
                userTmp.setStatus(UserStatus.leaveoff.value());
                orgServiceDao.updateOrgUser(userTmp);
                
                //TODO 移除当前用户
//            LoginUser current = currentUser();
//            if (current.getId().equals(userId)) {// 自己离职
//                removeClient();// 移除当前用户
//                userRealm.clearCache(SecurityUtils.getSubject().getPrincipals());
//            }
                break;
        }
        return userId;
    }

    @Override
    public int updatePassword(Integer id, String oldPsw, String newPsw) {
        TOrgUser user = commonTrans.getEntity(TOrgUser.class, id);
        oldPsw = MD5Utils.encryptPassword(oldPsw);
        if (!user.getPassword().equals(oldPsw)) {
            return 0;// 旧密码不对
        }
        newPsw = MD5Utils.encryptPassword(newPsw);
        user.setPassword(newPsw);
        commonTrans.updateEntitie(user);
        return 1;
    }

    @Override
    public Map<String, Object> getEmploy(Integer userId) {
        Map<String, Object> entity = new HashMap<>();
        TOrgUser user = commonTrans.getEntity(TOrgUser.class, userId);

        entity.put("id", user.getId());
        entity.put("photo", user.getPhoto());
        entity.put("userType", user.getUserType());
        entity.put("realName", user.getRealName());
        entity.put("sex", user.getSex());
        String birthday = "";
        if (user.getBirthday() != null) {
            birthday = DateTimeUtilT.date(user.getBirthday());
        }
        entity.put("birthday", birthday);
        entity.put("mobile", user.getMobile());
        entity.put("email", user.getEmail());
        entity.put("tel", user.getTel());
        entity.put("address", user.getAddress());
        entity.put("detail", user.getDetail());
        entity.put("about", user.getAbout());

        TOrg org = commonTrans.getEntity(TOrg.class, user.getOrgId());
        entity.put("orgName", org.getOrgName());

        return entity;
    }

    @Override
    public int addUser(Map<String, Object> params) {
        String userName = StringUtil.decodeStr((String) params.get("userName"));
        if (memberService.userIsExist(userName))
            return 0;
        String password = StringUtil.decodeStr((String) params.get("password"));
        String realName = (String) params.get("realName");
        Boolean sex = (Boolean) params.get("sex");
        String phone = (String) params.get("mobile");

        Integer orgServeId = Integer.parseInt((String) params.get("serveId"));
        Integer groupId = Integer.parseInt((String) params.get("groupId"));

        UserDTO user = new UserDTO();
        user.setUserName(userName);
        user.setPassword(password);
        user.setMobile(phone);
        user.setRealName(realName);
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setGender(sex);
        user.setRecordDTO(recordDTO);
        
        int userId = memberService.registMember(user);
        if (userId != -1) {
            String userCode = user.getUserCode();
            
            if (orgServeId != 0) {
                int count = (Integer) params.get("count");
                orderService.addServeOrder(userId, orgServeId, AlipayService.createOrderNumber(userCode),
                        (Integer) params.get("chargeMode"), count, "", "", 3, groupId);
            }
        }
        return userId;
    }

    @Override
    public OrgUserDTO getOrgUser(Integer userId) {
        return orgServiceDao.getOrgUser(userId);
    }

    @Override
    public void updateBaseProfile(BaseProfileDTO data) {
        orgUserDao.updateBaseProfile(data);
    }

    @Override
    public boolean updateEmploy(Map<String, Object> employ) {
        TOrgUser user = commonTrans.getEntity(TOrgUser.class, (Integer) employ.get("id"));

        user.setRealName((String) employ.get("realName"));
        user.setSex((Boolean) employ.get("sex"));
        if (employ.get("birthday") != null) {
            user.setBirthday(DateTimeUtilT.date((String) employ.get("birthday")));
        }

        if (employ.containsKey("mobile")) {
            user.setMobile((String) employ.get("mobile"));
            user.setMobileVerified(false);
        }

        user.setTel((String) employ.get("tel"));
        // user.setAddress((String) employ.get("address"));

        user.setAbout((String) employ.get("about"));

        if (employ.containsKey("email")) {
            user.setEmail((String) employ.get("email"));
            user.setEmailVerified(false);
        }

        if (employ.containsKey("detail")) {
            String filterHtml = StringUtil.filterHtml((String) employ.get("detail"));
            String newDetail = ImageUtilV2.regexPathFromHtml(filterHtml);
            user.setDetail(newDetail);// 过滤掉危险脚本并进行对里面的图片进行转换
        }
        if (employ.containsKey("userType")) {
            user.setUserType((Integer) employ.get("userType"));
        }
        if (employ.containsKey("photo")) {
            try {
                user.setPhoto(ImageUtilV2.copyImgFileToUploadFolder((String) employ.get("photo"), "photo"));
            } catch (IOException e) {
                return false;
            }
        }
        user.setModifyDate(new Date());
        return orgUser.updateOrgUser(user); // orgUser.updateOrgUser(user)
    }

    @Override
    public boolean updateOrgUserMobile(int userId, String mobile) {
        if (StringUtils.isBlank(checkMobile(mobile))) {
            TOrgUser user = commonTrans.getEntity(TOrgUser.class, userId);
            user.setMobile(mobile);
            user.setMobileVerified(true); // true 表示已验证
            user.setModifyDate(new Date());
            return orgUser.updateOrgUser(user);
        }
        return false;
    }

    @Override
    public boolean updateOrgUserEmail(int userId, String email) {
        if (StringUtils.isBlank(checkEmail(email))) {
            TOrgUser user = commonTrans.getEntity(TOrgUser.class, userId);
            user.setEmail(email);
            user.setEmailVerified(true); // true 表示已验证
            user.setModifyDate(new Date());
            return orgUser.updateOrgUser(user);
        }
        return false;
    }

    @Override
    public DataResult getUserSecurityData( OrgUserSharingData orgUserSharingData ) {

        DataResult dataResult = new DataResult();
        String mobile = null;
        if (StringUtils.isNotBlank(orgUserSharingData.getMobile()))
            mobile = orgUserSharingData.getMobile().substring(0, 3) + "***"
                    + orgUserSharingData.getMobile().substring(8);
        dataResult.put("mobile", mobile);
        dataResult.put("mobileVerify", orgUserSharingData.getMobileVerified());
        dataResult.put("emailVerify", orgUserSharingData.getEmailVerified());
        dataResult.put("email", orgUserSharingData.getEmail());
        return dataResult;
    }

    @Override
    public boolean isOrgRemainOneAdmin(int orgId) {
        boolean onlyOne = true;
        int adminCount = 0;

        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        params.put("status", 0);

        List<Map<String, Object>> users = commonTrans.findByMap(TOrgUser.class, params);
        for (Map<String, Object> user : users) {
            int userType = (int) user.get("userType");
            if (userType == 0 || userType == 2) {
                adminCount++;
            }
        }

        if (adminCount > 1)
            onlyOne = false;

        return onlyOne;
    }

    @Override
    public boolean commitReport(OrgUserSharingData orgUserSharingData,String text) {
    //TODO  user
//        OrgUserSharingData orgUserSharingData = sessionManageService.getCacheOrgMemberSharingData(user.getId());
        TReport report = new TReport();
        report.setCreateDate(new Date());
        report.setMessage(text);
        report.setContactInformation(orgUserSharingData.getMobile());
        report.setUserId(orgUserSharingData.getId());
        return commonTrans.save(report) > 0;
    }

    @Override
    public List<Integer> getOrgUsersByOrgId(Integer orgId) {
        return orgUserDao.getOrgUsersByOrgId(orgId);
    }
    
    @Override
    public void tobeStoreCheck(LoginUser user) {
    	ShopDTO shop = orgUserDao.getShopByOrgId(user.getOrgId());
    	if(shop != null) {
    		user.setAgentId(AgentType.商铺.getCode());
        	user.setAgentNum(shop.getId());
    	}
    }
    
    @Override @Transactional
    public int createShop(LoginUser user, String shopName) {
    	TOrgUser orgUser = commonTrans.findUniqueByProperty(TOrgUser.class, "userName", user.getUserName());
    	TOrg org = commonTrans.get(TOrg.class, orgUser.getOrgId());
    	ShopDTO shop = new ShopDTO();
    	if(StringUtils.isNotBlank(shopName)) {
    		shop.setShopName(shopName);
    	} else {
    		shop.setShopName(user.getRealName() == null?org.getOrgName():user.getRealName() + "的店铺");
    	}
    	shop.setOrgId(user.getOrgId());
    	shop.setUserId(orgUser.getId());
    	shop.setCreateTime(new Date());
    	shop.setUserName(orgUser.getUserName());
    	shop.setMobile(user.getMobile());
    	shop.setState(ShopState.审核通过.getCode());
    	int s = shopDao.saveShop(shop);
    	adminPO admin = new adminPO();
    	admin.setAgentId(AgentType.商铺.getCode());
    	admin.setAgentNum(shop.getId());
    	admin.setCreateDate(new Date());
    	admin.setUserName(shop.getUserName());
    	admin.setPassword(orgUser.getPassword());
    	admin.setMobile(orgUser.getMobile());
    	admin.setRealName(user.getRealName() == null?org.getOrgName():user.getRealName());
    	admin.setStatus(0);
    	admin.setPhoto(org.getLogo());
    	adminDao.saveAdminUser(admin);
    	adminDao.saveAdminUserRole(admin.getId());
    	return s;
    }
}
