package com.lifeshs.component.umeng.util;

/**
 * 友盟推送类型
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月31日 上午10:17:29
 */
public enum UMengTypeEnum {

    /** 单播 */
    UNICAST("unicast"),
    /** 列播(多播) */
    LISTCAST("listcast"),
    /** 广播 */
    BROADCAST("broadcast");

    private String value;

    private UMengTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
