package com.lifeshs.common.constants.common;

/**
 * @Author Yue.Li
 * @Date 2017/5/12.
 */
public enum RecordStatus {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 删除
     */
    DELETED(2, "删除"),
    /**
     * 冻结
     */
    FROZEN(3, "冻结");

    private int _value;
    private String _name;

    RecordStatus(int value, String name)
    {
        _value = value;
        _name = name;
    }

    public int value()
    {
        return _value;
    }

    public String getName()
    {
        return _name;
    }
}
