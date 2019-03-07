package com.lifeshs.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.order.OrderCommentDTO;
import com.lifeshs.vo.order.comment.CommentVO;

/**
 *  订单评论dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月17日 下午5:12:19
 */
@Repository(value = "orderCommentDao")
public interface CommentDao {

    /**
     *  通过id获取订单评论
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:13:37
     *
     *  @param id
     *  @return
     */
    OrderCommentDTO get(@Param("id") int id);
    
    /**
     *  通过订单ID获取订单评论
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:13:35
     *
     *  @param orderId
     *  @return
     */
    OrderCommentDTO getCommentByOrderId(@Param("orderId") int orderId);
    
    /**
     *  获取项目的评论列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月5日 下午4:42:36
     *
     *  @param projectCode 项目code
     *  @param serveUserId 服务师id(可以为NULL不限制服务师id)
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<CommentVO> listCommentByProjectCode(@Param("projectCode") String projectCode,
            @Param("serveUserId") Integer serveUserId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    /**
     *  添加
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:13:41
     *
     *  @param comment
     */
    int save(OrderCommentDTO comment);
    
    /**
     *  更新
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午4:13:43
     *
     *  @param comment
     */
    int update(OrderCommentDTO comment);
}
