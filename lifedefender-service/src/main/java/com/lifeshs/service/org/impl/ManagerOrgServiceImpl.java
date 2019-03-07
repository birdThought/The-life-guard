package com.lifeshs.service.org.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.OrgStatus;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.common.exception.org.AuthorityException;
import com.lifeshs.dao.data.IAreaDao;
import com.lifeshs.dao.org.manage.OrgManageDao;
import com.lifeshs.dao1.data.MeasureSiteDao;
import com.lifeshs.dao1.org.OrgDao;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.OrgPO;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.data.MeasureSite;
import com.lifeshs.pojo.org.OrgRegisterDTO;
import com.lifeshs.pojo.org.bank.BankInfoDTO;
import com.lifeshs.pojo.org.management.ManagementVO;
import com.lifeshs.pojo.org.management.OrgDetailVO;
import com.lifeshs.pojo.org.management.OrgServiceAndMemberDO;
import com.lifeshs.pojo.org.management.OrgTreeVO;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.impl.user.OrgUser;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.member.memberCountVo;

/**
 * 机构服务类
 * 
 * @author duosheng.mo
 * @DateTime 2016年5月23日 下午4:18:47
 */
@Service("orgService")
public class ManagerOrgServiceImpl extends CommonServiceImpl implements IManagerOrgService {

    @Autowired
    ManagerOrg managerOrg;

    @Autowired
    OrgUser orgUser;

    @Autowired
    OrgManageDao orgManageDao;

    @Resource(name = "orgDao")
    private OrgDao orgDao;

    @Autowired
    IAreaDao areaDao;
    
    @Autowired
    private IDataAreaService areaService;
    
    @Autowired
    MeasureSiteDao  measureSiteDao;

    /**
     * 非事务形式注册,里面嵌套带有事务注册方法
     * 
     * @param orgName
     * @param userName
     * @param password
     * @param type
     * @param orgType
     * @return
     */
    @Override
    public int registOrg(String orgName, String userName, String password, Integer type, String orgType) {
        // 添加机构
        TOrg torg = getInitialOrg(orgName, managerOrg.getOrgCode(), 0);
        torg.setType(type);
        torg.setOrgType(orgType);
        managerOrg.addOrg(torg);

        // 添加机构初始用户
        TOrgUser torgUser = getInitialOrgUser(userName, password, torg.getId(), orgUser.getUserCode());
        registHxUser(torgUser.getUserCode());
        if (type == 2)
            torgUser.setUserType(2);// 个人机构下为管理员+服务师
        commonTrans.save(torgUser);
        return torg.getId();
    }
    
    /**
     * 非事务形式注册,里面嵌套带有事务注册方法
     * 
     * @param orgName
     * @param userName
     * @param password
     * @param type
     * @param orgType
     * @return
     */
    @Override
    public int registOrg(TOrg org, TOrgUser user) {
        // 添加机构
        org.setOrgCode(managerOrg.getOrgCode());
        
        managerOrg.addOrg(org);
        
        String orgUserUserNo = AgentConstant.AGENT_USER_TYPE_O + org.getId();
        
        org.setUserNo(orgUserUserNo);
        //执行修改方法
        managerOrg.updateOrg(org);

     // 添加机构初始用户
        TOrgUser torgUser = getInitialOrgUser(user.getUserName(), user.getPassword(), org.getId(), orgUser.getUserCode());
        registHxUser(torgUser.getUserCode());
        if (org.getType() == 2){
            torgUser.setUserType(2);// 个人机构下为管理员+服务师
        }
        
        torgUser.setMobile(org.getContactInformation());//手机号码
        torgUser.setParentId(orgUserUserNo);
        commonTrans.save(torgUser);
        
        torgUser.setUserNo(AgentConstant.AGENT_USER_TYPE_O + torgUser.getId());
        commonTrans.updateEntitie(torgUser);
        return org.getId();
    }


