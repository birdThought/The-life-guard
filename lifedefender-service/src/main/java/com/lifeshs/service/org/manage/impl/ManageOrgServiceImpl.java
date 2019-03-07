package com.lifeshs.service.org.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.org.lesson.ILessonGroupDao;
import com.lifeshs.dao.org.manage.ManageOrgDao;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.service.org.manage.IManageOrgService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.thirdservice.HuanXinService;

/**
 * 
 *  管理机构
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2019年1月19日 上午11:58:45
 */

@Service("manageOrgService")
public class ManageOrgServiceImpl  implements IManageOrgService{

    @Autowired
    ManageOrgDao manageOrgDao;

    @Autowired
    ILessonGroup lessonGroup;

    @Autowired
    HuanXinService huanxinService;

    @Autowired
    ILessonGroupDao lesson;

    @Override
    public Paging<TOrg> listManageOrg(Integer orgId, String orgName, Integer pageIndex, Integer pageSize) {

        Paging<TOrg> p = new Paging<>(pageIndex, pageSize);
        p.setQueryProc(new IPagingQueryProc<TOrg>() {
            @Override
            public int queryTotal() {
                    return manageOrgDao.getCountOfOrg(orgId, orgName);
            }

            @Override
            public List<TOrg> queryData(int startRow, int pageSize) {
                return manageOrgDao.listManageOrg(orgId, orgName, (pageIndex - 1) * pageSize, pageSize);
            }
        });
        return p;
    }
    
    @Override
    public List<TOrg> findOrgListByOrgId(Integer orgId){
        return manageOrgDao.findOrgListByOrgId(orgId);
    }
    
    @Override
    public List<OrgUserPO> findOrgUserByOrgId(Integer orgId){
        return manageOrgDao.findOrgUserByOrgId(orgId);
    }
    
    @Override
    public int updateOrgByMobile(Integer orgId, String mobile){
        return manageOrgDao.updateOrgByMobile(orgId, mobile);
    }

	@Override
	public TOrg getOrgById(int orgId) {
		
		return manageOrgDao.getOrgById(orgId);
	}
}
