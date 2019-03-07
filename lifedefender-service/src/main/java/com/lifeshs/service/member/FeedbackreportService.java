package com.lifeshs.service.member;

import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.member.FeedbackreportVo;

/**
 * @author Administrator
 * @create 2018-01-31
 * 16:38
 * @desc
 */
public interface FeedbackreportService {

    Paging<FeedbackreportVo> findByReportAll(String userNo,int page, int pageSize);

    void delId(Integer id);

    UserPO findByUserData(Integer userId);

    OrgUserPO findByUSerOrgData(Integer userId);

    void updateReportData(Integer id, String report);
}
