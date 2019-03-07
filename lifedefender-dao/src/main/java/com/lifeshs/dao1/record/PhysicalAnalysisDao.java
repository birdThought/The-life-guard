package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.PhysicalAnalysisPO;

/**
 *  体检报告分析dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月25日 下午5:51:17
 */
@Repository(value = "physicalAnalysisDao")
public interface PhysicalAnalysisDao {

    /**
     *  获取分析
     *  @author yuhang.weng 
     *  @DateTime 2017年10月25日 下午4:20:07
     *
     *  @param id
     *  @return
     */
    PhysicalAnalysisPO getAnalysis(@Param("id") int id);
    
    /**
     *  获取分析列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月25日 下午4:20:05
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param recordPhysicalId 体检报告id
     *  @param reply 是否回复
     *  @param deleted 是否删除
     *  @param customerUserId 客服id
     *  @param read 是否已读
     *  @return
     */
    List<PhysicalAnalysisPO> findAnalysisListWithCondition(@Param("startRow") Integer startRow,
            @Param("pageSize") Integer pageSize, @Param("userId") Integer userId, @Param("recordPhysicalId") Integer recordPhysicalId,
            @Param("reply") Boolean reply, @Param("deleted") Boolean deleted,
            @Param("customerUserId") Integer customerUserId, @Param("read") Boolean read);
    
    /**
     *  统计分析列表数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月25日 下午4:20:02
     *
     *  @param userId 用户id
     *  @param recordPhysicalId 体检报告id
     *  @param reply 是否回复
     *  @param deleted 是否删除
     *  @param customerUserId 客服id
     *  @param read 是否已读
     *  @return
     */
    int countAnalysisWithCondition(@Param("userId") Integer userId, @Param("recordPhysicalId") Integer recordPhysicalId,
            @Param("reply") Boolean reply, @Param("deleted") Boolean deleted,
            @Param("customerUserId") Integer customerUserId, @Param("read") Boolean read);
    
    /**
     *  更新分析
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:06:47
     *
     *  @param analysis
     *  @return
     */
    int updateAnalysis(PhysicalAnalysisPO analysis);
    
    /**
     *  更新分析
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午11:32:26
     *
     *  @param recordPhysicalId 体检报告id
     *  @param read 是否已读
     *  @param deleted 是否删除
     *  @return
     */
    int updateAnalysisListByRecordPhysicalId(@Param("recordPhysicalId") int recordPhysicalId,
            @Param("read") Boolean read, @Param("deleted") Boolean deleted);
    
    /**
     *  添加分析
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 上午9:31:59
     *
     *  @param analysis
     *  @return
     */
    int addAnalysis(PhysicalAnalysisPO analysis);
}
