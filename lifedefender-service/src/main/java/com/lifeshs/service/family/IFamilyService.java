package com.lifeshs.service.family;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.family.GroupMemberVO;

/**
 * 版权归 TODO 类说明
 * 
 * @author zhiguo.lin
 * @DateTime 2016年8月2日 下午3:24:26
 */
public interface IFamilyService {

	/**
	 * @author zhiguo.lin
	 * @DateTime 2016年8月2日 下午3:24:23
	 * @serverComment 服务注解
	 *
	 * @param entityName
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public TUser findUserByUserName(String userName) throws Exception;

	/**
	 * @author zhiguo.lin
	 * @DateTime 2016年8月13日 下午5:22:07
	 * @serverComment 通过用户名查找用户列表
	 *
	 * @param userName
	 * @param userId
	 *            目前登录的用户ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findUserListByUserName(String userName, Integer userId);

	/**
	 *  添加用户到家庭组
	 *  @author zhiguo.lin
	 *	@DateTime 2016年8月9日 下午4:50:56
	 *
	 *  @param userName 账号
	 *  @param password 密码
	 *  @param currentUserId 该群组用户的ID
	 *  @return
	 */
	public ServiceMessage updateUserGroupKey(String userName, String password, int currentUserId);

	/**
	 *  服务注解
	 *  @author yuhang.weng 
	 *	@DateTime 2016年12月16日 下午2:35:44
	 *
	 *  @param userId
	 *  @return
	 */
	public List<GroupMemberVO> findGroupMember(Integer userId);
	
	/**
	 *  
	 *  @author yuhang.weng 
	 *	@DateTime 2016年12月16日 下午2:35:42
	 *
	 *  @param user
	 */
	public void updateMemberInfo(UserDTO user, Integer currentUserId);
}
