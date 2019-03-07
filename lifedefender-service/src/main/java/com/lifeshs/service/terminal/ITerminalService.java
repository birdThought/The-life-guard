package com.lifeshs.service.terminal;

import java.sql.Time;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserBlackWhiteList;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.entity.member.TUserOperationDetail;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.pojo.jsp.GpsModel;
import com.lifeshs.pojo.member.HealthDataDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.commond.ClearSMSDto;
import com.lifeshs.pojo.member.commond.ContactDto;
import com.lifeshs.pojo.member.commond.DataTestDto;
import com.lifeshs.pojo.member.commond.LocationDto;
import com.lifeshs.pojo.member.commond.LogDto;
import com.lifeshs.pojo.member.commond.ModeDefineDto;
import com.lifeshs.pojo.member.commond.ModnumDto;
import com.lifeshs.pojo.member.commond.MoniterDto;
import com.lifeshs.pojo.member.commond.PowerOffDto;
import com.lifeshs.pojo.member.commond.RunModeDto;
import com.lifeshs.pojo.member.commond.SendSMSDto;
import com.lifeshs.pojo.member.commond.TimerDto;
import com.lifeshs.pojo.page.PaginationDTO;

/**
 * 版权归 TODO 终端服务类
 * 
 * @author yuhang.weng
 * @DateTime 2016年6月20日 上午11:04:39
 */
public interface ITerminalService {

