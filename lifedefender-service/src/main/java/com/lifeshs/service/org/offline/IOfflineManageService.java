package com.lifeshs.service.org.offline;

import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.page.PaginationDTO;

/**
 * 
 *  线下用户管理
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年12月12日 下午7:31:25
 */
public interface IOfflineManageService {

    /**
     * 
     *  服务注解
     *  @author liaoguo
     *  @DateTime 2018年12月12日 下午7:35:51
     *
     *  @param userNo
     *  @param realName
     *  @param mobile
     *  @param pageIndex
     *  @param pageSize
     *  @return
     */
    PaginationDTO<UserPO> listOffile(String userNo, String realName,String mobile, Integer pageIndex, Integer pageSize);
    

}
