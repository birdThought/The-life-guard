package com.lifeshs.controller.terminal;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.ContactTerminalType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.constants.common.VcodeTerminalType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.sms.SMSException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.map.Gps;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.entity.member.TUserBlackWhiteList;
import com.lifeshs.entity.member.TUserContacts;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.entity.member.TUserMonitorTrack;
import com.lifeshs.entity.member.TUserNotice;
import com.lifeshs.entity.member.TUserOperationDetail;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.jsp.GpsModel;
import com.lifeshs.service.contacts.IContactsService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.thirdservice.SMSService;
import com.lifeshs.utils.BaiduMapAPI;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.SMSCommand;
import com.lifeshs.utils.Toolkits;

/**
 * <p>
 * 设备的WEB端控制器
 * 
 * @author yuhang.weng
 * @DateTime 2016年7月29日 下午5:02:46
 */
@Controller
@RequestMapping(value = {"terminalWebControl", "/member/terminalWebControl"})
public class TerminalWebController extends BaseController{

	private static final Logger logger = Logger.getLogger(TerminalWebController.class);

	/** C3 */
	private final TerminalType C3 = TerminalType.C3;

	/** HL031 */
	private final TerminalType HL031 = TerminalType.HL031;

	@Autowired
	private ITerminalService tService;

	@Autowired
	private IContactsService contactService;

	@Autowired
	private SMSService smsService;
	
