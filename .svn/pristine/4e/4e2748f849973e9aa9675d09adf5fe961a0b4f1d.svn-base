package com.lifeshs.thirdservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.dao1.order.jztx.JztxDao;
import com.lifeshs.po.transfer.TransferOrder;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.thirdservice.JztxService;

@Service("jztxService")
public class JztxServiceImpl extends AppNormalService implements JztxService {
    private final Logger logger = LoggerFactory.getLogger(JztxServiceImpl.class);

    @Autowired
    JztxDao jztxDao;
    

    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveTransferOrder(TransferOrder to) {

        return jztxDao.saveTransferOrder(to);
    }
}
