package com.lifeshs.dao1.record;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.record.MedicalPO;
import com.lifeshs.vo.record.MedicalBasicVO;
import com.lifeshs.vo.record.MedicalVO;

@Repository(value = "medicalDao")
public interface MedicalDao {

    /**
     *  获取一个病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月19日 下午1:56:05
     *
     *  @param id
     *  @return
     */
    MedicalVO getMedical(@Param("id") int id);
    
    /**
     *  获取一个病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 上午11:49:17
     *
     *  @param id 病历id
     *  @param userId 用户id
     *  @return
     */
    MedicalVO findMedicalByIdAndUserId(@Param("id") int id, @Param("userId") Integer userId);
    
    /**
     *  通过用户id查询病历记录(基础信息)
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 上午10:30:00
     *
     *  @param userId 用户id
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<MedicalBasicVO> findBasicMedicalByUserIdList(@Param("userId") int userId, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     *  统计用户的病历数量
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:00:45
     *
     *  @param userId 用户id
     *  @return
     */
    int countMedical(@Param("userId") int userId);
    
    /**
     *  通过用户id查询病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 上午11:50:15
     *
     *  @param userId 用户id
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<MedicalVO> findMedicalByUserIdList(@Param("userId") int userId, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     *  添加病历
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:09:41
     *
     *  @param medical 一个病历
     *  @return
     */
    int addMedical(MedicalPO medical);
    
    /**
     *  删除病历
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:56:41
     *
     *  @param id 病历id
     *  @param userId 用户id
     *  @return
     */
    int delMedicalByIdAndUserId(@Param("id") int id, @Param("userId") int userId);
}
