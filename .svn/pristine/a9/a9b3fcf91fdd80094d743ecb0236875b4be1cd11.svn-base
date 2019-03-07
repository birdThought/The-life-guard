package com.lifeshs.dao.org.offline;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.user.UserPO;

/**
 * 
 *  线下用户管理
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年12月12日  19:41
 */

@Repository(value = "offlineManageDao")
public interface IOfflineManageDao {

    /**
     * @Description: 获取线下用户数量
     * @Author: liaoguo
     * @Date: 2018/12/12 19:41
     */
    int getCountOfOffline(@Param(value = "userNo") String userNo, @Param(value = "realName") String realName, @Param(value = "mobile") String mobile);
    
    /**
     * @Description: 获取线下用户列表
     * @Author: liaoguo
     * @Date: 2018/12/12 19:41
     */
    List<UserPO> listOffile(@Param(value = "userNo") String userNo, @Param(value = "realName") String realName, 
            @Param(value = "mobile") String mobile, @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);

}
