package com.lifeshs.service.device.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureBloodpressure;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthStandard.BaseMemberDo;
import com.lifeshs.pojo.healthStandard.HealthStandardValue;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.utils.DateTimeUtilT;

/**
 * 血压计业务类
 * 
 * @author dengfeng
 * 
 */
@Component("bloodPressure")
public class BloodPressure extends MeasureDevice<TMeasureBloodpressure> implements IMeasureDevice<Map<String, Object>> {

    private HealthPackageType deviceType = HealthPackageType.BloodPressure;

    @Override
    protected void deviantHandling(TMeasureBloodpressure bloodpressureEntity) throws OperationException {
        int userId = bloodpressureEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,bloodpressureEntity.getMeasureDate());

        int diastolic = bloodpressureEntity.getDiastolic();
        int heartRate = bloodpressureEntity.getHeartRate();
        int systolic = bloodpressureEntity.getSystolic();
        // 获取各参数的正常范围最大值最小值
        String diastolicArea = bloodpressureEntity.getDiastolicArea();
        String heartRateArea = bloodpressureEntity.getHeartRateArea();
        String systolicArea = bloodpressureEntity.getSystolicArea();
        // 判断用户的异常
        // 2017年6月26日11:07:21 取消
        /** 手动录入数据，不需要发送短信 */
        /*if (HealthDataType.MANUAL.getValue().equals(bloodpressureEntity.getDataType())) {
//            logger.info("手动录入数据不需要发送短信");
            return;
        }*/
        String name = user.getRealName();
        if (StringUtils.isBlank(name)) {
            name = user.getUserName();
        }
        
