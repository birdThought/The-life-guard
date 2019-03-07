package com.lifeshs.service.device.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.constants.common.healthPackage.DataTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureBodyfatscale;
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
 * 版权归 TODO 体脂秤业务类
 * 
 * @author dachang.luo
 * @DateTime 2016-5-18 下午04:50:55
 */
@Component("bodyfatscale")
public class Bodyfatscale extends MeasureDevice<TMeasureBodyfatscale> implements IMeasureDevice<Map<String, Object>> {

    private HealthPackageType deviceType = HealthPackageType.BodyFatScale;

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-19 下午02:34:32
     * @serverCode 服务代码
     * @serverComment 异常(数据走出正常值范围)处理
     * @param bodyfatscaleEntity
     * @see MeasureDevice#deviantHandling(java.lang.Object)
     */
    @Override
    protected void deviantHandling(TMeasureBodyfatscale bodyfatscaleEntity) throws OperationException {
        int userId = bodyfatscaleEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,bodyfatscaleEntity.getMeasureDate());
        String detail = "";
        // 获取用户体脂秤检测的各项数值测量值
        Float weight = bodyfatscaleEntity.getWeight();
        Float axungeRatio = bodyfatscaleEntity.getAxungeRatio();
        Float baseMetabolism = bodyfatscaleEntity.getBaseMetabolism();
        Float BMI = bodyfatscaleEntity.getBMI();
        Float boneWeight = axungeRatio = bodyfatscaleEntity.getBoneWeight();
        Integer bodyage = bodyfatscaleEntity.getBodyage();
        Float fatFreeWeight = bodyfatscaleEntity.getFatFreeWeight();
        Float moisture = bodyfatscaleEntity.getMoisture();
        Float muscle = bodyfatscaleEntity.getMuscle();
        Float WHR = bodyfatscaleEntity.getWHR();
        Float proteide = bodyfatscaleEntity.getProteide();
        Float visceralFat = bodyfatscaleEntity.getVisceralFat();

