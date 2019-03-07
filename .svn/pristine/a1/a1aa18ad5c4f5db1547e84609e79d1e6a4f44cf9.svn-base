package com.lifeshs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *  版权归
 *  百度地图工具类
 *  @author duosheng.mo  
 *  @DateTime 2016年6月1日 上午10:02:59
 */
public class BaiduMapAPI {
    
    private static final Logger logger = Logger.getLogger(BaiduMapAPI.class);
    
    private static String baidu_ak = "";
    private static String haosercice_key = "";
        
    static {
        PropertiesUtil pu = new PropertiesUtil("sysConfig.properties");
        baidu_ak = pu.readProperty("baidu.ak");
        haosercice_key = pu.readProperty("haosercice.key");
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年6月1日 上午10:10:50
     *  @serverComment 获取地理位置
     *
     *  @param lng (百度坐标)
     *  @param lat (百度坐标)
     *  @return
     */
    public static String getAddress(String lng, String lat){
        String address = "";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=" + baidu_ak +"&location=" + lat + "," + lng + "&output=json&pois=0";
        String str = openUrl(url);
        if(StringUtils.isNotEmpty(str)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.fromObject(str);
            } catch (JSONException e) {
                logger.error("获取结果失败", e);
                return "";
            }
            JSONObject result = jsonObject.getJSONObject("result");
            address = result.getString("formatted_address");
        }
        return address;
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月31日 下午5:56:04
     *  @serverComment 根据两点的经纬度获取两点间的距离
     *
     *  @param lng_s 开始经度
     *  @param lat_s 开始纬度
     *  @param lng_e 结束经度
     *  @param lat_e 结束纬度
     *  @return
     */
    public static Double getDistance(Double lng_s,Double lat_s,Double lng_e,Double lat_e){
        Double distance = null;
        String pathurl = "http://api.map.baidu.com/telematics/v3/distance?waypoints="+lng_s+","+lat_s+";"+lng_e+","+lat_e+"&output=json&ak="+baidu_ak;
        String str = openUrl(pathurl);
         if(StringUtils.isNotEmpty(str)) {
            JSONObject jsonObject = JSONObject.fromObject(str);
            net.sf.json.JSONArray jsonArray = jsonObject.getJSONArray("results");
            String juli = jsonArray.getString(0);
            distance= Double.valueOf(juli);
         }
        return distance;
    }
    
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2015-6-8 上午11:11:12
     *  @serverCode 服务代码
     *  @serverComment gps坐标转百度坐标、获取地址
     *
     *  @param lng
     *  @param lat
     *  @param getAddress  true 获取地理位置
     *  @return
     */
    public static String gpsFormatBaidu(String lng, String lat, boolean getAddress){
        String result = "";
        String uri = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="+lng+"&y="+lat+ "&output=json";
        String str = openUrl(uri);
        if(StringUtils.isNotEmpty(str)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.fromObject(str);
            } catch (JSONException e) {
                logger.error("获取结果失败", e);
                return "";
            }
            String state = jsonObject.getString("error");
            if("0".equals(state)){
                lng = getFromBASE64(jsonObject.getString("x"));
                lat = getFromBASE64(jsonObject.getString("y"));
                if(getAddress){
                    result = getAddress(lng,lat);
                }else{
                    result = lng+","+lat;
                }
            }
        }
    return result;
    
    }
    
