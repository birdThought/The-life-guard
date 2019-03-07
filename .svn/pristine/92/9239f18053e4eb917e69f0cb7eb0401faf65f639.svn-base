package com.lifeshs.service1.serve.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.serve.*;
import com.lifeshs.pojo.serve.visit.VisitConditionDTO;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao.serve.ServeDao;
import com.lifeshs.po.org.TServerPO;
import com.lifeshs.service1.serve.ServeService;
import com.lifeshs.vo.serve.ServeStatisticsVO;

@Service(value = "v2ServeService")
public class ServeServiceImpl implements ServeService {

    @Resource(name = "serveDao")
    private ServeDao serveDao;
    
    @Override
    public List<ServeTypeDTO> listFirstServeType() {
        return serveDao.listFirstServeType();
    }

    @Override
    public List<ServeTypeSecondDTO> listServeType(){
        return serveDao.listServeType();
    }

    @Override
    public List<ServeStatisticsVO> listServeStatistics(String userNo) {
        return serveDao.findServeStatistics(userNo);
    }

    @Override
    public void addServe(TServerPO serve) throws OperationException {
        int result = serveDao.addServe(serve);
        if (result == 0) {
            throw new OperationException("添加失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void modifyServe(TServerPO serve) throws OperationException {
        int result = serveDao.updateServe(serve);
        if (result == 0) {
            throw new OperationException("修改失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void deleteServe(int id) throws OperationException {
        int result = serveDao.delServe(id);
        if (result == 0) {
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
    }
    @Override
    public ServeTypeSecondDTO getByServeItemId(int serveItemId) {

        return serveDao.getByServeItemId(serveItemId);
    }

    @Override
    public ServeConditionDTO getServeCondition() {
        ServeConditionDTO condition = new ServeConditionDTO();
        List<String> type = new ArrayList<>();
        List<ServeTypeDTO> slist = serveDao.listFullServeType();
        condition.setType(slist);

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
}
