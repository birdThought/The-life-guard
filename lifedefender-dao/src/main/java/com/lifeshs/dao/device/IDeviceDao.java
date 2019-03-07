package com.lifeshs.dao.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.vo.member.MemberC3Vo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.pojo.healthDevice.BloodLipidDTO;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.healthDevice.UaDTO;
import com.lifeshs.pojo.healthDevice.UranDTO;

/**
 * 设备Dao层
 * 
 * @author yuhang.weng
 * @DateTime 2016年7月27日 上午9:36:54
 */
@Repository("deviceDao")
public interface IDeviceDao {

    /**
     * 
     * @author zhiguo.lin
     * @DateTime 2016年7月27日 上午9:36:56
     * @serverComment 查询最后一条记录
     *
     * @param params
     * @return
     */
    <T> T selectDeviceDataLastestDate(Map<String, Object> params);

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月20日 下午3:57:53
     * @serverComment 精确到年月日的某天查询
     *
     * @param params
     *            参数只接收{"userId":"[userId]","measureDate":"[YYYY-mm-dd]",
     *            "deviceType":"deviceType"}
     * @return
     */
    <T> List<Map<String, Object>> selectDeviceDataWithSpecificDate(Map<String, Object> params);

    /**
     * @author wenxian.cai
     * @DateTime 2016年7月27日下午2:32:37
     * @serverComment 精确到年月日的某天查询
     * @param 参数只接收{"userId":"[userId]","date":"[YYYY-mm-dd]","deviceType":
     *            "deviceType"}
     */
    <T> List<Map<String, Object>> selectDeviceDataWithSpecificDateByDate(Map<String, Object> params);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月23日 下午3:26:47
     * @serverComment 服务注解
     *
     * @param params
     *            参数只接收{"userId":"[userId]","measureDate":"[YYYY-mm-dd]","start"
     *            :"[start]","pageSize":"[pageSize]"}
     * @return
     */
    <T> List<Map<String, Object>> selectDeviceDataWithSpecificDatePageSplit(Map<String, Object> params);

    /**
     * @author wenxian.cai
     * @DateTime 2016年7月25日上午10:24:34
     * @serverComment 服务注解
     * @param 参数只接收{"userId":"[userId]"}
     */
    <T> List<Map<String, Object>> selectDeviceAllData(Map<String, Object> params);

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月24日 上午11:14:45
     * @serverComment 按条件获取健康标准
     *
     * @param healthType
     *            健康参数
     * @param age
     *            用户年龄
     * @param sex
     *            用户性别
     * @return
     */
    Map<String, Object> selectDeviceDataStandard(Map<String, Object> params);

    /**
     * @author yuhang.weng
     * @DateTime 2016年5月26日 下午5:05:42
     * @serverComment 获取设备最新的数据
     *
     * @param entityName
     * @param userId
     * @return
     */
    Map<String, Object> selectDeviceDataLastest(Map<String, Object> params);

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月31日 下午3:43:35
     * @serverComment 查询未发送的指令
     *
     * @param imei
     * @param userId
     * @return
     */
    List<Map<String, Object>> findCommond(@Param("imei") String imei, @Param("userId") int userId);

    /**
     * 查询未发送的指令 不同的实现
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> findCommond(Map<String,Object> params);

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月31日 下午3:51:20
     * @serverComment 发送指令后更新
     *
     * @param imei
     * @param userId
     * @return
     */
    int updateCommond(@Param("imei") String imei, @Param("userId") int userId);

