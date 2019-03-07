package com.lifeshs.service1.electronicCoupons.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsPackageRelationDao;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsTempletDao;
import com.lifeshs.po.electronicCoupons.ElectronicCouponsTempletPO;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsTempletService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.CouponsTempletVO;
import com.lifeshs.vo.electronicCoupons.UpdateElectronicCouponsTempletVO;

@Service(value = "electronicCouponsTempletService")
public class ElectronicCouponsTempletServiceImpl implements ElectronicCouponsTempletService {

    @Resource(name = "electronicCouponsTempletDao")
    private ElectronicCouponsTempletDao templetDao;

    @Resource(name = "electronicCouponsPackageRelationDao")
    private ElectronicCouponsPackageRelationDao relationDao;
    
    @Override
    public void addTemplet(AddElectronicCouponsTempletVO addTemplet) throws BaseException {
        ElectronicCouponsTempletPO templetPO = new ElectronicCouponsTempletPO();
        templetPO.setName(addTemplet.getName());
        templetPO.setPrice(NumberUtils.changeY2F(String.valueOf(addTemplet.getPrice())));
        templetPO.setOrgId(addTemplet.getOrgId());
        templetPO.setServeCode(addTemplet.getServeCode());
        templetPO.setServeItemId(addTemplet.getServeItemId());
        templetPO.setServeItemName(addTemplet.getServeItemName());
        templetPO.setProjectCode(addTemplet.getProjectCode());
        templetPO.setProjectName(addTemplet.getProjectName());
        templetPO.setOverdueModel(addTemplet.getOverDue().getValue());
        templetPO.setValidDay(addTemplet.getValidDay());
        templetPO.setEndDate(addTemplet.getEndDate());
        int result = templetDao.addTemplet(templetPO);
        if (result == 0) {
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public void updateTemplet(UpdateElectronicCouponsTempletVO updateTemplet) throws BaseException {
        // TODO Auto-generated method stub
//        int result = templetDao.updateTemplet(updateTemplet.getElectronicCouponsTempletPO());
//        if (result == 0) {
//            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
//        }
    }

    @Override
    public void deleteTemplet(int id) throws OperationException {
        // 删除模板
        ElectronicCouponsTempletPO templet = new ElectronicCouponsTempletPO();
        templet.setId(id);
        templet.setDeleted(true);
        int result = templetDao.updateTemplet(templet);
        if (result == 0) {
            throw new OperationException("更新失败", ErrorCodeEnum.FAILED);
        }
        // 删除模板关联
        relationDao.delRelationByTempletId(id);
    }

    @Override
    public Paging<CouponsTempletVO> listTemplet(int curPage, int pageSize) {
        Paging<CouponsTempletVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<CouponsTempletVO>() {

            @Override
            public int queryTotal() {
                return templetDao.countTempletWithCondition();
            }

            @Override
            public List<CouponsTempletVO> queryData(int startRow, int pageSize) {
                return templetDao.findTempletListWithCondition(startRow, pageSize, null, null, null);
            }
        });
        return p;
    }

    @Override
    public List<CouponsTempletVO> listTemplet(int orgId, String projectCode, Integer serveItemId) {
        return templetDao.findTempletListWithCondition(null, null, orgId, projectCode, serveItemId);
    }
}
