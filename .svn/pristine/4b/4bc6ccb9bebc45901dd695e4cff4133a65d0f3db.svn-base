package com.lifeshs.entity.area;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_area
 */
@Table(name = "t_data_area", schema = "")
@SuppressWarnings("serial")
public class TDataArea implements Serializable {
    public Integer id;
    /**
     * 地区名称
     */
    public String name;

    /**
     * 行政区划
     */
    public String code;


    public TDataArea() {
        super();
    }

    public TDataArea(Integer id, String name, String code) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", nullable = true, length = 6)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
