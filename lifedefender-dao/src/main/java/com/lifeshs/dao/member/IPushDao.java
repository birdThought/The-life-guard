package com.lifeshs.dao.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.push.MeasureReminderTaskPo;
import com.lifeshs.pojo.app.PushDataDTO;
import com.lifeshs.pojo.app.PushTaskDTO;
import com.lifeshs.pojo.member.MeasureReminderTaskDTO;

/**
 *  用户推送信息相关dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月23日 下午3:32:50
 */
@Repository(value = "memberPushDao")
public interface IPushDao {

    /**
     *  获取用户的app设备推送信息
     *  @author yuhang.weng 
     *  @DateTime 2017年5月17日 下午3:32:13
     *
     *  @param userId
     *  @return
     */
    PushDataDTO getAppPushData(@Param("userId") int userId);
    
    /**
     *  添加一条app设备推送信息记录
     *  @author yuhang.weng 
     *  @DateTime 2017年5月17日 下午3:32:42
     *
     *  @param pushData
     */
    int addAppPushData(PushDataDTO pushData);
    
    /**
     *  删除app设备推送信息记录
     *  @author yuhang.weng 
     *  @DateTime 2017年5月17日 下午3:33:07
     *
     *  @param id
     */
    int deleteAppPushData(@Param("id") int id);
    
    /**
     *  删除用户app设备推送信息记录
     *  @author yuhang.weng 
     *  @DateTime 2017年5月22日 下午5:59:24
     *
     *  @param userId
     *  @return
     */
    int deleteAppPushDataByUserId(@Param("userId") int userId);
    
    /**
     *  删除设备app设备推送信息记录
     *  @author yuhang.weng 
     *  @DateTime 2017年5月22日 下午6:00:47
     *
     *  @param deviceToken
     *  @return
     */
    int deleteAppPushDataByDeviceToken(@Param("deviceToken") String deviceToken);
    
    /**
     *  获取测量提醒任务
     *  @author yuhang.weng 
     *	@DateTime 2017年5月23日 下午3:04:39
     *
     *  @param day
     *  @return
     */
    List<MeasureReminderTaskDTO> listMeasureReminderTask(@Param("day") Integer day);
    
    int addPushTask(PushTaskDTO data);
    
    /**
     * 获取满足条件的定时任务
     * @param weekDay
     * @return
     */
	List<MeasureReminderTaskPo> MemberserviceRemind(@Param("day")Integer Day);
}
