package com.lifeshs.customer.controller.combo;

import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.common.model.AjaxJsonV2;
import com.lifeshs.customer.controller.common.BaseController;
import com.lifeshs.po.measure.MeasureAnalysisPO;
import com.lifeshs.po.user.UserServeRecordPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.healthDevice.BloodLipidDTO;
import com.lifeshs.pojo.healthDevice.EcgHistoryDTO;
import com.lifeshs.pojo.healthDevice.UaDTO;
import com.lifeshs.pojo.healthDevice.UranDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.record.DietVO;
import com.lifeshs.service.device.impl.*;
import com.lifeshs.service.org.IServiceOrgService;
import com.lifeshs.service.record.IMedicalService;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.member.ServeRecordService;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.PhysicalService;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.JSONHelper;
import com.lifeshs.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * vip会员控制器
 * author: wenxian.cai
 * date: 2017/10/9 14:56
 */

@Controller()
@RequestMapping(value = "combo/member")
public class VipMemberController extends BaseController{

	static final Logger logger = Logger.getLogger(BaseController.class);
	static final int VIP_MEMBER_PAGE_SIZE = 10;
	final static int PAGE_SIZE_HEALTH_DATA = 12;
	final static int PAGE_SIZE_MEDICAL_DATA = 6;
	final static int SERVE_RECORD_PAGE_SIZE = 100;

	@Autowired
	IVipUserService vipUserService;
	@Autowired
	IRecordService recordService;
	@Autowired
	IServiceOrgService serviceOrgService;
	@Resource(name = "bloodPressure")
	private BloodPressure bloodPressure;
	@Resource(name = "lunginstrument")
	private Lunginstrument lunginstrument;
	@Resource(name = "glucometer")
	private Glucometer glucometer;
	@Resource(name = "oxygen")
	private Oxygen oxygen;
	@Resource(name = "heartRate")
	private HeartRate heartRate;
	@Resource(name = "bodyfatscale")
	private Bodyfatscale bodyfatscale;
	@Resource(name = "band")
	private Band band;
	@Resource(name = "temperature")
	private Temperature temperature;
	@Resource(name = "bloodLipid")
	private BloodLipid bloodLipid;
	@Resource(name = "ua")
	private Ua ua;
	@Resource(name = "uran")
	private Uran uran;
	@Resource(name = "ecg")
	private Ecg ecg;
	@Autowired
	private IMedicalService medicalService;
	@Autowired
	PhysicalService physicalService;
	@Autowired
	MeasureAnalysisService measureAnalysisService;
	@Autowired
	ServeRecordService serveRecordService;



	/**
	 * vip会员管理页面
	 * author: wenxian.cai
	 * date: 2017/10/13 15:43
	 */
	@RequestMapping("/page")
	public ModelAndView vipMmeberPage () {
		return new ModelAndView("platform/vipmember/vip-member");
	}


