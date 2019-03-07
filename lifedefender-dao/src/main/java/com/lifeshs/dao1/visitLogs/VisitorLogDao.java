package com.lifeshs.dao1.visitLogs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.visit.VisitLogVo;




/**
 * 访问记录方法
 * @author shiqiang.zeng
 * @Date 2018.1.16 20:22
 */
@Repository(value="visitorLogDao")
public interface VisitorLogDao {
	/**
	 * 用户登录记录页面
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<VisitLogVo> findUserLog(@Param("orgId") Integer orgId,@Param("userType") Integer userType,
								@Param("terminalType")String terminalType,@Param("startRow")int startRow,@Param("pageSize") int pageSize);
	/**
	 * 获取数量
	 * @param orgId
	 * @param userType
	 * @param terminalType
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	int countUserLog(@Param("orgId") Integer orgId,@Param("userType") Integer userType,
					@Param("terminalType")String terminalType);
	

}