    /**
     * @author yuhang.weng
     * @DateTime 2016年6月16日 下午4:45:28
     * @serverComment 按照terminalType查询已绑定的设备记录
     *
     * @param imei
     *            串号
     * @param terminalType
     *            HL03、C3
     * @return
     */
    TUserTerminal selectDeviceIsBindingLimitType(@Param("imei") String imei,
                                                 @Param("terminalType") String terminalType);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月12日 上午11:11:06
     * @serverComment 按照测量时间倒序获取定位数据
     *
     * @param userId
     * @param productModel
     *            LCHB HL031 HL03 C3 ...
     *            (详细查看com.lifeshs.service.impl.terminal.TerminalType)
     * @param limit
     *            限制查询记录数量 (1..2..3..4...按需求填写)
     * @return
     */
    List<TSportLocation> selectLatestLocation(@Param("userId") int userId, @Param("productModel") String productModel,
                                              @Param("limit") int limit);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月22日 上午10:54:23
     * @serverComment 按照指定日期查询定位信息
     *
     * @param userId
     * @param productModel
     *            LCHB HL031 HL03 C3 ...
     *            (详细查看com.lifeshs.service.impl.terminal.TerminalType)
     * @param date
     *            查询日期
     * @return
     */
    List<TSportLocation> selectLocationByDate(@Param("userId") int userId, @Param("productModel") String productModel,
                                              @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月22日 上午11:25:09
     * @serverComment 查询运动轨迹的信息（通过orderParam对结果进行排序）
     *
     * @param deviceId
     *            设备id
     * @param orderParam
     *            排序条件
     * @param order
     *            ASC、DESC
     * @return
     */
    List<TUserMonitorTrack> selectTracksOrderByParam(@Param("deviceId") int deviceId,
                                                     @Param("orderParam") String orderParam, @Param("order") String order);

    /***************** 映射新建的单独xml Start ********************/
    /*
     * 分别获取Band,BloodPressure,BodyFatscale,Ecg,Glucometer,HeartRate,
     * LungInstrument,Oxygen,Temperature 全部、 一天、一周、一月、三月 的数据
     */
    List<Map<String, Object>> selectBandWithSplit(Map<String, Object> params);

    List<Map<String, Object>> selectBandWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectBandWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectBandWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectBandWithThreeMonth(Map<String, Object> params);

    TSportBand selectBandWithDate(@Param("userId") int userId, @Param("date") String date);
    
    /**
     * 按条件查询一天内最后一条tSportBand的数据
     * 
     * @author zhiguo.lin
     * @DateTime 2016年9月13日 下午7:00:47
     *
     * @param userId
     * @param date
     *            测量日期
     * @param terminalType
     *            终端类型
     * @return
     */
    Map<String, Object> selectLatestBandWithDate(@Param("userId") Integer userId, @Param("date") String date,
                                                 @Param("terminalType") String terminalType);

    /**
     * @Description: 获取最后一条数据
     * @author: wenxian.cai
     * @create: 2017/4/20 9:48
     */
    Map<String, Object> selectLatestBand(@Param("userId") Integer userId, @Param("terminalType") String terminalType);

    List<Map<String, Object>> selectBloodPressureWithSplit(Map<String, Object> params);

    List<Map<String, Object>> selectBloodPressureWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectBloodPressureWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectBloodPressureWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectBloodPressureWithThreeMonth(Map<String, Object> params);

    List<Map<String, Object>> selectBodyfatscaleWithSplit(Map<String, Object> params);

    List<Map<String, Object>> selectBodyfatscaleWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectBodyfatscaleWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectBodyfatscaleWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectBodyfatscaleWithThreeMonth(Map<String, Object> params);
    
    List<Map<String, Object>> selectBodyfatscaleWithYear(Map<String, Object> params);

    List<Map<String, Object>> selectGlucometerWithSplit(Map<String, Object> params);

    /**
     * 获取血糖仪特殊月份全部数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月23日下午2:21:05
     * @serverComment
     * @param
     */
    List<Map<String, Object>> selectGlucometerWithSpecialMonth(Map<String, Object> params);

    List<Map<String, Object>> selectGlucometerWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectGlucometerWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectGlucometerWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectGlucometerWithThreeMonth(Map<String, Object> params);

    List<Map<String, Object>> selectHeartRateWithSplit(Map<String, Object> params);

    /**
     * 获取心率特殊日期全部数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月23日下午3:14:59
     * @serverComment
     * @param
     */
    List<Map<String, Object>> selectHeartRateWithSpecialDate(Map<String, Object> params);

    List<Map<String, Object>> selectHeartRateWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectHeartRateWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectHeartRateWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectHeartRateWithThreeMonth(Map<String, Object> params);

    List<Map<String, Object>> selectLungInstrumentWithSplit(Map<String, Object> params);

    List<Map<String, Object>> selectLungInstrumentWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectLungInstrumentWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectLungInstrumentWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectLungInstrumentWithThreeMonth(Map<String, Object> params);

    List<Map<String, Object>> selectOxygenWithSplit(Map<String, Object> params);

    List<Map<String, Object>> selectOxygenWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectOxygenWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectOxygenWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectOxygenWithThreeMonth(Map<String, Object> params);

    List<Map<String, Object>> selectTemperatureWithSplit(Map<String, Object> params);

    /**
     * 获取特殊日期全部数据
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月23日下午3:04:47
     * @serverComment
     * @param
     */
    List<Map<String, Object>> selectTemperatureWithSpecialDate(Map<String, Object> params);

    List<Map<String, Object>> selectTemperatureWithDay(Map<String, Object> params);

    List<Map<String, Object>> selectTemperatureWithWeek(Map<String, Object> params);

    List<Map<String, Object>> selectTemperatureWithMonth(Map<String, Object> params);

    List<Map<String, Object>> selectTemperatureWithThreeMonth(Map<String, Object> params);

    /**
     * 分页获取心电数据
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月20日 下午2:58:44
     *
     * @param userId
     * @param deviceType
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<EcgDTO> listEcgWithPageSplit(@Param("userId") int userId, @Param("deviceType") String deviceType,
                                      @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     *  获取最近一天的心电数据
     *  @author yuhang.weng 
     *	@DateTime 2017年3月20日 下午4:34:16
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<EcgDTO> listEcgWithLatestDay(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     * 获取最近一周的心电数据
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月20日 下午3:01:25
     *
     * @param userId
     * @param deviceType
     * @return
     */
    List<EcgDTO> listEcgWithLatestWeek(@Param("userId") int userId, @Param("deviceType") String deviceType);

    /**
     * 获取最近一月的心电数据
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月20日 下午3:02:58
     *
     * @param userId
     * @param deviceType
     * @return
     */
    List<EcgDTO> listEcgWithLatestMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);

