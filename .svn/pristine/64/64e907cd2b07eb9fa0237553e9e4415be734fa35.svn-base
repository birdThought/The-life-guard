package com.lifeshs.service.org.offline.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.org.offline.IOfflineManageDao;
import com.lifeshs.po.user.UserPO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.org.offline.IOfflineManageService;

/**
 * 
 *  线下用户管理
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年12月12日 下午7:31:25
 **/

@Service("offlineManageService")
public class OfflineManageServiceImpl implements IOfflineManageService {

    @Autowired
    IOfflineManageDao offlineManageDao;

    @Override
    public PaginationDTO<UserPO> listOffile(String userNo, String realName, String mobile, Integer pageIndex, Integer pageSize) {
        PaginationDTO<UserPO> pagination = new PaginationDTO<>();
        List<UserPO> services = new ArrayList<>();
        
        int count = offlineManageDao.getCountOfOffline(userNo, realName, mobile);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;

        }
        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = offlineManageDao.listOffile(userNo, realName, mobile, startIndex, pageSize);
       
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }
}
