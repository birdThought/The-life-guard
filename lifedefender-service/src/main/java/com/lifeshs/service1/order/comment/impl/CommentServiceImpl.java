package com.lifeshs.service1.order.comment.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.order.CommentDao;
import com.lifeshs.pojo.order.OrderCommentDTO;
import com.lifeshs.service1.order.comment.CommentService;

@Service(value = "orderCommentService")
public class CommentServiceImpl implements CommentService {

    @Resource(name = "orderCommentDao")
    private CommentDao commentDao;
    
    @Override
    public int commitComment(int orderId, int score, int userId) throws OperationException {
        /**
         * TODO orderId与userId判断订单是否存在，不存在就抛出异常
         * if (order == null || order.getUserId() != userId ) {
         *    throw new OperationException("该订单不存在", 404);
         * }
        */
        
        OrderCommentDTO data = commentDao.getCommentByOrderId(orderId);
        if (data != null) {
            throw new OperationException("请勿重复提交评论", 400);
        }
        
        OrderCommentDTO comment = new OrderCommentDTO();
        comment.setOrderId(orderId);
        comment.setScore(score);
        commentDao.save(comment);
        return comment.getId();
    }

    @Override
    public boolean replyComment(int orderId, String reply, int serveUserId) throws OperationException {
        /**
         * TODO orderId与serveUserId判断订单是否存在，不存在就抛出异常
         * if (order == null || order.getServeUserId() != serveUserId ) {
         *    throw new OperationException("该订单不存在", 404);
         * }
        */
        
        OrderCommentDTO data = commentDao.getCommentByOrderId(orderId);
        if (data == null) {
            throw new OperationException("找不到该订单的评论", 404);
        }
        // true表示该订单已回复
        if (data.isStatus()) {
            throw new OperationException("请勿重复回复该评论", 400);
        }
        
        int effectRow = 0;
        OrderCommentDTO comment = new OrderCommentDTO();
        comment.setOrderId(orderId);
        comment.setReply(reply);
        comment.setStatus(true);
        comment.setReplyDate(new Date());
        
        effectRow = commentDao.update(comment);
        
        return (effectRow == 1);
    }
}
