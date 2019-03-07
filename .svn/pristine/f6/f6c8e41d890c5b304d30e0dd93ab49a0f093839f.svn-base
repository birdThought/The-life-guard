package com.lifeshs.dao1.measure;

import com.lifeshs.po.UaPO;
import com.lifeshs.po.UranPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 尿检分析仪
 */
@Repository
public interface UranDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UranPO record);

    int insertSelective(UranPO record);

    UranPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UranPO record);

    int updateByPrimaryKey(UranPO record);

    List<UranPO> selectMeasureDatesByUserId(@Param("userId") Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);

    /**
     * 获取最后一条数据
     * @param userId
     * @return
     */
    UranPO getLastData(@Param("userId") int userId);
}