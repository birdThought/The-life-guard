package com.lifeshs.service1.systemManage.sportKind;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.service1.page.Paging;

public interface SportKindService {

	/**
	 * 运动种类列表
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<TDataSportKind> findSportKind(int curPage,int pageSize);
	
	/**
	 * 添加运动种类
	 * @param tDataSportKind
	 * @throws OperationException
	 */
	void addSportKind(TDataSportKind tDataSportKind) throws OperationException;
	
	/**
	 * 更改运动种类
	 * @param tDataSportKind
	 * @throws OperationException
	 */
	void updateSportKind(TDataSportKind tDataSportKind) throws OperationException;
	
	/**
	 * 删除运动种类
	 * @param id
	 * @throws OperationException
	 */
	void deleteSportKind(Integer id)throws OperationException;
	
	/**
	 * 获取运动种类列表
	 * @return
	 */
	List<TDataSportKind> getSportKind();
	
}
