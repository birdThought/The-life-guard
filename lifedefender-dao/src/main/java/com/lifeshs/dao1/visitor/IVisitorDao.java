package com.lifeshs.dao1.visitor;

import com.lifeshs.po.visitor.FeedBackPO;
import org.springframework.stereotype.Repository;

/**
 * 游客相关操作dao
 * author: wenxian.cai
 * date: 2017/11/30 14:48
 */
@Repository("iVisitorDao")
public interface IVisitorDao {

	/**
	 * 添加游客反馈信息
	 * author: wenxian.cai
	 * date: 2017/11/30 14:49
	 */
	int addFeedBack(FeedBackPO po);
}
