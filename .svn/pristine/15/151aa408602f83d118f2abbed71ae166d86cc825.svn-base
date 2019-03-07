package com.lifeshs.service1.serve.lesson.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.HuanxinCmdActionType;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.SignExpressionDTO;
import com.lifeshs.dao.org.lesson.ILessonGroupDao;
import com.lifeshs.dao.org.service.IOrgServiceManageDao;
import com.lifeshs.dao1.project.ILessonDao;
import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.dto.manager.serve.GetLessonByHXData;
import com.lifeshs.po.LessonPO;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.po.UserPO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.serve.PriceConditionDTO;
import com.lifeshs.pojo.serve.lesson.LessonConditionDTO;
import com.lifeshs.pojo.serve.lesson.LessonProjectDTO;
import com.lifeshs.pojo.serve.lesson.RecommendedLessonDTO;
import com.lifeshs.service1.order.OrderService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.InterfaceP;
import com.lifeshs.service1.page.Pagination;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.lesson.LessonService;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.utils.SignExpressionUtil;

/**
 *  课堂
 *  @author yuhang.weng
 *  @version 2.0
 *  @DateTime 2017年6月19日 下午4:57:33
 */
@Service(value = "v2LessonService")
public class LessonServiceImpl implements LessonService {

    @Autowired
    private ILessonGroupDao lessonDao;

    @Autowired
    ILessonDao lessonDaoV2;

    @Autowired
    private HuanXinService huanxinService;

    @Autowired
    private IProjectDao projectDao;
    
    @Resource(name = "v2OrderService")
    private OrderService orderService;

    @Autowired
    IOrgServiceManageDao orgServiceManageDao;
    
    @Override
    public List<RecommendedLessonDTO> listRecommendedLesson(int limit) {
        return lessonDao.listLagerNumberOfBuyer(limit);
    }

    @Override
    public LessonConditionDTO getLessonCondition() {
        LessonConditionDTO lessonCondition = new LessonConditionDTO();
        List<String> type = new ArrayList<>();
        type.add("全部");
        type.add("慢病康复");
        type.add("健康养生");
        type.add("母婴学堂");
        type.add("中医");
        lessonCondition.setType(type);
        
        List<PriceConditionDTO> priceCondition = new ArrayList<>();
        PriceConditionDTO p0 = new PriceConditionDTO();
        p0.setName("全部");
        p0.setArea(">-1");
        priceCondition.add(p0);
        PriceConditionDTO p1 = new PriceConditionDTO();
        p1.setName("免费");
        p1.setArea("=0");
        priceCondition.add(p1);
        PriceConditionDTO px = new PriceConditionDTO();
        px.setName("小于1");
        px.setArea("0.001-0.999");
        priceCondition.add(px);
        PriceConditionDTO p2 = new PriceConditionDTO();
        p2.setName("1-20");
        p2.setArea("1-20");
        priceCondition.add(p2);
        PriceConditionDTO p3 = new PriceConditionDTO();
        p3.setName("21-50");
        p3.setArea("21-50");
        priceCondition.add(p3);
        PriceConditionDTO p4 = new PriceConditionDTO();
        p4.setName("51以上");
        p4.setArea(">50");
        priceCondition.add(p4);
        lessonCondition.setPrice(priceCondition);
        
        return lessonCondition;
    }

    @Override
    public PaginationDTO<RecommendedLessonDTO> listProjectLesson(String priceArea, String type, String likeName, int curPage,
            int pageSize) {
        String priceType = null;
        double startPrice = 0, endPrice = 0;
        if (StringUtils.isNotBlank(priceArea)) {
            SignExpressionDTO signExpression = SignExpressionUtil.getResult(priceArea);
            if (signExpression != null) {
                startPrice = signExpression.getStart();
                endPrice = signExpression.getEnd();
                priceType = signExpression.getExpression().getValue();
            }
        }
        
        // 类型如果是全部，就取消该条件
        if ("全部".equals(type)) {
            type = null;
        }
        
        int totalSize = lessonDao.countLessonWithCondition(priceType, startPrice, endPrice, type, likeName);
        // 下面这段代码是一个弊端，需要将变量声明为final才可以在实现接口中正常使用
        final String ptf = priceType;
        final double spf = startPrice;
        final double epf = endPrice;
        final String tf = type;
        
        Pagination<RecommendedLessonDTO> p = new Pagination<>(new InterfaceP<RecommendedLessonDTO>() {

            @Override
            public List<RecommendedLessonDTO> getData(int startIndex) {
                List<RecommendedLessonDTO> data = lessonDao.listLessonWithCondition(ptf, spf, epf, tf, likeName, startIndex, pageSize);
                return data;
            }
        });
        
        return p.getPagination(totalSize, curPage, pageSize);
    }
    
    @Override
    public RecommendedLessonDTO getLesson(int id) {
        return lessonDao.findLesson(id);
    }

    @Override
    public List<RecommendedLessonDTO> listLessonByOrgId(int orgId) {
        return lessonDao.findLessonByOrgId(orgId);
    }

    @Override
    public List<RecommendedLessonDTO> listLessonByServeUserId(int serveUserId) {
        return lessonDao.findLessonByServeUserId(serveUserId);
    }

    /**
     * 得到单条健康课堂信息
     * @param projectCode
     * @return
     */
    public LessonPO getLesson(String projectCode){
        return lessonDaoV2.getLesson(projectCode);
    }

    /**
     * 得到单条健康课堂信息（按环信ID）
     * @param huanxinId
     * @return
     */
    public LessonPO findLessonByHuanxinId(String huanxinId){
        List<String> huanxinIdList = new ArrayList<>();
        huanxinIdList.add(huanxinId);
        LessonPO data = null;
        List<LessonPO> result = lessonDaoV2.findLessonByHuanxinId(huanxinIdList);
        if (!result.isEmpty()) {
            data = result.get(0);
        }
        return data;
    }
    
