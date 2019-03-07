package com.lifeshs.service.terminal.impl;

import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.model.map.Weather;
import com.lifeshs.common.model.map.Wifi;
import com.lifeshs.entity.device.*;
import com.lifeshs.pojo.C3.Lbs;
import com.lifeshs.pojo.member.commond.HeartRateInterval;
import com.lifeshs.pojo.member.commond.LCHBContact;
import com.lifeshs.pojo.mina.HLCommand;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.terminal.ILCHBTerminal;
import com.lifeshs.utils.BaiduMapAPI;
import com.lifeshs.utils.DateTimeUtilT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *  版权归
 *  TODO 梦时代适配器
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 上午11:33:15
 */
@Component
public class LCHBAdapter extends CommonServiceImpl implements ILCHBTerminal {

    /**
     * 适配源，标准功能的对象
     */
    @Autowired
    private TerminalAdaptee terminal;
    
    private static TerminalType terminalType = TerminalType.LCHB;

    @Override
    public String login(HLCommand data) throws Exception {
        String imei = data.getImei();
        TUserTerminal entity = getLCHB(imei);
        StringBuffer message = new StringBuffer();
        
        if(entity == null){ // 查找不到imei对应的设备
            message.append("0");
        }else{  // 登录成功
            message.append("1");
            // 修改终端状态为在线
            entity.setStatus(1);
            this.commonTrans.updateEntitie(entity);
        }
        message.append("," + DateTimeUtilT.dateTimeWithoutForm(new Date()));
        
        // TODO 如果ip地址修改了，就在此处向返回字符串中添加新的ip地址
        
        return returnNormalServerTCPData(data, message.toString());
    }

