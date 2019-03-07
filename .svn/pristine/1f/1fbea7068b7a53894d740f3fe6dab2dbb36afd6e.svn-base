package com.lifeshs.service1.serve.visit.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.dao.serve.ServeDao;
import com.lifeshs.pojo.org.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.SignExpressionDTO;
import com.lifeshs.dao.order.CommentDao;
import com.lifeshs.dao.org.employee.IEmployeeManageDao;
import com.lifeshs.dao.serve.visit.VisitDao;
import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.po.CommentPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.VisitPO;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;
import com.lifeshs.pojo.serve.DistanceConditionDTO;
import com.lifeshs.pojo.serve.PriceConditionDTO;
import com.lifeshs.pojo.serve.SortConditionDTO;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.pojo.serve.visit.VisitConditionDTO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.visit.VisitService;
import com.lifeshs.utils.MapApi;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.SignExpressionUtil;
import com.lifeshs.vo.order.comment.CommentVO;
import com.lifeshs.vo.serve.ServeUserVO;
import com.lifeshs.vo.serve.visit.ComboVO;
import com.lifeshs.vo.serve.visit.RecommendedVisitDetailVO;

@Service(value = "v2VisitService")
public class VisitServiceImpl implements VisitService {

    @Resource(name = "visitDao")
    private VisitDao visitDao;
    
    @Resource(name = "orderCommentDao")
    private CommentDao commentDao;
    
    @Resource(name = "employeeManageDao")
    private IEmployeeManageDao employeeDao;

    @Autowired
    private IProjectDao projectDao;

    @Autowired
    private ServeDao serveDao;

    @Override
    public List<RecommendedComboDTO> listRecommendedCombo(Double longitude, Double latitude, int limit) {
        // 如果经纬度数值为0就将该参数置空
        if ((longitude != null && longitude.doubleValue() == 0) && (latitude != null && latitude.doubleValue() == 0)) {
            longitude = null;
            latitude = null;
        }
        List<RecommendedComboDTO> list = visitDao.listLagerNumberOfBuyer(longitude, latitude, limit);
        for (RecommendedComboDTO l : list) {
            VisitServiceDTO vs = l.getCombo().getVisitServe();
            // 距离
            OrgDTO org = vs.getOrg();
            String longitude_os = org.getLongitude();
            String latitude_os = org.getLatitude();
            // 如果没有经纬度，就返回距离为-1，不查询具体距离
            if (longitude == null || latitude == null || StringUtils.isBlank(longitude_os) || StringUtils.isBlank(latitude_os)) {
                l.setDistance(-1);
            } else {
                Double lng_e = Double.valueOf(longitude_os);
                Double lat_e = Double.valueOf(latitude_os);
                // 百度地图计算距离
                double distance = MapApi.getDistance(longitude, latitude, lng_e, lat_e);
                BigDecimal bigDecimal = new BigDecimal(distance);
                // 四舍五入精确到小数点后1位
                double distanceAfterScale = bigDecimal.setScale(1, RoundingMode.HALF_UP).doubleValue();
                l.setDistance(distanceAfterScale);
            }
        }
        list = comboAddScore(list);
        return list;
    }

    @Override
    public VisitConditionDTO getVisitServeCondition(String serveCode) {
        VisitConditionDTO condition = new VisitConditionDTO();
        List<String> type = new ArrayList<>();
        List<ServeTypeDTO> slist = serveDao.listServeTypeByParentId(serveCode);
        type.add("全部");
        for(ServeTypeDTO secondDTO : slist){
            type.add(secondDTO.getName());
        }

        condition.setType(type);
        
        List<PriceConditionDTO> price = new ArrayList<>();
        PriceConditionDTO p1 = new PriceConditionDTO();
        p1.setArea("0-50");
        p1.setName("0-50");
        price.add(p1);
        PriceConditionDTO p2 = new PriceConditionDTO();
        p2.setArea("51-100");
        p2.setName("51-100");
        price.add(p2);
        PriceConditionDTO p3 = new PriceConditionDTO();
        p3.setArea("101-150");
        p3.setName("101-150");
        price.add(p3);
        PriceConditionDTO p4 = new PriceConditionDTO();
        p4.setArea(">150");
        p4.setName("151以上");
        price.add(p4);
        condition.setPrice(price);
        
        List<DistanceConditionDTO> distance = new ArrayList<>();
        DistanceConditionDTO d1 = new DistanceConditionDTO();
        d1.setArea("<1000");
        d1.setName("1千米");
        distance.add(d1);
        DistanceConditionDTO d2 = new DistanceConditionDTO();
        d2.setArea("<3000");
        d2.setName("3千米");
        distance.add(d2);
        DistanceConditionDTO d3 = new DistanceConditionDTO();
        d3.setArea("<5000");
        d3.setName("5千米");
        distance.add(d3);
        DistanceConditionDTO d4 = new DistanceConditionDTO();
        d4.setArea("<10000");
        d4.setName("10千米");
        distance.add(d4);
        DistanceConditionDTO d5 = new DistanceConditionDTO();
        d5.setArea("-1");
        d5.setName("全城");
        distance.add(d5);
        condition.setDistance(distance);

        List<SortConditionDTO> sort = new ArrayList<>();
        SortConditionDTO s1 = new SortConditionDTO();
        s1.setName("智能排序");
        s1.setValue(SortEnum.SCORE_AVG.getValue());
        sort.add(s1);
        SortConditionDTO s2 = new SortConditionDTO();
        s2.setName("人气最高");
        s2.setValue(SortEnum.QUANTITY.getValue());
        sort.add(s2);
        SortConditionDTO s3 = new SortConditionDTO();
        s3.setName("离我最近");
        s3.setValue(SortEnum.DISTANCE.getValue());
        condition.setSort(sort);
        
        return condition;
    }

