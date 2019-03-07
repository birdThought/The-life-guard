package com.lifeshs.app.api.manager;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.app.api.common.AppBaseController;
import com.lifeshs.common.constants.app.*;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.app.manager.MAppJSON;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.order.OrderDTO;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.management.OrgServiceAndMemberDO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mapp/index")
public class MAppIndexController extends AppBaseController {

    @Autowired
    private IManagerOrgService managerOrgService;

    @Autowired
    private IServiceOrgService serviceOrgService;
    
    @Autowired
    private InformationService informationService;
    
    @Autowired
    private IDataAreaService areaService;
    
    /**
     *  获取首页信息
     *  @author yuhang.weng 
     *  @DateTime 2017年2月7日 上午9:59:30
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public @ResponseBody JSONObject index(@RequestParam String json) {
        MAppJSON appJSON = MAppNormalService.parseAppJSON(json);
        TOrgUser orgUser = appJSON.getAopData().getOrgUser();
        int orgId = orgUser.getOrgId();
        
        OrgDTO org = serviceOrgService.getOrg(orgId);
        
        Map<String, Object> returnData = new HashMap<>();
        int type = org.getType();
        if (type == 0) {
            returnData = getManageOrgIndex(org);
        }
        if (type == 1 || type == 2) {
            returnData = getStoreOrgIndex(org);
            returnData.put(OrgUser.NAME, orgUser.getRealName());
            returnData.put(OrgUser.PHOTO, orgUser.getPhoto());
            returnData.put(OrgUser.ABOUT, orgUser.getAbout());
        }
        
        return MAppNormalService.success(returnData);
    }
    
    /**
     *  管理机构首页信息获取
     *  @author yuhang.weng 
     *  @DateTime 2017年2月7日 上午9:59:24
     *
     *  @param org
     *  @return
     */
    private Map<String, Object> getManageOrgIndex(OrgDTO org) {
        Map<String, Object> manageOrg = new HashMap<>();
        
        int orgId = org.getId();
        String logo = org.getLogo();
        String orgName = org.getOrgName();
        
        Integer managementAmount = managerOrgService.getTheAmountOfChildManagement(orgId);
        OrgServiceAndMemberDO serviceAndMemberAmount = managerOrgService.getTheAmountOfChildService(orgId);
        
        manageOrg.put(Org.LOGO, logo);
        manageOrg.put(Org.NAME, orgName);
        manageOrg.put(Org.SUB_ORG_COUNT, managementAmount);
        manageOrg.put(Org.SUB_STORE_COUNT, serviceAndMemberAmount.getAmountOfService());
        manageOrg.put(OrgServe.MEMBER_COUNT, serviceAndMemberAmount.getAmountOfMember());
        
        List<Map<String, Object>> sNews = new ArrayList<>();
        List<TInformation> stationNews = getNews("其它", 10);
        for (int i = 0; i < stationNews.size(); i++) {
            String title = stationNews.get(i).getTitle();
            if (title.length() > 19) {
                title = title.substring(0, 18) + "...";
            }

            Map<String, Object> sws = new HashMap<>();
            sws.put(Announcement.TITLE, title);
            sws.put(Announcement.ID, stationNews.get(i).getId());
            
            sNews.add(sws);
        }
        manageOrg.put(Announcement.ANNOUNCEMENTS, sNews);
        
        return manageOrg;
    }
    
    private List<TInformation> getNews(String parentColumnName, Integer columnId) {
        List<TInformation> news = new ArrayList<>();

        DataResult dataResult = informationService.loadNewsInformationDatas(parentColumnName, columnId, 1, null, null);

        PaginationDTO informations = (PaginationDTO) dataResult.getAttr().get("informations");
        List<Object> datas = informations.getDataObject();

        for (Object data : datas) {
            TInformation nw = (TInformation) data;
            news.add(nw);
        }

        return news;
    }
    
    /**
     *  门店首页信息获取
     *  @author yuhang.weng 
     *  @DateTime 2017年2月7日 上午10:00:38
     *
     *  @return
     */
    private Map<String, Object> getStoreOrgIndex(OrgDTO org) {
        Map<String, Object> storeOrg = new HashMap<>();
        
        int orgId = org.getId();
        String logo = org.getLogo();
        String orgName = org.getOrgName();
        String p = org.getProvince();
        String d = org.getDistrict();
        String c = org.getCity();
        String s = org.getStreet();
        
        storeOrg.put(Org.LOGO, logo);
        storeOrg.put(Org.NAME, orgName);
        
        // 地址
        StringBuffer address = new StringBuffer("");
        if (StringUtils.isNotBlank(p) && StringUtils.isNotBlank(d) && StringUtils.isNotBlank(c)) {
            AreaVO areaVO = areaService.getAreaNameByCode(p, c, d);
            
            String pN = areaVO.getProvinceName();
            String cN = areaVO.getCityName();
            String dN = areaVO.getDistrictName();
            
            if (StringUtils.equals(dN, cN)) {
                dN = "";
            }
            if (StringUtils.equals(pN, cN)) {
                cN = "";
            }
            
            address.append(pN + " ");
            address.append(cN + " ");
            address.append(dN + " ");
        }
        address.append(s);
        if (StringUtils.isBlank(address.toString()) || address.toString().equals("null")) {
            address = new StringBuffer("");
        }
        
        storeOrg.put(Area.ADDRESS, address.toString());
        // 订单数量(有效)
        int orderCount = 0;
        // 交易总量
        Double tradeSum = 0.0;
        // 用户数量
        List<Integer> memberIds = new ArrayList<>();
        // 机构服务

        List<Map<String, Object>> orgServeMapList = new ArrayList<>();
        // 遍历机构服务
        for (OrgServeDTO orgServe : org.getOrgServes()) {
            for (OrderDTO order : orgServe.getValidOrders()) {
                if (order.getChargeMode() != 0) {
                    Double charge = NumberUtils.changeF2Y(order.getCharge() + "");
                    tradeSum = NumberUtils.add(charge, tradeSum);
                }
                if (!memberIds.contains(order.getUserId())) {
                    memberIds.add(order.getUserId());
                }
            }
            // 统计订单数量
            orderCount += orgServe.getValidOrders().size();
            // 封装服务数据
            Map<String, Object> orgServeMap = new HashMap<>();
            ServeTypeSecondDTO serve = orgServe.getServeType();
            String image = ""; //serve.getImage();
            orgServeMap.put(Serve.IMAGE, image);
            orgServeMap.put(Serve.NAME, serve.getName());
            orgServeMapList.add(orgServeMap);
        }
        
        // 员工数量
        Map<String, Object> condition = new HashMap<>();
        condition.put("orgId", orgId);
        int orgUserCount = commonTrans.getCount(TOrgUser.class, condition);
        
        storeOrg.put("orderCount", orderCount);
        storeOrg.put("tradeSum", tradeSum);
        storeOrg.put("visitingCount", 0);
        storeOrg.put("userCount", memberIds.size());
        storeOrg.put("orgUserCount", orgUserCount);
        storeOrg.put(OrgServe.SERVES, orgServeMapList);
        
        return storeOrg;
    }
}
