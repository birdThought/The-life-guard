package com.lifeshs.thirdservice;

import com.lifeshs.po.transfer.TransferOrder;

public interface JztxService {
    
    //添加订单
    public int saveTransferOrder(TransferOrder to);
    
}