    @Override
    public String heartPackage(HLCommand data) throws Exception {
        
        String[] metas = data.getParam().split(",");    // 将数据切割为元数据
        
        locationHandle(data.getImei(), 1, metas[1], metas[2], "DS");    // 基站地址解析并保存数据
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String heartRate(HLCommand data) throws Exception {
        String[] metas = data.getParam().split(",");
        
        int dataType = Integer.valueOf(metas[0]);
        String[] heartRates = metas[1].split("\\|");
        Date date = gmt(metas[2]);
        
        int userId = getLCHB(data.getImei()).getUserId();
        
        /* TODO 目前只处理最后一条数据的信息 */
        TMeasureHeartrate heartrate = new TMeasureHeartrate();
        heartrate.setHeartRate(Integer.valueOf(heartRates[heartRates.length - 1]));
        heartrate.setDataType(dataType);
        heartrate.setMeasureDate(date);
        heartrate.setUserId(userId);
        heartrate.setCreateDate(new Date());
        heartrate.setDeviceType(terminalType.getName());
        terminal.getHeartRate().save(heartrate);
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String location(HLCommand data) throws Exception {

        String imei = data.getImei();
        String[] metas = data.getParam().split(",");
        
        int dataType = Integer.valueOf(metas[0]);
        int locationMode = 0;
        String realLoca = "";
        
        switch (dataType) {
        case 1:
            locationMode = 1;
            realLoca = "DW";
            break;
        case 2:
            locationMode = 1;
            realLoca = "DS";
            break;
        case 3:
            locationMode = 2;
            realLoca = "DW";
            break;
        case 4:
            locationMode = 3;
            realLoca = "DW";
        }
        
        locationHandle(imei, locationMode, metas[1], metas[2], realLoca);
        
        return returnNormalServerTCPData(data, "");
    }

    
    /**     
     *  @author yuhang.weng 
     *  @DateTime 2016年7月7日 下午2:27:30
     *  @serverCode 服务代码
     *  @serverComment 服务注解
     *  @param data
     *  @return
     *  @throws Exception    
     *  @see com.lifeshs.service.terminal.ILCHBTerminal#measure(com.lifeshs.pojo.mina.HLCommand)    
     */
    @Override
    public String measure(HLCommand data) throws Exception {
        
        String[] metas = data.getParam().split(",");
        
        int userId = getLCHB(data.getImei()).getUserId();
        Date measureDate = gmt(metas[6]);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(measureDate);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        
        if(hours == 23){
            
            String[] heartRates = metas[5].split("\\|");
            String[] steps = metas[1].split("\\|");
            String[] mileages = metas[2].split("\\|");
            String[] kcals = metas[3].split("\\|");
            
            // 心率
            TMeasureHeartrate heartRate = new TMeasureHeartrate();
            heartRate.setHeartRate(Integer.valueOf(heartRates[heartRates.length - 1]));
            heartRate.setMeasureDate(measureDate);
            heartRate.setUserId(userId);
            heartRate.setCreateDate(new Date());
            heartRate.setDataType(2);   // 2表示定时数据
            heartRate.setDeviceType(terminalType.getName());
            terminal.getHeartRate().save(heartRate);
            // 计步 TODO 测量时间不做处理
            TSportBandStep bandStep = new TSportBandStep();
            bandStep.setSteps(steps[steps.length - 1]);
            bandStep.setMileage(mileages[mileages.length - 1]);
            bandStep.setKcal(kcals[kcals.length - 1]);
            bandStep.setCreateDate(new Date());
            bandStep.setDeviceType(terminalType.getName());
            bandStep.setUserId(userId);
            terminal.getBandStep().save(bandStep);
        }
        
        String[] tls = metas[4].split("\\|");
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < tls.length; i++){
            if(i <= 3){
                sb.append(tls[i] + "|");
            }else{
                sb.append(tls[i]);
                if((i + 1 - 4 ) % 4 == 0){
                    sb.append("$");
                }else{
                    sb.append("&");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        locationHandle(data.getImei(), 1, sb.toString(), metas[6], "DS");
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String bloodPressure(HLCommand data) throws Exception {
        
        int userId = getLCHB(data.getImei()).getUserId();
        String[] metas = data.getParam().split(",");
        
        Date measureDate = gmt(metas[3]);
        
        TMeasureBloodpressure bloodPressure = new TMeasureBloodpressure();
        bloodPressure.setSystolic(Integer.valueOf(metas[0]));
        bloodPressure.setDiastolic(Integer.valueOf(metas[1]));
        bloodPressure.setHeartRate(Integer.valueOf(metas[2]));
        bloodPressure.setMeasureDate(measureDate);
        bloodPressure.setDataType(1);
        bloodPressure.setCreateDate(new Date());
        bloodPressure.setUserId(userId);
        bloodPressure.setDeviceType(terminalType.getName());
        
        terminal.getBloodPressure().save(bloodPressure);
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String weather(HLCommand data) throws Exception {
        String imei = data.getImei();
        String[] metas = data.getParam().split(",");
        
        // 获取经纬度
        TSportLocation location = locationHandle(imei, 1, metas[0], metas[1], "DW");
        
        // 获取该城市的天气详细 TODO 天气图片未定
        Weather weather = terminal.getLocation().gpsGetWeather(location.getLongitude(), location.getLatitude());
        weather.setLog("1");    // 1
        
        String weatherMessage = weather.getMin() + "," + weather.getMax() + ","
                + weather.getContent() + "," + weather.getLog()
                + "," + weather.getPm() + "," + weather.getCityName();
        
        return returnNormalServerTCPData(data, weatherMessage);
    }

    @Override
    public String powerOff(HLCommand data) throws Exception {
//      String message = data.getParam();   // TODO 关机原因，1代表正常关机，2代表电量不足
        TUserTerminal entity = getLCHB(data.getImei());
        entity.setStatus(0);
        this.commonTrans.updateEntitie(entity);
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String glucometer(HLCommand data) throws Exception {
        
        int userId = getLCHB(data.getImei()).getUserId();
        String[] metas = data.getParam().split(",");
        Date measureDate = gmt(metas[1]);
        
        TMeasureGlucometer glucometer = new TMeasureGlucometer();
        glucometer.setBloodSugar(Integer.valueOf(metas[0]).floatValue());
        glucometer.setMeasureDate(measureDate);
        glucometer.setCreateDate(new Date());
        glucometer.setDataType(1);  // 实时数据
        glucometer.setDeviceType(terminalType.getName());
        glucometer.setUserId(userId);
        terminal.getGlucometer().save(glucometer);
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String message(HLCommand data) throws Exception {
        
//      String[] metas = data.getParam().split(",");
        // TODO 数据未处理
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String memberPointFollowerHotData(HLCommand data) throws Exception {
        String message = "100,25,34";   // TODO 积分，关注数量，点赞
        return returnNormalServerTCPData(data, message);
    }

    @Override
    public String mainClockStylish(HLCommand data) throws Exception {
//      int style = Integer.valueOf(data.getParam());   // TODO 样式1
        return returnNormalServerTCPData(data, "");
    }

    
    /* 终端上传数据处理方法 */
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月16日 下午4:35:34
     *  @serverComment 获取已绑定的LCHB设备(TCP)
     *
     *  @param imei
     *  @return
     */
    private TUserTerminal getLCHB(String imei){
        return terminal.getDeviceService().selectDeviceIsBinding(imei, terminalType.getName());
    }
    
    /**
     *  <p>HL031的TCP请求返回结果
     *  <p>通过获取data数据中的固定部分，再对数据进行拼装即可获得返回结果字符串
     *  <p>如:{1:1:0:232322:39238239329:T8:1,2,3,4,5,6,7}其返回结果为{1:1:0:232322:39238239329:S8&lt;:message&gt;}
     *  <p>首先将data进行字符串按":"分割，保留前5个字符串数据，即下标为0、1、2、3、4，接着对下标为5的字符串数据进行substring处理
     *  <p>除去第一个字母T(T表示Terminal-终端)改为S，后面数字保留不变
     *  @author yuhang.weng 
     *  @DateTime 2016年7月1日 上午9:51:41
     *
     *  @param data
     *  @param message 返回信息
     *  @return
     */
    private String returnNormalServerTCPData(HLCommand data, String message){
        
        StringBuffer result = new StringBuffer("{");
        
        result.append(data.getTerminalType() + ":" + data.getVersionNum() + ":" + data.getEncryptionMode() + ":" + data.getiDCode() + ":" + data.getImei() + ":");
        String sn = data.getCommond().substring(1, data.getCommond().length());
        result.append("S" + sn);
        
        if(StringUtils.isNotEmpty(message)){
            result.append(":" + message);
        }
        result.append("}");
        return result.toString();
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月4日 下午4:50:27
     *
     *  @param imei 串号
     *  @param locationMode 测量类型 1_LBS、2_WIFI、3_GPS
     *  @param param 参数(需要解析)
     *  @param dateSencond 为1970年 1月1号凌晨到当前的总秒数
     *  @param realLoca 等于 DW 是实时定位、等于 SOS 是SOS定位信息，等于DS是定时上传定位的内容
     *  @return
     */
    private TSportLocation locationHandle(String imei, int locationMode, String param, String dateSencond, String realLoca) throws Exception{
        
        // 参数转换
        int userId = getLCHB(imei).getUserId();
        String address = "";
        double longitude = 0.0;
        double latitude = 0.0;
        int mode = 0;
        
        
        switch (locationMode) {
        case 1: // LBS
            mode = 3;
            
            // 示例：2|460|00|25|6212&3FB5&12B5&4321$6213&3FB6&12B6&4321
            
            // 参数构造
            String[] metas = param.split("\\|");
            int count = Integer.valueOf(metas[0]);  // 基站数据 数量2
            int mcc = Integer.valueOf(metas[1]);    // 国家代码 460
            int mnc = Integer.valueOf(metas[2]);    // 00 中国移动
            
            String[] temps = metas[4].split("\\$"); // [6212&3FB5&12B5&4321] [6213&3FB6&12B6&4321]
            
            List<Lbs> lbsList = new ArrayList<>();
            for(int i=0; i < count; i++){
                // 封装参数
                Lbs lbs = new Lbs();
                String[] minests = temps[i].split("&"); // [6212] [1234] [1256] [4321]
                lbs.setLac(Integer.parseInt(minests[0], 16));   // 小区编号
                lbs.setCell(Integer.parseInt(minests[1], 16));  // 基站编号
                int signal = Double.valueOf(-(10 * Math.log(Double.valueOf(minests[2]) + 220))).intValue(); // 信号dBm 10*log(1256 + 220)
                lbs.setSignal(signal);
                
                lbs.setMcc(mcc);
                lbs.setMnc(mnc);
                lbsList.add(lbs);
            }
            
            TSportLocation entity = terminal.getLocation().lbsGetGps(lbsList);
            if(entity != null){
                address = entity.getAddress();
                longitude = entity.getLongitude();
                latitude = entity.getLatitude();
            }
            
            break;
        case 2: // WIFI
            mode = 2;
            
            // 示例 22&27&1d&20&08&d5$-55|5c&63&bf&a4&bf&56$-86
            
            String[] metaws = param.split("\\|");
            List<Wifi> wifiList = new ArrayList<>();
            for (int i = 0; i < metaws.length; i++) {
                Wifi wifi = new Wifi();
                String mac = metaws[i].split("&")[0];   // mac
                wifi.setMac(mac);
                wifi.setSingal(Integer.valueOf(metaws[i].split("&")[1]));   // -55
                
                wifiList.add(wifi);
            }
            
            TSportLocation entityW = terminal.getLocation().wifiGetGps(wifiList);
            if(entityW != null){
                address = entityW.getAddress();
                longitude = entityW.getLongitude();
                latitude = entityW.getLatitude();
            }
            
            break;
        case 3: // GPS
            mode = 1;
            
            longitude = Double.valueOf(param.split("\\|")[0]);
            latitude = Double.valueOf(param.split("\\|")[1]);
            address = BaiduMapAPI.getAddress(String.valueOf(longitude), String.valueOf(latitude));
            break;
        }
        
        Date measureDate = gmt(dateSencond);
        
        TSportLocation location = new TSportLocation();
        location.setUserId(userId);
        location.setAddress(address);
        location.setRealLoca(realLoca);
        location.setLocationMode(mode);
        location.setCreateDate(new Date());
        location.setMeasureDate(measureDate);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setProductModel(terminalType.getName());
        if(StringUtils.isNotBlank(address)){    // address为null或""说明定位失败，数据无须保存
            terminal.getLocation().save(location);
        }
        
        return location;
    }

    
    /* 服务器发送指令 */
    
    
    @Override
    public String sendHeartRateCommand(String imei) throws Exception {
        return assembNormalReturnStr(imei, 5, "");
    }

    @Override
    public String sendLocationCommand(String imei) throws Exception {
        return assembNormalReturnStr(imei, 13, "");
    }

    @Override
    public String sendSOSCommand(String imei, String[] numbers) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (String number : numbers) {
            sb.append(number);
        }
        return assembNormalReturnStr(imei, 18, sb.toString());
    }

    @Override
    public String sendHeightWeightAgeStepLength(String imei, int height, int weight, int age, int stepLength)
            throws Exception {
        String command = age + "," + stepLength + "," + weight + "," + height;
        return assembNormalReturnStr(imei, 38, command);
    }

    @Override
    public String sendMessageCommand(String imei, int contentType, int contentLength, String content,
            String appAccountNumber, int second) throws Exception {
        String command = contentType + "," + contentLength + ","
            + content + "," + appAccountNumber + "," + second;
        return assembNormalReturnStr(imei, 49, command);
    }

    @Override
    public String sendHeartRateIntervalCommand(String imei, List<HeartRateInterval> intervals) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (HeartRateInterval interval : intervals) {
            sb.append(interval.toString() + ";");
        }
        if(intervals.size() > 0)
            sb = sb.deleteCharAt(sb.length() - 1);  // 将最后一个";"去掉
        return assembNormalReturnStr(imei, 8, sb.toString());
    }

    @Override
    public String sendHeartRateHealthStandardCommand(String imei, int min, int max) throws Exception {
        String command = min + "," + max;
        return assembNormalReturnStr(imei, 52, command);
    }

    @Override
    public String sendIpAddressAndPort(String imei, String ip, int port) throws Exception {
        String command = ip + "," + port;
        return assembNormalReturnStr(imei, 4, command);
    }

    @Override
    public String sendWatchNoticeCommand(String imei, int noticeType, int status, String[] noticeTime)
            throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(noticeType + "," + status + ",");
        if(noticeTime != null){ // 如果值为null，表示这次是一个关闭提醒的指令
            for (String time : noticeTime) {
                sb.append(time + "|");
            }
            sb.deleteCharAt(sb.length() - 1);
        }else{
            sb.append("");
        }
        return assembNormalReturnStr(imei, 53, sb.toString());
    }

    @Override
    public String sendContactCommand(String imei, List<LCHBContact> contacts) throws Exception {
        StringBuffer sb = new StringBuffer();
        if(contacts != null){
            for (LCHBContact contact : contacts) {
                sb.append(contact.toString() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return assembNormalReturnStr(imei, 57, sb.toString());
    }
    
    /**
     *  <p>组织一般的返回串，用于服务器发出的命令
     *  <p>组串的内容包含{终端类型:版本号:加密方式：识别码：imei：命令:命令返回数据}
     *  <p>如：{2:1:0:154601:01234567890123456789012345678900:S3:111,222,333}
     *  @author yuhang.weng 
     *  @DateTime 2016年7月1日 下午3:36:19
     *
     *  @param imei 串号
     *  @param commandNum 命令编号[详细查看 平台接口规范(HL031 TCP).doc文档]
     *  @return
     */
    private String assembNormalReturnStr(String imei, int commandNum, String command){
        String result = null;
        
        if(StringUtils.isEmpty(command)){
            result = "{2:1:0:" + DateTimeUtilT.timeWithoutForm(new Date()) + ":" + imei + ":S" + commandNum + "}";
        }else{
            result = "{2:1:0:" + DateTimeUtilT.timeWithoutForm(new Date()) + ":" + imei + ":S" + commandNum + ":" + command + "}";
        }
        return result;
    }
    
    /**
     *  <p>LCHB设备默认市区为GMT，服务器默认时区为GMT+8
     *  <p>需要通过调用该方法获取正确的GMT+8时间
     *  @author yuhang.weng 
     *  @DateTime 2016年7月11日 下午2:25:15
     *
     *  @param seconds 1970年 1月1号凌晨到当前的总秒数
     *  @return
     */
    private Date gmt(String seconds){
        Date now = new Date(Long.valueOf(seconds + "000"));
        int rowOffSet = TimeZone.getTimeZone("GMT+8").getRawOffset() - TimeZone.getTimeZone("GMT").getRawOffset();
        return new Date(now.getTime() - rowOffSet);
    }
}
