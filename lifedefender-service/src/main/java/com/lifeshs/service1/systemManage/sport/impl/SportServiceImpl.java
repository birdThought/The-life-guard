package com.lifeshs.service1.systemManage.sport.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.SportManageDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.sport.SportService;
import com.lifeshs.vo.systemManage.SportVo;

@Service(value="sportService")
public class SportServiceImpl implements SportService{
	
	@Autowired
	SportManageDao sportManageDao;

	@Override
	public Paging<SportVo> findSport(Integer kind, String name, int curPage, int pageSize) {
		
		Paging<SportVo> p =new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<SportVo>() {
			
			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return sportManageDao.countSport(kind, name);
			}
			
			@Override
			public List<SportVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return sportManageDao.findSport(kind, name, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addSport(SportVo sportVo) throws OperationException {
		
		try {
			sportManageDao.addSport(sportVo);
		}catch (Exception e) {
			throw new OperationException("运动添加失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void updateSport(SportVo sportVo) throws OperationException {
		
		try {
			sportManageDao.updateSport(sportVo);
		}catch (Exception e) {
			throw new OperationException("运动编辑失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void deleteSport(Integer id) throws OperationException {
		
		try {
			sportManageDao.deleteSport(id);
		}catch (Exception e) {
			throw new OperationException("运动删除失败", ErrorCodeEnum.FAILED);
		}
	}

}
