package com.lifeshs.component.umeng.util;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  可选 发送策略
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月22日 下午1:46:23
 */
public @Data class PolicyBaseDTO {
    // 可选 定时发送时间，若不填写表示立即发送。格式: "yyyy-MM-dd HH:mm:ss"
    @JSONField(name = "start_time")
    private String startTime;
    // 可选 消息过期时间,其值不可小于发送时间或者start_time(如果填写了的话),如果不填写此参数，默认为3天后过期。格式同start_time
    @JSONField(name = "expire_time")
    private String expireTime;
    // 可选 发送限速，每秒发送的最大条数。开发者发送的消息如果有请求自己服务器的资源，可以考虑此参数。
    @JSONField(name = "max_send_num")
    private String maxSendNum;
}
