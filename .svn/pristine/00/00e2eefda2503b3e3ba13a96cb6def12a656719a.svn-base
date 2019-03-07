package com.lifeshs.service1.order;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dto.manager.order.GetPaymentListData;
import com.lifeshs.po.OrgStatementPO;
import com.lifeshs.service1.page.Paging;

import org.apache.ibatis.annotations.Param;

import javax.management.openmbean.OpenDataException;
import java.util.List;

/**
 * 订单结算
 * Created by dengfeng on 2017/7/7 0007.
 */
public interface IStatementService {
    /**
     * 获取门店一月结算记录
     * @param orgId
     * @param month "yyyy-MM"
     * @return
     */
    OrgStatementPO findStatement(int orgId, String month);

    /**
     * 获取门店多个月份结算记录
     * @param orgId
     * @param month "yyyy-MM"
     * @return
     */
    List<OrgStatementPO> findStatementList(int orgId, String startMonth, String endMonth);

    /**
     * 获取月已结算清单
     * @param statementId  结算记录ＩＤ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<GetPaymentListData> getMonthOrderList(int statementId, int pageIndex, int pageSize);

    /**
     * 更新机构账单状态为“未转账”状态
     * @param orgId
     * @param statementId
     */
    void updateStatementToUnTransfer(int orgId, int statementId) throws OperationException;
    
    /**
     *  分页获取数据
     *  @author yuhang.weng 
     *  @DateTime 2018年2月10日 上午9:54:16
     *
     *  @param curPage
     *  @param pageSize
     *  @return
     */
    Paging<OrgStatementPO> listStatement(String userNo, int curPage, int pageSize);
}
