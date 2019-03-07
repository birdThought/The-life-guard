package com.lifeshs.dao1.customer;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.customer.CustomerUserOfflinePO;
import com.lifeshs.po.customer.CustomerUserPO;

/**
 * 客服用户表
 * author: wenxian.cai
 * date: 2017/10/10 10:36
 */

@Repository(value = "customerUserDao")
public interface ICustomerUserDao {

	/**
	 * 根据用户名获取用户
	 * @param userName
	 * @return
	 */
	CustomerUserPO getUserByUserName(@Param("userName") String userName);

	/**
	 * 根据主键获取用户实体
	 * author: wenxian.cai
	 * date: 2017/10/23 17:40
	 */
	CustomerUserPO getUser(@Param("id") int id);
	
	/**
	 * 
	 *  根据用户id或用户登录名称查找用户信息
	 *  @author NaN
	 *  @DateTime 2018年10月31日 下午12:00:00
	 *
	 *  @param id
	 *  @param userName
	 *  @return
	 */
	List<CustomerUserPO> getUserByParam(@Param("id") String id, @Param("userNo")  String userNo, @Param("userName") String userName);

	/**
     * 
     *  根据地域查找代理商
     *  @author NaN
     *  @DateTime 2018年10月31日 下午12:00:00
     *
     *  @param id
     *  @param userName
     *  @return
     */
    CustomerUserPO getAgentByCity(@Param("province")  String province, @Param("city") String city,@Param("area") String area);

	
	
	/**
	 * 更新密码
	 * author: wenxian.cai
	 * date: 2017/10/23 17:41
	 */
	void updatePassword(@Param("id") int id, @Param("password") String password);
	
	/**
	 *  获取客服列表
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月27日 下午5:26:16
	 *
	 *  @param userCodeList
	 *  @return
	 */
	List<CustomerUserPO> findUserListByUserCodeList(@Param("userCodeList") List<String> userCodeList);
	
	/**
	 * 添加客服
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日16:53:19
	 * 
	 * @param customerUserPO
	 * @return
	 */
	int addUser(CustomerUserPO customerUserPO);
	
	/**
	 * 获取客服总记录数
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日14:09:43
	 * 
	 * @return
	 */
	int getUserTotalRecord();
	
//	/**
//	 * 
//	 *  获取线下用户记录数
//	 *  @author liaoguo
//	 *  @DateTime 2018年10月11日 上午11:45:35
//	 *
//	 *  @param agentId
//	 *  @param userName
//	 *  @param realName
//	 *  @param mobile
//	 *  @return
//	 */
//    int findMemberOfflineCount(int agentId, String userName, String realName, String mobile);
	
	/**
	 * 获取客服列表
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日14:11:18
	 * 
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<CustomerUserPO> findUserList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
//	/**
//     * 获取客服列表
//     * @author zizhen.huang
//     * @DateTime 2018年1月10日14:11:18
//     * 
//     * @param startRow
//     * @param pageSize
//     * @return
//     */
//    List<CustomerUserPO> findMemberOfflineList(@Param("agentId") int agentId,@Param("userName") String userName,@Param("realName") String realName,
//            @Param("mobile") String mobile,  @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    
    /**
     * 统计线下用户数量
     * 
     * @return
     */
    int countMemberOffline(@Param("userNo") String userNo,@Param("realName") String realName,@Param("mobile") String mobile);

    /**
     * 获取线下用户列表
     * 
     * @param startRow
     * @param pageSize
     * @return
     */
    List<CustomerUserOfflinePO> findMemberOfflineList(@Param("userNo") String userNo,
            @Param("realName") String realName,@Param("mobile") String mobile,@Param("startRow") int startRow, 
            @Param("pageSize") int pageSize);
}
