package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.PhysicalImgPO;

/**
 *  体检报告图片dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月11日 下午2:30:49
 */
@Repository(value = "physicalImgDao")
public interface PhysicalImgDao {

    /**
     *  通过体检报告id查询图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:28:24
     *
     *  @param physicalId 体检报告id
     *  @return
     */
    List<PhysicalImgPO> findImgByPhysicalId(@Param("physicalId") int physicalId);
    
    /**
     *  添加体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:28:22
     *
     *  @param imgList 图片列表
     *  @param physicalId 体检报告id
     *  @return
     */
    int addPhysicalImgList(@Param("imgList") List<String> imgList, @Param("physicalId") int physicalId);
    
    /**
     *  删除体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:28:20
     *
     *  @param id 图片id
     *  @return
     */
    int delImg(@Param("id") int id);
    
    /**
     *  删除体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 下午1:51:37
     *
     *  @param idList 图片id集合
     *  @return
     */
    int delImgList(@Param("idList") List<Integer> idList);

    /**
     *  删除体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:28:17
     *
     *  @param physicalId 体检报告id
     *  @return
     */
    int delImgByPhysicalIdList(@Param("physicalId") int physicalId);
    
    /**
     *  更改体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月11日 下午2:28:15
     *
     *  @param physicalImg 图片
     *  @return
     */
    int updateImg(PhysicalImgPO physicalImg);
    
    /**
     *  更新体检报告图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月12日 上午9:27:10
     *
     *  @param imgList 图片列表
     *  @param physicalId 体检报告id
     *  @return
     */
    int updateImgList(@Param("imgList") List<PhysicalImgPO> imgList, @Param("physicalId") int physicalId);
}
