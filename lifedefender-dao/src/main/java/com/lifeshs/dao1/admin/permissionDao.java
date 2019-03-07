package com.lifeshs.dao1.admin;

import com.lifeshs.po.admin.permissionPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-10
 * 11:44
 * @desc
 */
@Repository(value = "permissionDao")
public interface permissionDao {

    List<permissionPo>  findByAll();
}
