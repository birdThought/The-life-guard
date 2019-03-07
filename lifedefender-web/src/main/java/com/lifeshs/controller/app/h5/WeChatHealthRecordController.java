package com.lifeshs.controller.app.h5;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeshs.common.constants.app.Diet;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.app.healthPackage.HealthPackage;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.controller.common.BaseController;
import com.lifeshs.entity.device.TMeasureBloodlipid;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.entity.device.TMeasureHeartrate;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.entity.device.TMeasureOxygen;
import com.lifeshs.entity.device.TMeasureTemperature;
import com.lifeshs.entity.device.TMeasureUa;
import com.lifeshs.entity.device.TMeasureUran;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.healthDevice.BloodLipidDTO;
import com.lifeshs.pojo.healthDevice.EcgDTO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.healthDevice.EcgHistoryDTO;
import com.lifeshs.pojo.healthDevice.UaDTO;
import com.lifeshs.pojo.healthDevice.UranDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.DietDetail;
import com.lifeshs.service.device.impl.Band;
import com.lifeshs.service.device.impl.BloodLipid;
import com.lifeshs.service.device.impl.BloodPressure;
import com.lifeshs.service.device.impl.Bodyfatscale;
import com.lifeshs.service.device.impl.Ecg;
import com.lifeshs.service.device.impl.Glucometer;
import com.lifeshs.service.device.impl.HeartRate;
import com.lifeshs.service.device.impl.Lunginstrument;
import com.lifeshs.service.device.impl.MeasureDevice;
import com.lifeshs.service.device.impl.Oxygen;
import com.lifeshs.service.device.impl.Temperature;
import com.lifeshs.service.device.impl.Ua;
import com.lifeshs.service.device.impl.Uran;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.record.ISportService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.MedicalService;
import com.lifeshs.service1.record.PhysicalService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.record.MedicalVO;
import com.lifeshs.vo.record.PhysicalVO;

/**
 * 微信健康数据接口
 * author: wenxian.cai
 * date: 2017/11/9 17:12
 */

@Controller
@RequestMapping("wechat-record")
public class WeChatHealthRecordController extends BaseController{

	final static Logger logger = Logger.getLogger(WeChatHealthRecordController.class);
	final static int MEDICAL_PAGE_SIZE = 8;
	final static String DEVICE_TYPE = "APP";

	@Resource(name = "medicalService")
	private MedicalService medicalService;
	@Resource(name = "physicalService")
	private PhysicalService physicalService;
	@Autowired
	private IRecordService recordService;
	@Autowired
	private ISportService sportService;

	@Autowired
	private Band band;

	@Resource(name = "bloodPressure")
	private BloodPressure bloodPressure;

	@Resource(name = "heartRate")
	private HeartRate heartRate;

	@Resource(name = "lunginstrument")
	private Lunginstrument lunginstrument;

	@Resource(name = "oxygen")
	private Oxygen oxygen;

	@Resource(name = "bodyfatscale")
	private Bodyfatscale bodyfatscale;

	@Resource(name = "glucometer")
	private Glucometer glucometer;

	@Resource(name = "temperature")
	private Temperature temperature;

	@Resource(name = "uran")
	private Uran uran;

	@Resource(name = "ua")
	private Ua ua;

	@Resource(name = "bloodLipid")
	private BloodLipid bloodLipid;

	@Resource
	private Ecg ecg;

	/**
	 * 病历列表界面
	 * author: wenxian.cai
	 * date: 2017/11/9 17:25
	 */
	@RequestMapping(value = "/medical/page", method = RequestMethod.GET)
	public ModelAndView medical () {
		return new ModelAndView("mobile/wechat/medical/medical-record");
	}

	/**
	 * 病历详细
	 * author: wenxian.cai
	 * date: 2017/11/9 17:25
	 */
	@RequestMapping(value = "/medical-details/page", method = RequestMethod.GET)
	public ModelAndView medicalDetails (HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("mobile/wechat/medical/medical-record-details");
		try {
			int medicalId = Integer.parseInt(request.getParameter("medicalId"));
			modelAndView.addObject("medicalId", medicalId);
		} catch (Exception e) {
			return new ModelAndView("mobile/404");
		}
		return modelAndView;
	}

	/**
	 * 体检报告列表界面
	 * author: wenxian.cai
	 * date: 2017/11/9 17:25
	 */
	@RequestMapping(value = "/physical/page", method = RequestMethod.GET)
	public ModelAndView physical () {
		return new ModelAndView("mobile/wechat/physical/physical-record");
	}

	/**
	 * 体检报告详细
	 * author: wenxian.cai
	 * date: 2017/11/9 17:25
	 */
	@RequestMapping(value = "/physical-details/page", method = RequestMethod.GET)
	public ModelAndView physicalDetails (HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("mobile/wechat/physical/physical-record-details");
		try {
			int physicalId = Integer.parseInt(request.getParameter("physicalId"));
			modelAndView.addObject("physicalId", physicalId);
		} catch (Exception e) {
			return new ModelAndView("mobile/404");
		}
		return modelAndView;
	}

	/**
	 * 健康日记列表界面
	 * author: wenxian.cai
	 * date: 2017/11/10 9:58
	 */
	@RequestMapping(value = "/health-diary/page", method = RequestMethod.GET)
	public ModelAndView diet () {
		return new ModelAndView("mobile/wechat/healthdiary/health-diary");
	}

	/**
	 * 健康日记详细界面
	 * author: wenxian.cai
	 * date: 2017/11/10 9:58
	 */
	@RequestMapping(value = "/health-diary-details/page", method = RequestMethod.GET)
	public ModelAndView dietDetails () {
		return new ModelAndView("mobile/wechat/diet-record-details");
	}

	/**
	 * 全部设备健康数据界面
	 * author: wenxian.cai
	 * date: 2017/11/10 10:43
	 */
	@RequestMapping(value = "/all-device/page", method = RequestMethod.GET)
	public ModelAndView allDeviceData () {
		return new ModelAndView("mobile/wechat/device/all-device");
	}

