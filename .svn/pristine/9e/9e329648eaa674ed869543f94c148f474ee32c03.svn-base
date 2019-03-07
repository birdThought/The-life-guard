package com.lifeshs.controller.health;

import com.alibaba.fastjson.JSONObject;

import com.lifeshs.common.constants.common.BaseDefine;

import com.lifeshs.service.terminal.app.IAppVersion;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("healthController")
public class HealthController {
    @Autowired
    private IAppVersion appVersionService;

    @RequestMapping(params = "VersionControl")
    public @ResponseBody
    JSONObject VersionControl(String appName, String version) {
        JSONObject root = new JSONObject();
        root.put("code", "0");

        boolean isBlank = StringUtils.isBlank(appName) ||
            StringUtils.isBlank(version);

        if (!isBlank) {
            // query version
            Integer newVersion = appVersionService.queryAppVersion(appName,
                    Double.valueOf(version).intValue());

            boolean versionNotFound = newVersion == null;

            if (!versionNotFound) {
                root.put("code", "1");
                root.put("version", newVersion + "");
                root.put("url",
                    BaseDefine.SYS_URL + "/tz_res/download/apk/" + appName);
            }
        }

        return root;
    }
}
