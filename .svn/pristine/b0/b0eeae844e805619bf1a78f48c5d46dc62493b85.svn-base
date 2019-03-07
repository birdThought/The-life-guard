package com.lifeshs.controller.officialwebsite;

import com.alibaba.fastjson.JSON;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.data.AdDTO;
import com.lifeshs.pojo.data.AreaVO;
import com.lifeshs.pojo.health.ServiceItem;
import com.lifeshs.pojo.healthDevice.HealthPackageDTO;
import com.lifeshs.pojo.healthDevice.InteDeviceVO;
import com.lifeshs.pojo.org.OrgDTO;
import com.lifeshs.pojo.org.server.OrgServeDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.paper.*;
import com.lifeshs.service.data.IAdService;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.paper.IPaperService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.ServletUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  官网首页
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月9日 上午11:31:01
 */
@Controller
@RequestMapping("indexControl")
public class IndexController extends BaseController{

    @Autowired
    private IServiceOrgService orgService;

    @Autowired
    private IDataAreaService areaService;

    @Autowired
    private ITerminalService tService;

    @Autowired
    private InformationService informationService;

    @Autowired
    IPaperService question;
    
    @Resource(name = "adService")
    private IAdService adService;

    /**
     *  首页
     *  @author yuhang.weng 
     *	@DateTime 2017年3月9日 上午11:31:15
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "index", method = RequestMethod.GET)
    public String index(Model model, HttpServletResponse response) {
        try {
            response.sendRedirect("/index/v2.5/home");  //跳转到改版官网
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> news = new ArrayList<>();
        List<TInformation> hangyezixunNews = getNews("其它", 6);
        for (int i = 0; i < hangyezixunNews.size(); i++) {

            if (i > 4) {
                break;
            }

            TInformation nw = hangyezixunNews.get(i);

            String content = nw.getContent();

            if (StringUtils.isBlank(content)) {
                content = "";
            }

            if (content.length() > 27) {
                content = content.substring(0, 26) + "...";
            }

            String image = nw.getImage();
            if (StringUtils.isBlank(image)) {
                image = "";
            }

            Map<String, Object> map = new HashMap<>();
            map.put("id", nw.getId());
            map.put("title", nw.getTitle());
            map.put("content", content);
            map.put("image", image);
            map.put("date", DateTimeUtilT.date(nw.getCreateDate()));

            news.add(map);
        }

        model.addAttribute("news", news);

        // 站内公告
        Map<String, Object> firstsNews = null;
        List<Map<String, Object>> sNews = new ArrayList<>();
        List<TInformation> stationNews = getNews("其它", 10);

        if (stationNews.size() > 0) {
            firstsNews = new HashMap<>();
            TInformation firstInformation = stationNews.remove(0);

            String image = firstInformation.getImage();
            if (StringUtils.isBlank(image)) {
                image = "static/images/serviceBg1.png";
            }
            String title = firstInformation.getTitle();
            if (title.length() > 20) {
                title = title.substring(0, 19) + "...";
            }

            firstsNews.put("image", image);
            firstsNews.put("title", title);
            firstsNews.put("date", DateTimeUtilT.date(firstInformation.getCreateDate()));
            firstsNews.put("id", firstInformation.getId());
        }
        model.addAttribute("firstsNews", firstsNews);

        for (int i = 0; i < stationNews.size(); i++) {
            if (i > 1) {
                break; // 取3条数据
            }

            String title = stationNews.get(i).getTitle();
            if (title.length() > 19) {
                title = title.substring(0, 18) + "...";
            }

            Map<String, Object> sws = new HashMap<>();
            sws.put("title", title);
            sws.put("date", DateTimeUtilT.date(stationNews.get(i).getCreateDate()));
            sws.put("id", stationNews.get(i).getId());

            sNews.add(sws);
        }
        model.addAttribute("sNews", sNews);
        
        List<AdDTO> bannerList = adService.listBanner();
        List<String> bannerSrcList = new ArrayList<>();
        for (AdDTO b : bannerList) {
            String photo = b.getPhoto();
            bannerSrcList.add(photo);
        }
        model.addAttribute("banners", bannerSrcList);

        return "officialwebsite/index";
    }

    /**
     *  机构服务
     *  @author yuhang.weng 
     *	@DateTime 2017年3月9日 上午11:31:33
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "healthService", method = RequestMethod.GET)
    public String healthService(Model model) {
        List<OrgDTO> orgs = orgService.getRecommendManage(4);

        List<Map<String, Object>> orgList = new ArrayList<>();
        for (OrgDTO org : orgs) {
            StringBuffer classifies = new StringBuffer("");
            StringBuffer classifies_new = new StringBuffer("");
            String logo = org.getLogo();
            StringBuffer address = new StringBuffer("");
            String p = org.getProvince();
            String d = org.getDistrict();
            String c = org.getCity();
            String s = org.getStreet();

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

            for (OrgServeDTO orgServe : org.getOrgServes()) {
                classifies.append(orgServe.getClassify() + ",");
            }
            if (classifies.length() > 0) {
                classifies = new StringBuffer(classifies.substring(0, classifies.length() - 1));
            }

            String[] cls = classifies.toString().split(",");
            List<String> tmps = new ArrayList<>();
            for (String cl : cls) {
                tmps.add(cl);
            }

            tmps = ListUtil.removeRepeatElement(tmps, String.class);
            for (String tmp : tmps) {
                classifies_new.append(tmp + ",");
            }
            classifies_new = new StringBuffer("服务项目:" + classifies_new.substring(0, classifies_new.length() - 1));

            if (StringUtils.isBlank(logo)) {
                logo = "";
            }

            Map<String, Object> orgMap = new HashMap<>();
            orgMap.put("id", org.getId());
            orgMap.put("logo", logo);
            orgMap.put("name", org.getOrgName());
            orgMap.put("classfies", classifies_new.toString());
            orgMap.put("address", address.toString());

            orgList.add(orgMap);
        }
        model.addAttribute("orgs", orgList);

        List<TInformation> datas = getNews("其它", 27);
//        Map<String, Object> firstNews = null;
//        if (datas.size() > 0) {
//            TInformation firstInformation = datas.remove(0);
//            String image = firstInformation.getImage();
//            if (StringUtils.isBlank(image)) {
//                image = "static/images/serviceBg1.png";
//            }
//            String content = firstInformation.getContent();
//            if (StringUtils.isBlank(content)) {
//                content = "";
//            }
//
//            if (content.length() > 61) {
//                content = content.substring(0, 60) + "...";
//            }
//
//            firstNews = new HashMap<>();
//
//            firstNews.put("id", firstInformation.getId());
//            firstNews.put("image", image);
//            firstNews.put("title", firstInformation.getTitle());
//            firstNews.put("content", content);
//            firstNews.put("date", DateTimeUtilT.date(firstInformation.getCreateDate()));
//        }
//        model.addAttribute("firstNews", firstNews);

        List<Map<String, Object>> news = new ArrayList<>();
        int c = 1;
        for (int i = 0; i < datas.size(); i++, c++) {
            if (c > 5) { // 取4条数据
                break;
            }
            TInformation information = datas.get(i);
            String content = information.getContent();
            if (StringUtils.isBlank(content)) {
                content = "";
            }
            if (content.length() > 61) {
                content = content.substring(0, 60) + "...";
            }
            
            Map<String, Object> nw = new HashMap<>();
            nw.put("id", information.getId());
            nw.put("image", information.getImage());
            nw.put("title", information.getTitle());
            nw.put("content", content);
            nw.put("date", DateTimeUtilT.date(information.getCreateDate()));

            news.add(nw);
        }
        model.addAttribute("news", news);

        return "officialwebsite/healthService";
    }

    @RequestMapping(params = "healthServiceOrg", method = RequestMethod.GET)
    public String healthServiceOrg(String pcName, String orgType, String service,
            @RequestParam(required = false, defaultValue = "1") Integer page, String filterName, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (filterName != null)
            params.put("filterName", filterName);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (service != null)
            condition.put("serviceList", service);
        if (orgType != null)
            params.put("orgType", orgType);
        if (pcName != null) {
            String areaCode = areaService.getAreaCode(pcName).get(0);
            if (pcName.equals("北京") || pcName.equals("上海") || pcName.equals("重庆") || pcName.equals("天津")) {
                params.put("pCode", areaCode.substring(0, 2));
            } else {
                params.put("pcCode", areaCode.substring(0, 4));
            }
        }
        if (!condition.isEmpty())
            params.put("condition", condition);

        List<ServiceItem> itemList = orgService.getConsultItemList(params, page, 10);
        int count = orgService.getHealthConsultListCount(params);
        List<Map<String, Object>> serviceTag = orgService.getServiceTags();

        model.addAttribute("itemList", itemList);
        model.addAttribute("serviceTag", serviceTag);
        model.addAttribute("count", count);
        model.addAttribute("initPage", page);
        model.addAttribute("itemList", itemList);
        model.addAttribute("pageCount", count % 10 == 0 ? count / 10 : (int) Math.floor(count / 10) + 1);
        model.addAttribute("service", service);
        model.addAttribute("orgType", orgType);
        model.addAttribute("pcName", pcName);

        return "officialwebsite/healthService_org";
    }

    @RequestMapping(params = "healthserviceorgdetails", method = RequestMethod.GET)
    public String healthServiceOrgDetail(Integer orgId, Model model) {

        OrgDTO org = orgService.getOrg(orgId);

        Map<String, Object> returnMap = new HashMap<>();

        if (org != null) {
            OrgDTO parentOrg = org.getParentOrg();
            if (parentOrg != null) {
                Map<String, Object> parent = new HashMap<>();

                String pAbout = parentOrg.getAbout();
                if (StringUtils.isNotBlank(pAbout) && pAbout.length() > 74) {
                    pAbout = pAbout.substring(0, 73) + "...";
                }

                parent.put("about", pAbout);
                parent.put("name", parentOrg.getOrgName());
                parent.put("logo", parentOrg.getLogo());

                returnMap.put("parent", parent);
            }

            String orgName = org.getOrgName();
            String about = org.getAbout();
            String address = "";
            String logo = org.getLogo();

            returnMap.put("name", orgName);

            String p = org.getProvince();
            String c = org.getCity();
            String d = org.getDistrict();

            String pN = "";
            String cN = "";
            String dN = "";

            if (StringUtils.isNotBlank(p) && StringUtils.isNotBlank(c) && StringUtils.isNotBlank(d)) {
                AreaVO areaVO = areaService.getAreaNameByCode(p, c, d);
                pN = areaVO.getProvinceName();
                cN = areaVO.getCityName();
                dN = areaVO.getDistrictName();

                if (StringUtils.equals(dN, cN)) {
                    dN = "";
                }
                if (StringUtils.equals(pN, cN)) {
                    cN = "";
                }
            }

            address = pN + " " + cN + " " + dN + " " + org.getStreet();

            if (StringUtils.isBlank(address) || address.trim().equals("null")) {
                address = "";
            }
            returnMap.put("address", address);

            if (StringUtils.isNotBlank(about) && about.length() > 51) {
                about = about.substring(0, 50) + "...";
            }
            returnMap.put("about", about);

            if (StringUtils.isBlank(logo)) {
                logo = "";
            }
            returnMap.put("logo", logo);

            List<OrgServeDTO> orgServes = org.getOrgServes();
            for (OrgServeDTO orgServe : orgServes) {
                if (!returnMap.containsKey("serves")) {
                    returnMap.put("serves", new ArrayList<>());
                }
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> ss = (List<Map<String, Object>>) returnMap.get("serves");
                Map<String, Object> s = new HashMap<>();
                s.put("id", orgServe.getId());
                s.put("name", orgServe.getServeType().getName());

                String sAbout = orgServe.getAbout(); //.getServe().getAbout();
                String sImage = ""; //orgServe.getServe().getImage();
                String classify = orgServe.getClassify();

                if (StringUtils.isNotBlank(sAbout) && sAbout.length() > 51) {
                    sAbout = sAbout.substring(0, 50) + "...";
                }

                s.put("about", sAbout);

                if (StringUtils.isBlank(sImage)) {
                    sImage = "";
                }
                s.put("image", sImage);

                String[] cs = classify.split(",");
                List<String> cList = new ArrayList<>();
                for (String cTmp : cs) {
                    cList.add(cTmp);
                }
                s.put("classify", cList);

                List<Map<String, Object>> paies = new ArrayList<>();
                Map<String, Object> pay = null;
                if (orgServe.getHasFree() != null && orgServe.getHasFree()) {
                    pay = new HashMap<>();
                    pay.put("type", 0);
                    pay.put("value", "免费");
                    paies.add(pay);
                }
                if (orgServe.getHasTime() != null && orgServe.getHasTime()) {
                    pay = new HashMap<>();
                    pay.put("type", 1);
                    pay.put("value", NumberUtils.changeF2Y(orgServe.getTimePrice() + ""));
                    pay.put("unit", "元/次");
                    paies.add(pay);
                }
                if (orgServe.getHasMonth() != null && orgServe.getHasMonth()) {
                    pay = new HashMap<>();
                    pay.put("type", 2);
                    pay.put("value", NumberUtils.changeF2Y(orgServe.getMonthPrice() + ""));
                    pay.put("unit", "元/月");
                    paies.add(pay);
                }
                if (orgServe.getHasYear() != null && orgServe.getHasYear()) {
                    pay = new HashMap<>();
                    pay.put("type", 3);
                    pay.put("value", NumberUtils.changeF2Y(orgServe.getYearPrice() + ""));
                    pay.put("unit", "元/年");
                    paies.add(pay);
                }
                s.put("price", paies);

                ss.add(s);

                returnMap.put("serves", ss);
            }

            returnMap.put("detail", org.getDetail());
        }

        model.addAttribute("item", returnMap);

        return "officialwebsite/healthserviceorgdetails";
    }

    private List<TInformation> getNews(String parentColumnName, Integer columnId) {
        List<TInformation> news = new ArrayList<>();

        DataResult dataResult = informationService.loadNewsInformationDatas(parentColumnName, columnId, 1, null, 10);

        PaginationDTO informations = (PaginationDTO) dataResult.getAttr().get("informations");
        List<Object> datas = informations.getDataObject();

        for (Object data : datas) {
            TInformation nw = (TInformation) data;
            news.add(nw);
        }

        return news;
    }

    /**
     * 智能设备页面
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午10:25:11
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "inteDevice", method = RequestMethod.GET)
    public String inteDevice(Model model) {

        List<HealthPackageDTO> hpDTOs = tService.getUserHealthProductsList(null);

        List<InteDeviceVO> idVOs = new ArrayList<>();
        for (HealthPackageDTO hpDTO : hpDTOs) {
            InteDeviceVO idVO = new InteDeviceVO();
            idVO.setImage(hpDTO.getImg_no_circle());
            idVO.setName(hpDTO.getName_cn());
            idVO.setUrl(hpDTO.getShopUrl());
            idVOs.add(idVO);
        }
        model.addAttribute("devices", idVOs);

        List<InteDeviceVO> deviceSets = new ArrayList<>();
        InteDeviceVO deviceSet = new InteDeviceVO();
        deviceSet.setName("便携式健康包");
        deviceSet.setImage("static/images/device/no_circle/jiankangbao.png");
        deviceSet.setUrl("https://item.taobao.com/item.htm?id=42941528924");
        deviceSets.add(deviceSet);

        model.addAttribute("deviceSets", deviceSets);

        return "officialwebsite/inteDevice";
    }
    
    /**
     *  app介绍
     *  @author yuhang.weng 
     *	@DateTime 2017年3月10日 上午10:35:58
     *
     *  @return
     */
    @RequestMapping(params = "appIntroduce", method = RequestMethod.GET)
    public String appIntroduce() {
        return "officialwebsite/appIntroduce";
    }
    
