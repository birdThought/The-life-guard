package com.lifeshs.service1.vip.impl;

import com.lifeshs.dao1.vip.IVipComboDao;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipComboService;
import com.lifeshs.vo.record.ComboOrderVo;
import com.lifeshs.vo.record.RecordComboVo;
import com.lifeshs.vo.record.RecordSpreadComboVo;
import com.lifeshs.vo.vip.VipComboVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * vip套餐业务实现
 * author: wenxian.cai
 * date: 2017/10/11 16:51
 */
@Service("vipComboService")
public class VipComboServiceImpl implements IVipComboService{

	@Autowired
	IVipComboDao vipComboDao;

	@Override
	public List<VipComboPO> findVipComboList(List<Integer> idList) {
		return vipComboDao.findVipComboList(idList);
	}

    @Override
    public VipComboVO getVipCombo(int id) {
        return vipComboDao.getVipCombo(id);
    }

    @Override
    public Paging<VipComboVO> listVipCombo(String l1, String l2, int curPage, int pageSize) {
        Paging<VipComboVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<VipComboVO>() {

            @Override
            public int queryTotal() {
                return vipComboDao.countVipCombo(l1, l2);
            }

            @Override
            public List<VipComboVO> queryData(int startRow, int pageSize) {
                return vipComboDao.findVipComboWithItemList(l1, l2, startRow, pageSize);
            }
        });
        
        return p;
    }

    @Override
    public List<RecordSpreadComboVo> getOrderComboData() {
        return vipComboDao.findByOrderComboDataList();
    }

    @Override
    public Paging<RecordComboVo> findByRecordComboList(String moon,Integer superior, Integer page, Integer pagesize) {
        Paging<RecordComboVo> p = new Paging<>(page,pagesize);
        p.setQueryProc(new IPagingQueryProc<RecordComboVo>() {
            @Override
            public int queryTotal() {
                return vipComboDao.findByCountRecordCombo(moon,superior);
            }

            @Override
            public List<RecordComboVo> queryData(int startRow, int pageSize) {
                return vipComboDao.findByRecordComboList(moon,superior,(page -1) * pageSize,pageSize);
            }
        });
	    return p;
    }

    @Override
    public List<ComboOrderVo> findByComboOrderDetails(Integer superior, String format) {
        return vipComboDao.findByRecordDetails(superior,format);
    }
}
