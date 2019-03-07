package com.lifeshs.dao1.measure;

import com.lifeshs.po.TemperaturePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 体温
 */
@Repository
public interface TemperatureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TemperaturePO record);

    int insertSelective(TemperaturePO record);

    TemperaturePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TemperaturePO record);

    int updateByPrimaryKey(TemperaturePO record);

    List<TemperaturePO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);
}