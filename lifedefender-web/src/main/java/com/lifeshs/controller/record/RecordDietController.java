package com.lifeshs.controller.record;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeshs.controller.common.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.controller.terminal.HealthDataController;
import com.lifeshs.common.constants.app.RecordSport;
import com.lifeshs.common.constants.app.RecordSportDetail;
import com.lifeshs.common.constants.app.Sport;
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.entity.record.TRecordSportDetail;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.record.DietDTO;
import com.lifeshs.pojo.record.EnergyAnalyzeDTO;
import com.lifeshs.pojo.record.SportsDTO;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.record.ISportService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.ResourceUtil;

/**
 * 版权归
 * TODO 健康日记控制器
 *
 * @author wenxian.cai
 * @datetime 2016年8月20日下午2:34:04
 */
@Controller
@RequestMapping("recordDietControl")
public class RecordDietController extends BaseController{

    private static final Logger logger = Logger.getLogger(HealthDataController.class);
    @Autowired
    IRecordService IService;
    
    @Autowired
    ISportService sportService;

    @RequestMapping(params = "RecordDietEnter")
    public String recordDietEnter() {
        return "com/lifeshs/member/dietRecord";    // main/testAjaxDataSend
    }

    @RequestMapping(params = "RecordDietFoodEnter")
    public String recordDietFoodEnter() {
        return "com/lifeshs/member/checkDiet";    // main/testAjaxDataSend
    }

