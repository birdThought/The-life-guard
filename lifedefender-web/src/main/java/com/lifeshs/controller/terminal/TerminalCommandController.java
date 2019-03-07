package com.lifeshs.controller.terminal;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.ServiceMessage;

import com.lifeshs.controller.common.BaseController;

import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.entity.member.TUserOperationDetail;

import com.lifeshs.pojo.member.HealthDataDTO;
import com.lifeshs.pojo.member.UserRecordDTO;

import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.terminal.ITerminalService;

import com.lifeshs.utils.ResourceUtil;
import com.lifeshs.utils.Toolkits;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *  用户对终端设备的设置操作
 *  @author yuhang.weng
 *  @DateTime 2016年6月1日 下午1:27:04
 */
@Controller
@RequestMapping("terminalCommandControl")
public class TerminalCommandController extends BaseController {
    private static final Logger logger = Logger.getLogger(TerminalCommandController.class);
    @Autowired
    private ITerminalService tService;

    //  @Autowired
    //  private IContactsService contacts;
    @Autowired
    private IDeviceService deviceService;

    //  @Autowired
    //  private MinaAdapter mina;

    //  @Autowired
    //  private IHL03Service hl03;

    /** 测试入口 */
    @RequestMapping(params = "hLEnter", method = RequestMethod.GET)
    public String hLEnter() {
        // 这是一段测试代码
        // 测试是否能够正常从数据库获取到科室与二级科室的信息
        // 不要打开这段代码
        //      TRecordDepartment department = dao.selectDepartmentByDepartmentChildId(1);
        //      List<TRecordDepartmentChild> departmentChilds = dao.selectDepartmentChildsByDepartmentId(1);
        //      List<TRecordDepartment> departments = dao.selectDepartments();
        return "com/lifeshs/member/healthFile"; // main/testAjaxDataSend
    }

    /** 测试方法 */
    @RequestMapping(params = "pageSplit", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson commandEnter(@RequestParam
    int pageSize, @RequestParam
    int page) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        try {
            List<String> keys = new ArrayList<String>(4);
            keys.add("status");
            keys.add("heartRate");
            keys.add("heartRateArea");
            keys.add("measureDate");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userId", getLoginUser().getId());
            params.put("measureDate", "2016-05-31");

            List<Map<String, Object>> result = deviceService.selectDeviceDataWithSpecificDatePageSplit(TMeasureHeartrate.class,
                    keys, params, pageSize, page);
            resObject.setSuccess(true);
            resObject.setObj(result);

            return resObject;
        } catch (Exception e) {
            resObject.setMsg(e.getMessage());
            logger.error(e.getMessage());

            return resObject;
        }
    }

