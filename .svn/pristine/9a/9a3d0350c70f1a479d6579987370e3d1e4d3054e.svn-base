package com.lifeshs.service1.electronicCoupons;

import java.util.List;

import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.CouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.UpdateElectronicCouponsTempletVO;

/**
 *  电子券模板Service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 下午3:07:11
 */
public interface ElectronicCouponsTempletService {

    /**
     *  添加模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午2:23:36
     *
     *  @param addTemplet 模板
     *  @throws BaseException
     */
    void addTemplet(AddElectronicCouponsTempletVO addTemplet) throws BaseException;
    
    /**
     *  更新模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:02:08
     *
     *  @param updateTemplet 模板
     *  @throws BaseException
     */
    void updateTemplet(UpdateElectronicCouponsTempletVO updateTemplet) throws BaseException;
    
    /**
     *  删除模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:02:39
     *
     *  @param id 模板id
     *  @throws OperationException
     */
    void deleteTemplet(int id) throws OperationException;
    
    /**
     *  获取模板列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午3:06:43
     *
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<CouponsTempletVO> listTemplet(int curPage, int pageSize);
    
    List<CouponsTempletVO> listTemplet(int orgId, String projectCode, Integer serveItemId);
}
