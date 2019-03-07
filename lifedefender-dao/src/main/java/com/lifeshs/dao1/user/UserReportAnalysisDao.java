package com.lifeshs.dao1.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.ReportAnalysisPO;

/**
 *  用户分析报告dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月9日 下午5:01:08
 */
@Repository(value = "userReportAnalysisDao")
public interface UserReportAnalysisDao {

    /**
     *  获取一份报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 下午5:18:40
     *
     *  @param id 报告id
     *  @return
     */
    ReportAnalysisPO getReport(@Param("id") int id);
    
    /**
     *  统计分析报告数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月10日 上午9:22:11
     *
     *  @param userId 用户id
     *  @param healthProduct 健康设备
     *  @param read 是否已读
     *  @param deleted 是否已删除
     *  @param reply 是否已回复
     *  @param status 状态 0正常 1异常
     *  @return
     */
    int countReportWithCondition(@Param("userId") Integer userId, @Param("healthProduct") Integer healthProduct,
            @Param("read") Boolean read, @Param("deleted") Boolean deleted, @Param("reply") Boolean reply, @Param("status") Integer status);
    
    /**
     *  获取分析报告列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月10日 上午9:22:13
     *
     *  @param startRow 页码
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param healthProduct 健康设备
     *  @param read 是否已读
     *  @param deleted 是否已删除
     *  @param reply 是否已回复
     *  @param status 状态 0正常 1异常
     *  @return
     */
    List<ReportAnalysisPO> findReportWithConditionList(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userId") Integer userId, @Param("healthProduct") Integer healthProduct, @Param("read") Boolean read,
            @Param("deleted") Boolean deleted, @Param("reply") Boolean reply, @Param("status") Integer status);
    
    /**
     *  添加报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 下午5:09:05
     *
     *  @param report
     *  @return
     */
    int addReport(ReportAnalysisPO report);
    
    /**
     *  更新报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 下午5:08:58
     *
     *  @param report
     *  @return
     */
    int updateReport(ReportAnalysisPO report);
    
    /**
     *  删除分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午2:20:54
     *
     *  @param id 报告id
     *  @param userId 用户id
     *  @return
     */
    int delReportByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
}
