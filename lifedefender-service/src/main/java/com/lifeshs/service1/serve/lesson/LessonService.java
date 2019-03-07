package com.lifeshs.service1.serve.lesson;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dto.manager.serve.GetLessonByHXData;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.serve.lesson.LessonConditionDTO;
import com.lifeshs.pojo.serve.lesson.RecommendedLessonDTO;
import com.lifeshs.service1.page.Paging;

public interface LessonService {

    /**
     *  获取推荐课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年6月20日 上午10:40:02
     *
     *  @param limit 数量
     *  @return
     */
    List<RecommendedLessonDTO> listRecommendedLesson(int limit);
    
    /**
     *  获取课堂筛选条件
     *  @author yuhang.weng 
     *	@DateTime 2017年6月23日 下午3:59:38
     *
     *  @return
     */
    LessonConditionDTO getLessonCondition();
    
    /**
     *  搜索课堂信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月22日 下午4:33:58
     *
     *  @param priceArea 价格区间
     *  @param type 课堂类型  类型如果是"全部"就取消该条件限制
     *  @param likeName 课堂名称模糊查询
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @return
     */
    PaginationDTO<RecommendedLessonDTO> listProjectLesson(String priceArea, String type, String likeName, int curPage, int pageSize);
    
    /**
     *  获取课堂详情
     *  @author yuhang.weng 
     *  @DateTime 2017年7月10日 下午6:37:10
     *
     *  @param id 课堂ID
     *  @return
     */
    RecommendedLessonDTO getLesson(int id);
    
    /**
     *  获取门店下的课堂列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午5:50:09
     *
     *  @param orgId
     *  @return
     */
    List<RecommendedLessonDTO> listLessonByOrgId(int orgId);

    /**
     *  获取服务师所属的课堂列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午11:41:24
     *
     *  @param serveUserId
     *  @return
     */
    List<RecommendedLessonDTO> listLessonByServeUserId(int serveUserId);
    
    /**
     * 得到单条健康课堂信息
     * @param projectCode
     * @return
     */
    LessonPO getLesson(String projectCode);

    /**
     *  获取课堂信息，根据环信ID
     *  <p>该方法会查询用户所属的全部环信群组信息，然后根据传递的huanxinIds数组，对应的过滤查询结果
     *
     *  @param huanxinIds
     *  @param userId
     *  @return
     */
    List<GetLessonByHXData> getLessonListByHX(List<String> huanxinIds, int userId);

    /**
     * 得到单条健康课堂信息（按环信ID）
     * @param huanxinId
     * @return
     */
    LessonPO findLessonByHuanxinId(String huanxinId);

    /**
     *  得到多条健康课堂信息（按环信ID）
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 下午3:42:47
     *
     *  @param huanxinIdList
     *  @return
     */
    List<LessonPO> findLessonByHuanxinId(List<String> huanxinIdList);
    
    /**
     *  课堂禁言
     *  @return
     */
    boolean gag(String projectCode);

    /**
     *  解除禁言
     *  @return
     */
    boolean removeGag(String projectCode);

    /**
     * 获取课堂群成员
     * @param code
     * @return
     */
    List<UserPO> findLessonMemberList(String code);
    
    /**
     *  移除群组
     *  @author yuhang.weng 
     *	@DateTime 2017年7月20日 上午10:50:55
     *
     *  @param id 群组id
     *  @param userId
     */
    void removeUser(int id, int userId) throws OperationException;
    
    /**
     *  获取过期的课堂服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 上午9:11:13
     *
     *  @param remainDay 剩余天数
     *  @return
     */
    List<LessonPO> listLessonOutOfEndDate(int remainDay);

    /**
     * 根据服务师获取课堂列表
     * @param orgUserId
     * @return
     */
    Paging<LessonPO> findLessonListByServices(Integer orgUserId, String name, Integer curPage, Integer pageSize);

    /**
     * 修改课堂服务项目
     * @param lesson
     */
    void updateLesson(LessonPO lesson, List<ServiceOrgUserRelationDTO> orgUsers) throws OperationException;
}