        if (bloodpressureEntity.getStatus() != 0) {
            String detail = "";
            // 判断心率是否异常
            if ((bloodpressureEntity.getStatus() | HealthType.heartRate.value()) == bloodpressureEntity.getStatus()) {
                detail = detail + "、心率值为" + heartRate + " " + compareArea(heartRate, heartRateArea) + "正常范围值(" + heartRateArea + "bpm)";
            }
            // 判断收缩压是否异常
            if ((bloodpressureEntity.getStatus() | HealthType.systolic.value()) == bloodpressureEntity.getStatus()) {
                detail = detail + "、收缩压为" + systolic + " " + compareArea(systolic, systolicArea) + "正常范围值(" + systolicArea + "mmHg)";
            }
            // 判断舒张压是否异常
            if ((bloodpressureEntity.getStatus() | HealthType.diastolic.value()) == bloodpressureEntity.getStatus()) {
                detail = detail + "、舒张压" + diastolic + " " + compareArea(diastolic, diastolicArea) + "正常范围值(" + diastolicArea + "mmHg)";
            }
            super.sendWarningSMS(userId, name, detail.substring(1, detail.length()), deviceType, "血压测量结果",
                    bloodpressureEntity.getStatus());
        }

    }

    @Override
    protected void perfectData(TMeasureBloodpressure deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean userSex = baseMemberDo.isSex();
        int userAge = baseMemberDo.getAge();

        HealthStandardValue<Integer> diastolicStandard = HealthStandard.getDiastolic(userSex, userAge);
        HealthStandardValue<Integer> systolicStandard = HealthStandard.getSystolic(userSex, userAge);
        HealthStandardValue<Integer> heartRateStandard = HealthStandard.getHeartRate(userSex, userAge);

        Float diastolicMin = diastolicStandard.getMin().floatValue();
        Float diastolicMax = diastolicStandard.getMax().floatValue();
        Float systolicMin = systolicStandard.getMin().floatValue();
        Float systolicMax = systolicStandard.getMax().floatValue();
        Float heartRateMin = heartRateStandard.getMin().floatValue();
        Float heartRateMax = heartRateStandard.getMax().floatValue();

        // 舒张压
        String diastolicArea = getArea(diastolicMin, diastolicMax);
        deviceEntity.setDiastolicArea(diastolicArea);
        // 收缩压
        String systolicArea = getArea(systolicMin, systolicMax);
        deviceEntity.setSystolicArea(systolicArea);
        // 心率
        String heartRateArea = getArea(heartRateMin, heartRateMax);
        deviceEntity.setHeartRateArea(heartRateArea);

        Integer diastolic = deviceEntity.getDiastolic();
        Integer systolic = deviceEntity.getSystolic();
        Integer heartRate = deviceEntity.getHeartRate();

        // 获取状态
        long status = 0;
        status = getStatus(diastolicMin, diastolicMax, diastolic.floatValue(), status, HealthType.diastolic.value());
        status = getStatus(systolicMin, systolicMax, systolic.floatValue(), status, HealthType.systolic.value());
        status = getStatus(heartRateMin, heartRateMax, heartRate.floatValue(), status, HealthType.heartRate.value());
        deviceEntity.setStatus(status);

        HealthRank systolicStatus = getHealthValueStatus(systolicMin, null, null, systolicMax, systolic.floatValue());
        HealthRank diastolicStatus = getHealthValueStatus(diastolicMin, null, null, diastolicMax,
                diastolic.floatValue());
        HealthRank heartRateStatus = getHealthValueStatus(heartRateMin, null, null, heartRateMax,
                heartRate.floatValue());

        String diastolicStatusDescription = "";
        String systolicStatusDescription = "";
        String heartRateStatusDescription = "";
        /** 获取血压计健康描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(userSex, userAge, deviceType.value(),
                null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.systolic.value())
                    && descriptionStatus.equals(systolicStatus.getRankValue())) {
                systolicStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.diastolic.value())
                    && descriptionStatus.equals(diastolicStatus.getRankValue())) {
                diastolicStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.heartRate.value())
                    && descriptionStatus.equals(heartRateStatus.getRankValue())) {
                heartRateStatusDescription = descriptionText;
                continue;
            }
        }
        // 描述
        deviceEntity.setDiastolicStatusDescription(diastolicStatusDescription);
        deviceEntity.setSystolicStatusDescription(systolicStatusDescription);
        deviceEntity.setHeartRateStatusDescription(heartRateStatusDescription);
        // 状态保存
        deviceEntity.setDiastolicStatus(diastolicStatus);
        deviceEntity.setSystolicStatus(systolicStatus);
        deviceEntity.setHeartRateStatus(heartRateStatus);

        boolean bloodPressureStatus = false;
        if (!diastolicStatus.equals(HealthRank.normal)) {
            bloodPressureStatus = true;
        }
        if (!systolicStatus.equals(HealthRank.normal) || !bloodPressureStatus) {
            bloodPressureStatus = true;
        }
        // 添加记录
        memberService.addBloodPressureRecord(userId, diastolic, systolic, bloodPressureStatus, heartRate,
                !heartRateStatus.equals(HealthRank.normal));
    }

    @Override
    protected boolean checkStatus(TMeasureBloodpressure entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureBloodpressure entity) {
        if (entity.getStatus() == null)
            return true;
        return false;
    }

    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectBloodPressureWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectBloodPressureWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectBloodPressureWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectBloodPressureWithThreeMonth(params);
            break;
        default:
            break;
        }

        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        for (int i = 0; i < list.size() - 1; i++) {
            HashMap<String, Object> map = new LinkedHashMap<String, Object>(); // 必须使用LinkedHashMap(有序)
            for (int j = i + 1; j < list.size(); j++) {
                if (DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate"))
                        .equals(DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate")))) {
                    if (list.get(j).get("status").equals(0)) { // 去除正常数据
                        list.remove(j);
                        j--; // 每移除一个元素以后再把i移回来
                    } else {
                        list.remove(i);
                        j--;
                        map.put(j + "", list.get(j));
                    }
                }
            }
            int k = 0;
            for (String key : map.keySet()) {
                if (map.size() == 1) {
                    continue;
                }
                list.remove(Integer.parseInt(key) - k);
                k++;
            }
        }
        return list;
    }

    @Override
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int nowPage,
            int pageSize) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureBloodpressure.class, conditionMap);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(nowPage, pageSize, totalSize);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();

        pagination.setTotalPage(totalPage);
        pagination.setNowPage(nowPage);
        if (PaginationDTO.isDataOverFlow(nowPage, pageSize, totalSize)) {
            pagination.setData(list);
            return pagination;
        }

        conditionMap.put("start", startIndex);
        conditionMap.put("pageSize", pageSize);
        list = deviceDao.selectBloodPressureWithSplit(conditionMap);

        // 封装数据
        pagination.setData(list);
        return pagination;
    }

}
