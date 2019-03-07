package com.lifeshs.service1.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.order.IStatementDao;
import com.lifeshs.dto.manager.order.GetPaymentListData;
import com.lifeshs.po.OrgStatementPO;
import com.lifeshs.service1.order.IStatementService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;

/**
 * 订单结算
 * Created by dengfeng on 2017/7/7 0007.
 */
@Service(value = "statementService")
public class StatementService implements IStatementService {
    @Autowired
    IStatementDao statementDao;

    /**
     * 获取门店一月结算记录
     * @param orgId
     * @param month "yyyy-MM"
     * @return
     */
    public OrgStatementPO findStatement(int orgId, String month){
        OrgStatementPO orgStatementPO = statementDao.findStatement(orgId, month);
        if(orgStatementPO != null) {
            orgStatementPO.setOrderCharge(NumberUtils.changeF2Y(String.valueOf(orgStatementPO.getOrderCharge().intValue())));
            orgStatementPO.setStatementCharge(NumberUtils.changeF2Y(String.valueOf(orgStatementPO.getStatementCharge().intValue())));
        }
        return orgStatementPO;
    }

    /**
     * 获取门店多个月份结算记录
     * @param orgId
     * @return
     */
    public List<OrgStatementPO> findStatementList(int orgId, String startMonth, String endMonth){
        List<OrgStatementPO> list = statementDao.findStatementList(orgId, startMonth, endMonth);
        for (OrgStatementPO orgStatementPO : list) {
            orgStatementPO.setOrderCharge(NumberUtils.changeF2Y(String.valueOf(orgStatementPO.getOrderCharge().intValue())));
            orgStatementPO.setStatementCharge(NumberUtils.changeF2Y(String.valueOf(orgStatementPO.getStatementCharge().intValue())));
        }
        return list;
    }

    /**
     * 获取月已结算清单
     * @param statementId  结算记录ＩＤ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<GetPaymentListData> getMonthOrderList(int statementId, int pageIndex, int pageSize){

        return statementDao.getMonthOrderList(statementId, (pageIndex-1)*pageSize, pageSize);
    }

    public void updateStatementToUnTransfer(int orgId, int statementId) throws OperationException {
        try {
            statementDao.updateStatementStatus(orgId, statementId);
        } catch (Exception e) {
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }

    }

    @Override
    public Paging<OrgStatementPO> listStatement(String userNo, int curPage, int pageSize) {
        Paging<OrgStatementPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<OrgStatementPO>() {
            
            @Override
            public int queryTotal() {
                return statementDao.countStatement(userNo);
            }
            
            @Override
            public List<OrgStatementPO> queryData(int startRow, int pageSize) {
                return statementDao.findStatement2(userNo, startRow, pageSize);
            }
        });
        return p;
    }

}
