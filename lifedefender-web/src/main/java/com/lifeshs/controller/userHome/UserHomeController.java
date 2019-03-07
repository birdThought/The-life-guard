package com.lifeshs.controller.userHome;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.TerminalType;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.entity.device.TSportLocation;
import com.lifeshs.entity.device.TUserTerminal;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.service.device.IDeviceService;
import com.lifeshs.service.device.impl.LocationService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.utils.ListUtil;

/**
 *  版权归
 *  TODO 个人用户主页信息
 *  @author duosheng.mo  
 *  @DateTime 2016年6月17日 下午2:36:56
 */
@Controller
@RequestMapping("/userHomeControl")
public class UserHomeController extends BaseController{
	
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private LocationService location;
	@Autowired
	private ITerminalService iTerminalService;
	
	private final String TERMINAL_TYPE_HL = "HL031";
	
	private final String TERMINAL_TYPE_C3 = "C3";

	/**
	 *  @author zhiguo.lin 
	 *	@DateTime 2016年8月2日 下午4:20:43
	 *  @serverComment 用户主页数据展示
	 *
	 *  @return
	 */
	@RequestMapping(params = "getMeasureLastedData")
	public @ResponseBody AjaxJson getMeasureLastedData() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		AjaxJson resObject = new AjaxJson();
		
		LoginUser user = getLoginUser();
		Integer userId = user.getId();
		
		List<HealthType> healthTypes = new ArrayList<>();
		List<Class<?>> devicesName = new ArrayList<>();
		
		/** 获取健康包数据 */
		MemberSharingData sharingData = getCacheMemberSharingData(userId);
		
		Integer healthProduct = sharingData.getHealthProduct();
		if((healthProduct == null || healthProduct == 0) && !sharingData.isBindC3() && !sharingData.isBindHL031()){
			resObject.setSuccess(false);
			resObject.setMsg("你还没绑定任何设备!");
			resObject.setObj(true);
			return resObject;
		}
		
		if((healthProduct & HealthPackageType.Band.value()) == HealthPackageType.Band.value()){
			//用于识别前台是否显示心率手环模块
			map.put("showHeartRate", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.heartRate);
			// 添加设备查询条件
			devicesName.add(TSportBand.class);
			devicesName.add(TMeasureHeartrate.class);
		}
		
		if((healthProduct & HealthPackageType.BodyFatScale.value()) == HealthPackageType.BodyFatScale.value()){
			//用于识别前台是否显示体脂秤模块
			map.put("showBodyfatscale", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.weight);
			healthTypes.add(HealthType.axungeRatio);
			healthTypes.add(HealthType.WHR);
			healthTypes.add(HealthType.BMI);
			healthTypes.add(HealthType.fatFreeWeight);
			healthTypes.add(HealthType.muscle);
			healthTypes.add(HealthType.moisture);
			healthTypes.add(HealthType.boneWeight);
			healthTypes.add(HealthType.bodyage);
			healthTypes.add(HealthType.baseMetabolism);
			healthTypes.add(HealthType.proteide);
			healthTypes.add(HealthType.visceralFat);
			// 添加设备查询条件
			devicesName.add(TMeasureBodyfatscale.class);
		}
		
		if((healthProduct & HealthPackageType.BloodPressure.value()) == HealthPackageType.BloodPressure.value()){
			//用于识别前台是否显示血压计模块
			map.put("showBloodPressure", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.diastolic);
			healthTypes.add(HealthType.systolic);
			healthTypes.add(HealthType.heartRate);
			// 添加设备查询条件
			devicesName.add(TMeasureBloodpressure.class);
		}
		
		if((healthProduct & HealthPackageType.Lunginstrument.value()) == HealthPackageType.Lunginstrument.value()){
			//用于识别前台是否显示肺活仪模块
			map.put("showLunginstrument", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.vitalCapacity);
			// 添加设备查询条件
			devicesName.add(TMeasureLunginstrument.class);
		}
		
		if((healthProduct & HealthPackageType.Glucometer.value()) == HealthPackageType.Glucometer.value()){
			//用于识别前台是否显示血糖仪模块
			map.put("showGlucometer", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.bloodSugar);
			// 添加设备查询条件
			devicesName.add(TMeasureGlucometer.class);
		}
		
