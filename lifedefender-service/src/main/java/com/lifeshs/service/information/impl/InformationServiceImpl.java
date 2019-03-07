package com.lifeshs.service.information.impl;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.model.DataResult;
import com.lifeshs.dao.information.IInformationDao;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.entity.consult.TInformationCollect;
import com.lifeshs.pojo.information.InformationCollectDTO;
import com.lifeshs.pojo.information.InformationDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.information.InformationService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.HtmlUtils;
import com.lifeshs.utils.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XuZhanSi on 2017/1/10 0010.
 */


@Service("informationServiceImpl")

public class InformationServiceImpl extends CommonServiceImpl implements InformationService {
    @Autowired
    IInformationDao informationDao;

    @Override
    public DataResult loadNewsInformationDatas(String columnName, @Nullable Integer fenlei, @Nullable Integer page, @Nullable String search, Integer max) {
        Map<String, Object> condition = new HashMap<>();
        if (search != null)
            condition.put("title", search);
        // 获取所有的column
        List<TInfomationColumn> columns = informationDao.loadColumnsByParentName(columnName);
        // 如果不限制feilei，默认取columnName栏目下的第一项columnId
        if (fenlei != null)
            condition.put("columnId", fenlei);
        else {
            if (columns.size() > 1) {
                TInfomationColumn col = columns.get(0);
                condition.put("columnId", col.getId());
            }
        }
        int nowPage = page == null ? 1 : page; // 默认页码为1
        max = max == null ? 10 : max;   // 默认页面大小为10
        List<TInformation> informations = informationDao.loadInformationList(condition, (nowPage - 1) * max, max);
        if (informations != null && !informations.isEmpty()) {
            for (TInformation information : informations) {
                information.setContent(HtmlUtils.getTextFromHtml(information.getContent()));
            }
        }
        PaginationDTO<TInformation> dto = new PaginationDTO<>(nowPage, max, informationDao.getCountFromInformation(condition), informations);
        DataResult dataResult = new DataResult();
        dataResult.put("columns", columns);
        dataResult.put("informations", dto);

//        dataResult.put("hangye", getHangYeTop5Infomation());//摆5个行业资讯
        dataResult.put("yangsheng", getHealthYsInfoTop5Infomation());//摆5个行业资讯

        dataResult.put("hot", getHotNews());//拿到5个热门的资讯
        return dataResult;
    }

