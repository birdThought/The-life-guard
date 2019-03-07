package com.lifeshs.service1.record.impl;

import com.lifeshs.dao1.user.UserRecordDao;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.UserRecordService;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.member.UserVo;
import com.lifeshs.vo.member.memberCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-04
 * 13:49  获取用户列表
 * @desc
 */

@Service("userRecordService")
public class UserRecordServiceImpl implements UserRecordService {

    @Autowired
    private UserRecordDao userRecordDao;


    @Override
    public Paging<UserVo> listpackage(String userNo,Integer agentId, String userName,String realName,String orgName,String mobile, int curPage, int pageSize) {
        Paging<UserVo> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<UserVo>() {
            @Override
            public int queryTotal() {
//                if(agentId == 0)
//                    return userRecordDao.findByCountSum(userName,realName,orgName,mobile);
//                else
                    return userRecordDao.findByCountSumAgent(userNo, userName,realName,orgName,mobile);
            }

            @Override
            public List<UserVo> queryData(int startRow, int pageSize) {
                List<UserVo> byUserInfoList;
//                if(agentId == 0)
//                    byUserInfoList= userRecordDao.findByUserInfoList(userName,realName,orgName,mobile,(curPage - 1) * pageSize, pageSize);
//                else
                    byUserInfoList= userRecordDao.findByUserInfoListAgent(userNo, userName,realName,orgName,mobile,(curPage - 1) * pageSize, pageSize);
                Calendar cal = Calendar.getInstance();
                Calendar cld = Calendar.getInstance();
                List<UserVo> list = new ArrayList<>();
                for (UserVo vo : byUserInfoList){
                    Date b = vo.getBirthday();
                    if (b == null){
                        vo.setAge(0);
                        list.add(vo);
                    }else {
                        cld.setTime(new Date());//当前
                        cal.setTime(b);//生日
                        vo.setAge(cld.get(Calendar.YEAR) - cal.get(Calendar.YEAR));
                        list.add(vo);
                    }
                }
                return list;
            }
        });
        return p;
    }

    @Override
        public Paging<StatisticsVO> findDataStatisticsList(String province, Integer users, Integer gender, String startAge, String endAge, Integer weight, Integer label, String mobile, Integer page, Integer pageSize) {
            Paging<StatisticsVO> paging = new Paging<>(page, pageSize);
           paging.setQueryProc(new IPagingQueryProc() {
                @Override
                public int queryTotal() {
                    return userRecordDao.findByDataListSum(province, users, gender, startAge, endAge, weight, label, mobile);
                }
                @Override
                public List<StatisticsVO> queryData(int startRow, int pageSize) {
                    List<StatisticsVO> statisticsDetaList = userRecordDao.findStatisticsDetaList(province, users, gender, startAge, endAge, weight, label, mobile, (page - 1) * pageSize, pageSize);
                    Calendar cal = Calendar.getInstance();
                    Calendar cld = Calendar.getInstance();
                    List<StatisticsVO> list = new ArrayList<>();
                    for (StatisticsVO vo : statisticsDetaList) {
                        Date b = vo.getBirthday();
                        if (b == null){
                        vo.setAge(0);
                        list.add(vo);
                        }else {
                            cld.setTime(new Date());//当前
                            cal.setTime(b);//生日
                            vo.setAge(cld.get(Calendar.YEAR) - cal.get(Calendar.YEAR));
                            list.add(vo);
                        }
                    }
                    return list;
                }
            });
            return paging;
    }

    @Override
    public Paging<memberCountVo> getCountData(String userNo, String orgName, String province, Integer avgAge, Integer radioValue, Integer page, Integer pageSize) {
       Paging<memberCountVo> p = new Paging<>(page,pageSize);
       p.setQueryProc(new IPagingQueryProc<memberCountVo>() {
           @Override
           public int queryTotal() {
               return userRecordDao.findByCountData(userNo, orgName,province,avgAge,radioValue);
           }

           @Override
           public List<memberCountVo> queryData(int startRow, int pageSize) {
               return userRecordDao.findByCountDataList(userNo, orgName,province,avgAge,radioValue,(page - 1) * pageSize, pageSize);
           }
       });
        return p;
    }

    @Override
    public memberCountVo findByGender(String userNo) {
        return userRecordDao.countGenderSum(userNo);
    }
}
