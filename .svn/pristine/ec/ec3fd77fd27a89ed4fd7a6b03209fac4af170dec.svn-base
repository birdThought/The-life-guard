package com.lifeshs.service1.electronicCoupons.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.dao.serve.ServeDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.electronicCoupons.CouponsStatusEnum;
import com.lifeshs.common.constants.common.electronicCoupons.OverdueModelEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsDao;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageRecordService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.electronicCoupons.CouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

@Service(value = "electronicCouponsService")
public class ElectronicCouponsServiceImpl implements ElectronicCouponsService {

    @Resource(name = "electronicCouponsPackageService")
    private ElectronicCouponsPackageService packageService;
    
    @Resource(name = "electronicCouponsDao")
    private ElectronicCouponsDao electronicCouponsDao;
    
    @Resource(name = "electronicCouponsPackageRecordService")
    private ElectronicCouponsPackageRecordService recordService;
    
    @Autowired
    private IMemberService memberService;

    @Autowired
    private ServeDao serveDao;
    
    @Override
    public void addCoupons(String packageCode, int userId) throws OperationException {
    	ElectronicCouponsPackageVO electronicCouponsPackageVO = packageService.getPackage(packageCode);
    	if (electronicCouponsPackageVO == null) {
    	    throw new OperationException("找不到该卡包信息", ErrorCodeEnum.NOT_FOUND);
    	}
    	int packageId = electronicCouponsPackageVO.getId();
    	boolean hasRecord = recordService.hasRecord(userId, packageId);
    	if (hasRecord) {
    	    throw new OperationException("请勿重复领取该卡包", ErrorCodeEnum.FAILED);
    	}
    	
    	// 获取用户的引入渠道商信息
    	// 领的时候判断用户是否绑定过渠道商，如果有绑定过就不能领了。因为一个用户只能被一个渠道商引入
    	UserDTO user = memberService.getUser(userId);
    	Integer businessId = user.getBusinessId();
    	if (businessId != null && !businessId.equals(electronicCouponsPackageVO.getBusinessId())) {
    	    throw new OperationException("无法引入其它渠道商", ErrorCodeEnum.FAILED);
    	}
    	// 为用户引入渠道商
    	memberService.updateBusinessId(userId, electronicCouponsPackageVO.getBusinessId());
    	
    	List<CouponsTempletVO> electronicCouponsTempletPOList =  electronicCouponsPackageVO.getTempletList();
    	List<UserElectronicCouponsPO> couponsPOList = new ArrayList<>();
    	for(CouponsTempletVO electronicCouponsTempletPO : electronicCouponsTempletPOList) {
    		UserElectronicCouponsPO userElectronicCouponsPO = new UserElectronicCouponsPO();
    		userElectronicCouponsPO.setUserId(userId);
    		userElectronicCouponsPO.setStatus(CouponsStatusEnum.USEABLE.getValue());
    		userElectronicCouponsPO.setTempletId(electronicCouponsTempletPO.getId());
    		userElectronicCouponsPO.setServeCode(electronicCouponsTempletPO.getServeCode());
    		userElectronicCouponsPO.setServeItemId(electronicCouponsTempletPO.getServeItemId());
    		userElectronicCouponsPO.setPrice(electronicCouponsTempletPO.getPrice());
    		userElectronicCouponsPO.setOrgId(electronicCouponsTempletPO.getOrgId());
    		userElectronicCouponsPO.setName(electronicCouponsTempletPO.getName());
    		userElectronicCouponsPO.setProjectCode(electronicCouponsTempletPO.getProjectCode());
    		int overdueModel = electronicCouponsTempletPO.getOverdueModel();
    		Date endDate = null;
    		// 判断overdueModel的值
    		if (OverdueModelEnum.ETERNAL.getValue() == overdueModel) {
    		    // 0_不会失效 endDate设置为null
    		}
    		if (OverdueModelEnum.END_DATE.getValue() == overdueModel) {
    		    // 1_限制时间 endDate为模板的endDate
    		    endDate = electronicCouponsTempletPO.getEndDate();
    		}
    		if (OverdueModelEnum.RECKON_AFTER_RECEIVE.getValue() == overdueModel) {
    		    // 2_领券后计时
    		    // 将有效天数转换成LocalDate类型
                Instant instant = new Date().toInstant();
                ZoneId zone = ZoneId.systemDefault();
                LocalDateTime localStartDate = LocalDateTime.ofInstant(instant, zone);
                LocalDateTime localEndDate = localStartDate.plusDays(electronicCouponsTempletPO.getValidDay());
                Instant endInstant = localEndDate.atZone(zone).toInstant();
                endDate = Date.from(endInstant);
    		}
    		userElectronicCouponsPO.setEndDate(endDate);
    		couponsPOList.add(userElectronicCouponsPO);
    	}
    	// 调用electronicCouponsDao.addCouponsList(List<UserElectronicCouponsPO>)批量添加
    	electronicCouponsDao.addCouponsList(couponsPOList);
    	
    	// 添加成功之后需要添加一条记录
    	recordService.addRecord(userId, packageId);
    }

