package com.lifeshs.customer.controller.org;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.utils.MD5Utils;

/**
 * 
 *  机构用户的基本控制器
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年5月14日 下午3:31:14
 */
@Controller
@RequestMapping("/orgUser")
public class OrgUserController extends BaseController {

    @Autowired
    private IOrgUserService orgUserService;

    /**
     * 根据姓名查询用户
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年5月11日 上午11:42:41
     *
     *  @param rName 用户姓名
     *  @return 用户姓名+手机号码
     */
    @RequestMapping("/listByRealName")
    @ResponseBody
    public AjaxJson getEmployListByRealName(@RequestParam String rName){
        AjaxJson ajaxJson = new AjaxJson();
        
        if(StringUtils.isNotBlank(rName)){
            List<TOrgUser> orgUserList = orgUserService.getEmployListByRealName(rName, 0);
            ajaxJson.setObj(orgUserList);
        }
        
        return ajaxJson;
    }
    
    
    /**
     * 
     *  根据门店id查找服务师
     *  @author liaoguo
     *  @DateTime 2018年5月23日 上午11:18:39
     *
     *  @param orgId 机构ID
     *  @return 机构用户
     */
    @RequestMapping("/list")
    @ResponseBody
    public AjaxJson findOrgUserListByOrgId(@RequestParam Integer orgId){
        AjaxJson ajaxJson = new AjaxJson();
        
        List<TOrgUser> orguserList = orgUserService.findOrgUserListByOrgId(orgId);
        ajaxJson.setObj(orguserList);

        return ajaxJson;
    }
    
    /**
     * 
     *  根据用户id修改用户密码
     *  @author liaoguo
     *  @DateTime 2018年5月23日 下午4:37:32
     *
     *  @param id
     *  @param pwd
     *  @return
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public AjaxJson updateOrgUserPwdByUserId(
            @RequestParam(value="id") Integer id,
            @RequestParam(value="pwd",required=false) String pwd){
        
        AjaxJson ajaxJson = new AjaxJson();
        //默认值
        if(StringUtils.isBlank(pwd)){
            pwd = Constant.DEFAULT_USER_PWD;
        }
        
        //MD5加密
        pwd = MD5Utils.encryptPassword(pwd);
        
        //执行修改
        int result = orgUserService.updateOrgUserPwdByUserId(id, pwd);
        if(result !=1){
            ajaxJson.setMsg("保存失败!");
            ajaxJson.setSuccess(false);
        }
        
        return ajaxJson;
    }
    
    
    /**
     * 根据姓名查询用户
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年5月11日 上午11:42:41
     *
     *  @param rName 用户姓名
     *  @return 用户姓名+手机号码
     */
    @RequestMapping("/getOrgUserByrealName")
    @ResponseBody
    public AjaxJson getOrgUserByRealName(@RequestParam String rName){
        AjaxJson ajaxJson = new AjaxJson();
        
        if(StringUtils.isNotBlank(rName)){
            List<OrgUserPO> orgUserList = orgUserService.getOrgUserByRealName(rName);
            ajaxJson.setObj(orgUserList);
        }
        
        return ajaxJson;
    }
    
}
