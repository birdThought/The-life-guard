package com.lifeshs.service1.data.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.app.banner.TypeEnum;
import com.lifeshs.dao1.data.AppBannerDao;
import com.lifeshs.po.data.AppBannerPO;
import com.lifeshs.service1.data.AppBannerService;

@Service(value = "appBannerService")
public class AppBannerServiceImpl implements AppBannerService {

    @Resource(name = "appBannerDao")
    private AppBannerDao bannerDao;

    @Override
    public List<AppBannerPO> listBanner(TypeEnum type) {
        return bannerDao.findBannerByTypeList(type.value());
    }

    @Override
    public List<AppBannerPO> listModifyBanner(Date datePoint) {
        return bannerDao.findModifyBannerList(datePoint, null);
    }

}
