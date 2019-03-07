package com.lifeshs.service1.customer;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.customer.CustomerUserOfflinePO;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.pojo.client.Client;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

/**
 * 客服用户service
 * author: wenxian.cai
 * date: 2017/10/10 10:46
 */
public interface CustomerUserService {

	/**
	 *  获取客服
	 *  @author yuhang.weng
	 *  @DateTime 2017年9月25日 上午9:52:58
	 *
	 *  @param userName
	 *  @param password
	 *  @return
	 */
	CustomerUserPO getUser(String userName, String password);
	
	/**
	 * 
	 *  根据用户id或登录名称查找用户
	 *  @author NaN
	 *  @DateTime 2018年10月31日 下午12:00:52
	 *
	 *  @param id
	 *  @param userNo
	 *  @param userName
	 *  @return
	 */
	List<CustomerUserPO> getUserByParam(String id, String userNo, String userName);

	/**
	 *  获取客服
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月27日 下午5:17:37
	 *
	 *  @param id 客服id
	 *  @return
	 */
	CustomerUserPO getUser(int id);

	/**
	 *  获取客服列表
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月27日 下午5:17:24
	 *
	 *  @param userCodeList 用户code列表
	 *  @return
	 */
	List<CustomerUserPO> listUser(List<String> userCodeList);
	
	/**
	 * 根据用户名获取一个用户
	 * author: wenxian.cai
	 * date: 2017/9/25 15:24
	 */
	CustomerUserPO getUserByUserName(String userName);

	/**
	 * 存储用户到缓存
	 * @param po
	 * @throws OperationException
	 */
	void saveUserSharingData(CustomerSharingDataVO po) throws OperationException;

	/**
	 * 从缓存获取用户信息
	 * @param userId
	 * @return
	 */
	CustomerSharingDataVO getUserSharingData(int userId);

	/**
	 * 更新用户缓存信息
	 * @throws OperationException
	 */
	void updateUserSharingData(int userId) throws OperationException;

	/**
	 *
	 * @param userId
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 */
	void updatePassword(int userId, String oldPassword, String newPassword) throws OperationException;

	/**
	 * 判断登录账号是否存在
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日17:38:28
	 * 
	 * @param userName
	 * @return
	 */
	boolean checkUserName(String userName);
	
	/**
	 * 添加客服
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日17:12:10
	 * 
	 * @param customerUserPO 客服用户
	 * @return
	 */
	boolean addUser(CustomerUserPO customerUserPO);
	
	/**
	 * 获取客服列表
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日14:18:56
	 * 
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
    Paging<CustomerUserPO> findUserList(int curPage, int pageSize);
    
	Client saveCustomerUserRecord(String userName);
	
	/**
     * 获取线下用户
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<CustomerUserOfflinePO> findMemberOfflineList(String userNo,String realName,String mobile,int curPage, int pageSize);
}