    @Override
    public Paging<RecommendedComboDTO> listProjectCombo(String areaCode, Double longitude, Double latitude,
            String distanceArea, String priceArea, String type, SortEnum sort, String likeName, ProjectType projectType,
            int curPage, int pageSize) throws OperationException {
        if (longitude == null || latitude == null) {
            throw new OperationException("请定位后再获取具体数据", 400);
        }
        
        // 价格区间解析
        String ptT = null;
        double spT = 0, epT = 0;
        if (StringUtils.isNotBlank(priceArea)) {
            SignExpressionDTO signExpression = SignExpressionUtil.getResult(priceArea);
            if (signExpression != null) {
                spT = signExpression.getStart();
                epT = signExpression.getEnd();
                ptT = signExpression.getExpression().getValue();
            }
        }
        
        // 排序
        /** 如果没有该值，按照默认方式排序 */
        if (sort == null) {
            sort = SortEnum.DEFAULT;
        }
        
        // 地区码
        String acrT = null;
        if (areaCode != null && !"-1".equals(distanceArea)) {
            /** 地区编码的头部 */
            String codeHead = areaCode.substring(0, 4);
            /** 地区编码的尾部 */
            String codeTail = areaCode.substring(4, 6);
            if ("00".equals(codeTail)) {
                acrT = "^" + codeHead + "[[:digit:]]{2}$";
            } else {
                acrT = "^" + codeHead + codeTail + "$";
            }
        }
        
        // 解析距离表达式
        double distance = 0;
        if (distanceArea != null && !"-1".equals(distanceArea)) {
            // 目前距离都是 "<1000" "<2000" 这种格式的数值，直接获取符号后面的数
            SignExpressionDTO distanceExpressionDTO = SignExpressionUtil.getResult(distanceArea);
            distance = distanceExpressionDTO.getStart();
        }
        
        final String areaCodeRegex = acrT;
        final String priceType = ptT;
        final String typeF = type;
        final double startPrice = spT;
        final double endPrice = epT;
        final double endDistance = distance;
        final SortEnum sf = sort;
        
        Paging<RecommendedComboDTO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<RecommendedComboDTO>() {

            @Override
            public int queryTotal() {
                Integer code = null;
                if (projectType != null) {
                    code = projectType.getValue();
                }
                return visitDao.countVisitServeWithCondition(longitude, latitude, areaCodeRegex, priceType, startPrice, endPrice, typeF, likeName, code);
            }

            @Override
            public List<RecommendedComboDTO> queryData(int startRow, int pageSize) {
                Integer code = null;
                if (projectType != null) {
                    code = projectType.getValue();
                }
                
                List<RecommendedComboDTO> data = visitDao.listVisitServeWithCondition(longitude, latitude, areaCodeRegex, priceType,
                        startPrice, endPrice, typeF, likeName, startRow, pageSize, sf, code);
                Iterator<RecommendedComboDTO> iterator = data.iterator();
                while (iterator.hasNext()) {
                    RecommendedComboDTO d = iterator.next();
                    OrgDTO org = d.getCombo().getVisitServe().getOrg();
                    if(org.getLongitude() != null && org.getLatitude() != null) {
                        double distance = MapApi.getDistance(longitude, latitude, Double.valueOf(org.getLongitude()), Double.valueOf(org.getLatitude()));
                        // 过滤距离不符合要求的数据
                        if (distanceArea != null && !"-1".equals(distanceArea)) {
                            if (distance > endDistance) {
                                iterator.remove();
                                continue;
                            }
                        }
                        BigDecimal bigDecimal = new BigDecimal(distance);
                        // 四舍五入精确到小数点后1位
                        double distanceAfterScale = bigDecimal.setScale(1, RoundingMode.HALF_UP).doubleValue();
                        d.setDistance(distanceAfterScale);
                    }
                }
                return comboAddScore(data);
            }
        });
        
