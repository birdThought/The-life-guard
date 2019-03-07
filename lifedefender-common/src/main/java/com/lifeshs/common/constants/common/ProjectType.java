package com.lifeshs.common.constants.common;

/**
 * 项目类型
 * @Author: wenxian.cai
 * @Date: 2017/6/15 15:31
 */
public enum ProjectType {

    PROJECT_ALL(0, "全部服务"),

    PROJECT_CONSULT(1, "咨询服务"),

    PROJECT_TOSTORE(2, "到店服务"),

    PROJECT_VISIT(3, "上门服务"),

    PROJECT_LESSON(4, "课堂服务");

    private Integer _value;

    private String _name;

    private ProjectType(Integer value, String name)
    {
        _value = value;
        _name = name;
    }

    public Integer getValue() {
        return _value;
    }

    public void setValue(Integer _value) {
        this._value = _value;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