        // 获取各参数的正常范围最大值最小值
        String weightArea = bodyfatscaleEntity.getWeightArea();
        String axungeRatioArea = bodyfatscaleEntity.getAxungeRatioArea();
        String baseMetabolismArea = bodyfatscaleEntity.getBaseMetabolismArea();
        String BMIArea = bodyfatscaleEntity.getBMIArea();
        String boneWeightArea = bodyfatscaleEntity.getBoneWeightArea();
        String bodyageArea = bodyfatscaleEntity.getBodyageArea();
        String fatFreeWeightArea = bodyfatscaleEntity.getFatFreeWeightArea();
        String moistureArea = bodyfatscaleEntity.getMoistureArea();
        String muscleArea = bodyfatscaleEntity.getMuscleArea();
        String WHRArea = bodyfatscaleEntity.getWHRArea();
        String proteideArea = bodyfatscaleEntity.getProteideArea();
        String visceralFatArea = bodyfatscaleEntity.getVisceralFatArea();
        /** 异常处理 */
        /** 手动录入数据，不需要发送短信 */
        if (DataTypeEnum.MANUAL.value().equals(bodyfatscaleEntity.getDataType())) {
            return;
        }
        Long status = bodyfatscaleEntity.getStatus(); // status
        if (status != 0) {
            // 判断用户是否选择此项参数短信预警
            if ((user.getHealthWarning() | deviceType.value()) == user.getHealthWarning()) {
                // 判断体重是否异常
                if ((status | HealthType.weight.value()) == status) {
                    detail = detail + "、体重" + weight + "超出了正常范围值(" + weightArea + ")";
                }
                // 判断体脂肪率是否异常
                if ((status | HealthType.axungeRatio.value()) == status) {
                    detail = detail + "、体脂肪率" + axungeRatio + "超出了正常范围值(" + axungeRatioArea + ")";
                }
                // 判断BMI是否异常
                if ((status | HealthType.BMI.value()) == status) {
                    detail = detail + "、BMI" + BMI + "超出了正常范围值(" + BMIArea + ")";
                }
                // 判断WHR是否异常
                if ((status | HealthType.WHR.value()) == status) {
                    detail = detail + "、WHR" + WHR + "超出了正常范围值(" + WHRArea + ")";
                }
                // 判断去脂体重是否异常
                if ((status | HealthType.fatFreeWeight.value()) == status) {
                    detail = detail + "、去脂体重" + fatFreeWeight + "超出了正常范围值(" + fatFreeWeightArea + ")";
                }
                // 判断人体肌肉是否异常
                if ((status | HealthType.muscle.value()) == status) {
                    detail = detail + "、人体肌肉" + muscle + "超出了正常范围值(" + muscleArea + ")";
                }
                // 判断人体水分是否异常
                if ((status | HealthType.moisture.value()) == status) {
                    detail = detail + "、人体水分" + moisture + "超出了正常范围值(" + moistureArea + ")";
                }
                // 判断体年龄是否异常
                if ((status | HealthType.bodyage.value()) == status) {
                    detail = detail + "、体年龄" + bodyage + "超出了正常范围(" + bodyageArea + ")";
                }
                // 判断骨骼重量是否异常
                if ((status | HealthType.boneWeight.value()) == status) {
                    detail = detail + "、骨骼重量" + boneWeight + "超出了正常范围值(" + boneWeightArea + ")";
                }
                // 判断基础代谢是否异常
                if ((status | HealthType.baseMetabolism.value()) == status) {
                    detail = detail + "、基础代谢" + baseMetabolism + "超出了正常范围值(" + baseMetabolismArea + ")";
                }
                if ((status | HealthType.proteide.value()) == status) {
                    detail = detail + "、蛋白质" + proteide + "超出了正常范围值(" + proteideArea + ")";
                }
                if ((status | HealthType.visceralFat.value()) == status) {
                    detail = detail + "、内脏脂肪" + visceralFat + "超出了正常范围(" + visceralFatArea + ")";
                }
                /* 所有参数都不用发短信 */
                /*
                 * super.sendWarningSMS(user.getId(),user.getRealName(),detail.
                 * substring(1, detail.length()), deviceType);
                 */
            }
        }

    }

    /**
     * 
     * @author dachang.luo
     * @DateTime 2016-5-19 下午03:34:50
     * @serverCode 服务代码
     * @serverComment 完善数据
     * @param bodyfatscaleEntity
     * @see MeasureDevice#perfectData(java.lang.Object)
     */
    @Override
    protected void perfectData(TMeasureBodyfatscale deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean sex = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();
        Float height = baseMemberDo.getHeight();
        Float weight = deviceEntity.getWeight();
        
        if (weight == null) {
            weight = baseMemberDo.getWeight();
        }

        HealthStandardValue<Integer> axungeRatioStandard = HealthStandard.getAxungeRatio(sex, age);
        HealthStandardValue<String> WHRStandard = HealthStandard.getWHR(sex);
        HealthStandardValue<Integer> muscleStandard = HealthStandard.getMuscle(sex);
        HealthStandardValue<Integer> moistureStandard = HealthStandard.getMoisture(sex);
        HealthStandardValue<String> boneWeightStandard = HealthStandard.getBoneWeight(sex, weight);
        HealthStandardValue<String> baseMetabolismStandard = HealthStandard.getBaseMetabolism(sex, age, weight, height);
        HealthStandardValue<String> BMIStandard = HealthStandard.getBMI();
        HealthStandardValue<Integer> fatFreeWeightStandard = HealthStandard.getFatFreeWeight();
        HealthStandardValue<Integer> bodyageStandard = HealthStandard.getBodyage(age);
        HealthStandardValue<Integer> proteideStandard = HealthStandard.getProteideArea(sex);
        HealthStandardValue<Integer> visceralFatStandard = HealthStandard.getVisceralFat();
        HealthStandardValue<Integer> weightStandard = HealthStandard.getWeight(age, height);

        Float weightMin = weightStandard.getMin().floatValue();
        Float weightMax = weightStandard.getMax().floatValue();
        Float axungeRatioMin = axungeRatioStandard.getMin().floatValue();
        Float axungeRatioMax = axungeRatioStandard.getMax().floatValue();
        Float WHRMin = Float.valueOf(WHRStandard.getMin());
        Float WHRMax = Float.valueOf(WHRStandard.getMax());
        Float muscleMin = muscleStandard.getMin().floatValue();
        Float muscleMax = muscleStandard.getMax().floatValue();
        Float moistureMin = moistureStandard.getMin().floatValue();
        Float moistureMax = moistureStandard.getMax().floatValue();
        Float boneWeightMin = Float.valueOf(boneWeightStandard.getMin());
        Float boneWeightMax = Float.valueOf(boneWeightStandard.getMax());
        Float baseMetabolismMin = Float.valueOf(baseMetabolismStandard.getMin());
        Float baseMetabolismMax = Float.valueOf(baseMetabolismStandard.getMax());
        Float BMIMin = Float.valueOf(BMIStandard.getMin());
        Float BMIMax = Float.valueOf(BMIStandard.getMax());
        Float fatFreeWeightMin = fatFreeWeightStandard.getMin().floatValue();
        Float fatFreeWeightMax = fatFreeWeightStandard.getMax().floatValue();
        Float bodyageMin = bodyageStandard.getMin().floatValue();
        Float bodyageMax = bodyageStandard.getMax().floatValue();

        Float proteideMin = proteideStandard.getMin().floatValue();
        Float proteideMax = proteideStandard.getMax().floatValue();
        Float visceralFatMin = visceralFatStandard.getMin().floatValue();
        Float visceralFatMax = visceralFatStandard.getMax().floatValue();

        // 获取信息
        String axungeRatioArea = getArea(axungeRatioMin, axungeRatioMax);
        deviceEntity.setAxungeRatioArea(axungeRatioArea);

        String wHRArea = getArea(WHRMin, WHRMax);
        deviceEntity.setWHRArea(wHRArea);

        String bMIArea = getArea(BMIMin, BMIMax);
        deviceEntity.setBMIArea(bMIArea);

        String fatFreeWeightArea = getArea(fatFreeWeightMin, fatFreeWeightMax);
        deviceEntity.setFatFreeWeightArea(fatFreeWeightArea);

        String muscleArea = getArea(muscleMin, muscleMax);
        deviceEntity.setMuscleArea(muscleArea);

        String moistureArea = getArea(moistureMin, moistureMax);
        deviceEntity.setMoistureArea(moistureArea);

        String boneWeightArea = getArea(boneWeightMin, boneWeightMax);
        deviceEntity.setBoneWeightArea(boneWeightArea);

        String bodyageArea = getArea(bodyageMin, bodyageMax);
        deviceEntity.setBodyageArea(bodyageArea);

        String baseMetabolismArea = getArea(baseMetabolismMin, baseMetabolismMax);
        deviceEntity.setBaseMetabolismArea(baseMetabolismArea);

        String proteideArea = getArea(proteideMin, proteideMax);
        deviceEntity.setProteideArea(proteideArea);

        String visceralFatArea = getArea(visceralFatMin, visceralFatMax);
        deviceEntity.setVisceralFatArea(visceralFatArea);

        String weightArea = getArea(weightMin, weightMax);
        deviceEntity.setWeightArea(weightArea);

        // Float weight = deviceEntity.getWeight();
        Float axungeRatio = deviceEntity.getAxungeRatio();
        Float baseMetabolism = deviceEntity.getBaseMetabolism();
        Float BMI = deviceEntity.getBMI();
        Float boneWeight = deviceEntity.getBoneWeight();
        Integer bodyage = deviceEntity.getBodyage();
        Float fatFreeWeight = deviceEntity.getFatFreeWeight();
        Float moisture = deviceEntity.getMoisture();
        Float muscle = deviceEntity.getMuscle();
        Float WHR = deviceEntity.getWHR();

        Float proteide = deviceEntity.getProteide();
        Float visceralFat = deviceEntity.getVisceralFat();

        if (baseMetabolism == null) {
            baseMetabolism = calculateBaseMetabolism(recordDTO.getWeight(), recordDTO.getHeight(), recordDTO.getBirthday(), recordDTO.getGender());
        }
        
        Long status = 0l;

        status = getStatus(weightMin, weightMax, weight, status, HealthType.weight.value());
        if (axungeRatio != null) {
            status = getStatus(axungeRatioMin, axungeRatioMax, axungeRatio, status, HealthType.axungeRatio.value());
        }
        if (baseMetabolism != null) {
            status = getStatus(baseMetabolismMin, baseMetabolismMax, baseMetabolism.floatValue(), status,
                    HealthType.baseMetabolism.value());
        }
        if (BMI != null) {
            status = getStatus(BMIMin, BMIMax, BMI, status, HealthType.BMI.value());
        }
        if (boneWeight != null) {
            status = getStatus(boneWeightMin, boneWeightMax, boneWeight, status, HealthType.boneWeight.value());
        }
        if (bodyage != null) {
            status = getStatus(bodyageMin, bodyageMax, bodyage.floatValue(), status, HealthType.bodyage.value());
        }
        if (fatFreeWeight != null) {
            status = getStatus(fatFreeWeightMin, fatFreeWeightMax, fatFreeWeight, status, HealthType.fatFreeWeight.value());
        }
        if (moisture != null) {
            status = getStatus(moistureMin, moistureMax, moisture, status, HealthType.moisture.value());
        }
        if (muscle != null) {
            status = getStatus(muscleMin, muscleMax, muscle, status, HealthType.muscle.value());
        }
        if (WHR != null) {
            status = getStatus(WHRMin, WHRMax, WHR, status, HealthType.WHR.value());
        }
        if (proteide != null) {
            status = getStatus(proteideMin, proteideMax, proteide, status, HealthType.proteide.value());
        }
        if (visceralFat != null) {
            status = getStatus(visceralFatMin, visceralFatMax, visceralFat, status, HealthType.visceralFat.value());
        }
        deviceEntity.setStatus(status);

        /** 体脂秤各项参数的的状态值 */
        HealthRank weightStatus = HealthRank.normal;
        if (weight != null) {
            weightStatus = getHealthValueStatus(weightMin, null, null, weightMax, weight);
        }
        HealthRank axungeRatioStatus = HealthRank.normal;
        if (axungeRatio != null) {
            axungeRatioStatus = getHealthValueStatus(axungeRatioMin, null, null, axungeRatioMax, axungeRatio);
        }
        HealthRank baseMetabolismStatus = HealthRank.normal;
        if (baseMetabolism != null) {
            baseMetabolismStatus = getHealthValueStatus(baseMetabolismMin, null, null, baseMetabolismMax,
                    baseMetabolism.floatValue());
        }
        HealthRank bMIStatus = HealthRank.normal;
        if (BMI != null) {
            bMIStatus = getHealthValueStatus(BMIMin, null, null, BMIMax, BMI);
        }
        HealthRank boneWeightStatus = HealthRank.normal;
        if (boneWeight != null) {
            boneWeightStatus = getHealthValueStatus(boneWeightMin, null, null, boneWeightMax, boneWeight);
        }
        HealthRank bodyageStatus = HealthRank.normal;
        if (bodyage != null) {
            bodyageStatus = getHealthValueStatus(bodyageMin, null, null, bodyageMax, bodyage.floatValue());
        }
        HealthRank fatFreeWeightStatus = HealthRank.normal;
        if (fatFreeWeight != null) {
            fatFreeWeightStatus = getHealthValueStatus(fatFreeWeightMin, null, null, fatFreeWeightMax,
                    fatFreeWeight);
        }
        HealthRank moistureStatus = HealthRank.normal;
        if (moisture != null) {
            moistureStatus = getHealthValueStatus(moistureMin, null, null, moistureMax, moisture);
        }
        HealthRank muscleStatus = HealthRank.normal;
        if (muscle != null) {
            muscleStatus = getHealthValueStatus(muscleMin, null, null, muscleMax, muscle);
        }
        HealthRank wHRStatus = HealthRank.normal;
        if (WHR != null) {
            wHRStatus = getHealthValueStatus(WHRMin, null, null, WHRMax, WHR);
        }
        HealthRank proteideStatus = HealthRank.normal;
        if (proteide != null) {
            proteideStatus = getHealthValueStatus(proteideMin, null, null, proteideMax, proteide);
        }
        HealthRank visceralFatStatus = HealthRank.normal;
        if (visceralFat != null) {
            visceralFatStatus = getHealthValueStatus(visceralFatMin, null, null, visceralFatMax, visceralFat);
        }

        /** 体脂秤各项参数的健康描述文字 */
        String weightStatusDescription = "";
        String axungeRatioStatusDescription = "";
        String baseMetabolismStatusDescription = "";
        String bMIStatusDescription = "";
        String boneWeightStatusDescription = "";
        String bodyageStatusDescription = "";
        String fatFreeWeightStatusDescription = "";
        String moistureStatusDescription = "";
        String muscleStatusDescription = "";
        String wHRStatusDescription = "";
        String proteideStatusDescription = "";
        String visceralFatStatusDescription = "";
        /** 获取体脂秤的健康描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(sex, age, deviceType.value(), null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.weight.value())
                    && descriptionStatus.equals(weightStatus.getRankValue())) {
                weightStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.axungeRatio.value())
                    && descriptionStatus.equals(axungeRatioStatus.getRankValue())) {
                axungeRatioStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.baseMetabolism.value())
                    && descriptionStatus.equals(baseMetabolismStatus.getRankValue())) {
                baseMetabolismStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.BMI.value())
                    && descriptionStatus.equals(bMIStatus.getRankValue())) {
                bMIStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.boneWeight.value())
                    && descriptionStatus.equals(boneWeightStatus.getRankValue())) {
                boneWeightStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.bodyage.value())
                    && descriptionStatus.equals(bodyageStatus.getRankValue())) {
                bodyageStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.fatFreeWeight.value())
                    && descriptionStatus.equals(fatFreeWeightStatus.getRankValue())) {
                fatFreeWeightStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.moisture.value())
                    && descriptionStatus.equals(moistureStatus.getRankValue())) {
                moistureStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.muscle.value())
                    && descriptionStatus.equals(muscleStatus.getRankValue())) {
                muscleStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.WHR.value())
                    && descriptionStatus.equals(wHRStatus.getRankValue())) {
                wHRStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.proteide.value())
                    && descriptionStatus.equals(proteideStatus.getRankValue())) {
                proteideStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.visceralFat.value())
                    && descriptionStatus.equals(visceralFatStatus.getRankValue())) {
                visceralFatStatusDescription = descriptionText;
                continue;
            }
        }

        /** 描述保存 */
        deviceEntity.setWeightStatusDescription(weightStatusDescription);
        deviceEntity.setAxungeRatioStatusDescription(axungeRatioStatusDescription);
        deviceEntity.setBaseMetabolismStatusDescription(baseMetabolismStatusDescription);
        deviceEntity.setBMIStatusDescription(bMIStatusDescription);
        deviceEntity.setBoneWeightStatusDescription(boneWeightStatusDescription);
        deviceEntity.setBodyageStatusDescription(bodyageStatusDescription);
        deviceEntity.setFatFreeWeightStatusDescription(fatFreeWeightStatusDescription);
        deviceEntity.setMoistureStatusDescription(moistureStatusDescription);
        deviceEntity.setMuscleStatusDescription(muscleStatusDescription);
        deviceEntity.setWHRStatusDescription(wHRStatusDescription);
        deviceEntity.setProteideStatusDescription(proteideStatusDescription);
        deviceEntity.setVisceralFatStatusDescription(visceralFatStatusDescription);

        /** 状态保存 */
        deviceEntity.setWeightStatus(weightStatus);
        deviceEntity.setAxungeRatioStatus(axungeRatioStatus);
        deviceEntity.setBaseMetabolismStatus(baseMetabolismStatus);
        deviceEntity.setBMIStatus(bMIStatus);
        deviceEntity.setBoneWeightStatus(boneWeightStatus);
        deviceEntity.setBodyageStatus(bodyageStatus);
        deviceEntity.setFatFreeWeightStatus(fatFreeWeightStatus);
        deviceEntity.setMoistureStatus(moistureStatus);
        deviceEntity.setMuscleStatus(muscleStatus);
        deviceEntity.setWHRStatus(wHRStatus);
        deviceEntity.setProteideStatus(proteideStatus);
        deviceEntity.setVisceralFatStatus(visceralFatStatus);

        // 添加记录
        memberService.addBodyfatscaleRecord(userId, WHR, isRankStatusUnusual(wHRStatus), BMI,
                bMIStatus.getRankValue(), baseMetabolism, isRankStatusUnusual(baseMetabolismStatus), weight);
    }

    @Override
    protected boolean checkStatus(TMeasureBodyfatscale entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureBodyfatscale entity) {
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
            list = deviceDao.selectBodyfatscaleWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectBodyfatscaleWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectBodyfatscaleWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectBodyfatscaleWithThreeMonth(params);
            break;
        case "YEAR":
            list = deviceDao.selectBodyfatscaleWithYear(params);
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
        int totalSize = commonTrans.getCount(TMeasureBodyfatscale.class, conditionMap);

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
        list = deviceDao.selectBodyfatscaleWithSplit(conditionMap);

        // 封装数据
        pagination.setData(list);
        return pagination;
    }

    private Float calculateBaseMetabolism(Float weight, Float height, Date birthday, Boolean gender) {
        // 如果用户信息不全，直接返回null
        if (weight == null || height == null || birthday == null || gender == null) {
            return null;
        }
        Float baseMetabolism = 0f;
        int age = DateTimeUtilT.calculateAge(birthday);
        if (gender) { // 男
            baseMetabolism = (float) (67.0 + (13.73 * weight) + (5.0 * height) - (6.9 * age));
        } else { // 女
            baseMetabolism = (float) (661.0 + (9.6 * weight) + (1.72 * height) - (4.7 * age));
        }
        return baseMetabolism;
    }

}
