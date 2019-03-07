package com.lifeshs.app.api.member.v24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.BusinessConstant;
import com.lifeshs.common.constants.app.HuanXin;
import com.lifeshs.common.constants.app.NormalMessage;
import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.app.Org;
import com.lifeshs.common.constants.app.OrgUser;
import com.lifeshs.common.constants.app.Page;
import com.lifeshs.common.constants.app.ProjectServe;
import com.lifeshs.common.constants.app.business.BusinessCard;
import com.lifeshs.common.constants.app.famousDoctor.FamousDoctor;
import com.lifeshs.common.constants.app.famousDoctor.FamousDoctorOrder;
import com.lifeshs.common.constants.app.healthPackage.BloodLipid;
import com.lifeshs.common.constants.app.healthPackage.Ua;
import com.lifeshs.common.constants.app.healthPackage.Uran;
import com.lifeshs.common.constants.app.user.Customer;
import com.lifeshs.common.constants.app.vip.VipCombo;
import com.lifeshs.common.constants.app.vip.VipComboItem;
import com.lifeshs.common.constants.app.vip.VipOrder;
import com.lifeshs.common.constants.app.vip.VipPhysicalItem;
import com.lifeshs.common.constants.app.vip.VipReport;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.constants.common.order.VipOrderTypeEnum;
import com.lifeshs.common.constants.common.vip.VipStatusEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.business.BusinessCardPO;
import com.lifeshs.po.comboLevel.SecondLevelComboPo;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.po.data.PhysicalPO;
import com.lifeshs.po.famousDoctor.FamousDoctorPO;
import com.lifeshs.po.order.FamousDoctorOrderPO;
import com.lifeshs.po.order.VipUserOrderPO;
import com.lifeshs.po.user.PhysicalItemPO;
import com.lifeshs.po.user.ReportAnalysisPO;
import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.po.vip.VipUserPO;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.service1.business.BusinessCardService;
import com.lifeshs.service1.customer.CustomerUserService;
import com.lifeshs.service1.data.PhysicalItemService;
import com.lifeshs.service1.famousDoctor.FamousDoctorService;
import com.lifeshs.service1.levelCombo.LevelComboService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.member.ReportAnalysisService;
import com.lifeshs.service1.order.famousDoctor.FamousDoctorOrderService;
import com.lifeshs.service1.order.vip.VipUserOrderService;
import com.lifeshs.service1.record.PhysicalAnalysisService;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.utils.HttpUtils;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.Toolkits;
import com.lifeshs.vo.analysisReport.BloodLipidVO;
import com.lifeshs.vo.analysisReport.UaVO;
import com.lifeshs.vo.analysisReport.UranVO;
import com.lifeshs.vo.comboLevel.LevelComboVo;
import com.lifeshs.vo.famousDoctor.ProfessionKindVO;
import com.lifeshs.vo.serve.consult.OrgUserVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;
import com.lifeshs.vo.vip.VipComboVO;

/**
 *  用户vip相关
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月18日 下午2:04:40
 */
@RestController(value = "v24VipController")
@RequestMapping(value = "app/vip/v24")
public class VipController {
    private final Logger logger = LoggerFactory.getLogger(VipController.class);

    @Resource(name = "reportAnalysisService")
    private ReportAnalysisService reportService;
    
    @Resource(name = "dataPhysicalServiceImpl")
    private PhysicalItemService physicalItemService;
    
    @Resource(name = "userPhysicalItemServiceImpl")
    private com.lifeshs.service1.member.PhysicalItemService userPhysicalItemService;
    
    @Resource(name = "vipUserService")
    private IVipUserService vipUserService;
    
    @Resource(name = "vipUserOrderServiceImpl")
    private VipUserOrderService vipUserOrderService;
    
    @Resource(name = "vipComboService")
    private IVipComboService comboService;
    
    @Resource(name = "famousDoctorServiceImpl")
    private FamousDoctorService famousDoctorService;
    
    @Resource(name = "famousDoctorOrderServiceImpl")
    private FamousDoctorOrderService famousDoctorOrderService;
    
    @Resource(name = "physicalAnalysisServiceImpl")
    private PhysicalAnalysisService physicalAnalysisService;
    
    @Resource(name = "v2ConsultService")
    private ConsultService consultService;
    
    @Resource(name = "customerUserService")
    private CustomerUserService customerUserService;
    
