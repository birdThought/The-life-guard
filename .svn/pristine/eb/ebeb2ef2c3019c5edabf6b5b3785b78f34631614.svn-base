package com.lifeshs.service1.transfer;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dto.manager.JztxDTO;
import com.lifeshs.po.transfer.TransferDelivery;

import java.util.Map;

/**
 * @author Administrator
 * @create 2018-01-18
 * 17:07
 * @desc
 */
public interface TransferDeliveryService {

    Map<String,Object> saveCallback(String appId, String accesstoken, String oderNo, String sfz, String name, String info) throws OperationException;

    TransferDelivery findByOrder(String orderNo);

    String findBySaveJztx(JztxDTO jztxDTO);
}
