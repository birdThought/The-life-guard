package com.lifeshs.service.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.SensitiveOperationType;
import com.lifeshs.common.constants.common.SensitiveUserType;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.entity.sms.TSms;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.data.AddressDTO;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDetailDTO;
import com.lifeshs.pojo.member.UserOauthDTO;
import com.lifeshs.pojo.member.UserPcLoginDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.VcodeDTO;
import com.lifeshs.pojo.member.commond.TUserInfoDto;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.ServletUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 版权归 TODO
 * 
 * @author duosheng.mo
 * @DateTime 2016年4月20日 上午9:41:03 extends CommonDaoImpl
 */
@Service("memberService")
public class MemberServiceImpl extends CommonServiceImpl implements IMemberService {

    @Autowired
    private Member member;

    @Autowired
    private IMemberDao memberDao;

    @Resource(name = "smsRemindService")
    private SmsRemindService smsRemindService;
    
    @Override
    public int registMember(String userName, String password) {
        if (userIsExist(userName)) {
            return -1;
        }
        String userCode = getUserCode().getMemberCode();
        if (!registHxUser(userCode)) {// 注册环信失败
            return -1;
        }
        // 注册用户
        Integer userId = member.register(userName, password, userCode);
        if (userId != null) {
            // 添加用户个人档案
            UserRecordDTO recordDTO = new UserRecordDTO();
            recordDTO.setUserId(userId);
            memberDao.addUserRecord(recordDTO);
            // 添加用户短信剩余
            try {
                smsRemindService.addMemberSmsRemind(userId, 0L);
            } catch (OperationException e) {
                // TODO 失败了就算了 ( ´_ゝ｀)
                e.printStackTrace();
            }
            
            // 添加用户提醒开关
            memberDao.addUserMeasurementReminder(userId);
            return userId;
        }
        return -1;
    }

    @Override
    public int registMember(UserDTO userDTO) {
        if (userIsExist(userDTO.getUserName())) {
            return -1;
        }
        String userCode = getUserCode().getMemberCode();
        if (!registHxUser(userCode)) {// 注册环信失败
            return -1;
        }
        // 注册用户
        userDTO.setUserCode(userCode);
        Integer userId = member.register(userDTO);
        if (userId != null) {
            userDTO.setId(userId);
            // 添加用户个人档案
            UserRecordDTO recordDTO = userDTO.getRecordDTO();
            if (recordDTO == null) {
                // 如果没有设置用户个人档案，就重新new一个
                recordDTO = new UserRecordDTO();
                userDTO.setRecordDTO(recordDTO);
            }
            recordDTO.setUserId(userId);
            memberDao.addUserRecord(recordDTO);

            // 添加用户短信剩余
            try {
                smsRemindService.addMemberSmsRemind(userId, 0L);
            } catch (OperationException e) {
                // TODO 失败了就算了 ( ´_ゝ｀)
                e.printStackTrace();
            }
            return userId;
        }
        return -1;
    }

    @Override
    public boolean userIsExist(String userName) {
        return member.userIsExist(userName);
    }

    @Override
    public String checkMobile(String mobile) {
        String userId = null;
        if (member.checkMobile(mobile)) {
            UserDTO user = memberDao.getUserByMobile(mobile);
            if (user != null) {
                userId = String.valueOf(user.getId());
            }
        }
        return userId;
    }

    @Override
    public String checkEmail(String email) throws Exception {
        String userId = null;
        if (member.checkEmail(email)) {
            // 查找用户的userId
            List<Map<String, Object>> resList = commonTrans.findByPropertyByMap(TUser.class, "email", email);
            for (Map<String, Object> map : resList) {
                if ((Integer) map.get("status") != UserStatus.logoff.value()) {
                    userId = String.valueOf((Integer) map.get("id"));
                    break;
                }
            }
        }
        return userId;
    }

    @Override
    public String modifyPasswordByUserId(String userId, String password, String ip) {
        UserDTO user = memberDao.getUser(Integer.parseInt(userId));
        // 查找不到用户信息直接返回null
        if (user == null) {
            return null;
        }
        
        String newPassword = MD5Utils.encryptPassword(password);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(newPassword);
        memberDao.updateUser(userDTO);
        
        SensitiveOperationLogDTO log = new SensitiveOperationLogDTO();
        log.setGenerateData(user.getPassword());
        log.setNewData(MD5Utils.encryptPassword(password));
        log.setUserId(user.getId());
        log.setUserType(SensitiveUserType.USER);
        log.setOperationType(SensitiveOperationType.MODIFY_PASSWORD);
        log.setIp(ip);
        member.saveSensitiveLog(log);
        
        return user.getUserName();
    }

