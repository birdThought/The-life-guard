//package com.lifeshs.service.terminal;
//
//import java.util.List;
//import java.util.Map;
//
///**
// *  版权归
// *  TODO   健康数据服务类
// *  @author wenxian.cai 
// *  @datetime 2016年7月14日下午5:36:23
// */
//public interface IHealthDataService {
//
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月14日下午5:47:39
//	 * @serverComment 获取具体日期段血压数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getBloodPressureByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月18日下午1:46:09
//	 * @serverComment 获取具体日期段肺活仪数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getLunginstrumentByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月18日下午1:46:51
//	 * @serverComment 获取具体日期段血糖仪数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getGlucometerByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月18日下午1:47:14
//	 * @serverComment 获取具体日期段血氧仪数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getOxygeneByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月18日下午1:47:45
//	 * @serverComment 获取具体日期段心率手环心率数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getHeartRateByDatediff(String userId,String date,String deviceType);
//	
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月27日上午10:38:51
//	 * @serverComment 获取具体日期段心率手环的计步和睡眠信息
//	 * @param 
//	 */
//	public List<Map<String, Object>> getBandByDatediff(String userId,String date,String deviceType);
//	
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月26日下午4:09:22
//	 * @serverComment 获取具体日期段心率手环计步数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getBandStepByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月26日下午4:10:53
//	 * @serverComment 获取具体日期段心率手环睡眠数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getBandSleepByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月18日下午1:48:00
//	 * @serverComment 获取具体日期段体脂秤数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getBodyfatscaleByDatediff(String userId,String date,String deviceType);
//	
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年8月3日下午5:54:20
//	 * @serverComment 获取具体日期段体温计数据
//	 * @param 
//	 */
//	public List<Map<String, Object>> getTemperatureByDatediff(String userId,String date,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取血压计的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getBloodPressureByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取肺活仪的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getLunginstrumentByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取血糖仪的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getGlucometerByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取血氧仪的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getOxygeneByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取心率的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getHeartRateByPage(String userId,int pageSize,int page,String deviceType);
//	
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月29日下午3:34:57
//	 * @serverComment 获取心率手环的计步、睡眠数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码,deviceType:设备类型
//	 */
//	public List<Map<String, Object>> getBandByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年7月20日下午7:51:02
//	 * @serverComment 获取体脂秤的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码
//	 */
//	public List<Map<String, Object>> getBodyfatscaleByPage(String userId,int pageSize,int page,String deviceType);
//	/**
//	 * @author wenxian.cai
//	 * @DateTime 2016年8月3日下午6:00:47
//	 * @serverComment 获取体温计的全部数据，并进行分页
//	 * @param pageSize:每一页的数据量,page:页码,deviceType:设备类型
//	 */
//	public List<Map<String, Object>> getTemperatureByPage(String userId,int pageSize,int page,String deviceType);
//	
//}
