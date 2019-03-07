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
import com.lifeshs.entity.device.TMeasureTemperature;
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
 * 版权归 TODO 体温设备服务
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月23日 下午2:31:53
 */
@Component("temperature")
public class Temperature extends MeasureDevice<TMeasureTemperature> implements IMeasureDevice<Map<String, Object>> {

    private final HealthPackageType deviceType = HealthPackageType.Temperature;

    @Override
    protected void deviantHandling(TMeasureTemperature deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,deviceEntity.getMeasureDate());

        /** 异常处理 */
        /** 手动录入数据，不需要发送短信 */
        if (DataTypeEnum.MANUAL.value().equals(deviceEntity.getDataType())) {
            return;
        }
        String name = user.getRealName();
        if (StringUtils.isBlank(name)) {
            name = user.getUserName();
        }
        if (deviceEntity.getStatus() != 0) {
            Float temperature = deviceEntity.getTemperature();
            String temperatureArea = deviceEntity.getTemperatureArea();
            String detail = "体温值为" + temperature + " " + compareArea(temperature, temperatureArea) + "正常范围(" + temperatureArea + ")";
//            // 如果用户设置了预警提醒
//            if ((user.getHealthWarning() | HealthType.temperature.value()) == user.getHealthWarning()) {
                super.sendWarningSMS(userId, name, detail, deviceType, "体温测量结果", deviceEntity.getStatus());
//            }
        }
    }

    @Override
    protected void perfectData(TMeasureTemperature deviceEntity) {
        // 获取用户信息
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean sex = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();

        HealthStandardValue<Integer> temperatureStandard = HealthStandard.getTemperature();

        Float temperatureMin = temperatureStandard.getMin().floatValue();
        Float temperatureMax = temperatureStandard.getMax().floatValue();

        String temperatureArea = getArea(temperatureMin, temperatureMax);
        deviceEntity.setTemperatureArea(temperatureArea);

        Float temperature = deviceEntity.getTemperature();

        Long status = 0l;
        status = getStatus(temperatureMin, temperatureMax, temperature.floatValue(), status,
                HealthType.temperature.value());
        deviceEntity.setStatus(status);

        /** 体温的状态 */
        HealthRank temperatureStatus = getHealthValueStatus(temperatureMin, null, null, temperatureMax, temperature);

        /** 体温的状态描述文字 */
        String temperatureStatusDescription = "";
        /** 获取体温的状态描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(sex, age, deviceType.value(),
                temperatureStatus.getRankValue());
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.temperature.value())
                    && temperatureStatus.equals(descriptionStatus)) {
                temperatureStatusDescription = descriptionText;
                break;
            }
        }

        deviceEntity.setTemperatureStatusDescription(temperatureStatusDescription);
    }

    @Override
    protected boolean checkStatus(TMeasureTemperature entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureTemperature entity) {
        if (entity.getStatus() == null)
            return true;
        return false;
    }

    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        if (DateTimeUtilT.valiDateTimeWithFormat(dateType)) { // 若为时间格式，则将dataType作为查询条件
            params.put("measureDate", dateType);
            params.put("dateType", "day"); // 作为dao层判断日期格式唯一标志
            dateType = "SPECIAL_DAY";
        }
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectTemperatureWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectTemperatureWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectTemperatureWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectTemperatureWithThreeMonth(params);
            break;
        case "SPECIAL_DAY": // 获取具体日期全部数据
            list = deviceDao.selectTemperatureWithSpecialDate(params);
            break;
        default:

            break;
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("temperature", (Math.floor(((float) list.get(i).get("temperature") * 10)) / 10));
        }
        if (!dateType.equals("SPECIAL_DAY")) { // 查询具体日期的数据保留全部
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
                            list.remove(i); // 若存在至少一条异常数据，则将正常数据全部清除
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
        int totalSize = commonTrans.getCount(TMeasureTemperature.class, conditionMap);

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
        list = deviceDao.selectTemperatureWithSplit(conditionMap);

        // 封装数据
        pagination.setData(list);
        return pagination;
    }

}
