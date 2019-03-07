package com.lifeshs.dao1.business;

import com.lifeshs.po.business.BusinessCardPO;
import com.lifeshs.vo.business.BusinessCardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 渠道商服务卡号dao
 * author: wenxian.cai
 * date: 2017/9/29 11:30
 */
@Repository("businessCardDao")
public interface IBusinessCardDao {

	/**
	 * 添加服务卡号
	 * @param po
	 */
	int addBusinessCard(BusinessCardPO po);

	/**
	 * 更新服务卡号状态
	 * @param code
	 * @param status
	 */
	int updateBusinessCardSatatus(@Param("code") String code, @Param("status") int status);

	/**
	 * 获取服务卡号列表（渠道商id为null时表示为客服查询）
	 * @param keyword 关键词
	 * @param businessId 渠道商id
	 * @return
	 */
	List<BusinessCardVO> findBusinessCardList(@Param("businessId") Integer businessId, @Param("keyword") String keyword, @Param("status") int status,
											  @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * 获取服务卡号数量
	 * @param keyword
	 * @param status
	 * @return
	 */
	int countBusinessCard(@Param("businessId") Integer businessId, @Param("keyword") String keyword, @Param("status") int status);

	/**
	 *  获取一个服务卡
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月16日 上午11:33:30
	 *
	 *  @param code 卡号
	 *  @return
	 */
	BusinessCardVO findBusinessCardByCode(@Param("code") String code);
}
