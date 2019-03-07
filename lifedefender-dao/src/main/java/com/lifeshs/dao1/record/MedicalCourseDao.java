package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.MedicalCoursePO;
import com.lifeshs.vo.record.MedicalCourseVO;

@Repository(value = "medicalCourseDao")
public interface MedicalCourseDao {

    /**
     *  获取一个病程记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午5:29:09
     *
     *  @param id 病程id
     *  @param userId 用户id
     *  @return
     */
    MedicalCourseVO findCourseByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  添加病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:24:16
     *
     *  @param medicalCourse 一个病程
     *  @return
     */
    int addCourse(MedicalCoursePO medicalCourse);
    
    /**
     *  添加病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:25:03
     *
     *  @param medicalCourseList 病程列表
     *  @param medicalId 病历id
     *  @return
     */
    int addCourseList(@Param("medicalCourseList") List<MedicalCoursePO> medicalCourseList, @Param("medicalId") int medicalId);
    
    /**
     *  删除病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 上午10:04:54
     *
     *  @param id 病程id
     *  @param userId 用户id
     *  @return
     */
    int delCourseByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
    
    /**
     *  删除病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午3:03:01
     *
     *  @param medicalId 病历id
     *  @param userId 用户id
     *  @return
     */
    int delCourseByMedicalIdAndUserIdList(@Param("medicalId") int medicalId, @Param("userId") int userId);
    
    /**
     *  更新病程信息
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 上午10:02:21
     *
     *  @param medicalCourse 一个病程
     *  @return
     */
    int updateCourseByIdAndUserId(@Param("course") MedicalCoursePO medicalCourse, @Param("userId") int userId);
}
