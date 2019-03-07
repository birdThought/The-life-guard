package com.lifeshs.service1.data.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.data.HobbyDao;
import com.lifeshs.dao1.data.HotHobbyDao;
import com.lifeshs.po.data.HobbyPO;
import com.lifeshs.service1.data.HobbyService;
import com.lifeshs.utils.ListUtil;
import com.lifeshs.vo.data.hobby.HotHobbyVO;

@Service(value = "hobbyService")
public class HobbyServiceImpl implements HobbyService {

    @Resource(name = "hobbyDao")
    private HobbyDao hobbyDao;

    @Resource(name = "hotHobbyDao")
    private HotHobbyDao hotHobbyDao;
    
    @Override
    public HobbyPO getHobby(int id) {
        return hobbyDao.getHobby(id);
    }

    @Override
    public List<HobbyPO> listHobby() {
        return hobbyDao.findHobbyList();
    }

    @Override
    public List<HobbyPO> listHobby(List<Integer> idList) {
        // 移除重复的id
        idList = ListUtil.removeRepeatElement(idList, Integer.class);
        return hobbyDao.findHobbyByIdList(idList);
    }

    @Override
    public List<HotHobbyVO> listHotHobby(int maxSize) {
        List<HotHobbyVO> dataList = new ArrayList<>();
        if (maxSize == 0) {
            return dataList;
        }
        return hotHobbyDao.findHotHobbyList(maxSize);
    }

    @Override
    public List<HobbyPO> listModifyHobby(Date datePoint) {
        return hobbyDao.findModifyHobbyList(datePoint);
    }

}
