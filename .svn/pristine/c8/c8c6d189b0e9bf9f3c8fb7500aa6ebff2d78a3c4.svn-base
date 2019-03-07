package com.lifeshs.support.plantform.listener;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.dao.information.IInformationDao;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.entity.consult.TInformationLook;
import com.lifeshs.service.common.impl.transform.CommonTransImpl;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.utils.HtmlUtils;
import net.sf.ehcache.Ehcache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 版权归
 * TODO 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 *
 * @author duosheng.mo
 * @DateTime 2016-6-22 上午11:25:35
 */
public class InitListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InitListener.class);

    private IInformationDao informationDao;

    @Autowired
    private ICommonTrans commonTrans;
    @Autowired
    private ICacheService cacheService;

    public void contextDestroyed(ServletContextEvent arg0) {
//		socketThread.closeServerSocket();
        //logger.info("关闭Mian...");
        updateInformationLookRecord();
    }


    public void contextInitialized(ServletContextEvent event) {
//		ServletContext servletContext = event.getServletContext();
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //开启 Mian
        logger.info("开启 Mian...");
        long interval = 60 * 1000 * 60 * 24;
//		MinaServer.getInstances();

        informationDao = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean(IInformationDao.class);
        commonTrans = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean(CommonTransImpl.class);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                /**
                 * 资讯访问量更新，每天凌晨0点更新一次
                 */
                updateInformationLookRecord();
                updateHotNewsToCache();//更新本日浏览最多的前五资讯进缓存
            }
        }, interval, interval);
    }

    private void updateHotNewsToCache() {
        cacheService.delCacheValue(CacheType.HOT_NEWS_CACHE, "healthNews");
        List<TInformation> hotNews = informationDao.findHotInformation(5);
        if (hotNews != null && !hotNews.isEmpty()) {
            for (TInformation information : hotNews) {
                information.setContent(HtmlUtils.getTextFromHtml(information.getContent()));
            }
        }
        cacheService.saveKeyValue(CacheType.HOT_NEWS_CACHE, "healthNews", hotNews);
    }

    private void updateInformationLookRecord() {
        Ehcache newsLookCache = cacheService.getCache(CacheType.NEWS_LOOK_CACHE.getName());
        if (newsLookCache == null || newsLookCache.getSize() == 0)
            return;
        List<String> keyList = newsLookCache.getKeys();
        List<TInformationLook> looks = new ArrayList<>();
        for (String news : keyList) {
            Integer newsId = Integer.parseInt(news.split(":")[1]);
            List<String> ipList = (List<String>) newsLookCache.get(news).getValue();
            if (!ipList.isEmpty()) {
                informationDao.deleteExistNewsVisitor(newsId, ipList);//先删除有的数据后再增加
                for (String ip : ipList) {
                    TInformationLook look = new TInformationLook();
                    look.setInformationId(newsId);
                    look.setIp(ip);
                    looks.add(look);
                }
                newsLookCache.remove(news);//删除这个资讯的缓存
            }
        }
        commonTrans.batchSave(looks);
    }
}