    @Override
    public int registOrg(OrgRegisterDTO registerDTO){
        TOrg torg = new TOrg();
        torg.setType(registerDTO.getType());
        torg.setOrgType(registerDTO.getOrgType());
        torg.setOrgName(registerDTO.getOrgName());
        torg.setOrgCode(managerOrg.getOrgCode());
        torg.setParent(0);
        torg.setOrgVerified(0);
        torg.setTel(registerDTO.getMobile());
//        torg.setContactInformation(registerDTO.getAddress());
        torg.setBusinessLicense(registerDTO.getBusinessPic1());
        torg.setBusinessLicenseTwo(registerDTO.getBusinessPic2());
        torg.setProvince(registerDTO.getProvince());
        torg.setCity(registerDTO.getCity());
        torg.setDistrict(registerDTO.getDistrict());
        torg.setWorkField(registerDTO.getWorkField());
        torg.setBusinessLicenseNumber(registerDTO.getBusinessLicenseNumber());
        //测试版本设置为正常状态，正式版本改为待审核状态3 todo
        torg.setStatus(0);
        torg.setStreet(registerDTO.getAddress());
        torg.setLongitude(registerDTO.getLongitude());
        torg.setLatitude(registerDTO.getLatitude());
        torg.setAbout(registerDTO.getAbout());
        torg.setCreateDate(new Date());
        torg.setLogo(registerDTO.getLogo());
        torg.setParentId(registerDTO.getParentId());
        
        // 判断是否携带有法人信息与银行开户信息
        String bankAccount = registerDTO.getBankAccount();
        String bankDistrict = registerDTO.getBankDistrict();
        String bankBranch = registerDTO.getBankBranch();
        String legalPerson = registerDTO.getLegalPerson();
        Boolean legalPersonGender = registerDTO.getLegalPersonGender();
        String legalPersonIdCard = registerDTO.getLegalPersonIdCard();
        String newPersonPicOne = registerDTO.getLegalPersonPicOne();
        String newPersonPicTwo = registerDTO.getLegalPersonPicTwo();
        if (bankAccount != null) {
            torg.setBankAccount(bankAccount);
        }
        if (bankDistrict != null) {
            torg.setBankDistrict(bankDistrict);
        }
        if (bankBranch != null) {
            torg.setBankBranch(bankBranch);
        }
        if (legalPerson != null) {
            torg.setLegalPerson(legalPerson);
        }
        if (legalPersonGender != null) {
            torg.setLegalPersonGender(legalPersonGender);
        }
        if (legalPersonIdCard != null) {
            torg.setLegalPersonIdCard(legalPersonIdCard);
        }
        if (newPersonPicOne != null) {
            torg.setLegalPersonPicOne(newPersonPicOne);
        }
        if (newPersonPicTwo != null) {
            torg.setLegalPersonPicTwo(newPersonPicTwo);
        }
        
        managerOrg.addOrg(torg);
        
        //执行修改方法
        torg.setUserNo(AgentConstant.AGENT_USER_TYPE_O + torg.getId());
        managerOrg.updateOrg(torg);
        
        return torg.getId();
    }

    @Override
    public int registOrgUser(String userName, String password, Integer orgId, int type, String mobile){
        // 添加机构初始用户
        TOrgUser torgUser = getInitialOrgUser(userName, password, orgId, orgUser.getUserCode());
        registHxUser(torgUser.getUserCode());
        if (type == 2)
            torgUser.setUserType(2);// 个人机构下为管理员+服务师
        
        torgUser.setMobile(mobile);
        int result = commonTrans.save(torgUser);
        if (result < 0) {
            return  0;
        }
        
        torgUser.setUserNo(AgentConstant.AGENT_USER_TYPE_O + torgUser.getId());
        commonTrans.updateEntitie(torgUser);
        
        return torgUser.getId();
    }


