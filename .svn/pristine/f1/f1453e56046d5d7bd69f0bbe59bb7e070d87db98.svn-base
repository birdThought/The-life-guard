package com.lifeshs.dao1.push;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.push.OrgUserDeviceTokenPO;

/**
 *  机构用户推送设备token
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月16日 下午2:26:58
 */
@Repository(value = "orgUserPushDeviceTokenDao")
public interface OrgUserDeviceTokenDao {

    /**
     *  获取用户的推送设备token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月16日 下午2:33:48
     *
     *  @param userId
     *  @return
     */
    OrgUserDeviceTokenPO findDeviceTokenByUserId(@Param("userId") int userId);
    
    /**
     *  获取用户的推送设备token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月31日 上午10:30:59
     *
     *  @param userIdList 用户id集合
     *  @return
     */
    List<OrgUserDeviceTokenPO> findDeviceTokenByUserIdList(@Param("userIdList") List<Integer> userIdList);
    
    /**
     *  添加用户的推送设备token
     *  @author yuhang.weng 
     *  @DateTime 2017年8月16日 下午2:34:11
     *
     *  @param token
     *  @return
     */
    int addDeviceToken(OrgUserDeviceTokenPO token);
    
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
            @Param("systemVersion") String systemVersion,@Param("deleted") int deleted);
    
    /**
     * 
     *  查找是否纯在相同的登录记录
     *  @author liaoguo
     *  @DateTime 2018年6月5日 下午2:37:07
     *
     *  @param userId
     *  @param OS
     *  @param deviceToken
     *  @param systemVersion
     *  @return
     */
    OrgUserDeviceTokenPO findDeviceToken(@Param("userId") int userId,@Param("OS") int OS,@Param("deviceToken") String deviceToken,@Param("systemVersion") String systemVersion);
    
    List<OrgUserDeviceTokenPO> findDeviceTokenList(@Param("userId") int userId);
    
}
