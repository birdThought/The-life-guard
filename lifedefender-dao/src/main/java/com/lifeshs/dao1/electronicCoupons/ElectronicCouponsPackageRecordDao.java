package com.lifeshs.dao1.electronicCoupons;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.UserElectronicCouponsPackageRecordPO;

/**
 *  卡包领取记录
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月15日 下午3:48:22
 */
@Repository(value = "electronicCouponsPackageRecordDao")
public interface ElectronicCouponsPackageRecordDao {

    /**
     *  查询卡包记录
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:46:15
     *
     *  @param userId 用户id
     *  @param packageId 卡包id
     *  @return
     */
    UserElectronicCouponsPackageRecordPO findRecord(@Param("userId") int userId, @Param("packageId") int packageId);
    
    /**
     *  添加卡包记录
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:46:05
     *
     *  @param userId 用户id
     *  @param packageId 卡包id
     *  @return
     */
    int addRecord(UserElectronicCouponsPackageRecordPO record);
}
