package com.lifeshs.service1.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.user.UserPhysicalItemDao;
import com.lifeshs.dao1.vip.IVipUserDao;
import com.lifeshs.po.data.PhysicalPO;
import com.lifeshs.po.user.PhysicalItemPO;
import com.lifeshs.service1.member.PhysicalItemService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipUserService;

@Service(value = "userPhysicalItemServiceImpl")
public class PhysicalItemServiceImpl implements PhysicalItemService {

    @Resource(name = "userPhysicalItemDao")
    private UserPhysicalItemDao physicalItemDao;

    @Resource(name = "dataPhysicalServiceImpl")
    private com.lifeshs.service1.data.PhysicalItemService physicalItemDataService;
    
    @Resource(name = "vipUserService")
    private IVipUserService vipUserService;
    
    @Resource(name = "vipUserDao")
    private IVipUserDao vipUserDao;
    
    @Override
    public Paging<PhysicalItemPO> listPhysicalItem(Integer userId, Integer status, Integer physicalItemId,
            Boolean reply, int curPage, int pageSize) {
        Paging<PhysicalItemPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<PhysicalItemPO>() {

            @Override
            public int queryTotal() {
                return physicalItemDao.countPhysicalItemWithCondition(userId, status, physicalItemId, reply);
            }

            @Override
            public List<PhysicalItemPO> queryData(int startRow, int pageSize) {
                return physicalItemDao.findPhysicalItemWithConditionList(startRow, pageSize, userId, physicalItemId, status, reply);
            }
        });
        return p;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void commitPhysicalItem(int userId,int comboId, int physicalItemId) throws OperationException {
        
        PhysicalPO pItemData = physicalItemDataService.getPhysicalItem(physicalItemId);
        if (pItemData == null) {
            throw new OperationException("体检项目不存在", ErrorCodeEnum.NOT_FOUND);
        }
        
        // 判断剩余次数，如果次数等于0就抛出异常
        int comboNumber = vipUserService.getComboNumberById(userId,comboId, 5);
        if(comboNumber == 0) {
        	throw new OperationException("用户剩余体检次数不足", ErrorCodeEnum.FAILED);
        }
        
        PhysicalItemPO item = new PhysicalItemPO();
        item.setUserId(userId);
        item.setPhysicalItemId(physicalItemId);
        item.setStatus(1);
        int result = physicalItemDao.addPhysicalItem(item);
        if (result == 0) {
            throw new OperationException("提交数据失败", ErrorCodeEnum.FAILED);
        }
    }
}
