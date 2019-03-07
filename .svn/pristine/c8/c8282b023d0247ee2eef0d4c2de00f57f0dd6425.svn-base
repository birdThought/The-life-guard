package com.lifeshs.pojo.serve.lesson;

import java.util.Date;
import java.util.List;

import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;

import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import lombok.Data;

/**
 *  健康课堂服务项目（相比较com.lifeshs.pojo.org.service.LessonServiceDTO，使用lessonDTO替换原来的lessonId）
 *  @author yuhang.weng
 *  @version 2.0
 *  @DateTime 2017年6月22日 下午2:26:04
 */
public @Data class LessonProjectDTO {

    /**主键*/
    private Integer id;

    /**唯一随机码*/
    private String serialId;

    /**项目code*/
    private String code;

    /**名称*/
    private String name;

    /**状态*/
    private Integer status;

    /**价格*/
    private Double price;

    /**课堂image*/
    private String image;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;

    /**修改时间*/
    private Date modifyDate;
    
    private String description;
    
    private Date startDate;
    
    private Date endDate;
    
    private String huanxinId;
    
    private short silence;
    
    private String lessonTime;
    
    /** 所属机构 */
    private OrgDTO org;
    
    /** 所属服务 */
    private ServeTypeSecondDTO serve;
    /** 服务师列表 */
    private List<EmployeeDTO> serveUserList;
    /** 有效订单数量 */
    private int validOrderCount;
}
