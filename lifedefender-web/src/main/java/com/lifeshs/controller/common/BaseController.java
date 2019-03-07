package com.lifeshs.controller.common;

import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;

import com.lifeshs.service.common.impl.transform.CommonTransImpl;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.tool.ICacheService;

import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author zhansi.Xu
 * @DateTime 2016年9月5日
 * @Comment 公共控制器
 */
public class BaseController {
    @Autowired
    private ISessionManageService sessionManageService;
    @Autowired
    protected IDataAreaService dataAreaService;
    @Autowired
    protected CommonTransImpl commonTrans;
    @Autowired
    protected ICacheService cacheService;

    @Autowired
    private IOrgUserService orgUserService;
    /**
     * 获取当前用户
     */
    protected LoginUser getLoginUser() {
    	LoginUser user =  ClientManager.getSessionUser();
        orgUserService.tobeStoreCheck(user);
        return user;
    }

    /**
     * 获取用户共享数据
     *
     * @author yuhang.weng
     * @DateTime 2016年9月5日 上午9:09:46
     *
     * @param userId
     * @return
     */
    protected MemberSharingData getCacheMemberSharingData(Integer userId) {
        return sessionManageService.getCacheMemberSharingData(userId);
    }

    protected void updateLoginUserData() {
        LoginUser user = getLoginUser();
        int userId = user.getId();
        sessionManageService.updateLoginUser(userId, 0); // 更新session中的loginUser数据
    }

    protected void updateOrgUserData() {
        LoginUser user = getLoginUser();
        int userId = user.getId();
        sessionManageService.updateLoginUser(userId, 1); // 更新session中的loginUser数据
    }

    protected MemberSharingData getMemberSharingData() {
        LoginUser user = getLoginUser();
        int userId = user.getId();

        return sessionManageService.getCacheMemberSharingData(userId);
    }

    protected OrgUserSharingData getOrgUserSharingData() {
        LoginUser user = getLoginUser();
        int userId = user.getId();

        return sessionManageService.getCacheOrgMemberSharingData(userId);
    }
}
