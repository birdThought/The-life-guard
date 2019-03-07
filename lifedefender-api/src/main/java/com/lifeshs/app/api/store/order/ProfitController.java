package com.lifeshs.app.api.store.order;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.common.ReturnStatus;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.UpMonthPagingData;
import com.lifeshs.dto.manager.order.GetPaymentByMonthData;
import com.lifeshs.dto.manager.order.GetPaymentListData;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.OrderStatisticsByDayVO;
import com.lifeshs.dto.manager.order.GetTodayMonthData;
import com.lifeshs.po.OrgStatementPO;
import com.lifeshs.service1.order.IEmployeeOrderService;
import com.lifeshs.service1.order.IStatementService;
import com.lifeshs.service1.order.IStoreOrderService;
import com.lifeshs.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 收益、结算、对账控制器
 * Created by dengfeng on 2017/7/6 0006.
 */
@RestController
@RequestMapping("mapp/order/profit")
public class ProfitController {
    @Autowired
    IEmployeeOrderService employeeOrderService;
    @Autowired
    IStoreOrderService storeOrderService;

    @Autowired
    IStatementService statementService;

    /**
     * 获取今日收益、本月收益
     * @return
     */
    @RequestMapping(value = "getTodayAndMonth", method = RequestMethod.POST)
    public @ResponseBody JSONObject getTodayAndMonth(SubmitDTO sumbitDTO) {
        //用户类型:管理员_0,服务师_1,都有_2
        if (sumbitDTO.getUser().getUserType() == 1)
            return ReturnDataAgent.error(ReturnStatus.NonAuth);
        int orgId = sumbitDTO.getUser().getOrgId();
        GetTodayMonthData todayMonthData = new GetTodayMonthData();
        todayMonthData.setToday(storeOrderService.getTodayOrderCountAndCharge(orgId).getCharge());
        todayMonthData.setMonth(storeOrderService.getMonthOrderCountAndCharge(orgId, DateTimeUtil.yyyy_MM(new Date())).getCharge());
        return ReturnDataAgent.success(todayMonthData);
    }

    /**
     * 获取指定月份结算统计数据
     * @return
     */
    @RequestMapping(value = "getPaymentByMonth", method = RequestMethod.POST)
    public @ResponseBody JSONObject getPaymentByMonth(SubmitDTO sumbitDTO, String month) {
        //用户类型:管理员_0,服务师_1,都有_2
        if (sumbitDTO.getUser().getUserType() == 1)
            return ReturnDataAgent.error(ReturnStatus.NonAuth);
        int orgId = sumbitDTO.getUser().getOrgId();
        OrgStatementPO statementPO = statementService.findStatement(orgId, month);
        if(statementPO == null)
            return ReturnDataAgent.error(ReturnStatus.NonStatement);
        List<OrderStatisticsByDayVO> monthOrderStatisticsData = storeOrderService.getMonthOrderStatistics(statementPO.getId());
        GetPaymentByMonthData paymentByMonthData = new GetPaymentByMonthData();
        paymentByMonthData.setProfit(statementPO.getOrderCharge());
        paymentByMonthData.setPayment(statementPO.getStatementCharge());
        paymentByMonthData.setStatistics(monthOrderStatisticsData);
        return ReturnDataAgent.success(paymentByMonthData);
    }

    /**
     * 获取月已结算清单
     * @return
     */
    @RequestMapping(value = "getPaymentListByMonth", method = RequestMethod.POST)
    public @ResponseBody JSONObject getPaymentListByMonth(SubmitDTO sumbitDTO, UpMonthPagingData monthPagingData) {
        //用户类型:管理员_0,服务师_1,都有_2
        if (sumbitDTO.getUser().getUserType() == 1)
            return ReturnDataAgent.error(ReturnStatus.NonAuth);
        int orgId = sumbitDTO.getUser().getOrgId();
        OrgStatementPO statementPO = statementService.findStatement(orgId, monthPagingData.getMonth());
        if(statementPO == null)
            return ReturnDataAgent.error(ReturnStatus.NonRecord);
        List<GetPaymentListData> paymentListData = statementService.getMonthOrderList(statementPO.getId(), monthPagingData.getPageIndex(), monthPagingData.getPageSize());
        return ReturnDataAgent.success(paymentListData);
    }
}
