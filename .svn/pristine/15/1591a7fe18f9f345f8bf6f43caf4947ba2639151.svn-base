package com.lifeshs.service.record;

import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.entity.record.TDataFood;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.entity.record.TRecordPhysicals;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.DietDTO;
import com.lifeshs.pojo.record.DietDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  版权归
 *  TODO 健康档案总接口
 *  @author wenxian.cai 
 *  @datetime 2016年8月8日上午10:47:13
 */
public interface IRecordService {

	
	/**
	 * 通过主键ID查询病历实体
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日下午2:31:48
	 * @serverComment 
	 * @param 
	 */
//	public Object selectMedicalById(int Id) throws Exception;
	/**
	 * 通过用户ID查询病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:48:42
	 * @serverComment 
	 * @param userId
	 */
//	public List<Map<String, Object>> selectMedicalByUserId(Integer userId) throws Exception;
	
	/**
	 * 通过用户ID分页查询病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:53:01
	 * @serverComment 
	 * @param 
	 * @param userId 用户ID
	 * @param nowPage 当前页码
	 * @param pageSize 每页显示数据大小
	 */
//	public PaginationDTO selectMedicalByUserIdPageSplit(Integer userId, int nowPage, int pageSize);
	
	/**
	 * 通过主键Id查询病程实体
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日下午8:02:46
	 * @serverComment 
	 * @param 
	 */
//	public Object selectMedicalCourseById(int id) throws Exception;
	/**
	 * 通过病历ID查询病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日上午10:57:26
	 * @serverComment 
	 * @param medicalId:病历id
	 */
//	public List<Map<String, Object>> selectMedicalCourseByMedicalId(int medicalId) throws Exception;
	
	/**
	 * 通过病历ID分页查询病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:38:13
	 * @serverComment 
	 * @param medicalId:病历ID
	 * @param page:页码
	 * @param pageSize:每页显示数据大小
	 */
//	public List<Map<String, Object>> selectMedicalCourseByMedicalIdPageSplit(int medicalId,int page,int pageSize) throws Exception;
	
	/**
	 * 通过病程类型查询病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:40:11
	 * @serverComment 
	 * @param medicalId:病历ID
	 * @param courseType:病历类型
	 */
//	public List<Map<String, Object>> selectMedicalCourseByCourseType(int medicalId,int courseType) throws Exception;
	
	/**
	 * 通过病程类型查询病程并进行分页
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:47:30
	 * @serverComment 
	 * @param medicalId:病历ID
	 * @param courseType:病历类型
	 * @param page:页码
	 * @param pageSize:每页显示数量 
	 */
//	public List<Map<String, Object>> selectMedicalCourseByCourseTypePageSplit(int medicalId,int courseType,int page,int pageSize) throws Exception;
	
	/**
	 * 通过病历ID查询病程总记录数
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:55:42
	 * @serverComment 
	 * @param medicalId:病历ID
	 */
//	public Integer selectMedicalCourseCountByMedicalId(Integer medicalId) throws Exception;
	
	/**
	 * 通过主键ID获取体检报告实体
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日上午10:03:42
	 * @serverComment 
	 * @param 
	 */
	public Object selectPhysicalsById(int Id);
	/**
	 * 通过用户ID查询体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午4:36:02
	 * @serverComment 
	 * @param userID:用户ID 
	 */
	public <T> List<Map<String, Object>> selectPhysicalsByUserId(Integer userID); 
	
	/**
	 * 通过用户ID分页查询体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午4:43:25
	 * @serverComment 
	 * @param userId:用户ID
	 * @param page:页码
	 * @param pageSize:每页显示数量 
	 */
	public PaginationDTO selectPhysicalsByUserIdPageSplit(Integer userId, int page, int pageSize);

	/**
	 *  获取体检分页条信息
	 *  @author yuhang.weng 
	 *	@DateTime 2016年9月22日 下午4:44:30
	 *
	 *  @param userId
	 *  @param curPage 当前页码
	 *  @param pageSize 页面大小
	 *  @return
	 */
	public PaginationDTO getPhysicalsPageBarData(Integer userId, Integer curPage, Integer pageSize);
	
	/**
	 * 通过用户Id查询体检报告总记录数
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午4:45:01
	 * @serverComment 
	 * @param 
	 */
	public <T> Integer selectPhysicalsCountByUserId(Integer userId); 
	
	/**
	 * 通过主键id查询饮食
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:55:34
	 * @serverComment 
	 * @param 主键ID
	 */
	public <T> Object selectDietById(int Id) throws Exception;
	
	/**
	 * 通过用户id和日期查询饮食列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:56:08
	 * @serverComment 
	 * @param 
	 */
	public List<Map<String, Object>> selectDietByUserIdWithDate(Integer userID,String date);
	
	/**
	 *  按照日期获取饮食记录
	 *  @author yuhang.weng 
	 *	@DateTime 2016年10月28日 下午2:21:28
	 *
	 *  @param userId
	 *  @param curPage
	 *  @param pageSize
	 *  @return
	 */
	public List<DietDetail> selectDietSplitWithRecordDate(int userId, int curPage, int pageSize);
	
	/**
	 * 查询最近这一周的饮食能量
	 * @author wenxian.cai
	 * @DateTime 2016年8月13日下午4:12:04
	 * @serverComment 
	 * @param 
	 */
	public List<Map<String, Object>> selectDietEnergyByUserIdWithDate(Integer userId, boolean customDate, String startDate, String endDate);
	