    @Override
    public Paging<TInformation> listInformation(int curPage, int pageSize, int columnId) {
        // 获取所有的column
        List<TInfomationColumn> columnList = informationDao.findColumnByParentId(columnId);
        Map<String, Object> condition = new HashMap<>();
        List<Integer> searColumnIdList = new ArrayList<>();
        
        // 如果不包含子栏目就直接将columnId作为查询条件添加到searColumnIdList里面
        if (columnList.isEmpty()) {
            searColumnIdList.add(columnId);
        } else {
            for (TInfomationColumn c : columnList) {
                searColumnIdList.add(c.getId());
            }
        }
        condition.put("mutiColumn", searColumnIdList);
        
        Paging<TInformation> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<TInformation>() {

            @Override
            public int queryTotal() {
                return informationDao.getCountFromInformation(condition);
            }

            @Override
            public List<TInformation> queryData(int startRow, int pageSize) {
                return informationDao.loadInformationList(condition, curPage, pageSize);
            }
        });
        return p;
    }

    /**
     * 行业资讯部分
     */
    private List<TInformation> getHangYeTop5Infomation() {
        TInfomationColumn hangyeCol = commonTrans.findByProperty(TInfomationColumn.class, "name", "行业资讯").get(0);
        Map<String, Object> hangyeMap = new HashMap<>();
        hangyeMap.put("columnId", hangyeCol.getId());
        List<TInformation> hangYeXun = informationDao.loadInformationList(hangyeMap, 0, 5);
        return hangYeXun;
    }

    /**
     * 养生资讯
     *
     * @return
     */
    private List<TInformation> getHealthYsInfoTop5Infomation() {
        List<TInfomationColumn> tInfomationColumns = commonTrans.findByProperty(TInfomationColumn.class, "name", "养生资讯");
        if (tInfomationColumns == null || tInfomationColumns.size() == 0) {
            return null;
        }
        TInfomationColumn healthYsInfoColumn = commonTrans.findByProperty(TInfomationColumn.class, "name", "养生资讯").get(0);
        Map<String, Object> hangyeMap = new HashMap<>();
        hangyeMap.put("columnId", healthYsInfoColumn.getId());
        List<TInformation> hangYeXun = informationDao.loadInformationList(hangyeMap, 0, 5);
        return hangYeXun;

    }

    @Override
    public DataResult loadHelpInformationDatas(String columnName, @Nullable Integer fenlei, @Nullable Integer page, @Nullable String search) {
        Map<String, Object> condition = new HashMap<>();
        if (search != null)
            condition.put("title", search);
        List<TInfomationColumn> columns = informationDao.loadColumnsByParentName(columnName);
        if (fenlei != null)
            condition.put("columnId", fenlei);
        else {
            if (columns.size() > 1) {
                condition.put("columnId", columns.get(0).getId());
            }
        }
        int nowPage = page == null ? 1 : page;
        int max = 10;
        List<TInformation> informations = informationDao.loadInformationList(condition, (nowPage - 1) * max, max);
        PaginationDTO dto = new PaginationDTO(nowPage, max, informationDao.getCountFromInformation(condition), informations);
        DataResult dataResult = new DataResult();
        Map<String, Object> attr = new HashMap<>();
        attr.put("columns", columns);
        attr.put("informations", dto);
        dataResult.setAttr(attr);
        return dataResult;
    }

    private List<TInformation> getHotNews() {
        List<TInformation> hotNews = (List<TInformation>) cacheService.getCacheValue(CacheType.HOT_NEWS_CACHE, "healthNews");//获取热门的健康资讯
        if (hotNews == null || hotNews.isEmpty()) {
            hotNews = informationDao.findHotInformation(5);
            if (hotNews != null && !hotNews.isEmpty()) {
                for (TInformation information : hotNews) {
                    information.setContent(HtmlUtils.getTextFromHtml(information.getContent()));
                }
            }
            cacheService.saveKeyValue(CacheType.HOT_NEWS_CACHE, "healthNews", hotNews);
        }
        return hotNews;
    }

    @Override
    public DataResult lookNewsById(Integer informationId) {
        DataResult dataResult = new DataResult();
        TInformation information = commonTrans.get(TInformation.class, informationId);
        String columnName = commonTrans.get(TInfomationColumn.class, information.getColumnId()).getName();
        dataResult.put("info", information);
        dataResult.put("columnName", columnName);
        dataResult.put("hot", getHotNews());
        /**
         * 浏览信息记录
         */
        if (information != null) {
            List<String> ipList = (List<String>) cacheService.getCacheValue(CacheType.NEWS_LOOK_CACHE, "news:" + information.getId());
            String remoteIP = ServletUtil.getHost();
            if (ipList == null) {
                ipList = new ArrayList<>();
                ipList.add(remoteIP);
                cacheService.saveKeyValue(CacheType.NEWS_LOOK_CACHE, "news:" + information.getId(), ipList);
            } else {
                if (!ipList.contains(remoteIP)) {
                    ipList.add(remoteIP);
                }
            }
        }
        dataResult.put("hangye", getHangYeTop5Infomation());//摆5个行业资讯
        return dataResult;
    }

    @Override
    public List<TInfomationColumn> loadColumnByParentColName(String parentName) {
        return informationDao.loadColumnsByParentName(parentName);
    }

    @Override
    public List<TInformation> getInformationsByColumnId(Integer columnId) {
        return null;
    }

    @Override
    public boolean addInformationCollect(InformationCollectDTO collect) {
        int result = informationDao.isCollectedOfInformation(collect.getUserId(), collect.getInformationId());
        if (result > 0) {
            return true;
        }
        return informationDao.addInformationCollect(collect) > 0 ? true : false;
    }

    @Override
    public boolean deleteInformationCollect(List ids, int userId) {
        return informationDao.deleteInformationCollect(ids, userId) > 0 ? true : false;
    }

    @Override
    public List<InformationCollectDTO> listInformationCollects(Integer userId) {

        return informationDao.listInformationCollects(userId);
    }

    @Override
    public PaginationDTO<InformationDTO> listInformationByCollect(Integer userId, int pageIndex, int pageSize) {
        List<InformationDTO> list = new ArrayList<>();
        if (pageIndex == 0 ) {
            pageIndex = 1;
        }
        PaginationDTO<InformationDTO> pagination = new PaginationDTO<>();

        int totalSize = commonTrans.getEntityAmount(TInformationCollect.class, "userId", userId);

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, totalSize);

        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();

        pagination.setTotalPage(totalPage);
        pagination.setNowPage(pageIndex);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, totalSize)) {
            pagination.setData(list);
            return pagination;
        }
        List<InformationDTO> data = informationDao.listInformationByCollect(userId, startIndex, pageSize);
        pagination.setData(data);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        return pagination;
    }

    @Override
    public boolean isCollectedOfInformation(int id, int userId) {
        return informationDao.isCollectedOfInformation(userId, id) > 0 ? true : false;
    }
}
