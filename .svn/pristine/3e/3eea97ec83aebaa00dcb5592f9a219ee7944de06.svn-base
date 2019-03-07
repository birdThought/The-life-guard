package com.lifeshs.service.record.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao.data.IDepartmentDao;
import com.lifeshs.dao.member.IMemberDao;
import com.lifeshs.dao.record.IRecordDao;
import com.lifeshs.entity.data.TDataDepartment;
import com.lifeshs.entity.record.TRecordMedical;
import com.lifeshs.entity.record.TRecordMedicalCourse;
import com.lifeshs.pojo.member.UserDTO;
import com.lifeshs.pojo.member.UserRecordDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.pojo.record.MedicalCaseVO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.record.IMedicalService;
import com.lifeshs.utils.ConvertUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 病历
 *
 * @author yuhang.weng
 * @DateTime 2016年8月13日 下午3:56:53
 */
@Service
public class MedicalServiceImpl implements IMedicalService {

    @Autowired
    private IRecordDao recordDao;

    @Autowired
    private IDepartmentDao departmentDao;

    @Autowired
    private ICommonTrans commonTrans;

    @Autowired
    private IMemberDao memberDao;

    @Override
    public Map<String, Object> selectMedicalByMedicalIdAndUserId(Integer medicalId, Integer userId) {
        Map<String, Object> medical = recordDao.selectMedicalByMedicalIdAndUserId(medicalId, userId);

        if (medical != null) {
            Integer departmentId = (Integer) medical.get("departmentId");
            // 添加科室
            TDataDepartment department = commonTrans.getEntity(TDataDepartment.class, departmentId);
            medical.put("department", department);
        }

        return medical;
    }

    @Override
    public List<Map<String, Object>> selectMedicalsListByUserId(Integer userId) {
        List<Map<String, Object>> medicals = recordDao.selectMedicalByUserId(userId);
        return medicals;
    }

    @Override
    public PaginationDTO getMedicalByUserIdPageSplit(Integer userId, int nowPage, int pageSize) {
        List<Map<String, Object>> list = new ArrayList<>();

        PaginationDTO pagination = new PaginationDTO();

        int totalSize = commonTrans.getEntityAmount(TRecordMedical.class, "userId", userId);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(nowPage, pageSize, totalSize);

        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();

        pagination.setTotalPage(totalPage);
        pagination.setNowPage(nowPage);
        if (PaginationDTO.isDataOverFlow(nowPage, pageSize, totalSize)) {
            pagination.setData(list);
            return pagination;
        }


        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> medicals = recordDao.selectMedicalByUserIdPageSplit(userId, startIndex, pageSize);

        // 封装用户健康档案图片与科室名称，移除不需要的信息
        for (Map<String, Object> medical : medicals) {
            // 图片
            Integer medicalId = (Integer) medical.get("id");
            List<Map<String, Object>> courses = recordDao.selectMedicalCourseByMedicalId(medicalId);
            String photoPath = "";
            if (courses != null) {
                for (Map<String, Object> course : courses) {
                    if (StringUtils.isNotBlank((String) course.get("img1"))) {
                        photoPath = (String) course.get("img1");
                        break;
                    }
                    if (StringUtils.isNotBlank((String) course.get("img2"))) {
                        photoPath = (String) course.get("img2");
                        break;
                    }
                    if (StringUtils.isNotBlank((String) course.get("img3"))) {
                        photoPath = (String) course.get("img3");
                        break;
                    }
                }
            }
            medical.put("photoPath", photoPath);
            // 科室
            Integer departmentId = (Integer) medical.get("departmentId");
            TDataDepartment department = getTopDepartment(departmentId);
            String departmentName = department.getName();
            medical.put("departmentName", departmentName);
            // 移除
            medical.remove("userId");
            // medical.remove("doctorDiagnosis");
            medical.remove("createDate");
            medical.remove("departmentId");
        }

        // 封装数据

        pagination.setTotalPage(totalPage);
        pagination.setNowPage(nowPage);
        pagination.setData(medicals);

        return pagination;
    }

