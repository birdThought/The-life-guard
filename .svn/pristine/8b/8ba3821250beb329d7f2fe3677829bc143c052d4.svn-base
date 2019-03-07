package com.lifeshs.service1.push.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lifeshs.common.constants.common.PushMsgType;
import com.lifeshs.po.UserPO;
import com.lifeshs.service1.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.push.PushMessageDao;
import com.lifeshs.po.push.PushMessagePO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.service1.push.PushMessageService;
import com.lifeshs.thirdservice.UMengPushService;
/**
 * 推送消息
 * author: wenxian.cai
 * date: 2017/8/24 11:16
 */

@Service("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService{

    @Autowired
    PushMessageDao pushMessageDao;

    @Autowired
	UMengPushService uMengPushService;

    @Autowired
	PushDataService pushDataService;

    @Autowired
	IMemberService memberService;
    @Autowired
    private UMengPushService umengPushService;

    @Override
    public void addPushMessage(PushMessagePO pushMessagePO) throws OperationException {
        try {
            pushMessageDao.addPushMessage(pushMessagePO);
            //todo 添加到t_message表
        } catch (Exception e) {
            throw new OperationException(Error.ADD_FAILED, ErrorCodeEnum.FAILED);
        }

    }

    @Override
    public Paging<PushMessagePO> listStorePushMessage(UserType userType, int sendId, List sendType, int pageIndex, int pageSize) throws OperationException {
        try {
            Paging<PushMessagePO> paging = new Paging<>(pageIndex, pageSize);
            paging.setQueryProc(new IPagingQueryProc<PushMessagePO>() {
                @Override
                public int queryTotal() {
                    return pushMessageDao.countPushMessage(userType.getValue(), sendId, Arrays.asList(PushMsgType.store.getValue()), sendType);
                }

                @Override
                public List<PushMessagePO> queryData(int startRow, int pageSize) {
					List<PushMessagePO> list = pushMessageDao.findPushMessageList(userType.getValue(), sendId, Arrays.asList(PushMsgType.store.getValue()), sendType, startRow, pageSize);
                	for (PushMessagePO po : list){
                		String userIds = po.getReceiveId();
                		String[] ids = userIds.split(";");
                		int[] newIds = new int[ids.length];
                		int j = 0;
                		for (String id : ids) {
							newIds[j] = Integer.valueOf(id);
							j ++;
						}
						List<UserPO> users = memberService.listUserByIds(newIds);
						int i = 0;
						String[] names = new String[users.size()];
						for (UserPO user : users) {
							names[i] = user.getRealName() == null ? user.getUserName() : user.getRealName();
							i ++;
						}
						po.setUserName(names);
					}
					return list;
                }
            });
            return paging;
        } catch (Exception e) {

            throw new OperationException(Error.ADD_FAILED, ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void deletePushMessage(int id, int sentId) throws OperationException {
        try {
            pushMessageDao.delPushMessage(id, sentId);
        } catch (Exception e) {
            throw new OperationException(Error.ADD_FAILED, ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void batchDeletePushMessage(List<Integer> ids, int sentId) throws OperationException {
        try {
            pushMessageDao.delPushMessageList(ids, sentId);
        } catch (Exception e) {
            throw new OperationException(Error.ADD_FAILED, ErrorCodeEnum.FAILED);
        }
    }

}
