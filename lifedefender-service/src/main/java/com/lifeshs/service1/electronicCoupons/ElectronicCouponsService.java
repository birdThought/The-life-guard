package com.lifeshs.service1.electronicCoupons;

import java.util.List;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.service1.page.Paging;

/**
 *  电子券service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 下午3:23:36
 */
public interface ElectronicCouponsService {

    /**
     *  添加电子券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:13:14
     *
     *  @param packageCode 卡包识别码
     *  @param userId 用户id
     *  @throws OperationException
     */
    void addCoupons(String packageCode, int userId) throws OperationException;
    
    /**
     *  使用电子券，将电子券状态修改为使用中
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:15:00
     *
     *  @param id 电子券id
     *  @param userId 用户id
     *  @param serveCode 服务code
     *  @param projectCode 机构项目code
     *  @param serveItemId 具体服务项目id
     *  @throws BaseException
     */
    void useCoupons(int id, int userId, String serveCode, String projectCode, int serveItemId) throws BaseException;
    
    /**
     *  结束电子券，将电子券状态修改为已使用
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:16:35
     *
     *  @param id 电子券id
     *  @param userId 用户id
     *  @param projectCode 机构项目code
     *  @param serveItemId 具体服务项目id
     *  @throws OperationException
     */
    void finishCoupons(int id, int userId, String projectCode, int serveItemId) throws OperationException;
    
    /**
     *  获取电子券列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:17:55
     *
     *  @param userId 用户id
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<UserElectronicCouponsPO> listCoupons(int userId, int curPage, int pageSize);
    
    /**
     *  获取可用的电子券列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:21:01
     *
     *  @param userId 用户id
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @param projectCode 服务项目code
     *  @param serveItemId 服务项目id
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<UserElectronicCouponsPO> listCouponsUsable(int userId, ProjectType projectType, String projectCode, Integer serveItemId,
                                                      int curPage, int pageSize);
    
    /**
     *  获取即将过期的电子券列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:18:48
     *
     *  @param validDay 剩余有效天数
     *  @return
     */
    List<UserElectronicCouponsPO> listCouponsOutOfEndDate(int validDay);
    
    /**
     *  获取电子券信息
     *  @author yuhang.weng 
     *  @DateTime 2017年12月14日 下午3:48:22
     *
     *  @param id 电子券id
     *  @return
     */
    UserElectronicCouponsPO getCoupons(int id);
    
    /**
     *  更新电子券状态为失效
     *  @author yuhang.weng 
     *  @DateTime 2018年1月2日 下午1:44:58
     *
     *  @param idList
     *  @throws ParamException
     */
    void updateCouponsOutOfEndDate(List<Integer> idList) throws ParamException;
}
