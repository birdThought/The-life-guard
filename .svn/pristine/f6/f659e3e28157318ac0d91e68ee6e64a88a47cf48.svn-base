package com.lifeshs.service.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.dao.data.IHealthReasonDao;
import com.lifeshs.pojo.data.ReasonDTO;
import com.lifeshs.service.data.IHealthReasonService;

@Service(value = "healthReasonService")
public class HealthReasonServiceImpl implements IHealthReasonService {

    @Resource(name = "healthReasonDao")
    private IHealthReasonDao reasonDao;
    
    @Override
    public String getReason(HealthType healthType, HealthRank rank, boolean professional) {
        // 健康参数的二进制值
        Long healthParamBinaryValue = healthType.value();
        // status修改为偏低或偏高 
        Integer status = rank.getRankValue();
        if (status == HealthRank.less.getRankValue()) {
            status = HealthRank.min.getRankValue();
        }
        if (status == HealthRank.more.getRankValue()) {
            status = HealthRank.max.getRankValue();
        }
        
        String reason = "";
        List<ReasonDTO> reasonDTOs = reasonDao.getReason(healthParamBinaryValue, status, professional);
        // 获取的结果长度
        int reasonListSize = reasonDTOs.size();
        if (reasonListSize > 0) {
            /** 
             * 使用ThreadLocalRandom随机一个下标
             * 获取该下标对应的原因
            */
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int index = random.nextInt(0, reasonListSize);
            reason = reasonDTOs.get(index).getReason();
        }
        return reason;
    }

    @Override
    public List<ReasonDTO> listReason(HealthPackageType packageType, boolean professional) {
        List<ReasonDTO> returnData = new ArrayList<>();
        
        int healthPackageBinaryValue = packageType.value();
        List<ReasonDTO> reasonDTOs = reasonDao.listReason(healthPackageBinaryValue, professional);
        
        Map<Long, List<ReasonDTO>> sameParamBinaryValueReasonMap = getSameParamBinaryValueReasonMap(reasonDTOs);
        /**
         * 利用map的键值对，随机获取相同status的一条建议
         */
        for (Long key : sameParamBinaryValueReasonMap.keySet()) {
            List<ReasonDTO> distinctStatusReasonList = getDistinctStatusReasonList(sameParamBinaryValueReasonMap.get(key));
            returnData.addAll(distinctStatusReasonList);
        }
        
        return returnData;
    }
    
    /**
     *  对相同参数二进制的ReasonDTO进行分类
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 下午6:41:17
     *
     *  @param reasonDTOs
     *  @return
     */
    private Map<Long, List<ReasonDTO>> getSameParamBinaryValueReasonMap(List<ReasonDTO> reasonDTOs) {
        /**
         * 利用map的键值对，把相同的参数筛选出来
         */
        Map<Long, List<ReasonDTO>> sameParamBinaryValueReasonMap = new HashMap<>();
        for (ReasonDTO reasonDTO : reasonDTOs) {
            Long paramBinaryValue = reasonDTO.getHealthParamBinaryValue();
            // 已有该Reason
            if (sameParamBinaryValueReasonMap.containsKey(paramBinaryValue)) {
                sameParamBinaryValueReasonMap.get(paramBinaryValue).add(reasonDTO);
                continue;
            }
            List<ReasonDTO> sameParamBinaryValueReasonList = new ArrayList<>();
            sameParamBinaryValueReasonList.add(reasonDTO);
            sameParamBinaryValueReasonMap.put(paramBinaryValue, sameParamBinaryValueReasonList);
        }
        return sameParamBinaryValueReasonMap;
    }
    
    private List<ReasonDTO> getDistinctStatusReasonList(List<ReasonDTO> reasonList) {
        List<ReasonDTO> reasonDTOs = new ArrayList<>();
        Map<Integer, List<ReasonDTO>> sameStatusReasonMap = getSameStatusReasonMap(reasonList);
        for (Integer status : sameStatusReasonMap.keySet()) {
            List<ReasonDTO> sameStatusList = sameStatusReasonMap.get(status);
            int size = sameStatusList.size();
            if (size > 0) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int index = random.nextInt(0, size);
                reasonDTOs.add(sameStatusList.get(index));
            }
        }
        
        return reasonDTOs;
    }
    
    /**
     *  对相同status的ReasonDTO进行分类
     *  @author yuhang.weng 
     *	@DateTime 2017年3月14日 下午6:40:48
     *
     *  @param reasonDTOs
     *  @return
     */
    private Map<Integer, List<ReasonDTO>> getSameStatusReasonMap(List<ReasonDTO> reasonDTOs) {
        /**
         * 利用map的键值对，把相同的status筛选出来
         */
        Map<Integer, List<ReasonDTO>> sameStatusReasonMap = new HashMap<>();
        for (ReasonDTO reasonDTO : reasonDTOs) {
            int status = reasonDTO.getStatus();
            if (sameStatusReasonMap.containsKey(status)) {
                sameStatusReasonMap.get(status).add(reasonDTO);
                continue;
            }
            List<ReasonDTO> sameStatusReasonList = new ArrayList<>();
            sameStatusReasonList.add(reasonDTO);
            sameStatusReasonMap.put(status, sameStatusReasonList);
        }
        return sameStatusReasonMap;
    }
}
