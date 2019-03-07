package com.lifeshs.customer.controller.serve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.org.TServerPO;
import com.lifeshs.service1.serve.ServeService;

@RestController
@RequestMapping(value = "serve")
public class ServeController {

    @Resource(name = "v2ServeService")
    private ServeService serveService;
    
    @RequestMapping(value = "/serves", method = RequestMethod.GET)
    public JSONObject listServe() {
        List<ServeTypeSecondDTO> serveList = serveService.listServeType();
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ServeTypeSecondDTO serve : serveList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("name", serve.getFirstName() + "-" + serve.getName());
            returnData.put("code", serve.getCode());
            returnDataList.add(returnData);
        }
        JSONObject root = new JSONObject();
        root.put("success", true);
        root.put("data", returnDataList);
        return root;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONObject add(@RequestBody TServerPO serve) throws OperationException {
        serveService.addServe(serve);
        JSONObject root = new JSONObject();
        root.put("success", true);
        return root;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JSONObject modify(@PathVariable(value = "id") int id, @RequestBody TServerPO serve) throws OperationException {
        serve.setId(id);
        serveService.modifyServe(serve);
        JSONObject root = new JSONObject();
        root.put("success", true);
        return root;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JSONObject delete(@PathVariable(value = "id") int id) throws OperationException {
        serveService.deleteServe(id);
        JSONObject root = new JSONObject();
        root.put("success", true);
        return root;
    }
}
