package com.lifeshs.dao1.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.FoodPO;

@Repository(value = "foodDao")
public interface FoodDao {

    /**
     *  获取食物列表
     *  @author yuhang.weng 
     *  @DateTime 2017年9月13日 下午5:46:34
     *
     *  @return
     */
    List<FoodPO> getFoodList();
    
    /**
     *  添加食物
     *  @author yuhang.weng 
     *  @DateTime 2017年9月13日 下午5:46:20
     *
     *  @param foodList 食物列表
     *  @return
     */
    int addFoodList(@Param("foodList") List<FoodPO> foodList);
}
