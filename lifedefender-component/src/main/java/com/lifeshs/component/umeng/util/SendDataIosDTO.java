package com.lifeshs.component.umeng.util;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  推送发送消息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月22日 下午1:56:34
 */
@EqualsAndHashCode(callSuper = false)
public @Data class SendDataIosDTO extends SendDataBaseDTO {

    private PayLoadDTO payload = new PayLoadDTO();
    
    private PolicyDTO policy = new PolicyDTO();

    /**
     * 消息内容(iOS最大为2012B)
     *
     * @author yuhang.weng
     * @version 1.0
     * @DateTime 2017年5月22日 下午3:59:20
     */
    public @Data class PayLoadDTO {

        private ApsDTO aps = new ApsDTO();
    }

    /**
     * 可选 发送策略
     *
     * @author yuhang.weng
     * @version 1.0
     * @DateTime 2017年5月22日 下午1:46:33
     */
    @EqualsAndHashCode(callSuper = false)
    public @Data class PolicyDTO extends PolicyBaseDTO {

        // 可选，iOS10开始生效。
        @JSONField(name = "apns-collapse-id")
        private String apnsCollapseId;
    }

    /**
     *  APNs
     *  @author yuhang.weng
     *  @version 1.0
     *  @DateTime 2017年5月22日 上午11:58:23
     */
    public @Data class ApsDTO {

        // 必填
        private AlertDTO alert = new AlertDTO();
        // 可选
        private Integer badge;
        // 可选
        private String sound;
        // 可选
        @JSONField(name = "content-available")
        private Integer contentAvailable;
        // 可选, 注意: ios8才支持该字段。
        private String category;
    }

    public @Data class AlertDTO {

        private String title;

        private String body;
    }
}
