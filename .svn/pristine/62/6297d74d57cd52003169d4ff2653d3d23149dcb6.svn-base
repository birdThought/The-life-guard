package com.lifeshs.dao1.order;

import com.lifeshs.dto.manager.order.GetPaymentListData;
import com.lifeshs.po.OrgStatementPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述
 * Created by dengfeng on 2017/7/7 0007.
 */
@Repository("statementDao")
public interface IStatementDao {

    /**
     * 获取门店一月结算记录
     * @param orgId
     * @param month "yyyy-MM"
     * @return
     */
    OrgStatementPO findStatement(@Param(value = "orgId") Integer orgId, @Param(value = "month") String month);

    /**
     * 获取多个月份的结算记录
     * @param orgId
     * @param endMonth "yyyy-MM"
     * @param startMonth
     * @return
     */
    List<OrgStatementPO> findStatementList (@Param(value = "orgId") Integer orgId,
                                            @Param(value = "startMonth") String startMonth,
                                            @Param(value = "endMonth") String endMonth);

    /**
     * 获取月已结算清单
     * @param statementId  结算记录ＩＤ
     * @return
     */
    List<GetPaymentListData> getMonthOrderList(@Param(value = "statementId") Integer statementId, @Param(value = "startRow") Integer startRow, @Param(value = "lenght") Integer lenght);

    /**
     * 更新机构账单状态
     * @param orgId
     * @param statementId
     */
    void updateStatementStatus(@Param("orgId") Integer orgId, @Param("statementId") Integer statementId);
    
    /**
     *  统计
     *  @author yuhang.weng 
     *  @DateTime 2018年2月10日 上午9:52:21
     *
     *  @return
     */
    int countStatement(@Param("userNo") String userNo);
    
    /**
     *  获取结算
     *  @author yuhang.weng 
     *  @DateTime 2018年2月10日 上午9:52:44
     *
     *  @return
     */
    List<OrgStatementPO> findStatement2(@Param("userNo") String userNo, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
}