	/**
	 * 血压
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/bloodpressure/page", method = RequestMethod.GET)
	public ModelAndView bloodpressure () {
		return new ModelAndView("mobile/wechat/device/bloodpressure");
	}

	/**
	 * 肺活仪
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/lunginstrument/page", method = RequestMethod.GET)
	public ModelAndView lunginstrument () {
		return new ModelAndView("mobile/wechat/device/lunginstrument");
	}

	/**
	 * 心率手环
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/band/page", method = RequestMethod.GET)
	public ModelAndView band () {
		return new ModelAndView("mobile/wechat/device/band");
	}

	/**
	 * 血氧仪
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/oxygen/page", method = RequestMethod.GET)
	public ModelAndView oxygen () {
		return new ModelAndView("mobile/wechat/device/oxygen");
	}

	/**
	 * 血糖仪
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/glucometer/page", method = RequestMethod.GET)
	public ModelAndView glucometer () {
		return new ModelAndView("mobile/wechat/device/glucometer");
	}

	/**
	 * 体脂秤
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/bodyfatscale/page", method = RequestMethod.GET)
	public ModelAndView bodyfatscale () {
		return new ModelAndView("mobile/wechat/device/bodyfatscale");
	}

	/**
	 * 体温
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/temperature/page", method = RequestMethod.GET)
	public ModelAndView temperature () {
		return new ModelAndView("mobile/wechat/device/temperature");
	}

	/**
	 * 血脂仪
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/bloodlipid/page", method = RequestMethod.GET)
	public ModelAndView bloodlipid () {
		return new ModelAndView("mobile/wechat/device/bloodlipid");
	}

	/**
	 * 尿检
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/uran/page", method = RequestMethod.GET)
	public ModelAndView uran () {
		return new ModelAndView("mobile/wechat/device/uran");
	}

	/**
	 * 尿酸
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/ua/page", method = RequestMethod.GET)
	public ModelAndView ua () {
		return new ModelAndView("mobile/wechat/device/ua");
	}

	/**
	 * 心电
	 * author: wenxian.cai
	 * date: / 11:46
	 */
	@RequestMapping(value = "/ecg/page", method = RequestMethod.GET)
	public ModelAndView ecg () {
		return new ModelAndView("mobile/wechat/device/ecg");
	}






	/**
	 * 病历列表
	 * author: wenxian.cai
	 * date: 2017/11/9 17:21
	 */
	@RequestMapping(value = "/medicals/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listMedical (@PathVariable("page") int page) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = medicalService.listMedical(getLoginUser().getId(), page, MEDICAL_PAGE_SIZE);
		ajaxJson.setObj(paging.getData());
		return ajaxJson;
	}

