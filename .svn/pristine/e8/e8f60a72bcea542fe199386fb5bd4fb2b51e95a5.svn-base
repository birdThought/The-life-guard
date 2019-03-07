package com.lifeshs.thirdservice;

import com.lifeshs.utils.SerializeUtil;
import redis.clients.jedis.Jedis;

/**
 * redis服务（增删查改）
 * 序列化与反序列化
 * author: wenxian.cai
 * date: 2017/9/21 11:16
 */
public class RedisService {

	private static final String ip = "127.0.0.1";
	private static final int port = 6379;
	static Jedis jedis = null;
	static {
		jedis = new Jedis(ip, port);
	}
	/**
	 * 存储值
	 * @param object
	 * @param key
	 * @return
	 */
	public String set(String key, Object object)
	{
		return jedis.set(key.getBytes(), SerializeUtil.serialize(object));
	}

	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public Object get(String key)
	{
		byte[] value = jedis.get(key.getBytes());
		return SerializeUtil.unserialize(value);
	}

	/**
	 * 删除值
	 * @param key
	 * @return
	 */
	public boolean del(String key)
	{
		return jedis.del(key.getBytes()) > 0;
	}
}
