package com.lifeshs.service1.record;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.record.PhysicalAnalysisPO;

/**
 *  体检分析报告service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月27日 上午9:36:02
 */
public interface PhysicalAnalysisService {

    /**
     *  获取分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:35:38
     *
     *  @param id 分析报告id
     *  @return
     */
    PhysicalAnalysisPO getAnalysis(int id);
    
    /**
     *  分析体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:16:30
     *
     *  @param id 分析报告id
     *  @param reply 回复内容
     *  @param customerUserId 客服id
     *  @param doctorSign 医生签名
     *  @throws OperationException
     */
    void replyAnalysis(int id, String reply, int customerUserId, String doctorSign) throws OperationException;
    
    /**
     *  添加一条分析
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:21:30
     *
     *  @param physicalId 体检报告id
     *  @param reply 回复内容
     *  @param customerUserId 客服id
     *  @param doctorSign 医生签名
     *  @throws OperationException
     */
    void addAnalysis(int physicalId, String reply, int customerUserId, String doctorSign) throws OperationException;
    
    /**
     *  添加一条分析记录（未回复的）
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:37:48
     *
     *  @param physicalId 体检报告id
     *  @throws OperationException
     */
    void addAnalysisRecord(int physicalId) throws OperationException;
    
    /**
     *  统计用户未读的体检分析报告数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午10:05:33
     *
     *  @param userId 用户id
     *  @return
     */
    int countUnReadAnalysis(int userId);
    
    /**
     *  修改分析报告阅读状态为已读
     *  <p>如果客服还没有回复就不能修改为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午10:16:58
     *
     *  @param id 分析报告id
     *  @throws OperationException
     */
    void readAnalysis(int id) throws OperationException;
    
    /**
     *  修改分析报告阅读状态为已读
     *  <p>如果客服还没有回复就不能修改为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午11:48:31
     *
     *  @param physicalId 体检报告id
     *  @throws OperationException
     */
    void readAnalysisList(int physicalId);
    
    /**
     *  删除分析记录
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午1:57:00
     *
     *  @param physicalId 体检报告id
     *  @throws OperationException
     */
    void deleteAnalysis(int physicalId);
}
