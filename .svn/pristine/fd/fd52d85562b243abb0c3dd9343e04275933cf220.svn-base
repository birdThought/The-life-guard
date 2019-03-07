package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lifeshs.dao1.measure.UaDao;
import com.lifeshs.po.BloodLipidPO;
import com.lifeshs.po.UaPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureUa;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthDevice.UaDTO;
import com.lifeshs.pojo.healthStandard.BaseMemberDo;
import com.lifeshs.pojo.healthStandard.HealthStandardValue;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;

@Component(value = "ua")
public class Ua extends MeasureDevice<UaDTO> implements IMeasureDevice<UaDTO> {

    private final HealthPackageType deviceType = HealthPackageType.UA;
    @Autowired
    UaDao uaDao;

    @Override
    public List<UaDTO> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<UaDTO> list = new ArrayList<>();
        switch (dateType) {
            case "DAY":
                list = deviceDao.listUaWithLatestDay(userId, deviceType);
                break;
            case "WEEK":
                list = deviceDao.listUaWithLatestWeek(userId, deviceType);
                break;
            case "MONTH":
                list = deviceDao.listUaWithLatestMonth(userId, deviceType);
                break;
            case "THREEMONTH":
                list = deviceDao.listUaWithLatestThreeMonth(userId, deviceType);
                break;
            default:
                break;
        }
        /* 遍历list，去除每天重复元素，异常数据优先保留，若存在多个异常数据，则保留第一个 */
        for (int i = 0; i < list.size() - 1; i++) {
            DateTimeUtil.getDateTime(DateTimeUtilT.date(list.get(i).getMeasureDate()));
            HashMap<String, Object> list_3 = new LinkedHashMap<String, Object>();
            for (int j = i + 1; j < list.size(); j++) {
                if (DateTimeUtilT.date(list.get(i).getMeasureDate())
                        .equals(DateTimeUtilT.date(list.get(j).getMeasureDate()))) {
                    if (list.get(j).getStatus().equals(0)) { // 去除正常数据
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

        supplyData(list);

        return list;
    }

    @Override
    public PaginationDTO<UaDTO> getMeasureDataWithSplit(Integer userId, String deviceType, int pageIndex,
                                                        int pageSize) {
        PaginationDTO<UaDTO> pagination = new PaginationDTO<>();
        List<UaDTO> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureUa.class, conditionMap);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, totalSize);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();

        pagination.setTotalSize(totalSize);
        pagination.setTotalPage(totalPage);
        pagination.setNowPage(pageIndex);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, totalSize)) {
            pagination.setData(list);
            return pagination;
        }

        list = deviceDao.listUa(userId, deviceType, startIndex, pageSize);

        supplyData(list);

        pagination.setData(list);
        return pagination;
    }

    @Override
    protected boolean checkStatus(UaDTO entity) {
        if (entity.getStatus() > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(UaDTO entity) {
        if (entity.getStatus() == null) {
            return true;
        }
        return false;
    }

    @Override
    protected void deviantHandling(UaDTO deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType, deviceEntity.getMeasureDate());
    }

    @Override
    protected void perfectData(UaDTO deviceEntity) {
        // TODO Auto-generated method stub
        Integer userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean gender = baseMemberDo.isSex();
        int age = baseMemberDo.getAge();

        HealthStandardValue<String> uaStandard = HealthStandard.getUA(age, gender);
        float uaMin = Float.valueOf(uaStandard.getMin());
        float uaMax = Float.valueOf(uaStandard.getMax());
        String uaArea = getArea(uaMin, uaMax);
        deviceEntity.setUAArea(uaArea);

        Float ua = deviceEntity.getUA();

        HealthRank uaRank = getHealthValueStatus(uaMin, null, null, uaMax, ua);
        deviceEntity.setUARank(uaRank);

        long status = 0;
        status = getStatus(uaMin, uaMax, ua, status, HealthType.UA.value());
        deviceEntity.setStatus(status);

        String uaStatusDescription = "";
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(null, null, deviceType.value(), null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.UA.value()) && descriptionStatus.equals(uaRank.getRankValue())) {
                uaStatusDescription = descriptionText;
                break;
            }
        }
        deviceEntity.setUAStatusDescription(uaStatusDescription);
    }

    @Override
    public void save(UaDTO entity) throws OperationException {
        perfectData(entity);
        if (checkStatus(entity)) {
            deviantHandling(entity);
        }
        deviceDao.saveUa(entity);
    }

    private void supplyData(List<UaDTO> uaDTOList) {
        for (UaDTO uaDTO : uaDTOList) {
            long status = uaDTO.getStatus();
            uaDTO.setUAStatus(isHealthDeviceUnusual(status, HealthType.UA));
        }
    }


    public UaPO getLastestData(int userId) {
        return uaDao.getLastestData(userId);
    }
}