	/** 短信的模板 */
	SMSCommand smsCommand = SMSCommand.LOCATION;
	
	
	/**
	 * 进入用户c系列手环功能页面
	 * @author dachang.luo
	 * @DateTime 2016年7月12日 上午11:59:35
	 * @serverComment 服务注解
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "cseries")
	public String cseries(HttpServletRequest request, Model model) {		
		// 判断是否已绑定了C3设备
		
		MemberSharingData memberSharingData = getMemberSharingData(); 
		
		if(!memberSharingData.isBindC3()){
			logger.info("用户并未绑定C3系列设备");
			model.addAttribute("error", "用户并未绑定C3系列设备");
			return "com/lifeshs/member/error/error";
		}
		return "com/lifeshs/member/Cseries";
	}
	
	/**
	 * 获取用户C系列手环监控频率
	 * @author dachang.luo
	 * @DateTime 2016年7月12日 下午2:17:48
	 * @serverComment 服务注解
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getMonitorRate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson getMonitorRate(HttpServletRequest request) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取监控频率式失败";
		resObject.setMsg(msg);
		LoginUser loginUser =getLoginUser();
		Map<String, Object> attribute = new HashMap<String, Object>();
		if (loginUser == null) {
			resObject.setMsg("登录信息已过期，请重新登录");
			logger.info("登录信息已过期，要求用户重新登录");
			return resObject;
		}
		if (!tService.isUserBindSameTerminal(loginUser.getId(), C3.getName())) {
			resObject.setMsg("用户未绑定C3手环");
			logger.info("用户未绑定C3手环");
			return resObject;
		}
		List<TUserTerminal> terminals = tService.geTUserTerminals(loginUser.getId());
		TUserTerminal terminal = new TUserTerminal();
		for (TUserTerminal tUserTerminal : terminals) {
			if (tUserTerminal.getTerminalType().equals("C3")) {
				terminal = tUserTerminal;
			}
		}
		attribute.put("heartFrequency", terminal.getHeartFrequency());
		attribute.put("locationFrequency", terminal.getLocationFrequency());
		/*attribute.put("autoFrequency70", terminal.getAutoFrequency70());
		attribute.put("autoFrequency50", terminal.getAutoFrequency50());
		attribute.put("autoFrequency30", terminal.getAutoFrequency30());*/
		resObject.setAttributes(attribute);
		msg = "获取监控频率成功";
		resObject.setMsg(msg);
		resObject.setSuccess(true);
		return resObject;
	}
	
	/**
	 * C3设置监控频率
	 * @author dachang.luo
	 * @DateTime 2016年7月12日 下午7:48:30
	 * @serverComment 服务注解
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "setMonitorRate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson setMonitorRate(HttpServletRequest request) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "设置监控频率式失败";
		resObject.setMsg(msg);
		LoginUser loginUser =getLoginUser();
		if (loginUser == null) {
			resObject.setMsg("登录信息已过期，请重新登录");
			logger.info("登录信息已过期，要求用户重新登录");
			return resObject;
		}
		int userId = loginUser.getId();
		int heartFrequency = Integer.parseInt(request
				.getParameter("heartFrequency"));
		int locationFrequency = Integer.parseInt(request
				.getParameter("locationFrequency"));
		int autoFrequency70 = Integer.parseInt(request
				.getParameter("autoFrequency70"));
		int autoFrequency50 = Integer.parseInt(request
				.getParameter("autoFrequency50"));
		int autoFrequency30 = Integer.parseInt(request
				.getParameter("autoFrequency30"));

		if (!tService
				.isUserBindSameTerminal(loginUser.getId(), C3.getName())) {
			resObject.setMsg("用户未绑定C3手环");
			logger.info("用户未绑定C3手环");
			return resObject;
		}
		boolean bool = tService.modifyMoniterFrequency(userId,
				C3.getName(), heartFrequency, locationFrequency,
				autoFrequency70, autoFrequency50, autoFrequency30);
		if (!bool) {
			return resObject;
		}
		msg = "设置监控频率成功";
		resObject.setMsg(msg);
		resObject.setSuccess(true);
		return resObject;
	}
	
	/**
	 * 获取用户C系列手环运行模式
	 * @author dachang.luo
	 * @DateTime 2016年7月12日 上午11:59:13
	 * @serverComment 服务注解
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getRunMode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson getRunMode() throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取运行模式失败";
		resObject.setMsg(msg);
		int userId = getLoginUser().getId();
		Map<String, Object> attribute = new HashMap<String, Object>();
		
		if (!tService.isUserBindSameTerminal(userId, C3.getName())) {
			resObject.setMsg("用户未绑定C3手环");
			logger.info("用户未绑定C3手环");
			return resObject;
		}
		Map<String, Object> mode = tService.getTerminalOperationMode(userId, C3.getName());
		attribute.put("mode", mode);
		resObject.setAttributes(attribute);
		msg = "获取运行模式成功";
		resObject.setMsg(msg);
		resObject.setSuccess(true);
		return resObject;
	}
	
	/**
	 *  设置运行模式
	 *  @author dachang.luo 
	 *	@DateTime 2016年7月18日 下午2:27:44
	 *  @serverComment 服务注解
	 *  @param request
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(params = "setRunMode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson setRunMode(HttpServletRequest request) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "设置运行模式失败";
		resObject.setMsg(msg);
		LoginUser loginUser =getLoginUser();
		int userId = getLoginUser().getId();
		if (loginUser == null) {
			resObject.setMsg("登录信息已过期，请重新登录");
			return resObject;
		}
		if (!tService
				.isUserBindSameTerminal(userId, C3.getName())) {
			resObject.setMsg("用户未绑定C3手环");
			logger.info("用户未绑定C3手环");
			return resObject;
		}
		int mode = Integer.parseInt(request.getParameter("radMode"));
		String week = request.getParameter("week");
		TUserOperationDetail detail=new TUserOperationDetail();
		detail.setOperationMode(mode);
		if(mode==6){
			String start1=request.getParameter("start1");
			String end1=request.getParameter("end1");
			String start2=request.getParameter("start2");
			String end2=request.getParameter("end2");
			String start3=request.getParameter("start3");
			String end3=request.getParameter("end3");
			String enable = request.getParameter("enable");
			
			String[] wks=week.split(",");
			String[] weeks=new String[]{"0","0","0","0","0","0","0"};
			for(int i=0;i<wks.length;i++){
				switch (wks[i]) {
				case "一":
					weeks[0]="1";
					break;
				case "二":
					weeks[1]="1";
					break;
				case "三":
					weeks[2]="1";
					break;
				case "四":
					weeks[3]="1";
					break;
				case "五":
					weeks[4]="1";
					break;
				case "六":
					weeks[5]="1";
					break;
				case "日":
					weeks[6]="1";
					break;		
				}
			}
			String str="";
			for(int i=0;i<weeks.length;i++){
				str+=weeks[i];
			}
			detail.setStartTime1(start1);
			detail.setStartTime2(start2);
			detail.setStartTime3(start3);
			detail.setEndTime1(end1);
			detail.setEndTime2(end2);
			detail.setEndTime3(end3);
			detail.setEnable(enable);
			detail.setWeeks(str);
		}
		
		
		boolean bool=tService.modTerminalOperationMode(loginUser.getId(), C3.getName(), detail);
		if(!bool){
			return resObject;
		}
		msg = "设置运行模式成功";
		resObject.setMsg(msg);
		resObject.setSuccess(true);
		return resObject;
	}
	
	/**
	 * 获取C3定位记录信息
	 * @return
	 */
	@RequestMapping(params = "getSportRecord")
	public @ResponseBody AjaxJson getSportLocationRecord(@RequestParam Integer next){
		AjaxJson ajaxJson=new AjaxJson();
		ajaxJson.setObj(tService.getSportLocationRecord(getLoginUser().getId(),next,10));
		return ajaxJson;
	}

	/**
	 * <p>
	 * 获取GPS坐标点
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月12日 下午3:14:07
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "gps", method = RequestMethod.GET)
	public @ResponseBody AjaxJson gps(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取用户经纬度信息失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			GpsModel gps = null;
			try {
				gps = tService.getGps(userId, type);
			} catch (Exception e) {
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			if (gps == null) {
				// 如果查找不到定位信息，默认定位到天安门
				gps = new GpsModel();
				gps.setLat(39.915122);
				gps.setLng(116.404026);
				gps.setAddress("天安门");
			}
			resObject.setSuccess(true);
			resObject.setMsg("获取经纬度信息成功");
			resObject.setObj(gps);
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 经纬度转换地址
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月19日 下午2:34:59
	 *
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @return
	 */
	@RequestMapping(params = "address", method = RequestMethod.GET)
	public @ResponseBody AjaxJson address(@RequestParam String lng, @RequestParam String lat) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		if (StringUtils.isNotBlank(lng) && !lng.equals("0") && StringUtils.isNotBlank(lat) && !lat.equals("0")) {
			Map<String, Object> attributes = new HashMap<>();
			String address = BaiduMapAPI.getAddress(lng, lat);
			/**
			 * 获取的address可能为空，需要判断下获取结果
			 */
			if (StringUtils.isNotEmpty(address)) {
				attributes.put("address", address);
				resObject.setSuccess(true);
				resObject.setMsg("获取详细地址成功");
				resObject.setAttributes(attributes);
				return resObject;
			}
			resObject.setMsg("转换失败，请稍后重试");
			return resObject;
		}
		resObject.setMsg("请确保查询的地址有效");
		return resObject;
	}

	/**
	 * <p>
	 * 电子围栏选项卡数据获取
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月18日 下午4:54:00
	 *
	 * @param terminalType
	 *            设备类别 hl031、c3
	 * @return
	 */
	@RequestMapping(params = "electricFences", method = RequestMethod.GET)
	public @ResponseBody AjaxJson electricFences(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取电子围栏信息失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			/**
			 * 1.围栏个数(如果数量为0就不传递电子围栏对象) 2.围栏1的设置参数
			 */
			Map<String, Object> resMap = new HashMap<>();
			List<TUserElectronicFence> fences = tService.getElectronicFences(userId, type);
			/**
			 * 判断获得的fences对象，为了避免空值
			 */
			if (fences == null || fences.size() == 0) {
				resMap.put("size", 0);
				GpsModel gps = tService.getGps(userId, type);
				if (gps == null) {
					// 如果查找不到定位信息，默认定位到天安门
					gps = new GpsModel();
					gps.setLat(39.915122);
					gps.setLng(116.404026);
					gps.setAddress("天安门");
				}
				resMap.put("gps", gps);
			} else {
				resMap.put("size", fences.size());

				// 将围栏编号用List存储后再转换为数组
				List<Integer> numberList = new ArrayList<>();
				for (TUserElectronicFence fence : fences) {
					numberList.add(fence.getNumber());
				}
				Collections.sort(numberList);
				resMap.put("numbers", numberList.toArray());

				resMap.put("fence", fences.get(0));
			}
			resObject.setSuccess(true);
			resObject.setObj(resMap);
			resObject.setMsg("获取电子围栏信息成功");
			return resObject;
		}
		resObject.setMsg(msg + ":找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取电子围栏详细信息
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月19日 上午9:06:30
	 *
	 * @param number
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "electricFence", method = RequestMethod.GET)
	public @ResponseBody AjaxJson electricFence(@RequestParam int number, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取电子围栏详细信息失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			TUserElectronicFence fence = null;
			fence = tService.getElectronicFence(userId, number, type);
			if (fence != null) {
				resObject.setObj(fence);
				resObject.setSuccess(true);
				resObject.setMsg("成功获取电子围栏信息");
				return resObject;
			}
			resObject.setMsg(msg + ":找不到该电子围栏的信息");
			return resObject;
		}
		resObject.setMsg(msg + ":找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 创建一个新的电子围栏
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月19日 上午9:32:30
	 *
	 * @param map
	 *            电子围栏封装对象(包含 terminalType 设备类型)
	 * @return
	 */
	@RequestMapping(params = "electricFence", method = RequestMethod.POST)
	public @ResponseBody AjaxJson addElectricFence(@RequestBody Map<String, Object> map) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "添加电子围栏失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName((String) map.get("terminalType"));

		if (StringUtils.isNotBlank(type)) {
			int fenceNumber = 0;
			// 将map转换为TUserElectronicFence对象
			TUserElectronicFence fence = new TUserElectronicFence();
			fence.setLatitude(Double.valueOf((String) map.get("latitude")));
			fence.setLongitude(Double.valueOf((String) map.get("longitude")));
			fence.setRadius((Integer) map.get("radius"));
			fence.setWarningType((Integer) map.get("warningType"));
			fence.setWarningPhone((String) map.get("phone"));
			fence.setAddress((String) map.get("address"));
			fence.setModifyDate(new Date());
			String startTime1 = (String) map.get("startTime1");
			String endTime1 = (String) map.get("endTime1");
			String startTime2 = (String) map.get("startTime2");
			String endTime2 = (String) map.get("endTime2");
			String startTime3 = (String) map.get("startTime3");
			String endTime3 = (String) map.get("endTime3");

			if (StringUtils.isNotBlank(startTime1) && StringUtils.isNotBlank(endTime1)) {
				fence.setStartTime1(Time.valueOf(startTime1));
				fence.setEndTime1(Time.valueOf(endTime1));
			}
			if (StringUtils.isNotBlank(startTime2) && StringUtils.isNotBlank(endTime2)) {
				fence.setStartTime2(Time.valueOf(startTime2));
				fence.setEndTime2(Time.valueOf(endTime2));
			}
			if (StringUtils.isNotBlank(startTime3) && StringUtils.isNotBlank(endTime3)) {
				fence.setStartTime3(Time.valueOf(startTime3));
				fence.setEndTime3(Time.valueOf(endTime3));
			}

			String enabled = (String) map.get("enabled");
			fence.setEnabled(enabled);
			fence.setMapType("B"); // 百度地图B
			/**
			 * 判断电子围栏是否数量达到上限3 如果返回的结果是0，表示"设备不存在或电子围栏已达上限"
			 * 其它大于0的结果就是最新的电子围栏信息的编号
			 */
			fenceNumber = tService.isElectronicFenceLimited(userId, type);
			if (fenceNumber == 0) {
				resObject.setMsg("设备不存在或电子围栏已达上限");
				return resObject;
			}
			fence.setNumber(fenceNumber);
			try {
				if (!tService.addElectronicFence(userId, type, fence)) {
					resObject.setMsg(msg);
					return resObject;
				}
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			resObject.setSuccess(true);
			resObject.setMsg("添加电子围栏成功");
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 修改电子围栏设置
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年6月23日 下午5:43:29
	 *
	 * @param map
	 *            电子围栏封装对象(包含terminalType 设备类型)
	 * @return
	 */
	@RequestMapping(params = "modifyElectronicFence", method = RequestMethod.POST)
	public @ResponseBody AjaxJson modifyElectronicFence(@RequestBody Map<String, Object> map) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "修改电子围栏失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName((String) map.get("terminalType"));

		if (StringUtils.isNotBlank(type)) {
			// 将map转换为TUserElectronicFence对象
			TUserElectronicFence fence = new TUserElectronicFence();
			fence.setNumber((Integer) map.get("number"));
			fence.setLatitude(Double.valueOf((String) map.get("latitude")));
			fence.setLongitude(Double.valueOf((String) map.get("longitude")));
			fence.setRadius((Integer) map.get("radius"));
			fence.setWarningType((Integer) map.get("warningType"));
			fence.setWarningPhone((String) map.get("phone"));
			fence.setAddress((String) map.get("address"));
			fence.setModifyDate(new Date());
			String startTime1 = (String) map.get("startTime1");
			String endTime1 = (String) map.get("endTime1");
			String startTime2 = (String) map.get("startTime2");
			String endTime2 = (String) map.get("endTime2");
			String startTime3 = (String) map.get("startTime3");
			String endTime3 = (String) map.get("endTime3");

			if (StringUtils.isNotBlank(startTime1) && StringUtils.isNotBlank(endTime1)) {
				fence.setStartTime1(Time.valueOf(startTime1));
				fence.setEndTime1(Time.valueOf(endTime1));
			}
			if (StringUtils.isNotBlank(startTime2) && StringUtils.isNotBlank(endTime2)) {
				fence.setStartTime2(Time.valueOf(startTime2));
				fence.setEndTime2(Time.valueOf(endTime2));
			}
			if (StringUtils.isNotBlank(startTime3) && StringUtils.isNotBlank(endTime3)) {
				fence.setStartTime3(Time.valueOf(startTime3));
				fence.setEndTime3(Time.valueOf(endTime3));
			}

			String enabled = (String) map.get("enabled");
			fence.setEnabled(enabled);

			try {
				if (!tService.modifyElectronicFence(userId, type, fence)) {
					resObject.setMsg(msg);
					return resObject;
				}
				resObject.setSuccess(true);
				resObject.setMsg("修改电子围栏成功");
				return resObject;
			} catch (Exception e) {
				resObject.setMsg(msg);
				return resObject;
			}
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 删除电子围栏
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年6月23日 下午5:43:32
	 *
	 * @param number
	 *            围栏编号
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "delElectronicFence", method = RequestMethod.POST)
	public @ResponseBody AjaxJson delElectronicFence(@RequestParam int number, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "删除电子围栏失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			try {
				if (!tService.delElectronicFence(userId, type, number)) {
					resObject.setMsg("找不到该设备或电子围栏的信息");
					return resObject;
				}
				resObject.setSuccess(true);
				resObject.setMsg("删除电子围栏成功");
				return resObject;
			} catch (Exception e) {
				resObject.setMsg(msg);
				return resObject;
			}
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取轨迹的详细数据
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月22日 下午3:23:19
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "orbitDetail", method = RequestMethod.GET)
	public @ResponseBody AjaxJson orbitDetail(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取轨迹工具条数据获取失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			List<TUserMonitorTrack> tracks = null;
			try {
				tracks = tService.getMonitorTrack(userId, type);
			} catch (Exception e) {
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			if (tracks == null) {
				resObject.setMsg(msg + ":找不到该设备");
				return resObject;
			}
			resObject.setSuccess(true);
			resObject.setMsg("成功获取轨迹回放数据");
			/** 封装tracks对象到js，然后根据其长度生成"时间段"按钮 以及遍历tracks对象，将数据保存到侧边栏中，用于展示 */
			resObject.setObj(tracks);
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取运动轨迹
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月22日 上午9:54:12
	 *
	 * @param number
	 *            轨迹编号
	 * @param date_to_s
	 *            指定日期
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "orbit2", method = RequestMethod.GET)
	public @ResponseBody AjaxJson orbit2(@RequestParam int number,
			@RequestParam(value = "date", required = false) String date_to_s, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取运动轨迹失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			/**
			 * 数据格式转换 如果date_to_s不为空，就使用原来的数值 如果数据为空，就获取现在的日期
			 */
			date_to_s = StringUtils.isBlank(date_to_s) ? DateTimeUtilT.date(new Date()) : date_to_s;
			List<GpsModel> models = tService.getOrbit(userId, type, number, date_to_s);
			if (models == null || models.size() == 0) {
				resObject.setObj(new Gps(39.915122, 116.404026));
				resObject.setMsg(msg + ":定位数据不足");
				return resObject;
			}
			if (models.size() == 1) {
				GpsModel model = models.get(0);
				resObject.setObj(new GpsModel(model.getLat(), model.getLng()));
				resObject.setMsg(msg + ":定位数据不足");
				return resObject;
			}
			resObject.setMsg("成功获取运动轨迹");
			resObject.setSuccess(true);
			resObject.setObj(models);
			logger.info("用户id(" + userId + ")获取第" + number + "运动轨迹:起点坐标(lng:" + models.get(0).getLng() + ",lat:"
					+ models.get(0).getLat() + ")，终点坐标(lng:" + models.get(1).getLng() + ",lat:" + models.get(1).getLat()
					+ ")");
			return resObject;
		}
		resObject.setMsg(msg + ":找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 修改运动轨迹
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月22日 下午5:29:41
	 *
	 * @param params
	 *            (包含terminalType 设备类型)
	 * @return
	 */
	@RequestMapping(params = "modifyOrbit", method = RequestMethod.POST)
	public @ResponseBody AjaxJson modifyOrbit(@RequestBody Map<String, Object> params) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "修改运动轨迹失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName((String) params.get("terminalType"));

		if (StringUtils.isNotBlank(type)) {
			// 解析params参数，将解析的结果保存到TUserMonitorTrack对象中，然后存储到数据库
			String s1 = (String) params.get("s1");
			String s2 = (String) params.get("s2");
			String s3 = (String) params.get("s3");
			String e1 = (String) params.get("e1");
			String e2 = (String) params.get("e2");
			String e3 = (String) params.get("e3");
			boolean enable1 = (Integer) params.get("c1") == 1 ? true : false;
			boolean enable2 = (Integer) params.get("c2") == 1 ? true : false;
			boolean enable3 = (Integer) params.get("c3") == 1 ? true : false;

			List<TUserMonitorTrack> list = new ArrayList<>();
			TUserMonitorTrack track;
			track = new TUserMonitorTrack();
			track.setNumber(1);
			track.setStartTime(Time.valueOf(s1));
			track.setEndTime(Time.valueOf(e1));
			track.setEnabled(enable1);
			list.add(track);
			track = new TUserMonitorTrack();
			track.setNumber(2);
			track.setStartTime(Time.valueOf(s2));
			track.setEndTime(Time.valueOf(e2));
			track.setEnabled(enable2);
			list.add(track);
			track = new TUserMonitorTrack();
			track.setNumber(3);
			track.setStartTime(Time.valueOf(s3));
			track.setEndTime(Time.valueOf(e3));
			track.setEnabled(enable3);
			list.add(track);

			try {
				if (tService.modifyMonitorTrack(userId, type, list)) {
					resObject.setSuccess(true);
					resObject.setMsg("修改成功");
					return resObject;
				}
				resObject.setMsg(msg + ":修改失败");
				return resObject;
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取提醒设置
	 * 
	 * @author dachang.luo
	 * @DateTime 2016年7月13日 下午4:25:33
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getNotices", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getNotices(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		// 获取提醒设置
		List<TUserNotice> notices = tService.getTerminalNotices(userId, type);
		if (notices != null && notices.size() != 0) {
			for (int i = 0; i < notices.size(); i++) {
				String week = notices.get(i).getWeeks();
				String[] weeks = { "一", "二", "三", "四", "五", "六", "日" };
				StringBuffer noticeDay = new StringBuffer();
				char[] c = week.toCharArray();
				for (int j = 0; j < c.length; j++) {
					if (c[j] == '1') {
						noticeDay.append(weeks[j]);
					}
				}
				notices.get(i).setWeeks(noticeDay.toString());
			}
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("notices", notices);
		resObject.setAttributes(attributes);

		resObject.setMsg("获取提醒设置成功");
		resObject.setSuccess(true);
		return resObject;
	}

	/**
	 * <p>
	 * 获取一个提醒的详细内容
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月29日 上午11:23:39
	 *
	 * @param noticeId
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "notice", method = RequestMethod.GET)
	public @ResponseBody AjaxJson notice(@RequestParam int noticeId, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取详细信息失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		TUserNotice notice = tService.getTerminalNotice(userId, type, noticeId);

		if (notice == null) {
			resObject.setMsg(msg + ":找不到该纪录");
			return resObject;
		}
		resObject.setSuccess(true);
		resObject.setMsg("获取成功");
		resObject.setObj(notice);
		return resObject;
	}

	/**
	 * <p>
	 * 添加提醒
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月28日 下午1:57:27
	 *
	 * @param weeks
	 *            周参数(从星期一到星期日)，0表示关闭，1表示打开，比如：0000001表示星期日开启，其余都关闭
	 * @param time
	 *            提醒时间，需要注意格式限制为00:00
	 * @param content
	 *            提醒内容
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "addNotice", method = RequestMethod.POST)
	public @ResponseBody AjaxJson addNotice(@RequestParam String weeks, @RequestParam String time,
			@RequestParam String content, @RequestParam Double intervalM, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "添加提醒失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (!isWellFormed(weeks, "weeks")) {
			resObject.setMsg(msg + ":日期不符合要求");
			return resObject;
		}
		if (!isWellFormed(time, "time")) {
			resObject.setMsg(msg + ":提醒时间格式不符合要求");
			return resObject;
		}
		if (!isWellFormed(content, "noticeContent")) {
			resObject.setMsg(msg + ":提醒内容长度不能超过10个字符");
			return resObject;
		}
		if (tService.isNoticeLimited(userId, type)) {
			resObject.setMsg(msg + ":提醒设置已被限制");
			return resObject;
		}
		try {
			if (!tService.addTerminalNotice(userId, type, weeks, time, content, intervalM)) {
				resObject.setMsg(msg + "添加失败");
				return resObject;
			}
		} catch (Exception e) {
			resObject.setMsg(msg + ":" + e.getMessage());
			return resObject;
		}
		resObject.setMsg("添加成功");
		resObject.setSuccess(true);
		return resObject;
	}

	/**
	 * <p>
	 * 转换提醒勾选状态
	 * <p>
	 * 如果是已勾选状态，就改为取消勾选
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月28日 下午5:53:28
	 *
	 * @param noticeId
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "toggleCheckNotice", method = RequestMethod.POST)
	public @ResponseBody AjaxJson toggleCheckNotice(@RequestParam int noticeId, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "修改提醒状态失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (!tService.toggleTerminalNotice(userId, type, noticeId)) {
			resObject.setMsg(msg);
			return resObject;
		}
		resObject.setMsg("修改成功");
		resObject.setSuccess(true);
		return resObject;
	}

	/**
	 * <p>
	 * 修改提醒
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月29日 下午4:22:49
	 *
	 * @param weeks
	 *            日期
	 * @param time
	 *            时间
	 * @param content
	 *            内容
	 * @param noticeId
	 *            id
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "modifyNotice", method = RequestMethod.POST)
	public @ResponseBody AjaxJson modifyNotice(@RequestParam String weeks, @RequestParam String time,
			@RequestParam String content, @RequestParam Double intervalM, @RequestParam int noticeId,
			@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "修改提醒失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (!tService.modTerminalNotice(userId, type, weeks, time, content, intervalM, null, noticeId)) {
			resObject.setMsg(msg);
			return resObject;
		}
		resObject.setSuccess(true);
		resObject.setMsg("修改成功");
		return resObject;
	}

	/**
	 * <p>
	 * 删除提醒
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月29日 上午9:50:54
	 *
	 * @param noticeId
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "delNotice", method = RequestMethod.POST)
	public @ResponseBody AjaxJson delNotice(@RequestParam int noticeId, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "删除提醒失败";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (!tService.delTerminalNotice(userId, noticeId, type)) {
			resObject.setMsg(msg);
			return resObject;
		}
		resObject.setMsg("删除成功");
		resObject.setSuccess(true);
		return resObject;
	}

	/**
	 * <p>
	 * 获取设备号码
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月1日 上午11:59:20
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "getTerminalMobile", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getTerminalMobile(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取设备号码失败";
		resObject.setMsg(msg);
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			// 获取用户设备信息
			List<TUserTerminal> terminals = tService.geTUserTerminals(userId);

			if (terminals == null || terminals.size() == 0) {
				resObject.setMsg(msg + ":找不到该设备");
				return resObject;
			}

			TUserTerminal terminal = null;
			for (int i = 0; i < terminals.size(); i++) {
				if (terminals.get(i).getTerminalType().equals(type)) {
					terminal = terminals.get(i);
					break;
				}
			}
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("terminalImei", terminal.getImei());
			attributes.put("terminalMobile", terminal.getMobile());
			resObject.setAttributes(attributes);
			msg = "获取设备号码成功";
			resObject.setMsg(msg);
			resObject.setSuccess(true);
			return resObject;
		}
		resObject.setMsg(msg + ":找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 修改设备的手机号码
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月3日 下午5:00:07
	 *
	 * @param mobile
	 * @param terminalType
	 * @return
	 */
	@RequestMapping(params = "modifyTerminalNumber", method = RequestMethod.POST)
	public @ResponseBody AjaxJson modifyTerminalNumber(@RequestParam String mobile, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "修改失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {

			if (!Toolkits.verifyPhone(mobile)) {
				resObject.setMsg(msg + ":手机号码格式错误");
				return resObject;
			}

			try {
				if (!tService.setTerminalMobile(userId, type, mobile)) {
					resObject.setMsg(msg);
					return resObject;
				}
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			resObject.setSuccess(true);
			resObject.setMsg("修改成功");
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取黑名单列表
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月1日 下午12:01:22
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "getBlackLists", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getBlackLists(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取黑名单失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			Map<String, Object> attribute = new HashMap<String, Object>();
			// 获取黑名单
			List<TUserBlackWhiteList> balckLists = tService.getDeviceBlackList(userId, type);
			attribute.put("balckLists", balckLists);
			resObject.setAttributes(attribute);
			msg = "获取黑名单成功";
			resObject.setMsg(msg);
			resObject.setSuccess(true);
			return resObject;
		}
		resObject.setMsg(msg + ":找不到设备");
		return resObject;
	}

	/**
	 * <p>
	 * 添加黑名单
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月2日 下午3:36:28
	 *
	 * @param terminalType
	 *            设备类型
	 * @param mobile
	 * @return
	 */
	@RequestMapping(params = "addBlackList", method = RequestMethod.POST)
	public @ResponseBody AjaxJson addBlackList(@RequestParam String terminalType, @RequestParam String mobile) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "添加黑名单失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {

			if (!Toolkits.verifyPhone(mobile)) {
				resObject.setMsg(msg + ":手机号码格式错误");
				return resObject;
			}

			if (tService.isBlackListMobileExist(userId, mobile, type)) {
				resObject.setMsg(msg + ":该号码已存在");
				return resObject;
			}

			if (tService.isBlackListLimited(userId, type)) {
				resObject.setMsg(msg + ":黑名单已达上限8条");
				return resObject;
			}

			try {
				if (!tService.addBlackList(userId, mobile, "", type)) {
					resObject.setMsg(msg);
					return resObject;
				}
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			resObject.setSuccess(true);
			resObject.setMsg("添加成功");
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 删除黑名单
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月1日 下午12:08:00
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(params = "deleteBlackList", method = RequestMethod.POST)
	public @ResponseBody AjaxJson deleteBlackList(@RequestParam String terminalType, @RequestParam int blackListId) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "删除黑名单失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			try {
				if (!tService.delBlackList(userId, blackListId, type)) {
					resObject.setMsg(msg);
					return resObject;
				}
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			msg = "删除黑名单成功";
			resObject.setMsg(msg);
			// logger.info(msg);
			resObject.setSuccess(true);
			return resObject;
		}
		resObject.setMsg(msg + ":找不到设备");
		return resObject;
	}

	/**
	 * <p>
	 * 获取实时监听页面的数据
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月3日 下午2:20:21
	 *
	 * @param terminalType
	 * @return
	 */
	@RequestMapping(params = "monitors", method = RequestMethod.GET)
	public @ResponseBody AjaxJson monitors(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取数据失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);
		if (StringUtils.isNotBlank(type)) {
			List<Map<String, Object>> resList = new ArrayList<>();
			List<TUserContacts> contacts = contactService.findAllContacts(userId);
			Integer monitorContactId = tService.getTerminal(userId, type).getMonitorContactId();
			if (contacts != null && contacts.size() > 0) {
				for (TUserContacts contact : contacts) {
					Map<String, Object> map = new HashMap<>();
					map.put("name", contact.getName());
					map.put("number", contact.getContactNumber());
					String type_tmp = "未知类型";
					if (contact.getSos() == 1) {
						type_tmp = "预警号码";
					}
					if (contact.getFamily() == 1) {
						type_tmp = "亲情号码";
					}
					if (contact.getSos() == 1 && contact.getFamily() == 1) {
						type_tmp = "预警号码,亲情号码";
					}
					map.put("type", type_tmp);
					if (monitorContactId != null && monitorContactId.equals(contact.getId())) {
						map.put("selected", true);
					} else {
						map.put("selected", false);
					}
					resList.add(map);
				}
			}
			resObject.setSuccess(true);
			resObject.setObj(resList);
			resObject.setMsg("获取成功");
			return resObject;
		}
		resObject.setMsg(msg + ":找不到该设备");
		return resObject;
	}

	/**
	 * <p>
	 * 修改监听号码
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年8月3日 下午4:22:34
	 *
	 * @param number
	 *            号码
	 * @param terminalType
	 * @return
	 */
	@RequestMapping(params = "modifyMonitor", method = RequestMethod.POST)
	public @ResponseBody AjaxJson modifyMonitor(@RequestParam String number, @RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "监听失败";
		int userId = getLoginUser().getId();
		String type = getTerminalTypeName(terminalType);

		if (StringUtils.isNotBlank(type)) {
			try {
				if (!tService.modifyTerminalMonitorContactId(userId, type, number)) {
					resObject.setMsg(msg);
					return resObject;
				}
			} catch (Exception e) {
				// logger.error(msg + ":" + e.getMessage());
				resObject.setMsg(msg + ":" + e.getMessage());
				return resObject;
			}
			resObject.setSuccess(true);
			resObject.setMsg("监听成功");
			return resObject;
		}
		resObject.setMsg("找不到该设备");
		return resObject;
	}

	@RequestMapping(params = "getAllContact", method = RequestMethod.POST)
	public @ResponseBody AjaxJson getAllContact() throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);

		int userId = getLoginUser().getId();
		List<Map<String, Object>> list = contactService.getAllContactsMap(userId);
		// TODO 可能需要解除注释
//		int terminalTypeValue = 2; 2代表C3?
		
		int csV = ContactTerminalType.C3_SOS.value();
		int cfV = ContactTerminalType.C3_FAMILY.value();
		
		for (int i = 0; i < list.size(); i++) {
			
			int contactType = (int) list.get(i).get("contactType");
			
			if ((contactType & csV) == csV) {
				list.remove(i);
				break;
			}
			
			if ((contactType &cfV) == cfV) {
				list.remove(i);
				break;
			}
			
//			if (list.get(i).get("isSOS") != null) {
//				if (list.get(i).get("isSOS").equals(true)) {
//					list.remove(i);
//
//				}
//			}
			// TODO  可能需要解除注释  移除C3号码?
//			if ((terminalTypeValue | contactType) == contactType) {
//				list.remove(i);
//			}
		}
		resObject.setObj(list);
		resObject.setSuccess(true);
		return resObject;
	}

	@RequestMapping(params = "addFamily", method = RequestMethod.POST)
	public @ResponseBody AjaxJson addFamily(@RequestBody Map<String, Object> map) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);

		int contactId = Integer.parseInt(String.valueOf(map.get("contactId")));
		String terminalType = (String) map.get("terminalType");
		String name = (String) map.get("name");
		String contactNumber = (String) map.get("contactNumber");
//		boolean isSOS = Boolean.parseBoolean((String) map.get("isSOS"));
		int contactTypeValue = 0;
//		for (ContactTerminalType terminal : ContactTerminalType.values()) {
//			if (terminal.getName().equals(terminalType)) {
//				terminalTypeValue = terminal.value();
//			}
//		}
		
		if (terminalType.contains("C3")) {
			contactTypeValue = ContactTerminalType.C3_FAMILY.value();
		}
		if (terminalType.contains("Health")) {
			contactTypeValue = ContactTerminalType.HEALTH_PACKAGE_FAMILY.value();
		}
		
		int userId = getLoginUser().getId();

		TUserContacts oldContacts = contactService.getContactsById(contactId);
		contactTypeValue |= oldContacts.getContactType();

		TUserContacts userContact = new TUserContacts();
		userContact.setUserId(userId);
		userContact.setContactNumber(contactNumber);
		userContact.setId(contactId);
//		userContact.setIsSOS(isSOS);
//		userContact.setIsFamily(true);
		userContact.setName(name);
		userContact.setContactType(contactTypeValue);

		contactService.modifyContacts(userContact);
		resObject.setSuccess(true);
		return resObject;
	}

	@RequestMapping(params = "getFamilyList", method = RequestMethod.POST)
	public @ResponseBody AjaxJson getFamilyList() throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);

		int userId = getLoginUser().getId();
		List<TUserContacts> list = contactService.findFamilyByTerminalType(userId, "C3");
		resObject.setObj(list);
		resObject.setSuccess(true);
		return resObject;
	}

	@RequestMapping(params = "deleteFamily", method = RequestMethod.POST)
	public @ResponseBody AjaxJson deleteFamily(@RequestBody Map<String, Object> map) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);

		int contactId = Integer.parseInt((String) map.get("contactId"));
