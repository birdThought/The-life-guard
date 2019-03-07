package com.lifeshs.common.constants.app;

/**
 *  消息
 *  @author yuhang.weng
 *  @version 2.4
 *  @DateTime 2017年6月26日 下午1:44:06
 */
public class Message {

    public static final String MESSAGE = "message";
    
    public static final String ID = "id";
    /** 标题 */
    public static final String TITLE = "title";
    /** 内容 */
    public static final String CONTENT = "content";
    /** 是否已读 */
    public static final String READ = "read";
    /** 日期 */
    public static final String DATE = "date";
    /** 未读消息数 */
    public static final String UNREAD_MSG_COUNT = "unreadMessageCount";
    /** 占位符参数 */
    public static final String PLACEHOLDER = "placeholder";
    /** "go_app": 打开应用, "go_url": 跳转到URL, "go_activity": 打开特定的activity, "go_custom": 用户自定义内容。 */
    public static final String OPEN_TYPE = "openType";
    /** 在AFTER_OPEN=go_url时，保存url地址，=go_activity时，保存窗口名称含包路径 */
    public static final String OPEN_TARGET = "openTarget";
    /** 附加内容："key1": "value1","key2": "value2" */
    public static final String OPEN_ATTACH = "openAttach";
}
