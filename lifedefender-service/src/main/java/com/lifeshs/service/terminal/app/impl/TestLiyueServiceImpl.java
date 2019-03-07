package com.lifeshs.service.terminal.app.impl;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.service.terminal.app.ITestLiyueService;
import org.springframework.stereotype.Service;

/**
 * @author YueLi
 * @date 2/22/17.
 */
@Service
public class TestLiyueServiceImpl implements ITestLiyueService {

    public JSONObject testLog(String val){
        return AppNormalService.success(val);
    }
}
