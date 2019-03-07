package com.lifeshs.service1.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao1.order.ICustomOrderDao;
import com.lifeshs.po.CustomOrderPo;
import com.lifeshs.pojo.member.address.AddressDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service1.order.CustomOrderService;
import com.lifeshs.utils.NumberUtils;

/**
 * Created by Administrator on 2017/12/8.
 */
@Service("customOrderService")
public class CustomOrderServiceImpl implements CustomOrderService {

    private static final Logger logger = Logger.getLogger("pay");
    @Resource(name = "iCustomOrderDao")
    private ICustomOrderDao customOrderDao;
    @Resource(name = "memberDao")
    private IMemberDao memberDao;

    @Transactional
    @Override
    public CustomOrderPo addPrivateOrder(String paysCode,String userCode ,String productName, String productSpec, String produstPrice) throws OperationException {
        CustomOrderPo cp = new CustomOrderPo();
        cp.setProductName(productName);
        cp.setProductSpec(productSpec);
        // 将价格由String 转换为Double
        double price_f = Double.valueOf(produstPrice).doubleValue();
        // 单位元转换为分
        int price = NumberUtils.changeY2F(Double.valueOf(price_f).toString());
        // 总价
        int cost = price;
        // 生成订单号
        String orderNumber = AlipayService.createOrderNumber();
        cp.setOrderNumber(orderNumber);
        cp.setProductPrice(price);
        cp.setPhysCode(paysCode);  //医师编号
        cp.setUserCode(userCode);  //会员编号
        cp.setCreateDate(new Date());
        cp.setStatus(0);
        cp.setCost(cost);
        /**
         * 避免生成订单的时候因为订单编号的唯一约束限制订单的正常添加
         * 写一个遍历5次的循环，当订单创建失败的时候就生成新的订单编号
         */
        for (int i = 0; i < 5; i++) {
            try {
                int result = customOrderDao.addCustom(cp);
                if (result==0){
                    throw new OperationException("添加失败",ErrorCodeEnum.FAILED);
                }
                //TODO 发送环信透传消息 由APP发送
                //huanxinService.sendTransparentMsg(,userCode);
                return cp;
            } catch (Exception e) {
                System.out.println(e);
                orderNumber = AlipayService.createOrderNumber();
                cp.setOrderNumber(orderNumber);
                logger.error("订单生成错误次数" + (i + 1) + "，生成新的订单编号:" + orderNumber);
            }
        }
        /**
         * 如果循环5次之后还是没有成功创建，就让用户重新提交订单
         */
        throw new OperationException("添加订单失败，请稍后重试", ErrorCodeEnum.SERVE);
    }

    @Override
    public CustomOrderPo confirmPrivateOrder(Integer userId, Integer addressId, String orderNumber) throws OperationException {
        if (userId != null && addressId != null) {
            //获取用户地址
            AddressDTO address = memberDao.getAddress(addressId,userId);
            if (address == null){
                throw new OperationException("地址查询失败",ErrorCodeEnum.FAILED);
            }
            CustomOrderPo cpu = customOrderDao.getCustomOrder(orderNumber);
            cpu.setStatus(1); //输入后修改支付状态
            cpu.setUserId(userId);
            cpu.setAddressId(addressId);

            int result = customOrderDao.updateCustom(cpu);
            if (result == 0){
                throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
            }
            return cpu;

        }else {
            logger.error("用户为空！！");
        }
           return null;
    }


    @Override
    public String finishOrder(String orderNumber) throws OperationException {
        //根据订单号查询
        CustomOrderPo customOrderPo = customOrderDao.getCustomOrder(orderNumber);
        if (customOrderPo == null){
            throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        double price = NumberUtils.changeF2Y(String.valueOf(customOrderPo.getProductPrice()).toString());
        int cost = customOrderPo.getCost();
        double priceCost = NumberUtils.changeF2Y(String.valueOf(cost).toString());
        if (!OrderStatus.PAYABLE.getStatus().equals(customOrderPo.getStatus())){
            throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
        }
        if (priceCost != price){
            throw new OperationException("金额不匹配 单位 分 (" + priceCost + "=>" + customOrderPo.getProductPrice(),ErrorCodeEnum.FAILED);
        }
        String title = "支付订单";//标题
        String content = "完成支付";//
        String notifyUrl = Normal.APP_RECHARGE_NOTIFY_URL;
        Map<String,String> extraData = new HashMap<>();
        //额外参数
        extraData.put("orderType",String.valueOf(PayReturnOrderTypeEnum.PRIVATE_ORDER.getValue()));
        String info = AlipayService.getOrderInfo(priceCost, title, content, orderNumber, extraData, notifyUrl);
        return info;
    }

    @Override
    public CustomOrderPo getOrderDetails(String orderNumber) {
        return customOrderDao.getCustomOrder(orderNumber);
    }

    @Override
    public void finishOrderPrivate(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount,
                                   Double payMoney, PayTypeEnum alipay, String deviceType) throws OperationException {
        CustomOrderPo cp = customOrderDao.getCustomOrder(orderNumber);
        int f = NumberUtils.changeY2F(Double.valueOf(payMoney).toString());
        cp.setStatus(2);
        cp.setPayType(alipay.getValue());
        cp.setPayAccount(payerAccount);
        cp.setSellerAccount(sellerAccount);
        cp.setPayCost(f);
        int result = customOrderDao.updateCustom(cp);//保存完成
        if (result == 0){
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public AddressDTO findByAdddress(Integer userId, Integer addressId) {
        return memberDao.getAddress(addressId,userId);
    }
}

