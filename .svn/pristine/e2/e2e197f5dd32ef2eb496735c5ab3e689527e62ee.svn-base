package com.lifeshs.service1.systemManage.foodKind.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.FoodKindDao;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.foodKind.FoodKindService;

@Service(value="foodKindService")
public class FoodKindServiceImpl implements FoodKindService{
	
	@Autowired
	FoodKindDao foodKindDao;
	
	@Override
	public Paging<TDataFoodKind> findFoodKind(int curPage, int pageSize) {
		
		Paging<TDataFoodKind>p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<TDataFoodKind>() {
			
			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return foodKindDao.countFoodKind();
			}
			
			@Override
			public List<TDataFoodKind> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return foodKindDao.findFoodKind(startRow, pageSize);
			}
		});
		
		return p;
	}

	@Override
	public void addFoodKind(TDataFoodKind tDataFoodKind) throws OperationException {
		try {
			foodKindDao.addFoodKind(tDataFoodKind);
		}catch (Exception e) {
			throw new OperationException("添加食物种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void updateFoodKind(TDataFoodKind tDataFoodKind) throws OperationException {
		
		try {
			foodKindDao.updateFoodKind(tDataFoodKind);
		}catch (Exception e) {
			throw new OperationException("编辑食物种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void deleteFoodKind(Integer id) throws OperationException {
		
		try {
			foodKindDao.deleteFoodKind(id);
		}catch (Exception e) {
			throw new OperationException("删除食物种类失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public List<TDataFoodKind> findfoodKind() {
		// TODO Auto-generated method stub
		return foodKindDao.findfoodkind();
	}

}
