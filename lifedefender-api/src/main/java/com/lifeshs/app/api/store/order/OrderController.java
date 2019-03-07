package com.lifeshs.app.api.store.order;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lifeshs.common.constants.common.ProjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.lifeshs.app.api.store.ReturnDataAgent;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.ReturnStatus;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.dao1.employee.IEmployeeDao;
import com.lifeshs.dto.manager.SubmitDTO;
import com.lifeshs.dto.manager.UpDatePagingData;
import com.lifeshs.dto.manager.order.GetOrderDetailData;
import com.lifeshs.dto.manager.order.GetOrderListAllData;
import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.dto.manager.order.GetServeListData;
import com.lifeshs.dto.manager.order.UpServerListData;
import com.lifeshs.dto.manager.order.UpSubmitVerifyCodeData;
import com.lifeshs.po.CommentPO;
import com.lifeshs.po.OrderPO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service1.order.IEmployeeOrderService;
import com.lifeshs.service1.order.IStoreOrderService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.vo.RefundOrderVO;

/**
 * 订单业务控制器
 * Created by dengfeng on 2017/6/21 0021.
 */
@RestController
@RequestMapping("mapp/order")
public class OrderController {
    @Autowired
    IEmployeeOrderService employeeOrderService;

    @Autowired
    IStoreOrderService storeOrderService;

    @Autowired
    OrderService orderService;

    @Autowired
    IOrgUserService orgUserService;

    @Autowired
    IEmployeeDao employeeDao;

