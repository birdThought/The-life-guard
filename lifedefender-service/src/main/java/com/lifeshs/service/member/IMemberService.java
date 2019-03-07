package com.lifeshs.service.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.pojo.data.AddressDTO;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
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

/**
 * 版权归 TODO
 * 
 * @author duosheng.mo
 * @DateTime 2016年4月20日 上午9:41:03
 */

public interface IMemberService {

    /**
     * 用户是否已存在（和机构用户一起判断）
     * 
     * @author dengfeng
     * @DateTime 2016-6-2 上午10:44:39
     *
     * @param userName
     * @return
     * @throws Exception
     */
    boolean userIsExist(String userName);

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月13日 上午10:19:19
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     */
    int registMember(String userName, String password);

    int registMember(UserDTO userDTO);

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-13 下午03:05:00
     * @serverComment 根据手机判断用户是否存在(手机必须是已验证）
     *
     * @param mobile
     *            手机号
     * @return userId
     * @throws Exception
     */
    String checkMobile(String mobile);

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-13 下午02:36:27
     * @serverComment 根据邮箱判断用户是否存在（邮箱必须是已验证邮箱）
     *
     * @param email
     *            邮箱
     * @return userId
     */
    String checkEmail(String email) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月15日 上午11:44:21
     * @serverComment 检查用户密码是与参数password相同
     *
     * @param userId
     *            用户ID
     * @param password
     *            密码
     * @return
     */
    boolean checkPwd(int userId, String password);

    /**
     * 
     * @author yuhang.weng
     * @DateTime 2016年6月8日 上午10:00:40
     * @serverComment 重置密码
     *
     * @param userId 用户ID
     * @param password 新密码
     * @param ip ip
     * @return
     */
    String modifyPasswordByUserId(String userId, String password, String ip);

    /**
     * <p>
     * 根据个人信息界面需要展示的信息获取用户信息
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:24:15
     *
     * @param userId
     * @return
     * @throws Exception
     */
    TUserInfoDto getUserInfo(int userId);

    /**
     * <p>
     * 修改个人信息界面的信息
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:37:29
     *
     * @param tUser
     * @return
     */
    boolean updatetUserInfo(TUserInfoDto tUser);

    /**
     * <p>
     * 修改用户手机号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:24:20
     *
     * @param id
     * @param mobile
     * @return
     */
    boolean updateMobile(int id, String mobile);

    /**
     * <p>
     * 修改用户邮箱
     * 
     * @author dachang.luo
     * @DateTime 2016年6月8日下午4:25:26
     *
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    boolean updateEmail(int id, String email) throws Exception;

    /**
     * 设置用户的token
     * 
     * @author dachang.luo
     * @DateTime 2016年7月1日 下午4:12:37
     * @serverComment 服务注解
     *
     * @param userId
     * @param token
     * @return
     */
    void setToken(int userId, String token);

    /**
     * 修改用户头像(相对路径存放)
     * </p>
     * 并且如果原来保存有头像，就会删除掉原图
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月5日 上午9:48:14
     *
     * @param userId
     * @param relativePath
     * @return
     */
    boolean modifyUserPhoto(int userId, String relativePath);

    /**
     * @author wenxian.cai
     * @DateTime 2017年1月3日下午5:29:02
     * @serverComment 获取短信记录列表
     * @param
     */
    PaginationDTO<Map<String, Object>> getSmsRecordList(int userId, int pageIndex, int pageSize);

    /**
     * @author wenxian.cai
     * @DateTime 2017年1月13日上午10:14:47
     * @serverComment 存储短信记录
     * @param
     */
    void saveSmsRecord(int sendId, int sendType, String content, String receiveMobile, Boolean status) throws OperationException;
    
    /**
     *  存储短信记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月28日 上午10:52:46
     *
     *  @param sendId 发送者id
     *  @param sendType 发送类型
     *  @param content 内容
     *  @param receiveMobileList 接收用户号码列表
     *  @param status 发送状态
     *  @throws OperationException
     */
    void saveSmsRecord(int sendId, int sendType, String content, List<String> receiveMobileList, boolean status) throws OperationException;

    /**
     * @author wenxian.cai
     * @DateTime 2017-4-1
     * @param sendId
     * @return
     * 判断用户当天的短信发送数量是否超出范围
     */
    boolean filterIllegalMobileNumber(int sendId, String userName);

    /**
     * 获取用户的意见反馈记录
     * 
     * @param userId
     * @return
     */
    PaginationDTO<Map<String, Object>> getUserSuggestionRecords(Integer userId);

    /**
     * 获取用户个人档案
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 上午11:38:40
     *
     * @param userId
     * @return
     */
    UserRecordDTO getRecord(int userId);

    /**
     * 获取一个用户
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午2:52:00
     *
     * @param id
     * @return
     */
    UserDTO getUser(Integer id);

    /**
     * 获取一个用户
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午3:46:43
     *
     * @param userName
     * @return
     */
    UserDTO getUser(String userName);
    
    /**
     *  获取用户id信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月20日 上午9:25:15
     *
     *  @param userName   getUserByUserNo
     *  @return
     */
    UserDTO getUserByUserNo(String userNo);
    
    
    
    /*
     * 登录判断推荐人ID
     * 用户 判断 t_admin_user 表   UserNo
     * 
     */
    AdminUserDTO getAdminUserUserNo(String userNo);
    
    /*
     * 推荐人编号
     * 用户 判断 t_org 表   UserNo
     * 
     */
    OrgUserDTO getOrgUserNo(String userNo);
    
    
    
    /**
     *  通过手机获取一个用户
     *  @author yuhang.weng 
     *	@DateTime 2017年4月14日 上午11:13:22
     *
     *  @param mobile
     *  @return
     */
    UserDTO getUserByMobile(String mobile);

    /**
     * 勾选饮食问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:47:38
     *
     * @param userId
     * @param optionId
     */
    void tickDietQuestionnaireOption(int userId, int optionId);

    /**
     * 勾选运动问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:47:41
     *
     * @param userId
     * @param optionId
     */
    void tickSportQuestionnaireOption(int userId, int optionId);

    /**
     * 勾选心理健康问卷选项
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午1:47:43
     *
     * @param userId
     * @param optionId
     */
    void tickMentalHealthQuestionnaireOption(int userId, int optionId);

    /**
     * 更新用户基本信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午3:33:32
     *
     * @param id
     * @param birthday
     * @param gender
     * @param realName
     * @param photo
     * @param address
     */
    void updateUserBaseInfo(int id, Date birthday, Boolean gender, String realName, String photo, AddressDTO address);

    /**
     * 更新用户healthWarning字段
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午4:01:44
     *
     * @param id
     * @param healthWarningBinaryValue
     */
    void updateUserHealthWarning(int id, int healthWarningBinaryValue);

    /**
     * 更新用户healthProduct字段
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午4:01:31
     *
     * @param id
     * @param healthProductBinaryValue
     */
    void updateUserHealthProduct(int id, int healthProductBinaryValue);

    /**
     * 修改用户个人档案信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月17日 下午5:01:50
     *
     * @param userId
     * @param height
     * @param weight
     * @param waist
     * @param bust
     * @param hip
     */
    void updateUserRecord(int userId, Float height, Float weight, Float waist, Float bust, Float hip);

    /**
     *  更新用户个人信息
     *  @author yuhang.weng 
     *  @DateTime 2017年8月15日 上午11:58:49
     *
     *  @param user
     *  @return
     *  @throws OperationException
     */
    boolean updateUserInfo(UserDTO user) throws OperationException;
    
    /**
     *  添加血压记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午2:33:27
     *
     *  @param userId
     *  @param diastolic
     *  @param systolic
     *  @param bloodPressureStatus
     *  @param heartRate
     *  @param heartRateStatus
     */
    void addBloodPressureRecord(int userId, Integer diastolic, Integer systolic, Boolean bloodPressureStatus,
            Integer heartRate, Boolean heartRateStatus);

    /**
     * 添加血氧记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午2:33:52
     *
     * @param userId
     * @param saturationStatus
     * @param heartRate 心率
     * @param heartRateStatus 心率是否异常
     */
    void addOxygenRecord(int userId, Integer saturation, Boolean saturationStatus, Integer heartRate, Boolean heartRateStatus);

    /**
     * 添加肺活仪分数记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午2:37:44
     *
     * @param userId
     * @param vitalCapacityScore
     */
    void addLunginstrumentRecord(int userId, Integer vitalCapacity, Integer vitalCapacityScore);

    /**
     * 添加体脂秤记录
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午2:37:57
     *
     * @param userId
     * @param WHRStatus
     * @param BMIRankStatus
     * @param baseMetabolismStatus
     * @param weight
     */
    void addBodyfatscaleRecord(int userId, Float WHR, Boolean WHRStatus, Float BMI, Integer BMIRankStatus,
            Float baseMetabolism, Boolean baseMetabolismStatus, Float weight);
    
    /**
     *  更新中风风险分数
     *  @author yuhang.weng 
     *  @DateTime 2017年8月18日 下午4:07:49
     *
     *  @param userId
     *  @param strokeRiskScore
     */
    void updateStorkeRiskScore(int userId, int strokeRiskScore);
    
    /**
     *  添加睡眠记录
     *  @author yuhang.weng 
     *	@DateTime 2017年3月21日 下午2:20:14
     *
     *  @param userId
     *  @param hour
     */
    void addSleepHourRecord(int userId, float hour);

    HuanxinUserVO getUserHaveHuanxinAccount(int userId, boolean isOrgUser);

    /**
     * 用户退出群组
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午4:57:40
     *
     * @param userId
     */
    void leaveGroup(int userId);
    
    /**
     *  更新用户异常项
     *  @author yuhang.weng 
     *	@DateTime 2017年3月21日 下午2:44:10
     *
     *  @param userId
     *  @param hasWarning
     */
    void updateHasWarning(int userId, Integer hasWarning);

    /**
     *  设置用户体质测量结果
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午2:03:04
     *
     *  @param userId
     *  @param corporeityResult
     */
    void setCorporeityResult(int userId, String corporeityResult, String corporeityScore);
    
    /**
     *  设置用户心理健康结果
     *  @author yuhang.weng 
     *  @DateTime 2017年3月23日 下午1:49:55
     *
     *  @param json
     *  @return
     */
    void setSubHealthSymptom(int userId, int subHealthSymptomHealthPoint, int subHealthSymptomScore);

    /**
     * 用户名查询用户
     * @param entityClass 实体类 类型
     * @param loginName 登录名
     * @param <T>
     * @return
     */
    public <T> T findByUsername(Class<T> entityClass, String loginName);

    /**
     * 添加第三方登录信息
     * @param oauth
     * @return
     */
    boolean addOauthUser(UserOauthDTO oauth);

    /**
     * 根据openid获取用户
     * @param uId
     * @return
     */
    UserDTO getUserByUId(String uId);
    
    /**
     * 根据id获取用户(关联 表: t_user_oauth)
     * @param userId
     * @return
     * @author liu
     * @时间 2018年12月26日 上午11:34:51
     * @remark
     */
    Map<String, Object> getUserByUserId(Integer userId);

    /**
     * @Description: 获取扫一扫登录pc信息
     * @author: wenxian.cai
     * @create: 2017/4/18 10:28
     */
    UserPcLoginDTO getUserPcLogin(String token);

    /**
     * @Description: 添加扫一扫登录pc信息
     * @author: wenxian.cai
     * @create: 2017/4/18 10:29
     */
    boolean addUserPcLogin(UserPcLoginDTO userPcLoginDTO);

    /**
     * @Description: 添加验证码信息
     * @author: wenxian.cai
     * @create: 2017/4/18 14:42
     */
    boolean addVcode(VcodeDTO vcodeDTO);
    
    /**
     *获取提醒设置
     * @param userId
     * @return
     */
    UserMeasurementReminderDTO getUserMeasurementReminder(int userId);

    /**
     *获取提醒项目列表
     * @param userId
     * @return
     */
    List<UserMeasurementReminderDetailDTO> listUserMeasurementReminderDetail(int userId,int recordStatus);

    /**
     * 设置短信提醒
     * @param userId
     * @param status
     * @return
     */
    boolean setUserMeasurementReminderSmsSwitch(int userId,int status);

    /**
     * 设置推送提醒
     * @param userId
     * @param status
     * @return
     */
    boolean setUserMeasurementReminderPushSwitch(int userId,int status);

    /**
     * 新增提醒项目
     * @param userMeasurementReminderDetailDTO
     * @return
     */
    boolean addUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO);

