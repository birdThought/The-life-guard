package com.lifeshs.dao1.electronicCoupons;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.UserElectronicCouponsPO;

/**
 * 电子券dao
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年12月8日 上午9:25:10
 */
@Repository(value = "electronicCouponsDao")
public interface ElectronicCouponsDao {

	/**
	 * 添加电子券
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:51:57
	 *
	 * @param coupons
	 *            电子券
	 * @return
	 */
	int addCoupons(UserElectronicCouponsPO coupons);

	/**
	 * 添加电子券
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:52:04
	 *
	 * @param couponsList
	 *            电子券集合
	 * @return
	 */
	int addCouponsList(@Param("couponsList") List<UserElectronicCouponsPO> couponsList);

	/**
	 * 更新电子券
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:52:11
	 *
	 * @param coupons
	 *            电子券
	 * @return
	 */
	int updateCoupons(UserElectronicCouponsPO coupons);

	/**
	 *  批量更新电子券状态
	 *  @author yuhang.weng 
	 *  @DateTime 2018年1月2日 上午11:21:50
	 *
	 *  @param idList 电子券id列表
	 *  @param status 状态
	 *  @return
	 */
	int updateCouponsListStatus(@Param("idList") List<Integer> idList, @Param("status") int status);
	
	/**
	 * 获取电子券
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:52:26
	 *
	 * @param id
	 *            电子券id
	 * @return
	 */
	UserElectronicCouponsPO getCoupons(@Param("id") int id);

	/**
	 * 统计电子券
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:52:38
	 *
	 * @param userId 用户id
	 * @param serveCode 服务code
	 * @param projectCode 服务项目code
	 * @param serveItemId 服务项目id
	 * @param status 电子券状态
	 * @return
	 */
	int countCouponsWithCondition(@Param("userId") int userId,
			@Param("serveCode") String serveCode, @Param("projectCode") String projectCode,
			@Param("serveItemId") Integer serveItemId, @Param("status") Integer status);

	/**
	 * 获取电子券列表
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:52:45
	 *
	 * @param userId 用户id
	 * @param serveCode 服务code
	 * @param projectCode 服务项目
	 * @param serveItemId 服务项目id
	 * @param status 电子券状态
	 * @param startRow 开始下标
	 * @param pageSize 页面大小
	 * @return
	 */
	List<UserElectronicCouponsPO> findCouponsListWithCondition(@Param("userId") int userId,
			@Param("serveCode") String serveCode, @Param("projectCode") String projectCode,
			@Param("serveItemId") Integer serveItemId, @Param("status") Integer status,
			@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 *  统计可用的电子券列表
	 *  @author yuhang.weng 
	 *  @DateTime 2017年12月15日 下午2:01:29
	 *
	 *  @param userId 用户id
     *  @param projectType 服务code
     *  @param projectCode 服务项目
     *  @param serveItemId 服务项目id
	 *  @return
	 */
	int coutUsableCoupons(@Param("userId") int userId,
            @Param("projectType") Integer projectType, @Param("projectCode") String projectCode,
            @Param("serveItemId") Integer serveItemId);
	
	/**
	 *  获取可用的电子券列表
	 *  @author yuhang.weng 
	 *  @DateTime 2017年12月15日 下午2:00:13
	 *
	 *  @param userId 用户id
     *  @param projectType 服务code
     *  @param projectCode 服务项目
     *  @param serveItemId 服务项目id
	 *  @param startRow 开始下标
	 *  @param pageSize 页面大小
	 *  @return
	 */
	List<UserElectronicCouponsPO> findUsableCouponsList(@Param("userId") int userId,
	        @Param("projectType") Integer projectType, @Param("projectCode") String projectCode,
	        @Param("serveItemId") Integer serveItemId,
	        @Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	/**
	 * 获取即将到期的电子券列表
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年12月8日 上午9:53:08
	 *
	 * @param remainDay
	 *            剩余天数
	 * @return
	 */
	List<UserElectronicCouponsPO> findCouponsListOutOfEndDate(@Param("remainDay") int remainDay);
}
