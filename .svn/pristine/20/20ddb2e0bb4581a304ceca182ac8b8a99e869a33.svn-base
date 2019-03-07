package com.lifeshs.component.umeng.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  推送发送消息的android消息体
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月22日 下午1:56:54
 */
@EqualsAndHashCode(callSuper = false)
public @Data class SendDataAndroidDTO extends SendDataBaseDTO {

    //在调用API接口实现推送消息时，如果需要使用小米/华为弹窗，需添加下面两个参数：
    private boolean mipush = true;  //默认开启小米和华为的离线推送

    @JSONField(name = "mi_activity")
    private String miActivity;  // 应用APP："com.mgc.lifeguardian.umeng.MipushTestActivity"    管理APP：com.example.tzdq.lifeshsmanager.umeng.MipushTestActivity

    private PayloadDTO payload = new PayloadDTO();
    
    private PolicyDTO policy = new PolicyDTO();

    /**
     *  消息内容
     *  @author yuhang.weng
     *  @version 1.0
     *  @DateTime 2017年5月22日 下午1:57:29
     */
    public @Data class PayloadDTO {

        @JSONField(name = "display_type")
        private String displayType;

        private BodyDTO body = new BodyDTO();

        private JSONObject extra = new JSONObject();
    }

    /**
     * 可选 发送策略
     *
     * @author yuhang.weng
     * @version 1.0
     * @DateTime 2017年5月22日 上午10:34:35
     */
    @EqualsAndHashCode(callSuper = false)
    public @Data class PolicyDTO extends PolicyBaseDTO {

        // 可选
        // 开发者对消息的唯一标识，服务器会根据这个标识避免重复发送。有些情况下（例如网络异常）开发者可能会重复调用API导致消息多次下发到客户端。如果需要处理这种情况，可以考虑此参数。注意,
        // out_biz_no只对任务生效。
        @JSONField(name = "out_biz_no")
        private String outBizNo;
    }

    public @Data class BodyDTO {
        // 通知展现内容
        // 必填 通知栏提示文字
        private String ticker;
        // 必填 通知标题
        private String title;
        // 必填 通知文字描述
        private String text;
        // 可选 状态栏图标ID 如果没有, 默认使用应用图标
        private String icon;
        // 可选 通知栏拉开后左侧图标ID
        private String largeIcon;
        // 可选 通知栏大图标的URL链接。该字段的优先级大于largeIcon。
        private String img;
        // 可选 通知声音。如果该字段为空，采用SDK默认的声音
        private String sound;
        // 可选 默认为0，用于标识该通知采用的样式。使用该参数时,开发者必须在SDK里面实现自定义通知栏样式。
        @JSONField(name = "builder_id")
        private String builderId;

        // 通知到达设备后的提醒方式
        // 可选 收到通知是否震动,默认为"true".
        @JSONField(name = "play_vibrate")
        private String playVibrate;

        @JSONField(name = "play_lights")
        private String playLights;

        @JSONField(name = "play_sound")
        private String playSound;

        @JSONField(name = "after_open")
        private String afterOpen;

        private String url;

        private String activity;

        private String custom;
    }
}
