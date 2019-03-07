package com.lifeshs.utils;

/**
 *  地图API
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月30日 下午1:52:37
 */
public class MapApi {

    /**
     *  计算两个坐标之间的距离
     *  @author yuhang.weng 
     *	@DateTime 2017年6月30日 下午1:52:15
     *
     *  @param lng_s
     *  @param lat_s
     *  @param lng_e
     *  @param lat_e
     *  @return
     */
    public static double getDistance(Double lng_s,Double lat_s,Double lng_e,Double lat_e) {
        double radLat1 = rad(lat_s);  
        double radLat2 = rad(lat_e);  
        double a = radLat1 - radLat2;  
        double b = rad(lng_s) - rad(lng_e);  
        double s = 2 * Math.asin(  
             Math.sqrt(  
                 Math.pow(Math.sin(a/2),2)   
                 + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
             )  
         );  
        s = s * EARTH_RADIUS;  
        s = Math.round(s * 10000) / 10000;  
        return s;
    }
    
    private static final double EARTH_RADIUS = 6378137;  
    //把经纬度转为度（°）  
    private static double rad(double d){  
       return d * Math.PI / 180.0;  
    }
}
