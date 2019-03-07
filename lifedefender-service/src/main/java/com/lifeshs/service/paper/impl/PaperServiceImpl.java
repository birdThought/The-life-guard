package com.lifeshs.service.paper.impl;

import java.util.List;

import com.lifeshs.pojo.paper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.paper.IPaperDao;
import com.lifeshs.service.paper.IPaperService;

/**
 *  版权归
 *  TODO 问卷调查实现类
 *  @author wenxian.cai 
 *  @datetime 2017年3月7日下午5:01:34
 */
@Service("PaperService")
public class PaperServiceImpl implements IPaperService{

	@Autowired
	IPaperDao paperDao;
	
	
	@Override
	public List<PaperDTO> listPaper(String type) {
		List<PaperDTO> list = paperDao.listPaper(type);
		List<PaperTypeDTO> typeDTOs = paperDao.listPaperType();
		for (PaperDTO paperDTO : list) {
			for (PaperTypeDTO paperTypeDTO : typeDTOs) {
				if (paperTypeDTO.getId().equals(Integer.parseInt(paperDTO.getTopicType()))) {
					paperDTO.setTopicType(paperTypeDTO.getName());
					break;
				}
			}
		}
		
		return list;
	}

	@Override
	public List<PaperOptionDTO> listPaperOption(String type) {
		List<PaperOptionDTO> list = paperDao.listPaperOption(type);
		return list;
	}

	@Override
	public Boolean addPaperResult(PaperResultDTO paperResultDTO) {
		List<PaperTypeDTO> typeDTOs = paperDao.listPaperType();
		for (PaperTypeDTO paperTypeDTO : typeDTOs) {
			if (paperResultDTO.getPaperTypeName().equals(paperTypeDTO.getName())) {
				if (paperTypeDTO.getParentId() == 0) {
					paperResultDTO.setPaperType(paperTypeDTO.getId());
				}
				
			}
		}
		return paperDao.addPaperResult(paperResultDTO) > 0 ? true : false;
	}

	@Override
	public PaperSubHealthStandardDTO getPaperSubHealthStandard(int score) {
		
		return paperDao.getPaperSubHealthStandard(score);
	}

	@Override
	public PaperPhysicalStandardDTO getPaperPhysicalStandard(String physicalName) {
		
		return paperDao.getPaperPhysicalStandard(physicalName);
	}

	@Override
	public PaperStrokeStandardDTO getPaperStrokeStandard(String physicalName) {
		return paperDao.getPaperStrokeStandard(physicalName);
	}
}
