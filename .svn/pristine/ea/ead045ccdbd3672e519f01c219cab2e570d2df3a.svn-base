package com.lifeshs.dao1.measure;

import com.lifeshs.po.OxygenPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 血氧仪
 */
@Repository
public interface OxygenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OxygenPO record);

    int insertSelective(OxygenPO record);

    OxygenPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OxygenPO record);

    int updateByPrimaryKey(OxygenPO record);

    List<OxygenPO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    OxygenPO oxygenDate(@Param("userId")Integer userId, @Param("date") String date);
}