package com.lifeshs.customer.controller.drugs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.constants.common.jianKe.JianKe;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.po.drugs.DrugsPO;
import com.lifeshs.po.drugs.PrescriptionType;
import com.lifeshs.po.drugs.ProductAttribute;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.thirdservice.DrugsService;
import com.lifeshs.thirdservice.impl.WeChatHttpUtils;
import com.lifeshs.utils.JSONHelper;

import net.sf.json.JSONObject;

/**
 * 
 * 药品管理
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年6月6日 下午3:07:30
 */
@RestController
@RequestMapping("/drugs")
public class DrugsManageController {

    static final Logger logger = Logger.getLogger(DrugsManageController.class);
    private static final int CUSTOMER_ORDER_PAGE_SIZE = 10;

    

    
    @Autowired
    private DrugsService drugsService;
  
    /**
     * 获取当前用户
     */
    protected LoginUser getLoginUser() {
        return ClientManager.getSessionUser();
    }

    /**
     * 套餐管理页面
     * 
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView ComboControllerPage() {
        return new ModelAndView("platform/drugs/drugs-manager");
    }

    /**
     * 获取药品列表
     * 
     * @param page
     */
    @RequestMapping("/data/list/{page}")
    @ResponseBody
    public AjaxJson listComboItem(@PathVariable("page") int page,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "productAttribute", required = false) String productAttribute,
            @RequestParam(value = "prescriptionType", required = false) String prescriptionType) {
        AjaxJson ajaxJson = new AjaxJson();

        Paging<DrugsPO> paging = drugsService.listLocalDrugs(productName, productAttribute, prescriptionType, page,
                CUSTOMER_ORDER_PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    /**
     * 同步药品
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "synchro", method = RequestMethod.POST)
    public AjaxJsonV2 synchroDrugs() {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        Map<String, String> headers = new HashMap<String, String>();
        SortedMap<String, Object> params = new TreeMap<String, Object>();

        // 获取参数
        String key = JianKe.LOGIN_PWD;
        String url = JianKe.INTERFACE_URL;
        String cid = JianKe.LOGIN_NAME;
        String signMethod = Constant.MD5;
        String count = Constant.SYNC_DATA_NUMBER;
        String method = Constant.JIANKE_PRODUCT_SYNC;
        headers.put("Content-Type", "application/json");
        String randomStr = WXPayUtil.generateNonceStr().substring(0, 8);

        // 获取药品表中下一次同步时间
        String syncLastDate = drugsService.getSyncLastDate();
//        if (StringUtils.isBlank(syncLastDate)) {
//            ajaxJson.setSuccess(false);
//            ajaxJson.setMsg("同步时间异常,请联系管理员.");
//            return ajaxJson;
//        }
        
        syncLastDate = "2005-01-01 0:00:00";

        // 参数设置
        params.put("method", method);
        params.put("cid", cid);
        params.put("signMethod", signMethod);
        params.put("randomStr", randomStr);
        params.put("startTime", syncLastDate);
        params.put("count", count);

        // MD5签名
        String sign = WeChatHttpUtils.createJKSign(params, key);
        params.put("sign", sign);

        // 将Map转换成json
        JSONObject jsonObject = JSONObject.fromObject(params);
        String josn = jsonObject.toString();
        System.out.println("params:" + params);
        System.out.println("josn:" + josn);

        // 接口调用
        String result = WeChatHttpUtils.httpsRequest(url, "POST", josn, headers);
        System.out.println("result:" + result);
      
        // 把数据保存至本地（了解数据格式再做）
        JSONObject jsonObj = JSONObject.fromObject(result);
        /*String nextTime = jsonObj.get("nextTime").toString();*/
        String nextTime = null;  //临时
        String product = jsonObj.get("productList").toString();
        LoginUser user = getLoginUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String syncTime = sdf.format(new Date());
        List<DrugsPO> list = JSONHelper.toList(product, DrugsPO.class);
        if (list.size() > 0) {
            String ids = drugsService.saveDrugsList(list, syncTime);
            drugsService.saveSyncRecord(user.getId(), syncTime, nextTime, ids);
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/data/getPrescriptionType", method = RequestMethod.POST)
    public AjaxJson getPrescriptionType() {
        AjaxJson ajaxJson = new AjaxJson();
        List<PrescriptionType> list = drugsService.getPrescriptionType();
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value = "/data/getProductAttribute", method = RequestMethod.POST)
    public AjaxJson getProductAttribute() {
        AjaxJson ajaxJson = new AjaxJson();
        List<ProductAttribute> list = drugsService.getProductAttribute();
        ajaxJson.setObj(list);
        return ajaxJson;
    }

}
