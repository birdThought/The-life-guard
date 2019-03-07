package com.lifeshs.service.tool;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.sms.SMSException;

/**
 * 验证码工具服务类
 * @author Yue.Li
 * @date 3/14/17.
 */
public interface IValidCodeService {
    /**
     *  根据手机号码发送验证码,并保存验证码到缓存
     *  @author dengfeng
     *	@DateTime 2016-6-2 上午11:19:05
     *
     *  @param sendId 发送者ID
     *  @param mobile 接收者手机号码
     *  @param key 保存缓存的key
     *  @param cache 缓存类型
     *  @param vcodeTerminalType
     *  @param checkSmsReminderNumber 是否检查用户短信剩余量
     *  @return 生成的验证码
     *  @throws SMSException 短信发送异常
     */
    String sendToMobile(Integer sendId, String mobile, String key, CacheType cache, VcodeTerminalType vcodeTerminalType, boolean checkSmsReminderNumber) throws SMSException;
    String sendToEmail(String email, String key, CacheType cache) throws SMSException;
    boolean valid(String key, String code, CacheType cache);
    boolean destory(String key, CacheType cache);
}
