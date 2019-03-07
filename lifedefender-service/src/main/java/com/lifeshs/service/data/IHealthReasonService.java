package com.lifeshs.service.data;

import java.util.List;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.pojo.data.ReasonDTO;

/**
 *  形成原因Service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月14日 上午11:26:45
 */
public interface IHealthReasonService {

    /**
     *  获取一条异常形成原因
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 下午1:45:36
     *
     *  @param healthType
     *  @param rank
     *  @param professional 是否为专业信息
     *  @return
     */
    String getReason(HealthType healthType, HealthRank rank, boolean professional);
    
    /**
     *  获取形成原因集合
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 下午2:36:34
     *
     *  @param packageType
     *  @param professional 是否为专业信息
     *  @return
     */
    List<ReasonDTO> listReason(HealthPackageType packageType, boolean professional);
}
