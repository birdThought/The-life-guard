package com.lifeshs.service.terminal.impl;

import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.model.map.Weather;
import com.lifeshs.common.model.map.Wifi;
import com.lifeshs.entity.device.*;
import com.lifeshs.pojo.C3.Lbs;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.member.commond.HL031Contact;
import com.lifeshs.pojo.mina.HLCommand;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.terminal.IHL03Terminal;
import com.lifeshs.utils.BaiduMapAPI;
import com.lifeshs.utils.DateTimeUtilT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *  HL03设备的功能类
 *  功能适配TerminalAdaptee，不适配的部分在此类自实现
 *  @author dengfeng  
 *  @DateTime 2016-5-20 下午03:29:47
 */
@Component("hL03Adapter")
public class HL03Adapter extends CommonServiceImpl implements IHL03Terminal{
    
    /**
     * 适配源，标准功能的对象
     */
    @Autowired
    private TerminalAdaptee terminal;
    
    @Autowired
    private IMemberService memberService;
    
    TerminalType terminalType = TerminalType.HL031;

    @Override
    public String login(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        TUserTerminal entity = getHL031(imei);
        
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
    public String heartpackge(HLCommand data) throws Exception {
        
        String[] metas = data.getParam().split(",");    // 将数据切割为元数据
        
        locationHandle(data.getImei(), 1, metas[1], metas[2], "DS");    // 基站地址解析并保存数据
        
        return returnNormalServerTCPData(data, "");
    }
    
    @Override
    public String measure(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        int userId = getHL031(imei).getUserId();
        String[] params = data.getParam().split(",");

        int dataType = Integer.valueOf(params[0]);  // 数据类型:1代表实时数据,2代表定时数据,3代表手动数据,4代表睡眠数据
        
        /** 
         *  此处的status表示健康参数的状态
         *  状态：正常_0,异常_项目和
         *  status只记录本次数据的健康参数状态
         *  假如本次操作为血压数据上传，其数据包含的参数有舒张压与收缩压两项结果
         *  其中舒张压对应的值为4，收缩压为2
         *  如果status的结果为4（二进制0100），表示舒张压异常
         *  如果status的结果为2（二进制0010），表示收缩压异常
         *  如果status的结果为6（二进制0110），表示舒张压与收缩压异常
         *  上面举例只是作为单项健康数据上传的处理结果，多数据上传存在数据隔阂，不会互相影响，即是说，status会重新初始化归0
         *  */
        Long status = 0l;
        
        Date measureDate = new Date(Long.parseLong(params[3]));
        String[] heartRates = null;
        String[] diastolics = null;
        String[] systolics = null;
        String[] temperatures = null;
        
        /** 1：心率  2：体温  3：血压  4：心电  0：所有  */
        switch (params[1]) {
        case "0":   // 所有：心率 | 心电? | 舒张压 | 收缩压 | 体温
            String[] metas = params[2].split("\\|");
            
            // 心率
            status = 0l; // 初始化status的值
            heartRates = metas[0].split("&");
            TMeasureHeartrate heartRate0 = new TMeasureHeartrate();
            heartRate0.setHeartRate(Integer.valueOf(heartRates[0]));
            if(Integer.valueOf(heartRates[1]) == 1) // 如果数据异常，status修改为健康数据对应的数值
                status = HealthType.heartRate.value();
            heartRate0.setStatus(status);
            heartRate0.setHeartRateArea(heartRates[2] + "-" + heartRates[3]);
            
            heartRate0.setCreateDate(new Date());
            heartRate0.setDeviceType(terminalType.getName());
            heartRate0.setMeasureDate(measureDate);
            heartRate0.setUserId(userId);
            heartRate0.setDataType(dataType);
            terminal.getHeartRate().save(heartRate0);
            
            // TODO 心电省略
            
            // 舒张压
            status = 0l; // 初始化status的值
            diastolics = metas[2].split("&");
            TMeasureBloodpressure bloodPressure0 = new TMeasureBloodpressure();
            bloodPressure0.setDiastolic(Integer.valueOf(diastolics[0]));
            status = 0l; // status归零
            if(Integer.valueOf(diastolics[1]) == 1) // 如果数据异常，status修改为健康数据对应的数值
                status = HealthType.diastolic.value();
            bloodPressure0.setDiastolicArea(diastolics[2] + "-" + diastolics[3]);
            // 收缩压
            systolics = metas[3].split("&");
            bloodPressure0.setSystolic(Integer.valueOf(systolics[0]));
            if(Integer.valueOf(systolics[1]) == 1)  // 如果数据异常，status修改为健康数据对应的数值
                status += HealthType.systolic.value();
            bloodPressure0.setStatus(status);
            bloodPressure0.setSystolicArea(systolics[2] + "-" + systolics[3]);
            
            bloodPressure0.setCreateDate(new Date());
            bloodPressure0.setDeviceType(terminalType.getName());
            bloodPressure0.setMeasureDate(measureDate);
            bloodPressure0.setUserId(userId);
            bloodPressure0.setDataType(dataType);
            terminal.getBloodPressure().save(bloodPressure0);
            
            // 体温
            status = 0l; // 初始化status的值
            temperatures = metas[4].split("&");
            TMeasureTemperature temperature0 = new TMeasureTemperature();
            temperature0.setTemperature(Float.valueOf(temperatures[0]));
            status = 0l; // 
            if(Integer.valueOf(temperatures[1]) == 1)   // 如果数据异常，status修改为健康数据对应的数值
                status = HealthType.temperature.value();
            temperature0.setStatus(status);
            temperature0.setTemperatureArea(temperatures[2] + "-" + temperatures[3]);
            
            temperature0.setCreateDate(new Date());
            temperature0.setDeviceType(terminalType.getName());
            temperature0.setMeasureDate(measureDate);
            temperature0.setUserId(userId);
            temperature0.setDataType(dataType);
            terminal.getTemperature().save(temperature0);
            
            break;
            
        case "1":   // 心率
            
            heartRates = params[2].split("&");
            TMeasureHeartrate heartRate1 = new TMeasureHeartrate();
            heartRate1.setHeartRate(Integer.valueOf(heartRates[0]));
            if(Integer.valueOf(heartRates[1]) == 1) // 如果数据异常，status修改为健康数据对应的数值
                status = HealthType.heartRate.value();
            heartRate1.setStatus(status);
            heartRate1.setHeartRateArea(heartRates[2] + "-" + heartRates[3]);
            
            heartRate1.setCreateDate(new Date());
            heartRate1.setDeviceType(terminalType.getName());
            heartRate1.setMeasureDate(measureDate);
            heartRate1.setUserId(userId);
            heartRate1.setDataType(dataType);
            terminal.getHeartRate().save(heartRate1);
            
            break;
            
        case "2":   // 体温
            
            temperatures = params[2].split("&");
            TMeasureTemperature temperature2 = new TMeasureTemperature();
            temperature2.setTemperature(Float.valueOf(temperatures[0]));
            if(Integer.valueOf(temperatures[1]) == 1)
                status = HealthType.temperature.value();
            temperature2.setStatus(status);
            temperature2.setTemperatureArea(temperatures[2] + "-" + temperatures[3]);
            
            temperature2.setCreateDate(new Date());
            temperature2.setDeviceType(terminalType.getName());
            temperature2.setMeasureDate(measureDate);
            temperature2.setUserId(userId);
            temperature2.setDataType(dataType);
            terminal.getTemperature().save(temperature2);
            
            break;
            
        case "3":   // 血压
            String[] metas3 = params[2].split("\\|");
            
            // 舒张压
            diastolics = metas3[0].split("&");
            TMeasureBloodpressure bloodPressure3 = new TMeasureBloodpressure();
            bloodPressure3.setDiastolic(Integer.valueOf(diastolics[0]));
            if(Integer.valueOf(diastolics[1]) == 1) // 如果数据异常，status在原有的基础上添加健康数据对应的数值
                status = HealthType.diastolic.value();
            bloodPressure3.setDiastolicArea(diastolics[2] + "-" + diastolics[3]);
            // 收缩压
            systolics = metas3[1].split("&");
            bloodPressure3.setSystolic(Integer.valueOf(systolics[0]));
            if(Integer.valueOf(systolics[1]) == 1)  // 如果数据异常，status在原有的基础上添加健康数据对应的数值
                status += HealthType.systolic.value();
            bloodPressure3.setStatus(status);
            bloodPressure3.setSystolicArea(systolics[2] + "-" + systolics[3]);
            
            bloodPressure3.setCreateDate(new Date());
            bloodPressure3.setDeviceType(terminalType.getName());
            bloodPressure3.setMeasureDate(measureDate);
            bloodPressure3.setUserId(userId);
            bloodPressure3.setDataType(dataType);
            terminal.getBloodPressure().save(bloodPressure3);
            
            break;
            
        case "4":   // 心电 TODO 省略
            
            break;
            
        }
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String poweroff(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        TUserTerminal hl031 = getHL031(imei);
//      String param = data.getParam(); // 1，关机原因，1代表正常关机，2代表电量不足
        // TODO 关机原因记录或处理
        
        hl031.setStatus(0);
        this.commonTrans.updateEntitie(hl031);
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String sleepofday(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        int userId = getHL031(imei).getUserId();
        String[] params = data.getParam().split(",");
        
        // 测量日期转换为Date对象
        Date date = DateTimeUtilT.dateWithoutForm(params[7]);
        
        TSportBand entity = new TSportBand();
        entity.setSteps(Integer.valueOf(params[0]));
        entity.setMileage(Integer.valueOf(params[1]));
        entity.setKcal(Integer.valueOf(params[2]));
        entity.setShallowDuration(Integer.valueOf(params[3]));
        entity.setDeepDuration(Integer.valueOf(params[4]));
        entity.setWakeupDuration(Integer.valueOf(params[5]));
        entity.setSleepDuration(Integer.valueOf(params[6]));
        
        entity.setUserId(userId);
        entity.setDeviceType(terminalType.getName());
        entity.setCreateDate(new Date());
        entity.setDate(date);
        
        terminal.getBand().save(entity);
        
        return returnNormalServerTCPData(data, "");
    }
    
    @Override
    public String receiveLocation(HLCommand data) throws Exception{
        String imei = data.getImei();
        
        String[] metas = data.getParam().split(",");
        
        int dataType = Integer.valueOf(metas[0]);   // 数据类型，1代表实时数据， 2代表定时数据，3代表手动数据
        String realLoca = "";
        switch (dataType) {
        case 1: realLoca = "DW"; break;
        case 2: realLoca = "DS"; break;
        case 3: realLoca = "SD"; break;
        }
        
        int locationType = Integer.valueOf(metas[1]);   // 1：LBS，2:wifi，3:gps
        
        locationHandle(imei, locationType, metas[2], metas[3], realLoca);   // 定位参数解析与保存
        
        return returnNormalServerTCPData(data, "");
    }

    @Override
    public String heightWeightAgeStepLength(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        TUserTerminal hl031 = getHL031(imei);
        UserRecordDTO recordDTO = memberService.getRecord(hl031.getUserId());
        
        Float height = recordDTO.getHeight();
        Float weight = recordDTO.getWeight();
        
        // 计算年龄
        Date birth = recordDTO.getBirthday();
        Calendar calendar = Calendar.getInstance();
        // 获取现在的年份
        int yearNow = calendar.get(Calendar.YEAR);
        // 将日期设置为生日那一天
        calendar.setTime(birth);
        int yearBirth = calendar.get(Calendar.YEAR);
        int age = yearNow - yearBirth;
        
        // TODO 获取步长
        int stepLength = 0;
        
        return returnNormalServerTCPData(data, height + "," + weight + "," + age + "," + stepLength);
    }

    @Override
    public String weather(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        String[] metas = data.getParam().split(",");
        
        // 获取经纬度
        TSportLocation location = locationHandle(imei, 1, metas[0], metas[1], "DW");
        
        // 获取该城市的天气详细 TODO 天气图片未定
        Weather weather = terminal.getLocation().gpsGetWeather(location.getLongitude(), location.getLatitude());
        
        String weatherMessage = weather.getMin() + "," + weather.getMax() + ","
                + weather.getContent() + "," + weather.getLog()
                + "," + weather.getPm() + "," + weather.getCityName();
        
        return returnNormalServerTCPData(data, weatherMessage);
    }

    @Override
    public String timer(HLCommand data) throws Exception {
        
        String imei = data.getImei();
        int userId = getHL031(imei).getUserId();
        String[] metas = data.getParam().split(",");
//      String[] intervals = metas[0].split("\\|");
        String[] heartRates = metas[1].split("\\|");
        String[] steps = metas[2].split("\\|");
        String[] mileages = metas[3].split("\\|");
        String[] kcals = metas[4].split("\\|");
        
//      /*
//       * 计算数据的具体测量时间，将数据封装成一个Map对象
//       * 
//      */
//      int int_h = Integer.valueOf(intervals[0]);  // 每隔int_h分钟采集一次心率
//      int int_s = Integer.valueOf(intervals[1]);  // 每隔int_s分钟采集一次计步
//      int upload = Integer.valueOf(intervals[2]); // 每upload分钟上传一次数据
//      int count_h = upload / int_h;
//      int count_s = upload / int_s;
//      for(int i = 0; i < heartRates.length; i++){
//          
//      }
        Date measureDate = new Date(Long.valueOf(metas[6]));
        /* 判断Date是否为一天中的23时，取23时测量的数据最后一条做保存 */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(measureDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour == 23){
            calendar.add(Calendar.HOUR_OF_DAY, 1);  // 增加一个小时变为24点
            TMeasureHeartrate heartrate = new TMeasureHeartrate();
            /* 获取最后一条数据进行处理 */
            // 心率
            heartrate.setHeartRate(Integer.valueOf(heartRates[heartRates.length - 1]));
            heartrate.setCreateDate(new Date());
            heartrate.setMeasureDate(calendar.getTime());
            heartrate.setDataType(2);   // 2_定时数据
            heartrate.setUserId(userId);
            heartrate.setDeviceType(terminalType.getName());
            terminal.getHeartRate().save(heartrate);
            // 计步数据 TODO 测量时间不做处理
            TSportBandStep bandStep = new TSportBandStep();
            bandStep.setSteps(steps[steps.length - 1]);
            bandStep.setMileage(mileages[mileages.length - 1]);
            bandStep.setKcal(kcals[kcals.length - 1]);
            bandStep.setCreateDate(new Date());
            bandStep.setDeviceType(terminalType.getName());
            bandStep.setUserId(userId);
            terminal.getBandStep().save(bandStep);
        }
        
        // 做一次定位处理
        locationHandle(imei, 1, metas[5], metas[6], "DS");
        
        return returnNormalServerTCPData(data, "");
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月16日 下午4:35:34
     *  @serverComment 获取已绑定的HL031设备(TCP)
     *
     *  @param imei
     *  @return
     */
    private TUserTerminal getHL031(String imei){
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
        int userId = getHL031(imei).getUserId();
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
                lbs.setLac(Integer.valueOf(minests[0]));    // 小区编号
                lbs.setCell(Integer.valueOf(minests[1]));   // 基站编号
                int signal = Double.valueOf((10 * Math.log(Double.valueOf(minests[2]) + 220))).intValue();  // 信号dBm 10*log(1256 + 220)
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
            
            // 示例 22:27:1d:20:08:d5&-55&|22:27:1d:20:08:d5&-86&
            
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
        
        TSportLocation location = new TSportLocation();
        location.setUserId(userId);
        location.setAddress(address);
        location.setRealLoca(realLoca);
        location.setLocationMode(mode);
        location.setCreateDate(new Date());
        location.setMeasureDate(new Date(Long.parseLong(dateSencond)));
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setProductModel(terminalType.getName());
        if(StringUtils.isNotBlank(address)){    // address为null或""说明定位失败，数据无须保存
            terminal.getLocation().save(location);
        }
        
        return location;
    }
    
    
    
    
    
    /* 服务器发送请求到终端 */
    
    @Override
    public String sendMeasureCommand(String imei, int typeNum) throws Exception {
        String command = "" + typeNum;
        return assembNormalReturnStr(imei, 3, command);
    }

    @Override
    public String sendLocationCommand(String imei) throws Exception {
        return assembNormalReturnStr(imei, 5, "");
    }

    @Override
    public String sendMoniterCommand(String imei, int second) throws Exception {
        String command = "" + second;
        return assembNormalReturnStr(imei, 7, command);
    }

    @Override
    public String sendTimerCommand(String imei, int heartRate, int step, int upload) throws Exception {
        String command = heartRate + "," + step + "," + upload;
        return assembNormalReturnStr(imei, 10, command);
    }

    @Override
    public String sendMessageCommand(String imei, int typeNum, String message, String terminalCode, int voiceSecond)
            throws Exception {
        String command = typeNum + "," + message.length() + "," + message
                + "," + terminalCode + "," + voiceSecond;
        return assembNormalReturnStr(imei, 12, command);
    }

    @Override
    public String sendPowerOffCommand(String imei) throws Exception {
        return assembNormalReturnStr(imei, 13, "");
    }

    @Override
    public String sendHealthStandardCommand(String imei, int hMin, int hMax, int tMin, int tMax, int dMin, int dMax,
            int sMin, int sMax, int eMin, int eMax) throws Exception {
        String command = hMin + "|" + hMax + "," + tMin + "|" + tMax
                + "," + dMin + "|" + dMax + "," + sMin + "|" + sMax
                + "," + eMin + "|" + eMax;
        return assembNormalReturnStr(imei, 15, command);
    }

    @Override
    public String sendServerAddressCommand(String imei, int ipAddress, int port) throws Exception {
        String command = ipAddress + "," + port;
        return assembNormalReturnStr(imei, 16, command);
    }

    @Override
    public String sendNoticeCommand(String imei, int type, int status, String recordId, String noticeTime, String weeks,
            String content) throws Exception {
        String command = type + "," + status + "," + recordId + "," + noticeTime
                + "," + weeks + "," + content;
        return assembNormalReturnStr(imei, 17, command);
    }

    @Override
    public String sendSMSCommand(String imei, String content, String mobile) throws Exception {
        String command = content + "," + mobile;
        return assembNormalReturnStr(imei, 18, command);
    }

    @Override
    public String sendContactCommand(String imei, List<HL031Contact> contacts) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (HL031Contact contact : contacts) {
            sb.append(contact.getRecordId() + "|" + contact.getNumber()
                + "|" + contact.getName()
                + "|" + contact.getType() + "|" + contact.getNumberType() + ",");
        }
        if(sb.length() > 0)
            sb = sb.deleteCharAt(sb.length() - 1);  // 去除最后一个字符
        return assembNormalReturnStr(imei, 19, sb.toString());
    }

    @Override
    public String sendSMSGatewayCommand(String imei, String gateway) throws Exception {
        String command = gateway;
        return assembNormalReturnStr(imei, 20, command);
    }

    @Override
    public String sendBlackListCommand(String imei, int commandType, List<String> numbers) throws Exception {

        StringBuffer sb = new StringBuffer();
        switch (commandType) {
        case 0:case 1:
            sb.append(commandType); break;
        case 2:
            for (String number : numbers) {
                sb.append(number + ",");
            }
            if(sb.length() > 0){
                sb = sb.deleteCharAt(sb.length() - 1);  // 去除最后一个字符
            }
            break;
        }
        
        return assembNormalReturnStr(imei, 21, sb.toString());
    }

    @Override
    public String sendHeightWeightAgeStepLengthCommand(String imei, int height, int weight, int age, int stepLength)
            throws Exception {
        String command = height + "," + weight + "," + age + "," + stepLength;
        return assembNormalReturnStr(imei, 22, command);
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
}
