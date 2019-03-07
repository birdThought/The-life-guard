package com.lifeshs.dao1.business;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.business.BusinessAccountPO;
import com.lifeshs.vo.business.BusinessUserVO;
import com.lifeshs.vo.business.ExpenseDetailVO;
import com.lifeshs.vo.business.StatisticVO;

/**
 * 渠道商绑定的用户
 * 
 * @author zizhen.huang
 * @DateTime 2018年1月4日15:28:12
 */
@Repository("businessBindUserDao")
public interface IBusinessBindUserDao {

	/**
	 * 获取渠道商绑定的用户总记录数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月4日16:45:09
	 * 
	 * @param businessId 渠道商id
	 * @param realName 用户姓名
	 * @return
	 */
	int getUserTotalRecord(@Param("businessId") int businessId, @Param("realName") String realName);
	
	/**
	 * 获取渠道商绑定的用户列表
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月4日15:35:42
	 * 
	 * @param businessId 渠道商id
	 * @param realName 用户姓名
	 * @param startRow 当前页数
	 * @param pageSize 每页记录数
	 * @return
	 */
    List<BusinessUserVO> findBusinessBindUserList(@Param("businessId") int businessId, @Param("realName") String realName, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 获取用户当月消费清单总记录数
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日10:52:30
     * 
     * @param businessId 渠道商id
     * @param userId 用户id
     * @return
     */
    int getDetailTotalRecord(@Param("businessId") int businessId, @Param("userId") int userId);
    
    /**
     * 获取用户当月消费清单列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日10:53:17
     * 
     * @param businessId 渠道商id
     * @param userId 用户id
     * @param startRow 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    List<ExpenseDetailVO> findExpenseDetailList(@Param("businessId") int businessId, @Param("userId") int userId, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 统计所有绑定用户当月的总消费记录
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日13:05:37
     * 
     * @param businessId 渠道商id
     * @return
     */
    ExpenseDetailVO countMonthCost(@Param("businessId") int businessId);
    
    /**
     * 获取每个绑定用户当月的消费总记录数
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日15:09:04
     * 
     * @param businessId 渠道商id
     * @return
     */
    int getMonthCostTotalRecord(@Param("businessId") int businessId);
    
    /**
     * 获取每个绑定用户当月的消费列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日15:01:11
     * 
     * @param businessId 渠道商id
     * @param startRow 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    List<ExpenseDetailVO> findMonthCostList(@Param("businessId") int businessId, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 获取结算表总记录数
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日16:38:17
     * 
     * @param businessId 渠道商id
     * @return
     */
    int getAccountTotalRecord(@Param("businessId") int businessId);
    
    /**
     * 获取结算列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日17:16:37
     * 
     * @param businessId 渠道商id
     * @param startRow 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    List<BusinessAccountPO> findAccountList(@Param("businessId") int businessId, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 获取当月所有用户的消费总记录数
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日11:44:56
     * 
     * @param months 当前月份
     * @param businessId 渠道商id
     * @return
     */
    int getAllUserTotalRecord(@Param("businessId") int businessId, @Param("months") Date months);
    
    /**
     * 获取当月所有用户的消费列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日11:47:56
     * 
     * @param months 当前月份
     * @param businessId 渠道商id
     * @param startRow 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    List<ExpenseDetailVO> findAllUserList(@Param("businessId") int businessId, @Param("months") Date months, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 获取本月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日09:39:58
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getLastMonth(@Param("businessId") int businessId);
    
    /**
     * 获取近三个月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日09:59:47
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getRecentlyThreeMonths(@Param("businessId") int businessId);
    
    /**
     * 获取近半年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日10:00:45
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getHalfYear(@Param("businessId") int businessId);
    
    /**
     * 获取近一年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日10:02:17
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getAlmostYear(@Param("businessId") int businessId);
}
