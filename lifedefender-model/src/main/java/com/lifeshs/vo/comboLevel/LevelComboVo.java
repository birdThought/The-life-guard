package com.lifeshs.vo.comboLevel;

import java.util.List;

import com.lifeshs.po.comboLevel.FirstLevelComboPo;
import com.lifeshs.po.comboLevel.SecondLevelComboPo;


/**
 *  等级套餐
 *  @author shiqiang.zeng
 *  @version 1.0
 *  @DateTime 2017年12月20日 上午10:12
 */
public class LevelComboVo extends FirstLevelComboPo {
	
	/**获取第二级套餐列表*/
	public List<SecondLevelComboPo> secondLevelComboList;

	public List<SecondLevelComboPo> getSecondLevelComboList() {
		return secondLevelComboList;
	}

	public void setSecondLevelComboList(List<SecondLevelComboPo> secondLevelComboList) {
		this.secondLevelComboList = secondLevelComboList;
	}
}