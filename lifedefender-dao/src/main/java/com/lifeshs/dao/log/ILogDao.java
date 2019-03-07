package com.lifeshs.dao.log;

import com.lifeshs.pojo.log.LoginLogDTO;
import com.lifeshs.pojo.log.SensitiveOperationLogDTO;
import com.lifeshs.vo.visit.OperatingVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: log操作DAO
 * @author: wenxian.cai
 * @create: 2017/4/19 9:50
 */
@Repository("logDao")
public interface ILogDao {

    /**
     * @Description: 获取用户最后登录记录
     * @author: wenxian.cai
     * @create: 2017/4/19 9:52
     */
    LoginLogDTO getLastLogLogin(@Param("userId") int userId);

    /**
     * @Description: 添加敏感操作
     * @Author: wenxian.cai
     * @Date: 2017/7/19 11:46
     */
    void addSensitiveLog(SensitiveOperationLogDTO sensitiveOperationLogDTO);

    /**
     * 获取用户登录数据 + 条件
     * @param terminalType 类型
     * @param mech 机构
     * @param user 会员
     * @param curPage
     * @param pageSize
     * @return
     */
    List<OperatingVo> findByLogDataList(@Param("terminalType") String terminalType, @Param("mech")Integer mech, @Param("user")Integer user,
                                        @Param("curPage") Integer curPage, @Param("pageSize")Integer pageSize);

    /**
     *  统计数量
     * @param terminalType
     * @param mech
     * @param user
     * @return
     */
    int findByLoginCount(@Param("terminalType") String terminalType, @Param("mech")Integer mech, @Param("user")Integer user);

}
