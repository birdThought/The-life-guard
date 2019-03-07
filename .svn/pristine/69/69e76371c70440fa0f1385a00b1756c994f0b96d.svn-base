package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lifeshs.dao1.measure.BloodLipidDao;
import com.lifeshs.po.BloodLipidPO;
import com.lifeshs.po.UranPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TMeasureBloodlipid;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthDevice.BloodLipidDTO;
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
import com.lifeshs.utils.NumberUtils;

@Component(value = "bloodLipid")
public class BloodLipid extends MeasureDevice<BloodLipidDTO> implements IMeasureDevice<BloodLipidDTO>  {

    private final HealthPackageType deviceType = HealthPackageType.BloodLipid;
    @Autowired
    BloodLipidDao bloodLipidDao;
    @Override
    public List<BloodLipidDTO> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<BloodLipidDTO> list = new ArrayList<>();
        switch (dateType) {
        case "DAY":
            list = deviceDao.listBloodLipidWithLatestDay(userId, deviceType);
            break;
        case "WEEK":
            list = deviceDao.listBloodLipidWithLatestWeek(userId, deviceType);
            break;
        case "MONTH":
            list = deviceDao.listBloodLipidWithLatestMonth(userId, deviceType);
            break;
        case "THREEMONTH":
            list = deviceDao.listBloodLipidWithLatestThreeMonth(userId, deviceType);
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
    public PaginationDTO<BloodLipidDTO> getMeasureDataWithSplit(Integer userId, String deviceType, int pageIndex,
            int pageSize) {
        PaginationDTO<BloodLipidDTO> pagination = new PaginationDTO<>();
        List<BloodLipidDTO> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureBloodlipid.class, conditionMap);

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

        list = deviceDao.listBloodLipid(userId, deviceType, startIndex, pageSize);
        
        supplyData(list);
        
        pagination.setData(list);
        return pagination;
    }

