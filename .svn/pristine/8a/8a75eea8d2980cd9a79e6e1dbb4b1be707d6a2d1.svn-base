package com.lifeshs.dao1.member;

import com.lifeshs.po.ReportPo;
import com.lifeshs.vo.member.FeedbackreportVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-31
 * 16:30
 * @desc
 */
@Repository("feedbackreportDao")
public interface FeedbackreportDao {

    List<FeedbackreportVo> findByFeedbackAll(@Param("userNo") String userNo,@Param("curPage")Integer curPage,@Param("pageSize")Integer pageSize);

    int findByFeedbacCount(@Param("userNo") String userNo);

    int delete(@Param("id")Integer id);

    ReportPo findById(Integer id);

    void updateReport(ReportPo fk);
}
