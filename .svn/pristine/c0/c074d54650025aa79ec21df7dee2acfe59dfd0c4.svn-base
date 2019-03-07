package com.lifeshs.dao1.user;

import com.lifeshs.po.user.UserServeRecordPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository("userServeRecordDao")
public interface UserServeRecordDao {

	/**
	 * 添加用户服务记录
	 * @param po
	 * @return
	 */
	int addUserServeRecord(UserServeRecordPO po);

	/**
	 * 获取用户服务记录列表
	 * @param userId
	 * @param customerId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<UserServeRecordPO> findUserServeRecordList(@Param("userId") Integer userId, @Param("customerId") Integer customerId,
													@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 * 统计服务记录总数
	 * @param userId
	 * @param customerId
	 * @return
	 */
	int countUserServeRecord(@Param("userId") Integer userId, @Param("customerId") Integer customerId);
}
