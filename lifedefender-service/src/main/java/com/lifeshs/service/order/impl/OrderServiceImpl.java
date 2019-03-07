package com.lifeshs.service.order.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.OrderType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao.order.IOrderDao;
import com.lifeshs.dao.org.manage.OrgServiceDao;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.order.TOrder;
import com.lifeshs.entity.order.TOrderFlow;
import com.lifeshs.entity.org.TOrgGroup;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.order.OrderDTO;
import com.lifeshs.pojo.order.OrderFlowBasicDTO;
import com.lifeshs.pojo.order.SMSOrderDTO;
import com.lifeshs.pojo.order.ServiceOrderDTO;
import com.lifeshs.pojo.order.ServiceOrderDetailDO;
import com.lifeshs.pojo.order.UserServeDO;
import com.lifeshs.pojo.order.homeOrderDTO;
import com.lifeshs.pojo.org.group.GroupDTO;
import com.lifeshs.pojo.org.profit.ProfitDTO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service1.data.invitation.InvitationService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.NumberUtils;

@Service
public class OrderServiceImpl extends CommonServiceImpl implements IOrderService {

    private static final Logger logger = Logger.getLogger("pay");

    @Autowired
    private ICommonTrans common;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private com.lifeshs.dao1.order.IOrderDao orderDao1;

    @Autowired
    private OrgServiceDao orgServiceDao;

    @Autowired
    private HuanXinService huanxinService;

    @Autowired
    private IMemberDao memberDao;

    @Resource(name = "invitationService")
    private InvitationService invitationService;

    @Resource(name = "smsRemindService")
    private SmsRemindService smsRemindService;
    
    @Override
    public ServiceMessage addServeOrder(int userId, int orgServeId, String orderNumber, int priceType, int count,
                                        String subject, String body, int status, Integer groupId) {
        ServiceMessage sm = new ServiceMessage(false, "添加订单失败");
        // 查询服务详细信息
        OrgServeDTO serve = orgServiceDao.getOrgServeById(orgServeId);
        if (serve == null) {
            sm.setMessage("找不到该服务");
            return sm;
        }
        // 查询订单号是否重复
        if (!isOrderUnique(orderNumber, serve.getValidOrders())) {
            sm.setMessage("订单已存在");
            return sm;
        }

        Boolean hasFree = serve.getHasFree() == null ? false : serve.getHasFree();
        Boolean hasTime = serve.getHasTime() == null ? false : serve.getHasTime();
        Boolean hasMonth = serve.getHasMonth() == null ? false : serve.getHasMonth();
        Boolean hasYear = serve.getHasYear() == null ? false : serve.getHasYear();
        // 校验收费方式
        String checkChargeModeReturnMsg = checkChargeMode(priceType, hasFree, hasTime, hasMonth, hasYear);
        if (StringUtils.isNotBlank(checkChargeModeReturnMsg)) {
            sm.setMessage(checkChargeModeReturnMsg);
            return sm;
        }
        // 订单状态
        // 剩余服务次数
        Integer timesRemaining = null;
        // 计算收费结束日期
        Date startDate = null;
        Date endDate = null;
        TOrder order = new TOrder();

        // @TEST-start 判断用户是否已购买过该服务
        // Map<String, Object> params_order_own = new HashMap<>();
        // params_order_own.put("orgServeId", orgServeId);
        // params_order_own.put("userId", userId);
        // params_order_own.put("status", 3);
        // List<Map<String, Object>> order_own = commonTrans.findByMap(TOrder.class,
        // params_order_own);
        // if (order_own != null && order_own.size() > 0) {
        // sm.setMessage("请勿重复购买该服务");
        // return sm;
        // }
        if (isUserHaveSameServe(userId, serve.getValidOrders(), serve.getServeType().getCode(), groupId)) {
            sm.setMessage("请勿重复购买该服务");
            return sm;
        }
        // @TEST-end

        if (priceType == 0) {
            // // 判断用户是否已购买过免费服务，并且免费服务未到期
            // Map<String, Object> params_order = new HashMap<>();
            // params_order.put("orgServeId", orgServeId);
            // params_order.put("userId", userId);
            // params_order.put("status", 3);
            //
            // List<Map<String, Object>> orders_free =
            // commonTrans.findByMap(TOrder.class, params_order);
            // if (orders_free != null && orders_free.size() > 0) {
            // sm.setMessage("请勿重复购买免费服务");
            // return sm;
            // }

            int freeDay = serve.getFreeDate() * 30;
            // 计算结束日期
            endDate = freeDay == 0 ? null : calculateEndDate(freeDay, new Date());
            startDate = new Date();

            if (groupId == null) {
                // 免费需要立即分组
                Map<String, Object> params = new HashMap<>();
                params.put("orgServeId", orgServeId);
                params.put("defaultGroup", true);

                List<Map<String, Object>> groups = common.findByMap(TOrgGroup.class, params);
                int groupId_default = (int) groups.get(0).get("id");
                groupId = groupId_default;
            }

            status = 3;
        } else {
            // status等于3表示该订单为有效
            if (status == 3) {
                startDate = new Date();
                endDate = calculateChargeEndDate(priceType, count, startDate);
            }
            if (priceType == 1) { // 按次
                timesRemaining = count;
            }
            // 清除groupId
            // groupId = null; // TODO 再观望下
        }
        // 计算实际收费价格与总价
        Integer timePrice = serve.getTimePrice();
        Integer monthPrice = serve.getMonthPrice();
        Integer yearPrice = serve.getYearPrice();

        int price_real = getRealPrice(priceType, timePrice == null ? 0 : timePrice, monthPrice == null ? 0 : monthPrice,
                yearPrice == null ? 0 : yearPrice);
        int total_real = price_real * count;

        order.setUserId(userId);
//        order.setOrgServeId(orgServeId);
        order.setOrderNumber(orderNumber);
        order.setNumber(count);
        order.setPrice(price_real);
        order.setCharge(total_real);
        order.setChargeMode(priceType);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setTimesRemaining(timesRemaining);
        order.setStatus(status);
        order.setCreateDate(new Date());
        order.setSubject(subject);
        order.setBody(body);
        order.setOrderType(OrderType.ServeOrder.getValue());
        boolean isServeGroupIdBlank = groupId == null;
        if (!isServeGroupIdBlank) {
            // 这里为该订单添加群组
//            order.setServeGroupId(groupId);
        }

        int effectRowCount = common.save(order);
        if (effectRowCount != 1) {
            sm.setMessage("生成订单过程失败");
            return sm;
        }

        /** 健康课堂需要加入环信群组 */
        if (status == 3 && "02".equals(serve.getServeType().getCode())) {
            GroupDTO group = getGroup(groupId, serve.getGroups());
            TUser user = common.getEntity(TUser.class, userId);
            huanxinService.joinGroup(group.getHuanxinId(), user.getUserCode());
        }
        sm.setSuccess(true);
        sm.setMessage("添加订单成功");
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", order.getId());
        attr.put("number", order.getOrderNumber());
        sm.setAttributes(attr);
        return sm;
    }