    /**
     * 得到任务/服务订单列表
     *
     * @return
     */
    @RequestMapping(value = "getServeList", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getServeList(SubmitDTO submitDTO, UpServerListData upServerListData) throws InvocationTargetException, IllegalAccessException {
        int orderState = upServerListData.getOrderState();  //订单状态：1未完成，2待回复，3已完成，4全部
        int serveType = upServerListData.getServeType();   //1：咨询；2：线下；3：上门；4：课堂，0全部
        int pageIndex = upServerListData.getPageIndex();
        int pageSize = upServerListData.getPageSize();
        String search = upServerListData.getSearch();   // 有的话按用户姓名过滤
        String memberId = upServerListData.getMemberId(); // 有的话需要根据会员ID获取

        if ("".equals(search))
            search = null;
        List<OrderPO> orderList = null;
        //用户类型:管理员_0,服务师_1,都有_2
        if (submitDTO.getUser().getUserType() == 0) {
            int orgId = submitDTO.getUser().getOrgId();
            orderList = storeOrderService.findOrderList(orgId, serveType, orderState, search, pageIndex,
                    pageSize, memberId == null ? null : Integer.valueOf(memberId));
        } else {
            int employeeId = submitDTO.getUser().getId();
            orderList = employeeOrderService.findOrderList(employeeId, serveType, orderState, search, pageIndex,
                    pageSize,memberId == null ? null : Integer.valueOf(memberId));
        }
        //转换为DTO LIST
        if (orderList != null && orderList.size() > 0) {
            List<GetServeListData> serveOrderList = new ArrayList<>();
            for (OrderPO orderPO : orderList) {
                String userPhotoNetPath = MAppNormalService.normativeApproachImagePath(
                        orderPO.getUser() == null ? null : orderPO.getUser().getPhoto() // 防止 NPE
                );
                String serveImgNetPath = MAppNormalService.normativeApproachImagePath(orderPO.getProjectImage());

                String mobile = null;
                int code = orderPO.getProject().getProjectType();
                ProjectType projectType = ProjectType.values()[code];
                if (ProjectType.PROJECT_CONSULT.equals(projectType) || ProjectType.PROJECT_LESSON.equals(projectType)) {
                    mobile = orderPO.getUser().getMobile();
                }
                if (ProjectType.PROJECT_VISIT.equals(projectType) || ProjectType.PROJECT_TOSTORE.equals(projectType)) {
                    mobile = orderPO.getReceiverMobile();
                }

                GetServeListData orderData = new GetServeListData();
                //BeanUtils.copyProperties(orderData,orderPO);
                orderData.setName(orderPO.getUser().getRealName());
                orderData.setUserCode(orderPO.getUser().getUserCode());
                orderData.setOrderId(orderPO.getId());
                orderData.setOrderNumber(orderPO.getOrderNumber());
                orderData.setOrderState(orderPO.getStatus());
                orderData.setOrderTime(DateTimeUtil.yyyy_MM_ddHHmmss(orderPO.getCreateDate()));
                orderData.setMobile(mobile);
                orderData.setPhoto(userPhotoNetPath);
                orderData.setPrice(String.valueOf(NumberUtils.changeF2Y(String.valueOf(orderPO.getPrice()))));
                orderData.setCharge(String.valueOf(NumberUtils.changeF2Y(String.valueOf(orderPO.getCharge()))));
                orderData.setNumber(orderPO.getNumber());
                orderData.setServeCode(orderPO.getServeType().getCode());
                orderData.setProjectCode(orderPO.getProjectCode());
                orderData.setServeType(projectType.getValue());
                orderData.setServeImg(serveImgNetPath);
                orderData.setServeName(orderPO.getSubject());
                orderData.setUserId(orderPO.getUser().getId());
                orderData.setHuanxinId(orderPO.getProject().getHuanxinId());
                orderData.setEmployeeId(orderPO.getEmployee().getId());
                orderData.setEmployeeName(orderPO.getEmployee().getRealName());
                orderData.setEmployeeCode(orderPO.getEmployee().getUserCode());
                orderData.setAddress(orderPO.getAddress());
                serveOrderList.add(orderData);
            }
            return ReturnDataAgent.success(serveOrderList);
        }
        return ReturnDataAgent.success();
    }

    /**
     * 得到任务/服务订单列表
     *
     * @return
     */
    @RequestMapping(value = "getServe", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getServe(SubmitDTO submitDTO, String orderId) throws InvocationTargetException, IllegalAccessException {

        OrderPO orderPO = orderService.getOrderDetail(Integer.parseInt(orderId));
        //转换为DTO LIST
        if (orderPO != null) {
            List<GetServeListData> serveOrderList = new ArrayList<>();
            String userPhotoNetPath = MAppNormalService.normativeApproachImagePath(
                    orderPO.getUser() == null ? null : orderPO.getUser().getPhoto() // 防止 NPE
            );
            String serveImgNetPath = MAppNormalService.normativeApproachImagePath(orderPO.getProjectImage());

            String mobile = null;
            int code = orderPO.getProject().getProjectType();
            ProjectType projectType = ProjectType.values()[code];
            if (ProjectType.PROJECT_CONSULT.equals(projectType) || ProjectType.PROJECT_LESSON.equals(projectType)) {
                mobile = orderPO.getUser().getMobile();
            }
            if (ProjectType.PROJECT_VISIT.equals(projectType) || ProjectType.PROJECT_TOSTORE.equals(projectType)) {
                mobile = orderPO.getReceiverMobile();
            }

            GetServeListData orderData = new GetServeListData();
            //BeanUtils.copyProperties(orderData,orderPO);
            orderData.setName(orderPO.getUser().getRealName());
            orderData.setUserCode(orderPO.getUser().getUserCode());
            orderData.setOrderId(orderPO.getId());
            orderData.setOrderNumber(orderPO.getOrderNumber());
            orderData.setOrderState(orderPO.getStatus());
            orderData.setOrderTime(DateTimeUtil.yyyy_MM_ddHHmmss(orderPO.getCreateDate()));
            orderData.setMobile(mobile);
            orderData.setPhoto(userPhotoNetPath);
            orderData.setPrice(String.valueOf(NumberUtils.changeF2Y(String.valueOf(orderPO.getPrice()))));
            orderData.setCharge(String.valueOf(NumberUtils.changeF2Y(String.valueOf(orderPO.getCharge()))));
            orderData.setNumber(orderPO.getNumber());
            orderData.setServeCode(orderPO.getServeType().getCode());
            orderData.setProjectCode(orderPO.getProjectCode());
            orderData.setServeType(projectType.getValue());
            orderData.setServeImg(serveImgNetPath);
            orderData.setServeName(orderPO.getSubject());
            orderData.setUserId(orderPO.getUser().getId());
            orderData.setHuanxinId(orderPO.getProject().getHuanxinId());
            orderData.setEmployeeId(orderPO.getEmployee().getId());
            orderData.setEmployeeName(orderPO.getEmployee().getRealName());
            orderData.setEmployeeCode(orderPO.getEmployee().getUserCode());
            orderData.setAddress(orderPO.getAddress());
            serveOrderList.add(orderData);

            return ReturnDataAgent.success(serveOrderList);
        }
        return ReturnDataAgent.success();
    }

    /**
     * 得到订单列表
     *
     * @return
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getOrderList(SubmitDTO submitDTO, UpDatePagingData upDatePagingData) {
        String startDate = upDatePagingData.getStartDate();
        String endDate = upDatePagingData.getEndDate();
        int pageIndex = upDatePagingData.getPageIndex();
        int pageSize = upDatePagingData.getPageSize();
        List<GetOrderListData> orderList = null;
        OrderCountAndChargeVO countAndChargeVO;
        //用户类型:管理员_0,服务师_1,都有_2
        if (submitDTO.getUser().getUserType() == 0) {
            int orgId = submitDTO.getUser().getOrgId();
            orderList = storeOrderService.findOrderListWithUser(orgId, startDate, endDate, pageIndex, pageSize);
            countAndChargeVO = storeOrderService.getDateDiffOrderCountAndCharge(orgId, startDate, endDate);
        } else {
            int employeeId = submitDTO.getUser().getId();
            orderList = employeeOrderService.findOrderListWithUser(employeeId, startDate, endDate, pageIndex, pageSize);
            countAndChargeVO = employeeOrderService.getDateDiffOrderCountAndCharge(employeeId, startDate, endDate);
        }
        GetOrderListAllData orderListAllData = new GetOrderListAllData();
        orderListAllData.setData(orderList);
        orderListAllData.setTotalCharge(countAndChargeVO.getCharge());
        orderListAllData.setTotalSize(countAndChargeVO.getOrderNumber());
        return ReturnDataAgent.success(orderListAllData);
    }

    /**
     * 得到用户订单详细信息
     *
     * @return
     */
    @RequestMapping(value = "getOrderDetail", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getOrderDetail(SubmitDTO submitDTO, String orderId) {

        OrderPO orderPO = orderService.getOrderDetail(Integer.parseInt(orderId));

        String userPhotoNetPath = MAppNormalService.normativeApproachImagePath(orderPO.getUser().getPhoto());
        String serveImgNetPath = MAppNormalService.normativeApproachImagePath(orderPO.getProjectImage());

        GetOrderDetailData orderDetail = new GetOrderDetailData();
        orderDetail.setId(orderPO.getId());
        orderDetail.setOrderNumber(orderPO.getOrderNumber());
        orderDetail.setStatus(orderPO.getStatus());
        orderDetail.setOrderTime(DateTimeUtilT.dateTime(orderPO.getCreateDate()));
        orderDetail.setPrice(String.valueOf(NumberUtils.changeF2Y(String.valueOf(orderPO.getPrice()))));
        orderDetail.setNumber(orderPO.getNumber());
        orderDetail.setUserId(orderPO.getUser().getId());
        orderDetail.setRealName(orderPO.getUser().getRealName());

        orderDetail.setPhoto(userPhotoNetPath);
        orderDetail.setMobile(orderPO.getReceiverMobile());
        orderDetail.setAddress(orderPO.getAddress());
        orderDetail.setServeId(orderPO.getServeId());
        orderDetail.setSubject(orderPO.getSubject());
        orderDetail.setServeImg(serveImgNetPath);
        CommentPO commentPO = orderPO.getComment();
        String comment = "";
        String reply = "";
        if (commentPO != null) {
            comment = commentPO.getComment();
            reply = commentPO.getReply();
        }
        orderDetail.setCommont(comment);
        orderDetail.setReply(reply);

        return ReturnDataAgent.success(orderDetail);
    }

    /**
     * 得到退款订单详细信息
     *
     * @return
     */
    @RequestMapping(value = "getRefundOrderDetail", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getRefundOrderDetail(SubmitDTO submitDTO, String orderId) {

        RefundOrderVO orderPO = orderService.getRefundOrderDetail(Integer.parseInt(orderId));

        return ReturnDataAgent.success(orderPO);
    }

    /**
     * 提交邀请码
     *
     * @return
     */
    @RequestMapping(value = "submitVerifyCode", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject submitVerifyCode(SubmitDTO submitDTO, UpSubmitVerifyCodeData upVerifyCodeData) {
        int orderId = upVerifyCodeData.getOrderId();
        String verifyCode = upVerifyCodeData.getVerifyCode();
        if (StringUtil.isBlank(verifyCode))
            return ReturnDataAgent.error(ReturnStatus.ParamIsEmpty);

        OrderPO orderPO = orderService.getOrderDetail(orderId);
        if (orderPO == null)
            return ReturnDataAgent.error(ReturnStatus.NonRecord);

        if (!verifyCode.equals(orderPO.getVerifyCode())) {
            return ReturnDataAgent.error(ReturnStatus.VerifyCodeError);
        }
        if (orderPO.getStatus() != OrderStatus.VALID.getStatus()) {
            return ReturnDataAgent.error(ReturnStatus.VerifyCodeUsed);
        }
        orderService.completeOrder(orderId);
        return ReturnDataAgent.success();

    }

    /**
     * 确认退款订单
     *
     * @param submitDTO
     * @param orderId
     * @return
     * @author wuj
     * @updateTime none
     * @since 2017-07-18 10:08:42
     */
    @RequestMapping(value = "confirmRefundOrder", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject confirmRefundOrder(SubmitDTO submitDTO, String orderId) {
        Integer auditorId = submitDTO.getUser().getId();

        try {
            orderService.confirmRefundOrder(Integer.parseInt(orderId), auditorId);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ReturnDataAgent.error(e.getErrCode() + " : " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnDataAgent.error("退款失败");
        } catch (DataBaseException e) {
            e.printStackTrace();
            if ("退款成功,数据更新异常".equals(e.getMessage())) {
                return ReturnDataAgent.success(e.getMessage(), null);
            }
            return ReturnDataAgent.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDataAgent.error("退款失败");
        }

        return ReturnDataAgent.success();
    }

    /**
     * 根据订单状态获取订单列表
     *
     * @param status 订单状态
     * @return
     */
    @PostMapping("getOrderCountByStatus")
    public @ResponseBody
    JSONObject getOrderCountByStatus(SubmitDTO submitDTO, String status) {
        Integer id = submitDTO.getUser().getId();

        OrgUserDTO orgUser = orgUserService.getOrgUser(id);
        if (orgUser.getOrg() == null) {
            return ReturnDataAgent.error("机构用户所属机构不能为空");
        }
        Integer userType = orgUser.getUserType();

        if (userType == 0) {
            // 用户为管理员,那么需要先获取机构下属服务师
            List<Integer> employeeIds = orgUserService.getOrgUsersByOrgId(orgUser.getOrg().getId());
            Integer count = 0;
            for (Integer employeeId : employeeIds) {
                count += orderService.getOrderCountByIdAndStatus(employeeId, Integer.parseInt(status));
            }

            HashMap<Object, Object> map = new HashMap<>();
            map.put("count", count);
            return ReturnDataAgent.success(map);
        } else if (userType == 1 || userType == 2) {
            Integer count = orderService.getOrderCountByIdAndStatus(id, Integer.parseInt(status));
            HashMap<Object, Object> map = new HashMap<>();
            map.put("count", count);
            return ReturnDataAgent.success(map);
        } else {
            return ReturnDataAgent.error("userType:" + orgUser.getUserType() + " 错误");
        }
    }
}
