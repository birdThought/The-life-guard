package com.lifeshs.vo.serve.consult;

import lombok.Data;

/**
 * 
 *  vip套餐服务师
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年10月16日 下午4:20:16
 */
public @Data class OrgUserVO {

    /** 服务id t_vip_combo_orguser_relation 的id */
    private Integer id;
    /** 服务师id */
    private Integer userId;
    /** 名字 */
    private String realName;
    /** 职称 */
    private String professionalName;
    /** 专业特长 */
    private String expertise;
    /** 照片 */
    private String photo;
    /** 服务师的UserCode */
    private String huanxinUserName;
    /** 机构名称 */
    private String orgName;
    /** 机构地址 */
    private String street;
    
}
