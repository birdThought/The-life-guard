package com.lifeshs.dao1.electronicCoupons;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  卡包电子券模板关联dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月7日 下午5:33:23
 */
@Repository(value = "electronicCouponsPackageRelationDao")
public interface ElectronicCouponsPackageRelationDao {

    /**
     *  添加关联关系
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午9:08:18
     *
     *  @param packageId 卡包id
     *  @param templetIdList 电子券模板id
     *  @return 受影响的字段行数
     */
    int addRelation(@Param("packageId") int packageId, @Param("templetIdList") List<Integer> templetIdList);
    
    /**
     *  删除关联关系
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午9:08:16
     *
     *  @param id 关联id
     *  @return 受影响的字段行数
     */
    int delRelation(@Param("id") int id);
    
    /**
     *  删除关联关系
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午9:09:41
     *
     *  @param packageId 卡包id
     *  @param templetIdList 电子券模板id
     *  @return 受影响的字段行数
     */
    int delRelationByPackageIdAndTempletId(@Param("packageId") int packageId, @Param("templetIdList") List<Integer> templetIdList);
    
    /**
     *  删除关联关系
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午6:34:56
     *
     *  @param templetId 模板id
     *  @return
     */
    int delRelationByTempletId(@Param("templetId") int templetId);
}
