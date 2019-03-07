package com.lifeshs.service.record.impl;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.dao.record.IRecordDao;
import com.lifeshs.dao1.measure.BloodLipidDao;
import com.lifeshs.dao1.measure.BloodPressureDao;
import com.lifeshs.dao1.measure.BodyFatScaleDao;
import com.lifeshs.dao1.measure.EcgDao;
import com.lifeshs.dao1.measure.GluCometerDao;
import com.lifeshs.dao1.measure.HeartRateDao;
import com.lifeshs.dao1.measure.LungInstrumentDao;
import com.lifeshs.dao1.measure.OxygenDao;
import com.lifeshs.dao1.measure.SportBandDao;
import com.lifeshs.dao1.measure.TemperatureDao;
import com.lifeshs.dao1.measure.UaDao;
import com.lifeshs.dao1.measure.UranDao;
import com.lifeshs.dao1.member.IMemberDao;
import com.lifeshs.entity.record.TDataFood;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.entity.record.TRecordDiet;
import com.lifeshs.entity.record.TRecordDietFood;
import com.lifeshs.entity.record.TRecordPhysicals;
import com.lifeshs.po.BloodLipidPO;
import com.lifeshs.po.BloodPressurePO;
import com.lifeshs.po.BodyFatScalePO;
import com.lifeshs.po.GluCometerPO;
import com.lifeshs.po.HeartRatePO;
import com.lifeshs.po.LungInstrumentPO;
import com.lifeshs.po.OxygenPO;
import com.lifeshs.po.SportBandPO;
import com.lifeshs.po.TemperaturePO;
import com.lifeshs.po.UaPO;
import com.lifeshs.po.UranPO;
import com.lifeshs.pojo.healthDevice.EcgDetailDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.pojo.record.DietDTO;
import com.lifeshs.pojo.record.DietDetail;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.record.IRecordService;
import com.lifeshs.utils.ConvertUtil;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.FullUserVO;

@Component("record")
public class RecordServiceImpl implements IRecordService {

    @Autowired
    IRecordDao recordDao;

    @Autowired
    ICommonTrans commonTrans;

    @Autowired
    IMemberDao memberDao;

    @Autowired
    private HeartRateDao heartRateDao;

    @Autowired
    private BloodPressureDao bloodPressureDao;

    @Autowired
    private OxygenDao oxygenDao;

    @Autowired
    private BodyFatScaleDao bodyFatScaleDao;

    @Autowired
    private SportBandDao sportBandDao;

    @Autowired
    private GluCometerDao gluCometerDao;

    @Autowired
    private LungInstrumentDao lungInstrumentDao;

    @Autowired
    private EcgDao ecgDao;

    @Autowired
    private TemperatureDao temperatureDao;

    @Autowired
    private UranDao uranDao;

    @Autowired
    private UaDao uaDao;

    @Autowired
    private BloodLipidDao bloodLipidDao;

    /*
     * @Override public Object selectMedicalById(int Id) throws Exception {
     * 
     * TRecordMedical recordMedical=commonTrans.getEntity(TRecordMedical.class,
     * Id); return recordMedical; }
     * 
     * @Override public List<Map<String, Object>> selectMedicalByUserId(Integer
     * userId) throws Exception { Map<String, Object> map=new HashMap<>();
     * map.put("userId", userId); List<Map<String, Object>>
     * list=recordDao.selectMedicalByUserId(map); return list; }
     * 
     * @Override public PaginationDTO selectMedicalByUserIdPageSplit(Integer
     * userId, int nowPage, int pageSize){ Map<String, Object> map = new
     * HashMap<>(); int totalSize =
     * commonTrans.getEntityAmount(TRecordMedical.class, "userId", userId);
     *//**
     * 重新修正pageSize大小
     */

    /*
     * pageSize = pageSize <= 0 ? 1 : pageSize; // 总页数 int totalPage = 1; if
     * (pageSize <= totalSize) { // 如果不能被整除，就数值+1 totalPage = totalSize %
     * pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1; }
     */

