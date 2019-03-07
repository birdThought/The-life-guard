package com.lifeshs.service.terminal.impl;

import com.lifeshs.common.model.DataResult;
import com.lifeshs.dao.information.IInformationDao;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.terminal.IWebIndexService;
import com.lifeshs.utils.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XuZhanSi on 2017/2/13 0013.
 */
@Service
public class AppWebIndexServiceImpl extends CommonServiceImpl implements IWebIndexService {

    @Autowired
    IInformationDao informationDao;

    @Override
    public List<TInfomationColumn> loadInformationColumns(String parentColumnName) {
        List<TInfomationColumn> columns = informationDao.loadColumnsByParentName(parentColumnName);
        return columns != null && !columns.isEmpty() ? columns : null;
    }

    @Override
    public PaginationDTO getInformationsByPage(Integer col, Integer page, Integer max) {
        int nowPage = page == null ? 1 : page;
        Map<String, Object> condition = new HashMap<>();
        condition.put("columnId", col);
        List<TInformation> informations = informationDao.loadInformationList(condition, (nowPage - 1) * max, max);
        if (informations != null && !informations.isEmpty()) {
            for (TInformation information : informations) {
                information.setContent(HtmlUtils.getTextFromHtml(information.getContent()));
            }
        }
        PaginationDTO dto = new PaginationDTO(nowPage, max, informationDao.getCountFromInformation(condition), informations);
        DataResult dataResult = new DataResult();
        dataResult.put("informations", dto);
        return dto;
    }
}
