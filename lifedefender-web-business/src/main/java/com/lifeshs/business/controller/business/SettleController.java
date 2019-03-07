package com.lifeshs.business.controller.business;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.vo.record.ComboOrderVo;
import com.lifeshs.vo.record.RecordComboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-04-13
 * 15:13    套餐推广结算
 * @desc
 */
@Controller("settleController")
@RequestMapping("/settle")
public class SettleController extends BaseController{

    private static final Integer PAGESIZE = 15;

    @Autowired
    private IVipComboService comboService;

    @RequestMapping("/page")
    public ModelAndView getSettleDateList(){
        return new ModelAndView("platform/package/spreadl-list");
    }


    @RequestMapping(value = "/data/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getBusinessSettle(@PathVariable("page")Integer page, @RequestParam(value = "moons",required = false)String moon){
        AjaxJson ajaxJson = new AjaxJson();
        if ("" == moon && moon.equals("")){
            moon = null;
        }
        LoginUser loginUser = getLoginUser();
        Integer superior = loginUser.getSuperior();
        Paging<RecordComboVo> comboList = comboService.findByRecordComboList(moon,superior,page, PAGESIZE);
        ajaxJson.setObj(comboList.getPagination());
        return ajaxJson;
    }

    @RequestMapping(value = "/obtain",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getDetailedComboRecordData(@RequestParam("moons")String moon){
        AjaxJson ajaxJson = new AjaxJson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date(Long.parseLong(moon)));
        LoginUser loginUser = getLoginUser();
        Integer superior = loginUser.getSuperior();
        List<ComboOrderVo> details = comboService.findByComboOrderDetails(superior, format);
        ajaxJson.setObj(details);
        return ajaxJson;
    }
}
