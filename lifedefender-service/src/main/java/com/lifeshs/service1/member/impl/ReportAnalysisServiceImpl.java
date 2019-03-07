package com.lifeshs.service1.member.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.reportAnalysis.ContentStatusEnum;
import com.lifeshs.common.constants.common.reportAnalysis.RequestorTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.user.UserReportAnalysisDao;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.po.user.ReportAnalysisPO;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.service1.member.ReportAnalysisService;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.DateTimeUtilT;

@Service(value = "reportAnalysisService")
public class ReportAnalysisServiceImpl implements ReportAnalysisService {

    private Logger logger = Logger.getLogger(ReportAnalysisServiceImpl.class);
    
    @Resource(name = "userReportAnalysisDao")
    private UserReportAnalysisDao reportDao;
    
    @Autowired
    private UMengPushService uMengService;
    
    @Resource(name = "pushDataService")
    private PushDataService pDataService;
    
    @Autowired
    private MessageService messageService;
    
    @Override
    public ReportAnalysisPO getReport(int id) {
        return reportDao.getReport(id);
    }

    @Override
    public Paging<ReportAnalysisPO> listReport(int curPage, int pageSize, Integer userId, Integer healthProduct,
            Boolean read, Boolean deleted, Boolean reply, ContentStatusEnum status) {
        Paging<ReportAnalysisPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ReportAnalysisPO>() {

            @Override
            public int queryTotal() {
                Integer s = null;
                if (status != null) {
                    s = status.getValue();
                }
                return reportDao.countReportWithCondition(userId, healthProduct, read, deleted, reply, s);
            }

            @Override
            public List<ReportAnalysisPO> queryData(int startRow, int pageSize) {
                Integer s = null;
                if (status != null) {
                    s = status.getValue();
                }
                return reportDao.findReportWithConditionList(startRow, pageSize, userId, healthProduct, read, deleted, reply, s);
            }
        });
        
        return p;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public int commitReport(int userId, RequestorTypeEnum requestorType, HealthPackageType healthPackageType,
            String content, ContentStatusEnum status) throws OperationException {
        ReportAnalysisPO report = new ReportAnalysisPO();
        report.setUserId(userId);
        report.setHealthProduct(healthPackageType.value());
        report.setRequestorType(requestorType.getValue());
        report.setContent(content);
        report.setRead(false);
        report.setDeleted(false);
        report.setStatus(status.getValue());
        int result = reportDao.addReport(report);
        if (result == 0) {
            throw new OperationException("添加分析报告失败", ErrorCodeEnum.FAILED);
        }
        return report.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void replyReport(int id, int customerId, String reply, String doctorSign) throws OperationException {
        ReportAnalysisPO r = reportDao.getReport(id);
        if (r == null) {
            throw new OperationException("分析报告不存在", ErrorCodeEnum.NOT_FOUND);
        }
        
        if (r.getReply() != null) {
            throw new OperationException("请勿重复提交分析结果", ErrorCodeEnum.FAILED);
        }
        
        ReportAnalysisPO report = new ReportAnalysisPO();
        report.setId(id);
        report.setReplyUserId(customerId);
        report.setReply(reply);
        report.setDoctorSign(doctorSign);
        int result = reportDao.updateReport(report);
        if (result == 0) {
            throw new OperationException("提交分析结果失败", ErrorCodeEnum.FAILED);
        }
        
        // 保存推送消息记录
        int userId = r.getUserId();
        uMengService.replyReportEnd(userId, r.getId(), r.getCreateDate());
    }

    @Override
    public void readReport(int id) {
        ReportAnalysisPO report = new ReportAnalysisPO();
        report.setId(id);
        report.setRead(true);
        reportDao.updateReport(report);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void deleteReport(int id, int userId) throws OperationException {
        int result = reportDao.delReportByIdAndUserId(id, userId);
        if (result == 0) {
            throw new OperationException("删除分析报告失败", ErrorCodeEnum.FAILED);
        }
    }

}
