package com.lifeshs.service.record.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.dao.data.ISportDao;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao.record.IRecordDao;
import com.lifeshs.entity.data.TDataSport;
import com.lifeshs.entity.data.TDataSportKind;
import com.lifeshs.entity.record.TRecordSport;
import com.lifeshs.entity.record.TRecordSportDetail;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.record.EnergyAnalyzeDTO;
import com.lifeshs.pojo.record.SportsDTO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.service.record.ISportService;
import com.lifeshs.utils.ConvertUtil;
import com.lifeshs.utils.DateTimeUtilT;

@Component
public class SportServiceImpl implements ISportService {

    @Autowired
    private IRecordDao recordDao;

    @Autowired
    private ISportDao sportDao;

    @Autowired
    private ICommonTrans common;

    @Autowired
    private IMemberDao memberDao;

    @Autowired
    private IRecordService recordService;

    @Override
    public List<TRecordSport> selectTRecordSportWithDate(int userId, String recordDate) {
        return recordDao.selectTRecordSportWithDate(userId, recordDate);
    }

    @Override
    public List<TDataSportKind> selectAllSports() {
        return sportDao.getAllSport();
    }

    @Override
    public Integer addSport(TRecordSport sport) {
        sport.setCreateDate(new Date());

        List<TRecordSportDetail> details = sport.getDetails();

        if (sport.getEnergy() == null) {

            Integer energy = 0;

            List<TDataSport> sports = common.loadAll(TDataSport.class);

            for (TRecordSportDetail detail : details) {
                int sportId = detail.getSportId();
                for (TDataSport s : sports) {
                    if (s.getId().equals(sportId)) {
                        energy += Double.valueOf((s.getKcal() * detail.getDuration())).intValue();
                    }
                }
            }

            sport.setEnergy(energy);
        }

        common.save(sport);
        Integer id = sport.getId();

        if (details.size() > 0) {
            for (TRecordSportDetail detail : sport.getDetails()) {
                detail.setRecordId(id);
            }
            common.batchSave(sport.getDetails());
        }

        return id;
    }

    @Override
    public List<TRecordSport> selectSportEnergyByUserIdWithDate(Integer userId, boolean customDate, String startDate,
            String endDate) {
        return recordDao.selectSportEnergyByUserIdWithDate(userId, customDate, startDate, endDate);
    }

    @Override
    public boolean delSportByRecordSportId(Integer recordSportId) {
        return recordDao.delSportByRecordSportId(recordSportId) > 0 ? true : false;
    }

    @Override
    public boolean delSportDetail(Integer detailId) {
        return recordDao.deleteSportDetail(detailId) > 0 ? true : false;
    }

    @Override
    public boolean updateSport(SportsDTO sportsDTO) {

        Boolean bool = true;
        TRecordSport recordSport = sportsDTO.getRecordSport();
        TRecordSport entity = common.findUniqueByProperty(TRecordSport.class, "id", recordSport.getId());
        entity.setEnergy(recordSport.getEnergy());
        bool &= common.updateEntitie(entity) > 0;

        List<Integer> idList = new ArrayList<>();
        List<TRecordSportDetail> detailsTmp = common.findByProperty(TRecordSportDetail.class, "recordId",
                recordSport.getId());
        Integer recordId = recordSport.getId();
        List<TRecordSportDetail> sports = sportsDTO.getDetails();
        if (sports != null) {
            if (sports.size() > 0 && recordId > 0) {
                for (TRecordSportDetail detail : sports) {
                    detail.setRecordId(recordId);
                    bool &= (common.saveOrUpdate(detail) > 0);
                    if (detail.getId() != null) {
                        idList.add(detail.getId());
                    }
                }
                Object[] idss = idList.toArray();
                for (TRecordSportDetail detail : detailsTmp) {
                    if (!ConvertUtil.isIn(detail.getId(), idss)) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("id", detail.getId());
                        bool &= common.deleteByProperty(TRecordSportDetail.class, param) > 0;
                        /* bool &= commonTrans.delete(detail) > 0; */
                    }
                }
            } else {
                for (TRecordSportDetail detail : detailsTmp) {
                    bool &= common.delete(detail) > 0;
                }
            }
        }
        return bool;
    }

    @Override
    public EnergyAnalyzeDTO getEnergyAnalyze(int userId, String recordDate) {
        EnergyAnalyzeDTO energyAnalyze = new EnergyAnalyzeDTO();
        Date birthday = null;
        Float weight = null;
        Float height = null;
        Boolean sex = null;
        int age = 0;
        Double basicMetabolism = 0.0;
        Double requiredEnergy = 0.0;
        Double basicMetabolismDeviation = 0.0;
        Double requiredEnergyDeviation = 0.0;
        Integer sportEnergy = 0;
        Integer dietEnergy = 0;
        String basicMetabolismSuggetion = null;
        String requiredEnergySuggetion = null;

        UserRecordDTO recordDTO = memberDao.getUserRecord(userId);
        // TUser user = member.getTUser(userId);
        birthday = recordDTO.getBirthday();
        weight = recordDTO.getWeight();
        height = recordDTO.getHeight();
        sex = recordDTO.getGender();
        if (birthday == null || weight == null || height == null) {
            energyAnalyze.setErrorMessage("个人信息不全,无法获取能量分析数据");
            return energyAnalyze;
        }
        age = DateTimeUtilT.calculateAge(birthday);
        if (sex) { // 男
            basicMetabolism = 66 + 13.7 * weight + 5 * height - 6.8 * age;
        } else { // 女
            basicMetabolism = 655 + 9.6 * weight + 1.72 * height - 4.7 * age;
        }
        requiredEnergy = basicMetabolism * 1.2;
        List<TRecordSport> list = selectSportEnergyByUserIdWithDate(userId, true, recordDate, recordDate);
        for (TRecordSport recordSport : list) {
            if (recordSport.getRecordDate().equals(DateTimeUtilT.date(recordDate))) {
                sportEnergy = recordSport.getEnergy();
            }
        }
        List<Map<String, Object>> dietList = recordService.selectDietEnergyByUserIdWithDate(userId, true, recordDate,
                recordDate);
        for (Map<String, Object> map : dietList) {
            if (((Date) map.get("recordDate")).equals(DateTimeUtilT.date(recordDate))) {
                dietEnergy = ((BigDecimal) map.get("energy")).intValue();
            }
        }
        basicMetabolismDeviation = dietEnergy - sportEnergy - basicMetabolism;
        requiredEnergyDeviation = dietEnergy - sportEnergy - requiredEnergy;
        if (basicMetabolismDeviation > 0) {
            basicMetabolismSuggetion = "您的饮食运动处于正常范围，请持续保持。";
        } else {
            basicMetabolismSuggetion = "您的饮食运动处于异常范围，日常饮食不足，请您多吃一些有营养的食物。";
        }
        if (requiredEnergyDeviation < 0) {
            requiredEnergySuggetion = "您的饮食运动处于正常范围，请持续保持。";
        } else {
            requiredEnergySuggetion = "您的饮食运动处于异常范围，日常饮食过多，请您多加节制。";
        }
        energyAnalyze.setBasicMetabolism(basicMetabolism);
        energyAnalyze.setRequiredEnergy(requiredEnergy);
        energyAnalyze.setBasicMetabolismDeviation(basicMetabolismDeviation);
        energyAnalyze.setRequiredEnergyDeviation(requiredEnergyDeviation);
        energyAnalyze.setBasicMetabolismSuggetion(basicMetabolismSuggetion);
        energyAnalyze.setRequiredEnergySuggetion(requiredEnergySuggetion);
        return energyAnalyze;
    }

}
