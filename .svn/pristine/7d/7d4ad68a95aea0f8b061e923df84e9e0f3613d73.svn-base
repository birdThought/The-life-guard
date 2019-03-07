package com.lifeshs.thirdservice.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.lifeshs.component.weChat.MD5Util;

import net.sf.json.JSONObject;

public class WeChatHttpUtils {

  //微信参数配置  
    //    public static String API_KEY = "aabe26408852e78249d51e7fb9800b7d";

    //随机字符串生成  
    public static String getRandomString(int length) { //length表示生成字符串的长度      
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //请求xml组装  
    public static String getRequestXml(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
//            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
//                sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
//            } else {
//                sb.append("<" + key + ">" + value + "</" + key + ">");
//            }
            sb.append("<" + key + ">" + value + "</" + key + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获取签名 md5加密(微信支付必须用MD5加密)
     * 获取支付签名
     * @param params
     * @return
     */
    public static String createSign(SortedMap<String, Object> parameters,String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        System.out.println(sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
        return sign;
    }
    
    /**
     * 健客签名
     * null过滤,""不过滤
     * 获取签名 md5加密(微信支付必须用MD5加密)
     * 获取支付签名
     * @param params
     * @return
     */
    public static String createJKSign(SortedMap<String, Object> parameters,String apiKey) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        System.out.println(sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
        return sign;
    }

    /**
     * 验证回调签名
     * @param packageParams
     * @param key
     * @param charset
     * @return
     */
    public static boolean isTenpaySign(Map<String, String> map, String apiKey) throws UnsupportedEncodingException {
        String charset = "utf-8";
        String signFromAPIResponse = map.get("sign");
        if (signFromAPIResponse == null || signFromAPIResponse.equals("")) {
            System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);
        //过滤空 设置 TreeMap
        SortedMap<String, String> packageParams = new TreeMap<>();
        for (String parameter : map.keySet()) {
            String parameterValue = map.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);

        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较

        //算出签名
        String resultSign = "";
        String tobesign = sb.toString();
        if (null == charset || "".equals(charset)) {
            resultSign = MD5Util.MD5Encode(tobesign, charset).toUpperCase();
        } else {
            resultSign = MD5Util.MD5Encode(tobesign, charset).toUpperCase();
        }
        String tenpaySign = packageParams.get("sign").toUpperCase();
        return tenpaySign.equals(resultSign);
    }

    //请求方法  
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）  
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据  
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式  
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容  
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源  
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}" + e);
        }
        return null;
    }
    
  //请求方法  
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr,Map<String, String> headers) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）  
            conn.setRequestMethod(requestMethod);
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            
            // 当outputStr不为null时向输出流写数据  
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式  
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容  
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源  
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}" + e);
        }
        return null;
    }
}
