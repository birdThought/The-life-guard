package com.lifeshs.service1.business;

import java.util.List;

import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.business.BusinessPo;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.BusinessSharingDataVO;


/**
 *  渠道商用户服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月9日 上午11:54:04
 */
public interface UserService {

    /**
     *  获取一个用户
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 上午11:29:10
     *
     *  @param userId 用户id
     *  @return
     */
    BusinessUserPO getUser(int userId);

    /**
     * 根据用户名获取一个用户
     * author: wenxian.cai
     * date: 2017/9/25 15:24
     */
    BusinessUserPO getUserByUserName(String userName);

    /**
     * 存储用户到缓存
     * @param po
     * @throws OperationException
     */
    void saveUserSharingData(BusinessSharingDataVO po) throws OperationException;

    /**
     * 从缓存获取用户信息
     * @param userId
     * @return
     */
    BusinessSharingDataVO getUserSharingData(int userId);

    /**
     * 更新用户缓存信息
     * @throws OperationException
     */
    void updateUserSharingData(int userId) throws OperationException;
    
    /**
     *  更新用户信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月9日 上午11:32:40
     *
     *  @param user
     *  @throws BaseException
     */
    void updateUser(BusinessUserPO user) throws BaseException;

    /**
     * 获取用户列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Paging<BusinessPo> listUser(int pageIndex, int pageSize);

    /**
     *  获取用户列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月24日 上午10:14:42
     *
     *  @param name 名称
     *  @return
     */
    List<BusinessPo> listUser(String name);
    
    /**
     *
     * @param userId
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(int userId, String oldPassword, String newPassword) throws OperationException;
    
    /**
     * 添加渠道商
     * @author shiqiang.zeng
     * @date 2018年1月4日
     */
    
    int addBusiness(BusinessUserPO businessUserPO)throws OperationException;
    
    /**
     * 更改渠道商
     * @param
     * @throws OperationException
     */
    void updateBusiness(String userName,String name,String address,String phone,String email,Integer id) throws OperationException;

    int findById(Integer orgId);

    /**
     *  添加渠道商机构及开通登录用户
     * @param bp
     * @param userName
     * @param password
     * @return
     */
    int saveBusiness(BusinessPo bp, String userName, String password);

    /**
     * 添加推销员
     * @param id
     * @param bup
     * @return
     */
    int addbusinessUser(Integer id, BusinessUserPO bup);
}
