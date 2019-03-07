package com.lifeshs.service1.combo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.combo.ComboItemDao;
import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.service1.combo.ComboItemManageService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.image.ImageUtilV2;

/**
 * 
 * 套餐项目管理服务实现
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年6月1日 下午5:02:16
 */

@Service("comboItemManageService")
public class ComboItemManageServiceImpl implements ComboItemManageService {

    @Autowired
    ComboItemDao comboItemDao;

    @Override
    public Paging<VipComboItemPO> listComboItem(int curPage, int pageSize) {

        Paging<VipComboItemPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<VipComboItemPO>() {

            @Override
            public int queryTotal() {
                // TODO Auto-generated method stub
                return comboItemDao.countComboItem();
            }

            @Override
            public List<VipComboItemPO> queryData(int startRow, int pageSize) {
                // TODO Auto-generated method stub
                return comboItemDao.listComboItem(startRow, pageSize);
            }
        });
        return p;
    }

    @Override
    public void addComboItem(VipComboItemPO comboItemVo) throws OperationException {
        try {
            
            comboItemDao.addComboItem(comboItemVo);
        } catch (Exception e) {
            throw new OperationException("添加套餐失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void updataComboItem(VipComboItemPO comboItemVo) throws OperationException {

        int id = comboItemVo.getId();
        String name = comboItemVo.getName();
        String remark = comboItemVo.getRemark();
        String icon = comboItemVo.getIcon();
        String itemDetail = comboItemVo.getItemDetail();

        try {
            comboItemDao.updateComboItem(id, name, remark, icon, itemDetail);
        } catch (Exception e) {
            throw new OperationException("套餐更新失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void deleteComboItem(Integer comboItemId) throws OperationException {
        // TODO Auto-generated method stub
        try {
            comboItemDao.delComboItem(comboItemId);
        } catch (Exception e) {
            throw new OperationException("套餐删除失败", ErrorCodeEnum.FAILED);
        }
    }
}
