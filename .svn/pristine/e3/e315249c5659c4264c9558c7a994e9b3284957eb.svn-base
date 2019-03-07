package com.lifeshs.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

//@Repository(value = "taskPoolService")
// 写在applicationContext文件中
/**
 *  任务池
 *  <p>任务池用使用{@link ExecutorService}方式创建队列
 *  <p>使用spring来管理线程池的初始化信息，以及在关闭服务的同时shutdown线程池
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月21日 上午10:28:23
 */
public class TaskPoolService {

    private static ExecutorService pool;

    private static final Logger logger = Logger.getLogger(TaskPoolService.class);

    private static int haveInitFlag = 0;

    @PostConstruct
    private void initTaskPoolService() {
        // 使用flag避免多次初始化线程池大小
        if (haveInitFlag == 0) {
            logger.info("TaskPoolService init...");
            // 初始化容量为10
            pool = Executors.newFixedThreadPool(10);
            haveInitFlag = 1;
        }
    }

    @PreDestroy
    private void shutDownPool() {
        logger.info("TaskPoolService destroy...");
        pool.shutdownNow();
    }
    
    /**
     *  提交新的任务
     *  @author yuhang.weng 
     *  @DateTime 2017年11月27日 上午10:40:59
     *
     *  @param task 任务
     */
    public void put(Runnable task) {
        pool.submit(task);
    }
    
    /**
     *  提交新的任务
     *  <p>这种提交任务的方式可以通过 {@link Future#get} 获取返回值
     *  <p>不过只有在线程池还有空闲的时候，你可以快捷轻便地拿到想要的结果
     *  <p>否则它会花费大量的时间，消耗在排队上面，尽管你的任务看起来或许1毫秒都不需要就能够完成
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 上午10:28:09
     *
     *  @param task 一个会响应你的任务
     *  @return
     */
    public <T> Future<T> put(Callable<T> task) {
        return pool.submit(task);
    }
}
