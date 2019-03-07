package com.lifeshs.app.api.store;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.order.RefundOrderStatus;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.order.IOrderRefundDao;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.UpPagingData;
import com.lifeshs.dto.manager.UpSearchPagingData;
import com.lifeshs.dto.manager.employee.GetEmployeeData;
import com.lifeshs.dto.manager.employee.GetEmployeeListData;
import com.lifeshs.dto.manager.employee.UpdateMineInfoData;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.OrderRefundPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.pojo.message.MessagePlaceholderDTO;
import com.lifeshs.service1.member.IMemberService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.order.IEmployeeOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.vo.OrderCountAndChargeVO;

/**
 * 门店员工业务控制类
 * Created by dengfeng on 2017/6/21 0021.
 */
@RestController
@RequestMapping("mapp/employee")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderService orderService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IMemberService memberService;

    @Autowired
    IEmployeeOrderService employeeOrderService;

    @Autowired
    MessageService messageService;

    @Autowired
    IOrderRefundDao orderRefundDao;

    /**
     * 得到门店的员工列表
     *
     * @return
     */
    @RequestMapping(value = "getEmployeeList", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getEmployeeList(SubmitDTO sumbitDTO, UpSearchPagingData upServerListData) {
        int orgId = sumbitDTO.getUser().getOrgId();
        int pageIndex = upServerListData.getPageIndex();
        int pageSize = upServerListData.getPageSize();
        String search = upServerListData.getSearch();
        //用户类型:管理员_0,服务师_1,都有_2
        if (sumbitDTO.getUser().getUserType() != 0) {
            return ReturnDataAgent.error("服务师没有此方法权限。");
        }
        List<GetEmployeeListData> employeeList = employeeService.getEmployeeList(orgId, pageIndex, pageSize, search);
        return ReturnDataAgent.success(employeeList);
    }

    /**
     * 得到单个员工的信息(含订单统计）
     *
     * @return
     */
    @RequestMapping(value = "getEmployee", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getEmployee(SubmitDTO sumbitDTO, String employeeId) throws InvocationTargetException, IllegalAccessException {
        int empId = Integer.parseInt(employeeId);
        //用户类型:管理员_0,服务师_1,都有_2
        if (sumbitDTO.getUser().getUserType() != 0) {
            return ReturnDataAgent.error("服务师没有此方法权限。");
        }
        EmployeePO employee = employeeService.getEmployee(empId);

        GetEmployeeData getEmployeeData = new GetEmployeeData();
        BeanUtils.copyProperties(getEmployeeData, employee);

        List<ProjectPO> projectList = employeeService.getProjectList(empId);
        List<String> strList = new ArrayList<>(projectList.size());
        for (ProjectPO projectPO : projectList) {
            strList.add(projectPO.getName());
        }
        getEmployeeData.setProjectList(strList);
        getEmployeeData.setMemberNumber(memberService.getMemberCountByEmployee(empId));

        OrderCountAndChargeVO orderCountAndMoneyData = employeeOrderService.getCountAndMoneyByMonth(empId);
        getEmployeeData.setOrderNumber(orderCountAndMoneyData.getOrderNumber());
        getEmployeeData.setCharge(orderCountAndMoneyData.getCharge());
        return ReturnDataAgent.success(getEmployeeData);
    }

    /**
     * 员工离职
     *
     * @return
     */
    @RequestMapping(value = "leaveJob", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject leaveJob(SubmitDTO sumbitDTO, String employeeId) throws InvocationTargetException, IllegalAccessException {
        int eId = Integer.parseInt(employeeId);

        Integer count = orderService.getOrderCountByIdAndStatus(eId, OrderStatus.VALID.getStatus());
        if (count > 0) {
            return ReturnDataAgent.error("服务师还有订单没有完成,无法离职!!!");
        }

        employeeService.leaveJob(eId);
        return ReturnDataAgent.success();
    }

    /**
     * 得到单个员工的信息
     *
     * @return
     */
    @RequestMapping(value = "getMineInfo", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getMineInfo(SubmitDTO sumbitDTO) throws InvocationTargetException, IllegalAccessException {
        int employeeId = sumbitDTO.getUser().getId();
        EmployeePO employee = employeeService.getEmployee(employeeId);
        return ReturnDataAgent.success(employee);
    }

    /**
     * 更新个人信息
     *
     * @return
     */
    @RequestMapping(value = "updateMineInfo", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject updateMineInfo(SubmitDTO sumbitDTO, UpdateMineInfoData updateMineInfoData) throws InvocationTargetException, IllegalAccessException {
        int employeeId = sumbitDTO.getUser().getId();
        employeeService.updateEmployeeMine(employeeId, updateMineInfoData);
        return ReturnDataAgent.success();
    }

    /**
     * 得到个人收到的系统消息
     *
     * @return
     */
    @RequestMapping(value = "getMineMessage", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getMineMessage(SubmitDTO sumbitDTO, UpPagingData pagingData) throws InvocationTargetException, IllegalAccessException {
        int employeeId = sumbitDTO.getUser().getId();
//        Paging<MessageDTO> messageList = messageService.listSystemMessage(employeeId, UserType.orgUser, pagingData.getPageIndex(), pagingData.getPageSize());
//        return ReturnDataAgent.success(messageList.getData());
        // 成了
        List<MessageDTO> messageList = messageService.listSystemMessage(employeeId, UserType.orgUser, 1, 10).getData();
        JSONArray messageJSONArray = JSONArray.parseArray(JSONArray.toJSONString(messageList));
        return ReturnDataAgent.success(messageJSONArray);
    }

    /**
     * 得到个人收到的系统消息数量
     *
     * @return
     */
    @RequestMapping(value = "getMineMessageCount", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getMineMessageCount(SubmitDTO sumbitDTO) throws InvocationTargetException, IllegalAccessException {
        int employeeId = sumbitDTO.getUser().getId();
        int count = messageService.countSystemUnreadMessage(employeeId, UserType.orgUser);
        HashMap<String, String> countMap = new HashMap<>();
        countMap.put("messageCount", String.valueOf(count));
        return ReturnDataAgent.success(countMap);
    }

    /**
     * 标记消息为已读
     *
     * @return
     */
    @RequestMapping(value = "readedMessage", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject readedMessage(SubmitDTO sumbitDTO, String messageId) throws InvocationTargetException, IllegalAccessException, OperationException {
        int employeeId = sumbitDTO.getUser().getId();
        messageService.updateSystemMessageToRead(Integer.parseInt(messageId), employeeId, UserType.orgUser);
        return ReturnDataAgent.success();
    }

    /**
     * 删除系统消息
     *
     * @param submitDTO
     * @param messageId 想要删除系统消息的ID
     * @author wuj
     * @since 2017-08-29 10:09:50
     * @return
     */
    @PostMapping("deleteMessage")
    public JSONObject deleteMessage(SubmitDTO submitDTO, String messageId) {
        int id = submitDTO.getUser().getId();
        JSONObject object = JSON.parseObject(submitDTO.getJson());
        String orderNum = object.getJSONObject("data").getString("orderNum"); // 获取订单号
        boolean result = false;

        try {
            if (StringUtils.hasText(orderNum)) { // 退款订单处理
                OrderRefundPO refundPO = orderRefundDao.getOrderRefundPOByOrderNumber(orderNum);
                if (refundPO == null) {
                    return ReturnDataAgent.error("订单号:" + orderNum + "无效");
                }

                Integer status = refundPO.getStatus().intValue();
                if (status.equals(RefundOrderStatus.REFUNDIND.getStatus()) ||
                        status.equals(RefundOrderStatus.TO_REFUND.getStatus()) ||
                        status.equals(RefundOrderStatus.AUDITED.getStatus())) {
                    return ReturnDataAgent.error("退款订单:" + orderNum + " 尚未处理完成");
                }
            }

            messageService.deleteMessage(Integer.parseInt(messageId), id, UserType.orgUser);
        } catch (Exception e) {
            log.debug("消息id:" + messageId + " 删除失败", e);
            return ReturnDataAgent.error("删除失败");
        }

        return result ? ReturnDataAgent.success() : ReturnDataAgent.error("系统消息删除失败");
    }
}
