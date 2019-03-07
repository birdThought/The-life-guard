package com.lifeshs.service.tool.impl;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.service.tool.ICacheService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 版权归 TODO 缓存工具类
 * 
 * @author duosheng.mo
 * @DateTime 2016年5月31日 上午11:12:26
 */
@Service
public class CacheServiceImpl implements ICacheService {

    private static final Logger logger = Logger.getLogger(CacheServiceImpl.class);

    @Autowired
    private EhCacheManager ehcache;

    private static int haveInitFlag = 0;
    
    @PostConstruct
    public void initCacheManager() {
        // shiro配置的cachemanager实例，直接拿来用，不必创建新实例
        if (haveInitFlag == 0) {
            logger.info("cache manager init...");
            System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
            
            haveInitFlag = 1;
        }
    }
    
    @PreDestroy
    public void flushCacheToDisk() {
        logger.info("cache manager destroy...");
        if (ehcache != null) {
            CacheManager cacheManager = ehcache.getCacheManager();
            for(String cacheName : cacheManager.getCacheNames()) {
                cacheManager.getCache(cacheName).flush();
            }
            cacheManager.shutdown();
        }
    }

    /**
     * @author duosheng.mo
     * @DateTime 2015-11-21 下午04:01:40
     * @serverCode 服务代码
     * @serverComment 服务注解
     *
     * @param cacheName 缓存名
     * @return
     */
    public Ehcache getCache(String cacheName) {
        // Cache cache = cacheManager.getCache(cacheName);//"wxCache"
        CacheManager cacheManager = ehcache.getCacheManager();
        Ehcache cache = null;
        try {
            cache = cacheManager.getEhcache(cacheName); // 新框架下cacheManager为null // 好像不会为null
        } catch (Exception e) {
            logger.error("缓存异常");
            logger.error(e);
        }
        return cache;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2015-11-21 下午04:01:15
     * @serverCode 服务代码
     * @serverComment 服务注解
     *
     * @param cacheType 缓存名
     * @param key
     * @param value
     */
    public void saveKeyValue(CacheType cacheType, String key, Object value) {
        Ehcache cache = getCache(cacheType.getName());
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * @author duosheng.mo
     * @DateTime 2015-11-21 下午04:01:30
     * @serverCode 服务代码
     * @serverComment 服务注解
     *
     * @param cacheType 缓存名
     * @param key
     * @return
     */
    public Object getCacheValue(CacheType cacheType, String key) {
        Element ele = getCache(cacheType.getName()).get(key);
        if (ele != null) {
            return ele.getValue();
        }
        return null;
    }

    public boolean delCacheValue(CacheType cacheType, String key) {
        Ehcache cache = getCache(cacheType.getName());
        boolean success = cache.remove(key);
        return success;
    }
}
