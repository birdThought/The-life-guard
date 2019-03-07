package com.lifeshs.service1.electronicCoupons;

import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsPackageVO;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

/**
 *  电子券卡包Service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 上午11:12:49
 */
public interface ElectronicCouponsPackageService {

    /**
     *  添加卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:59:16
     *
     *  @param addPackageVO 卡包
     *  @throws BaseException
     */
    void addPackage(AddElectronicCouponsPackageVO addPackageVO) throws BaseException;
    
    /**
     *  更新卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午11:11:10
     *
     *  @param updatePackageVO 卡包
     *  @throws BaseException
     */
    void updatePackage(AddElectronicCouponsPackageVO updatePackageVO) throws BaseException;
    
    /**
     *  删除卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午11:11:20
     *
     *  @param id 卡包id
     *  @throws OperationException
     */
    void deletePackage(int id) throws OperationException;
    
    /**
     *  获取卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午11:11:27
     *
     *  @param id 卡包id
     *  @return
     */
    ElectronicCouponsPackageVO getPackage(int id);
    
    /**
     *  获取卡包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午11:11:34
     *
     *  @param code 卡包识别码
     *  @return
     */
    ElectronicCouponsPackageVO getPackage(String code);
    
    /**
     *  获取卡包列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午11:11:40
     *
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<ElectronicCouponsPackageVO> listPackage(int curPage, int pageSize);
}
