package com.lifeshs.pojo.healthDevice;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 心电
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月20日 下午2:39:54
 */
@Data
public class EcgDTO {

    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 终端类型 */
    private String deviceType;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    /** 测量日期 */
    private Date date;

    /** 创建日期 */
    private Date createDate;

    /** 心电详情 */
    private List<EcgDetailDTO> detailList;
}
