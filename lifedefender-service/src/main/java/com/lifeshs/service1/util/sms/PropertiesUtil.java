package com.lifeshs.service1.util.sms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *  版权归
 *  TODO 获取属性文件工具类
 *  @author duosheng.mo  
 *  @DateTime 2016年5月9日 下午2:47:46
 */
public class PropertiesUtil {
	private String properiesName = "";

	 public static PropertiesUtil getInstances() {     
		 PropertiesUtil pro = new PropertiesUtil("sysConfig.properties");
		 return pro;     
	}   
	 
	public PropertiesUtil() {

	}
	
	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月9日 下午2:54:45
	 *  @serverCode 通过key获取value值
	 *  @serverComment 服务注解
	 *
	 *  @param key
	 *  @return
	 */
	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月9日 下午2:58:28
	 *  @serverCode 获取 Properties 类
	 *  @serverComment 服务注解
	 *
	 *  @return
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月9日 下午2:56:08
	 *  @serverCode 向属性文件写值
	 *  @serverComment 服务注解
	 *
	 *  @param key
	 *  @param value
	 */
	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(properiesName);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void main(String[] args) {
		System.out.println("1212121");
		PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
		Properties pro = p.getProperties();
		System.out.println(pro.getProperty("randCodeLength"));
		System.out.println(p.readProperty("randCodeLength"));
	}

}
