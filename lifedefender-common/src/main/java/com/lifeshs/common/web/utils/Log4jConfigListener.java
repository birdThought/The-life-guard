package com.lifeshs.common.web.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.lifeshs.utils.SysEnvUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.Log4jConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * log4j初始化监听器，从系统环境变量中读取log文件的输出目录
 * 
 */
public class Log4jConfigListener implements ServletContextListener {

	static Logger logger = Logger.getLogger(Log4jConfigListener.class); 
	/** Parameter specifying the refresh interval for checking the log4j config file */
	public static final String REFRESH_INTERVAL_PARAM = "log4jRefreshInterval";
	
	/** Parameter specifying the output path of the log file while is define in system environment variables*/
	private static final String LOG4JOUTPUTENVKEY = "log4jOutputEnvKey";
	
	/** Parameter specifying the config path of the log file while is define in system environment variables*/
	private static final String LOG4JCONFIGENVKEY = "log4jConfigEnvKey";
	
	/** Parameter specifying the location of the log4j config file */
	public static final String LOG4JFILENAME = "log4jFileName";
	
	public Log4jConfigListener(){
    } 
	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		ServletContext servletContext = sce.getServletContext();
		//指定输出文件夹
		String log4jOutputEnvKey = servletContext.getInitParameter(LOG4JOUTPUTENVKEY);
		if(log4jOutputEnvKey==null||log4jOutputEnvKey.trim().length()==0)
		{
			servletContext.log("Can't find "+LOG4JOUTPUTENVKEY+",this causes the log4j not work");
			return;
		}
		//
		String log4jOutputPath = SysEnvUtil.getEnv(log4jOutputEnvKey);
		if(log4jOutputPath==null||log4jOutputPath.trim().length()==0)
		{
			servletContext.log("Can't find system environment variables "+log4jOutputEnvKey+",this causes the log4j not work");
			return;
		}
		System.setProperty(log4jOutputEnvKey, log4jOutputPath);
		//指定log4j配置文件的路径
		String log4jConfigEnvKey = servletContext.getInitParameter(LOG4JCONFIGENVKEY);
		if(log4jConfigEnvKey==null||log4jConfigEnvKey.trim().length()==0)
		{
			servletContext.log("Can't find "+LOG4JCONFIGENVKEY+",this causes the log4j not work");
			return;
		}
		String log4jConfigPath = SysEnvUtil.getEnv(log4jConfigEnvKey);
		if(log4jConfigPath==null||log4jConfigPath.trim().length()==0)
		{
			servletContext.log("Can't find system environment variables "+log4jConfigEnvKey+",this causes the log4j not work");
			return;
		}
		//
		String name = servletContext.getInitParameter(LOG4JFILENAME);
		String location = log4jConfigPath+ File.separator+name;
		if (location != null) {
			// Perform actual log4j initialization; else rely on log4j's default initialization.
			try {
				// Write log message to server log.
				servletContext.log("Initializing log4j from [" + location + "]");

				// Check whether refresh interval was specified.
				String intervalString = servletContext.getInitParameter(REFRESH_INTERVAL_PARAM);
				if (intervalString != null) {
					// Initialize with refresh interval, i.e. with log4j's watchdog thread,
					// checking the file in the background.
					try {
						long refreshInterval = Long.parseLong(intervalString);
						//PropertyConfigurator.configureAndWatch(location, refreshInterval);
						Log4jConfigurer.initLogging(location, refreshInterval);
					}
					catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Invalid 'log4jRefreshInterval' parameter: " + ex.getMessage());
					}
				}
				else {
					// Initialize without refresh check, i.e. without log4j's watchdog thread.
					//PropertyConfigurator.configure(location);
					Log4jConfigurer.initLogging(location);
				}
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Invalid 'log4jConfigLocation' parameter: " + ex.getMessage());
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		LogManager.shutdown();
		String log4jOutputEnvKey = sce.getServletContext().getInitParameter(LOG4JOUTPUTENVKEY);
		if(log4jOutputEnvKey!=null&&log4jOutputEnvKey.trim().length()>0)
		{
			System.getProperties().remove(log4jOutputEnvKey);
		}
		String log4jConfigEnvKey = sce.getServletContext().getInitParameter(LOG4JCONFIGENVKEY);
		if(log4jConfigEnvKey!=null&&log4jConfigEnvKey.trim().length()>0)
		{
			System.getProperties().remove(log4jConfigEnvKey);
		}
	}
}
