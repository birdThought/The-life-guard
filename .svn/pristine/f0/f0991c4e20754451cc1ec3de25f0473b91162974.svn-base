package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.map.Gps;
import com.lifeshs.common.model.map.Weather;
import com.lifeshs.common.model.map.Wifi;
import com.lifeshs.dao.device.IDeviceDao;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.pojo.C3.Lbs;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.fence.impl.FenceServiceImpl;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.BaiduMapAPI;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.PositionUtil;
import com.lifeshs.utils.SMSCommand;
import com.lifeshs.utils.Toolkits;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 定位设备操作类
 * 
 * @author dengfeng
 * @DateTime 2016-5-23 下午04:33:51
 */
@Component
public class LocationService extends CommonServiceImpl {

    private static final Logger logger = Logger.getLogger(LocationService.class);

    @Autowired
    private FenceServiceImpl fenceService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private IContactsService contactsService;

    @Autowired
    private IDeviceDao iDeviceDao;

    @Autowired
    private IMemberDao memberDao;

    /**
     * 设备类型
     */
    HealthPackageType deviceType = HealthPackageType.Location;

    private CacheType locationCache = CacheType.LOCATION;

    private CacheType fenceCache = CacheType.SEND_FENCEWARNING_SMS;

    final static VcodeTerminalType vcodeTerminalType = VcodeTerminalType.USER_PLATFORM;

    /**
     * 数据持久化对象 构造器注入，由service注入
     * 
     * @param commonTrans
     * 
     */
    // public Location(ICommonTrans commonTrans) {
    // this.commonTrans = commonTrans;
    // }

    /**
     * @author zhiguo.lin
     * @DateTime 2016年8月1日 上午9:27:34
     * @serverComment
     *
     * @param userId
     * @param productModel
     * @param limit
     * @return
     */
    public List<TSportLocation> selectLatestLocation(int userId, String productModel, int limit) {
        return iDeviceDao.selectLatestLocation(userId, productModel, limit);
    }

    /**
     * 保存提交的设备数据
     * 
     * @author dengfeng
     * @DateTime 2016-5-13 下午03:08:31
     * @param
     *            值对象
     * @return
     * @throws SMSException 
     * @throws OperationException
     */
    public int save(TSportLocation entity) throws OperationException, SMSException {
        if (entity == null) {
            return -1;
        }
        // realLoca 等于 DW 是实时定位、等于 SOS 是SOS定位信息，等于DS是定时上传定位的内容
        String realLoca = entity.getRealLoca();
        Double lng = entity.getLongitude();
        Double lat = entity.getLatitude();

        String address = entity.getAddress();

        UserDTO user = memberDao.getUser(entity.getUserId());

        // 转换坐标系
        Map<String, Object> resultMap = BaiduMapAPI.gpsFormatBaidu(String.valueOf(lng), String.valueOf(lat));
        entity.setLatitude(Double.valueOf(resultMap.get("lat").toString()));
        entity.setLongitude(Double.valueOf(resultMap.get("lng").toString()));
        entity.setAddress(resultMap.get("address").toString());

        // 如果与上一次提交的定位点（可以放缓存）差不多（如不超过20M），则本次提交丢弃。
        Gps oldGps = (Gps) cacheService.getCacheValue(locationCache, String.valueOf(entity.getUserId()));

        Gps gps = new Gps(entity.getLatitude(), entity.getLongitude());
        if (oldGps != null) {
            double distance = PositionUtil.getDistance(oldGps, gps);
            if (distance < 40 && realLoca.equalsIgnoreCase("DS")) {
                return 0;
            }
        }
        cacheService.saveKeyValue(locationCache, String.valueOf(entity.getUserId()), gps);
        int result = commonTrans.save(entity);
        // 如果是SOS定位则发送短信(根据缓存判断是否为平台的实时定位获取)
        if ((realLoca.equalsIgnoreCase("SOS") || realLoca.equalsIgnoreCase("DW"))
                && (cacheService.getCacheValue(CacheType.LOCATION, user.getId() + "currentLocation") == null)) {
            sendSMS("", entity.getLocationMode(), realLoca, String.valueOf(lng), String.valueOf(lat), address, user);
        }
        // 清除平台实时定位缓存
        cacheService.delCacheValue(CacheType.LOCATION, user.getId() + "currentLocation");
        // 查询用户设备
        List<TUserTerminal> listDevice = commonTrans.findByProperty(TUserTerminal.class, "userId", user.getId());
        int userDeviceId = 0;
        if (listDevice != null) {
            for (TUserTerminal ud : listDevice) {
                if (entity.getProductModel().equals(ud.getTerminalType())) {
                    userDeviceId = ud.getId();
                    break;
                }
            }
        }
        // 出入栏判断
        outFence(userDeviceId, entity.getMeasureDate(), user, lng, lat, entity.getLocationMode(), realLoca, address);
        return result;
    }

