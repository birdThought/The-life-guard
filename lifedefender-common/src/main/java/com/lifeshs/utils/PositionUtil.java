package com.lifeshs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lifeshs.common.model.map.Gps;

/**
 *  版权归
 *  TODO  各地图API坐标系统比较与转换; 
 * 		  WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系, 
 * 			谷歌地图采用的是WGS84地理坐标系（中国范围除外）; 
 * 			GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。 
 * 			谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系; 
 * 			搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。 
 *  @author duosheng.mo  
 *  @DateTime 2016年6月12日 下午2:20:47
 */
public class PositionUtil {  
      
    public static final String BAIDU_LBS_TYPE = "bd09ll";  
    						
    public static double pi = 3.1415926535897932384626;  
    public static double a = 6378245.0;  
    public static double ee = 0.00669342162296594323;  
                   
    /** 
     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System 
     *  
     * @param lat 
     * @param lon 
     * @return 
     */  
    public static Gps gps84_To_Gcj02(double lat, double lon) {  
        if (outOfChina(lat, lon)) {  
            return null;  
        }  
        double dLat = transformLat(lon - 105.0, lat - 35.0);  
        double dLon = transformLon(lon - 105.0, lat - 35.0);  
        double radLat = lat / 180.0 * pi;  
        double magic = Math.sin(radLat);  
        magic = 1 - ee * magic * magic;  
        double sqrtMagic = Math.sqrt(magic);  
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
        double mgLat = lat + dLat;  
        double mgLon = lon + dLon;  
        return new Gps(mgLat, mgLon);  
    }  
  
    /** 
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return 
     * */  
    public static Gps gcj_To_Gps84(double lat, double lon) {  
        Gps gps = transform(lat, lon);  
        double lontitude = lon * 2 - gps.getLng();
        double latitude = lat * 2 - gps.getLat();  
        return new Gps(latitude, lontitude);  
    }  
  
    /** 
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标 
     *  
     * @param gg_lat 
     * @param gg_lon 
     */  
    public static Gps gcj02_To_Bd09(double gg_lat, double gg_lon) {  
        double x = gg_lon, y = gg_lat;  
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);  
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);  
        double bd_lon = z * Math.cos(theta) + 0.0065;  
        double bd_lat = z * Math.sin(theta) + 0.006;  
        return new Gps(bd_lat, bd_lon);  
    }  
  
    /** 
     * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param 
     * bd_lat * @param bd_lon * @return 
     */  
    public static Gps bd09_To_Gcj02(double bd_lat, double bd_lon) {  
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);  
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);  
        double gg_lon = z * Math.cos(theta);  
        double gg_lat = z * Math.sin(theta);  
        return new Gps(gg_lat, gg_lon);  
    }  
  
    /** 
     * (BD-09)-->84 
     * @param bd_lat 
     * @param bd_lon 
     * @return 
     */  
    public static Gps bd09_To_Gps84(double bd_lat, double bd_lon) {  
  
        Gps gcj02 = PositionUtil.bd09_To_Gcj02(bd_lat, bd_lon);  
        Gps map84 = PositionUtil.gcj_To_Gps84(gcj02.getLat(),  
                gcj02.getLng());  
        return map84;  
  
    }  
    
    /**
     *  @author duosheng.mo 
     *	@DateTime 2016年6月12日 下午4:41:01
     *  @serverCode GPS 转百度
     *
     *  @param gg_lat
     *  @param gg_lon
     *  @return
     */
    public static Gps Gps84_To_Bd09(double gg_lat, double gg_lon) {  
    	//gps转百度 调用百度的API
    	Gps latLng = null;
		String lanLat = BaiduMapAPI.gpsFormatBaidu(String.valueOf(gg_lon),String.valueOf(gg_lat), false);
		if(StringUtils.isNotBlank(lanLat)){
			String ll[] =  lanLat.split(",");
			latLng = new Gps(Double.parseDouble(ll[1]),Double.parseDouble(ll[0]));
		}else{
			latLng = new Gps();
		}
    	return latLng;
    } 
  
    public static boolean outOfChina(double lat, double lon) {  
        if (lon < 72.004 || lon > 137.8347)  
            return true;  
        if (lat < 0.8293 || lat > 55.8271)  
            return true;  
        return false;  
    }  
  
    public static Gps transform(double lat, double lon) {  
        if (outOfChina(lat, lon)) {  
            return new Gps(lat, lon);  
        }  
        double dLat = transformLat(lon - 105.0, lat - 35.0);  
        double dLon = transformLon(lon - 105.0, lat - 35.0);  
        double radLat = lat / 180.0 * pi;  
        double magic = Math.sin(radLat);  
        magic = 1 - ee * magic * magic;  
        double sqrtMagic = Math.sqrt(magic);  
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
        double mgLat = lat + dLat;  
        double mgLon = lon + dLon;  
        return new Gps(mgLat, mgLon);  
    }  
  
    public static double transformLat(double x, double y) {  
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y  
                + 0.2 * Math.sqrt(Math.abs(x));  
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;  
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;  
        return ret;  
    }  
  
    public static double transformLon(double x, double y) {  
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1  
                * Math.sqrt(Math.abs(x));  
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;  
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0  
                * pi)) * 2.0 / 3.0;  
        return ret;  
    }  
    
    
    /**
     *  @author duosheng.mo 
     *	@DateTime 2015-6-12 上午10:43:42
     *  @serverCode 服务代码
     *  @serverComment 坐标转换  type == "" 时不做转换
     *
     *  @param listMap
     *  @param type 转换类型
     *  @param bool 是否是电子围栏
     *  @return
     */
    @SuppressWarnings("unchecked")
	public static List<Map<String, Object>> coordSys(List<Map<String , Object>> listMap,String type,boolean bool){
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(null != listMap && !listMap.isEmpty()){
			for(Map map : listMap){
				String strLat = (String)map.get("lat");
				String strLng = (String)map.get("lng");
				if(StringUtils.isBlank(strLat) || StringUtils.isBlank(strLng)){
					continue;
				}
				double lat = Double.parseDouble(strLat);
				double lng = Double.parseDouble(strLng);
				Gps latLng = null; 
				if("GpsToGcj".equals(type)){
					//WGS84(GPS)坐标系转  Gcj02(谷歌中国大陆)
					latLng = PositionUtil.gps84_To_Gcj02(lat, lng);
					if(bool){//电子围栏坐标转换
						String mapType = (String)map.get("map_type");
						if("B".equals(mapType)){
							double zx_lat = Double.parseDouble((String)map.get("zx_lat"));
							double zx_lng = Double.parseDouble((String)map.get("zx_lng"));
							Gps zxLatLng = PositionUtil.bd09_To_Gcj02(zx_lat, zx_lng);
							map.put("zx_lat", zxLatLng.getLat());
							map.put("zx_lng", zxLatLng.getLng());
						}
					}
				}else if("GcjToGps".equals(type)){
					//Gcj02(谷歌中国大陆)坐标系转  WGS84(GPS)
					latLng = PositionUtil.gcj_To_Gps84(lat, lng); 
				}else if("GcjToBd".equals(type)){
					//Gcj02(谷歌中国大陆)坐标系转  Bd09(百度)
					latLng = PositionUtil.gcj02_To_Bd09(lat, lng);
				}else if("BdToGcj".equals(type)){
					//Bd09(百度)坐标系转 Gcj02(谷歌中国大陆)
					latLng = PositionUtil.bd09_To_Gcj02(lat, lng);
				}else if("BdToGps".equals(type)){
					//Bd09(百度)坐标系转 WGS84(GPS)坐标
					latLng = PositionUtil.bd09_To_Gps84(lat, lng);  
				}else if("GpsToBd".equals(type)){
					//gps转百度 调用百度的API
					latLng = Gps84_To_Bd09(Double.valueOf((String)map.get("lat")), Double.valueOf((String)map.get("lng")));
					if(bool){//电子围栏坐标转换
						String mapType = (String)map.get("map_type");
						if("G".equals(mapType)){
							double zx_lat = Double.parseDouble((String)map.get("zx_lat"));
							double zx_lng = Double.parseDouble((String)map.get("zx_lng"));
							Gps zxLatLng = PositionUtil.gcj02_To_Bd09(zx_lat, zx_lng);
							map.put("zx_lat", zxLatLng.getLat());
							map.put("zx_lng", zxLatLng.getLng());
						}
					}
				}else{
					latLng = new Gps(lat,lng);
				}
				map.put("lng", latLng.getLng());
				map.put("lat", latLng.getLat());
				result.add(map);
			}
		}
		return result;
	}
    
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月1日 上午9:04:19
	 *  @serverComment 计算两点之间距离
	 *
	 *  @param start
	 *  @param end
	 *  @return 米
	 */
	public static double getDistance(Gps start,Gps end){
		double lat1 = (Math.PI/180)*start.getLat();//纬度
		double lat2 = (Math.PI/180)*end.getLat();
		
		double lon1 = (Math.PI/180)*start.getLng();//经度
		double lon2 = (Math.PI/180)*end.getLng();
		//地球半径
		double R = 6371;
		double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+
					Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
		//两点间距离 km，如果想要米的话，结果*1000就可以了    
		return d*1000;
	}
  
