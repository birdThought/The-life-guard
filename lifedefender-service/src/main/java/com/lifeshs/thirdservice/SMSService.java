package com.lifeshs.thirdservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.component.sms.SmsPortType;
import com.lifeshs.component.sms.SmsService;
import com.lifeshs.po.SmsRecordPO;
import com.lifeshs.pojo.member.VcodeDTO;
import com.lifeshs.pojo.sms.SmsResult;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service1.smsRemind.SmsRecordService;
import com.lifeshs.service1.smsRemind.SmsRemindService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.PropertiesUtil;
import com.lifeshs.utils.SMSCommand;

/**
 * 版权归 TODO 短信发送服务类
 *
 * @author duosheng.mo
 * @DateTime 2016年5月16日 下午3:07:49
 */
public class SMSService {

    @Autowired
    private IMemberService memberService;

    @Resource(name = "smsRemindService")
    private SmsRemindService smsRemindService;
    
    @Resource(name = "smsRecordService")
    private SmsRecordService smsRecordService;
    
    /**
     * 发送短信
     *
     * @param userNumber 手机号码多个用逗号隔开
     * @param smsCommand 发送的指令
     * @param checkSmsReminder 是否检查用户短信剩余量
     * @param str 参数
     * @return 返回结果
     * @throws OperationException 操作失败
     * @throws SMSException 短信发送失败
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {OperationException.class, SMSException.class})
    public void send(Integer sendId, VcodeTerminalType vcodeTerminalType, String userNumber,
            SMSCommand smsCommand, boolean checkSmsReminder, String... str) throws OperationException, SMSException {
        /**
         * 会导致数据回滚
         * 1. 用户短信剩余量不足
         * 2. 扣除短信数量失败
         * 3. 发送短信失败
         * 
         * 一定会执行的
         * a. 发送短信不管成功与否，都会做记录
         */
        // 发送手机号码非空判断
        if (!StringUtils.isNotBlank(userNumber)) {
            throw new OperationException("手机号码不能为空", ErrorCodeEnum.MISSING);
        }
        // 发送次数限制
        if (sendId != null && !memberService.filterIllegalMobileNumber(sendId, userNumber)) {
            throw new OperationException(String.format("发送人：%s，号码：%s，发送次数超过10次，已被拦截 ", sendId, userNumber), ErrorCodeEnum.FAILED);
        }
        
        /** 短信内容开始 */
        String message = messageHandle(smsCommand, str);
        // 发送的内容末端追加日期
        message = message + "  " + DateTimeUtilT.dateTime(new Date());
        /** 短信内容结束 */
        
        /** 记录发送的数据开始 */
        int sendType = 0; // 发送类型为平台_0
        
        // 存在sendId为空的平台发送信息
        // checkSmsReminder检查用户短信剩余量
        if (sendId != null && checkSmsReminder) {
            // 减少用户短信剩余量
            smsRemindService.reduceMemberSmsRemind(sendId, 1);
        }
        /** 记录发送的数据结束 */
        
        /** 验证码开始 */
        // 估计是WEB需要用到的短信登录
        // 作为验证码类型，将验证码存储起来
        if ("CODE".equals(smsCommand.name())) {
            VcodeDTO vcodeDTO = new VcodeDTO();
            vcodeDTO.setContent(str[0]);
            vcodeDTO.setTerminalType(vcodeTerminalType.getValue());
            vcodeDTO.setType(1); // 暂时设为“注册”类型 TODO
            memberService.addVcode(vcodeDTO);
        }
        /** 验证码结束 */
        
        /** 发送短信代码模块开始 */
        PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
        SmsService sms = new SmsService();
        SmsPortType smsPortType = sms.getSmsHttpPort();

        // 获取发送信息
        String orgNumber = pro.readProperty("sms.org.unmber");
        String smsUser = pro.readProperty("sms.user");
        String smsPassword = pro.readProperty("sms.password");
        // 提交发送请求
        String result = smsPortType.sms(orgNumber, smsUser, smsPassword, message, userNumber, "", "", "1", "", "1", "");

        boolean res = true;    // 记录短信发送成功或者失败
        String errorMsg = null; // 记录短信发送失败原因
        
