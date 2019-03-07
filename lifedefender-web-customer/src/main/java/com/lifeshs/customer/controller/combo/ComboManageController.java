package com.lifeshs.customer.controller.combo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.common.Constant;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.po.vip.VipComboItemRelationPO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service1.combo.ComboManageService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.combo.ComboVo;

/**
 * 套餐管理
 * 
 * @author shiqiang.zeng
 * @Date 2018.1.10 14:59
 *
 */
@RestController
@RequestMapping("/combo")
public class ComboManageController {

    private static final int COMBO_MANAGE_PAGE_SIZE = 10;
    
    @Resource(name = "comboManageService")
    private ComboManageService comboManageService;
    
    @Autowired
    private IOrgUserService orgUserService;

    @Autowired
    IVipComboService vipComboService;

    /**
     * 套餐管理页面
     * 
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView ComboControllerPage() {
        return new ModelAndView("platform/combomanage/combo-manage");
    }
    
    /**
     * 获取套餐列表
     * @param page
     */
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public AjaxJsonV2 listCombo(@PathVariable("page") int page) {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        Paging<ComboVo> paging = comboManageService.findComboList(page, COMBO_MANAGE_PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }

    /**
     * 获取vip套餐列表
     * author: wenxian.cai
     * date: 2017/10/11 16:42
     */
    @RequestMapping("/list/id")
    @ResponseBody
    public AjaxJson listVipCombo () {
        AjaxJson ajaxJson = new AjaxJson();
//		Paging paging = vipComboService.listVipCombo(1, VIP_CARD_COMBO_PAGE_SIZE);
        List list = vipComboService.findVipComboList(null);
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public AjaxJsonV2 updateCombo(@RequestParam(value = "vipComboItem") String vipComboItem, ComboVo comboVo,String orgUserIds) throws OperationException, IOException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        String url = ImageUtilV2.copyImgFileToUploadFolder(comboVo.getPhoto(),"vip/combo");
        comboVo.setPhoto(url);
        comboVo.setDetail(Constant.COMBO_MANAGER_DETAIL + comboVo.getDetail());
        comboManageService.updataCombo(comboVo);
        comboManageService.updataComboItemRelation(comboVo.getId(), vipComboItem, orgUserIds);
        return ajaxJson;
    }
    
    /**
     * 删除套餐
     * @param comboVo
     * @return
     */
    @RequestMapping(value="delete", method = RequestMethod.POST)
    public AjaxJsonV2 deleteCombo(ComboVo comboVo) throws OperationException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        comboManageService.deleteCombo(comboVo);
        return ajaxJson;
    }
    /**
     * 
     *  添加套餐
     *  @author NaN
     *  @DateTime 2018年10月15日 上午9:23:20
     *
     *  @param vipComboItem
     *  @param orgUserIds
     *  @param comboVo
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public AjaxJsonV2 addCombo(@RequestParam(value = "vipComboItem") String vipComboItem,String orgUserIds, ComboVo comboVo) throws OperationException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        comboVo.setDetail(Constant.COMBO_MANAGER_DETAIL + comboVo.getDetail());
        comboManageService.addCombo(vipComboItem, orgUserIds, comboVo);
        return ajaxJson;
    }

    /**
     * 文件上传
     * @param target
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile/{target}", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadFile(@PathVariable String target,
    		@RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "file", required = false) MultipartFile uploadFile) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "img":// 图片
            String netPath = "";
            String uploadName = uploadFile.getOriginalFilename();
            String arr[] = uploadName.split("\\.");
            if (!"png".equalsIgnoreCase(arr[arr.length - 1]) && !"jpg".equalsIgnoreCase(arr[arr.length - 1])) {
                resObject.setMsg("图片类型不合法");
                break;
            }
            if (uploadFile.getSize() > 1024 * 1024) {
                resObject.setMsg("图片大小超出限制");
                break;
            }
            if (!"original".equals(type) && uploadFile.getSize() > 200 * 1024) {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true, 500, 500);
            } else {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true);
            }
            resObject.setSuccess(true);
            resObject.setObj(netPath);
            resObject.setMsg("上传文件成功");
            return resObject;
        }
        return resObject;
    }
    
//    @RequestMapping(value="findL1All", method = RequestMethod.GET)
//    public AjaxJsonV2 findL1All(){
//        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
//        List<VipComboPO> list = comboManageService.findL1All();
//        ajaxJson.setObj(list);
//        return ajaxJson;
//    }
    
    /**
     * 
     *  根据套餐id查找关系
     *  @author NaN
     *  @DateTime 2018年6月1日 上午10:27:49
     *
     *  @param vipComboId
     *  @return
     */
    @RequestMapping(value="findComboItemList", method = RequestMethod.GET)
    public AjaxJsonV2 findComboItemList(@RequestParam(value = "vipComboId") Integer vipComboId){
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<Integer,Object> orgUserMap = new HashMap<Integer,Object>();
        Map<String,Object> orgUserInfo = new HashMap<String,Object>();
        List<VipComboItemRelationPO> comboItemList = comboManageService.findComboItemList(vipComboId);
//        List<VipComboItemServeUserRelationPO> comboItemList = comboManageService.findComboItemList(vipComboId);
        map.put("vipComboItemRelation", comboItemList);
        
//        if(comboItemList.size() > 0){
//            VipComboItemRelationPO comboItem = comboItemList.get(0);
//            List<OrgUserDTO> orgUser = orgUserService.findComboServeUserRelation(vipComboId,comboItem.getId());
//            for(int j=0;j<orgUser.size();j++){
//                orgUserInfo = new HashMap<String,Object>();
//                OrgUserDTO user = orgUser.get(j);
//                orgUserInfo.put("id", user.getId());
//                orgUserInfo.put("realName", user.getRealName());
//                orgUserInfo.put("orgName", user.getOrg().getOrgName());
//                list.add(orgUserInfo);
//            }
//            orgUserMap.put(comboItem.getId(), list);
//            map.put("vipComboItemOrgUserRelation", orgUserMap);
//        }
        
        if(comboItemList.size() > 0){
             VipComboItemRelationPO comboItem = comboItemList.get(0);
             //获取服务师
             List<OrgUserPO> comboOrgUserList = orgUserService.findComboOrgUserRelation(vipComboId,comboItem.getId());
             map.put("vipComboOrgUserRelation", comboOrgUserList);
        }
        
        
        ajaxJson.setObj(map);
        System.out.println(map);
        return ajaxJson;
    }
    
