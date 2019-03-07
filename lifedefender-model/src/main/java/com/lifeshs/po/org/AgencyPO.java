package com.lifeshs.po.org;

import java.util.Date;

import lombok.Data;

/**
 *  管理机构
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月21日 下午1:52:46
 */
@Data
public class AgencyPO {

    private Integer id;
    /** 名字 */
    private String name;
    /** 父机构id */
    private Integer parentId;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