    /**
     * @author wenxian.cai
     * @DateTime 2016年8月11日下午2:56:49
     * @serverComment 查询饮食列表
     * @param date
     */
    @RequestMapping(params = "selectDietByUserIdWithDate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson selectDietByUserIdWithDate(@RequestParam String date) throws Exception {
        AjaxJson resObject = new AjaxJson();
        String msg = "获取用户饮食列表失败";
//        List<Object> list = new ArrayList<>();
        int userId = getLoginUser().getId();
        List<Map<String, Object>> dietlist = IService.selectDietByUserIdWithDate(userId, date);    //存放饮食Diet集合
        //"早餐"和"早餐加餐"交换位置
        Collections.sort(dietlist, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String d1 = DateTimeUtilT.time((Date) o1.get("dietTime"));
                String d2 = DateTimeUtilT.time((Date) o2.get("dietTime"));
                if (d1.equals(d2)) {
                    String type1=(String)o1.get("dietType");
                    String type2= (String) o2.get("dietType");
                    return type1.length()-type2.length();
                }
                return 0;
            }
        });
        List<Map<String, Object>> foodList = null;
        for (int i = 0; i < dietlist.size(); i++) {
            foodList = new ArrayList<>();    //存放一个Diet的食物Food集合
            foodList = IService.selectDietFoodByDietId(Integer.parseInt(String.valueOf(dietlist.get(i).get("id"))));
            dietlist.get(i).put("details", foodList);
        }
       /* list.add(dietlist);
        list.add(allFoodlist);*/
        if (dietlist.size() > 0) {
        	resObject.setSuccess(true);
        	msg = "获取用户饮食列表成功";
        	resObject.setMsg(msg);
        	resObject.setObj(dietlist);
        	return resObject;
		}
        resObject.setMsg(msg);
        resObject.setSuccess(false);
        return resObject;
    }

    /**
     * 添加饮食
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月11日下午2:57:12
     * @serverComment
     */
    @RequestMapping(params = "addDietByUserIdWithDate", method = RequestMethod.POST)
    public
    @ResponseBody
    @Transactional
    AjaxJson addDietByUserIdWithDate( @RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "添加用户饮食失败";
        resObject.setMsg(msg);
        
        String date = (String) data.get("recordDate");
        Time dietTime = new Time(DateTimeUtilT.time((String) data.get("dietTime")).getTime());
        String dietType = (String) data.get("dietType");
        int energy = (int) data.get("energy");    //饮食能量
        int userId = getLoginUser().getId();
        List<Map<String, Object>> details = (List<Map<String, Object>>) data.get("details");
        
        //判断饮食类型是否已存在
        List<Map<String, Object>> dietlist = IService.selectDietByUserIdWithDate(userId, date);    //存放饮食Diet集合
        for (int i = 0; i < dietlist.size(); i++) {
            String type = (String) dietlist.get(i).get("dietType");
            if (type.equals(dietType)) {
            	 msg = "" + dietType + "已存在";
                 resObject.setMsg(msg);
                 return resObject;
            }
        }
//			添加饮食
            HashMap<String, Object> dietMap = new HashMap<>();
            dietMap.put("recordDate", date);
            dietMap.put("dietType", dietType);
            dietMap.put("dietTime", dietTime);
            dietMap.put("energy", energy);
            int dietId = IService.addDiet(dietMap, userId);
//			添加食物
            for (int i = 0; i < details.size(); i++) {
                HashMap<String, Object> foodMap = new HashMap<>();
                foodMap.put("dietId", dietId);
                foodMap.put("foodID", details.get(i).get("foodId"));
                foodMap.put("foodWeight", details.get(i).get("foodWeight"));
                foodMap.put("kcal", details.get(i).get("kcal"));  
                IService.addDietFood(foodMap, dietId);
            }
            msg = "添加用户饮食成功";
            resObject.setMsg(msg);
            return resObject;
    }


    /**
     * 删除饮食
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月12日上午11:52:44
     * @serverComment
     */
    @RequestMapping(params = "delDietById", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson delDietById(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除用户饮食失败";
        resObject.setMsg(msg);
        int id = Integer.parseInt((String) data.get("id"));        //获取饮食diet的ID
        int result = IService.deleteDiet(id);
        List<Map<String, Object>> list = IService.selectDietFoodByDietId(id);
        for (int i = 0; i < list.size(); i++) {
            IService.deleteDietFood((Integer) list.get(i).get("id"));
        }
        if (result > 0) {
            msg = "删除用户饮食成功";
            resObject.setMsg(msg);
            return resObject;
        }
        return resObject;
    }

    /**
     * 修改用户饮食
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月13日下午2:06:08
     * @serverComment
     */
    @RequestMapping(params = "modifyDiet", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson modifyDiet(@RequestBody DietDTO dietDTO) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改用户饮食失败";
        resObject.setMsg(msg);
        if (IService.updataDiet(dietDTO)) {
        	resObject.setSuccess(true);
            msg = "修改用户饮食成功";
            resObject.setMsg(msg);
            return resObject;
		}
        return resObject;
    }

    /**
     * 删除食物
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月13日下午2:44:11
     * @serverComment
     */
    @RequestMapping(params = "delFoodById", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson delFoodById(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除用户食物失败";
        resObject.setMsg(msg);
        int id = Integer.parseInt((String) data.get("id"));        //获取饮食diet的ID
//		int userId = getLoginUser().getId();
        int result = IService.deleteDietFood(id);
        if (result > 0) {
            msg = "删除用户食物成功";
            resObject.setMsg(msg);
            return resObject;
        }
        return resObject;
    }

    /**
     * 获取用户饮食能量表
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月13日下午4:34:26
     * @serverComment
     */
    @RequestMapping(params = "selectDietEnergyByUserId", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson selectDietEnergyByUserId(@RequestBody Map<String, Object> map) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取用户饮食能量表失败";
        resObject.setMsg(msg);
        int userId = getLoginUser().getId();
        String endDate = (String) map.get("date");
        String StartDate = "";
        StartDate =  DateTimeUtilT.date(DateTimeUtilT.addDay(DateTimeUtilT.date(endDate), -6));
        List<Map<String, Object>> list = IService.selectDietEnergyByUserIdWithDate(userId, true, StartDate, endDate);
        resObject.setObj(list);
        if (list.size() > 0) {
            msg = "获取用户饮食能量表成功";
            resObject.setMsg(msg);
            return resObject;
        }
        return resObject;
    }

    /**
     * 根据种类名称获取属于这个种类的食物信息
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月16日下午5:35:25
     * @serverComment
     */
    @RequestMapping(params = "selectFoodByKind", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson selectFoodByKind(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取种类-食物失败";
        resObject.setMsg(msg);
        String kindName = (String) data.get("kind");
        List<Map<String, Object>> list = IService.selectFoodByKind(kindName);
        if (list.size() > 0) {
            msg = "获取种类-食物成功";
            resObject.setMsg(msg);
            resObject.setObj(list);
            return resObject;
        }
        return resObject;
    }

    /**
     * 获取全部食物类型
     *
     * @param
     * @author wenxian.cai
     * @DateTime 2016年8月16日下午8:35:28
     * @serverComment
     */
    @RequestMapping(params = "selectAllFoodKind", method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxJson selectAllFoodKind(@RequestBody Map<String, Object> data) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取全部食物类型失败";
        resObject.setMsg(msg);
        List<TDataFoodKind> list = IService.selectAllFoodKind();
        if (list.size() > 0) {
            msg = "获取全部食物类型成功";
            resObject.setMsg(msg);
            resObject.setObj(list);
            return resObject;
        }
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月6日下午2:23:02
     * @serverComment 获取运动能量
     * @param 
     */
    @RequestMapping(params = "selectSportEnergyByUserIdWithDate", method = RequestMethod.POST)
    public @ResponseBody AjaxJson selectSportEnergyByUserIdWithDate(@RequestBody Map<String, Object> map) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取运动能量失败";
        int userId = getLoginUser().getId();
        String endDate = (String) map.get("date");
        String StartDate = "";
        StartDate =  DateTimeUtilT.date(DateTimeUtilT.addDay(DateTimeUtilT.date(endDate), -6));
        List<TRecordSport> list = sportService.selectSportEnergyByUserIdWithDate(userId, true, StartDate, endDate);
        if (list.size() == 0) {
			msg = "暂未有记录";
		}else{
			msg = "获取运动能量成功";
		}
        resObject.setSuccess(true);
        resObject.setObj(list);
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月6日下午2:22:49
     * @serverComment 获取所有运动
     * @param 
     */
    @RequestMapping(params = "getAllSports", method = RequestMethod.POST)
    public @ResponseBody AjaxJson getAllSports() throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取所有运动失败";
        List<TDataSportKind> list = sportService.selectAllSports();
        if (list.size() == 0) {
			msg = "暂未有记录";
		}else{
			msg = "获取所有运动成功";
		}
        resObject.setObj(list);
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月6日下午2:22:35
     * @serverComment 添加运动
     * @param 
     */
	@RequestMapping(params = "addSport", method = RequestMethod.POST)
    public @ResponseBody AjaxJson addSport(@RequestBody String json) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "添加运动成功";
        JSONObject root = JSONObject.parseObject(json);
        com.alibaba.fastjson.JSONArray mm = root.getJSONArray("sportItem");
        String recordDate = root.getString("recordDate");
		String sportTime = root.getString("sportTime");
		int userId = getLoginUser().getId();
		List<TRecordSportDetail> details = new ArrayList<>();
		for (int i = 0; i < mm.size(); i++) {
			TRecordSportDetail detail = new TRecordSportDetail();
			detail.setDuration(mm.getJSONObject(i).getInteger("duration"));
			detail.setSportId(mm.getJSONObject(i).getInteger("sportId"));
			details.add(detail);
		}
		TRecordSport recordSport = new TRecordSport();
		recordSport.setCreateDate(new Date());
		recordSport.setRecordDate(DateTimeUtilT.date(recordDate));
		recordSport.setStartTime(DateTimeUtilT.StringToTime(sportTime, "hh:mm:ss"));
		recordSport.setDetails(details);
		recordSport.setUserId(userId);
		int result = sportService.addSport(recordSport);
        if (result > 0) {
			msg = "添加运动成功";
		}
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月6日下午2:47:11
     * @serverComment 获取运动
     * @param 
     */
    @RequestMapping(params = "getSportWithDate", method = RequestMethod.POST)
    public @ResponseBody AjaxJson getSportWithDate(@RequestBody Map<String, Object> map) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取运动失败";
        int userId = getLoginUser().getId();
        String recordDate = (String) map.get("recordDate");
        List<Map<String, Object>> records = new ArrayList<>();
		List<TRecordSport> recordSports = sportService.selectTRecordSportWithDate(userId, recordDate);
		for (TRecordSport recordSport : recordSports) {
			Map<String, Object> tMap = new HashMap<>();
			tMap.put(RecordSport.ID, recordSport.getId());
			tMap.put(RecordSport.START_TIME, recordSport.getStartTime());
			tMap.put(RecordSport.ENERGY, recordSport.getEnergy());
			List<Map<String, Object>> tList = new ArrayList<>();
			List<TRecordSportDetail> details = recordSport.getDetails();
			for (TRecordSportDetail detail : details) {
				Map<String, Object> tMap2 = new HashMap<>();
				tMap2.put("duration", detail.getDuration());
				tMap2.put(Sport.NAME, detail.getSport().getName());
				tMap2.put("detailId", detail.getId());
				tMap2.put("sportId", detail.getSport().getId());
				tList.add(tMap2);
			}
			tMap.put(RecordSportDetail.DETAILS, tList);
			records.add(tMap);
		}
		if (records.size() > 0) {
			msg = "获取运动成功";
			resObject.setObj(records);
	        resObject.setMsg(msg);
	        resObject.setSuccess(true);
	        return resObject;
		}
		resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月10日下午3:32:35
     * @serverComment 删除运动记录
     * @param 
     */
    @RequestMapping(params = "delSportByRecordSportId", method = RequestMethod.POST)
    public @ResponseBody AjaxJson delSportByRecordSportId(@RequestParam(value = "recordSportId") Integer  recordSportId) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除运动失败";
        boolean bool = sportService.delSportByRecordSportId(recordSportId);
        if (bool) {
			msg = "删除运动成功";
			resObject.setSuccess(true);
		}
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月10日下午3:33:03
     * @serverComment 修改运动记录
     * @param 
     */
    @RequestMapping(params = "modifySport", method = RequestMethod.POST)
    public @ResponseBody AjaxJson modifySport(@RequestBody SportsDTO sportsDTO) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "修改运动失败";
        
       /* JSONObject root = JSONObject.parseObject(json);
        com.alibaba.fastjson.JSONArray mm = root.getJSONArray("sportItem");
		String sportTime = root.getString("sportTime");
		int recordId = root.getInteger("recordId");
		int userId = getLoginUser().getId();
		List<TRecordSportDetail> list = new ArrayList<>();
		for(int i = 0; i < mm.size(); i ++){
			TRecordSportDetail detail = new TRecordSportDetail();
			detail.setRecordId(recordId);
			detail.setDuration(mm.getJSONObject(i).getInteger("duration"));
			detail.setSportId(mm.getJSONObject(i).getInteger("sportId"));
			if (mm.getJSONObject(i).getString("operation").equals("modify")) {	//修改
				detail.setId(mm.getJSONObject(i).getInteger("detailId"));
			}else {		//增加
				detail.setId(null);
			}
			list.add(detail);
		}*/
		boolean bool = sportService.updateSport(sportsDTO);
		if (bool) {
			msg = "修改运动成功";
			resObject.setSuccess(true);
		}
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月10日下午4:19:48
     * @serverComment 删除活动记录里的单个活动
     * @param 
     */
    @RequestMapping(params = "delSportDetail", method = RequestMethod.POST)
    public @ResponseBody AjaxJson delSportDetail(@RequestParam(value = "detailId") Integer  detailId) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "删除运动失败";
        boolean bool = sportService.delSportDetail(detailId);
        if (bool) {
			msg = "删除运动成功";
			resObject.setSuccess(true);
		}
        resObject.setMsg(msg);
        return resObject;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月11日上午11:25:09
     * @serverComment 能量分析
     * @param 
     */
    @RequestMapping(params = "energyAnalyze", method = RequestMethod.POST)
    public @ResponseBody AjaxJson energyAnalyze(@RequestParam(value = "recordDate") String  recordDate) throws Exception {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        String msg = "获取能量失败";
        int userId = getLoginUser().getId();
        
        EnergyAnalyzeDTO energyAnalyze = sportService.getEnergyAnalyze(userId, recordDate);
        resObject.setObj(energyAnalyze);
        if (energyAnalyze.getErrorMessage() == null) {
        	if (!energyAnalyze.getBasicMetabolism().equals(Math.abs(energyAnalyze.getBasicMetabolismDeviation())) && 
            		!energyAnalyze.getRequiredEnergy().equals(Math.abs(energyAnalyze.getRequiredEnergyDeviation()))) {
            	msg = "获取能量分析成功";
            	resObject.setMsg(msg);
            	resObject.setSuccess(true);
            	return resObject;
    		}
		}
        resObject.setMsg(msg);
        return resObject;
    }
}
