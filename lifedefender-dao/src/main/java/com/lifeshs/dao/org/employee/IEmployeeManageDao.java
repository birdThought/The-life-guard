package com.lifeshs.dao.org.employee;/**
 * Created by Administrator on 2017/6/2.
 */

import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工管理dao
 *
 * @author wenxian.cai
 * @create 2017-06-02 15:52
 **/

@Repository(value = "employeeManageDao")
public interface IEmployeeManageDao {

    /**
     * @Description: 获取员工列表
     * @Author: wenxian.cai
     * @Date: 2017/6/2 15:56
     */
    List<EmployeeDTO> listEmployee(@Param(value = "orgId") Integer orgId, @Param(value = "realName") String realName,
                                   @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);

    /**
     * @Description: 统计员工数量
     * @Author: wenxian.cai
     * @Date: 2017/6/2 15:58
     */
    int getCountOfEmployee(@Param(value = "orgId") Integer orgId, @Param(value = "realName") String realName);

    /**
     * @Description: 更新员工状态
     * @Author: wenxian.cai
     * @Date: 2017/6/6 17:17
     */
    void updateEmployee(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status);

    /**
     * @Description: 注册员工
     * @Author: wenxian.cai
     * @Date: 2017/6/7 14:10
     */
    int addEmployee(EmployeeRegisterDTO employee);
    
    /**
     *  获取一个员工
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 上午9:36:37
     *
     *  @param orgUserId
     *  @return
     */
    EmployeeDTO getEmployeeByUserCode(@Param("userCode") String userCode);
    
    /**
     *  获取一个员工
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:11:46
     *
     *  @param id
     *  @return
     */
    EmployeeDTO findEmployee(@Param("id") int id);
    
    /*
     *  获取一个员工编号
     * 
     */
    EmployeeDTO findEmployeeUserNo(@Param("userNo") String userNo);
    
    /**
     *  通过项目code查询服务师列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午4:33:09
     *
     *  @param projectCode
     *  @return
     */
    List<EmployeeDTO> findOrgUserByProjectCode(@Param("projectCode") String projectCode);
    
    /**
     *  通过用户code查找员工信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月21日 下午3:30:21
     *
     *  @param userCodeList
     *  @return
     */
    List<EmployeeDTO> findServeUserByUserCode(@Param("userCodeList") List<String> userCodeList);
}