	/**
	 * 通过主键ID查询食物
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:57:01
	 * @serverComment 
	 * @param 主键id
	 */
	public <T> Object selectDietFoodById(int Id) throws Exception;
	
	/**
	 * 通过饮食id查询食物列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:57:23
	 * @serverComment 
	 * @param 
	 */
	public <T> List<Map<String, Object>> selectDietFoodByDietId(Integer DietID); 
	
	/**
	 * 根据食物类型名称获取属于该类型的食物信息
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日下午7:55:11
	 * @serverComment 
	 * @param 
	 */
	public List<Map<String, Object>> selectFoodByKind(String kindName); 
	
	/**
	 * 获取全部食物信息
	 * @author wenxian.cai
	 * @DateTime 2016年8月20日上午11:19:46
	 * @serverComment 
	 * @param 
	 */
	public List<TDataFood> selectAllFood(); 
	
	/**
	 * 返回全部食物类型
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日下午8:30:53
	 * @serverComment 
	 * @param 
	 */
	public List<TDataFoodKind> selectAllFoodKind(); 
	
	
	/**
	 * 添加病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午1:53:19
	 * @serverComment 
	 * @param object:实体
	 */
//	public Integer addMedical(HashMap<String, Object> map,Integer userId) throws Exception;
	
	/**
	 * 添加病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:14:40
	 * @serverComment 
	 * @param 病历id
	 */
//	public Integer addMedicalCourse(HashMap<String, Object> map,Integer medicalId) throws Exception;
	
	/**
	 * 添加体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午4:56:32
	 * @serverComment 
	 * @param 
	 */
	public Boolean addPhysicals(TRecordPhysicals recordPhysical) throws Exception;
	
	/**
	 * 添加饮食
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:48:10
	 * @serverComment 
	 * @param 
	 */
	public <T> Integer addDiet(HashMap<String, Object> map,Integer userId) throws Exception;
	
	/**
	 * 添加食物
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:48:25
	 * @serverComment 
	 * @param 饮食id
	 */
	public <T> Integer addDietFood(HashMap<String, Object> map,Integer dietId);
	
	/**
	 * 更新食物
	 * @author zhansi.Xu
	 * @DateTime 2016年9月27日
	 * @serverComment
	 */
	public <T> Integer updataDietFood(Map<String,Object> map,Integer id,Integer dietId);
	
	/**
	 * 修改病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午2:24:56
	 * @serverComment 
	 * @param 主键ID
	 */
//	public <T> Integer updataMedical(HashMap<String, Object> map,Integer Id) throws Exception;
	
	/**
	 * 修改病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:48:30
	 * @serverComment 
	 * @param 
	 */
//	public <T> Integer updataMedicalCourse(HashMap<String, Object> map,Integer Id) throws Exception;
	
	/**
	 * 修改体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午4:57:34
	 * @serverComment 
	 * @param 
	 */
	public Boolean updatePhysicals(TRecordPhysicals recordPhysicals) throws ParamException, IOException;
	
	/**
	 * 修改饮食
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:46:27
	 * @serverComment 
	 * @param 
	 */
	public boolean updataDiet(DietDTO dietDTO) throws Exception;
	 
	 /**
	 * 删除病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:51:49
	 * @serverComment 
	 * @param 
	 */
//	public <T> Integer deleteMedical(Integer id) throws Exception;
	
	/**
	 * 删除病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月8日下午3:51:58
	 * @serverComment 
	 * @param 
	 */
//	public <T> Integer deleteMedicalCourse(Integer id) throws Exception;
	
	/**
	 *  删除体检报告
	 *  @author wenxian.cai
	 *	@DateTime 2016年8月8日下午4:59:00
	 *
	 *	@param userId
	 *  @param reportId
	 *  @return
	 */
	public Integer deletePhysicals(Integer userId, Integer reportId);
	
	/**
	 * 删除饮食
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:40:56
	 * @serverComment 
	 * @param 主键id
	 */
	public <T> Integer deleteDiet(Integer id) throws Exception;
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2016年8月10日下午2:41:31
	 * @serverComment 
	 * @param 主键id
	 */
	public <T> Integer deleteDietFood(Integer id) throws Exception;
	
	public List<String> uploadPhoto(MultipartFile[] files) throws IOException;

	/**
	 * 获取指定月存在数据的日期
	 *
	 * @param userId 用户ID
	 * @param queryDate 查询当月的第一天,比如:2017-07-01.
	 * @param queryType 1->健康数据,2->体检报告,3->病例记录,4->饮食记录
	 * @return 结果集映射
	 * @author wuj
	 *
	 * @since 2017-07-28 12:03:48
	 */
	Map<String,Integer> queryDatesWithData(Integer userId, String queryDate, Integer queryType) throws DataBaseException;

	/**
	 * 获得用户最后一次的测量日期
	 *
	 * @param userId
	 * @return 日期,Format: "yyyy-MM-dd"
	 * @throws DataBaseException 查询不到用户则抛出
	 */
	 String getLastDateWithinData(Integer userId) throws DataBaseException;

	/**
	 * 获取用户最后一次存在饮食数据的日期
	 *
	 * @param userId
	 * @return
	 * @throws DataBaseException
	 */
	 String getLastDateForUserDietData(Integer userId);
}