    @Resource(name = "measureAnalysisServiceImpl")
    private MeasureAnalysisService measureAnalysisService;
    
    @Resource(name="levelComboServiceImpl")
    private LevelComboService levelComboService;
    
    @Resource(name="businessCardService")
    private BusinessCardService businessCardService;
    
    /** start 不需要检测用户会员状态 */
    
    /**
     *  输入激活码
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午2:04:14
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "entryVipCode", method = RequestMethod.POST)
    public JSONObject entryVipCode(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        String code = mm_0.getString(BusinessCard.CODE);
        int comboId = mm_0.getIntValue(VipCombo.ID);
        vipUserService.activateVip(userId, comboId, code);
        return AppNormalService.success();
    }
    
    /**
     * 
     *  扫一扫 激活会员套餐
     *  @author liaoguo
     *  @DateTime 2018年12月4日 上午11:51:29
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "scanVipCombo", method = RequestMethod.POST)
    public JSONObject scanVipCombo(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int comboId = mm_0.getIntValue(VipCombo.ID);
        int businessId = mm_0.getIntValue("businessId");
        if(businessId == 0 || StringUtils.isBlank(businessId+"")){
            businessId = BusinessConstant.BUSINESS_ID_GZTZDQSYYXGS_25;
        }
        
        //目前只支持81天天健康，如果后续增加其他的，考虑把开通的套餐id用数据表存储。
        //防止恶意制作其它编号套餐id攻击
        if(comboId != 81){
            throw new OperationException("扫描二维码有误!", ErrorCodeEnum.FAILED);
        }
        
        
        //验证用户是否已购买
        String msg = checkVipUserCondition(userId, comboId);
        if(StringUtils.isNotBlank(msg)){
            throw new OperationException(msg, ErrorCodeEnum.FAILED);
        }
        
        try {
            //创建一个激活码
            BusinessCardPO po = new BusinessCardPO();
            po.setBusinessId(businessId);
            po.setVipComboId(comboId);
            businessCardService.addBusinessCardBatch(po, 1);
            
            String code = po.getCode();
            
            //激活
            vipUserService.activateVip(userId, comboId, code);
        } catch (OperationException o) {
            logger.error(o.getMessage());
            throw new OperationException("激活套餐失败！", ErrorCodeEnum.FAILED);
        }
        
        return AppNormalService.success();
    }
    
    
  //检查用户是否重复购买
    public String checkVipUserCondition(Integer userId,int vipComboId){
        String message = "";
        
        // 查找未过期的vip服务
        List<VipUserPO> vipUserList = vipUserService.findVipUserListWithCondition(userId, VipStatusEnum.NORMAL.getValue(), null);
        
        for (VipUserPO vipUser : vipUserList) {
            // 如果会员服务未过期，无法重复使用激活码
            int vipUserVipComboId = vipUser.getVipComboId();
            if (vipUserVipComboId == vipComboId) {
                message = "该会员服务尚未过期，请勿重复使用激活码";
            }
            if (vipUserVipComboId != vipComboId) {
                // 一个用户只能激活一项套餐 2017年10月24日16:54:32
                message = "暂不支持激活多项vip套餐";
            }
        }
        
        return message;
    }
    
    
    
    
    /**
     *  添加会员订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午1:57:17
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "addVipOrder", method = RequestMethod.POST)
    public JSONObject addVipOrder(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        int vipComboId = appJSON.getData().getFirstJSONObject().getIntValue(VipCombo.ID);
        VipOrderTypeEnum vipOrderType = VipOrderTypeEnum.PAY;
        Integer businessCardId = null;
        if(appJSON.getData().getFirstJSONObject().containsKey("businessUserId")){
            try{
                businessCardId = appJSON.getData().getFirstJSONObject().getIntValue("businessUserId");
                if(businessCardId != null && businessCardId > 0){
                    vipOrderType = VipOrderTypeEnum.QRPAY;
                }else {
                    businessCardId = null;
                }
            }catch (Exception ex){}
        }
        Integer businessId = appJSON.getData().getFirstJSONObject().getIntValue("businessId");

        // app用户添加订单,不需要填写businessCardId,businessIncome,businessId 
        VipUserOrderPO order = vipUserOrderService.addOrder(userId, vipComboId, OrderStatus.PAYABLE, "购买会员服务", businessCardId, vipOrderType, null, businessId);
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(VipOrder.ID, order.getId());
        return AppNormalService.success(returnData);
    }
    
    /**
     *  获取订单支付信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午1:57:34
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "getVipOrderInfo", method = RequestMethod.POST)
    public JSONObject getVipOrderInfo(String json,HttpServletRequest request) throws OperationException {
        Map<String, Object> returnData = new HashMap<>();
        
        //区分支付方式    1：支付宝   2：微信
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject msg_0 = appJSON.getData().getFirstJSONObject();
        int payType = msg_0.getInteger(Order.PAY_TYPE);
        
        String sign = "";
        int orderId = appJSON.getData().getFirstJSONObject().getIntValue(VipOrder.ID);
        if(PayTypeEnum.ALIPAY.getValue() == payType){
            sign = vipUserOrderService.getAliPaySign(orderId);
            returnData.put(VipOrder.SIGN, sign);
        }else if(PayTypeEnum.WECHAT.getValue() == payType){
            String spbill_create_ip = HttpUtils.getIpAddress(request);
            int orderType = PayReturnOrderTypeEnum.VIP.getValue();
            
            returnData = vipUserOrderService.getWeChatPaySign(orderId,spbill_create_ip,orderType);
        }
        return  AppNormalService.success(returnData);
    }
    
    /**
     *  获取vip套餐列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午3:02:49
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listCombo", method = RequestMethod.POST)
    public JSONObject listCombo(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        int curPage = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        String l1 = mm_0.getString("l1");
        String l2 = mm_0.getString("l2");
        
        if (StringUtils.isBlank(l1)) {
            l1 = null;
        }
        if (StringUtils.isBlank(l2)) {
            l2 = null;
        }
        
        List<VipComboVO> dataList = comboService.listVipCombo(l1, l2, curPage, pageSize).getData();
        if (dataList.isEmpty()) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> returnDataList = enclosureComboList(dataList, false);
        return AppNormalService.success(returnDataList);
    }
    /**
     * 两级获取套餐列表
     * @param json
     * @return
     */
    @RequestMapping(value = "listComboCondition", method = RequestMethod.POST)
    public JSONObject listComboCondition(String json) {
    	List<LevelComboVo> dataList = levelComboService.listLevel();	// 数据库查询VO
    	List<Map<String, Object>> returnDataList = new ArrayList<>();
    	for (LevelComboVo d : dataList) {
    		Map<String, Object> level = new HashMap<>();
    		
    		level.put("name", d.getName());
        	List<Map<String, Object>> secondLevelList = new ArrayList<>();
        	for (SecondLevelComboPo l : d.getSecondLevelComboList()) {
        		Map<String, Object> secondLevel = new HashMap<>();
        		secondLevel.put("name", l.getName());
        		secondLevelList.add(secondLevel);
        	}
        	level.put("child", secondLevelList);
        	returnDataList.add(level);
    	}
    	
        return AppNormalService.success(returnDataList);
    }
   