//		int terminalTypeValue = 2;
		// boolean bool = contactService.deleteContacts(contactId);
		int userId = getLoginUser().getId();
		TUserContacts userContacts = new TUserContacts();
		userContacts.setId(contactId);
		userContacts.setContactType(ContactTerminalType.C3_FAMILY.value());
		userContacts.setUserId(userId);
		boolean bool = contactService.deleteFamily(userContacts);
		resObject.setSuccess(bool);
		return resObject;
	}

	@RequestMapping(params = "getTerminalSosMobile", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getTerminalSosMobile(@RequestParam String terminalType) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取终端预警号码失败";
		resObject.setMsg(msg);
		int userId = getLoginUser().getId();
		TUserContacts userContacts = contactService.findSos(userId, "C3");
		if (userContacts != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("sosMobile", userContacts.getContactNumber());
			resObject.setAttributes(map);
			resObject.setSuccess(true);
			resObject.setMsg("获取终端预警号码成功");
		}
		return resObject;
	}

	@RequestMapping(params = "modifyTerminalSosMobile", method = RequestMethod.GET)
	public @ResponseBody AjaxJson modifyTerminalSosMobile(@RequestParam String terminalType,
			@RequestParam String contactNumber) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "设置终端预警号码失败";
		resObject.setMsg(msg);
		int userId = getLoginUser().getId();
		boolean bool = contactService.modifySos(userId, terminalType, contactNumber);
		if (bool) {
			resObject.setSuccess(true);
			resObject.setMsg("设置终端预警号码成功");
		}
		return resObject;
	}

	@RequestMapping(params = "getDeviceInfo", method = RequestMethod.POST)
	public @ResponseBody AjaxJson getDeviceInfo(@RequestParam String terminalType) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取终端信息失败";
		int userId = getLoginUser().getId();
		TUserTerminal userTerminal = tService.getTerminal(userId, terminalType);
		if (userTerminal != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", userTerminal.getStatus());
			map.put("imei", userTerminal.getImei());
			resObject.setAttributes(map);
			resObject.setSuccess(true);
			msg = "获取终端信息成功";
		}
		resObject.setMsg(msg);
		return resObject;
	}

	@RequestMapping(params = "poweroff", method = RequestMethod.POST)
	public @ResponseBody AjaxJson poweroff(@RequestParam String terminalType) throws Exception {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取终端信息失败";
		int userId = getLoginUser().getId();
		TUserTerminal userTerminal = tService.getTerminal(userId, terminalType);
		int status = userTerminal.getStatus();
		if (status == 1) {
			if (tService.sendPoweroffCommond(userTerminal)) {
				resObject.setSuccess(true);
				msg = "终端将在" + userTerminal.getHeartFrequency() + "秒内关机";
			}
		} else {
			msg = "终端处于离线状态,不可执行关机操作!";
			resObject.setSuccess(false);
		}

		resObject.setMsg(msg);
		return resObject;
	}
	
	@RequestMapping(params = "getCurrentLocation", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getCurrentLocation(@RequestParam String terminalType) {
		AjaxJson resObject = new AjaxJson();
		resObject.setSuccess(false);
		String msg = "获取最新位置失败!";
		int userId = getLoginUser().getId();
		// 切换设备
		String type = getTerminalTypeName(terminalType);
		TUserTerminal userTerminal = tService.getTerminal(userId, type);
		VcodeTerminalType vcodeTerminalType = VcodeTerminalType.USER_PLATFORM;
		String password = userTerminal.getImei().substring(userTerminal.getImei().length() - 6, userTerminal.getImei().length());
		try {
            smsService.send(userTerminal.getUserId(), vcodeTerminalType, userTerminal.getMobile(), smsCommand, false, password);
        } catch (OperationException e) {
            logger.error(e.getMessage());
            resObject.setSuccess(false);
            resObject.setMsg(e.getMessage());
            return resObject;
        } catch (SMSException e) {
            logger.error(e.getMessage());
            resObject.setSuccess(false);
            resObject.setMsg(e.getMessage());
            return resObject;
        }
        cacheService.saveKeyValue(CacheType.LOCATION, userId + "currentLocation", "currentLocation");
        msg = "正向设备发送指令,请等待设备返回数据。。。";
        resObject.setSuccess(true);
		resObject.setMsg(msg);
		return resObject;
	}

	/**
	 * <p>
	 * 切换设备
	 * <p>
	 * 目前能切换的设备有hl031,c3,band
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年7月29日 下午5:16:52
	 *
	 * @param terminalType
	 *            设备类型
	 * @return
	 */
	private String getTerminalTypeName(String terminalType) {

		MemberSharingData memberSharingData = getMemberSharingData();

		String type = null;
		switch (terminalType) {
		case "hl031":
			if (memberSharingData.isBindHL031()) {
				type = HL031.getName();
			}
			break;
		case "c3":
			if (memberSharingData.isBindC3()) {
				type = C3.getName();
			}
			break;
		case "band":
			type = null;
		}
		return type;
	}

	/**
	 * <p>
	 * 校验数据格式
	 * 
	 * @author yuhang.weng
	 * @DateTime 2016年6月20日 下午3:31:35
	 *
	 * @param data
	 *            数据
	 * @param checkName
	 *            校验名称[weeks,time,status,imei]
	 * @return
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
			// 要求格式为 00:00到23:59
			String regex_time = "^(([0-1][0-9])|(2[0-3])):([0-5][0-9])$";
			Pattern p_time = Pattern.compile(regex_time);
			Matcher m_time = p_time.matcher(data);
			return m_time.matches();
		case "noticeContent":
			int length = 0;
			String chinese = "[\u4e00-\u9fa5]";
			for (int i = 0; i < data.length(); i++) {
				String tmp = data.substring(i, i + 1);
				if (tmp.matches(chinese)) {
					length += 2;
				} else {
					length += 1;
				}
			}
			// 长度不能超过10
			if (length > 20)
				return false;
			else
				return true;
		}
		return false;
	}
}