    /**
     * 设置提醒项目状态
     * @param reminderDetailId
     * @param status
     * @return
     */
    boolean setUserMeasurementReminderDetailStatus(int reminderDetailId,int status);

    /**
     * 逻辑删除提醒项目
     * @param reminderDetailId
     * @param recordStatus 2
     * @return
     */
    boolean delUserMeasurementReminderDetail(int reminderDetailId,int recordStatus);

    /**
     * 更新提醒项目
     * @param userMeasurementReminderDetailDTO
     * @return
     */
    boolean updateUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO);
    
    /**
     *  获取用户地址列表
     *  @author yuhang.weng 
     *	@DateTime 2017年6月14日 下午4:00:23
     *
     *  @param userId
     *  @return
     */
    List<com.lifeshs.pojo.member.address.AddressDTO> listAddress(int userId);
    
    /**
     *  添加地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:08:25
     *
     *  @param addressDTO
     *  @return
     */
    ServiceMessage saveAddress(com.lifeshs.pojo.member.address.AddressDTO addressDTO);
    
    /**
     *  更新地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:08:32
     *
     *  @param addressDTO
     *  @return
     */
    ServiceMessage updateAddress(com.lifeshs.pojo.member.address.AddressDTO addressDTO);
    
    /**
     *  删除地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:08:41
     *
     *  @param id
     *  @param userId
     *  @return
     */
    boolean removeAddress(int id, int userId);

    /**
     * 根据用户openId获取用户信息
     * author: wenxian.cai
     * date: 2017/11/7 16:22
     */
    UserDTO getUserByOpenId(String openId);

    /**
     * 更新用户openid
     * author: wenxian.cai
     * date: 2017/11/7 19:24
     */
    int updateOpenId(int userId, String openId) throws OperationException;
    
    /**
     *  更新用户渠道商id
     *  @author yuhang.weng 
     *  @DateTime 2018年1月12日 下午2:58:11
     *
     *  @param userId
     *  @param businessId
     *  @throws OperationException
     */
    void updateBusinessId(int userId, int businessId) throws OperationException;
    
    /**
     *  判断手机号是否被绑定
     *  @author yuhang.weng 
     *  @DateTime 2018年1月19日 下午5:23:02
     *
     *  @param mobile
     *  @return
     */
    boolean isMobileExist(String mobile);
}