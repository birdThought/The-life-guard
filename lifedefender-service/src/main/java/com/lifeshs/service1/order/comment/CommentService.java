package com.lifeshs.service1.order.comment;

import com.lifeshs.common.exception.common.OperationException;

public interface CommentService {

    /**
     *  提交评论
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:04:17
     *
     *  @return
     */
    int commitComment(int orderId, int score, int userId) throws OperationException;
    
    /**
     *  回复评论
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:04:10
     *
     *  @param serveUserId 服务师ID
     *  @return
     */
    boolean replyComment(int orderId, String reply, int serveUserId) throws OperationException;
}
