package com.lifeshs.service1.electronicCoupons;

import com.lifeshs.common.exception.common.OperationException;

/**
 *  用户卡包领取记录service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月15日 下午3:58:36
 */
public interface ElectronicCouponsPackageRecordService {

    /**
     *  检测用户是否使用过该卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:59:41
     *
     *  @param userId 用户id
     *  @param packageId 卡包id
     *  @return
     */
    boolean hasRecord(int userId, int packageId);
    
    /**
     *  添加记录
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:59:35
     *
     *  @param userId 用户id
     *  @param packageId 卡包id
     */
    void addRecord(int userId, int packageId) throws OperationException;
}