    @Override
    public void useCoupons(int id, int userId, String serveCode, String projectCode, int serveItemId) throws BaseException {
        UserElectronicCouponsPO couponsPO = electronicCouponsDao.getCoupons(id);
        if (couponsPO == null) {
            throw new OperationException("找不到该电子券信息", ErrorCodeEnum.NOT_FOUND);
        }
        int status = couponsPO.getStatus();
        if (CouponsStatusEnum.USEABLE.getValue() != status) {
            throw new OperationException("电子券状态为不可使用", ErrorCodeEnum.FAILED);
        }
        if (StringUtils.isBlank(serveCode)) {
            throw new ParamException("服务code不能为空", ErrorCodeEnum.MISSING);
        }
        if (serveDao.getServeType(Integer.parseInt(serveCode)) == null) {
            throw new OperationException("服务不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (!StringUtils.equals(couponsPO.getProjectCode(), projectCode)) {
            throw new OperationException("电子券不支持该服务使用", ErrorCodeEnum.FAILED);
        }
        Integer couponServeItemId = couponsPO.getServeItemId();
        // 如果电子券的具体服务id有设置，需要判断是否相同
        if (couponServeItemId != null && couponServeItemId.intValue() != serveItemId) {
            throw new OperationException("电子券不支持该服务使用", ErrorCodeEnum.FAILED);
        }
        
    	UserElectronicCouponsPO userElectronicCouponsPO = new UserElectronicCouponsPO();
    	userElectronicCouponsPO.setUserId(userId);
    	userElectronicCouponsPO.setId(id);
    	userElectronicCouponsPO.setStatus(CouponsStatusEnum.USING.getValue());
    	int result = electronicCouponsDao.updateCoupons(userElectronicCouponsPO);
    	if (result == 0) {
    	    throw new OperationException("使用电子券失败", ErrorCodeEnum.FAILED);
    	}
    }

    @Override
    public void finishCoupons(int id, int userId, String projectCode, int serveItemId) throws OperationException {
        UserElectronicCouponsPO couponsPO = electronicCouponsDao.getCoupons(id);
        if (couponsPO == null) {
            throw new OperationException("找不到该电子券信息", ErrorCodeEnum.NOT_FOUND);
        }
        int status = couponsPO.getStatus();
        if (CouponsStatusEnum.USEABLE.getValue() != status && CouponsStatusEnum.USING.getValue() != status) {
            throw new OperationException("电子券状态为不可修改", ErrorCodeEnum.FAILED);
        }
        if (!StringUtils.equals(couponsPO.getProjectCode(), projectCode)) {
            throw new OperationException("电子券不支持该服务使用", ErrorCodeEnum.FAILED);
        }
        // 如果电子券的具体服务id有设置，需要判断是否相同
        Integer couponServeItemId = couponsPO.getServeItemId();
        if (couponServeItemId != null && couponServeItemId.intValue() != serveItemId) {
            throw new OperationException("电子券不支持该服务使用", ErrorCodeEnum.FAILED);
        }
        
    	UserElectronicCouponsPO userElectronicCouponsPO = new UserElectronicCouponsPO();
    	userElectronicCouponsPO.setUserId(userId);
    	userElectronicCouponsPO.setId(id);
    	userElectronicCouponsPO.setStatus(CouponsStatusEnum.USED.getValue());
    	int result = electronicCouponsDao.updateCoupons(userElectronicCouponsPO);
        if (result == 0) {
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public Paging<UserElectronicCouponsPO> listCoupons(int userId, int curPage, int pageSize) {
        // TODO Auto-generated method stub
    	Paging<UserElectronicCouponsPO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<UserElectronicCouponsPO>() {

			@Override
			public int queryTotal() {
				return electronicCouponsDao.countCouponsWithCondition(userId, null, null, null, null);
			}

			@Override
			public List<UserElectronicCouponsPO> queryData(int startRow, int pageSize) {
				return electronicCouponsDao.findCouponsListWithCondition(userId, null, null, null, null, startRow, pageSize);
			}
		});
		return p;
    }

    @Override
    public Paging<UserElectronicCouponsPO> listCouponsUsable(int userId, ProjectType projectType, String projectCode, Integer serveItemId,
                                                             int curPage, int pageSize) {
    	Paging<UserElectronicCouponsPO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<UserElectronicCouponsPO>() {

			@Override
			public int queryTotal() {
				return electronicCouponsDao.coutUsableCoupons(userId, projectType.getValue(), projectCode, serveItemId);
			}

			@Override
			public List<UserElectronicCouponsPO> queryData(int startRow, int pageSize) {
				return electronicCouponsDao.findUsableCouponsList(userId, projectType.getValue(), projectCode, serveItemId, startRow, pageSize);
			}
		});
		return p;
    }

    @Override
    public List<UserElectronicCouponsPO> listCouponsOutOfEndDate(int validDay) {
        // TODO Auto-generated method stub
        return electronicCouponsDao.findCouponsListOutOfEndDate(validDay);
    }

    @Override
    public UserElectronicCouponsPO getCoupons(int id) {
        return electronicCouponsDao.getCoupons(id);
    }

    @Override
    public void updateCouponsOutOfEndDate(List<Integer> idList) throws ParamException {
        if (idList.isEmpty()) {
            throw new ParamException("id不能为空", ErrorCodeEnum.MISSING);
        }
        electronicCouponsDao.updateCouponsListStatus(idList, CouponsStatusEnum.OUT_OF_END_DATE.getValue());
    }

}
