package com.lifeshs.dao1.comboLevel;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.comboLevel.LevelComboVo;

@Repository(value="levelComboDao")
public interface LevelComboDao {
	
	/**
	 * 通过1级获取2级
	 * 
	 * @param fid
	 * @return
	 */
	List<LevelComboVo> findSecondComboListByFirst(@Param("fid") int fid);
	
    /**
	 *  通过1级获取2级套餐（通过fid）
	 *  @author shiqiang.zeng 
	 *  @DateTime 2017年12月19日 下午6:07:16
	 *
	 *  @param id 套餐id
	 *  @return
	 */
    LevelComboVo getSecondLevelComboByFirst(@Param("fid") int fid);

    /**
     * 获取所有的套餐栏目
     * @return
     */
    List<LevelComboVo> getAllLevel();
}
