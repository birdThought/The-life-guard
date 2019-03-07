package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.PhysicalPO;
import com.lifeshs.vo.record.PhysicalVO;

/**
 *  体检报告dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月30日 下午2:59:05
 */
@Repository(value = "physicalDao")
public interface PhysicalDao {

    /**
     *  通过用户id查询体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 上午11:31:27
     *
     *  @param userId 用户id
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<PhysicalVO> findPhysicalListByUserId(@Param("userId") int userId, @Param(value = "startRow") int startRow,
            @Param(value = "pageSize") int pageSize);
    
    /**
     *  统计用户的体检报告数量
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午3:31:13
     *
     *  @param userId 用户id
     *  @return
     */
    int countPhysical(@Param("userId") int userId);
    
    /**
     *  获取一个体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 上午10:00:28
     *
     *  @param id 体检报告id
     *  @param userId 用户id
     *  @return
     */
    PhysicalVO findPhysicalByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  添加体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午3:34:40
     *
     *  @param physical 体检报告
     *  @return
     */
    int addPhysical(PhysicalPO physical);
    
    /**
     *  删除体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午4:54:49
     *
     *  @param id 体检报告id
     *  @param userId 用户id
     *  @return
     */
    int delPhysicalByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  更新体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午4:55:04
     *
     *  @param physical 体检报告
     *  @return
     */
    int updatePhysical(PhysicalPO physical);
    
    /**
     *  获取体检报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午2:59:08
     *
     *  @param id 体检报告id
     *  @return
     */
    PhysicalVO getPhysical(@Param("id") int id);
}
