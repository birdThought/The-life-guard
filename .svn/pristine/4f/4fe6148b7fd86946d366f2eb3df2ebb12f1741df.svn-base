package com.lifeshs.dao1.measure;

import com.lifeshs.po.LungInstrumentPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 肺活仪
 */
@Repository
public interface LungInstrumentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LungInstrumentPO record);

    int insertSelective(LungInstrumentPO record);

    LungInstrumentPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LungInstrumentPO record);

    int updateByPrimaryKey(LungInstrumentPO record);

    List<LungInstrumentPO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    LungInstrumentPO lunginstrumentDate(@Param("userId") Integer userId,@Param("date")String date);
}