package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.ProjectServe;
import com.lifeshs.common.constants.app.Serve;
import com.lifeshs.common.constants.app.electronicCoupons.Coupons;
import com.lifeshs.common.constants.app.electronicCoupons.CouponsPackage;
import com.lifeshs.common.constants.app.electronicCoupons.CouponsTemplet;
import com.lifeshs.common.constants.common.electronicCoupons.OverdueModelEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.po.electronicCoupons.ElectronicCouponsTempletPO;
import com.lifeshs.po.user.UserElectronicCouponsPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

/**
 *  电子券controller
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月15日 下午3:15:16
 */
@RestController(value = "appV24CouponsController")
@RequestMapping(value = "app/coupons/v24")
public class CouponsController {

    @Resource(name = "electronicCouponsPackageService")
    private ElectronicCouponsPackageService packageService;
    
    @Resource(name = "electronicCouponsService")
    private ElectronicCouponsService couponsSerivce;
    
    /**
     *  获取电子券卡包详情
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:13:33
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getPackageDetail", method = RequestMethod.POST)
    public JSONObject getPackageDetail(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String code = mm_0.getString(CouponsPackage.CODE);
        ElectronicCouponsPackageVO packageVO = packageService.getPackage(code);
        if (packageVO == null) {
            return AppNormalService.error("找不到该卡包信息", ErrorCodeEnum.NOT_FOUND);
        }
        // 封装卡包信息
        List<Map<String, Object>> returnTempletList = new ArrayList<>();
        for (ElectronicCouponsTempletPO templet : packageVO.getTempletList()) {
            double price = NumberUtils.changeF2Y(String.valueOf(templet.getPrice()));
            OverdueModelEnum overDueModel = OverdueModelEnum.findModel(templet.getOverdueModel());
            String termOfValidity = "";
            if (OverdueModelEnum.ETERNAL.equals(overDueModel)) {
                termOfValidity = "不会失效";
            }
            if (OverdueModelEnum.END_DATE.equals(overDueModel)) {
                termOfValidity = DateTimeUtilT.date(templet.getEndDate()) + "后失效";
            }
            if (OverdueModelEnum.RECKON_AFTER_RECEIVE.equals(overDueModel)) {
                termOfValidity = "领券" + templet.getValidDay() + "天后失效";
            }
            
            Map<String, Object> returnTemplet = new HashMap<>();
            returnTemplet.put(CouponsTemplet.NAME, templet.getName());
            returnTemplet.put(CouponsTemplet.PRICE, price);
            returnTemplet.put(CouponsTemplet.TERM_OF_VALIDITY, termOfValidity);
            returnTempletList.add(returnTemplet);
        }
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(CouponsPackage.INSTRUCTIONS, packageVO.getInstructions());
        returnData.put(CouponsTemplet.TEMPLET, returnTempletList);
        return AppNormalService.success(returnData);
    }
    
    /**
     *  添加电子券
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 下午3:13:23
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "addCoupons", method = RequestMethod.POST)
    public JSONObject addCoupons(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String packageCode = mm_0.getString(CouponsPackage.CODE);
        couponsSerivce.addCoupons(packageCode, userId);
        return AppNormalService.success();
    }
    
    /**
     *  获取电子券列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 上午11:58:29
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listCoupons", method = RequestMethod.POST)
    public JSONObject listCoupons(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        List<UserElectronicCouponsPO> couponsList = couponsSerivce.listCoupons(userId, curPage, pageSize).getData();
        
        List<Map<String, Object>> returnDataList = enclosureCouponsList(couponsList);
        return AppNormalService.success(returnDataList);
    }
    
    private List<Map<String, Object>> enclosureCouponsList(List<UserElectronicCouponsPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (UserElectronicCouponsPO data : dataList) {
            returnDataList.add(enclosureCoupons(data));
        }
        return returnDataList;
    }
    
    private Map<String, Object> enclosureCoupons(UserElectronicCouponsPO data) {
        int type = 1;   // 电子券类型 1表示服务项目 2表示具体服务
        String projectCode = data.getProjectCode();
        Integer serveItemId = data.getServeItemId();
        if (serveItemId != null) {
            type = 2;
        }
        String endDate_s = "";
        Date endDate = data.getEndDate();
        if (endDate != null) {
            endDate_s = DateTimeUtilT.date(data.getEndDate());
        }
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(Coupons.ID, data.getId());
        returnData.put(Coupons.NAME, data.getName());
        returnData.put(Coupons.END_DATE, endDate_s);
        returnData.put(Coupons.CREATE_DATE, DateTimeUtilT.date(data.getCreateDate()));
        returnData.put(Coupons.STATUS, data.getStatus());
        returnData.put(Serve.CODE, data.getServeCode());
        returnData.put(ProjectServe.CODE, projectCode);
        returnData.put(Coupons.SERVE_ITEM_ID, serveItemId);
        returnData.put(Coupons.TYPE, type);
        returnData.put(Coupons.PRICE, NumberUtils.changeF2Y(String.valueOf(data.getPrice())));
        returnData.put(Org.ID, data.getOrgId());
        return returnData;
    }
    
    /**
     *  获取可使用的电子券列表
     *  @author yuhang.weng 
     *  @DateTime 2017年12月15日 上午11:57:56
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listUsableCoupons", method = RequestMethod.POST)
    public JSONObject listUsableCoupons(String json) throws ParamException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        String projectCode = mm_0.getString(ProjectServe.CODE);
        int code = Integer.parseInt(mm_0.getString(Serve.CODE));
        Integer serveItemId = mm_0.getInteger("serveItemId");
        
        ProjectType projectType = ProjectType.values()[code];
        if (StringUtils.isBlank(projectCode)) {
            throw new ParamException("项目code不能为空", ErrorCodeEnum.MISSING);
        }
        List<UserElectronicCouponsPO> couponsList = couponsSerivce.listCouponsUsable(userId, projectType, projectCode, serveItemId, curPage, pageSize).getData();
        
        List<Map<String, Object>> returnDataList = enclosureCouponsList(couponsList);
        return AppNormalService.success(returnDataList);
    }
}