        // try-catch如果捕获到SMSException，说明发送短信失败，并且能够在message中得到错误信息
        // 这里先不对发送短信是否成功进行处理
        // 先进行短信发送记录，接着如果短信发送失败，对除了保存记录这个操作之外的所有数据库操作，进行回滚
        try {
            handleResult(result);
        } catch (SMSException e) {
            errorMsg = "短信发送失败(" + e.getMessage() + ")";
            res = false;
        }
        if (sendId != null) {
            int sendStatus = res ? 0 : 1;
            // 记录发送的指令
            String arr[] = userNumber.split(",");
            if (arr.length > 0) {
                List<SmsRecordPO> recordList = new ArrayList<>();
                for (String number : arr) {
                    SmsRecordPO record = new SmsRecordPO();
                    record.setSendId(sendId);
                    record.setSendType(sendType);
                    record.setContent(message);
                    record.setReceiveMobile(number);
                    record.setStatus(sendStatus);
                    recordList.add(record);
                }
                smsRecordService.addSmsRecord(recordList);
            }
        }
        
        if (!res) {
            throw new SMSException(errorMsg);
        }
        /** 发送短信代码模块结束 */
    }

    /**
     *  发送短信 （免费）
     * @param sendId
     * @param vcodeTerminalType
     * @param userNumber
     * @param smsCommand
     * @param str
     */
    public void sendSms(Integer sendId,VcodeTerminalType vcodeTerminalType, String userNumber, SMSCommand smsCommand,String... str)
            throws OperationException, SMSException{
        if (!StringUtils.isNotBlank(userNumber)){
            throw new OperationException("手机号码不能为空", ErrorCodeEnum.MISSING);
        }

        /** 短信内容开始 */
        String message = messageHandle(smsCommand, str);
        // 发送的内容末端追加日期
        message = message + "  " + DateTimeUtilT.dateTime(new Date());
        /** 短信内容结束 */

        /** 记录发送的数据开始 */
        int sendType = 3; // 发送类型为客服
        /** 验证码开始 */
        // 估计是WEB需要用到的短信登录
        // 作为验证码类型，将验证码存储起来
        if ("CODE".equals(smsCommand.name())) {
            VcodeDTO vcodeDTO = new VcodeDTO();
            vcodeDTO.setContent(str[0]);
            vcodeDTO.setTerminalType(vcodeTerminalType.getValue());
            vcodeDTO.setType(3); //  服务受理通知
            memberService.addVcode(vcodeDTO);
        }
        /** 验证码结束 */

        /** 发送短信代码模块开始 */
        PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
        SmsService sms = new SmsService();
        SmsPortType smsPortType = sms.getSmsHttpPort();

        // 获取发送信息
        String orgNumber = pro.readProperty("sms.org.unmber");
        String smsUser = pro.readProperty("sms.user");
        String smsPassword = pro.readProperty("sms.password");
        System.out.println(String.format("orgNumber:%s,smsUser:%s,smsPassword:%s,message:%s,userNumber:%s", orgNumber, smsUser, smsPassword, message, userNumber));
        // 提交发送请求
        String result = smsPortType.sms(orgNumber, smsUser, smsPassword, message, userNumber, "", "", "1", "", "1", "");
        System.out.println("短信发送：result："+result);
        
        boolean res = true;    // 记录短信发送成功或者失败
        String errorMsg = null; // 记录短信发送失败原因

        try {
            handleResult(result);
        } catch (SMSException e) {
            errorMsg = "短信发送失败(" + e.getMessage() + ")";
            res = false;
        }
        if (sendId != null) {
            int sendStatus = res ? 0 : 1;
            // 记录发送的指令
            String arr[] = userNumber.split(",");
            if (arr.length > 0) {
                List<SmsRecordPO> recordList = new ArrayList<>();
                for (String number : arr) {
                    SmsRecordPO record = new SmsRecordPO();
                    record.setSendId(sendId);
                    record.setSendType(sendType);
                    record.setContent(message);
                    record.setReceiveMobile(number);
                    record.setStatus(sendStatus);
                    recordList.add(record);
                }
                smsRecordService.addSmsRecord(recordList);
            }
        }

        if (!res) {
            throw new SMSException(errorMsg);
        }
    }


    /**
     * 过滤非法号码
     * 
     * @param userNumber
     * @param sendId
     */
    /*
     * private void filterIllegalMobileNumber(String userNumber, int sendId) {
     * //TODO 调用HttpUtils.getIpAddress获取IP地址 //其他逻辑... }
     */

    /**
     * 指令处理
     *
     * @param smsCommand
     * @return
     */
    private String messageHandle(SMSCommand smsCommand, String... str) {
        /*
         * String message=null; switch (smsCommand) { case LOCATION://实时定位 case
         * DZWL://电子围栏、轨迹
         * 
         * case MONITOR://实时监听 message=replace(smsCommand.GetCommand(), str);
         * break; case MODNUM://修改密码 message=replace(smsCommand.GetCommand(),
         * str); break; case SENDSMS://发送SMS消息
         * message=replace(smsCommand.GetCommand(), str); break; case
         * PLAYVOICE://播放语言 message=replace(smsCommand.GetCommand(), str);
         * break; case POWEROFF://关机 message=smsCommand.GetCommand(); break;
         * case CLEARSMS://清除SIM卡中的SMS记录 message=smsCommand.GetCommand(); break;
         * case UPDATEFW://文件、图片、视屏等资源下载更新
         * message=replace(smsCommand.GetCommand(),str); break; case
         * OUTFENCE://出栏文本提醒 message=smsCommand.GetCommand(); break; case
         * ONFENCE://入栏文本提醒 message=smsCommand.GetCommand(); break; case
         * SENPOWEROFF://关机通知亲情号码 message=smsCommand.GetCommand(); break; case
         * GROUPMONITOR://群组监控 message = replace(smsCommand.GetCommand(),str);
         * break; case SOS://SOS求救 message=replace(smsCommand.GetCommand(),
         * str); break; case SOSGPS://SOSGPS求救
         * message=replace(smsCommand.GetCommand(), str); break; case
         * HEARTPACKGE://上传心跳包指令 message = replace(smsCommand.GetCommand(),
         * str); break; case SSDW://实时定位 message =
         * replace(smsCommand.GetCommand(), str); break; case SSDWGPS://实时定位
         * message = replace(smsCommand.GetCommand(), str); break; case
         * CODE://注册验证码 message = replace(smsCommand.GetCommand(), str); break;
         * case JKTX://注册验证码 message = replace(smsCommand.GetCommand(), str);
         * break; default: break; }
         */
        String message = String.format(smsCommand.GetCommand().replace("?", "%s"), str);
        return message;
    }

    /**
     * 结果处理
     *
     * @param result
     * @return
     * @throws SMSException
     */
    private static SmsResult handleResult(String result) throws SMSException {
        SmsResult sr = new SmsResult();
        String[] results = result.split("&");
        for (String string : results) {
            String[] params = string.split("=");
            if ("result".equals(params[0])) {
                sr.setResult(params[1]);
                if (!"0".equals(params[1])) {
                    sr.setSuccess(false);
                }
            } else if ("description".equals(params[0])) {
                sr.setDescription(params[1]);
            } else if ("taskid".equals(params[0]) && params.length == 2) {
                sr.setTaskid(params[1]);
            } else if ("faillist".equals(params[0]) && params.length == 2) {
                sr.setFaillist(params[1]);
            } else if ("task_id".equals(params[0]) && params.length == 2) {
                sr.setTask_id(params[1]);
            }
        }
        // 如果短信发送失败抛出发送失败异常
        if (!sr.isSuccess()) {
            throw new SMSException(sr.getDescription());
        }
        return sr;
    }
    /*
     * 0 发送短信成功 1 提交参数不能为空 2 账号无效或未开户 3 账号密码错误, 4 预约发送时间格式不正确，应为yyyyMMddHHmmss 5
     * IP不合法, 6 号码中含有无效号码或不在规定的号段, 7 非法关键字 8 内容长度超过上限，最大402字符 9 接受号码过多，最大1000 11
     * 提交速度太快 12 您尚未订购[普通短信业务]，暂不能发送该类信息 13 您的[普通短信业务]剩余数量发送不足，暂不能发送该类信息 14
     * 流水号格式不正确 15 流水号重复 16 超出发送上限（操作员帐户当日发送上限） 17 余额不足 18 扣费不成功 20 发送失败 24
     * 账户状态不正常 25 账户权限不足 26 需要人工审核 28 发送内容与模板不符 29 扩展号太长或不是数字&accnum= 32
     * 同一号码相同内容发送次数太多
     */
}