    @Override
    public TUserInfoDto getUserInfo(int id) {
        UserDTO user = memberDao.getUser(id);
        TUserInfoDto entity = new TUserInfoDto();
        entity.setId(user.getId());
        entity.setBirthday(user.getRecordDTO().getBirthday());
        entity.setRealName(user.getRealName());
        entity.setMobile(user.getMobile());
        entity.setMobileVerified(user.getMobileVerified());
        entity.setEmail(user.getEmail());
        entity.setEmailVerified(user.getEmailVerified());
        entity.setSex(user.getRecordDTO().getGender());
        entity.setPhoto(user.getPhoto());
        return entity;
    }

    @Override
    public boolean updateMobile(int id, String mobile) {
        if (StringUtils.isBlank(checkMobile(mobile))) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userDTO.setMobile(mobile);
            userDTO.setMobileVerified(true); // true 表示已验证
            memberDao.updateUser(userDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmail(int id, String email) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setEmailVerified(true); // true 表示已验证
        memberDao.updateUser(userDTO);
        return true;
    }

    @Override
    public boolean updatetUserInfo(TUserInfoDto tUser) {
        int userId = tUser.getId();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setRealName(tUser.getRealName());
        String mobile = tUser.getMobile();
        String email = tUser.getEmail();
        if (tUser.getProvince() != null) {
            userDTO.setProvince(tUser.getProvince());
        }
        if (tUser.getCity() != null) {
            userDTO.setCity(tUser.getCity());
        }
        if (tUser.getCountry() != null) {
            userDTO.setCounty(tUser.getCountry());
        }
        if (StringUtils.isNotBlank(mobile)) {
            userDTO.setMobile(tUser.getMobile());
            userDTO.setMobileVerified(false); // false表示未验证
        }
        if (StringUtils.isNotBlank(email)) {
            userDTO.setEmail(tUser.getEmail());
            userDTO.setEmailVerified(false); // false表示未验证
        }
        memberDao.updateUser(userDTO);

        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setBirthday(tUser.getBirthday());
        recordDTO.setGender(tUser.getSex());
        memberDao.updateUserRecord(recordDTO);
        return true;
    }
    
    @Override
    public boolean checkPwd(int userId, String password) {
        UserDTO user = memberDao.getUser(userId);
        if (user == null) {
            return false;
        }
        boolean same = StringUtils.equals(user.getPassword(), MD5Utils.encryptPassword(password));
        return same;
    }

    @Override
    public void setToken(int userId, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setToken(token);
        memberDao.updateUser(userDTO);
    }

    @Override
    public boolean modifyUserPhoto(int userId, String relativePath) {
        UserDTO user = memberDao.getUser(userId);
        // 删除旧头像
        ImageUtilV2.delImg(user.getPhoto());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setPhoto(relativePath);
        memberDao.updateUser(userDTO);
        return true;
    }

    /**
     * @author wenxian.cai
     * @DateTime 2017年1月12日下午4:34:00
     * @serverComment 获取短信记录
     * @param
     */
    @Override
    public PaginationDTO<Map<String, Object>> getSmsRecordList(int userId, int nowPage, int pageSize) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        HashMap<String, Object> params = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("sendId", userId);
        int totalSize = commonTrans.getCount(TSms.class, conditionMap);
        if ((nowPage - 1) * pageSize > totalSize) {
            pagination.setData(list);
            return pagination;
        }
        params.put("sendId", userId);
        pageSize = pageSize <= 0 ? 1 : pageSize;
        // 总页数
        int totalPage = 1;
        if (pageSize <= totalSize) {
            // 如果不能被整除，就数值+1
            totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1;
        }
        if (nowPage <= 0)
            nowPage = 1;
        if (nowPage > totalPage)
            nowPage = totalPage;
        int startIndex = (nowPage - 1) * pageSize; // 开始下标
        params.put("start", startIndex);
        params.put("pageSize", pageSize);
        list = memberDao.selectSmsRecordListByPage(params);
        // 封装数据
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);
        pagination.setNowPage(nowPage);
        pagination.setData(list);
        return pagination;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = OperationException.class)
    public void saveSmsRecord(int sendId, int sendType, String content, String receiveMobile, Boolean status) throws OperationException {
        int s = status ? 0 : 1;
        TSms sms = new TSms();
        sms.setSendId(sendId);
        sms.setSendType(sendType);
        sms.setContent(content);
        sms.setReceiveMobile(receiveMobile);
        sms.setStatus(s);
        sms.setCreateDate(new Date());
        sms.setIp(ServletUtil.getIp(ServletUtil.getCurrentServletRequest()));
        int num = commonTrans.save(sms);
        if (num == 0) {
            throw new OperationException("保存短信记录失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void saveSmsRecord(int sendId, int sendType, String content, List<String> receiveMobileList, boolean status)
            throws OperationException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public  boolean filterIllegalMobileNumber(int sendId, String userName) {

        return memberDao.getIllegalMobileNumber(sendId, userName) <= 10 ? true : false;
    }

    @Override
    public PaginationDTO<Map<String, Object>> getUserSuggestionRecords(Integer userId) {
        PaginationDTO<Map<String, Object>> dto = new PaginationDTO<>();
        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", userId);
        int count = commonTrans.getCount(TReport.class, condition);
        dto.setTotalPage((int) (count % 10 == 0 ? count / 10 : Math.floor(count / 10) + 1));
        dto.setObj(JSONObject.toJSON(commonTrans.findEntityByPageDesc(TReport.class, condition, 1, 10, "createDate")));
        return dto;
    }

    @Override
    public UserRecordDTO getRecord(int userId) {
        return memberDao.getUserRecord(userId);
    }

    @Override
    public void tickDietQuestionnaireOption(int userId, int optionId) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setDietQuestionnaireOptionId(optionId);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void tickSportQuestionnaireOption(int userId, int optionId) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setSportQuestionnaireOptionId(optionId);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void tickMentalHealthQuestionnaireOption(int userId, int optionId) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setMentalHealthQuestionnaireOptionId(optionId);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public UserDTO getUser(Integer id) {
    	
        return memberDao.getUser(id);
    }

    @Override
    public UserDTO getUser(String userName) {
        return memberDao.getUserByUserName(userName);
    }
    
    @Override
    public UserDTO getUserByMobile(String mobile) {
        return memberDao.getUserByMobile(mobile);
    }

    @Override
    public void updateUserBaseInfo(int id, Date birthday, Boolean gender, String realName, String photo,
            AddressDTO address) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(id);
        recordDTO.setBirthday(birthday);
        recordDTO.setGender(gender);
        memberDao.updateUserRecord(recordDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setRealName(realName);
        userDTO.setPhoto(photo);
        userDTO.setCity(address.getCity());
        userDTO.setCounty(address.getCountry());
        userDTO.setProvince(address.getProvince());
        userDTO.setStreet(address.getStreet());
        memberDao.updateUser(userDTO);
    }

    @Override
    public void updateUserHealthWarning(int id, int healthWarningBinaryValue) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setHealthWarning(healthWarningBinaryValue);
        memberDao.updateUser(userDTO);
    }

    @Override
    public void updateUserHealthProduct(int id, int healthProductBinaryValue) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setHealthProduct(healthProductBinaryValue);
        memberDao.updateUser(userDTO);
    }

    @Override
    public void updateUserRecord(int userId, Float height, Float weight, Float waist, Float bust, Float hip) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setHeight(height);
        recordDTO.setWeight(weight);
        recordDTO.setWaist(waist);
        recordDTO.setBust(bust);
        recordDTO.setHip(hip);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public boolean updateUserInfo(UserDTO user) throws OperationException {
        int effectRow = 0;  // SQL实际操作行数
        int expectEffectRow = 0;    // SQL预期操作行数
        int userId = user.getId();

        String realName = user.getRealName();
        String photo = user.getPhoto();
        String city = user.getCity();
        String county = user.getCounty();
        String province = user.getProvince();
        String street = user.getStreet();
        // 判断用户名称是否合法
        if (realName != null && !Toolkits.isVerifyRealName(realName)) {
            throw new OperationException("用户昵称不合法", ErrorCodeEnum.REQUEST);
        }
        if (realName != null || photo != null || city != null || county != null || province != null || street != null) {
            expectEffectRow += 1;   // 预期操作的行数+1
            
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userId);
            userDTO.setRealName(realName);
            userDTO.setPhoto(user.getPhoto());
            userDTO.setCity(user.getCity());
            userDTO.setCounty(user.getCounty());
            userDTO.setProvince(user.getProvince());
            userDTO.setStreet(user.getStreet());
            effectRow += memberDao.updateUser(userDTO);
        }
        
        // 判断是否包含了record信息的更新
        UserRecordDTO recordTmp = user.getRecordDTO();
        if (recordTmp == null) {
            return (effectRow == expectEffectRow);
        }
        
        expectEffectRow += 1;   // 预期操作的行数+1
        // userRecord的临时对象，避免更新多余的内容
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setBirthday(recordTmp.getBirthday());
        recordDTO.setGender(recordTmp.getGender());
        recordDTO.setHeight(recordTmp.getHeight());
        recordDTO.setWeight(recordTmp.getWeight());
        recordDTO.setWaist(recordTmp.getWaist());
        recordDTO.setBust(recordTmp.getBust());
        recordDTO.setHip(recordTmp.getHip());
        effectRow += memberDao.updateUserRecord(recordDTO);
        
        return (effectRow == expectEffectRow);
    }

    @Override
    public void addBloodPressureRecord(int userId, Integer diastolic, Integer systolic, Boolean bloodPressureStatus,
            Integer heartRate, Boolean heartRateStatus) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setDiastolic(diastolic);
        recordDTO.setSystolic(systolic);
        recordDTO.setBloodPressureStatus(bloodPressureStatus);
        recordDTO.setHeartRate(heartRate);
        recordDTO.setHeartRateStatus(heartRateStatus);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void addOxygenRecord(int userId, Integer saturation, Boolean saturationStatus, Integer heartRate, Boolean heartRateStatus) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setSaturation(saturation);
        recordDTO.setSaturationStatus(saturationStatus);
        recordDTO.setHeartRate(heartRate);
        recordDTO.setHeartRateStatus(heartRateStatus);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void addLunginstrumentRecord(int userId, Integer vitalCapacity, Integer vitalCapacityScore) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setVitalCapacity(vitalCapacity);
        recordDTO.setVitalCapacityScore(vitalCapacityScore);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void addBodyfatscaleRecord(int userId, Float WHR, Boolean WHRStatus, Float BMI, Integer BMIRankStatus,
            Float baseMetabolism, Boolean baseMetabolismStatus, Float weight) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        if (WHR != null && WHR != 0) {
            recordDTO.setWHR(WHR);
            recordDTO.setWHRStatus(WHRStatus);
        }
        if (BMI != null && BMI != 0) {
            recordDTO.setBMI(BMI);
            recordDTO.setBMIRankStatus(BMIRankStatus);
        }
        if (baseMetabolism != null && baseMetabolism != 0) {
            recordDTO.setBaseMetabolism(baseMetabolism);
            recordDTO.setBaseMetabolismStatus(baseMetabolismStatus);
        }
        recordDTO.setWeight(weight);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void updateStorkeRiskScore(int userId, int strokeRiskScore) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setStrokeRiskScore(strokeRiskScore);
        memberDao.updateUserRecord(recordDTO);
    }

    /**
     * 获取用户环信账号
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月21日 下午2:56:15
     *
     * @param userId
     */
    public HuanxinUserVO getUserHaveHuanxinAccount(int userId, boolean isOrgUser) {
        UserDTO user = memberDao.getUser(userId);
        HuanxinUserVO account = new HuanxinUserVO();
        account.setUsername(user.getUserCode());
        account.setPassword("123456");

        boolean isOk = huanXinService.registryUserWhenIsNotExist(account);
        if (isOk) {
            return account;
        }
        return null;
    }

    @Override
    public void leaveGroup(int userId) {
        // 关闭开关
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("smsSwitch", 0);
        params.put("pushSwitch", 0);
        memberDao.setUserMeasurementReminderSwitch(params);
        // 移除测量提醒通知
        memberDao.delUserMeasurementReminderDetailByUserId(userId, 2);
        // 移除群组
        memberDao.leaveGroup(userId);
    }

    @Override
    public void addSleepHourRecord(int userId, float hour) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setSleepHour(hour);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public void updateHasWarning(int userId, Integer hasWarning) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setHasWarning(hasWarning);
        memberDao.updateUser(userDTO);
    }

    @Override
    public <T> T findByUsername(Class<T> entityClass, String loginName) {
        T obj = this.commonTrans.findUniqueByProperty(entityClass, "userName", loginName);
        return obj;
    }

    @Override
    public boolean addOauthUser(UserOauthDTO oauth) {
        return memberDao.addOauthUser(oauth) > 0 ? true : false;
    }

    @Override
    public UserDTO getUserByUId(String uId) {
        return memberDao.getUserByUId(uId);
    }
    
    @Override
    public Map<String, Object> getUserByUserId(Integer userId) {
    	return memberDao.getUserByUserId(userId);
    }

    @Override
    public UserPcLoginDTO getUserPcLogin(String token) {
        return memberDao.getUserPcLogin(token);
    }

    @Override
    public boolean addUserPcLogin(UserPcLoginDTO userPcLoginDTO) {
        return memberDao.addUserPcLogin(userPcLoginDTO) > 0 ? true : false;
    }

    @Override
    public boolean addVcode(VcodeDTO vcodeDTO) {
        return memberDao.addVcode(vcodeDTO) > 0 ? true : false;
    }

    @Override
    public void setCorporeityResult(int userId, String corporeityResult, String corporeityScore) {
        // 体质测试结果避免保存空值，以及空格
        if (corporeityResult != null) {
            corporeityResult = corporeityResult.replace(" ", "");
        }
        if (StringUtils.isNotBlank(corporeityResult)) {
            UserRecordDTO recordDTO = new UserRecordDTO();
            recordDTO.setUserId(userId);
            recordDTO.setCorporeityResult(corporeityResult);
            recordDTO.setCorporeityScore(corporeityScore);
            memberDao.updateUserRecord(recordDTO);
        }
    }

    @Override
    public void setSubHealthSymptom(int userId, int subHealthSymptomHealthPoint, int subHealthSymptomScore) {
        UserRecordDTO recordDTO = new UserRecordDTO();
        recordDTO.setUserId(userId);
        recordDTO.setSubHealthSymptomHealthPoint(subHealthSymptomHealthPoint);
        recordDTO.setSubHealthSymptomScore(subHealthSymptomScore);
        memberDao.updateUserRecord(recordDTO);
    }

    @Override
    public UserMeasurementReminderDTO getUserMeasurementReminder(int userId) {
        return memberDao.getUserMeasurementReminder(userId);
    }

    @Override
    public List<UserMeasurementReminderDetailDTO> listUserMeasurementReminderDetail(int userId,int recordStatus) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        params.put("recordStatus",recordStatus);
        return memberDao.listUserMeasurementReminderDetail(params);
    }

    @Override
    public boolean setUserMeasurementReminderSmsSwitch(int userId, int smsSwitch) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        params.put("smsSwitch",smsSwitch);
        return memberDao.setUserMeasurementReminderSmsSwitch(params) == 1;
    }

    @Override
    public boolean setUserMeasurementReminderPushSwitch(int userId, int pushSwitch) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        params.put("pushSwitch",pushSwitch);
        return memberDao.setUserMeasurementReminderPushSwitch(params) == 1;
    }

    @Override
    public boolean addUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO) {
        return memberDao.addUserMeasurementReminderDetail(userMeasurementReminderDetailDTO)==1;
    }

    @Override
    public boolean setUserMeasurementReminderDetailStatus(int reminderDetailId, int reminderStatus) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("reminderDetailId",reminderDetailId);
        params.put("reminderStatus",reminderStatus);
        return memberDao.setUserMeasurementReminderDetailStatus(params) == 1;
    }

    @Override
    public boolean delUserMeasurementReminderDetail(int reminderDetailId, int recordStatus) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("reminderDetailId",reminderDetailId);
        params.put("recordStatus",recordStatus);
        return memberDao.delUserMeasurementReminderDetail(params) == 1;
    }

    @Override
    public boolean updateUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO) {
        return memberDao.updateUserMeasurementReminderDetail(userMeasurementReminderDetailDTO) == 1;
    }

    @Override
    public List<com.lifeshs.pojo.member.address.AddressDTO> listAddress(int userId) {
        return memberDao.listAddress(userId);
    }

    @Override
    public ServiceMessage saveAddress(com.lifeshs.pojo.member.address.AddressDTO addressDTO) {
        ServiceMessage sm = new ServiceMessage(false, null);
        int userId = addressDTO.getUserId();
        String receiverName = addressDTO.getReceiverName();
        String contactNumber = addressDTO.getContactNumber();
        String street = addressDTO.getStreet();
        String errorMsg = verifyAddressData(receiverName, contactNumber, street);
        if (StringUtils.isNotBlank(errorMsg)) {
            sm.setMessage(errorMsg);
            return sm;
        }
        
        // 如果用户选择了默认，就把其它的记录修改为非默认状态
        Boolean selected = addressDTO.getSelected();
        if (selected != null && selected) {
            memberDao.updateUserAddressToUnSelected(userId);
        }
        
        int effectRow = memberDao.saveAddress(addressDTO);
        boolean success = (effectRow == 1);
        sm.setSuccess(success);
        return sm;
    }

    @Override
    public ServiceMessage updateAddress(com.lifeshs.pojo.member.address.AddressDTO addressDTO) {
        ServiceMessage sm = new ServiceMessage(false, null);
        int userId = addressDTO.getUserId();
        String receiverName = addressDTO.getReceiverName();
        String contactNumber = addressDTO.getContactNumber();
        String street = addressDTO.getStreet();
        String errorMsg = verifyAddressData(receiverName, contactNumber, street);
        if (StringUtils.isNotBlank(errorMsg)) {
            sm.setMessage(errorMsg);
            return sm;
        }
        
        // 如果用户选择了默认，就把其它的记录修改为非默认状态
        Boolean selected = addressDTO.getSelected();
        if (selected != null && selected) {
            memberDao.updateUserAddressToUnSelected(userId);
        }
        
        int effectRow = memberDao.updateAddress(addressDTO);
        boolean success = (effectRow == 1);
        sm.setSuccess(success);
        return sm;
    }
    
    /**
     *  验证地址信息是否合法
     *  @author yuhang.weng 
     *  @DateTime 2017年6月15日 上午10:32:41
     *
     *  @param receiverName
     *  @param contactNumber
     *  @param street
     *  @return 错误信息
     */
    private String verifyAddressData(String receiverName, String contactNumber, String street) {
        // 判断收货人长度不能超过20个字符
        if (StringUtils.isNotBlank(receiverName) && !Toolkits.isVerifyReciverName(receiverName)) {
            return String.format(Error.UNVERIFIY, "收货人");
        }
        // 判断街道长度不能超过60个字符
        if (StringUtils.isNotBlank(street) && street.length() > 60) {
            return String.format(Error.LENGTH, "街道", 60);
        }
        // 判断联系电话合法
        if (StringUtils.isNotBlank(contactNumber) && !Toolkits.verifyPhone(contactNumber)) {
            return String.format(Error.UNVERIFIY, "联系电话");
        }
        return null;
    }

    @Override
    public boolean removeAddress(int id, int userId) {
        int effectRow = memberDao.removeAddress(id, userId);
        return (effectRow == 1);
    }

    @Override
    public UserDTO getUserByOpenId(String openId) {
        return memberDao.getUserByOpenId(openId);
    }

    @Override
    public int updateOpenId(int userId, String openId) throws OperationException {
        try {
            return memberDao.updateOpenId(userId, openId);
        } catch (Exception e) {
            throw new OperationException("更新用户openId失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void updateBusinessId(int userId, int businessId) throws OperationException {
        UserDTO user = new UserDTO();
        user.setId(userId);
        user.setBusinessId(businessId); // 渠道商ID
        user.setJoinDate(new Date());   // 引入日期
        int result = memberDao.updateUser(user);
        if (result == 0) {
            throw new OperationException("更新用户渠道商id失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public boolean isMobileExist(String mobile) {
        UserPO user = memberDao.findUserByVerifyMobile(mobile);
        if (user == null) {
            return false;
        }
        return true;
    }

	@Override
	public UserDTO getUserByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return memberDao.getUserByUserNo(userNo);
	}

	@Override
	public AdminUserDTO getAdminUserUserNo(String userNo) {
		// TODO Auto-generated method stub
		return memberDao.getAdminUserUserNo(userNo);
	}

	@Override
	public OrgUserDTO getOrgUserNo(String userNo) {
		// TODO Auto-generated method stub
		return memberDao.getOrgUserNo(userNo);
	}
}