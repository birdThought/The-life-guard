package com.lifeshs.service1.record;

import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.member.UserVo;
import com.lifeshs.vo.member.memberCountVo;

/**
 * @author Administrator
 * @create 2018-01-04
 * 12:02
 * @desc
 */
public interface UserRecordService {

    /**
     *  获取用户数据
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<UserVo> listpackage(String userNo,Integer agentId, String userName,String realName,String orgName,String mobile,int curPage, int pageSize);

    /**
     *  查询数据
     * @param province
     * @param users
     * @param gender
     * @param startAge
     * @param endAge
     * @param weight
     * @param label
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    Paging<StatisticsVO> findDataStatisticsList(String province,Integer users,Integer gender,String startAge,String endAge,Integer weight,
                                                Integer label,String keyword,Integer page,Integer pageSize);

    /**
     * 统计用户总数
     * @param orgName
     * @param province
     * @param avgAge
     * @param radioValue
     * @param page
     * @param pageSize
     * @return
     */
    Paging<memberCountVo> getCountData(String userNo, String orgName, String province, Integer avgAge, Integer radioValue, Integer page, Integer pageSize);

    memberCountVo findByGender(String userNo);
}
