package com.lifeshs.service1.business;

import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.business.TemporaryDataVO;

/**
 * @author Administrator
 * @create 2018-03-15
 * 15:55
 * @desc
 */
public interface TemporaryService {

    /**
     *  获取推广数据
     * @param id
     * @param type
     * @param superior
     * @param name
     * @param date
     * @param bname
     * @param page
     * @param pagesize
     * @return
     */
    Paging<TemporaryDataVO> findByDataList(Integer id,Integer type,Integer superior,String name,String date,String bname,Integer page,int pagesize);
}
