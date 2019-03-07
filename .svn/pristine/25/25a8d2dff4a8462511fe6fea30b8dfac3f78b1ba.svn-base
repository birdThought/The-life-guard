package com.lifeshs.customer.controller.combo;

import java.io.IOException;
import java.util.List;

import com.lifeshs.service1.combo.ComboManageService;
import com.lifeshs.vo.combo.ComboItemListVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.service1.combo.ComboItemManageService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.combo.ComboVo;

import javax.annotation.Resource;

/**
 * 
 *  套餐项目管理
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月1日 下午2:42:24
 */
@RestController
@RequestMapping("/combo/item")
public class ComboItemManageController {

    static final Logger logger = Logger.getLogger(ComboItemManageController.class);
    private static final int CUSTOMER_ORDER_PAGE_SIZE = 10;
    
    @Autowired
    private ComboItemManageService comboItemManageService;


    @Resource(name = "comboManageService")
    private ComboManageService comboManageService;
    /**
     * 套餐管理页面
     * 
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView ComboControllerPage() {
        return new ModelAndView("platform/combomanage/combo-item-manager");
    }

    /**
     * 获取套餐项目列表
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public  AjaxJsonV2 listComboItem() {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        List<ComboItemListVo> list =comboManageService.getComboItemList();
        ajaxJson.setObj(list);
        return ajaxJson;
    }

    /**
     * 获取客服工单列表
     * @param page
     */
    @RequestMapping("/work-list/{page}")
    @ResponseBody
    public AjaxJson listComboItem(@PathVariable("page") int page) {
        AjaxJson ajaxJson=new AjaxJson();
        Paging<VipComboItemPO> paging=comboItemManageService.listComboItem(page, CUSTOMER_ORDER_PAGE_SIZE);
        ajaxJson.setObj(paging.getPagination());
        return ajaxJson;
    }
    
    /**
     * 添加套餐
     * @param comboItemVo
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public AjaxJsonV2 addComboItem(VipComboItemPO comboItemVo) throws OperationException, IOException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        if(StringUtils.isNotBlank(comboItemVo.getIcon())){
            String url = ImageUtilV2.copyImgFileToUploadFolder(comboItemVo.getIcon(), "vip/combo/item");
            comboItemVo.setIcon(url);
        }
        comboItemVo.setItemDetail(Constant.COMBO_MANAGER_DETAIL + comboItemVo.getItemDetail());
        comboItemManageService.addComboItem(comboItemVo);
        return ajaxJson;
    }
    
    @RequestMapping(value="edit", method = RequestMethod.POST)
    public AjaxJsonV2 updateComboItem(VipComboItemPO comboItemVo) throws OperationException, IOException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        comboItemVo.setItemDetail(Constant.COMBO_MANAGER_DETAIL + comboItemVo.getItemDetail());
        if(StringUtils.isNotBlank(comboItemVo.getIcon())){
            String url = ImageUtilV2.copyImgFileToUploadFolder(comboItemVo.getIcon(), "vip/combo/item");
            comboItemVo.setIcon(url);
        }
        comboItemManageService.updataComboItem(comboItemVo);
        return ajaxJson;
    }
    
    /**
     * 删除套餐
     * @param comboItemId
     * @return
     */
    @RequestMapping(value="delete", method = RequestMethod.POST)
    public AjaxJsonV2 deleteComboItem(Integer comboItemId) throws OperationException {
        AjaxJsonV2 ajaxJson = new AjaxJsonV2();
        comboItemManageService.deleteComboItem(comboItemId);
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
            @RequestParam(value = "file", required = false) MultipartFile uploadFile) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "img":// 图片
            String netPath = "";
            String uploadName = uploadFile.getOriginalFilename();
            String arr[] = uploadName.split("\\.");
            if (!"png".equals(arr[arr.length - 1]) && !"jpg".equals(arr[arr.length - 1])) {
                resObject.setMsg("图片类型不合法");
                break;
            }
            if (uploadFile.getSize() > 1024 * 1024) {
                resObject.setMsg("图片大小超出限制");
                break;
            }
            if (uploadFile.getSize() > 200 * 1024) {
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
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
