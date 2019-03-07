package com.lifeshs.service.log.impl;/**
 * Created by Administrator on 2017/4/19.
 */

import com.lifeshs.dao.log.ILogDao;
import com.lifeshs.pojo.log.LoginLogDTO;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.log.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LOG服务层实现
 *
 * @author wenxian.cai
 * @create 2017-04-19 10:06
 **/
@Service("logService")
public class LogServiceImpl extends CommonServiceImpl implements ILogService{
    @Autowired
    private ILogDao logDao;

    @Override
    public LoginLogDTO getLastLoginLog(int userId) {
        return logDao.getLastLogLogin(userId);
    }

    @Override
    public void addSensitiveLog(SensitiveOperationLogDTO sensitiveOperationLogDTO) {
        logDao.addSensitiveLog(sensitiveOperationLogDTO);
    }
}
