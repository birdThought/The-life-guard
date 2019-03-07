package com.lifeshs.service1.levelCombo.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lifeshs.dao1.comboLevel.LevelComboDao;
import com.lifeshs.service1.levelCombo.LevelComboService;

import com.lifeshs.vo.comboLevel.LevelComboVo;

@Service(value="levelComboServiceImpl")
public class LevelComboServiceImpl implements LevelComboService {
	
	@Resource(name = "levelComboDao")
	LevelComboDao levelComboDao;

	@Override
	public List<LevelComboVo> listLevel() {
		return levelComboDao.getAllLevel();
	}
}