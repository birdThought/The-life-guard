package com.lifeshs.app.api.manager;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.service.terminal.app.IAppVersion;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理app-升级
 *
 * @author yuhang.weng
 * @DateTime 2016年11月18日 下午2:37:23
 */
@Controller
@RequestMapping("mapp/version")
public class MAppVersionController {

    @Autowired
    private IAppVersion appVersionService;

    @RequestMapping(value = "getAppVersion", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject getAppVersion(@RequestParam String json) {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        String type = root.getString(Normal.TYPE);

        Map<String, Object> returnData = new HashMap<>();
        if (Normal.IOS_TYPE.equals(type)) {
            String newVersion = appVersionService.queryManagerIOSPublicVersion();
            returnData.put(Normal.VERSION, newVersion);
        }
        if (Normal.ANDROID_TYPE.equals(type)) {
            String appName = data.getString(Normal.APPNAME);
            String version_s = data.getString(Normal.VERSION);
            
            Integer version = null;
            if (StringUtils.isNotBlank(version_s)) {
                version = Double.valueOf(version_s).intValue();
            }

            Integer newVersion = appVersionService.queryAppVersion(appName, version);
            boolean isNewVersionNull = newVersion == null;
            if (isNewVersionNull) {
                return MAppNormalService.success(NormalMessage.NO_VERSION);
            }

            String url = BaseDefine.SYS_URL + "/tz_res/download/apk/" + appName;
            returnData.put(Normal.VERSION, newVersion);
            returnData.put(Normal.URL, url);
        }
        return MAppNormalService.success(returnData);
    }

}
