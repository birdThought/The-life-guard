package com.lifeshs.service.member.impl;

import com.lifeshs.dao.org.user.OrgUserDao;
import com.lifeshs.dao.user.UserDao;
import com.lifeshs.dao1.member.FeedbackreportDao;
import com.lifeshs.po.ReportPo;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.service.member.FeedbackreportService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.member.FeedbackreportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-31
 * 16:38
 * @desc
 */
@Service("feedbackreportService")
public class FeedbackreportServiceImpl implements FeedbackreportService {

    @Autowired
    private FeedbackreportDao feedbackreportDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrgUserDao orgUserDao;

    @Override
    public Paging<FeedbackreportVo> findByReportAll(String userNo,int page, int pageSize) {
        Paging<FeedbackreportVo> p = new Paging<>(page,pageSize);
        p.setQueryProc(new IPagingQueryProc<FeedbackreportVo>() {
            @Override
            public int queryTotal() {
                return feedbackreportDao.findByFeedbacCount(userNo);
            }

            @Override
            public List<FeedbackreportVo> queryData(int startRow, int pageSize) {
                return feedbackreportDao.findByFeedbackAll(userNo, (page -1) * pageSize,pageSize);
            }
        });
        return p;
    }

    @Override
    public void delId(Integer id) {
        feedbackreportDao.delete(id);
    }

    @Override
    public UserPO findByUserData(Integer userId) {
        return userDao.getFullUserById(userId);
    }

    @Override
    public OrgUserPO findByUSerOrgData(Integer userId) {
        return orgUserDao.findUserByVerifyId(userId);
    }

    @Override
    public void updateReportData(Integer id, String report) {
        ReportPo fk =  feedbackreportDao.findById(id);
        fk.setReply(report);
        feedbackreportDao.updateReport(fk);
    }
}
