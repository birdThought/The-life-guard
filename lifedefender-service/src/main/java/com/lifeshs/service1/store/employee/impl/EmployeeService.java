package com.lifeshs.service1.store.employee.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.dto.manager.employee.UpdateMineInfoData;
import com.lifeshs.dto.manager.serve.GetLessonGroupData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.serve.ServeDao;
import com.lifeshs.dao1.employee.IEmployeeDao;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.dto.manager.employee.GetEmployeeListData;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.ServePO;
import com.lifeshs.service1.serve.ServeService;
import com.lifeshs.service1.store.employee.IEmployeeService;

/**
 * 门店员工业务类
 * Created by dengfeng on 2017/6/19 0019.
 */
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeDao employeeDao;

    @Autowired
    IProjectDao projectDao;

    @Autowired
    IOrderDao orderDao;
    /**
     *  获取机构员工数量
     *  @author yuhang.weng
     *	@DateTime 2017年6月6日 上午11:33:03
     *
     *  @param orgId 机构id
     *  @return
     */
    public int getEmployeeCount(Integer orgId){
        int count = employeeDao.getEmployeeCount(orgId);
        return count;
    }

    /**
     * 得到门店的员工列表
     * @return
     */
        public List<GetEmployeeListData> getEmployeeList(int orgId, int pageIndex, int pageSize, String search){
        if("".equals(search))
            search = null;
        return employeeDao.getEmployeeList(orgId, (pageIndex-1)*pageSize, pageSize, search);
    }

    /**
     * 得到单个员工的信息
     * @param employeeId
     * @return
     */
    public EmployeePO getEmployee(int employeeId){
        return employeeDao.getEmployee(employeeId);
    }

    /**
     * 得到服务师的服务项目列表
     * @param employeeId
     * @return
     */
    public List<ProjectPO> getProjectList(int employeeId){
        return projectDao.findProjectListByEmployee(employeeId);
    }

    /**
     * 获取健康课堂的服务师成员
     * @param projectCode
     * @return
     */
    public List<LessonUserInfo> findEmployeeListByLesson(String projectCode){
        return employeeDao.findEmployeeListByLesson(projectCode);
    }

    /**
     * 更新个人信息(仅地址、简介、专长）
     * @param employeeId
     * @param updateMineInfoData
     */
    public void updateEmployeeMine(int employeeId, UpdateMineInfoData updateMineInfoData){
        employeeDao.updateEmployeeMine(employeeId, updateMineInfoData.getAddress(), updateMineInfoData.getAbout(), updateMineInfoData.getExpertise());
    }

    /**
     * 员工离职
     * @param employeeId
     */
    public void leaveJob(int employeeId){
        employeeDao.leaveJob(employeeId);
    }

    @Override
    public EmployeePO getManage(Integer orgId) {
        return employeeDao.findManage(orgId);
    };

    @Override
    public List<Integer> findEmployeeListByUserId(Integer userId) {
        return employeeDao.findEmployeeListByUserId(userId);
    }




}
