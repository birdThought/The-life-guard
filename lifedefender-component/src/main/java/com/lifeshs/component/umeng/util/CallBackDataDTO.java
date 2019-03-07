package com.lifeshs.component.umeng.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 返回结果data对象
 *
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年5月22日 下午2:02:06
 */
public @Data
class CallBackDataDTO {

    @JSONField(name = "msg_id")
    private String msgId;

    @JSONField(name = "task_id")
    private String taskId;

    @JSONField(name = "error_code")
    private String errorCode;

    @JSONField(name = "error_msg")
    private String errorMsg;

    private String ip;
}