    @Override
    public Integer registChildOrg(ManagementVO orgVO, Integer parent) {
        TOrg org = getInitialOrg(orgVO.getOrgName(), managerOrg.getOrgCode(), parent);
        org = managementVOToTOrg(org, orgVO);
        managerOrg.addOrg(org);
        TOrgUser user = getInitialOrgUser(orgVO.getUserName(), orgVO.getPassword(), org.getId(), orgUser.getUserCode());
        if (!registHxUser(user.getUserCode())) {
            commonTrans.delete(org);
            return null;
        }
        if (orgUser.addUser(user))
            return org.getId();
        return null;
    }

    @Override
    public boolean orgIsExist(String orgName) {
        return managerOrg.orgIsExist(orgName);
    }

    @Override
    public Integer getTheAmountOfChildManagement(Integer orgId) {
        return orgManageDao.countChildOrg(orgId);
    }

    @Override
    public OrgServiceAndMemberDO getTheAmountOfChildService(Integer orgId) {
        return orgManageDao.countOrgChildServiceAndMember(orgId);
    }

    @Override
    public TOrg getOrgMessage(Integer orgId) {
        return commonTrans.getEntity(TOrg.class, orgId);
    }

    @Override
    public List<Map<String, Object>> getChildOrgMessage(Integer parentId) {

        List<Map<String, Object>> datas = new ArrayList<>();

        List<TOrg> orgs = commonTrans.findByProperty(TOrg.class, "parent", parentId);

        for (TOrg org : orgs) {

            /** 只获取管理机构信息 */
            if (org.getType().intValue() == 0) {

                String contactInformation = org.getContactInformation();
                if (StringUtils.isBlank(contactInformation)) {
                    contactInformation = "暂未填写";
                }

                Map<String, Object> data = new HashMap<>();
                data.put("id", org.getId());
                data.put("orgName", org.getOrgName());
                data.put("contact", org.getContacts());
                data.put("contactNumber", contactInformation);
                data.put("type", org.getType());

                List<OrgTreeVO> trees = orgManageDao.selectChildOrgTreeByParentId(org.getId());

                Integer amountOfManagement = 0;
                Integer amountOfService = 0;

                for (OrgTreeVO tree : trees) {
                    switch (tree.getType()) {
                    case 0:
                        amountOfManagement++;
                        break;
                    case 1:
                        amountOfService++;
                        break;
                    }
                }

                data.put("amountOfManagement", amountOfManagement);
                data.put("amountOfService", amountOfService);

                Boolean hasNext = true;
                if (amountOfManagement == 0 && amountOfService == 0) {
                    hasNext = false;
                }
                data.put("hasNext", hasNext);

                datas.add(data);
            }

        }

        return datas;
    }

    @Override
    public List<Map<String, Object>> getChildOrgServiceMessage(Integer parentId) {

        List<Map<String, Object>> datas = new ArrayList<>();

        List<OrgTreeVO> trees = orgManageDao.selectChildOrgTreeByParentId(parentId);

        for (OrgTreeVO tree : trees) {
            if (tree.getType().intValue() == 1) {
                Map<String, Object> data = new HashMap<>();
                String orgName = StringUtils.isNotBlank(tree.getOrgName()) ? tree.getOrgName() : "暂未填写";
                String contact = StringUtils.isNotBlank(tree.getContact()) ? tree.getContact() : "暂未填写";
                String contactNumber = StringUtils.isNotBlank(tree.getContactInformation())
                        ? tree.getContactInformation() : "暂未填写";

                data.put("id", tree.getId());
                data.put("orgName", orgName);
                data.put("contact", contact);
                data.put("contactNumber", contactNumber);
                data.put("amountOfMemeber", tree.getAmountOfChildMember());
                datas.add(data);
            }
        }

        return datas;
    }

