package com.lifeshs.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.lifeshs.utils.ResourceUtil;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 *  目录任务服务类
 *  @author yuhang.weng  
 *  @DateTime 2016年8月25日 下午4:02:59
 */

//@Component
public class Folder {

    private static final Logger logger = Logger.getLogger(Folder.class);
    
    /**
     *  每天凌晨2点清除临时目录下的文件
     *  清除目标是3天前的临时文件
     *  eg： 今天是星期5，那么会保留星期4,3,2的临时文件，星期1会被清除
     *  @author yuhang.weng 
     *  @DateTime 2016年8月25日 下午4:04:55
     *
     */
    @Scheduled(cron = "0 0 2 1/1 * ?")
    public void cleanTmpFolder() {
        logger.info("开始执行删除临时文件夹命令");
        
        // TODO 后续有时间再写。。。。
        
        LocalDate now = LocalDate.now();
        
        File folder = null;
        int m = 4;
        String separator = ResourceUtil.getSeparator();
        while (true) {
            Date date = minusDay(now, m);
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy" + separator + "MM" + separator + "dd");
            String folderPath = sdFormat.format(date);
            folder = new File(ImageUtilV2.ROOT_PATH + separator + "temp" + separator + folderPath);
            if (folder.exists()) {
                logger.info("删除目录[" + folder.getAbsolutePath() + "]");
                deleteDir(folder);
                m++;
            } else {
                break;
            }
        }
    }
    
    private Date minusDay(LocalDate localDate, int day) {
        LocalDate targetDate = localDate.minusDays(day);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = targetDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }
    
    private Boolean deleteDir(File dir) {
        if(dir.isDirectory()) {
            String[] childrens = dir.list();
            for(int i = 0; i < childrens.length; i++) {
                Boolean success = deleteDir(new File(dir, childrens[i]));
                if(!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
