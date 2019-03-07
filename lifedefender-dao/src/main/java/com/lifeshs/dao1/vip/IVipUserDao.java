package com.lifeshs.dao1.vip;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.vo.StatisticsVO;

/**
 * vip会员
 * author: wenxian.cai
 * date: 2017/9/29 10:23
 */
@Repository("vipUserDao")
public interface IVipUserDao {

	/**
	 * 渠道商获取vip会员
	 * @param isEndTime 是否服务即将过期
	 * @param gender 性别
	 * @param startAge 开始年龄
	 * @param endAge 结束年龄
	 * @param comboType 套餐类别
	 * @param status 会员状态
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List findVipUserListByBusiness(@Param("businessId") int businessId, @Param("isEndTime") Boolean isEndTime, @Param("gender") Boolean gender,
								   @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
								   @Param("vipComboId") Integer comboType, @Param("status") Integer status,
								   @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 * 渠道商获取vip会员数量
	 * @param isEndTime 是否服务即将过期
	 * @param gender 性别
	 * @param startAge 开始年龄
	 * @param endAge 结束年龄
	 * @param comboType 套餐类别
	 * @param status 会员状态
	 * @return
	 */
	int countVipUserByBusiness(@Param("businessId") int businessId, @Param("isEndTime") Boolean isEndTime, @Param("gender") Boolean gender,
							   @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
							   @Param("vipComboId") Integer comboType, @Param("status") Integer status,
							   @Param("keyword") String keyword);

	/**
	 * 客服获取vip会员
	 * @param gender 性别
	 * @param startAge 开始年龄
	 * @param endAge 结束年龄
	 * @param comboType 套餐类别
	 * @param status 会员状态
	 * @param keyword
	 * @param todayAbnormal 是否今日异常
	 * @param isEndTime	是否即将到期
	 * @param todayNotMeasure 是否今日未测量
	 * @param monthNotMeasure 是否本月未测量
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List findVipUserListByCustomer(@Param("gender") Boolean gender,
								   @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
								   @Param("vipComboId") Integer comboType, @Param("status") Integer status, @Param("keyword") String keyword,
								   @Param("todayAbnormal") Boolean todayAbnormal, @Param("isEndTime") Boolean isEndTime,
								   @Param("todayNotMeasure") Boolean todayNotMeasure, @Param("monthNotMeasure") Boolean monthNotMeasure,
								   @Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 * 客服获取vip会员总数
	 * @param gender 性别
	 * @param startAge 开始年龄
	 * @param endAge 结束年龄
	 * @param comboType 套餐类别
	 * @param status 会员状态
	 * @param keyword
	 * @param todayAbnormal 是否今日异常
	 * @param isEndTime	是否即将到期
	 * @param todayNotMeasure 是否今日未测量
	 * @param monthNotMeasure 是否本月未测量
	 * @return
	 */
	int countVipUserByCustomer(@Param("gender") Boolean gender,
							   @Param("startAge") Integer startAge, @Param("endAge") Integer endAge,
							   @Param("vipComboId") Integer comboType, @Param("status") Integer status, @Param("keyword") String keyword,
							   @Param("todayAbnormal") Boolean todayAbnormal, @Param("isEndTime") Boolean isEndTime,
							   @Param("todayNotMeasure") Boolean todayNotMeasure, @Param("monthNotMeasure") Boolean monthNotMeasure);

	/**
     *  添加vip用户
     *  @author yuhang.weng 
     *  @DateTime 2017年10月16日 下午1:47:19
     *
     *  @param user
     *  @return
     */
    int addVipUser(VipUserPO user);
    
    /**
     *  更新vip用户
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 上午10:42:09
     *
     *  @param user
     *  @return
     */
    int updateVipUser(VipUserPO user);
    
    /**
     *  批量更新vip用户的status
     *  @author yuhang.weng 
     *  @DateTime 2017年11月3日 上午10:51:33
     *
     *  @param userList 用户列表
     *  @return
     */
    int updateVipUserStatusList(@Param("userList") List<VipUserPO> userList);
    
    /**
     *  获取用户的vip会员列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 上午10:26:39
     *
     *  @param userId 用户id
     *  @param status 会员状态
     *  @param remainDay 剩余天数
     *  @return
     */
    List<VipUserPO> findVipUserListWithCondition(@Param("userId") Integer userId,
            @Param("status") Integer status, @Param("remainDay") Integer remainDay);
    
    /**
     * 根据用户id、套餐id、套餐项id获取剩余套餐的次数
     * @author zizhen.huang
     * @DataTime 2017年12月25日15:27:24
     * 
     * @param userId 用户id
     * @param comboId 套餐id
     * @param comboItemId 套餐项id
     * @return 剩余套餐次数
     */
    Integer getComboNumberById(@Param("userId") int userId,@Param("comboId") int comboId,@Param("comboItemId") int comboItemId);

	/**
	 * 	 客服推送列表获取有效会员
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<StatisticsVO> findByUserVipList(@Param("startRow") Integer startRow,@Param("pageSize")Integer pageSize);

	/**
	 * 获取数量
	 * @return
	 */
	int getUserVipCount();

}
