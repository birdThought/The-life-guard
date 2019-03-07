package com.lifeshs.service1.transfer.impl;

import com.lifeshs.common.constants.common.Transfer;
import com.lifeshs.dao1.transfer.ThirdPartyDao;
import com.lifeshs.dao1.transfer.TransferDeliceryDao;
import com.lifeshs.dto.manager.JztxDTO;
import com.lifeshs.po.transfer.TransferCleaning;
import com.lifeshs.po.transfer.TransferDelivery;
import com.lifeshs.service1.transfer.TransferCleaningService;
import com.lifeshs.service1.transfer.TransferDeliveryService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-01-18
 * 17:09
 * @desc
 */
@Service(value = "transferDeliveryService")
public class TransferDeliveryServiceImpl implements TransferDeliveryService {
    @Autowired
    private TransferDeliceryDao transferDeliceryDao;
    @Autowired
    private SMSService smsService;
    @Autowired
    private TransferCleaningService transferCleaningService;

    @Autowired
    private ThirdPartyDao thirdPartyDao;

    @Override
    public Map<String,Object> saveCallback(String appId, String accesstoken, String oderNo, String sfz, String name, String info){
        Map<String,Object> map = new HashMap<>();
        map.put("errorcode", 0);
        return map;
    }

    @Override
    public TransferDelivery findByOrder(String orderNo) {
        return transferDeliceryDao.findByOrderNo(orderNo);
    }

    @Override
    public String findBySaveJztx(JztxDTO jztxDTO) {
        String orderno = null;
        String message = null;
        String oderNO = null;
        // TODO 查看下单是否有此订单
        TransferCleaning transferCleaning = transferCleaningService.finfByOrderNo(jztxDTO.getOrderNO());
        // TODO 查看是否已经通知过了
        TransferDelivery byOrder = findByOrder(jztxDTO.getOrderNO());
        try {
            orderno = transferCleaning.getOrderno();
            oderNO = byOrder.getOrderno();
        } catch (NullPointerException e) {
           // e.printStackTrace();
        }
        if (orderno == null){
                return message = "订单有误、请查证！";
        }
        if (oderNO == null) {
            TransferDelivery td = new TransferDelivery();
            td.setSfz(jztxDTO.getSFZ());
            td.setSname(jztxDTO.getName());
            td.setOrderno(jztxDTO.getOrderNO());
            td.setJzyInfo(jztxDTO.getJzyInfo());
            transferDeliceryDao.saveDelivery(td);
             /* String mobile = transferCleaning.getCustomermobile();
        SMSCommand smsCommand = SMSCommand.ORG_PUSH;
        String content ="您好，您所订购的服务已受理成功,为您服务的是 "+ name +" 更多信息为 " +info+";如有疑问、请于客服联系！！" ;
        try {
            smsService.sendSms(0,VcodeTerminalType.CUSTOMER,mobile,smsCommand,content);
        } catch (SMSException e) {
            e.printStackTrace();
        }*/
             return "添加成功!!";
        }else{
            TransferDelivery td = new TransferDelivery();
            td.setSfz(jztxDTO.getSFZ());
            td.setSname(jztxDTO.getName());
            td.setOrderno(jztxDTO.getOrderNO());
            td.setJzyInfo(jztxDTO.getJzyInfo());
            td.setCreatetime(byOrder.getCreatetime());
            transferDeliceryDao.updateDelivery(td);
             /* String mobile = transferCleaning.getCustomermobile();
        SMSCommand smsCommand = SMSCommand.ORG_PUSH;
        String content ="您好，您所订购的服务已受理成功,为您服务的是 "+ name +" 更多信息为 " +info+";如有疑问、请于客服联系！！" ;
        try {
            smsService.sendSms(0,VcodeTerminalType.CUSTOMER,mobile,smsCommand,content);
        } catch (SMSException e) {
            e.printStackTrace();
        }*/
        return message = "修改成功！！";
        }
    }
}
