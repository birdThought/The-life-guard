package com.lifeshs.service.record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeshs.entity.data.TDataDepartment;
import com.lifeshs.entity.record.TRecordMedicalCourse;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.MedicalCaseVO;

/**
 * <p>
 * 病例
 * 
 * @author yuhang.weng
 * @DateTime 2016年8月13日 下午3:56:28
 */
public interface IMedicalService {

	/**
	 * 通过主键ID与用户ID查询病历实体
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日下午2:31:48
	 * @serverComment
	 * @param
	 */
	public Map<String, Object> selectMedicalByMedicalIdAndUserId(Integer medicalId, Integer userId);

	/**
	 * 通过用户ID查询病历
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:48:42
	 * @serverComment
	 * @param userId
	 */
	public List<Map<String, Object>> selectMedicalsListByUserId(Integer userId);

	/**
	 * <p>
	 * 通过用户ID分页查询病历（包含部分course）
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:53:01
	 *
	 * @param userId 用户ID
	 * @param nowPage 当前页码
	 * @param pageSize 每页显示数据大小
	 * @return
	 */
	public PaginationDTO getMedicalByUserIdPageSplit(Integer userId, int nowPage, int pageSize);
	
	/**
	 *  通过用户ID分页查询病历
	 *  @author yuhang.weng 
	 *	@DateTime 2016年11月30日 上午9:57:39
	 *
	 *  @param userId
	 *  @param curPage
	 *  @param pageSize
	 *  @return
	 */
	public PaginationDTO getMedicalByUserIdWithPageSplit(int userId, int curPage, int pageSize);
	
	/**
	 * 通过主键Id查询病程实体
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日下午8:02:46
	 * @serverComment
	 * @param
	 */
	public TRecordMedicalCourse selectMedicalCourseById(int id);

	/**
	 * 通过病历ID查询病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:57:26
	 * @serverComment
	 * @param medicalId:病历id
	 */
	public List<Map<String, Object>> selectMedicalCoursesListByMedicalId(int medicalId);

	/**
	 * 通过病历ID分页查询病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:38:13
	 * @serverComment
	 * @param medicalId:病历ID
	 * @param page:页码
	 * @param pageSize:每页显示数据大小
	 */
	public PaginationDTO selectMedicalCourseByMedicalIdPageSplit(int medicalId, int page, int pageSize);

	/**
	 * 通过病程类型查询病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:40:11
	 * @serverComment
	 * @param medicalId:病历ID
	 * @param courseType:病历类型
	 */
	public List<Map<String, Object>> selectMedicalCoursesListByCourseType(int medicalId, String courseType)
			throws Exception;

	/**
	 * 通过病程类型查询病程并进行分页
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:47:30
	 * @serverComment
	 * @param medicalId 病历ID
	 * @param courseType 病历类型
	 * @param page 页码
	 * @param pageSize 每页显示数量
	 */
	public PaginationDTO selectMedicalCourseByCourseTypePageSplit(int medicalId, String courseType, int page,
			int pageSize);

	/**
	 * 通过parentId获取department
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月16日 下午4:01:33
	 *
	 * @param parentId
	 * @return
	 */
	public List<TDataDepartment> selectDepartmentsByParentId(Integer parentId);

	/**
	 * 通过病历ID查询病程总记录数
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:55:42
	 * @serverComment
	 * @param medicalId:病历ID
	 */
	public Integer selectMedicalCourseCountByMedicalId(Integer medicalId);

	/**
	 * 通过子类部门的ID获取其父类部门(逐层向上查找)的集合
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月23日 上午11:33:12
	 *
	 * @param childDepartmentId
	 * @return
	 */
	public List<TDataDepartment> selectParentsDepartmentByChildId(Integer childDepartmentId);

	/**
	 * 添加病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:14:40
	 * @serverComment
	 * @param 病历id
	 */
	public Integer addMedicalCourse(Integer userId, Integer medicalId, String courseType, String remark,
			Date visitingDate, String img1, String img2, String img3) throws Exception;

	/**
	 * 添加病历
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月22日 下午4:13:43
	 *
	 * @param userId
	 * @param medicalCaseVO
	 * @return
	 */
	public Boolean addMedical(Integer userId, MedicalCaseVO medicalCaseVO) throws Exception;

	/**
	 * 修改病历
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:24:56
	 * @serverComment
	 * @param 主键ID
	 */
	public Integer updataMedical(Integer userId, Integer medicalId, String title, Date visitingDate,
			Integer departmentId, String doctorDiagnosis, String basicCondition, String hospital) throws Exception;

	/**
	 * 更新病历与病程，病程如果包含了id，就进行更新操作，如果不含有id，就添加一个新的病程 更新完后，会对不包含在本次操作中的病程进行删除操作
	 * </p>
	 * 示例： 本次更新包含的病程id有1，2，以及一个没有包含id的病程，就会更新id为1,2的数据，然后插入一个新的病程，
	 * 这时候，如果原病程包含有id为3的数据，由于本次更新操作不含有id为3的对象，就会对id为3的数据进行删除
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月23日 下午4:11:48
	 *
	 * @param medicalCaseVO
	 * @return
	 * @throws Exception
	 */
	public Boolean updateMedicalAndCourses(MedicalCaseVO medicalCaseVO) throws Exception;

	/**
	 * 修改病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:48:30
	 * @serverComment
	 * @param
	 */
	public Integer updataMedicalCourse(HashMap<String, Object> map, Integer Id) throws Exception;

	/**
	 * 删除病历
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:51:49
	 * @serverComment
	 * @param
	 */
	public Boolean deleteMedical(Integer userId, Integer medicalId) throws Exception;

	/**
	 * 删除病程
	 * 
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:51:58
	 * @serverComment
	 * @param
	 */
	public Boolean deleteMedicalCourse(Integer courseId);
	
	/**
	 * 
	 * 
	 *  @author yuhang.weng 
	 *	@DateTime 2016年12月12日 上午10:58:31
	 *
	 *  @return
	 */
	public List<TDataDepartment> selectAllDepartments();
}
