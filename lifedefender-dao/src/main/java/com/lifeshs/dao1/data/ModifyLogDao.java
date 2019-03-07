package com.lifeshs.dao1.data;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.ModifyLogPO;

/**
 *  字典表修改记录dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月22日 下午4:30:45
 */
@Repository(value = "dataModifyLogDao")
public interface ModifyLogDao {

    /**
     *  获取最新的更新记录
     *  @author yuhang.weng 
     *  @DateTime 2017年8月22日 下午4:33:48
     *
     *  @param datePoint 时间节点
     *  @return
     */
    List<ModifyLogPO> findLatestLogList(@Param("datePoint") Date datePoint);
}
