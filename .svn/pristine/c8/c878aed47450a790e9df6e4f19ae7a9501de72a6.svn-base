package com.lifeshs.service1.message;

import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service1.page.Paging;

public interface MessageService {

    /**
     *  获取系统消息
     *  @author yuhang.weng 
     *  @DateTime 2017年6月26日 下午1:45:48
     *
     *  @param userId
     *  @param userType
     *  @param pageIndex
     *  @param pageSize
     *  @return
     */
    Paging<MessageDTO> listSystemMessage(int userId, UserType userType, int pageIndex, int pageSize);
    
    /**
     *  系统消息状态修改为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年6月26日 下午2:02:00
     *
     *  @param id
     *  @param userId
     *  @param userType
     *  @exception OperationException
     *  @return
     */
    void updateSystemMessageToRead(int id, int userId, UserType userType) throws OperationException;
    
    /**
     *  删除消息
     *  @author yuhang.weng 
     *  @DateTime 2017年6月26日 下午2:01:19
     *
     *  @param id
     *  @param userId
     *  @param userType
     *  @exception OperationException
     *  @return
     */
    void deleteMessage(int id, int userId, UserType userType) throws OperationException;
    
    /**
     *  批量删除消息
     *  @author yuhang.weng 
     *  @DateTime 2017年6月26日 下午2:54:58
     *
     *  @param ids
     *  @param userId
     *  @param userType
     *  @exception OperationException
     *  @return
     */
    void deleteMessage(List<Integer> ids, int userId, UserType userType) throws OperationException;
    
    /**
     *  获取系统未读消息数量
     *  @author yuhang.weng 
     *  @DateTime 2017年6月26日 下午2:51:41
     *
     *  @param userId
     *  @param userType
     *  @return
     */
    int countSystemUnreadMessage(int userId, UserType userType);
    
    /**
     * 添加消息
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年5月8日 下午5:03:58
     *
     *  @param receiverId
     *  @param receiverType
     *  @param title
     *  @param content
     *  @param openTarget
     *  @param openAttach
     *  @param msgType
     *  @return
     *  @throws OperationException
     */
     
    int saveMessage(int receiverId, UserType receiverType, String title, String content, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, MessageType msgType) ;
            
    /**
     *  保存多条消息
     *  @author yuhang.weng 
     *  @DateTime 2017年7月21日 上午10:10:15
     *
     *  @param messageList
     */
    void saveMessage(List<MessageDTO> messageList, MessageType type);

    void saveMessage(List<Integer> userList, UMengOpenTypeEnum openType, UserType userType, String title, String content, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, MessageType msgType);

    /**
     * @Description: 获取门店推送消息
     * @Author: wenxian.cai
     * @Date: 2017/7/12 18:04
     */
    Paging<MessageDTO> listStorePushMessage(int userId, UserType userType, int pageIndex, int pageSize);

    /**
     * 获取订单消息
     * @param userId
     * @param userType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Paging<MessageDTO> listOrderMessage(int userId, UserType userType, int pageIndex, int pageSize);

    /**
     * 获取所有未读消息
     * @param userId
     * @return
     */
    int countUnreadMessage(int userId, UserType userType) throws DataBaseException;

    /**
     * 获取订单未读消息
     * @param userId
     * @param userType
     * @return
     */
    int countOrderUnreadMessage(int userId, UserType userType);
}
