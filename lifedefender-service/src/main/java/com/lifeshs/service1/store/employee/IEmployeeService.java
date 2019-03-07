package com.lifeshs.service1.store.employee;

import com.lifeshs.dto.manager.employee.GetEmployeeListData;
import com.lifeshs.dto.manager.employee.UpdateMineInfoData;
import com.lifeshs.dto.manager.serve.GetLessonGroupData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.ServePO;

import java.util.List;

/**
 * 门店员工业务接口
 * Created by dengfeng on 2017/6/19 0019.
 */
public interface IEmployeeService {
    /**
     *  获取机构员工数量
     *  @author yuhang.weng
     *	@DateTime 2017年6月6日 上午11:33:03
     *
     *  @param orgId 机构id
     *  @return
     */
    int getEmployeeCount(Integer orgId);

    /**
     * 得到门店的员工列表
     * @return
     */
    List<GetEmployeeListData> getEmployeeList(int orgId, int pageIndex, int pageSize, String search);

    /**
     * 得到单个员工的信息
     * @param employeeId
     * @return
     */
    EmployeePO getEmployee(int employeeId);

    /**
     * 得到服务师的服务项目列表
     * @param employeeId
     * @return
     */
    List<ProjectPO> getProjectList(int employeeId);

    /**
     * 获取健康课堂的服务师成员
     * @param projectCode
     * @return
     */
    List<LessonUserInfo> findEmployeeListByLesson(String projectCode);

    /**
     * 更新个人信息(仅地址、简介、专长）
     * @param employeeId
     * @param updateMineInfoData
     */
    void updateEmployeeMine(int employeeId, UpdateMineInfoData updateMineInfoData);

    /**
     * 员工离职
     * @param employeeId
     */
    void leaveJob(int employeeId);

    /**
     * 获取门店管理员
     * @param orgId
     * @return
     */
    EmployeePO getManage(Integer orgId);

    /**
     * 根据用户的订单获取所属服务师id列表
     * @param userId
     * @return
     */
    List<Integer> findEmployeeListByUserId(Integer userId);
}


