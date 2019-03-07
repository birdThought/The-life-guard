package com.lifeshs.customer.controller.statistics;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.UserRecordService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.vo.StatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-01-05
 * 13:46
 * @desc
 */
@Controller
@RequestMapping(value = "datalist")
public class DataListController extends BaseController{

    private final static int CUR_SIZE = 15;

    @Autowired
    private UserRecordService userRecordService;


    @RequestMapping(value = "/list-data-statistics/{page}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson findByDataStatistics(@PathVariable(value = "page")int page,@RequestParam(value = "area",required = false)String area,@RequestParam(value = "users",required = false)Integer users
                                        ,@RequestParam(value = "gender",required = false)Integer gender,@RequestParam(value = "startAge",required = false) Integer startAge,
                                         @RequestParam(value = "endAge",required = false)Integer endAge,@RequestParam(value = "weight",required = false) Integer weight,
                                         @RequestParam(value = "label", required = false)Integer label, @RequestParam(value = "mobile",required = false) String mobile){

        AjaxJson ajaxJson = new AjaxJson();
        LoginUser user = getLoginUser();
        if ("".equals(area)){
            area = null;
        }
        if (users != null){
            users = 0;
        }
        if ("".equals(mobile)){
            mobile = null;
        }
        String start = null;
        String end = null;

        if ((startAge != null) && (endAge != null)) {
            LocalDate date = LocalDate.now();
            start = date.minus(endAge, ChronoUnit.YEARS).toString();
            end = date.minus(startAge, ChronoUnit.YEARS).toString();
        }
        Paging<StatisticsVO> statisticsList = userRecordService.findDataStatisticsList(area, users, gender, start, end, weight, label, mobile, page, CUR_SIZE);
        PaginationDTO<StatisticsVO> pagination = statisticsList.getPagination();
        ajaxJson.setObj(pagination);
        return ajaxJson;
    }
    /**
     * 获取省份
     * @return
     */
    @RequestMapping(params = "getProvince",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getProvince(){
        AjaxJson ajaxJson = new AjaxJson();
        List<Map<String, String>> province = dataAreaService.findAllProvince();
        ajaxJson.setObj(province);
        return ajaxJson;
    }

    /**
     * 根据省份获取城市
     * @param provinceCode
     * @return
     */
    @RequestMapping(params = "getCity", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getCitiesByCode(@RequestParam String provinceCode) {
        if ("".equals(provinceCode)) {
            return null;
        }
        List<Map<String, String>> cities = dataAreaService.findCity( "^" + provinceCode + "[0-9][0-9][0]{2}");
        AjaxJson json = new AjaxJson();
        json.setObj(cities);
        return json;
    }

    @RequestMapping(params = "getArea", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getCitiesArea(@RequestParam String provinceCode) {
        if ("".equals(provinceCode))
            return null;
        String substring = provinceCode.substring(2, 4);
        if (substring.equals("00")){
//            provinceCode = provinceCode.replace("00", "01");
            provinceCode = provinceCode.substring(0, 2) + "01";
        }
        AjaxJson json = new AjaxJson();
        List<Map<String, String>> dis = dataAreaService.findDistrict("^" + provinceCode + "[0-9][1-9]");
        json.setObj(dis);
        return json;
    }
    
    
    
    
    /**
     * 根据省份获取城市
     * @param provinceCode
     * @return
     */
    @RequestMapping(params = "getCityByProvinceCode", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getCityByProvinceCode(@RequestParam String provinceCode) {
        if (provinceCode == null)
            return null;
        String regex = "^" + provinceCode + "[0-9][0-9][0]{2}";
        List<Map<String, String>> cities = dataAreaService.findCity(regex);
        AjaxJson json = new AjaxJson();
        Map<String, Object> city = new HashMap<String, Object>();
        if (cities.size() > 1) {
            cities.remove(0);
        }
        city.put("city", cities);
        List<Map<String, String>> dis = dataAreaService.findDistrict("^" + provinceCode + "01[0-9][1-9]");
        json.setAttributes(city);
        json.setObj(dis);
        return json;
    }
    
    /**
     * @author liaoguo
     * @DateTime 2018年11月16日
     * @serverComment 根据省份城市code正则表达动态获取对应的区域
     */
    @RequestMapping(params = "getAreaByCityCode", method = RequestMethod.GET)
    public @ResponseBody AjaxJson getAreaByCityCode(@RequestParam String cityCode) {
        AjaxJson json = new AjaxJson();
        if (cityCode == null){
            return null;
        }
        String regex = "^" + cityCode + "[0-9][1-9]";
        List<Map<String, String>> districts = dataAreaService.findDistrict(regex);
        json.setObj(districts);
        return json;
    }
}

