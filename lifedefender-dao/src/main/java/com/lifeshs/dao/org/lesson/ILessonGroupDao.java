package com.lifeshs.dao.org.lesson;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.org.group.CourseTimeDTO;
import com.lifeshs.pojo.org.group.LessonDTO;
import com.lifeshs.pojo.serve.lesson.RecommendedLessonDTO;

/**
 * 课堂群组Dao
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年2月28日 上午10:02:02
 */
@Repository(value = "lessonDao")
public interface ILessonGroupDao {

    /**
     * 获取机构服务下的所有课堂信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午9:58:33
     *
     * @param orgServeId
     * @return
     */
    @Deprecated
    List<LessonDTO> listServeLesson(@Param("orgServeId") int orgServeId);

    /**
     * 获取机构用户所属的所有课堂信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午9:59:32
     *
     * @param serveUserId
     * @return
     */
    @Deprecated
    List<LessonDTO> listServeUserLesson(@Param("serveUserId") int serveUserId);

    /**
     * 获取用户所属的所有课堂信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午10:00:13
     *
     * @param memberId
     * @return
     */
    @Deprecated
    List<LessonDTO> listMemberLesson(@Param("memberId") int memberId);

    /**
     * 获取课堂信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午10:01:13
     *
     * @param id
     * @return
     */
    @Deprecated
    LessonDTO getLesson(@Param("id") int id);

    /**
     *  添加课堂群组
     *  @author yuhang.weng 
     *	@DateTime 2017年2月28日 下午3:04:58
     *
     *  @param lesson
     *  @return
     */
    @Deprecated
    void addLesson(LessonDTO lesson);
    
    /**
     *  删除课堂开课时间
     *  @author yuhang.weng 
     *	@DateTime 2017年3月1日 上午10:03:42
     *
     *  @param groupId
     */
    @Deprecated
    void deleteCourseTime(@Param("groupId") int groupId);
    
    /**
     *  添加课堂开课时间
     *  @author yuhang.weng 
     *	@DateTime 2017年3月1日 上午10:05:27
     *
     *  @param courseTimeDTOs
     */
    @Deprecated
    void addCourseTime(@Param("groupId") int groupId,
                       @Param("courseTimeDTOs") List<CourseTimeDTO> courseTimeDTOs);

    /**
     * 更新课堂信息
     * 如果为NULL表示不更新该字段属性
     * 注意：photo、name、silence、description不能全部都是NULL
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午10:03:46
     *
     * @param id 群组ID
     * @param photo 群组头像路径
     * @param name 群组名称
     * @param silence 禁言状态
     * @return
     */
    @Deprecated
    void updateLesson(@Param("id") int id, @Param("photo") String photo, @Param("name") String name,
                      @Param("silence") Boolean silence, @Param("description") String description);

    /**
     * 删除群组
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月28日 上午10:05:17
     *
     * @param id
     * @return
     */
    @Deprecated
    boolean deleteLesson(@Param("id") int id);
    
    /**
     *  添加服务师
     *  @author yuhang.weng 
     *	@DateTime 2017年3月2日 下午6:38:20
     *
     *  @param userIds
     */
    @Deprecated
    void addOrgUser(@Param("groupId") int groupId, @Param("userIds") List<Integer> userIds);
    
    /**
     *  移除服务师
     *  @author yuhang.weng 
     *	@DateTime 2017年3月2日 下午6:45:44
     *
     *  @param groupId
     *  @param userIds
     */
    @Deprecated
    void removeOrgUser(@Param("groupId") int groupId, @Param("userIds") List<Integer> userIds);
    
    /**
     *  获取人数最多的课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年6月20日 上午9:45:44
     *
     *  @param limit
     *  @return
     */
    List<RecommendedLessonDTO> listLagerNumberOfBuyer(@Param("limit") int limit);
    
    /**
     *  筛选课堂信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月22日 上午11:51:02
     *
     *  @param priceType 价格类型 equals between under over
     *  @param startPrice 开始价格
     *  @param endPrice 结束价格
     *  @param type 课堂类型
     *  @param likeName 课堂名称
     *  @param startIndex 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<RecommendedLessonDTO> listLessonWithCondition(@Param("priceType") String priceType, @Param("startPrice") double startPrice,
            @Param("endPrice") double endPrice, @Param("type") String type, @Param("likeName") String likeName,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    /**
     *  统计筛选课堂信息数量
     *  @author yuhang.weng 
     *	@DateTime 2017年6月22日 下午4:31:07
     *
     *  @param priceType 价格类型 equals between under over
     *  @param startPrice 开始价格
     *  @param endPrice 结束价格
     *  @param type 课堂类型
     *  @param likeName 课堂名称
     *  @return
     */
    Integer countLessonWithCondition(@Param("priceType") String priceType, @Param("startPrice") double startPrice,
            @Param("endPrice") double endPrice, @Param("type") String type, @Param("likeName") String likeName);
    
    /**
     *  查询机构下所有的课堂服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月8日 下午4:52:31
     *
     *  @param orgId
     *  @return
     */
    List<RecommendedLessonDTO> findLessonByOrgId(@Param("orgId") int orgId);
    
    /**
     *  查询服务师所有的课堂服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午11:25:46
     *
     *  @param serveUserId
     *  @return
     */
    List<RecommendedLessonDTO> findLessonByServeUserId(@Param("serveUserId") int serveUserId);
    
    /**
     *  查找一个课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午6:39:19
     *
     *  @param id
     *  @return
     */
    RecommendedLessonDTO findLesson(@Param("id") int id);
    
    /**
     *  查找一个课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年7月14日 下午3:17:24
     *
     *  @param projectCode
     *  @return
     */
    RecommendedLessonDTO findLessonByProjectCode(@Param("projectCode") String projectCode);
}
