package com.lifeshs.dao1.combo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.vip.VipComboItemPO;

/**
 * 套餐管理
 * @author shiqiang.zeng 
 * @date 2018.1.10
 */
@Repository(value="comboItemDao")
public interface ComboItemDao {
	
    /**
     * 统计套餐数量
     * @return
     */
    int countComboItem();
    
    /**
     * 套餐列表
     * @param startRow
     * @param pageSize
     * @return
     */
    List<VipComboItemPO> listComboItem(@Param("startRow") int startRow,@Param("pageSize") int pageSize);
    
    /**
     * 
     *  添加套餐内容
     *  @author liaoguo
     *  @DateTime 2018年6月1日 下午5:50:26
     *
     *  @param comboItem
     *  @return
     */
    int addComboItem(VipComboItemPO comboItem);
    
    /**
     * 
     *  修改套餐项目
     *  @author liaoguo
     *  @DateTime 2018年6月1日 下午5:51:41
     *
     *  @param id
     *  @param name
     *  @param remark
     *  @param icon
     *  @param itemDetail
     */
    void updateComboItem(@Param("id")Integer id,@Param("name") String name,@Param("remark") String remark,
                    @Param("icon") String icon,@Param("itemDetail") String itemDetail);

    /**
     * 删除套餐
     * @param id
     * @return
     */
    int delComboItem(@Param("comboItemId") Integer comboItemId);

}
