package com.lifeshs.service1.business;
/**
 * 渠道商绑定的用户
 * 
 * @author zizhen.huang
 * @DateTime 2018年1月4日16:55:33
 */

import java.util.Date;
import java.util.List;

import com.lifeshs.po.business.BusinessAccountPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.BusinessUserVO;
import com.lifeshs.vo.business.ExpenseDetailVO;
import com.lifeshs.vo.business.StatisticVO;

public interface BusinessBindUserService {
	
	/**
	 * 获取渠道商绑定的用户列表
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月4日16:59:48
	 * 
	 * @param businessId 渠道商id
	 * @param realName 用户姓名
	 * @param curPage 当前页数
	 * @param pageSize 每页记录数
	 * @return
	 */
    Paging<BusinessUserVO> findBusinessBindUserList(int businessId, String realName, int curPage, int pageSize);
    
    /**
     * 获取用户当月消费清单列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日11:20:04
     * 
     * @param businessId 渠道商id
     * @param userId 用户id
     * @param curPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Paging<ExpenseDetailVO> findExpenseDetailList(int businessId, int userId, int curPage, int pageSize);
    
    /**
     * 统计所有绑定用户当月的总消费记录
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日13:42:57
     * 
     * @param businessId 渠道商id
     */
    ExpenseDetailVO countMonthCost(int businessId);
    
    /**
     * 获取每个绑定用户当月的消费列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月5日15:12:23
     * 
     * @param businessId 渠道商id
     * @param curPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Paging<ExpenseDetailVO> findMonthCostList(int businessId, int curPage, int pageSize);
    
    /**
     * 获取结算列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日09:15:41
     * 
     * @param businessId 渠道商id
     * @param curPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Paging<BusinessAccountPO> findAccountList(int businessId, int curPage, int pageSize);
    
    /**
     * 获取当月所有用户的消费列表
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月8日11:55:02
     * 
     * @param businessId 渠道商id
     * @param months 当前月份
     * @param curPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Paging<ExpenseDetailVO> findAllUserList(int businessId, Date months, int curPage, int pageSize);
    
    /**
     * 获取本月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:10:14
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getLastMonth(int businessId);
    
    /**
     * 获取近三个月的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:23:28
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getRecentlyThreeMonths(int businessId);
    
    /**
     * 获取近半年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:33:09
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getHalfYear(int businessId);
    
    /**
     * 获取近一年的新用户数量
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月9日18:34:37
     * 
     * @param businessId 渠道商id
     * @return
     */
    List<StatisticVO> getAlmostYear(int businessId);
}
