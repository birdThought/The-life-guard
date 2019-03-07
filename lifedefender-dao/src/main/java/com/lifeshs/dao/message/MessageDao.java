package com.lifeshs.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.message.MessageDTO;

/**
 *  消息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月12日 下午5:37:42
 */
@Repository(value = "messageDao")
public interface MessageDao {

    /**
     *  获取消息
     *  @author yuhang.weng 
     *	@DateTime 2017年5月12日 下午4:48:37
     *
     *  @param userId
     *  @param userType
     *  @param msgTypeList
     *  @param read
     *  @param startIndex
     *  @param pageSize
     *  @return
     */
    List<MessageDTO> listMessage(@Param("userId") int userId, @Param("userType") int userType,
            @Param("msgTypeList") List<Integer> msgTypeList, @Param("read") Boolean read, @Param("startIndex") int startIndex,
            @Param("pageSize") int pageSize);
    
    /**
     *  获取消息数量
     *  @author yuhang.weng 
     *	@DateTime 2017年5月12日 下午5:12:29
     *
     *  @param userId
     *  @param userType
     *  @param msgTypeList
     *  @param read
     *  @return
     */
    Integer countMessage(@Param("userId") int userId, @Param("userType") int userType,
            @Param("msgTypeList") List<Integer> msgTypeList, @Param("read") Boolean read);
    
    /**
     *  删除消息
     *  @author yuhang.weng 
     *	@DateTime 2017年5月15日 下午5:08:50
     *
     *  @param ids
     *  @param userId
     *  @param userType
     *  @param msgType
     */
    int deleteMessage(@Param("ids") List<Integer> ids, @Param("userId") int userId,
            @Param("userType") int userType, @Param("msgType") Integer msgType);
    
    /**
     *  更新消息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月13日 下午2:36:03
     *
     *  @param data
     *  @return
     */
    int updateMessage(MessageDTO data);
    
    /**
     *  添加一条消息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 上午9:26:34
     *
     *  @param data
     */
    int saveMessage(MessageDTO data);
    
    /**
     *  添加多条消息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 上午10:14:09
     *
     *  @param datas
     */
    int saveMessageList(@Param("datas") List<MessageDTO> datas);
}
