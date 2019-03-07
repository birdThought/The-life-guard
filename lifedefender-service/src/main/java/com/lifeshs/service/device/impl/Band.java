package com.lifeshs.service.device.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TSportBand;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.device.IMeasureDevice;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;

/**
 * 
 * 版权归 手环-计步睡眠日 服务类
 * 
 * @author dengfeng
 * @DateTime 2016-5-23 下午04:29:04
 */

@Component("band")
public class Band extends MeasureDevice<TSportBand> implements IMeasureDevice<Map<String, Object>> {

    /**
     * 设备类型
     */
    HealthPackageType deviceType = HealthPackageType.Band;

    /**
     * 保存提交的设备数据
     * 
     * @author dengfeng
     * @DateTime 2016-5-13 下午03:08:31
     * @param T
     *            值对象
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void save(TSportBand entity) throws OperationException {
        int userId = entity.getUserId();
        String mDate = DateTimeUtilT.date(entity.getDate());
        TSportBand band = getBandData(userId, mDate);
        
        Integer shallowDuration = entity.getShallowDuration();
        Integer deepDuration = entity.getDeepDuration();
        Integer wakeupDuration = entity.getWakeupDuration();
        Integer kcal = entity.getKcal();
        Integer steps = entity.getSteps();
        Integer mileage = entity.getMileage();
        Integer sleepDuration = null;
        
        if (band == null) { // 该天还没有数据,直接插入数据
            band = entity;
        } else { // 该天已存在数据,更新数据
            band.setDeviceType(entity.getDeviceType());

            /** 如果app传了该项数据，直接覆盖原来的内容 */
            if (shallowDuration != null) {
                band.setShallowDuration(shallowDuration);
            }
            
            if (deepDuration != null) {
                band.setDeepDuration(deepDuration);
            }
            
            if (wakeupDuration != null) {
                band.setWakeupDuration(wakeupDuration);
            }
            
            if (kcal != null) {
                band.setKcal(kcal);
            }
            
            if (steps != null) {
                band.setSteps(steps);
            }
            
            if (mileage != null) {
                band.setMileage(mileage);
            }
        }
        if (shallowDuration != null && deepDuration != null && wakeupDuration != null) {
            sleepDuration = shallowDuration + deepDuration + wakeupDuration;
        }
        band.setSleepDuration(sleepDuration);
        int result = commonTrans.saveOrUpdate(band);
        if (result == 0) {
            throw new OperationException("更新数据失败", ErrorCodeEnum.FAILED);
        }
        if (sleepDuration != null) {
            // 健康指数睡眠时长
            float hour = 0.0f;
            if (sleepDuration != null) {
                hour = NumberUtils.divide(sleepDuration.floatValue(), 60f, 1);
            }
            memberService.addSleepHourRecord(userId, hour);
        }
    }

    protected boolean checkStatus(TSportBand entity) {
        return false;
    }

    protected boolean checkStatusIsNull(TSportBand entity) {
        return false;
    }

    protected void deviantHandling(TSportBand deviceEntity) {
    }

    protected void perfectData(TSportBand deviceEntity) {
    }

    @Override
    public List<Map<String, Object>> getMeasureDataWithDate(Integer userId, String deviceType, String dateType) {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        if (DateTimeUtilT.valiDateTimeWithFormat(dateType)) { // 若为时间格式，则将dataType作为查询条件
            params.put("date", dateType);
        }
        params.put("deviceType", deviceType);
        switch (dateType) {
        case "DAY":
            list = deviceDao.selectBandWithDay(params);
            break;
        case "WEEK":
            list = deviceDao.selectBandWithWeek(params);
            break;
        case "MONTH":
            list = deviceDao.selectBandWithMonth(params);
            break;
        case "THREEMONTH":
            list = deviceDao.selectBandWithThreeMonth(params);
            break;
        default:
            list = deviceDao.selectBandWithDay(params); // 获取一天全部数据
            break;
        }
        if (dateType.equals("DAY") || dateType.equals("WEEK") || dateType.equals("MONTH")
                || dateType.equals("THREEMONTH")) { // 查询一天的数据保留全部
            /* 遍历list，去除每天重复元素，保留最后一条(计步和睡眠不存在异常数据) */
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (DateTimeUtilT.date((java.sql.Date) list.get(i).get("date"))
                            .equals(DateTimeUtilT.date((java.sql.Date) list.get(j).get("date")))) {
                        list.remove(j);
                        j--; // 每移除一个元素以后再把i移回来
                    }
                }
            }
        }
        return list;
    }

    @Override
    public PaginationDTO<Map<String, Object>> getMeasureDataWithSplit(Integer userId, String deviceType, int nowPage, int pageSize) {
        PaginationDTO<Map<String, Object>> pagination = new PaginationDTO<>();
        HashMap<String, Object> params = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId", userId);
        conditionMap.put("deviceType", deviceType);
        int totalSize = commonTrans.getCount(TSportBand.class, conditionMap);
        if ((nowPage - 1) * pageSize > totalSize) {
            pagination.setData(list);
            return pagination;
        }
        params.put("userId", userId);
        params.put("deviceType", deviceType);
        pageSize = pageSize <= 0 ? 1 : pageSize;
        // 总页数
        int totalPage = 1;
        if (pageSize <= totalSize) {
            // 如果不能被整除，就数值+1
            totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1;
        }
        if (nowPage <= 0)
            nowPage = 1;
        if (nowPage > totalPage)
            nowPage = totalPage;
        int startIndex = (nowPage - 1) * pageSize; // 开始下标
        params.put("start", startIndex);
        params.put("pageSize", pageSize);
        list = deviceDao.selectBandWithSplit(params);
        // 封装数据

        pagination.setTotalPage(totalPage);
        pagination.setNowPage(nowPage);
        pagination.setData(list);
        pagination.setTotalSize(totalSize);
        return pagination;
    }

    public <T> T getMeasureLastedData(Class<T> clazz, int userId, String deviceType, String measureDate) {
        return deviceService.selectDeviceDataLastestDate(clazz, userId, deviceType, measureDate);
    }
    
    public TSportBand getBandData(int userId, String measureDate) {
        TSportBand data = deviceDao.selectBandWithDate(userId, measureDate);
        return data;
    }
}