	/**
	 * 病历详情
	 * author: wenxian.cai
	 * date: 2017/11/9 17:21
	 */
	@RequestMapping(value = "/medical-details", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getMedicalDetails (@RequestParam("medicalId") int medicalId) {
		AjaxJson ajaxJson = new AjaxJson();
		LoginUser loginUser = getLoginUser();
		MedicalVO vo = medicalService.getMedical(medicalId, loginUser.getId());
		MemberSharingData memberSharingData = getMemberSharingData();
		Map<String, Object> user = new HashMap();
		user.put("userName", memberSharingData.getUserName());
		user.put("realName", memberSharingData.getRealName());
		user.put("age", DateTimeUtilT.calculateAge(memberSharingData.getBirthday()));
		user.put("gender", memberSharingData.getSex());
		ajaxJson.setObj(vo);
		ajaxJson.setAttributes(user);
		return ajaxJson;
	}

	/**
	 * 体检报告列表
	 * author: wenxian.cai
	 * date: 2017/11/9 17:21
	 */
	@RequestMapping(value = "/physicals/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listPhysical (@PathVariable("page") int page) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = physicalService.listPhysical(getLoginUser().getId(), page, MEDICAL_PAGE_SIZE);
		ajaxJson.setObj(paging.getData());
		return ajaxJson;
	}

	/**
	 * 体检报告详情
	 * author: wenxian.cai
	 * date: 2017/11/9 17:21
	 */
	@RequestMapping(value = "/physical-details", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getPhysicalDetails (@RequestParam("physicalId") int physicalId) {
		AjaxJson ajaxJson = new AjaxJson();
		PhysicalVO vo = physicalService.getPhysical(physicalId, getLoginUser().getId());
		ajaxJson.setObj(vo);
		return ajaxJson;
	}

	/**
	 * 健康日记列表
	 * author: wenxian.cai
	 * date: 2017/11/10 10:03
	 */
	@RequestMapping(value = "/health-diary/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listDiet (@PathVariable("page") int page) {
		AjaxJson ajaxJson = new AjaxJson();
		List list = recordService.selectDietSplitWithRecordDate(getLoginUser().getId(), page, MEDICAL_PAGE_SIZE);
		List<Map<String, Object>> diets = groupDietWithDate(list);
		ajaxJson.setObj(diets);
		return ajaxJson;
	}

	/**
	 * 健康日记详细
	 * author: wenxian.cai
	 * date: 2017/11/10 10:03
	 */
	@RequestMapping(value = "/health-diary-details/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listDiet (@RequestParam("date") String date) {
		AjaxJson ajaxJson = new AjaxJson();
		List<Map<String, Object>> diets = recordService.selectDietByUserIdWithDate(getLoginUser().getId(), date);
		List<TRecordSport> recordSports = sportService.selectTRecordSportWithDate(getLoginUser().getId(), date);
		Map map = new HashMap();
		map.put("diets", diets);
		map.put("sports", recordSports);
		ajaxJson.setObj(map);
		return ajaxJson;
	}

	/**
     * 获取有健康数据的日期
     * @param date
     * @return
     */
    @RequestMapping(value = "/list-valid-date", method = RequestMethod.GET)
    public @ResponseBody AjaxJson listVilidDate(@RequestParam(value = "date") String date) {
        AjaxJson resObject = new AjaxJson();
        int userId = getLoginUser().getId();
                
        LoginUser user = getLoginUser();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate thisMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastMonth = localDate.minus(1, ChronoUnit.MONTHS).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastTwoMonth = localDate.minus(2, ChronoUnit.MONTHS).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastThreeMonth = localDate.minus(3, ChronoUnit.MONTHS).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastFourMonth = localDate.minus(4, ChronoUnit.MONTHS).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastFiveMonth = localDate.minus(5, ChronoUnit.MONTHS).with(TemporalAdjusters.firstDayOfMonth());
        List<LocalDate> list = new ArrayList();
        list.add(thisMonth);
        list.add(lastMonth);
        list.add(lastTwoMonth);
        list.add(lastThreeMonth);
        list.add(lastFourMonth);
        list.add(lastFiveMonth);
        Map dates = new HashMap();
        for (LocalDate localDate1 : list) {
            try {
                Map<String, Integer> dat = recordService.queryDatesWithData(userId, localDate1.toString(), 1);
                List<Integer> dateList = new ArrayList<>();
                for (String key : dat.keySet()) {
                    dateList.add(Integer.valueOf(key.substring(8, 10)));
                }
                    dates.put(localDate1.toString().substring(0, 7), dateList);
            } catch (DataBaseException e) {
                System.out.println("获取会员有效日期失败");
            }


        }
        Map map = new HashMap();
        map.put("date", dates);
        resObject.setAttributes(map);
        return resObject;
    }
	
	
	/**
	 * 获取全部设备健康数据
	 * author: wenxian.cai
	 * date: 2017/11/10 10:41
	 */
	@RequestMapping(value = "/all-device", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 getAllDeviceData (@RequestParam("date") String date) {
		AjaxJsonV2 json = new AjaxJsonV2();
		List<Map<String, Object>> list = new ArrayList<>();
		String deviceType = "APP";
		int device = 0; // 存在数据的设备
		int userId = getLoginUser().getId();
		boolean addVitalCapacityScore = false;
		// 判断用户有哪些健康设备
		Map<String, Object> map = new HashMap<>();

		TSportBand sportBand = band.getMeasureLastedData(TSportBand.class, userId, deviceType,
				date);// 运动腕表
		TMeasureHeartrate measureHeartrate = heartRate.getMeasureLastedData(TMeasureHeartrate.class, userId,
				deviceType, date); // 心率

		if (sportBand == null && measureHeartrate != null) {
			Long status = measureHeartrate.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Band.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureHeartrate.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.heartRate, measureHeartrate.getHeartRate(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Band.value();
		}

		if (sportBand != null) {
			Long status = measureHeartrate == null ? null : measureHeartrate.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Band.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME,date+" 00:00:00");
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.heartRate, measureHeartrate == null ? null : measureHeartrate.getHeartRate(), status, list2);
			getHashMap(HealthPackage.STEP, sportBand.getSteps(), list2);
			getHashMap(HealthPackage.MILEAGE, sportBand.getMileage(), list2);
			getHashMap(HealthPackage.KCAL, sportBand.getKcal(), list2);
			getHashMap(HealthPackage.SHALLOW_DURATION, sportBand.getShallowDuration(), list2);
			getHashMap(HealthPackage.DEEP_DURATION, sportBand.getDeepDuration(), list2);
			getHashMap(HealthPackage.WAKEUP_DURATION, sportBand.getWakeupDuration(), list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Band.value();
		}

		map = new HashMap<>();
		TMeasureBodyfatscale measureBodyfatscale = new TMeasureBodyfatscale();

		measureBodyfatscale.setUserId(userId);
		measureBodyfatscale = bodyfatscale.getMeasureLastedData(TMeasureBodyfatscale.class,
				userId, deviceType, date); //体脂称
		if (measureBodyfatscale != null) {
			Long status = measureBodyfatscale.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.BodyFatScale.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBodyfatscale.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.weight, measureBodyfatscale.getWeight(), status, list2);
			getHashMap(HealthType.axungeRatio, measureBodyfatscale.getAxungeRatio(), status, list2);
			getHashMap(HealthType.WHR, measureBodyfatscale.getWHR(), status, list2);
			getHashMap(HealthType.BMI, measureBodyfatscale.getBMI(), status, list2);
			getHashMap(HealthType.fatFreeWeight, measureBodyfatscale.getFatFreeWeight(), status, list2);
			getHashMap(HealthType.muscle, measureBodyfatscale.getMuscle(), status, list2);
			getHashMap(HealthType.moisture, measureBodyfatscale.getMoisture(), status, list2);
			getHashMap(HealthType.boneWeight, measureBodyfatscale.getBoneWeight(), status, list2);
			getHashMap(HealthType.bodyage, measureBodyfatscale.getBodyage(), status, list2);
			getHashMap(HealthType.baseMetabolism, measureBodyfatscale.getBaseMetabolism(), status, list2);
			getHashMap(HealthPackage.PROTEIDE, measureBodyfatscale.getProteide(), list2);
			getHashMap(HealthType.visceralFat, measureBodyfatscale.getVisceralFat(), status, list2);

			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.BodyFatScale.value();
		}

		map = new HashMap<>();
		TMeasureBloodpressure measureBloodpressure = new TMeasureBloodpressure();

		measureBloodpressure.setUserId(userId);
		measureBloodpressure = bloodPressure.getMeasureLastedData(TMeasureBloodpressure.class,
				userId, deviceType, date);  //血压仪
		if (measureBloodpressure != null) {
			Long status = measureBloodpressure.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.BloodPressure.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBloodpressure.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.diastolic, measureBloodpressure.getDiastolic(), status, list2);
			getHashMap(HealthType.systolic, measureBloodpressure.getSystolic(), status, list2);
			getHashMap(HealthType.heartRate, measureBloodpressure.getHeartRate(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.BloodPressure.value();
		}

		map = new HashMap<>();
		TMeasureLunginstrument measureLunginstrument = new TMeasureLunginstrument();

		measureLunginstrument.setUserId(userId);
		measureLunginstrument = lunginstrument.getMeasureLastedData(TMeasureLunginstrument.class,
				userId, deviceType, date);  //肺活仪
		if (measureLunginstrument != null) {
			Long status = measureLunginstrument.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Lunginstrument.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureLunginstrument.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();

			getHashMap(HealthType.vitalCapacity, measureLunginstrument.getVitalCapacity(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Lunginstrument.value();
		}

		map = new HashMap<>();
		TMeasureGlucometer measureGlucometer = new TMeasureGlucometer(); //血糖仪
		measureGlucometer.setUserId(userId);

		measureGlucometer = glucometer.getMeasureLastedData(TMeasureGlucometer.class, userId,
				deviceType, date);
		if (measureGlucometer != null) {
			Long status = measureGlucometer.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Glucometer.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureGlucometer.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.bloodSugar, measureGlucometer.getBloodSugar(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Glucometer.value();
		}

		map = new HashMap<>();
		EcgDTO ecgDTO = ecg.selectByUserIdAndDate(userId, DateTimeUtilT.date(date)); // 心电仪
        if (ecgDTO != null && !ecgDTO.getDetailList().isEmpty()) {
            EcgDetailDTO ed = ecgDTO.getDetailList().get(0);    // 获取第一条数据
            Integer status = ed.getStatus();
            map.put(Normal.DEVICE_NAME, HealthPackageType.ECG.name());
            map.put(HealthPackage.STATUS, status + "");
            map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(ed.getMeasureDate()));
            List<Map<String, Object>> list2 = new ArrayList<>();
            getHashMap(HealthType.heartRate, ed.getHeartRate(), status.longValue(), list2);
            getHashMap(HealthType.ECG, ed.getImage(), status.longValue(), list2);
            map.put("param", list2);
            list.add(map);

            device |= HealthPackageType.ECG.value();
        }


		map = new HashMap<>();
		TMeasureOxygen measureOxygen = new TMeasureOxygen(); //血氧仪

		measureOxygen.setUserId(userId);
		measureOxygen = oxygen.getMeasureLastedData(TMeasureOxygen.class, userId, deviceType,
				date);
		if (measureOxygen != null) {
			Long status = measureOxygen.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Oxygen.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureOxygen.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.saturation, measureOxygen.getSaturation(), status, list2);
			getHashMap(HealthType.heartRate, measureOxygen.getHeartRate(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Oxygen.value();
		}

		map = new HashMap<>();
		TMeasureTemperature measureTemperature = new TMeasureTemperature(); //体温计

		measureTemperature.setUserId(userId);
		measureTemperature = temperature.getMeasureLastedData(TMeasureTemperature.class, userId,
				deviceType, date);
		if (measureTemperature != null) {
			Long status = measureTemperature.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.Temperature.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureTemperature.getMeasureDate()));
			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.temperature, Math.floor(measureTemperature.getTemperature() * 10) / 10, status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.Temperature.value();
		}

		map = new HashMap<>();

		TMeasureBloodlipid measureBloodLipid = bloodLipid.getMeasureLastedData(TMeasureBloodlipid.class,
				userId, deviceType, date);  //血脂仪

		if (measureBloodLipid != null) {
			Long status = measureBloodLipid.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.BloodLipid.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureBloodLipid.getMeasureDate()));

			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.HDL, measureBloodLipid.getHDL(), status, list2);
			getHashMap(HealthType.LDL, measureBloodLipid.getLDL(), status, list2);
			getHashMap(HealthType.TC, measureBloodLipid.getTC(), status, list2);
			getHashMap(HealthType.TG, measureBloodLipid.getTG(), status, list2);
			getHashMap(HealthType.BloodLipidRation, measureBloodLipid.getBloodLipidRatio(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.BloodLipid.value();
		}

		map = new HashMap<>();

		TMeasureUran measureUran = uran.getMeasureLastedData(TMeasureUran.class, userId, deviceType,
				date); //尿液分析仪

		if (measureUran != null) {
			Long status = measureUran.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.URAN.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureUran.getMeasureDate()));

			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.LEU, measureUran.getLEU(), status, list2);
			getHashMap(HealthType.NIT, measureUran.getNIT(), status, list2);
			getHashMap(HealthType.UBG, measureUran.getUBG(), status, list2);
			getHashMap(HealthType.PRO, measureUran.getPRO(), status, list2);
			getHashMap(HealthType.pH, measureUran.getPH(), status, list2);
			getHashMap(HealthType.BLD, measureUran.getBLD(), status, list2);
			getHashMap(HealthType.SG, measureUran.getSG(), status, list2);
			getHashMap(HealthType.KET, measureUran.getKET(), status, list2);
			getHashMap(HealthType.BIL, measureUran.getBIL(), status, list2);
			getHashMap(HealthType.GLU, measureUran.getGLU(), status, list2);
			getHashMap(HealthType.VC, measureUran.getVC(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.URAN.value();
		}

		map = new HashMap<>();

		TMeasureUa measureUa = ua.getMeasureLastedData(TMeasureUa.class, userId, deviceType,
				date);  //尿检分析仪
		if (measureUa != null) {
			Long status = measureUa.getStatus();
			map.put(Normal.DEVICE_NAME, HealthPackageType.UA.name());
			map.put(HealthPackage.STATUS, status + "");
			map.put(HealthPackage.MEASURE_TIME, DateTimeUtilT.dateTime(measureUa.getMeasureDate()));

			List<Map<String, Object>> list2 = new ArrayList<>();
			getHashMap(HealthType.UA, measureUa.getUA(), status, list2);
			map.put("param", list2);
			list.add(map);

			device |= HealthPackageType.UA.value();
		}

		map = new HashMap<>(1);
		map.put("device",device);
		list.add(map);
		json.setObj(list);
		return json;
	}


	/**
	 * 获取血压计数据
	 * author: wenxian.cai
	 * date: 2017/11/10 14:19
	 */
	@RequestMapping(value = "/bloodpressure", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listBloodPressures (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> bloodPressureList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO =bloodPressure
						.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				bloodPressureList = paginationDTO.getData();
				break;
			case "1": // 日
				bloodPressureList = bloodPressure.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.DAY);
				break;
			case "2": // 周
				bloodPressureList = bloodPressure.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.WEEK);
				break;
			case "3": // 一月
				bloodPressureList = bloodPressure.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				bloodPressureList = bloodPressure.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;

			default:
				break;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(bloodPressureList);
		}
		json.setObj(bloodPressureList);
		return json;
	}

