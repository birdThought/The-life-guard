package com.lifeshs.service1.consult.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.consult.IConsultManagerDao;
import com.lifeshs.po.consult.InformationColumnPO;
import com.lifeshs.po.consult.InformationPO;
import com.lifeshs.service1.consult.ConsultManagerService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.consult.InformationColumnVO;
import com.lifeshs.vo.consult.InformationVO;

@Service(value = "consultManagerService")
public class ConsultManagerServiceImpl implements ConsultManagerService {

    @Resource(name = "consultManagerDao")
	private IConsultManagerDao consultManagerDao;

	@Override
	public List<InformationColumnVO> getColumnListById(Integer parentId) {
		return consultManagerDao.getColumnListById(parentId);
	}

	@Override
	public Paging<InformationPO> getInformationListById(Integer parentId, int curPage, int pageSize) {
		
		List<InformationColumnVO> childColumnList = consultManagerDao.getColumnListById(parentId);
		List<Integer> columnListCondition = new ArrayList<>();
		if (childColumnList.isEmpty()) {
			columnListCondition.add(parentId);
		} else {
			for (InformationColumnVO c : childColumnList) {
				columnListCondition.add(c.getId());
			}
		}
		
		final List<Integer> condition = columnListCondition;
		
		Paging<InformationPO> paging = new Paging<>(curPage, pageSize);
		paging.setQueryProc(new IPagingQueryProc<InformationPO>() {

			@Override
			public int queryTotal() {
				return consultManagerDao.getTotalRecord(condition);
			}

			@Override
			public List<InformationPO> queryData(int startRow, int pageSize) {
				return consultManagerDao.getInformationListById(condition, startRow, pageSize);
			}
		});
		return paging;
	}

	@Override
	public Paging<InformationPO> getSecondInformationListById(Integer id, int curPage, int pageSize) {
		Paging<InformationPO> paging = new Paging<>(curPage, pageSize);
		paging.setQueryProc(new IPagingQueryProc<InformationPO>() {

			@Override
			public int queryTotal() {
				return consultManagerDao.getSecondTotalRecord(id);
			}

			@Override
			public List<InformationPO> queryData(int startRow, int pageSize) {
				return consultManagerDao.getSecondInformationListById(id, startRow, pageSize);
			}
		});
		return paging;
	}

	@Override
	public boolean addInformation(InformationPO informationPO) {
		informationPO.setImage(ImageUtilV2.get3IndexImgFromHtml(informationPO.getContent()));
		int result = consultManagerDao.addInformation(informationPO);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delInformationById(Integer id) {
		int result = consultManagerDao.delInformationById(id);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateInformation(InformationPO informationPO) {
		informationPO.setImage(ImageUtilV2.get3IndexImgFromHtml(informationPO.getContent()));
		int result = consultManagerDao.updateInformation(informationPO);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public InformationVO getInformationById(Integer id) {
		InformationVO data = consultManagerDao.getInformationById(id);
		if (data != null) {
			int parentColumnId = data.getParentColumnId();
			if (parentColumnId == 0) {
				data.setParentColumnId(data.getColumnId());
			}
		}
		return data;
	}

	@Override
	public boolean addColumn(InformationColumnPO informationColumnPO) {
		int result = consultManagerDao.addColumn(informationColumnPO);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delColumnById(Integer id) {
		int result = consultManagerDao.delColumnById(id);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateColumn(InformationColumnPO informationColumnPO) {
		int result = consultManagerDao.updateColumn(informationColumnPO);
		if(result == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<InformationColumnPO> getChildColumnById(Integer id) {
		return consultManagerDao.getChildColumnById(id);
	}

}
