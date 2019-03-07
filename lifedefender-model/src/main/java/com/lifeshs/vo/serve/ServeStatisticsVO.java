package com.lifeshs.vo.serve;

import lombok.Data;

/**
 *  服务类别统计VO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年2月8日 下午5:16:48
 */
@Data
public class ServeStatisticsVO {

    private Integer id;
    /** 机构数量 */
    private Integer orgCount;
    /** 服务类别名称 */
    private String name;
    /** 用户数量 */
    private Integer memberCount;
    /** 服务类型 */
    private Integer serveType;
    /** 服务父类别名称 */
    private String parentName;
}
