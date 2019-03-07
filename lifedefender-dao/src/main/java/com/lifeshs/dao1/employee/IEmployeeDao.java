package com.lifeshs.dao1.employee;

import com.lifeshs.dto.manager.employee.GetEmployeeListData;
import com.lifeshs.dto.manager.serve.GetLessonGroupData;
import com.lifeshs.dto.manager.serve.LessonUserInfo;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 门店员工的数据操作
 * Created by dengfeng on 2017/7/3 0003.
 */
@Repository("employeeDao")
public interface IEmployeeDao {
    /**
     * 得到门店的员工列表
     * @return
     */
    List<GetEmployeeListData> getEmployeeList(@Param(value = "orgId") int orgId, @Param(value = "startRow") int startRow, @Param(value = "lenght") int lenght, @Param(value = "realName") String realName);

    /**
     * @Description: 统计员工数量
     */
    int getEmployeeCount(@Param(value = "orgId") Integer orgId);

    /**
     * 得到单个员工的信息
     * @param employeeId
     * @return
     */
    EmployeePO getEmployee(@Param(value = "employeeId") int employeeId);

    /**
     * 获取健康课堂的服务师成员
     * @param projectCode
     * @return
     */
    List<LessonUserInfo> findEmployeeListByLesson(@Param(value = "projectCode") String projectCode);

    /**
     * 更新个人信息(仅地址、简介、专长）
     * @param employeeId
     * @param about 简介
     * @param address 地址
     * @param expertise 专长
     */
    void updateEmployeeMine(@Param(value = "employeeId") int employeeId, @Param(value = "address") String address, @Param(value = "about") String about, @Param(value = "expertise") String expertise);

    /**
     * 员工离职
     * @param employeeId
     */
    void leaveJob(@Param(value = "employeeId") int employeeId);

    /**
     * 获取门店管理员
     * @param orgId
     * @return
     */
    EmployeePO findManage(@Param("orgId") Integer orgId);

    /**
     * 根据用户的订单获取所属服务师id列表
     * @param userId
     * @return
     */
    List<Integer> findEmployeeListByUserId(@Param("userId") Integer userId);

}
