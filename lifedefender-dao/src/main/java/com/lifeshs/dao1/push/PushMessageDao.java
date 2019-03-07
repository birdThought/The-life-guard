package com.lifeshs.dao1.push;

import com.lifeshs.po.push.PushMessagePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 推送信息操作
 * author: wenxian.cai
 * date: 2017/8/24 10:10
 */
@Repository("pushMessageDao")
public interface PushMessageDao {

    /**
     * 添加推送消息
     * @param pushMessagePO
     */
    void addPushMessage(PushMessagePO pushMessagePO);

    /**
     * 获取推送消息列表
     * @param userType
     * @param sendId
     * @param msgTypeList
     * @param sendType
     * @return
     */
    List<PushMessagePO> findPushMessageList(@Param("userType") int userType,
                                            @Param("sendId") int sendId,
                                            @Param("msgTypeList") List msgTypeList,
                                            @Param("sendTypeList") List sendType,
                                            @Param("pageIndex") int pageIndex,
                                            @Param("pageSize") int pageSize);

    /**
     * 获取推送消息数目
     * @param userType
     * @param sendId
     * @param msgTypeList
     * @param sendType
     * @return
     */
    int countPushMessage(@Param("userType") int userType,
                                            @Param("sendId") int sendId,
                                            @Param("msgTypeList") List msgTypeList,
                                            @Param("sendTypeList") List sendType);

    /**
     * 删除推送消息
     * @param id
     */
    void delPushMessage(@Param("id") int id, @Param("sendId") int sendId);

    /**
     * 批量删除推送消息
     * @param id
     */
    void delPushMessageList(@Param("ids") List<Integer> ids, @Param("sendId") int sendId);

}
