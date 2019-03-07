package com.lifeshs.dao1.project;

import com.lifeshs.dto.manager.serve.GetLessonByHXData;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 健康课堂实体操作
 * Created by dengfeng on 2017/7/5 0005.
 */
@Repository("v2lessonDao")
public interface ILessonDao {
    /**
     * 得到单条健康课堂信息
     * @param projectCode
     * @return
     */
    LessonPO getLesson(@Param(value = "projectCode") String projectCode);

    /**
     * 得到单条健康课堂信息（按环信ID）
     * @param huanxinId
     * @return
     *//*
    LessonPO findLessonByHuanxinId(@Param(value = "huanxinId") String huanxinId);*/

    /**
     * 得到健康课堂信息（按环信ID）
     * @param huanxinId
     * @return
     */
    List<LessonPO> findLessonByHuanxinId(@Param(value = "huanxinIdList") List<String> huanxinIdList);
    
    /**
     *  获取课堂信息，根据环信ID
     *  <p>该方法会查询用户所属的全部环信群组信息，然后根据传递的huanxinIds数组，对应的过滤查询结果
     *
     *  @param huanxinIds
     *  @return
     */
    List<GetLessonByHXData> getLessonListByHX(@Param(value = "huanxinIds") List<String> huanxinIds);

    /**
     * 控制健康课堂禁言
     * @param projectCode
     * @param silence 是否禁言,0:不禁，1禁
     */
    void updateGag(@Param(value = "projectCode") String projectCode, @Param(value = "silence") int silence);

    /**
     * 获取课堂群成员
     * @param code
     * @return
     * @param code:项目code
     */
    List<UserPO> findLessonMemberList(@Param("projectCode") String code);
    
    /**
     *  查找过期的课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 上午9:13:53
     *
     *  @param remainDay 剩余天数
     *  @return
     */
    List<LessonPO> findLessonOutOfEndDate(@Param("remainDay") int remainDay);

    /**
     * 根据服务师获取课堂信息列表
     * @param orgUserId
     * @param name 课堂名称
     * @return
     */
    int countLessonByServices(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "name") String name);

    /**
     * 根据服务师获取课堂数目
     * @param orgUserId 服务师id
     * @param name 课堂名称
     * @return
     */
    List<LessonPO> findLessonListByServices(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "name") String name, @Param(value = "startRow") Integer startRow,
                                 @Param(value = "pageSize") Integer pageSize);

    /**
     * 修改课堂项目
     * @param lesson
     */
    void updateLesson(LessonPO lesson);

}
