package com.lifeshs.service1.member;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.user.PhysicalItemPO;
import com.lifeshs.service1.page.Paging;

/**
 *  会员体检项目service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月12日 下午3:58:38
 */
public interface PhysicalItemService {

    /**
     *  获取体检项目列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 下午3:54:57
     *
     *  @param userId 用户id
     *  @param status 状态
     *  @param physicalItemId 体检项目id
     *  @param reply 是否已回复
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<PhysicalItemPO> listPhysicalItem(Integer userId, Integer status, Integer physicalItemId, Boolean reply, int curPage, int pageSize);
    
    /**
     *  提交体检项目预约
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 下午4:54:38
     *
     *  @param userId 用户id
     *  @param physicalItemId 体检项目id
     */
    void commitPhysicalItem(int userId,int comboId, int physicalItemId) throws OperationException;
}
