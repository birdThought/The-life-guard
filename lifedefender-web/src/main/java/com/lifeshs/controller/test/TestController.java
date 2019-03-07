package com.lifeshs.controller.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.model.AjaxJson;

import com.lifeshs.common.model.DataResult;
import com.lifeshs.controller.common.BaseController;

import com.lifeshs.dao.common.IBaseDao;
import com.lifeshs.dao.data.IAreaDao;
import com.lifeshs.dao.device.IDeviceDao;

import com.lifeshs.entity.org.user.TOrgUser;

import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.huanxin.GroupDTO;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.commond.TUserInfoDto;
import com.lifeshs.pojo.order.ServiceOrderDTO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.page.PaginationDTO;

import com.lifeshs.pojo.paper.PaperDTO;
import com.lifeshs.pojo.paper.PaperOptionDTO;
import com.lifeshs.pojo.paper.PaperPhysicalStandardDTO;
import com.lifeshs.service.alipay.util.WechatMessageUtil;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.data.IHealthReasonService;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.service.org.service.IOrgServiceManage;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.paper.IPaperService;
import com.lifeshs.service.terminal.impl.TerminalAdaptee;
import com.lifeshs.service1.member.impl.MemberService;
import com.lifeshs.support.plantform.security.sessionmgr.ISessionManageService;

import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.thirdservice.WeiXinGZPTApi;
import com.lifeshs.utils.ParserParaUtil;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  版权归
 *  TODO 测试用的，与实际业务无关
 *  @author duosheng.mo
 *  @DateTime 2016年4月20日 下午5:21:17
 */
@RequestMapping(value =  {
    "/testController", "test"}
)
@Controller
public class TestController extends BaseController {
    private static final Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    public IMemberService memberService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ISessionManageService sessionManageService;
    @Autowired
    IAreaDao dao;
    @Autowired
    IOrgUserService orgUserService;
    @Autowired
    IBaseDao commonTrans;
    @Autowired
    HuanXinService huanXinService;
    @Autowired
    private IServiceOrgService serviceOrgService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    IOrgServiceManage orgServiceManage;
    @Autowired
    private ILessonGroup lesson;
    @Autowired
    private HuanXinService huanxinService;
    @Autowired
    IPaperService question;
    @Autowired
    private InformationService informationService;
    
    @Autowired
    private WeiXinGZPTApi weixinApi;

    /*@RequestMapping(params = "subHealth")
    public ModelAndView subHealth() {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/paper/subHealth");
        List<PaperDTO> questionnaireDTOs = question.listPaper("亚健康");
        for (PaperDTO questionnaireDTO : questionnaireDTOs) {
    //                        System.out.println(questionnaireDTO.toString());
                }
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("亚健康");
        for (PaperOptionDTO questionnaireOptionDTO : optionDTOs) {
    //                        System.        out.println(questionnaireOptionDTO.toString());
                }
        modelAndView.addObject("questionnaire", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("questionnaireOption", JSON.toJSON(optionDTOs));
        return modelAndView;
    }
    
    @RequestMapping(params = "addSubHealthResult")
    @ResponseBody
    public AjaxJson addSubHealthResult(
                    @RequestParam(value = "paperTypeName") String paperTypeName,
                    @RequestParam(value = "result") String result ,HttpServletRequest request) {
                AjaxJson ajaxJson = new AjaxJson();
                PaperResultDTO paperResultDTO = new PaperResultDTO();
                String newIp = null;
                Integer userId = null;
                String ip = request.getHeader("X-Forwarded-For");
                if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                    //多次反向代理后会有多个ip值，第一个ip才是真实ip
                    int index = ip.indexOf(",");
                    if(index != -1){
                            newIp = ip.substring(0,index);
                    }else{
                            newIp = ip;
                    }
                }
                ip = request.getHeader("X-Real-IP");
                if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                        newIp = ip;
                }
                newIp = request.getRemoteAddr();
    
                try {
                        userId = getLoginUser().getId();
                } catch (Exception e) {
                }
    
                paperResultDTO.setIp(newIp);
                paperResultDTO.setUserId(userId);
                paperResultDTO.setResult(result);
                paperResultDTO.setPaperTypeName(paperTypeName);
                boolean bool = question.addPaperResult(paperResultDTO);
                if (bool) {
                        return ajaxJson;
                }
                ajaxJson.setSuccess(false);
                return ajaxJson;
    
        }
    
    @RequestMapping(params = "subHealthAnalyze")
    public ModelAndView subHealthAnalyze(@RequestParam(value = "score") Integer score) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/paper/subHealthAnalyze");
        PaperSubHealthStandardDTO subHealthStandardDTO = question.getPaperSubHealthStandard(score);
        modelAndView.addObject("subHealthStandard", JSON.toJSON(subHealthStandardDTO));
        modelAndView.addObject("score", score);
    //        System.out.println("sub:" + subHealthStandardDTO.toString());
        return modelAndView;
    }
    
    @RequestMapping(params = "physicalPaper")
    public ModelAndView physicalPaper() {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/paper/physicalPaper");
        List<PaperDTO> questionnaireDTOs = question.listPaper("体质");
        for (PaperDTO questionnaireDTO : questionnaireDTOs) {
                        System.out.println(questionnaireDTO.toString());
                }
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("体质");
        for (PaperOptionDTO questionnaireOptionDTO : optionDTOs) {
    //                        System.        out.println(questionnaireOptionDTO.toString());
                }
        modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));
        return modelAndView;
    }
    
    @RequestMapping(params = "physicalAnalyze")
    public ModelAndView physicalAnalyze(@RequestParam(value = "result") String physicalName,
                    @RequestParam(value = "scoreArr") List<Integer> scoreArr) {
        ModelAndView modelAndView = new ModelAndView("com/lifeshs/member/paper/physicalAnalyze");
        try {
                        physicalName = new String (physicalName.getBytes("iso8859-1"),"utf-8");
                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                }
        PaperPhysicalStandardDTO physicalStandardDTO = question.getPaperPhysicalStandard(physicalName);
        modelAndView.addObject("physicalStandard", JSON.toJSON(physicalStandardDTO));
        modelAndView.addObject("scoreArr", scoreArr);
        System.out.println("arr:" + scoreArr);
        System.out.println("sub:" + physicalStandardDTO.toString());
        return modelAndView;
    }*/
    @Autowired
    private IHealthReasonService reasonService;
    @Autowired
    private IDeviceDao deviceDao;
    @Autowired
    private TerminalAdaptee terminalA;
    @Autowired
    IDataAreaService areaService;

