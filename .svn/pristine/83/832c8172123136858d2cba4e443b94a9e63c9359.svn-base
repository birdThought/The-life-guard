package com.lifeshs.service1.vip;

import java.util.List;

import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.record.ComboOrderVo;
import com.lifeshs.vo.record.RecordComboVo;
import com.lifeshs.vo.record.RecordSpreadComboVo;
import com.lifeshs.vo.vip.VipComboVO;

/**
 * vip套餐业务
 * author: wenxian.cai
 * date: 2017/10/11 16:50
 */
public interface IVipComboService {

	/**
	 * 获取vip套餐列表
	 * 
	 * @param idList id列表
	 * @return
	 */
	List<VipComboPO> findVipComboList(List<Integer> idList);
	
	/**
	 *  获取vip套餐
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月18日 上午11:10:17
	 *
	 *  @param id 套餐id
	 *  @return
	 */
	VipComboVO getVipCombo(int id);
	
	/**
	 *  服务注解
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月18日 下午2:37:04
	 *
	 *	@param l1 一级栏目
	 *	@param l2 二级栏目 
	 *  @param curPage
	 *  @param pageSize
	 *  @return
	 */
	Paging<VipComboVO> listVipCombo(String l1, String l2, int curPage, int pageSize);

    /**
     *  获取本月推广套餐的数据
     * @return
     */
    List<RecordSpreadComboVo> getOrderComboData();

	/**
	 *  获取列表
	 * @param moon
	 * @param page
	 * @param pagesize
	 * @return
	 */
    Paging<RecordComboVo> findByRecordComboList(String moon,Integer superior,Integer page, Integer pagesize);

	/**
	 *  获取结算详情
	 * @param superior
	 * @param format
	 * @return
	 */
	List<ComboOrderVo> findByComboOrderDetails(Integer superior, String format);
}
