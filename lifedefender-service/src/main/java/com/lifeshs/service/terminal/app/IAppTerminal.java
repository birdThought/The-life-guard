package com.lifeshs.service.terminal.app;

import net.sf.json.JSONObject;

/**
 * 
 *  面向上层的终端设备业务接口(APP, android和ios共用)
 *  @author dengfeng  
 *  @DateTime 2016-5-20 上午10:59:55
 */
public interface IAppTerminal {
	
	/**
	 *  app用户登录
	 *  @author dachang.luo 
	 *	@DateTime 2016年5月26日下午5:52:02 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject login(String json) throws Exception;
	
	/**
	 *  用户注册 
	 *  @author dachang.luo 
	 *	@DateTime 2016年5月27日上午9:46:39 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject register(String json) throws Exception;
	
	/**
	 *  发送手机验证码 
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月20日 上午9:52:27
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject sendVerifyCode(String json) throws Exception;
	
	/**
	 *  核对验证码
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月16日下午4:55:26
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject checkVerifyCode(String json)  throws Exception;

	/**
	 *  忘记密码 (找回密码)
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月20日 上午9:52:57
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setPasswod(String json) throws Exception;

	/**
	 *  修改手机号码
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月16日下午5:51:47 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyMobile(String json)  throws Exception;
	
	/**
	 *  修改密码
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月21日 下午8:22:58
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyPassword(String json) throws Exception;

	/**
	 *  获取用户信息
	 *  @author dachang.luo 
	 *	@DateTime 2016年5月27日上午9:52:01 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getUserInfo(String json) throws Exception;
	
	/**
	 *  更新用户信息 
	 *  @author dachang.luo 
	 *	@DateTime 2016年5月27日上午9:51:29 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 */
	public JSONObject modifyUserBaseInfo(String json) throws Exception;

	/**
	 *  修改用户身体数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月16日下午8:20:52 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 */
	public JSONObject modifyUserBodyInfo(String json);
	