        return p;
    }

    @Override
    public RecommendedVisitDetailVO getVisitComboDetail(int comboId) throws OperationException {
        RecommendedVisitDetailVO data = visitDao.findComboDetail(comboId);
        
        String projectCode = data.getProjectCode();
        if (StringUtils.isBlank(projectCode)) {
            throw new OperationException("找不到该套餐信息", 404);
        }
        // 查询套餐
        List<ComboVO> combo = visitDao.findComboByProjectCode(projectCode);
        data.setCombo(combo);
        // 查询评论列表
        List<CommentVO> comment = commentDao.listCommentByProjectCode(projectCode, null, 0, 2);
        data.setComment(comment);
        // 查询服务师
        List<ServeUserVO> serveUser = new ArrayList<>();
        List<EmployeeDTO> employee = employeeDao.findOrgUserByProjectCode(projectCode);
        for (EmployeeDTO e : employee) {
            ServeUserVO s = new ServeUserVO();
            s.setId(e.getId());
            s.setRealName(e.getRealName());
            s.setHead(e.getPhoto());
            serveUser.add(s);
        }
        data.setServeUser(serveUser);
        
        return data;
    }

    @Override
    public List<RecommendedComboDTO> listOrgProjectCombo(int orgId, ProjectType projectType) {
        Integer code = null;
        if (projectType != null) {
            code = projectType.getValue();
        }
        List<RecommendedComboDTO> data = visitDao.findVisitServeByOrgId(orgId, code);
        return comboAddScore(data);
    }

    @Override
    public List<RecommendedComboDTO> listServeUserProjectCombo(int serveUserId, ProjectType projectType) {
        Integer code = null;
        if (projectType != null) {
            code = projectType.getValue();
        }
        List<RecommendedComboDTO> data = visitDao.findVisitServeByServeUserId(serveUserId, code);
        return comboAddScore(data);
    }
    
    private List<RecommendedComboDTO> comboAddScore(List<RecommendedComboDTO> data) {
        for (RecommendedComboDTO d : data) {
            // 分数
            double scoreTotal = 0;
            List<OrderDTO> orderCompleteList =  d.getCombo().getVisitServe().getOrderComplete();
            int orderCompleteListSize = orderCompleteList.size();
            for (OrderDTO orderComplete : orderCompleteList) {
                CommentPO comment = orderComplete.getComment();
                if (comment != null) {
                    scoreTotal += comment.getScore();
                }
            }
            Double score = 0d;
            if (orderCompleteListSize != 0) {
                // 避免被除数为0
                score = NumberUtils.divide(scoreTotal, Integer.valueOf(orderCompleteListSize).doubleValue(), 1);
            }
            d.setScore(score);
        }
        return data;
    }

    @Override
    public RecommendedComboDTO getProjectCombo(int id) {
        return visitDao.findVisitServe(id);
    }

    @Override
    public VisitPO getVisit(String code) {

        return visitDao.findVisit(code);
    }

    @Override
    public void updateVisit(VisitPO visitPO, List<ServiceOrgUserRelationDTO> orgUsers,
                            List<ServiceComboDTO> comboVO, ServiceMediaDTO mediaDTO)throws OperationException {
        try {
            //更新课堂内容
            visitDao.updateVisit(visitPO);

            //更新绑定的服务师
            List<ServiceOrgUserRelationDTO> oldUsers = projectDao.findServiceOrgUserRelationList(visitPO.getCode());
            List<ServiceOrgUserRelationDTO> addId = new ArrayList<>();
            List<Integer> removeId = new ArrayList<>();
            for (ServiceOrgUserRelationDTO oldUser : oldUsers) {   //获取要删除的服务师id
                boolean bool = true;
                for (ServiceOrgUserRelationDTO newUser : orgUsers) {
                    if (oldUser.getOrgUserId().equals(newUser.getOrgUserId())) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    removeId.add(oldUser.getOrgUserId());
                }
            }

            for (ServiceOrgUserRelationDTO newUser : orgUsers) {   //获取要添加的服务师id
                boolean bool = true;
                for (ServiceOrgUserRelationDTO oldUser : oldUsers) {
                    if (oldUser.getOrgUserId().equals(newUser.getOrgUserId())) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    ServiceOrgUserRelationDTO serviceOrgUserRelationDTO = new ServiceOrgUserRelationDTO();
                    serviceOrgUserRelationDTO.setOrgUserId(newUser.getOrgUserId());
                    serviceOrgUserRelationDTO.setProjectCode(visitPO.getCode());
                    addId.add(serviceOrgUserRelationDTO);
                }
            }
            if (removeId.size() > 0) {
                projectDao.delServiceOrgUserRelationList(visitPO.getCode(), removeId);
            }
            if (addId.size() > 0) {
                projectDao.addServiceOrgUserRelation(addId);
            }
            mediaDTO.setProjectCode(visitPO.getCode());

            //处理媒体资料
            projectDao.updateProjectMedia(mediaDTO);    //todo 删除图片

            //处理套餐
            projectDao.delProjectComboList(visitPO.getCode());
            for (ServiceComboDTO serviceComboDTO : comboVO) {
                serviceComboDTO.setProjectCode(visitPO.getCode());
            }
            projectDao.addProjectComboList(comboVO);

            //处理公用表
            ProjectPO projectPO = new ProjectPO();
            projectPO.setProjectCode(visitPO.getCode());
            projectPO.setName(visitPO.getName());
            projectPO.setImage(visitPO.getImage());
            projectPO.setServeId(visitPO.getServeId());
            projectDao.updateProject(projectPO);
        } catch (Exception e) {
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }
    }
}
