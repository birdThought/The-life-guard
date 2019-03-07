package com.lifeshs.service.realTime.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.entity.data.TDataImei;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.realTime.IRealTimeService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.SMSCommand;

/**
 *  版权归
 *  TODO 实时数据实现类
 *  @author duosheng.mo  
 *  @DateTime 2016年6月20日 下午3:54:10
 */
@Component
public class RealTimeServiceImpl extends CommonServiceImpl implements IRealTimeService {

    protected static final Logger logger = Logger.getLogger(RealTimeServiceImpl.class);
    
    @Autowired
    private SMSService smsService;

    VcodeTerminalType vcodeTerminalType = VcodeTerminalType.USER_PLATFORM;
    
    @Override
    public boolean sendLocationCommand(String imei) throws SMSException {
        // TODO Auto-generated method stub
//      LoginUser user = ResourceUtil.getSessionUser();
        TUserTerminal terinal = commonTrans.findUniqueByProperty(TUserTerminal.class, "imei", imei);
        String mobile = terinal.getMobile();//手表要sim卡号
        TDataImei dataImei = commonTrans.findUniqueByProperty(TDataImei.class, "imei", imei);
        try {
            smsService.send(terinal.getUserId(), vcodeTerminalType, mobile, SMSCommand.LOCATION, false, dataImei.getPassword());
        } catch (OperationException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public boolean sendMonitorCommand(String mobile, String imei) throws SMSException {
        TUserTerminal terinal = commonTrans.findUniqueByProperty(TUserTerminal.class, "imei", imei);
        String simMobile = terinal.getMobile();//手表要sim卡号
        TDataImei dataImei = commonTrans.findUniqueByProperty(TDataImei.class, "imei", imei);
        try {
            smsService.send(terinal.getUserId(), vcodeTerminalType, simMobile, SMSCommand.MONITOR, false, dataImei.getPassword(), mobile);
        } catch (OperationException e) {
            return false;
        }
        return true;
    }

}
