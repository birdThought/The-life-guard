package com.lifeshs.service1.member;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.reportAnalysis.ContentStatusEnum;
import com.lifeshs.common.constants.common.reportAnalysis.RequestorTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.user.ReportAnalysisPO;
import com.lifeshs.service1.page.Paging;

/**
 *  分析报告service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月10日 上午11:16:26
 */
public interface ReportAnalysisService {

    /**
     *  获取报告详情
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 下午6:01:31
     *
     *  @param id 报告id
     *  @return
     */
    ReportAnalysisPO getReport(int id);
    
    /**
     *  获取分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 下午5:56:49
     *
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @param healthProduct 健康设备
     *  @param userId 用户id
     *  @param read 是否已读
     *  @param deleted 是否已删除
     *  @param reply 是否已回复
     *  @param status 状态 0正常 1异常
     *  @return
     */
    Paging<ReportAnalysisPO> listReport(int curPage, int pageSize, Integer userId, Integer healthProduct, Boolean read, Boolean deleted, Boolean reply, ContentStatusEnum status);
    
    /**
     *  提交分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月10日 上午11:37:44
     *
     *  @param userId 用户id
     *  @param requestorType 请求者类型
     *  @param healthPackageType 健康设备
     *  @param content 分析报告具体内容
     *  @throws OperationException
     */
    int commitReport(int userId, RequestorTypeEnum requestorType, HealthPackageType healthPackageType, String content, ContentStatusEnum status) throws OperationException;
    
    /**
     *  回复分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月10日 下午1:59:15
     *
     *  @param id 分析报告id
     *  @param customerId 客服id
     *  @param reply 回复内容
     *  @param doctorSign 医生署名
     *  
     *  @exception OperationException
     */
    void replyReport(int id, int customerId, String reply, String doctorSign) throws OperationException;
    
    /**
     *  修改报告的状态为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午3:52:19
     *
     *  @param id
     */
    void readReport(int id);
    
    /**
     *  删除分析报告
     *  ps: 只针对用户提供该方法
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午2:16:55
     *
     *  @param id 分析报告id
     *  @param userId 用户id
     *  @throws OperationException
     */
    void deleteReport(int id, int userId) throws OperationException;
}
