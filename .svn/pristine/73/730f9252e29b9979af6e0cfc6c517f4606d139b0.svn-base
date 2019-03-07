package com.lifeshs.service.org.user;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.model.DataResult;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.profile.BaseProfileDTO;

/**
 * 版权归
 * TODO 企业用户
 *
 * @author yuhang.weng
 * @DateTime 2016年5月13日 下午5:09:40
 */
public interface IOrgUserService {

    /**
     * 用户是否已存在（和机构用户一起判断）
     *
     * @param userName
     * @return
     * @throws Exception
     * @author dengfeng
     * @DateTime 2016-6-2 上午10:44:39
     */
    boolean userIsExist(String userName);

    /**
     * @param tBaseOrgUser 企业用户对象
     * @return ServiceMessage 服务层返回消息封装对象
     * @author yuhang.weng
     * @DateTime 2016年5月16日 下午4:55:15
     * @serverComment 修改用户基本信息
     */
    ServiceMessage basicInformation(TOrgUser tBaseOrgUser) throws Exception;
    
    /**
     * 根据姓名查询用户
     *  @author liaoguo
     *  @DateTime 2018年5月11日 上午11:18:23
     *
     *  @param rName 用户姓名
     *  @param orgId 机构Id
     *  @return 用户姓名+手机号码
     */
    List<TOrgUser> getEmployListByRealName(String rName, int orgId);
    
    /**
     * 
     *  根据姓名查询用户
     *  @author NaN
     *  @DateTime 2018年10月12日 下午2:33:22
     *
     *  @param rName
     *  @return
     */
    List<OrgUserPO> getOrgUserByRealName(String rName);
    
    /**
     * 
     *  根据套餐id查询服务师
     *  @author NaN
     *  @DateTime 2018年10月16日 上午11:51:13
     *
     *  @param comboId
     *  @return
     */
    List<OrgUserPO> findComboOrgUserRelation(int comboId,int vipComboItemId);
    List<OrgUserDTO> findComboServeUserRelation(int comboId,int vipComboItemId);
    
    /**
     * 
     *  根据门店Id查找服务师总数量
     *  @author liaoguo
     *  @DateTime 2018年5月23日 上午11:15:05
     *
     *  @param orgId 门店id
     *  @return
     */
    List<TOrgUser> findOrgUserListByOrgId(Integer orgId);
    
    /**
     * 
     *  修改服务师密码
     *  @author liaoguo
     *  @DateTime 2018年5月23日 下午4:53:27
     *
     *  @param id
     *  @param pwd
     *  @return
     */
    Integer updateOrgUserPwdByUserId(Integer id, String pwd);

    /**
     * 根据手机判断用户是否存在(手机必须是已验证）[注:会检验TUser与TOrgUser]
     *
     * @param mobile
     * @return userId
     * @author dachang.luo
     * @DateTime 2016-5-13 下午03:05:00
     */
    String checkMobile(String mobile);

    /**
     * 根据邮箱判断用户是否存在（邮箱必须是已验证邮箱）[注:会检验TUser与TOrgUser]
     *
     * @param email
     * @return userId
     * @author dachang.luo
     * @DateTime 2016-5-13 下午02:36:27
     */
    String checkEmail(String email);

    /**
     * @param userId   用户ID
     * @param password 新密码
     * @return userName
     * @author dachang.luo
     * @DateTime 2016-5-13 上午10:17:40
     * @serverComment 重置密码
     */
    String modifyPasswordByUserId(Integer userId, String password, String ip);

    /**
     * 查找该用户所在的机构类型
     *
     * @param orgId 机构id
     * @author zhansi.Xu
     * @DateTime 2016年9月6日
     * @serverComment
     */
    int getOrgType(int orgId);

    /**
     * @author zhansi.Xu
     * @DateTime 2016年9月6日
     * @serverComment 添加一个员工
     */
    Integer addEmploy(Map<String, Object> user, Integer parentId) throws Exception;

    /**
     * 根据查询条件筛选出员工列表
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月7日
     * @serverComment
     */
    List<TOrgUser> getEmployList(Map<String, Object> params);

    /**
     * 根据条件筛选出员工列表(2)
     *
     * @param pageIndex
     * @param pageSize
     * @param status    正常_0,停用_1,注销_2,离职_3,为null表示不限定该条件
     * @param userType  管理员_0,服务师_1,都有_2,为null表示不限定该条件
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月22日 上午10:41:46
     */
    List<TOrgUser> getEmployList2(int pageIndex, int pageSize, Integer status, Integer userType, int orgId);

    /**
     * 操作员工信息
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月8日
     * @serverComment 删除、离职等操作
     */
    int controlEmploy(Map<String, Object> params);

    /**
     * 机构用户改密码
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月28日
     * @serverComment
     */
    int updatePassword(Integer id, String oldPsw, String newPsw);

    /**
     * 获取员工信息
     *
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月9日 下午4:24:03
     */
    Map<String, Object> getEmploy(Integer userId);

    /**
     * 更新员工信息
     *
     * @param employ
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月11日 上午10:26:12
     */
    boolean updateEmploy(Map<String, Object> employ);

    /**
     * 添加一个用户
     *
     * @author zhansi.Xu
     * @DateTime 2016年10月9日
     * @serverComment
     */
    int addUser(Map<String, Object> params);

    /**
     * 获取一个机构用户信息
     *
     * @return
     */
    OrgUserDTO getOrgUser(Integer userId);
    
    /**
     *  更新基础信息
     *  @author yuhang.weng 
     *	@DateTime 2017年5月11日 上午10:26:58
     *
     *  @param data
     */
    void updateBaseProfile(BaseProfileDTO data);

    /**
     * 更新用户手机号码
     *
     * @param userId
     * @param mobile
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月6日 下午4:03:39
     */
    boolean updateOrgUserMobile(int userId, String mobile);

    /**
     * 安全设置页面的基本信息
     *
     * @return
     */
    DataResult getUserSecurityData(OrgUserSharingData orgUserSharingData);

    /**
     * 机构是否只有一个管理员
     *
     * @param orgId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年12月10日 上午10:56:58
     */
    boolean isOrgRemainOneAdmin(int orgId);

    boolean updateOrgUserEmail(int userId, String email);

    boolean commitReport( OrgUserSharingData orgUserSharingData,String text);

    List<Integer> getOrgUsersByOrgId(Integer orgId);
    
    void tobeStoreCheck(LoginUser user);
    
    int createShop(LoginUser user, String shopName);
}
