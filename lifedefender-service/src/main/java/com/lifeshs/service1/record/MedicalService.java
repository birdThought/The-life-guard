package com.lifeshs.service1.record;

import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.record.MedicalPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.record.MedicalBasicVO;
import com.lifeshs.vo.record.MedicalCourseVO;
import com.lifeshs.vo.record.MedicalSubmitVO;
import com.lifeshs.vo.record.MedicalVO;

/**
 *  病历
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 下午1:47:36
 */
public interface MedicalService {

    // 病历
    
    /**
     *  获取一个病历记录(推荐使用)
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:50:52
     *
     *  @param id 病历id
     *  @param userId 用户id
     *  @return
     */
    MedicalVO getMedical(int id, int userId);
    
    /**
     *  获取一个病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月19日 下午1:54:07
     *
     *  @param id 病历id
     *  @return
     */
    MedicalVO getMedical(int id);
    
    /**
     *  获取用户病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午1:57:34
     *
     *  @param userId 用户id
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<MedicalBasicVO> listMedicalBasic(int userId, int curPage, int pageSize);
    
    /**
     *  获取用户病历记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:39:32
     *
     *  @param userId 用户id
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<MedicalVO> listMedical(int userId, int curPage, int pageSize);
    
    /**
     *  添加病历
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:54:31
     *
     *  @param medical 病历数据（基础）
     *  @throws BaseException
     */
    void addMedical(MedicalPO medical) throws BaseException;
    
    /**
     *  添加病历
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午3:51:11
     *
     *  @param medical 病历数据
     */
    void addMedical(MedicalSubmitVO medical) throws BaseException;
    
    /**
     *  删除病历
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:54:53
     *
     *  @param id
     *  @param userId
     *  @throws OperationException
     */
    void deleteMedical(int id, int userId) throws OperationException;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // 病程
    
    /**
     *  获取一个病程记录
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午5:26:25
     *
     *  @param id 病程id
     *  @param userId 用户id
     *  @return
     */
    MedicalCourseVO getMedicalCourse(int id, int userId);
    
    /**
     *  添加病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午4:28:13
     *
     *  @param course 病程
     *  @param userId 用户id
     *  @throws BaseException
     */
    void addMedicalCourse(MedicalCourseVO course, int userId) throws BaseException;
    
    /**
     *  删除病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:43:13
     *
     *  @param id
     *  @param userId
     *  @throws OperationException
     */
    void deleteMedicalCourse(int id, int userId) throws OperationException;
    
    /**
     *  更新病程
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:43:24
     *
     *  @param course
     *  @param userId
     *  @throws BaseException
     */
    void updateMedicalCourse(MedicalCourseVO course, int userId) throws BaseException;
}
