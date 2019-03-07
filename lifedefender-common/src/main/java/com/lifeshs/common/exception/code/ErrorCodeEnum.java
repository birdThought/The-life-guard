package com.lifeshs.common.exception.code;

/**
 *  异常错误码
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月1日 上午11:04:38
 */
public enum ErrorCodeEnum {

    REQUEST(400, "（错误请求） 服务器不理解请求的语法"),

    AUTHORIZED(401, "（未授权） 请求要求身份验证"),

    NOT_FOUND(404, "（未找到） 服务器找不到请求的内容"),

    DENY(405, "（方法禁用） 禁用请求中指定的方法"),

    SERVE(500, "（服务器内部错误）  服务器遇到错误，无法完成请求"),

    REPEAT(170802, "重复请求"),

    NOT_COMPLETE(170804, "部分操作失败"),

    FAILED(170805, "操作失败"),

    FORMAT(170831, "参数格式不正确"),

    MISSING(170901, "参数丢失");
    /** 值 */
    private int value;
    /** 描述 */
    private String remark;

    private ErrorCodeEnum(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int value() {
        return this.value;
    }

    public String remark() {
        return this.remark;
    }
}
