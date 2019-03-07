package com.lifeshs.service.org.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.pojo.page.PaginationDTO;

/**
 * 员工管理
 * @Author: wenxian.cai
 * @Date: 2017/6/2 15:44
 */
public interface IEmployeeManageService {

    /**
     * @Description: 获取员工列表
     * @Author: wenxian.cai
     * @Date: 2017/6/2 15:49
     */
    PaginationDTO<EmployeeDTO> listEmployee(Integer orgId, String realName, Integer pageIndex, Integer pageSize);
    
    /**
     * 
     *  获取员工列表
     *  @author NaN
     *  @DateTime 2018年8月17日 下午5:53:37
     *
     *  @param orgId
     *  @param realName
     *  @param pageIndex
     *  @param pageSize
     *  @return
     */
    List<EmployeeDTO> getEmployeeList(Integer orgId, String realName, Integer pageIndex, Integer pageSize);


    /**
     * @Description: 更新员工状态
     * @Author: wenxian.cai
     * @Date: 2017/6/6 17:15
     */
    boolean updateEmployee(Integer orgUserId, Integer status);

    /**
     * @Description: 添加员工
     * @Author: wenxian.cai
     * @Date: 2017/6/7 11:57
     */
    boolean addEmployee(EmployeeRegisterDTO employee);

    /**
     *  获取机构员工数量
     *  @author yuhang.weng 
     *	@DateTime 2017年6月6日 上午11:33:03
     *
     *  @param orgId 机构id
     *  @return
     */
    int countEmployee(Integer orgId);
    
    /**
     *  通过userCode查询员工信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 上午10:02:40
     *
     *  @param userCode
     *  @return
     */
    EmployeeDTO getEmployeeByUserCode(String userCode);
    
    /**
     *  获取员工信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:10:41
     *
     *  @param id
     *  @return
     */
    EmployeeDTO getEmployee(int id);
    
    /*
     *  获取一个员工编号
     * 
     */
    EmployeeDTO findEmployeeUserNo(String userNo);
    
    /**
     *  获取项目下的员工列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午3:48:55
     *
     *  @param lessonId
     *  @return
     */
    List<EmployeeDTO> listEmployeeByProjectCode(String projectCode);
    
    /**
     *  通过usercode查找服务师信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 下午3:28:09
     *
     *  @param projectCode
     *  @return
     */
    List<EmployeeDTO> listEmployee(List<String> userCode);
}