    /**
     *  商家入驻
     *  @author yuhang.weng 
     *	@DateTime 2017年3月10日 上午10:36:39
     *
     *  @return
     */
    @RequestMapping(params = "merchantsSettled", method =RequestMethod.GET)
    public String merchantsSettled() {
        return "officialwebsite/merchantsSettled";
    }
    
    /**
     *  关于我们
     *  @author yuhang.weng 
     *	@DateTime 2017年3月10日 下午4:50:44
     *
     *  @return
     */
    @RequestMapping(params = "aboutUs", method =RequestMethod.GET)
    public ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/aboutUs");
        modelAndView.addObject("data", informationService.loadHelpInformationDatas("帮助中心", null, null, null));
        return modelAndView;
    }

    /**
     * 问卷-亚健康页面
     * @return
     */
    @RequestMapping(params = "subHealth")
    public ModelAndView subHealth() {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/paper/subHealth");
        List<PaperDTO> questionnaireDTOs = question.listPaper("亚健康");
        for (PaperDTO questionnaireDTO : questionnaireDTOs) {
//			System.out.println(questionnaireDTO.toString());
        }
//        List<PaperOptionDTO> optionDTOs = question.listPaperOption("亚健康");
//        for (PaperOptionDTO questionnaireOptionDTO : optionDTOs) {
////			System.	out.println(questionnaireOptionDTO.toString());
//        }
        modelAndView.addObject("questionnaire", JSON.toJSON(questionnaireDTOs));
//        modelAndView.addObject("questionnaireOption", JSON.toJSON(optionDTOs));
        return modelAndView;
    }

    /**
     * 添加问卷测试结果
     * @param paperTypeName
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(params = "addSubHealthResult")
    @ResponseBody
    public AjaxJson addSubHealthResult(
            @RequestParam(value = "paperTypeName") String paperTypeName,
            @RequestParam(value = "result") String result ,HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        PaperResultDTO paperResultDTO = new PaperResultDTO();
        Integer userId = null;
        try {
            userId = getLoginUser().getId();
        } catch (Exception e) {
            System.out.println("游客问卷");
        }

        paperResultDTO.setIp(ServletUtil.getIp(request));
        paperResultDTO.setUserId(userId);
        paperResultDTO.setResult(result);
        paperResultDTO.setPaperTypeName(paperTypeName);
        boolean bool = question.addPaperResult(paperResultDTO);
        if (bool) {
            return ajaxJson;
        }
        ajaxJson.setSuccess(false);
        return ajaxJson;

    }

    /**
     * 亚健康结果分析页面
     * @param score
     * @return
     */
    @RequestMapping(params = "subHealthAnalyze")
    public ModelAndView subHealthAnalyze(@RequestParam(value = "score") Integer score) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/paper/subHealthAnalyze");
        PaperSubHealthStandardDTO subHealthStandardDTO = question.getPaperSubHealthStandard(score);
        int points = -1;
        switch (subHealthStandardDTO.getIntervalNumber()) {
            case 1:
                points = 0;
                break;
            case 2:
                points = 2;
                break;
            case 3:
                points = 6;
                break;
            case 4:
                points = 10;
                break;
            case 5:
                points = 15;
                break;
            default:
                break;
        }
        modelAndView.addObject("subHealthStandard", JSON.toJSON(subHealthStandardDTO));
        modelAndView.addObject("score", score);
        modelAndView.addObject("points", points);

//        System.out.println("sub:" + subHealthStandardDTO.toString());
        return modelAndView;
    }

    /**
     * 问卷-体质页面
     * @return
     */
    @RequestMapping(params = "physicalPaper")
    public ModelAndView physicalPaper() {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/paper/physicalPaper");
        List<PaperDTO> questionnaireDTOs = question.listPaper("体质");
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("体质");
        modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));
        return modelAndView;
    }

    /**
     * 体质结果分析界面
     * @param physicalName
     * @param scoreArr
     * @return
     */
    @RequestMapping(params = "physicalAnalyze")
    public ModelAndView physicalAnalyze(@RequestParam(value = "result") String physicalName,
                                        @RequestParam(value = "scoreArr") List<Integer> scoreArr) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/paper/physicalAnalyze");

        PaperPhysicalStandardDTO physicalStandardDTO = question.getPaperPhysicalStandard(physicalName);
        modelAndView.addObject("physicalStandard", JSON.toJSON(physicalStandardDTO));
        modelAndView.addObject("scoreArr", scoreArr);
        return modelAndView;
    }

}
