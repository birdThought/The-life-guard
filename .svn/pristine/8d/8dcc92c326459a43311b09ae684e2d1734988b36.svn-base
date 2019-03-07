package com.lifeshs.app.api.member.release;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.service.terminal.app.IAppVersion;
import com.lifeshs.service.terminal.app.impl.AppNormalService;

@Controller(value = "appVersionController")
@RequestMapping(value = {"/app", "/app/version"})
public class VersionController {

    @Autowired
    private IAppVersion appVersionService;
    
    /**
     * app版本更新控制
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "queryAppVersion", method = RequestMethod.POST)
    public @ResponseBody JSONObject queryAppVersion(@RequestParam String json) throws Exception {
        /** 此处不使用parseAppJSON方法转换对象，app_A版本22.0之前提供的userId与token位置调换，会出现类型转换错误 */
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        String type = root.getString(Normal.TYPE);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);
        
        JSONObject result = new JSONObject();
        if (Normal.IOS_TYPE.equals(type)) {
            String newPublicVersion = appVersionService.queryIOSPublicVersion();
            result.put(Normal.VERSION, newPublicVersion);
        }
        if (Normal.ANDROID_TYPE.equals(type)) {
            JSONObject mm_0 = mm.getJSONObject(0);
            String appName = mm_0.getString(Normal.APPNAME);
            String version_s = mm_0.getString(Normal.VERSION);
            
            Integer version = null;
            if (StringUtils.isNotBlank(version_s)) {
                version = Double.valueOf(version_s).intValue();
            }

            Integer newVersion = appVersionService.queryAppVersion(appName, version);
            if (newVersion == null) {
                return AppNormalService.success(NormalMessage.NO_VERSION);
            }
            result.put(Normal.URL, BaseDefine.SYS_URL + "/tz_res/download/apk/" + appName);
            result.put(Normal.VERSION, newVersion);
        }
        return AppNormalService.success(result);
    }
}
