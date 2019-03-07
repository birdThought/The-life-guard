package com.lifeshs.app.api.store;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.index.IndexData;
import com.lifeshs.dto.manager.index.StatisticalData;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.util.HealthTypeAnalysis;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.vo.WarningUserVO;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service1.member.IMemberService;
import com.lifeshs.service1.order.IEmployeeOrderService;
import com.lifeshs.service1.order.IStoreOrderService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店首页综合信息，相关统计信息
 * Created by dengfeng on 2017/6/19 0019.
 */
@RestController
@RequestMapping("mapp/index")
public class IndexController {
    @Autowired
    IMemberService memberService;

    @Autowired
    IEmployeeOrderService employeeOrderService;

    @Autowired
    IStoreOrderService storeOrderService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    MessageService messageService;

    /**
     *  得到门店/服务师的业务数据统计(会员数量、员工数量、今日订单数量）
     *  @author dengfeng
     *  @return
     */
    @RequestMapping(value = "getIndexData", method = RequestMethod.POST)
    public @ResponseBody JSONObject getIndexData(SubmitDTO sumbitDTO) {
        int orgId = sumbitDTO.getUser().getOrgId();
        int employeeId = sumbitDTO.getUser().getId();

        IndexData indexData = new IndexData();
        StatisticalData statisticalData = new StatisticalData();
        List<WarningUserVO> members;
        //用户类型:管理员_0,服务师_1,都有_2
        if(sumbitDTO.getUser().getUserType() == 1){
            statisticalData.setMemberNumber(memberService.getMemberCountByEmployee(employeeId)); // 会员数量
            statisticalData.setOutstandingOrderNumber(employeeOrderService.getOrderCount(employeeId)); // 订单数量
            statisticalData.setTodayOrderNumber(employeeOrderService.getTodayOrderCount(employeeId)); // 今日订单数量
            statisticalData.setNonReadMessage(messageService.countSystemUnreadMessage(employeeId, UserType.orgUser)); // 未读消息
            members = memberService.findWainingMemberListByEmployee(employeeId, 1, 2); // 用户警告
        }else{
            statisticalData.setMemberNumber(memberService.getMemberCountByStore(orgId));
            statisticalData.setOrgUserNumber(employeeService.getEmployeeCount(orgId));
            OrderCountAndChargeVO countAndChargeVO = storeOrderService.getTodayOrderCountAndCharge(orgId);
            statisticalData.setTodayOrderNumber(countAndChargeVO.getOrderNumber());
            statisticalData.setNonReadMessage(messageService.countSystemUnreadMessage(employeeId, UserType.orgUser));
            members = memberService.findWainingMemberListByStore(orgId, 1, 2);
        }
        for(WarningUserVO warningUserVO : members){
            Long warningValue = Long.valueOf(warningUserVO.getHasWarning());
            warningUserVO.setHasWarning(HealthTypeAnalysis.parseString(warningValue));
        }
        indexData.setStatisticalData(statisticalData);

        indexData.setWarningUserVOList(members);
        return ReturnDataAgent.success(indexData);
    }

    /**
     *  得到门店/服务师的业务数据统计(会员数量、员工数量、今日订单数量）
     *  @author dengfeng
     *  @return
     */
    @RequestMapping(value = "getStatisticalData", method = RequestMethod.POST)
    public @ResponseBody JSONObject getStatisticalData(SubmitDTO sumbitDTO) {
        int orgId = sumbitDTO.getUser().getOrgId();
        int employeeId = sumbitDTO.getUser().getId();

        StatisticalData statisticalData = new StatisticalData();
        //用户类型:管理员_0,服务师_1,都有_2
        if(sumbitDTO.getUser().getUserType() == 0){
            statisticalData.setMemberNumber(memberService.getMemberCountByStore(orgId));    //会员数量
            statisticalData.setOrgUserNumber(employeeService.getEmployeeCount(orgId));  //员工数量
            statisticalData.setTodayOrderNumber(storeOrderService.getTodayOrderCountAndCharge(orgId).getOrderNumber());  //今日订单数量
        }else{
            statisticalData.setMemberNumber(memberService.getMemberCountByEmployee(employeeId));  //会员数量
            statisticalData.setOutstandingOrderNumber(employeeOrderService.getOrderCount(employeeId));  //待办任务数量
            statisticalData.setTodayOrderNumber(employeeOrderService.getTodayOrderCount(employeeId));     //今日订单数量
        }
        return ReturnDataAgent.success(statisticalData);
    }
}
