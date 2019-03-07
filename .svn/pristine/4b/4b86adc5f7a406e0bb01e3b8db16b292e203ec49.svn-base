package com.lifeshs.customer.controller.org;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.common.OrgStatus;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.entity.org.TOrg;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.service.org.IManagerOrgService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.vo.customer.CustomerSharingDataVO;

/**
 * @author Administrator
 * @create 2018-02-02
 * 10:25
 * @desc
 */
@Controller("orgListController")
@RequestMapping(value = "/org")
public class OrgController extends BaseController{
    
    @Autowired
    private IManagerOrgService managerOrgService;
    
    @Autowired
    protected IOrgUserService orgUserService;

    @RequestMapping(value = "/page")
    public ModelAndView OrgListPage(){
        return new ModelAndView("platform/org/orgList");
    }

    @RequestMapping(value = "/add/page")
    public ModelAndView addOrgPage(){
        return new ModelAndView("platform/org/addOrg");
    }

    @RequestMapping(value = "/check/page")
    public ModelAndView orgCheck(){
        return new ModelAndView("platform/org/orgCheck");
    }

    @RequestMapping("/recommend/page")
    public ModelAndView OrgRcm(){
        return new ModelAndView("platform/org/orgRCM");
    }
    
    /**
     * 
     *  添加门店
     *  @author liaoguo
     *  @DateTime 2018年7月19日 下午2:57:30
     *
     *  @param org
     *  @param orgUser
     *  @return
     */
    @RequestMapping(value = "/addOrg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addOrgInfo(TOrg org, TOrgUser orgUser) {
        AjaxJson resObject = new AjaxJson();
        CustomerSharingDataVO user = getUserSharingData();
        
        //检查机构名是否已被注册
        if(managerOrgService.orgIsExist(org.getOrgName())){
            resObject.setMsg("机构名"+org.getOrgName()+"已被注册!");
            return resObject;
        }
        
        // 检查用户名是否被占用
        if (orgUserService.userIsExist(orgUser.getUserName())) {
            resObject.setMsg("用户名"+orgUser.getUserName()+"已被注册!");
            return resObject;
        }
        
        org.setProvince(org.getProvince().substring(0,2));
        org.setCity(org.getCity().substring(2, 4));
        org.setDistrict(org.getDistrict().substring(4, 6));
        org.setStreet(org.getStreet());
        org.setOrgVerified(0);  //默认未审核
        org.setStatus(0);   // 默认0正常
        org.setParent(0); //父机构ID，没有为0
        org.setCreateDate(new Date());
        org.setStatus(OrgStatus.normal.value());
        org.setParentId(user.getUserNo());

        orgUser.setUserType(org.getType() == 2 ? 2 : 0);//个人机构下为管理员+服务师
        orgUser.setPassword(MD5Utils.encryptPassword(orgUser.getPassword()));
//        orgUser.setOrgId(orgId);
        orgUser.setCreateDate(new Date());
//        orgUser.setUserCode(getNewCode(1));
        orgUser.setStatus(0);  //默认正常 TODO
        int orgId = managerOrgService.registOrg(org, orgUser);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Org.ID, orgId + "");
        resObject.setMsg("注册成功!");
        
        return resObject;
    }
}
