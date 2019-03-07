package com.lifeshs.dao1.transfer;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @create 2018-01-29
 * 15:02
 * @desc
 */
@Repository
public interface ThirdPartyDao {

    String findByAppsecert(@Param("appid") String appid);
}
