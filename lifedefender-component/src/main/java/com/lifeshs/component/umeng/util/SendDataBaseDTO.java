package com.lifeshs.component.umeng.util;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  推送发送消息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月22日 下午1:56:19
 */
public @Data class SendDataBaseDTO {

    // 必填 应用唯一标识
    private String appkey;

    // 必填 时间戳，10位或者13位均可，时间戳有效期为10分钟
    private String timestamp;
    // 必填 消息发送类型,其值可以为:unicast-单播
    // listcast-列播(要求不超过500个device_token)
    // filecast-文件播
    // (多个device_token可通过文件形式批量发送）
    // broadcast-广播
    // groupcast-组播
    // (按照filter条件筛选特定用户群, 具体请参照filter参数)
    // customizedcast(通过开发者自有的alias进行推送),
    // 包括以下两种case:
    // - alias: 对单个或者多个alias进行推送
    // - file_id: 将alias存放到文件后，根据file_id来推送
    private String type;

    // 可选 设备唯一表示
    @JSONField(name = "device_tokens")
    private String deviceTokens;

    // 可选 当type=customizedcast时，必填，alias的类型,
    // alias_type可由开发者自定义,开发者在SDK中调用setAlias(alias, alias_type)时所设置的alias_type
    @JSONField(name = "alias_type")
    private String aliasType;

    // 可选 当type=customizedcast时, 开发者填写自己的alias。
    private String alias;

    // 可选 当type=filecast时，file内容为多条device_token, device_token以回车符分隔
    // 当type=customizedcast时，file内容为多条alias， alias以回车符分隔，注意同一个文件内的alias所对应
    // 的alias_type必须和接口参数alias_type一致。
    // 注意，使用文件播前需要先调用文件上传接口获取file_id,
    // 具体请参照"2.4文件上传接口"
    @JSONField(name = "file_id")
    private String fileId;

    // 可选 正式/测试模式。测试模式下，广播/组播只会将消息发给测试设备。
    @JSONField(name = "production_mode")
    private String productionMode;

    // 可选 发送消息描述，建议填写。
    private String description;

}