    /**
     * 重新修正nowPage 如果nowPage比1小，就将page修改为1 如果nowPage比总页数大，就将nowPage修改为总页数
     *//*
         * if(nowPage <= 0) nowPage = 1; if(nowPage > totalPage) nowPage =
         * totalPage;
         * 
         * int startIndex = (nowPage - 1) * pageSize; // 开始下标
         * 
         * map.put("userId", userId); map.put("startIndex", startIndex);
         * map.put("pageSize", pageSize); List<Map<String, Object>> medicals
         * =recordDao.selectMedicalByUserIdPageSplit(map);
         * 
         * // 封装用户健康档案图片与科室名称，移除不需要的信息 for (Map<String, Object> medical :
         * medicals) { // 图片 Integer medicalId = (Integer)medical.get("id");
         * TRecordMedicalCourse course =
         * commonTrans.findUniqueByProperty(TRecordMedicalCourse.class,
         * "medicalId", medicalId); String photoPath = ""; Boolean getPhotoPath
         * = false; if(course != null){
         * if(StringUtils.isNotBlank(course.getImg1())){ photoPath =
         * course.getImg1(); getPhotoPath = true; }
         * if(StringUtils.isNotBlank(course.getImg2()) && !getPhotoPath){
         * photoPath = course.getImg2(); getPhotoPath = true; }
         * if(StringUtils.isNotBlank(course.getImg3()) && !getPhotoPath){
         * photoPath = course.getImg3(); getPhotoPath = true; } }
         * medical.put("photoPath", photoPath); // 科室 Integer departmentChildId
         * = (Integer)medical.get("departmentChildId"); String departmentName =
         * ""; medical.remove(departmentChildId); medical.put("departmentName",
         * departmentName); // 移除 medical.remove("userId");
         * medical.remove("basicCondition"); medical.remove("createDate");
         * medical.remove("departmentChildId"); }
         * 
         * // 封装数据 PaginationDTO pagination = new PaginationDTO();
         * pagination.setTotalPage(totalPage); pagination.setNowPage(nowPage);
         * pagination.setData(medicals);
         * 
         * return pagination; }
         * 
         * @Override public List<Map<String, Object>>
         * selectMedicalCourseByMedicalId(int medicalId) throws Exception {
         * Map<String, Object> map=new HashMap<>(); map.put("medicalId",
         * medicalId); // List<Map<String, Object>> list
         * =recordDao.selectMedicalCourseByMedicalId(map); // return list;
         * return null; }
         * 
         * @Override public Integer addMedical(HashMap<String, Object>
         * map,Integer userId) throws Exception { TRecordMedical recordMedical
         * =new TRecordMedical(); SimpleDateFormat format = new
         * SimpleDateFormat("yyyy-MM-dd"); recordMedical.setUserId(userId);
         * recordMedical.setTitle((String)map.get("title"));
         * recordMedical.setVisitingDate(format.parse((String)map.get(
         * "visitingDate"))); //
         * recordMedical.setDepartmentChildId((Integer)map.get(
         * "departmentChildId"));
         * recordMedical.setDoctorDiagnosis((String)map.get("doctorDiagnosis"));
         * recordMedical.setBasicCondition((String)map.get("basicCondition"));
         * int result=commonTrans.save(recordMedical); return result; }
         * 
         * @Override public Integer addMedicalCourse(HashMap<String, Object>
         * map, Integer medicalId) throws Exception { TRecordMedicalCourse
         * recordMedicalCourse = new TRecordMedicalCourse(); SimpleDateFormat
         * format = new SimpleDateFormat("yyyy-MM-dd");
         * recordMedicalCourse.setMedicalId(medicalId);
         * recordMedicalCourse.setCourseType((String)map.get("courseType"));
         * recordMedicalCourse.setRemark((String)map.get("remark"));
         * recordMedicalCourse.setVisitingDate(format.parse((String)map.get(
         * "visitingDate")));
         * recordMedicalCourse.setImg1((String)map.get("img1"));
         * recordMedicalCourse.setImg2((String)map.get("img2"));
         * recordMedicalCourse.setImg3((String)map.get("img3")); int
         * result=commonTrans.save(recordMedicalCourse); return result; }
         * 
         * @Override public <T> Integer updataMedical(HashMap<String, Object>
         * map, Integer Id) throws Exception {
         * 
         * TRecordMedical recordMedical =new TRecordMedical();
         * recordMedical=(TRecordMedical) selectMedicalById(Id); //根据ID获取实体
         * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         * recordMedical.setTitle((String)map.get("title"));
         * recordMedical.setVisitingDate(format.parse((String)map.get(
         * "visitingDate"))); //
         * recordMedical.setDepartmentChildId((Integer)map.get(
         * "departmentChildId"));
         * recordMedical.setDoctorDiagnosis((String)map.get("doctorDiagnosis"));
         * recordMedical.setBasicCondition((String)map.get("basicCondition"));
         * 
         * //
         * recordMedical.setCreateDate(format.parse((String)map.get("createDate"
         * ))); int result=commonTrans.updateEntitie(recordMedical); return
         * result; }
         * 
         * @Override public List<Map<String, Object>>
         * selectMedicalCourseByMedicalIdPageSplit(int medicalId, int page, int
         * pageSize) throws Exception { Map<String, Object> map=new HashMap<>();
         * int totalSize=selectMedicalCourseCountByMedicalId(medicalId);
         * 
         * pageSize = pageSize <= 0 ? 1 : pageSize; // 总页数 int totalPage = 1; if
         * (pageSize <= totalSize) { // 如果不能被整除，就数值+1 totalPage = totalSize %
         * pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1; }
         * 
         * if(page <= 0) page = 1; if(page > totalPage) page = totalPage; page =
         * (page - 1) * pageSize; // 开始下标
         * 
         * map.put("medicalId", medicalId); map.put("page", page);
         * map.put("pageSize", pageSize); List<Map<String, Object>> list
         * =recordDao.selectMedicalCourseByMedicalIdPageSplit(map); return list;
         * }
         * 
         * @Override public List<Map<String, Object>>
         * selectMedicalCourseByCourseType(int medicalId, int courseType) throws
         * Exception { Map<String, Object> map=new HashMap<>();
         * map.put("medicalId", medicalId); map.put("courseType", courseType);
         * List<Map<String, Object>> list
         * =recordDao.selectMedicalCourseByCourseType(map); return list; }
         * 
         * @Override public List<Map<String, Object>>
         * selectMedicalCourseByCourseTypePageSplit(int medicalId, int
         * courseType, int page, int pageSize) throws Exception { Map<String,
         * Object> map=new HashMap<>(); int
         * totalSize=selectMedicalCourseCountByMedicalId(medicalId);
         * 
         * pageSize = pageSize <= 0 ? 1 : pageSize; // 总页数 int totalPage = 1; if
         * (pageSize <= totalSize) { // 如果不能被整除，就数值+1 totalPage = totalSize %
         * pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize) + 1; }
         * 
         * if(page <= 0) page = 1; if(page > totalPage) page = totalPage; page =
         * (page - 1) * pageSize; // 开始下标
         * 
         * 
         * map.put("medicalId", medicalId); map.put("courseType", courseType);
         * map.put("page", page); map.put("pageSize", pageSize);
         * List<Map<String, Object>> list
         * =recordDao.selectMedicalCourseByCourseTypePageSplit(map); return
         * list; }
         * 
         * @Override public Integer selectMedicalCourseCountByMedicalId(Integer
         * medicalId) throws Exception { Map<String, Object> map=new
         * HashMap<>(); map.put("medicalId", medicalId); int
         * count=recordDao.selectMedicalCourseCountByMedicalId(map); return
         * count; }
         * 
         * @Override public <T> Integer updataMedicalCourse(HashMap<String,
         * Object> map, Integer id) throws Exception { TRecordMedicalCourse
         * recordMedicalCourse = new TRecordMedicalCourse();
         * System.out.println("map:"+map.toString());
         * recordMedicalCourse=(TRecordMedicalCourse)
         * selectMedicalCourseById(id); SimpleDateFormat format = new
         * SimpleDateFormat("yyyy-MM-dd"); //
         * recordMedicalCourse.setMedicalId(medicalId);
         * recordMedicalCourse.setCourseType((String)map.get("courseType"));
         * recordMedicalCourse.setRemark((String)map.get("remark"));
         * recordMedicalCourse.setVisitingDate(format.parse((String)map.get(
         * "visitingDate")));
         * recordMedicalCourse.setImg1((String)map.get("img1"));
         * recordMedicalCourse.setImg2((String)map.get("img2"));
         * recordMedicalCourse.setImg3((String)map.get("img3")); //
         * recordMedicalCourse.setCreateDate(format.parse((String)map.get(
         * "createDate"))); int
         * result=commonTrans.saveOrUpdate(recordMedicalCourse); return result;
         * }
         * 
         * @Override public <T> Integer deleteMedical(Integer id) throws
         * Exception { int
         * result=commonTrans.deleteEntityById(TRecordMedical.class, id); return
         * result; }
         * 
         * @Override public <T> Integer deleteMedicalCourse(Integer id) throws
         * Exception { int
         * result=commonTrans.deleteEntityById(TRecordMedicalCourse.class, id);
         * return result; }
         */
    @Override
    public <T> List<Map<String, Object>> selectPhysicalsByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Map<String, Object>> list = recordDao.selectPhysicalsByUserId(map);
        return list;
    }

    @Override
    public PaginationDTO selectPhysicalsByUserIdPageSplit(Integer userId, int page, int pageSize) {
        //PaginationDTO pagination = getPhysicalPageBarData(userId, page, pageSize);
        PaginationDTO pagination = new PaginationDTO();

        int totalSize = selectPhysicalsCountByUserId(userId);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(page, pageSize, totalSize);

        if (PaginationDTO.isDataOverFlow(page, pageSize, totalSize)) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        page = queryPageData.getCurPage();

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = recordDao.selectPhysicalsByUserIdPageSplit(map);

        // 封装分页信息
        pagination.setData(list);
        pagination.setNowPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public PaginationDTO getPhysicalsPageBarData(Integer userId, Integer curPage, Integer pageSize) {
        PaginationDTO pagination = new PaginationDTO();
        int totalSize = selectPhysicalsCountByUserId(userId);

        if (PaginationDTO.isDataOverFlow(curPage, totalSize, totalSize)) {
            pagination.setData(new ArrayList<Map<String, Object>>());
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);

        pagination.setNowPage(queryPageData.getCurPage());
        pagination.setTotalPage(queryPageData.getTotalPage());
        pagination.setTotalSize(totalSize);

        return pagination;
    }

    @Override
    public <T> Integer selectPhysicalsCountByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        int result = recordDao.selectPhysicalsCountByUserId(map);
        return result;
    }

    /**
     * @param recordPhysical
     * @return
     * @throws Exception
     * @author zhiguo.lin
     * @DateTime 2016年8月18日 下午8:19:42
     * @serverCode 服务代码
     * @serverComment 添加体检报告
     * @see com.lifeshs.service.record.IRecordService#addPhysicals(com.lifeshs.entity.record.TRecordPhysicals)
     */

    @Override
    public Boolean addPhysicals(TRecordPhysicals recordPhysical) throws Exception {
        if (recordPhysical.getPhysicalsDate() == null || com.lifeshs.utils.StringUtil.isBlank(recordPhysical.getPhysicalsOrg())) {
            throw new ParamException("体检日期和体检机构不能为空", ErrorCodeEnum.MISSING);
        }

        if (recordPhysical.getPhysicalsOrg().length() > 30) {
            throw new ParamException("体检机构字数不能超过30个字符", ErrorCodeEnum.FORMAT);
        }

        if (recordPhysical.getDescription().length() > 150) {
            throw new ParamException("体检报告描述内容不能超过150个字符", ErrorCodeEnum.FORMAT);
        }
        
        // 设置体检报告的信息
        String title = DateTimeUtilT.dateCN(recordPhysical.getPhysicalsDate()) + "体检报告";
        recordPhysical.setTitle(title);
        recordPhysical.setCreateDate(new Date());

        if (recordPhysical.getImg1() != null) {
            String destination = ImageUtilV2.copyImgFileToUploadFolder(recordPhysical.getImg1(), "record");
            recordPhysical.setImg1(destination);
        }
        if (recordPhysical.getImg2() != null) {
            String destination = ImageUtilV2.copyImgFileToUploadFolder(recordPhysical.getImg2(), "record");
            recordPhysical.setImg2(destination);
        }
        if (recordPhysical.getImg3() != null) {
            String destination = ImageUtilV2.copyImgFileToUploadFolder(recordPhysical.getImg3(), "record");
            recordPhysical.setImg3(destination);
        }
        return commonTrans.save(recordPhysical) > 0 ? true : false;
    }

    /**
     * @param target
     * @return
     * @throws IOException
     * @throws Exception
     * @author zhiguo.lin
     * @DateTime 2016年8月18日 下午8:21:53
     * @serverCode 服务代码
     * @serverComment 更新体检报告
     * @see com.lifeshs.service.record.IRecordService#updatePhysicals(com.lifeshs.entity.record.TRecordPhysicals)
     */
    @Override
    public Boolean updatePhysicals(TRecordPhysicals target) throws ParamException, IOException {
        TRecordPhysicals source = commonTrans.getEntity(TRecordPhysicals.class, target.getId());
        if (target.getPhysicalsDate() == null || target.getPhysicalsOrg().isEmpty()) {
            throw new ParamException("体检日期和体检机构不能为空", ErrorCodeEnum.FORMAT);
        }

        if (target.getPhysicalsOrg().length() > 30) {
            throw new ParamException("体检机构字数不能超过30个字符", ErrorCodeEnum.FORMAT);
        }

        String img1 = target.getImg1();
        String img2 = target.getImg2();
        String img3 = target.getImg3();

        if (img1 != null) { // 为null不进行任何操作
            if (img1.equals("")) {
                ImageUtilV2.delImg(source.getImg1());
                source.setImg1("");
            }
            if (!img1.equals("") && !img1.equals(source.getImg1())) { // !img1.equals("") 避免空字符串copyImgFileToUploadFolder
                ImageUtilV2.delImg(source.getImg1()); // 删除原图
                String destination = ImageUtilV2.copyImgFileToUploadFolder(target.getImg1(), "record");
                source.setImg1(destination);
            }
        }
        if (img2 != null) {
            if (img2.equals("")) {
                ImageUtilV2.delImg(source.getImg2());
                source.setImg2("");
            }
            if (!img2.equals("") && !img2.equals(source.getImg3())) {
                ImageUtilV2.delImg(source.getImg2());
                String destination = ImageUtilV2.copyImgFileToUploadFolder(target.getImg2(), "record");
                source.setImg2(destination);
            }
        }
        if (img3 != null) {
            if (img3.equals("")) {
                ImageUtilV2.delImg(source.getImg2());
                source.setImg3("");
            }
            if (!img3.equals("") && !img3.equals(source.getImg3())) {
                ImageUtilV2.delImg(source.getImg3());
                String destination = ImageUtilV2.copyImgFileToUploadFolder(target.getImg3(), "record");
                source.setImg3(destination);
            }
        }

        String title = DateTimeUtilT.dateCN(target.getPhysicalsDate()) + "体检报告";
        source.setTitle(title);
        source.setPhysicalsDate(target.getPhysicalsDate());
        source.setPhysicalsOrg(target.getPhysicalsOrg());
        source.setDescription(target.getDescription());
        return commonTrans.saveOrUpdate(source) > 0 ? true : false;

    }

    @Override
    public Integer deletePhysicals(Integer userId, Integer reportId) {

        // 获取用户的体检报告
        TRecordPhysicals recordPhysical = commonTrans.findUniqueByProperty(TRecordPhysicals.class, "id", reportId);
        int result = 0;
        // 删除该记录关联的图片
        if (recordPhysical != null) {

            if (!recordPhysical.getUserId().equals(userId)) {
                return 0;
            }
            result = commonTrans.deleteEntityById(TRecordPhysicals.class, reportId);
            if (result < 1) {
                return 0;
            } else {
                ImageUtilV2.delImg(recordPhysical.getImg1());
                ImageUtilV2.delImg(recordPhysical.getImg2());
                ImageUtilV2.delImg(recordPhysical.getImg3());
            }
        }
        return result;
    }

    /*
     * @Override public Object selectMedicalCourseById(int id) throws Exception
     * { TRecordMedicalCourse
     * recordMedicalCourse=commonTrans.getEntity(TRecordMedicalCourse.class,
     * id); return recordMedicalCourse; }
     */

    @Override
    public Object selectPhysicalsById(int Id) {
        TRecordPhysicals recordPhysicals = commonTrans.getEntity(TRecordPhysicals.class, Id);
        return recordPhysicals;
    }

    @Override
    public <T> Object selectDietById(int Id) throws Exception {
        TRecordDiet diet = commonTrans.getEntity(TRecordDiet.class, Id);
        return diet;
    }

    @Override
    public List<Map<String, Object>> selectDietByUserIdWithDate(Integer userId, String date) {
        List<Map<String, Object>> list = recordDao.selectDietByUserIdWithDate(userId, date);
        return list;
    }

    @Override
    public <T> Object selectDietFoodById(int Id) throws Exception {
        TRecordDietFood dietFood = commonTrans.getEntity(TRecordDietFood.class, Id);
        return dietFood;
    }

    @Override
    public <T> List<Map<String, Object>> selectDietFoodByDietId(Integer dietId) {
        List<Map<String, Object>> list = recordDao.selectDietFoodByDietId(dietId);
        return list;
    }

    @Override
    public <T> Integer addDiet(HashMap<String, Object> map, Integer userId) throws Exception {
        TRecordDiet recordDiet = new TRecordDiet();
        recordDiet.setUserId(userId);
        recordDiet.setDietType((String) map.get("dietType"));
        recordDiet.setDietTime((Time) map.get("dietTime"));
        recordDiet.setRecordDate(DateTimeUtilT.date((String) map.get("recordDate")));
        recordDiet.setEnergy((Integer) map.get("energy"));
        recordDiet.setCreateDate(new Date());
        commonTrans.save(recordDiet);
        return recordDiet.getId();
    }

    @Override
    public <T> Integer addDietFood(HashMap<String, Object> map, Integer dietId) {
        TRecordDietFood recordDietFood = new TRecordDietFood();
        recordDietFood.setDietId(dietId);
        recordDietFood.setKcal((float) (Integer) map.get("kcal"));
        recordDietFood.setFoodID((Integer) map.get("foodID"));
        recordDietFood.setFoodWeight((Integer) map.get("foodWeight"));
        recordDietFood.setCreateDate(new Date());
        int result = commonTrans.save(recordDietFood);
        return result;
    }

    @Override
    public boolean updataDiet(DietDTO dietDTO) throws Exception {
        Boolean bool = true;
        TRecordDiet recordDiet = dietDTO.getRecordDiet();
        TRecordDiet entity = commonTrans.findUniqueByProperty(TRecordDiet.class, "id", recordDiet.getId());
        entity.setEnergy(recordDiet.getEnergy());
        bool &= commonTrans.updateEntitie(entity) > 0;

        List<Integer> idList = new ArrayList<>();
        Integer dietId = recordDiet.getId();
        List<TRecordDietFood> foodsTmp = commonTrans.findByProperty(TRecordDietFood.class, "dietId", dietId);
        List<TRecordDietFood> foods = dietDTO.getRecordDietFoods();
        /**
         * 先查询用户这条记录对应的食物列表（旧数据）
         * 然后，将用户上传的食物列表直接保存（新数据）
         * 接着对比新旧数据，食物id相同的记录，保留新数据，删除旧数据
         * 
         * 如果，用户上传的食物列表是空的话，就删除掉就数据
         */
        if (foods != null) {
            if (foods.size() > 0 && dietId > 0) {
                for (TRecordDietFood food : foods) {
                    food.setDietId(dietId);
                    food.setCreateDate(new Date());
                    bool &= (commonTrans.saveOrUpdate(food) > 0);

                    if (food.getId() != null) {
                        idList.add(food.getId());
                    }
                }
                Object[] idss = idList.toArray();
                for (TRecordDietFood food : foodsTmp) {
                    if (!ConvertUtil.isIn(food.getId(), idss)) {
                        bool &= commonTrans.delete(food) > 0;
                    }
                }
            } else {
                for (TRecordDietFood food : foodsTmp) {
                    bool &= commonTrans.delete(food) > 0;
                }
            }
        }
        return bool;
    }

    @Override
    public <T> Integer deleteDiet(Integer id) throws Exception {
        int result = commonTrans.deleteEntityById(TRecordDiet.class, id);
        return result;
    }

    @Override
    public <T> Integer deleteDietFood(Integer id) throws Exception {
        int result = commonTrans.deleteEntityById(TRecordDietFood.class, id);
        return result;
    }

    @Override
    public List<Map<String, Object>> selectDietEnergyByUserIdWithDate(Integer userId, boolean customDate,
                                                                      String startDate, String endDate) {
        return recordDao.selectDietEnergyByUserIdWithDate(userId, customDate, startDate, endDate);
    }

    @Override
    public List<String> uploadPhoto(MultipartFile[] files) throws IOException {
        // 存储上传图片的相对路径
        List<String> picPaths = new ArrayList<String>();
        // 文件上传
        for (MultipartFile file : files) {
            String netPath = ImageUtilV2.saveByte(file.getBytes(), "", true);
            picPaths.add(netPath);
        }
        return picPaths;
    }

    @Override
    public List<Map<String, Object>> selectFoodByKind(String kindName) {
        Map<String, Object> map = new HashMap<>();
        map.put("kindName", kindName);
        return recordDao.selectFoodByKind(map);
    }

    @Override
    public List<TDataFoodKind> selectAllFoodKind() {

        return commonTrans.loadAll(TDataFoodKind.class);
    }

    @Override
    public List<TDataFood> selectAllFood() {
        return commonTrans.loadAll(TDataFood.class);
    }

    @Override
    public <T> Integer updataDietFood(Map<String, Object> map, Integer id, Integer dietId) {
        TRecordDietFood recordDietFood = new TRecordDietFood();
        recordDietFood.setId(id);
        recordDietFood.setDietId(dietId);
        recordDietFood.setKcal((float) (Integer) map.get("kcal"));
        recordDietFood.setFoodID(Integer.parseInt((String) map.get("foodID")));
        recordDietFood.setFoodWeight(Integer.parseInt((String) map.get("foodWeight")));
        int result = commonTrans.saveOrUpdate(recordDietFood);
        return result;

    }

    @Override
    public List<DietDetail> selectDietSplitWithRecordDate(int userId, int curPage, int pageSize) {
        List<DietDetail> diets = new ArrayList<>();

        int totalSize = recordDao.selectCountOfDietGroupByDate(userId);

        if (PaginationDTO.isDataOverFlow(curPage, pageSize, totalSize)) {
            return diets;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);
        int startIndex = queryPageData.getStartIndex();
        curPage = queryPageData.getCurPage();

        List<String> recordDates = recordDao.selectDietDateGroupByDateWithPageSplit(userId, startIndex, pageSize);
        diets = recordDao.selectDietsWithDates(userId, recordDates);

        return diets;
    }


    /**
     * 获取指定月存在数据的日期
     *
     * @param userId    用户ID
     * @param queryDate 查询当月的第一天,比如:2017-07-01.
     * @param queryType 1->健康数据,2->体检报告,3->病例记录,4->饮食记录
     * @return
     * @author wuj
     * @updateTime none
     * @since 2017-07-28 12:03:48
     */
    public Map<String, Integer> queryDatesWithData(Integer userId, String queryDate, Integer queryType) throws DataBaseException {
        if (userId == null || queryDate == null || queryType == null) {
            throw new IllegalArgumentException("查询参数不能为空");
        }

        //需要返回的结果集
        Map<String, Integer> result = new HashMap<>();

        switch (queryType) {
            case 1://健康数据
                FullUserVO userVO = memberDao.getMemberById(userId);
                if (userVO == null) {
                    throw new DataBaseException("用户ID:" + userId + "不存在");
                }
                int healthProduct = userVO.getHealthProduct();

                //心率手环
                if ((HealthPackageType.HeartRate.value() & healthProduct) == HealthPackageType.HeartRate.value()) {
                    List<HeartRatePO> list = heartRateDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (HeartRatePO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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
                }

                //血压仪
                if ((HealthPackageType.BloodPressure.value() & healthProduct) == HealthPackageType.BloodPressure.value()) {
                    List<BloodPressurePO> list = bloodPressureDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (BloodPressurePO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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
                }

                //血氧仪
                if ((HealthPackageType.Oxygen.value() & healthProduct) == HealthPackageType.Oxygen.value()) {
                    List<OxygenPO> list = oxygenDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (OxygenPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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

                }

                //体脂称
                if ((HealthPackageType.BodyFatScale.value() & healthProduct) == HealthPackageType.BodyFatScale.value()) {
                    List<BodyFatScalePO> list = bodyFatScaleDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (BodyFatScalePO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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
                }

                //手环
                if ((HealthPackageType.Band.value() & healthProduct) == HealthPackageType.Band.value()) {
                    List<SportBandPO> list = sportBandDao.selectDatesByUserId(userId, queryDate);

                    for (SportBandPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getDate(), "yyyy-MM-dd");

                        // 修复数据覆盖问题
                        if (!result.containsKey(date)) {
                            result.put(date, 0);
                        }
                    }
                }

                //血糖仪
                if ((HealthPackageType.Glucometer.value() & healthProduct) == HealthPackageType.Glucometer.value()) {
                    List<GluCometerPO> list = gluCometerDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (GluCometerPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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
                }

                //肺活仪
                if ((HealthPackageType.Lunginstrument.value() & healthProduct) == HealthPackageType.Lunginstrument.value()) {
                    List<LungInstrumentPO> list = lungInstrumentDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (LungInstrumentPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();

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
                }

                //心电
                if ((HealthPackageType.ECG.value() & healthProduct) == HealthPackageType.ECG.value()) {
                    List<EcgDetailDTO> list = ecgDao.selectMeasureDatesByUserId(userId, DateTimeUtilT.date(queryDate));

                    for (EcgDetailDTO po : list) {
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
                }

                //体温计
                if ((HealthPackageType.Temperature.value() & healthProduct) == HealthPackageType.Temperature.value()) {
                    List<TemperaturePO> list = temperatureDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (TemperaturePO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();

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
                }

                //睡眠    睡眠的数据整合到了SportBand
                if ((HealthPackageType.BandSleep.value() & healthProduct) == HealthPackageType.BandSleep.value()) {
                    // System.out.println("睡眠");
                }

                //记步    记步的数据整合到了SportBand
                if ((HealthPackageType.BandStep.value() & healthProduct) == HealthPackageType.BandStep.value()) {
                    //System.out.println("记步");
                }

                //尿液分析仪
                if ((HealthPackageType.URAN.value() & healthProduct) == HealthPackageType.URAN.value()) {
                    List<UranPO> list = uranDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (UranPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();

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
                }

                //尿检分析仪
                if ((HealthPackageType.UA.value() & healthProduct) == HealthPackageType.UA.value()) {
                    List<UaPO> list = uaDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (UaPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();

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
                }

                //血脂分析仪
                if ((HealthPackageType.BloodLipid.value() & healthProduct) == HealthPackageType.BloodLipid.value()) {
                    List<BloodLipidPO> list = bloodLipidDao.selectMeasureDatesByUserId(userId, queryDate);

                    for (BloodLipidPO po : list) {
                        //具有数据的日期
                        String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");

                        //之前的结果
                        Integer preStatus = result.get(date);

//                        int status = po.getStatus().intValue();
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
                }

                break;
            case 2://体检报告
                Set<String> set1 = recordDao.selectPhysicalsDataByUserIdAndQueryDate(userId, queryDate);

                for (String date : set1) {
                    result.put(date, 0);
                }
                break;
            case 3://病例记录
                Set<String> set2 = recordDao.selectMedicalDataByUserIdAndQueryDate(userId, queryDate);

                for (String date : set2) {
                    result.put(date, 0);
                }
                break;
            case 4://饮食记录
                Set<String> set3 = recordDao.selectDietDataByUserIdAndQueryDate(userId, queryDate);

                for (String date : set3) {
                    result.put(date, 0);
                }
                break;
            default:
                throw new RuntimeException("参数queryType:" + queryType + "错误");
        }

        return result;
    }

    /**
     * 获得用户最后一次的测量日期
     *
     * @param userId
     * @return 日期, Format: "yyyy-MM-dd"
     * @throws DataBaseException 查询不到用户则抛出
     */
    public String getLastDateWithinData(Integer userId) throws DataBaseException {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不允许为空");
        }

        //获取结果集后,需要排序
        List<Date> sortList = new ArrayList<>();

        //查询用户勾选的健康包
        FullUserVO userVO = memberDao.getMemberById(userId);
        if (userVO == null) {
            throw new DataBaseException("用户ID:" + userId + "不存在");
        }
        int healthProduct = userVO.getHealthProduct();

        //心率手环 整合到手环sportBand
        /*if ((HealthPackageType.HeartRate.value() & healthProduct) == HealthPackageType.HeartRate.value()) {
                    *//*dates.addAll(heartRateDao.selectMeasureDatesByUserId(userId, queryDate));*//*
        }*/

        //血压仪
        if ((HealthPackageType.BloodPressure.value() & healthProduct) == HealthPackageType.BloodPressure.value()) {
            Date date = bloodPressureDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //血氧仪
        if ((HealthPackageType.Oxygen.value() & healthProduct) == HealthPackageType.Oxygen.value()) {
            Date date = oxygenDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //体脂称
        if ((HealthPackageType.BodyFatScale.value() & healthProduct) == HealthPackageType.BodyFatScale.value()) {
            Date date = bodyFatScaleDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //手环
        if ((HealthPackageType.Band.value() & healthProduct) == HealthPackageType.Band.value()) {
            Date date = sportBandDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //血糖仪
        if ((HealthPackageType.Glucometer.value() & healthProduct) == HealthPackageType.Glucometer.value()) {
            Date date = gluCometerDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //肺活仪
        if ((HealthPackageType.Lunginstrument.value() & healthProduct) == HealthPackageType.Lunginstrument.value()) {
            Date date = lungInstrumentDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //心电图
        if ((HealthPackageType.ECG.value() & healthProduct) == HealthPackageType.ECG.value()) {
            Date date = ecgDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //体温计
        if ((HealthPackageType.Temperature.value() & healthProduct) == HealthPackageType.Temperature.value()) {
            Date date = temperatureDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //睡眠    睡眠的数据整合到了SportBand
        /*if ((HealthPackageType.BandSleep.value() & healthProduct) == HealthPackageType.BandSleep.value()) {
            // System.out.println("睡眠");
        }*/

        //记步    记步的数据整合到了SportBand
        /*if ((HealthPackageType.BandStep.value() & healthProduct) == HealthPackageType.BandStep.value()) {
            //System.out.println("记步");
        }*/

        //尿液分析仪
        if ((HealthPackageType.URAN.value() & healthProduct) == HealthPackageType.URAN.value()) {
            Date date = uranDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //尿检分析仪
        if ((HealthPackageType.UA.value() & healthProduct) == HealthPackageType.UA.value()) {
            Date date = uaDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        //血脂分析仪
        if ((HealthPackageType.BloodLipid.value() & healthProduct) == HealthPackageType.BloodLipid.value()) {
            Date date = bloodLipidDao.getLastDateByUserId(userId);
            if (date != null) sortList.add(date);
        }

        if (CollectionUtils.isEmpty(sortList)) {
            return null;
        }

        //升序处理
        sortList.sort(null);

        //获得存在数据的最后一天
        Date lastDate = sortList.get(sortList.size() - 1);

        return DateFormatUtils.format(lastDate, "yyyy-MM-dd");
    }

    @Override
    public String getLastDateForUserDietData(Integer userId) {
        List<Date> dates = recordDao.selectLastDietDateWithExistData(userId);

        if (CollectionUtils.isEmpty(dates)) return null;

        dates.sort(null);

        Date lastDate = dates.get(dates.size() - 1);

        return DateFormatUtils.format(lastDate,"yyyy-MM-dd");
    }
}