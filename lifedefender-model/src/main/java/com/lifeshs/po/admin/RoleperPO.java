package com.lifeshs.po.admin;

import lombok.Data;

/**
 * @author Administrator
 * @create 2018-02-24
 * 15:50   权限明细
 * @desc
 */
public  @Data class RoleperPO {
    private Integer id;
    private Integer roleId;
    private Integer perid;
    private Integer param;
}