//    public static void main(String[] args) {  
//    	//113.39856397808,23.129687505337
//    	//113.39865705054235, 23.12925821360386
//        // 北斗芯片获取的经纬度为WGS84地理坐标 31.426896,119.496145    , 
//        Gps gps = new Gps(23.1906058, 113.40672813333333);  
//        System.out.println("gps :" + gps);  
//        
//        Gps gcj = gps84_To_Gcj02(gps.getLat(), gps.getLng());  
//        System.out.println("gps84_To_Gcj02 = " + gcj);  
//        
//        Gps star = gcj_To_Gps84(gcj.getLat(), gcj.getLng());  
//        System.out.println("gcj_To_Gps84   = " + star);  
//        
//        Gps bd = gcj02_To_Bd09(gcj.getLat(), gcj.getLng());  
//        System.out.println("gcj02_To_Bd09  = " + bd);  
//        
//        Gps gcj2 = bd09_To_Gcj02(bd.getLat(), bd.getLng());  
//        System.out.println("bd09_To_Gcj02  = " + gcj2);  
//        
//        System.out.println("=========================================");
//        
//        Gps baidu = bd09_To_Gcj02(23.120470378879, 113.27967038761);  
//        System.out.println("bd09_To_Gps84  = " + baidu); 
//        
//        System.out.println(".>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        //(113.41872480032,23.194418304525,113.418829,23.19383);
//        Gps google = bd09_To_Gcj02(23.19383, 113.418829);  
//        System.out.println("bd09_To_Gcj02  = " + google);  
//        //23.18851317700628,113.41221679272347
//        //23.187924759186803,113.41232098473591
//        
//        //bd09_To_Gps84
//        Gps bgps1 = bd09_To_Gps84(29.490295,106.486654);
//        Gps bgps2 = bd09_To_Gps84(29.615467,106.581515);
//        System.out.println(bgps1.getLat()+","+bgps1.getLng());
//        System.out.println(bgps2.getLat()+","+bgps2.getLng());
//        
//    }  
}  
