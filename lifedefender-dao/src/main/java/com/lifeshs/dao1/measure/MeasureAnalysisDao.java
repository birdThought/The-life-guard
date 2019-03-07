package com.lifeshs.dao1.measure;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.measure.MeasureAnalysisPO;

/**
 *  用户测量数据分析dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月30日 下午3:37:36
 */
@Repository(value = "measureAnalysisDao")
public interface MeasureAnalysisDao {

    /**
     *  获取测量分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午3:55:12
     *
     *  @param id 分析报告id
     *  @return
     */
    MeasureAnalysisPO getAnalysis(@Param("id") int id);
    
    /**
     *  获取测量分析报告列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午3:54:48
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @return
     */
    List<MeasureAnalysisPO> findAnalysisListByUserIdAndMeasueDate(@Param("userId") int userId, @Param("measureDate") Date measureDate);
    
    /**
     *  获取测量分析报告列表（分页）
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:12:10
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @param healthProduct 健康设备
     *  @param customerUserId 客服id
     *  @param read （用户）是否已读
     *  @param reply 是否回复
     *  @return
     */
    List<MeasureAnalysisPO> findAnalysisListWithCondition(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userId") Integer userId, @Param("measureDate") Date measureDate, @Param("healthProduct") Integer healthProduct,
            @Param("customerUserId") Integer customerUserId, @Param("read") Boolean read, @Param("reply") Boolean reply);
    
    /**
     *  统计测量分析报告数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:17:28
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @param healthProduct 健康设备
     *  @param customerUserId 客服id
     *  @param read （用户）是否已读
     *  @param reply 是否回复
     *  @return
     */
    int countAnalysisWithCondition(@Param("userId") Integer userId, @Param("measureDate") Date measureDate,
            @Param("healthProduct") Integer healthProduct, @Param("customerUserId") Integer customerUserId,
            @Param("read") Boolean read, @Param("reply") Boolean reply);
    
    /**
     *  添加测量数据分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午3:35:21
     *
     *  @param analysis 分析报告
     *  @return
     */
    int addAnalysis(MeasureAnalysisPO analysis);
    
    /**
     *  更新测量数据分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午3:34:43
     *
     *  @param analysis 分析报告
     *  @return
     */
    int updateAnalysis(MeasureAnalysisPO analysis);
    
    /**
     *  根据测量日期，更新测量数据分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午5:09:30
     *
     *  @param read 阅读状态
     *  @param deleted 是否删除
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @return
     */
    int updateAnalysisListByUserIdAndMeasureDate(@Param("read") Boolean read, @Param("deleted") Boolean deleted,
           @Param("userId") int userId, @Param("measureDate") Date measureDate);
}