    /**
     * 校验订单号是否唯一
     *
     * @param orderNumber
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月19日 上午11:42:57
     */
    private boolean isOrderUnique(String orderNumber, List<OrderDTO> orderDTOs) {
        for (OrderDTO orderDTO : orderDTOs) {
            if (orderNumber.equals(orderDTO.getOrderNumber())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断用户是否已购买过该服务，并且未过期
     *
     * @param userId
     * @param orderDTOs
     * @param code
     * @param groupId
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月8日 下午2:39:18
     */
    private boolean isUserHaveSameServe(Integer userId, List<OrderDTO> orderDTOs, String code, Integer groupId) {
        for (OrderDTO orderDTO : orderDTOs) {
            if (userId.equals(orderDTO.getUserId()) && orderDTO.getStatus() == 3) {
                if (!"02".equals(code)) {
                    return true;
                }
                /** 如果是健康课堂，按照群组进行划分服务，相同群组不可以购买同样的服务 */
                if (orderDTO.getGroup().getId().equals(groupId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过id找到一个群组
     *
     * @param groupId
     * @param groupDTOs
     * @return
     * @author yuhang.weng
     * @DateTime 2017年3月8日 下午3:47:50
     */
    private GroupDTO getGroup(Integer groupId, List<GroupDTO> groupDTOs) {
        for (GroupDTO groupDTO : groupDTOs) {
            if (groupDTO.getId().equals(groupId)) {
                return groupDTO;
            }
        }
        return new GroupDTO();
    }

    /**
     * 校验收费方式
     *
     * @param chargeMode
     * @param hasFree
     * @param hasTime
     * @param hasMonth
     * @param hasYear
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月19日 上午11:37:58
     */
    private static String checkChargeMode(int chargeMode, boolean hasFree, boolean hasTime, boolean hasMonth,
                                          boolean hasYear) {
        String checkReturnMsg = "";
        switch (chargeMode) {
            case 0:
                if (!hasFree) { // 1_免费，0_收费
                    checkReturnMsg = "该服务不提供免费服务";
                    break;
                }
                break;
            case 1:
                if (hasFree) {
                    checkReturnMsg = "该服务不提供收费服务";
                    break;
                }
                if (!hasTime) { // 有按次收费：1_是，0_否
                    checkReturnMsg = "该服务不提供按次服务";
                    break;
                }
                break;
            case 2:
                if (hasFree) {
                    checkReturnMsg = "该服务不提供收费服务";
                    break;
                }
                if (!hasMonth) { // 有按月收费：1_是，0_否
                    checkReturnMsg = "该服务不提供按月服务";
                    break;
                }
                break;
            case 3:
                if (hasFree) {
                    checkReturnMsg = "该服务不提供收费服务";
                    break;
                }
                if (!hasYear) { // 有按年收费：1_是，0_否
                    checkReturnMsg = "该服务不提供按年服务";
                    break;
                }
                break;
            default:
                checkReturnMsg = "该服务不提供该收费方式";
        }
        return checkReturnMsg;
    }

    /**
     * 计算收费结束日期
     *
     * @param chargeMode
     * @param count
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月19日 上午11:40:25
     */
    private Date calculateChargeEndDate(int chargeMode, int count, Date startDate) {
        Date endDate = null;
        switch (chargeMode) {
            case 1: // 按次
                break;
            case 2: // 按月
                endDate = calculateEndDate(30 * count, startDate);
                break;
            case 3: // 按年
                endDate = calculateEndDate(365 * count, startDate);
                break;
        }
        return endDate;
    }

    /**
     * 计算结束日期
     *
     * @param day
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午1:47:08
     */
    private Date calculateEndDate(int day, Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取真实的价格
     *
     * @param chargeMode
     * @param timePrice
     * @param monthPrice
     * @param yearPrice
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午3:13:19
     */
    private int getRealPrice(int chargeMode, int timePrice, int monthPrice, int yearPrice) {
        int realPrice = 0;
        switch (chargeMode) {
            case 0:
                break;
            case 1:
                realPrice = timePrice;
                break;
            case 2:
                realPrice = monthPrice;
                break;
            case 3:
                realPrice = yearPrice;
                break;
        }
        return realPrice;
    }

    @Override
    public PaginationDTO getOrderListWithPageSplit(List<Integer> status, List<Integer> groupIds, int userId,
                                                   int curPage, int pageSize) {

        PaginationDTO pagination = new PaginationDTO();
        List<Map<String, Object>> orders = new ArrayList<>();

        int count = orderDao.getCountOfServeOrderByUserId(status, groupIds, userId);
        if ((curPage - 1) * pageSize >= count) {
            pagination.setData(orders);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        curPage = queryPageData.getCurPage();

        orders = orderDao.listServeOrderWithPageSplit(status, groupIds, userId, startIndex, pageSize);
        for (Map<String, Object> data : orders) {
            data.put("price", NumberUtils.changeF2Y(data.get("price") + ""));
        }
        pagination.setData(orders);
        pagination.setNowPage(curPage);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public Map<String, Object> getOrder(int userId, int orderId) {
        Map<String, Object> order = orderDao.getServeOrder(userId, orderId);
        if (order != null) {
            order.put("price", NumberUtils.changeF2Y(order.get("price") + ""));
        }
        return order;
    }

    @Override
    public boolean modifyOrderStatusToValid(int userId, String orderNumber, String totalMoney, boolean isfree) {
        TOrder order = common.findUniqueByProperty(TOrder.class, "orderNumber", orderNumber);
        if (order == null) {
            return false;
        }

        if (isfree && order.getChargeMode() != 0) {
            return false;
        }

        int fTotal = NumberUtils.changeY2F(totalMoney);
        int realTotal = order.getPrice() * order.getNumber();
        if (fTotal != realTotal) {
            return false;
        }

        if (order.getUserId() != userId) {
            return false;
        }
        int orderStatus = order.getStatus();
        // 1_未支付 2_付款失败
        if (orderStatus != 1 && orderStatus != 2) {
            return false;
        }
        order.setStatus(3);
        int effectRow = common.updateEntitie(order);
        return effectRow > 0;
    }

    @Override
    public boolean modifyOrderStatusToFaild(int userId, String orderNumber) {
        TOrder order = common.findUniqueByProperty(TOrder.class, "orderNumber", orderNumber);
        if (order == null) {
            return false;
        }
        if (order.getUserId() != userId) {
            return false;
        }
        if (order.getStatus() != 1) {
            return false;
        }
        order.setStatus(2);
        int effectRow = common.updateEntitie(order);
        return effectRow > 0;
    }

    @Override
    public boolean addOverFlow(TOrderFlow orderFlow) {
        int effectRow = 0;
        effectRow = common.save(orderFlow);

        TOrder order = common.findUniqueByProperty(TOrder.class, "orderNumber", orderFlow.getOrderNumber());
        Integer orderType = order.getOrderType();

        if (orderType == OrderType.SMSOrder.getValue()) {
            // 短信充值订单需要为用户充值短信
            int userId = order.getUserId();
            Integer number = order.getNumber();

            try {
                smsRemindService.addMemberSmsRemind(userId, number);
            } catch (OperationException e) {
                // 添加失败
                e.printStackTrace();
            }
            
            order.setStatus(4);
            common.updateEntitie(order);

            return effectRow == 1;
        }
        if (orderType == OrderType.ServeOrder.getValue()) {
            // 服务订单需要将用户添加到群组中
//            Integer orgServeId = order.getOrgServeId();
            Integer chargeMode = order.getChargeMode();
            int number = order.getNumber();
//            Integer serveGroupId = order.getServeGroupId();

//            if (serveGroupId == null) {
//                Map<String, Object> params = new HashMap<>();
//                params.put("orgServeId", orgServeId);
//                params.put("defaultGroup", true);
//
//                List<Map<String, Object>> groups = common.findByMap(TOrgGroup.class, params);
//                serveGroupId = (int) groups.get(0).get("id");
//            }

            Date startDate = new Date();
            Date endDate = calculateChargeEndDate(chargeMode, number, startDate);

            // TODO 查找出order相同服务的订单信息，确定startDate与endDate
//            ServiceOrderDetailDO detail = orderDao.getUserOrderMaxStartDateAndEndDateByUserIdAndOrgServeId(userId,
//                    orgServeId);
//            if (detail.getEndDate() != null) {
//                startDate = DateTimeUtilT.addDay(detail.getEndDate(), 1);
//                endDate = calculateChargeEndDate(chargeMode, number, startDate);
//            }

//            order.setServeGroupId(serveGroupId);
            order.setStatus(3);
            order.setStartDate(startDate);
            order.setEndDate(endDate);
            effectRow += common.updateEntitie(order);

            return effectRow == 2;
        }
        return false;
    }

    @Override
    public boolean isOverFlowExist(String orderNumber) {
        TOrderFlow orderFlow = common.findUniqueByProperty(TOrderFlow.class, "orderNumber", orderNumber);
        if (orderFlow == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void delOrder(Integer userId, Integer ordeId) {
        TOrder order = new TOrder();
        order.setId(ordeId);
        order.setUserId(userId);
        common.delete(order);
    }

    @Override
    public List<UserServeDO> getUserServeDOList(int userId) {

        List<UserServeDO> serves = orderDao.getUserServeDOList(userId);
        /*List<UserServeDO> servesTemp = new ArrayList<>();
        Map<Integer, List<UserServeDO>> serveIdMap = new HashMap<>();
        for (UserServeDO project : serves) {
            int orgServeId = project.getOrgServeId();

            if (serveIdMap.containsKey(orgServeId)) {
                serveIdMap.get(orgServeId).add(project);
            } else {
                List<UserServeDO> serveIdsList = new ArrayList<>();
                serveIdsList.add(project);
                serveIdMap.put(orgServeId, serveIdsList);
            }

        }

        for (Integer key : serveIdMap.keySet()) {
            Map<Integer, UserServeDO> serveChargeModeMap = new HashMap<>();
            List<UserServeDO> serveDOs = serveIdMap.get(key);
            for (UserServeDO serveDO : serveDOs) {
                int chargeMode = serveDO.getChargeMode();
                if (chargeMode != 1) {
                    // 把chargeMode不为0的order归为一类，统一用0作为key，然后比较保存着的
                    if (serveChargeModeMap.containsKey(0)) {
                        Date endDate_new = serveDO.getEndDate();
                        Date endDate_old = serveChargeModeMap.get(0).getEndDate();
                        boolean newEndDateIsBigger = DateTimeUtilT.calculateDayInterval(endDate_old, endDate_new) > 0;
                        if (newEndDateIsBigger) {
                            serveChargeModeMap.put(0, serveDO);
                        }
                    } else {

                        serveChargeModeMap.put(0, serveDO);
                    }
                } else {
                    serveChargeModeMap.put(1, serveDO);
                }
            }
            // 将遍历的结果保存起来
            if (serveChargeModeMap.containsKey(0)) {
                servesTemp.add(serveChargeModeMap.get(0));
            }
            if (serveChargeModeMap.containsKey(1)) {
                servesTemp.add(serveChargeModeMap.get(1));
            }
        }*/

        return serves;
    }

    @Override
    public List<Map<String, Object>> getUserServeList(int userId) {
        List<Map<String, Object>> serves = new ArrayList<>();

        List<UserServeDO> datas = getUserServeDOList(userId);
        for (UserServeDO data : datas) {
            Map<String, Object> serve = new HashMap<>();
            // remaining
            String remaining = "";
            // daysRemaining
            String daysRemaining_s = "";
            if (data.getChargeMode() != 1) {
                Date endDate = data.getEndDate();
                if (endDate == null) {
                    daysRemaining_s = "∞";// 无限期
                } else {
                    int remain = DateTimeUtilT.calculateDayInterval(new Date(), endDate);
                    if (remain <= 0) {
                        continue;
                    }
                    daysRemaining_s = remain + "";
                }
                remaining = daysRemaining_s + " 天";
            } else {
                if (data.getTimesRemaining() <= 0)
                    continue;
                remaining = data.getTimesRemaining() + " 次";
            }
            // classify
            List<String> classifies = orgServiceDao.getServeOrgClassify(data.getOrgId());
            String classify = classifies2String(classifies);

            serve.put("serveName", data.getServeName());
            serve.put("orgName", data.getOrgName());
            serve.put("orgServeId", data.getOrgServeId());
            serve.put("logo", data.getLogo());
            // project.put("daysRemainings", daysRemainings);
            // project.put("timesRemaining", data.getTimesRemaining());
            serve.put("classify", classify);

            serve.put("remaining", remaining);

            serves.add(serve);
        }

        return serves;
    }

    @Override
    public boolean cancelOrder(Integer userId, String orderNumber) {
        TOrder order = common.findUniqueByProperty(TOrder.class, "orderNumber", orderNumber);
        if (order == null) {
            return false;
        }
        if (order.getUserId().intValue() != userId) {
            return false;
        }
        if (!isCancelableStatus(order.getStatus())) {
            return false;
        }
        order.setStatus(6);
        return common.updateEntitie(order) > 0;
    }

    /**
     * 判断是否为可以取消订单的状态
     *
     * @param status
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月24日 下午8:28:10
     */
    private boolean isCancelableStatus(int status) {
        if (status == 3 || status == 4 || status == 5 || status == 6) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> getUserServeDetail(int userId, int orgServeId) {

        Map<String, Object> data = new HashMap<>();

        ServiceOrderDetailDO detail = orderDao.getServiceOrderDetailDO(userId, orgServeId);
        if (detail.getOrderId() == null) {
            return data;
        }
        // logo
        String logo = detail.getLogo();
        // daysRemaining
        Date endDate = detail.getEndDate();
        String daysRemaining_s = "";
        int timesRemaining = 0;
        // String endDate=null;

        if (detail.getChargeMode() != 1) {
            if (endDate == null) {
                daysRemaining_s = "∞";
            } else {
                daysRemaining_s = DateTimeUtilT.calculateDayInterval(new Date(), endDate) + "";
            }
            // timesRemaining = detail.getTimesRemaining();
            // endDate=DateTimeUtil.printNextTime2(DateTimeUtil.getNow(),daysRemaining);
        } else if (detail.getChargeMode() == 1) {
            // timesRemaining
            timesRemaining = detail.getTimesRemaining();
        }

        // data.put("orderId", detail.getOrderId());
        data.put("orgId", detail.getOrgId());
        data.put("orgName", detail.getOrgName());
        data.put("serveName", detail.getServiceName());
        data.put("daysRemaining", daysRemaining_s);
        data.put("timesRemaining", timesRemaining);
        data.put("logo", logo);
        data.put("orgServeId", detail.getOrgServeId());
        data.put("chargeMode", detail.getChargeMode());

        int serveGroupId = detail.getServeGroupId();

        List<Map<String, Object>> serveUsers = orgServiceDao.getServeOrgUserList(serveGroupId);
        if (serveUsers != null && !serveUsers.isEmpty()) {
            for (int i = 0; i < serveUsers.size(); i++) {
                // classify
                int orgUserId = (int) serveUsers.get(i).get("userId");
                List<String> classifies = orgServiceDao.getOrgUserClassifyByUserId(orgUserId);
                String classify = classifies2String(classifies);
                serveUsers.get(i).put("classify", classify);

                // 环信用户在线状态检测
                String huanxinUserName = (String) serveUsers.get(i).get("huanxinUserName");
                serveUsers.get(i).put("isOnline", huanXinService.userStatus(huanxinUserName));
            }
        }
        data.put("serveUsers", serveUsers);
        return data;
    }

    @Transactional
    @Override
    public boolean finishOrder(String outNo, String tradeNo, String payerAccount, String sellerAccount,
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

        TOrderFlow orderFlow = new TOrderFlow();

        OrderFlowBasicDTO orderFlowDTO = orderDao.getOrderBasicDataByOrderNumber(outNo);
        int total_original = orderFlowDTO.getTotal();
        int orderType = orderFlowDTO.getOrderType();
        String realName = orderFlowDTO.getUserRealName();
        Integer orgId = orderFlowDTO.getOrgId();
        String orgName = orderFlowDTO.getOrgName();
        String serveName = orderFlowDTO.getServeName();
        Integer profitShare = 0;
        int total_alipay = NumberUtils.changeY2F(totalCount + "");

        if (total_alipay != total_original) {
            logger.error("订单异常:订单(编号" + outNo + ")订单预计收取金额为" + total_original + "分, 支付宝只收到转账" + total_alipay + "分");
            return false;
        }

        // 短信充值订单
        if (orderType == OrderType.SMSOrder.getValue()) {
            orgName = "广州通众电气实业有限公司";
        }
        if (orderType == OrderType.ServeOrder.getValue()) {
            int sysIncome = 0;
            int orgIncome = 0;

            profitShare = orderFlowDTO.getProfitShare();
            double sysIncome_d = totalCount * (profitShare / 100.0);
            BigDecimal decimal_sys = new BigDecimal(sysIncome_d);
            sysIncome = decimal_sys.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();

            double orgIncome_d = totalCount * ((100 - profitShare) / 100.0);
            BigDecimal decimal_org = new BigDecimal(orgIncome_d);
            orgIncome = decimal_org.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();

            if (sysIncome == 0 && orgIncome == 0 && (sysIncome + orgIncome != total_alipay)) {
                sysIncome = total_alipay;
            }

            orderFlow.setSysIncome(sysIncome);
            orderFlow.setOrgIncome(orgIncome);
        }
        // 输出内容
        logger.info(orderFlowDTO);

        orderFlow.setPayCost(total_alipay);
        orderFlow.setCost(total_alipay);
        orderFlow.setOrderNumber(outNo);
        orderFlow.setRealName(realName);
        orderFlow.setOrgId(orgId);
        orderFlow.setOrgName(orgName);
        orderFlow.setServeName(serveName);
        orderFlow.setFlowType(1); // 收款_1
        orderFlow.setPayType(payType);
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
        logger.info(orderFlow);

        return addOverFlow(orderFlow);
    }

    private String classifies2String(List<String> classifies) {
        String classifiesString = "";

        List<String> newClassifyList = new ArrayList<>();

        for (String classify : classifies) {
            if (StringUtils.isNotBlank(classify)) {
                for (String c : classify.split(",")) {
                    newClassifyList.add(c);
                }
            }
        }
        if (newClassifyList.size() > 0) {
            newClassifyList = ListUtil.removeRepeatElement(newClassifyList, String.class);
            for (String cs : newClassifyList) {
                classifiesString += cs + ",";
            }
            classifiesString = classifiesString.substring(0, classifiesString.length() - 1);
        }
        return classifiesString;
    }

    @Override
    public boolean updateOrderNumber(Integer orderId, Integer userId, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("table", "t_order");
        Map<String, Object> column = new HashMap<>();
        column.put("number", count);
        Map<String, Object> condition = new HashMap<>();
        condition.put("id", orderId);
        condition.put("userId", userId);
        map.put("column", column);
        map.put("condition", condition);
        common.updateByMap(map);
        return true;
    }

    @Override
    public Map<String, Object> createOrderMsg(Integer orgServeId, Integer userId, Integer count, Integer mode) {
        Map<String, Object> map = orgServiceDao.selectServeDetailByOrgServeId(orgServeId);
        UserDTO user = memberDao.getUser(userId);
        map.put("orderNumber", AlipayService.createOrderNumber(user.getUserCode()));
        map.put("number", count);// 购买的数量
        map.put("chargeMode", mode);// 0_免费，1_按次，2_按月，3_按年
        map.put("createDate", new Date());
        switch (mode) {
            case 0:
                if ((Boolean) map.get("hasFree"))
                    map.put("price", 0);
                break;
            case 1:
                if ((Boolean) map.get("hasTime"))
                    map.put("price", NumberUtils.changeF2Y(String.valueOf(map.get("timePrice"))));
                break;
            case 2:
                if ((Boolean) map.get("hasMonth"))
                    map.put("price", NumberUtils.changeF2Y(String.valueOf(map.get("monthPrice"))));
                break;
            case 3:
                if ((Boolean) map.get("hasYear"))
                    map.put("price", NumberUtils.changeF2Y(String.valueOf(map.get("yearPrice"))));
                break;
        }
        return map;
    }

    @Override
    public boolean reduceServeTime(Integer userId, Integer orgServeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("status", 3);
        params.put("orgServeId", orgServeId);
        List<Map<String, Object>> orders = common.findByMap(TOrder.class, params);
        if (orders == null || orders.size() == 0) {
            for (Map<String, Object> order : orders) {
                int chargeMode = (int) order.get("chargeMode");
                if (chargeMode != 1) {
                    return false;
                }
            }
        }

        TOrder order = orderDao.getUserFirstOrderIdWithChargeModeIsByTime(userId, orgServeId);
        if (order == null) {
            return false;
        }
        int timesRemaining = order.getTimesRemaining() - 1;
        order.setTimesRemaining(timesRemaining);
        if (timesRemaining == 0) {
            order.setStatus(4);
        }
        int effectRowCount = common.updateEntitie(order);
        return effectRowCount > 0;
    }

    /**
     * 更新用户订单的健康警示值
     *
     * @param measureDate 测量时间
     * @param userId      用户ID
     * @param deviceType  设备类型
     * @author yuhang.weng
     * @updateBy wuj 2017-08-29 15:47:33
     * @updateReason
     *          与前端要求不符, 且可能会出现日期BUG.例如,用户在2017-08-29测试数据,2017-08-30号上传数据,
     *   那么new Date()获取到的就不是用户测试时间.
     *
     * @DateTime 2016年11月29日 下午4:27:30
     */
    @Override
    public void updateUserHealthWarningToOrder(int userId, HealthPackageType deviceType, Date measureDate) {
        int bValue = deviceType.value();

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("status", 3);
        List<Map<String, Object>> orders = common.findByMap(TOrder.class, params); // 获取状态为有效的订单列表


        // GROUP BY ORG USER ID
        // 根据服务师ID进行分组
        Map<Integer, List<Map<String, Object>>> orders_tmp = new HashMap<>();
        for (Map<String, Object> order : orders) {
            List<Map<String, Object>> project = null;
            Integer serveUserId = (Integer) order.get("orgUserId");
            if (orders_tmp.containsKey(serveUserId)) {
                project = orders_tmp.get(serveUserId);
            } else {
                project = new ArrayList<>();
            }
            project.add(order);
            orders_tmp.put(serveUserId, project);
        }

        for (Integer serveUserId : orders_tmp.keySet()) {
            List<Map<String, Object>> orderGroupByOrgUserId = orders_tmp.get(serveUserId);
            if (orders != null && orders.size() > 0) {
                Map<String, Object> order = orderGroupByOrgUserId.get(0);

                Object wd = order.get("warningDate"); // 设备警告日期
                Object hw = order.get("hasWarning"); // 提醒项
                Date warningDate = null;
                int hasWarning = 0;
                if (wd != null) {
                    logger.debug("设备告警日期: " + warningDate);

                    warningDate = (Date) wd; // 设备告警日期
                    hasWarning = (hw == null) ? 0 : (int) hw; // 设备告警值
                    String mDate = DateFormatUtils.format(measureDate, "yyyy-MM-dd"); // 测量时间

                    if (mDate.equals(wd)) {
                        //如果是同一天上传的,更新hasWarning
                        hasWarning |= bValue;
                    } else {
                        //不是同一天,那么日期,hasWarning都需要更新
                        hasWarning = bValue;
                        warningDate = measureDate;
                    }

                   /* warningDate = (Date) wd; // 日期
                    hasWarning = (hw == null) ? 0 : (int) hw;
                    int intervalDay = DateTimeUtilT.calculateDayInterval(warningDate, new Date());
                    if (intervalDay <= 3) {
                        // 在三天内，不需要刷新日期，沿用原来的wd日期
                        // 将hasWarning字段更新
                        hasWarning |= bValue;
                    } else {
                        // 超出三天，刷新日期为今天
                        // haWarning不更新，直接替换
                        hasWarning = bValue;
                        warningDate = new Date();
                    }*/
                } else {
                    // 第一次填写，刷新日期，以及直接替换hasWarning
                    hasWarning = bValue;
                    warningDate = measureDate;
                }

                setHealthWarning(userId, warningDate, hasWarning, serveUserId);
            } else {
                // 找不到订单信息，不需要做更新处理

            }
        }

    }

    private void setHealthWarning(int userId, Date warningDate, int hasWarning, Integer serveUserId) {
        orderDao.updateServeOrderHasWarning(warningDate, hasWarning, userId, serveUserId);
    }

    @Override
    public void removeRedPoint(int userId, int serveUserId) {
        orderDao.updateServeOrderHasWarning(null, 0, userId, serveUserId);
    }

    @Override
    public void addSMSOrder(String orderNumber, int userId, int price, int number, String subject,
                            String body) {
        int charge = price * number;
        orderDao.addSMSOrder(orderNumber, userId, price, number, charge, subject, body, 1);
    }

    @Override
    @Transactional(rollbackFor = OperationException.class, propagation = Propagation.REQUIRED)
    public void addSmsInvitationCode(String code, int userId) throws OperationException {
        invitationService.updateInvitaionToUse(userId, code);
        // 获取订单号
        String orderNumber = AlipayService.createOrderNumber();
        // 添加订单
        orderDao.addSMSOrder(orderNumber, userId, 0, 0, 0, "邀请码:" + code, "赠送150条短信", 4);
        // 添加150条短信
        smsRemindService.addMemberSmsRemind(userId, 150);
    }

    @Override
    public PaginationDTO<SMSOrderDTO> listSMSOrder(int userId, int curPage, int pageSize) {
        List<Integer> statusList = new ArrayList<>();
        statusList.add(OrderStatus.COMPLETED.getStatus());

        PaginationDTO<SMSOrderDTO> pagination = new PaginationDTO<>();
        List<SMSOrderDTO> orders = new ArrayList<>();

        int count = orderDao.getCountOfSMSOrderByUserId(statusList, userId);
        if ((curPage - 1) * pageSize >= count) {
            pagination.setData(orders);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        curPage = queryPageData.getCurPage();

        orders = orderDao.listSMSOrder(userId, statusList, startIndex, pageSize);
        pagination.setData(orders);
        pagination.setNowPage(curPage);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public PaginationDTO<ServiceOrderDTO> listOrderByOrgUser(Integer orgUserId, Integer status, String userName, String type, Integer pageIndex, Integer pageSize) {

        PaginationDTO<ServiceOrderDTO> pagination = new PaginationDTO<>();
        List<ServiceOrderDTO> orders = new ArrayList<>();

        int count = orderDao.getCountOfServiceOrderByOrgUser(orgUserId, status, userName, type);
        if ((pageIndex - 1) * pageSize >= count) {
            pagination.setData(orders);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        orders = orderDao.listOrderByOrgUser(orgUserId, status, userName, type, startIndex, pageSize);
        for (ServiceOrderDTO order : orders) {
            /*价格单位转换*/
            if (order.getPrice() != null) {
                double price = NumberUtils.changeF2Y(String.valueOf(order.getPrice().intValue()));
                order.setPrice(price);
            } else {
                order.setPrice(0D);
            }
        }
        pagination.setData(orders);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public PaginationDTO<ServiceOrderDTO> listOrderByComments(Integer orgUserId, Integer status, String type, String userName, Boolean commentsStatus, Integer pageIndex, Integer pageSize) {
        PaginationDTO<ServiceOrderDTO> pagination = new PaginationDTO<>();
        List<ServiceOrderDTO> orders = new ArrayList<>();

        int count = orderDao.getCountOfServiceOrderByComments(orgUserId, status, userName, type, commentsStatus);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(orders);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        orders = orderDao.listOrderByComments(orgUserId, status, userName, type, commentsStatus, startIndex, pageSize);
        for (ServiceOrderDTO order : orders) {
            /*价格单位转换*/
            if (order.getPrice() == null) {
                double price = NumberUtils.changeF2Y(String.valueOf(order.getPrice().intValue()));
                order.setPrice(price);
            } else {
                order.setPrice(0D);
            }
        }
        pagination.setData(orders);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public ProfitDTO countDayProfit(int orgId) {
        String date = DateTimeUtilT.date(new Date());
        ProfitDTO p = orderDao.countDayProfit(orgId, date);
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }

    @Override
    public ProfitDTO countDayProfitByServices(int orgUserId) {
        String date = DateTimeUtilT.date(new Date());
        ProfitDTO p = orderDao.countDayProfitByServices(orgUserId, date);
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }

    @Override
    public ProfitDTO countWeekProfit(int orgId) {
        String date = DateTimeUtilT.date(new Date());
        ProfitDTO p = orderDao.countWeekProfit(orgId, date);
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }


    @Override
    public List<ProfitDTO> listWeekProfit(int orgId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousWeek = localDate.minus(1, ChronoUnit.WEEKS);
        List<ProfitDTO> ps = orderDao.listProfit(orgId, null, previousWeek.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listWeekProfitByOrgUser(int orgUserId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousWeek = localDate.minus(1, ChronoUnit.WEEKS);
        List<ProfitDTO> ps = orderDao.listProfit(null, orgUserId, previousWeek.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listTwoWeekProfit(int orgId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousTwoWeek = localDate.minus(2, ChronoUnit.WEEKS);
        List<ProfitDTO> ps = orderDao.listProfit(orgId, null, previousTwoWeek.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listTwoWeekProfitByOrgUser(int orgUserId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousTwoWeek = localDate.minus(2, ChronoUnit.WEEKS);
        List<ProfitDTO> ps = orderDao.listProfit(null, orgUserId, previousTwoWeek.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listMonthProfit(int orgId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousMonth = localDate.minus(1, ChronoUnit.MONTHS);
        List<ProfitDTO> ps = orderDao.listProfit(orgId, null, previousMonth.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listMonthProfitByOrgUser(int orgUserId) {
        LocalDate localDate = LocalDate.now();
        LocalDate previousMonth = localDate.minus(1, ChronoUnit.MONTHS);
        List<ProfitDTO> ps = orderDao.listProfit(null, orgUserId, previousMonth.toString(), localDate.toString());
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listProfit(int orgId, String startDate, String endDate) {
        LocalDate localDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate previousDay = localDate.minus(1, ChronoUnit.DAYS);
        List<ProfitDTO> ps = orderDao.listProfit(orgId, null, previousDay.toString(), endDate);
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    @Override
    public List<ProfitDTO> listProfitByOrgUser(int orgId, String startDate, String endDate) {
        LocalDate localDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate previousDay = localDate.minus(1, ChronoUnit.DAYS);
        List<ProfitDTO> ps = orderDao.listProfit(null, orgId, previousDay.toString(), endDate);
        profitEarningFenToYuan(ps); // 单位转换
        return ps;
    }

    private void profitEarningFenToYuan(List<ProfitDTO> datas) {
        for (ProfitDTO p : datas) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        }
    }

    @Override
    public double countMonthIncome(int orgId) {
        LocalDate endDate = LocalDate.now();
        // 获取本月的第一天
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        LocalDate startDate = LocalDate.of(year, month, 1);

        Long income = orderDao.countIncome(orgId, startDate.toString(), endDate.toString());
        double d = NumberUtils.changeF2Y(String.valueOf(income));
        return d;
    }

    @Override
    public double countLastMonthIncome(int orgId) {
        LocalDate date = LocalDate.now();
        LocalDate previousMonth = date.minus(1, ChronoUnit.MONTHS);
        // 获取上月的第一天
        LocalDate startDate = previousMonth.with(TemporalAdjusters.firstDayOfMonth());
        // 获取上月的最后一天
        LocalDate endDate = previousMonth.with(TemporalAdjusters.lastDayOfMonth());

        Long income = orderDao.countIncome(orgId, startDate.toString(), endDate.toString());
        double d = NumberUtils.changeF2Y(String.valueOf(income));
        return d;
    }

    @Override
    public PaginationDTO<ServiceOrderDTO> listOrderByOrg(Integer orgId, String userName, Integer status, String type, Integer pageIndex, Integer pageSize) {

        PaginationDTO<ServiceOrderDTO> pagination = new PaginationDTO<>();
        List<ServiceOrderDTO> orders = new ArrayList<>();

        int count = orderDao.getCountOfOrderByOrg(orgId, userName, status, type);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(orders);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        orders = orderDao.listOrderByOrg(orgId, userName, status, type, startIndex, pageSize);
        for (ServiceOrderDTO serviceOrderDTO : orders) {
            serviceOrderDTO.setPrice(NumberUtils.changeF2Y(serviceOrderDTO.getPrice().intValue() + ""));
        }
        pagination.setData(orders);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public ServiceOrderDTO getOrderDetails(Integer orderId, Integer orgId) {
        ServiceOrderDTO p = orderDao.getOrderDetails(orderId, orgId);

        if (p.getPrice() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getPrice().intValue()));
            p.setPrice(earning_y);
        } else {
            p.setPrice(0D);
        }
        return p;
    }

    @Override
    public int getCountOfServiceOrderByOrgUser(Integer orgUserId, Integer status, String userName, String type) {
        return orderDao.getCountOfServiceOrderByOrgUser(orgUserId, status, userName, type);
    }


    @Override
    public ProfitDTO countWeekProfitByOrgUser(Integer orgUserId) {
        String date = DateTimeUtilT.date(new Date());
        ProfitDTO p = orderDao.countWeekProfitByOrgUser(orgUserId, date);
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }

    @Override
    public boolean checkVerifyCode(int orderId, String orderNumber, String verifyCode) {
        String code = orderDao.getVerifyCode(orderId, orderNumber);
        if (!verifyCode.equals(code)) {
            return false;
        }
        //todo 修改订单状态
        orderDao1.completeOrder(orderId);
        return true;
    }

    @Override
    public List<homeOrderDTO> listOrderByOrgUser(String startDate, String endDate, int orgUserId) {
        List<homeOrderDTO> list = orderDao.listOrderByServices(startDate, endDate, orgUserId);
        for (homeOrderDTO order : list) {
            if (order.getCharge() != null) {
                double charge = NumberUtils.changeF2Y(String.valueOf(order.getCharge().intValue()));
                order.setCharge(charge);
            } else {
                order.setCharge(0D);
            }
        }
        return list;
    }

    @Override
    public List<homeOrderDTO> listOrderByOrg(String startDate, String endDate, int orgId) {
        List<homeOrderDTO> list = orderDao.listOrderByStore(startDate, endDate, orgId);
        for (homeOrderDTO order : list) {
            if (order.getCharge() != null) {
                double charge = NumberUtils.changeF2Y(String.valueOf(order.getCharge().intValue()));
                order.setCharge(charge);
            } else {
                order.setCharge(0D);
            }
        }
        return list;
    }

    @Override
    public ProfitDTO countThisMonthProfit(int orgId) {
        LocalDate endDate = LocalDate.now();
        // 获取本月的第一天
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        LocalDate startDate = LocalDate.of(year, month, 1);

        ProfitDTO p = orderDao.countProfit(orgId, startDate.toString(), endDate.toString());
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }

    @Override
    public ProfitDTO countThisMonthProfitByServices(int orgUserId) {
        LocalDate endDate = LocalDate.now();
        // 获取本月的第一天
        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        LocalDate startDate = LocalDate.of(year, month, 1);

        ProfitDTO p = orderDao.countProfitByServices(orgUserId, startDate.toString(), endDate.toString());
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }

    @Override
    public ProfitDTO countLastMonthProfit(int orgId) {
        LocalDate date = LocalDate.now();

        LocalDate previousMonth = date.minus(1, ChronoUnit.MONTHS);
        // 获取上月的第一天
        LocalDate startDate = previousMonth.with(TemporalAdjusters.firstDayOfMonth());

        LocalDate endDate = previousMonth.with(TemporalAdjusters.lastDayOfMonth());

        ProfitDTO p = orderDao.countProfit(orgId, startDate.toString(), endDate.toString());
        if (p.getEarning() != null) {
            double earning_y = NumberUtils.changeF2Y(String.valueOf(p.getEarning().intValue()));
            p.setEarning(earning_y);
        } else {
            p.setEarning(0D);
        }
        return p;
    }


    @Override
    public List<ProfitDTO> countProfitByMonth(int orgId, String startMonth, String endMonth) {
        LocalDate start = LocalDate.parse(startMonth);
        LocalDate end = LocalDate.parse(endMonth);

        // 获取第一天
        LocalDate startDate = start.with(TemporalAdjusters.firstDayOfMonth());
        // 获取最后一天
        LocalDate endDate = end.with(TemporalAdjusters.lastDayOfMonth());

        List<ProfitDTO> p = orderDao.countProfitByMonth(orgId, startDate.toString(), endDate.toString());
        profitEarningFenToYuan(p);
        return p;
    }
}
