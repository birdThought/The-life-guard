package com.lifeshs.service1.business;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.business.BusinessCardPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.BusinessCardVO;

import java.util.List;

/**
 * 渠道商服务卡号服务
 * author: wenxian.cai
 * date: 2017/9/29 11:16
 */
public interface BusinessCardService {

	/**
	 * 添加服务卡号
	 * @param po
	 */
	BusinessCardPO addBusinessCard(BusinessCardPO po) throws OperationException;

	/**
	 * 批量添加服务卡号
	 * @param po
	 * @throws OperationException
	 */
	List<BusinessCardPO> addBusinessCardBatch(BusinessCardPO po, int amount) throws OperationException;

	/**
	 * 激活服务卡号
	 * @param code
	 */
	void useBusinessCard(String code) throws OperationException;

	/**
	 * 获取服务卡号列表
	 * @param keyword 关键词
	 * @param status 状态
	 * @return
	 */
	Paging<BusinessCardVO> listBusinessCard(Integer businessId, String keyword, int status, int pageIndex, int pageSize);

	/**
	 *  获取服务卡信息
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月16日 上午11:26:42
	 *
	 *  @param code 卡号
	 *  @return
	 */
	BusinessCardVO getBusinessCard(String code);
}
