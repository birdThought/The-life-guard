package com.lifeshs.service1.transfer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.Transfer;
import com.lifeshs.dao1.transfer.TransferCleaningDao;
import com.lifeshs.po.transfer.TransferCleaning;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.transfer.TransferCleaningService;
import com.lifeshs.utils.Replace;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-01-18
 * 11:32
 * @desc
 */
@Service("transferCleaningService")
public class TransferCleaningServiceImpl implements TransferCleaningService {

    @Resource(name = "transferCleaningDao")
    private TransferCleaningDao transferCleaningDao;

    private String province = "广东省";
    private String city = "广州市";
    private String jobname ="小时工";

    private static final Logger logger = Logger.getLogger(TransferCleaningServiceImpl.class);

    @Override
    public int saveTransferleaning(BigDecimal totalprice, String customername, String customermobile, String workdistrict, String workaddress, String details,String youxiqi, String  yong,Integer yonggongshichang, String area, Integer gender, Integer language, Integer pulation){
        TransferCleaning tfc = new TransferCleaning();
        tfc.setAppid(Transfer.APPID);
        String appsecert = Transfer.APPSECERT;
        long l = System.currentTimeMillis();
        String accesstoken = Replace.getStringDate(appsecert,l);
        //生成订单号
        String orderno = AlipayService.createOrderNumber();
        BigDecimal platformprice =null;//家政
        BigDecimal companyprice = null;//门店
        if ("小时工".equals(jobname)){  //上门服务
            double v1 = 0.6; //门店
            double v2 = 0.2; //家政天下
            BigDecimal b1 = new BigDecimal(Double.toString(v1)); //门店
            BigDecimal b2 = new BigDecimal(Double.toString(v2)); // 家政天下
            companyprice = BigDecimal.valueOf(totalprice.multiply(b1).doubleValue());
            platformprice = BigDecimal.valueOf(totalprice.multiply(b2).doubleValue());
        }

        /** 家政月工资  默认为 0*/
        BigDecimal nurseprice = new BigDecimal(0);
        tfc.setAccesstoken(accesstoken);
        tfc.setOrderno(orderno);
        tfc.setTotalprice(totalprice);
        tfc.setPlatformprice(platformprice);
        tfc.setCompanyprice(companyprice);
        tfc.setNurseprice(nurseprice);
        tfc.setCustomername(customername);
        tfc.setCustomermobile(customermobile);
        tfc.setWorkprovince(province);
        tfc.setWorkcity(city);
        tfc.setWorkdistrict(workdistrict);
        tfc.setWorkaddress(workaddress);
        tfc.setJobname(jobname);
        tfc.setDetails(details);
        tfc.setYouxiqi(youxiqi);
        tfc.setYonggongshijian(yong);
        tfc.setYonggongshichang(yonggongshichang);
        tfc.setFamilyarea(area);
        tfc.setPopulation(pulation);
        tfc.setGender(gender);
        tfc.setLanguage(language);

        String response = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(Transfer.JIAZHENG_TIANXIA_APP);
            Map<String, Object> params = new HashMap<>();
            params.put("orderno", orderno);
            params.put("totalprice", totalprice);
            params.put("platformprice", platformprice);
            params.put("companyprice", companyprice);
            params.put("nurseprice", nurseprice);
            params.put("customername", customername);
            params.put("customermobile", customermobile);
            params.put("workprovince", province);
            params.put("workcity", city);
            params.put("workdistrict", workdistrict);
            params.put("workaddress", workaddress);
            params.put("jobname", jobname);
            params.put("details", details);
            params.put("youxiqi", youxiqi);
            params.put("yonggongshijian", yong);
            params.put("yonggongshichang", yonggongshichang);
            JSONObject success = AppNormalService.successJia(params, Transfer.APPID, accesstoken, l);
            String string = success.toString();
            String s = string.replaceAll("\\[|\\]", "");
            JSONObject json = JSONObject.parseObject(s);
            //开始访问
            URLConnection con = url.openConnection();
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), "utf-8");
            out.write(json.toJSONString());
            out.flush();
            sb = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
            }
            response = sb.toString();
            JSONObject object = JSON.parseObject(response);
            Object errorcode = object.get("errorcode");
            Object errmsg = object.get("errmsg");
            Object x =0;
            if (errorcode != x) {
                logger.error("下单失败 错误的信息是" + errmsg);
                return 0;
            }
            int i = transferCleaningDao.saveTransferOrder(tfc);
            if (i == 0) {
                logger.error("添加失败！！");
            }
            return i;
        } catch (Exception e) {

        }
        return 0;
    }
    
    
    
    @Override
    public int saveTransferOrder(){
//        TransferCleaning tfc = new TransferCleaning();
//        tfc.setAppid(Transfer.APPID);
//        String appsecert = Transfer.APPSECERT;
//        long l = System.currentTimeMillis();
//        String accesstoken = Replace.getStringDate(appsecert,l);
//        //生成订单号
//        String orderno = AlipayService.createOrderNumber();
//        BigDecimal platformprice =null;//家政
//        BigDecimal companyprice = null;//门店
//        if ("小时工".equals(jobname)){  //上门服务
//            double v1 = 0.6; //门店
//            double v2 = 0.2; //家政天下
//            BigDecimal b1 = new BigDecimal(Double.toString(v1)); //门店
//            BigDecimal b2 = new BigDecimal(Double.toString(v2)); // 家政天下
//            companyprice = BigDecimal.valueOf(totalprice.multiply(b1).doubleValue());
//            platformprice = BigDecimal.valueOf(totalprice.multiply(b2).doubleValue());
//        }
//
//        /** 家政月工资  默认为 0*/
//        BigDecimal nurseprice = new BigDecimal(0);
//        tfc.setAccesstoken(accesstoken);
//        tfc.setOrderno(orderno);
//        tfc.setTotalprice(totalprice);
//        tfc.setPlatformprice(platformprice);
//        tfc.setCompanyprice(companyprice);
//        tfc.setNurseprice(nurseprice);
//        tfc.setCustomername(customername);
//        tfc.setCustomermobile(customermobile);
//        tfc.setWorkprovince(province);
//        tfc.setWorkcity(city);
//        tfc.setWorkdistrict(workdistrict);
//        tfc.setWorkaddress(workaddress);
//        tfc.setJobname(jobname);
//        tfc.setDetails(details);
//        tfc.setYouxiqi(youxiqi);
//        tfc.setYonggongshijian(yong);
//        tfc.setYonggongshichang(yonggongshichang);
//        tfc.setFamilyarea(area);
//        tfc.setPopulation(pulation);
//        tfc.setGender(gender);
//        tfc.setLanguage(language);
//
//        String response = null;
//        StringBuffer sb = new StringBuffer();
//        try {
//            URL url = new URL(Transfer.JIAZHENG_TIANXIA_APP);
//            Map<String, Object> params = new HashMap<>();
//            params.put("orderno", orderno); //外部系统订单号
//            params.put("totalprice", totalprice);//订单总金额
//            params.put("platformprice", platformprice);//家政天下所得金额
//            params.put("companyprice", companyprice);//门店所得金额
//            params.put("nurseprice", nurseprice);//家政员月工资
//            params.put("FullName",fullName);//客户姓名
//            params.put("Tel",tel);//客户手机号
//            params.put("Area_P",area_P);//工作地点（省）
//            params.put("Area_C",area_C);//工作地点（市）
//            params.put("Area_D",area_D);//工作地点（区）
//            params.put("Address",address);//工作地点
//            params.put("JobTypeID",jobTypeID);//工作类型
//            params.put("smalljob",smalljob);//岗位服务内容
//            params.put("WorkDate",workDate);//具体用工时间起
//            params.put("WorkDate",workDate);//时长
//            params.put("ReadMe",readMe);//详细订单情况
//            params.put("isHome",isHome);//是否需要陪护人员住家
//            params.put("RestWay",restWay);//休息方式
//            params.put("RestDay",restDay);//休息几天
//            params.put("GoWorkTimeStart",goWorkTimeStart);//上班时间点/上门时间
//            params.put("GoWorkTimeEnd",goWorkTimeEnd);//下班时间点
//            params.put("ZiLiSex",ziLiSex);//被照顾者性别
//            params.put("ZiLi",ziLi);//自理能力
//            params.put("BabyAge",babyAge);//宝宝年龄
//            params.put("YuChanQi",yuChanQi);//月嫂使用时长
//            
////            params.put("customername", customername);
////            params.put("customermobile", customermobile);
////            params.put("workprovince", province);
////            params.put("workcity", city);
////            params.put("workdistrict", workdistrict);
////            params.put("workaddress", workaddress);
////            params.put("jobname", jobname);
////            params.put("details", details);
////            params.put("youxiqi", youxiqi);
////            params.put("yonggongshijian", yong);
////            params.put("yonggongshichang", yonggongshichang);
//            JSONObject success = AppNormalService.successJia(params, Transfer.APPID, accesstoken, l);
//            String string = success.toString();
//            String s = string.replaceAll("\\[|\\]", "");
//            JSONObject json = JSONObject.parseObject(s);
//            //开始访问
//            URLConnection con = url.openConnection();
//            con.setRequestProperty("accept", "*/*");
//            con.setRequestProperty("connection", "Keep-Alive");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.setUseCaches(false);
//
//            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), "utf-8");
//            out.write(json.toJSONString());
//            out.flush();
//            sb = new StringBuffer();
//            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
//            if (contentLength > 0) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//                String temp;
//                while ((temp = br.readLine()) != null) {
//                    sb.append(temp);
//                }
//            }
//            response = sb.toString();
//            JSONObject object = JSON.parseObject(response);
//            Object errorcode = object.get("errorcode");
//            Object errmsg = object.get("errmsg");
//            Object x =0;
//            if (errorcode != x) {
//                logger.error("下单失败 错误的信息是" + errmsg);
//                return 0;
//            }
//            int i = transferCleaningDao.saveTransferOrder(tfc);
//            if (i == 0) {
//                logger.error("添加失败！！");
//            }
//            return i;
//        } catch (Exception e) {
//
//        }
        return 0;
    }


    @Override
    public TransferCleaning findByTransferId(Integer id) {
        return null;
    }


    @Override
    public TransferCleaning finfByOrderNo(String oderNo) {
        return transferCleaningDao.findByOrderNO(oderNo);
    }
}
