package com.lifeshs.service1.electronicCoupons.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsPackageDao;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsPackageRelationDao;
import com.lifeshs.po.electronicCoupons.ElectronicCouponsPackagePO;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.RandCodeUtil;
import com.lifeshs.vo.electronicCoupons.AddElectronicCouponsPackageVO;
import com.lifeshs.vo.electronicCoupons.ElectronicCouponsPackageVO;

@Service(value = "electronicCouponsPackageService")
public class ElectronicCouponsPackageServiceImpl implements ElectronicCouponsPackageService {

    @Resource(name = "electronicCouponsPackageDao")
    private ElectronicCouponsPackageDao packageDao;

    @Resource(name = "electronicCouponsPackageRelationDao")
    private ElectronicCouponsPackageRelationDao relationDao;

    @Override
    public void addPackage(AddElectronicCouponsPackageVO addPackageVO) throws BaseException {
        String code = RandCodeUtil.randNumberCodeByCustom("5", 8);
        
        ElectronicCouponsPackagePO pPO = new ElectronicCouponsPackagePO();
        pPO.setName(addPackageVO.getName());
        pPO.setInstructions(addPackageVO.getInstructions());
        pPO.setBusinessId(addPackageVO.getBusinessId());
        pPO.setCode(code);
        
        // 如果code重复，最多重新生成5次code
        int result = 0;
        for (int i = 0; i < 5; i++) {
            try{
                result = packageDao.addPackage(pPO);
                break;
            } catch (DuplicateKeyException e) {
                // 重新生成code
                code = RandCodeUtil.randNumberCodeByCustom("5", 8);
                pPO.setCode(code);
            }
        }
        // result表示sql语句影响行数(effectRow)
        if (result == 0) {
            // 添加失败 抛出异常 会触发事务进行回滚
            throw new OperationException("添加失败", ErrorCodeEnum.FAILED);
        }
        
        // TODO 如果templetIdList集合不为空，需要调用relationDao.addRelation(int, List<Integer>)方法将卡包与电子券模板添加关联关系
        List<Integer> tepmletList =  addPackageVO.getTempletIdList();
        if(tepmletList != null && !tepmletList.isEmpty()) {
            relationDao.addRelation(pPO.getId(), tepmletList);
        }
        // packageId可以通过mybatis的 selectKey 获取insert操作返回的主键
        // 因为不能确定addRelation方法会有多少条sql语句产生作用，所以不需要判断effectRow来抛出异常

    }

    @Override
    public void updatePackage(AddElectronicCouponsPackageVO updatePackageVO) throws BaseException {
        /*ElectronicCouponsPackagePO packagePO = updatePackageVO.getElectronicCouponsPackagePO();
        int result = packageDao.updatePackage(packagePO);
        // TODO 如果templetIdList集合不为空，需要先删除与packageId有关的所有关联(relationDao.delRelationByPackageIdAndTempletId(int, List<Integer>))
        // 然后重新addRelation建立关联关系
        List<Integer> templetIdList =  updatePackageVO.getTempletIdList();
        if(templetIdList!=null && !templetIdList.isEmpty()) {
            int packageId =  packagePO.getId();
            relationDao.delRelationByPackageIdAndTempletId(packageId, null);
            relationDao.addRelation(packageId, templetIdList);
        }*/
    
    }

    @Override
    public void deletePackage(int id) throws OperationException {
        ElectronicCouponsPackagePO packagePO = new ElectronicCouponsPackagePO();
        packagePO.setId(id);
        packagePO.setDeleted(true);
        int result = packageDao.updatePackage(packagePO);
        // TODO 通过result的值判断deletePackage()方法是否正常执行
        // TODO 调用relationDao.delRelationByPackageIdAndTempletId(int, List<Integer>)删除与这个卡包有关的关联关系
        if (result == 0) {
            // 添加失败 抛出异常 会触发事务进行回滚
            throw new OperationException("删除失败", ErrorCodeEnum.FAILED);
        }
        relationDao.delRelationByPackageIdAndTempletId(id, null);
    }

    @Override
    public ElectronicCouponsPackageVO getPackage(int id) {
        return packageDao.getPackage(id);
    }

    @Override
    public ElectronicCouponsPackageVO getPackage(String code) {
        return packageDao.findPackageByCode(code);
    }

    @Override
    public Paging<ElectronicCouponsPackageVO> listPackage(int curPage, int pageSize) {
        Paging<ElectronicCouponsPackageVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ElectronicCouponsPackageVO>() {

            @Override
            public int queryTotal() {
                return packageDao.countPackageWithCondition();
            }

            @Override
            public List<ElectronicCouponsPackageVO> queryData(int startRow, int pageSize) {
                return packageDao.findPackageListWithCondition(startRow, pageSize);
            }
        });
        return p;
    }

}
