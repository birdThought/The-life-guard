package com.lifeshs.service.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.TerminalType;

public interface IDeviceService {
     
    /**
     *  获取特定的健康标准值
     *  @author zhiguo.lin 
     *  @DateTime 2016年9月13日 上午9:56:26
     *
     *  @param userId
     *  @param healthTypes
     *  @return
     */
    public Map<String, Object> getHealthStandardValueByHealthType(Integer userId, List<HealthType> healthTypes);
    
    /**
     *  获取特定的健康标准值
     *  @author yuhang.weng 
     *  @DateTime 2016年12月24日 下午4:03:00
     *
     *  @param userId
     *  @param healthTypes
     *  @return
     */
    public Map<String, Object> getHealthStandardValueByHealthType2(Integer userId, List<HealthType> healthTypes);
    
    /**
     * 
     *  @author zhiguo.lin 
     *  @DateTime 2016年7月27日 上午9:38:04
     *  @serverComment 服务注解
     *
     *  @param params
     *  @return
     */
    public <T> T selectDeviceDataLastestDate(Class<T> entityName, int userId, String terminalType, String measure);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年5月20日 下午3:57:53
     *  @serverComment 精确到年月日的某天查询
     *
     *  @param entityName
     *  @param keys 获取结果字段
     *  @param params 参数只接收{"userId":"[userId]","measureDate":"[YYYY-mm-dd]"}
     *  @return
     */
    public <T> List<Map<String, Object>> selectDeviceDataWithSpecificDate(Class<T> entityName, List<String> keys,
            Map<String, Object> params);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月23日 下午3:23:02
     *  @serverComment 精确到年月日的某天查询(加入了分页查询功能)
     *
     *  @param entityName
     *  @param keys 获取结果字段
     *  @param params 参数只接收{"userId":"[userId]","measureDate":"[YYYY-mm-dd]"}
     *  @param pageSize 页面展示数据多少
     *  @param page 页码
     *  @return
     */
    public <T> List<Map<String, Object>> selectDeviceDataWithSpecificDatePageSplit(Class<T> entityName, List<String> keys, Map<String, Object> params, int pageSize, int page);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年5月24日 上午11:14:45
     *  @serverComment 按条件获取健康标准
     *
     *  @param healthType 健康参数
     *  @param age 用户年龄
     *  @param sex 用户性别
     *  @return
     */
    public Map<String, Object> selectDeviceDataStandard(String healthType, Integer age, Boolean sex);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月26日 下午4:35:10
     *  @serverComment 获取设备最新的数据(设备类型限定)
     *
     *  @param entityName
     *  @param userId
     *  @param terminalType 设备类型
     *  @return
     *  @throws Exception
     */
    public <T> T selectDeviceDataLastest(Class<T> entityName, int userId, String terminalType, String measureDate);
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月31日 下午3:43:35
     *  @serverComment 查询未发送的指令
     *
     *  @param imei
     *  @param userId
     *  @return
     */
    public List<Map<String, Object>> findCommond(String imei, int userId);

    public List<Map<String, Object>> findCommond(Map<String,Object> params);

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月31日 下午3:51:20
     *  @serverComment 发送指令后更新
     *
     *  @param imei
     *  @param userId
     *  @return
     */
    public int updateCommond(String imei, int userId);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月16日 下午4:03:01
     *  @serverComment 查询已绑定的设备记录
     *
     *  @param imei 串号
     *  @param terminalType HL03、C3
     *  @return
     */
    public TUserTerminal selectDeviceIsBinding(String imei, String terminalType);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月12日 上午11:20:04
     *  @serverComment 按照测量时间倒序获取定位数据
     *
     *  @param userId
     *  @param terminalType LCHB HL031 HL03 C3 ... (详细查看com.lifeshs.service.impl.terminal.TerminalType)
     *  @param limit 限制查询记录数量 (1..2..3..4...按需求填写)
     *  @return
     */
    public List<TSportLocation> findLatestGpsMessage(int userId, String terminalType, int limit);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月22日 上午11:20:21
     *  @serverComment 查询运动轨迹的信息（通过orderParam对结果进行排序）
     *
     *  @param deviceId 设备ID
     *  @param orderParam 排序条件
     *  @param type 0_ASC、1_DESC
     *  @return
     */
    public List<TUserMonitorTrack> findTracksOrderByParam(int deviceId, String orderParam, int type);
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月22日 上午10:34:35
     *  @serverComment 按照指定日期查询定位信息
     *
     *  @param userId
     *  @param terminalType LCHB HL031 HL03 C3 ... (详细查看com.lifeshs.service.impl.terminal.TerminalType)
     *  @param date 查询日期
     *  @return
     *  @throws Exception
     */
    public List<TSportLocation> findLocationByDateTime(int userId, String terminalType, String startTime, String endTime);
    
    /**
     *  获取设备指定日期，最后一条数据，并且限制该设备所属的设备类型是健康包</p>
     *  @author zhiguo.lin 
     *  @DateTime 2016年9月13日 下午5:38:28
     *  @serverComment 服务注解
     *
     *  @param userId 用户ID
     *  @param measureDate 测量日期
     *  @param devicesName 设备名称
     *  @param terminalType 设备类型
     *  @return
     */
    public Map<String, Object> getDeviceDataLatestByDateWithDeviceNameAndTerminalType(
            Integer userId, Date measureDate, List<Class<?>> devicesName, TerminalType terminalType);
    /**按照日期获取设备数据总条数（日期相同的多条数据算一条）
     * @author wenxian.cai
     * @DateTime 2016年9月20日下午7:12:18
     * @serverComment 
     * @param 
     */
    public <T> Integer selectDeviceDataCountByMeasureDate(Class<T> entityClass,Map<String, Object> params);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月14日上午11:59:01
     * @serverComment 获取短信预警项目
     * @param 
     */
    public List<HealthPackageDTO> getUserHealthWarningList(int userId);
    
//  移动到IContactsService
//  /**
//   * @author wenxian.cai
//   * @DateTime 2016年12月13日下午5:47:19
//   * @serverComment 修改接受短信联系人
//   * @param 
//   */
//  public ServiceMessage modifyContactreceiveSms(Map<String, Object> params);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月13日下午5:52:25
     * @serverComment 添加短信预警项目
     * @param 
     */
    public ServiceMessage addWarningDevice(Map<String, Object> params);
    
    /**
     * @author wenxian.cai
     * @DateTime 2016年12月14日上午9:51:57
     * @serverComment 删除短信预警项目
     * @param 
     */
    public ServiceMessage deleteWarningDevice(Map<String, Object> params);
}
