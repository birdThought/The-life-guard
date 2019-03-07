package com.lifeshs.service.device.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureOxygen;
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
 * 
 * 版权归 TODO 血氧计业务类
 * 
 * @author dachang.luo
 * @DateTime 2016-5-20 上午10:45:23
 */
@Component("oxygen")
public class Oxygen extends MeasureDevice<TMeasureOxygen> implements IMeasureDevice<Map<String, Object>> {

    private HealthPackageType deviceType = HealthPackageType.Oxygen;

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-20 上午10:11:47
     * @serverCode 服务代码
     * @serverComment 异常(数据走出正常值范围)处理
     * @param bloodpressureEntity
     *            终端传过来的数据
     * @see MeasureDevice#deviantHandling(java.lang.Object)
     */
    @Override
    protected void deviantHandling(TMeasureOxygen oxygeneEntity) throws OperationException {
        int userId = oxygeneEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,oxygeneEntity.getMeasureDate());

        String detail = "";
        // 获取用户的测量数据
        int heartRate = oxygeneEntity.getHeartRate();
        int saturation = oxygeneEntity.getSaturation();

        // 获取用户参数正常范围
        String heartRateArea = oxygeneEntity.getHeartRateArea();
        String saturationArea = oxygeneEntity.getSaturationArea();
        Long status = oxygeneEntity.getStatus(); // status
        // 异常处理
        // 2017年6月26日11:07:21 取消
        /** 手动录入数据，不需要发送短信 */
        /*if (HealthDataType.MANUAL.getValue().equals(oxygeneEntity.getDataType())) {
            return;
        }*/
        String name = user.getRealName();
        if (StringUtils.isBlank(name)) {
            name = user.getUserName();
        }
        if (status != 0) {
            // 判断血氧饱和度是否异常
            if ((status | HealthType.saturation.value()) == status) {
                detail = detail + "、血氧饱和度为" + saturation + compareArea(saturation, saturationArea) + "正常范围值(" + saturationArea + "%)";
            }
            // 判断心率是否异常
            if ((status | HealthType.heartRate.value()) == status) {
                detail = detail + "、心率值为" + heartRate + compareArea(heartRate, heartRateArea) + "正常范围值(" + heartRateArea + "bpm)";
            }
            super.sendWarningSMS(userId, name, detail.substring(1, detail.length()), deviceType, "血氧测量结果",
                    oxygeneEntity.getSaturation());
        }

    }

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-20 下午02:28:37
     * @serverCode 服务代码
     * @serverComment 用于补全终端传过来的数据的正常范围值
     * @param oxygeneEntity
     *            终端传过来的血氧计数据
     * @see MeasureDevice#perfectData(java.lang.Object)
     */
    @Override
    protected void perfectData(TMeasureOxygen deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean sex = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();

        HealthStandardValue<Integer> heartRateStandard = HealthStandard.getHeartRate(sex, age);
        HealthStandardValue<Integer> saturationStandard = HealthStandard.getSaturation();

        Float heartRateMin = heartRateStandard.getMin().floatValue();
        Float heartRateMax = heartRateStandard.getMax().floatValue();

        Float saturationMin = saturationStandard.getMin().floatValue();
        Float saturationMax = saturationStandard.getMax().floatValue();

        // 获取心率信息
        String heartRateArea = getArea(heartRateMin, heartRateMax);
        deviceEntity.setHeartRateArea(heartRateArea);

        // 获取血氧饱和度
        String saturationArea = getArea(saturationMin, saturationMax);
        deviceEntity.setSaturationArea(saturationArea);

        // 获取测量状态值
        Integer heartRate = deviceEntity.getHeartRate();
        Integer saturation = deviceEntity.getSaturation();

        Long status = 0l;

        status = getStatus(heartRateMin, heartRateMax, heartRate.floatValue(), status, HealthType.heartRate.value());
        status = getStatus(saturationMin, saturationMax, saturation.floatValue(), status,
                HealthType.saturation.value());

        // 设置状态仪的测量状态
        deviceEntity.setStatus(status);

        /** 血氧仪各项参数的状态 */
        HealthRank heartRateStatus = getHealthValueStatus(heartRateMin, null, null, heartRateMax,
                heartRate.floatValue());
        HealthRank saturationStatus = getHealthValueStatus(saturationMin, null, null, saturationMax,
                saturation.floatValue());

        /** 血氧仪各项参数的描述文字 */
        String heartRateStatusDescription = "";
        String saturationStatusDescription = "";
        /** 获取血氧仪的健康描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(sex, age, deviceType.value(), null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.saturation.value())
                    && descriptionStatus.equals(saturationStatus.getRankValue())) {
                saturationStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.heartRate.value())
                    && descriptionStatus.equals(heartRateStatus.getRankValue())) {
                heartRateStatusDescription = descriptionText;
                continue;
            }
        }

        deviceEntity.setHeartRateStatusDescription(heartRateStatusDescription);
        deviceEntity.setSaturationStatusDescription(saturationStatusDescription);

        deviceEntity.setSaturationStatus(saturationStatus);
        deviceEntity.setHeartRateStatus(heartRateStatus);

        // 添加记录
        memberService.addOxygenRecord(userId, saturation, isRankStatusUnusual(saturationStatus), heartRate, isRankStatusUnusual(heartRateStatus));
    }

    @Override
    protected boolean checkStatus(TMeasureOxygen entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureOxygen entity) {
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
            list = deviceDao.selectOxygenWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectOxygenWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectOxygenWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectOxygenWithThreeMonth(params);
            break;
        default:
            break;
        }

        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        for (int i = 0; i < list.size() - 1; i++) {
            HashMap<String, Object> list_3 = new LinkedHashMap<String, Object>();
            for (int j = i + 1; j < list.size(); j++) {
                if (DateTimeUtilT.date((Timestamp) list.get(i).get("measureDate"))
                        .equals(DateTimeUtilT.date((Timestamp) list.get(j).get("measureDate")))) {
                    if (list.get(j).get("status").equals(0)) { // 去除正常数据
                        list.remove(j);
                        j--; // 每移除一个元素以后再把i移回来
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
            // }
        }
        return list;
    }

    @Override
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int nowPage, int pageSize) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureOxygen.class, conditionMap);

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
        list = deviceDao.selectOxygenWithSplit(conditionMap);

        // 封装数据
        pagination.setData(list);
        return pagination;
    }

}
