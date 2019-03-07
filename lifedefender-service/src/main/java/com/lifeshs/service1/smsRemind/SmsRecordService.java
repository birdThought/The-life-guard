package com.lifeshs.service1.smsRemind;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.SmsRecordPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.util.sms.SmsCommand;
import com.lifeshs.vo.sms.SmsRecordVO;

/**
 *  短信记录service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月28日 下午2:07:37
 */
public interface SmsRecordService {

    /**
     *  添加短信发送记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月28日 下午2:07:47
     *
     *  @param record 一条记录
     *  @throws OperationException
     */
    void addSmsRecord(SmsRecordPO record) throws OperationException;
    
    /**
     *  添加短信发送记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月28日 下午2:08:01
     *
     *  @param recordList 记录列表
     *  @throws OperationException
     */
    void addSmsRecord(List<SmsRecordPO> recordList) throws OperationException;
    
    /**
     * 获取短信记录列表
     * @author zizhen.huang
     * @DateTime 2018年1月23日20:16:59
     * 
     * @param userName 用户名
     * @param receiveMobile 接收号码
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<SmsRecordVO> findSmsRecordList(String userName, String receiveMobile, int curPage, int pageSize);
    
    /**
     * 发送短信
     * @author zizhen.huang
     * @DateTime 2018年1月26日14:59:27
     * 
     * @param userNumber
     * @param message
     * @return
     */
    boolean send(String userNumber, String message);
}
