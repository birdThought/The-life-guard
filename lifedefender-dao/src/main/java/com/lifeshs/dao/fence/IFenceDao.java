package com.lifeshs.dao.fence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.member.TUserElectronicFence;

/**
 *  版权归
 *  TODO 电子围栏dao
 *  @author duosheng.mo  
 *  @DateTime 2016年6月2日 下午5:27:14
 */
@Repository("fenceDao")
public interface IFenceDao {
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月31日 下午3:43:35
	 *  @serverComment 查询电子围栏
	 *
	 *  @param imei
	 *  @param userId
	 *  @return
	 */
	public List<TUserElectronicFence> findFenceByList(@Param("userDeviceId") int userDeviceId);
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月20日 下午5:47:21
	 *  @serverComment 查询电子围栏(按照围栏编号排序)
	 *
	 *  @param userDeviceId
	 *  @return
	 */
	public List<TUserElectronicFence> findFenceByListOrderByNumber(@Param("userDeviceId") int userDeviceId);
}
