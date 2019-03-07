package com.lifeshs.dao1.push;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.push.UserDeviceTokenPO;

/**
 *  用户推送设备token
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月16日 下午5:42:55
 */
@Repository(value = "userPushDeviceTokenDao")
public interface UserDeviceTokenDao {

    /**
     *  获取用户的设备推送token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月16日 下午5:42:46
     *
     *  @param userId
     *  @return
     */
    UserDeviceTokenPO findDeviceTokenByUserId(@Param("userId") int userId);
    
    /**
     *  获取用户的设备推送token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午10:37:58
     *
     *  @param userIdList 用户id集合
     *  @return
     */
    List<UserDeviceTokenPO> findDeviceTokenByUserIdList(@Param("userIdList") List<Integer> userIdList);
    
    /**
     * 根据查询条件获取用户的设备推送token
     * @param projectCode
     * @param diseasesId
     * @param gender 0/1
     * @param startAge '2017-01-01'
     * @param endAge '2017-01-01'
     * @param orgId
     * @return
     */
    List<UserDeviceTokenPO> findUserDeviceTokenPOList(@Param("projectCode") String projectCode,
                                          @Param("diseasesId") Integer diseasesId,
                                          @Param("gender") Integer gender,
                                          @Param("startAge") String startAge,
                                          @Param("endAge") String endAge,
                                          @Param("mobile") String mobile,
                                          @Param("orgId") Integer orgId);
    
    /**
     *  添加用户的推送设备token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月16日 下午2:34:11
     *
     *  @param token
     *  @return
     */
    int addDeviceToken(UserDeviceTokenPO token);
    
    /**
     *  删除用户的推送设备token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月16日 下午2:34:48
     *
     *  @param userId
     *  @return
     */
    int delDeviceTokenByUserId(int userId);
    
    /**
     * 
     *  修改用户的推送设备token状态
     *  @author liaoguo
     *  @DateTime 2018年6月5日 下午5:22:58
     *
     *  @param userId
     *  @param OS
     *  @param deviceToken
     *  @param systemVersion
     *  @param display
     *  @return
     */
    int updateTokenByUserId(@Param("userId") int userId,@Param("OS") int OS, @Param("deviceToken") String deviceToken,
            @Param("systemVersion") String systemVersion,@Param("display") int display);
    
    /**
     * 
     *  查看是否存在设备记录
     *  @author liaoguo
     *  @DateTime 2018年6月5日 下午3:10:05
     *
     *  @param userId
     *  @param OS
     *  @param deviceToken
     *  @param systemVersion
     *  @return
     */
    UserDeviceTokenPO findDeviceToken(@Param("userId") int userId,@Param("OS") int OS,
            @Param("deviceToken") String deviceToken,@Param("systemVersion") String systemVersion);
    
    List<UserDeviceTokenPO> findDeviceTokenList(@Param("userId") int userId);
    
}