    /**
     * 获取最近三月的心电数据
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月20日 下午3:03:11
     *
     * @param userId
     * @param deviceType
     * @return
     */
    List<EcgDTO> listEcgWithLatestThreeMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取指定日期的心电数据
     *  @author yuhang.weng 
     *	@DateTime 2017年5月4日 下午3:20:52
     *
     *  @param userId 用户id
     *  @param deviceType 设备类型
     *  @param date 指定日期
     *  @return
     */
    List<EcgDTO> listEcgWithSpecialDate(@Param("userId") int userId, @Param("deviceType") String deviceType,
            @Param("date") Date date);
    
    /**
     *  统计心电数据
     *  @author yuhang.weng 
     *  @DateTime 2017年11月28日 下午5:12:30
     *
     *  @param userId 用户id
     *  @param deviceType 终端类型
     *  @param date 测量日期
     *  @param signType 标记类型
     *  @param rhythmId 心律id(可以不填写)
     *  @return
     */
    Integer countEcgWithDateAndSignType(@Param("userId") int userId, @Param("deviceType") String deviceType,
            @Param("date") Date date, @Param("signType") int signType, @Param("rhythmId") Integer rhythmId);
    
    List<EcgDTO> listEcgWithDateAndSignType(@Param("userId") int userId, @Param("deviceType") String deviceType,
            @Param("measureDate") String measureDate, @Param("signType") Integer signType, @Param("rhythmId") Integer rhythmId,
            @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
    
    /**
     *  添加心电数据
     *  @author yuhang.weng 
     *  @DateTime 2017年11月28日 上午10:38:35
     *
     *  @param ecg
     *  @return
     */
    int addEcg(EcgDTO ecg);
    
    /**
     *  添加心电数据详情
     *  @author yuhang.weng 
     *  @DateTime 2017年11月28日 上午10:38:22
     *
     *  @param ecgMeasureId 心电测量id
     *  @param detailList
     *  @return
     */
    int addEcgDetail(@Param("ecgMeasureId") int ecgMeasureId, @Param("detailList") List<EcgDetailDTO> detailList);
    
    /** 血脂仪 */
    /**
     *  分页获取血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月13日 上午9:30:24
     *
     *  @param userId
     *  @param deviceType
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<BloodLipidDTO> listBloodLipid(@Param("userId") int userId, @Param("deviceType") String deviceType,
                                      @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    /**
     *  获取最近一天的血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月13日 上午9:31:59
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<BloodLipidDTO> listBloodLipidWithLatestDay(@Param("userId") int userId, @Param("deviceType") String deviceType);

    /**
     *  获取最近一周的血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月25日 下午1:52:15
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<BloodLipidDTO> listBloodLipidWithLatestWeek(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近一周的血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月13日 上午9:32:15
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<BloodLipidDTO> listBloodLipidWithLatestMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近三个月的血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月18日 下午5:10:06
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<BloodLipidDTO> listBloodLipidWithLatestThreeMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  保存一条血脂仪数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月25日 下午4:38:56
     *
     *  @param bloodLipidDTO
     */
    void saveBloodLipid(BloodLipidDTO bloodLipidDTO);
    
    /**
     *  分页获取尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月18日 下午5:14:01
     *
     *  @param userId
     *  @param deviceType
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<UranDTO> listUran(@Param("userId") int userId, @Param("deviceType") String deviceType,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    /**
     *  获取最近一天的尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月18日 下午5:14:30
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UranDTO> listUranWithLatestDay(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近一周的尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月19日 上午11:07:41
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UranDTO> listUranWithLatestWeek(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近一月的尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月18日 下午5:14:47
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UranDTO> listUranWithLatestMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近三个月的尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月18日 下午5:15:03
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UranDTO> listUranWithLatestThreeMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  添加一条尿液分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月19日 上午11:46:46
     *
     *  @param uran
     */
    void saveUran(UranDTO uran);
    
    /**
     *  分页获取尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:56:24
     *
     *  @param userId
     *  @param deviceType
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<UaDTO> listUa(@Param("userId") int userId, @Param("deviceType") String deviceType,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    /**
     *  获取最近一天的尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:56:42
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UaDTO> listUaWithLatestDay(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近一周的尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:57:31
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UaDTO> listUaWithLatestWeek(@Param("userId") int userId, @Param("deviceType") String deviceType);
    
    /**
     *  获取最近一月的尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:57:46
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UaDTO> listUaWithLatestMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);

    /**
     *  获取最近三月的尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:58:09
     *
     *  @param userId
     *  @param deviceType
     *  @return
     */
    List<UaDTO> listUaWithLatestThreeMonth(@Param("userId") int userId, @Param("deviceType") String deviceType);

    /**
     *  保存尿酸分析仪的数据
     *  @author yuhang.weng 
     *	@DateTime 2017年4月24日 上午11:58:23
     *
     *  @param ua
     */
    void saveUa(UaDTO ua);
    
    /***************** 映射新建的单独xml End ********************/

    // /**
    // * 查询血糖健康标准值(血糖比较特殊，因此单独查询)
    // * 其中以sex字段作为餐前餐后判断，餐前为true、餐后为false
    // * @author zhiguo.lin
    // * @DateTime 2016年9月10日 下午1:35:07
    // *
    // * @return
    // */
    // List<Map<String,Object>> selectHealthStandardValueByGlucometer();
    //
    // /**
    // * 不区分性别获取健康标准值
    // * 包括：BMI、去脂体重、体年龄、血氧饱和度、体温、内脏脂肪
    // * @author zhiguo.lin
    // * @DateTime 2016年9月10日 下午5:38:40
    // *
    // * @return
    // */
    // List<Map<String,Object>> selectHealthStandardValueNotSex();

    /**
     * 按照日期获取设备数据总条数（日期相同的多条数据算一条）
     * 
     * @author wenxian.cai
     * @DateTime 2016年9月20日下午6:58:40
     * @serverComment
     * @param S
     */
    Integer selectDeviceDataCountByMeasureDate(Map<String, Object> params);

    /**
     * 按天获取测量数据，一天最多可显示24条数据
     * @param entity
     * @return
     */
    List<Map<String,Object>> selectDeviceDataByDayHour(Map<String, Object> entity);

    // 移动到IContactsDao
    // /**
    // * @author wenxian.cai
    // * @DateTime 2016年12月13日下午7:47:44
    // * @serverComment 更新预警接受信息联系人
    // * @param
    // */
    // int updateContactReceiveSms(Map<String, Object> map);

    /**
     *  获取C3用户量
     * @return
     */
    int findByC3Sum(@Param("condition")Integer condition, @Param("name")String Name,@Param("imei")String imei,
                    @Param("date")String date,@Param("status")Integer status);

    /**
     * 查询与获取C3用户列表与条件查询
     * @param Name
     * @param imei
     * @param date
     * @param status
     * @return
     */
    List<MemberC3Vo> findByDataList(@Param("condition")Integer condition,@Param("name")String Name, @Param("imei")String imei, @Param("date")String date,
                                    @Param("status")Integer status, @Param("curPage")Integer curPage, @Param("pageSize")Integer pageSize);


}