	/**
	 *  获取用户的联系人列表（预警、亲情号）
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月17日上午9:33:37 
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getUserContacts(String json)throws Exception;
	
	/**
	 *  添加联系人
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月17日下午1:39:25
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addUserContacts(String json)throws Exception;

	/**
	 *  修改联系人
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月17日下午1:39:57
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyUserContacts(String json)throws Exception;
	
	/**
	 *  删除联系人
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月17日下午1:40:13
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject delUserContacts(String json)throws Exception;
	
	/**
	 *  获取用户的设备列表（含健康包勾选、HL/C3绑定的）
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日上午9:44:54
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getUserDevices(String json)throws Exception;
	
	/**
	 *  选择健康包设备
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日上午10:17:32
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setHealthDevice(String json)throws Exception;
	
	/**
	 *  绑定HL、C3等设备(同一设备一个用户只能绑定一个)
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日上午11:03:46
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject bindTerminal(String json)throws Exception;
	
	/**
	 *  解绑用户设备
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午2:31:31
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject unBindTerminal(String json)throws Exception;
	
	/**
	 *  上传血压数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午2:44:51
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addBloodPressure(String json)throws Exception;
	
	/**
	 *  获取血压数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午4:45:16
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getBloodPressure(String json)throws Exception;
	
	/**
	 *  上传血氧仪数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午4:52:08
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addOxygen(String json)throws Exception;

	/**
	 *  获取血氧数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午4:56:25
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getOxygen(String json)throws Exception;
	
	/**
	 *  上传血糖数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:16:28
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addGlucometer(String json)throws Exception;
	
	/**
	 *  获取体脂秤数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:23:40
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getGlucometer(String json)throws Exception;

	/**
	 *  获取肺活仪数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:23:40
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getLunginstrument(String json)throws Exception;
	
	/**
	 *  上传肺活仪数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:28:04
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addLunginstrument(String json)throws Exception;
	
	/**
	 *  上传体脂秤数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:28:04
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addBodyfatscale(String json)throws Exception;
	
	/**
	 *  获取体脂秤数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月18日下午5:23:40
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getBodyfatscale(String json)throws Exception;
	
	/**
	 * 	手环数据上传
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月20日 上午10:31:00
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addBand(String json)throws Exception;
	
	/**
	 *  获取手环数据
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月20日 上午10:31:43
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getBand(String json)throws Exception;
	
	/**
	 * 获取终端号码
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:12:09
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getTerminalMobile(String json)throws Exception;
	
	/**
	 * 设置终端号码
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:13:11
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setTerminalMobile(String json)throws Exception;
	
	/**
	 * 获取运行模式
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:18:40
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getRunMode(String json)throws Exception;

	/**
	 * 设置运行模式
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:18:56
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setRunMode(String json)throws Exception;

	/**
	 * 获取监控频率设置
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:19:07
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getFrequency(String json)throws Exception;

	/**
	 * 设置监控频率设置
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:19:28
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setFrequency(String json)throws Exception;

	/**
	 * 获取提醒设置列表
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:19:45
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getNotices(String json)throws Exception;

	/**
	 * 添加提醒
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:19:57
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addNotice(String json)throws Exception;

	/**
	 * 修改提醒
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:20:05
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyNotice(String json)throws Exception;

	/**
	 * 删除提醒
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:20:34
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject delNotice(String json)throws Exception;

	/**
	 * 获取黑名单
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:32:59
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getBlackList(String json)throws Exception;

	/**
	 * 设置黑名单
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:35:19
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setBlackList(String json)throws Exception;

	/**
	 * 删除黑名单
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:36:49
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject delBlackList(String json)throws Exception;

	/**
	 * 获取当前定位
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:37:13
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getlocation(String json)throws Exception;

	/**
	 * 设置实时监听
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:37:40
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject setMonitor(String json)throws Exception;

	/**
	 * 获取电子围栏
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:37:58
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getElectronicFence(String json)throws Exception;

	/**
	 * 添加电子围栏
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:38:07
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addElectronicFence(String json)throws Exception;

	/**
	 * 修改电子围栏
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:39:38
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyElectronicFence(String json)throws Exception;

	/**
	 * 删除电子围栏
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:39:48
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject delElectronicFence(String json)throws Exception;

	/**
	 * 获取监控轨迹
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:40:07
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getMonitorTrack(String json)throws Exception;

	/**
	 * 获取监控轨迹内容
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:40:14
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject getMonitorTrackContent(String json)throws Exception;

	/**
	 * 添加监控轨迹
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:40:34
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject addMonitorTrack(String json)throws Exception;

	/**
	 * 修改监控轨迹
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:40:51
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject modifyMonitorTrack(String json)throws Exception;

	/**
	 * 删除监控轨迹
	 *  @author dachang.luo 
	 *	@DateTime 2016年6月29日 上午10:41:16
	 *  @serverComment 服务注解
	 *
	 *  @param json
	 *  @return
	 *  @throws Exception
	 */
	public JSONObject delMonitorTrack(String json)throws Exception;
	
	/**
	 * 上传体温数据
	 * @author wenxian.cai
	 * @DateTime 2016年8月5日下午4:29:48
	 * @serverComment 服务注解 
	 * @param json
	 */
	public JSONObject addTemperature(String json) throws Exception;
	/**
	 * 获取体温数据
	 * @author wenxian.cai
	 * @DateTime 2016年8月4日上午11:43:18
	 * @serverComment 服务注解
	 * @param json
	 */
	public JSONObject getTemperature(String json)throws Exception;
	
	/**
	 * 获取健康范围值
	 * @author wenxian.cai
	 * @DateTime 2016年8月6日下午2:21:16
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getHealthArea(String json)throws Exception;
	
	/**
	 * 获取家庭成员列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午9:32:46
	 * @serverComment 
	 * @param json
	 */
	public JSONObject getFamilyMemberList(String json)throws Exception;
	
