package com.lifeshs.service.shop.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.dao.shop.goods.ShopLabelDao;
import com.lifeshs.service.shop.goods.ShopLabelService;
import com.lifeshs.shop.LabelDTO;

@Transactional
@Service("shop_label_serivce")
public class ShopLabelServiceImpl implements ShopLabelService {
	
	@Autowired
	ShopLabelDao shopLabelDao;
	
	@Override
	public List<LabelDTO> getLabelList() {
		return shopLabelDao.findAllLabel();
	}
}
