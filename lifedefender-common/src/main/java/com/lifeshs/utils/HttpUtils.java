package com.lifeshs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtils {
    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
    public static final int DELETE = 3;

    private static ExecutorService httpExcutor;//线程池
    
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
	private final static String DEFAULT_ENCODING = "UTF-8";


    static {
        httpExcutor = Executors.newCachedThreadPool();
    }

    public HttpUtils() {
    }

    /**
     * 获取实体
     *
     * @param url         链接
     * @param headers     头部
     * @param jsonObject  post的json
     * @param method      访问方法
     * @param getRightNow 是否需要立即获取返回结果，false会返回null
     * @param callBack    回调函数，结果可在回调中获取
     * @return
     */
    public static String getResultEntity(String url, Map<String, String> headers, Object jsonObject,
                                         int method, boolean getRightNow, HttpCallBack callBack) {
        if (jsonObject instanceof String) {
            jsonObject = JSON.parseObject((String) jsonObject);
        }
        try {
            return (String) connect(url, headers, (JSON) jsonObject, method, false, getRightNow, callBack);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getResultStatus(String url, Map<String, String> headers, JSON jsonObject,
                                      int method, boolean getRightNow, HttpCallBack callBack) {
        Integer status = 0;
        try {
            status = (Integer) connect(url, headers, (JSON) jsonObject, method, true, getRightNow, callBack);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status == null ? 0 : status;
    }

    public static HttpResponse getResponseData(int method, String url, Map<String, String> headers, JSON jsonObject) {
        HttpResponse response = null;
        try {
        	 // HttpClient client = HttpClientBuilder.create().build();
           HttpClient client = PoolConnectManager.gethttpClient();
            switch (method) {
                case HttpUtils.GET:
                    response = client.execute(doGet(url, headers));
                    break;
                case HttpUtils.POST:
                    response = client.execute(doPost(url, headers, jsonObject));
                    break;
                case HttpUtils.PUT:
                    response = client.execute(doPut(url, headers, jsonObject));
                    break;
                case HttpUtils.DELETE:
                    response = client.execute(doDelete(url, headers));
                    break;
                default:
                    return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    private static Object connect(final String url, final Map<String, String> headers, final JSON jsonObject, final int method,
                                  final boolean isGetCode, boolean getImmedetaly, final HttpCallBack callBack) throws ClientProtocolException, IOException {
       
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                HttpResponse response = getResponseData(method, url, headers, jsonObject);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                if (isGetCode) {
                    int code = response.getStatusLine().getStatusCode();
                    if (callBack != null)
                        callBack.resultCallBack(code);
                    return code;
                }
                if (callBack != null) {
                    callBack.resultCallBack(result);//返回实体
                }
                return result;
            }
        });

        httpExcutor.execute(task);
        if (getImmedetaly) {//如果需要阻塞获取结果
            try {
                return task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static HttpPost doPost(String url, Map<String, String> headers, JSON body) {
        HttpPost post = new HttpPost(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (body != null) {
            StringEntity entity = new StringEntity(body.toJSONString(), "UTF-8");
            post.setEntity(entity);
        }
        return post;
    }

    private static HttpGet doGet(String url, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return get;
    }

    private static HttpDelete doDelete(String url, Map<String, String> headers) {
        HttpDelete delete = new HttpDelete(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                delete.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return delete;
    }

    private static HttpPut doPut(String url, Map<String, String> headers, JSON body) {
        HttpPut put = new HttpPut(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                put.addHeader(entry.getKey(), entry.getValue());
            }
        }
        StringEntity entity = new StringEntity(body.toJSONString(), "UTF-8");
        put.setEntity(entity);
        return put;
    }


    /**
     * 连接池
     */
    static class PoolConnectManager {
        private static PoolingHttpClientConnectionManager cm;
        private static HttpRequestRetryHandler httpRequestRetryHandler;

        static {
            ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
            LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf)
                    .register("https", sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            // 将最大连接数增加到200
            cm.setMaxTotal(200);
            // 将每个路由基础的连接增加到20
            cm.setDefaultMaxPerRoute(20);

            // 将目标主机的最大连接数增加到50
        /*HttpHost localhost = new HttpHost("https://a1.easemob.com/");
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);*/
            //请求重试处理
            httpRequestRetryHandler = new HttpRequestRetryHandler() {
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                        return false;
                    }
                    if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                        return true;
                    }
                    if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {// 超时
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {// 目标服务器不可达
                        return false;
                    }
                    if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                        return false;
                    }
                    if (exception instanceof SSLException) {// ssl握手异常
                        return false;
                    }

                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    // 如果请求是幂等的，就再次尝试
                    if (!(request instanceof HttpEntityEnclosingRequest)) {
                        return true;
                    }
                    return false;
                }
            };
        }

        public static CloseableHttpClient gethttpClient() {
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .setRetryHandler(httpRequestRetryHandler)
                    .build();

            return httpClient;
        }
    }


    /**
     * 异步回调
     */
    public interface HttpCallBack<T> {
        <T> void resultCallBack(T result);
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    
    
    public static String postData(String urlStr, String data) {
		return postData(urlStr, data, null);
	}
 
	public static String postData(String urlStr, String data, String contentType) {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(CONNECT_TIMEOUT);
			if (contentType != null)
				conn.setRequestProperty("content-type", contentType);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
			if (data == null)
				data = "";
			writer.write(data);
			writer.flush();
			writer.close();
 
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			return sb.toString();
		} catch (IOException e) {
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

    
    
    
}
