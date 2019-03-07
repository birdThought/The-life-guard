package com.lifeshs.service1.record.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.record.MedicalCourseDao;
import com.lifeshs.dao1.record.MedicalCourseImgDao;
import com.lifeshs.dao1.record.MedicalDao;
import com.lifeshs.po.record.MedicalCourseImgPO;
import com.lifeshs.po.record.MedicalCoursePO;
import com.lifeshs.po.record.MedicalPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.record.MedicalService;
import com.lifeshs.vo.record.MedicalBasicVO;
import com.lifeshs.vo.record.MedicalCourseVO;
import com.lifeshs.vo.record.MedicalSubmitVO;
import com.lifeshs.vo.record.MedicalVO;

@Service(value = "medicalService")
public class MedicalServiceImpl implements MedicalService {

    @Resource(name = "medicalDao")
    private MedicalDao medicalDao;
    
    @Resource(name = "medicalCourseDao")
    private MedicalCourseDao courseDao;
    
    @Resource(name = "medicalCourseImgDao")
    private MedicalCourseImgDao imgDao;
    
    @Override
    public MedicalVO getMedical(int id, int userId) {
        MedicalVO data = medicalDao.findMedicalByIdAndUserId(id, userId);
        if (data != null) {
            // 对数据按照病程的顺序进行排序 jdk1.8 排序
            Collections.sort(data.getCourseList(), new Comparator<MedicalCourseVO>() {
                @Override
                public int compare(MedicalCourseVO o1, MedicalCourseVO o2) {
                    int courseTypeOrderNumber1 = courseDescToTypeNumber(o1.getCourseType());
                    int courseTypeOrderNumber2 = courseDescToTypeNumber(o2.getCourseType());
                    return courseTypeOrderNumber1 - courseTypeOrderNumber2;
                }
            });
        }
        
        return data;
    }

    @Override
    public MedicalVO getMedical(int id) {
        MedicalVO data = medicalDao.getMedical(id);
        if (data != null) {
            // 对数据按照病程的顺序进行排序 jdk1.8 排序
            Collections.sort(data.getCourseList(), new Comparator<MedicalCourseVO>() {
                @Override
                public int compare(MedicalCourseVO o1, MedicalCourseVO o2) {
                    int courseTypeOrderNumber1 = courseDescToTypeNumber(o1.getCourseType());
                    int courseTypeOrderNumber2 = courseDescToTypeNumber(o2.getCourseType());
                    return courseTypeOrderNumber1 - courseTypeOrderNumber2;
                }
            });
        }
        
        return data;
    }
    
    @Override
    public Paging<MedicalBasicVO> listMedicalBasic(int userId, int curPage, int pageSize) {
        Paging<MedicalBasicVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<MedicalBasicVO>() {

            @Override
            public int queryTotal() {
                return medicalDao.countMedical(userId);
            }

            @Override
            public List<MedicalBasicVO> queryData(int startRow, int pageSize) {
                return medicalDao.findBasicMedicalByUserIdList(userId, startRow, pageSize);
            }
        });
        