    /**
     * 
     *  根据套餐id查找关系
     *  @author NaN
     *  @DateTime 2018年6月1日 上午10:27:49
     *
     *  @param vipComboId
     *  @return
     */
    @RequestMapping(value="findOrgUserRelation", method = RequestMethod.GET)
    public AjaxJsonV2 findOrgUserRelation(@RequestParam(value = "vipComboId") Integer vipComboId,@RequestParam(value = "vipComboItemId") Integer vipComboItemId){
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();

        //获取服务师
        List<OrgUserPO> comboOrgUserList = orgUserService.findComboOrgUserRelation(vipComboId,vipComboItemId);
        
        ajaxJson.setObj(comboOrgUserList);
        return ajaxJson;
    }
    
    /**
     * 添加套餐与服务师关系
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年5月11日 上午11:42:41
     *
     *  @param rName 用户姓名
     *  @return 用户姓名+手机号码
     */
    @RequestMapping("/addOrgUserRelation")
    @ResponseBody
    public AjaxJson addOrgUserRelation(@RequestParam(value = "vipComboId") Integer vipComboId,@RequestParam(value = "vipComboItemId") String vipComboItemId,
            @RequestParam String rName){
        AjaxJson ajaxJson = new AjaxJson();
        
        if(StringUtils.isNotBlank(rName)){
            List<OrgUserPO> orgUserList = orgUserService.getOrgUserByRealName(rName);
            
            if(orgUserList.size()>0){
                for(int i=0;i<orgUserList.size();i++){
                    OrgUserPO userPo = orgUserList.get(i);
                    
                    int result = comboManageService.findComboOrgUserRelation(vipComboId, vipComboItemId,userPo.getId());
                    if(result == 0){
                        //添加套餐与服务师关系
                        comboManageService.addComboOrgUserRelation(vipComboId, vipComboItemId,userPo.getId());
                        ajaxJson.setObj(orgUserList);
                    }
                }
            }
        }
        
        return ajaxJson;
    }
    
    /**
     * 删除套餐与服务师关系
     * @param comboVo
     * @return
     */
    @RequestMapping(value="delOrgUserRelationByVipComboId", method = RequestMethod.POST)
    public AjaxJsonV2 delOrgUserRelationByVipComboId(@RequestParam(value = "vipComboId") Integer vipComboId,@RequestParam(value = "vipComboItemId") String vipComboItemId,
            @RequestParam(value = "userId") String userId) throws OperationException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        comboManageService.delOrgUserRelationByVipComboId(vipComboId,vipComboItemId,userId);
        return ajaxJson;
    }
    
}
