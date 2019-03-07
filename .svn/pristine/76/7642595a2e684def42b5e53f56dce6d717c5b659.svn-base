package com.lifeshs.dao1.measure;

import com.lifeshs.po.SportBandPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 运动腕带
 */
@Repository
public interface SportBandDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SportBandPO record);

    int insertSelective(SportBandPO record);

    SportBandPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportBandPO record);

    int updateByPrimaryKey(SportBandPO record);

    /**
     * 返回指定月份存在测量数据的日期集合
     *
     * @param userId
     * @param queryDate 查询月的第一天,例如:2017-05-01
     * @return
     */
    List<SportBandPO> selectDatesByUserId(@Param("userId")Integer userId, @Param("queryDate") String queryDate);

    Date getLastDateByUserId(@Param("userId") Integer userId);
}