	/**
	 * 肺活仪
	 * author: wenxian.cai
	 * date: 2017/11/10 14:21
	 */
	@RequestMapping(value = "/lunginstrument", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listLunginstruments (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> lunginstrumentList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = lunginstrument
						.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				lunginstrumentList = paginationDTO.getData();
				break;
			case "1":
				lunginstrumentList = lunginstrument.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.DAY);
				break;
			case "2": // 周
				lunginstrumentList = lunginstrument.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.WEEK);
				break;
			case "3": // 一月
				lunginstrumentList = lunginstrument.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				lunginstrumentList = lunginstrument.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;

			default:
				break;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(lunginstrumentList);
		}
		json.setObj(lunginstrumentList);
		return json;
	}

	/**
	 * 心率手环
	 * author: wenxian.cai
	 * date: 2017/11/10 14:29
	 */
	@RequestMapping(value = "/band", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listBands (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> bands = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = band
						.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				bands = paginationDTO.getData();
				break;
			case "1":
				bands = band.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.DAY);
				break;
			case "2": // 周
				bands = band.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.WEEK);
				break;
			case "3": // 一月
				bands = band.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				bands = band.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;

			default:
				break;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(bands);
		}
		json.setObj(bands);
		return json;
	}

	/**
	 * 血氧仪
	 * author: wenxian.cai
	 * date: 2017/11/10 14:30
	 */
	@RequestMapping(value = "/oxygen", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listOxygens (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> oxygens = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = oxygen
						.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				oxygens = paginationDTO.getData();
				break;
			case "1":
				oxygens = oxygen.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.DAY);
				break;
			case "2": // 周
				oxygens = oxygen.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.WEEK);
				break;
			case "3": // 一月
				oxygens = oxygen.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				oxygens = oxygen.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;

			default:
				break;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(oxygens);
		}
		json.setObj(oxygens);
		return json;
	}

	/**
	 * 血糖仪
	 * author: wenxian.cai
	 * date: 2017/11/10 14:32
	 */
	@RequestMapping(value = "/glucometer", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listGlucometers (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page,
									   @RequestParam(value = "measureType", required = false) Integer measureType) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		String dataType = type;
		if (DateTimeUtilT.valiDateTimeWithShortFormat(type)) {
			dataType = "5"; // 判断若为"2016-01"时间格式，则将withDate作为查询条件
		}
		List<Map<String, Object>> glucometerList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		if (measureType != null) { // 加上测量类型条件
			switch (dataType) {
				case "0":
					PaginationDTO<Map<String, Object>> paginationDTO = glucometer
							.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE, measureType);
					glucometerList = paginationDTO.getData();
					break;
				case "1":
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY,
							measureType);
					break;
				case "2": // 周
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK,
							measureType);
					break;
				case "3": // 一月
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE,
							HealthPackage.MONTH, measureType);
					break;
				case "4": // 三月
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE,
							HealthPackage.THREEMONTH, measureType);
					break;
				case "5": // 获取具体年月份全部数据
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE, type,
							measureType);
					break;

				default:
					break;
			}

		} else {
			switch (dataType) {
				case "0":
					PaginationDTO<Map<String, Object>> paginationDTO = glucometer
							.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
					glucometerList = paginationDTO.getData();
					break;
				case "1":
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
					break;
				case "2": // 周
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE,
							HealthPackage.WEEK);
					break;
				case "3": // 一月
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE,
							HealthPackage.MONTH);
					break;
				case "4": // 三月
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE,
							HealthPackage.THREEMONTH);
					break;
				case "5": // 获取具体年月份全部数据
					glucometerList = glucometer.getMeasureDataWithDate(userId, DEVICE_TYPE, type);
					break;

				default:
					break;
			}
		}
		if ("0".equals(dataType)) {
			json.setObj(glucometerList);
			return json;
		}
		// 重新组装list
		List<Map<String, Object>> newList = new ArrayList<>();
		for (int i = 0; i < glucometerList.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<>();
			List<Map<String, Object>> list2 = new ArrayList<>();
			Map<Integer, Map<String, Object>> mapReplaceList = new HashMap<>();

			Timestamp measureDate_old_t = (Timestamp) glucometerList.get(i).get("measureDate");
			String measureDate_old = DateTimeUtilT.date(measureDate_old_t);
			hashMap.put(HealthPackage.MEASUREDATE, DateTimeUtilT.dateTime(measureDate_old_t));

			int listSize = glucometerList.size();
			for (int j = i; j < listSize; j++) {

				Timestamp measureDate_new_t = (Timestamp) glucometerList.get(j).get("measureDate");
				String measureDate_new = DateTimeUtilT.date(measureDate_new_t);

				if (measureDate_old.equals(measureDate_new)) {
					HashMap<String, Object> hashMap2 = new HashMap<>();
					HashMap<String, Object> hashMap3 = new HashMap<>();

					hashMap3.put(HealthPackage.STATUS, glucometerList.get(j).get("status") + "");
					hashMap3.put(HealthPackage.BLOODSUGAR, (glucometerList.get(j).get("bloodSugar") + ""));
					hashMap3.put(HealthPackage.BLOODSUGAR_AREA, glucometerList.get(j).get("bloodSugarArea"));
					hashMap3.put(HealthPackage.MEASURETYPE, glucometerList.get(j).get("measureType") + "");
					hashMap3.put(HealthPackage.MEASUREDATE, DateTimeUtilT.dateTime(measureDate_new_t));
					hashMap2.put(HealthPackage.CHILD_DATA, hashMap3);

					/**
					 * 将数据保存到map中，如果存在相同日期，并且相同的measureType的数据，就会被最后一条顶替掉
					 * 最后保存到这个map中的数据（相同的measureType）就会剩下0条或者1条
					 */
					if (glucometerDataMeasureTypeSingle(type)) {
						mapReplaceList.put((Integer) glucometerList.get(j).get("measureType"), hashMap2);
					} else {
						list2.add(hashMap2);
					}

					glucometerList.remove(j);
					listSize = listSize - 1;
					j--;
				}
			}

			if (glucometerDataMeasureTypeSingle(type)) {
				for (Integer key : mapReplaceList.keySet()) {
					list2.add(mapReplaceList.get(key));
				}
			}

			hashMap.put(HealthPackage.PARAM_DATA, list2);
			newList.add(hashMap);
			i--;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(newList);
		}
		json.setObj(newList);
		return json;
	}


	/**
	 * 体脂秤
	 * author: wenxian.cai
	 * date: 2017/11/10 14:51
	 */
	@RequestMapping(value = "/bodyfatscale", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listBodyfatscales (@RequestParam("type") String type,
										 @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> bodyfatscales = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = bodyfatscale
						.getMeasureDataWithSplit(userId, DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				bodyfatscales = paginationDTO.getData();
				break;
			case "1":
				bodyfatscales = bodyfatscale.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.DAY);
				break;
			case "2": // 周
				bodyfatscales = bodyfatscale.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.WEEK);
				break;
			case "3": // 一月
				bodyfatscales = bodyfatscale.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				bodyfatscales = bodyfatscale.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;

			default:
				break;
		}
		if (!type.equals("0")) {
			java.util.Collections.reverse(bodyfatscales);
		}
		json.setObj(bodyfatscales);
		return json;
	}

	/**
	 * 体温计数据
	 * author: wenxian.cai
	 * date: 2017/11/10 15:18
	 */
	@RequestMapping(value = "/temperature", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listTemperatures (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		String dataType = type;
		if (DateTimeUtilT.valiDateTimeWithFormat(type)) {
			dataType = "5"; // 判断若为时间格式，则将withDate作为查询条件
		}
		List<Map<String, Object>> temperatureList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的体温数据
		switch (dataType) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = temperature.getMeasureDataWithSplit(userId,
						DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				temperatureList = paginationDTO.getData();
				break;
			case "1":
				temperatureList = temperature.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
				break;
			case "2": // 周
				temperatureList = temperature.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				temperatureList = temperature.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.MONTH);
				break;
			case "4": // 三月
				temperatureList = temperature.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;
			case "5":
				// 若传送过来的withDate是日期格式，则将withDate作为查询条件，并获取某一天的全部数据
				temperatureList = temperature.getMeasureDataWithDate(userId, DEVICE_TYPE, type);
				break;
			default:

				break;
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(temperatureList);
		}
		json.setObj(temperatureList);
		return json;
	}

	/**
	 * 血脂仪
	 * author: wenxian.cai
	 * date: 2017/11/10 15:23
	 */
	@RequestMapping(value = "/bloodlipid", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listBloodlipids (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<BloodLipidDTO> bloodLipidDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<BloodLipidDTO> paginationDTO = bloodLipid.getMeasureDataWithSplit(userId,
						DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				bloodLipidDTOList = paginationDTO.getData();
				break;
			case "1":
				bloodLipidDTOList = bloodLipid.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
				break;
			case "2": // 周
				bloodLipidDTOList = bloodLipid.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				bloodLipidDTOList = bloodLipid.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.MONTH);
				break;
			case "4": // 三月
				bloodLipidDTOList = bloodLipid.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;
			default:
				break;
		}

		List<Map<String, Object>> bloodLipidList = new ArrayList<>();
		for (BloodLipidDTO bloodLipidDTO : bloodLipidDTOList) {
			Map<String, Object> bloodLipidMap = new HashMap<>();
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.HDL, bloodLipidDTO.getHDL());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.HDL_AREA, bloodLipidDTO.getHDLArea());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LDL, bloodLipidDTO.getLDL());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LDL_AREA, bloodLipidDTO.getLDLArea());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TC, bloodLipidDTO.getTC());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TC_AREA, bloodLipidDTO.getTCArea());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TG, bloodLipidDTO.getTG());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TG_AREA, bloodLipidDTO.getTGArea());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.MEASURE_DATE, bloodLipidDTO.getMeasureDate());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.STATUS, bloodLipidDTO.getStatus());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.HDL_STATUS, bloodLipidDTO.getHDLStatus());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LDL_STATUS, bloodLipidDTO.getLDLStatus());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TC_STATUS, bloodLipidDTO.getTCStatus());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.TG_STATUS, bloodLipidDTO.getTGStatus());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LAPID_RATIO, bloodLipidDTO.getBloodLipidRatio());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LIPID_RATIO_AREA, bloodLipidDTO.getBloodLipidRatioArea());
			bloodLipidMap.put(com.lifeshs.common.constants.app.healthPackage.BloodLipid.LIPID_RATIO_STATUS, bloodLipidDTO.getBloodLipidRatioStatus());

			bloodLipidList.add(bloodLipidMap);
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(bloodLipidList);
		}
		json.setObj(bloodLipidList);
		return json;
	}

	/**
	 * 尿液分析仪
	 * author: wenxian.cai
	 * date: 2017/11/10 15:25
	 */
	@RequestMapping(value = "/uran", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listUrans (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<UranDTO> uranDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<UranDTO> paginationDTO = uran.getMeasureDataWithSplit(userId, DEVICE_TYPE,
						page, MEDICAL_PAGE_SIZE);
				uranDTOList = paginationDTO.getData();
				break;
			case "1":
				uranDTOList = uran.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
				break;
			case "2": // 周
				uranDTOList = uran.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				uranDTOList = uran.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.MONTH);
				break;
			case "4": // 三月
				uranDTOList = uran.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.THREEMONTH);
				break;
			default:
				break;
		}

		List<Map<String, Object>> uranList = new ArrayList<>();
		for (UranDTO uranDTO : uranDTOList) {
			Map<String, Object> uranMap = new HashMap<>();
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.BIL, uranDTO.getBIL());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.BLD, uranDTO.getBLD());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.GLU, uranDTO.getGLU());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.KET, uranDTO.getKET());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.LEU, uranDTO.getLEU());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.NIT, uranDTO.getNIT());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.PH, uranDTO.getPH());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.PRO, uranDTO.getPRO());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.SG, NumberUtils.format(uranDTO.getSG(), 3));
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.UBG, uranDTO.getUBG());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.VC, uranDTO.getVC());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.BIL_STATUS, uranDTO.getBILStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.BLD_STATUS, uranDTO.getBLDStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.GLU_STATUS, uranDTO.getGLUStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.KET_STATUS, uranDTO.getKETStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.LEU_STATUS, uranDTO.getLEUStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.NIT_STATUS, uranDTO.getNITStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.PH_STATUS, uranDTO.getPhStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.PRO_STATUS, uranDTO.getPROStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.SG_STATUS, uranDTO.getSgStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.UBG_STATUS, uranDTO.getUBGStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.VC_STATUS, uranDTO.getVCStatus());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.PH_AREA, uranDTO.getPHArea());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.SG_AREA, uranDTO.getSGArea());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.MEASURE_DATE, uranDTO.getMeasureDate());
			uranMap.put(com.lifeshs.common.constants.app.healthPackage.Uran.STATUS, uranDTO.getStatus());

			uranList.add(uranMap);
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(uranList);
		}
		json.setObj(uranList);
		return json;
	}

	/**
	 * 尿酸分析仪
	 * author: wenxian.cai
	 * date: 2017/11/10 15:30
	 */
	@RequestMapping(value = "/ua", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listUas (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<UaDTO> uaDTOList = new ArrayList<>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<UaDTO> paginationDTO = ua.getMeasureDataWithSplit(userId, DEVICE_TYPE, page,
						MEDICAL_PAGE_SIZE);
				uaDTOList = paginationDTO.getData();
				break;
			case "1":
				uaDTOList = ua.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
				break;
			case "2": // 周
				uaDTOList = ua.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				uaDTOList = ua.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.MONTH);
				break;
			case "4": // 三月
				uaDTOList = ua.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.THREEMONTH);
				break;
			default:
				break;
		}

		List<Map<String, Object>> uaList = new ArrayList<>();
		for (UaDTO uaDTO : uaDTOList) {
			Map<String, Object> uaMap = new HashMap<>();
			uaMap.put(com.lifeshs.common.constants.app.healthPackage.Ua.UA, uaDTO.getUA());
			uaMap.put(com.lifeshs.common.constants.app.healthPackage.Ua.UA_AREA, uaDTO.getUAArea());
			uaMap.put(com.lifeshs.common.constants.app.healthPackage.Ua.MEASURE_DATE, uaDTO.getMeasureDate());
			uaMap.put(com.lifeshs.common.constants.app.healthPackage.Ua.STATUS, uaDTO.getStatus());
			uaMap.put(com.lifeshs.common.constants.app.healthPackage.Ua.UA_STATUS, uaDTO.getUAStatus());

			uaList.add(uaMap);
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(uaList);
		}
		json.setObj(uaList);
		return json;
	}

	/**
	 * 心电数据
	 * author: wenxian.cai
	 * date: 2017/11/10 15:35
	 */
	@RequestMapping(value = "/ecg", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listEcgs (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<EcgHistoryDTO> hs = new ArrayList<>();// 存放符合日期条件的体温数据
		switch (type) {
			case "0":
				PaginationDTO<EcgHistoryDTO> paginationDTO = ecg.getMeasureDataWithSplit(userId, DEVICE_TYPE,
						page, MEDICAL_PAGE_SIZE);
				hs = paginationDTO.getData();
				break;
			case "2": // 周
				hs = ecg.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				hs = ecg.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.MONTH);
				break;
			case "4": // 三月
				hs = ecg.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.THREEMONTH);
				break;
			default:
				break;
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(hs);
		}

		List<Map<String, Object>> returnDatas = new ArrayList<>();
		for (EcgHistoryDTO h : hs) {
			int autoSize = 0;
			int activeSize = 0;
			for (EcgDTO e : h.getDatas()) {
			    for (EcgDetailDTO ed : e.getDetailList()) {
			        if (0 == ed.getSignType().intValue()) {
			            autoSize++;
			        }
			        if (1 == ed.getSignType().intValue()) {
			            activeSize++;
			        }
			    }
			}
			h.getMeasureDate();
			Map<String, Object> returnData = new HashMap<>();
			returnData.put(com.lifeshs.common.constants.app.healthPackage.Ecg.DATE, h.getMeasureDate());
			returnData.put(com.lifeshs.common.constants.app.healthPackage.Ecg.AUTO_SIZE, autoSize);
			returnData.put(com.lifeshs.common.constants.app.healthPackage.Ecg.ACTIVE_SIZE, activeSize);

			returnDatas.add(returnData);
		}
		json.setObj(returnDatas);
		return json;
	}

	/**
	 * 心率
	 * author: wenxian.cai
	 * date: 2017/11/10 14:29
	 */
	@RequestMapping(value = "/heartrate", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJsonV2 listHeartRates (@RequestParam("type") String type, @RequestParam(value = "page", required = false) int page) {
		AjaxJsonV2 json = new AjaxJsonV2();
		int userId = getLoginUser().getId();
		List<Map<String, Object>> heartRateList = new ArrayList<Map<String, Object>>();// 存放符合日期条件的心率数据
		switch (type) {
			case "0":
				PaginationDTO<Map<String, Object>> paginationDTO = heartRate.getMeasureDataWithSplit(userId,
						DEVICE_TYPE, page, MEDICAL_PAGE_SIZE);
				heartRateList = paginationDTO.getData();
				break;
			case "1":
				heartRateList = heartRate.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.DAY);
				break;
			case "2": // 周
				heartRateList = heartRate.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.WEEK);
				break;
			case "3": // 一月
				heartRateList = heartRate.getMeasureDataWithDate(userId, DEVICE_TYPE, HealthPackage.MONTH);
				break;
			case "4": // 三月
				heartRateList = heartRate.getMeasureDataWithDate(userId, DEVICE_TYPE,
						HealthPackage.THREEMONTH);
				break;
			default:
				// 若传送过来的withDate是日期格式，则将withDate作为查询条件，并获取某一天的全部数据
				heartRateList = heartRate.getMeasureDataWithDate(userId, DEVICE_TYPE, type);
				break;
		}

		if (!type.equals("0")) {
			java.util.Collections.reverse(heartRateList);
		}
		json.setObj(heartRateList);
		return json;
	}


	private void getHashMap(String param, Object value, List<Map<String, Object>> root) {
		if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
			Map<String, Object> map = new HashMap<>();
			map.put("paramName", param);
			map.put("paramValue", "" + value);
			root.add(map);
		}
	}

	private void getHashMap(HealthType healthType, Object value, Long status, List<Map<String, Object>> root) {
		if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
			Map<String, Object> map = new HashMap<>();
			boolean isUnusual = true;
			if (status != null) {
				isUnusual = MeasureDevice.isHealthDeviceUnusual(status, healthType);
			}
			map.put("paramName", healthType.name());
			map.put("paramValue", "" + value);
			map.put("status", isUnusual ? "1" : "0");
			root.add(map);
		}
	}

	private boolean glucometerDataMeasureTypeSingle(String withDate) {
		boolean flag = false;
		switch (withDate) {
			case "0":
			case "1":
				flag = false;
				break;
			case "2":
			case "3":
			case "4":
				flag = true;
				break;
			default:
				flag = false;
		}
		return flag;
	}

	/**
	 * 为饮食类对象重新组装数据
	 *
	 * @author yuhang.weng
	 * @DateTime 2016年10月28日 下午5:25:57
	 *
	 * @param diets
	 * @return
	 */
	private List<Map<String, Object>> groupDietWithDate(List<DietDetail> diets) {

		List<Map<String, Object>> datas = new ArrayList<>();

		/**
		 * 把数据保存为以date为键，diets为值的形式
		 *
		 * {"recordDate", [Map, Map, Map, ...], "recordDate", [Map, ...]}
		 *
		 */
		Map<String, List<DietDetail>> root = new LinkedHashMap<>();
		for (DietDetail diet : diets) {
			String recordDate = DateTimeUtilT.date(diet.getRecordDate());
			if (root.containsKey(recordDate)) { // 包含该数据就再次加入
				root.get(recordDate).add(diet);
			} else {
				List<DietDetail> root_tmp = new ArrayList<>();
				root_tmp.add(diet);
				root.put(recordDate, root_tmp);
			}
		}
		/**
		 * 把内层保存为dietTime为键，diets为值的形式
		 */
		// List<String> dietTypes = new ArrayList<>();
		for (String recordDateKey : root.keySet()) {

			/**
			 * secondData: {"dietType", [Map, Map, Map, ...], "dietType", [Map,
			 * ...]}
			 */

			List<Map<String, Object>> datas_tmp = new ArrayList<>();

			Map<String, List<DietDetail>> secondData = new LinkedHashMap<>();
			for (DietDetail diet : root.get(recordDateKey)) {
				String dietType = diet.getDietType();
				if (secondData.containsKey(dietType)) {
					((List<DietDetail>) secondData.get(dietType)).add(diet);
				} else {
					List<DietDetail> details = new ArrayList<>();
					details.add(diet);
					secondData.put(dietType, details);
				}
			}
			for (String dietTypeKey : secondData.keySet()) {
				List<Map<String, Object>> foods = new ArrayList<>();
				List<DietDetail> details = secondData.get(dietTypeKey);
				for (DietDetail detail : details) {
					Map<String, Object> food = new HashMap<>();
					food.put(Diet.FOOD_ID, detail.getFoodId());
					food.put(Diet.FOOD_NAME, detail.getFoodName());
					food.put(Diet.FOOD_WEIGHT, detail.getFoodWeight());
					food.put(Diet.FOOD_KCAL, detail.getKcal());
					food.put(Diet.FOOD_IMAGE, detail.getImage());

					foods.add(food);
				}

				/** 插入数据 */
				Map<String, Object> thirdData = new LinkedHashMap<>();

				DietDetail diet_0 = details.get(0);

				thirdData.put(Diet.ID, diet_0.getId() + "");
				thirdData.put(Diet.TYPE, diet_0.getDietType());
				// thirdData.put(AppDiet.TIME,
				// DateTimeUtilT.time(diet_0.getDietTime()));
				thirdData.put(Diet.TIME, diet_0.getDietTime());
				thirdData.put(Diet.ENERGY, diet_0.getEnergy());
				thirdData.put(Diet.RECORD_DATE, recordDateKey);
				thirdData.put(Diet.FOODS, foods);

				datas_tmp.add(thirdData);
			}

			datas_tmp = dietTimeSort(datas_tmp);

			if (datas_tmp.size() > 0) {
				java.util.Collections.reverse(datas_tmp);
			}

			datas.addAll(datas_tmp);
		}

		if (datas.size() > 0) {
			java.util.Collections.reverse(datas);
		}

		return datas;
	}
	/**
	 * 对饮食记录一天内的数据进行排序（按照早餐，早餐加餐，午餐，午餐加餐，晚餐，晚餐加餐排列）
	 *
	 * @author yuhang.weng
	 * @DateTime 2016年12月19日 上午11:22:53
	 *
	 * @param dietDatas
	 * @return
	 */
	public static List<Map<String, Object>> dietTimeSort(List<Map<String, Object>> dietDatas) {

		Collections.sort(dietDatas, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				// Date d1 = DateTimeUtilT.time((String) o1.get("dietTime"));
				// Date d2 = DateTimeUtilT.time((String) o2.get("dietTime"));
				Date d1 = (Date) o1.get("dietTime");
				Date d2 = (Date) o2.get("dietTime");

				if (d1.before(d2)) {
					return -1;
				}

				if (d1.equals(d2)) {
					String type1 = (String) o1.get("dietType");
					String type2 = (String) o2.get("dietType");
					return type1.length() - type2.length();
				}
				return 0;
			}
		});

		return dietDatas;
	}
}

