package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.MedicalCourseImgPO;

@Repository(value = "medicalCourseImgDao")
public interface MedicalCourseImgDao {

    /**
     *  添加图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:47:17
     *
     *  @param imgList 图片列表
     *  @param medicalCourseId 病历id
     *  @return
     */
    int addImgList(@Param("imgList") List<String> imgList, @Param("medicalCourseId") int medicalCourseId);
    
    /**
     *  删除图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 上午9:41:26
     *
     *  @param idList 图片id列表
     *  @return
     */
    int delImgList(@Param("idList") List<Integer> idList);
    
    /**
     *  删除病程图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午1:46:56
     *
     *  @param medicalCourseId 病程id
     *  @return
     */
    int delImgByMedicalCourseIdList(@Param("medicalCourseId") int medicalCourseId);
    
    /**
     *  删除病历图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午3:11:31
     *
     *  @param medicalId 病历id
     *  @return
     */
    int delImgByMedicalIdList(@Param("medicalId") int medicalId);
    
    /**
     *  更新病程图片
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 上午9:41:16
     *
     *  @param imgList 图片列表
     *  @param medicalCourseId 病程id
     *  @return
     */
    int updateImgList(@Param("imgList") List<MedicalCourseImgPO> imgList, @Param("medicalCourseId") int medicalCourseId);
}
