package com.lifeshs.service.device.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureLunginstrument;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthStandard.BaseMemberDo;
import com.lifeshs.pojo.healthStandard.HealthStandardValueEx;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.utils.DateTimeUtilT;

/**
 * 版权归 TODO 肺活量设备服务
 * 
 * @author yuhang.weng
 * @DateTime 2016年5月24日 上午10:23:05
 */
@Component("lunginstrument")
public class Lunginstrument extends MeasureDevice<TMeasureLunginstrument> implements IMeasureDevice<Map<String, Object>> {

    private final HealthPackageType deviceType = HealthPackageType.Lunginstrument;

    @Override
    protected void deviantHandling(TMeasureLunginstrument deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,deviceEntity.getMeasureDate());
        if (deviceEntity.getStatus() != 0) {
            // TODO 数据异常处理
        }

    }

    @Override
    protected void perfectData(TMeasureLunginstrument deviceEntity) {
        int userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);

        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean sex = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();

        HealthStandardValueEx<Integer> svex = HealthStandard.getVitalCapacity(sex, age);

        float min = svex.getMin();
        float max = 99999F;
        String vitalCapacityArea = getArea(min, max);
        deviceEntity.setVitalCapacityArea(vitalCapacityArea);

        Float vitalCapacity = deviceEntity.getVitalCapacity().floatValue();
        Long status = 0l;
        status = getStatus(min, max, vitalCapacity, status, HealthType.vitalCapacity.value());
        deviceEntity.setStatus(status);

        /** 肺活量状态 */
        HealthRank vitalCapacityStatus = getHealthValueStatus(min, null, null, max, vitalCapacity);

        /** 肺活量健康描述 */
        String vitalCapacityStatusDescription = "";
        /** 获取肺活量描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(sex, age, deviceType.value(),
                vitalCapacityStatus.getRankValue());
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.vitalCapacity.value())
                    && vitalCapacityStatus.equals(descriptionStatus)) {
                vitalCapacityStatusDescription = descriptionText;
                break;
            }
        }

        deviceEntity.setVitalCapacityStatusDescription(vitalCapacityStatusDescription);

        deviceEntity.setVitalCapacityStatus(vitalCapacityStatus);
        // 添加记录
        memberService.addLunginstrumentRecord(userId, vitalCapacity.intValue(), vitalCapacityStatus.getRankValue());
    }

    @Override
    protected boolean checkStatus(TMeasureLunginstrument entity) {
        if (entity.getStatus() > 0)
            return true;
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(TMeasureLunginstrument entity) {
        if (entity.getStatus() == null)
            return true;
        return false;
    }

    // 使用独立映射的业务
    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectLungInstrumentWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectLungInstrumentWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectLungInstrumentWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectLungInstrumentWithThreeMonth(params);
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

    // 使用独立映射的业务
    @Override
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int nowPage, int pageSize) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureLunginstrument.class, conditionMap);

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
        list = deviceDao.selectLungInstrumentWithSplit(conditionMap);

        // 封装数据
        pagination.setTotalPage(totalPage);
        pagination.setData(list);
        return pagination;
    }

}
