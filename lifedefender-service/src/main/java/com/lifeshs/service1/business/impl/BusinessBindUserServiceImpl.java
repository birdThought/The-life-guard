package com.lifeshs.service1.business.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.business.IBusinessBindUserDao;
import com.lifeshs.po.business.BusinessAccountPO;
import com.lifeshs.service1.business.BusinessBindUserService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.BusinessUserVO;
import com.lifeshs.vo.business.ExpenseDetailVO;
import com.lifeshs.vo.business.StatisticVO;

@Service("businessBindUserService")
public class BusinessBindUserServiceImpl implements BusinessBindUserService {

	@Resource(name = "businessBindUserDao")
    private IBusinessBindUserDao businessBindUserDao;

	@Override
	public Paging<BusinessUserVO> findBusinessBindUserList(int businessId, String realName, int curPage, int pageSize) {
		Paging<BusinessUserVO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<BusinessUserVO>() {

			@Override
			public int queryTotal() {
				return businessBindUserDao.getUserTotalRecord(businessId, realName);
			}

			@Override
			public List<BusinessUserVO> queryData(int startRow, int pageSize) {
				return businessBindUserDao.findBusinessBindUserList(businessId, realName, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<ExpenseDetailVO> findExpenseDetailList(int businessId, int userId, int curPage, int pageSize) {
        Paging<ExpenseDetailVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ExpenseDetailVO>() {

			@Override
			public int queryTotal() {
				return businessBindUserDao.getDetailTotalRecord(businessId, userId);
			}

			@Override
			public List<ExpenseDetailVO> queryData(int startRow, int pageSize) {
				return businessBindUserDao.findExpenseDetailList(businessId, userId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public ExpenseDetailVO countMonthCost(int businessId) {
		return businessBindUserDao.countMonthCost(businessId);
	}

	@Override
	public Paging<ExpenseDetailVO> findMonthCostList(int businessId, int curPage, int pageSize) {
		Paging<ExpenseDetailVO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<ExpenseDetailVO>() {

			@Override
			public int queryTotal() {
				return businessBindUserDao.getMonthCostTotalRecord(businessId);
			}

			@Override
			public List<ExpenseDetailVO> queryData(int startRow, int pageSize) {
				return businessBindUserDao.findMonthCostList(businessId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<BusinessAccountPO> findAccountList(int businessId, int curPage, int pageSize) {
		Paging<BusinessAccountPO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<BusinessAccountPO>() {

			@Override
			public int queryTotal() {
				return businessBindUserDao.getAccountTotalRecord(businessId);
			}

			@Override
			public List<BusinessAccountPO> queryData(int startRow, int pageSize) {
				return businessBindUserDao.findAccountList(businessId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<ExpenseDetailVO> findAllUserList(int businessId, Date months, int curPage, int pageSize) {
		Paging<ExpenseDetailVO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<ExpenseDetailVO>() {

			@Override
			public int queryTotal() {
				return businessBindUserDao.getAllUserTotalRecord(businessId, months);
			}

			@Override
			public List<ExpenseDetailVO> queryData(int startRow, int pageSize) {
				return businessBindUserDao.findAllUserList(businessId, months, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public List<StatisticVO> getLastMonth(int businessId) {
		return businessBindUserDao.getLastMonth(businessId);
	}

	@Override
	public List<StatisticVO> getRecentlyThreeMonths(int businessId) {
		return businessBindUserDao.getRecentlyThreeMonths(businessId);
	}

	@Override
	public List<StatisticVO> getHalfYear(int businessId) {
		return businessBindUserDao.getHalfYear(businessId);
	}

	@Override
	public List<StatisticVO> getAlmostYear(int businessId) {
		return businessBindUserDao.getAlmostYear(businessId);
	}
}
