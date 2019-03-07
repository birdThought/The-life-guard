package com.lifeshs.service.device.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lifeshs.dao1.measure.GluCometerDao;
import com.lifeshs.po.GluCometerPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureGlucometer;
import com.lifeshs.pojo.healthDescription.BloodSugarDTO;
import com.lifeshs.pojo.healthStandard.HealthStandardValueEx;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.utils.DateTimeUtilT;

/**
 * 版权归 TODO 血糖设备服务
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月20日 上午11:10:53
 */

@Component("glucometer")
public class Glucometer extends MeasureDevice<TMeasureGlucometer> implements IMeasureDevice<Map<String, Object>> {

    private final HealthPackageType deviceType = HealthPackageType.Glucometer;

    @Autowired
    GluCometerDao gluCometerDao;
    @Override
    protected void deviantHandling(TMeasureGlucometer deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,deviceEntity.getMeasureDate());
        /** 异常处理 */
        // 2017年6月26日11:07:21 取消
        /** 手动录入数据，不需要发送短信 */
        /*if (HealthDataType.MANUAL.getValue().equals(deviceEntity.getDataType())) {
            return ;
        }*/
        String name = user.getRealName();
        if (StringUtils.isBlank(name)) {
            name = user.getUserName();
        }
        // 如果用户设置了预警提醒
        if (deviceEntity.getStatus() != 0) {
            float bloodSugar = deviceEntity.getBloodSugar();
            String bloodSugarArea = deviceEntity.getBloodSugarArea();
            String measureType = getMeasureType(deviceEntity.getMeasureType());

            String detail = measureType + "的血糖值为" + bloodSugar + " " + compareArea(bloodSugar, bloodSugarArea) + "正常范围(" + bloodSugarArea + "mmol/L)";
            super.sendWarningSMS(userId, name, detail, deviceType, "血糖测量结果", deviceEntity.getStatus());
        }
    }

    private String getMeasureType(int measureType) {
        String measureType_s = "";
        switch (measureType) {
        case 1:
            measureType_s = "早餐前";
            break;
        case 2:
            measureType_s = "早餐后";
            break;
        case 3:
            measureType_s = "午餐前";
            break;
        case 4:
            measureType_s = "午餐后";
            break;
        case 5:
            measureType_s = "晚餐前";
            break;
        case 6:
            measureType_s = "晚餐后";
            break;
        }
        return measureType_s;
    }

    @Override
    protected void perfectData(TMeasureGlucometer deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        
        HealthStandardValueEx<String> glucometerHealthStandard = HealthStandard.getBloodSugar();

        Integer measureType = deviceEntity.getMeasureType();
        Boolean isBeforeMeal = measureType % 2 == 0 ? false : true;

        Float min = 0.0f;
        Float max = 0.0f;

        Float bloodSugar = deviceEntity.getBloodSugar();

        if (isBeforeMeal) {
            min = Float.valueOf(glucometerHealthStandard.getLess());
            max = Float.valueOf(glucometerHealthStandard.getMore());
        } else {
            min = Float.valueOf(glucometerHealthStandard.getMin());
            max = Float.valueOf(glucometerHealthStandard.getMax());
        }
        String bloodSugarArea = getArea(min, max);
        deviceEntity.setBloodSugarArea(bloodSugarArea);

        Long status = getStatus(min, max, bloodSugar, 0, HealthType.bloodSugar.value());
        deviceEntity.setStatus(status);

        /** 默认用户糖尿病状态为正常 */
        HealthRank bloodSugarStatus = getHealthValueStatus(min, null, null, max, bloodSugar);

        String bloodSugarStatusDescription = "";
        List<BloodSugarDTO> descriptions = healthDescriptionDao.listBloodSuagarDescription(0, bloodSugarStatus.getRankValue(), recordDTO.getGender());
        for (BloodSugarDTO description : descriptions) {
            Integer descriptionStatus = description.getStatus();
            String descriptionText = description.getDescription();
            if (bloodSugarStatus.equals(descriptionStatus)) {
                bloodSugarStatusDescription = descriptionText;
                break;
            }
        }

        deviceEntity.setBloodSugarStatusDescription(bloodSugarStatusDescription);
        deviceEntity.setBloodSugarStatus(bloodSugarStatus);
    }

    @Override
    protected boolean checkStatus(TMeasureGlucometer entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureGlucometer entity) {
        if (entity.getStatus() == null)
            return true;
        return false;
    }

    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        if (DateTimeUtilT.valiDateTimeWithShortFormat(dateType)) { // 若为"2016-01"时间格式，则将dataType作为查询条件
            params.put("measureDate", dateType);
            dateType = "SPECIAL_MONTH";
        }
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectGlucometerWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectGlucometerWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectGlucometerWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectGlucometerWithThreeMonth(params);
            break;
        case "SPECIAL_MONTH":
            // 获取具体月份全部数据
            list = deviceDao.selectGlucometerWithSpecialMonth(params);
            break;
        default:
            break;
        }

        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        for (int i = 0; i < list.size() - 1; i++) {
            String measureDate_i_s = (DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate")));
            Object measureType_i = list.get(i).get("measureType");
            HashMap<String, Object> list_3 = new LinkedHashMap<String, Object>();
            for (int j = i + 1; j < list.size(); j++) { // 20 19 18 17
                String measureDate_j_s = DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate"));
                Object measureType_j = list.get(j).get("measureType");
                if ((measureDate_i_s.equals(measureDate_j_s)) && (measureType_i.equals(measureType_j))) { // 血糖特殊判断，一天可以存在测量类型不同的多条数据
                    if (list.get(j).get("status").equals(0)) { // 去除正常数据
                        list.remove(j);
                        j--; // 每移除一个元素以后再把j移回来
                    } else {
                        list.remove(i);
                        j--;
                        list_3.put(j + "", list.get(j));
                    }
                }
            }
            if (list_3.size() >= 1) {
                Iterator<Entry<String, Object>> iter = list_3.entrySet().iterator();
                int q = 0;
                while (iter.hasNext()) {
                    if (list_3.size() == 1) {
                        break;
                    }
                    Map.Entry<String, Object> entry = iter.next();
                    int key = Integer.valueOf((String) entry.getKey());
                    list.remove(key - q); // 删除每一个元素后list的大小减一
                    q++;
                }
            }
        }

        return list;
    }

    // 重载方法,加上测量类型判断
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType,
            int measureType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("measureType", measureType);
        if (DateTimeUtilT.valiDateTimeWithShortFormat(dateType)) { // 若为"2016-01"时间格式，则将dataType作为查询条件
            params.put("measureDate", dateType);
            dateType = "SPECIAL_MONTH";
        }
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectGlucometerWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectGlucometerWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectGlucometerWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectGlucometerWithThreeMonth(params);
            break;
        case "SPECIAL_MONTH":
            // 获取具体月份全部数据
            list = deviceDao.selectGlucometerWithSpecialMonth(params);
            break;
        default:
            break;
        }

        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        for (int i = 0; i < list.size() - 1; i++) {
            String measureDate_i_s = (DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate")));
            Object measureType_i = list.get(i).get("measureType");
            HashMap<String, Object> list_3 = new LinkedHashMap<String, Object>();
            for (int j = i + 1; j < list.size(); j++) { // 20 19 18 17
                String measureDate_j_s = DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate"));
                Object measureType_j = list.get(j).get("measureType");
                if ((measureDate_i_s.equals(measureDate_j_s)) && (measureType_i.equals(measureType_j))) { // 血糖特殊判断，一天可以存在测量类型不同的多条数据
                    if (list.get(j).get("status").equals(0)) { // 去除正常数据
                        list.remove(j);
                        j--; // 每移除一个元素以后再把j移回来
                    } else {
                        list.remove(i);
                        j--;
                        list_3.put(j + "", list.get(j));
                    }
                }
            }
            Iterator<Entry<String, Object>> iter = list_3.entrySet().iterator();
            int q = 0;
            while (iter.hasNext() && ((list_3.size() > 1))) {
                Map.Entry<String, Object> entry = iter.next();
                int key = Integer.valueOf((String) entry.getKey());
                list.remove(key - q); // 删除每一个元素后list的大小减一
                q++;
            }
        }

        return list;
    }

    /*
     * 重载方法,添加measureType(测量类型)条件
     */
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int nowPage, int pageSize,
            int measureType) {
        return listMeasureDataWithSplit(userId, deviceType, nowPage, pageSize, measureType);
    }

    @Override
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int pageIndex, int pageSize) {
        return listMeasureDataWithSplit(userId, deviceType, pageIndex, pageSize, null);
    }

    private PaginationDTO<Map<String, Object>> listMeasureDataWithSplit(int userId, String deviceType, int curPage, int pageSize,
            Integer measureType) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        if (measureType != null) {
            conditionMap.put("measureType", measureType);
        }
        int totalSize = commonTrans.getCount(TMeasureGlucometer.class, conditionMap);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        
        pagination.setTotalPage(totalPage);
        pagination.setNowPage(curPage);
        if (PaginationDTO.isDataOverFlow(curPage, pageSize, totalSize)) {
            pagination.setData(list);
            return pagination;
        }

        conditionMap.put("start", startIndex);
        conditionMap.put("pageSize", pageSize);
        list = deviceDao.selectGlucometerWithSplit(conditionMap);
        // 封装数据
        pagination.setData(list);
        return pagination;
    }

    public GluCometerPO getLastestData(int userId) {
        return gluCometerDao.getLastestData(userId);
    }
}
