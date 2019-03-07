package com.lifeshs.service.tool.impl;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.component.mail.MailSender;
import com.lifeshs.component.mail.MailSenderInfo;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.RandCodeUtil;
import com.lifeshs.utils.SMSCommand;

/**
 * 验证码工具类(暂时废弃)
 *  @author dengfeng  
 *  @DateTime 2016-6-2 下午02:11:29
 */
//@Component
@Deprecated
public class ValidCodeUtil {
	
	@Autowired
	private SMSService sendSMS;
	
	/**
	 *  根据手机号码发送验证码,并保存验证码到缓存
	 *  @author dengfeng
	 *	@DateTime 2016-6-2 上午11:19:05
	 *
	 *  @param mobile 接收验证码的手机号
	 *  @param key 保存缓存的key
	 *  @param cache 缓存类型
	 *  @return 生成的验证码
	 *  @throws SMSException 短信发送异常
	 */
	public String sendToMobile11111(Integer sendId, VcodeTerminalType vcodeTerminalType, String mobile, String key, CacheType cache) throws SMSException{
		
		// 生成验证码
		String code = RandCodeUtil.randNumberCodeByCustom("1", 4);
		
		// 保存缓存
		
		// 发送短信
		String[] strs = {code, "5"};
		try {
            sendSMS.send(sendId, vcodeTerminalType, mobile, SMSCommand.CODE, false, strs);
        } catch (OperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		CacheUtil.saveKeyValue(cache, key, code);	// mobile:code
		return code;
	}
	
	/**
	 *  根据邮箱地址发送验证码,并保存验证码到缓存
	 *  @author dengfeng
	 *	@DateTime 2016-6-2 上午11:19:05
	 *
	 *  @param email 接收验证码的邮箱
	 *  @param key 保存缓存用的key
	 *  @param cache 缓存类型
	 *  @return 生成的验证码
	 *  @throws SMSException 短信发送异常
	 */
	public static String sendToEmail(String email, String key, CacheType cache) throws SMSException{

		// 生成验证码
		String code = RandCodeUtil.randNumberCodeByCustom("1", 4);
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(email);
//		// TODO 规范发送内容
		mailInfo.setSubject("生命守护-验证码");
		mailInfo.setContent("您的激活码为" + code + "，请在5分钟内使用。");
		boolean bool = MailSender.sendTextMail(mailInfo);
		
		if(bool){
			// 保存缓存
			CacheUtil.saveKeyValue(cache, key, code);
			System.out.println("验证码：" + code);
		}else{
			code = "";	// 发送失败将验证码擦除
		}
		
		return code;
	}
	
	/**
	 *  验证已发送的验证码
	 *  @author dengfeng
	 *	@DateTime 2016-6-2 下午02:01:15
	 *  @serverComment 服务注解
	 *
	 *  @param key 查找缓存用的key
	 *  @param code 验证码
	 *  @param cache 缓存类型
	 *  @return
	 */
	public static boolean valid(String key, String code, CacheType cache){
		String validCode = (String) CacheUtil.getCacheValue(cache, key);
		
//		System.out.println(validCode);
//		
//		if (validCode == null || !validCode.equals(code)) 
//			return false;
		return true;
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月6日 上午11:02:59
	 *  @serverComment 销毁缓存
	 *
	 *  @param key 查找缓存用的key
	 *  @param cache 缓存类型
	 *  @return
	 */
	public static boolean destory(String key, CacheType cache){
		return CacheUtil.getCache(cache.getName()).remove(key);
	}
}

