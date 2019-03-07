package com.lifeshs.dao1.vip;

import java.util.List;

import com.lifeshs.vo.record.ComboOrderVo;
import com.lifeshs.vo.record.RecordComboVo;
import com.lifeshs.vo.record.RecordSpreadComboVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.vo.vip.VipComboVO;

/**
 * vip套餐业务
 * author: wenxian.cai
 * date: 2017/10/11 16:44
 */
@Repository("vipComboDao")
public interface IVipComboDao {

	/**
	 * 获取vip套餐列表
	 * 
	 * @param id列表
	 * @return
	 */
	List<VipComboPO> findVipComboList(@Param("idList") List<Integer> idList);
    
    /**
     *  获取vip套餐列表（添加套餐详细项）
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午2:38:39
     *
     *	@param l1 一级栏目
	 *	@param l2 二级栏目 
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<VipComboVO> findVipComboWithItemList(@Param("l1") String l1, @Param("l2") String l2,
    		@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	/**
	 *  获取vip套餐（添加套餐详细项）
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月18日 上午11:07:16
	 *
	 *  @param id 套餐id
	 *  @return
	 */
    VipComboVO getVipCombo(@Param("id") int id);
	
	/**
	 *  统计vip套餐数量
	 *  @author yuhang.weng 
	 *  @DateTime 2017年10月18日 下午2:39:44
	 *
	 *	@param l1 一级栏目
	 *	@param l2 二级栏目 
	 *  @return
	 */
	int countVipCombo(@Param("l1") String l1, @Param("l2") String l2);


	/**
	 *  获取每个月份的套餐推广数据
	 * @return
	 */
    List<RecordSpreadComboVo> findByOrderComboDataList();

    /**
     *  添加结算记录
     * @param list
     * @return
     */
    boolean saveRecordComboData(@Param("list") List<RecordComboVo> list);

    /**
     * 分页参数
     * @param moon
     * @return
     */
	int findByCountRecordCombo(@Param("moon") String moon,@Param("superior")Integer superior);

    /**
     *  分页参数
     * @param moon
     * @param curPage
     * @param pageSize
     * @return
     */
	List<RecordComboVo> findByRecordComboList(@Param("moon") String moon,@Param("superior") Integer superior,@Param("curPage") int curPage,@Param("pageSize") int pageSize);

	/**
	 *  获取结算详情
	 * @param superior
	 * @param format
	 * @return
	 */
	List<ComboOrderVo> findByRecordDetails(@Param("superior") Integer superior,@Param("format") String format);
	
	/**
	 * 
	 *  获取套餐类型
	 *  @author liaoguo
	 *  @DateTime 2018年5月29日 下午4:12:30
	 *
	 *  @return
	 */
	List<VipComboPO> findL1All();
}
