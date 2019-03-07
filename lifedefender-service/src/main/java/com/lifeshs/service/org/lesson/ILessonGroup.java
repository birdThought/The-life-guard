package com.lifeshs.service.org.lesson;

import java.util.List;

import com.lifeshs.pojo.org.group.LessonDTO;

/**
 *  版权归
 *  TODO 群组课堂接口
 *  @author wenxian.cai 
 *  @datetime 2017年3月1日上午9:59:04
 */

public interface ILessonGroup {
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月1日上午10:02:57
	 * @serverComment 根据服务Id获取健康课堂列表
	 * @param 
	 */
	List<LessonDTO> listServeLesson(int orgServeId);
	
	/**
	 *  创建课堂
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月1日 下午2:12:44
	 *
	 *  @param lesson
	 *  @param userId
	 *  @param ownerHuanxinId
	 */
	void addLesson(LessonDTO lesson, int userId, String ownerHuanxinId);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月2日上午10:27:38
	 * @serverComment 删除课堂
	 * @param id
	 */
	Boolean deleteLesson(int id);
	
	/**
	 *  获取课堂信息
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月1日 下午7:40:52
	 *
	 *  @param id
	 *  @return
	 */
	LessonDTO getLesson(int id);
	
	/**
	 *  获取课堂信息
	 *  <p>该方法会查询用户所属的全部环信群组信息，然后根据传递的huanxinIds数组，对应的过滤查询结果
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月10日 下午4:04:20
	 *
	 *  @param huanxinIds 课堂环信ID
	 *  @param memberId 用户ID
	 *  @return
	 */
	List<LessonDTO> listLesson(List<String> huanxinIds, int memberId);
	
	/**
	 *  获取课堂信息
     *  <p>该方法会查询用户所属的全部环信群组信息，然后根据传递的huanxinIds数组，对应的过滤查询结果
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月13日 下午4:00:44
	 *
	 *  @param huanxinIds
	 *  @param userId
	 *  @return
	 */
	List<LessonDTO> listServeUserLesson(List<String> huanxinIds, int userId);
	
	/**
	 *  更新课堂信息
	 *  注意：更新开课时间会导致删除原有的开课时间信息，重新创建开课时间信息
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月2日 下午3:07:58
	 *
	 *  @param lesson
	 *  @return
	 */
	boolean updateLesson(LessonDTO lesson);
	
	/**
	 *  添加机构用户
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月2日 下午5:59:11
	 *
	 *  @param groupId 群组ID
	 *  @param orgUserIds
	 *  @return
	 */
	boolean addOrgUser(int groupId, List<Integer> orgUserIds);
	
	/**
	 *  移除机构用户
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月2日 下午5:59:14
	 *
	 *  @param groupId 群组ID
	 *  @param orgUserIds
	 *  @return
	 */
	boolean removeOrgUser(int groupId, List<Integer> orgUserIds);
	
	/**
	 *  移除用户
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月9日 下午3:36:58
	 *
	 *  @param groupId
	 *  @param userId
	 *  @return
	 */
	boolean removeUser(int groupId, int userId);
	
	/**
	 *  课堂禁言
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月3日 上午10:41:56
	 *
	 *  @param groupId
	 *  @return
	 */
	boolean gag(int groupId);
	
	/**
	 *  解除禁言
	 *  @author yuhang.weng 
	 *	@DateTime 2017年3月3日 上午10:43:16
	 *
	 *  @param groupId
	 *  @return
	 */
	boolean removeGag(int groupId);
}
