package com.lifeshs.dao.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.data.ReasonDTO;

/**
 *  形成原因dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月14日 上午10:39:05
 */
@Repository(value = "healthReasonDao")
public interface IHealthReasonDao {

    /**
     *  获取一项健康参数的形成原因
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 上午11:27:57
     *
     *  @param healthParamBinaryValue
     *  @param status
     *  @param professional 是否为专业信息
     *  @return
     */
    List<ReasonDTO> getReason(
            @Param("healthParamBinaryValue") long healthParamBinaryValue,
            @Param("status") long status,
            @Param("professional") boolean professional);
    
    /**
     *  获取健康包相关的所有健康参数的形成原因
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 下午2:12:19
     *
     *  @param healthPackageBinaryValue
     *  @param professional 是否为专业信息
     *  @return
     */
    List<ReasonDTO> listReason(
            @Param("healthPackageBinaryValue") int healthPackageBinaryValue,
            @Param("professional") boolean professional);
}
