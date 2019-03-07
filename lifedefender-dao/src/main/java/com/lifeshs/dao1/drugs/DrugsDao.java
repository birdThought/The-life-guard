package com.lifeshs.dao1.drugs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.DrugsTypePO;
import com.lifeshs.po.drugs.OrderPO;
import com.lifeshs.po.drugs.OrderProductPO;
import com.lifeshs.po.drugs.PrescriptionType;
import com.lifeshs.po.drugs.ProductAttribute;

/**
 * 
 * 药品管理
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年6月6日 下午4:59:19
 */
@Repository(value = "drugsDao")
public interface DrugsDao {
    
    //查找药品类型
    public List<DrugsTypePO> findDrusType();
    
    /**
     * 客户端
     * 统计套餐数量
     * 
     * @return
     */
    int countLocalDrugs(@Param(value = "productName") String productName,
            @Param(value = "productAttribute") String productAttribute,
            @Param(value = "prescriptionType") String prescriptionType);

    /**
     * 客户端
     * 套餐列表
     * 
     * @param startRow
     * @param pageSize
     * @return
     */
    List<DrugsPO> listLocalDrugs(@Param(value = "productName") String productName,
            @Param(value = "productAttribute") String productAttribute,
            @Param(value = "prescriptionType") String prescriptionType, @Param("startRow") int startRow,
            @Param("pageSize") int pageSize);
    
    
    /** 应用端药品app接口 */
    List<DrugsPO> findDrusListByName(@Param(value = "productName") String productName,
                                     @Param(value = "firstClassName") String firstClassName,
                                     @Param(value = "secondClassName") String secondClassName,
                                     @Param("startRow") int startRow, 
                                     @Param("pageSize") int pageSize);

    
    /**
     * 管理端
     *  添加订单
     *  @author liaoguo
     *  @DateTime 2018年6月13日 下午4:50:36
     *
     *  @param orderPO
     *  @return
     */
    public int addLocalOrder(OrderPO orderPO);
    
    /**
     * 
     *  添加产品
     *  @author liaoguo
     *  @DateTime 2018年6月14日 上午9:26:03
     *
     *  @param orderProduct
     *  @return
     */
    public int addOrderProduct(@Param(value = "orderProduct") List<OrderProductPO> orderProduct);
    
    
    public DrugsPO getDrugsByCode(@Param(value = "productCode") String productCode);
    
    
    
    
    
    
    

    
    

    /**
     * 
     * 同步数据后插入
     * 
     * @author liaoguo
     * @DateTime 2018年6月8日 下午1:45:41
     *
     * @param datas
     * @return
     */
    int saveDrugsList(DrugsPO d);

    /**
     * 
     * 保存同步记录表
     * 
     * @author liaoguo
     * @DateTime 2018年6月8日 下午2:07:58
     *
     * @param userId
     * @param syncTime
     * @param nextTime
     * @param content
     * @return
     */
    int saveSyncRecord(@Param(value = "userId") Integer userId, @Param(value = "syncTime") String syncTime,
            @Param(value = "nextTime") String nextTime, @Param(value = "content") String content);

    /**
     * 
     * 获取同步时间
     * 
     * @author liaoguo
     * @DateTime 2018年6月8日 下午2:29:53
     *
     * @return
     */
    String getSyncLastDate();

    public List<PrescriptionType> getPrescriptionType();

    public List<ProductAttribute> getProductAttribute();
    
    /**
     * 统计订单数量
     * 
     * @return
     */
    int countOrder();
    
    /**
     * 
     *  查询订单
     *  @author liaoguo
     *  @DateTime 2018年6月14日 上午10:30:47
     *
     *  @param orderNo
     *  @return
     */
    public List<OrderPO> getOrderList(int startRow,int pageSize);
    
    /**
     * 
     *  查询订单
     *  @author liaoguo
     *  @DateTime 2018年6月14日 上午10:30:47
     *
     *  @param orderNo
     *  @return
     */
    public List<OrderPO> getLocalOrderByParam(String orderNo);
    
    
    
    
    //修改订单
    public int updateOrder(OrderPO order);
    
    
    //修改产品
    public int updateProduct(@Param(value="product") OrderProductPO product,
                             @Param(value ="currentOrderNo") String currentOrderNo);
    
    //查询产品
    public List<OrderProductPO> getOrderProductList(@Param(value="orderNo") String orderNo);
    
    
    
    /**
     * 统计订单数量
     * 
     * @return
     */
    int countLocalOrder();

    /**
     * 套餐列表
     * 
     * @param startRow
     * @param pageSize
     * @return
     */
    List<DrugsPO> findOrderList(@Param(value = "productName") String productName,
            @Param(value = "productAttribute") String productAttribute,
            @Param(value = "prescriptionType") String prescriptionType, @Param("startRow") int startRow,
            @Param("pageSize") int pageSize);
    
    
    
    
    
    

    
}
