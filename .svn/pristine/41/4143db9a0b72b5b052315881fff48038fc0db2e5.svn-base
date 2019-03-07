package com.lifeshs.service.help.impl;

import com.lifeshs.dao.information.IInformationDao;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.help.HelpDTO;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.help.IHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yue.Li
 * @Date 2017/4/27.
 */
@Component
public class HelpServiceImpl implements IHelpService {

    @Autowired
    private IInformationDao iInformationDao;

    @Autowired
    private ICommonTrans commonTrans;

    @Override
    public TInformation getHelpDetail(int id) {
        return commonTrans.get(TInformation.class, id);
    }

    @Override
    public HelpDTO listHelp(String parentName) {
        List<TInfomationColumn> columns = iInformationDao.loadColumnsByParentName(parentName);
        List columnsId = new ArrayList();
        for (TInfomationColumn column : columns) {
            columnsId.add(column.getId());
        }
        List<TInformation> informations = iInformationDao.listInformationName(columnsId);
        HelpDTO helpDTO = new HelpDTO();
        helpDTO.setHelpColumns(columns);
        helpDTO.setHelps(informations);
        return helpDTO;
    }

    @Override
    public List<TInfomationColumn> getTest(String name) {
        List<TInfomationColumn> infomationColumnList = iInformationDao.loadColumnsByParentName(name);
        return infomationColumnList;
    }
}
