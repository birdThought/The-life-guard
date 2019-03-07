package com.lifeshs.app.api.wearterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.common.constants.common.TerminalErrorType;
import com.lifeshs.common.model.map.Gps;
import com.lifeshs.pojo.C3.C3Location;
import com.lifeshs.pojo.C3.Lbs;
import com.lifeshs.service.terminal.IC3Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

// 单独作为一个项目部署到另一个tomcat中
//@Controller
//@RequestMapping("/zdPushDataController")
public class C3Controller extends AppBaseController {

    private static final Logger logger = Logger.getLogger(C3Controller.class);

    @Autowired
    public IC3Service c3Service;

    /**
     * @author duosheng.mo
     * @DateTime 2016-5-26 下午09:00:41
     * @serverCode 服务代码
     * @serverComment 终端上传数据
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(params = "pushData", produces = "text/html;charset=UTF-8", method = { RequestMethod.GET,
            RequestMethod.POST })
    public String pushData(HttpServletRequest request) {
        String result = "", json = "";
        json = request.getParameter("json");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        logger.info("[" + df.format(System.currentTimeMillis()) + "]pushData传入的json=" + json);
        try {
            if (StringUtils.isBlank(json)) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader((InputStream) request.getInputStream(), "UTF-8"));
                String line = null;
                StringBuilder sbjson = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    sbjson.append(line);
                }
                in.close();
                json = sbjson.toString();
            }
        } catch (IOException e) {
            logger.error("[" + df.format(System.currentTimeMillis()) + "]pushData出现异常", e);
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        if (StringUtils.isBlank(json)) {
            // 返回信息到终端，参数空
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        try {
            result = dataParse(json);
            logger.info("方法返回:" + result);
        } catch (Exception e) {
            logger.error("[" + df.format(System.currentTimeMillis()) + "]pushData出现异常", e);
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        return result;
    }

    /**
     * @author duosheng.mo
     * @DateTime 2016年5月30日 下午2:57:44
     * @serverComment 数据解析、判断
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    private String dataParse(String json) {
        String result = "";
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.fromObject(json);
        } catch (JSONException e) {
            logger.error("dataParse json数据格式错误[" + json + "]", e);
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        String type = (String) jsonObject.get("type");
        if (!type.equals("C3")) {
            return c3Service.returnErrorJson(TerminalErrorType.TypeMismatch);
        }
        if (!jsonObject.containsKey("data")) {
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        jsonObject = jsonObject.getJSONObject("data");
        String imei = (String) jsonObject.get("imei");
        String password = (String) jsonObject.get("password");
        if (StringUtils.isBlank(imei) || StringUtils.isBlank(password)) {
            return c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        String command = (String) jsonObject.get("command");
        if ("login".equals(command)) {
            result = c3Service.login(imei, password);
        } else if ("poweroff".equals(command)) {
            result = c3Service.poweroff(imei, password);
        } else if ("datasync".equals(command)) {
            result = c3Service.datasync(imei, password);
        } else if ("heartpackge".equals(command)) {
            result = c3Service.heartpackge(imei, password);
        } else if ("location".equals(command)) {
            JSONArray msgArray = jsonObject.getJSONArray("msg");
            jsonObject.remove("msg");
            jsonObject.remove("size");
            C3Location lcal = (C3Location) JSONObject.toBean(jsonObject, C3Location.class);
            lcal.setType(type);
            JSONArray lbsArray = msgArray.getJSONArray(0);
            List<Lbs> lbsList = JSONArray.toList(lbsArray, Lbs.class);
            JSONObject gpsJson = msgArray.getJSONObject(1);
            gpsJson.remove("latlng_f");
            Gps gps = (Gps) JSONObject.toBean(gpsJson, Gps.class);
            lcal.setGps(gps);
            lcal.setListLBS(lbsList);
            result = c3Service.location(lcal);
        } else {
            result = c3Service.returnErrorJson(TerminalErrorType.DataFormatError);
        }
        return result;
    }
}
