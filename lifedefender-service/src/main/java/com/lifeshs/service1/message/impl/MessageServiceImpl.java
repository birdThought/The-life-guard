package com.lifeshs.service1.message.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.dao.message.MessageDao;
import com.lifeshs.pojo.message.MessageDTO;
import com.lifeshs.service1.message.MessageService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

@Service("v2MessageService")
public class MessageServiceImpl implements MessageService{

    @Resource(name = "messageDao")
    private MessageDao messageDao;

    @Override
    public Paging<MessageDTO> listSystemMessage(int userId, UserType userType, int pageIndex, int pageSize) {
        Paging<MessageDTO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<MessageDTO>() {
            @Override
            public int queryTotal() {
                return messageDao.countMessage(userId, userType.getValue(), Arrays.asList(MessageType.SYSTEM.value(), MessageType.ORDER.value()), null);
            }

            @Override
            public List<MessageDTO> queryData(int startRow, int pageSize) {
                return messageDao.listMessage(userId, userType.getValue(), Arrays.asList(MessageType.SYSTEM.value(), MessageType.ORDER.value()), null, startRow, pageSize);
            }
        });
        
        return paging;
    }

    @Override
    public void updateSystemMessageToRead(int id, int userId, UserType userType) throws OperationException {
        MessageDTO data = new MessageDTO();
        data.setId(id);
        data.setUserId(userId);
        data.setRead(true);
        data.setUserType(userType.getValue());
        int result = messageDao.updateMessage(data);
        if (result == 0) {
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }

    /**
     * 删除系统消息
     *
     * @param id 消息ID
     * @param userId 用户ID
     * @param userType 用户类型
     * @return
     */
    @Override
    public void deleteMessage(int id, int userId, UserType userType) throws OperationException {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        int effectRow = messageDao.deleteMessage(ids, userId, userType.getValue(), null);
        if (effectRow == 0) {
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void deleteMessage(List<Integer> ids, int userId, UserType userType) throws OperationException {
        int effectRow = messageDao.deleteMessage(ids, userId, userType.getValue(), MessageType.SYSTEM.value());
        if (effectRow == 0) {
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
    }

    /**
     *
     * @updateBy wuj 2017-09-06 14:29:41
     * @updateReason 订单消息也加入到未读消息
     *
     * @param userId 用户Id
     * @param userType 用户类型
     * @return
     */
    @Override
    public int countSystemUnreadMessage(int userId, UserType userType) {
        int totalSize = messageDao.countMessage(userId, userType.getValue(),
                Arrays.asList(MessageType.SYSTEM.value(),MessageType.ORDER.value()),
                false);
        return totalSize;
    }
    
    /**
     * 添加消息
     *  服务注解  
     *  @author liaoguo
     *  @DateTime 2018年5月7日 下午2:28:05
     *  @serverCode 服务代码
     *  @param receiverId 发送者ID
     *  @param receiverType 用户类型
     *  @param title 标题
     *  @param content 内容
     *  @param openTarget 打开指定URL
     *  @param openAttach 附加消息,格式：key1:value1,key2:value2
     *  @param openType 打开类型：1查看消息,2打开指定APP页面,3打开指定URL
     *  @param msgType 消息类型，1_系统消息，2_服务消息，3_门店推送，4_服务师推送，5_订单消息
     *  @return
     *  @throws OperationException    
     */
    @Override
    public int saveMessage(int receiverId, UserType receiverType, String title, String content, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, MessageType msgType) {
        
        MessageDTO data = new MessageDTO();
        data.setUserId(receiverId);
        data.setUserType(receiverType.getValue());
        data.setTitle(title);
        data.setContent(content);
        data.setMsgType(msgType.value());
        data.setOpenType(openType.value());
        data.setOpenTarget(openTarget);
        data.setOpenAttach(openAttach);
        data.setOpenTargetIOS(openTargetIOS);
        data.setOpenAttachIOS(openAttachIOS);

        int result = messageDao.saveMessage(data);
        return data.getId();
    }

    @Override
    public void saveMessage(List<MessageDTO> messageList, MessageType type) {
        for (MessageDTO m : messageList) {
            m.setMsgType(type.value());
        }
        messageDao.saveMessageList(messageList);
    }

    @Override
    public void saveMessage(List<Integer> userList, UMengOpenTypeEnum openType, UserType userType, String title, String content, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, MessageType msgType) {
        List<MessageDTO> messageList = new ArrayList<MessageDTO>();
        for(Integer userId : userList){
            MessageDTO data = new MessageDTO();
            data.setUserId(userId);
            data.setUserType(userType.getValue());
            data.setTitle(title);
            data.setContent(content);
            data.setMsgType(msgType.value());
            data.setOpenType(openType.value());
            data.setOpenAttach(openAttach);
            data.setOpenTarget(openTarget);
            data.setOpenTargetIOS(openTargetIOS);
            data.setOpenAttachIOS(openAttachIOS);
            messageList.add(data);
        }
        messageDao.saveMessageList(messageList);
    }

    @Override
    public Paging<MessageDTO> listStorePushMessage(int userId, UserType userType, int pageIndex, int pageSize) {
        Paging<MessageDTO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<MessageDTO>() {
            @Override
            public int queryTotal() {
                return messageDao.countMessage(userId, userType.getValue(), Arrays.asList(MessageType.STORE.value()), null);
            }

            @Override
            public List<MessageDTO> queryData(int startRow, int pageSize) {
                return messageDao.listMessage(userId, userType.getValue(), Arrays.asList(MessageType.STORE.value()), null, startRow, pageSize);
            }
        });

        return paging;
    }

    @Override
    public Paging<MessageDTO> listOrderMessage(int userId, UserType userType, int pageIndex, int pageSize) {
        Paging<MessageDTO> paging = new Paging<>(pageIndex, pageSize);
        paging.setQueryProc(new IPagingQueryProc<MessageDTO>() {
            @Override
            public int queryTotal() {
                return messageDao.countMessage(userId, userType.getValue(), Arrays.asList(MessageType.ORDER.value()), null);
            }

            @Override
            public List<MessageDTO> queryData(int startRow, int pageSize) {
                return messageDao.listMessage(userId, userType.getValue(), Arrays.asList(MessageType.ORDER.value()), null, startRow, pageSize);
            }
        });

        return paging;
    }

    @Override
    public int countUnreadMessage(int userId, UserType userType) {
        int totalSize = messageDao.countMessage(userId, userType.getValue(), null, false);
        return totalSize;
    }

    @Override
    public int countOrderUnreadMessage(int userId, UserType userType) {
        int totalSize = messageDao.countMessage(userId, userType.getValue(), Arrays.asList(MessageType.ORDER.value()), false);
        return totalSize;
    }

}