    @Override
    public PaginationDTO getMedicalByUserIdWithPageSplit(int userId, int curPage, int pageSize) {

        PaginationDTO pagination = new PaginationDTO();

        int totalSize = commonTrans.getEntityAmount(TRecordMedical.class, "userId", userId);

        if (PaginationDTO.isDataOverFlow(curPage, pageSize, totalSize)) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        UserDTO user = memberDao.getUser(userId);

        if (user == null) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        UserRecordDTO recordDTO = user.getRecordDTO();
        String realName = user.getRealName();
        Boolean sex = recordDTO.getGender();
        Date birthday = recordDTO.getBirthday();

        int sex_i = 1;
        if (sex != null) {
            sex_i = sex ? 1 : 0;
        }
        int age = 0;
        if (birthday != null) {
            age = DateTimeUtilT.calculateAge(birthday);
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);
        curPage = queryPageData.getCurPage();
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();

        List<Map<String, Object>> medicals = recordDao.selectMedicalByUserIdPageSplit(userId, startIndex, pageSize);
        for (int i = 0; i < medicals.size(); i++) {
            int medicalId = (int) medicals.get(i).get("id");
            int departmentId = (int) medicals.get(i).get("departmentId");

            TDataDepartment department = commonTrans.getEntity(TDataDepartment.class, departmentId);

            String departmentName = department == null ? "" : department.getName();
            String visitingDate = "";
            Object v = medicals.get(i).get("visitingDate");
            if (v != null) {
                visitingDate = DateTimeUtilT.date((Date) v);
            }

            medicals.get(i).put("id", medicalId + "");
            medicals.get(i).put("visitingDate", visitingDate);
            medicals.get(i).put("departments", departmentName);
            medicals.get(i).put("name", realName);
            medicals.get(i).put("sex", sex_i + "");
            medicals.get(i).put("age", age + "");

            // remove
            medicals.get(i).remove("doctorDiagnosis");
            medicals.get(i).remove("userId");
            medicals.get(i).remove("createDate");
            medicals.get(i).remove("departmentId");
        }

        pagination.setNowPage(curPage);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);
        pagination.setData(medicals);

