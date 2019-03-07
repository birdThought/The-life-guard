package com.lifeshs.service.data.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.common.AdType;
import com.lifeshs.dao.data.IAdDao;
import com.lifeshs.pojo.data.AdDTO;
import com.lifeshs.service.data.IAdService;

@Service(value = "adService")
public class AdServiceImpl implements IAdService {

    @Resource(name = "adDao")
    private IAdDao adDao;
    
    @Override
    public List<AdDTO> listBanner() {
        return adDao.listAd(AdType.BANNER, 3);
    }

}
