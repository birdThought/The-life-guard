package com.lifeshs.customer.controller.org;

import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.org.TOrgServePO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.store.StoreService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.SMSCommand;
import com.lifeshs.utils.StringUtil;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.customer.CustomerSharingDataVO;
import com.lifeshs.vo.member.OrgRcmVo;
import com.lifeshs.vo.org.OrgRecommendVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-02
 * 11:03
 * @desc
 */
@Controller
@RequestMapping("/org/data")
public class OrgDataController extends BaseController {

    private final static int PAGE_SIZE = 15;
    @Autowired
    private StoreService storeService;
    
    @Autowired
    private SMSService sendSMS;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson getOrgList(@PathVariable(value = "page") Integer page, @RequestParam(value = "orgName")String orgName,@RequestParam(value = "p") String p,
                                             @RequestParam(value = "c")String c,@RequestParam(value = "d")String d){
        AjaxJson ajaxJson = new AjaxJson();

        if ("".equals(orgName)){
            orgName = null;
        }
        if ("".equals(p)){
            p = null;
        }
        if ("".equals(c)){
            c = null;
        }
        if ("".equals(d)){
            d = null;
        }
        CustomerSharingDataVO user = getUserSharingData();
        Paging<OrgPO> pg  = storeService.getOrgListData(user.getUserNo(), orgName,p,c,d,page,PAGE_SIZE);
        ajaxJson.setObj(pg.getPagination());
        return ajaxJson;
    }

    @RequestMapping(value = "/getInfo/{id}")
    public @ResponseBody AjaxJson getDetails(@PathVariable(value = "id") Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        OrgPO op = storeService.getDetailsOrg(id);
        ajaxJson.setObj(op);
        return ajaxJson;
    }

    @RequestMapping(value = "/operate/{open}/{id}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyOrg(@PathVariable(value = "open") String open,@PathVariable(value = "id") Integer id,
                                            @RequestParam(value = "isRecommend",required = false) boolean isRecommend, @RequestParam(value = "status",required = false)Integer status,
                                            @RequestParam(value = "orgVerified",required = false)Integer orgVerified,@RequestParam("reason") String reason){
                AjaxJson ajaxJson = new AjaxJson();
        switch (open){
            case "recommend":
                storeService.findByOrgUpdate(isRecommend,id);
            break;
            case "status" :
                storeService.findByOrgSrttus(status,id);
            break;
            case "delVeryRecord" ://删除
                storeService.findByOrgVerify(id,orgVerified);
            break;
            case "orgVerify" : //审核
                EmployeePO employeePO = storeService.findByModifyIrg(id,orgVerified,reason);
                if(employeePO != null && StringUtil.isNotEmpty(employeePO.getMobile()) && Toolkits.verifyPhone(employeePO.getMobile())) {
                    String msg = null;
                    if(orgVerified == 1){
                        msg = "已通过审核祝您使用愉快";
                    }
                    if(orgVerified == 2){
                        msg = "未通过审核原因是"+reason;
                    }
                    
                    try {
                        sendSMS.sendSms(employeePO.getId(), VcodeTerminalType.CUSTOMER, employeePO.getMobile(), SMSCommand.STORE_REGISTER, msg);
                        
                    } catch (SMSException e) {
                        e.printStackTrace();
                    } catch (OperationException e) {
                        e.printStackTrace();
                    }
                }
            break;
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/check/{open}/{page}",method = RequestMethod.POST)
    public @ResponseBody AjaxJson orgCheck(@PathVariable("page") Integer page,@PathVariable("open")String open,@RequestParam(value = "orgOption",required = false) Integer orgOption,
                                           @RequestParam(value = "id",required = false) Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        if (orgOption == null){

        }else if (orgOption == -1){
            orgOption = null;
        }

        switch (open){
            case "list" : 
                CustomerSharingDataVO user = getUserSharingData();
                Paging<OrgPO> p  = storeService.findByOrgCheck(user.getUserNo(),orgOption,page,PAGE_SIZE);
                ajaxJson.setObj(p.getPagination());
            break;
            case "data" :
                OrgPO op = storeService.findByOrgCheckId(id);
            ajaxJson.setObj(op);
            break;
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/rcm/{page}")
    public @ResponseBody AjaxJson getOrgRcm(@PathVariable(value = "page")Integer page) {
        AjaxJson ajaxJson = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        
        Paging<OrgRcmVo> p = storeService.findByOrgRcm(user.getUserNo(), page, PAGE_SIZE);
        List<OrgRcmVo> data = p.getData();
        List<OrgRecommendVO> or = new ArrayList<>();
        for (OrgRcmVo op : data) {
            OrgRecommendVO recommend = new OrgRecommendVO(op.getId(), op.getOrgName(), op.getLogo(), op.getOrgType(), op.getStreet());
            or.add(recommend);
        }
        ajaxJson.setObj(or);
        return ajaxJson;
    }


}