    @Override
    public List<LessonPO> findLessonByHuanxinId(List<String> huanxinIdList) {
        List<LessonPO> data = new ArrayList<>();
        if (!huanxinIdList.isEmpty()) {
            data = lessonDaoV2.findLessonByHuanxinId(huanxinIdList);
        }
        return data;
    }

    /**
     *  获取课堂信息，根据环信ID
     *  <p>该方法会查询用户所属的全部环信群组信息，然后根据传递的huanxinIds数组，对应的过滤查询结果
     *
     *  @param huanxinIds
     *  @param userId
     *  @return
     */
    public List<GetLessonByHXData> getLessonListByHX(List<String> huanxinIds, int userId){
        return lessonDaoV2.getLessonListByHX(huanxinIds);
    }

    @Override
    public boolean gag(String projectCode) {
        LessonPO lessonPO = getLesson(projectCode);
        String huanxinId = lessonPO.getHuanxinId();

        List<String> receiverHuanxinId = new ArrayList<>();
        receiverHuanxinId.add(huanxinId);
        boolean success = huanxinService.sendGroupTransparentMsg(HuanxinCmdActionType.GAG, receiverHuanxinId);
        if (!success) {
            return false;
        }

        lessonDaoV2.updateGag(projectCode, 1);
        return true;
    }

    @Override
    public boolean removeGag(String projectCode) {
        LessonPO lessonPO = getLesson(projectCode);
        String huanxinId = lessonPO.getHuanxinId();

        List<String> receiverHuanxinId = new ArrayList<>();
        receiverHuanxinId.add(huanxinId);
        boolean success = huanxinService.sendGroupTransparentMsg(HuanxinCmdActionType.GAG_REMOVE, receiverHuanxinId);
        if (!success) {
            return false;
        }

        lessonDaoV2.updateGag(projectCode, 0);
        return true;
    }

    @Override
    public List<UserPO> findLessonMemberList(String code) {
        return lessonDaoV2.findLessonMemberList(code);
    }

    @Override
    public void removeUser(int id, int userId) throws OperationException {
        RecommendedLessonDTO lesson = lessonDao.findLesson(id);
        LessonProjectDTO p = lesson.getLessonProject();
        List<OrderPO> orderList = orderService.listOrderByProjectCode(p.getCode());
        String userCode = null;
        Integer orderId = null;
        for (OrderPO o : orderList) {
            // 找到订单状态是有效的，并且userId与用户id相同的那条订单id
            if (OrderStatus.VALID.getStatus().intValue() == o.getStatus() && userId == o.getUser().getId()) {
                userCode = o.getUser().getUserCode();
                orderId = o.getId();
                break;
            }
        }
        
        if (userCode == null) {
            throw new OperationException(String.format(Error.NOT_FOUND, "用户"), ErrorCodeEnum.NOT_FOUND);
        }
        if (orderId == null) {
            throw new OperationException(String.format(Error.NOT_FOUND, "订单"), ErrorCodeEnum.NOT_FOUND);
        }
        
        // 完成订单
        boolean success = orderService.completeOrder(orderId);
        if (success) {
            /** 环信退出群组 */
            huanxinService.removeGroupUser(p.getHuanxinId(), userCode);
        }
    }

    @Override
    public List<LessonPO> listLessonOutOfEndDate(int remainDay) {
        return lessonDaoV2.findLessonOutOfEndDate(remainDay);
    }

    @Override
    public Paging<LessonPO> findLessonListByServices(Integer orgUserId, String name, Integer curPage, Integer pageSize) {
        Paging<LessonPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<LessonPO>() {
            @Override
            public int queryTotal() {
                return lessonDaoV2.countLessonByServices(orgUserId, name);
            }

            @Override
            public List queryData(int startRow, int pageSize) {
                List<LessonPO> data = lessonDaoV2.findLessonListByServices(orgUserId, name, startRow, pageSize);
                /*for (LessonPO lessonPO : data) {
                    if (lessonPO.getPrice() != null) {
                        lessonPO.setPrice(NumberUtils.changeF2Y(lessonPO.getPrice().intValue() + ""));
                    }
                }*/
                return data;
            }
        });
        return p;
    }

    @Override
    public void updateLesson(LessonPO lesson, List<ServiceOrgUserRelationDTO> orgUsers) throws OperationException{
        try {
            //更新课堂内容
            lessonDaoV2.updateLesson(lesson);
            //更新绑定的服务师
            List<ServiceOrgUserRelationDTO> oldUsers = projectDao.findServiceOrgUserRelationList(lesson.getCode());
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
                    serviceOrgUserRelationDTO.setProjectCode(lesson.getCode());
                    serviceOrgUserRelationDTO.setPrice((lesson.getPrice().doubleValue()));
                    addId.add(serviceOrgUserRelationDTO);
                }
            }
            if (removeId.size() > 0) {
                projectDao.delServiceOrgUserRelationList(lesson.getCode(), removeId);
            }
            if (addId.size() > 0) {
                projectDao.addServiceOrgUserRelation(addId);
            }

            ProjectPO projectPO = new ProjectPO();
            projectPO.setProjectCode(lesson.getCode());
            projectPO.setName(lesson.getName());
            projectPO.setPrice(lesson.getPrice().doubleValue());
            projectPO.setImage(lesson.getImage());
            projectPO.setServeId(lesson.getServeId());
            projectDao.updateProject(projectPO);
        } catch (Exception e) {
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }
    }


}
