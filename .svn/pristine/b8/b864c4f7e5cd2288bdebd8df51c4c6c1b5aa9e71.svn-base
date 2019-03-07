package com.lifeshs.dao.contacts;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.member.TUserContacts;

/**
 * 版权归 TODO 联系人
 * 
 * @author duosheng.mo
 * @DateTime 2016年6月15日 上午11:37:31
 */
@Repository("contactsDao")
public interface IContactsDao {

	/**
	 * @author duosheng.mo
	 * @DateTime 2016年5月30日 下午3:38:32
	 * @serverComment 查询亲情号和SOS
	 *
	 * @param userId
	 * @return
	 */
	public List<TUserContacts> findFamilyAndSos(int userId);

	/**
	 * <p>
	 * 修改联系人
	 * 
	 * @author dachang.luo
	 * @DateTime 2016年6月17日上午11:22:13
	 *
	 * @param contacts
	 * @return
	 * @throws Exception
	 */
	// public int updateContact(TUserContacts contacts) throws Exception;

	/**
	 * <p>
	 * 删除联系人
	 * 
	 * @author dachang.luo
	 * @DateTime 2016年6月17日上午11:22:13
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteContact(@Param("id") int id);

	/**
	 *  消除特定的contactType
	 *  @author yuhang.weng 
	 *	@DateTime 2017年1月4日 上午10:08:36
	 *
	 *  @param userId
	 *  @param removeTerminalType
	 */
	public void removeContactTypeInAllContact(@Param("userId") int userId,
                                              @Param("removeContactType") int removeContactType);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2016年12月13日下午7:47:44
	 * @serverComment 更新预警接受信息联系人
	 * @param 
	 */
	public int updateContactReceiveSMS(@Param("receiveSMS") boolean receiveSMS, @Param("userId") int userId);
	
	/**
	 * 添加联系人
	 * @author zizhen.huang
	 * @DateTime 2018年1月10日15:31:22
	 * 
	 * @param userId
	 * @param contactNumber
	 * @return
	 */
	int addContanct(@Param("userId") int userId, @Param("contactNumber") String contactNumber);
}
