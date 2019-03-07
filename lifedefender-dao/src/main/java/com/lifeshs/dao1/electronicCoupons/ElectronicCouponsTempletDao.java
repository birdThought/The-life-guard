package com.lifeshs.dao1.electronicCoupons;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.electronicCoupons.ElectronicCouponsTempletPO;
import com.lifeshs.vo.electronicCoupons.CouponsTempletVO;

/**
 *  电子券模板dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 上午10:27:23
 */
@Repository(value = "electronicCouponsTempletDao")
public interface ElectronicCouponsTempletDao {

    /**
     *  添加模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:28:24
     *
     *  @param templet 模板
     *  @return
     */
    int addTemplet(ElectronicCouponsTempletPO templet);
    
    /**
     *  更新模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:28:33
     *
     *  @param templet 模板
     *  @return
     */
    int updateTemplet(ElectronicCouponsTempletPO templet);
    
    /**
     *  获取模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:28:42
     *
     *  @param id 模板id
     *  @return
     */
    ElectronicCouponsTempletPO getTemplet(@Param("id") int id);
    
    /**
     *  统计模板
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:28:53
     *
     *  @return
     */
    int countTempletWithCondition();
    
    /**
     *  获取模板列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 上午10:29:01
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param orgId 机构id
     *  @param projectCode 项目code
     *  @param serveItemId 具体服务项目id
     *  @return
     */
    List<CouponsTempletVO> findTempletListWithCondition(@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize,
            @Param("orgId") Integer orgId, @Param("projectCode") String projectCode, @Param("serveItemId") Integer serveItemId);
}