    @Override
    public Object getOrganizeTree(Integer parentId) {

        List<Map<String, Object>> datas = new ArrayList<>();

        List<OrgTreeVO> trees = orgManageDao.selectChildOrgTreeByParentId(parentId);

        for (OrgTreeVO tree : trees) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", tree.getId());
            data.put("name", tree.getOrgName());
            data.put("downJgCount", tree.getAmountOfChildOrg());
            data.put("memberCount", tree.getAmountOfChildMember());
            data.put("type", tree.getType());

            datas.add(data);
        }

        Map<String, Object> obj = new HashMap<>();
        obj.put("data", datas);
        return obj;
    }

    @Override
    public OrgDetailVO getOrgDetailById(Integer orgId) {
        return orgManageDao.selectOrgDetailById(orgId);
    }

    @Override
    public void isThisOrgBelongToAnotherOne(Integer fatherOrgId, Integer childOrgId) throws AuthorityException {
        Integer result = orgManageDao.isThisOrgBelongToAnotherOne(fatherOrgId, childOrgId);
        if (result == 0) {
            throw new AuthorityException("您无权查看该信息");
        }
    }

    /**
     * 获取一个初始化的企业
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月5日 下午3:06:06
     *
     * @param orgName
     * @param orgCode
     * @return
     */
    private TOrg getInitialOrg(String orgName, String orgCode, Integer parent) {
        TOrg org = new TOrg();
        org.setOrgName(orgName);
        org.setStatus(OrgStatus.normal.value());
        org.setOrgVerified(0);
        org.setOrgCode(orgCode);
        org.setCreateDate(new Date());
        org.setParent(parent);
        return org;
    }

    /**
     * 把VO的数据转入实体类TOrg中
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月5日 下午4:45:10
     *
     * @param org
     * @param orgVO
     * @return
     */
    private TOrg managementVOToTOrg(TOrg org, ManagementVO orgVO) {

        org.setOrgType(orgVO.getOrgType());
        org.setContacts(orgVO.getContact());
        org.setContactInformation(orgVO.getContactInformation());
        org.setProvince(orgVO.getProvince());
        org.setCity(orgVO.getCity());
        org.setDistrict(orgVO.getDistrict());
        org.setStreet(orgVO.getStreet());
        org.setBusinessLicense(orgVO.getBusinessLicense());
        org.setType(orgVO.getType());

        return org;
    }

    /**
     * 获取一个初始化的企业用户
     * 
     * @author yuhang.weng
     * @DateTime 2016年9月5日 下午2:36:26
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @param orgId
     *            机构ID
     * @param userCode
     *            用户code编码
     * @return
     */
    private TOrgUser getInitialOrgUser(String userName, String password, Integer orgId, String userCode) {
        TOrgUser user = new TOrgUser();
        user.setUserName(StringUtil.decodeStr(userName));
        user.setPassword(MD5Utils.encryptPassword(StringUtil.decodeStr(password)));
        user.setOrgId(orgId);
        user.setParentId(AgentConstant.AGENT_USER_TYPE_O + orgId);
        user.setUserCode(userCode);
        user.setSex(false); // 默认性别为女
        user.setMobileVerified(false);
        user.setEmailVerified(false);
        user.setStatus(UserStatus.normal.value());
        user.setUserType(0); // 默认管理员
        user.setCreateDate(new Date());
        
        return user;
    }

    @Override
    public void updateOrgDetails(Map<String, Object> params, Integer orgId) {
        TOrg org = commonTrans.getEntity(TOrg.class, orgId);
        int orgVerified = org.getOrgVerified();
        if (orgVerified != 2) {
            return;
        }
        
        params.put("modifyDate", new Date());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", "t_org");
        if (params.containsKey("logo")) {
            try {
                params.put("logo", ImageUtilV2.copyImgFileToUploadFolder((String) params.get("logo"), "logo"));
            } catch (IOException e) {
            }
        }
        if (params.containsKey("businessLicense")) {
            try {
                params.put("businessLicense",
                        ImageUtilV2.copyImgFileToUploadFolder((String) params.get("businessLicense"), "license"));
            } catch (IOException e) {
            }
            // params.remove("businessLicense");
        }
        if (params.containsKey("detail")) {
            params.put("detail", ImageUtilV2.regexPathFromHtml(StringUtil.filterHtml((String) params.get("detail")))); // 过滤掉危险脚本并进行对里面的图片进行转换
        }
//        params.put("orgVerified", 0);
        map.put("column", params);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", orgId);
        map.put("condition", condition);
        commonTrans.updateByMap(map);
    }

    @Override
    public TOrg getOrgDetail(int orgId) {
        return orgManageDao.getOrgDetail(orgId);
    }

    @Override
    public boolean updateOrg(OrgRegisterDTO org) {
        return orgManageDao.updateOrg(org) > 0 ? true : false;
    }

    @Override
    public OrgPO getOrg(int id) {
        return orgDao.getOrg(id);
    }

    @Override
    public OrgProfileDTO getOrgInfo(Integer orgId) {
        OrgProfileDTO org = orgDao.getOrgInfo(orgId);
        org.setProvince(areaDao.getProvinceName(org.getProvinceCode() + "0000"));
        org.setCity(areaDao.getCityName(org.getProvinceCode() + org.getCityCode() + "00"));
        if ("00".equals(org.getCityCode())) {
            org.setCityCode("01");
        }
        org.setDistrict(areaDao.getDistrictName(org.getProvinceCode() + org.getCityCode() + org.getDistrictCode()));
        return org;
    }

    @Override
    public boolean updateOrgInfo(OrgProfileDTO org) {
        int result = orgDao.updateOrgInfo(org);
        if (result < 1) {
            return false;
        }
        return true;
    }


    @Override
    public boolean updateOrgBankInfo(BankInfoDTO data) {
        return orgDao.updateOrgBankInfo(data) == 1;
    }

	@Override
	public Integer updataOrgById(Integer orgId, String netPath, Date startTime, Date endTime, Integer statusMap) {
		
		return orgManageDao.updataOrgById(orgId,netPath,startTime,endTime,statusMap);
	}

	@Override
	public Integer addDataSize(Integer siteId ,Integer orgId, String netPath, String startTime, String endTime, String statusMap) {
		OrgPO orgPO = orgDao.getOrg(orgId);
		AreaVO areaVO = areaService.getAreaNameByCode(orgPO.getProvince(), orgPO.getCity(), orgPO.getDistrict());
		
		MeasureSite measureSite = new MeasureSite();
		measureSite.setName(orgPO.getOrgName());
		measureSite.setLatitude(Double.parseDouble(orgPO.getLatitude()));
		measureSite.setLongitude(Double.parseDouble(orgPO.getLongitude()));
		measureSite.setCityAreaCode(orgPO.getProvince()+orgPO.getCity()+orgPO.getDistrict());
		measureSite.setLogo(orgPO.getLogo());
		measureSite.setBanner(netPath);
		measureSite.setPhoneNumber(orgPO.getTel());
		measureSite.setCreateDate(orgPO.getCreateDate());
		measureSite.setFree(1);
		measureSite.setAddress(areaVO.getProvinceName()+areaVO.getCityName()+areaVO.getDistrictName()+orgPO.getStreet());
		measureSite.setOrgId(orgPO.getId());
		measureSite.setStatusMap(statusMap);
		
		if(siteId != null){
			measureSiteDao.updateDataSize(siteId,measureSite);			
			measureSiteDao.delDataSizeOpeningTime(siteId);
			return measureSiteDao.addDataSizeOpeningTime2(siteId,startTime,endTime);
		}else{
			measureSiteDao.addDataSize(measureSite);
			if(startTime!=null && startTime.length() !=0 && endTime !=null && endTime.length() !=0){
				Integer addDataSizeOpeningTime = measureSiteDao.addDataSizeOpeningTime(measureSite.getId(),startTime,endTime);
			}
			
			return 1;
		}
		
		
		
	}
	
}
