package com.lifeshs.service1.vip.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import javax.annotation.Resource;

import com.lifeshs.dao1.vip.IVipComboDao;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.record.RecordComboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.BusinessConstant;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.card.CardStatusEnum;
import com.lifeshs.common.constants.common.order.VipOrderTypeEnum;
import com.lifeshs.common.constants.common.vip.VipStatusEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.vip.IVipUserDao;
import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.service1.business.BusinessCardService;
import com.lifeshs.service1.member.PhysicalItemService;
import com.lifeshs.service1.order.famousDoctor.FamousDoctorOrderService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.service1.vip.IVipWorkOrderService;
import com.lifeshs.vo.business.BusinessCardVO;
import com.lifeshs.vo.vip.VipComboVO;
import com.lifeshs.vo.vip.VipUserVO;

/**
 * vip会员业务实现
 * author: wenxian.cai
 * date: 2017/9/29 9:52
 */

@Service("vipUserService")
public class VipUserServiceImpl implements IVipUserService{

	@Autowired
	IVipUserDao vipUserDao;

	@Resource(name = "vipComboDao")
    IVipComboDao vipComboDao;
	
	@Resource(name = "businessCardService")
	private BusinessCardService cardService;

	@Resource(name = "vipUserOrderServiceImpl")
	private VipUserOrderService orderService;
	
	@Resource(name = "vipComboService")
	private IVipComboService vipComboService;
	
	@Resource(name = "userPhysicalItemServiceImpl")
	private PhysicalItemService userPhysicalItemService;
	
	@Resource(name = "famousDoctorOrderServiceImpl")
    private FamousDoctorOrderService famousDoctorOrderService;
	
	@Resource(name = "vipWorkOrderService")
	private IVipWorkOrderService vipWorkOrderService;

	
	@Override
	public Paging<VipUserVO> listVipUserByBusiness(int businessId, Boolean isEndTime, Boolean gender, Integer startAge, Integer endAge,
												   Integer comboType, Integer status, String keyword, int pageIndex, int pageSize) {
		Paging paging = new Paging(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc() {
			@Override
			public int queryTotal() {
				return vipUserDao.countVipUserByBusiness(businessId, isEndTime, gender,startAge, endAge, comboType, status, keyword);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				List list = vipUserDao.findVipUserListByBusiness(businessId, isEndTime, gender,startAge, endAge, comboType, status, keyword, paging.getStartRow(), pageSize);
				return list;
			}
		});
		return paging;
	}

