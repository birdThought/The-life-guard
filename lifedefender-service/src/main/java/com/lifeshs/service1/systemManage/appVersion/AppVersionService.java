package com.lifeshs.service1.systemManage.appVersion;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.AppVersionVo;

/**
 * app版本控制方法
 * @author shiqiang.zeng
 * @date 2018.1.30 15:04
 */
public interface AppVersionService {
	
	/**
	 * 获取app版本列表
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<AppVersionVo> findVersion(int curPage,int pageSize);
	
	/**
	 * 添加
	 * @param appVersionVo
	 * @throws OperationException
	 */
	void addVersion(AppVersionVo appVersionVo) throws OperationException;
	
	/**
	 * 更改
	 * @param appVersionVo
	 * @throws OperationException
	 */
	void updateVersion(AppVersionVo appVersionVo) throws OperationException;
	
	/**
	 * 删除
	 * @param id
	 * @throws OperationException
	 */
	void deleteVersion(Integer id) throws OperationException;
}