    /**
     * 电子围栏设置
     * 
     * @author dengfeng
     * @DateTime 2016-5-23 下午04:38:41
     *
     * @param entity
     */
    public void electronicFence(TUserElectronicFence entity) throws Exception {
        commonTrans.save(entity);
    }

    /**
     * 监控轨迹设置
     * 
     * @author dengfeng
     * @DateTime 2016-5-23 下午04:43:41
     *
     * @param entity
     */
    public void monitorTrack(TUserMonitorTrack entity) throws Exception {
        commonTrans.save(entity);
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月1日 下午6:01:21
     * @serverComment 判断用户是否超出围栏,超出短信预警
     *
     * @param userDeviceId
     * @param datetime
     *            数据上传时间
     * @param user
     *            用户对象
     * @param lat
     *            标准的GPS纬度
     * @param lat
     *            标准的GPS纬度
     * @return
     */
    private boolean outFence(int userDeviceId, Date datetime, UserDTO user, double lng, double lat, int locaType,
            String realLoca, String address) {
        boolean bool = false;
        if (cacheService.getCacheValue(fenceCache, user.getUserName()) != null) {
            return false;
        }
        List<TUserElectronicFence> fenceList = fenceService.findFenceByList(userDeviceId);// 查询启用的电子围栏
        StringBuffer smsInfo = new StringBuffer("通知：" + user.getUserName());
        String outNum = "", inNum = "";
        Set<String> mobile = new HashSet<String>();
        for (TUserElectronicFence ef : fenceList) {
            // 判断是否在时间内
            if (inTime(ef, datetime)) {
                // 预警方式1：入栏报警，2：出栏报警、3：出入报警
                Integer warningType = ef.getWarningType();
                int radius = ef.getRadius() == null ? 0 : ef.getRadius();// 电子围栏的半径
                Gps fencePoint = new Gps(ef.getLatitude(), ef.getLongitude());// 电子围栏
                Gps newPoint = null;
                // G:谷歌
                if ("G".equals(ef.getMapType())) {
                    newPoint = PositionUtil.gps84_To_Gcj02(lat, lng);
                } else {
                    // gps转百度 调用百度的API
                    String lanLat = BaiduMapAPI.gpsFormatBaidu(String.valueOf(lng), String.valueOf(lat), false);
                    if (StringUtils.isNotBlank(lanLat)) {
                        String ll[] = lanLat.split(",");
                        newPoint = new Gps(Double.parseDouble(ll[1]), Double.parseDouble(ll[0]));
                    }
                }
                double distance = PositionUtil.getDistance(fencePoint, newPoint); // 两点之间的距离
                // 半径加上5是加大范围防止误差
                if (distance > (radius + 5)) {// 半径外面
                    if (warningType == 2) {
                        outNum += ef.getNumber() + "、";
                        mobile.add(ef.getWarningPhone());
                    }
                } else {// 半径里面
                    if (warningType == 1) {
                        inNum += ef.getNumber() + "、";
                        mobile.add(ef.getWarningPhone());
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(inNum)) {
            inNum = inNum.substring(0, inNum.length() - 1);
            smsInfo.append(" 进入" + inNum + "号电子围栏的监控范围");
            if (StringUtils.isBlank(outNum)) {
                smsInfo.append("请留意。");
            } else {
                smsInfo.append("，");
            }
        }
        if (StringUtils.isNotBlank(outNum)) {
            outNum = outNum.substring(0, outNum.length() - 1);
            smsInfo.append(" 超出" + outNum + "号电子围栏的监控范围，请留意。");
        }
        if (StringUtils.isNotBlank(inNum) || StringUtils.isNotBlank(outNum)) {
            String mobiles = "";
            // 写入短信内容 smsInfo
            for (String mob : mobile) {
                // 判断不能为空并且是手机号
                if (StringUtils.isNotBlank(mob) && Toolkits.verifyPhone(mob)) {
                    mobiles += mob + ",";
                }
            }
            if (StringUtils.isNotBlank(mobiles)) {
                mobiles = mobiles.substring(0, mobiles.length() - 1);
                cacheService.saveKeyValue(fenceCache, user.getUserName(), mobiles);
                try {
                    smsService.send(user.getId(), vcodeTerminalType, mobiles, SMSCommand.OUTFENCE, false);
                } catch (OperationException e) {
                    logger.error(e.getMessage());
                } catch (SMSException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        logger.info("电子围栏=" + smsInfo);
        return bool;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月13日 上午10:58:18
     * @serverCode 判断是否在时间内
     *
     * @param ef
     * @param datetime
     * @return
     */
    private boolean inTime(TUserElectronicFence ef, Date datetime) {
        if (ef.getEnabled() == null || ef.getEnabled().length() != 3 || ef.getEnabled().equals("000"))
            return false;
        char[] enabled = ef.getEnabled().toCharArray();
        if (enabled[0] == '1' && ef.getStartTime1() != null && ef.getEndTime1() != null) {
            return DateTimeUtilT.inTime(datetime, ef.getStartTime1(), ef.getEndTime1());
        }
        if (enabled[1] == '1' && ef.getStartTime2() != null && ef.getEndTime2() != null) {
            return DateTimeUtilT.inTime(datetime, ef.getStartTime2(), ef.getEndTime2());
        }
        if (enabled[2] == '1' && ef.getStartTime3() != null && ef.getEndTime3() != null) {
            return DateTimeUtilT.inTime(datetime, ef.getStartTime3(), ef.getEndTime3());
        }
        return false;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月1日 下午5:18:16
     * @serverComment 实时定位发短信通知定位者、sos
     *
     * @param smsNum
     *            发送定位短信的号码(实时定位有效)
     * @param locaType
     *            定位类型
     * @param realLoca
     * @param lng
     * @param lng
     * @param address
     *            地理位置
     * @param userId
     *            用户Id
     * @return
     * @throws SMSException
     * @throws OperationException 
     */
    private boolean sendSMS(String smsNum, Integer locaType, String realLoca, String lng, String lat, String address,
            UserDTO user) throws SMSException, OperationException {
        boolean bool = false;

        if (locaType == 1) { // GPS_1,WIFI_2,SIM_3*/
            if ("DW".equalsIgnoreCase(realLoca)) {// 实时定位
                if (StringUtils.isBlank(smsNum)) {
                    smsNum = contactsService.findFirstFamily(user.getId());
                }
                if (StringUtils.isNotBlank(smsNum)) {
                    address = address + "。" + BaseDefine.SYS_URL + "/releaseControl.do?showMap&lng=" + lng + "&lat="
                            + lat;
                    smsService.send(user.getId(), vcodeTerminalType, smsNum.trim(), SMSCommand.SSDWGPS, false, user.getUserName(), address);
                }
            } else if ("SOS".equalsIgnoreCase(realLoca)) {// 求助
                smsNum = contactsService.findFamilySos(user.getId());
                smsService.send(user.getId(), vcodeTerminalType, smsNum, SMSCommand.SOSGPS, false, user.getMobile(), address);
            }
        } else {
            if ("DW".equalsIgnoreCase(realLoca)) {// 实时定位
                if (StringUtils.isBlank(smsNum)) {
                    smsNum = contactsService.findFirstFamily(user.getId());
                }
                if (StringUtils.isNotBlank(smsNum)) {
                    address = address + "。" + BaseDefine.SYS_URL + "/releaseControl.do?showMap&lng=" + lng + "&lat="
                            + lat;
                    smsService.send(user.getId(), vcodeTerminalType, smsNum.trim(), SMSCommand.SSDW, false, user.getUserName(), address);
                }
            } else if ("SOS".equalsIgnoreCase(realLoca)) {// 求助
                smsNum = contactsService.findFamilySos(user.getId());
                smsService.send(user.getId(), vcodeTerminalType, smsNum, SMSCommand.SOS, false, user.getMobile(), address);
            }
        }
        return bool;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年6月14日 下午5:23:10
     * @serverCode lbs获取Gps
     *
     * @param lbsList
     * @return
     */
    public TSportLocation lbsGetGps(List<Lbs> lbsList) {
        TSportLocation lacal = null;
        if (lbsList != null) {
            List<Map<String, Object>> lbsListMap = new ArrayList<Map<String, Object>>();
            for (Lbs lbs : lbsList) {
                Map<String, Object> lbsMap = new HashMap<String, Object>();
                lbsMap.put("cell_id", lbs.getCell());// 基站ID
                lbsMap.put("lac", lbs.getLac());// 大区好
                lbsMap.put("mcc", lbs.getMcc());// mcc国家代码
                lbsMap.put("mnc", lbs.getMnc());// 运营商代码
                lbsMap.put("signalstrength", lbs.getSignal());// 信号
                lbsListMap.add(lbsMap);
            }
            Map<String, Object> lbsCsMap = new HashMap<String, Object>();
            lbsCsMap.put("celltowers", lbsListMap);
            lbsCsMap.put("mnctype", "gsm");
            String resGps = BaiduMapAPI.lbsCoord(lbsCsMap, "2");
            JSONObject jsonObj = JSONObject.fromObject(resGps);
            String errCode = jsonObj.getString("ErrCode");
            if ("0".equals(errCode)) {
                JSONObject location = jsonObj.getJSONObject("location");
                String lng = location.getString("longitude");
                String lat = location.getString("latitude");
                String address = location.getString("addressDescription");
                // 保存定位信息数据
                lacal = new TSportLocation();
                lacal.setLongitude(Double.parseDouble(lng));
                lacal.setLatitude(Double.parseDouble(lat));
                lacal.setAddress(address);
                lacal.setLocationMode(3);
                lacal.setCreateDate(new Date());
            }
        }
        return lacal;
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月5日 上午11:44:10
     * @serverComment wifi获取GPS经纬度，地址
     *
     * @param wifiList
     * @return
     */
    public TSportLocation wifiGetGps(List<Wifi> wifiList) {
        TSportLocation lacal = null;
        if (wifiList != null) {
            List<Map<String, Object>> wifiListMap = new ArrayList<>();
            for (Wifi wifi : wifiList) {
                Map<String, Object> wifiMap = new HashMap<>();
                wifiMap.put("macaddress", wifi.getMac());
                wifiMap.put("singalstrength", wifi.getSingal());
                wifiListMap.add(wifiMap);
            }
            Map<String, Object> wifiData = new HashMap<>();
            wifiData.put("wifilist", wifiListMap);
            wifiData.put("mnctype", "gsm");

            // 同lbs
            String result = BaiduMapAPI.wifiCoord(wifiData, "2");
            JSONObject jsonObj = JSONObject.fromObject(result);
            String errCode = jsonObj.getString("ErrCode");
            if ("0".equals(errCode)) {
                JSONObject location = jsonObj.getJSONObject("location");
                String lng = location.getString("longitude");
                String lat = location.getString("latitude");
                String address = location.getString("addressDescription");
                // 保存定位信息数据
                lacal = new TSportLocation();
                lacal.setLongitude(Double.parseDouble(lng));
                lacal.setLatitude(Double.parseDouble(lat));
                lacal.setAddress(address);
                lacal.setLocationMode(3);
                lacal.setCreateDate(new Date());
            }
        }

        return lacal;
    }

    /**
     * @author yuhang.weng
     * @DateTime 2016年7月5日 下午2:38:48
     * @serverComment 通过GPS的经纬度获取天气对象
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @return
     */
    public Weather gpsGetWeather(double lng, double lat) {
        Weather weather = new Weather();

        String result = BaiduMapAPI.weather(lng, lat);
        JSONObject jsonObj = JSONObject.fromObject(result);
        String errCode = jsonObj.getString("error");
        if (errCode.equals("0")) {
            JSONArray results = jsonObj.getJSONArray("results");
            JSONObject res = results.getJSONObject(0);

            weather.setCityName(res.getString("currentCity"));
            weather.setPm(Double.valueOf(res.getDouble("pm25")).floatValue());

            JSONArray weather_data = res.getJSONArray("weather_data");
            JSONObject now = JSONObject.fromObject(weather_data.get(0)); // 获取第一个json对象(今天的天气)
            weather.setContent(now.getString("weather")); // 天气描述

            // "temperature": "33 ~ 26℃"
            // 去除"℃"符号
            // 将数据分隔成两段分别填值到weather对象中
            String temperatures = now.getString("temperature");
            temperatures = temperatures.replace("℃", "");
            String[] temperature = temperatures.split(" ~ ");
            weather.setMin(Float.valueOf(temperature[1]));
            weather.setMax(Float.valueOf(temperature[0]));
        }

        return weather;
    }
}