	@Override
	public Paging<VipUserVO> listVipUserByCustomer(Boolean todayAbnormal, Boolean isEndTime, Boolean todayNotMeasure, Boolean monthNotMeasure, Boolean gender,
												   Integer startAge, Integer endAge, Integer comboType, int status, String keyword, int pageIndex, int pageSize) {
		Paging paging = new Paging(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc() {
			@Override
			public int queryTotal() {
				return vipUserDao.countVipUserByCustomer(gender,startAge, endAge, comboType, status, keyword,
						todayAbnormal, isEndTime, todayNotMeasure, monthNotMeasure);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				List list = vipUserDao.findVipUserListByCustomer(gender,startAge, endAge, comboType, status, keyword,
						todayAbnormal, isEndTime, todayNotMeasure, monthNotMeasure, startRow, pageSize);
				return list;
			}
		});
		return paging;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void activateVip(int userId, int vipComboId, String code) throws OperationException {
	    // 检查激活码是否存在
        BusinessCardVO card = cardService.getBusinessCard(code);
        if (card == null) {
            throw new OperationException("激活码不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (CardStatusEnum.Normal.getStatus() != card.getStatus()) {
            throw new OperationException("激活码已失效", ErrorCodeEnum.FAILED);
        }
        if (card.getVipComboPO().getId() != vipComboId) {
            throw new OperationException("激活码与会员套餐不符合", ErrorCodeEnum.FAILED);
        }

        // 检查用户是vip
        /**
         * 非vip -> 激活码             √
         * 激活码vip -> 激活码     x
         * 充值vip -> 激活码         x
         */
        // 查找未过期的vip服务
        List<VipUserPO> vipUserList = vipUserDao.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        
        for (VipUserPO vipUser : vipUserList) {
            // 如果会员服务未过期，无法重复使用激活码
            int vipUserVipComboId = vipUser.getVipComboId();
            if (vipUserVipComboId == vipComboId) {
                throw new OperationException("该会员服务尚未过期，请勿重复使用激活码", ErrorCodeEnum.REPEAT);
            }
            if (vipUserVipComboId != vipComboId) {
                // 一个用户只能激活一项套餐 2017年10月24日16:54:32
                throw new OperationException("暂不支持激活多项vip套餐", ErrorCodeEnum.FAILED);
            }
        }

        // 激活卡号
        cardService.useBusinessCard(code);
        
        // 计算截止日期
        int validDay = card.getVipComboPO().getValidDay();
        LocalDateTime endLocalDateTime = LocalDateTime.now().plusDays(validDay);
        Date endTime = Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        // 添加vip用户
        VipUserPO user = new VipUserPO();
        user.setUserId(userId);
        user.setEndTime(endTime);
        user.setStatus(VipStatusEnum.NORMAL.getValue());
        user.setVipComboId(vipComboId);
        int result = vipUserDao.addVipUser(user);
        if (result == 0) {
            throw new OperationException("添加vip用户失败", ErrorCodeEnum.FAILED);
        }
        
        // 激活码激活 需要设置利润分成 
        int price = card.getVipComboPO().getPrice();
        int businessIncome = Double.valueOf(price * BusinessConstant.PROFIT_SHARE).intValue();
        
        // 添加vip订单
        orderService.addOrder(userId, vipComboId, OrderStatus.COMPLETED, "激活卡卡号" + code,
                card.getId(), VipOrderTypeEnum.CODE, businessIncome, card.getBusinessId());
        
        // 添加服务次数记录
        addVipItemRecord(userId, vipComboId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void activateVip(int userId, int vipComboId) throws OperationException {
        VipComboPO vipComboPO = vipComboService.getVipCombo(vipComboId);
        if (vipComboPO == null) {
            throw new OperationException("该vip套餐不存在", ErrorCodeEnum.NOT_FOUND);
        }
        int validDay = vipComboPO.getValidDay();
        
        // 获取用户有效的vip服务列表
        List<VipUserPO> vipUserList = vipUserDao.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        
        // 一个用户只能激活一项套餐 2017年10月24日16:54:32
        for (VipUserPO vip : vipUserList) {
            int comboId = vip.getVipComboId();
            if (comboId == vipComboId) {
                throw new OperationException("请使用续费vip服务", ErrorCodeEnum.REQUEST);
            }
            if (comboId != vipComboId) {
                throw new OperationException("暂不支持激活多项vip套餐", ErrorCodeEnum.FAILED);
            }
        }
        
//        for (VipUserPO vip : vipUserList) {
//            int comboId = vip.getVipComboId();
//            if (vipComboId != comboId) {
//                continue;
//            }
//            int id = vip.getId();    // 用户vip id
//            
//            int vipStatus = vip.getStatus();
//            // 如果会员状态是过期，修改为激活，然后结束日期按照套餐的有效天数计算
//            if (VipStatusEnum.OVER.getValue() == vipStatus) {
//                LocalDateTime endLocalDateTime = LocalDateTime.now().plusDays(validDay);
//                Date endTime = Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
//                
//                VipUserPO vipUserPO = new VipUserPO();
//                vipUserPO.setId(id);
//                vipUserPO.setStatus(VipStatusEnum.NORMAL.getValue());
//                vipUserPO.setEndTime(endTime);
//                int result = vipUserDao.updateVipUser(vipUserPO);
//                if (result == 0) {
//                    throw new OperationException("重新激活会员失败", ErrorCodeEnum.FAILED);
//                }
//            }
            // 如果会员状态是激活，保持激活状态，然后结束日期往上叠加
//            if (VipStatusEnum.NORMAL.getValue() == vipStatus) {
//                LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(vip.getEndTime().toInstant(), ZoneId.systemDefault());
//                LocalDateTime endLocalDateTime = startLocalDateTime.plusDays(validDay);
//                Date endTime = Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
//                
//                VipUserPO vipUserPO = new VipUserPO();
//                vipUserPO.setId(id);
//                vipUserPO.setEndTime(endTime);
//                int result = vipUserDao.updateVipUser(vipUserPO);
//                if (result == 0) {
//                    throw new OperationException("延长会员期限失败", ErrorCodeEnum.FAILED);
//                }
//            }
//            return; // 跳出方法
//        }
        
        // 添加一条新的记录
        LocalDateTime endLocalDateTime = LocalDateTime.now().plusDays(validDay);
        Date endTime = Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        VipUserPO vipUserPO = new VipUserPO();
        vipUserPO.setUserId(userId);
        vipUserPO.setStatus(VipStatusEnum.NORMAL.getValue());
        vipUserPO.setVipComboId(vipComboId);
        vipUserPO.setEndTime(endTime);
        
        int result = vipUserDao.addVipUser(vipUserPO);
        if (result == 0) {
            throw new OperationException("激活会员失败", ErrorCodeEnum.FAILED);
        }
        
    }

   /* @Override  废弃
    public List<String> getVipName() {
        return vipComboDao.findVipNameList();
    }*/

    @Override
    public List<VipUserPO> getUserVip(int userId) {
        List<VipUserPO> vipList = vipUserDao.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        // 一个用户只能激活一项套餐 2017年10月24日16:54:32
       /* if (vipList.size() > 0) {
            return vipList.get(0);
        }*/
        return vipList;
    }

    @Override
    public VipUserPO getUserVip1(int userId) {
        List<VipUserPO> vipUserList = vipUserDao.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        if (vipUserList.size() > 0){
            return vipUserList.get(0);
        }
        return null;
    }

    @Override
    public void renewalVip(int userId, int vipComboId) throws OperationException {
        VipComboPO vipComboPO = vipComboService.getVipCombo(vipComboId);
        if (vipComboPO == null) {
            throw new OperationException("该vip套餐不存在", ErrorCodeEnum.NOT_FOUND);
        }
        int validDay = vipComboPO.getValidDay();
        
        /**
         * 1. 查询用户的vip状态，如果不是vip用户，抛出异常提示用户不符合续费要求
         * 2. 修改用户的vip时长
         */
        
        VipUserPO vipUserTemp = null;
        // 获取用户有效的vip服务列表
        List<VipUserPO> vipUserList = vipUserDao.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        for (VipUserPO vip : vipUserList) {
            int comboId = vip.getVipComboId();
            if (comboId != vipComboId) {
                throw new OperationException("暂不支持激活多项vip套餐", ErrorCodeEnum.FAILED);
            }
            vipUserTemp = vip;
        }
        
        if (vipUserTemp == null) {
            throw new OperationException("不符合续费要求", ErrorCodeEnum.REQUEST);
        }
        // 计算新的结束日期
        LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(vipUserTemp.getEndTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime endLocalDateTime = startLocalDateTime.plusDays(validDay);
        Date endTime = Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        VipUserPO vipUserPO = new VipUserPO();
        vipUserPO.setId(vipUserTemp.getId());
        vipUserPO.setEndTime(endTime);
        int result = vipUserDao.updateVipUser(vipUserPO);
        if (result == 0) {
            throw new OperationException("续费失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void expireVip(List<Integer> idList) throws OperationException {
        if (idList == null || idList.isEmpty()) {
            throw new OperationException("参数异常", ErrorCodeEnum.FORMAT);
        }
        List<VipUserPO> dataList = new ArrayList<>();
        for (Integer id : idList) {
            VipUserPO data = new VipUserPO();
            data.setId(id);
            data.setStatus(VipStatusEnum.OVER.getValue());
            dataList.add(data);
        }
        vipUserDao.updateVipUserStatusList(dataList);
    }

    @Override
    public List<VipUserPO> listVipUserOutOfEndDate(int remainDay) {
        return vipUserDao.findVipUserListWithCondition(null, VipStatusEnum.NORMAL.getValue(), remainDay);
    }

	@Override
	public int getComboNumberById(int userId,int comboId, int comboItemId) {
		Integer count = vipUserDao.getComboNumberById(userId, comboId, comboItemId);
		return count == null ? 0 : count;
	}

	@Override
	public void addVipItemRecord(int userId, int comboId) throws OperationException {
		VipComboVO combo = vipComboService.getVipCombo(comboId);
		for (VipComboItemPO item : combo.getItemList()) {
			Integer number = item.getNumber();
			if (number == null || number == 0) {
				continue;
			}
			int itemId = item.getId();
			//Integer recordNumber =vipWorkOrderService.findComboNumberById(userId, itemId);
			Integer recordNumber = vipWorkOrderService.findComboNumberById(userId, comboId, itemId);
			if (recordNumber != null && recordNumber != 0) {
				recordNumber += number;
				vipWorkOrderService.updateComboNumber(userId, comboId, itemId, recordNumber);
				continue;
			}
			vipWorkOrderService.addComboNumber(userId, comboId, itemId, number);
			
		}
	}

    @Override
    public Paging<StatisticsVO> findByUserVipRecord(int pageIndex, int pageSize) {
        Paging paging = new Paging(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<StatisticsVO>() {
            @Override
            public int queryTotal() {
                return vipUserDao.getUserVipCount();
            }

            @Override
            public List<StatisticsVO> queryData(int startRow, int pageSize) {
                List<StatisticsVO> byUserVipList = vipUserDao.findByUserVipList(startRow, pageSize);
                Calendar cal = Calendar.getInstance();
                Calendar cld = Calendar.getInstance();
                List<StatisticsVO> list = new ArrayList<>();
                for (StatisticsVO vo : byUserVipList){
                    Date birthday = vo.getBirthday();
                    cld.setTime(new Date());//当前
                    if (birthday != null) {
                        cal.setTime(birthday);//生日
                    vo.setAge(cld.get(Calendar.YEAR) - cal.get(Calendar.YEAR));
                    }
                    // 直接返回
                    list.add(vo);
                }
                return list;
            }
        });
        return paging;
    }

    @Override
    public boolean addRecordComboData(List<RecordComboVo> list) {
        return vipComboDao.saveRecordComboData(list);
    }
    
    @Override
    public List<VipUserPO> findVipUserListWithCondition(Integer userId, Integer status, Integer remainDay){
        return vipUserDao.findVipUserListWithCondition(userId, status, remainDay);
    }
    
}
