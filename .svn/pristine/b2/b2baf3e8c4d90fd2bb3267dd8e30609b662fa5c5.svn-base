package com.lifeshs.service1.serve.consult.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.Error;
import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.SignExpressionDTO;
import com.lifeshs.dao.order.CommentDao;
import com.lifeshs.dao.serve.consult.ConsultDao;
import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.po.ConsultPO;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.serve.PriceConditionDTO;
import com.lifeshs.pojo.serve.SortConditionDTO;
import com.lifeshs.pojo.serve.consult.ConsultConditionDTO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.consult.ConsultService;
import com.lifeshs.utils.SignExpressionUtil;
import com.lifeshs.vo.order.comment.CommentVO;
import com.lifeshs.vo.serve.consult.OrgUserVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;

@Service(value = "v2ConsultService")
public class ConsultServiceImpl implements ConsultService {

    @Resource(name = "consultDao")
    private ConsultDao consultDao;
    
    @Resource(name = "orderCommentDao")
    private CommentDao commentDao;

    @Autowired
    IProjectDao projectDao;
    
    @Override
    public List<ServeUserVO> listRecommendedConsult(int limit) {
        List<ServeUserVO> dataList = consultDao.findLagerNumberOfBuyerList(limit);
        for (ServeUserVO d : dataList) {
            List<CommentVO> cList = new ArrayList<>();
            for (CommentVO c : d.getComment()) {
                cList.add(c);
                if (cList.size() == 2) {
                    break;
                }
            }
            d.setComment(cList);
        }

        return dataList;
    }

    @Override
    public ConsultConditionDTO getConsultCondition() {
        ConsultConditionDTO condition = new ConsultConditionDTO();
        
        List<String> type = new ArrayList<>();
        type.add("健康养生");
        type.add("慢病康复");
        type.add("母婴");
        type.add("中医");
        condition.setType(type);
        
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
        condition.setPrice(priceCondition);
        
        List<SortConditionDTO> sort = new ArrayList<>();
        SortConditionDTO s1 = new SortConditionDTO();
        s1.setName("综合排序");
        s1.setValue(SortEnum.SCORE_AVG.getValue());
        sort.add(s1);
        SortConditionDTO s2 = new SortConditionDTO();
        s2.setName("咨询人次");
        s2.setValue(SortEnum.QUANTITY.getValue());
        sort.add(s2);
        condition.setSort(sort);
        
        return condition;
    }

