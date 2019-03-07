package com.lifeshs.utils;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 *  版权归
 *  TODO 项目参数工具类
 *  @author duosheng.mo
 *  @DateTime 2016年4月28日 上午9:16:50
 */
public class ResourceUtil {


	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	/**
	 * 获取session定义名称
	 *
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return bundle.getString(sessionName);
	}

	/**
	 *  @author zhiguo.lin
	 *	@DateTime 2016年8月31日 上午10:06:44
	 *  @serverComment 计算用户年龄
	 *
	 *  @param birth
	 *  @return
	 *//*
	public static Integer getAge(Date birth) {
		Calendar calendar = Calendar.getInstance();
		// 获取现在的年份
		int yearNow = calendar.get(Calendar.YEAR);
		// 将日期设置为生日那一天
		Integer age = null;
		if(birth != null){
			calendar.setTime(birth);
			int yearBirth = calendar.get(Calendar.YEAR);
			// 通过年龄区间判断获取min值与max值
			age = yearNow - yearBirth; // 计算年龄
		}
		return age;
	}*/


	/**
	 * 获得请求路径
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String queryString = request.getQueryString();
		String requestPath = "";
		if(StringUtils.isNotBlank(queryString)){
			requestPath = request.getRequestURI() + "?" + request.getQueryString();
		}else{
			requestPath = request.getRequestURI();
		}
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 *
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}

	/**
	 * 获取配置文件参数
	 *
	 * @param name
	 * @return
	 */
	public static final Map<Object, Object> getConfigMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Set set = bundle.keySet();
		return ConvertUtil.SetToMap(set);
	}


	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取容器根目录
	 *
	 * @return
	 */
	public static String getRootPath() {
		// 当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\bin
		String nowpath = System.getProperty("user.dir");
		String tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += System.getProperty("file.separator");
		//tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	/**
	 * 获取项目根目录
	 *
	 * @return
	 */
	public static String getPorjectPath() {
		// 当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\webapps/lifeshs.web/WEB-INF/classes/pack/ 
		String nowpath = new ResourceUtil().getClass().getResource("/").toString();
		nowpath = nowpath.replaceFirst("file:/", "");
		int num = nowpath.indexOf("WEB-INF");
		nowpath = nowpath.substring(0, num);
		String separator = System.getProperty("file.separator");

		nowpath = nowpath.replaceAll("/", separator + separator);

		// 如果是Linux系统，需要加多一个"/"
		if(System.getProperty("os.name").indexOf("Linux") != -1){
			nowpath = separator + nowpath;
		}

//		String projectName = getConfigByName("project.name");
//		nowpath += projectName;

		return nowpath;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}


//    update-begin--Author:zhangguoming  Date:20140226 for：添加验证码
	/**
	 * 获取随机码的长度
	 *
	 * @return 随机码的长度
	 */
	public static String getRandCodeLength() {
		return bundle.getString("randCodeLength");
	}

	/**
	 * 获取随机码的类型
	 *
	 * @return 随机码的类型
	 */
	public static String getRandCodeType() {
		return bundle.getString("randCodeType");
	}
}
