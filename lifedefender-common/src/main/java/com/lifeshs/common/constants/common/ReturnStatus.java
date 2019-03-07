package com.lifeshs.common.constants.common;

/**
 * 接口返回状态码+描述
 * Created by dengfeng on 2017/6/27 0027.
 */
public enum ReturnStatus {

    Normal(0, "正常响应"),
    Error (1, "错误"),
    Fail(2, "操作失败"),
    NonAuth (3, "没有权限"),
    ParamIsEmpty(4, "参数为空"),
    VerifyCodeError(5, "验证码不正确"),
    VerifyCodeUsed(6, "验证码已被使用"),
    NonRecord(7, "没有记录"),
    NonStatement(8, "没有查询到结算记录");

    private int _value;
    private String _name;

    private ReturnStatus(int value, String name)
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
