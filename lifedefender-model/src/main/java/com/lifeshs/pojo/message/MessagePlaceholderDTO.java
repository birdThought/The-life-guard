package com.lifeshs.pojo.message;

import lombok.Data;

/**
 *  消息占位符
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月2日 下午2:48:18
 */
@Data
public class MessagePlaceholderDTO {

    /** 占位符key */
    private String key;
    /** 占位符值 */
    private String value;
    /** 附带参数 */
    private MessageAttachParamDTO attach;
}
