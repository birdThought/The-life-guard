package com.lifeshs.service1.levelCombo;

import java.util.List;


import com.lifeshs.vo.comboLevel.LevelComboVo;

public interface LevelComboService {
	
	/**
	 * 获取套餐分类栏目
	 * @return
	 */
	List<LevelComboVo> listLevel();
}
