package com.lifeshs.dao1.transfer;

import com.lifeshs.po.transfer.TransferDelivery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @create 2018-01-18
 * 17:18
 * @desc
 */
@Repository(value = "transferDeliceryDao")
public interface TransferDeliceryDao {

    int  saveDelivery(TransferDelivery transferDelivery);

    TransferDelivery findByOrderNo(@Param("orderno") String orderno);

    int updateDelivery(TransferDelivery td);
}
