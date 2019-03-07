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
import com.lifeshs.common.constants.common.healthPackage.DataTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureHeartrate;
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
 * 版权归 TODO 心率设备服务
 *
 * @author yuhang.weng
 * @DateTime 2016年5月20日 上午11:11:09
 */

@Component("heartRate")
public class HeartRate extends MeasureDevice<TMeasureHeartrate> implements IMeasureDevice<Map<String, Object>> {

    private final HealthPackageType deviceType = HealthPackageType.Band;

    @Override
    protected boolean checkStatus(TMeasureHeartrate entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureHeartrate entity) {
        if (entity.getStatus() == null)
            return true;
        return false;
    }

    @Override
    protected void deviantHandling(TMeasureHeartrate heartRateEntity) throws OperationException {
        /** 运动录入数据，不对异常做任何处理 */
        if (DataTypeEnum.SPORT.value().equals(heartRateEntity.getDataType())) {
            return ;
        }
        
        int userId = heartRateEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType, heartRateEntity.getMeasureDate());
        // 处理异常情况
        // 2017年6月26日11:07:21 取消
        /** 手动录入数据，不需要发送短信 */
        /*if (HealthDataType.MANUAL.getValue().equals(heartRateEntity.getDataType())) {
            return ;
        }*/
        
        String name = user.getRealName();
        if (StringUtils.isBlank(name)) {
            name = user.getUserName();
        }
        if ((heartRateEntity.getStatus() | HealthType.heartRate.value()) == heartRateEntity.getStatus()) {
            int heartRate = heartRateEntity.getHeartRate();
            String heartRateArea = heartRateEntity.getHeartRateArea();
            String detail = "心率值为" + heartRate + " " + compareArea(heartRate, heartRateArea) + "正常范围值(" + heartRateArea + "bpm)";
            super.sendWarningSMS(userId, name, detail, deviceType, "智能手环测量结果", heartRateEntity.getStatus());
        }
    }

    @Override
    protected void perfectData(TMeasureHeartrate deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean sex = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();

        Integer dataType = deviceEntity.getDataType();
        HealthStandardValue<Integer> heartRateStandard = HealthStandard.getHeartRate(sex, age);
        // 如果是运动模式，心率的健康范围会改变
        if (DataTypeEnum.SPORT.value().equals(dataType)) {
            heartRateStandard = HealthStandard.getHeartRateSportMode(age);
        }
        Float min = heartRateStandard.getMin().floatValue();
        Float max = heartRateStandard.getMax().floatValue();

        String heartRateArea = getArea(min, max);
        deviceEntity.setHeartRateArea(heartRateArea);

        // 计算测量状态
        Float heartRate = deviceEntity.getHeartRate().floatValue();
        Long status = 0l;
        status = getStatus(min, max, heartRate, status, HealthType.heartRate.value());
        deviceEntity.setStatus(status);

        /** 心率状态 */
        HealthRank heartRateStatus = getHealthValueStatus(min, null, null, max, heartRate);

        /** 心率描述文字 */
        String heartRateStatusDescription = "";
        /** 获取心率手环的健康描述文字，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(sex, age, deviceType.value(),
                heartRateStatus.getRankValue());
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == HealthType.heartRate.value()
                    && descriptionStatus.equals(heartRateStatus.getRankValue())) {
                heartRateStatusDescription = descriptionText;
                break;
            }
        }

        deviceEntity.setHeartRateStatusDescription(heartRateStatusDescription);
    }

    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        if (DateTimeUtilT.valiDateTimeWithFormat(dateType)) { // 若为"2016-01-01"时间格式，则将dataType作为查询条件
            params.put("measureDate", dateType);
            params.put("dateType", "day"); // 作为dao层判断日期格式唯一标志
            dateType = "SPECIAL_DAY";
        }
        params.put("deviceType", deviceType);
        switch (dateType) {
            case "DAY":
                list = deviceDao.selectHeartRateWithDay(params);
                break;
            case "WEEK":
                list = deviceDao.selectHeartRateWithWeek(params);
                break;
            case "MONTH":
                list = deviceDao.selectHeartRateWithMonth(params);
                break;
            case "THREEMONTH":
                list = deviceDao.selectHeartRateWithThreeMonth(params);
                break;
            case "SPECIAL_DAY":
                list = deviceDao.selectHeartRateWithSpecialDate(params); // 获取指定日期一天全部数据
                break;
            default:
                break;
        }

        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        /*System.out.println("===原方法========开始=====");
        long s1 = System.currentTimeMillis();*/
        if (dateType.equals("DAY") || dateType.equals("WEEK") || dateType.equals("MONTH")
                || dateType.equals("THREEMONTH")) { // 查询一天的数据保留全部
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
        int totalSize = commonTrans.getCount(TMeasureHeartrate.class, conditionMap);

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
        list = deviceDao.selectHeartRateWithSplit(conditionMap);

        // 封装数据
        pagination.setData(list);
        return pagination;
    }

}
