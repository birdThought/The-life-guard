package com.lifeshs.service1.measure.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lifeshs.dao1.measure.*;
import com.lifeshs.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.po.measure.MeasureAnalysisPO;
import com.lifeshs.service1.measure.MeasureAnalysisService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

@Service(value = "measureAnalysisServiceImpl")
public class MeasureAnalysisServiceImpl implements MeasureAnalysisService {

    @Resource(name = "measureAnalysisDao")
    private MeasureAnalysisDao analysisDao;
    @Autowired
    private BloodPressureDao bloodPressureDao;
    @Autowired
    private BodyFatScaleDao bodyFatScaleDao;
    @Autowired
    private GluCometerDao gluCometerDao;
    @Autowired
    private HeartRateDao heartRateDao;
    @Autowired
    private LungInstrumentDao lungInstrumentDao;
    @Autowired
    private OxygenDao oxygenDao;


    @Override
    public int countUnReadAnalysis(int userId) {
        return analysisDao.countAnalysisWithCondition(userId, null, null, null, false, true);
    }

    @Override
    public List<MeasureAnalysisPO> listAnalysis(int userId, Date measureDate) {
        return analysisDao.findAnalysisListByUserIdAndMeasueDate(userId, measureDate);
    }

    @Override
    public Paging<MeasureAnalysisPO> listAnalysis(int curPage, int pageSize, Integer userId, Date measureDate, HealthPackageType healthProduct,
            Integer customerUserId, Boolean read, Boolean reply) {
        Paging<MeasureAnalysisPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<MeasureAnalysisPO>() {

            @Override
            public int queryTotal() {
                Integer healthProductValue = healthProduct == null ? null : healthProduct.value();
                return analysisDao.countAnalysisWithCondition(userId, measureDate, healthProductValue, customerUserId, read, reply);
            }

            @Override
            public List<MeasureAnalysisPO> queryData(int startRow, int pageSize) {
                Integer healthProductValue = healthProduct == null ? null : healthProduct.value();
                return analysisDao.findAnalysisListWithCondition(startRow, pageSize, userId, measureDate, healthProductValue, customerUserId, read, reply);
            }
        });
        return p;
    }

    @Override
    public MeasureAnalysisPO getAnalysis(int id) {
        return analysisDao.getAnalysis(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void addAnalysis(int userId, Date measureDate) throws BaseException {
        if (measureDate == null) {
            throw new ParamException("测量日期不能为空", ErrorCodeEnum.MISSING);
        }
        
        MeasureAnalysisPO analysis = new MeasureAnalysisPO();
        analysis.setUserId(userId);
        analysis.setMeasureDate(measureDate);
        analysis.setRead(false);
        int result = analysisDao.addAnalysis(analysis);
        if (result == 0) {
            throw new OperationException("添加批注失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void addAnalysis(int userId, Date measureDate, String reply, int customerUserId, String doctorSign)
            throws BaseException {
        if (measureDate == null) {
            throw new ParamException("测量日期不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(reply)) {
            throw new ParamException("批注内容不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(doctorSign)) {
            throw new ParamException("医生签名不能为空", ErrorCodeEnum.MISSING);
        }
        
        MeasureAnalysisPO analysis = new MeasureAnalysisPO();
        analysis.setUserId(userId);
        analysis.setMeasureDate(measureDate);
        analysis.setRead(false);
        analysis.setReply(reply);
        analysis.setCustomerUserId(customerUserId);
        analysis.setDoctorSign(doctorSign);
        int result = analysisDao.addAnalysis(analysis);
        if (result == 0) {
            throw new OperationException("添加批注失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void replyAnalysis(int id, String reply, int customerUserId, String doctorSign) throws BaseException {
        if (StringUtils.isBlank(reply)) {
            throw new ParamException("批注内容不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(doctorSign)) {
            throw new ParamException("医生签名不能为空", ErrorCodeEnum.MISSING);
        }
        
        MeasureAnalysisPO analysis = new MeasureAnalysisPO();
        analysis.setId(id);
        analysis.setReply(reply);
        analysis.setCustomerUserId(customerUserId);
        analysis.setDoctorSign(doctorSign);
        int result = analysisDao.updateAnalysis(analysis);
        if (result == 0) {
            throw new OperationException("写批注失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void readAnalysis(int id) throws OperationException {
        MeasureAnalysisPO analysisPO = analysisDao.getAnalysis(id);
        boolean isReply = StringUtils.isNotBlank(analysisPO.getReply());
        if (isReply) {
            MeasureAnalysisPO analysis = new MeasureAnalysisPO();
            analysis.setId(id);
            analysis.setRead(true);
            int result = analysisDao.updateAnalysis(analysis);
            if (result == 0) {
                throw new OperationException("修改已读失败", ErrorCodeEnum.FAILED);
            }
        }
    }

    @Override
    public void readAnalysis(int userId, Date measureDate) {
        analysisDao.updateAnalysisListByUserIdAndMeasureDate(true, null, userId, measureDate);
        // 批量更新不做回滚操作
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void deleteAnalysis(int id) throws OperationException {
        MeasureAnalysisPO analysis = new MeasureAnalysisPO();
        analysis.setId(id);
        analysis.setDeleted(true);
        int result = analysisDao.updateAnalysis(analysis);
        if (result == 0) {
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void deleteAnalysis(int userId, Date measureDate) {
        analysisDao.updateAnalysisListByUserIdAndMeasureDate(null, true, userId, measureDate);
        // 批量更新不做回滚操作
    }

    @Override
    public Map<String, Object> getSpecifiedDateData(int userId, String date) {
        Map<String,Object> map = new HashMap<>();
        BloodPressurePO blood = bloodPressureDao.currentPressureDate(userId, date);
        BodyFatScalePO fatScale = bodyFatScaleDao.currrntDodyfatsDate(userId, date);
        OxygenPO oxygen = oxygenDao.oxygenDate(userId, date);
        HeartRatePO heartRate = heartRateDao.heartrateDate(userId, date);
        LungInstrumentPO lung = lungInstrumentDao.lunginstrumentDate(userId, date);
        GluCometerPO gluCometer = gluCometerDao.glucometerDate(userId, date);
        map.put("blood",blood);//血压
        map.put("fatScale",fatScale);//体脂
        map.put("oxygen",oxygen);//血氧
        map.put("heartRate",heartRate); //心率
        map.put("lung",lung); //肺活
        map.put("gluCometer",gluCometer);//血糖
        return map;
    }
}
