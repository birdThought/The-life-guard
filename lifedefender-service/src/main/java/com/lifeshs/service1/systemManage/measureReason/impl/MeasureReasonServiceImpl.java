package com.lifeshs.service1.systemManage.measureReason.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.systemManage.MeasureReasonDao;
import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.systemManage.measureReason.MeasureReasonService;
import com.lifeshs.vo.systemManage.MeasureReasonVo;

@Service(value="measureReasonService")
public class MeasureReasonServiceImpl implements MeasureReasonService {

	@Autowired
	MeasureReasonDao measureReasonDao;

	@Override
	public Paging<MeasureReasonVo> findReason(Integer healthPackageParamId, int curPage, int pageSize) {

		Paging<MeasureReasonVo> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<MeasureReasonVo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return measureReasonDao.countReason(healthPackageParamId);
			}

			@Override
			public List<MeasureReasonVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return measureReasonDao.findMessageReason(healthPackageParamId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addReason(MeasureReasonVo measureReasonVo) throws OperationException {

		try {
			measureReasonDao.addReason(measureReasonVo);
		} catch (Exception e) {
			throw new OperationException("添加失败", ErrorCodeEnum.FAILED);
		}

	}

	@Override
	public void updateReason(MeasureReasonVo measureReasonVo) throws OperationException {

		try {
			measureReasonDao.updateReason(measureReasonVo);
		} catch (Exception e) {
			throw new OperationException("更改失败", ErrorCodeEnum.FAILED);
		}

	}

	@Override
	public void deleteReason(Integer id) throws OperationException {

		try {
			measureReasonDao.deleteReason(id);
		} catch (Exception e) {
			throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public List<HealthPackParamPO> getHealthParam() {
		// TODO Auto-generated method stub
		return measureReasonDao.getHealthParamById();
	}

}
