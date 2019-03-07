package com.lifeshs.app.api.member.release;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.famousDoctor.FamousDoctorDao;
import com.lifeshs.pojo.app.member.AppJSON;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.service.terminal.app.ITestLiyueService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service1.agency.AgencyService;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.data.HobbyService;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageService;
import com.lifeshs.service1.famousDoctor.FamousDoctorService;
import com.lifeshs.service1.member.ReportAnalysisService;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.record.MedicalService;
import com.lifeshs.service1.record.PhysicalService;
import com.lifeshs.task.MeasureReminder;
import com.lifeshs.task.Project;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.vo.famousDoctor.ProfessionKindVO;

/**
 * @author YueLi
 * @date 2/22/17.
 */
@RestController(value = "testlyapp")
@RequestMapping("app/testly")
public class TestController {

    @Autowired
    private ITestLiyueService testLiyueService;
    
    @Autowired
    private MeasureReminder t;
    
    @Autowired
    private UMengPushService pushService;
    
    @Resource
    private OrderService orderService;
    
    @Autowired
    private Project project;
    
    @Autowired
    private HuanXinService huanxinService;
    
    @Autowired
    private ICacheService cacheService;
    
    @Resource(name = "hobbyService")
    private HobbyService hobbyService;
    
    @Resource(name = "userHobbyService")
    private com.lifeshs.service1.member.HobbyService memberHobbyService;
    
    @Resource(name = "appCacheService")
    private com.lifeshs.service1.app.cache.CacheService cService;

    @Resource(name = "physicalService")
    private PhysicalService physicalService;

    @Autowired
    private UserService userService;
    
    public void setTestLiyueService(ITestLiyueService testLiyueService) {
        this.testLiyueService = testLiyueService;
    }

