package com.lifeshs.controller.terminal;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.service.terminal.ITerminalService;

/**
 *  健康包设备管理控制器
 *  @author yuhang.weng  
 *  @DateTime 2016年7月28日 上午10:05:31
 */
@Controller
@RequestMapping("healthDeviceManagerControl")
public class HealthDeviceManagerController extends BaseController{

    private static final Logger logger = Logger.getLogger(HealthDeviceManagerController.class);
    
    @Autowired
    private ITerminalService tService;
    
    /**
     *  (健康包)设备管理页面跳转
     *  @author yuhang.weng 
     *  @DateTime 2016年6月17日 下午2:44:22
     *
     *  @return
     */
    @RequestMapping(params = "showHealthPackageDevices", method = RequestMethod.GET)
    public String showHealthPackageDevices(Model model){
        int userId = getLoginUser().getId();
        model.addAttribute("devices", tService.getUserHealthProductsList(userId));
        return "com/lifeshs/member/device";
    }
    
    /**
     *  检查健康档案数据的完整性
     *  @author yuhang.weng 
     *  @DateTime 2016年9月7日 下午5:53:55
     *
     *  @param pageType 1表示健康包、2表示穿戴设备
     *  @return
     */
    @RequestMapping(params = "isUserHealthDataComplete", method = RequestMethod.GET)
    public @ResponseBody AjaxJson isUserHealthDataComplete(Integer pageType) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        
        LoginUser user = getLoginUser();
        
        MemberSharingData sharingData = getCacheMemberSharingData(user.getId());
        
        String msg = "";
        
        switch(pageType) {
        case 1:
            msg = "绑定健康包设备";
            break;
        case 2:
            msg = "绑定穿戴设备";
            break;
        }
        
        /** 是否检测通过 */
        boolean success = true;
        /** 检测不通过后重定向的页面 */
        String errorRedirectHref = "";
        
        if(sharingData.getHeight() == null || sharingData.getWeight() == null || sharingData.getWaist() == null || sharingData.getBust() == null || sharingData.getHip() == null) {
            success = false;
            errorRedirectHref = "memberControl.do?showUserRecord";
            msg = "请先填写完整个人档案后才可以" + msg;
        }
        if(sharingData.getBirthday() == null || sharingData.getSex() == null) {
            success = false;
            errorRedirectHref = "memberControl.do?getUserInfor";
            msg = "请先填写完整个人信息后才可以" + msg;
        }
        
        if(!success) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("href", errorRedirectHref);
            