    @RequestMapping(value = "test")
    @ResponseBody
    public AjaxJson test(HttpServletRequest request,
        HttpServletResponse response) {
        AjaxJson resObj = new AjaxJson();
        Map<String, Object> param = ParserParaUtil.getParams(request);

        //      List<Map<String,Object>> list = memberService.selectMemberByMap(param);

        //      List<TSysUser> listUser = commonService.loadAll(TSysUser.class);
        //      
        //      System.out.println(listUser.size());
        long startTime = System.currentTimeMillis();

        //      int num = commonService.batchSave(listObj);
        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");

        //      System.out.println("num="+num);
        return resObj;
    }

    @RequestMapping(value = "/json/{json}", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject returnJson(@PathVariable
    String json, HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + ":" + value);
        }

        JSONObject jsonObject = JSONObject.parseObject(json);

        return jsonObject;
    }

    @RequestMapping(params = "testMian")
    public String testMian(HttpServletRequest request) {
        // serviceDoctor
        // serviceOrganize
        // ourProduce
        // healthConsult
        return "com/lifeshs/member/userService";
    }

    //    @RequestMapping(params = "mian")@ResponseBody
    //    public AjaxJson mian(HttpServletRequest request){
    //        AjaxJson res = new AjaxJson();
    //        String content = request.getParameter("content");
    //        String imei = request.getParameter("imei");
    //        String TerminalType = request.getParameter("TerminalType");
    //        IoSession sessions = ClientManager.getInstance().getMianSession(TerminalType,imei);
    //        if(sessions == null){
    //            res.setMsg("终端不存在");
    //        }else{
    //            sessions.write(content);
    //            res.setMsg("发送成功！");
    //        }
    //        return res;
    //    }
    @RequestMapping(params = "qy")
    public void jumpToTest(@RequestParam(required = false)
    String target, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        if (target != null) {
            if (target.equals("error")) {
                String html = "<body bgcolor=\"#000\"><div style=\"width:1200px;color:white;padding-top:200px;text-align:center;margin:0 auto;text-shadow:1px 5px 25px #fff;font-size:40px;font-weight:bold\"><h1>该页面还未设计！</h1><a href=\"javascript:history.go(-1)\" style=\"color:#fff\">点击返回上一页</a></div></body>";
                response.getOutputStream().write(html.getBytes());

                return;
            }

            request.getRequestDispatcher("/view/com/QYPart/" + target + ".jsp")
                   .forward(request, response);

            return;
        }

        request.getRequestDispatcher("/view/com/QYPart/Home.jsp")
               .forward(request, response);
    }

    @RequestMapping(params = "lll")
    public void test(@RequestParam(required = false)
    String target, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<Map<String, String>> province = dao.findAllProvince();
        String regex = "^44[0-9]{2}[0-9]{2}";
        List<Map<String, String>> city = dao.findCity(regex);
        String regex2 = "^4401[0-9]{2}";

        //List<String> district=dao.findDistrict(regex2);
        //System.out.println(district);
    }

    @RequestMapping(params = "t")
    public void testPage() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orgId", 19);
        params.put("begin", 1);

        List<TOrgUser> list = orgUserService.getEmployList(params);
        System.out.println(list);
    }

    @RequestMapping(params = "ceshi")
    public void ceshi(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("orgControl.do?orgBasePage")
               .forward(request, response);
        ;
    }

    @RequestMapping(params = "testHuanxin")
    public ModelAndView testHuanxin() {
        return new ModelAndView("com/lifeshs/member/messageCenter");
    }

    @RequestMapping(params = "pay_d")
    public ModelAndView payPage(@RequestParam
    String cash, @RequestParam
    String shop, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("test/payPage");

        if (shop.length() < 256) {
            double money = Double.parseDouble(cash);

            //String html= AlipayService.createOrdeFlow(shop,money);
            //modelAndView.addObject("html",html);
        }

        return modelAndView;
    }

    @RequestMapping(params = "rhx")
    public void regisHX(HttpServletResponse response) throws IOException {
        /*int page=3;
        int start=(page-1)*50;
        List<Map<String,Object>> list=commonTrans.registHx(1,start);
        List<HuanxinUserVO> voList=new LinkedList<>();
        for(Map<String,Object> map:list){
            HuanxinUserVO vo=new HuanxinUserVO();
            vo.setPassword("123456789");
            vo.setUsername((String)map.get("userCode"));
            voList.add(vo);
        }
        System.out.println(voList);*/

        //response.getWriter().write(huanXinService.batchDeleteUsers(300));
    }

    /** testControl.do?pay  */
    @RequestMapping(params = "pay")
    public ModelAndView gotoPay() {
        return new ModelAndView("com/lifeshs/index/userService");
    }

    @RequestMapping(params = "myIndex", method = RequestMethod.GET)
    public String myIndex(Model model) {
        //      model.addAttribute("orgs", orgs);
        return "officialwebsite/index";
    }

    @RequestMapping(params = "rhx2")
    public @ResponseBody
    JSONObject regisHX2() throws InterruptedException {
        //      List<String> userCodes = jdbcTemplate.queryForList("SELECT userCode FROM t_user", String.class);
        //      
        //      List<HuanxinUserVO> users = new ArrayList<>();
        //      for (int i = 599; i < userCodes.size(); i++) {
        //          HuanxinUserVO user = new HuanxinUserVO();
        //          user.setUsername(userCodes.get(i));
        //          user.setPassword("123456789");
        //          user.setNickname("用户名"+i);
        //          users.add(user);
        //          
        //          if (users.size() == 30) {
        //              HuanXinService.registUsers(users);
        //              users.clear();
        //              logger.info("休息1秒###已注册用户数量:"+(i+1));
        //              logger.info("总数量："+600+i);
        //              Thread.sleep(1000);
        //          }
        //      }
        //      
        //      logger.info("结束");
        JSONObject root = new JSONObject();
        root.put("status", 200);

        return root;
    }

    private void updateMobile() {
        jdbcTemplate.update("");
    }

    @RequestMapping(params = "get2json")
    public @ResponseBody
    JSONObject get2json(String name, String pwd) {
        JSONObject root = new JSONObject();
        root.put("name", name);
        root.put("pwd", pwd);

        return root;
    }

    @RequestMapping(params = "zixun")
    public ModelAndView zixun() {
        ModelAndView modelAndView = new ModelAndView(
                "com/lifeshs/member/information");

        return modelAndView;
    }

    @RequestMapping(params = "test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("test/test");

        /*Map<String, Object> condition = new HashMap<>();
        condition.put("userId", getLoginUser().getId());
        int count = commonTrans.getCount(TReport.class, condition);
        modelAndView.addObject("pageCount", (int) (count % 10 == 0 ? count / 10 : Math.floor(count / 10) + 1));
        modelAndView.addObject("data",
                JSONObject.toJSON(commonTrans.findEntityByPageDesc(TReport.class, condition, 1, 10, "createDate")));*/

        //        orgServiceManage.listService(150, null, null, "all");
        return modelAndView;
    }

    @RequestMapping(params = "updateMeasureData")
    public @ResponseBody
    JSONObject updateMeasureData() {
        long s = System.currentTimeMillis();

        //        List<TMeasureGlucometer> bs = terminal.getCommonTrans().loadAll(TMeasureGlucometer.class);
        JSONObject root = new JSONObject();

        //        JSONArray datas = new JSONArray(bs.size());
        //        for (TMeasureGlucometer b : bs) {
        //            terminal.getGlucometer().getPerfectData(b);
        //            datas.add(b);
        //            terminal.getCommonTrans().saveOrUpdate(b);
        //        }

        //        root.put("xxxx", datas);
        throw new NullPointerException("空指针");

        //        System.out.println("耗时:" + (System.currentTimeMillis() - s) + "毫秒");
        //        return root;
    }

    @RequestMapping(params = "lookMessageTest")
    public ModelAndView lookMessage(
        @RequestParam(required = false)
    Integer serverId, @RequestParam(required = false)
    Integer orgServeId) {
        ModelAndView modelAndView = new ModelAndView(
                "com/lifeshs/member/messageCenter");
        LoginUser user = getLoginUser();
        MemberSharingData memberSharingData = getMemberSharingData();

        if ((serverId != null) && (orgServeId != null)) {
            Map<String, Object> detail = orderService.getUserServeDetail(user.getId(),
                    orgServeId);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> servers = (List<Map<String, Object>>) detail.get(
                    "serveUsers");
            modelAndView.addObject("targetServer", serverId);
            modelAndView.addObject("servers", JSONArray.fromObject(servers));

            Integer chargeMode = (Integer) detail.get("chargeMode");
            String remain = "";

            if (chargeMode == 1) { // 按次
                remain = detail.get("timesRemaining") + "次";
            } else {
                remain = detail.get("daysRemaining") + "天";
            }

            modelAndView.addObject("remain", remain);
        }

        modelAndView.addObject("orgServeId", orgServeId);
        modelAndView.addObject("headUrl",
            Normal.PHOTO_PREFIX + memberSharingData.getPhotoPath());
        modelAndView.addObject("nick", memberSharingData.getRealName());
        modelAndView.addObject("hxCode", user.getUserCode());

        return modelAndView;
    }

    public MemberSharingData getMemberSharingData() {
        LoginUser user = getLoginUser();
        int userId = user.getId();

        return sessionManageService.getCacheMemberSharingData(userId);
    }

    @RequestMapping(params = "lessGroupTest", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject lessGroupTest() {
        JSONObject root = new JSONObject();

        //        LessonDTO lessonDTO = new LessonDTO();
        //        lessonDTO.setDefaultGroup(false);
        //        lessonDTO.setName("测试群组");
        //        lessonDTO.setOrgServeId(154);
        //        
        //        List<CourseTimeDTO> courseTimeDTOs = new ArrayList<>();
        //        CourseTimeDTO courseTimeDTO = new CourseTimeDTO();
        //        courseTimeDTO.setStartTime("18:01");
        //        courseTimeDTO.setWeeks("1000011");
        //        courseTimeDTOs.add(courseTimeDTO);
        //        lessonDTO.setCourseTime(courseTimeDTOs);
        GroupDTO groupDTO = huanxinService.getGroup("9407033311234");
        ////        
        root = (JSONObject) JSONObject.toJSON(groupDTO);

        //        List<String> receiverHuanxinId = new ArrayList<>();
        //        receiverHuanxinId.add("100314");
        //        boolean success = huanxinService.sendGroupTransparentMsg(HuanxinCmdActionType.GAG, receiverHuanxinId);
        //        root.put("success", success);

        //        List<Integer> orgUserIds = new ArrayList<>();
        //        orgUserIds.add(940);
        //        
        //        boolean success = lesson.removeOrgUser(254, orgUserIds);
        //        root.put("success", success);

        //        huanxinService.removeGroupUser("9386104782852", "100119");
        //        huanxinService.joinGroup("9386104782852", "100119");
        lesson.listServeUserLesson(null, 887);

        return root;
    }

    @RequestMapping(params = "reason", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject getReason() {
        JSONObject root = new JSONObject();

        /**
         * TODO Request processing failed;
         * nested exception is java.lang.IllegalStateException: getOutputStream() has already been called for this response
         * com.lifeshs.commonTrans.exception.SystemExceptionHandler.resolveException(SystemExceptionHandler.java:57)
         */

        //List<EcgDTO> ecgDTOs = deviceDao.listEcgWithPageSplit(1, "APP", 0, 10);

        //        UserDTO userDTO = memberService.getUser("fa1f651561adf13");
        //        root.put("a", userDTO);

        //        terminalA.getEcg().save(null);

        /*List<TUser> userList = terminalA.getCommonTrans().loadAll(TUser.class);
        List<Integer> idList = new ArrayList<>();
        for (TUser user : userList) {
            idList.add(user.getId());
        }
        root.put("userList", idList);*/
        OrgUserDTO user = orgUserService.getOrgUser(744);
        root.put("groups", user.getOrg());

        return root;
    }

    /*****************应用平台改版测试start*******************/
    /**
     * @Description: 用户主页页面
     * @author: wenxian.cai
     * @create: 2017/4/24 13:55
     */
    @RequestMapping(params = "usercenter")
    public ModelAndView usercenter() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/member/usercenter/user-index");

        return modelAndView;
    }

    /**
     * @Description: 用户基本资料页面
     * @author: wenxian.cai
     * @create: 2017/4/24 13:56
     */
    @RequestMapping(params = "userinfo")
    public ModelAndView userinfo() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/member/usercenter/user-info");
        int userId = getLoginUser().getId();
        UserDTO tUser = memberService.getUser(userId);
        //        System.out.println("user:" + tUser.toString());
        modelAndView.addObject("user", JSON.toJSON(tUser));

        List<Map<String, String>> province = areaService.findAllProvince();
        List<Map<String, String>> city = null;
        List<Map<String, String>> district = null;

        if (tUser.getProvince() == null) {
            city = areaService.findCity("^11[0-9]{2}[0]{2}"); // 默认为北京
            district = areaService.findDistrict("^11[0-9]{2}[0-9][1-9]");
        } else {
            String provinceCode = areaService.getCode(tUser.getProvince());
            String cityCode = areaService.getCode(tUser.getCity());
            String cityRegex = "^" + provinceCode.substring(0, 2) +
                "[0-9][0-9][0]{2}";
            String disRegex = "^" + cityCode.substring(0, 4) + "[0-9][1-9]";
            city = areaService.findCity(cityRegex);
            district = areaService.findDistrict(disRegex);

            if (city.size() > 1) {
                city.remove(0);
            }
        }

        modelAndView.addObject("province", JSON.toJSON(province));
        modelAndView.addObject("city", JSON.toJSON(city));
        modelAndView.addObject("district", JSON.toJSON(district));

        return modelAndView;
    }

    /**
     * @Description: 验证邮箱页面
     * @author: wenxian.cai
     * @create: 2017/4/24 13:56
     */
    @RequestMapping(params = "validateemail")
    public ModelAndView validateemail() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/member/usercenter/validate-email");
        int userId = getLoginUser().getId();
        TUserInfoDto userInfoDto = memberService.getUserInfo(userId);
        modelAndView.addObject("user", JSON.toJSON(userInfoDto));

        return modelAndView;
    }

    /**
     * @Description: 验证手机号码页面
     * @author: wenxian.cai
     * @create: 2017/4/24 13:56
     */
    @RequestMapping(params = "validatemobile")
    public ModelAndView validatemobile() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/member/usercenter/validate-mobile");
        int userId = getLoginUser().getId();
        TUserInfoDto userInfoDto = memberService.getUserInfo(userId);
        modelAndView.addObject("user", JSON.toJSON(userInfoDto));

        return modelAndView;
    }

    /**
     * @Description: 修改密码页面
     * @author: wenxian.cai
     * @create: 2017/4/24 13:57
     */
    @RequestMapping(params = "modifypassword")
    public ModelAndView modifypassword() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/member/usercenter/modify-password");
        int userId = getLoginUser().getId();
        TUserInfoDto userInfoDto = memberService.getUserInfo(userId);
        modelAndView.addObject("user", JSON.toJSON(userInfoDto));

        return modelAndView;
    }

    /*****************应用平台改版测试end*******************/

    /*****************机构平台改版测试start*******************/

    /**
     * @Description: 机构主页
     * @author: wenxian.cai
     * @create: 2017/5/4 11:38
     */

    /*@RequestMapping(value = "/orghome")
    public ModelAndView orgHome() {
        ModelAndView modelAndView = new ModelAndView("platform/org/home/home");
        return  modelAndView;
    }*/

    /**
     * @Description: 机构-消息中心
     * @author: wenxian.cai
     * @create: 2017/5/4 11:38
     */

    /*@RequestMapping(value = "/orgmessage")
    public ModelAndView orgMessage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/message/message");
        String userCode = getLoginUser().getUserCode();
        int offLineCount = huanXinService.offlineMsgCount(userCode);
        int systemCount = 3;
        modelAndView.addObject("offLineCount", offLineCount);
        modelAndView.addObject("systemCount", systemCount);
        return  modelAndView;
    }*/

    /**
     * @Description: 聊天窗口
     * @author: wenxian.cai
     * @create: 2017/5/4 15:58
     */
    @RequestMapping(value = "/orgchat")
    public ModelAndView chat() {
        ModelAndView modelAndView = new ModelAndView(
                "platform/org/message/chat-dialog");

        return modelAndView;
    }

    /**
     * @Description: 会员管理
     * @author: wenxian.cai
     * @create: 2017/5/4 15:59
     */

    /*@RequestMapping(value = "/membermanage")
    public ModelAndView memberManage() {
        ModelAndView modelAndView = new ModelAndView("platform/org/membermanage/member-manage");
        int orgId = getLoginUser().getOrgId();
        List<UserDTO> userDTOS = serviceOrgService.listOrgMember(orgId);
        modelAndView.addObject("users", userDTOS);
        return modelAndView;
    }*/
    @RequestMapping(value = "/order")
    public ModelAndView memberManage() {
        PaginationDTO<ServiceOrderDTO> paginationDTO = orderService.listOrderByOrg(150,
                null, null, null, 1, 100);
        System.out.println(paginationDTO.getData());

        return new ModelAndView();
    }

    /*****************机构平台改版测试end*******************/
    @RequestMapping(value = "/layui-test")
    public ModelAndView layuiTest() {
        return new ModelAndView("test/layui");
    }

    @RequestMapping(value = "/vue-router-test")
    public ModelAndView vueRouterTest() {
        return new ModelAndView("test/vue.router");
    }

    @RequestMapping(value = "/vue-transition-test")
    public ModelAndView vueTransitionTest() {
        return new ModelAndView("test/vue.transition");
    }

    /**
     * 主页 vue路由测试
     * @return
     */
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("test/home");
    }

    
    
    @RequestMapping(value = "/weixin",method = RequestMethod.GET)
        public void callback(PrintWriter out, HttpServletResponse response,
                           @RequestParam(value = "signature", required = false) String signature, @RequestParam String timestamp,
                           @RequestParam String nonce, @RequestParam String echostr) {
        System.out.println("=1=======微信聊天调用==========");
        logger.info("=1=======微信聊天调用=========");
        logger.info("signature：" + signature + "\ntimestamp：" + timestamp + "\nnonce：" + nonce + "\nechostr：" + echostr);
        if (WechatMessageUtil.checkSignature(signature, timestamp, nonce)) {
            logger.info(echostr);
            out.print(echostr);
        }
    }

    /**
     * 微信开发测试
     * author: wenxian.cai
     * date: 2017/11/6 11:17
     */
    @RequestMapping(value = "/weixin",method = RequestMethod.POST)
    @ResponseBody
    public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=2=======微信聊天调用==========");
        logger.info("=2=======微信聊天调用=========");
        
        
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseMessage = weixinApi.processRequest(request);
        logger.info("**********responseMessage:"+responseMessage);
        System.out.println(responseMessage);
        out.print(responseMessage);
        out.flush();
        
        
        
