package com.lifeshs.po.user;

import lombok.Data;

/**
 * @author Administrator
 * @create 2018-02-09
 * 17:15
 * @desc
 */
public @Data class AdminRolePO {

    private Integer id;
    /** 角色名称*/
    private String name;
    /** 权限id*/
    private Integer permissionId;
    /** 父级id,*/
    private Integer parentId;
    /** 区别id*/
    private Integer difference;



}