        return pagination;
    }

    @Override
    public List<Map<String, Object>> selectMedicalCoursesListByMedicalId(int medicalId) {
        return recordDao.selectMedicalCourseByMedicalId(medicalId);
    }

    @Override
    public List<Map<String, Object>> selectMedicalCoursesListByCourseType(int medicalId, String courseType)
            throws Exception {
        return recordDao.selectMedicalCourseByCourseType(medicalId, courseType);
    }

    @Override
    public TRecordMedicalCourse selectMedicalCourseById(int id) {
        return commonTrans.getEntity(TRecordMedicalCourse.class, id);
    }

    @Override
    public PaginationDTO selectMedicalCourseByMedicalIdPageSplit(int medicalId, int page, int pageSize) {
        int totalSize = selectMedicalCourseCountByMedicalId(medicalId);

        PaginationDTO pagination = new PaginationDTO();

        if (PaginationDTO.isDataOverFlow(page, pageSize, totalSize)) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(page, pageSize, totalSize);

        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        page = queryPageData.getCurPage();

        List<Map<String, Object>> list = recordDao.selectMedicalCourseByMedicalIdPageSplit(medicalId, startIndex,
                pageSize);

        pagination.setData(list);
        pagination.setNowPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public PaginationDTO selectMedicalCourseByCourseTypePageSplit(int medicalId, String courseType, int page,
                                                                  int pageSize) {

        PaginationDTO pagination = new PaginationDTO();

        int totalSize = selectMedicalCourseCountByMedicalId(medicalId);

        if (PaginationDTO.isDataOverFlow(page, pageSize, totalSize)) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(page, pageSize, totalSize);

        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        page = queryPageData.getCurPage();

        List<Map<String, Object>> list = recordDao.selectMedicalCourseByCourseTypePageSplit(medicalId, courseType,
                startIndex, pageSize);

        pagination.setData(list);
        pagination.setNowPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public Integer selectMedicalCourseCountByMedicalId(Integer medicalId) {
        return recordDao.selectMedicalCourseCountByMedicalId(medicalId);
    }

    @Override
    public List<TDataDepartment> selectDepartmentsByParentId(Integer parentId) {
        return departmentDao.selectChildDepartmentsDataByParentId(parentId);
    }

    @Override
    public List<TDataDepartment> selectParentsDepartmentByChildId(Integer childDepartmentId) {

        List<TDataDepartment> departments = departmentDao.selectParentsDepartmentByChildId(childDepartmentId);

        return departments;
    }

    @Override
    public Integer addMedicalCourse(Integer userId, Integer medicalId, String courseType, String remark,
                                    Date visitingDate, String img1, String img2, String img3) throws Exception {
        /** 在插入之前判断medicalId对应的病历是否存在 */
        if (recordDao.selectMedicalByMedicalIdAndUserId(medicalId, userId) == null) {
            return 0;
        }

        TRecordMedicalCourse recordMedicalCourse = new TRecordMedicalCourse();
        recordMedicalCourse.setMedicalId(medicalId);
        recordMedicalCourse.setCourseType(courseType);
        recordMedicalCourse.setRemark(remark);
        recordMedicalCourse.setVisitingDate(visitingDate);
        recordMedicalCourse.setImg1(img1);
        recordMedicalCourse.setImg2(img2);
        recordMedicalCourse.setImg3(img3);
        recordMedicalCourse.setCreateDate(new Date());
        return commonTrans.save(recordMedicalCourse);
    }

    @Override
    public Boolean addMedical(Integer userId, MedicalCaseVO medicalCaseVO) throws Exception {
        Boolean bool = true;

        TRecordMedical medical = medicalCaseVO.getMedical();

        if (medical.getDepartmentId() == null) {
            throw new ParamException("请选择病历的科室", ErrorCodeEnum.MISSING);
        }

        medical.setUserId(userId);
        medical.setCreateDate(new Date());

        bool &= commonTrans.save(medical) > 0;

        Integer medicalId = medical.getId();

        List<TRecordMedicalCourse> courses = medicalCaseVO.getCourses();
        if (courses != null && courses.size() > 0 && medicalId > 0) {
            for (TRecordMedicalCourse course : courses) {
                course.setMedicalId(medicalId);
                course.setCreateDate(new Date());
                bool &= (commonTrans.save(course) > 0);
            }
        }

        return bool;
    }

    @Override
    public Integer updataMedical(Integer userId, Integer medicalId, String title, Date visitingDate,
                                 Integer departmentId, String doctorDiagnosis, String basicCondition, String hospital) throws Exception {
        /**
         * 病历的中部分数据不可以重新设置(createDate)，为确保数据完整性，需要将实体查询出来再进行赋值
         */
        TRecordMedical recordMedical = commonTrans.getEntity(TRecordMedical.class, medicalId);
        // recordMedical=(TRecordMedical) selectMedicalById(Id); //根据ID获取实体

        recordMedical.setTitle(title);
        recordMedical.setVisitingDate(visitingDate);
        recordMedical.setDepartmentId(departmentId);
        recordMedical.setDoctorDiagnosis(doctorDiagnosis);
        recordMedical.setBasicCondition(basicCondition);
        recordMedical.setHospital(hospital);

        return commonTrans.updateEntitie(recordMedical);
    }

    @Override
    public Integer updataMedicalCourse(HashMap<String, Object> map, Integer id) throws Exception {

        Object img1 = map.get("img1");
        Object img2 = map.get("img2");
        Object img3 = map.get("img3");

        TRecordMedicalCourse recordMedicalCourse = selectMedicalCourseById(id);
        // System.out.println("map:"+map.toString());
        // recordMedicalCourse.setMedicalId(medicalId);
        recordMedicalCourse.setCourseType((String) map.get("courseType"));
        recordMedicalCourse.setRemark((String) map.get("remark"));
        recordMedicalCourse.setVisitingDate(DateTimeUtilT.date((String) map.get("visitingDate")));
        if (img1 != null) {
            recordMedicalCourse.setImg1((String) img1);
        }
        if (img2 != null) {
            recordMedicalCourse.setImg2((String) img2);
        }
        if (img3 != null) {
            recordMedicalCourse.setImg3((String) img3);
        }
        // recordMedicalCourse.setCreateDate(format.parse((String)map.get("createDate")));
        return commonTrans.saveOrUpdate(recordMedicalCourse);
    }

    @Override
    public Boolean updateMedicalAndCourses(MedicalCaseVO medicalCaseVO) throws Exception {
        Boolean bool = true;

        TRecordMedical medical = medicalCaseVO.getMedical();

        TRecordMedical entity = commonTrans.findUniqueByProperty(TRecordMedical.class, "id", medical.getId());
        entity.setTitle(medical.getTitle());
        entity.setVisitingDate(medical.getVisitingDate());
        entity.setDoctorDiagnosis(medical.getDoctorDiagnosis());
        entity.setBasicCondition(medical.getBasicCondition());
        entity.setDepartmentId(medical.getDepartmentId());
        entity.setHospital(medical.getHospital());

        bool &= commonTrans.updateEntitie(entity) > 0;

        List<Integer> idList = new ArrayList<>();
        List<TRecordMedicalCourse> coursesTmp = commonTrans.findByProperty(TRecordMedicalCourse.class, "medicalId",
                medical.getId());

        Integer medicalId = medical.getId();
        List<TRecordMedicalCourse> courses = medicalCaseVO.getCourses();
        if (courses != null) {
            if (courses.size() > 0 && medicalId > 0) {
                for (TRecordMedicalCourse course : courses) {
                    course.setMedicalId(medicalId);
                    course.setCreateDate(new Date());
                    bool &= (commonTrans.saveOrUpdate(course) > 0);

                    idList.add(course.getId());
                }
                Object[] idss = idList.toArray();
                for (TRecordMedicalCourse course : coursesTmp) {
                    if (!ConvertUtil.isIn(course.getId(), idss)) {
                        bool &= commonTrans.delete(course) > 0;
                    }
                }
            } else {
                // 病历已经被清空完毕
                for (TRecordMedicalCourse course : coursesTmp) {
                    bool &= commonTrans.delete(course) > 0;
                }
            }
        }

        return bool;
    }

    @Override
    public Boolean deleteMedical(Integer userId, Integer medicalId) throws Exception {
        Boolean bool = true;

        TRecordMedical medical = commonTrans.getEntity(TRecordMedical.class, medicalId);
        if (medical == null)
            return false;
        if (!medical.getUserId().equals(userId))
            return false;
        bool &= commonTrans.deleteEntityById(TRecordMedical.class, medicalId) > 0;

        List<TRecordMedicalCourse> courses = commonTrans.findByProperty(TRecordMedicalCourse.class, "medicalId",
                medicalId);
        if (courses != null && courses.size() > 0) {
            for (TRecordMedicalCourse course : courses) {
                // 删图
                if (StringUtils.isNotBlank(course.getImg1())) {
                    ImageUtilV2.delImg(course.getImg1());
                }
                if (StringUtils.isNotBlank(course.getImg2())) {
                    ImageUtilV2.delImg(course.getImg2());
                }
                if (StringUtils.isNotBlank(course.getImg3())) {
                    ImageUtilV2.delImg(course.getImg3());
                }
                /** 利用与运算判断在删除过程中是否出现失败的情况 */
                bool &= commonTrans.delete(course) > 0;
            }
        }

        return bool;
    }

    @Override
    public Boolean deleteMedicalCourse(Integer courserId) {
        /*
         * TRecordMedicalCourse recordMedicalCourse=new TRecordMedicalCourse();
         * recordMedicalCourse=commonTrans.getEntity(TRecordMedicalCourse.class,
         * id); int result=commonTrans.delete(recordMedicalCourse); return
         * result;
         */
        int effectRow = commonTrans.deleteEntityById(TRecordMedicalCourse.class, courserId);
        return effectRow > 0;
    }

    /**
     * 获取顶级部门
     *
     * @param departmentId
     * @return
     * @author yuhang.weng
     * @DateTime 2016年8月15日 下午4:38:57
     */
    private TDataDepartment getTopDepartment(Integer departmentId) {
        TDataDepartment department = departmentDao.selectTopDepartmentByChildId(departmentId);
        return department;
    }

    @Override
    public List<TDataDepartment> selectAllDepartments() {
        return departmentDao.selectAllDepartments();
    }
}