//        request.getParameter("signature");
//        request.getParameter("timestamp");
//        request.getParameter("nonce");
//        String echostr = request.getParameter("echostr");
//        response.getWriter().append(echostr).append(request.getContextPath());
    }

    @RequestMapping(value = "/account-bind")
    public ModelAndView accountBind() {
        return new ModelAndView("mobile/wechat/account-bind");
    }


    @RequestMapping(value = "/wechat-oauth")
    public ModelAndView weChatOauth(final HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            return new ModelAndView(new RedirectView("http://mue.s1.natapp.cc/wechat/login?code=" + request.getParameter("code")));
        } catch (Exception e) {

        }

        return new ModelAndView("mobile/wechat/account-bind");
    }


    @RequestMapping(value = "/health-record")
    public ModelAndView healthRecord() {
        return new ModelAndView("mobile/wechat/health-record");
    }

    @RequestMapping(value = "/physical-record")
    public ModelAndView physicalRecord() {
        return new ModelAndView("mobile/wechat/physical/physical-record");
    }


    @RequestMapping(value = "/user")
    @ResponseBody
    public AjaxJson getUser () {
        AjaxJson ajaxJson = new AjaxJson();
        long begintime = System.nanoTime();
        for (int i = 0; i < 100; i ++) {
            memberService.getUserInfo(1330);
        }


        long endtime = System.nanoTime();
        long costTime = (endtime - begintime)/1000;
        System.out.println("costTime:" + costTime);
        return ajaxJson;
    }

    /*****************官网改版测试Start***************/

    /**
     * 首页
     * author: wenxian.cai
     * date: 2017/11/24 14:22
     */
    @RequestMapping(value = "/v2.5/home")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/home");
        DataResult dataResult = informationService.loadNewsInformationDatas("资讯信息", null, 1, null, 4);
        PaginationDTO informations = (PaginationDTO) dataResult.getAttr().get("informations");
        List list = informations.getDataObject();
        modelAndView.addObject("information", JSON.toJSON(informations.getDataObject()));
        return modelAndView;
    }

    /**
     * 专业服务-全科医生
     * author: wenxian.cai
     * date: 2017/11/24 14:22
     */
    @RequestMapping(value = "/v2.5/serve/doctor")
    public ModelAndView doctorPage() {
        return new ModelAndView("officialwebsite/v2.5.0/serve/doctor");
    }

    /**
     * 招聘人才
     * author: wenxian.cai
     * date: 2017/11/24 15:29
     */
    @RequestMapping(value = "/v2.5/about/recruitment")
    public ModelAndView recruitmentPage() {
        return new ModelAndView("officialwebsite/v2.5.0/about/recruitment");
    }


    /**
     * 招聘合作伙伴
     * author: wenxian.cai
     * date: 2017/11/24 15:32
     */
    @RequestMapping(value = "/v2.5/about/partner")
    public ModelAndView partnerPage() {
        return new ModelAndView("officialwebsite/v2.5.0/about/partner");
    }

    /**
     * 健康机构服务
     * author: wenxian.cai
     * date: 2017/11/24 15:32
     */
    @RequestMapping(value = "/v2.5/serve/store")
    public ModelAndView storePage() {
        return new ModelAndView("officialwebsite/v2.5.0/serve/store");
    }

    /**
     * 合作案例
     * author: wenxian.cai
     * date: 2017/11/24 15:51
     */
    @RequestMapping(value = "/v2.5/case")
    public ModelAndView casePage() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case");
    }
    
    /**
     * 健康机构服务详情
     * author: wenxian.cai
     * date: 2017/11/24 16:13
     */
    @RequestMapping(value = "/v2.5/serve/store-details/{id}")
    public ModelAndView storeDetailsPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/store-details");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    /**
     * 智能设备
     * author: wenxian.cai
     * date: 2017/11/24 16:16
     */
    @RequestMapping(value = "/v2.5/product/device")
    public ModelAndView devicePage() {
        return new ModelAndView("officialwebsite/v2.5.0/product/device");
    }

    /**
     * 公司简介
     * author: wenxian.cai
     * date: 2017/11/24 16:17
     */
    @RequestMapping(value = "/v2.5/about/introduction")
    public ModelAndView introductionPage() {
        return new ModelAndView("officialwebsite/v2.5.0/about/introduction");
    }

    /**
     * 健康管理平台与系统
     * author: wenxian.cai
     * date: 2017/11/24 16:58
     */
    @RequestMapping(value = "/v2.5/product/platform")
    public ModelAndView platformPage() {
        return new ModelAndView("officialwebsite/v2.5.0/product/platform");
    }

    /**
     * 帮助中心-产品说明书
     * author: wenxian.cai
     * date: 2017/11/24 16:59
     */
    @RequestMapping(value = "/v2.5/help/product-instruction")
    public ModelAndView productIntroductionPage() {
        return new ModelAndView("officialwebsite/v2.5.0/help/product-instruction");
    }

    /**
     * 专业服务-健康套餐
     * author: wenxian.cai
     * date: 2017/11/24 17:00
     */
    @RequestMapping(value = "/v2.5/serve/health-combo")
    public ModelAndView healthComboPage() {
        return new ModelAndView("officialwebsite/v2.5.0/serve/health-combo");
    }

    /**
     * 首页-公司动态
     * author: wenxian.cai
     * date: 2017/11/24 17:02
     */
    @RequestMapping(value = "/v2.5/home/news")
    public ModelAndView newsPage(@RequestParam(required = false) Integer f,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) String search) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/news");
        modelAndView.addObject("data", informationService.loadNewsInformationDatas("资讯信息",f, page, search, null));
        if (f!=null)
            modelAndView.addObject("f",f);
        if (search!=null)
            modelAndView.addObject("search",search);

        return modelAndView;
    }

    /**
     * 帮助中心-问题回馈
     * author: wenxian.cai
     * date: 2017/11/27 9:28
     */
    @RequestMapping(value = "/v2.5/help/feedback")
    public ModelAndView feedbackPage() {
        return new ModelAndView("officialwebsite/v2.5.0/help/feedback");
    }

    /**
     * 专业服务-全科医生详细
     * author: wenxian.cai
     * date: 2017/11/27 9:30
     */
    @RequestMapping(value = "/v2.5/serve/doctor-details/{id}")
    public ModelAndView doctorDetailsPage(@PathVariable("id") int id) {

        return new ModelAndView("officialwebsite/v2.5.0/serve/doctor-details-" + id);
    }

    /**
     * 合作案例1
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-1")
    public ModelAndView case1Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-1");
    }

    /**
     * 合作案例2
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-2")
    public ModelAndView case2Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-2");
    }


    /**
     * 合作案例3
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-3")
    public ModelAndView case3Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-3");
    }


    /**
     * 合作案例4
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-4")
    public ModelAndView case4Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-4");
    }


    /**
     * 合作案例5
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-5")
    public ModelAndView case5Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-5");
    }


    /**
     * 合作案例6
     * author: wenxian.cai
     * date: 2017/11/27 9:31
     */
    @RequestMapping(value = "/v2.5/case/case-6")
    public ModelAndView case6Page() {
        return new ModelAndView("officialwebsite/v2.5.0/case/case-6");
    }

    /**
     * 关于我们-联系我们
     * author: wenxian.cai
     * date: 2017/11/27 10:32
     */
    @RequestMapping(value = "/v2.5/about/contact")
    public ModelAndView contactPage() {
        return new ModelAndView("officialwebsite/v2.5.0/about/contact");
    }

    /**
     * 关于我们-二维码下载
     * author: wenxian.cai
     * date: 2017/11/27 10:50
     */
    @RequestMapping(value = "/v2.5/about/download-code")
    public ModelAndView downloadCodePage() {
        return new ModelAndView("officialwebsite/v2.5.0/about/download-code");
    }

    /**
     * 产品与平台-机构app
     * author: wenxian.cai
     * date: 2017/11/27 10:53
     */
    @RequestMapping(value = "/v2.5/product/store-app")
    public ModelAndView storeAppPage() {
        return new ModelAndView("officialwebsite/v2.5.0/product/store-app");
    }

    /**
     * 帮助中心-产品视频
     * author: wenxian.cai
     * date: 2017/11/27 10:54
     */
    @RequestMapping(value = "/v2.5/help/product-video")
    public ModelAndView productVideoPage() {
        return new ModelAndView("officialwebsite/v2.5.0/help/product-video");
    }

    /**
     * 产品与平台
     * author: wenxian.cai
     * date: 2017/11/27 10:56
     */
    @RequestMapping(value = "/v2.5/product/user-app")
    public ModelAndView userAppPage() {
        return new ModelAndView("officialwebsite/v2.5.0/product/user-app");
    }

    /**
     * 专业服务-健康套餐详情
     * author: wenxian.cai
     * date: 2017/11/27 10:58
     */
    @RequestMapping(value = "/v2.5/serve/health-combo-details")
    public ModelAndView healthComboDetailsPage() {
        return new ModelAndView("officialwebsite/v2.5.0/serve/health-combo-details");
    }

    /**
     * 首页-公司动态详细
     * author: wenxian.cai
     * date: 2017/11/27 11:55
     */
    @RequestMapping(value = "/v2.5/home/news-details")
    public ModelAndView newsDetailsPage(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/home/news-details");
        DataResult result = informationService.lookNewsById(id);
        modelAndView.addObject("info", result.get("info"));
        modelAndView.addObject("columnName", result.get("columnName"));
        modelAndView.addObject("hot",result.get("hot"));
        modelAndView.addObject("hangye",result.get("hangye"));
        return modelAndView;
    }

    /**
     * 首页-资讯
     * author: wenxian.cai
     * date: 2017/11/27 11:55
     */
    @RequestMapping(value = "/v2.5/home/information")
    public ModelAndView informationPage() {
        return new ModelAndView("officialwebsite/v2.5.0/home/information");
    }

    /**
     * 知识库
     * author: wenxian.cai
     * date: 2017/11/27 11:57
     */
    @RequestMapping(value = "/v2.5/home/repository")
    public ModelAndView repositoryPage() {
        return new ModelAndView("officialwebsite/v2.5.0/home/repository");
    }

    @RequestMapping(value = "/v2.5/serve/paper/physical")
    public ModelAndView physicalPage() {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/paper/physical-paper");
        List<PaperDTO> questionnaireDTOs = question.listPaper("体质");
        List<PaperOptionDTO> optionDTOs = question.listPaperOption("体质");
        modelAndView.addObject("paper", JSON.toJSON(questionnaireDTOs));
        modelAndView.addObject("paperOption", JSON.toJSON(optionDTOs));
        return modelAndView;
    }


    @RequestMapping(value = "/v2.5/serve/paper/physical-analyze")
    public ModelAndView physicalAnalyzePage(@RequestParam(value = "result") String physicalName,
                                            @RequestParam(value = "scoreArr") List<Integer> scoreArr) {
        ModelAndView modelAndView = new ModelAndView("officialwebsite/v2.5.0/serve/paper/physical-analyze");
        PaperPhysicalStandardDTO physicalStandardDTO = question.getPaperPhysicalStandard(physicalName);
        modelAndView.addObject("physicalStandard", JSON.toJSON(physicalStandardDTO));
        modelAndView.addObject("scoreArr", scoreArr);
        return modelAndView;
    }


    /*****************官网改版测试End***************/

    public static void main(String[] arg) {
        logger.info("info");
        logger.error("error");
    }


}
