package com.lifeshs.customer.controller.coupon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.po.business.BusinessPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.electronicCoupons.OverdueModelEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.po.OrgPO;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsTempletService;
import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.service1.store.StoreService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsPackageVO;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.CouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

/**
 *  卡券管理
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月24日 上午10:44:46
 */
@RestController(value = "CouponController")
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    private ElectronicCouponsPackageService packageService;
    
    @Autowired
    private ElectronicCouponsTempletService templetService;

    @Resource(name = "v2ProjectService")
    private ProjectService projectService;
    
    @Autowired
    private StoreService storeService;
    
    @Resource(name = "businessUserService")
    private UserService businessService;
    
    /**
     *  卡券首页
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:10:18
     *
     *  @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/platform/coupon/coupon");
        return modelAndView;
    }
    
    /**
     *  分页获取卡券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:10:01
     *
     *  @param curPage
     *  @return
     */
    @RequestMapping(path = "/list/{page}")
    public JSONObject coupons(@PathVariable(value = "page") int curPage) {
        JSONObject returnData = new JSONObject();
        PaginationDTO<CouponsTempletVO> p = templetService.listTemplet(curPage, 10).getPagination();
        List<CouponsTempletVO> dataList = p.getData();
        List<Map<String, Object>> templetDataList = new ArrayList<>();
        if (!dataList.isEmpty()) {
            templetDataList = enclosureTemplet(dataList);
        }
        returnData.put("data", templetDataList);
        returnData.put("totalPage", p.getTotalPage());
        returnData.put("totalSize", p.getTotalSize());
        returnData.put("nowPage", p.getNowPage());
        returnData.put("success", true);
        return returnData;
    }
    
    /**
     *  封装数据
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:09:55
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosureTemplet(List<CouponsTempletVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (CouponsTempletVO data : dataList) {
            String serveItemName = data.getServeItemName();
            String serveName = serveItemName;
            if (StringUtils.isBlank(serveItemName)) {
                serveName = data.getProjectName();
            }
            String termOfValidity = "";
            OverdueModelEnum overDueModel = OverdueModelEnum.findModel(data.getOverdueModel());
            if (OverdueModelEnum.ETERNAL.equals(overDueModel)) {
                termOfValidity = "不会失效";
            }
            if (OverdueModelEnum.END_DATE.equals(overDueModel)) {
                termOfValidity = DateTimeUtilT.date(data.getEndDate()) + "后失效";
            }
            if (OverdueModelEnum.RECKON_AFTER_RECEIVE.equals(overDueModel)) {
                termOfValidity = "领券" + data.getValidDay() + "天后失效";
            }
            
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("id", data.getId());
            returnData.put("name", data.getName());
            returnData.put("orgName", data.getOrgName());
            returnData.put("serveName", serveName);
            returnData.put("price", NumberUtils.changeF2Y(String.valueOf(data.getPrice())));
            returnData.put("termOfValidity", termOfValidity);
            returnData.put("type", "抵扣券");
            returnData.put("modifyDate", data.getModifyDate());
            returnData.put("serveItemId", data.getServeItemId());
            returnData.put("projectCode", data.getProjectCode());
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
    
    /**
     *  删除卡券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午6:32:12
     *
     *  @param id
     *  @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public JSONObject delCoupon(@PathVariable(name = "id") int id) throws OperationException {
        JSONObject root = new JSONObject();
        templetService.deleteTemplet(id);
        root.put("success", true);
        return root;
    }
    
    /**
     *  礼包首页
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:10:29
     *
     *  @return
     */
    @RequestMapping(value = "package/index")
    public ModelAndView packageIndex() {
        ModelAndView modelAndView = new ModelAndView("/platform/coupon/package");
        return modelAndView;
    }
    
    /**
     *  分页获取礼包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:10:39
     *
     *  @param curPage
     *  @return
     */
    @RequestMapping(path = "/package/list/{page}")
    public JSONObject couponsPackage(@PathVariable(value = "page") int curPage) {
        JSONObject returnData = new JSONObject();
        PaginationDTO<ElectronicCouponsPackageVO> p = packageService.listPackage(curPage, 10).getPagination();
        returnData.put("success", true);
        returnData.put("data", enclosurePackage(p.getData()));
        returnData.put("totalSize", p.getTotalSize());
        returnData.put("totalPage", p.getTotalPage());
        returnData.put("nowPage", p.getNowPage());
        return returnData;
    }
    
    private List<Map<String, Object>> enclosurePackage(List<ElectronicCouponsPackageVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ElectronicCouponsPackageVO data : dataList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("id", data.getId());
            returnData.put("name", data.getName());
            returnData.put("code", data.getCode());
            returnData.put("businessId", data.getBusinessId());
            returnData.put("createDate", data.getCreateDate());
            returnData.put("templetList", enclosureTemplet(data.getTempletList()));
            returnDataList.add(returnData);
        }
        
        return returnDataList;
    }
    
    /**
     *  删除礼包
     *  @author yuhang.weng 
     *  @DateTime 2017年12月21日 下午5:10:50
     *
     *  @param id
     *  @return
     */
    @RequestMapping(value = "package/del/{id}", method = RequestMethod.DELETE)
    public AjaxJsonV2 delPackage(@PathVariable(value = "id") int id) throws OperationException {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        packageService.deletePackage(id);
        returnData.setSuccess(true);
        return returnData;
    }
    
    /**
     *  获取服务机构列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月26日 上午9:55:09
     *
     *  @return
     */
    @RequestMapping(value = "/store/stores", method = RequestMethod.GET)
    public AjaxJsonV2 listAllStore() {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<Map<String, Object>> returnDataList = enclosureStore(storeService.listStore(null));
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
    }
    
    /**
     *  获取服务机构列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月26日 上午9:55:18
     *
     *  @param name
     *  @return
     */
    @RequestMapping(value = "/store/name/{name}", method = RequestMethod.GET)
    public AjaxJsonV2 listStore(@PathVariable(value = "name") String name) {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<Map<String, Object>> returnDataList = enclosureStore(storeService.listStore(name));
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
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
    
    /**
     *  获取机构服务列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月26日 上午9:55:27
     *
     *  @param id
     *  @return
     */
    @RequestMapping(value = "/store/{id}/serve", method = RequestMethod.GET)
    public AjaxJsonV2 listServe(@PathVariable(value = "id") int orgId) {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<OrgServiceDTO> services = projectService.listProject(orgId);
        
        // 返回数据集合
        List<Map<String, Object>> dataList = new ArrayList<>();
        // 项目服务project名字
        Map<String, OrgServiceDTO> projectCodeMap = new HashMap<>();
        for (OrgServiceDTO s : services) {
//            String serveCode = s.getServeCode();
            String projectCode = s.getCode();
            // 2018年1月17日11:24:10 优惠券定到projectCode级别
            /*Map<String, Object> data = new HashMap<>();
            data.put("serveItemId", s.getId());
            data.put("projectCode", projectCode);
            data.put("projectName", s.getProjectName());
            data.put("serveItemName", s.getName());
            data.put("name", s.getName());
            data.put("serveCode", serveCode);
            dataList.add(data);
            
            // 咨询服务 健康养生 居家养老 可以直接选择服务项目
            // 课堂服务 下没有再继续细化，只能选择具体服务
            if (ServeType.SERVE_CONSULT.getCode().equals(serveCode)
                    || ServeType.SERVE_HEALTH.getCode().equals(serveCode)
                    || ServeType.SERVE_VISIT.getCode().equals(serveCode)) {
                if (!projectCodeMap.containsKey(projectCode)) {
                    projectCodeMap.put(projectCode, s);
                }
            }*/
            if (!projectCodeMap.containsKey(projectCode)) {
                projectCodeMap.put(projectCode, s);
            }
        }
        for (String projectCode : projectCodeMap.keySet()) {
            OrgServiceDTO s = projectCodeMap.get(projectCode);
            Map<String, Object> data = new HashMap<>();
            data.put("projectCode", s.getCode());
            data.put("projectName", s.getProjectName());
            data.put("name", s.getProjectName());
            data.put("serveCode", s.getServeCode());
            dataList.add(data);
        }
        
        Comparator<Map<String, Object>> comparator = new OrgServiceDTOComparator();
        Collections.sort(dataList, comparator);
        Collections.reverse(dataList);
        returnData.setObj(dataList);
        returnData.setSuccess(true);
        return returnData;
    }
    
    /**
     *  添加卡券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月27日 上午10:22:34
     *
     *  @param coupon
     *  @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxJsonV2 addCoupon(@RequestBody AddElectronicCouponsTempletVO coupon) throws BaseException {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        coupon.setOverDue(OverdueModelEnum.RECKON_AFTER_RECEIVE);
        templetService.addTemplet(coupon);
        returnData.setSuccess(true);
        return returnData;
    }
    
    @RequestMapping(value = {"/orgId/{orgId}/projectCode/{projectCode}/serveItemId/{serveItemId}",
            "/orgId/{orgId}/projectCode/{projectCode}"}, method = RequestMethod.GET)
    public AjaxJsonV2 listCoupon(@PathVariable(value = "orgId") int orgId,
            @PathVariable(value = "projectCode") String projectCode,
            @PathVariable(required = false, value = "serveItemId") Integer serveItemId) {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<CouponsTempletVO> dataList = templetService.listTemplet(orgId, projectCode, serveItemId);
        List<Map<String, Object>> returnDataList = enclosureTemplet(dataList);
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
    }
    
    @RequestMapping(value = "/package/add", method = RequestMethod.POST)
    public AjaxJsonV2 addPackage(@RequestBody AddElectronicCouponsPackageVO addPackageVO) throws BaseException {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        packageService.addPackage(addPackageVO);
        returnData.setSuccess(true);
        return returnData;
    }
    
    /**
     *  获取渠道商列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月2日 下午4:47:43
     *
     *  @return
     */
    @RequestMapping(value = "/business/businesses", method = RequestMethod.GET)
    public AjaxJsonV2 listAllBusiness() {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<Map<String, Object>> returnDataList = enclosureBusiness(businessService.listUser(null));
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
    }
    
    /**
     *  获取渠道商列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月2日 下午4:47:33
     *
     *  @param name
     *  @return
     */
    @RequestMapping(value = "/business/name/{name}", method = RequestMethod.GET)
    public AjaxJsonV2 listBusiness(@PathVariable(value = "name") String name) {
        AjaxJsonV2 returnData = new AjaxJsonV2();
        List<Map<String, Object>> returnDataList = enclosureBusiness(businessService.listUser(name));
        returnData.setObj(returnDataList);
        returnData.setSuccess(true);
        return returnData;
    }
    
    private List<Map<String, Object>> enclosureBusiness(List<BusinessPo> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (BusinessPo data : dataList) {
            Map<String, Object> returnData = new HashMap<>();
            returnData.put("id", data.getId());
            returnData.put("name", data.getName());
            returnDataList.add(returnData);
        }
        return returnDataList;
    }
}

class OrgServiceDTOComparator implements Comparator<Map<String, Object>> {

    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        String c1 = (String) o1.get("projectCode");
        String c2 = (String) o2.get("projectCode");
        if (c1.compareTo(c2) > 0) {
            return 1;
        } else if (c1.compareTo(c2) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}