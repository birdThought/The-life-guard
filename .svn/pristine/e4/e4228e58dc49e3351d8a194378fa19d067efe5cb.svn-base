package com.lifeshs.service1.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.card.CardStatusEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.business.IBusinessCardDao;
import com.lifeshs.po.business.BusinessCardPO;
import com.lifeshs.service.tool.IInvitationCodeService;
import com.lifeshs.service1.business.BusinessCardService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.business.BusinessCardVO;


/**
 * 渠道商服务卡号service
 * author: wenxian.cai
 * date: 2017/9/29 11:18
 */

@Service("businessCardService")
public class BusinessCardServiceImpl implements BusinessCardService {

	static final Logger logger = Logger.getLogger(BusinessCardServiceImpl.class);
	@Autowired
	IBusinessCardDao businessCardDao;
	@Autowired
	IInvitationCodeService iInvitationCodeService;


	@Override
	public BusinessCardPO addBusinessCard(BusinessCardPO po) throws OperationException{
		int result = 0;
		for (int i = 1; i <= 5; i ++) {
			try {
				po.setCode(iInvitationCodeService.createCode());
				result = businessCardDao.addBusinessCard(po);
				return po;
			} catch (DuplicateKeyException e) {
				logger.error("vip邀请码：创建卡号第" + i + "次异常");
			}
		}
		if (result == 0) {
			throw new OperationException("vip邀请码：新增出错", ErrorCodeEnum.FAILED);
		}
		return po;
	}

	@Override
	public List<BusinessCardPO> addBusinessCardBatch(BusinessCardPO po, int amount) throws OperationException {
		List<BusinessCardPO> list = new ArrayList<>();
		for (int j = 0; j < amount; j ++) {
			addBusinessCard(po);
			list.add(po);
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void useBusinessCard(String code) throws OperationException {
//		try {
//			businessCardDao.updateBusinessCardSatatus(code, 2);
//		} catch (Exception e) {
//			throw new OperationException("vip邀请码: 更新状态失败", ErrorCodeEnum.FAILED);
//		}
		int result = businessCardDao.updateBusinessCardSatatus(code, CardStatusEnum.ACTIVED.getStatus());
        if (result == 0) {
            throw new OperationException("vip邀请码: 激活失败", ErrorCodeEnum.NOT_FOUND);
        }
	}

	@Override
	public Paging<BusinessCardVO> listBusinessCard(Integer businessId, String keyword, int status, int pageIndex, int pageSize) {
		Paging<BusinessCardVO> paging = new Paging<>(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc() {
			@Override
			public int queryTotal() {
				return businessCardDao.countBusinessCard(businessId, keyword, status);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				List<BusinessCardVO> list = businessCardDao.findBusinessCardList(businessId, keyword, status, paging.getStartRow(), pageSize);
				for (BusinessCardVO businessCardVO : list) {
					businessCardVO.setPrice(NumberUtils.changeF2Y(businessCardVO.getPrice().intValue() + ""));
				}
				return list;
			}
		});
		return paging;
	}

    @Override
    public BusinessCardVO getBusinessCard(String code) {
        return businessCardDao.findBusinessCardByCode(code);
    }
}
