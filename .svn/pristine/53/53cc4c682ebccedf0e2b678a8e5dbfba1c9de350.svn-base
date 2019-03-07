package com.lifeshs.dao1.electronicCoupons;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.electronicCoupons.ElectronicCouponsPackagePO;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

/**
 * 电子券卡包dao
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年12月8日 上午9:58:26
 */
@Repository(value = "electronicCouponsPackageDao")
public interface ElectronicCouponsPackageDao {

    /**
     * 添加卡包
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:07:55
     *
     * @param entity
     *            卡包
     * @return
     */
    int addPackage(ElectronicCouponsPackagePO entity);

    /**
     * 更新卡包
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:08:03
     *
     * @param entity
     *            卡包
     * @return
     */
    int updatePackage(ElectronicCouponsPackagePO entity);
    
    /**
     * 删除卡包
     * 
     * @author zizhen.huang
     * @DateTime 2017年12月12日10:16:20
     * 
     * @param id
     * @return
     */
    int deletePackage(@Param("id") int id);

    /**
     * 获取卡包
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:08:09
     *
     * @param id
     *            卡包id
     * @return
     */
    ElectronicCouponsPackageVO getPackage(@Param("id") int id);

    /**
     * 获取卡包
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:08:15
     *
     * @param code
     *            卡包识别码
     * @return
     */
    ElectronicCouponsPackageVO findPackageByCode(@Param("code") String code);

    /**
     * 统计卡包
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:08:26
     *
     * @return
     */
    int countPackageWithCondition();

    /**
     * 获取卡包列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年12月8日 上午10:08:33
     *
     * @param startRow
     *            开始下标
     * @param pageSize
     *            页面大小
     * @return
     */
    List<ElectronicCouponsPackageVO> findPackageListWithCondition(@Param("startRow") int startRow,
            @Param("pageSize") int pageSize);
}
