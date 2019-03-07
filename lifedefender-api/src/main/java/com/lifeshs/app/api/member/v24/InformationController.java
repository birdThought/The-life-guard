package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Information;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;

@RestController(value = "v24InformationController")
@RequestMapping(value = "app/information/v24")
public class InformationController {

    @Autowired
    private InformationService informationService;
    
    @RequestMapping(value = "listInformation", method = RequestMethod.POST)
    public JSONObject listInformation(@RequestParam String json) {
        List<Map<String, Object>> news = new ArrayList<>();
        
        DataResult dataResult = informationService.loadNewsInformationDatas("营养美食", 7, 1, null, 10);
        @SuppressWarnings("unchecked")
        PaginationDTO<TInformation> informations = (PaginationDTO<TInformation>) dataResult.getAttr().get("informations");
        List<TInformation> datas = informations.getDataObject();

        for (TInformation data : datas) {
            Map<String, Object> info = new HashMap<>();
            info.put(Information.IMAGE, data.getImage());
            info.put(Information.TITLE, data.getTitle());
            String content = data.getContent();
            if (content.length() > 50) {
                content = content.substring(0, 49) + "...";
            }
            info.put(Information.CONTENT, content);
            info.put(Information.ID, data.getId());
            news.add(info);
            
            if (news.size() >= 5) {
                break;
            }
        }

        return AppNormalService.success(news);
    }
}
