package com.lifeshs.service1.order;

import java.util.Date;

import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.order.OrderFlowVO;

public interface OrderFlowService {
	/**
     * 获取交易流水列表
     * @author zizhen.huang
     * @DateTime 2018年1月29日14:03:40
     * @param orgName 门店名称
     * @param realName 用户姓名
     * @param serveName 服务名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<OrderFlowVO> findOrderFlowList(String userNo, String orgName, String realName, String orderNumber, String serveName, Date startDate, Date endDate, int curPage, int pageSize);
    
    /**
     * 根据id获取交易流水记录
     * @author zizhen.huang
     * @DateTime 2018年1月30日14:05:31
     * @param id 主键
     * @return
     */
    OrderFlowVO getOrderFlowById(Integer id);
}
