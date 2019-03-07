package com.lifeshs.service1.systemManage.food.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.FoodManageDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.food.FoodService;
import com.lifeshs.vo.systemManage.FoodVo;

@Service(value="foodService")
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	FoodManageDao foodManageDao;

	@Override
	public Paging<FoodVo> findFood(Integer kind, String name,int curPage, int pageSize) {
		
		Paging<FoodVo> p =new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<FoodVo>() {
			
			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return foodManageDao.countFood(kind,name);
			}
			
			@Override
			public List<FoodVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return foodManageDao.findFood(kind,name,startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addFood(FoodVo foodVo)throws OperationException {
		
		try {
			foodManageDao.addFood(foodVo);
		} catch (Exception e) {
			throw new OperationException("食物添加失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public void updateFood(FoodVo foodVo) throws OperationException{
		
		try {
			foodManageDao.updateFood(foodVo);
		}catch (Exception e) {
			throw new OperationException("食物编辑失败", ErrorCodeEnum.FAILED);
		}
		
	}

	@Override
	public void deleteFood(Integer id)throws OperationException {
		
		try {
			foodManageDao.deleteFood(id);
		}catch (Exception e) {
			throw new OperationException("食物删除失败", ErrorCodeEnum.FAILED);
		}
		
	}

}
