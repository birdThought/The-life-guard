package com.lifeshs.dao1.measure;

import com.lifeshs.po.UserInfoRead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoRead record);

    int insertSelective(UserInfoRead record);

    UserInfoRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoRead record);

    int updateByPrimaryKey(UserInfoRead record);

    /**
     * 根据会员ID和测量时间获取userInfoRead List
     *
     * @param userId
     * @param measureDate
     * @return
     */
    List<UserInfoRead> selectByUserId(@Param("userId") Integer userId, @Param("measureDate") String measureDate);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<UserInfoRead> list);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int batchUpdate(@Param("list") List<UserInfoRead> list);

    /**
     *
     * @param userId
     * @param orgUserId
     * @param measureDate
     * @return
     */
    UserInfoRead selectByUserIdAndOrgUserId(@Param("userId") int userId,
                                                  @Param("orgUserId") Integer orgUserId,
                                                  @Param("measureDate") String measureDate);

    /**
     * 将会员的消息设置为已读
     *
     * @param userId
     * @param orgUserId
     * @param measureDate
     * @return
     */
    int readUserInfo(@Param("userId") Integer userId,
                     @Param("orgUserId") Integer orgUserId,
                     @Param("measureDate") String measureDate);
}