package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.FMRank;
import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthRank;
import com.lifeshs.common.constants.common.HealthType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.measure.UranDao;
import com.lifeshs.entity.device.TMeasureUran;
import com.lifeshs.po.UranPO;
import com.lifeshs.pojo.healthDescription.NormalHealthPackageDTO;
import com.lifeshs.pojo.healthDevice.UranDTO;
import com.lifeshs.pojo.healthStandard.HealthStandardValue;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.service.device.impl.healthStandard.HealthStandard;
import com.lifeshs.utils.DateTimeUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;

/**
 * 尿液分析仪
 *
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月19日 上午11:51:16
 */
@Component(value = "uran")
public class Uran extends MeasureDevice<UranDTO> implements IMeasureDevice<UranDTO> {

    private final HealthPackageType deviceType = HealthPackageType.URAN;
    @Autowired
    private UranDao uranDao;

    @Override
    public List<UranDTO> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<UranDTO> list = new ArrayList<>();
        switch (dateType) {
            case "DAY":
                list = deviceDao.listUranWithLatestDay(userId, deviceType);
                break;
            case "WEEK":
                list = deviceDao.listUranWithLatestWeek(userId, deviceType);
                break;
            case "MONTH":
                list = deviceDao.listUranWithLatestMonth(userId, deviceType);
                break;
            case "THREEMONTH":
                list = deviceDao.listUranWithLatestThreeMonth(userId, deviceType);
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
    public PaginationDTO<UranDTO> getMeasureDataWithSplit(Integer userId, String deviceType, int pageIndex,
                                                          int pageSize) {
        PaginationDTO<UranDTO> pagination = new PaginationDTO<>();
        List<UranDTO> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TMeasureUran.class, conditionMap);

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

        list = deviceDao.listUran(userId, deviceType, startIndex, pageSize);
        supplyData(list);

        pagination.setData(list);
        return pagination;
    }

