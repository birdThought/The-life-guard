package com.lifeshs.service1.systemManage.sport;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.SportVo;

public interface SportService {
	
	/**
	 * 获取运动列表
	 * @param kind
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<SportVo> findSport(Integer kind,String name,int curPage,int pageSize);
	
	/**
	 * 添加运动
	 * @param SportVo
	 */
	void addSport(SportVo sportVo) throws OperationException;
	
	/**
	 * 编辑运动
	 * @param SportVo
	 */
	void updateSport(SportVo sportVo)throws OperationException;
	
	/**
	 * 删除运动
	 * @param id
	 */
	void deleteSport(Integer id)throws OperationException;
}