		if((healthProduct & HealthPackageType.Oxygen.value()) == HealthPackageType.Oxygen.value()){
			//用于识别前台是否显示血氧仪模块
			map.put("showOxygen", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.saturation);
			healthTypes.add(HealthType.heartRate);
			// 添加设备查询条件
			devicesName.add(TMeasureOxygen.class);
		}
		
		if((healthProduct & HealthPackageType.Temperature.value()) == HealthPackageType.Temperature.value()){
			//用于识别前台是否显示体温计模块
			map.put("showTemperature", "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.temperature);
			// 添加设备查询条件
			devicesName.add(TMeasureTemperature.class);
		}
		
		Map<String, Object> data = deviceService.getDeviceDataLatestByDateWithDeviceNameAndTerminalType(userId, new Date(), devicesName, TerminalType.APP);
		map.put("APP", data);
		
		/** 获取HL系列手环信息 */
		if(sharingData.isBindHL031()){
			//用于识别前台是否显示HL模块
			map.put(TERMINAL_TYPE_HL, "true");
			// 添加健康标准查询条件
			healthTypes.add(HealthType.diastolic);
			healthTypes.add(HealthType.systolic);
			healthTypes.add(HealthType.heartRate);
			healthTypes.add(HealthType.heartRate);
			healthTypes.add(HealthType.temperature);
			// TODO 心电数据暂无
			
			// 添加设备查询条件
			List<Class<?>> hLDevicesName = new ArrayList<>();
			hLDevicesName.add(TMeasureBloodpressure.class);
			hLDevicesName.add(TSportBand.class);
			hLDevicesName.add(TMeasureTemperature.class);
			hLDevicesName.add(TMeasureHeartrate.class);
			//心电数据暂无
			//hLDevicesName.add(TMeasureEcg.class);
			Map<String, Object> hLData = deviceService.getDeviceDataLatestByDateWithDeviceNameAndTerminalType(userId, new Date(), hLDevicesName, TerminalType.HL031);
			map.put("HL", hLData);
		}
		
		/** 获取C3系列数据信息 */
		if(sharingData.isBindC3()){
			//C3 系列
			List<TSportLocation> sportLocationList= null;
			sportLocationList = location.selectLatestLocation(userId, TERMINAL_TYPE_C3, 5);
			map.put("location", sportLocationList);	
			
			TUserTerminal userTerminal = null;
			userTerminal = iTerminalService.getTerminal(userId, TERMINAL_TYPE_C3);
			map.put("mobile", userTerminal.getMobile());
			//用于识别前台是否显示C3模块
			map.put(TERMINAL_TYPE_C3, "true");
		}
		
		
		/** 查询健康标准值数据 */
		healthTypes = ListUtil.removeRepeatElement(healthTypes, HealthType.class);	// 移除重复项
		Map<String, Object> healthStandardValues = deviceService.getHealthStandardValueByHealthType(userId, healthTypes);
		map.put("healthStandard", healthStandardValues);
		
		/** 查询HL的数据 */
		
		/** 查询C的数据 */
		
		
		resObject.setAttributes(map);
		
		return resObject;
	}
	
	/**
	 *  @author zhiguo.lin 
	 *	@DateTime 2016年9月10日 下午4:35:54
	 *  @serverComment 查询健康标准值
	 *
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(params = "getHealthStandardValue", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getHealthStandardValue() throws Exception{
		
		Integer userId = getLoginUser().getId();
		
		List<Class<?>> classNames = new ArrayList<>();
		classNames.add(TSportBand.class);
		classNames.add(TMeasureBodyfatscale.class);
		classNames.add(TMeasureBloodpressure.class);
		classNames.add(TMeasureLunginstrument.class);
		classNames.add(TMeasureGlucometer.class);
		classNames.add(TMeasureOxygen.class);
		classNames.add(TMeasureTemperature.class);
		Map<String, Object> data = deviceService.getDeviceDataLatestByDateWithDeviceNameAndTerminalType(userId, new Date(), classNames, TerminalType.APP);
		return data;
	}
}
