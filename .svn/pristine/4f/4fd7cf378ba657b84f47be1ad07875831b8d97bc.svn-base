package com.lifeshs.service.terminal.app.information.impl;/**
 * Created by Administrator on 2017/5/2.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Information;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.User;
import com.lifeshs.pojo.information.InformationCollectDTO;
import com.lifeshs.pojo.information.InformationDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service.terminal.app.information.IAppInformationService;

/**
 * 资讯服务实现
 *
 * @author wenxian.cai
 * @create 2017-05-02 13:50
 **/

@Service(value = "appInformationService")
public class AppInformationServiceImpl extends AppNormalService implements IAppInformationService{

    @Autowired
    InformationService informationService;

    @Override
    public JSONObject addInformationCollect(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int informationId = mm_0.getIntValue(Information.ID);   //资讯ID
        InformationCollectDTO collect = new InformationCollectDTO();
        collect.setInformationId(informationId);
        collect.setUserId(userId);
        boolean bool = informationService.addInformationCollect(collect);
        if (!bool) {
            return error("操作失败");
        }
        return success(true);
    }

    @Override
    public JSONObject deleteInformationCollect(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int informationId = mm_0.getIntValue(Information.ID);   //资讯收藏ID
        List ids = new ArrayList();
        ids.add(informationId);
        boolean bool = informationService.deleteInformationCollect(ids, userId);
        if (!bool) {
            return error("操作失败");
        }
        return success(true);
    }

    @Override
    public JSONObject listInformationCollects(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        PaginationDTO<InformationDTO> pagination = informationService.listInformationByCollect(userId, pageIndex, pageSize);
        List<InformationDTO> list = pagination.getData();
        for (InformationDTO information : list) {
            if (StringUtils.isBlank(information.getImage())) {
                continue;
            }
            String img = information.getImage().split(",")[0];
            if (StringUtils.isNotBlank(img)
                    && !img.startsWith(Normal.PHOTO_PREFIX)
                    && !img.startsWith("http://www.lifekeepers.cn")
                    && !img.startsWith("http://apit.lifeshs.com")) {
                // TODO 对不规范的斜杠进行替换,暂时这么处理，后续会在保存图片的时候就对图片的斜杠进行规范
                // 利用正则把斜杠替换
                img = img.replaceAll("\\\\", "/");
                img = img.replaceAll("//", "/");

                // TODO 文件路径中缺少'/',导致图片路径错误
                if (!img.startsWith("/")) {
                    img = "/" + img;
                }
                img = Normal.PHOTO_PREFIX + img;
            }
            
            information.setImage(img);
        }
        return success(pagination.getData(), true);
    }

    @Override
    public JSONObject isCollectedOfInformation(String json) throws Exception {
        JSONObject root = JSONObject.parseObject(json);
        JSONObject data = root.getJSONObject(Normal.DATA);
        int userId = data.getIntValue(User.ID);
        JSONArray mm = data.getJSONArray(Normal.MESSAGE);

        JSONObject mm_0 = mm.getJSONObject(0);

        int informationId = mm_0.getIntValue(Information.ID);   //资讯收藏ID
        boolean bool = informationService.isCollectedOfInformation(informationId, userId);
        Map map = new HashMap();
        map.put("isCollected", bool);
        return success(map);
    }
}
