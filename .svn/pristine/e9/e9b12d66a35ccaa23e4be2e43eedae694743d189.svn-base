package com.lifeshs.service1.business.impl;

import com.lifeshs.dao1.business.TemporaryDao;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.service1.business.TemporaryService;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.TemporaryDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-15
 * 15:58
 * @desc
 */
@Service("temporaryService")
public class TemporaryServiceImpl implements TemporaryService {

    @Resource(name = "temporaryDao")
    private TemporaryDao temporaryDao;

    @Override
    public Paging<TemporaryDataVO> findByDataList(Integer id,Integer type,Integer superior, String name, String date, String bname, Integer page, int pagesize) {
        System.out.println(superior);
        Paging<TemporaryDataVO> p = new Paging<>(page, pagesize);
        p.setQueryProc(new IPagingQueryProc<TemporaryDataVO>() {
            @Override
            public int queryTotal() {
                return temporaryDao.findByInteger(id,type,superior,name,date,bname);
            }

            @Override
            public List<TemporaryDataVO> queryData(int startRow, int pageSize) {
                return temporaryDao.findByAllData(id,type,superior,name,date,bname,(page -1) * pageSize,pagesize);
            }
        });
        return p;
    }
}
