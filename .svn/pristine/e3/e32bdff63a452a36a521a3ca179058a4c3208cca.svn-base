package com.lifeshs.service1.systemManage.sportKind.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.SportKindDao;
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.sportKind.SportKindService;

@Service(value="sportKindService")
public class SportKindServiceImpl implements SportKindService{
	
	@Autowired
	SportKindDao sportKindDao;
	
	@Override
	public Paging<TDataSportKind> findSportKind(int curPage, int pageSize) {
		
		Paging<TDataSportKind> p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<TDataSportKind>() {
			
			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return sportKindDao.countSportKind();
			}
			
			@Override
			public List<TDataSportKind> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return sportKindDao.findSportKind(startRow,pageSize);
			}
		});
		return p;
	}

	@Override
	public void addSportKind(TDataSportKind tDataSportKind) throws OperationException {
		try {
			sportKindDao.addSportKind(tDataSportKind);
		}catch (Exception e) {
			throw new OperationException("添加运动种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void updateSportKind(TDataSportKind tDataSportKind) throws OperationException {
		
		try {
			sportKindDao.updateSportKind(tDataSportKind);
		}catch (Exception e) {
			throw new OperationException("添加运动种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void deleteSportKind(Integer id) throws OperationException {
		
		try {
			sportKindDao.deleteSportKind(id);
		}catch (Exception e) {
			throw new OperationException("删除运动种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public List<TDataSportKind> getSportKind() {
		// TODO Auto-generated method stub
		return sportKindDao.findsportKind();
	}
	
	
}
