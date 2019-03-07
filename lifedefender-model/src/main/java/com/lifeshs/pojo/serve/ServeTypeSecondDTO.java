package com.lifeshs.pojo.serve;

import lombok.Data;

/**
 * 二级服务种类
 * 
 * @author yuhang.
 *
 * @version 2.0
 * @DateTime 2017年3月8日 上午11:18:30
 */
public @Data class ServeTypeSecondDTO {

    /** 平台服务 */
    private Integer id;

    /** 服务名称 */
    private String name;

    /** 代码 */
    private String code;

    /** 平台服务 */
    private Integer firstId;

    /** 服务名称 */
    private String firstName;

    /** 代码 */
    private String firstCode;

}
