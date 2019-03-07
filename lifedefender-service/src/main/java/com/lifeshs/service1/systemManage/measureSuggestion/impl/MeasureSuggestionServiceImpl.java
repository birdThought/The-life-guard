package com.lifeshs.service1.systemManage.measureSuggestion.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.MeasureSuggestionDao;
import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.measureSuggestion.MeasureSuggestionService;
import com.lifeshs.vo.systemManage.MeasureSuggestionVo;

@Service(value = "measureSuggestionService")
public class MeasureSuggestionServiceImpl implements MeasureSuggestionService {

	@Autowired
	MeasureSuggestionDao measureSuggestionDao;

	@Override
	public Paging<MeasureSuggestionVo> findSuggestion(Integer healthPackageParamId, int curPage, int pageSize) {

		Paging<MeasureSuggestionVo> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<MeasureSuggestionVo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return measureSuggestionDao.countSuggestion(healthPackageParamId);
			}

			@Override
			public List<MeasureSuggestionVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return measureSuggestionDao.findMessageSuggestion(healthPackageParamId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addSuggestion(MeasureSuggestionVo measureSuggestionVo) throws OperationException {
	
		try {
			measureSuggestionDao.addSuggestion(measureSuggestionVo);
		} catch (Exception e) {
			throw new OperationException("添加失败", ErrorCodeEnum.FAILED);
		}

	}

	@Override
	public void updateSuggestion(MeasureSuggestionVo measureSuggestionVo) throws OperationException {

		try {
			measureSuggestionDao.updateSuggestion(measureSuggestionVo);
		} catch (Exception e) {
			throw new OperationException("更改失败", ErrorCodeEnum.FAILED);
		}

	}

	@Override
	public void deleteSuggestion(Integer id) throws OperationException {

		try {
			measureSuggestionDao.deleteSuggestion(id);
		} catch (Exception e) {
			throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public List<HealthPackParamPO> getHealthParam() {
		// TODO Auto-generated method stub
		return measureSuggestionDao.getHealthParamById();
	}

}