            resObject.setMsg(msg);
            resObject.setAttributes(attributes);
            return resObject;
        }
        
        resObject.setSuccess(true);
        return resObject;
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月16日 下午1:43:22
     *  @serverComment 选择(添加/删除)健康包设备
     *
     *  @param typeName 健康包名称
     *  @param cancel true表示该次请求为取消勾选,false表示该次请求为勾选新的健康包
     *  @return
     */
    @RequestMapping(params = "modifyHealthPackageDevices", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyHealthPackageDevices(@RequestParam String typeName, @RequestParam boolean cancel){
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "健康包设备管理失败";
        int userId = getLoginUser().getId();
        if (cancel) {
            try {
                if(!tService.delUserHealthProducts(userId, typeName)){  // 取消勾选
                    resObject.setMsg(msg + ":解绑失败");
                    return resObject;
                }
            } catch (Exception e) {
                logger.error(msg + ":" + e.getMessage());
                resObject.setMsg(msg + ":" + e.getMessage());
                return resObject;
            }
            resObject.setSuccess(true);
            resObject.setMsg("取消健康包成功");
            updateLoginUserData(); // 更新缓存中的loginUser对象
            return resObject;
        } else {
            /*// 如果勾选的是体脂秤，需要用户将健康数据补全
            if(StringUtils.equals(typeName, "BodyFatScale")){
                if(!tService.isUserHealthDataComplete(userId)){
                    resObject.setMsg("请先填写完整个人档案后才可以绑定该健康包设备");
                    resObject.setObj("uncomplete");
                    return resObject;
                }
            }*/
            try {
                if(!tService.addUserHealthProducts(userId, typeName)){ // 勾选
                    resObject.setMsg(msg + ":绑定失败");
                    return resObject;
                }
            } catch (Exception e) {
                logger.error(msg + ":" + e.getMessage());
                resObject.setMsg(msg + ":" + e.getMessage());
                return resObject;
            }
            resObject.setSuccess(true);
            resObject.setMsg("添加健康包成功");
            updateLoginUserData(); // 更新缓存中的loginUser对象
            return resObject;
        }
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月16日 下午1:47:04
     *  @serverComment 健康包参数设置
     *
     *  @param map 健康包参数封装对象
     *  @return
     */
    @RequestMapping(params = "setHealthPackageParams", method = RequestMethod.POST)
    public @ResponseBody AjaxJson setHealthPackageParams(@RequestBody Map<String, Object> map){
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);
        String msg = "健康包参数设置失败";
        
        int age = (Integer)map.get("age");
        
        // 年龄参数审核
        if(age < 0 || age > 200){
            resObject.setMsg("年龄应设置在0-200之间");
            return resObject;
        }

        int userId = getLoginUser().getId();
        boolean gender = (boolean)map.get("sex");
        Float height = ((Double)map.get("height")).floatValue();
        Float weight = ((Double)map.get("weight")).floatValue();
        Float waist = ((Double)map.get("waist")).floatValue();
        Float bust = ((Double)map.get("bust")).floatValue();
        Float hip = ((Double)map.get("hip")).floatValue();
        
        if(!tService.modifyUserHealthPackageParams(userId, gender, height, weight, waist, bust, hip, age)){
            resObject.setMsg(msg);
            return resObject;
        }
        resObject.setSuccess(true);
        resObject.setMsg("修改参数成功");
        return resObject;
    }
    
    @RequestMapping(params = "modifyHealthWarningDevices", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifyHealthWarningDevices(@RequestParam String typeName, @RequestParam boolean cancel){
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "健康包设备管理失败";
        int userId = getLoginUser().getId();
        if (cancel) {
            try {
                if(!tService.delUserHealthWarning(userId, typeName)){   // 取消勾选
                    resObject.setMsg(msg + ":解绑失败");
                    return resObject;
                }
            } catch (Exception e) {
                logger.error(msg + ":" + e.getMessage());
                resObject.setMsg(msg + ":" + e.getMessage());
                return resObject;
            }
            resObject.setSuccess(true);
            resObject.setMsg("取消健康包成功");
            updateLoginUserData(); // 更新缓存中的loginUser对象
            return resObject;
        } else {
            try {
                if(!tService.addUserHealthWarning(userId, typeName)){ // 勾选
                    resObject.setMsg(msg + ":绑定失败");
                    return resObject;
                }
            } catch (Exception e) {
                logger.error(msg + ":" + e.getMessage());
                resObject.setMsg(msg + ":" + e.getMessage());
                return resObject;
            }
            resObject.setSuccess(true);
            resObject.setMsg("添加健康包成功");
            updateLoginUserData(); // 更新缓存中的loginUser对象
            return resObject;
        }
    }
    
//  /**
//   *  判断用户健康数据是否完善
//   *  @author yuhang.weng 
//   *  @DateTime 2016年8月30日 下午8:12:11
//   *
//   *  @param user
//   *  @return
//   */
//  private Map<String, Object> isUserHealthDataComplete(MemberSharingData sharingData) {
//      Map<String, Object> result = new HashMap<>();
//      result.put("bool", true);
//      if(sharingData.getHeight() == null || sharingData.getWeight() == null || sharingData.getWaist() == null || sharingData.getBust() == null || sharingData.getHip() == null) {
//          result.put("bool", false);
//          result.put("type", 1);
//          return result;
//      }
//      if(sharingData.getBirthday() == null || sharingData.getSex() == null) {
//          result.put("bool", false);
//          result.put("type", 2);
//          return result;
//      }
//      return result;
//  }
}
