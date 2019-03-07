package com.lifeshs.service1.vip;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.record.RecordComboVo;
import com.lifeshs.vo.vip.VipUserVO;

/**
 * vip会员服务 author: wenxian.cai date: 2017/9/29 9:51
 */
public interface IVipUserService {

	/**
	 * 渠道商获取vip会员信息列表
	 * 
	 * @param gender
	 *            性别
	 * @param startAge
	 *            开始年龄
	 * @param endAge
	 *            结束年龄
	 * @param comboType
	 *            套餐类别
	 * @param status
	 *            会员状态
	 * @return
	 */
	Paging<VipUserVO> listVipUserByBusiness(int businessId, Boolean isEndTime, Boolean gender, Integer startAge,
			Integer endAge, Integer comboType, Integer status, String keyword, int pageIndex, int pageSize);

	/**
	 * 客服获取vip会员信息列表
	 * 
	 * @param todayAbnormal
	 *            是否今日异常
	 * @param isEndTime
	 *            是否即将过期
	 * @param todayNotMeasure
	 *            是否今日已测量
	 * @param monthNotMeasure
	 *            是否本月已测量
	 * @param gender
	 *            性别
	 * @param startAge
	 *            开始年龄
	 * @param endAge
	 *            结束年龄
	 * @param comboType
	 *            套餐类别
	 * @param status
	 *            会员状态
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Paging<VipUserVO> listVipUserByCustomer(Boolean todayAbnormal, Boolean isEndTime, Boolean todayNotMeasure,
			Boolean monthNotMeasure, Boolean gender, Integer startAge, Integer endAge, Integer comboType, int status,
			String keyword, int pageIndex, int pageSize);

	/**
	 * 激活vip
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月16日 上午11:17:12
	 *
	 * @param userId
	 *            用户id
	 * @param vipComboId
	 *            会员套餐id
	 * @param code
	 *            邀请码
	 * 
	 * @exception OperationException
	 */
	void activateVip(int userId, int vipComboId, String code) throws OperationException;

	/**
	 * 激活vip
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月18日 上午9:58:02
	 *
	 * @param userId
	 *            用户id
	 * @param vipComboId
	 *            vip套餐id
	 * @throws OperationException
	 */
	void activateVip(int userId, int vipComboId) throws OperationException;

	/**
	 * 获取用户的vip
	 * <p>
	 * 目前用户只能购买一项vip套餐服务  2018年3月21日 10:08:42  修改为多个
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年10月25日 上午9:26:21
	 *
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<VipUserPO> getUserVip(int userId);

	/**
	 * vip续费
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年11月1日 下午2:24:05
	 *
	 * @param userId
	 *            用户id
	 * @param vipComboId
	 *            vip套餐id
	 * @exception OperationException
	 */
	void renewalVip(int userId, int vipComboId) throws OperationException;

	/**
	 * 获取过期的vipUser记录
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年11月3日 下午1:42:02
	 *
	 * @param remainDay
	 *            剩余天数
	 * @return
	 */
	List<VipUserPO> listVipUserOutOfEndDate(int remainDay);

	/**
	 * 将vip状态修改为过期
	 * 
	 * @author yuhang.weng
	 * @DateTime 2017年11月3日 上午10:47:35
	 *
	 * @param idList
	 *            id列表
	 * @throws OperationException
	 */
	void expireVip(List<Integer> idList) throws OperationException;

	/**
	 * 根据用户id和套餐项id获取剩余套餐的次数
	 * 
	 * @author zizhen.huang
	 * @DataTime 2017年12月25日15:27:24
	 * 
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @return 剩余套餐次数
	 */
	int getComboNumberById(int userId, int comboId, int comboItemId);

	/**
	 * 添加vip项目记录
	 * 
	 * @author zizhen.huang
	 * 
	 * @param userId
	 *            用户id
	 * @param comboId
	 *            套餐id
	 * @throws OperationException
	 */
	void addVipItemRecord(int userId, int comboId) throws OperationException;

	/**
	 *  客服推送获取有效会员用户
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Paging<StatisticsVO> findByUserVipRecord(int pageIndex,int pageSize);

	/**
	 * 获取用户的vip
	 * <p>
	 * 目前用户只能购买一项vip套餐服务
	 *
	 * @author yuhang.weng
	 * @DateTime 2017年10月25日 上午9:26:21
	 *
	 * @param userId
	 *            用户id
	 * @return
	 */
	VipUserPO getUserVip1(int userId);

	/**
	 *  添加结算记录
	 * @param list
	 * @return
	 */
	boolean addRecordComboData(List<RecordComboVo> list);
	
	List<VipUserPO> findVipUserListWithCondition(Integer userId, Integer status, Integer remainDay);
}
