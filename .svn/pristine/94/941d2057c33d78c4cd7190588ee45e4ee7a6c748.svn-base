package com.lifeshs.dao.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.member.TUser;
import com.lifeshs.po.admin.AdminUserDTO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDTO;
import com.lifeshs.pojo.member.UserMeasurementReminderDetailDTO;
import com.lifeshs.pojo.member.UserOauthDTO;
import com.lifeshs.pojo.member.UserPcLoginDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.VcodeDTO;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;


/**
 *  版权归
 *  TODO 
 *  @author duosheng.mo  
 *  @DateTime 2016年4月20日 上午9:41:03
 *  
 */
@Repository("memberDao")
public interface IMemberDao {

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年4月22日 上午10:46:25
     *  @serverCode 服务代码
     *  @serverComment 查询会员
     *
     *  @param param
     *  @return
     */
    List<Map<String,Object>> selectMemberByMap(Map<String, Object> param) throws Exception;
    
    /**
     *  @author zhiguo.lin 
     *  @DateTime 2016年8月13日 下午5:47:24
     *  @serverComment 根据用户名模糊查询
     *
     *  @return
     *  @throws Exception
     */
    List<TUser> selectLikeUser(@Param("userName") String userName);
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月3日下午5:36:43
     * @serverComment 分页获取短信记录
     * @param 
     */
    List<Map<String, Object>> selectSmsRecordListByPage(Map<String, Object> map);
    
    /**
     *  获取用户userCode
     *  @author yuhang.weng 
     *  @DateTime 2017年3月2日 下午6:16:49
     *
     *  @param userIds
     *  @return
     */
    List<String> listUserCode(@Param("userIds") List<Integer> userIds);
    
    /**
     *  获取用户个人档案
     *  @author yuhang.weng 
     *  @DateTime 2017年3月17日 上午11:36:21
     *
     *  @param userId
     *  @return
     */
    UserRecordDTO getUserRecord(@Param("userId") int userId);
    
    /**
     *  更新用户个人档案
     *  @author yuhang.weng 
     *  @DateTime 2017年3月17日 下午3:04:13
     *
     *  @param recordDTO
     */
    int updateUserRecord(UserRecordDTO recordDTO);
    
    /**
     *  获取用户信息
     *  @author yuhang.weng 
     *  @DateTime 2017年3月17日 下午3:04:05
     *
     *  @param id
     *  @return
     */
    UserDTO getUser(@Param("id") Integer id);
    
    /**
     *  获取用户信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月20日 上午9:25:15
     *
     *  @param userName   
     *  @return
     */
    UserDTO getUserByUserName(@Param("userName") String userName);
    
    /**
     *  获取用户id信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月20日 上午9:25:15
     *
     *  @param userName   getUserByUserNo
     *  @return
     */
    UserDTO getUserByUserNo(@Param("userNo") String userNo);
    
    /*
     * 登录判断推荐人ID
     * 用户 判断 t_admin_user 表   UserNo
     * 
     */
    AdminUserDTO getAdminUserUserNo(@Param("userNo") String userNo);
    
    /*
     * 推荐人编号
     * 用户 判断 t_org_user 表   UserNo
     * 
     */
    OrgUserDTO getOrgUserNo(@Param("userNo") String userNo);
    
    
    /**
     *  获取用户信息
     *  @author yuhang.weng 
     *	@DateTime 2017年4月14日 上午11:17:47
     *
     *  @param mobile
     *  @return
     */
    UserDTO getUserByMobile(@Param("mobile") String mobile);
    
    /**
     *  获取用户信息
     *  @author yuhang.weng 
     *  @DateTime 2017年3月20日 上午9:25:11
     *
     *  @param groupKey
     *  @return
     */
    List<UserDTO> listUserByGroupKey(@Param("groupKey") String groupKey);
    
    /**
     *  更新用户信息
     *  @author yuhang.weng 
     *	@DateTime 2017年3月17日 下午3:58:43
     *
     *  @param userDTO
     */
    int updateUser(UserDTO userDTO);
    
    /**
     *  添加用户
     *  @author yuhang.weng 
     *	@DateTime 2017年8月3日 下午2:42:38
     *
     *  @param userDTO
     *  @return
     */
    int addUser(UserDTO userDTO);
    
    /**
     *  添加用户个人档案
     *  @author yuhang.weng 
     *	@DateTime 2017年3月18日 上午11:18:57
     *
     *  @param recordDTO
     */
    int addUserRecord(UserRecordDTO recordDTO);
    
    /**
     *  退出家庭群组
     *  @author yuhang.weng 
     *	@DateTime 2017年3月18日 下午4:59:06
     *
     *  @param userId
     */
    void leaveGroup(@Param("userId") int userId);

    /**
     * 获取用户每天发送短信条数
     * @author wenxian.cai
     * @DateTime 2017-4-1
     * @param sendId receiveMobile
     * @return
     */
    int getIllegalMobileNumber(@Param("sendId")int sendId, @Param("receiveMobile")String receiveMobile);

    /**
     * 添加第三方登录信息
     * @param oauth
     * @return
     */
    int addOauthUser(UserOauthDTO oauth);

    /**
     * 根据uId获取用户
     * @param uId
     * @return
     */
    UserDTO getUserByUId(String uId);
    
