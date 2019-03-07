package com.lifeshs.dao1.admin;

import com.lifeshs.po.admin.adminPO;
import com.lifeshs.vo.visit.OperatingVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-06
 * 15:06    后台用户
 * @desc
 */
@Repository("adminDao")
public interface adminDao {

    int findByAdminCount();


    List<adminPO> findByAdminData(@Param("curPage")Integer curPage,@Param("pageSize")Integer pageSize);


    int findByOperatingCount(@Param("type")Integer type,@Param("realName") String realName,@Param("createDate")String createDate);

    List<OperatingVo> findByOperatingData(@Param("type")Integer type,@Param("realName") String realName,@Param("createDate")String createDate,
                                          @Param("curPage")Integer curPage,@Param("pageSize")Integer pageSize);

    int saveAdminUser(adminPO admin);

    /**
     * 设置用户与角色绑定
     * @param id
     */
	void saveAdminUserRole(@Param("userId")Integer id);

}