        return p;
    }

    @Override
    public Paging<MedicalVO> listMedical(int userId, int curPage, int pageSize) {
        Paging<MedicalVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<MedicalVO>() {

            @Override
            public int queryTotal() {
                return medicalDao.countMedical(userId);
            }

            @Override
            public List<MedicalVO> queryData(int startRow, int pageSize) {
                List<MedicalVO> medicalList = medicalDao.findMedicalByUserIdList(userId, startRow, pageSize);
                for (MedicalVO medical : medicalList) {
                    // 对数据按照病程的顺序进行排序 jdk1.8 排序
                    Collections.sort(medical.getCourseList(), new Comparator<MedicalCourseVO>() {
                        @Override
                        public int compare(MedicalCourseVO o1, MedicalCourseVO o2) {
                            int courseTypeOrderNumber1 = courseDescToTypeNumber(o1.getCourseType());
                            int courseTypeOrderNumber2 = courseDescToTypeNumber(o2.getCourseType());
                            return courseTypeOrderNumber1 - courseTypeOrderNumber2;
                        }
                    });
                }
                return medicalList;
            }
        });
        
        return p;
    }

    @Override
    public void addMedical(MedicalPO medical) throws BaseException {
        Integer userId = medical.getUserId();
        String title = medical.getTitle();
        Date visitingDate = medical.getVisitingDate();
        String hospital = medical.getHospital();
        Integer departmentId = medical.getDepartmentId();
        String doctorDiagnosis = medical.getDoctorDiagnosis();
        String basicCondition = medical.getBasicCondition();
        if (userId == null) {
            throw new ParamException("用户Id不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(title)) {
            throw new ParamException("病历标题不能为空", ErrorCodeEnum.MISSING);
        }
        if (visitingDate == null) {
            throw new ParamException("就诊日期不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(hospital)) {
            throw new ParamException("就诊医院不能为空", ErrorCodeEnum.MISSING);
        }
        if (departmentId == null) {
            throw new ParamException("请选择病历的科室", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(doctorDiagnosis)) {
            throw new ParamException("医生诊断不能为空", ErrorCodeEnum.MISSING);
        }
        if (doctorDiagnosis.length() > 500) {
            throw new ParamException("医生诊断字数不能超过500个字符", ErrorCodeEnum.FORMAT);
        }
        if (StringUtils.isBlank(basicCondition)) {
            throw new ParamException("基本病情不能为空", ErrorCodeEnum.MISSING);
        }
        if (basicCondition.length() > 500) {
            throw new ParamException("基本病情字数不能超过500个字符", ErrorCodeEnum.FORMAT);
        }
        
        int result = medicalDao.addMedical(medical);
        if (result == 0) {
            throw new OperationException("添加病历失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void addMedical(MedicalSubmitVO medical) throws BaseException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteMedical(int id, int userId) throws OperationException {
        int result = medicalDao.delMedicalByIdAndUserId(id, userId);
        if (result == 0) {
            throw new OperationException("删除病历失败", ErrorCodeEnum.FAILED);
        }
        courseDao.delCourseByMedicalIdAndUserIdList(id, userId);
        imgDao.delImgByMedicalIdList(id);
    }

    @Override
    public MedicalCourseVO getMedicalCourse(int id, int userId) {
        return courseDao.findCourseByIdAndUserId(id, userId);
    }

    @Override
    public void addMedicalCourse(MedicalCourseVO course, int userId) throws BaseException {
        Integer medicalId = course.getMedicalId();
        String courseType = course.getCourseType();
        String remark = course.getRemark();
        Date visitingDate = course.getVisitingDate();
        if (medicalId == null) {
            throw new ParamException("病历id不能为空", ErrorCodeEnum.MISSING);
        }
        if (StringUtils.isBlank(courseType)) {
            throw new ParamException("病程类型不能为空", ErrorCodeEnum.MISSING);
        }
        if (!correctCourseType(courseType)) {
            throw new ParamException("病程类型不正确", ErrorCodeEnum.FORMAT);
        }
        if (StringUtils.isBlank(remark)) {
            throw new ParamException("备注不能为空", ErrorCodeEnum.MISSING);
        }
        if (remark.length() > 500) {
            throw new ParamException("备注字数不能超过500个字符", ErrorCodeEnum.FORMAT);
        }
        if (visitingDate == null) {
            throw new ParamException("就诊日期不能为空", ErrorCodeEnum.MISSING);
        }
        
        MedicalVO medicalVO = medicalDao.findMedicalByIdAndUserId(medicalId, userId);
        if (medicalVO == null) {
            throw new OperationException("找不到该病历信息", ErrorCodeEnum.NOT_FOUND);
        }
        
        MedicalCoursePO coursePO = new MedicalCoursePO();
        coursePO.setMedicalId(medicalId);
        coursePO.setCourseType(courseType);
        coursePO.setRemark(remark);
        coursePO.setVisitingDate(visitingDate);
        int result = courseDao.addCourse(coursePO);
        if (result == 0) {
            throw new OperationException("添加病程失败", ErrorCodeEnum.FAILED);
        }

        course.setId(coursePO.getId());
        
        List<MedicalCourseImgPO> imgList = course.getImgList();
        // 对于没有图片的体检报告就不作保存图片的处理
        if (imgList == null || imgList.isEmpty()) {
            return;
        }
        
        List<String> imgStrList = new ArrayList<>();
        for (MedicalCourseImgPO img : imgList) {
            imgStrList.add(img.getImg());
        }
        int resultImg = imgDao.addImgList(imgStrList, coursePO.getId());
        if (resultImg != imgList.size()) {
            throw new OperationException("添加病程图片失败", ErrorCodeEnum.NOT_COMPLETE);
        }
    }

    @Override
    public void deleteMedicalCourse(int id, int userId) throws OperationException {
        int result = courseDao.delCourseByIdAndUserId(id, userId);
        if (result == 0) {
            throw new OperationException("删除病程失败", ErrorCodeEnum.FAILED);
        }
        // 删除病程图片
        imgDao.delImgByMedicalCourseIdList(id);
    }

    @Override
    public void updateMedicalCourse(MedicalCourseVO course, int userId) throws BaseException {
        boolean needUpdate = false; // 是否需要进行更新操作
        
        Integer courseId = course.getId();
        String courseType = course.getCourseType();
        String remark = course.getRemark();
        Date visitingDate = course.getVisitingDate();
        
        if (courseId == null) {
            throw new ParamException("病程id不能为空", ErrorCodeEnum.MISSING);
        }
        if (courseType != null && !correctCourseType(courseType)) {
            throw new ParamException("病程类型不正确", ErrorCodeEnum.FORMAT);
        }
        if (remark != null && remark.length() > 500) {
            throw new ParamException("备注字数不能超过500个字符", ErrorCodeEnum.FORMAT);
        }
        
        /**
         * 判断病程是否存在的代码，在更新病程数据的时候，已经做了验证，如果病程不属于这个用户，或者不存在这条病程的记录，数据就会回滚
        */
        
        // 判断是否需要进行图片修改操作
        List<MedicalCourseImgPO> imgList = course.getImgList();
        if (imgList != null && !imgList.isEmpty()) {
            needUpdate = true;  // 需要更新图片操作
            
            // 需要删除的id
            List<Integer> deleteIdList = new ArrayList<>();
            // 需要更新的图片
            List<MedicalCourseImgPO> updateImgList = new ArrayList<>();
            // 需要添加的图片
            List<MedicalCourseImgPO> addImgList = new ArrayList<>();
            
            Iterator<MedicalCourseImgPO> iterator = imgList.iterator();
            while (iterator.hasNext()) {
                MedicalCourseImgPO img = iterator.next();
                // 删除id不为空，img值为null的记录
                if (img.getId() != null && img.getImg() == null) {
                    deleteIdList.add(img.getId());
                    iterator.remove();
                    continue;
                }
                // 更新id不为空，img值不为null的记录
                if (img.getId() != null && img.getImg() != null) {
                    updateImgList.add(img);
                    iterator.remove();
                    continue;
                }
                // 添加id为空的记录
                if (img.getId() == null) {
                    addImgList.add(img);
                    iterator.remove();
                    continue;
                }
            }
            
            // 删除图片
            if (!deleteIdList.isEmpty()) {
                int resultImg = imgDao.delImgList(deleteIdList);
                if (resultImg != deleteIdList.size()) {
                    throw new OperationException("删除病程图片失败", ErrorCodeEnum.NOT_COMPLETE);
                }
            }
            
            // 更新图片
            if (!updateImgList.isEmpty()) {
                int resultImg = imgDao.updateImgList(updateImgList, courseId);
                if (resultImg != updateImgList.size()) {
                    throw new OperationException("更新病程图片失败", ErrorCodeEnum.NOT_COMPLETE);
                }
            }
            
            // 添加图片
            if (!addImgList.isEmpty()) {
                List<String> imgStrList = new ArrayList<>();
                for (MedicalCourseImgPO img : addImgList) {
                    imgStrList.add(img.getImg());
                }
                int resultImg = imgDao.addImgList(imgStrList, courseId);
                if (resultImg != addImgList.size()) {
                    throw new OperationException("添加病程图片失败", ErrorCodeEnum.NOT_COMPLETE);
                }
            }
        }
        // courseType remark visitingDate
        if (courseType != null || remark != null || visitingDate != null) {
            needUpdate = true;  // 需要更新病程操作
        }
        
        if (needUpdate) {
            MedicalCoursePO coursePO = new MedicalCoursePO();
            coursePO.setId(courseId);
            coursePO.setCourseType(courseType);
            coursePO.setRemark(remark);
            coursePO.setVisitingDate(visitingDate);
            int result = courseDao.updateCourseByIdAndUserId(coursePO, userId);
            if (result == 0) {
                throw new OperationException("更新病程失败", ErrorCodeEnum.FAILED);
            }
        }
    }

    /**
     *  病程类型解析成数字
     *  @author yuhang.weng 
     *  @DateTime 2017年9月14日 下午2:56:37
     *
     *  @param courseDesc
     *  @return
     */
    private int courseDescToTypeNumber(String courseDesc) {
        /** 类型序号 */
        Integer typeNumber = 0;

        if (courseDesc.equals("首诊")) {
            typeNumber = 1;
        }
        if (courseDesc.equals("复诊")) {
            typeNumber = 2;
        }
        if (courseDesc.equals("出院")) {
            typeNumber = 3;
        }
        if (courseDesc.equals("晚期")) {
            typeNumber = 4;
        }
        return typeNumber;
    }

    /**
     *  判断病程类型是否合法
     *  @author yuhang.weng 
     *  @DateTime 2017年9月15日 下午2:48:22
     *
     *  @param courseType
     *  @return
     */
    private boolean correctCourseType(String courseType) {
        if (StringUtils.isBlank(courseType)) {
            return false;
        }
        switch (courseType) {
        case "首诊":
        case "复诊":
        case "出院":
        case "晚期":
            return true;
        }
        return false;
    }
}
