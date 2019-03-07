package com.lifeshs.dao1.order;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.OrderFlowPO;
import com.lifeshs.vo.order.OrderFlowVO;

@Repository("iOrderFlowDao")
public interface IOrderFlowDao {
    /**
     * 删除对象
     * <p>
     * // TODO: 2017/7/12 0012 工具自动生成的接口方法，与readme中的命名规范不符.
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加对象
     * <p>
     * // TODO: 2017/7/12 0012 工具自动生成的接口方法，与readme中的命名规范不符.
     *
     * @param record
     * @return
     */
    int insert(OrderFlowPO record);

    /**
     * 添加对象
     * 只插入不为空的属性
     * <p>
     * // TODO: 2017/7/12 0012 工具自动生成的接口方法，与readme中的命名规范不符.
     *
     * @param record
     * @return
     */
    int addSelective(OrderFlowPO record);

    /**
     * 获取对象
     *
     * @param id
     * @return
     */
    OrderFlowPO getOrderFlowPO(Integer id);

    /**
     * 更新对象，只更新对象中不为空的属性.
     * <p>
     * // TODO: 2017/7/12 0012 工具自动生成的接口方法，与readme中的命名规范不符.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderFlowPO record);

    /**
     * 更新对象
     * <p>
     * // TODO: 2017/7/12 0012 工具自动生成的接口方法，与readme中的命名规范不符.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(OrderFlowPO record);

    /**
     * 根据订单号获取OrderFlowPO
     *
     * @author wuj
     * @since 2017-07-17 14:28:45
     * @updateTime none
     *
     * @param orderNumber
     * @return
     */
    OrderFlowPO getOrderFlowPOByOrderNumber(@Param("orderNumber") String orderNumber);
    
    /**
     * 获取交易流水总记录数
     * @author zizhen.huang
     * @DateTime 2018年1月29日11:59:03
     * @param orgName 门店名称
     * @param realName 用户姓名
     * @param serveName 服务名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    int getTotalRecord(@Param("userNo") String userNo, @Param("orgName") String orgName,
    		@Param("realName") String realName,
    		@Param("orderNumber") String orderNumber,
            @Param("serveName") String serveName,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
    
    /**
     * 获取交易流水列表
     * @author zizhen.huang
     * @DateTime 2018年1月29日12:02:43
     * @param orgName 门店名称
     * @param realName 用户姓名
     * @param serveName 服务名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    List<OrderFlowVO> findOrderFlowList(@Param("userNo") String userNo, @Param("orgName") String orgName,
    		@Param("realName") String realName,
    		@Param("orderNumber") String orderNumber,
            @Param("serveName") String serveName,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("startRow") int startRow,
            @Param("pageSize") int pageSize);
    
    /**
     * 获取按服务名称分组的列表
     * @author zizhen.huang
     * @DateTime 2018年1月29日16:40:52
     * @return
     */
    List<String> getGroupByServeNameList();
    
    /**
     * 根据id获取交易流水记录
     * @author zizhen.huang
     * @DateTime 2018年1月30日13:40:27
     * @param id 主键
     * @return
     */
    OrderFlowVO getOrderFlowById(@Param("id") Integer id);
}