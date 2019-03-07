package com.lifeshs.service1.systemManage.appVersion.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.AppVersionDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.appVersion.AppVersionService;
import com.lifeshs.vo.systemManage.AppVersionVo;

@Service(value="appVersionService")
public class AppVersionServiceImpl implements AppVersionService{
	
	@Autowired
	AppVersionDao appVersionDao;
	
	@Override
	public Paging<AppVersionVo> findVersion(int curPage, int pageSize) {

		Paging<AppVersionVo> p =new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<AppVersionVo>() {
			
			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return appVersionDao.countVersion();
			}
			
			@Override
			public List<AppVersionVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return appVersionDao.findAppVersion(startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addVersion(AppVersionVo appVersionVo)throws OperationException {
		
//		try {
			appVersionDao.addVersion(appVersionVo);
//		}catch (Exception e) {
//			throw new OperationException("添加失败",ErrorCodeEnum.FAILED);
//		}
		
	}

	@Override
	public void updateVersion(AppVersionVo appVersionVo)throws OperationException {
		
//		try {
			appVersionDao.updateVersion(appVersionVo);
//		}catch (Exception e) {
//			throw new OperationException("更改失败",ErrorCodeEnum.FAILED);
//		}
		
	}

	@Override
	public void deleteVersion(Integer id)throws OperationException {

//		try {
			appVersionDao.deleteVersion(id);
//		}catch (Exception e) {
//			throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
//		}
		
	}

}