    @RequestMapping(params = "normalEnter", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson normalEnter() throws Exception {
        //      TerminalCommandControl.class.getResource("static\\images\\1.png").getFile();
        //      File file1 = new File("static\\images\\1.png");
        //      byte[] data = readInputStream(new FileInputStream(file1));
        //      File file = new File("static\\images\\666.png");
        //      FileOutputStream out = new FileOutputStream(file);
        //      out.write(data);
        //      out.flush();
        //      out.close();

        //      ImageUtil util = new ImageUtil(new byte[]{});
        //      
        //      String r = util.GeneratePath("upload", "userImages", 1, "1.png");
        //      
        //      System.out.println(r);
        //      System.out.println("RelativePath:" + util.getRelativePath());
        System.out.println(ResourceUtil.getPorjectPath());

        return null;
    }

    //  
    //  public static byte[] readInputStream(InputStream inStream) throws Exception{  
    //        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
    //        //创建一个Buffer字符串  
    //        byte[] buffer = new byte[1024];  
    //        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
    //        int len = 0;  
    //        //使用一个输入流从buffer里把数据读取出来  
    //        while( (len=inStream.read(buffer)) != -1 ){  
    //            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
    //            outStream.write(buffer, 0, len);  
    //        }  
    //        //关闭输入流  
    //        inStream.close();  
    //        //把outStream里的数据写入内存  
    //        return outStream.toByteArray();  
    //    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月15日 下午3:50:18
     *  @serverComment (穿戴设备)设备管理页面跳转
     *
     *  @return
     */
    @RequestMapping(params = "showDevice", method = RequestMethod.GET)
    public String showDevice(Model model) {
        try {
            List<TUserTerminal> devices = tService.geTUserTerminals(getLoginUser()
                                                                        .getId());
            model.addAttribute("devices", devices);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取设备列表失败:" + e.getMessage());
            model.addAttribute("error", "获取设备列表失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月16日 下午1:48:42
     *  @serverComment HL设备绑定
     *
     *  @param imei 串号
     *  @param mobile 设备插入的手机卡号码
     *  @param sosContactNumber 紧急求助号码
     *  @return
     */
    @RequestMapping(params = "bindHLDevice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson bindHLDevice(@RequestParam
    String imei, @RequestParam
    String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "HL设备绑定失败";

        // 数据校验
        if (!isWellFormed(imei, "imei")) {
            resObject.setMsg("请输入正确的imei号");

            return resObject;
        }

        if (!Toolkits.verifyPhone(mobile)) {
            resObject.setMsg("请输入正确的手机号码");

            return resObject;
        }

        int userId = getLoginUser().getId();

        try {
            if (tService.isUserBindSameTerminal(userId, "HL03")) {
                resObject.setMsg(msg + "：不能同时绑定两个HL设备");

                return resObject;
            }

            ServiceMessage sm = tService.bindTerminal(userId, imei, "", mobile,
                    "HL03");
            resObject.setSuccess(sm.isSuccess());
            resObject.setMsg(sm.getMessage());

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月16日 下午1:49:11
     *  @serverComment HL设备解绑
     *
     *  @param imei 串号
     *  @return
     */
    @RequestMapping(params = "unBindHLDevice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson unBindHLDevice(@RequestParam
    String imei) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "HL设备解绑失败";

        // 数据校验
        if (!isWellFormed(imei, "imei")) {
            resObject.setMsg("请输入正确的imei号");

            return resObject;
        }

        int userId = getLoginUser().getId();

        try {
            if (!tService.unBindTerminal(userId, imei, "HL03")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("HL设备解绑成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月16日 下午1:49:44
     *  @serverComment C3设备绑定
     *
     *  @param imei 串号
     *  @param mobile 设备插入的手机卡号码
     *  @param sosContactNumber 紧急求助号码
     *  @return
     */
    @RequestMapping(params = "bindCDevice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson bindCDevice(@RequestParam
    String imei, @RequestParam
    String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "C3设备绑定失败";

        // 数据校验
        if (!isWellFormed(imei, "imei")) {
            resObject.setMsg("请输入正确的imei号");

            return resObject;
        }

        if (!Toolkits.verifyPhone(mobile)) {
            resObject.setMsg("请输入正确的手机号码");

            return resObject;
        }

        int userId = getLoginUser().getId();

        try {
            if (tService.isUserBindSameTerminal(userId, "C3")) {
                resObject.setMsg(msg + "：不能同时绑定两个C3设备");

                return resObject;
            }

            ServiceMessage sm = tService.bindTerminal(userId, imei, "", mobile,
                    "C3");
            resObject.setSuccess(sm.isSuccess());
            resObject.setMsg(sm.getMessage());

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月16日 下午1:50:12
     *  @serverComment C3设备解绑
     *
     *  @param imei 串号
     *  @return
     */
    @RequestMapping(params = "unBindCDevice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson unBindCDevice(@RequestParam
    String imei) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "C3设备解绑失败";

        // 数据校验
        if (!isWellFormed(imei, "imei")) {
            resObject.setMsg("请输入正确的imei号");

            return resObject;
        }

        int userId = getLoginUser().getId();

        try {
            if (!tService.unBindTerminal(userId, imei, "C3")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("C3设备解绑成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 上午10:13:22
     *  @serverComment 获取HL设备的黑名单列表
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showHLBlackList", method = RequestMethod.GET)
    public String showHLBlackList(Model model) {
        try {
            model.addAttribute("lists",
                tService.getDeviceBlackList(getLoginUser().getId(), "HL03"));
        } catch (Exception e) {
            logger.error("获取HL设备黑名单列表失败:" + e.getMessage());
            model.addAttribute("error", "获取HL设备黑名单列表失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }

        return "";
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 上午10:35:24
     *  @serverComment HL设备添加黑名单
     *
     *  @param mobile 黑名单电话号码
     *  @return
     */
    @RequestMapping(params = "addHLBlackList", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson addHLBlackList(@RequestParam
    String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "HL设备添加黑名单失败";
        int userId = getLoginUser().getId();

        try {
            if (tService.isBlackListLimited(userId, "HL03")) {
                resObject.setMsg(msg + ":用户HL设备黑名单列表已达上限");

                return resObject;
            }

            if (!tService.addBlackList(userId, mobile, "", "HL03")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("添加黑名成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 下午3:15:06
     *  @serverComment 删除HL设备的一条黑名单记录
     *
     *  @param mobileId 黑名单电话号码ID
     *  @return
     */
    @RequestMapping(params = "delHLBlackList", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson delHLBlackList(@RequestParam
    int mobileId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "删除黑名单失败";

        try {
            if (!tService.delBlackList(getLoginUser().getId(), mobileId, "HL03")) {
                resObject.setMsg(msg + ":找不到该黑名单内容");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("删除黑名成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 上午10:13:37
     *  @serverComment 获取C3设备的黑名单列表
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showCBlackList", method = RequestMethod.GET)
    public String showCBlackList(Model model) {
        try {
            model.addAttribute("lists",
                tService.getDeviceBlackList(getLoginUser().getId(), "C3"));
        } catch (Exception e) {
            logger.error("获取C3设备黑名单列表失败:" + e.getMessage());
            model.addAttribute("error", "获取C3设备黑名单列表失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }

        return "";
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 上午10:38:00
     *  @serverComment C3设备添加黑名单
     *
     *  @param 黑名单电话号码
     *  @return
     */
    @RequestMapping(params = "addCBlackList", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson addCBlackList(@RequestParam
    String mobile) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "C3设备添加黑名单失败";
        int userId = getLoginUser().getId();

        try {
            if (tService.isBlackListLimited(userId, "C3")) {
                resObject.setMsg(msg + ":用户C3设备黑名单列表已达上限");

                return resObject;
            }

            if (!tService.addBlackList(userId, mobile, "", "C3")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("添加黑名成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月18日 下午3:15:41
     *  @serverComment 删除C3设备的一条黑名单记录
     *
     *  @param mobileId 黑名单电话号码ID
     *  @return
     */
    @RequestMapping(params = "delCBlackList", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson delCBlackList(@RequestParam
    int mobileId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "删除黑名单失败";

        try {
            if (!tService.delBlackList(getLoginUser().getId(), mobileId, "C3")) {
                resObject.setMsg(msg + ":找不到该黑名单内容");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("删除黑名成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午12:07:04
     *  @serverComment HL03设备提醒细节内容展示
     *
     *  @param noticeId 提醒id
     *  @return
     */
    @RequestMapping(params = "showHLNoticeDetail", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson showHLNoticeDetail(@RequestParam
    int noticeId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "获取详细内容失败";

        try {
            TUserNotice notice = tService.getTerminalNotice(getLoginUser()
                                                                .getId(),
                    "HL03", noticeId);
            resObject.setObj(notice); // 将数据保存后传递
            resObject.setSuccess(true);
            resObject.setMsg("获取详细内容成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    //  /**
    //   *  @author yuhang.weng 
    //   *  @DateTime 2016年6月20日 下午12:11:49
    //   *  @serverComment 添加HL03设备提醒
    //   *
    //   *  @param weeks 星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
    //   *  @param time 提醒时间
    //   *  @param content 提醒内容
    //   *  @return
    //   */
    //  @RequestMapping(params = "addHLNotice", method = RequestMethod.POST)
    //  public @ResponseBody AjaxJson addHLNotice(@RequestParam String weeks, @RequestParam String time, @RequestParam String content){
    //      AjaxJson resObject = new AjaxJson();
    //      resObject.setSuccess(false);
    //      String msg = "添加提醒失败";
    //      int userId = getLoginUser().getId();
    //      
    //      // 对传递的参数的数据格式校验
    //      if(!isWellFormed(weeks, "weeks") || !isWellFormed(time, "time")){
    //          resObject.setMsg("请按要求输入内容");
    //          return resObject;
    //      }
    //      
    //      try {
    //          if(tService.isNoticeLimited(userId, "HL03")){   // 校验是否被限制不能添加提醒
    //              resObject.setMsg(msg + ":已被限制不能继续添加提醒");
    //              return resObject;
    //          }
    //          if(!tService.addTerminalNotice(userId, "HL03", weeks, time, content)){
    //              resObject.setMsg(msg);
    //              return resObject;
    //          }
    //          resObject.setSuccess(true);
    //          resObject.setMsg("添加提醒成功");
    //          return resObject;
    //      } catch (Exception e) {
    //          logger.error(msg + ":" + e.getMessage());
    //          resObject.setMsg(msg + ":" + e.getMessage());
    //          return resObject;
    //      }
    //  }

    //  /**
    //   *  @author yuhang.weng 
    //   *  @DateTime 2016年6月20日 下午1:54:24
    //   *  @serverComment 修改HL03设备的提醒
    //   *
    //   *  @param weeks 星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
    //   *  @param time 提醒时间
    //   *  @param content 提醒内容
    //   *  @param status 状态，开启_1，关闭_0
    //   *  @param noticeId 提醒id
    //   *  @return
    //   */
    //  @RequestMapping(params = "modifyHLNotice", method = RequestMethod.POST)
    //  public @ResponseBody AjaxJson modifyHLNotice(@RequestParam String weeks, @RequestParam String time, @RequestParam String content, @RequestParam int status, @RequestParam int noticeId){
    //      AjaxJson resObject = new AjaxJson();
    //      resObject.setSuccess(false);
    //      String msg = "修改提醒失败";
    //      
    //      if(!isWellFormed(weeks, "weeks") || !isWellFormed(time, "time") || !isWellFormed(String.valueOf(status), "status")){
    //          resObject.setMsg("请按要求输入内容");
    //          return resObject;
    //      }
    //      
    //      try {
    //          if(!tService.modTerminalNotice(getLoginUser().getId(), "HL03", weeks, time, content, status, noticeId)){
    //              resObject.setMsg(msg);
    //              return resObject;
    //          }
    //          resObject.setSuccess(true);
    //          resObject.setMsg("修改提醒成功");
    //          return resObject;
    //      } catch (Exception e) {
    //          logger.error(msg + ":" + e.getMessage());
    //          resObject.setMsg(msg + ":" + e.getMessage());
    //          return resObject;
    //      }
    //  }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午1:55:07
     *  @serverComment 删除HL03设备的提醒
     *
     *  @param noticeId 提醒id
     *  @return
     */
    @RequestMapping(params = "delHLNotice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson delHLNotice(@RequestParam
    int noticeId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "删除提醒失败";

        try {
            if (!tService.delTerminalNotice(getLoginUser().getId(), noticeId,
                        "HL03")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("删除提醒成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午12:05:09
     *  @serverComment C3设备提醒设置页面跳转
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showCNotices", method = RequestMethod.GET)
    public String showCNotices(Model model) {
        try {
            List<TUserNotice> notices = tService.getTerminalNotices(getLoginUser()
                                                                        .getId(),
                    "C3");
            model.addAttribute("notices", notices);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取C3设备提醒列表失败:" + e.getMessage());
            model.addAttribute("error", "获取C3设备提醒列表失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午12:07:04
     *  @serverComment C3设备提醒细节内容展示
     *
     *  @return
     */
    @RequestMapping(params = "showCNoticeDetail", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson showCNoticeDetail(@RequestParam
    int noticeId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "获取详细内容失败";

        try {
            TUserNotice notice = tService.getTerminalNotice(getLoginUser()
                                                                .getId(), "C3",
                    noticeId);
            resObject.setObj(notice); // 将数据保存后传递
            resObject.setSuccess(true);
            resObject.setMsg("获取详细内容成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    //  /**
    //   *  @author yuhang.weng 
    //   *  @DateTime 2016年6月20日 下午3:53:52
    //   *  @serverComment 添加C3设备提醒
    //   *
    //   *  @param weeks 星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
    //   *  @param time 提醒时间
    //   *  @param content 提醒内容
    //   *  @return
    //   */
    //  @RequestMapping(params = "addCNotice", method = RequestMethod.POST)
    //  public @ResponseBody AjaxJson addCNotice(@RequestParam String weeks, @RequestParam String time, @RequestParam String content){
    //      AjaxJson resObject = new AjaxJson();
    //      resObject.setSuccess(false);
    //      String msg = "添加提醒失败";
    //      int userId = getLoginUser().getId();
    //      
    //      // 对传递的参数的数据格式校验
    //      if(!isWellFormed(weeks, "weeks") || !isWellFormed(time, "time")){
    //          resObject.setMsg("请按要求输入内容");
    //          return resObject;
    //      }
    //      
    //      try {
    //          if(tService.isNoticeLimited(userId, "C3")){ // 校验是否被限制不能添加提醒
    //              resObject.setMsg(msg + ":已被限制不能继续添加提醒");
    //              return resObject;
    //          }
    //          if(!tService.addTerminalNotice(userId, "C3", weeks, time, content)){
    //              resObject.setMsg(msg);
    //              return resObject;
    //          }
    //          resObject.setSuccess(true);
    //          resObject.setMsg("添加提醒成功");
    //          return resObject;
    //      } catch (Exception e) {
    //          logger.error(msg + ":" + e.getMessage());
    //          resObject.setMsg(msg + ":" + e.getMessage());
    //          return resObject;
    //      }
    //  }

    //  /**
    //   *  @author yuhang.weng 
    //   *  @DateTime 2016年6月20日 下午1:54:24
    //   *  @serverComment 修改C3设备的提醒
    //   *
    //   *  @param weeks 星期设定,从低到高分别代表星期1－星期日，0代表关，1代表开
    //   *  @param time 提醒时间
    //   *  @param content 提醒内容
    //   *  @param status 状态，开启_1，关闭_0
    //   *  @param noticeId 提醒id
    //   *  @return
    //   */
    //  @RequestMapping(params = "modifyCNotice", method = RequestMethod.POST)
    //  public @ResponseBody AjaxJson modifyCNotice(@RequestParam String weeks, @RequestParam String time, @RequestParam String content, @RequestParam int status, @RequestParam int noticeId){
    //      AjaxJson resObject = new AjaxJson();
    //      resObject.setSuccess(false);
    //      String msg = "修改提醒失败";
    //      
    //      // 对传递的参数的数据格式校验
    //      if(isWellFormed(weeks, "weeks") || isWellFormed(time, "time") || isWellFormed(String.valueOf(status), "status")){
    //          resObject.setMsg("请按要求输入内容");
    //          return resObject;
    //      }
    //      
    //      try {
    //          if(!tService.modTerminalNotice(getLoginUser().getId(), "C3", weeks, time, content, status, noticeId)){
    //              resObject.setMsg(msg);
    //              return resObject;
    //          }
    //          resObject.setSuccess(true);
    //          resObject.setMsg("修改提醒成功");
    //          return resObject;
    //      } catch (Exception e) {
    //          logger.error(msg + ":" + e.getMessage());
    //          resObject.setMsg(msg + ":" + e.getMessage());
    //          return resObject;
    //      }
    //  }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午1:55:07
     *  @serverComment 删除C3设备的提醒
     *
     *  @return
     */
    @RequestMapping(params = "delCNotice", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson delCNotice(@RequestParam
    int noticeId) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "删除提醒失败";

        try {
            if (tService.delTerminalNotice(getLoginUser().getId(), noticeId,
                        "C3")) {
                resObject.setMsg(msg);

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("删除提醒成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午9:18:44
     *  @serverComment HL03运行模式页面跳转
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showHLOperationMode", method = RequestMethod.GET)
    public String showHLOperationMode(Model model) {
        try {
            Map<String, Object> resMap = tService.getTerminalOperationMode(getLoginUser()
                                                                               .getId(),
                    "HL03");
            model.addAttribute("resMap", resMap);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取HL03设备运行模式失败:" + e.getMessage());
            model.addAttribute("error", "获取HL03设备运行模式失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午11:03:44
     *  @serverComment 获取HL设备模式设置详细信息
     *
     *  @return
     */
    @RequestMapping(params = "showHLModeDetail", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson showHLModeDetail() {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "获取模式详细内容失败";

        try {
            Map<String, Object> resMap = tService.getTerminalOperationMode(getLoginUser()
                                                                               .getId(),
                    "HL03");
            resObject.setObj(resMap);
            resObject.setSuccess(true);
            resObject.setMsg("获取内容成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午9:20:59
     *  @serverComment HL03运行模式功能修改
     *
     *  @return
     */
    @RequestMapping(params = "modifyHLOperationMode", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson modifyHLOperationMode(@RequestBody
    TUserOperationDetail detail) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "运行模式修改失败";

        try {
            if (!tService.modTerminalOperationMode(getLoginUser().getId(),
                        "HL03", detail)) {
                resObject.setMsg(msg + ":查找不到该设备信息");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("修改运行模式成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午9:19:48
     *  @serverComment C3运行模式页面跳转
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showCOperationMode", method = RequestMethod.GET)
    public String showCOperationMode(Model model) {
        try {
            Map<String, Object> resMap = tService.getTerminalOperationMode(getLoginUser()
                                                                               .getId(),
                    "C3");
            model.addAttribute("resMap", resMap);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取C3设备运行模式失败:" + e.getMessage());
            model.addAttribute("error", "获取C3设备运行模式失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午9:20:59
     *  @serverComment C3运行模式功能修改
     *
     *  @return
     */
    @RequestMapping(params = "showCModeDetail", method = RequestMethod.GET)
    public @ResponseBody
    AjaxJson showCModeDetail() {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "获取模式详细内容失败";

        try {
            Map<String, Object> resMap = tService.getTerminalOperationMode(getLoginUser()
                                                                               .getId(),
                    "C3");
            resObject.setObj(resMap);
            resObject.setSuccess(true);
            resObject.setMsg("获取内容成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月21日 上午9:20:59
     *  @serverComment C3运行模式功能修改
     *
     *  @return
     */
    @RequestMapping(params = "modifyCOperationMode", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson modifyCOperationMode(@RequestBody
    TUserOperationDetail detail) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "运行模式修改失败";

        try {
            if (!tService.modTerminalOperationMode(getLoginUser().getId(),
                        "C3", detail)) {
                resObject.setMsg(msg + ":查找不到该设备信息");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("修改运行模式成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月17日 下午2:44:22
     *  @serverComment (健康包)设备管理页面跳转
     *
     *  @return
     */
    @RequestMapping(params = "showHealthPackageDevices", method = RequestMethod.GET)
    public String showHealthPackageDevices(Model model) {
        int userId = getLoginUser().getId();

        try {
            // 获取用户勾选的健康产品
            model.addAttribute("devices",
                tService.getUserHealthProductsList(userId));

            // 获取健康包参数
            UserRecordDTO user = tService.getUserHealthPackageParams(userId);
            model.addAttribute("user", user);

            // 计算年龄
            Date birth = user.getBirthday();
            Calendar calendar = Calendar.getInstance();

            // 获取现在的年份
            int yearNow = calendar.get(Calendar.YEAR);
            // 将日期设置为生日那一天
            calendar.setTime(birth);

            int yearBirth = calendar.get(Calendar.YEAR);
            int age = yearNow - yearBirth;

            model.addAttribute("age", age);

            return "com/lifeshs/member/device"; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取用户勾选的健康包参数失败");
            model.addAttribute("error", "获取用户勾选的健康包参数失败:" + e.getMessage());

            return ""; // TODO 错误页面
        }
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
    public @ResponseBody
    AjaxJson modifyHealthPackageDevices(@RequestParam
    String typeName, @RequestParam
    boolean cancel) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "健康包设备管理失败";
        int userId = getLoginUser().getId();

        try {
            if (cancel) {
                tService.delUserHealthProducts(userId, typeName); // 取消勾选
                resObject.setSuccess(true);
                resObject.setMsg("取消健康包成功");
            } else {
                tService.addUserHealthProducts(userId, typeName); // 勾选
                resObject.setSuccess(true);
                resObject.setMsg("添加健康包成功");
            }

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月16日 下午1:45:18
     *  @serverComment 健康包参数设置页面跳转
     *
     *  @return
     */
    @RequestMapping(params = "showHealthPackageParams", method = RequestMethod.GET)
    public String showHealthPackageParams(Model model) {
        int userId = getLoginUser().getId();

        try {
            UserRecordDTO user = tService.getUserHealthPackageParams(userId);
            model.addAttribute("user", user);

            // 计算年龄
            Date birth = user.getBirthday();
            Calendar calendar = Calendar.getInstance();

            // 获取现在的年份
            int yearNow = calendar.get(Calendar.YEAR);
            // 将日期设置为生日那一天
            calendar.setTime(birth);

            int yearBirth = calendar.get(Calendar.YEAR);
            int age = yearNow - yearBirth;

            model.addAttribute("age", age);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取健康包参数失败:" + e.getMessage());
            model.addAttribute("error", "获取健康包参数失败:" + e.getMessage());

            return ""; // TODO 失败页面
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
    public @ResponseBody
    AjaxJson setHealthPackageParams(@RequestBody
    Map<String, Object> map) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(true);

        String msg = "健康包参数设置失败";

        int age = (Integer) map.get("age");

        // 年龄参数审核
        if ((age < 0) || (age > 200)) {
            resObject.setMsg("年龄应设置在0-200之间");

            return resObject;
        }

        int userId = getLoginUser().getId();
        boolean gender = (boolean) map.get("sex");
        Float height = ((Double) map.get("height")).floatValue();
        Float weight = ((Double) map.get("weight")).floatValue();
        Float waist = ((Double) map.get("waist")).floatValue();
        Float bust = ((Double) map.get("bust")).floatValue();
        Float hip = ((Double) map.get("hip")).floatValue();

        if (!tService.modifyUserHealthPackageParams(userId, gender, height,
                    weight, waist, bust, hip, age)) {
            resObject.setMsg(msg);

            return resObject;
        }

        resObject.setSuccess(true);
        resObject.setMsg("修改参数成功");

        return resObject;
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月22日 上午9:54:48
     *  @serverComment 添加健康数据
     *
     *  @param data 健康数据
     *  @return
     */
    @RequestMapping(params = "addHealthData", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson addHealthData(@RequestBody
    HealthDataDTO data) throws OperationException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "手动添加健康数据失败";
        String healthType = data.getHealthType();
        int userId = getLoginUser().getId();

        Integer userHealthProduct = getCacheMemberSharingData(userId)
                                        .getHealthProduct();

        if (!tService.isHealthDataBinded(healthType, userHealthProduct)) {
            resObject.setMsg("用户尚未绑定该健康包");

            return resObject;
        }

        if (!tService.addHealthData(data, userId)) {
            resObject.setMsg(msg);
        try {
            if (!tService.addHealthData(data, userId)) {
                resObject.setMsg(msg);
				return resObject;
			}
        } catch (OperationException e) {
            e.printStackTrace();
                return resObject;
            }
        }

        resObject.setSuccess(true);
        resObject.setMsg("手动添加健康包数据成功");

        return resObject;
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月22日 下午2:27:05
     *  @serverComment HL03设备监控频率页面跳转(获取HL03设备的监控频率参数)
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showHLMonitorFrequency", method = RequestMethod.GET)
    public String showHLMonitorFrequency(Model model) {
        try {
            TUserTerminal terminal = tService.getMoniterFrequency(getLoginUser()
                                                                      .getId(),
                    "HL03");

            if (terminal == null) {
                model.addAttribute("success", false);
                model.addAttribute("result", "请先绑定HL03设备后再使用此功能");

                return ""; // TODO 错误页面
            }

            model.addAttribute("success", true);
            model.addAttribute("terminal", terminal);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取HL03设备监控频率参数失败:" + e.getMessage());
            model.addAttribute("error", "获取HL03设备监控频率参数失败::" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月22日 下午2:42:40
     *  @serverComment 修改HL03设备的监控频率参数
     *
     *  @param heartFrequency 心跳包频率（秒）
     *  @param locationFrequency 位置上传频率（秒）
     *  @param autoFrequency70  电量70%降频频率
     *  @param autoFrequency50  电量50%降频频率
     *  @param autoFrequency30  电量30%降频频率
     *  @return
     */
    @RequestMapping(params = "modifyHLMonitorFrequency", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson modifyHLMonitorFrequency(@RequestParam
    int heartFrequency, @RequestParam
    int locationFrequency, @RequestParam
    int autoFrequency70, @RequestParam
    int autoFrequency50, @RequestParam
    int autoFrequency30) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "修改设备参数失败";

        try {
            if (!tService.modifyMoniterFrequency(getLoginUser().getId(),
                        "HL03", heartFrequency, locationFrequency,
                        autoFrequency70, autoFrequency50, autoFrequency30)) {
                resObject.setMsg("找不到该设备的信息");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("修改设备参数成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月22日 下午2:27:58
     *  @serverComment C3设备监控频率页面跳转(获取C3设备的监控频率参数)
     *
     *  @param model
     *  @return
     */
    @RequestMapping(params = "showCMonitorFrequency", method = RequestMethod.GET)
    public String showCMonitorFrequency(Model model) {
        try {
            TUserTerminal terminal = tService.getMoniterFrequency(getLoginUser()
                                                                      .getId(),
                    "C3");

            if (terminal == null) {
                model.addAttribute("success", false);
                model.addAttribute("result", "请先绑定C3设备后再使用此功能");

                return ""; // TODO 错误页面
            }

            model.addAttribute("success", true);
            model.addAttribute("terminal", terminal);

            return ""; // TODO 成功页面
        } catch (Exception e) {
            logger.error("获取C3设备监控频率参数失败:" + e.getMessage());
            model.addAttribute("error", "获取C3设备监控频率参数失败::" + e.getMessage());

            return ""; // TODO 错误页面
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月22日 下午2:29:42
     *  @serverComment 修改C3设备的监控频率参数
     *
     *  @param heartFrequency 心跳包频率（秒）
     *  @param locationFrequency 位置上传频率（秒）
     *  @param autoFrequency70  电量70%降频频率
     *  @param autoFrequency50  电量50%降频频率
     *  @param autoFrequency30  电量30%降频频率
     *  @return
     */
    @RequestMapping(params = "modifyCMonitorFrequency", method = RequestMethod.POST)
    public @ResponseBody
    AjaxJson modifyCMonitorFrequency(@RequestParam
    int heartFrequency, @RequestParam
    int locationFrequency, @RequestParam
    int autoFrequency70, @RequestParam
    int autoFrequency50, @RequestParam
    int autoFrequency30) {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);

        String msg = "修改设备参数失败";

        try {
            if (!tService.modifyMoniterFrequency(getLoginUser().getId(), "C3",
                        heartFrequency, locationFrequency, autoFrequency70,
                        autoFrequency50, autoFrequency30)) {
                resObject.setMsg("找不到该设备的信息");

                return resObject;
            }

            resObject.setSuccess(true);
            resObject.setMsg("修改设备参数成功");

            return resObject;
        } catch (Exception e) {
            logger.error(msg + ":" + e.getMessage());
            resObject.setMsg(msg + ":" + e.getMessage());

            return resObject;
        }
    }

    /**
     *  @author yuhang.weng
     *  @DateTime 2016年6月20日 下午3:31:35
     *  @serverComment 校验数据格式
     *
     *  @param data 数据
     *  @param checkName 校验名称[weeks,time,status,imei]
     *  @return
     */
    private boolean isWellFormed(String data, String checkName) {
        switch (checkName) {
        case "weeks":

            // 要求格式为 长度为7，且内容为0或1
            String regex_weeks = "^[0|1]{7}$";
            Pattern p_weeks = Pattern.compile(regex_weeks);
            Matcher m_weeks = p_weeks.matcher(data);

            return m_weeks.matches();

        case "time":

            // 要求格式为 xx:xx，x表示0-9的数字
            String regex_time = "^[0-9]{2}:[0-9]{2}$";
            Pattern p_time = Pattern.compile(regex_time);
            Matcher m_time = p_time.matcher(data);

            return m_time.matches();

        case "status":

            // 要求格式为0或1
            String regex_status = "^(0|1)$";
            Pattern p_status = Pattern.compile(regex_status);
            Matcher m_status = p_status.matcher(data);

            return m_status.matches();

        case "imei":

            String regex_imei = "^[0-9]{32}$";
            Pattern p_imei = Pattern.compile(regex_imei);
            Matcher m_imei = p_imei.matcher(data);

            return m_imei.matches();
        }

        return false;
    }
}
