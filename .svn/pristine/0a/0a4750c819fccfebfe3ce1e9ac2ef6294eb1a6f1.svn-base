package com.lifeshs.service1.order.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.*;
import com.lifeshs.common.constants.common.Error;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lifeshs.common.constants.app.AlipayReturnCode;
import com.lifeshs.common.constants.app.FlowType;
import com.lifeshs.common.constants.common.order.ChargeModeEnum;
import com.lifeshs.common.constants.common.order.EasyStatusEnum;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.RefundOrderStatus;
import com.lifeshs.common.constants.common.order.StatusEnum;
import com.lifeshs.common.constants.common.project.Status;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao.org.lesson.ILessonGroupDao;
import com.lifeshs.dao.serve.ServeDao;
import com.lifeshs.po.CommentPO;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.OrderFlowPO;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrderRefundFlowPO;
import com.lifeshs.po.OrderRefundPO;
import com.lifeshs.po.OrderStoreSmsPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.po.order.OrderWithVipPO;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.pojo.order.OrderFlowBasicDTO;
import com.lifeshs.pojo.order.PaymentOrderDTO;
import com.lifeshs.pojo.order.v2.OrderCountDTO;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.order.v2.visitServe.BodyDTO;
import com.lifeshs.pojo.order.v2.visitServe.BodyDetailDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceCommonDTO;
import com.lifeshs.pojo.serve.lesson.LessonProjectDTO;
import com.lifeshs.pojo.serve.lesson.RecommendedLessonDTO;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.alipay.config.AgentConstant;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.service1.store.employee.IEmployeeService;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.MoneyUtils;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.RefundOrderVO;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.agent.AgetnIncomeVo;
import com.lifeshs.vo.order.OrderAgentAmountVO;
import com.lifeshs.vo.order.customer.OrderCountVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;

import net.sf.json.JSONObject;

/**
 * 订单
 *
 * @author yuhang.weng
 * @version 2.0
 * @DateTime 2017年6月19日 上午10:26:45
 */
