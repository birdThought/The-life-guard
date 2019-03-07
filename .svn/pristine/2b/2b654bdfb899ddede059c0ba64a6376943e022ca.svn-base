package com.lifeshs.service.tool.impl;

import com.lifeshs.common.constants.common.CacheType;
import net.sf.ehcache.*;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *  使用CacheService
 *  TODO 缓存工具类
 *  @author duosheng.mo  
 *  @DateTime 2016年5月31日 上午11:12:26
 */
//@Component
@Deprecated
public class CacheUtil {
	public static CacheManager cacheManager;
    public static CacheUtil cacheUtil;
	@Autowired
    EhCacheManager ehcache;
	/* static {
		try {
			//cacheManager = CacheManager.create(CacheFactory.class.getResource("/spring/ehcache.xml"));
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}*/

	@PostConstruct
	public void initCacheManager(){
		cacheUtil=this;
		cacheUtil.ehcache=this.ehcache;
		setCacheManager();
	}

	public static void setCacheManager(){
        cacheManager=cacheUtil.ehcache.getCacheManager();//shiro配置的cachemanager实例，直接拿来用，不必创建新实例
    }

	public static void main(String [] args){
		System.out.println(cacheManager);
	}


	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2015-11-21 下午04:01:40
	 *  @serverCode 服务代码
	 *  @serverComment 服务注解
	 *
	 *  @param cacheName 缓存名
	 *  @return
	 */
	public static Ehcache getCache(String cacheName) {
		//Cache cache = cacheManager.getCache(cacheName);//"wxCache"
		Ehcache cache = cacheManager.getEhcache(cacheName);
		return cache;
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2015-11-21 下午04:01:15
	 *  @serverCode 服务代码
	 *  @serverComment 服务注解
	 *
	 *  @param cacheName 缓存名
	 *  @param key
	 *  @param value
	 */
	public static void saveKeyValue(CacheType cacheType, String key, Object value) {
		Ehcache cache = getCache(cacheType.getName());
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2015-11-21 下午04:01:30
	 *  @serverCode 服务代码
	 *  @serverComment 服务注解
	 *
	 *  @param cacheName 缓存名
	 *  @param key
	 *  @return
	 */
	public static Object getCacheValue(CacheType cacheType,String key){
		Element ele = getCache(cacheType.getName()).get(key);
		if(ele != null){
			return ele.getValue();
		}
		return null;
	}
	
	public static void delCacheValue(CacheType cacheType,String key){
		Ehcache cache = getCache(cacheType.getName());
		cache.remove(key);
	}
}
