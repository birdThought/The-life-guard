package com.lifeshs.service.common;

import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.pojo.member.UserDTO;

public interface IBaseUser {

    /**
     *  @author dachang.luo 
     *  @DateTime 2016-5-13 上午10:21:29
     *  @serverComment 用户注册
     *
     *  @param <T>
     *  @param entity 用户实体
     *  @return
     * @throws Exception 
     */
    boolean addMember(UserDTO entity);
    
    /**
     * 
     *  @author dachang.luo 
     *  @DateTime 2016-5-13 上午10:19:55
     *  @serverComment 修改基本资料
     *
     *  @param <T> 
     *  @param entity 用户实体
     *  @return
     */
    <T> boolean basicInformation(T entity);
    
    /**
     * 
     *  @author dachang.luo 
     *  @DateTime 2016-5-13 下午02:36:27
     *  @serverComment 根据邮箱判断用户是否存在（邮箱必须是已验证邮箱）
     *
     *  @param email 邮箱
     *  @return
     */
    boolean checkEmail(String email);
    
    /**
     * 
     *  @author dachang.luo 
     *  @DateTime 2016-5-13 下午03:05:00
     *  @serverComment 根据手机判断用户是否存在(默认手机号已验证）
     *
     *  @param moblie 手机号
     *  @return
     * @throws Exception 
     */
    boolean checkMobile(String moblie);

    void saveSensitiveLog(SensitiveOperationLogDTO log);
}