	/**
	 * 获取病历列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:34:40
	 * @serverComment 
	 * @param json
	 */
	public JSONObject getMedicalRecordsList(String json)throws Exception;
	/**
	 * 获取病历详细
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getMedicalRecords(String json)throws Exception;
	/**
	 * 添加个人病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addMedicalRecords(String json)throws Exception;
	/**
	 * 修改个人病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject modifyMedicalRecords(String json)throws Exception;
	/**
	 * 删除个人病历
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject delMedicalRecords(String json)throws Exception;
	/**
	 * 添加个人病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addMedicalCourse(String json)throws Exception;
	/**
	 * 修改个人病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject modifyMedicalCourse(String json)throws Exception;
	/**
	 * 删除个人病程
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject delMedicalCourse(String json)throws Exception;
	/**
	 * 获取体检报告列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getPhysicalsList	(String json)throws Exception;
	/**
	 * 获取体检报告详细
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getPhysicals(String json)throws Exception;
	/**
	 * 添加体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addPhysicals(String json)throws Exception;
	/**
	 * 修改体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject modifyPhysicals(String json)throws Exception;
	/**
	 * 删除体检报告
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject delPhysicals(String json)throws Exception;
	/**
	 * 获取健康日记详细
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getDiet(String json)throws Exception;
	/**
	 * 添加健康日记
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addDiet(String json)throws Exception;
	/**
	 * 修改健康日记(暂不实现)
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject modifyDiet(String json)throws Exception;
	/**
	 * 删除健康日记
	 * @author wenxian.cai
	 * @DateTime 2016年8月9日上午11:37:54
	 * @serverComment 
	 * @param 
	 */
	public JSONObject delDiet(String json)throws Exception;
	
	/**
	 * 心率数据上传
	 * @author wenxian.cai
	 * @DateTime 2016年8月11日下午7:05:31
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addHeartRate(String json)throws Exception;

	/**
	 * 添加新家庭成员
	 * @author wenxian.cai
	 * @DateTime 2016年8月15日下午6:01:25
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addNewFamilyMember(String json) throws Exception;

	/**
	 * 修改家庭成员信息
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日上午9:49:33
	 * @serverComment 
	 * @param 
	 */
	public JSONObject modifyFamilyMember(String json) throws Exception;

	/**
	 * 查找用户列表
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日上午10:39:25
	 * @serverComment 
	 * @param 
	 */
	public JSONObject queryUserList(String json) throws Exception;

	/**
	 * 验证用户
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日上午11:18:38
	 * @serverComment 
	 * @param 
	 */
	public JSONObject checkUserPassword(String json) throws Exception;

	/**
	 * 加入家庭成员
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日上午11:24:43
	 * @serverComment 
	 * @param 
	 */
	public JSONObject addFamilyMember(String json) throws Exception;

	/**
	 * 删除家庭成员
	 * @author wenxian.cai
	 * @DateTime 2016年8月16日下午1:49:21
	 * @serverComment 
	 * @param 
	 */
	public JSONObject delFamilyMember(String json) throws Exception;

	/**
	 * 获取全部食物类型
	 * @author wenxian.cai
	 * @DateTime 2016年8月20日上午10:25:44
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getFoodType(String json) throws Exception;

	/**
	 * 获取食物详细
	 * @author wenxian.cai
	 * @DateTime 2016年8月20日上午11:02:42
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getFood(String json) throws Exception;

	/**
	 * 获取全部设备数据
	 * @author wenxian.cai
	 * @DateTime 2016年8月23日下午3:58:08
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getAllData(String json) throws Exception;

	/**
	 * 获取心率数据
	 * @author wenxian.cai
	 * @DateTime 2016年8月24日下午7:15:41
	 * @serverComment 
	 * @param 
	 */
	public JSONObject getHeartRate(String json) throws Exception;
	

	
}