    /**
     * @author zhiguo.lin
     * @DateTime 2016年7月28日 下午5:45:19
     * @serverComment 获取用户已绑定、指定类型的设备
     *
     * @param userId
     * @param terminalType
     * @return
     * @throws Exception
     */
    public TUserTerminal getTerminal(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:41:32
     * @serverComment 发送SMS消息
     *
     * @param smsDto
     * @throws Exception
     */
    public int sendsms(SendSMSDto smsDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:41:46
     * @serverComment 提醒指令(闹钟)
     *
     * @param timerDto
     * @throws Exception
     */
    public int timer(TimerDto timerDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:42:15
     * @serverComment 监听指令
     *
     * @param moniterDto
     * @throws Exception
     */
    public int monitor(MoniterDto moniterDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:42:56
     * @serverComment 位置上传指令
     *
     * @param locationDto
     * @throws Exception
     */
    public int sendLocation(LocationDto locationDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午11:37:04
     * @serverComment 上传LOG(日志文件)
     *
     * @param logDto
     */
    public int sendLog(LogDto logDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午11:37:06
     * @serverComment 关机控制
     *
     * @param pDto
     * @throws Exception
     */
    public int sendPoweroff(PowerOffDto pDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:44:48
     * @serverComment 清除SIM卡中SMS内容
     *
     * @param cDto
     * @throws Exception
     */
    public int clearsms(ClearSMSDto cDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:45:17
     * @serverComment 通讯录、亲情号码、白名单、黑名单设置(一般在终端数据同步时设置)
     *
     * @param cDto
     * @throws Exception
     */
    public int contact(ContactDto cDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:46:23
     * @serverComment 测量指令
     *
     * @param dataTestDto
     * @throws Exception
     */
    public int datatest(DataTestDto dataTestDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:47:20
     * @serverComment 功能自定义(一般在终端数据同步时设置)
     *
     * @param mDto
     * @throws Exception
     */
    public int modedefine(ModeDefineDto mDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午9:47:20
     * @serverComment 运行模式
     *
     * @param mDto
     * @throws Exception
     */
    public int runMode(RunModeDto mDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月27日 上午11:47:41
     * @serverComment 修改心跳间隔、终端密码、服务中心号码、短信网关号码、服务访问地址
     *
     * @param modnumDto
     *            修改指令的数据
     * @throws Exception
     * @return 0表示成功 1表示失败
     */
    public int sendModifyMessage(ModnumDto modnumDto) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月17日 下午4:18:12
     * @serverComment 获取用户设备列表
     *
     * @param userId
     *            用户ID
     * @return
     */
    public List<TUserTerminal> geTUserTerminals(int userId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午2:41:34
     * @serverComment 设备绑定
     *
     * @param userId
     *            用户ID
     * @param imei
     *            串号
     * @param name
     *            备注名
     * @param mobile
     *            HL设备插入的手机卡号码
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public ServiceMessage bindTerminal(int userId, String imei, String name, String mobile, String terminalType)
            throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月17日 上午11:59:20
     * @serverComment 用户是否已绑定了与deivceType相同系列的设备
     *
     * @param userId
     *            用户ID
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean isUserBindSameTerminal(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午2:49:00
     * @serverComment HL设备解绑
     *
     * @param userId
     *            用户ID
     * @param imei
     *            串号
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean unBindTerminal(int userId, String imei, String terminalType) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月18日 上午10:02:23
     * @serverComment 获取设备的黑名单列表
     *
     * @param userId
     *            用户ID
     * @param terminalType
     *            HL03、C3
     * @return
     */
    public List<TUserBlackWhiteList> getDeviceBlackList(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月18日 上午10:52:57
     * @serverComment 黑名单列表是否已达上限(8条)
     *
     * @param userId
     *            用户ID
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean isBlackListLimited(int userId, String terminalType);

    /**
     * 黑名单号码是否存在
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月19日 上午9:53:09
     *
     * @param userId
     * @param mobile
     *            黑名单号码
     * @param terminalType
     *            设备类别
     * @return
     */
    public boolean isBlackListMobileExist(int userId, String mobile, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月18日 上午10:41:10
     * @serverComment 设备添加黑名单
     *
     * @param userId
     *            用户ID
     * @param mobile
     *            黑名单手机号码
     * @param name
     *            备注名称
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean addBlackList(int userId, String mobile, String name, String terminalType) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月18日 上午11:25:24
     * @serverComment 设备删除黑名单
     *
     * @param userId
     *            用户ID
     * @param mobileId
     *            黑名单手机号码ID
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean delBlackList(int userId, int mobileId, String terminalType) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午1:31:46
     * @serverComment 获取设备提醒列表
     *
     * @param userId
     * @param terminalType
     * @return
     */
    public List<TUserNotice> getTerminalNotices(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午2:28:45
     * @serverComment 设备提醒设置是否被限制
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public boolean isNoticeLimited(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午1:38:23
     * @serverComment 添加一项设备提醒
     *
     * @param userId
     * @param terminalType
     *            HL03、C3 如果为null表示该设备为健康包
     * @param weeks
     *            星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
     * @param time
     *            提醒时间
     * @param content
     *            提醒内容
     * @param intervalM
     *            重复间隔分钟数
     * @return
     * @throws Exception
     */
    public boolean addTerminalNotice(int userId, String terminalType, String weeks, String time, String content,
            Double intervalM) throws Exception;

    /**
     * 修改设备提醒
     * 
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午1:49:14
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param weeks
     *            星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
     * @param time
     *            提醒时间
     * @param content
     *            提醒内容
     * @param intervalM
     *            重复间隔
     * @param status
     *            1_开启、0_关闭
     * @param noticeId
     * @return
     * @throws Exception
     */
    public boolean modTerminalNotice(int userId, String terminalType, String weeks, String time, String content,
            Double intervalM, Integer status, int noticeId);

    /**
     * <p>
     * 切换提醒状态，开启与关闭
     * 
     * @author yuhang.weng
     * @DateTime 2016年7月28日 下午7:03:25
     *
     * @param userId
     * @param terminalType
     *            设备类型
     * @param noticeId
     * @return
     */
    public boolean toggleTerminalNotice(int userId, String terminalType, int noticeId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午1:46:59
     * @serverComment 删除设备提醒
     *
     * @param userId
     * @param noticeId
     * @param terminalType
     *            HL03、C3
     * @return
     */
    public boolean delTerminalNotice(int userId, int noticeId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月20日 下午3:04:55
     * @serverComment 查找设备提醒详细内容
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param noticeId
     * @return
     */
    public TUserNotice getTerminalNotice(int userId, String terminalType, int noticeId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月21日 上午9:30:10
     * @serverComment 获取设备模式设置内容
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public Map<String, Object> getTerminalOperationMode(int userId, String terminalType) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月21日 上午10:20:57
     * @serverComment 修改设备模式
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param detail
     * @return
     * @throws Exception
     */
    public boolean modTerminalOperationMode(int userId, String terminalType, TUserOperationDetail detail)
            throws Exception;

    /**
     * <p>
     * 获取用户勾选的健康包勾选参数的结果集
     * 
     * @author yuhang.weng
     * @DateTime 2016年6月17日 下午3:07:28
     *
     * @param userId
     * @return
     */
    public List<HealthPackageDTO> getUserHealthProductsList(Integer userId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月17日 下午3:39:55
     * @serverComment 勾选用户健康包
     *
     * @param userId
     * @param typeName
     *            健康包类型名称
     * @return
     * @throws Exception
     */
    public boolean addUserHealthProducts(int userId, String typeName);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月17日 下午3:41:41
     * @serverComment 取消用户健康包
     *
     * @param userId
     * @param typeName
     *            健康包类型名称
     * @return
     * @throws Exception
     */
    public boolean delUserHealthProducts(int userId, String typeName);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午2:10:54
     * @serverComment 获取用户健康包参数数值
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public UserRecordDTO getUserHealthPackageParams(int userId);

    /**
     * <p>
     * 检测用户健康数据是否完整
     * <p>
     * 用于体脂秤等健康包(需要使用到健康参数)绑定之前
     * 
     * @author yuhang.weng
     * @DateTime 2016年7月28日 上午10:53:59
     *
     * @param userId
     * @return
     */
    public boolean isUserHealthDataComplete(int userId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午2:23:58
     * @serverComment 设置用户健康参数
     *
     * @param user
     * @param age
     *            年龄
     * @return
     * @throws Exception
     */
    public boolean modifyUserHealthPackageParams(int userId, Boolean gender, Float height, Float weight, Float waist, Float bust, Float hip, int age);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月22日 上午10:08:34
     * @serverComment 检测健康数据所属的健康包是否已绑定
     *
     * @param healthType
     *            健康包名称
     * @param userHealthProduct
     *            用户健康包数据和
     * @return
     */
    public boolean isHealthDataBinded(String healthType, Integer userHealthProduct);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月22日 上午10:09:25
     * @serverComment 手动添加数据
     *
     * @param healthData
     *            健康数据
     * @param userId
     *            用户ID
     * @return
     * @throws Exception
     */
    public boolean addHealthData(HealthDataDTO healthData, int userId) throws OperationException;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月22日 下午2:32:07
     * @serverComment 获取设备监控频率
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @return
     * @throws Exception
     */
    public TUserTerminal getMoniterFrequency(int userId, String terminalType) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月22日 下午2:44:50
     * @serverComment 修改设备监控频率参数
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param heartFrequency
     *            心跳包频率（秒）
     * @param locationFrequency
     *            位置上传频率（秒）
     * @param autoFrequency70
     *            电量70%降频频率
     * @param autoFrequency50
     *            电量50%降频频率
     * @param autoFrequency30
     *            电量30%降频频率
     * @return
     * @throws Exception
     */
    public boolean modifyMoniterFrequency(int userId, String terminalType, int heartFrequency, int locationFrequency,
            int autoFrequency70, int autoFrequency50, int autoFrequency30) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月18日 下午4:59:15
     * @serverComment 获取电子围栏信息List数组
     *
     * @param userId
     * @param terminalType
     *            设备类型
     * @return
     */
    public List<TUserElectronicFence> getElectronicFences(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月19日 上午9:13:12
     * @serverComment 服务注解
     *
     * @param userId
     * @param number
     *            围栏编号
     * @param terminalType
     *            设备类型 HL-03 C3 ..
     * @return
     */
    public TUserElectronicFence getElectronicFence(int userId, int number, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月23日 下午7:12:11
     * @serverComment 添加一个新的围栏
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param fence
     *            电子围栏对象
     * @return
     * @throws Exception
     */
    public boolean addElectronicFence(int userId, String terminalType, TUserElectronicFence fence) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月23日 下午8:02:12
     * @serverComment 删除电子围栏
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param number
     *            围栏编号
     * @return
     * @throws Exception
     */
    public boolean delElectronicFence(int userId, String terminalType, int number) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月23日 下午7:23:03
     * @serverComment 检测用户设备电子围栏是否已达到设置上限，如果没有就返回新的围栏number
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @return 围栏number
     * @throws Exception
     */
    public int isElectronicFenceLimited(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月23日 下午7:34:55
     * @serverComment 修改电子围栏信息
     *
     * @param userId
     * @param terminalType
     *            HL03、C3
     * @param fence
     *            电子围栏对象
     * @return
     * @throws Exception
     */
    public boolean modifyElectronicFence(int userId, String terminalType, TUserElectronicFence fence);

    /**
     * <p>
     * 设置终端上的手机号码
     * 
     * @author dachang.luo
     * @DateTime 2016年6月29日 下午2:45:18
     *
     * @param userId
     * @param terminalType
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean setTerminalMobile(int userId, String terminalType, String mobile) throws Exception;

    /**
     * 获取设备运行模式的编号 家长-7，GPS-6，儿童-5，校园-4，上课-3，会议-2，飞行-1，正常-0
     * 
     * @author dachang.luo
     * @DateTime 2016年6月29日 下午5:57:48
     * @serverComment 服务注解
     *
     * @param userId
     * @param terminalType
     * @return
     * @throws Exception
     */
    public int getTerminalOperationModeNumber(int userId, String terminalType) throws Exception;

    /**
     * 设置设备的运行模式
     * 
     * @author dachang.luo
     * @DateTime 2016年6月29日 下午5:57:53
     * @serverComment 服务注解
     *
     * @param userId
     * @param terminalType
     * @return
     * @throws Exception
     */
    public boolean setTerminalOperationMode(int userId, String terminalType, int mode) throws Exception;

    /**
     * 获取监控轨迹
     * 
     * @author dachang.luo
     * @DateTime 2016年7月2日 下午3:54:26
     * @serverComment 服务注解
     *
     * @param userId
     * @param terminalType
     * @return
     * @throws Exception
     */
    public List<TUserMonitorTrack> getMonitorTrack(int userId, String terminalType) throws Exception;

    /**
     * 添加监控轨迹
     * 
     * @author dachang.luo
     * @DateTime 2016年7月4日 下午1:49:45
     * @serverComment 服务注解
     *
     * @param terminalType
     * @param number
     * @param name
     * @param startTime
     * @param endTime
     * @param enabled
     * @return
     * @throws Exception
     */
    public boolean addMonitorTrack(int userId, String terminalType, int number, String name, Time startTime,
            Time endTime, boolean enabled) throws Exception;

    /**
     * 修改监控轨迹
     * 
     * @author yuhang.weng
     * @DateTime 2016年7月2日 下午4:08:44
     * @serverComment 服务注解
     *
     * @param userId
     * @param terminalType
     * @param tracks
     * @return
     * @throws Exception
     */
    public boolean modifyMonitorTrack(int userId, String terminalType, List<TUserMonitorTrack> tracks) throws Exception;

    /**
     * 修改监控轨迹
     * 
     * @author dachang.luo
     * @DateTime 2016年7月23日 上午10:22:37
     * @serverComment 服务注解
     *
     * @param userId
     * @param terminalType
     * @param number
     * @param name
     * @param startTime
     * @param endTime
     * @param enabled
     * @return
     * @throws Exception
     */
    public boolean modifyMonitorTrack(int userId, String terminalType, int number, String name, Time startTime,
            Time endTime, boolean enabled) throws Exception;

    /**
     * 删除监控轨迹
     * 
     * @author dachang.luo
     * @DateTime 2016年7月2日 下午4:11:13
     * @serverComment 服务注解
     *
     * @param userId
     * @param trackId
     * @return
     * @throws Exception
     */
    public boolean deleteMonitorTrack(int userId, String terminalType, int number) throws Exception;

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月12日 上午10:47:41
     * @serverComment 获取GPS数据(lng,lat)
     *
     * @param userId
     * @param terminalType
     *            设备类型
     * @return
     */
    public GpsModel getGps(int userId, String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月22日 上午10:14:54
     * @serverComment 获取运动轨迹数据（精确到某一天的时间段）
     *
     * @param userId
     * @param terminalType
     *            设别类型
     * @param number
     *            轨迹编号
     * @param date
     *            日期
     * @return
     */
    public List<GpsModel> getOrbit(int userId, String terminalType, int number, String date);

    /**
     * <p>
     * 修改设备监控号码
     * 
     * @author yuhang.weng
     * @DateTime 2016年8月3日 下午4:13:19
     *
     * @param userId
     * @param terminalType
     * @param contactNumber
     *            监控号码
     * @return
     * @throws Exception
     */
    public boolean modifyTerminalMonitorContactId(int userId, String terminalType, String contactNumber)
            throws Exception;

    /**
     * 分页获取用户定位记录
     * 
     * @param userId
     * @param start
     * @param end
     * @return
     */
    public PaginationDTO getSportLocationRecord(int userId, int start, int end);

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月13日下午2:11:13
     * @serverComment 添加勾选设备短信预警
     * @param userId
     * @param typeName
     */
    public boolean addUserHealthWarning(int userId, String typeName) throws Exception;

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月13日下午2:15:53
     * @serverComment 取消设备短信预警
     * @param userId
     * @param typeName
     */
    public boolean delUserHealthWarning(int userId, String typeName) throws Exception;

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月30日下午3:17:26
     * @serverComment 发送位置指令
     * @param
     */
    public boolean sendElectronicFenceAndMonitorTrackCommond(TUserTerminal userTerminal);

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月30日下午3:19:09
     * @serverComment 发送提醒指令
     * @param
     */
    public boolean sendTerminalNoticeCommond(TUserTerminal userTerminal, TUserNotice userNotice, String op);

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月26日下午5:38:52
     * @serverComment 发送联系人指令
     * @param
     */
    public boolean sendContactCommond(TUserTerminal userTerminal);

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月30日下午3:19:50
     * @serverComment 发送运行模式指令
     * @param
     */
    public boolean sendRunModeCommond(TUserTerminal userTerminal) throws ParseException;

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月30日下午3:20:38
     * @serverComment 发送监听指令
     * @param
     */
    public boolean sendMonitorContactCommond(TUserTerminal userTerminal, String contactNumber) throws SMSException;

    /**
     * @author wenxian.cai
     * @DateTime 2016年12月30日下午3:16:32
     * @serverComment 发送关机指令
     * @param
     */
    public boolean sendPoweroffCommond(TUserTerminal userTerminal);
}