    @Override
    public Paging<ServeUserVO> listProjectConsult(String priceArea, String type, SortEnum sort, String likeName, int curPage,
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
        // 排序
        /** 如果没有该值，按照默认方式排序 */
        if (sort == null) {
            sort = SortEnum.DEFAULT;
        }
        
        final String ptf = priceType;
        final double spf = startPrice;
        final double epf = endPrice;
        final String tf = type;
        final SortEnum sf = sort;
        
        Paging<ServeUserVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ServeUserVO>() {

            @Override
            public int queryTotal() {
                return consultDao.countConsultServeUserWithCondition(ptf, spf, epf, tf, likeName);
            }

            @Override
            public List<ServeUserVO> queryData(int startRow, int pageSize) {
                List<ServeUserVO> dataList = consultDao.findConsultServeUserWithConditionList(ptf, spf, epf, tf, likeName, startRow, pageSize, sf);
                for (ServeUserVO d : dataList) {
                    List<CommentVO> cList = new ArrayList<>();
                    for (CommentVO c : d.getComment()) {
                        cList.add(c);
                        if (cList.size() == 2) {
                            break;
                        }
                    }
                    d.setComment(cList);
                }
                return dataList;
            }
        });
        return p;
    }

    @Override
    public ServeUserVO getServeUser(int relationId) {
        ServeUserVO data = consultDao.findServeUser(relationId);
//        if (data.getSid() == null) {
//            data = null;
//        }
        if (data != null) {
            List<CommentVO> cList = new ArrayList<>();
            for (CommentVO c : data.getComment()) {
                cList.add(c);
                if (cList.size() == 2) {
                    break;
                }
            }
            data.setComment(cList);
        }
        return data;
    }
    
    @Override
    public ServeUserVO getServeUser(int serveUserId, String projectCode) {
        ServeUserVO data = consultDao.findServeUserByUserIdAndProjectCode(serveUserId, projectCode);
        if (data != null) {
            List<CommentVO> cList = new ArrayList<>();
            for (CommentVO c : data.getComment()) {
                cList.add(c);
                if (cList.size() == 2) {
                    break;
                }
            }
            data.setComment(cList);
        }
        return data;
    }
    
    @Override
    public List<ServeUserVO> listOrgConsultServe(int orgId) {
        List<ServeUserVO> dataList = consultDao.findConsultServeUserByOrgIdList(orgId);
        for (ServeUserVO d : dataList) {
            List<CommentVO> cList = new ArrayList<>();
            for (CommentVO c : d.getComment()) {
                cList.add(c);
                if (cList.size() == 2) {
                    break;
                }
            }
            d.setComment(cList);
        }
        return dataList;
    }

    @Override
    public List<ServeUserVO> listUserConsultServe(int serveUserId) {
        List<ServeUserVO> dataList = consultDao.findConsultServeUserByServeUserIdList(serveUserId);
        for (ServeUserVO d : dataList) {
            List<CommentVO> cList = new ArrayList<>();
            for (CommentVO c : d.getComment()) {
                cList.add(c);
                if (cList.size() == 2) {
                    break;
                }
            }
            d.setComment(cList);
        }
        return dataList;
    }

    @Override
    public ConsultPO getConsult(String code) {
        return consultDao.findConsult(code);
    }

    @Override
    public void updateConsult(ConsultPO consultPO, List<ServiceOrgUserRelationDTO> orgUsers) throws OperationException {
        try {
            //更新咨询服务内容
            consultDao.updateConsult(consultPO);
            //更新绑定的服务师
            List<ServiceOrgUserRelationDTO> oldUsers = projectDao.findServiceOrgUserRelationList(consultPO.getCode());
            List<ServiceOrgUserRelationDTO> addId = new ArrayList<>();
            List<Integer> removeId = new ArrayList<>();
            List<ServiceOrgUserRelationDTO> modifyId = new ArrayList<>();
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
                        if (!oldUser.getPrice().equals(newUser.getPrice()) 
                            || !oldUser.getMonthPrice().equals(newUser.getMonthPrice())
                            || !oldUser.getYearPrice().equals(newUser.getYearPrice())) {   //获取需要修改价格的服务师id
                            newUser.setProjectCode(consultPO.getCode());
                            modifyId.add(newUser);
                        }
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    ServiceOrgUserRelationDTO serviceOrgUserRelationDTO = new ServiceOrgUserRelationDTO();
                    serviceOrgUserRelationDTO.setOrgUserId(newUser.getOrgUserId());
                    serviceOrgUserRelationDTO.setProjectCode(consultPO.getCode());
                    serviceOrgUserRelationDTO.setPrice(newUser.getPrice());
                    serviceOrgUserRelationDTO.setMonthPrice(newUser.getMonthPrice());
                    serviceOrgUserRelationDTO.setYearPrice(newUser.getYearPrice());
                    addId.add(serviceOrgUserRelationDTO);
                }
            }
            if (removeId.size() > 0) {
                projectDao.delServiceOrgUserRelationList(consultPO.getCode(), removeId);
            }
            if (addId.size() > 0) {
                projectDao.addServiceOrgUserRelation(addId);
            }
            if (modifyId.size() > 0) {
                projectDao.updateServiceOrgUserRelation(modifyId);
            }

            ProjectPO projectPO = new ProjectPO();
            projectPO.setProjectCode(consultPO.getCode());
            projectPO.setName(consultPO.getName());
            projectPO.setImage(consultPO.getImage());
            projectPO.setServeId(consultPO.getServeId());
            projectDao.updateProject(projectPO);
        } catch (Exception e) {
            throw new OperationException(Error.UPDATE_FAILED, ErrorCodeEnum.FAILED);
        }
    }

	@Override
	public List<ServeUserVO> listConsultServeUser() {
		return consultDao.listConsultServeUser();
	}
	
	@Override
	public List<OrgUserVO> listComboOrgUserByComboId(int comboId,int comboItemId){
	    return consultDao.listComboOrgUserByComboId(comboId,comboItemId);
	}
}
