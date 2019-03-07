package com.lifeshs.service.log;

import com.lifeshs.pojo.log.LoginLogDTO;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;

/**
 * LOG服务层
 *
 * @author wenxian.cai
 * @create 2017-04-19 10:05
 **/

public interface ILogService {

    /**
     * @Description: 获取用户最后一次操作记录
     * @author: wenxian.cai
     * @create: 2017/4/19 10:09
     */
    LoginLogDTO getLastLoginLog(int userId);

    /**
     * 存储敏感操作
     * @param sensitiveOperationLogDTO
     */
    void addSensitiveLog (SensitiveOperationLogDTO sensitiveOperationLogDTO);


}
