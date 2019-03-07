package com.lifeshs.service1.push;

import java.util.List;

import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.service1.page.Paging;

/**
 * 推送消息
 * author: wenxian.cai
 * date: 2017/8/24 11:00
 */
public interface PushMessageService {

    /**
     * 添加推送消息
     * @param pushMessagePO
     * @throws OperationException
     */
    void addPushMessage(PushMessagePO pushMessagePO) throws OperationException;

    /**
     * 获取门店推送消息
     * @param userType
     * @param sendId
     * @param sendType
     * @throws OperationException
     */
    Paging<PushMessagePO> listStorePushMessage(UserType userType, int sendId, List sendType, int pageIndex, int pageSize) throws OperationException;

    /**
     * 删除推送消息
     * @param id
     * @param sentId 发送者Id
     * @throws OperationException
     */
    void deletePushMessage(int id, int sentId) throws OperationException;

    /**
     * 批量删除推送消息
     * @param ids
     * @param sentId
     * @throws OperationException
     */
    void batchDeletePushMessage(List<Integer> ids, int sentId) throws OperationException;

//	/**
//	 * 门店向用户推送消息
//	 * @param userIdList
//	 * @param pushMessagePO
//	 */
//	void pushStoreMessage(List<Integer> userIdList, PushMessagePO pushMessagePO) throws OperationException;
}
