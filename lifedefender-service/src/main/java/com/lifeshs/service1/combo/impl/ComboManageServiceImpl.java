package com.lifeshs.service1.combo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.combo.ComboDao;
import com.lifeshs.dao1.combo.ComboRelationDao;
import com.lifeshs.dao1.vip.IVipComboDao;
import com.lifeshs.po.vip.VipComboItemRelationPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.service1.combo.ComboManageService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.utils.image.ImageUtilV2;
import com.lifeshs.vo.combo.ComboItemListVo;
import com.lifeshs.vo.combo.ComboVo;

/**
 * 套餐管理服务实现
 * 
 * @author Administrator
 * @Date 2018.1.10 17:59
 */

@Service("comboManageService")
public class ComboManageServiceImpl implements ComboManageService {

	@Autowired
	ComboDao comboDao;
	@Autowired
	ComboRelationDao comboRelationDao;
	@Autowired
	IVipComboDao vipComboDao;

	@Override
	public Paging<ComboVo> findComboList(int curPage, int pageSize) {

		Paging<ComboVo> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<ComboVo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return comboDao.countCombo();
			}

			@Override
			public List<ComboVo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return comboDao.findComboList(startRow, pageSize);
			}

		});
		return p;
	}

	@Override
	public void updataCombo(ComboVo comboVo) throws OperationException {

		int id = comboVo.getId();
		String name = comboVo.getName();
		String description = comboVo.getDescription();
		Integer price = NumberUtils.changeY2F(comboVo.getPrice()+"");
		Integer originalPrice = NumberUtils.changeY2F(comboVo.getOriginalPrice()+"");
		String photo = comboVo.getPhoto();
		Integer validDay = comboVo.getValidDay();
		String detail = comboVo.getDetail();
		Integer type = comboVo.getType();
		String l1 = comboVo.getL1();
		
		try {
			comboDao.updateCombo(id, name, description, price, originalPrice, photo, validDay, detail, type, l1);
		} catch (Exception e) {
			throw new OperationException("套餐更新失败", ErrorCodeEnum.FAILED);
		}
	}
	
	@Override
    public void delOrgUserRelationByVipComboId(Integer vipComboId, String vipComboItemId,String userId) {
        // TODO Auto-generated method stub
        //删除套餐与服务师关联关系
        comboRelationDao.delOrgUserRelationByVipComboId(vipComboId,vipComboItemId,userId);
    }
	
	public void addComboOrgUserRelation(Integer comboId, String vipComboItemId,Integer orgUserId){
	    comboRelationDao.addComboOrgUserRelation(comboId,vipComboItemId,orgUserId);
	}
	
	public int findComboOrgUserRelation(Integer comboId, String vipComboItemId,Integer orgUserId){
        return comboRelationDao.findComboOrgUserRelation(comboId,vipComboItemId,orgUserId);
    }
	
	
	@Override
    public void updataComboItemRelation(Integer comboId, String vipComboItem,String orgUserIds) {
        // TODO Auto-generated method stub
	    //删除套餐次数关联关系
	    comboRelationDao.delRelationByVipComboIdAndcomboItemId(comboId);
	    //删除套餐与服务师关联关系
	    //delOrgUserRelationByVipComboId(comboId);
	    executeAddComboRelation(vipComboItem,comboId,orgUserIds);
    }

	@Override
	public void deleteCombo(ComboVo comboVo) throws OperationException {
		// TODO Auto-generated method stub
		int id = comboVo.getId();
		int vipComboId = id;
		Integer result = comboDao.countComboItem(vipComboId);
		if (result != 0 || result != null) {
			try {
				comboRelationDao.delRelationByVipComboIdAndcomboItemId(vipComboId);
			} catch (Exception e) {
				throw new OperationException("套餐联系删除失败", ErrorCodeEnum.FAILED);
			}

			try {
				comboDao.delCombo(id);
			} catch (Exception e) {
				throw new OperationException("套餐删除失败", ErrorCodeEnum.FAILED);
			}
		}
		if(result == 0 || result == null) {
			try {
				comboDao.delCombo(id);
			} catch (Exception e) {
				throw new OperationException("套餐删除失败", ErrorCodeEnum.FAILED);
			}
		}


	}
	
	@Override
	public void addCombo(String vipComboItem,String orgUserIds, ComboVo comboVo) throws OperationException {
		try {
		    String url = ImageUtilV2.copyImgFileToUploadFolder(comboVo.getPhoto(),"vip/combo");
		    comboVo.setPhoto(url);
		    comboVo.setPrice(NumberUtils.changeY2F(comboVo.getPrice()+""));
		    comboVo.setOriginalPrice(NumberUtils.changeY2F(comboVo.getOriginalPrice()+""));
		    comboVo.setDetail(  comboVo.getDetail());
		    comboDao.addCombo(comboVo);
		    executeAddComboRelation(vipComboItem, comboVo.getId(), orgUserIds);
		    
		}catch (Exception e) {
			throw new OperationException("添加套餐失败",  ErrorCodeEnum.FAILED);
		}
	}
	
	//执行添加套餐关系
	private void executeAddComboRelation(String vipComboItem, Integer comboId, String orgUserIds){
	    if(StringUtils.isNotBlank(vipComboItem)){
	        //添加套餐次数关系
	        String [] idAndNumber = vipComboItem.split(",");
	        String [] ids = new String[idAndNumber.length];
	        for(int i=0; i<idAndNumber.length; i++){
	            ids = idAndNumber[i].split(":");
	            comboRelationDao.addComboRelation(comboId, Integer.parseInt(ids[0]), Integer.parseInt(ids[1]));
	        }
	        
//	        //添加套餐与服务师关系
//	        String [] orgUsers = orgUserIds.split(",");
//	        for(int i=0; i<orgUsers.length; i++){
//                comboRelationDao.addComboOrgUserRelation(comboId,orgUsers[i]);
//            }
	        
	    }
    }
	
	@Override
    public List<VipComboItemRelationPO> findComboItemList(Integer vipComboId) {
        return comboRelationDao.findComboItemList(vipComboId);
    }
//	@Override
//    public List<VipComboItemServeUserRelationPO> findComboItemList(Integer vipComboId) {
//        return comboRelationDao.findComboItemList(vipComboId);
//    }
    

	@Override
	public List<ComboItemListVo> getComboItemList() {
		// TODO Auto-generated method stub
		return comboDao.findItemList();
	}
	
	@Override
    public List<VipComboPO> findL1All(){
	    return vipComboDao.findL1All();
	}
}
