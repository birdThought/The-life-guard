package com.lifeshs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取系统环境变量
 *
 */
public class SysEnvUtil
{
	private static Lock locker = new ReentrantLock();
	private static Map<String,String> cache = new HashMap<String,String>();
	
	public static String getEnv(String key)
	{
		if(cache.containsKey(key))
		{
			return cache.get(key);
		}
		else
		{
			locker.lock();
			try
			{
				if(!cache.containsKey(key))
				{
					String value = System.getProperty(key);
					if (value == null || value.trim().length() == 0)
					{
						value = System.getenv(key);
					}
					if (value != null && value.trim().length() > 0)
					{
						cache.put(key, value);
						return value;
					}
					else
					{
						return null;
					}
				}
				else
				{
					return cache.get(key);
				}
			}
			finally
			{
				locker.unlock();
			}
			
		}
	}

	public static void main(String[] args)
	{
		System.out.println(getEnv("life_web_log"));
		//System.out.println(getEnv("life_api_log"));
	}
}