@Service(value = "v2OrderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger("pay");

    @Autowired
    private com.lifeshs.dao.order.IOrderDao orderDao;

    @Autowired
    private com.lifeshs.dao1.order.IOrderDao orderDao1;

    @Resource(name = "lessonDao")
    private ILessonGroupDao lessonDao;

    @Autowired
    private com.lifeshs.dao1.order.IOrderFlowDao orderFlowDao;

    @Autowired
    private com.lifeshs.dao1.order.IOrderRefundDao orderRefundDao;

    @Resource(name = "serveDao")
    private ServeDao serveDao;

    @Resource(name = "v2LessonService")
    private LessonService lessonService;

    @Resource(name = "v2ConsultService")
    private ConsultService consultSerivce;

    @Resource(name = "v2VisitService")
    private VisitService visitService;

    @Resource(name = "memberDao")
    private IMemberDao memberDao;

    @Resource(name = "orgUserService")
    private IOrgUserService orgUserService;

    @Autowired
    private HuanXinService huanxinService;

    @Autowired
    private com.lifeshs.thirdservice.IAlipayService iAlipayService;

    @Autowired
    private com.lifeshs.dao1.order.IOrderRefundFlowDao orderRefundFlowDao;

    @Resource(name = "smsRemindService")
    private SmsRemindService smsRemindService;

    @Autowired
    private UMengPushService umengPushService;

    @Resource(name = "electronicCouponsService")
    private ElectronicCouponsService couponsService;
    
    @Resource(name = "incomeService")
    private IncomeServiceImpl incomeService;
    
    @Override
    public OrderDTO getConsultOrder(int userId, int serveUserId) {
        return orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUserId, ProjectType.PROJECT_CONSULT.getValue());
    }

    @Override
    public OrderPO getLessonOrder(int userId, int lessonId) {
        return orderDao1.findUserValidLessonOrder(userId, lessonId);
    }

    @Override
    public OrderPO getOrderDetail(int orderId) {
        OrderPO data = orderDao1.getOrderDetail(orderId);

        ProjectType projectType = ProjectType.values()[data.getProject().getProjectType()];
        StatusEnum statusEnum = getStatusEnum(data.getStatus(), projectType, data.getRefund(), data.getComment(), data.getEndDate());
        data.setStatusEnum(statusEnum);

        // 咨询就查找服务师的环信id
        if (ProjectType.PROJECT_CONSULT.equals(projectType)) {
            String huanxinUserName = data.getEmployee().getUserCode();
            data.setHuanxinUserName(huanxinUserName);
        }
        if (ProjectType.PROJECT_LESSON.equals(projectType)) {
            LessonPO lesson = data.getLesson();
            data.setHuanxinUserName(lesson.getHuanxinId());
        }

        return data;
    }

    @Override
    public OrderDTO addServeOrder(int userId, ProjectType projectType, int id, Integer serveUserId, Integer number, Integer addressId, int userSelectChargeMode)
            throws OperationException {
        String subject = null;
        String body = null;
        double price = 0;
        ChargeModeEnum chargeMode = null;
        Integer serveId = null;
        Date startDate = null;
        Date endDate = null;
        String projectCode = null;
        String address = null;
        String receiverName = null;
        String receiverMobile = null;
        Long numberL = null;
        OrderStatus orderStatus = OrderStatus.PAYABLE;  // 默认 待支付状态
        Integer timesRemaining = null;
        String verifyCode = null;
        String realName;

        // 线下服务
        if (!online(projectType)) {
            // 获取线下服务对象
            RecommendedComboDTO combo = visitService.getProjectCombo(id);
            ServiceComboDTO comboP = combo.getCombo();
            if (comboP == null) {
                throw new OperationException(String.format(Error.NOT_FOUND, "套餐"), ErrorCodeEnum.NOT_FOUND);
            }
            subject = comboP.getVisitServe().getName();

            // 获取套餐详情
            List<BodyDetailDTO> bodyDetail = JSONArray.parseArray(comboP.getIntroduce(), BodyDetailDTO.class);
            BodyDTO bodyDTO = new BodyDTO();
            bodyDTO.setId(comboP.getId());
            bodyDTO.setName(comboP.getName());
            bodyDTO.setDetail(bodyDetail);
            body = JSONObject.fromObject(bodyDTO).toString();

            price = comboP.getPrice();
            chargeMode = ChargeModeEnum.TIMES;
            endDate = null; // TODO 结束日期  如果服务有限制到什么时候，就在这里为endDate赋值
            projectCode = comboP.getProjectCode();
            serveId = combo.getServeId();

            // 居家养老服务需要上门地址
            if (ProjectType.PROJECT_VISIT.equals(projectType) && addressId == null) {
                throw new OperationException(String.format(Error.MISSING, "地址"), ErrorCodeEnum.NOT_FOUND);
            }
            if (addressId != null) {
                AddressDTO addressDTO = memberDao.getAddress(addressId, userId);
                if (addressDTO == null) {
                    throw new OperationException(String.format(Error.NOT_FOUND, "地址"), ErrorCodeEnum.NOT_FOUND);
                }
                address = addressDTO.getAddress();
                receiverName = addressDTO.getReceiverName();
                receiverMobile = addressDTO.getContactNumber();
            }

            numberL = number.longValue();
        }
        // 咨询
        if (ProjectType.PROJECT_CONSULT.equals(projectType)) {
            number = 1; // 默认咨询只有1次服务

            ServeUserVO serveUser = consultSerivce.getServeUser(id);
            if (serveUser == null) {
                throw new OperationException(String.format(Error.NOT_FOUND, "咨询服务"), ErrorCodeEnum.NOT_FOUND);
            }

            // 线上咨询只能购买一同一个服务师的
            OrderDTO orderDTO = orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUser.getUserId(), projectType.getValue());
            if (orderDTO != null) {
                if (serveUser.getProjectCode().equals(orderDTO.getProjectCode()) && DateTimeUtilT.calculateHourInterval(new Date(), orderDTO.getEndDate()) > 0) {
                    throw new OperationException("请勿重复购买该服务", ErrorCodeEnum.REPEAT);
                }

                /*throw new OperationException("请勿购买同一个服务师的咨询服务", ErrorCodeEnum.REQUEST);*/
            }

            // 重新组装subject，格式：服务师[服务类型]
            subject = serveUser.getRealName() + "[" + Toolkits.projectType(serveUser.getProjectName()) + "]";
            price = serveUser.getPrice();
            projectCode = serveUser.getProjectCode();
            serveUserId = serveUser.getUserId();
            serveId = serveUser.getServeId();
            
//            ChargeModeEnum realChargeMode = ChargeModeEnum.getChargeModeEnum(serveUser.getChargeMode());
            ChargeModeEnum realChargeMode = ChargeModeEnum.getChargeModeEnum(userSelectChargeMode);
            if (0 == price) {
                chargeMode = ChargeModeEnum.FREE;   // 免费
                // 如果是免费的话，开始日期startDate就是NOW()
                orderStatus = OrderStatus.VALID;
                // 免费只有1次，且剩余服务次数只有1
                numberL = 1L;
                timesRemaining = 1;
            } else {
                chargeMode = realChargeMode;  // 收费方式
                numberL = number.longValue();
            }
            startDate = new Date();
            endDate = calculateConsultEndDate(number, new Date(), realChargeMode);
        }
        // 课堂
        if (ProjectType.PROJECT_LESSON.equals(projectType)) {
            RecommendedLessonDTO lesson = lessonService.getLesson(id);
            LessonProjectDTO lessonP = lesson.getLessonProject();
            if (lessonP == null) {
                throw new OperationException(String.format(Error.NOT_FOUND, "课堂服务"), ErrorCodeEnum.NOT_FOUND);
            }
            subject = lessonP.getName();
            price = lessonP.getPrice();
            projectCode = lessonP.getCode();
            serveId = lessonP.getServe().getId();

            List<EmployeeDTO> employeeList = lessonP.getServeUserList();
            if (employeeList == null || employeeList.isEmpty()) {
                throw new OperationException("课堂中暂无服务师，请勿购买该服务", ErrorCodeEnum.REQUEST);
            }

            OrderPO orderPO = orderDao1.findUserValidLessonOrder(userId, lessonP.getId());
            if (orderPO != null) {
                throw new OperationException("请勿重复购买该课堂的服务", ErrorCodeEnum.REPEAT);
            }

            if (0 == price) {
                chargeMode = ChargeModeEnum.FREE;   // 免费
                // 开始日期startDate就是NOW()
                startDate = new Date();
                orderStatus = OrderStatus.VALID;
                // 免费的群组，需要把用户加入环信群组
                UserDTO user = memberDao.getUser(userId);
                huanxinService.joinGroup(lessonP.getHuanxinId(), user.getUserCode());
                timesRemaining = 1;
            } else {
                chargeMode = null;  // 收费
            }
            // 获取课堂的服务师
            serveUserId = employeeList.get(0).getId();
            endDate = lessonP.getEndDate();
            numberL = 1L;
        }
        
        return addServeOrder(userId, subject, body, orderStatus, price, numberL, timesRemaining, chargeMode,
                serveUserId, projectCode, serveId, address, receiverName, receiverMobile, startDate, endDate, verifyCode, id);
    }

    /**
     * 添加订单
     *
     * @param userId         会员id
     * @param subject        订单标题
     * @param body           订单描述内容
     * @param statusEnum     订单状态(待付款, 有效, 已完成)
     * @param price          单价(单位元)
     * @param number         购买数量
     * @param timesRemaining 剩余服务次数
     * @param chargeMode     收费方式(免费，按次)
     * @param serveUserId    服务师ID
     * @param projectCode    项目code(在各自的项目表中含有该字段)
     * @param serveId        服务ID
     * @param address        收货人地址
     * @param receiverName   收货人名字
     * @param receiverMobile 收货人联系方式
     * @param startDate      开始日期
     * @param endDate        结束日期
     * @param verifyCode     线下服务验证码
     * @param serveItemId    具体项目服务id
     * @return
     * @throws OperationException 操作异常
     * @author yuhang.weng
     * @DateTime 2017年6月27日 下午5:06:42
     */
    private OrderDTO addServeOrder(int userId, String subject, String body, OrderStatus statusEnum, double price, long number,
                                   Integer timesRemaining, ChargeModeEnum chargeMode, int serveUserId, String projectCode, int serveId, String address,
                                   String receiverName, String receiverMobile, Date startDate, Date endDate, String verifyCode, int serveItemId) throws OperationException {
        // 查询服务详细信息
        ServiceCommonDTO project = serveDao.getCommonProject(projectCode);

        // 单位元转换为分
        int price_f = NumberUtils.changeY2F(Double.valueOf(price).toString());

        // 计算总价
        Long total = price_f * number;

        // 生成订单号
        String orderNumber = AlipayService.createOrderNumber();

        int numberInt = Long.valueOf(number).intValue();

        OrderDTO order = new OrderDTO();
        order.setOrderNumber(orderNumber);
        order.setUserId(userId);
        order.setPrice(price_f);
        order.setNumber(numberInt);
        order.setTimesRemaining(timesRemaining);
        order.setChargeMode(chargeMode == null ? null : chargeMode.getValue());
        order.setCharge(total.intValue());
        order.setSubject(subject);
        order.setBody(body);
        order.setOrgUserId(serveUserId);
        order.setProjectCode(projectCode);
        order.setServeId(serveId);
        order.setProjectImage(project.getImage());
        order.setStatus(statusEnum.getStatus());
        order.setOrderType(OrderType.ServeOrder.getValue());
        order.setAddress(address);
        order.setReceiverName(receiverName);
        order.setReceiverMobile(receiverMobile);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setVerifyCode(verifyCode);
        order.setServeItemId(serveItemId);
        
        /**
         * 避免生成订单的时候因为订单编号的唯一约束限制订单的正常添加
         * 写一个遍历5次的循环，当订单创建失败的时候就生成新的订单编号
         */
        for (int i = 0; i < 5; i++) {
            try {
                orderDao.saveOrder(order);

                //如果是免费订单，用户已完成购买，向服务师推送信息
                if(statusEnum == OrderStatus.VALID)
                    umengPushService.pushOrderMessage(serveUserId, userId, subject, orderNumber, order.getId());
                
                return order;
            } catch (org.springframework.dao.DuplicateKeyException e) {
                orderNumber = AlipayService.createOrderNumber();
                order.setOrderNumber(orderNumber);
                logger.error("订单生成错误次数" + (i + 1) + "，生成新的订单编号:" + orderNumber);
            }
        }
        /**
         * 如果循环5次之后还是没有成功创建，就让用户重新提交订单
         */
        throw new OperationException("添加订单失败，请稍后重试", ErrorCodeEnum.SERVE);
    }

    @Override
    public Paging<OrderDTO> listOrder(int userId, ProjectType projectType, int easyValue, int curPage,
                                      int pageSize) throws OperationException {
        List<Integer> sclT = new ArrayList<>();

        List<Integer> slT = new ArrayList<>();
        Boolean refundNotNullT = null;
        Boolean commentNotNullT = null;

        EasyStatusEnum es = EasyStatusEnum.getEasyStatusEnum(easyValue);
        if (es == null) {
            throw new OperationException("非法参数", 400);
        }

        slT = es.getStatusList();
        if (ProjectType.PROJECT_ALL.equals(projectType)) {
            sclT.add(projectType.getValue());
        } else {
            sclT = es.getProjectType();
        }

        refundNotNullT = es.getRefundNotNull();
        commentNotNullT = es.getCommentNotNull();

        // 待使用（限定健康养生与居家养老）
        // 需要判断serveCode属于 健康养生与居家养老
        // 不符合要求的话就直接返回
//        if (EasyStatusEnum.USEABLE.equals(es) && ProjectType.PROJECT_ALL.equals(projectType)) {
//            if (!ProjectType.PROJECT_TOSTORE.getValue().equals(projectType) && !ProjectType.PROJECT_VISIT.getValue().equals(projectType)) {
//                Paging<OrderDTO> p = new Paging<OrderDTO>(curPage, pageSize);
//                p.setQueryProc(new IPagingQueryProc<OrderDTO>() {
//
//                    @Override
//                    public int queryTotal() {
//                        return 0;
//                    }
//
//                    @Override
//                    public List<OrderDTO> queryData(int startRow, int pageSize) {
//                        return new ArrayList<OrderDTO>();
//                    }
//                });
//                return p;
//            }
//            sclT.clear();
//            sclT.add(projectType.getValue());
//        }

        final List<Integer> projectTypeList = sclT;
        final List<Integer> statusList = slT;
        final Boolean refundNotNull = refundNotNullT;
        final Boolean commentNotNull = commentNotNullT;

        Paging<OrderDTO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<OrderDTO>() {

            @Override
            public int queryTotal() {
                return orderDao.countOrderWithCondition(userId, statusList, projectTypeList, refundNotNull, commentNotNull);
            }

            public List<OrderDTO> queryData(int startRow, int pageSize) {
                List<OrderDTO> data = orderDao.listOrderWithCondition(userId, statusList, projectTypeList, refundNotNull, commentNotNull,
                        startRow, pageSize);
                for (OrderDTO d : data) {
                    ProjectType code = ProjectType.values()[d.getProjectType()];
                    StatusEnum statusEnum = getStatusEnum(d.getStatus(), code, d.getRefund(), d.getComment(), d.getEndDate());
                    d.setStatusEnum(statusEnum);
                    // 咨询就查找服务师的环信id
                    if (ProjectType.PROJECT_CONSULT.getValue().equals(d.getProjectType())) {
                        OrgUserDTO orgUser = orgUserService.getOrgUser(d.getOrgUserId());
                        String huanxinUserName = orgUser.getUserCode();
                        d.setHuanxinUserName(huanxinUserName);
                    }
                    if (ProjectType.PROJECT_LESSON.getValue().equals(d.getProjectType())) {
                        String projectCode = d.getProjectCode();
                        int projectLessonId = lessonDao.findLessonByProjectCode(projectCode).getLessonProject().getId();
                        d.setHuanxinUserName(d.getCommonProject().getHuanxinId());
                        d.setProjectLessonId(projectLessonId);
                    }
                }
                return data;
            }
        });

        return p;
    }

    @Override
    public OrderCountDTO getOrderCount(int userId) {
        OrderCountDTO data = new OrderCountDTO();
        EasyStatusEnum es = EasyStatusEnum.PAYABLE;
        int toBePaid = orderDao.countOrderWithCondition(userId, es.getStatusList(), es.getProjectType(), es.getRefundNotNull(), es.getCommentNotNull());
        es = EasyStatusEnum.COMMENTABLE;
        int unComment = orderDao.countOrderWithCondition(userId, es.getStatusList(), es.getProjectType(), es.getRefundNotNull(), es.getCommentNotNull());
        es = EasyStatusEnum.REFUND;
        int refund = orderDao.countOrderWithCondition(userId, es.getStatusList(), es.getProjectType(), es.getRefundNotNull(), es.getCommentNotNull());
        es = EasyStatusEnum.USEABLE;
        int toBeUsed = orderDao.countOrderWithCondition(userId, es.getStatusList(), es.getProjectType(), es.getRefundNotNull(), es.getCommentNotNull());

        data.setToBePaid(toBePaid);
        data.setUnComment(unComment);
        data.setRefund(refund);
        data.setToBeUsed(toBeUsed);
        return data;
    }

    @Override
    public boolean completeOrder(int orderId) {
        return orderDao1.completeOrder(orderId) == 1;
    }

    @Override
    public boolean isOrderBelongToUser(int orderId, int userId) {
        int result = orderDao1.countOrderByUser(orderId, userId);

        return result > 0 ? true : false;
    }

    @Override
    public OrderPO getOrderByOrderNumber(String orderNumber) {
        OrderPO data = orderDao1.getOrderDetailByOrderNumber(orderNumber);
        if (data == null){
            return null;
        }
        if(data.getServeId() != null) { //短信订单没有服务类别
            int serveId = data.getServeId();
            ServeTypeSecondDTO serveDTO = serveDao.getServeType(serveId);
            data.setServeType(serveDTO);
        }
        return data;
    }

    @Override
    public Paging<RefundOrderVO> findRefundOrderList(Integer orgId, Integer pageIndex, Integer pageSize) {
        Paging<RefundOrderVO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<RefundOrderVO>() {
            @Override
            public int queryTotal() {
                return orderDao1.countRefundOrder(orgId, null);
            }

            @Override
            public List<RefundOrderVO> queryData(int startRow, int pageSize) {
                List<RefundOrderVO> list = orderDao1.findRefundOrderList(orgId, null, (pageIndex - 1) * pageSize, pageSize);
                for (RefundOrderVO refundOrderVO : list) {
                    refundOrderVO.setCharge(NumberUtils.changeF2Y(refundOrderVO.getCharge().intValue() + ""));
                }
                return list;
            }
        });
        return paging;
    }

    @Override
    public void refundAuditedOrder(String orderNumber, Integer auditorId) {
        orderDao1.updateRefundOrderStatus(orderNumber, RefundOrderStatus.AUDITED.getStatus(), auditorId);
//        orderDao1.updateOrderStatus(orderNumber, OrderStatus.REFUNDING.getStatus());
    }

    @Override
    public void refundingOrder(String orderNumber) {
        orderDao1.updateRefundOrderStatus(orderNumber, RefundOrderStatus.REFUNDIND.getStatus(), null);
        orderDao1.updateOrderStatusByNumber(orderNumber, OrderStatus.REFUNDING.getStatus());

    }

    @Override
    public void refundCompleteOrder(String orderNumber) {
        orderDao1.updateRefundOrderStatus(orderNumber, RefundOrderStatus.COMPLETE.getStatus(), null);
        orderDao1.updateOrderStatusByNumber(orderNumber, OrderStatus.REFUNDED.getStatus());
    }

    @Override
    public void refundFailOrder(String orderNumber) {
        orderDao1.updateRefundOrderStatus(orderNumber, RefundOrderStatus.FAIL.getStatus(), null);
    }

    /**
     * 得到退款订单详细信息
     *
     * @param orderId
     * @return
     */
    public RefundOrderVO getRefundOrderDetail(int orderId) {
        RefundOrderVO data = orderDao1.getRefundOrderDetail(orderId, null);
        // charge修改金额
        double charge = NumberUtils.changeF2Y(String.valueOf(data.getCharge().intValue()));
        data.setCharge(charge);
        return data;
    }

    /**
     * 得到退款订单详细信息
     *
     * @param orderNumber
     * @return
     */
    public RefundOrderVO getRefundOrderDetail(String orderNumber) {
        RefundOrderVO refundOrderVO = orderDao1.getRefundOrderDetail(null, orderNumber);
        if (refundOrderVO.getCharge() != null) {
            refundOrderVO.setCharge(NumberUtils.changeF2Y(refundOrderVO.getCharge().intValue() + ""));
        }
        return refundOrderVO;
    }

    private StatusEnum getStatusEnum(Integer status, ProjectType projectType, RefundOrderVO refund, CommentPO comment, Date endDate) {
        // 待支付
        if (OrderStatus.PAYABLE.getStatus().equals(status)) {
            return StatusEnum.PAYABLE;
        }
        // 使用中
        if (online(projectType) && OrderStatus.VALID.getStatus().equals(status)) {
            return StatusEnum.USING;
        }
        // 待使用
        if (!online(projectType) && OrderStatus.VALID.getStatus().equals(status)) {
            return StatusEnum.USABLE;
        }
        // 待评价
        if (commentable(projectType) && OrderStatus.COMPLETED.getStatus().equals(status) && comment == null) {
            return StatusEnum.COMMENTABLE;
        }
        // 完成
        if (!needComment(projectType) && OrderStatus.COMPLETED.getStatus().equals(status)) {
            return StatusEnum.COMPLETED;
        }
        if (needComment(projectType) && OrderStatus.COMPLETED.getStatus().equals(status) && comment != null) {
            return StatusEnum.COMPLETED;
        }
        // TODO 过期 = 有效 + 超过结束日期
        if (OrderStatus.VALID.getStatus().equals(status) && endDate != null) {
            Instant instant = endDate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime endDateTime = LocalDateTime.ofInstant(instant, zone);
            if (LocalDateTime.now().isAfter(endDateTime)) {
                return StatusEnum.OVERDUE;
            }
        }

        // 退款失败
        if (OrderStatus.REFUNDING.getStatus().equals(status) && refund != null && 3 == refund.getRefundStatus()) {
            return StatusEnum.REFUND_FAILED;
        }
        // 退款中
        if (OrderStatus.REFUNDING.getStatus().equals(status) && refund != null) {
            return StatusEnum.REFUNDING;
        }
        // 退款成功
        if (OrderStatus.REFUNDED.getStatus().equals(status)) {
            return StatusEnum.REFUNDED;
        }
        return null;
    }

    private boolean online(ProjectType projectType) {
        if (ProjectType.PROJECT_CONSULT.equals(projectType) || ProjectType.PROJECT_LESSON.equals(projectType)) {
            return true;
        }
        return false;
    }

    private boolean commentable(ProjectType projectType) {
        if (ProjectType.PROJECT_CONSULT.equals(projectType) || ProjectType.PROJECT_TOSTORE.equals(projectType)
                || ProjectType.PROJECT_VISIT.equals(projectType)) {
            return true;
        }
        return false;
    }

    private boolean needComment(ProjectType projectType) {
        if (ProjectType.PROJECT_LESSON.equals(projectType)) {
            return false;
        }
        if (ProjectType.PROJECT_CONSULT.equals(projectType) || ProjectType.PROJECT_TOSTORE.equals(projectType)
                || ProjectType.PROJECT_VISIT.equals(projectType)) {
            return true;
        }
        return false;
    }

    /**
     * 订单退款
     *
     * @param orderId     订单ID
     * @param refundCause 退款原因
     * @param userId      用户ID
     * @return
     * @throws DataBaseException
     * @author wuj
     * @updateTime none
     * @since 2017-07-17 14:27:45
     */
    @Transactional
    @Override
    public Map<String, Object> updateRefundOrder(int orderId, String refundCause, int userId) throws DataBaseException {
        /** 将订单状态设置为 退款中->7 */
        int result1 = orderDao1.updateOrderStatus(orderId, OrderStatus.REFUNDING.getStatus());
        if (result1 <= 0) {
            throw new DataBaseException("订单状态更新失败");
        }

        /** 获取OrderFlowPO */
        OrderPO orderPO = orderDao1.getOrderDetail(orderId);
        if (orderPO == null) {
            throw new DataBaseException("未查询到用户订单,订单ID:" + orderId);
        }

        String orderNumber = orderPO.getOrderNumber();
        OrderFlowPO orderFlowPO = orderFlowDao.getOrderFlowPOByOrderNumber(orderNumber);
        if (orderFlowPO == null) {
            throw new DataBaseException("未查到订单号:" + orderNumber + " 对应流水");
        } else if (System.currentTimeMillis() - orderFlowPO.getCreateTime().getTime() > 1000 * 60 * 60 * 24 * 7) {
            throw new DataBaseException("订单支付超过7天,不能退款");
        }

        /**装配OrderRefundPO，插入数据库*/
        if (orderRefundDao.getOrderRefundPOByOrderNumber(orderNumber) != null) {
            //订单号重复
            throw new DataBaseException("此订单已进入退款流程");
        }

        OrderRefundPO orderRefundPO = new OrderRefundPO();
        orderRefundPO.setCause(refundCause);
        orderRefundPO.setOrderNumber(orderNumber);
        orderRefundPO.setRefundTime(new Date());
        orderRefundPO.setStatus(OrderRefundStatus.REFUND_WAIT.getStatus());

        int result2 = orderRefundDao.insertSelective(orderRefundPO);
        if (result2 <= 0) {
            throw new DataBaseException("数据库异常，退款失败");
        }

        // 进行消息推送
        umengPushService.refundMsgPush(orderPO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("charge", MoneyUtils.unitTransfer(String.valueOf(orderPO.getCharge())));
        resultMap.put("payType", orderFlowPO.getPayType());
        resultMap.put("refundTime", orderRefundPO.getRefundTime());
        return resultMap;
    }

    /**
     * 获取退款订单详情
     *
     * @param orderId
     * @return
     * @throws DataBaseException
     * @author wuj
     * @updateTime none
     * @since 2017-07-18 09:57:31
     */
    public Map<String, Object> getRefundDetail(int orderId) throws DataBaseException {
        /** 获取OrderPO */
        OrderPO orderPO = orderDao1.getOrderDetail(orderId);
        if (orderPO == null) {
            throw new DataBaseException("未查询到用户订单,订单ID:" + orderId);
        }

        //获取orderRefundPO
        String orderNumber = orderPO.getOrderNumber();
        OrderRefundPO orderRefundPO = orderRefundDao.getOrderRefundPOByOrderNumber(orderNumber);
        if (orderRefundPO == null) {
            throw new DataBaseException("未查询到退款订单，订单号:" + orderNumber);
        }

        //获取orderFlowPO
        OrderFlowPO orderFlowPO = orderFlowDao.getOrderFlowPOByOrderNumber(orderNumber);
        if (orderFlowPO == null) {
            throw new DataBaseException("未查到订单号:" + orderNumber + " 对应流水");
        }


        //装配返回对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("charge", MoneyUtils.unitTransfer(String.valueOf(orderPO.getCharge())));
        resultMap.put("payType", orderFlowPO.getPayType());
        resultMap.put("status", orderRefundPO.getStatus());
        /**
         * 默认的时间格式实现为: yyyy-MM-dd
         * 需要返回的格式: yyyy-MM-dd HH:mm:ss
         * 所以这里提前做一下时间格式处理
         */
        resultMap.put("refundTime", orderRefundPO.getRefundTime() == null ? "" : DateTimeUtilT.dateTime(orderRefundPO.getRefundTime()));
        resultMap.put("auditorTime", orderRefundPO.getAuditorTime() == null ? "" : DateTimeUtilT.dateTime(orderRefundPO.getAuditorTime()));
        resultMap.put("completeTime", orderRefundPO.getCompleteTime() == null ? "" : DateTimeUtilT.dateTime(orderRefundPO.getCompleteTime()));

        return resultMap;
    }

    /**
     * 确认退款订单
     *
     * @param orderId
     * @param auditorId
     * @throws AlipayApiException
     * @throws IOException
     * @throws DataBaseException
     * @author wuj
     * @updateTime none
     * @since 2017-07-17 14:26:09
     */
    @Override
    public void confirmRefundOrder(int orderId, int auditorId) throws AlipayApiException, IOException, DataBaseException {
        //调用支付宝退款服务，获取响应
        AlipayTradeRefundResponse response = iAlipayService.refund(orderId);

        if (!response.isSuccess()) {
            //没有响应处理逻辑
            throw new AlipayApiException("支付宝没有响应:" + response.getMsg());
        }

        // 获取OrderPO
        OrderPO orderPO = orderDao1.getOrderDetail(orderId);

        //获取orderRefundPO
        String orderNumber = orderPO.getOrderNumber();
        OrderRefundPO orderRefundPO = orderRefundDao.getOrderRefundPOByOrderNumber(orderNumber);

        //获取orderFlowPO
        OrderFlowPO orderFlowPO = orderFlowDao.getOrderFlowPOByOrderNumber(orderNumber);

        //退款成功
        if ("10000".equals(response.getCode())) {
            logger.info("支付宝退款成功，订单ID:" + orderId);

            try {
                //修改订单状态
                orderDao1.updateOrderStatus(orderId, OrderStatus.REFUNDED.getStatus());

                //更新orderRefund 状态
                orderRefundPO.setStatus(OrderRefundStatus.REFUND_COMPLETE.getStatus());
                orderRefundPO.setAuditorTime(new Date());
                orderRefundPO.setAuditorId(auditorId);
                orderRefundPO.setCompleteTime(new Date());
                orderRefundDao.updateByPrimaryKeySelective(orderRefundPO);

                //插入退款订单流水
                OrderRefundFlowPO orderRefundFlowPO = assembleOrderRefundFlowPo(orderFlowPO);
                orderRefundFlowPO.setCreateTime(response.getGmtRefundPay());
                orderRefundFlowDao.insertSelective(orderRefundFlowPO);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataBaseException("退款成功,数据更新异常");
            }
        } else if (AlipayReturnCode.RETRY.contains(response.getSubMsg())) {
            //支付宝返回异常的处理策略为重试,将退款订单状态改为:退款中->4
            logger.info("支付宝退款异常:" + response.getSubMsg() + ",退款订单号:" + orderId);

            //更新orderRefund 状态
            orderRefundPO.setStatus(OrderRefundStatus.REFUND_REFUNDING.getStatus());
            orderRefundPO.setAuditorTime(new Date());
            orderRefundPO.setAuditorId(auditorId);
            // TODO: 2017/7/18 0018 是否需要插入这条记录？
            orderRefundPO.setCompleteTime(new Date());
            orderRefundDao.updateByPrimaryKeySelective(orderRefundPO);
        } else {
            //一些配置上的异常，调试OK后基本不会出现
            logger.info("支付宝退款失败，退款订单:" + orderId);
            throw new DataBaseException("支付宝退款失败，失败原因:" + response.getMsg());
        }
    }

    /**
     * 退款订单流水对象装配
     * orderFlowPO与orderRefundPO之间只有flowType 与 createTime的差异
     *
     * @param orderFlowPO
     * @return
     * @author wuj
     * @updateTime none
     * @since 2017-07-17 14:27:18
     */
    private OrderRefundFlowPO assembleOrderRefundFlowPo(OrderFlowPO orderFlowPO) {
        OrderRefundFlowPO orderRefundFlowPO = new OrderRefundFlowPO();

        orderRefundFlowPO.setOrderNumber(orderFlowPO.getOrderNumber());
        orderRefundFlowPO.setRealName(orderFlowPO.getRealName());
        orderRefundFlowPO.setOrgId(orderFlowPO.getOrgId());
        orderRefundFlowPO.setOrgName(orderFlowPO.getOrgName());
        orderRefundFlowPO.setServeName(orderFlowPO.getServeName());
        orderRefundFlowPO.setFlowType(FlowType.REFUND);
        orderRefundFlowPO.setPayDevice(orderFlowPO.getPayDevice());
        orderRefundFlowPO.setPayType(orderFlowPO.getPayType().byteValue());
        orderRefundFlowPO.setCost(orderFlowPO.getCost());
        orderRefundFlowPO.setPayCost(orderFlowPO.getPayCost());
        orderRefundFlowPO.setUsePoints(orderFlowPO.getUsePoints());
        orderRefundFlowPO.setPoints(orderFlowPO.getPoints());
        orderRefundFlowPO.setPointsCost(orderFlowPO.getPointsCost());
        orderRefundFlowPO.setPayAccount(orderFlowPO.getPayAccount());
        orderRefundFlowPO.setTradeNo(orderFlowPO.getTradeNo());
        orderRefundFlowPO.setSellerAccount(orderFlowPO.getSellerAccount());
        orderRefundFlowPO.setProfitShare(orderFlowPO.getProfitShare());
        orderRefundFlowPO.setOrgIncome(orderFlowPO.getOrgIncome());
        orderRefundFlowPO.setSysIncome(orderFlowPO.getSysIncome());

        return orderRefundFlowPO;
    }

    @Override
    public void deleteOrder(int orderId) throws DataBaseException {
        int result = 0;
        try {
            result = orderDao1.deleteOrder(orderId);
        } catch (Exception e) {
            throw new DataBaseException("删除订单失败");
        }
        if (result < 1) {
            throw new DataBaseException("订单不可删除");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void finishOrder(String outNo, String tradeNo, String payerAccount, String sellerAccount,
                               double totalCount, int payType, String deviceType, Integer couponsId) throws OperationException {
        int payCost = 0;
        String msg = "支付宝";
        if (isOverFlowExist(outNo)) {
            throw new OperationException("订单流水已存在", ErrorCodeEnum.REPEAT);
        }
        switch (payType) {
            case 1:// 支付宝
                payCost = NumberUtils.changeY2F(totalCount + "");
                break;
            case 2:// 微信支付
                msg = "微信";
                String currency= (totalCount+"").replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
                Double d1=new Double(currency); 
               payCost = d1.intValue();
                break;
            case 3:// 网银在线
                
                break;
        }

        OrderFlowPO orderFlow = new OrderFlowPO();

        OrderFlowBasicDTO orderFlowBasic = orderDao.getOrderBasicDataByOrderNumber(outNo);

        int totalOriginal = orderFlowBasic.getTotal();
        int orderType = orderFlowBasic.getOrderType();
        String realName = orderFlowBasic.getUserRealName();
        Integer orgId = orderFlowBasic.getOrgId();
        String orgName = orderFlowBasic.getOrgName();
        String serveName = orderFlowBasic.getServeName();
        AgetnIncomeVo vo = null;
        Integer profitShare = 0;
        
        int couponPrice = 0;
        UserElectronicCouponsPO coupon = null;
        if (couponsId != null && couponsId!=0) {
            coupon = couponsService.getCoupons(couponsId);
            couponPrice = coupon.getPrice();
        }

        if (payCost < (totalOriginal - couponPrice)) {
            throw new OperationException("订单异常:订单(编号" + outNo + ")订单预计收取金额为" + (totalOriginal - couponPrice) + "分, "+msg+"只收到转账" + payCost + "分", ErrorCodeEnum.REQUEST);
        }

        OrderPO order = orderDao1.getOrderDetailByOrderNumber(outNo);
        Integer status = order.getStatus();
        UserPO user = order.getUser();
        int userId = user.getId();
        String userHuanxinId = user.getUserCode();
        String projectCode = order.getProjectCode();
        Integer serveItemId = order.getServeItemId();
        int number = order.getNumber();
        int chargeModeValue = order.getChargeMode();
        if (!OrderStatus.PAYABLE.getStatus().equals(status)) {
            throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
        }
        
        // 短信充值订单
        if (OrderType.SMSOrder.getValue() == orderType) {
            orgName = "广州通众电气实业有限公司";
            // 短信充值订单需要为用户充值短信
            smsRemindService.addMemberSmsRemind(userId, number);
            int result = orderDao1.updateOrderStatusByNumber(outNo, OrderStatus.COMPLETED.getStatus());
            if (result == 0) {
                throw new OperationException("更新订单状态失败", ErrorCodeEnum.FAILED);
            }
        }
        // 服务订单
        if (OrderType.ServeOrder.getValue() == orderType) {
//            // 平台分成
//            int sysIncome = 0;
//            int orgIncome = 0;
            // 剩余服务次数
            int timesRemaining = 1; // 课堂以及线下，上门服务的服务剩余次数定为1，咨询剩余次数需要根据用户购买服务次数确定

            profitShare = orderFlowBasic.getProfitShare();

                /** 替换成代理商分成 */
//            double orgIncome_d = payCost * ((100 - profitShare) / 100.0);
//            BigDecimal decimal_org = new BigDecimal(orgIncome_d);
//            orgIncome = decimal_org.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
//            sysIncome = payCost - orgIncome;
//
//            if (sysIncome == 0 && orgIncome == 0 && (sysIncome + orgIncome != payCost)) {
//                sysIncome = payCost;
//            }
//
//            orderFlow.setSysIncome(sysIncome);
//            orderFlow.setOrgIncome(orgIncome);
            orderFlow.setOrgIncome(0);

            ProjectType code = ProjectType.values()[order.getProject().getProjectType()];
            Date endDate = null;
            String verifyCode = null;

            // 课堂
            if (ProjectType.PROJECT_LESSON.equals(code)) {
                // 将用户拉入环信群组
                RecommendedLessonDTO lesson = lessonDao.findLessonByProjectCode(projectCode);
                String huanxinLessonId = lesson.getLessonProject().getHuanxinId();
                boolean result = huanxinService.joinGroup(huanxinLessonId, userHuanxinId);
                if (!result) {
                    throw new OperationException("加入课堂失败", ErrorCodeEnum.FAILED);
                }
            }
            // 咨询
            if (ProjectType.PROJECT_CONSULT.equals(code)) {
                Date start = new Date();    // 获取当前时间，用于计算咨询服务结束时间
                // 查找最新的有效订单
                int serveUserId = order.getEmployee().getId();
                OrderDTO latestValidOrder = orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUserId, ProjectType.PROJECT_CONSULT.getValue());
                if (latestValidOrder != null) {
                    // 特别说明下，更新订单状态status字段的计划任务，更新频率不高，这里查出来的有效订单，可能已经过期，但是还没有更新到status字段上
                    // 所以正确的判断是通过endDate与now()
                    Date latestValidOrderEndDate = latestValidOrder.getEndDate();
                    // 如果存在最新的有效订单
                    if (latestValidOrderEndDate.after(new Date())) {
                        // 修改开始时间
                        start = latestValidOrderEndDate;
                        // 修改订单为已完成
                        OrderDTO orderDTO = new OrderDTO();
                        orderDTO.setId(latestValidOrder.getId());
                        orderDTO.setStatus(OrderStatus.COMPLETED.getStatus());
                        int result = orderDao.updateOrder(orderDTO);
                        if (result == 0) {
                            throw new OperationException("修改旧订单状态为已完成失败", ErrorCodeEnum.FAILED);
                        }
                    }
                }
                
                ChargeModeEnum chargeMode = ChargeModeEnum.getChargeModeEnum(chargeModeValue);
                endDate = calculateConsultEndDate(number, start, chargeMode);
                // 咨询剩余次数为服务购买次数
                // 咨询服务的剩余次数此处不做处理
                timesRemaining = number;
            }
            // 线下与上门服务
            if (!online(code)) {
                // 生成一个验证码
                verifyCode = com.lifeshs.utils.RandCodeUtil.randNumberCodeByCustom("5", 8);
                // 结束日期目前尚未规定
            }

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setStartDate(new Date()); // 开始日期设置为今天
            orderDTO.setStatus(OrderStatus.VALID.getStatus());
            orderDTO.setEndDate(endDate);
            orderDTO.setProfitShare(profitShare);
//            orderDTO.setOrgIncome(orgIncome);
            orderDTO.setVerifyCode(verifyCode);
            orderDTO.setTimesRemaining(timesRemaining);
            orderDTO.setCouponsId(couponsId);   // 保存该订单使用的电子券id
            
            vo = incomeService.orderIncome(outNo, payCost, "Service");
            if(vo!=null){
                int sysIncome = vo.getSysIncome();
                int orgIncome = vo.getIntroduceOrgIncome(); //引入机构
                int serviceOrgIncome = vo.getServiceOrgIncome(); //服务机构
                int agentIncome = vo.getAgentIncome(); //代理商分成
                int salesmanIncome = vo.getSalesmanIncome(); //业务员分成
                
                //区分引入机构
                if(StringUtils.isNotBlank(vo.getSysPlatformId()) && vo.getSysPlatformId().equals(vo.getIntroduceOrgId())){
                    //平台
                    sysIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getAgentId()) && vo.getAgentId().equals(vo.getIntroduceOrgId())){
                    //代理商
                    agentIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getSalesmanId()) && vo.getSalesmanId().equals(vo.getIntroduceOrgId())){
                    //业务员
                    salesmanIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }else if (vo.getServiceOrgId().equals(vo.getIntroduceOrgId())){
                    //服务机构
                    serviceOrgIncome += vo.getIntroduceOrgIncome();
                    orgIncome = 0;
                }
                
                //服务机构
                if(StringUtils.isNotBlank(vo.getSysPlatformId()) && vo.getSysPlatformId().equals(vo.getServiceOrgId())){
                    //平台
                    sysIncome += serviceOrgIncome;
                    serviceOrgIncome = 0;
                }else if(StringUtils.isNotBlank(vo.getAgentId()) && vo.getAgentId().equals(vo.getServiceOrgId())){
                    //代理商
                    agentIncome += serviceOrgIncome;
                    serviceOrgIncome = 0;
                }
                orderDTO.setSysUserNo(vo.getSysPlatformId());
                orderDTO.setSysIncome(sysIncome);
                orderDTO.setIntroduceOrgIncome(orgIncome);
                orderDTO.setIntroduceOrgUserNo(vo.getIntroduceOrgId());
                orderDTO.setAgentUserNo(vo.getAgentId());
                orderDTO.setAgentIncome(agentIncome);
                orderDTO.setSalesmanUserNo(vo.getSalesmanId());
                orderDTO.setSalesmanIncome(salesmanIncome);
                orderDTO.setServiceOrgUserNo(vo.getServiceOrgId());
                orderDTO.setServiceOrgIncome(serviceOrgIncome);
            }
            
            
            int result = orderDao.updateOrder(orderDTO);
            if (result == 0) {
                throw new OperationException("更新新订单信息失败", ErrorCodeEnum.FAILED);
            }
            //发推送消息
            try {
                umengPushService.pushOrderMessage(order.getEmployee().getId(), userId, order.getSubject(), order.getOrderNumber(), order.getId());
            }catch (Exception ex){logger.error("finishOrder时推送APP失败[OrderId:"+order.getId()+"]");}
        }
        // 默认 总金额 = 支付金额
        int cost = payCost;
        // 判断用户是否使用了电子券
        if (couponsId != null && couponsId!=0) {
            UserElectronicCouponsPO couponsPO = couponsService.getCoupons(couponsId);
            // 总金额 = 支付金额 + 电子券的价格
            cost = payCost + couponsPO.getPrice();
            // 更新电子券为finish状态
            couponsService.finishCoupons(couponsId, userId, projectCode, serveItemId);
        }
        
        orderFlow.setPayCost(payCost);
        orderFlow.setCost(cost);
        orderFlow.setOrderNumber(outNo);
        orderFlow.setRealName(realName);
        orderFlow.setOrgId(orgId);
        orderFlow.setOrgName(orgName);
        orderFlow.setServeName(serveName);
        orderFlow.setFlowType(new Byte(1 + "")); // 收款_1
        orderFlow.setPayType(new Byte(payType + ""));
        orderFlow.setUsePoints(false);
        orderFlow.setPoints(0);
        orderFlow.setPointsCost(0);
        orderFlow.setPayAccount(payerAccount);
        orderFlow.setTradeNo(tradeNo);
        orderFlow.setSellerAccount(sellerAccount);
        orderFlow.setProfitShare(profitShare);
        orderFlow.setCreateTime(new Date());
        orderFlow.setPayDevice(deviceType);
        if(vo!=null){
            orderFlow.setSysIncome(vo.getSysIncome());
            orderFlow.setSysProfitShare(vo.getSysProfitShare());
            orderFlow.setAgentIncome(vo.getAgentIncome());
            orderFlow.setAgentProfitShare(vo.getAgentProfitShare());
            orderFlow.setSalesmanIncome(vo.getSalesmanIncome());
            orderFlow.setSalesmanProfitShare(vo.getSalesmanProfitShare());
            orderFlow.setIntroduceOrgIncome(vo.getIntroduceOrgIncome());
            orderFlow.setIntroduceOrgProfitShare(vo.getIntroduceOrgProfitShare());
            orderFlow.setServiceOrgIncome(vo.getServiceOrgIncome());
            orderFlow.setServiceOrgProfitShare(vo.getServiceOrgProfitShare());
            orderFlow.setOrgUserUserId(vo.getOrgUserUserId());
        }
        
        int effectRow = orderFlowDao.insert(orderFlow);
        if (effectRow == 0) {
            throw new OperationException("添加流水失败", ErrorCodeEnum.FAILED);
        }
    }

    @Transactional
    @Override
    public boolean finishOrderStoreSms(String outNo, String tradeNo, String payerAccount, String sellerAccount,
                                       double totalCount, int payType, String deviceType, String extraData) {
        if (isOverFlowExist(outNo)) {
            return false;
        }
        switch (payType) {
            case 1:// 支付宝

                break;
            case 2:// 网银在线

                break;
            case 3:// 微信支付

                break;
        }

        OrderFlowPO orderFlow = new OrderFlowPO();

        OrderFlowBasicDTO orderFlowBasic = orderDao.getOrderStoreSmsBasicDataByOrderNumber(outNo);
        // 输出内容
        logger.info("输出订单流水基础信息:" + orderFlowBasic);

        int total_original = orderFlowBasic.getTotal();
        String realName = orderFlowBasic.getUserRealName();
        Integer orgId = orderFlowBasic.getOrgId();
        String orgName = orderFlowBasic.getOrgName();
        String serveName = orderFlowBasic.getServeName();
        Integer profitShare = 0;
        int total_alipay = NumberUtils.changeY2F(totalCount + "");

        if (total_alipay != total_original) {
            logger.error("订单异常:订单(编号" + outNo + ")订单预计收取金额为" + total_original + "分, 支付宝只收到转账" + total_alipay + "分");
            return false;
        }
        OrderPO order = orderDao1.getOrderStoreSmsDetailByOrderNumber(outNo);
        int number = order.getNumber();
        orgName = "广州通众电气实业有限公司";


        // 短信充值订单需要为用户充值短信
        try {
            smsRemindService.addOrgSmsRemind(orgId, number);
        } catch (OperationException e) {
            // TODO 充值失败
            e.printStackTrace();
        }

        orderDao1.updateOrderStoreSmsStatus(outNo, OrderStatus.COMPLETED.getStatus());

        orderFlow.setPayCost(total_alipay);
        orderFlow.setCost(total_alipay);
        orderFlow.setOrderNumber(outNo);
        orderFlow.setRealName(realName);
        orderFlow.setOrgId(orgId);
        orderFlow.setOrgName(orgName);
        orderFlow.setServeName(serveName);
        orderFlow.setFlowType(new Byte(1 + "")); // 收款_1
        orderFlow.setPayType(new Byte(payType + ""));
        orderFlow.setUsePoints(false);
        orderFlow.setPoints(0);
        orderFlow.setPointsCost(0);
        orderFlow.setPayAccount(payerAccount);
        orderFlow.setTradeNo(tradeNo);
        orderFlow.setSellerAccount(sellerAccount);
        orderFlow.setProfitShare(profitShare);
        orderFlow.setCreateTime(new Date());
        orderFlow.setPayDevice(deviceType);
        // 输出内容
        logger.info("订单流水信息:" + JSONObject.fromObject(orderFlow).toString());
        int effectRow = orderFlowDao.insert(orderFlow);
        return effectRow == 1;
    }

    private boolean isOverFlowExist(String orderNumber) {
        OrderFlowPO orderFlow = orderFlowDao.getOrderFlowPOByOrderNumber(orderNumber);
        if (orderFlow == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 计算咨询的结束日期
     *
     * @param number
     * @return
     * @author yuhang.weng
     * @DateTime 2017年7月20日 下午2:51:48
     */
    private Date calculateConsultEndDate(int number, Date start, ChargeModeEnum chargeMode) {
        // TODO 48小时内，数量是1次
        // 这里设置了24*(2n+1)，n表示购买次数
        // 这里多加24小时，是为了避免用户如果是在购买时间不为00:00分的时候，服务实际过期时间会缩短
        // 同时，如果时间刚好是00:00，过期时间就是24*2n，会在下面的判断中减去24小时
        /*int plusHour = 24 * (2 * number + 1);
        LocalDateTime localEndDate = LocalDateTime.now().plusHours(plusHour);
        int hour = localEndDate.getHour();
        int minute = localEndDate.getMinute();
        if (hour == 0 && minute == 0) {
            localEndDate = localEndDate.minusDays(1);
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant endInstant = localEndDate.atZone(zone).toInstant();
        return Date.from(endInstant);*/

        long days = 0;
        if (ChargeModeEnum.FREE.equals(chargeMode) || ChargeModeEnum.TIMES.equals(chargeMode)) {
            // 按次每次添加2天
            days = 2 * number;
        }
        if (ChargeModeEnum.MONTH.equals(chargeMode)) {
            // 按月每次添加30天
            days = 30 * number;
        }
        if (ChargeModeEnum.YEAR.equals(chargeMode)) {
            // 按年每次添加365天
            days = 365 * number;
        }

        Instant instant = start.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localStartDate = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime localEndDate = localStartDate.plusDays(days);
        Instant endInstant = localEndDate.atZone(zone).toInstant();
        return Date.from(endInstant);
    }

    @Override
    public List<OrderPO> getOrderPOByCodeAndUserId(int userId, ProjectType projectType, int pageIndex, int pageSize) {
        List<OrderPO> data = orderDao1.getOrderPOByCodeAndUserId(userId, projectType.getValue(), (pageIndex - 1) * pageSize, pageSize);
        for (OrderPO d : data) {
            StatusEnum status = getStatusEnum(d.getStatus(), projectType, d.getRefund(), d.getComment(), d.getEndDate());
            d.setStatusEnum(status);
        }
        return data;
    }

    @Override
    public List<OrderPO> listOrderByProjectCode(String projectCode) {
        return orderDao1.findOrderByProjectCodeList(projectCode);
    }

    @Override
    public List<OrderPO> listConsultOrderOutOfEndDate(Date endTime, Date startTime) {
        return orderDao1.findConsultOrderOutOfEndDateList(endTime, startTime);
    }

    @Override
    public String getOrderAlipaySignInfo(OrderPO order, String notifyUrl, Map<String, String> extraData) throws OperationException {
        if (order == null || OrderStatus.PAYABLE.getStatus().intValue() != order.getStatus()) {
            throw new OperationException(String.format(com.lifeshs.common.constants.common.Error.NOT_FOUND, "订单"), ErrorCodeEnum.NOT_FOUND);
        }
        String orderNumber = order.getOrderNumber();
        int orderType = order.getOrderType();
        Double total = NumberUtils.changeF2Y(String.valueOf(order.getCharge()));
        String subject = "";
        String body = "";

        PayReturnOrderTypeEnum payReturnOrderType = null;
        if (OrderType.ServeOrder.getValue() == orderType) {
            subject = "服务订单";
            body = Toolkits.projectTilte(order.getSubject());

            String projectCode = order.getProjectCode();
            ServiceCommonDTO commonProject = serveDao.getCommonProject(projectCode);
            int projectStatus = commonProject.getStatus();
            if (Status.ONLINE.getValue() != projectStatus) {
                throw new OperationException("该服务已下架，请勿继续购买", ErrorCodeEnum.REQUEST);
            }

            Integer serveId = order.getServeId();
            ProjectType code = ProjectType.values()[order.getProject().getProjectType()];
            // 线上服务不能重复购买
            int userId = order.getUser().getId();
            int serveUserId = order.getEmployee().getId();
            if (ProjectType.PROJECT_CONSULT.equals(code)) {
                // 只能购买一个服务师的咨询服务
                // 如果projectCode相同说明这次是续费请求，可以正常进行下一步的操作
                OrderDTO validConsultOrder = orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUserId, code.getValue());
                if (validConsultOrder != null && !validConsultOrder.getProjectCode().equals(projectCode)) {
                    throw new OperationException("请勿购买同一个服务师的咨询服务", ErrorCodeEnum.REQUEST);
                }
            }
            if (ProjectType.PROJECT_LESSON.equals(code)) {
                LessonPO lesson = lessonService.getLesson(projectCode);
                if (lesson == null) {
                    throw new OperationException(String.format(Error.NOT_FOUND, "课堂服务"), ErrorCodeEnum.NOT_FOUND);
                }
                OrderPO orderPO = orderDao1.findUserValidLessonOrder(userId, lesson.getId());
                if (orderPO != null) {
                    throw new OperationException("请勿重复购买该课堂的服务", ErrorCodeEnum.REPEAT);
                }
            }
            
            payReturnOrderType = PayReturnOrderTypeEnum.SERVE;
        }

        if (OrderType.SMSOrder.getValue() == orderType) {
            subject = "短信充值订单";
            body = "用户充值" + total + "元";
            payReturnOrderType = PayReturnOrderTypeEnum.SMS;
        }

        extraData.put("orderType", String.valueOf(payReturnOrderType.getValue()));
        String orderInfo = AlipayService.getOrderInfo(total, subject, body, orderNumber, extraData, notifyUrl);
        return orderInfo;
    }
    
    @Override
    public String getOrderAlipaySignInfoNew(PaymentOrderDTO order,String notifyUrl, Map<String, String> extraData) throws OperationException {
        if (order == null || OrderStatus.PAYABLE.getStatus().intValue() != order.getStatus()) {
            throw new OperationException(String.format(com.lifeshs.common.constants.common.Error.NOT_FOUND, "订单"), ErrorCodeEnum.NOT_FOUND);
        }
        String orderNumber = order.getOrderNumber();
        int orderType = order.getOrderType();
        Double total = NumberUtils.changeF2Y(String.valueOf(order.getCharge()));
        String subject = "";
        String body = "";

        PayReturnOrderTypeEnum payReturnOrderType = null;
        if (OrderType.ServeOrder.getValue() == orderType) {
            subject = "服务订单";
            body = Toolkits.projectTilte(order.getSubject());

            String projectCode = order.getProjectCode();
            ServiceCommonDTO commonProject = serveDao.getCommonProject(projectCode);
            int projectStatus = commonProject.getStatus();
            if (Status.ONLINE.getValue() != projectStatus) {
                throw new OperationException("该服务已下架，请勿继续购买", ErrorCodeEnum.REQUEST);
            }

            Integer serveId = order.getServeId();
            ProjectType code = ProjectType.values()[order.getProjectType()];
            // 线上服务不能重复购买
            int userId = order.getUserId();
            int serveUserId = order.getEmployee();
            if (ProjectType.PROJECT_CONSULT.equals(code)) {
                // 只能购买一个服务师的咨询服务
                // 如果projectCode相同说明这次是续费请求，可以正常进行下一步的操作
                OrderDTO validConsultOrder = orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUserId, code.getValue());
                if (validConsultOrder != null && !validConsultOrder.getProjectCode().equals(projectCode)) {
                    throw new OperationException("请勿购买同一个服务师的咨询服务", ErrorCodeEnum.REQUEST);
                }
            }
            if (ProjectType.PROJECT_LESSON.equals(code)) {
                LessonPO lesson = lessonService.getLesson(projectCode);
                if (lesson == null) {
                    throw new OperationException(String.format(Error.NOT_FOUND, "课堂服务"), ErrorCodeEnum.NOT_FOUND);
                }
                OrderPO orderPO = orderDao1.findUserValidLessonOrder(userId, lesson.getId());
                if (orderPO != null) {
                    throw new OperationException("请勿重复购买该课堂的服务", ErrorCodeEnum.REPEAT);
                }
            }
            
            payReturnOrderType = PayReturnOrderTypeEnum.SERVE;
        }

        if (OrderType.SMSOrder.getValue() == orderType) {
            subject = "短信充值订单";
            body = "用户充值" + total + "元";
            payReturnOrderType = PayReturnOrderTypeEnum.SMS;
        }
        
        if (OrderType.Drugs.getValue() == orderType) {
            subject = "生命守护";
            body = "购买药品" + total + "元";
            payReturnOrderType = PayReturnOrderTypeEnum.DRUGS;
        }

        extraData.put("orderType", String.valueOf(payReturnOrderType.getValue()));
        logger.info(String.format("支付宝支付参数total:%s,subject:%s,body:%s,orderNumber:%s,extraData:%s,notifyUrl:%s", total,subject,body,orderNumber,extraData,notifyUrl));
        String orderInfo = AlipayService.getOrderInfo(total, subject, body, orderNumber, extraData, notifyUrl);
        return orderInfo;
    }

    @Override
    public Paging<StatisticsVO> findStatistics(String projectCode, Integer diseasesId, Integer gender, String startAge,
                                               String endAge, Integer orgId, Integer pageIndex, Integer pageSize) {
        Paging<StatisticsVO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<StatisticsVO>() {
            @Override
            public int queryTotal() {
                return orderDao1.countStatistics(projectCode, diseasesId, gender, startAge, endAge, orgId);
            }

            @Override
            public List<StatisticsVO> queryData(int startRow, int pageSize) {
                List<StatisticsVO> list = orderDao1.findStatisticsList(projectCode, diseasesId, gender, startAge, endAge, orgId, (pageIndex - 1) * pageSize, pageSize);
                for (StatisticsVO statisticsVO : list) {
                    statisticsVO.setTotalCharge(NumberUtils.changeF2Y(statisticsVO.getTotalCharge().intValue() + ""));
                    String temp = NumberUtils.format(String.valueOf(statisticsVO.getTotalCharge() / statisticsVO.getTotalOrder()), 2);
                    statisticsVO.setAverageCharge(Double.parseDouble(temp));
                }
                return list;
            }
        });
        return paging;
    }

    @Override
    public Paging<StatisticsVO> findStatisticsDetails(String projectCode, Integer diseasesId, Integer gender, String startAge,
                                                      String endAge, String mobile, Integer orgId, Integer pageIndex, Integer pageSize) {
        Paging<StatisticsVO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<StatisticsVO>() {
            @Override
            public int queryTotal() {
                return orderDao1.countStatisticsDetails(projectCode, diseasesId, gender, startAge, endAge, mobile, orgId);
            }

            @Override
            public List<StatisticsVO> queryData(int startRow, int pageSize) {
                List<StatisticsVO> list = orderDao1.findStatisticsDetailsList(projectCode, diseasesId, gender, startAge, endAge, mobile, orgId, (pageIndex - 1) * pageSize, pageSize);
                for (StatisticsVO statisticsVO : list) {
                    statisticsVO.setTotalCharge(NumberUtils.changeF2Y(statisticsVO.getTotalCharge().intValue() + ""));
//                    statisticsVO.setAverageCharge(statisticsVO.getTotalCharge()/statisticsVO.getTotalOrder());
                }
                return list;
            }
        });
        return paging;
    }

    @Override
    public void createOrderStoreSms(OrderStoreSmsPO orderStoreSmsPO) throws OperationException {
        try {
            orderDao1.addOrderStoreSms(orderStoreSmsPO);
        } catch (Exception e) {
            throw new OperationException(Error.ADD_FAILED, ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public OrderStatus getOrderStoreSmsStatus(String orderNumber) throws OperationException {
        OrderStatus orderStatus = null;
        try {
            int status = orderDao1.getOrderStoreSmsStatus(orderNumber);
            switch (status) {
                case 1:
                    orderStatus = OrderStatus.PAYABLE;
                    break;
                case 2:
                    orderStatus = OrderStatus.PAY_FAILED;
                    break;
                case 4:
                    orderStatus = OrderStatus.COMPLETED;
                    break;
            }
        } catch (Exception e) {
            logger.error("查询订单: 机构短信充值订单状态失败");
            throw new OperationException(Error.FETCH_FAILED, ErrorCodeEnum.FAILED);
        }

        return orderStatus;
    }

    /**
     * 获取状态为未完成的订单数量
     *
     * @param status 订单状态
     * @param id     门店用户ID
     * @return
     */
    @Override
    public Integer getOrderCountByIdAndStatus(Integer id, Integer status) {
        return orderDao1.getOrderCountByIdAndStatus(id, status);
    }

    @Override
    public void updateUserRemark(int orderId, int userId, String userRemark) throws OperationException {
        try {
            orderDao1.updateUserRemark(orderId, userId, userRemark);
        } catch (Exception e) {
            logger.error("更新订单表userRemark字段失败");
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void updateUserDiseases(int orderId, int userId, Integer userDiseasesId, String userDiseasesName) throws OperationException {
        try {
            orderDao1.updateUserDiseases(orderId, userId, userDiseasesId, userDiseasesName);
        } catch (Exception e) {
            logger.error("更新订单表userDiseases失败");
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public OrderDTO addRenewalOrder(int userId, ProjectType projectType, int id, int number) throws OperationException {
        String subject = null;
        String body = null;
        double price = 0;
        ChargeModeEnum chargeMode = null;
        Integer serveId = 0;
        Date startDate = null;
        Date endDate = null;
        String projectCode = null;
        String address = null;
        String receiverName = null;
        String receiverMobile = null;
        Long numberL = null;
        OrderStatus orderStatus = OrderStatus.PAYABLE;
        Integer timesRemaining = null;

        Integer serveUserId = null;
        
        // 线下服务
        if (!online(projectType)) {
            throw new OperationException("线下服务不支持续费", ErrorCodeEnum.REQUEST);
        }
        // 课堂
        if (ProjectType.PROJECT_LESSON.equals(projectType)) {
            throw new OperationException("课堂服务不支持续费操作", ErrorCodeEnum.REQUEST);
        }
        // 咨询
        if (ProjectType.PROJECT_CONSULT.equals(projectType)) {
            number = 1; // 默认咨询只有1次服务

            ServeUserVO serveUser = consultSerivce.getServeUser(id);
            if (serveUser == null || serveUser.getUserId() == null) {
                throw new OperationException(String.format(Error.NOT_FOUND, "咨询服务"), ErrorCodeEnum.NOT_FOUND);
            }

            // 线上咨询只能购买一同一个服务师的
            OrderDTO orderDTO = orderDao.getServiceOrderByUserIdAndServeUserId(userId, serveUser.getUserId(), projectType.getValue());
            if (orderDTO != null) {
                // 续费只能是同一个projectCode，如果用户已经购买了其它projectCode的服务师的咨询服务，需要特别提醒不能重复购买
                if (!serveUser.getProjectCode().equals(orderDTO.getProjectCode())) {
                    throw new OperationException("请勿购买同一个服务师的咨询服务", ErrorCodeEnum.REQUEST);
                }
            } else {
                throw new OperationException("用户尚未购买过该咨询服务，无法提供续费服务", ErrorCodeEnum.REQUEST);
            }

            // 重新组装subject，格式：服务师[服务类型]
            subject = serveUser.getRealName() + "[" + Toolkits.projectType(serveUser.getProjectName()) + "]";
            price = serveUser.getPrice();
            serveId = orderDTO.getServeId();
            projectCode = serveUser.getProjectCode();

            serveUserId = serveUser.getUserId();

            ChargeModeEnum realChargeMode = ChargeModeEnum.getChargeModeEnum(orderDTO.getChargeMode());
            if (0 == price) {
                throw new OperationException("免费服务不支持续费操作", ErrorCodeEnum.REQUEST);
            } else {
                chargeMode = realChargeMode;  // 收费方式
                numberL = Integer.valueOf(number).longValue();
            }
        }
        return addServeOrder(userId, subject, body, orderStatus, price, numberL, timesRemaining, chargeMode,
                serveUserId, projectCode, serveId, address, receiverName, receiverMobile, startDate, endDate, null, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void finishOrderWithCoupon(int orderId, int couponsId) throws OperationException {
        UserElectronicCouponsPO coupon = couponsService.getCoupons(couponsId);
        int couponPrice = coupon.getPrice();
        OrderPO orderPO = orderDao1.getOrderDetail(orderId);
        int orderCharge = orderPO.getCharge();   // 修改为charge费用
        
        if (couponPrice < orderCharge) {
            throw new OperationException("卡券金额不足", ErrorCodeEnum.FAILED);
        }
        
        double price = NumberUtils.changeF2Y(String.valueOf(orderCharge));
        finishOrder(orderPO.getOrderNumber(), null, null, null, price, 1, "app", couponsId);
    }
    
    @Override
    public OrderAgentAmountVO findTotalMoney(String userNo,String userName, String realName,
            String orgName, String projectType, OrderStatus status,String orderType) {
        Integer orderStatus = null;
        if (status != null) {
            orderStatus = status.getStatus();
        }
        return orderDao1.findTotalMoney(userNo, userName, realName, orgName, projectType, orderStatus,orderType);
    }

    @Override
    public Paging<OrderWithVipPO> listOrder(String userNo, int curPage, int pageSize, String userName, String realName,
            String orgName, String projectType, OrderStatus status, String orderType) {
        Paging<OrderWithVipPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<OrderWithVipPO>() {

            @Override
            public int queryTotal() {
                Integer orderStatus = null;
                if (status != null) {
                    orderStatus = status.getStatus();
                }
                return orderDao1.countOrderWithCondition(userNo, userName, realName, orgName, projectType, orderStatus,orderType);
            }

            @Override
            public List<OrderWithVipPO> queryData(int startRow, int pageSize) {
                Integer orderStatus = null;
                if (status != null) {
                    orderStatus = status.getStatus();
                }
                return orderDao1.findOrderWithCondition(userNo, startRow, pageSize, userName, realName, orgName, projectType, orderStatus,orderType);
            }
        });
        return p;
    }

    @Override
    public Paging<OrderCountVO> listOrderCount(String userNo, int curPage, int pageSize, String orgName, String serveCode,
            Date start, Date end) {
        Paging<OrderCountVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<OrderCountVO>() {

            @Override
            public int queryTotal() {
                return orderDao1.countOrderCountWithCondition(userNo, orgName, serveCode, start, end);
            }

            @Override
            public List<OrderCountVO> queryData(int startRow, int pageSize) {
                return orderDao1.findOrderCountWithCondition(userNo, startRow, pageSize, orgName, serveCode, start, end);
            }
        });
        return p;
    }

}
