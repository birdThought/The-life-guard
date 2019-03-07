package com.lifeshs.common.constants.common;

/**
 *  用户类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月12日 下午6:12:51
 */
public enum UserType {

    member(1, "用户"),
    
    orgUser(2, "机构用户"),

    customer(3,"客服");

    private Integer value;
    
    private String remark;

    private UserType(Integer value, String remark) {
        this.value = value;
        this.remark = remark;
    }
    
    public Integer getValue() {
        return value;
    }
    
    public String getRemark() {
        return remark;
    }

    public static UserType parseOf(int value){

        for(UserType userType : UserType.values()){
            if(userType.value == value)
                return userType;
        }
        return null;
    }
}
