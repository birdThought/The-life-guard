package com.lifeshs.dao.record;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.entity.record.TRecordPhysicals;
import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.pojo.record.DietDetail;

/**
 * 版权归
 * TODO 	健康档案
 *
 * @author wenxian.cai
 * @datetime 2016年8月6日下午4:09:46
 */
@Repository("recordDao")
public interface IRecordDao {

    /**
     * 通过用户Id查询病历
     *
     * @param userId:用户Id
     * @author wenxian.cai
     * @DateTime 2016年8月8日上午9:49:39
     * @serverComment 服务注解
     */
    public List<Map<String, Object>> selectMedicalByUserId(@Param("userId") Integer userId);

    /**
     * 通过用户ID与病历ID查询病历
     *
     * @param medicalId
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年8月15日 下午4:23:47
     */
    public Map<String, Object> selectMedicalByMedicalIdAndUserId(@Param("medicalId") Integer medicalId, @Param("userId") Integer userId);

    /**
     * 通过用户Id分页查询病历
     *
     * @param userId:用户Id,start:记录开始下标,pageSize:每页记录数
     * @author wenxian.cai
     * @DateTime 2016年8月8日上午9:57:11
     * @serverComment 服务注解
     */
    public List<Map<String, Object>> selectMedicalByUserIdPageSplit(@Param("userId") Integer userId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 根据日期与用户ID获取病历信息
     *
     * @param userId
     * @param visitingDate
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月19日 下午8:14:36
     */
    public List<Map<String, Object>> selectMedicalByVisitingDateAndUserId(@Param("userId") Integer userId, @Param("visitingDate") String visitingDate);

    /**
     * 通过病历ID查询病程信息
     *
     * @param medicalId 病历ID
     * @author wenxian.cai
     * @DateTime 2016年8月8日上午10:31:39
     * @serverComment 服务注解
     */
    public List<Map<String, Object>> selectMedicalCourseByMedicalId(@Param("medicalId") Integer medicalId);

    /**
     * 通过用户Id分页查询病程
     *
     * @param medicalId:病历Id,startIndex:记录开始下标,pageSize:每页记录数
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午2:35:02
     * @serverComment
     */
    public List<Map<String, Object>> selectMedicalCourseByMedicalIdPageSplit(@Param("medicalId") Integer medicalId,
                                                                             @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 通过病程类型查询病程
     *
     * @param courseType 病程类型
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午2:36:35
     * @serverComment
     */
    public List<Map<String, Object>> selectMedicalCourseByCourseType(@Param("medicalId") Integer medicalId, @Param("courseType") String courseType);

    /**
     * 通过病程类型查询病程并进行分页
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午2:45:16
     * @serverComment
     */
    public List<Map<String, Object>> selectMedicalCourseByCourseTypePageSplit(@Param("medicalId") Integer medicalId,
                                                                              @Param("courseType") String courseType, @Param("startIndex") Integer startIndex,
                                                                              @Param("pageSize") Integer pageSize);

    /**
     * 通过病历ID查询病程的总记录数
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午2:52:35
     * @serverComment
     */
    public Integer selectMedicalCourseCountByMedicalId(@Param("medicalId") Integer medicalId);

    /**
     * 通过用户ID查询体检报告
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午4:31:00
     * @serverComment
     */
    public <T> List<Map<String, Object>> selectPhysicalsByUserId(Map<String, Object> params);

    /**
     * 根据日期与用户ID获取体检报告信息
     *
     * @param userId        用户ID
     * @param physicalsDate 体检日期
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月20日 上午11:41:04
     */
    public List<TRecordPhysicals> selectPhysicalsByPhysicalsDateAndUserId(@Param("userId") Integer userId, @Param("physicalsDate") String physicalsDate);

    /**
     * 通过用户ID分页查询体检报告
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午4:33:08
     * @serverComment
     */
    public <T> List<Map<String, Object>> selectPhysicalsByUserIdPageSplit(Map<String, Object> params);

    /**
     * 通过用户Id查询体检报告总记录数
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午4:34:01
     * @serverComment
     */
    public <T> Integer selectPhysicalsCountByUserId(Map<String, Object> params);

    /**
     * 通过用户和日期获取指定日期饮食
     *
     * @param userId
     * @param recordDate 日期
     * @return
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午5:42:18
     */
    public List<Map<String, Object>> selectDietByUserIdWithDate(@Param("userId") Integer userId, @Param("recordDate") String recordDate);

    /**
     * 通过用户和日期分页获取饮食
     *
     * @param 接受参数param{"userId":"[userId]","recordDate":"[YYYY-mm-dd]","page":"[page]","pageSize":"[pageSize]"}
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午5:37:24
     * @serverComment
     */
    public <T> List<Map<String, Object>> selectDietByUserIdWithDatePageSplit(Map<String, Object> params);

    /**
     * 通过用户ID获取饮食总记录数
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月8日下午5:49:59
     * @serverComment
     */
    public <T> Integer selectDietCountByUserId(Map<String, Object> params);

    /**
     * 通过饮食id查询食物
     *
     * @param dietId
     * @return
     * @author wenxian.cai
     * @DateTime 2016年8月10日下午3:06:23
     */
    public List<Map<String, Object>> selectDietFoodByDietId(@Param("dietId") Integer dietId);

    /**
     * 查询最近一周的饮食能量
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月13日下午4:10:03
     * @serverComment
     */
    public List<Map<String, Object>> selectDietEnergyByUserIdWithDate(
            @Param("userId") int userId,
            @Param("customSetDate") boolean customSetDate,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /**
     * 根据食物类型名称获取属于该类型的食物信息
     *
     * @param 接受参数param{"kindName":"[kindName]"}
     * @author wenxian.cai
     * @DateTime 2016年8月16日下午5:59:56
     * @serverComment
     */
    public List<Map<String, Object>> selectFoodByKind(Map<String, Object> params);

    /**
     * 获取所有的食物分类
     *
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月25日 下午5:55:00
     */
    public List<TDataFoodKind> selectFoodAllKind();

    /**
     * 获取用户饮食记录数量（一天算一个）
     *
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月28日 下午2:13:50
     */
    public Integer selectCountOfDietGroupByDate(@Param("userId") int userId);

    /**
     * 分页获取饮食记录日期
     *
     * @param startIndex
     * @param pageSize
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月28日 下午2:13:53
     */
    public List<String> selectDietDateGroupByDateWithPageSplit(@Param("userId") int userId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 通过记录日期获取饮食记录
     *
     * @param recordDates
     * @param userId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年10月28日 下午2:13:55
     */
    public List<DietDetail> selectDietsWithDates(@Param("userId") int userId, @Param("recordDates") List<String> recordDates);

    public List<TRecordSport> selectTRecordSportWithDate(@Param("userId") int userId, @Param("recordDate") String recordDate);

    public List<TRecordSport> selectSportEnergyByUserIdWithDate(
            @Param("userId") int userId,
            @Param("customSetDate") boolean customSetDate,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /**
     * @param
     * @author wenxian.cai
     * @DateTime 2017年1月9日下午8:10:05
     * @serverComment 删除饮食记录
     */
    public int delSportByRecordSportId(@Param("recordSportId") int recordSportId);

    /**
     * @param
     * @author wenxian.cai
     * @DateTime 2017年1月10日下午4:59:47
     * @serverComment 删除运动记录里的单个运动(t_record_sport_detail)
     */
    public int deleteSportDetail(@Param("detailId") int detailId);


    /**
     * 获取指定月存在数据的日期 体检
     *
     * @param userId
     * @param queryDate
     * @return
     */
    Set<String> selectPhysicalsDataByUserIdAndQueryDate(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    /**
     * 获取指定月存在数据的日期 病例
     *
     * @author wuj
     * @param userId 用户ID
     * @param queryDate 查询月首日,例如:2017-05-01
     * @return 日期集合,利用set去重.format: yyyy-mm-dd
     */
    Set<String> selectMedicalDataByUserIdAndQueryDate(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    /**
     * 获取指定月存在数据的日期 饮食
     *
     * @param userId
     * @param queryDate
     * @return
     */
    Set<String> selectDietDataByUserIdAndQueryDate(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    /**
     * 获取存在饮食数据的日期集合
     *
     * @param userId
     * @return
     */
    List<Date> selectLastDietDateWithExistData(@Param("userId") Integer userId);
}