	/**
	 * 获取vip会员列表
	 * author: wenxian.cai
	 * date: 2017/10/13 15:44
	 */
	@RequestMapping("/data/list/{page}")
	@ResponseBody
	public AjaxJsonV2 listVipMember(@PathVariable("page") int page, @RequestParam(value = "gender", required = false) Boolean gender,
								  @RequestParam(value = "startAge", required = false) Integer startAge, @RequestParam(value = "endAge", required = false) Integer endAge,
								  @RequestParam(value = "vipComboId", required = false) Integer vipComboId, @RequestParam(value = "status", required = false) int status,
								  @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "todayAbnormal", required = false) Boolean todayAbnormal ,
								  @RequestParam(value = "isEndTime", required = false) Boolean isEndTime, @RequestParam(value = "todayNotMeasure", required = false) Boolean todayNotMeasure ,
								  @RequestParam(value = "monthNotMeasure") Boolean monthNotMeasure) {
	    AjaxJsonV2 ajaxJson = new AjaxJsonV2();
		if (StringUtil.isBlank(keyword)) {
			keyword = null;
		}
		Paging paging = vipUserService.listVipUserByCustomer(todayAbnormal, isEndTime, todayNotMeasure, monthNotMeasure, gender, startAge, endAge, vipComboId, status, keyword, page, VIP_MEMBER_PAGE_SIZE);
		ajaxJson.setObj(paging.getPagination());
		return ajaxJson;
	}

	/**
	 * 获取有效健康数据的日期
	 * @param type
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/data/list-valid-date", method = RequestMethod.GET)
	public @ResponseBody AjaxJson listVilidDate(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "type") Integer type,
												@RequestParam(value = "date") String date) {
		AjaxJson resObject = new AjaxJson();
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
				Map<String, Integer> dat = recordService.queryDatesWithData(userId, localDate1.toString(), type);
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
	 * @Description: 获取会员的健康信息
	 * @author: wenxian.cai
	 * @create: 2017/5/5 15:16
	 */
	@RequestMapping(value = "/data/health-data", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getMemberHealthData(@RequestParam(value = "userId") Integer userId,
													  @RequestParam(value = "measureDate") String measureDate) {
		AjaxJson resObject = new AjaxJson();
		LoginUser user = getLoginUser();
		Integer orgId = user.getOrgId();
//		serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);
		Map<String, Object> data = serviceOrgService.getMemberHealthDataSplitByMeasureDate(userId, measureDate);
		for (String key : data.keySet()) {
			if (data.get(key) == null) {
				continue;
			}
			Map<String, Object> map = null;
			Long status = null;
			switch (key) {
				case "Oxygen":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if ((status | HealthType.heartRate.value()) == status) {
						map.put("heartRateStatus", false);
					}
					if ((status | HealthType.saturation.value()) == status) {
						map.put("saturationStatus", false);
					}

					break;
				case "Temperature":
					break;
				case "Uran":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if ((status | HealthType.LEU.value()) == status) {
						map.put("lEUStatus", false);
					}
					if ((status | HealthType.NIT.value()) == status) {
						map.put("nITStatus", false);
					}
					if ((status | HealthType.UBG.value()) == status) {
						map.put("uBGStatus", false);
					}
					if ((status | HealthType.PRO.value()) == status) {
						map.put("pROStatus", false);
					}
					if ((status | HealthType.pH.value()) == status) {
						map.put("pHStatus", false);
					}
					if ((status | HealthType.BLD.value()) == status) {
						map.put("bLDStatus", false);
					}
					if ((status | HealthType.SG.value()) == status) {
						map.put("sGStatus", false);
					}
					if ((status | HealthType.KET.value()) == status) {
						map.put("kETStatus", false);
					}
					if ((status | HealthType.BIL.value()) == status) {
						map.put("bILStatus", false);
					}
					if ((status | HealthType.GLU.value()) == status) {
						map.put("gLUStatus", false);
					}
					if ((status | HealthType.VC.value()) == status) {
						map.put("vCStatus", false);
					}
					break;
				case "Glucometer":
                    /*map = JSONHelper.toHashMap(data.get(key));
                    status = Long.parseLong(String.valueOf(map.get("status")));
                    if (status != 0) {
                        map.put("bloodSugarStatus", false);
                    }*/
					break;
				case "Bloodlipid":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if ((status | HealthType.HDL.value()) == status) {
						map.put("hDLStatus", false);
					}
					if ((status | HealthType.LDL.value()) == status) {
						map.put("lDLStatus", false);
					}
					if ((status | HealthType.TG.value()) == status) {
						map.put("tGStatus", false);
					}
					if ((status | HealthType.TC.value()) == status) {
						map.put("tCStatus", false);
					}
					if ((status | HealthType.BloodLipidRation.value()) == status) {
						map.put("bloodLipidRationStatus", false);
					}
					break;
				case "Lunginstrument":
                    /*map = JSONHelper.toHashMap(data.get(key));
                    status = Long.parseLong(String.valueOf(map.get("status")));
                    if (status != 0) {
                        map.put("vitalCapacityStatus", false);
                    }*/
					break;
				case "Band":
                    /*List<Map<String, Object>> list = JSONHelper.toList(data.get(key));*/
					break;
				case "Heartrate":
                  /*  List<Map<String, Object>> list = JSONHelper.toList(data.get(key));
                    for (Map<String, Object> map1 : list) {
                        if (map1.get("status") != 0) {

                        }
                    }*/
					break;
				case "Bodyfatscale":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if ((status | HealthType.weight.value()) == status) {
						map.put("weightStatus", false);
					}
					if ((status | HealthType.axungeRatio.value()) == status) {
						map.put("axungeRatioStatus", false);
					}
					if ((status | HealthType.WHR.value()) == status) {
						map.put("WHRStatus", false);
					}
					if ((status | HealthType.BMI.value()) == status) {
						map.put("BMIStatus", false);
					}
					if ((status | HealthType.muscle.value()) == status) {
						map.put("muscleStatus", false);
					}
					if ((status | HealthType.moisture.value()) == status) {
						map.put("moistureStatus", false);
					}
					if ((status | HealthType.boneWeight.value()) == status) {
						map.put("boneWeightStatus", false);
					}
					if ((status | HealthType.bodyage.value()) == status) {
						map.put("bodyageStatus", false);
					}
					if ((status | HealthType.baseMetabolism.value()) == status) {
						map.put("baseMetabolismStatus", false);
					}
					if ((status | HealthType.visceralFat.value()) == status) {
						map.put("visceralFatStatus", false);
					}
					break;
				case "Bloodpressure":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if ((status | HealthType.diastolic.value()) == status) {
						map.put("diastolicStatus", false);
					}
					if ((status | HealthType.heartRate.value()) == status) {
						map.put("heartRateStatus", false);
					}
					if ((status | HealthType.systolic.value()) == status) {
						map.put("systolicStatus", false);
					}
					break;
				case "Ua":
					map = JSONHelper.toHashMap(data.get(key));
					status = Long.parseLong(String.valueOf(map.get("status")));
					if (status != 0) {
						map.put("uAStatus", false);
					}
					break;
				case "Ecg":
					map = JSONHelper.toHashMap(data.get(key));
                    /*
                    if (map.get("status") != null) {
                        status = Long.parseLong(String.valueOf(map.get("status")));
                    }

                    if (status != 0) {
                        map.put("uAStatus", false);
                    }*/
					if (map.get("status").equals(null)) {
						map.put("status", 0);
					}
					break;
			}
			if (map != null) {
				map.put("measureDate", JSONHelper.toHashMap(map.get("measureDate")).get("time"));
				data.put(key, map);
			}

		}
		Map map = new HashMap();
		map.put("data", data);
//        map.put("date", date);
		resObject.setAttributes(map);
		return resObject;
	}

	/**
	 * 分页获取体检数据
	 * @param page
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/data/list-member-physical-data-paging/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listMemberPhysicalDataPaging(@PathVariable("page")int page, @RequestParam(value = "userId") Integer userId) {
		AjaxJson resObject = new AjaxJson();
//
//		LoginUser user = getLoginUser();
//		Integer orgId = user.getOrgId();

//		serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

		Paging paging = physicalService.listPhysical(userId, page, PAGE_SIZE_MEDICAL_DATA);
		resObject.setObj(paging.getPagination());

		return resObject;
	}

	/**
	 * 分页获取病历数据
	 * @param page
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/data/list-member-medical-data-paging/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listMemberMedicalDataPaging(@PathVariable("page")int page, @RequestParam(value = "userId") Integer userId) {
		AjaxJson resObject = new AjaxJson();

//		LoginUser user = getLoginUser();
//		Integer orgId = user.getOrgId();

//		serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

		PaginationDTO paginationDTO = medicalService.getMedicalByUserIdWithPageSplit(userId, page, PAGE_SIZE_MEDICAL_DATA);
		resObject.setObj(paginationDTO);

		return resObject;
	}

	/**
	 * 按测量日期获取饮食数据
	 *
	 * @author yuhang.weng
	 * @DateTime 2016年9月20日 下午3:42:41
	 *
	 * @param userId
	 * @param recordDate
	 * @return
	 */
	@RequestMapping(value = "/data/member-diet-data", method = RequestMethod.GET)
	public @ResponseBody AjaxJson getMemberDietData(@RequestParam(value = "userId") Integer userId,
													@RequestParam(value = "recordDate") String recordDate) {
		AjaxJson resObject = new AjaxJson();

//		LoginUser user = getLoginUser();
//		Integer orgId = user.getOrgId();

//		serviceOrgService.isThisMemberBelongToTheOrg(orgId, userId);

		List<DietVO> diets = serviceOrgService.getDietDataSplitByRecordDate(userId, recordDate);

		resObject.setObj(diets);

		return resObject;
	}

	/**
	 * 获取会员最近一周数据
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/data/list-health-data/type/{dateType}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listMemberHealthDataByWeek(@PathVariable("dateType") String dateType,
											   @RequestParam("type") String type,
											   @RequestParam(value = "xlType", required = false) String xlType,
											   @RequestParam("userId") int userId) {
		AjaxJson resObject = new AjaxJson();
		//判断是否有权限查看用户信息 todo
		String deviceType = "";
		List<Map<String, Object>> listWeek = null;
		switch (type) {
			case "xyj":
				listWeek = bloodPressure.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (Map<String, Object> map: listWeek) {
                    Long status = Long.parseLong(String.valueOf(map.get("status")));
                    if ((status | HealthType.diastolic.value()) == status) {
                        map.put("diastolicStatus", "abnormal");
                    }
                    if ((status | HealthType.heartRate.value()) == status) {
                        map.put("heartRateStatus", "abnormal");
                    }
                    if ((status | HealthType.systolic.value()) == status) {
                        map.put("systolicStatus", "abnormal");
                    }
                }*/
				break;
			case "fhy":
				listWeek = lunginstrument.getMeasureDataWithDate(userId, deviceType, dateType);
				break;
			case "xty":
				listWeek = glucometer.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (Map<String, Object> map: listWeek) {
                    Long status = Long.parseLong(String.valueOf(map.get("status")));
                    if ((status | HealthType.bloodSugar.value()) == status) {
                        map.put("bloodSugarStatus", "abnormal");
                    }
                }*/
				listWeek = getGlucoseSpecialData(listWeek); // 将血糖数据进行重新组装
				break;
			case "xyy":
				listWeek = oxygen.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (Map<String, Object> map: listWeek) {
                    Long status = Long.parseLong(String.valueOf(map.get("status")));
                    if ((status | HealthType.heartRate.value()) == status) {
                        map.put("heartRateStatus", "abnormal");
                    }
                    if ((status | HealthType.saturation.value()) == status) {
                        map.put("saturationStatus", "abnormal");
                    }
                }*/
				break;
			case "band":
				switch (xlType) {
					case "step":
						listWeek = band.getMeasureDataWithDate(userId, deviceType, dateType);
						break;
					case "heartRate":
						listWeek = heartRate.getMeasureDataWithDate(userId, deviceType, dateType);
						break;
					case "sleep":
						listWeek = band.getMeasureDataWithDate(userId, deviceType, dateType);
						break;
					default:
						break;
				}
				break;
			case "tzc":
				listWeek = bodyfatscale.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (Map<String, Object> map: listWeek) {
                    Long status = Long.parseLong(String.valueOf(map.get("status")));
                    if ((status | HealthType.weight.value()) == status) {
                        map.put("weightStatus", "abnormal");
                    }
                    if ((status | HealthType.axungeRatio.value()) == status) {
                        map.put("axungeRatioStatus", "abnormal");
                    }
                    if ((status | HealthType.WHR.value()) == status) {
                        map.put("WHRStatus", "abnormal");
                    }
                    if ((status | HealthType.BMI.value()) == status) {
                        map.put("BMIStatus", "abnormal");
                    }
                    if ((status | HealthType.muscle.value()) == status) {
                        map.put("muscleStatus", "abnormal");
                    }
                    if ((status | HealthType.moisture.value()) == status) {
                        map.put("moistureStatus", "abnormal");
                    }
                    if ((status | HealthType.boneWeight.value()) == status) {
                        map.put("boneWeightStatus", "abnormal");
                    }
                    if ((status | HealthType.bodyage.value()) == status) {
                        map.put("bodyageStatus", "abnormal");
                    }
                    if ((status | HealthType.baseMetabolism.value()) == status) {
                        map.put("baseMetabolismStatus", "abnormal");
                    }
                    if ((status | HealthType.visceralFat.value()) == status) {
                        map.put("visceralFatStatus", "abnormal");
                    }
                }*/
				break;
			case "twj":
				listWeek = temperature.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (Map<String, Object> map: listWeek) {
                    Long status = Long.parseLong(String.valueOf(map.get("status")));
                    if ((status | HealthType.temperature.value()) == status) {
                        map.put("temperatureStatus", "abnormal");
                    }
                }*/
				break;
			case "xzy":
				List<BloodLipidDTO> bloodLipidDTOS = bloodLipid.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (BloodLipidDTO dto: bloodLipidDTOS) {
                    Long status = dto.getStatus();
                    if ((status | HealthType.HDL.value()) == status) {
                        dto.setHDLStatus(false);
                    }
                    if ((status | HealthType.LDL.value()) == status) {
                        dto.setLDLStatus(false);
                    }
                    if ((status | HealthType.TG.value()) == status) {
                        dto.setTGStatus(false);
                    }
                    if ((status | HealthType.TC.value()) == status) {
                        dto.setTCStatus(false);
                    }
                    if ((status | HealthType.BloodLipidRation.value()) == status) {
                        dto.setBloodLipidRatioStatus(false);
                    }
                }*/
				resObject.setObj(bloodLipidDTOS);
				break;
			case "ny":
				List<UranDTO> UranDTOs = uran.getMeasureDataWithDate(userId, deviceType, dateType);
                /*for (UranDTO dto: UranDTOs) {
                    Long status = dto.getStatus();
                    if ((status | HealthType.LEU.value()) == status) {
                        dto.setLEUStatus(false);
                    }
                    if ((status | HealthType.NIT.value()) == status) {
                        dto.setNITStatus(false);
                    }
                    if ((status | HealthType.UBG.value()) == status) {
                        dto.setUBGStatus(false);
                    }
                    if ((status | HealthType.PRO.value()) == status) {
                        dto.setPROStatus(false);
                    }
                    if ((status | HealthType.pH.value()) == status) {
                        dto.setPhStatus(false);
                    }
                    if ((status | HealthType.BLD.value()) == status) {
                        dto.setBLDStatus(false);
                    }
                    if ((status | HealthType.SG.value()) == status) {
                        dto.setSgStatus(false);
                    }
                    if ((status | HealthType.KET.value()) == status) {
                        dto.setKETStatus(false);
                    }
                    if ((status | HealthType.BIL.value()) == status) {
                        dto.setBILStatus(false);
                    }
                    if ((status | HealthType.GLU.value()) == status) {
                        dto.setGLUStatus(false);
                    }
                    if ((status | HealthType.VC.value()) == status) {
                        dto.setVCStatus(false);
                    }
                }*/
				resObject.setObj(UranDTOs);
				break;
			case "ns":
				List<UaDTO> uaDTOS = ua.getMeasureDataWithDate(userId, deviceType, dateType);
				resObject.setObj(uaDTOS);
				break;
			case "ecg":
				List<EcgHistoryDTO> ecgDTOS = ecg.getMeasureDataWithDate(userId,deviceType, dateType);
				resObject.setObj(ecgDTOS);
			default:
				break;
		}
		if (listWeek != null) {
			resObject.setObj(listWeek);
		}
		return resObject;
	}


	/**
	 * 分页获取会员健康数据
	 * @param page
	 * @param type
	 * @param xlType
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/data/list-health-data/paging/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listMemberHealthDataByPaging(@PathVariable("page") int page,
												 @RequestParam("type") String type,
												 @RequestParam(value = "xlType", required = false) String xlType,
												 @RequestParam("userId") int userId) {
		AjaxJson resObject = new AjaxJson();
		page = page < 1 ? 1 : page;
		String deviceType = "";
		PaginationDTO<Map<String, Object>> paDto = null;
		if (type.equals("xyj")) {
			paDto = bloodPressure.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			for (Map<String, Object> map :  paDto.getData()) {
				Long status = Long.parseLong(String.valueOf(map.get("status")));
				if ((status | HealthType.diastolic.value()) == status) {
					map.put("diastolicStatus", "abnormal");
				}
				if ((status | HealthType.heartRate.value()) == status) {
					map.put("heartRateStatus", "abnormal");
				}
				if ((status | HealthType.systolic.value()) == status) {
					map.put("systolicStatus", "abnormal");
				}
			}
		}
		if (type.equals("fhy")) {
			paDto = lunginstrument.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
		}
		if (type.equals("xty")) {
			paDto = glucometer.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			paDto.setData(getGlucoseSpecialData(paDto.getData())); // 将血糖数据进行重新组装
		}
		if (type.equals("xyy")) {
			paDto = oxygen.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			for (Map<String, Object> map: paDto.getData()) {
				Long status = Long.parseLong(String.valueOf(map.get("status")));
				if ((status | HealthType.heartRate.value()) == status) {
					map.put("heartRateStatus", "abnormal");
				}
				if ((status | HealthType.saturation.value()) == status) {
					map.put("saturationStatus", "abnormal");
				}
			}
		}
		if (type.equals("band")) {
			switch (xlType) {
				case "step":
					paDto = band.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
					break;
				case "heartRate":
					paDto = heartRate.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
					break;
				case "sleep":
					paDto = band.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
					break;
				default:
					break;
			}
		}
		if (type.equals("tzc")) {
			paDto = bodyfatscale.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			for (Map<String, Object> map: paDto.getData()) {
				Long status = Long.parseLong(String.valueOf(map.get("status")));
				if ((status | HealthType.weight.value()) == status) {
					map.put("weightStatus", "abnormal");
				}
				if ((status | HealthType.axungeRatio.value()) == status) {
					map.put("axungeRatioStatus", "abnormal");
				}
				if ((status | HealthType.WHR.value()) == status) {
					map.put("WHRStatus", "abnormal");
				}
				if ((status | HealthType.BMI.value()) == status) {
					map.put("BMIStatus", "abnormal");
				}
				if ((status | HealthType.muscle.value()) == status) {
					map.put("muscleStatus", "abnormal");
				}
				if ((status | HealthType.moisture.value()) == status) {
					map.put("moistureStatus", "abnormal");
				}
				if ((status | HealthType.boneWeight.value()) == status) {
					map.put("boneWeightStatus", "abnormal");
				}
				if ((status | HealthType.bodyage.value()) == status) {
					map.put("bodyageStatus", "abnormal");
				}
				if ((status | HealthType.baseMetabolism.value()) == status) {
					map.put("baseMetabolismStatus", "abnormal");
				}
				if ((status | HealthType.visceralFat.value()) == status) {
					map.put("visceralFatStatus", "abnormal");
				}
			}
		}
		if (type.equals("twj")) {
			paDto = temperature.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
		}
		if (type.equals("xzy")) {
			PaginationDTO<BloodLipidDTO> paginationDTO = bloodLipid.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			for (BloodLipidDTO dto: paginationDTO.getData()) {
				Long status = dto.getStatus();
				if ((status | HealthType.HDL.value()) == status) {
					dto.setHDLStatus(false);
				}
				if ((status | HealthType.LDL.value()) == status) {
					dto.setLDLStatus(false);
				}
				if ((status | HealthType.TG.value()) == status) {
					dto.setTGStatus(false);
				}
				if ((status | HealthType.TC.value()) == status) {
					dto.setTCStatus(false);
				}
				if ((status | HealthType.BloodLipidRation.value()) == status) {
					dto.setBloodLipidRatioStatus(false);
				}
			}
			resObject.setObj(paginationDTO);
		}
		if (type.equals("ny")) {
			PaginationDTO<UranDTO> paginationDTO = uran.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			for (UranDTO dto: paginationDTO.getData()) {
				Long status = dto.getStatus();
				if ((status | HealthType.LEU.value()) == status) {
					dto.setLEUStatus(false);
				}
				if ((status | HealthType.NIT.value()) == status) {
					dto.setNITStatus(false);
				}
				if ((status | HealthType.UBG.value()) == status) {
					dto.setUBGStatus(false);
				}
				if ((status | HealthType.PRO.value()) == status) {
					dto.setPROStatus(false);
				}
				if ((status | HealthType.pH.value()) == status) {
					dto.setPhStatus(false);
				}
				if ((status | HealthType.BLD.value()) == status) {
					dto.setBLDStatus(false);
				}
				if ((status | HealthType.SG.value()) == status) {
					dto.setSgStatus(false);
				}
				if ((status | HealthType.KET.value()) == status) {
					dto.setKETStatus(false);
				}
				if ((status | HealthType.BIL.value()) == status) {
					dto.setBILStatus(false);
				}
				if ((status | HealthType.GLU.value()) == status) {
					dto.setGLUStatus(false);
				}
				if ((status | HealthType.VC.value()) == status) {
					dto.setVCStatus(false);
				}
			}
			resObject.setObj(paginationDTO);
		}
		if (type.equals("ns")) {
			PaginationDTO paginationDTO = ua.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			resObject.setObj(paginationDTO);
		}
		if (type.equals("ecg")) {
			PaginationDTO paginationDTO = ecg.getMeasureDataWithSplit(userId, deviceType, page, PAGE_SIZE_HEALTH_DATA);
			resObject.setObj(paginationDTO);
		}


		if (paDto != null) {
			resObject.setObj(paDto);
		}

		return resObject;
	}


	private List<Map<String, Object>> getGlucoseSpecialData(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<>();
			List<HashMap<String, Object>> list2 = new ArrayList<>();
			hashMap.put("measureDate", list.get(i).get("measureDate"));
			String param = DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate"));
			int listSize = list.size();
			for (int j = i; j < listSize; j++) {
				if (param.equals(DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate")))) {
					HashMap<String, Object> hashMap2 = new HashMap<>();
					HashMap<String, Object> hashMap3 = new HashMap<>();
					hashMap3.put("status", list.get(j).get("status") + "");
					hashMap3.put("bloodSugar", list.get(j).get("bloodSugar") + "");
					hashMap3.put("bloodSugarArea", list.get(j).get("bloodSugarArea") + "");
					hashMap2.put(list.get(j).get("measureType") + "", hashMap3);
					list2.add(hashMap2);
					list.remove(j);
					listSize = listSize - 1;
					j--;
				}
			}
			hashMap.put("paramData", list2);
			newList.add(hashMap);
			i--;
		}
		return newList;
	}

	/**
	 * 添加健康数据批注
	 * author: wenxian.cai
	 * date: 2017/10/31 15:11
	 */
	@RequestMapping(value = "/data/health-analysis", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addHealthAnalysis (@RequestParam("userId") int userId, @RequestParam("measureDate") String measureDate,
										@RequestParam("content") String content, @RequestParam("doctorSign") String doctorSign) {
		AjaxJson ajaxJson = new AjaxJson();
		try {

			Date date = DateTimeUtilT.date(measureDate);
			measureAnalysisService.addAnalysis(userId, date, content, getLoginUser().getId(), doctorSign);
		} catch (BaseException b) {
			ajaxJson.setMsg(b.getMessage());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 获取健康数据批注
	 * author: wenxian.cai
	 * date: 2017/10/31 15:35
	 */
	@RequestMapping(value = "/data/health-analysis", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getHealthAnalysis (@RequestParam("userId") int userId, @RequestParam("measureDate") String measureDate) {
		AjaxJson ajaxJson = new AjaxJson();
		Date date = DateTimeUtilT.date(measureDate);
		List<MeasureAnalysisPO> list = measureAnalysisService.listAnalysis(userId, date);
		ajaxJson.setObj(list);
		return ajaxJson;
	}

	/**
	 * 添加服务记录
	 * author: wenxian.cai
	 * date: 2017/11/3 10:58
	 */
	@RequestMapping(value = "/data/serve-record", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addUserServeRecord (@RequestParam("userId") int userId, @RequestParam("serveDate") String serveDate,
										 @RequestParam("serveContent") String serveContent, @RequestParam("remark") String remark) {
		AjaxJson ajaxJson = new AjaxJson();
		UserServeRecordPO userServeRecordPO = new UserServeRecordPO();
		userServeRecordPO.setCustomerId(getLoginUser().getId());
		userServeRecordPO.setUserId(userId);
		userServeRecordPO.setServeDate(DateTimeUtilT.date(serveDate));
		userServeRecordPO.setServeContent(serveContent);
		userServeRecordPO.setRemark(remark);
		try {
			serveRecordService.addUserServeRecord(userServeRecordPO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			ajaxJson.setMsg(e.getMessage());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}

	/**
	 * 获取用户服务记录列表
	 * author: wenxian.cai
	 * date: 2017/11/3 11:01
	 */
	@RequestMapping(value = "/data/serve-record/{page}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listUserServeRecord (@PathVariable("page") int page, @RequestParam("userId") int userId) {
		AjaxJson ajaxJson = new AjaxJson();
		Paging paging = serveRecordService.listUserServeRecord(userId, null, page, SERVE_RECORD_PAGE_SIZE);
		ajaxJson.setObj(paging.getData());
		return ajaxJson;
	}
}
