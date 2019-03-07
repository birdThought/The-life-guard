package com.lifeshs.dao.device;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  app版本dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月7日 下午3:23:23
 */
@Repository("IAppVersionDao")
public interface IAppVersionDao {

    /**
     * 查询app最大的内部版本号
     *
     * @return
     */
    Integer queryAppMaxVersion(@Param("appName") String appName);

    /**
     * 查询app最大的公开版本号
     * 
     * @author yuhang.weng
     * @DateTime 2017年4月7日 下午3:22:38
     *
     * @param appName
     * @return
     */
    String queryAppMaxPubliceVersion(@Param("appName") String appName);
}
