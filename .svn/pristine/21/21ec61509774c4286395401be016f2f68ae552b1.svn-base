package com.lifeshs.service1.record;

import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.record.PhysicalVO;

/**
 *  体检报告
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月11日 下午2:54:30
 */
public interface PhysicalService {

    /**
     *  获取用户的体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:55:19
     *
     *  @param userId 用户id
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<PhysicalVO> listPhysical(int userId, int curPage, int pageSize);
    
    /**
     *  获取体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 上午9:59:02
     *
     *  @param id 体检报告id
     *  @param userId 用户id
     *  @return
     */
    PhysicalVO getPhysical(int id, int userId);
    
    /**
     *  获取体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午2:57:58
     *
     *  @param id 体检报告id
     *  @return
     */
    PhysicalVO getPhysical(int id);
    
    /**
     *  添加体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:59:04
     *
     *  @param physical 体检报告
     *  @throws BaseException
     */
    void addPhysical(PhysicalVO physical) throws BaseException;
    
    /**
     *  删除体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 上午9:53:45
     *
     *  @param id 体检报告id
     *  @param userId 用户id
     *  @exception OperationException
     */
    void deletePhysical(int id, int userId) throws OperationException;
    
    /**
     *  更新体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:59:16
     *
     *  @param physical 体检报告
     *  @throws BaseException
     */
    void updatePhysical(PhysicalVO physical) throws BaseException;
}