    @RequestMapping(value = "getServeList", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getServeList(@RequestParam String json) {
        
        JSONObject root = JSONObject.parseObject(json);
        AppJSON appJSON = JSONObject.toJavaObject(root, AppJSON.class);
        int userId = appJSON.getData().getUserId();
//        CallBackDTO c;
//        c = pushService.measureRemindPush(true, userId, "4.5", "dd07ff25995d057ec2c628a0e64146f1645256b9b9445354ec7653d56c660267", "ticker", "title", "text", "description",
//                UserType.member.getValue(),MessageType.SYSTEM.value());
      //pushService.measureRemindPush(true, userId, "dd07ff25995d057ec2c628a0e64146f1645256b9b9445354ec7653d56c660267", "title", "text");
        
        return testLiyueService.testLog(json);
    }
    
    @RequestMapping(value = "script", method = RequestMethod.POST)
    public JSONObject json(@RequestParam String json) {
        System.out.println(json);
        JSONObject root = new JSONObject();
        root.put("json", json);
        return root;
    }
    
    @Resource(name = "medicalService")
    private MedicalService medicalService;
    
    @Resource(name = "orgAgencyService")
    private AgencyService agencyService;
    
    @Resource(name = "reportAnalysisService")
    ReportAnalysisService rService;
    
    @Resource(name = "dataPhysicalServiceImpl")
    com.lifeshs.service1.data.PhysicalItemService pService;
    
    @Resource(name = "famousDoctorServiceImpl")
    private FamousDoctorService fService;
    
    @Resource(name = "famousDoctorDao")
    private FamousDoctorDao fDao;
    
    @Resource(name = "electronicCouponsPackageService")
    private ElectronicCouponsPackageService eService;
    
    @RequestMapping(value = "push", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody JSONObject push(@RequestParam String json) throws OperationException {
        
        JSONObject root = new JSONObject();
//        CallBackDTO callBack = pushService.refundMsgPush(true, "10.0.1", "e6c3d69f40e21cca88f47a849802e32a420d4187fe2bc7c91516465dbe5489f2", null);
//        orderService.finishOrder("20170905174430118196", "2017090521001004010202594178", "187****6442", "tzdianqi@126.com", 5.00, 1, "app", null);
//        project.checkUserConsultServe();
//        project.checkLessonEndDate();
        
//        JSONObject get = JSONObject.parseObject(json);
//        String modifyDate = get.getString("modifyDate");
//        Date mDate = DateTimeUtilT.dateTime(modifyDate);
        
        /*Paging<PhysicalVO> data = physicalService.listPhysical(280, 2, 1);
        PaginationDTO<PhysicalVO> p = data.getPagination();
        root.put("physical", p.getData());
        root.put("extra", p.getTotalPage());*/
        
//        PhysicalVO physical = new PhysicalVO();
//        physical.setUserId(280);
//        physical.setPhysicalsOrg("体检机构");
//        physical.setPhysicalsDate(new Date());
//        physical.setDescription("描述文字");
//        
//        List<PhysicalImgPO> imgList = new ArrayList<>();
//        PhysicalImgPO img = new PhysicalImgPO();
//        img.setImg("img12312313131");
//        imgList.add(img);
//        imgList.add(img);
//        physical.setImgList(imgList);
        
//        physicalService.addPhysical(physical);
        
//        cacheService.saveKeyValue(CacheType.SEND_HEALTHWARNING_SMS, "abcde", "哈哈哈");
        
//        List<PhysicalImgPO> imgList = new ArrayList<>();
//        PhysicalImgPO img0 = new PhysicalImgPO();
//        img0.setImg("18");
//        img0.setId(18);
//        imgList.add(img0);
//        
//        PhysicalImgPO img1 = new PhysicalImgPO();
//        img1.setImg(null);
//        img1.setId(19);
//        imgList.add(img1);
        
//        img1 = new PhysicalImgPO();
//        img1.setImg("20");
//        img1.setId(20);
//        imgList.add(img1);
        
        
//        PhysicalVO physical = new PhysicalVO();
//        physical.setId(523);
//        physical.setUserId(280);
//        physical.setPhysicalsDate(new Date());
//        physical.setPhysicalsOrg("ORG。。。");
//        physical.setImgList(imgList);
//        
//        physicalService.updatePhysical(physical);
//        
//        root.put("physical", physicalService.getPhysical(523, 280));
        
        
        /*cacheService.saveKeyValue(CacheType.USER_TOKEN_CACHE, "001", "abc啊");
        Object value = cacheService.getCacheValue(CacheType.USER_TOKEN_CACHE, "001");
        
        root.put("value", value);*/
        
        /*Ehcache e = cacheService.getCache(CacheType.USER_MOBILE_MODIFY.getName());
        System.out.println(e.getKeys());;
        
        Object value = cacheService.getCacheValue(CacheType.USER_MOBILE_MODIFY, "15511111111");
        root.put("15511111111", value);*/
        
//        cacheService.saveKeyValue(CacheType.USER_TOKEN_CACHE, "001", "hahahah....");
        
//        List<Integer> idList = new ArrayList<>();
//        idList.add(3);
//        idList.add(2);
//        idList.add(4);
//        UserHobbyPO userHobby = new UserHobbyPO();
//        userHobby.setId(6);
//        userHobby.setHobbyId(1);
//        userHobby.setUserId(2);
        
//        try {
//            memberHobbyService.addUserHobby(1267, idList);
//        } catch (OperationException e) {
//            root.put("error", e.getMessage());
//            return root;
//        }
        
//        root.put("userHobby", memberHobbyService.listUserHobby(1));
        
//        List<HotHobbyVO> d = hobbyService.listHotHobby(666);
//        root.put("hobbyList", d);
//        CacheVO data = cService.getDataCache(mDate);
//        root.put("data", data);
        
//        List<MedicalBasicVO> medical = medicalDao.findBasicMedicalByUserIdList(1317, 1, 5);
//        root.put("medicalList", medical);
        
//        root.put("list", agencyService.getBranch(1));
        
//        rService.commitReport(1317, RequestorTypeEnum.MEMBER, HealthPackageType.BloodLipid, "{'a':123}");
        
//        rService.replyReport(6, 1, "测试内容", "医生签名");
        
//        BloodLipidVO b = new BloodLipidVO();
//        b.setHdl(1F);
//        b.setLdl(2F);
//        b.setTc(3F);
//        b.setTg(4F);
//        
//        UranVO r = new UranVO();
//        r.setSg(1F);
//        r.setPh(2F);
//        r.setKet(3F);
//        r.setLeu(4F);
//        r.setPro(5F);
//        r.setNit(6F);
//        r.setUbg(7F);
//        r.setBld(8F);
//        r.setVc(9F);
//        r.setBil(10F);
//        r.setGlu(11F);
//        
//        UaVO u = new UaVO();
//        u.setUa(10F);
//        JSONObject bj = (JSONObject) JSONObject.toJSON(u);
//        
//        System.out.println(bj.toString());
        
//        List<PhysicalPO> dList = pService.listPhysicalItem(1, 1).getData();
//        root.put("d", dList);
        
        PaginationDTO<ProfessionKindVO> p = fService.listProfessionKind(1, 2).getPagination();
        root.put("p", p);
        return root;
    }

    public static void main(String[] args){
        String openAttach = "orderid:$1,orgid:$2";
        openAttach = openAttach.replace("$2", "22");
        System.out.println(openAttach);
    }
}
