package com.lifeshs.dao1.combo;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.combo.ComboItemListVo;
import com.lifeshs.vo.combo.ComboVo;

/**
 * 套餐管理
 * @author shiqiang.zeng 
 * @date 2018.1.10
 */
@Repository(value="comboDao")
public interface ComboDao {
    
    /**
     * 根据id获取套餐
     * @param id
     * @return 
     */
    ComboVo getCombovoById(@Param("id") Integer id);
	
	/**
	 * 根据id获取套餐
	 * @param id
	 * @return 
	 */
	int getCombovo(@Param("id") Integer id);
	/**
	 * 统计套餐数量
	 * @return
	 */
	int countCombo();
	/**
	 * 统计套餐里的套餐项目
	 * @param vipComboId
	 * @return
	 */
	int countComboItem(@Param("vipComboId") Integer vipComboId);
	
	/**
	 * 套餐列表
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<ComboVo> findComboList(@Param("startRow") int startRow,@Param("pageSize") int pageSize);
	
	/**
	 * 修改套餐
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param originalPrice
	 * @param photo
	 * @param validDay
	 * @param detail
	 * @param type
	 */
	void updateCombo(@Param("id")Integer id,@Param("name") String name,@Param("description") String description,
					@Param("price") double price,@Param("originalPrice") double originalPrice,@Param("photo") String photo,
					@Param("validDay") Integer validDay,@Param("detail") String detail,@Param("type") Integer type,@Param("l1") String l1);

	/**
	 * 删除套餐
	 * @param id
	 * @return
	 */
	int delCombo(@Param("id") int id);
	
	/**
	 * 	添加套餐内容
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param originalPrice
	 * @param photo
	 * @param validDay
	 * @param detail
	 * @param suitablePeople
	 * @param type
	 * @param createDate
	 * @return
	 */
	/*int addCombo(@Param("name") String name,@Param("description") String description,
				@Param("price") double price,@Param("originalPrice") double originalPrice,@Param("photo") String photo,
				@Param("validDay") int validDay,@Param("detail") String detail,@Param("suitablePeople") String suitablePeople,
				@Param("type") int type,@Param("createDate") Date createDate);*/
	int addCombo(ComboVo combo);
	
	/**
	 * 套餐项目列表
	 * @param id
	 * @return
	 */
	List<ComboItemListVo>findItemList();
	

}