    @Override
    protected boolean checkStatus(UranDTO entity) {
        if (entity.getStatus() > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkStatusIsNull(UranDTO entity) {
        if (entity.getStatus() == null) {
            return true;
        }
        return false;
    }

    @Override
    protected void deviantHandling(UranDTO deviceEntity) throws OperationException {
        int userId = deviceEntity.getUserId();
        // 设置 用户为 未处理异常 状态
        UserDTO user = memberService.getUser(userId);
        super.updateUserIsOverstep(userId, user.getHasWarning(), deviceType, deviceEntity.getMeasureDate());
    }

    @Override
    protected void perfectData(UranDTO deviceEntity) {
        // TODO Auto-generated method stub
        HealthStandardValue<String> phStandard = HealthStandard.getPh();
        float phMin = Float.valueOf(phStandard.getMin());
        float phMax = Float.valueOf(phStandard.getMax());
        String pHArea = getArea(phMin, phMax);
        deviceEntity.setPHArea(pHArea);

        HealthStandardValue<String> sgStandard = HealthStandard.getSG();
        float sgMin = Float.valueOf(sgStandard.getMin());
        float sgMax = Float.valueOf(sgStandard.getMax());
        //String sGArea = getArea(sgMin, sgMax);
        String sGArea = NumberUtils.format(sgMin, 3) + "-" + NumberUtils.format(sgMax, 3);
        deviceEntity.setSGArea(sGArea);

        Float ph = deviceEntity.getPH();
        Float sg = deviceEntity.getSG();
        String LEU = deviceEntity.getLEU();
        String NIT = deviceEntity.getNIT();
        String UBG = deviceEntity.getUBG();
        String PRO = deviceEntity.getPRO();
        String BLD = deviceEntity.getBLD();
        String KET = deviceEntity.getKET();
        String BIL = deviceEntity.getBIL();
        String GLU = deviceEntity.getGLU();
        String VC = deviceEntity.getVC();

        HealthRank pHRank = getHealthValueStatus(phMin, null, null, phMax, ph);
        HealthRank sGRank = getHealthValueStatus(sgMin, null, null, sgMax, sg);
        FMRank lEURank = getFMValueUnusual(LEU);
        FMRank nITRank = getFMValueUnusual(NIT);
        FMRank uBGRank = getFMValueUnusual(UBG);
        FMRank pRORank = getFMValueUnusual(PRO);
        FMRank bLDRank = getFMValueUnusual(BLD);
        FMRank kETRank = getFMValueUnusual(KET);
        FMRank bILRank = getFMValueUnusual(BIL);
        FMRank gLURank = getFMValueUnusual(GLU);
        FMRank vCRank = getFMValueUnusual(VC);

        deviceEntity.setPhRank(pHRank);
        deviceEntity.setSgRank(sGRank);
        deviceEntity.setLEURank(lEURank);
        deviceEntity.setNITRank(nITRank);
        deviceEntity.setUBGRank(uBGRank);
        deviceEntity.setPRORank(pRORank);
        deviceEntity.setBLDRank(bLDRank);
        deviceEntity.setKETRank(kETRank);
        deviceEntity.setBILRank(bILRank);
        deviceEntity.setGLURank(gLURank);
        deviceEntity.setVCRank(vCRank);

        long status = 0;
        status = getStatus(phMin, phMax, ph, status, HealthType.pH.value());
        status = getStatus(sgMin, sgMax, sg, status, HealthType.SG.value());
        status = getStatus(lEURank, status, HealthType.LEU.value());
        status = getStatus(nITRank, status, HealthType.NIT.value());
        status = getStatus(uBGRank, status, HealthType.UBG.value());
        status = getStatus(pRORank, status, HealthType.PRO.value());
        status = getStatus(bLDRank, status, HealthType.BLD.value());
        status = getStatus(kETRank, status, HealthType.KET.value());
        status = getStatus(bILRank, status, HealthType.BIL.value());
        status = getStatus(gLURank, status, HealthType.GLU.value());
        status = getStatus(vCRank, status, HealthType.VC.value());
        deviceEntity.setStatus(status);

        String pHStatusDescription = "";
        String sGStatusDescription = "";
        String lEUStatusDescription = "";
        String nITStatusDescription = "";
        String uBGStatusDescription = "";
        String pROStatusDescription = "";
        String bLDStatusDescription = "";
        String kETStatusDescription = "";
        String bILStatusDescription = "";
        String gLUStatusDescription = "";
        String vCStatusDescription = "";
        /** 获取血压计健康描述，并且通过遍历，查找到对应状态的描述文字，保存在记录中 */
        List<NormalHealthPackageDTO> descriptions = getHealthValueDescription(null, null, deviceType.value(), null);
        for (NormalHealthPackageDTO description : descriptions) {
            Long healthParamBinaryValue = description.getHealthPackageParamBinaryValue();
            String descriptionText = description.getDescription();
            Integer descriptionStatus = description.getStatus();

            if (healthParamBinaryValue.longValue() == (HealthType.pH.value()) && descriptionStatus.equals(pHRank.getRankValue())) {
                pHStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.SG.value()) && descriptionStatus.equals(sGRank.getRankValue())) {
                sGStatusDescription = descriptionText;
                continue;
            }

            if (healthParamBinaryValue.longValue() == (HealthType.LEU.value())
                    && descriptionStatus.equals(lEURank.getValue())) {
                lEUStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.NIT.value())
                    && descriptionStatus.equals(nITRank.getValue())) {
                nITStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.UBG.value())
                    && descriptionStatus.equals(uBGRank.getValue())) {
                uBGStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.PRO.value())
                    && descriptionStatus.equals(pRORank.getValue())) {
                pROStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.BLD.value())
                    && descriptionStatus.equals(bLDRank.getValue())) {
                bLDStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.KET.value())
                    && descriptionStatus.equals(kETRank.getValue())) {
                kETStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.BIL.value())
                    && descriptionStatus.equals(bILRank.getValue())) {
                bILStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.GLU.value())
                    && descriptionStatus.equals(gLURank.getValue())) {
                gLUStatusDescription = descriptionText;
                continue;
            }
            if (healthParamBinaryValue.longValue() == (HealthType.VC.value())
                    && descriptionStatus.equals(vCRank.getValue())) {
                vCStatusDescription = descriptionText;
                continue;
            }
        }

        deviceEntity.setPHStatusDescription(pHStatusDescription);
        deviceEntity.setSGStatusDescription(sGStatusDescription);
        deviceEntity.setLEUStatusDescription(lEUStatusDescription);
        deviceEntity.setNITStatusDescription(nITStatusDescription);
        deviceEntity.setUBGStatusDescription(uBGStatusDescription);
        deviceEntity.setPROStatusDescription(pROStatusDescription);
        deviceEntity.setBLDStatusDescription(bLDStatusDescription);
        deviceEntity.setKETStatusDescription(kETStatusDescription);
        deviceEntity.setBILStatusDescription(bILStatusDescription);
        deviceEntity.setGLUStatusDescription(gLUStatusDescription);
        deviceEntity.setVCStatusDescription(vCStatusDescription);
    }

    @Override
    public void save(UranDTO entity) throws OperationException {
        perfectData(entity);
        // 由子类重写deviantHandling方法来决定是否发送短信，或其它异常处理
        if (checkStatus(entity)) {
            deviantHandling(entity);
        }
        deviceDao.saveUran(entity);
    }

    private void supplyData(List<UranDTO> uranDTOList) {
        for (UranDTO uranDTO : uranDTOList) {
            long status = uranDTO.getStatus();
            uranDTO.setBILStatus(isHealthDeviceUnusual(status, HealthType.BIL));
            uranDTO.setBLDStatus(isHealthDeviceUnusual(status, HealthType.BLD));
            uranDTO.setGLUStatus(isHealthDeviceUnusual(status, HealthType.GLU));
            uranDTO.setKETStatus(isHealthDeviceUnusual(status, HealthType.KET));
            uranDTO.setLEUStatus(isHealthDeviceUnusual(status, HealthType.LEU));
            uranDTO.setNITStatus(isHealthDeviceUnusual(status, HealthType.NIT));
            uranDTO.setPhStatus(isHealthDeviceUnusual(status, HealthType.pH));
            uranDTO.setPROStatus(isHealthDeviceUnusual(status, HealthType.PRO));
            uranDTO.setSgStatus(isHealthDeviceUnusual(status, HealthType.SG));
            uranDTO.setUBGStatus(isHealthDeviceUnusual(status, HealthType.UBG));
            uranDTO.setVCStatus(isHealthDeviceUnusual(status, HealthType.VC));
        }
    }

    /**
     * 获取某月的有数据的日期
     * @param userId 用户id
     * @param queryDate 查询当月的第一天,比如:2017-07-01.
     * @return
     */
    private Map<String, Integer> listValidDate(int userId, String queryDate) {
        Map<String, Integer> result = new HashMap<>();
        List<UranPO> list = uranDao.selectMeasureDatesByUserId(userId, queryDate);
        for (UranPO po : list) {
            //具有数据的日期
            String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");
            //之前的结果
            Integer preStatus = result.get(date);
            Integer status = 0;
            if (po.getStatus() != null) {
                status = po.getStatus().intValue();
            }
            if (preStatus != null && preStatus > 0) {
                continue;
            } else if (preStatus == null || preStatus == 0) {
                result.put(date, status);
            }
        }
        return result;
    }

    /**
     * 获取指定日期数据
     * @param userId
     * @param queryDate
     * @return
     */
    private List<UranDTO> listDateByDay(int userId, String queryDate) {
        return null;
    }

    /**
     * 获取最后一条数据
     * author: wenxian.cai
     * date: 2017/10/24 10:39
     */
    public UranPO getLastestData(int userId) {
        return uranDao.getLastData(userId);
    }
}