    /**
     *  @author df
     *  @DateTime 2017-1-5 上午11:11:12
     *  @serverCode 服务代码
     *  @serverComment gps坐标转百度坐标、获取地址
     *
     *  @param lng
     *  @param lat
     *  @param getAddress  true 获取地理位置
     *  @return
     */
    public static Map<String, Object> gpsFormatBaidu(String lng, String lat){
        Map<String, Object> result = null;
        String uri = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="+lng+"&y="+lat+ "&output=json";
        String str = openUrl(uri);
        if(StringUtils.isNotEmpty(str)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.fromObject(str);
            } catch (JSONException e) {
                logger.error("获取结果失败", e);
                return null;
            }
            String state = jsonObject.getString("error");
            if("0".equals(state)){
                lng = getFromBASE64(jsonObject.getString("x"));
                lat = getFromBASE64(jsonObject.getString("y"));
                String address = getAddress(lng,lat);
                result = new HashMap<>();
                result.put("lng", lng);
                result.put("lat", lat);
                result.put("address", address);
            }
        }
    return result;
    
    }
  
    /**
     *  @author duosheng.mo 
     *  @DateTime 2015-6-8 上午11:33:52
     *  @serverCode 服务代码
     *  @serverComment 将 BASE64 编码的字符串 s 进行解码
     *
     *  @param str
     *  @return
     */
    public static String getFromBASE64(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(str);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *  @author duosheng.mo 
     *  @DateTime 2015-6-18 上午11:41:18
     *  @serverComment LBS坐标转换(HaoService 多基站wifi混合定位)
     *
     *  @param lbsMap lbs数据信息
     *  @param type 0(google坐标),1( 百度坐标),2(gps坐标)
     *  @return json 字符串
     */
    public static String lbsCoord(Map<String,Object> lbsMap, String type){
        String result = "";
        JSONObject jsonObj = JSONObject.fromObject(lbsMap);
        String uri = "http://api.haoservice.com/api/viplbs?requestdata=" + jsonObj;
        uri = uri + "&type=" + type+"&key="+haosercice_key;
        result = openUrl(uri);
        return result;
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2015-6-18 上午11:41:18
     *  @serverComment LBS坐标转换(HaoService 移动联通基站定位)
     *
     *  @param lbsMap lbs数据信息
     *  @param type 0(google坐标),1( 百度坐标),2(gps坐标)
     *  @return json 字符串
     */
    public static String lbsCoordOfGsm(Map<String,Object> lbsMap, String type){
        String result = "";
        JSONObject jsonObj = JSONObject.fromObject(lbsMap);
        String uri = "http://api.haoservice.com/getlbs?mcc="+lbsMap.get("mcc")+"&mnc="+lbsMap.get("mnc")+"&cell_id="+lbsMap.get("cell_id")+"&lac="+lbsMap.get("lac");
        uri = uri + "&type=" + type+"&key="+haosercice_key;
        result = openUrl(uri);
        return result;
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月5日 上午11:39:02
     *  @serverComment Wifi 数据转换坐标
     *
     *  @param wifiMap wifi数据信息
     *  @param type 0(google坐标),1( 百度坐标),2(gps坐标)
     *  @return json 字符串
     */
    public static String wifiCoord(Map<String, Object> wifiMap, String type){
        String result = "";
        JSONObject jsonObj = JSONObject.fromObject(wifiMap);
        String uri = "http://api.haoservice.com/api/viplbs?requestdata=" + jsonObj;
        uri += "&type=" + type + "&key=" + haosercice_key;
        result = openUrl(uri);
        return result;
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年7月5日 下午2:26:58
     *  @serverComment 通过经纬度查询天气信息
     *
     *  @param lng 经度
     *  @param lat 纬度
     *  @return
     */
    public static String weather(double lng, double lat){
        String result = "";
        String uri = "http://api.map.baidu.com/telematics/v3/weather?location=" + lng + "," + lat + "&output=json&ak=" + baidu_ak;
        result = openUrl(uri);
        return result;
    }
    
    public static String weather(String cityName) throws Exception {
        
//      cityName = java.net.URLEncoder.encode(java.net.URLEncoder.encode(cityName, "UTF-8"), "UTF-8");
        
        String result = "";
        String uri = "http://api.map.baidu.com/telematics/v3/weather?location=cityName&output=json&ak=" + baidu_ak;
        
        uri = uri.replace("cityName", cityName);
        
        result = openUrl(uri);
        return result;
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月31日 下午5:27:02
     *  @serverComment 打开url
     *
     *  @param url
     *  @return
     */
    public static String openUrl(String pathUrl){
         String result = "";
         PrintWriter out = null;
         BufferedReader in = null;
         HttpURLConnection connection = null;
        try {
            URL url = new URL(pathUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(20000);  
            connection.setReadTimeout(300000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=OCqxMF6-JxtxoMDHmoG5W5eY9MGRsTBp");//
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());
//          out.print(pathUrl.split("?")[1]);
            InputStream inputStream = connection.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String contents;
            StringBuilder sb = new StringBuilder("");
            while((contents = in.readLine())!=null){
                sb.append(contents.trim());
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {  
            try {
                // 断开连接
                connection.disconnect();
                out.flush();
                out.close(); 
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } 
        return result;
    }
    
    
    
  
public static void main(String[] args) throws Exception {
      //广东省深圳市福田区泰然九路香蜜湖街道村仔红松大厦西北
//      String address = BaiduMapAPI.gpsFormatBd("113.407115","23.189642",true);
//        System.out.println("address="+address);
//        Double distance = BaiduMapAPI.getDistance(106.47627586276933,29.48696504600049,
//                                                   106.57132032153478,29.61241578003655);
          //907666.96483972
//        System.out.println("两点间距离："+distance);
//       
//       Double distance2 = BaiduMapAPI.getDistance(new Gps(29.48696504600049,106.47627586276933),
//                  new Gps(29.61241578003655,106.57132032153478));
//       System.out.println("两点间距离："+distance2);
      
      
//   String str = getAddress("113.2776", "23.12858");
//   System.out.println("address="+str);
//  'lat':23.127952,'lng':113.278842,'
//  lng=113.2776,lat=23.12858,address=广东省广州市越秀区较场西路大塘街道青菜岗,国美电器中华广场商城店西北34米,较场西路西30米
    
//  String res = BaiduMapAPI.gpsFormatBaidu("113.278842", "23.127952", true);
//  System.out.println("address="+res);
    
//
//    List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//    Map<String,String> lbsMap = new HashMap<String,String>();
//
//    lbsMap.put("cell_id", "39065");//基站ID
//    lbsMap.put("lac",  "9533");//大区好
//    lbsMap.put("mcc",  "460");//国家代码
//    lbsMap.put("mnc",  "1");//运营商代码
//    lbsMap.put("signalstrength",  "132");//信号
//    list.add(lbsMap);
//    lbsMap = new HashMap<String,String>();
//
//    lbsMap.put("cell_id", "39581");//基站ID
//    lbsMap.put("lac",  "9533");//大区好
//    lbsMap.put("mcc",  "460");//国家代码
//    lbsMap.put("mnc",  "1");//运营商代码
//    lbsMap.put("signalstrength",  "134");//信号
//    list.add(lbsMap);
//    lbsMap = new HashMap<String,String>();
//
//    lbsMap.put("cell_id", "39062");//基站ID
//    lbsMap.put("lac",  "9533");//大区好
//    lbsMap.put("mcc",  "460");//国家代码
//    lbsMap.put("mnc",  "1");//运营商代码
//    lbsMap.put("signalstrength",  "128");//信号 lbsMap = new HashMap();
//    list.add(lbsMap);
//    lbsMap = new HashMap<String,String>();
//
//    lbsMap.put("cell_id", "39063");//基站ID
//    lbsMap.put("lac",  "9533");//大区好
//    lbsMap.put("mcc",  "460");//国家代码
//    lbsMap.put("mnc",  "1");//运营商代码
//    lbsMap.put("signalstrength",  "126");//信号
//    list.add(lbsMap);
//    lbsMap = new HashMap<String,String>();
//
//    lbsMap.put("cell_id", "39894");//基站ID
//    lbsMap.put("lac",  "9533");//大区好
//    lbsMap.put("mcc",  "460");//国家代码
//    lbsMap.put("mnc",  "1");//运营商代码
//    lbsMap.put("signalstrength",  "115");//信号
//    list.add(lbsMap);
//    lbsMap = new HashMap<String,String>();
//
//    list.add(lbsMap);
//  
//    Map<String,Object> lbsCsMap = new HashMap<String,Object>();
//      lbsCsMap.put("celltowers", list);
//      lbsCsMap.put("mnctype","gsm");
//    
//    String s  = lbsCoord(lbsCsMap,"0");
//    System.out.println(s);
//    JSONObject jsonObj = JSONObject.fromObject(s);
//    
//    JSONObject  location = jsonObj.getJSONObject("location");
//    
//    System.out.println(location.get("addressDescription"));
//    System.out.println(location.get("longitude"));
//    System.out.println(location.get("latitude"));
//    
//   String str = getAddress(String.valueOf(location.get("longitude")), String.valueOf(location.get("latitude")));
//   System.out.println("Address="+str);
     
     System.out.println(weather("北京"));
     //116.404, 39.915
    }
  
  
}