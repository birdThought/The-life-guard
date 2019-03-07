package com.lifeshs.dao1.business;

import com.lifeshs.po.business.BusinessPo;
import com.lifeshs.po.business.BusinessUserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 渠道商用户dao author: wenxian.cai date: 2017/9/25 15:30
 */
@Repository(value = "businessUserDao")
public interface IUserDao {

	/**
	 * 根据用户名获取
	 * 	校验用
	 * @param
	 * @return
	 */
	BusinessPo getUserByUserName(@Param("name") String name);

	/**
	 * 根据用户名获取
	 *  登录用
	 * @param userName
	 * @return
	 */
	BusinessUserPO getUserByBusiness(@Param("userName")String userName);

	/**
	 * 获取用户
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月9日 上午10:31:57
	 *
	 * @param id
	 *            用户id
	 * @return
	 */
	BusinessUserPO getUser(@Param("id") int id);

	/**
	 * 更新用户
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月9日 上午10:36:07
	 *
	 * @param user
	 *            用户对象
	 * @return
	 */
	int updateUser(BusinessUserPO user);

	/**
	 * 获取用户列表
	 * 
	 * @param name 名称
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<BusinessPo> findUserList(@Param("name") String name, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

	/**
	 * 获取用户数量
	 * 
	 * @return
	 */
	int countUser();

	/**
	 * 修改账户密码
	 * 
	 * @param userId
	 * @param password
	 */
	void updatePassword(@Param("userId") int userId, @Param("password") String password);

	/**
	 * 添加渠道商
	 * @author shiqiang.zeng
	 * @date 2018.1.4
	 * @param userName
	 * @param password
	 * @param name
	 * @param status
	 * @param photo
	 * @param userCode
	 * @param createDate
	 * @param photo
	 * @param email
	 * @param address
	 * @param type
	 *  2018年3月19日 17:37:58  废弃
	 */
	int addBussiness(@Param("userName") String userName, @Param("password") String password, @Param("name") String name,
					@Param("status") Integer status, @Param("photo") String photo,@Param("orgUserId") Integer orgUserId, @Param("userCode") String userCode,
					@Param("contactMan") String contactMan, @Param("phone") String phone, @Param("email") String email,
					@Param("address") String address, @Param("type") Integer type);
	
	/**
	 * 更改渠道商
	 * @param user
	 */
	void updateBusiness(@Param("userName")String userName,@Param("name")String name,@Param("address")String address,@Param("phone")String phone,
                        @Param("email") String email,@Param("id")Integer id);


	List<BusinessUserPO> findByAllSell(@Param("id") Integer id, @Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int findByCountManAgeMent(@Param("id") Integer id);

    int saveBusiness(BusinessPo bp);

	int saveBusinessUser(BusinessUserPO bup);
}