    /**
     * 根据id获取用户(第三方登录)
     * @param userId
     * @return
     * @author liu
     * @时间 2018年12月26日 上午11:33:14
     * @remark
     */
    Map<String, Object> getUserByUserId(Integer userId);

    /**
     * @Description: 获取扫一扫pc登录信息
     * @author: wenxian.cai
     * @create: 2017/4/18 10:24
     * @param token:存放在二维码的token
     */
    UserPcLoginDTO getUserPcLogin(String token);

    /**
     * @Description: 添加扫一扫pc登录信息
     * @author: wenxian.cai
     * @create: 2017/4/18 10:26
     */
    int addUserPcLogin(UserPcLoginDTO userPcLoginDTO);

    /**
     * @Description: 添加验证码信息
     * @author: wenxian.cai
     * @create: 2017/4/18 14:38
     */
    int addVcode(VcodeDTO vcodeDTO);

    /**
     *  添加用户提醒
     *  @author yuhang.weng 
     *	@DateTime 2017年5月25日 上午11:57:36
     *
     *  @param userId
     *  @return
     */
    int addUserMeasurementReminder(@Param("userId") int userId);
    
    /**
     * 获取提醒设置
     * @param userId
     * @return
     */
    UserMeasurementReminderDTO getUserMeasurementReminder(@Param("userId") int userId);

    /**
     * 获取提醒项目列表
     * @param params
     * @return
     */
    List<UserMeasurementReminderDetailDTO> listUserMeasurementReminderDetail(Map<String,Object> params);

    /**
     * 设置短信提醒
     * @param params
     * @return
     */
    int setUserMeasurementReminderSmsSwitch(Map<String,Object> params);

    /**
     * 设置推送提醒
     * @param params
     * @return
     */
    int setUserMeasurementReminderPushSwitch(Map<String,Object> params);
    
    /**
     *  设置用户提醒
     *  @author yuhang.weng 
     *	@DateTime 2017年6月5日 下午2:38:25
     *
     *  @param params
     *  @return
     */
    int setUserMeasurementReminderSwitch(Map<String, Object> params);

    /**
     * 新增提醒项目
     * @param userMeasurementReminderDetailDTO
     * @return
     */
    int addUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO);

    /**
     * 设置提醒项目状态
     * @param params
     * @return
     */
    int setUserMeasurementReminderDetailStatus(Map<String,Object> params);

    /**
     * 逻辑删除提醒项目
     * @param params
     * @return
     */
    int delUserMeasurementReminderDetail(Map<String,Object> params);
    
    /**
     *  逻辑删除指定用户的所有提醒项目
     *  @author yuhang.weng 
     *	@DateTime 2017年6月5日 下午2:02:01
     *
     *  @param userId
     *  @param status 2
     *  @return
     */
    int delUserMeasurementReminderDetailByUserId(@Param("userId") int userId, @Param("recordStatus") int status);

    /**
     * 更新提醒项目
     * @param userMeasurementReminderDetailDTO
     * @return
     */
    int updateUserMeasurementReminderDetail(UserMeasurementReminderDetailDTO userMeasurementReminderDetailDTO);
    
    /**
     *  获取用户地址列表
     *  @author yuhang.weng 
     *	@DateTime 2017年6月14日 下午3:59:41
     *
     *  @param userId
     *  @return
     */
    List<AddressDTO> listAddress(@Param("userId") Integer userId);
    
    /**
     *  添加地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:07:51
     *
     *  @param addressDTO
     *  @return
     */
    int saveAddress(AddressDTO addressDTO);
    
    /**
     *  更新地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:08:02
     *
     *  @param addressDTO
     *  @return
     */
    int updateAddress(AddressDTO addressDTO);
    
    /**
     *  移除地址
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 上午11:08:12
     *
     *  @param id
     *  @param userId
     *  @return
     */
    int removeAddress(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  获取地址
     *  @author yuhang.weng 
     *	@DateTime 2017年7月15日 下午2:10:17
     *
     *  @param id
     *  @param userId
     *  @return
     */
    AddressDTO getAddress(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  把用户的所有地址都修改为非默认状态
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 下午3:33:06
     *
     *  @param userId
     *  @return
     */
    int updateUserAddressToUnSelected(@Param("userId") int userId);

    /**
     * 根据openId获取用户信息
     * author: wenxian.cai
     * date: 2017/11/7 16:27
     */
    UserDTO getUserByOpenId(@Param("openId") String openId);

    /**
     * 更新用户openId
     * author: wenxian.cai
     * date: 2017/11/7 19:26
     */
    int updateOpenId(@Param("userId") int userId, @Param("openId") String openId);
    
    /**
     *  通过用户名查询用户
     *  @author yuhang.weng 
     *  @DateTime 2018年1月19日 下午2:47:36
     *
     *  @param userName
     *  @return
     */
    UserPO findUserByUserName(@Param("userName") String userName);
    
    /**
     *  通过已验证的手机号码查询用户
     *  @author yuhang.weng 
     *  @DateTime 2018年1月19日 下午2:47:22
     *
     *  @param mobile
     *  @return
     */
    UserPO findUserByVerifyMobile(@Param("mobile") String mobile);
}
