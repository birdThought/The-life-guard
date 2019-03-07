package com.lifeshs.business.controller.codePackage;

import com.lifeshs.business.controller.common.BaseController;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.org.employee.OrgEmploy;
import com.lifeshs.service1.business.BusinessCodeService;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.store.StoreService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-03-13
 * 14:37
 * @desc
 */
@Controller("sellManageController")
@RequestMapping("sell")
public class SellManageController extends BaseController {

    private static final int PAGESIZE = 15;

    @Resource(name = "businessCodeService")
    private BusinessCodeService businessCodeService;
    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @RequestMapping("page")
    public ModelAndView getSellManagement(){
        return new ModelAndView("platform/business/business");
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getDataList(@PathVariable("page") Integer page){
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        Integer id = loginUser.getSuperior();
        Paging<BusinessUserPO> sell = businessCodeService.findBySell(id,page,PAGESIZE);
        ajaxJson.setObj(sell.getPagination());
        return ajaxJson;
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public @ResponseBody AjaxJson ModifyData(@RequestParam("id")Integer id,@RequestParam("userName") String userName,@RequestParam("name") String name){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        int i = businessCodeService.ModifySell(id, userName, name);
        if (i != 0){
            ajaxJson.setSuccess(true);
            return ajaxJson;
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody AjaxJson addUserBusiness(@RequestParam("userName")String userName,@RequestParam("name")String name,@RequestParam("address") String address
                                                  ,@RequestParam("phone")String phone,@RequestParam("status")Integer status, @RequestParam("password") String password,
                                                  @RequestParam("mailbox")String email,BusinessUserPO bup){

        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = getLoginUser();
        Integer id = loginUser.getSuperior();
          int i = userService.addbusinessUser(id,bup);
            if (i != 0) {
                ajaxJson.setSuccess(true);
                return ajaxJson;
            }
        ajaxJson.setSuccess(false);
        return ajaxJson;
    }

    @RequestMapping(value = "/store/stores", method = RequestMethod.GET)
    public @ResponseBody AjaxJson listAllStore() {
        AjaxJson returnData = new AjaxJson();
        List<Map<String, Object>> returnDataList = enclosureStore(storeService.listStore(null));
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
    }

    @RequestMapping(value = "/server/{storeId}",method = RequestMethod.GET)
    public @ResponseBody AjaxJson getServer(@PathVariable("storeId")Integer storeId){
        AjaxJson  ajaxJson = new AjaxJson();
        List<OrgEmploy> list = businessCodeService.getSercerId(storeId);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    private List<Map<String, Object>> enclosureStore(List<OrgPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (OrgPO data : dataList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("id", data.getId());
            returnData.put("orgName", data.getOrgName());
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
}
