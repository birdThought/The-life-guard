package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Information;
import com.lifeshs.common.constants.app.banner.Banner;
import com.lifeshs.common.constants.app.banner.TypeEnum;
import com.lifeshs.common.constants.app.banner.VipBanner;
import com.lifeshs.common.constants.app.microLesson.MicroLesson;
import com.lifeshs.common.constants.app.vip.VipCombo;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.po.data.AppBannerPO;
import com.lifeshs.pojo.banner.VipBannerCustomDTO;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.data.AppBannerService;
import com.lifeshs.utils.HtmlUtils;

/**
 *  首页
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月18日 下午3:17:58
 */
@RestController(value = "appV24IndexController")
@RequestMapping(value = "app/index/v24")
public class IndexController {

    @Autowired
    private InformationService informationService;
    
    @Resource(name = "appBannerService")
    private AppBannerService bannerService;
    
    /**
     *  获取首页数据
     *  @author yuhang.weng 
     *  @DateTime 2017年8月18日 下午3:17:50
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject index(String json) {
        // banner
        List<Map<String, Object>> bannerList = new ArrayList<>();
        List<AppBannerPO> appBannerList = bannerService.listBanner(TypeEnum.HOME);
        for (AppBannerPO appBanner : appBannerList) {
            Map<String, Object> banner = new HashMap<>();
            banner.put(Banner.IMAGE, appBanner.getNetPath());
            banner.put(Banner.CUSTOM, appBanner.getCustom());
            bannerList.add(banner);
        }
        
        // 微课堂
        List<Map<String, Object>> microLessonList = new ArrayList<>();
        Map<String, Object> lesson1 = new HashMap<>();
        lesson1.put(MicroLesson.IMAGE, "http://www.lifekeepers.cn/lifekeepers_files/index/lesson/lesson1.png");
        lesson1.put(MicroLesson.URL, "http://www.lifekeepers.cn/app/appweb/public-lesson-2/hot-class-details");
        microLessonList.add(lesson1);
        Map<String, Object> lesson2 = new HashMap<>();
        lesson2.put(MicroLesson.IMAGE, "http://www.lifekeepers.cn/lifekeepers_files/index/lesson/lesson2.png");
        lesson2.put(MicroLesson.URL, "http://www.lifekeepers.cn/app/appweb/public-lesson-2/health-wangyuantong");
        microLessonList.add(lesson2);
        Map<String, Object> lesson3 = new HashMap<>();
        lesson3.put(MicroLesson.IMAGE, "http://www.lifekeepers.cn/lifekeepers_files/index/lesson/lesson3.png");
        lesson3.put(MicroLesson.URL, "http://www.lifekeepers.cn/app/appweb/public-lesson-2/health-hongyuantang");
        microLessonList.add(lesson3);
        
        // 推荐资讯
        List<Map<String, Object>> news = new ArrayList<>();
        List<TInformation> datas = informationService.listInformation(1, 3, 3).getData();
        for (TInformation data : datas) {
            Map<String, Object> info = new HashMap<>();
            info.put(Information.IMAGE, data.getImage());
            info.put(Information.TITLE, data.getTitle());
            String content = data.getContent();
            content = HtmlUtils.getTextFromHtml(content);
            if (content.length() > 50) {
                content = content.substring(0, 49) + "...";
            }
            info.put(Information.CONTENT, content);
            info.put(Information.ID, data.getId());
            news.add(info);
        }
        
        // vip服务
        List<Map<String, Object>> vipBannerList = new ArrayList<>();
        List<AppBannerPO> vipBannerPOList = bannerService.listBanner(TypeEnum.HOME_VIP);
        for (AppBannerPO appBanner : vipBannerPOList) {
            VipBannerCustomDTO custom = JSONObject.parseObject(appBanner.getCustom(), VipBannerCustomDTO.class);
            Map<String, Object> banner = new HashMap<>();
            banner.put(Banner.IMAGE, appBanner.getNetPath());
            banner.put(VipCombo.ID, custom.getComboId());
            vipBannerList.add(banner);
        }
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Banner.BANNER, bannerList);
        returnData.put(MicroLesson.MICRO_LESSON, microLessonList);
        returnData.put(Information.INFORMATION, news);
        returnData.put(VipBanner.BANNER, vipBannerList);
        return AppNormalService.success(returnData);
    }
}