    /**
     *  获取vip套餐详情
     *  @author yuhang.weng 
     *  @DateTime 2017年10月20日 上午10:43:45
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getCombo", method = RequestMethod.POST)
    public JSONObject getCombo(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        int id = mm_0.getIntValue(VipCombo.ID);
        VipComboVO combo = comboService.getVipCombo(id);
        if (combo == null) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        Map<String, Object> returnData = enclosureCombo(combo, false);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  vip套餐数据封装
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午3:44:11
     *
     *  @param dataList 数据源
     *  @param withItem 是否添加服务详情项目
     *  @return
     */
    private List<Map<String, Object>> enclosureComboList(List<VipComboVO> dataList, boolean withItem) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (VipComboVO data : dataList) {
            returnDataList.add(enclosureCombo(data, withItem));
        }
        return returnDataList;
    }
    
    /**
     *  vip套餐数据封装
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午3:44:11
     *
     *  @param data 数据源
     *  @param withItem 是否添加服务详情项目
     *  @return
     */
    private Map<String, Object> enclosureCombo(VipComboVO data, boolean withItem) {
        double price = NumberUtils.changeF2Y(String.valueOf(data.getPrice()));
        double oPrice = NumberUtils.changeF2Y(String.valueOf(data.getOriginalPrice()));
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(VipCombo.ID, data.getId());
        returnData.put(VipCombo.PHOTO, data.getPhoto());
        returnData.put(VipCombo.NAME, data.getName());
        returnData.put(VipCombo.DESCRIPTION, data.getDescription());
        returnData.put(VipCombo.PRICE, price);
        returnData.put(VipCombo.PRICE_ORIGINAL, oPrice);
        
        returnData.put(VipCombo.VALID_DAY, data.getValidDay());
        returnData.put(VipCombo.DETAIL, data.getDetail());
        returnData.put(VipCombo.SUITABLE_PEOPLE, data.getSuitablePeople());
        
        if (withItem) {
            List<Map<String, Object>> itemMapList = new ArrayList<>();
            List<VipComboItemPO> itemList = data.getItemList();
            for (VipComboItemPO item : itemList) {
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put(VipComboItem.ID, item.getId());
                itemMap.put(VipComboItem.NAME, item.getName());
                itemMapList.add(itemMap);
            }
            returnData.put(VipComboItem.ITEM, itemMapList);
        }
        return returnData;
    }
    
    /** end 不需要检测用户会员状态 */
    
    @RequestMapping(value = "getReport", method = RequestMethod.POST)
    public JSONObject getReport(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData().getFirstJSONObject();
        int id = data.getIntValue(VipReport.ID);
        
        ReportAnalysisPO report = reportService.getReport(id);
        if (report == null) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        Map<String, Object> returnData = enclosureReport(report);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  获取分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午2:15:22
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "listReport", method = RequestMethod.POST)
    public JSONObject listReport(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        JSONObject data = appJSON.getData().getFirstJSONObject();
        int curPage = data.getIntValue(Page.INDEX);
        int pageSize = data.getIntValue(Page.SIZE);
        
        List<ReportAnalysisPO> reportList = reportService.listReport(curPage, pageSize, userId, null, null, false, null, null).getData();
        if (reportList.size() == 0) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        
        List<Map<String, Object>> returnDataList = enclosureReportList(reportList);
        return AppNormalService.success(returnDataList);
    }
    
    private List<Map<String, Object>> enclosureReportList(List<ReportAnalysisPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ReportAnalysisPO data : dataList) {
            returnDataList.add(enclosureReport(data));
        }
        return returnDataList;
    }
    
    private Map<String, Object> enclosureReport(ReportAnalysisPO data) {
        Map<String, Object> returnData = new HashMap<>();
        
        int healthProduct = data.getHealthProduct();
        String content = data.getContent();
        String reply = data.getReply();
        String doctorSign = data.getDoctorSign();
        
        HealthPackageType hpt = HealthPackageType.getHealthPackageType(healthProduct);
        // 报告状态，0表示未处理， 1表示已回复
        int status = 0;
        if (reply != null && data.getReplyUserId() != null) {
            status = 1;
        }
        
        Map<String, Object> contentMap = new HashMap<>();
        // 血脂
        if (HealthPackageType.BloodLipid.equals(hpt)) {
            BloodLipidVO bloodLipidVO = JSONObject.parseObject(content, BloodLipidVO.class);
            contentMap.put(BloodLipid.HDL, bloodLipidVO.getHdl());
            contentMap.put(BloodLipid.LDL, bloodLipidVO.getLdl());
            contentMap.put(BloodLipid.TC, bloodLipidVO.getTc());
            contentMap.put(BloodLipid.TG, bloodLipidVO.getTg());
        }
        // 尿检
        if (HealthPackageType.URAN.equals(hpt)) {
            UranVO uranVO = JSONObject.parseObject(content, UranVO.class);
            contentMap.put(Uran.SG, uranVO.getSg());
            contentMap.put(Uran.PH, uranVO.getPh());
            contentMap.put(Uran.KET, uranVO.getKet());
            contentMap.put(Uran.LEU, uranVO.getLeu());
            contentMap.put(Uran.PRO, uranVO.getPro());
            contentMap.put(Uran.NIT, uranVO.getNit());
            contentMap.put(Uran.UBG, uranVO.getUbg());
            contentMap.put(Uran.BLD, uranVO.getBld());
            contentMap.put(Uran.VC, uranVO.getVc());
            contentMap.put(Uran.BIL, uranVO.getBil());
            contentMap.put(Uran.GLU, uranVO.getGlu());
        }
        // 尿酸
        if (HealthPackageType.UA.equals(hpt)) {
            UaVO uaVO = JSONObject.parseObject(content, UaVO.class);
            contentMap.put(Ua.UA, uaVO.getUa());
        }
        returnData.put(VipReport.ID, data.getId());
        returnData.put(VipReport.DETAIL, contentMap);
        returnData.put(VipReport.TYPE, hpt.name());
        returnData.put(VipReport.REPLY, reply);
        returnData.put(VipReport.DOCTOR_SIGN, doctorSign);
        returnData.put(VipReport.STATUS, status);
        returnData.put(VipReport.READ, data.getRead());
        returnData.put(VipReport.CREATE_DATE, data.getCreateDate());
        return returnData;
    }
    
    /**
     *  修改分析报告为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午4:14:18
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "readReport", method = RequestMethod.POST)
    public JSONObject readReport(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject data = appJSON.getData().getFirstJSONObject();
        int id = data.getIntValue(VipReport.ID);
        reportService.readReport(id);
        
        return AppNormalService.success();
    }
    
    /**
     *  删除分析报告
     *  @author yuhang.weng 
     *  @DateTime 2017年10月11日 下午4:13:56
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "deleteReport", method = RequestMethod.POST)
    public JSONObject deleteReport(String json) throws OperationException{
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        JSONObject data = appJSON.getData().getFirstJSONObject();
        int id = data.getIntValue(VipReport.ID);
        reportService.deleteReport(id, userId);
        
        return AppNormalService.success();
    }
    
    /**
     *  获取体检项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月12日 下午4:15:14
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getPhysicalItem", method = RequestMethod.POST)
    public JSONObject getPhysicalItem(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        PhysicalPO data = physicalItemService.listPhysicalItem(1, 1).getData().get(0);
        String content = data.getContent();
        
        int status = 0;
        List<PhysicalItemPO> userItemList = userPhysicalItemService.listPhysicalItem(userId, null, data.getId(), null, 1, 1).getData();
        if (userItemList != null && !userItemList.isEmpty()) {
            status = userItemList.get(0).getStatus();
        }
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(VipPhysicalItem.ID, data.getId());
        returnData.put(VipPhysicalItem.CONTENT, content);
        returnData.put(VipPhysicalItem.STATUS, status);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  申请体检项目
     *  <p>需要验证用户是否已开通会员
     *  @author yuhang.weng 
     *  @DateTime 2017年10月18日 下午2:04:22
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "reservePhysicalItem", method = RequestMethod.POST)
    public JSONObject reservePhysicalItem(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        checkVipStatus(userId);
        
        JSONObject data = appJSON.getData().getFirstJSONObject();
        int physicalItemId = data.getIntValue(VipPhysicalItem.ID);
        int comboId = data.getIntValue("comboId");
        userPhysicalItemService.commitPhysicalItem(userId,comboId, physicalItemId);
        
        return AppNormalService.success();
    }
    
    /**
     *  获取名医类型列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午3:37:51
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listFamousDoctorKind", method = RequestMethod.POST)
    public JSONObject listFamousDoctorKind(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        List<ProfessionKindVO> dataList = famousDoctorService.listProfessionKind(pageIndex, pageSize).getData();
        if (dataList.isEmpty()) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        
        List<Map<String, Object>> returnDataList = enclosureProKindList(dataList);
        
        return AppNormalService.success(returnDataList);
    }
    
    private List<Map<String, Object>> enclosureProKindList(List<ProfessionKindVO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (ProfessionKindVO data : dataList) {
            returnDataList.add(enclosureProKind(data));
        }
        return returnDataList;
    }
    
    private Map<String, Object> enclosureProKind(ProfessionKindVO data) {
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctor.PROFESSION_KIND_NAME, data.getProfessionKindName());
        returnData.put(FamousDoctor.USER_COUNT, data.getUserCount());
        List<Map<String, Object>> doctorList = new ArrayList<>();
        for (FamousDoctorPO d : data.getDoctorList()) {
            Map<String, Object> doctor = new HashMap<>();
            doctor.put(FamousDoctor.ID, d.getId());
            doctor.put(FamousDoctor.PHOTO, d.getPhoto());
            doctor.put(FamousDoctor.NAME, d.getName());
            doctorList.add(doctor);
        }
        returnData.put(FamousDoctor.DOCTORS, doctorList);
        return returnData;
    }
    
    /**
     *  按照类型获取名医列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午3:37:32
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listFamousDoctorWithKind", method = RequestMethod.POST)
    public JSONObject listFamousDoctorWithKind(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        String professionKindLikeName = mm_0.getString(FamousDoctor.PROFESSION_KIND_NAME);
        
        PaginationDTO<FamousDoctorPO> p = famousDoctorService.listFamousDoctor(null, professionKindLikeName, pageIndex, pageSize).getPagination();
        List<FamousDoctorPO> dataList = p.getData();
        if (dataList.isEmpty()) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> doctorDataList = enclosureFamousDoctorList(dataList);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctor.PROFESSION_KIND_NAME, professionKindLikeName);
        returnData.put(FamousDoctor.USER_COUNT, p.getTotalSize());
        returnData.put(FamousDoctor.DOCTORS, doctorDataList);
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  搜索名医
     *  @author yuhang.weng 
     *  @DateTime 2017年10月20日 上午10:55:25
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "searchFamousDoctor", method = RequestMethod.POST)
    public JSONObject searchFamousDoctor(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        String likeValue = mm_0.getString("value");
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        
        if (StringUtils.isBlank(likeValue)) {
            likeValue = null;
        }
        
        List<FamousDoctorPO> doctorList = famousDoctorService.listFamousDoctor(likeValue, likeValue, pageIndex, pageSize).getData();
        if (doctorList.isEmpty()) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        List<Map<String, Object>> returnDataList = enclosureFamousDoctorList(doctorList);
        
        return AppNormalService.success(returnDataList);
    }
    
    private List<Map<String, Object>> enclosureFamousDoctorList(List<FamousDoctorPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (FamousDoctorPO data : dataList) {
            returnDataList.add(enclosureFamousDoctor(data));
        }
        return returnDataList;
    }
    
    private Map<String, Object> enclosureFamousDoctor(FamousDoctorPO data) {
        double price = NumberUtils.changeF2Y(String.valueOf(data.getPrice()));
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctor.ID, data.getId());
        returnData.put(FamousDoctor.NAME, data.getName());
        returnData.put(FamousDoctor.PHOTO, data.getPhoto());
        returnData.put(FamousDoctor.PROFESSIONL_NAME, data.getProfessionalName());
        returnData.put(FamousDoctor.HONORARY, data.getHonorary());
        returnData.put(FamousDoctor.REMARK, data.getDescription());
        returnData.put(FamousDoctor.PRICE, price);
        returnData.put(FamousDoctor.ADDRESS_ABBREVIATED, data.getAbbreviatedAddress());
        returnData.put(FamousDoctor.ADDRESS, data.getAddress());
        returnData.put(FamousDoctor.VISITING_TIME, data.getVisitingTime());
        returnData.put(FamousDoctor.PROFESSION_KIND_NAME, data.getProfessionKindName());
        returnData.put(FamousDoctor.DETAIL, data.getDetail());
        returnData.put(FamousDoctor.EXPERT, data.getExpert());
        return returnData;
    }
    
    /**
     *  获取名医的信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午3:37:17
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "getFamousDoctor", method = RequestMethod.POST)
    public JSONObject getFamousDoctor(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int id = mm_0.getIntValue(FamousDoctor.ID);
        
        FamousDoctorPO doctor = famousDoctorService.getFamousDoctor(id);
        if (doctor == null) {
            throw new OperationException("找不到该名医信息", ErrorCodeEnum.NOT_FOUND);
        }
        
        Map<String, Object> returnData = enclosureFamousDoctor(doctor);
        return AppNormalService.success(returnData);
    }
    
    /**
     *  添加名医预约订单
     *  <p>需要验证用户是否已开通会员
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午3:36:48
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "addFamousDoctorOrder", method = RequestMethod.POST)
    public JSONObject addFamousDoctorOrder(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        
        checkVipStatus(userId);
        
        int famousDoctorId = appJSON.getData().getFirstJSONObject().getIntValue(FamousDoctor.ID);
        
        FamousDoctorOrderPO order = famousDoctorOrderService.addOrder(userId, famousDoctorId, OrderStatus.PAYABLE);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctorOrder.ID, order.getId());
        return AppNormalService.success(returnData);
    }
    
    /**
     *  获取名医预约订单的支付信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午3:37:00
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "getFamousDoctorOrderInfo", method = RequestMethod.POST)
    public JSONObject getFamousDoctorOrderInfo(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int orderId = appJSON.getData().getFirstJSONObject().getIntValue(FamousDoctorOrder.ID);
        String sign = famousDoctorOrderService.getAliPaySign(orderId);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctorOrder.SIGN, sign);
        return AppNormalService.success(returnData);
    }
    
    @RequestMapping(value = "listFamousDoctorOrder", method = RequestMethod.POST)
    public JSONObject listFamousDoctorOrder(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        
        int pageIndex = mm_0.getIntValue(Page.INDEX);
        int pageSize = mm_0.getIntValue(Page.SIZE);
        int status = mm_0.getIntValue("status");
        OrderStatus orderStatus = OrderStatus.findStatus(status);
        
        List<FamousDoctorOrderPO> dataList = famousDoctorOrderService.listOrder(pageIndex, pageSize, userId, null, orderStatus).getData();
        if (dataList.size() == 0) {
            return AppNormalService.success(NormalMessage.NO_DATA);
        }
        
        List<Map<String, Object>> returnDataList = enclosureFamousDoctorOrderList(dataList);
        return AppNormalService.success(returnDataList);
    }
    
    private List<Map<String, Object>> enclosureFamousDoctorOrderList(List<FamousDoctorOrderPO> dataList) {
        List<Map<String, Object>> returnDataList = new ArrayList<>();
        for (FamousDoctorOrderPO data : dataList) {
            returnDataList.add(enclosureFamousDoctorOrder(data));
        }
        return returnDataList;
    }
    
    private Map<String, Object> enclosureFamousDoctorOrder(FamousDoctorOrderPO data) {
        double price = NumberUtils.changeF2Y(data.getPrice().toString());
        
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(FamousDoctorOrder.ID, data.getId());
        returnData.put(FamousDoctorOrder.SUBJECT, data.getSubject());
        returnData.put(FamousDoctorOrder.BODY, data.getBody());
        returnData.put(FamousDoctorOrder.PRICE, price);
        returnData.put(FamousDoctorOrder.STATUS, data.getStatus());
        return returnData;
    }
    
    /**
     *  获取用户的vip项目
     *  @author yuhang.weng 
     *  @DateTime 2017年10月25日 上午10:17:42
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getUserVipServe", method = RequestMethod.POST)
    public JSONObject getUserVipServe(String json) {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();
        List<VipUserPO> vip = vipUserService.getUserVip(userId);
        List<Object> list = new ArrayList<>();
        List<Map<String,Object>> vipItemList = new ArrayList<>();
       if (vip != null) {
            for (VipUserPO v: vip) {
                int comboId = v.getVipComboId();
                VipComboVO combo = comboService.getVipCombo(comboId);
                for (VipComboItemPO item : combo.getItemList()) {
                    Map<String, Object> vipItem = new HashMap<>();
                    vipItem.put("name", item.getName());
                    vipItem.put("image", item.getIcon());
                    vipItem.put("comboItemDetail", item.getItemDetail());
                    vipItem.put("comboId", comboId);
                    vipItem.put("type", item.getType());
                    // 获取套餐项Id
                    int comboItemId = item.getId();
                    if (item.getName().equals("健康数据分析")) {
                        // 体检报告批注
                        int unReadPAnalysis = physicalAnalysisService.countUnReadAnalysis(userId);
                        // 测量数据批注
                        int unReadMAnalysis = measureAnalysisService.countUnReadAnalysis(userId);

                        vipItem.put("unReadMsgCount", unReadPAnalysis + unReadMAnalysis);
                    }

                    // 根据用户id或VIP套餐id获取套餐次数
                    int comboNumber = vipUserService.getComboNumberById(userId,comboId, comboItemId);
                    if(item.getName().equals("体检套餐")) {
                        int status = 0;
                        status = comboNumber>0?1:0;
                        vipItem.put("physicalItemUseStatus", status);
                    } else {
                    	// 添加套餐次数
                    	vipItem.put("comboNumber", comboNumber);
                    }
                    // 添加套餐id
                    vipItem.put("comboItemId", comboItemId);
                    vipItem.put("comboName", combo.getName());
                    vipItemList.add(vipItem);
                }

                list.add(vipItemList);
            }
        }
        return AppNormalService.success(list);
    }
    
    /**
     *  获取慢病资讯服务师列表
     *  <p>需要验证用户是否已开通会员
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 下午2:05:57
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "listConsultServeUser", method = RequestMethod.POST)
    public JSONObject listConsultServeUser(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        int userId = appJSON.getData().getUserId();

        checkVipStatus(userId);
        
        List<ServeUserVO> dataList = consultService.listConsultServeUser();
        List<Map<String, Object>> returnDataList = enclosureConsultList(dataList);
        return AppNormalService.success(returnDataList);
    }
    
    /**
     *  健康咨询模型封装
     *  @author yuhang.weng 
     *  @DateTime 2017年7月10日 上午10:06:59
     *
     *  @param dataList
     *  @return
     */
    private List<Map<String, Object>> enclosureConsultList(List<ServeUserVO> dataList) {
        List<Map<String, Object>> consultList = new ArrayList<>();
        for (ServeUserVO rc : dataList) {
            consultList.add(enclosureConsult(rc));
        }
        return consultList;
    }
    
    /**
     *  健康咨询模型封装
     *  @author yuhang.weng 
     *  @DateTime 2017年7月10日 下午6:31:43
     *
     *  @param data
     *  @return
     */
    private Map<String, Object> enclosureConsult(ServeUserVO data) {
        Map<String, Object> consult = new HashMap<>();
        
        consult.put(OrgUser.NAME, data.getRealName());
        consult.put(OrgUser.PRO_NAME, data.getProfessionalName());
        consult.put(OrgUser.EXPERT_FIELD, data.getExpertise());
        consult.put(OrgUser.PHOTO, data.getPhoto());
        consult.put(ProjectServe.NAME,data.getProjectName());
        consult.put(Org.NAME,data.getOrgName());
        consult.put(Org.PRICE, data.getPrice());
        consult.put(Order.BUYERCOUNT,data.getBuyerCount());        
        consult.put(ProjectServe.TYPE, Toolkits.projectType(data.getProjectName()));
        consult.put(Order.SCORETOTAL, data.getScoreTotal());
        consult.put(Order.COMMENTCOUNT,data.getCommentCount());
        consult.put(ProjectServe.CODE, data.getProjectCode());
//        consult.put(Order.CHARGE_MODE,data.getChargeMode());
        consult.put(OrgUser.CODE, data.getUserCode());
        consult.put(OrgUser.COMMENT, data.getComment());
        
        consult.put(HuanXin.USERNAME, data.getUserCode());
        return consult;
    }
    
    /**
     * 
     *  根据套餐id获取服务师
     *  @author liaoguo
     *  @DateTime 2018年10月16日 下午4:13:01
     *
     *  @param json
     *  @return
     *  @throws OperationException
     */
    @RequestMapping(value = "listComboOrgUserByComboId", method = RequestMethod.POST)
    public JSONObject listComboOrgUserByComboId(String json) throws OperationException {
        AppJSON appJSON = AppNormalService.parseAppJSON(json);
        
        JSONObject mm_0 = appJSON.getData().getFirstJSONObject();
        int userId = appJSON.getData().getUserId();
        int comboId = mm_0.getIntValue("comboId");
        int comboItemId = mm_0.getIntValue("comboItemId");

        checkVipStatus(userId);
        
        List<OrgUserVO> dataList = consultService.listComboOrgUserByComboId(comboId,comboItemId);
        //List<Map<String, Object>> returnDataList = enclosureConsultList(dataList);
        return AppNormalService.success(dataList);
    }
    
    
    /**
     *  获取客服信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月27日 下午2:28:19
     *
     *  @param json
     *  @return
     */
    @RequestMapping(value = "getCustomerUser", method = RequestMethod.POST)
    public JSONObject getCustomerUser(String json) {
        CustomerUserPO user = customerUserService.getUser(1);
        Map<String, Object> returnData = new HashMap<>();
        returnData.put(HuanXin.USERNAME, user.getUserCode());
        returnData.put(Customer.NAME, user.getName());
        returnData.put(Customer.PHOTO, user.getPhoto());
        
        return AppNormalService.success(returnData);
    }
    
    /**
     *  检查用户的会员情况
     *  <p>如果用户没有购买vip服务，或者服务已经过期，就会抛出一个错误码为401的权限异常
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 下午5:47:51
     *
     *  @param userId 用户id
     *  @throws OperationException
     */
    private void checkVipStatus(int userId) throws OperationException {
        VipUserPO vip = vipUserService.getUserVip1(userId);
        if (vip == null) {
            throw new OperationException("用户尚未购买vip服务", ErrorCodeEnum.AUTHORIZED);
        }
    }
}
