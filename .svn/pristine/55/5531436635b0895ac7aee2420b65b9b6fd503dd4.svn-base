package com.lifeshs.customer.controller.homeWorld;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.service1.transfer.TransferCleaningService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @create 2018-01-19
 * 15:06
 * @desc
 */
@Controller
@RequestMapping("home")
public class HousekeepingController extends BaseController {

    static final Logger logger = Logger.getLogger(HousekeepingController.class);
    @Autowired
    private TransferCleaningService transferCleaningService;

    /**
     *  totalprice : null, //总金额
     customername : null,
     customermobile : null,
     workdistrict : null, //区
     workaddress : null, //详细
     details : null, //其他需求
     yonggongshijian : null, //用工时间
     gender : null,
     language : null,//语言
     age : null,
     clean : null, //次
     home : null, //天
     area : null, //面积
     pulation : null,//人口
     yonggongshichang : null,//服务时长
     */
    @RequestMapping(value = "world",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addOrderWorld(String totalprice, String customername, String customermobile, String workdistrict, String workaddress, String details,String youxiqi,
                                  String yonggongshijian, Integer yonggongshichang, String area, Integer age, Integer gender, Integer language,Integer pulation) throws ParseException {
        AjaxJson ajaxJson = new AjaxJson();
        BigDecimal price = new BigDecimal(totalprice);
        int i = transferCleaningService.saveTransferleaning(price, customername, customermobile, workdistrict, workaddress, details,youxiqi,yonggongshijian, yonggongshichang, area, gender, language, pulation);
        if ( i == 0){
            ajaxJson.setMsg("添加失败");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        return ajaxJson;
    }
}
