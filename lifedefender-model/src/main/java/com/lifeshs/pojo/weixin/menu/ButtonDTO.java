package com.lifeshs.pojo.weixin.menu;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  微信公众号-菜单按钮模型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月13日 上午11:25:58
 */
@Data
public class ButtonDTO {
    /** 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型 */
    private String type;
    /** 菜单标题，不超过16个字节，子菜单不超过60个字节 */
    private String name;
    /** 菜单KEY值，用于消息接口推送，不超过128字节 */
    private String key;
    /** 网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。 */
    private String url;
    /** 调用新增永久素材接口返回的合法media_id */
    @JSONField(name = "media_id")
    private String mediaId;
    /** 小程序的页面路径(miniprogram类型必须) */
    private String pagepath;
    /** 二级菜单数组，个数应为1~5个 */
    @JSONField(name = "sub_button")
    private List<ButtonDTO> subButton;
}
