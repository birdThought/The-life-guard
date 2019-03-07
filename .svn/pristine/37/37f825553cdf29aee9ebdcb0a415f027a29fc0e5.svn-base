package com.lifeshs.service1.electronicCoupons.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.electronicCoupons.ElectronicCouponsPackageRecordDao;
import com.lifeshs.po.user.UserElectronicCouponsPackageRecordPO;
import com.lifeshs.service1.electronicCoupons.ElectronicCouponsPackageRecordService;

@Service(value = "electronicCouponsPackageRecordService")
public class ElectronicCouponsPackageRecordServiceImpl implements ElectronicCouponsPackageRecordService {

    @Resource(name = "electronicCouponsPackageRecordDao")
    private ElectronicCouponsPackageRecordDao recordDao;
    
    @Override
    public boolean hasRecord(int userId, int packageId) {
        UserElectronicCouponsPackageRecordPO recordPO = recordDao.findRecord(userId, packageId);
        if (recordPO == null) {
            return false;
        }
        return true;
    }

    @Override
    public void addRecord(int userId, int packageId) throws OperationException {
        // 判断用户是否已经有记录
        // 如果已经存在记录就不继续做添加操作
        if (!hasRecord(userId, packageId)) {
            UserElectronicCouponsPackageRecordPO recordPO = new UserElectronicCouponsPackageRecordPO();
            recordPO.setUserId(userId);
            recordPO.setPackageId(packageId);
            int result = recordDao.addRecord(recordPO);
            if (result == 0) {
                throw new OperationException("添加记录失败", ErrorCodeEnum.FAILED);
            }
        }
    }

}
