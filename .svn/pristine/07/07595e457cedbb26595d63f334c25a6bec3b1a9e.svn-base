package com.lifeshs.dao1.order;


import com.lifeshs.po.CustomOrderPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/12/7.
 */
@Repository("iCustomOrderDao")
public interface ICustomOrderDao {

    /**
     * 添加对象
     */
    int addCustom(CustomOrderPo record);


    /**
     * 获取对象 @Param("orderNumber")
     */
    CustomOrderPo getCustomOrder(String orderNumber);

    /**
     *  完成更新
     * @param cp
     */
    int updateCustom(CustomOrderPo cp);

}
