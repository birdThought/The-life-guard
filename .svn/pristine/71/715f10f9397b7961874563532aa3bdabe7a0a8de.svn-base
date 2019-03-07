package com.lifeshs.component.umeng.util;

/**
 * 功能描述
 * Created by dengfeng on 2018/5/7 0007.
 */
public enum  UMengOpenTypeEnum {
    TEXT(1, "go_app"),

    Activity(2, "go_activity"),

    URL(3, "go_url");

    /** 值 */
    private int value;
    /** 描述 */
    private String sname;

    private UMengOpenTypeEnum(int value, String sname) {
        this.value = value;
        this.sname = sname;
    }

    public int value() {
        return this.value;
    }

    public String sname() {
        return this.sname;
    }

    public static UMengOpenTypeEnum parseOf(int opentype){
        if (opentype == 1)
            return TEXT;
        if (opentype == 2)
            return Activity;
        if (opentype == 3)
            return URL;
        return null;
    }
}