    @Override
    protected boolean checkStatus(BloodLipidDTO entity) {
        if (entity.getStatus() > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(BloodLipidDTO entity) {
        if (entity.getStatus() == null) {
            return true;
        }
        return false;
    }

    @Override
    protected void deviantHandling(BloodLipidDTO deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType,deviceEntity.getMeasureDate());
    }

    @Override
    protected void perfectData(BloodLipidDTO deviceEntity) {
        Integer userId = deviceEntity.getUserId();
        UserRecordDTO recordDTO = memberService.getRecord(userId);
        BaseMemberDo baseMemberDo = getMemberBaseData(recordDTO);
        boolean gender = baseMemberDo.isSex();
        
        HealthStandardValue<String> HDLStandard = HealthStandard.getHDL(gender);
        float HDLMin = Float.valueOf(HDLStandard.getMin());
        float HDLMax = Float.valueOf(HDLStandard.getMax());
        String HDLArea = getArea(HDLMin, HDLMax);
        deviceEntity.setHDLArea(HDLArea);
        
        float hdl = deviceEntity.getHDL();
        HealthRank hdlRank = getHealthValueStatus(HDLMin, null, null, HDLMax, hdl);
        deviceEntity.setHDLRank(hdlRank);
        
        HealthStandardValue<String> LDLStandard = HealthStandard.getLDL();
        float LDLMin = Float.valueOf(LDLStandard.getMin());
        float LDLMax = Float.valueOf(LDLStandard.getMax());
        String LDLArea = getArea(LDLMin, LDLMax);
        deviceEntity.setLDLArea(LDLArea);
        
        float ldl = deviceEntity.getLDL();
        HealthRank ldlRank = getHealthValueStatus(LDLMin, null, null, LDLMax, ldl);
        deviceEntity.setLDLRank(ldlRank);
        
        HealthStandardValue<String> TCStandard = HealthStandard.getTC();
        float TCMin = Float.valueOf(TCStandard.getMin());
        float TCMax = Float.valueOf(TCStandard.getMax());
        String TCArea = getArea(TCMin, TCMax);
        deviceEntity.setTCArea(TCArea);
        
        float tc = deviceEntity.getTC();
        HealthRank tcRank = getHealthValueStatus(TCMin, null, null, TCMax, tc);
        deviceEntity.setTCRank(tcRank);
        
        HealthStandardValue<String> TGStandard = HealthStandard.getTG();
        float TGMin = Float.valueOf(TGStandard.getMin());
        float TGMax = Float.valueOf(TGStandard.getMax());
        String TGArea = getArea(TGMin, TGMax);
        deviceEntity.setTGArea(TGArea);
        
        float tg = deviceEntity.getTG();
        HealthRank tgRank = getHealthValueStatus(TGMin, null, null, TGMax, tg);
        deviceEntity.setTGRank(tgRank);
        
        HealthStandardValue<String> bloodLipidRatioStandard = HealthStandard.getBloodLipidRatio();
        float brMin = Float.valueOf(bloodLipidRatioStandard.getMin());
        float brMax = Float.valueOf(bloodLipidRatioStandard.getMax());
        String brArea = getArea(brMin, brMax);
        
        Float bloodLipidRatio = deviceEntity.getBloodLipidRatio();
        if (bloodLipidRatio == null) {
            bloodLipidRatio = NumberUtils.divide(tc, hdl, 2);
            deviceEntity.setBloodLipidRatio(bloodLipidRatio);
        }
        HealthRank bloodLipidRatioRank = getHealthValueStatus(brMin, null, null, brMax, bloodLipidRatio);
        deviceEntity.setBloodLipidRatioArea(brArea);
        deviceEntity.setBloodLipidRatioRank(bloodLipidRatioRank);
        
        long status = 0;
        status = getStatus(HDLMin, HDLMax, hdl, status, HealthType.HDL.value());
        status = getStatus(LDLMin, LDLMax, ldl, status, HealthType.LDL.value());
        status = getStatus(TCMin, TCMax, tc, status, HealthType.TC.value());
        status = getStatus(TGMin, TGMax, tg, status, HealthType.TG.value());
        deviceEntity.setStatus(status);
        
        String hdlStatusDescription = "";
        String ldlStatusDescription = "";
        String tcStatusDescription = "";
        String tgStatusDescription = "";
        String bloodLipidRatioStatusDescription = "";
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(null , null, deviceType.value(), null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();
            
            if (healthParamBinaryValue.longValue() == (HealthType.HDL.value()) && descriptionStatus.equals(hdlRank.getRankValue())) {
                hdlStatusDescription = descriptionText;
                continue;
            }
            
            if (healthParamBinaryValue.longValue() == (HealthType.LDL.value()) && descriptionStatus.equals(ldlRank.getRankValue())) {
                ldlStatusDescription = descriptionText;
                continue;
            }
            
            if (healthParamBinaryValue.longValue() == (HealthType.TC.value()) && descriptionStatus.equals(tcRank.getRankValue())) {
                tcStatusDescription = descriptionText;
                continue;
            }
            
            if (healthParamBinaryValue.longValue() == (HealthType.TG.value()) && descriptionStatus.equals(tgRank.getRankValue())) {
                tgStatusDescription = descriptionText;
                continue;
            }
            
            if (healthParamBinaryValue.longValue() == (HealthType.BloodLipidRation.value()) && descriptionStatus.equals(bloodLipidRatioRank.getRankValue())) {
                bloodLipidRatioStatusDescription = descriptionText;
                continue;
            }
        }
        deviceEntity.setHDLStatusDescription(hdlStatusDescription);
        deviceEntity.setLDLStatusDescription(ldlStatusDescription);
        deviceEntity.setTCStatusDescription(tcStatusDescription);
        deviceEntity.setTGStatusDescription(tgStatusDescription);
        deviceEntity.setBloodLipidRatioStatusDescription(bloodLipidRatioStatusDescription);
    }
    
    @Override
    public void save(BloodLipidDTO entity) throws OperationException {
        perfectData(entity);
        if (checkStatus(entity)) {
            deviantHandling(entity);
        }
        deviceDao.saveBloodLipid(entity);
    }

    private void supplyData(List<BloodLipidDTO> bloodLipidDTOList) {
        for (BloodLipidDTO bloodLipidDTO : bloodLipidDTOList) {
            long status = bloodLipidDTO.getStatus();
            bloodLipidDTO.setHDLStatus(isHealthDeviceUnusual(status, HealthType.HDL));
            bloodLipidDTO.setLDLStatus(isHealthDeviceUnusual(status, HealthType.LDL));
            bloodLipidDTO.setTCStatus(isHealthDeviceUnusual(status, HealthType.TC));
            bloodLipidDTO.setTGStatus(isHealthDeviceUnusual(status, HealthType.TG));
            bloodLipidDTO.setBloodLipidRatioStatus(isHealthDeviceUnusual(status, HealthType.BloodLipidRation));
        }
    }

    public BloodLipidPO getLastestData(int userId) {
        return bloodLipidDao.getLastData(userId);
    }
}
